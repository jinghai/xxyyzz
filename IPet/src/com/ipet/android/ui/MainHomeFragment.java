package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipet.R;
import com.ipet.android.ui.common.PinnedHeaderListView;
import com.ipet.android.ui.manager.FeedManager;
import com.ipet.android.ui.view.ListFeedAdapter;

public class MainHomeFragment extends Fragment {
	private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_home, container, false);
    }
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();

		PinnedHeaderListView  listView= (PinnedHeaderListView) activity.findViewById(R.id.main_home_listView);
		ListFeedAdapter adapter = new ListFeedAdapter(activity,listView,FeedManager.load());
        listView.setAdapter(adapter);
        listView.setOnScrollListener(adapter);
        listView.setPinnedHeaderView(activity.getLayoutInflater().inflate(   
                R.layout.list_feed_item_header, listView, false));   

	}
    
     
}
