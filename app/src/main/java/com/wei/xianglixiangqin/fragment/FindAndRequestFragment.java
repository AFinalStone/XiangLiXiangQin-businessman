package com.wei.xianglixiangqin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wei.xianglixiangqin.Interface.OnItemClickLitener;
import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.activity.MainActivity;
import com.wei.xianglixiangqin.activity.FindRequestDetailActivity;
import com.wei.xianglixiangqin.activity.ShopInfoActivity;
import com.wei.xianglixiangqin.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页发现和需求
 *
 * @author SHI
 * @time 2016/7/7 13:34
 */
public class FindAndRequestFragment extends BaseFragment<MainActivity> {

    View rootView;
    @Bind(R.id.recycleView)
    RecyclerView recycleView;
    /**
     * 附近商城数据
     **/
    private List<MyViewHolderValue> listData = new ArrayList<MyViewHolderValue>();
    /**
     * 附近商城适配器
     **/
    private RecycleViewAdapter mRecycleViewAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_find_and_request, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //StaggeredGridLayoutManager，实现水平GridView效果,每列展示3个item
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        //添加默认动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleViewAdapter = new RecycleViewAdapter(mActivity, listData);
        recycleView.setAdapter(mRecycleViewAdapter);
        mRecycleViewAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mActivity, FindRequestDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mRecycleViewAdapter.removeData(position);
            }
        });
    }



    /**
     * 数据适配器
     * SHI
     * 2016年6月30日 15:36:08
     */
    public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

        private List<MyViewHolderValue> listData;
        private LayoutInflater mInflater;

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        public RecycleViewAdapter(Context context, List<MyViewHolderValue> datas) {
            mInflater = LayoutInflater.from(context);
            listData = datas;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                    R.layout.item_adapter_find_request, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.ivHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ShopInfoActivity.class);
                    startActivity(intent);
                }
            });

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }

            int legth = DensityUtil.dip2px(mActivity,100);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(legth,legth);
            int margin = DensityUtil.dip2px(mActivity,5);
            layoutParams.setMargins(margin,margin,margin,margin);

            ImageView imageView01 = new ImageView(mActivity);
            imageView01.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView01.setLayoutParams(layoutParams);
            imageView01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.linearLayoutImageViewContainer01.addView(imageView01);
            Picasso.with(mActivity).load(R.mipmap.home_goods_picture1).into(imageView01);


            ImageView imageView02 = new ImageView(mActivity);
            imageView02.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView02.setLayoutParams(layoutParams);
            imageView02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.linearLayoutImageViewContainer01.addView(imageView02);
            Picasso.with(mActivity).load(R.mipmap.home_goods_picture2).into(imageView02);



            ImageView imageView03 = new ImageView(mActivity);
            imageView03.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView03.setLayoutParams(layoutParams);
            imageView03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.linearLayoutImageViewContainer01.addView(imageView03);
            Picasso.with(mActivity).load("http://img3.duitang.com/uploads/item/201604/22/20160422125942_RiF32.thumb.700_0.jpeg").into(imageView03);

            holder.linearLayoutImageViewContainer01.setVisibility(View.VISIBLE);

            ImageView imageView04 = new ImageView(mActivity);
            imageView04.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView04.setLayoutParams(layoutParams);
            imageView04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.linearLayoutImageViewContainer02.addView(imageView04);
            Picasso.with(mActivity).load(R.mipmap.home_goods_picture4).into(imageView04);

            ImageView imageView05 = new ImageView(mActivity);
            imageView05.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView05.setLayoutParams(layoutParams);
            imageView05.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.linearLayoutImageViewContainer02.addView(imageView05);
            Picasso.with(mActivity).load(R.mipmap.home_goods_picture5).into(imageView05);

            holder.linearLayoutImageViewContainer02.setVisibility(View.VISIBLE);


        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public void addData(int position, MyViewHolderValue viewHolderValue) {
            listData.add(position, viewHolderValue);
            notifyItemInserted(position);
        }


        public void removeData(int position) {
            if (position < listData.size()) {
                listData.remove(position);
                notifyItemRemoved(position);
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            /**
             * 用户头像
             **/
            @Bind(R.id.iv_head)
            ImageView ivHead;
            /**
             * 用户是否关注图标
             **/
            @Bind(R.id.iv_actionAttention)
            ImageView ivActionAttention;
            /**
             * 用户是否关注文字描述
             **/
            @Bind(R.id.tv_actionAttention)
            TextView tvActionAttention;
            /**
             * 用户是否关注外围控件
             **/
            @Bind(R.id.linearLayout_actionAttention)
            LinearLayout linearLayoutActionAttention;
            /**
             * 用户名称
             **/
            @Bind(R.id.tv_name)
            TextView tvName;
            /**
             * 用户店铺名称
             **/
            @Bind(R.id.tv_shopName)
            TextView tvShopName;
            /**
             * 当前说说具体内容
             **/
            @Bind(R.id.tv_content)
            TextView tvContent;
            /**
             * 产品图片描述
             **/
            @Bind(R.id.linearLayout_imageViewContainer01)
            LinearLayout linearLayoutImageViewContainer01;
            @Bind(R.id.linearLayout_imageViewContainer02)
            LinearLayout linearLayoutImageViewContainer02;
            @Bind(R.id.linearLayout_imageViewContainer03)
            LinearLayout linearLayoutImageViewContainer03;
            /**发布时间**/
            @Bind(R.id.tv_timePublish)
            TextView tvTimePublish;
            /**点赞图标**/
            @Bind(R.id.iv_likeNum)
            ImageView ivLikeNum;
            /**点赞数量**/
            @Bind(R.id.tv_likeNum)
            TextView tvLikeNum;
            /**浏览图标**/
            @Bind(R.id.iv_viewNum)
            ImageView ivViewNum;
            /**浏览数量**/
            @Bind(R.id.tv_viewNum)
            TextView tvViewNum;
            /**留言图标**/
            @Bind(R.id.iv_msgNum)
            ImageView ivMsgNum;
            /**留言数量**/
            @Bind(R.id.tv_msgNum)
            TextView tvMsgNum;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

    }

    public class MyViewHolderValue {
        /**
         * 头像url
         **/
        String headUrl;
        /**
         * 是否关注
         **/
        boolean IfAttention;
        /**
         * 名字
         **/
        String name;
        /**
         * 店铺名字
         **/
        String shopName;
        /**
         * 说说描述图片集合
         **/
        List<String> listDescImgs;
        /**
         * 说说发布时间
         **/
        String timePublish;
        /**点赞数量**/
        long likeNum;
        /**是否点过赞**/
        boolean Iflike;
        /**浏览数量**/
        long viewNum;
        /**留言数量**/
        long msgNum;

        public MyViewHolderValue(String headUrl, boolean ifAttention
            , String name, String shopName, List<String> listDescImgs
            , long likeNum, String timePublish, boolean iflike
                , long viewNum, long msgNum) {
            this.headUrl = headUrl;
            IfAttention = ifAttention;
            this.name = name;
            this.shopName = shopName;
            this.listDescImgs = listDescImgs;
            this.likeNum = likeNum;
            this.timePublish = timePublish;
            Iflike = iflike;
            this.viewNum = viewNum;
            this.msgNum = msgNum;
        }
    }
}
