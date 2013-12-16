package com.ipet.android.ui.view;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.ui.common.PinnedHeaderListView;
import com.ipet.android.ui.common.PinnedHeaderListView.PinnedHeaderAdapter;
import com.ipet.android.vo.Feed;

public class ListFeedAdapter extends BaseAdapter implements PinnedHeaderAdapter, OnScrollListener {
	private List<Feed> list;
	private LayoutInflater inflater;
	private ListView listView;
	private Context context;
	private int lastItemPosition = 0;
	
	public ListFeedAdapter(Context context,ListView listView,List<Feed> list) {
		this.context = context;
		this.list = list;
		this.listView = listView;
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
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.list_feed_item, null);
			holder = new ViewHolder();
			holder.create_by = (TextView) view.findViewById(R.id.feed_header);
			holder.content = (TextView) view.findViewById(R.id.feed_content);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		Feed feed = list.get(position);
		holder.create_by.setText(feed.getCreate_by());
		holder.content.setText(feed.getContent());
		if (lastItemPosition == position) {
			holder.create_by.setVisibility(View.INVISIBLE);
		} else {
			holder.create_by.setVisibility(View.VISIBLE);
		}
		return view;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPinnedHeaderState(int position) {
		// TODO Auto-generated method stub
		return PINNED_HEADER_PUSHED_UP;
	}

	@Override
	public void configurePinnedHeader(View header, int position, int alpha) {
       if (lastItemPosition != position) {   
           notifyDataSetChanged();   
        }   
 
		// TODO Auto-generated method stub
		((TextView) header.findViewById(R.id.feed_header)).setText(list.get(   
                position).getCreate_by());   
		lastItemPosition = position;   

	}

}
