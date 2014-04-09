package com.ipet.android.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.StringUtils;
import com.loopj.android.image.SmartImageView;

public class FindGridAdapter extends BaseAdapter {
	private final Context context;
	private List<IpetPhoto> list;
	private final LayoutInflater mInflater;
	private GridView gridview;

	public FindGridAdapter(Context context, GridView gridview) {
		super();
		this.context = context;
		this.gridview = gridview;
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
	public IpetPhoto getItem(int index) {
		return list.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	private class GridHolder {
		SmartImageView findImageView;
		TextView findTextView;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		GridHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_find_item, null);

			holder = new GridHolder();
			holder.findImageView = (SmartImageView) convertView.findViewById(R.id.itemImage);
			LayoutParams ps = (LayoutParams) holder.findImageView.getLayoutParams();
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			int width = dm.widthPixels / 3 - AnimUtils.dip2px(context, 5);
			ps.height = width;
			holder.findImageView.setLayoutParams(ps);
			holder.findTextView = (TextView) convertView.findViewById(R.id.itemText);
			convertView.setTag(holder);
		} else {
			holder = (GridHolder) convertView.getTag();
		}
		IpetPhoto feed = list.get(index);
		String imageURL = feed.getSmallURL();

		if (feed != null) {
			if (!StringUtils.isEmpty(imageURL)) {
				holder.findImageView.setImageUrl(imageURL);
			}
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

	public void loadList(List<IpetPhoto> list) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.appendList(list);
		this.notifyDataSetChanged();
	}

	public int getPosItemById(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public void updataItem(IpetPhoto ipetPhoto) {
		// TODO Auto-generated method stub
		int i = this.getPosItemById(ipetPhoto.getId());
		if (i == -1) {
			return;
		}
		this.list.set(i, ipetPhoto);
		this.notifyDataSetChanged();
	}
}
