package com.ipet.android.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetPhoto;

public class FindGridAdapter extends BaseAdapter {
	private final Context context;
	private List<IpetPhoto> list;
	private final LayoutInflater mInflater;

	public FindGridAdapter(Context context) {
		super();
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	public void setList(List<IpetPhoto> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int index) {
		return list.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	private class GridHolder {
		ImageView findImageView;
		TextView findTextView;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		GridHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_find_item, null);
			holder = new GridHolder();
			holder.findImageView = (ImageView) convertView.findViewById(R.id.itemImage);
			holder.findTextView = (TextView) convertView.findViewById(R.id.itemText);
			convertView.setTag(holder);
		} else {
			holder = (GridHolder) convertView.getTag();
		}
		IpetPhoto feed = list.get(index);
		if (feed != null) {
			// holder.findImageView.setId(id)
			holder.findTextView.setText(feed.getUserName());
		}
		return convertView;
	}

	public void prependList(List<IpetPhoto> list) {
		this.list.addAll(0, list);
		this.notifyDataSetChanged();
	}

	public void appendList(List<IpetPhoto> list) {
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}
}
