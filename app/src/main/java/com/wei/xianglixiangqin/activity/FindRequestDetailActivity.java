package com.wei.xianglixiangqin.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wei.xianglixiangqin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindRequestDetailActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    /**
     * 店铺头像
     **/
    @Bind(R.id.iv_head)
    ImageView ivHead;
    /**
     * 是否关注图标
     **/
    @Bind(R.id.iv_actionAttention)
    ImageView ivActionAttention;
    /**
     * 关注/已关注
     **/
    @Bind(R.id.tv_actionAttention)
    TextView tvActionAttention;
    /**
     * 店铺用户名称
     **/
    @Bind(R.id.tv_name)
    TextView tvName;
    /**
     * 店铺名称
     **/
    @Bind(R.id.tv_shopName)
    TextView tvShopName;
    /**
     * 说说内容
     **/
    @Bind(R.id.tv_content)
    TextView tvContent;
    /**
     * 说说附带图片
     **/
    @Bind(R.id.linearLayout_imageViewContainer01)
    LinearLayout linearLayoutImageViewContainer01;
    @Bind(R.id.linearLayout_imageViewContainer02)
    LinearLayout linearLayoutImageViewContainer02;
    @Bind(R.id.linearLayout_imageViewContainer03)
    LinearLayout linearLayoutImageViewContainer03;
    /**
     * 发布时间
     **/
    @Bind(R.id.tv_timePublish)
    TextView tvTimePublish;
    /**
     * 点赞图标
     **/
    @Bind(R.id.iv_likeNum)
    ImageView ivLikeNum;
    /**
     * 点赞数量
     **/
    @Bind(R.id.tv_likeNum)
    TextView tvLikeNum;
    /**
     * 浏览数量
     **/
    @Bind(R.id.tv_viewNum)
    TextView tvViewNum;
    /**
     * 留言数量
     **/
    @Bind(R.id.tv_msgNum)
    TextView tvMsgNum;
    /**
     * 具体留言内容
     **/
    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    List<MyViewHolderValue> listData = new ArrayList<MyViewHolderValue>();

    RecycleViewAdapter mRecycleViewAdapter;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_findrequest_detail);
        ButterKnife.bind(this);
        tvTitle.setText(getResources().getString(R.string.title_request));
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setItemAnimator(new DefaultItemAnimator());

        listData.add(new MyViewHolderValue());
        listData.add(new MyViewHolderValue());
        listData.add(new MyViewHolderValue());
        mRecycleViewAdapter = new RecycleViewAdapter(mContext, listData);
        recycleView.setAdapter(mRecycleViewAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

        private List<MyViewHolderValue> listData;
        private LayoutInflater mInflater;

        public RecycleViewAdapter(Context context, List<MyViewHolderValue> listData) {
            mInflater = LayoutInflater.from(context);
            this.listData = listData;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_adapter_leave_msg, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position == 0) {
                SpannableString sps = new SpannableString("曾挺梅: 你好，我这边需要8台，请问货物当天能够送到不？");
                sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed_FFE83821)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.leaveMsgContent.setText(sps);
            }
            if (position == 1) {
                SpannableString sps = new SpannableString("李晓华 回复 曾挺梅: 可以的，上午下单，下午就能送到！");
                sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed_FFE83821)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed_FFE83821)), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.leaveMsgContent.setText(sps);
            }
            if (position == 2) {
                SpannableString sps = new SpannableString("曾挺梅 回复 李晓华: 哈哈，那真的解决我的大忙了，我现在就去你店里面下单去。");
                sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed_FFE83821)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed_FFE83821)), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.leaveMsgContent.setText(sps);
            }

        }

        @Override
        public int getItemCount() {
            if (listData == null) {
                return 0;
            }
            return listData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.leaveMsgContent)
            TextView leaveMsgContent;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    public class MyViewHolderValue {

    }


}
