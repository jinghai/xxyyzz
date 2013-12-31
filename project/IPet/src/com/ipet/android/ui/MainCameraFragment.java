package com.ipet.android.ui;

import com.ipet.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainCameraFragment extends Fragment {
	private FragmentActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_camera, container, false);
    }
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
    	//这里相当于 activity的 onCreate方法处理
    	
    	this.activity = getActivity();
    	
    	//找到元素
    	//this.activity.findViewById(R.id.xxxx);
    }
}
