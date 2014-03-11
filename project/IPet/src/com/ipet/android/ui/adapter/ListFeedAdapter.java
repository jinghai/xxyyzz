package com.ipet.android.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.utils.StringUtils;
import com.loopj.android.image.SmartImageView;

public class ListFeedAdapter extends BaseAdapter implements OnScrollListener {
	private final List<IpetPhoto> list;
	private final LayoutInflater inflater;
	private final Context context;
	private final FeedListView listView;

	public ListFeedAdapter(Context context, FeedListView listView, List<IpetPhoto> list) {
		this.listView = listView;
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
		public SmartImageView avator;
		public TextView create_by;
		public TextView content;
		public SmartImageView content_image;
		public View header;
		public TextView create_at;
	}

	public ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;

		if (convertView == null) {
			view = inflater.inflate(R.layout.list_feed_item, null);
			holder = new ViewHolder();
			holder.header = view.findViewById(R.id.inc_feed_header);
			holder.avator = (SmartImageView) view.findViewById(R.id.feed_avatar);
			holder.create_by = (TextView) view.findViewById(R.id.feed_created_by);
			holder.create_at = (TextView) view.findViewById(R.id.feed_created_at);

			DisplayMetrics dm = new DisplayMetrics();
			((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			int width = dm.widthPixels;
			holder.content_image = (SmartImageView) view.findViewById(R.id.feed_content_image);
			LayoutParams ps = (LayoutParams) holder.content_image.getLayoutParams();
			ps.width = width;
			ps.height = width;
			holder.content_image.setLayoutParams(ps);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		IpetPhoto feed = list.get(position);
		holder.create_by.setText(feed.getUserName());
		holder.create_at.setText(feed.getCreateAt());
		// holder.content.setText(feed.getContent());
		String imageURL = feed.getSmallURL();
		/*
		 * holder.avator.setImageUrl(feed.getAvatar48(),
		 * R.drawable.list_default_avatar_boy, new
		 * SmartImageTask.OnCompleteListener() {
		 * 
		 * @Override public void onComplete() { // TODO Auto-generated method
		 * stub BitmapUtils.setRoundedCornerImageView(holder.avator); } });
		 */
		if (!StringUtils.isEmpty(imageURL)) {
			holder.content_image.setImageUrl(imageURL);
			// holder.content_image.setImageURI(Uri.parse(imageURI));
			Log.i("img", "newImg");
		} else {
			holder.content_image.setImageResource(R.drawable.xf1);
			Log.i("img", "oldImg");

		}

		// BitmapUtils.setRoundedCornerImageView(holder.avator);
		// holder.content_image

		return view;
	}

	public void prependList(List<IpetPhoto> list) {
		this.list.addAll(0, list);
		this.notifyDataSetChanged();
		// listView.setSelection(1);
	}

	public void appendList(List<IpetPhoto> list) {
		// TODO Auto-generated method stub
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}

	public void loadList(List<IpetPhoto> list) {
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
