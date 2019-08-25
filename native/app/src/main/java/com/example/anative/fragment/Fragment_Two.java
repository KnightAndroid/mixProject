package com.example.anative.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anative.R;


/**
 *
 * 第二个Fragment
 *
 */
public class Fragment_Two extends Fragment {


    /**
     *
     * 实例化fragment
     * @return
     */
    public static Fragment_Two newInstance(){
        Bundle args = new Bundle();
        Fragment_Two fragment_two = new Fragment_Two();
        fragment_two.setArguments(args);
        return fragment_two;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_native2,null);
    }


}
