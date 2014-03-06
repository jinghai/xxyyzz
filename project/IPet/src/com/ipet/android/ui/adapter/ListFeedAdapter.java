package com.ipet.android.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.ui.utils.StringUtils;
import com.ipet.android.vo.Feed;

public class ListFeedAdapter extends BaseAdapter implements OnScrollListener {
	private final List<Feed> list;
	private final LayoutInflater inflater;
	private final Context context;

	public ListFeedAdapter(Context context, List<Feed> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ViewHolder {
		public TextView create_by;
		public TextView content;
		public ImageView content_image;
		public View header;
		public TextView create_at;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		ViewHolder holder;

		if (convertView == null) {
			view = inflater.inflate(R.layout.list_feed_item, null);
			holder = new ViewHolder();
			holder.header = view.findViewById(R.id.inc_feed_header);
			holder.create_by = (TextView) view.findViewById(R.id.feed_created_by);
			holder.create_at = (TextView) view.findViewById(R.id.feed_created_at);

			DisplayMetrics dm = new DisplayMetrics();
			((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			int width = dm.widthPixels;
			holder.content_image = (ImageView) view.findViewById(R.id.feed_content_image);
			LayoutParams ps = (LayoutParams) holder.content_image.getLayoutParams();
			ps.width = width;
			ps.height = width;
			holder.content_image.setLayoutParams(ps);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		Feed feed = list.get(position);
		holder.create_by.setText(feed.getCreate_by());
		holder.create_at.setText(feed.getCreate_at());
		// holder.content.setText(feed.getContent());
		String imageURI = feed.getContent_image();

		if (!StringUtils.isEmpty(imageURI)) {
			holder.content_image.setImageURI(Uri.parse(imageURI));
			Log.i("img", "newImg");
		} else {
			holder.content_image.setImageResource(R.drawable.xf1);
			Log.i("img", "oldImg");

		}
		// holder.content_image

		return view;
	}

	public void prependList(List<Feed> list) {
		this.list.addAll(0, list);
		this.notifyDataSetChanged();
	}

	public void appendList(List<Feed> list) {
		// TODO Auto-generated method stub
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}

	public void loadList(List<Feed> list) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.appendList(list);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

}
