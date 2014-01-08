package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.ipet.R;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.AnimUtils;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.register_titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));
		titleBar.setRightViewClick(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				RegisterActivity.this.save();
			}
		});
	}

	protected void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AnimUtils.backAndFinish(this);
			return true;
		}
		return true;
	}
}
