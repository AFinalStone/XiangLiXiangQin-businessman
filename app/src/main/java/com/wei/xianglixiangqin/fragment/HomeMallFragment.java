package com.wei.xianglixiangqin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wei.xianglixiangqin.R;

/**
 * 主页商城Fragment
 * @author SHI
 * @time 2016/7/6 10:12
 */
public class HomeMallFragment extends BaseFragment{

    /**跟视图**/
    private View rootView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
          rootView = inflater.inflate(R.layout.fragment_home_mall,container,false);
        }
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
