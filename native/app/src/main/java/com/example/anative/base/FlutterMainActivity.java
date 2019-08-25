package com.example.anative.base;


import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

/**
 *
 * Android ->Flutter（FlutterActivity为载体）
 */
public class FlutterMainActivity extends FlutterActivity implements MethodChannel.MethodCallHandler{
    private static final String TAG = "FlutterMainActivity";
    private String routeStr = "";
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";



    @Override
    protected void onCreate(Bundle savedInstance){
        //执行FlutterMain初始化
        FlutterMain.startInitialization(getApplicationContext());
       super.onCreate(savedInstance);

       //插件注册
       GeneratedPluginRegistrant.registerWith(this);
       registerCustomPlugin(this);


    }


    private void registerCustomPlugin(PluginRegistry register){
         registerMethodChannel();

    }


    private void registerMethodChannel(){
        //调用原生toast
        new MethodChannel(this.registrarFor(TOAST_CHANNEL).messenger(),TOAST_CHANNEL).setMethodCallHandler(this);
    }


    @Override
    public FlutterView createFlutterView(Context context){
        getIntentData();
        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        //创建FlutterNativeView
        FlutterNativeView nativeView = this.createFlutterNativeView();
        //创建FlutterView
        FlutterView flutterView = new FlutterView(FlutterMainActivity.this,(AttributeSet)null,nativeView);
        //给FlutterView传递路由参数
        flutterView.setInitialRoute(routeStr);
        //FlutterView设置布局参数
        flutterView.setLayoutParams(matchParent);
        //将FlutterView设置进ContentView中,设置内容视图
        this.setContentView(flutterView);
        return flutterView;
    }


    /**
     * 获取参数信息
     * 传递给flutterVIew
     */
    private void getIntentData(){
        String route = getIntent().getStringExtra("_route_");
        String params = getIntent().getStringExtra("_params_");
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("pageParams",params);
        } catch (JSONException e){
            e.printStackTrace();
        }
        //字符串是路由参数 + 业务参数
        routeStr = route + "?" + jsonObject.toString();
        Log.d(TAG,"onCreate-route:" + route + "-params" + params);
        Log.d(TAG,"pnCreate-routeStr:" + routeStr);


    }

    /**
     * 插件要实现的方法
     * @param methodCall
     * @param result
     */
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
       switch(methodCall.method){
           case "showToast"://调用原生的toast
               String content = methodCall.argument("content");
               Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
               break;
               default:
                   result.notImplemented();

       }
    }
}
