package com.ipet.android.ui.common;

/**
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.widget.PullToRefreshListView;

/***
 * A ListView that maintains a header pinned at the top of the list. The pinned
 * header can be pushed up and dissolved as needed.
 */
public class FeedListView extends PullToRefreshListView {
	private LayoutInflater mInflater;
	private RelativeLayout mMoreView;
	private TextView mMoreViewText;
	private ProgressBar mMoreViewProgress;
	
	private OnLoadMoreListener onLoadMoreListener;



	/***
	 * Adapter interface. The list adapter must implement this interface.
	 */
	public interface PinnedHeaderAdapter {

		/***
		 * Pinned header state: don't show the header.
		 */
		public static final int PINNED_HEADER_GONE = 0;

		/***
		 * Pinned header state: show the header at the top of the list.
		 */
		public static final int PINNED_HEADER_VISIBLE = 1;

		/***
		 * Pinned header state: show the header. If the header extends beyond
		 * the bottom of the first shown element, push it up and clip.
		 */
		public static final int PINNED_HEADER_PUSHED_UP = 2;

		/***
		 * Computes the desired state of the pinned header for the given
		 * position of the first visible list item. Allowed return values are
		 * {@link #PINNED_HEADER_GONE}, {@link #PINNED_HEADER_VISIBLE} or
		 * {@link #PINNED_HEADER_PUSHED_UP}.
		 */
		int getPinnedHeaderState(int position);

		/***
		 * Configures the pinned header view to match the first visible list
		 * item.
		 * 
		 * @param header
		 *            pinned header view.
		 * @param position
		 *            position of the first visible list item.
		 * @param alpha
		 *            fading of the header view, between 0 and 255.
		 */
		void configurePinnedHeader(View header, int position, int alpha);
	}

	private static final int MAX_ALPHA = 255;

	private PinnedHeaderAdapter mAdapter;
	private View mHeaderView;
	private boolean mHeaderViewVisible;

	private int mHeaderViewWidth;

	private int mHeaderViewHeight;

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
	}
	
	public void prepareForMore() {
		mMoreViewProgress.setVisibility(View.VISIBLE);
		mMoreViewText.setText(R.string.drop_to_more_load_label);
    }
	
	public void setFooterVisiblity(int visiblity){
		mMoreView.setVisibility(visiblity);
	}
	
	public void resetFooter(int visiblity){
		setFooterVisiblity(visiblity);
		mMoreViewProgress.setVisibility(View.GONE);
		mMoreViewText.setText(R.string.drop_to_more_label);
	}

	public void setPinnedHeaderView(View view) {
		mHeaderView = view;

		// Disable vertical fading when the pinned header is present
		// TODO change ListView to allow separate measures for top and bottom
		// fading edge;
		// in this particular case we would like to disable the top, but not the
		// bottom edge.
		if (mHeaderView != null) {
			setFadingEdgeLength(0);
		}
		requestLayout();
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		mAdapter = (PinnedHeaderAdapter) adapter;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mHeaderView != null) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mHeaderView != null) {
			mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			configureHeaderView(getFirstVisiblePosition());
		}
	}

	public void configureHeaderView(int position) {
		if (position == 0) {
			// mHeaderViewVisible = false;
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();
			mAdapter.configurePinnedHeader(mHeaderView, position, MAX_ALPHA);
			mHeaderView.layout(0, bottom, mHeaderViewWidth, mHeaderViewHeight + bottom);
			mHeaderViewVisible = false;
			return;
		}
		if (mHeaderView == null) {
			return;
		}

		int state = mAdapter.getPinnedHeaderState(position);
		switch (state) {
		case PinnedHeaderAdapter.PINNED_HEADER_GONE: {
			mHeaderViewVisible = false;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_VISIBLE: {
			mAdapter.configurePinnedHeader(mHeaderView, position, MAX_ALPHA);
			if (mHeaderView.getTop() != 0) {
				mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			}
			mHeaderViewVisible = true;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP: {
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();
			int headerHeight = mHeaderView.getHeight();
			int y;
			int alpha;
			if (bottom < headerHeight) {
				y = (bottom - headerHeight);
				alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
			} else {
				y = 0;
				alpha = MAX_ALPHA;
			}
			mAdapter.configurePinnedHeader(mHeaderView, position, alpha);
			if (mHeaderView.getTop() != y) {
				mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight + y);
			}
			mHeaderViewVisible = true;
			break;
		}
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}
	
	private OnClickListener moreOnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			prepareForMore();
			onLoadMore();
		}
	};
	
	public void onLoadMore(){
	   if (onLoadMoreListener != null) {
		  onLoadMoreListener.LoadMore();
	   }
	}
	
	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}
	
    public interface OnLoadMoreListener {
        /**
         * Called when the list should be refreshed.
         * <p>
         * A call to {@link PullToRefreshListView #onRefreshComplete()} is
         * expected to indicate that the refresh has completed.
         */
        public void LoadMore();
    }

	public void onLoadMoreComplete() {
		// TODO Auto-generated method stub
		this.resetFooter(View.VISIBLE);
	}
	public void onLoadMoreComplete(int visiblity) {
		// TODO Auto-generated method stub
		this.resetFooter(visiblity);
	}
}
