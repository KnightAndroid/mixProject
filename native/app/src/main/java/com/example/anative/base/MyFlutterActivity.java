package com.example.anative.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * Android -> Flutter (普通Activity)
 *
 */
public class MyFlutterActivity extends AppCompatActivity implements MethodChannel.MethodCallHandler {
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";
    private FlutterView flutterView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceStae){
        super.onCreate(savedInstanceStae);

        String route = getIntent().getStringExtra("_route_");
        String params = getIntent().getStringExtra("_params_");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("pageParams",params);
        } catch(JSONException e){
            e.printStackTrace();

        }
        //创建FlutterView
        flutterView = Flutter.createView(this,getLifecycle(),route + "?" + jsonObject.toString());
        //设置显示视图
        setContentView(flutterView);
        //插件注册
        registerMethodChannel();

    }




    @Override
    public void onBackPressed(){
        if(flutterView != null){
            flutterView.popRoute();
        }else{
            super.onBackPressed();
        }
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
    }



    private void registerMethodChannel(){
        new MethodChannel(flutterView,TOAST_CHANNEL).setMethodCallHandler(this);
    }



    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch(methodCall.method){
            case "showToast":
                //调用原生的Toast
                String content = methodCall.argument("content");
                Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
                break;
            default:
                result.notImplemented();

        }
    }
}
