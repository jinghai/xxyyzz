package com.ipet.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.ui.utils.BitmapUtils;

public class MainMeFragment extends Fragment {
	private FragmentActivity activity;
	private ImageView avatar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_me, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();

		avatar = (ImageView) this.activity.findViewById(R.id.me_avatar);
		BitmapUtils.setRoundedCornerImageView((ImageView) avatar);

		View info_layout = this.activity.findViewById(R.id.me_info_layout);
		info_layout.setOnClickListener(onLayoutClickListener);
		
		View fans_layout = this.activity.findViewById(R.id.me_fans_layout);
		fans_layout.setOnClickListener(onLayoutClickListener);
		
		View feeds_layout = this.activity.findViewById(R.id.me_feeds_layout);
		feeds_layout.setOnClickListener(onLayoutClickListener);
		
		View followers_layout = this.activity.findViewById(R.id.me_followers_layout);
		followers_layout.setOnClickListener(onLayoutClickListener);
		
		View friends_layout = this.activity.findViewById(R.id.me_friends_layout);
		friends_layout.setOnClickListener(onLayoutClickListener);

	}

	private OnClickListener onLayoutClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.me_info_layout: {
					Toast.makeText(activity, "TT", Toast.LENGTH_LONG).show();
					break;
				}
				
				case R.id.me_feeds_layout: {
					Toast.makeText(activity, "Feeds", Toast.LENGTH_LONG).show();
					break;
				}
				
				case R.id.me_fans_layout: {
					Toast.makeText(activity, "Fans", Toast.LENGTH_LONG).show();
					break;
				}
				
				case R.id.me_followers_layout: {
					Toast.makeText(activity, "Followers", Toast.LENGTH_LONG).show();
					break;
				}
				case R.id.me_friends_layout: {
					Toast.makeText(activity, "Friends", Toast.LENGTH_LONG).show();
					break;
				}
				
			}
		}

	};

}
