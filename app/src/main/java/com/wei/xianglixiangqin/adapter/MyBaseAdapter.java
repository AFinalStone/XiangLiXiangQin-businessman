package com.wei.xianglixiangqin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @action GirdView和ListView一级分类适配器
 * @author SHI
 * @date  2016-2-17 15:34:13
 */
public abstract class MyBaseAdapter<VA> extends BaseAdapter {

	/**上下文对象**/
	protected LayoutInflater mLayoutInflater;
	/**数据源**/
	protected List<VA> listData;
	
	public MyBaseAdapter(Context context, List<VA> listData){
		this.mLayoutInflater = LayoutInflater.from(context);
		this.listData = listData;
	}

	@Override
	public int getCount() {
		if(listData == null){
			return 0;
		}
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
