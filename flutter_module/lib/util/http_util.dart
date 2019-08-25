import 'dart:io';

import 'package:dio/dio.dart';


class HttpUtil{
  Dio dio;
  BaseOptions baseOptions;

  static const String GET = 'GET';
  static const String POST = 'POST';
  static const String PUT = 'PUT';
  static const String PATCH = 'PATCH';
  static const String DELETE = 'DELETE';

  static HttpUtil instance;

  static HttpUtil getInstance(){
    print('getInstance');
    if(instance == null){
      instance = new HttpUtil();
    }
    return instance;
  }



  HttpUtil() {
    // 配置dio实例
    baseOptions = new BaseOptions(
      //配置dio实例
      baseUrl: "http://www.baidu.com/",
      //test
      connectTimeout: 5000,
      //连接超时时间
      receiveTimeout: 150000,
      //回调超时时间
      contentType: ContentType.json,
      //ContentType.parse("multipart/form-data")
      responseType: ResponseType.plain,

    );
    dio = new Dio(baseOptions);
    dio.interceptors.add(LogInterceptor(responseBody: true));
    //添加拦截器(日志)

    dio.interceptors.add(InterceptorsWrapper(onRequest: (RequestOptions options){
      print("\n============请求数据_START ==================");
      print("url = ${options.uri.toString()}");
      print("headers = ${options.headers}");
      print("params = ${options.data}");

      print("================请求数据_END ===================");
      print("================请求进度_START ==================");


    },onResponse: (Response response){
      print("=================请求进度_END ====================");
      print("\n===============响应数据_START ===================");
      print("code = ${response.statusCode}");
      print("data = ${response.data}");
      print("=================响应数据_END ====================");
    },onError: (DioError e){
      print("\n===============错误响应数据_START ===============");
      print("type = ${e.type}");
      print("message = ${e.message}");
      print("stackTrace = ${e.stackTrace}");
      print("=================错误响应数据_END ==================");
    }));




}


  doGet(url,{cancelToken,headers}) async {
    print('doGet-url: $url');
    Response response;


    try{
      response = await dio.get(
        url,
        cancelToken: cancelToken,
        options: new Options(headers:headers),
      );
    } on DioError catch(e){
      if(CancelToken.isCancel(e)){
        print('doGet-cancel!' + e.message);
      } else {
        print('doGet-error::$e');
        if (e.type == DioErrorType.CONNECT_TIMEOUT){
          print('网络异常-请求超时-请重试');
        } else if(e.type == DioErrorType.RECEIVE_TIMEOUT){
          print('网络异常-回调超时-请重试');
        } else if(e.type == DioErrorType.RESPONSE){
          print('网络异常-请退出重试');
        } else if(e.type == DioErrorType.CANCEL){
          print('网络异常-请退出重试');
        } else if(e.type == DioErrorType.DEFAULT){
          print('网络异常-请退出重试');

        }
      }
    }
    return response;
  }


  doPost(url,{cancelToken}) async{
    print('doPost-url: $url');
    Response response;
    try{
      response = await dio.post(
        url,
        cancelToken: cancelToken,
      );

    } on DioError catch(e){
      if(CancelToken.isCancel(e)){
        print('doPost-cancel!' + e.message);
      } else {
        print('doPost-erroe::$e');
        if(e.type == DioErrorType.CONNECT_TIMEOUT){
          print('网络异常-请求超时-请重试');
        } else if(e.type == DioErrorType.RECEIVE_TIMEOUT){
          print('网络异常-回调超时-请重试');
        } else if(e.type == DioErrorType.RESPONSE){
          print('网络异常-请退出重试');
        } else if(e.type == DioErrorType.CANCEL){
          print('网络异常-请退出重试');
        } else if(e.type == DioErrorType.DEFAULT){
          print('网络异常-请退出重试');
        }
      }
    }

    return response;
  }


  requestFormData(url,{formData,cancelToken,contentType,headers}) async{
     print('=======url:$url');
     print('=======formData:$formData');
     print('=======headers:$headers');
     Response response;

     try{
       response = await dio.request(
         url,
         data:formData,
         cancelToken: cancelToken,
         options:
         Options(method: POST,contentType: contentType,headers: headers),

       );
     } on DioError catch(e){
       if(CancelToken.isCancel(e)){
         print('requestFormData-cancel!' + e.message);
       }else{
         print('requestFormData-error::$e');
         if(e.type == DioErrorType.CONNECT_TIMEOUT){
           print('网络异常-请求超时-请重试');
         } else if(e.type == DioErrorType.RECEIVE_TIMEOUT){
           print('网络异常-回调超时-请重试');
         } else if(e.type == DioErrorType.RESPONSE){
           print('网络异常-请退出重试');
         } else if(e.type == DioErrorType.CANCEL){
           print('网络异常-请退出重试');
         } else if(e.type == DioErrorType.DEFAULT){
           print('网络异常-请退出重试');
         }
       }
     }
     return response;
  }


  requestJsonBody(url,{jsonBody,cancelToken,headers}) async {
    print('=========url:$url');
    print('=========body:$jsonBody');
    Response response;

    try{


    } on DioError catch (e){
      if(CancelToken.isCancel(e)){
        print('requestJsonBody-cancel!' + e.message);
      } else {
        print('requestJsonBody-error::$e');
        if(e.type == DioErrorType.CONNECT_TIMEOUT){
          print('网络异常-回调超时-请重试');
        }else if(e.type == DioErrorType.RECEIVE_TIMEOUT){
          print('网络异常-回调超时-请重试');
        }else if(e.type == DioErrorType.RESPONSE){
          print('网络异常-请退出重试');
        }else if(e.type == DioErrorType.CANCEL){
          print('网络异常-请退出重试');
        }else if(e.type == DioErrorType.DEFAULT){
          print('网络异常-请退出重试');

        }
      }

    }
    return response;
  }


  downLoadFile(url,fileNamePath,progressCallback,{cancelToken}) async {
    print('downLoadFile-url:$url');
    Response response;
    try{
      response = await dio.download(
        url,
        fileNamePath,
        cancelToken: cancelToken,
        onReceiveProgress: progressCallback,
      );
    } on DioError catch(e){
      if(CancelToken.isCancel(e)){
        print('downLoadFile-cancel!' + e.message);

      }else{
        print('downLoadFile-error::$e');
        if(e.type == DioErrorType.CONNECT_TIMEOUT){
          print('网络异常-请求超时-请重试');
        }else if(e.type == DioErrorType.RECEIVE_TIMEOUT){
          print('网络异常-回调超时-请重试');
        }else if(e.type == DioErrorType.RESPONSE){
          print('网络异常-请退出重试');
        }else if(e.type == DioErrorType.CANCEL){
          print('网络异常-请退出重试');
        }else if(e.type == DioErrorType.DEFAULT){
          print('网络异常-请退出重试');
        }
      }

    }
    return response;


  }



}


