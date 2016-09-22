package com.wei.xianglixiangqin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wei.xianglixiangqin.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的名片详情
 * @author SHI
 * @time 2016/7/14 15:43
 */
public class MyCardDetailActivity extends BaseActivity {

    @Bind(R.id.iv_titleLeft)
    ImageView ivTitleLeft;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_titleRight)
    TextView tvTitleRight;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_attentionNum)
    TextView tvAttentionNum;
    @Bind(R.id.tv_friendNum)
    TextView tvFriendNum;
    @Bind(R.id.tv_companyName)
    TextView tvCompanyName;
    @Bind(R.id.tv_companyPhone)
    TextView tvCompanyPhone;
    @Bind(R.id.tv_companyAddress)
    TextView tvCompanyAddress;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_card_detail);
        ButterKnife.bind(this);
        ivTitleLeft.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_myCardDetail));
        tvTitleRight.setText(getResources().getString(R.string.action_editMyCard));
        tvTitleRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

}
