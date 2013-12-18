package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ipet.R;
import com.ipet.android.ui.adapter.ListConversationAdapter;
import com.ipet.android.ui.manager.ConversationManager;

public class MainConversationFragment extends Fragment {
	private Activity activity;
	private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_conversation, container, false);
    }
    
   
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		this.activity = getActivity();
		listView = (ListView) activity.findViewById(R.id.main_conversation_listView);
		ListConversationAdapter adapter = new ListConversationAdapter(activity,listView,ConversationManager.load());
        listView.setAdapter(adapter);
        
        //adapter.prependList(ConversationManager.loadMore(null));
        adapter.appendList(ConversationManager.loadMore(null));
	}
}
