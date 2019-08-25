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
 * 第三个Fragment
 *
 */
public class Fragment_Three extends Fragment {

    public static Fragment_Three newInstance(){
        Bundle args = new Bundle();
        Fragment_Three fragment_three = new Fragment_Three();
        fragment_three.setArguments(args);
        return fragment_three;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_native3,null);
    }
}
