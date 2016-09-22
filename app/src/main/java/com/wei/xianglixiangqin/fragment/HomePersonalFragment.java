package com.wei.xianglixiangqin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.activity.MyCardDetailActivity;
import com.wei.xianglixiangqin.activity.OtherCardDetailActivity;
import com.wei.xianglixiangqin.adapter.MyBaseRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePersonalFragment extends BaseFragment {

    View rootView;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    private List<ViewHolderValue> listData = new ArrayList<ViewHolderValue>();
    private RecycleAdapter mRecycleAdapter;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_personal, container, false);
            ButterKnife.bind(this, rootView);
            tvTitle.setText(getResources().getString(R.string.title_homePersonal));
            tvTitle.setError("错误提示");
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            listData.add(new ViewHolderValue());
            mRecycleAdapter = new RecycleAdapter( mActivity, listData);
            recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
            recycleView.setItemAnimator(new DefaultItemAnimator());
            recycleView.setAdapter(mRecycleAdapter);
            mRecycleAdapter.setOnItemClickLitener(new MyBaseRecycleAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent;
                    switch (position){
                        case 0:
                            intent = new Intent(mActivity, MyCardDetailActivity.class);
                            mActivity.startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(mActivity, OtherCardDetailActivity.class);
                            mActivity.startActivity(intent);
                            break;
                    }
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        } else {
            ButterKnife.bind(this, rootView);
        }

        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    /**
     * 适配器
     **/
    protected class RecycleAdapter extends MyBaseRecycleAdapter<RecycleAdapter.MyViewHolder, ViewHolderValue> {

        public RecycleAdapter(Context context, List<ViewHolderValue> listData) {
            super(context, listData);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mLayoutInflater.inflate(R.layout.item_adapter_personal_item, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }

        protected class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

    private class ViewHolderValue {
        public ViewHolderValue() {
        }
    }
}
