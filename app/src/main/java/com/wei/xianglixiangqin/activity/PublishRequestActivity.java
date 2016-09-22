package com.wei.xianglixiangqin.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.adapter.MyBaseAdapter;
import com.wei.xianglixiangqin.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 发布我的发现界面
 *
 * @author SHI
 * @time 2016/7/15 11:21
 */
public class PublishRequestActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_titleLeft)
    ImageView ivTitleLeft;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.gridView)
    ScrollGridView gridView;

    private GridPictureAdapter mGridPictureAdapter;
    private List<ViewHolderValue> listData;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_request);
        ButterKnife.bind(this);
        ivTitleLeft.setVisibility(View.VISIBLE);
        ivTitleLeft.setOnClickListener(this);
        tvTitle.setText(getResources().getString(R.string.title_publishMyFind));
        listData = new ArrayList<ViewHolderValue>();
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        listData.add(new ViewHolderValue());
        mGridPictureAdapter = new GridPictureAdapter(mContext,listData);
        gridView.setAdapter(mGridPictureAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    public void finish() {
        //  IfOpenFinishActivityAnim(false);
        super.finish();
        overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_titleLeft:
                finish();
                break;
        }
    }


    protected class GridPictureAdapter extends MyBaseAdapter<ViewHolderValue> {

        public GridPictureAdapter(Context mContext, List<ViewHolderValue> listData) {
            super(mContext, listData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.item_adapter_publishfind_addpic,parent,false);

            return view;
        }

    }

    private class ViewHolderValue{

    }
}
