package com.wei.xianglixiangqin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wei.xianglixiangqin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMessageFragment extends BaseFragment {


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_message, container, false);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
