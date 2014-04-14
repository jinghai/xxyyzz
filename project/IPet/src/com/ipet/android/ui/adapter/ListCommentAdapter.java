package com.ipet.android.ui.adapter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetComment;
import com.loopj.android.image.SmartImageView;

public class ListCommentAdapter extends BaseAdapter implements OnScrollListener {
	public final List<IpetComment> list;
	private final LayoutInflater inflater;
	public final Context context;
	private final ListView listView;
	public int curIndex;

	public ListCommentAdapter(Context context, ListView listView, List<IpetComment> list) {
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

	public int getPosItemById(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public class ViewHolder {
		public SmartImageView avator;
		public TextView create_at;
		public TextView text;
		public String linkColor;

	}

	public ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;

		if (convertView == null) {
			view = inflater.inflate(R.layout.list_comment_item, null);
			holder = new ViewHolder();

			holder.avator = (SmartImageView) view.findViewById(R.id.avator);
			holder.text = (TextView) view.findViewById(R.id.text);
			holder.create_at = (TextView) view.findViewById(R.id.created_at);
			String link = this.context.getResources().getString(R.color.feed_link_color);
			holder.linkColor = link.substring(3, 9);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		IpetComment comment = list.get(position);
		holder.create_at.setText(comment.getCreateAt());
		holder.text.setText(Html.fromHtml("<b><font color='#" + holder.linkColor + "'>" + comment.getUserName() + "</font></b>&nbsp;" + comment.getText()));

		return view;
	}

	// --end
	public void prependList(List<IpetComment> list) {
		this.list.addAll(0, list);
		this.notifyDataSetChanged();
		listView.setSelection(list.size());
	}

	public void appendList(List<IpetComment> list) {
		// TODO Auto-generated method stub
		this.list.addAll(list);
		this.notifyDataSetChanged();
		listView.setSelection(list.size());
	}

	public void appendComment(IpetComment comment) {
		// TODO Auto-generated method stub
		this.list.add(comment);
		this.notifyDataSetChanged();
		listView.setSelection(list.size() - 1);
	}

	public void loadList(List<IpetComment> list) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.appendList(list);
		this.notifyDataSetChanged();
		listView.setSelection(list.size() - 1);
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
