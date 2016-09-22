package com.wei.xianglixiangqin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wei.xianglixiangqin.Interface.OnItemClickLitener;
import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.fragment.HomeFindFragment;
import com.wei.xianglixiangqin.fragment.HomeMallFragment;
import com.wei.xianglixiangqin.fragment.HomeMessageFragment;
import com.wei.xianglixiangqin.fragment.HomeNearbyFragment;
import com.wei.xianglixiangqin.fragment.HomePersonalFragment;
import com.wei.xianglixiangqin.view.PopSelectPublishMsgWindow;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 商城图标
     **/
    @Bind(R.id.iv_homeMall)
    ImageView ivHomeMall;
    /**
     * 商城
     **/
    @Bind(R.id.tv_homeMall)
    TextView tvHomeMall;
    /**
     * 商城热区
     **/
    @Bind(R.id.linearLayout_homeMall)
    LinearLayout linearLayoutHomeMall;

    /**
     * 附近图标
     **/
    @Bind(R.id.iv_homeNearBy)
    ImageView ivHomeNearBy;
    /**
     * 附近
     **/
    @Bind(R.id.tv_homeNearBy)
    TextView tvHomeNearBy;
    /**
     * 附近热区
     **/
    @Bind(R.id.linearLayout_homeNearBy)
    LinearLayout linearLayoutHomeNearBy;

    /**
     * 发布需求或发现
     **/
    @Bind(R.id.iv_homePublish)
    ImageView ivHomePublish;
    /**
     * 发现图标
     **/
    @Bind(R.id.linearLayout_homeFind)
    LinearLayout linearLayoutHomeFind;
    /**
     * 发现热区
     **/
    @Bind(R.id.relativeLayout_homeFind)
    RelativeLayout relativeLayoutHomeFind;

    /**
     * 聊天图标
     **/
    @Bind(R.id.iv_homeMessage)
    ImageView ivHomeMessage;
    /**
     * 聊天未读消息数量
     **/
    @Bind(R.id.tv_messageNumber)
    TextView tvMessageNumber;
    /**
     * 聊天
     **/
    @Bind(R.id.tv_homeMessage)
    TextView tvHomeMessage;
    /**
     * 聊天热区
     **/
    @Bind(R.id.linearLayout_homeMessage)
    LinearLayout linearLayoutHomeMessage;

    /**
     * 个人中心图标
     **/
    @Bind(R.id.iv_homePersonal)
    ImageView ivHomePersonal;
    /**
     * 个人中心
     **/
    @Bind(R.id.tv_homePersonal)
    TextView tvHomePersonal;
    /**
     * 个人中心热区
     **/
    @Bind(R.id.linearLayout_homePersonal)
    LinearLayout linearLayoutHomePersonal;


    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;

    /**主页商城界面**/
    HomeMallFragment homeMallFragment;
    /**主页附近界面**/
    HomeNearbyFragment homeNearbyFragment;
    /**主页发现界面**/
    HomeFindFragment homeFindFragment;
    /**主页发现界面**/
    HomeMessageFragment homeMessageFragment;
    /**主页个人中心界面**/
    HomePersonalFragment homePersonalFragment;

    /**底部导航栏当前选中热区ID**/
    private int ID_BEFAULTSELECTED = R.id.relativeLayout_homeFind;
    /**是否已经打开发布需求界面**/
    private boolean IfOpenPublishView = true;
    
    PopSelectPublishMsgWindow publishMsgPopWindow;
    View rootView;

    @Override
    public void initView(Bundle savedInstanceState) {
        rootView = View.inflate( mContext, R.layout.activity_main, null);
        setContentView(rootView);
        ButterKnife.bind(this);
        linearLayoutHomeMall.setOnClickListener(this);
        linearLayoutHomeNearBy.setOnClickListener(this);
        relativeLayoutHomeFind.setOnClickListener(this);
        linearLayoutHomeMessage.setOnClickListener(this);
        linearLayoutHomePersonal.setOnClickListener(this);
        setDefaultFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    private void setDefaultFragment()
    {
        showHomeFindIconView();
    }

    @Override
    public void onClick(View v) {

        int viewID = v.getId();
        if(!refreshBottomBar(viewID)){
            return ;
        }

        int red = getResources().getColor(R.color.colorRed_FFE83821);

        switch (viewID) {
            case R.id.linearLayout_homeMall:
                ivHomeMall.setImageResource(R.mipmap.home_nav_mall_slected);
                tvHomeMall.setTextColor(red);
                showHomeMallView();
                break;
            case R.id.linearLayout_homeNearBy:
                ivHomeNearBy.setImageResource(R.mipmap.home_nav_nearby_slected);
                tvHomeNearBy.setTextColor(red);
                showHomeNearbyView();
                break;
            case R.id.relativeLayout_homeFind:
                showHomeFindIcon();
                showHomeFindIconView();
                break;
            case R.id.linearLayout_homeMessage:
                ivHomeMessage.setImageResource(R.mipmap.home_nav_message_slected);
                tvHomeMessage.setTextColor(red);
                showHomeMessageView();
                break;
            case R.id.linearLayout_homePersonal:
                ivHomePersonal.setImageResource(R.mipmap.home_nav_personal_slected);
                tvHomePersonal.setTextColor(red);
                showHomePersonalView();
                break;
        }
    }

    /**显示主页商城**/
    private void showHomeMallView() {
        if(homeMallFragment == null){
            homeMallFragment = new HomeMallFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, homeMallFragment);
        transaction.commit();
    }

    /**显示主页附近**/
    private void showHomeNearbyView() {
        if(homeNearbyFragment == null){
            homeNearbyFragment = new HomeNearbyFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, homeNearbyFragment);
        transaction.commit();
    }

    /**显示主页发现**/
    private void showHomeFindIconView() {
        if(homeFindFragment == null){
            homeFindFragment = new HomeFindFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, homeFindFragment);
        transaction.commit();
    }

    /**显示主页聊天**/
    private void showHomeMessageView() {
        if(homeMessageFragment == null){
            homeMessageFragment = new HomeMessageFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, homeMessageFragment);
        transaction.commit();
    }

    /**显示主页个人中心**/
    private void showHomePersonalView() {
        if(homePersonalFragment == null){
            homePersonalFragment = new HomePersonalFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, homePersonalFragment);
        transaction.commit();
    }


    /** 根据ID切换展示页面 **/
    public void showViewByViewId(int viewId) {
        switch (viewId) {
            case R.id.linearLayout_homeMall:
                linearLayoutHomeMall.callOnClick();
                break;
            case R.id.linearLayout_homeNearBy:
                linearLayoutHomeNearBy.callOnClick();
                break;
            case R.id.relativeLayout_homeFind:
                relativeLayoutHomeFind.callOnClick();
                break;
            case R.id.linearLayout_homeMessage:
                linearLayoutHomeMessage.callOnClick();
                break;
            case R.id.linearLayout_homePersonal:
                linearLayoutHomePersonal.callOnClick();
                break;
            default:
                break;
        }
    }



    private boolean refreshBottomBar(int viewID){

        //之前选中的是发现且本次点击的还是发现
        if(ID_BEFAULTSELECTED == viewID){

            if(ID_BEFAULTSELECTED == R.id.relativeLayout_homeFind){
                //打开发布需求界面
                    showPublishMsgWindow();
            }
            return false;
        }

        if(ID_BEFAULTSELECTED == R.id.relativeLayout_homeFind){
            hideHomeFindIcon();
        }

        ID_BEFAULTSELECTED = viewID;
        int black = getResources().getColor(R.color.colorBlack_FF323232);
        ivHomeMall.setImageResource(R.mipmap.home_nav_mall_unslect);
        tvHomeMall.setTextColor(black);
        ivHomeNearBy.setImageResource(R.mipmap.home_nav_nearby_unslect);
        tvHomeNearBy.setTextColor(black);
        ivHomeMessage.setImageResource(R.mipmap.home_nav_message_unslect);
        tvHomeMessage.setTextColor(black);
        ivHomePersonal.setImageResource(R.mipmap.home_nav_personal_unslect);
        tvHomePersonal.setTextColor(black);
        return true;
    }


    /**
     * 拍照或从图库选择图片(PopupWindow形式)
     */
    public void showPublishMsgWindow(){
        publishMsgPopWindow = new PopSelectPublishMsgWindow(this, new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(mContext,PublishFindActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_top,R.anim.not_change);
                        break;
                    case 1:
                        intent = new Intent(mContext,PublishRequestActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_top,R.anim.not_change);
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        publishMsgPopWindow.showAtLocation( rootView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showHomeFindIcon(){

        linearLayoutHomeFind.setPivotX(linearLayoutHomeFind.getWidth()/2);
        linearLayoutHomeFind.setPivotY(linearLayoutHomeFind.getHeight()/2);
        linearLayoutHomeFind.invalidate();

        ivHomePublish.setPivotX(ivHomePublish.getWidth()/2);
        ivHomePublish.setPivotY(ivHomePublish.getHeight()/2);
        ivHomePublish.invalidate();

        ObjectAnimator objAnimScaleX01 = ObjectAnimator.ofFloat(linearLayoutHomeFind,"scaleX",1f, 0f);
        objAnimScaleX01.setDuration(250);
        objAnimScaleX01.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                ivHomePublish.setVisibility(View.INVISIBLE);
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ivHomePublish.setVisibility(View.VISIBLE);
                super.onAnimationEnd(animation);
            }
        });
        ObjectAnimator objAnimScaleY01 = ObjectAnimator.ofFloat(linearLayoutHomeFind,"scaleY",1f, 0f);
        objAnimScaleY01.setDuration(250);


        ObjectAnimator objAnimScaleX02 = ObjectAnimator.ofFloat(ivHomePublish,"scaleX",0f, 1f);
        objAnimScaleX02.setDuration(250);
        objAnimScaleX02.setStartDelay(200);

        ObjectAnimator objAnimScaleY02 = ObjectAnimator.ofFloat(ivHomePublish,"scaleY",0f, 1f);
        objAnimScaleY02.setDuration(250);
        objAnimScaleY02.setStartDelay(200);
        // 创建动画集合
        AnimatorSet set = new AnimatorSet();
//        set.play(objAnimScaleX01).with(objAnimScaleY01).before(objAnimScaleX02).before(objAnimScaleY02);
//        set.setDuration(250);
        set.playTogether(objAnimScaleX01,objAnimScaleY01,objAnimScaleX02,objAnimScaleY02);
        set.start();
    }

    private void hideHomeFindIcon(){
        ivHomePublish.setPivotX(ivHomePublish.getWidth()/2);
        ivHomePublish.setPivotY(ivHomePublish.getHeight()/2);
        ivHomePublish.invalidate();

        linearLayoutHomeFind.setPivotX(linearLayoutHomeFind.getWidth()/2);
        linearLayoutHomeFind.setPivotY(linearLayoutHomeFind.getHeight()/2);
        linearLayoutHomeFind.invalidate();

        ObjectAnimator objAnimScaleX01 = ObjectAnimator.ofFloat(ivHomePublish,"scaleX",1f, 0f);
        objAnimScaleX01.setDuration(250);

        objAnimScaleX01.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                linearLayoutHomeFind.setVisibility(View.INVISIBLE);
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linearLayoutHomeFind.setVisibility(View.VISIBLE);
                super.onAnimationEnd(animation);
            }
        });
        ObjectAnimator objAnimScaleY01 = ObjectAnimator.ofFloat(ivHomePublish,"scaleY",1f, 0f);
        objAnimScaleX01.setDuration(250);


        ObjectAnimator objAnimScaleX02 = ObjectAnimator.ofFloat(linearLayoutHomeFind,"scaleX",0f, 1f);
        objAnimScaleX02.setDuration(250);
        objAnimScaleX02.setStartDelay(200);

        ObjectAnimator objAnimScaleY02 = ObjectAnimator.ofFloat(linearLayoutHomeFind,"scaleY",0f, 1f);
        objAnimScaleY02.setDuration(250);
        objAnimScaleY02.setStartDelay(200);


        // 创建动画集合
        AnimatorSet set = new AnimatorSet();
//        set.play(objAnimScaleX01).with(objAnimScaleY01).before(objAnimScaleX02).before(objAnimScaleY02);
//        set.setDuration(250);
        set.playTogether(objAnimScaleX01,objAnimScaleY01,objAnimScaleX02,objAnimScaleY02);
        set.start();
    }
}
