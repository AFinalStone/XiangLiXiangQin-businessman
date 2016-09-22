package com.wei.xianglixiangqin.Interface;

import android.view.View;

/**
 * 自定义的接口监听器，主要是为了获取RecycleView点击事件
 * Created by SHI on 2016/7/6 16:34
 */
public interface OnItemClickLitener {

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
