package com.wei.xianglixiangqin.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.adapter.MyBaseRecycleAdapter;
import com.wei.xianglixiangqin.fragment.FindAndRequestFragment;
import com.wei.xianglixiangqin.view.TabIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShopInfoActivity extends BaseActivity {

    @Bind(R.id.iv_titleLeft)
    ImageView ivTitleLeft;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_titleRight)
    TextView tvTitleRight;

    @Bind(R.id.iv_head)
    ImageView ivHead;

    @Bind(R.id.linearLayout_actionCard)
    LinearLayout linearLayoutActionCard;

    @Bind(R.id.tv_name)
    TextView tvName;

    @Bind(R.id.tv_shopName)
    TextView tvShopName;

    @Bind(R.id.tv_strongth)
    TextView tvstrongth;

    @Bind(R.id.tv_strongthDesc)
    TextView tvstrongthDesc;

    @Bind(R.id.linearLayout_intoShop)
    LinearLayout linearLayoutIntoShop;

    @Bind(R.id.recycleView_shopGoods)
    RecyclerView recycleViewShopGoods;

    /**
     * TabIndicatorView
     **/
    @Bind(R.id.tabIndicatorView)
    TabIndicatorView tabIndicatorView;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private List<ViewHolderValue> listData = new ArrayList<ViewHolderValue>();
    private RecycleAdapter mRecycleAdapter;

    private List<Fragment> listData_Fragment = new ArrayList<Fragment>();
    private MyFragmentAdapter mMyFragmentAdapter;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shop_info);
        ButterKnife.bind(this);
        ivTitleLeft.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_shopInfo));
        tvTitleRight.setText(getResources().getString(R.string.publish));

        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        mRecycleAdapter = new RecycleAdapter( mContext, listData);
        recycleViewShopGoods.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recycleViewShopGoods.setItemAnimator(new DefaultItemAnimator());
        recycleViewShopGoods.setAdapter(mRecycleAdapter);

        //初始化Tab标签控件
        tabIndicatorView.initIndicatorGroupButton(displayDeviceWidth / 2, RadioGroup.LayoutParams.MATCH_PARENT, 16);
        tabIndicatorView.initIndicatorBottomBar(displayDeviceWidth / 4, 2);

        listData_Fragment.add(new FindAndRequestFragment());
        listData_Fragment.add(new FindAndRequestFragment());
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),listData_Fragment);

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

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //刷新发现需求tab控件

        List<String> listData = new ArrayList<String>();
        listData.add(getResources().getString(R.string.tab_find));
        listData.add(getResources().getString(R.string.tab_request));
        tabIndicatorView.refreshRadioGroup(listData);
    }


    /**适配器**/
    protected class RecycleAdapter extends MyBaseRecycleAdapter<RecycleAdapter.MyViewHolder,ViewHolderValue>{

        public RecycleAdapter(Context context, List<ViewHolderValue> listData) {
            super(context, listData);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

           View view = mLayoutInflater.inflate(R.layout.item_adapter_shopinfo_linkgoods,parent,false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        protected class MyViewHolder extends RecyclerView.ViewHolder{

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
    private class ViewHolderValue{
        public ViewHolderValue() {
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

}
