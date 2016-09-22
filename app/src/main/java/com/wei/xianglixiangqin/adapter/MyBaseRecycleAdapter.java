package com.wei.xianglixiangqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * RecycleView的适配器
 * Created by SHI on 2016/7/13 11:48
 */
public abstract class MyBaseRecycleAdapter<VH extends RecyclerView.ViewHolder, VA> extends RecyclerView.Adapter<VH>{

    protected List<VA> listData;
    protected LayoutInflater mLayoutInflater;

    public interface OnItemClickLitener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    protected OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MyBaseRecycleAdapter(Context context, List<VA> listData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType) ;

    @Override
    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        if(listData == null){
            return 0;
        }
        return listData.size();
    }

}
