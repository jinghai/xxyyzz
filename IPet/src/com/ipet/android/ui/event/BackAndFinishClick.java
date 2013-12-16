package com.ipet.android.ui.event;


import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.ipet.android.ui.utils.AnimUtils;

public class BackAndFinishClick implements OnClickListener {
	private Activity activity;
	
	public BackAndFinishClick(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		AnimUtils.backAndFinish(activity);
	}

}
