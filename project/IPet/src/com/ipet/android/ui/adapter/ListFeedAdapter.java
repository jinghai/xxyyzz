package com.ipet.android.ui.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.sdk.domain.IpetComment;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.FeedLikedAsyncTask;
import com.ipet.android.ui.CommentActivity;
import com.ipet.android.ui.MainActivity;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.utils.StringUtils;
import com.loopj.android.image.SmartImageView;

public class ListFeedAdapter extends BaseAdapter implements OnScrollListener {
	public final List<IpetPhoto> list;
	private final LayoutInflater inflater;
	public final Context context;
	private final FeedListView listView;
	public int curIndex;

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
		public TextView create_by;
		public TextView text;
		public SmartImageView content_image;
		public View header;
		public TextView create_at;
		public View comments_group;
		public LinearLayout layout;
		public CheckBox btn_liked;
		public TextView favor_count;
		public View likes_group;
		public ImageView btn_comment;
		public View comments_num_group;
		public TextView comments_num;
		public LinearLayout comments_group_list;
	}

	public ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;

		if (convertView == null) {
			view = inflater.inflate(R.layout.list_feed_item, null);
			holder = new ViewHolder();
			holder.layout = (LinearLayout) view.findViewById(R.id.feed_item_layout);
			holder.header = view.findViewById(R.id.inc_feed_header);
			holder.avator = (SmartImageView) view.findViewById(R.id.feed_avatar);
			holder.create_by = (TextView) view.findViewById(R.id.feed_created_by);
			holder.create_at = (TextView) view.findViewById(R.id.feed_created_at);
			holder.text = (TextView) view.findViewById(R.id.row_feed_photo_textview_comments);
			holder.comments_group = view.findViewById(R.id.row_feed_photo_comments_group);
			holder.likes_group = view.findViewById(R.id.row_feed_photo_likes_group);
			holder.btn_comment = (ImageView) view.findViewById(R.id.row_feed_photo_button_comment);

			holder.comments_num_group = view.findViewById(R.id.row_feed_photo_comments_num_group);
			holder.comments_num = (TextView) view.findViewById(R.id.row_feed_photo_textview_comments_num);

			holder.comments_group_list = (LinearLayout) view.findViewById(R.id.row_feed_photo_comments_list);

			DisplayMetrics dm = new DisplayMetrics();
			((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			int width = dm.widthPixels;
			holder.content_image = (SmartImageView) view.findViewById(R.id.feed_content_image);
			LayoutParams ps = (LayoutParams) holder.content_image.getLayoutParams();
			ps.width = width;
			ps.height = width;
			holder.content_image.setLayoutParams(ps);

			holder.favor_count = (TextView) view.findViewById(R.id.row_feed_photo_textview_likes);
			holder.btn_liked = (CheckBox) view.findViewById(R.id.row_feed_photo_toggle_button_like);
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		IpetPhoto feed = list.get(position);
		holder.create_by.setText(feed.getUserName());
		holder.create_at.setText(feed.getCreateAt());

		String text = feed.getText();
		String imageURL = feed.getSmallURL();

		String headerImage = feed.getAvatar48();
		// holder.avator.setImageUrl(feed.getAvatar48(),
		// R.drawable.list_default_avatar_boy);

		if (!StringUtils.isEmpty(headerImage)) {
			holder.avator.setImageUrl(headerImage);
		}

		if (!StringUtils.isEmpty(imageURL)) {
			holder.content_image.setImageUrl(imageURL);
		}

		if (!StringUtils.isEmpty(text)) {
			holder.comments_group.setVisibility(View.VISIBLE);
			holder.text.setText(text);
		} else {
			holder.comments_group.setVisibility(View.GONE);
		}

		initLikedBtnView(holder, feed, position);
		initCommentView(holder, feed, position);

		// holder.layout.addView(add());
		return view;
	}

	private RelativeLayout addComment(IpetComment comment) {
		String username = comment.getUserName();
		String text = comment.getText();

		RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.list_feed_item_feedback_comment, null);
		TextView t = (TextView) layout.findViewById(R.id.row_feed_photo_textview_comments_item);
		String link = this.context.getResources().getString(R.color.feed_link_color);
		link = link.substring(3, 9);
		t.setText(Html.fromHtml("<b><font color='#" + link + "'>" + username + "</font></b>&nbsp;" + text));
		return layout;
	}

	public class OnCommentClick implements OnClickListener {
		private IpetPhoto feed;

		public OnCommentClick(IpetPhoto feed) {
			this.feed = feed;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent((MainActivity) ListFeedAdapter.this.context, CommentActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(Constant.IPET_PHOTO_SERIALIZABLE, (Serializable) feed);
			intent.putExtras(mBundle);
			((MainActivity) ListFeedAdapter.this.context).startActivity(intent);
		}
	}

	private void initCommentView(ViewHolder holder, final IpetPhoto feed, int position) {
		// TODO Auto-generated method stub
		OnCommentClick myCommentClick = new OnCommentClick(feed);
		holder.btn_comment.setOnClickListener(myCommentClick);
		holder.comments_num.setOnClickListener(myCommentClick);

		String commentNumStr = this.context.getResources().getString(R.string.commentNum);
		String commentsNum = feed.getCommentCount();

		holder.comments_num.setText(String.format(commentNumStr, commentsNum));

		int size = feed.getComments().size();
		if (feed.getComments().size() == 0) {
			holder.comments_num_group.setVisibility(View.GONE);
		} else {
			holder.comments_num_group.setVisibility(View.VISIBLE);
		}

		holder.comments_group_list.removeAllViews();
		List<IpetComment> list = new ArrayList<IpetComment>(5);
		if (size <= 5) {
			list = feed.getComments();
		} else {
			list = feed.getComments().subList(size - 5, size);
		}

		for (IpetComment comment : list) {
			holder.comments_group_list.addView(addComment(comment));
		}

	}

	// 赞
	private void initLikedBtnView(ViewHolder holder, final IpetPhoto feed, final int position) {

		if ("0".equals(feed.getFavorCount())) {
			holder.likes_group.setVisibility(View.GONE);
		} else {
			holder.likes_group.setVisibility(View.VISIBLE);
		}

		String likedNum = this.context.getResources().getString(R.string.likedNum);
		holder.favor_count.setText(String.format(likedNum, feed.getFavorCount()));

		// TODO Auto-generated method stub
		holder.btn_liked.setChecked(feed.isFavored());
		holder.btn_liked.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CheckBox btn_liked = (CheckBox) v;
				boolean checked = btn_liked.isChecked();
				btn_liked.setChecked(!checked);
				new FeedLikedAsyncTask((MainActivity) ListFeedAdapter.this.context, feed, checked).execute();
				ListFeedAdapter.this.curIndex = position;
			}
		});
	}

	// --end
	public void prependList(List<IpetPhoto> list) {
		this.list.addAll(0, list);
		this.notifyDataSetChanged();
		listView.setSelection(1);
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
		listView.setSelection(1);
	}

	public void updataItem(IpetPhoto ipetPhoto) {
		int i = this.getPosItemById(ipetPhoto.getId());
		if (i == -1) {
			return;
		}
		this.list.set(i, ipetPhoto);
		this.notifyDataSetChanged();
	}

	public void updateLike(IpetPhoto ipetPhoto) {
		// TODO Auto-generated method stub
		int i = this.getPosItemById(ipetPhoto.getId());
		if (i == -1) {
			return;
		}
		IpetPhoto feed = this.list.get(i);
		feed.setFavorCount(ipetPhoto.getFavorCount());
		feed.setFavored(ipetPhoto.isFavored());
		this.notifyDataSetChanged();
	}

	public void updateComment(String type, IpetComment comment) {
		// TODO Auto-generated method stub
		if (Constant.IPET_COMMENT_TYPE_ADD.equals(type)) {
			this.updateAddComment(comment);
		}
	}

	public void updateAddComment(IpetComment comment) {
		int i = this.getPosItemById(comment.getPhotoId());
		if (i == -1) {
			return;
		}
		IpetPhoto ipetPhoto = this.list.get(i);
		ipetPhoto.getComments().add(comment);
		ipetPhoto.setCommentCount(Integer.toString(ipetPhoto.getComments().size()));
		this.notifyDataSetChanged();
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
