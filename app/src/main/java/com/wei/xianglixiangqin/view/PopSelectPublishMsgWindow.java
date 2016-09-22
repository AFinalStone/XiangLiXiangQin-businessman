package com.wei.xianglixiangqin.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wei.xianglixiangqin.Interface.OnItemClickLitener;
import com.wei.xianglixiangqin.R;
import com.wei.xianglixiangqin.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 发布发现，需求，上传产品界面
 * SHI
 * 2016年7月8日 11:53:43
 */
public class PopSelectPublishMsgWindow extends PopupWindow {

    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mRecycleViewAdapter;
    private List<MyViewHolderValue> listData = new ArrayList<MyViewHolderValue>();
    private ImageView iv_close;
    private View mMenuView;
    private Context mContext;

    @SuppressLint("InflateParams")
    public PopSelectPublishMsgWindow(Context context, OnItemClickLitener onItemClickLitener) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.item_pop_select_publish_msg, null);
        mRecyclerView = (RecyclerView) mMenuView.findViewById(R.id.recycleView);
        iv_close = (ImageView) mMenuView.findViewById(R.id.iv_close);

//        listData.add(new MyViewHolderValue(R.mipmap.icon_publish_find_msg
//                ,context.getResources().getString(R.string.type_publishFind)));
//        listData.add(new MyViewHolderValue(R.mipmap.icon_publish_request_msg
//                ,context.getResources().getString(R.string.type_publishRequest)));
//        listData.add(new MyViewHolderValue(R.mipmap.icon_publish_upload
//                ,context.getResources().getString(R.string.type_upload)));

        //StaggeredGridLayoutManager，实现水平GridView效果,每列展示3个item
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        //添加默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecycleViewAdapter = new RecycleViewAdapter(mContext,listData);
        mRecyclerView.setAdapter(mRecycleViewAdapter);
        mRecycleViewAdapter.setOnItemClickLitener(onItemClickLitener);
        iv_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupFromBottomAnimationStyle);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.linearLayout_bottomBar).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecycleViewAdapter.addData(0,new MyViewHolderValue(R.mipmap.icon_publish_find_msg
                        ,mContext.getResources().getString(R.string.type_publishFind)));
            }
        },100);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecycleViewAdapter.addData(1,new MyViewHolderValue(R.mipmap.icon_publish_request_msg
                        ,mContext.getResources().getString(R.string.type_publishRequest)));

            }
        },400);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecycleViewAdapter.addData(2,new MyViewHolderValue(R.mipmap.icon_publish_upload
                        ,mContext.getResources().getString(R.string.type_upload)));
            }
        },700);

    }

    /**
     * 附近商城数据适配器
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
                    R.layout.item_adapter_publish_msg, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.ivType.setImageResource(listData.get(position).typeResID);
            holder.tvTypeName.setText(listData.get(position).typeName);

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new OnClickListener() {
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

        public void addData(int position, MyViewHolderValue value) {
            listData.add(value);
            notifyItemInserted(position);
        }


        public void removeData(int position) {
            if (position < listData.size()) {
                listData.remove(position);
                notifyItemRemoved(position);
            }
        }


        public class MyViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.iv_type)
            ImageView ivType;
            @Bind(R.id.tv_typeName)
            TextView tvTypeName;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    public class MyViewHolderValue {
        int typeResID;
        String typeName;

        public MyViewHolderValue(int typeResID, String typeName) {
            this.typeResID = typeResID;
            this.typeName = typeName;
        }
    }

}
