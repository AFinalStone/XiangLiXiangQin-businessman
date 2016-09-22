package com.wei.xianglixiangqin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wei.xianglixiangqin.R;

import butterknife.ButterKnife;

/**
 * 所有Fragment的父类
 * Created by SHI on 2016/7/6 10:20
 */
public abstract  class BaseFragment<T extends FragmentActivity> extends Fragment {

    /**当前设备上下文**/
    protected T mActivity;
    /**网络请求是否成功**/
    protected boolean connectSuccessFlag = false;

    /**初始化界面布局**/
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**初始化数据**/
    public abstract void initData(@Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mActivity == null){
            mActivity = (T)getActivity();
        }
        return initView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
    }
}
