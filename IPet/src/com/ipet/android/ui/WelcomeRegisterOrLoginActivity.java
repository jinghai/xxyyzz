package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.ui.manager.ActivityManager;
import com.ipet.android.ui.utils.AppUtils;

public class WelcomeRegisterOrLoginActivity extends Activity {

	private TextView loginBtn = null;
	private TextView regBtn = null;
	private TextView version = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_register_or_login);
		
		ActivityManager.getInstance().addActivity(this);

		loginBtn = (TextView) this.findViewById(R.id.welcome_login_btn);
		loginBtn.setOnClickListener(myWelcomeClick);

		regBtn = (TextView) this.findViewById(R.id.welcome_reg_btn);
		regBtn.setOnClickListener(myWelcomeClick);
		
		version = (TextView) this.findViewById(R.id.version_info);
		String verStr = getResources().getString(R.string.app_version);  
		String v = String.format(verStr, AppUtils.getAppVersionName(this));  
		version.setText(v);
	}

	private OnClickListener myWelcomeClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TextView btn = (TextView) v;
			switch (btn.getId()) {
			case R.id.welcome_login_btn: {
				Intent intent = new Intent(WelcomeRegisterOrLoginActivity.this,
						LoginActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				break;
			}

			case R.id.welcome_reg_btn: {
				Toast.makeText(WelcomeRegisterOrLoginActivity.this, "‘› ±Œ¥ µœ÷",
						Toast.LENGTH_LONG).show();
			}
			}

		}

	};



}
