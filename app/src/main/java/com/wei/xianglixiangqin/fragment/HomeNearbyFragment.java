package com.wei.xianglixiangqin.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.wei.xianglixiangqin.Interface.OnItemClickLitener;
import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页附近店铺
 *
 * @author SHI
 * @time 2016/7/8 16:45
 */
public class HomeNearbyFragment extends BaseFragment {

    /**
     * 页面标题
     **/
    @Bind(R.id.tv_title)
    TextView tvTitle;
    /**
     * 刷新控件
     **/
    @Bind(R.id.ib_locationRefresh)
    ImageButton ibLocationRefresh;
    /**
     * 我的当前位置
     **/
    @Bind(R.id.tv_myLocationDesc)
    TextView tvMyLocationDesc;
    /**
     * 附近店铺
     **/
    @Bind(R.id.recycleView)
    RecyclerView recycleView;
    /**
     * 附近商铺数据
     **/
    private List<MyViewHolderValue> listData_NearByShops = new ArrayList<MyViewHolderValue>();;
    /**附近商铺适配器**/
    private RecycleViewAdapter_NearByShop mRecycleViewAdapter_NearByShop;

    /**
     * 跟视图
     **/
    private View rootView;

    int num = 1;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ViewHelper.setRotation(ibLocationRefresh, 10f * num);
            num++;
            handler.sendEmptyMessageDelayed(0, 25);
            return false;
        }
    });

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_nearby, container, false);
            ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        tvTitle.setText(getResources().getString(R.string.title_nearbyShop));

        handler.sendEmptyMessageDelayed(0, 5000);

        listData_NearByShops.add(new MyViewHolderValue("",false,"","","","",""));
        listData_NearByShops.add(new MyViewHolderValue("",false,"","","","",""));
        listData_NearByShops.add(new MyViewHolderValue("",false,"","","","",""));
        listData_NearByShops.add(new MyViewHolderValue("",false,"","","","",""));
        listData_NearByShops.add(new MyViewHolderValue("",false,"","","","",""));

        //StaggeredGridLayoutManager，实现水平GridView效果,每列展示3个item
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        //添加默认动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleViewAdapter_NearByShop = new RecycleViewAdapter_NearByShop(mActivity, listData_NearByShops);
        recycleView.setAdapter(mRecycleViewAdapter_NearByShop);

    }


    /**
     * 附近商铺数据适配器
     * SHI
     * 2016年6月30日 15:36:08
     */
    public class RecycleViewAdapter_NearByShop extends RecyclerView.Adapter<RecycleViewAdapter_NearByShop.MyViewHolder> {

        private List<MyViewHolderValue> listData;
        private LayoutInflater mInflater;

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        public RecycleViewAdapter_NearByShop(Context context, List<MyViewHolderValue> datas) {
            mInflater = LayoutInflater.from(context);
            listData = datas;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                    R.layout.item_adapter_nearbyshop, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            holder.ivShopIcon.setImageResource(R.mipmap.home_market3);
//            holder.tvIndustryClass.setText(listData.get(position).getMallName());
//            holder.tv_mallDistance.setText(listData.get(position).getMallDistance());
//            holder.ib_moreMall.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtils.show(mActivity, "用户想要查看更多");
//                }
//            });
//            if (position == listData.size() - 1) {
//                holder.ib_moreMall.setVisibility(View.VISIBLE);
//            } else {
//                holder.ib_moreMall.setVisibility(View.INVISIBLE);
//            }
//
//            // 如果设置了回调，则设置点击事件
//            if (mOnItemClickLitener != null) {
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
//                    }
//                });
//
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                        return false;
//                    }
//                });
//            }
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public void addData(int position, String str) {
            notifyItemInserted(position);
        }


        public void removeData(int position) {
            if (position < listData.size()) {
                listData.remove(position);
                notifyItemRemoved(position);
            }
        }


       public class MyViewHolder extends RecyclerView.ViewHolder
        {
            /**店铺头像**/
            @Bind(R.id.iv_shopIcon)
            ImageView ivShopIcon;
            /**交换名片**/
            @Bind(R.id.btn_exchangeCard)
            Button btnExchangeCard;
            /**店主姓名**/
            @Bind(R.id.tv_shopUserName)
            TextView tvShopUserName;
            /**店铺名称**/
            @Bind(R.id.tv_shopName)
            TextView tvShopName;
            /**行业分类**/
            @Bind(R.id.tv_IndustryClass)
            TextView tvIndustryClass;
            /**店铺地址**/
            @Bind(R.id.tv_shopAddress)
            TextView tvShopAddress;
            /**店铺距离**/
            @Bind(R.id.tv_shopDistance)
            TextView tvShopDistance;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    public class MyViewHolderValue {
        /**店铺头像**/
        String ivShopIconUrl;
        /**是否已经交换名片**/
        boolean IfExchangeCard;
        /**店主姓名**/
        String tvShopUserName;
        /**店铺名称**/
        String tvShopName;
        /**行业分类**/
        String tvIndustryClass;
        /**店铺地址**/
        String tvShopAddress;
        /**店铺距离**/
        String tvShopDistance;

        public MyViewHolderValue(String ivShopIconUrl, boolean ifExchangeCard
                , String tvShopName, String tvShopUserName, String tvIndustryClass
                , String tvShopAddress, String tvShopDistance) {
            this.ivShopIconUrl = ivShopIconUrl;
            IfExchangeCard = ifExchangeCard;
            this.tvShopName = tvShopName;
            this.tvShopUserName = tvShopUserName;
            this.tvIndustryClass = tvIndustryClass;
            this.tvShopAddress = tvShopAddress;
            this.tvShopDistance = tvShopDistance;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
