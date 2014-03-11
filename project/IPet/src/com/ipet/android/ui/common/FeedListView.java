package com.ipet.android.ui.common;

/**
 * Copyright (C) 2010 The Android Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.widget.PullToRefreshListView;

/***
 * A ListView that maintains a header pinned at the top of the list. The pinned
 * header can be pushed up and dissolved as needed.
 */
public class FeedListView extends PullToRefreshListView implements OnScrollListener {
	private LayoutInflater mInflater;
	private RelativeLayout mMoreView;
	private TextView mMoreViewText;
	private ProgressBar mMoreViewProgress;
	private OnLoadMoreListener onLoadMoreListener;
	private OnScrollListener mOnScrollListener;
	private static boolean MORE_LOAD_PROCESS = false;

	public FeedListView(Context context) {
		super(context);
		this.initFooter(context);
	}

	public FeedListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initFooter(context);
	}

	public FeedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initFooter(context);
	}

	private void initFooter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMoreView = (RelativeLayout) mInflater.inflate(R.layout.drop_to_load_footer, this, false);
		mMoreView.setOnClickListener(moreOnClickListener);
		mMoreViewText = (TextView) mMoreView.findViewById(R.id.drop_to_refresh_text);
		mMoreViewProgress = (ProgressBar) mMoreView.findViewById(R.id.drop_to_refresh_progress);
		addFooterView(mMoreView);
		super.setOnScrollListener(this);
	}

	public void prepareForMore() {
		MORE_LOAD_PROCESS = true;
		mMoreViewProgress.setVisibility(View.VISIBLE);
		mMoreViewText.setText(R.string.drop_to_more_load_label);
	}

	public void setFooterVisiblity(int visiblity) {
		mMoreView.setVisibility(visiblity);
	}

	public void resetFooter(int visiblity) {
		setFooterVisiblity(visiblity);
		mMoreViewProgress.setVisibility(View.GONE);
		mMoreViewText.setText(R.string.drop_to_more_label);
	}

	private final OnClickListener moreOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			prepareForMore();
			onLoadMore();
		}
	};

	public void onLoadMore() {
		if (onLoadMoreListener != null) {
			onLoadMoreListener.LoadMore();
		}
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}

	public interface OnLoadMoreListener {
		public void LoadMore();
	}

	public void onLoadMoreComplete() {
		// TODO Auto-generated method stub
		this.onLoadMoreComplete(View.VISIBLE);
	}

	public void onLoadMoreComplete(int visiblity) {
		// TODO Auto-generated method stub
		this.resetFooter(visiblity);
		MORE_LOAD_PROCESS = false;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);

		Log.i("TT", "T" + visibleItemCount);

		if (firstVisibleItem > 0 && visibleItemCount + firstVisibleItem == totalItemCount && !MORE_LOAD_PROCESS) {
			if (mMoreView.getVisibility() != View.GONE) {
				prepareForMore();
				onLoadMore();
			}
		}

		if (mOnScrollListener != null) {
			mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}

	}

}
