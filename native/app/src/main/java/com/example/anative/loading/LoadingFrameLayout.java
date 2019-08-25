package com.example.anative.loading;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.anative.R;


/**
 * 自定义布局
 *
 */
public class LoadingFrameLayout extends FrameLayout {

    //动画对象
    private Animation anim;
    private ImageView loadingImageView;


    public LoadingFrameLayout(Context context) {
        super(context);
        init(context, null,0);
    }
    public LoadingFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs,0);
    }
    public LoadingFrameLayout(Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init(context, attrs, defStyleAttr);
    }




    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        //加载自定义数据的布局
        View root = LayoutInflater.from(context).inflate(R.layout.plug_view_loading,this,true);
        loadingImageView = root.findViewById(R.id.loadingImageView);
        anim = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setInterpolator(new LinearInterpolator());//不停顿
        anim.setRepeatCount(-1);
        anim.setFillAfter(true);//停在最后
        anim.setDuration(3000);
        loadingImageView.startAnimation(anim);

    }


    @Override
    public void setVisibility(int visibility){
        super.setVisibility(visibility);
        if(visibility == VISIBLE){
            loadingImageView.startAnimation(anim);
        }else{
            loadingImageView.clearAnimation();
        }

    }

}
