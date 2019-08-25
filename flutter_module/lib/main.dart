import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:io';
import 'dart:ui';
import 'package:flutter/services.dart';
import 'package:flutter_module/ui/tab_fragment.dart';
import 'package:flutter_module/ui/test.dart';

void main(){
  //接受路由参数 路由参数可以自定义规则
  runApp(_widgetForRoute(window.defaultRouteName));

 // runApp(_widgetForRoute("test"));
//
//  if(Platform.isAndroid){
//    //Android同步沉浸式
//    SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle(statusBarColor: Colors.transparent);
//    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
//
//  }


}


/**
 * 路由参数处理
 *
 */
Widget _widgetForRoute(String route){
  print("route:" + route.toString());
  //解析路由参数
  String pageName = _getPageName(route);
  Map<String,dynamic> pageParams = json.decode(_parseNativeParams(route));
  //路由参数
  print("pageName:" + pageName.toString());
  //业务参数
  print("pageParams:" + pageParams.toString());

  //截取跳转到哪个页面参数
  switch(pageName){
    case 'tab_fragment':
       return new TabFragment();
    case 'test'://test页面
       String content = pageParams["content"] ?? "defaultContent";
       return new Test(content:content,);
  }


}


/**
 * 解析路由参数
 *
 */
String _getPageName(String route){
  String pageName = route;
  if (route.indexOf("?") != -1)
      //截取?之前的字符串 表明后面带有业务参数
      pageName = route.substring(0,route.indexOf("?"));
  print("pageName:" + pageName);
  return pageName;
}

/**
 * 返回业务参数
 *
 */
String _parseNativeParams(String route){
  Map<String,dynamic> nativeParams = {};
  if(route.indexOf("?") != -1){
    nativeParams = json.decode(route.substring(route.indexOf("?") + 1));
  }
  return nativeParams['pageParams'] ?? "{}";

}