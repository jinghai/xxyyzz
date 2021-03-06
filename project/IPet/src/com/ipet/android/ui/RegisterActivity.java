package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.task.RegisterAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.manager.ActivityManager;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.StringUtils;

public class RegisterActivity extends Activity {

	private EditText accountEdit;
	private EditText passwordEdit;
	private EditText repasswordEdit;
	private EditText nicknameEdit;

	public String account;
	public String password;
	public View register_btn;

	public final IpetUser user = new IpetUser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		ActivityManager.getInstance().addActivity(this);

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.register_titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));

		accountEdit = (EditText) this.findViewById(R.id.reg_account);
		passwordEdit = (EditText) this.findViewById(R.id.reg_password);
		repasswordEdit = (EditText) this.findViewById(R.id.reg_repassword);
		nicknameEdit = (EditText) this.findViewById(R.id.nickname_edit);
		register_btn = this.findViewById(R.id.register_btn);
		register_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				RegisterActivity.this.save();
			}
		});
	}

	protected void save() {
		// TODO Auto-generated method stub
		if (!validateRegister()) {
			return;
		}

		new RegisterAsyncTask(this).execute();

	}

	private boolean validateRegister() {
		this.account = accountEdit.getText().toString();
		if (StringUtils.isEmpty(account)) {
			showError(R.string.login_empty_account);
			accountEdit.requestFocus();
			return false;
		}

		this.password = passwordEdit.getText().toString();
		String repassword = repasswordEdit.getText().toString();
		if (StringUtils.isEmpty(password)) {
			showError(R.string.login_empty_password);
			passwordEdit.requestFocus();
			return false;
		}

		if (!password.equals(repassword)) {
			showError(R.string.reg_repassword_error);
			repasswordEdit.requestFocus();
			return false;
		}

		String nickname = nicknameEdit.getText().toString();
		if (StringUtils.isEmpty(nickname)) {
			showError(R.string.nickname_no_empty);
			nicknameEdit.requestFocus();
			return false;
		}

		user.setEmail(account);
		user.setDisplayName(nickname);

		return true;
	}

	public void reset() {
		accountEdit.setText("");
		passwordEdit.setText("");
		repasswordEdit.setText("");
		accountEdit.requestFocus();
	}

	public void showError(int resId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AnimUtils.backAndFinish(this);
			return true;
		}
		return true;
	}

	public void goMain() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public IpetUser getUser() {
		return user;
	}
}
