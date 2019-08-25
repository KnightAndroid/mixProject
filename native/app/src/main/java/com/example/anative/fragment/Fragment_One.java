package com.example.anative.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anative.R;
import com.example.anative.base.MyFlutterActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * 第一个Fragment
 *
 */
public class Fragment_One extends Fragment {


    private Button btnNativeToFlutter;
    private View mView;


    public static Fragment_One newInstance(){
        Bundle args = new Bundle();

        Fragment_One fragment_one = new Fragment_One();
        fragment_one.setArguments(args);
        return fragment_one;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_native1,null);
        init();
        return mView;
    }



    private void init(){
        initView();
        initListener();
    }



    private void initView(){
        btnNativeToFlutter = mView.findViewById(R.id.btn_native_to_flutter);
    }



    private void initListener(){
        btnNativeToFlutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到FlutterMainActivity
                Map<String,Object> map = new HashMap<>();
                //业务参数是json格式的
                map.put("content","这是测试内容");
                String jsonString = new Gson().toJson(map);
                String route = "test";

//                Intent intent = new Intent(getActivity(), FlutterMainActivity.class);
                Intent intent = new Intent(getActivity(), MyFlutterActivity.class);
                intent.putExtra("_route_",route);
                intent.putExtra("_params_",jsonString);
                startActivity(intent);

            }
        });
    }




}
