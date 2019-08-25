package com.example.anative;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.anative.adapter.FragmentAdapter;
import com.example.anative.base.MyFlutterFragment;
import com.example.anative.fragment.Fragment_One;
import com.example.anative.fragment.Fragment_Three;
import com.example.anative.fragment.Fragment_Two;

import io.flutter.facade.FlutterFragment;


public class MainActivity extends AppCompatActivity {

    //ViewPager
    private ViewPager mViewPager;
    //底部栏
    private RadioGroup radioGroup;
    //三个fragment
    private Fragment_One fragment_one;
    private Fragment_Two fragment_two;
    private Fragment_Three fragment_three;

    //Fragment适配器
    private FragmentAdapter<Fragment> mFragmentAdapter;

    //标识是否初始化FlutterView
    private boolean isFirstInitFlutterView = true;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化一些view
     */
    private void init(){
        initView();
        initFragment();
        initViewPagerAdapter();
        initListener();
        Log.d("sssd","ssss");
    }

    /**
     *
     * 绑定控件
     */
    private void initView(){
        radioGroup = findViewById(R.id.rg_foot_bar);
        mViewPager = findViewById(R.id.viewpager);
    }


    /**
     * 始化化Fragment
     */
    private void initFragment(){
        fragment_one = Fragment_One.newInstance();
        fragment_two = Fragment_Two.newInstance();
        fragment_three = Fragment_Three.newInstance();
    }


    public void initViewPagerAdapter(){
        mFragmentAdapter = new FragmentAdapter<>(getSupportFragmentManager());
        mFragmentAdapter.addFragment(fragment_one);
        mFragmentAdapter.addFragment(fragment_two);
        mFragmentAdapter.addFragment(fragment_three);

        mViewPager.setOffscreenPageLimit(mFragmentAdapter.getCount());
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
    }


    private int currIndex = 0;


    private void initListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //这里的逻辑是如果点击
            @Override
            public void onPageSelected(int position) {
               //如果点击第三个RadioButton
               if(position == 2){
                   //如果没有初始化就初始化FlutterFragment
                   if(isFirstInitFlutterView){
                       initFlutterFragment();
                       isFirstInitFlutterView = false;
                   }

               }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_one:
                        currIndex = 0;
                        break;
                    case R.id.radio_button_two:
                        currIndex = 1;
                        break;
                    case R.id.radio_button_three:
                        currIndex = 2;
                        if(isFirstInitFlutterView){
                            isFirstInitFlutterView = false;
                            initFlutterFragment();
                        }
                        break;
                }
                mViewPager.setCurrentItem(currIndex);
            }
        });











    }


    /**
     *
     * 初始化FlutterFragment
     *
     */
    private void initFlutterFragment(){
        mFragmentAdapter.updateFragment(2,MyFlutterFragment.newInstance("tab_fragment","ssssss"));
        //更新Fragment
        mFragmentAdapter.notifyDataSetChanged();
    }


    //  mFragmentAdapter.updateFragment(2, MyFlutterFragment.newInstance("tab_fragment","11111"));







}
