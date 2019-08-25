package com.example.anative.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment适配器
 * descript:ViewPager绑定Fragment桥梁
 *
 *
 */
public class FragmentAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
    private static final String TAG = "FragmentUpdateAdapter";
    private List<CharSequence> mTitleList;
    private List<Integer> mTiitleResList;
    private List<T> mFragmentList;


    public FragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    /**
     * POSITION_UNCHANGED  默认值，位置没有改变
     * POSITION_NONE       item已经不存在
     * position            item新的位置
     * 当position发生改变时这个方法应该返回改变后的位置，以便页面刷新。
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object){
        if(object != null){
            if(object instanceof Fragment){
                if(mFragmentList.contains(object)){
                    return mFragmentList.indexOf(object);
                } else {
                    return POSITION_NONE;
                }

            }else{
                return super.getItemPosition(object);
            }

        }else{
            return super.getItemPosition("");
        }

    }







    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    public FragmentAdapter<T> removeTitle(int position){
        if(mTitleList == null){
            mTitleList = new ArrayList<>();
        }

        mTitleList.remove(position);
        return this;
    }

    public FragmentAdapter<T> addFragment(int index,T f){
        if(mTitleList == null){
            mTitleList = new ArrayList<>();
        }
        if(mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }

        mFragmentList.add(index, f);
        return this;

    }

    public FragmentAdapter<T> addFragment(T f,CharSequence title){
        if(mTitleList == null){
            mTitleList = new ArrayList<>();
        }

        if(mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }
        mTitleList.add(title);
        mFragmentList.add(f);
        return this;

    }

    public FragmentAdapter<T> addFragment(int index,T f,int titleRes){
        if(mTiitleResList == null){
            mTiitleResList = new ArrayList<>();
        }

        if(mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }

        mTiitleResList.add(index,titleRes);
        mFragmentList.add(index,f);
        return this;

    }

    /**
     * 更新fragment页面
     *
     * @param index
     * @param f
     * @return
     */
    public synchronized FragmentAdapter<T> updateFragment(int index,T f){
        mFragmentList.set(index, f);
        return this;

    }


    public FragmentAdapter<T> updateFragment(int index,T f,CharSequence title){
        mFragmentList.set(index,f);
        mTitleList.set(index,title);
        return this;
    }


    public FragmentAdapter<T> updateFragment(int index,T f,int titleRes){
        mFragmentList.set(index,f);
        mTiitleResList.set(index,titleRes);
        return this;
    }

    /**
     * 添加fragment
     * @param f
     * @return
     */
    public FragmentAdapter<T> addFragment(T f){
        if(mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }

        mFragmentList.add(f);
        return this;
    }


    public T getFragment(int position){
        return mFragmentList == null ? null : mFragmentList.get(position);
    }


    public List<T> getmFragmentList(){
        return mFragmentList;
    }

    public List<CharSequence> getmTitleList(){
        return mTitleList;
    }


    public void updateTitle(int position,CharSequence title){
        mTitleList.set(position,title);

    }






}
