package com.wei.xianglixiangqin.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.wei.xianglixiangqin.Interface.OnItemClickLitener;
import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.activity.MainActivity;
import com.wei.xianglixiangqin.util.DensityUtil;
import com.wei.xianglixiangqin.util.ToastUtils;
import com.wei.xianglixiangqin.view.TabIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页商城
 *
 * @author SHI
 * @time 2016/7/6 11:33
 */
public class HomeFindFragment extends BaseFragment<MainActivity>{

    /**
     * 轮播图
     **/
    @Bind(R.id.sliderLayout_roolView)
    SliderLayout mSliderLayout_roolView;
    List<Integer> listData_ImageUrl = new ArrayList<Integer>();

    @Bind(R.id.recycleView)
    RecyclerView mRecyclerView_NearByMalls;
    /**
     * 附近商城数据
     **/
    private List<MyViewHolderValue> listData_NearByMalls = new ArrayList<MyViewHolderValue>();;
    /**附近商城适配器**/
    private RecycleViewAdapter_NearByMalls mRecycleViewAdapter_NearByMalls;

    /**
     * TabIndicatorView
     **/
    @Bind(R.id.tabIndicatorView)
    TabIndicatorView tabIndicatorView;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    /**跟**/
    private View rootView;

    private List<Fragment> listData_Fragment = new ArrayList<Fragment>();
    private MyFragmentAdapter mMyFragmentAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_find, container, false);
            ButterKnife.bind(this, rootView);
            mSliderLayout_roolView.setLayoutParams(new LinearLayout.LayoutParams(mActivity.displayDeviceWidth, mActivity.displayDeviceWidth * 4 / 9));

            //初始化轮播图
            mSliderLayout_roolView.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mSliderLayout_roolView.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mSliderLayout_roolView.setCustomAnimation(new DescriptionAnimation());
            mSliderLayout_roolView.setDuration(4000);
            mSliderLayout_roolView.setPresetTransformer(SliderLayout.Transformer.Default);

            //初始化附近商城
            mRecyclerView_NearByMalls.setLayoutManager(new GridLayoutManager(mActivity, 3));
            mRecyclerView_NearByMalls.setItemAnimator(new DefaultItemAnimator());
            mRecycleViewAdapter_NearByMalls = new RecycleViewAdapter_NearByMalls(mActivity, listData_NearByMalls);
            mRecyclerView_NearByMalls.setAdapter(mRecycleViewAdapter_NearByMalls);

            //初始化Tab标签控件
            tabIndicatorView.initIndicatorGroupButton(mActivity.displayDeviceWidth / 2, RadioGroup.LayoutParams.MATCH_PARENT, 16);
            tabIndicatorView.initIndicatorBottomBar(mActivity.displayDeviceWidth / 4, 2);

            listData_Fragment.add(new FindAndRequestFragment());
            listData_Fragment.add(new FindAndRequestFragment());
            mMyFragmentAdapter = new MyFragmentAdapter(mActivity.getSupportFragmentManager(),listData_Fragment);

            viewPager.setAdapter(mMyFragmentAdapter);
            tabIndicatorView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    viewPager.setCurrentItem(checkedId);
                }
            });
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tabIndicatorView.setCurrentSelectItem(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }else{
            ButterKnife.bind(this, rootView);
        }

        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if(connectSuccessFlag == false) {
            //刷新轮播图
            listData_ImageUrl.add(R.mipmap.home_banner);
            listData_ImageUrl.add(R.mipmap.home_goods_picture2);
            listData_ImageUrl.add(R.mipmap.home_goods_picture3);

            for (int i = 0; i < listData_ImageUrl.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(mActivity);
                //初始化轮播图的Item
                textSliderView
                        .description("")
                        .image(listData_ImageUrl.get(i))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                //添加额外信息,用于点击时候使用
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", "测试数据" + i);

                mSliderLayout_roolView.addSlider(textSliderView);
            }

            //刷新附近商城
            listData_NearByMalls.add(new MyViewHolderValue(R.mipmap.home_market1, "杭州乡里乡亲有限公司", "2.5KM"));
            listData_NearByMalls.add(new MyViewHolderValue(R.mipmap.home_market2, "杭州颐高大厦", "2.5KM"));
            listData_NearByMalls.add(new MyViewHolderValue(R.mipmap.home_market3, "文三数码商城", "2.5KM"));
            mRecycleViewAdapter_NearByMalls.notifyDataSetChanged();


            //刷新发现需求tab控件

            List<String> listData = new ArrayList<String>();
            listData.add(getResources().getString(R.string.tab_find));
            listData.add(getResources().getString(R.string.tab_request));
            tabIndicatorView.refreshRadioGroup(listData);
            connectSuccessFlag = true;
        }
    }




    private class MyFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> listData;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> listData) {
            super(fm);
            this.listData = listData;
        }

        @Override
        public Fragment getItem(int position) {

            return listData.get(position);
        }

        @Override
        public int getCount() {
            return listData.size();
        }

    }

    /**
     * 附近商城数据适配器
     * SHI
     * 2016年6月30日 15:36:08
     */
    public class RecycleViewAdapter_NearByMalls extends RecyclerView.Adapter<RecycleViewAdapter_NearByMalls.MyViewHolder> {

        private List<MyViewHolderValue> listData;
        private LayoutInflater mInflater;

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        public RecycleViewAdapter_NearByMalls(Context context, List<MyViewHolderValue> datas) {
            mInflater = LayoutInflater.from(context);
            listData = datas;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                    R.layout.item_adapter_nearbymalls, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.iv_mallIcon.setImageResource(listData.get(position).getMallIcon());
            holder.tv_mallName.setText(listData.get(position).getMallName());
            holder.tv_mallDistance.setText(listData.get(position).getMallDistance());
            holder.ib_moreMall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.show(mActivity, "用户想要查看更多");
                }
            });
            if (position == listData.size() - 1) {
                holder.ib_moreMall.setVisibility(View.VISIBLE);
            } else {
                holder.ib_moreMall.setVisibility(View.INVISIBLE);
            }

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

        public class MyViewHolder extends RecyclerView.ViewHolder {

            /**
             * 商城图标
             **/
            ImageView iv_mallIcon;
            /**
             * 商城名字
             **/
            TextView tv_mallName;
            /**
             * 商城距离
             **/
            TextView tv_mallDistance;
            /**
             * 查看更多
             **/
            ImageButton ib_moreMall;

            public MyViewHolder(View view) {
                super(view);
                iv_mallIcon = (ImageView) view.findViewById(R.id.iv_mallIcon);
                tv_mallName = (TextView) view.findViewById(R.id.tv_mallName);
                tv_mallDistance = (TextView) view.findViewById(R.id.tv_mallDistance);
                ib_moreMall = (ImageButton) view.findViewById(R.id.ib_moreMall);
            }
        }

    }

    public class MyViewHolderValue {
        int mallIcon;
        String mallName;
        String mallDistance;

        public MyViewHolderValue(int mallIcon, String mallName, String mallDistance) {
            this.mallIcon = mallIcon;
            this.mallName = mallName;
            this.mallDistance = mallDistance;
        }

        public int getMallIcon() {
            return mallIcon;
        }

        public void setMallIcon(int mallIcon) {
            this.mallIcon = mallIcon;
        }

        public String getMallName() {
            return mallName;
        }

        public void setMallName(String mallName) {
            this.mallName = mallName;
        }

        public String getMallDistance() {
            return mallDistance;
        }

        public void setMallDistance(String mallDistance) {
            this.mallDistance = mallDistance;
        }
    }

}
