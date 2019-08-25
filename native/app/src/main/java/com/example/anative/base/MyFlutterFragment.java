package com.example.anative.base;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;
import io.flutter.facade.Flutter;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;

public class MyFlutterFragment extends Fragment {
    private static final String TAG = "MyFlutterFragment";
    //路由
    public static final String AGR_ROUTE = "_route_";
    //参数
    public static final String PARAMS = "_params_";
    private String mRoute = "/";
    private String mParams = "";
    private FlutterView mFlutterView;


    public static MyFlutterFragment newInstance(String route,String params){
        Bundle args = new Bundle();
        MyFlutterFragment fragment = new MyFlutterFragment();
        args.putString(MyFlutterFragment.AGR_ROUTE,route);
        args.putString(MyFlutterFragment.PARAMS,params);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mRoute = getArguments().getString(AGR_ROUTE);
            mParams = getArguments().getString(PARAMS);
            //这里拼接参数
            JSONObject jsonObject = new JSONObject();
            JSONObject pageParamsObject;

            if(!TextUtils.isEmpty(mParams)){
                try {
                    //json字符串
                    pageParamsObject = new JSONObject(mParams);
                    jsonObject.put("pageParams",pageParamsObject);
                    mRoute = mRoute + "？" + jsonObject.toString();
                    Log.d("ssd",mRoute);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Log.d(TAG,"onCreateView-mRoute:"+mRoute);
        mFlutterView = Flutter.createView(getActivity(),getLifecycle(),mRoute);
        //综合解决闪屏，布局覆盖问题
        mFlutterView.setZOrderOnTop(true);
        mFlutterView.setZOrderMediaOverlay(false);
        mFlutterView.getHolder().setFormat(Color.parseColor("#00000000"));

        //注册channel
       // GeneratedPluginRegistrant.registerWith(mFlutterView.getPluginRegistry());
        //返回FlutterView
        return mFlutterView;
    }









}
