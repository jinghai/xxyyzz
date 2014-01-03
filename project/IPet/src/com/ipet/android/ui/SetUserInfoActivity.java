package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.ipet.R;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.AnimUtils;

public class SetUserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_user_info);
		
		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.me_set_info_titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));
		
		
	}

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AnimUtils.backAndFinish(this);
			return true;
		}
		return true;
	}

}
