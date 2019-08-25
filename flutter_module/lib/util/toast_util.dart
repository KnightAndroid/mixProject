import 'package:flutter/services.dart';




class ToastUtil {
  static const MethodChannel _methodChannel = const MethodChannel('com.test.native_flutter/toast');

  static void showToastInfo(content){
    _methodChannel.invokeMethod('showToast',{'content':content});

  }

}


