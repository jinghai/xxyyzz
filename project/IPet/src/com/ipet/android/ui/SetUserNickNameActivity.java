package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.StringUtils;

public class SetUserNickNameActivity extends Activity {

	private EditText nicknameView = null;
	private MyApp application = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_user_nick_name);

		application = (MyApp) this.getApplication();

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.me_set_info_nickname_titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));

		titleBar.setRightViewClick(saveClick);

		nicknameView = (EditText) this.findViewById(R.id.nickname_edit);

	}

	private final OnClickListener saveClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String nickname = nicknameView.getText().toString();

			if (StringUtils.isEmpty(nickname)) {
				showError(R.string.nickname_empty);
				nicknameView.requestFocus();
				return;
			}

			application.getUser().setDisplayName(nickname);

		}
	};

	public void showError(int resId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return true;
	}

}
