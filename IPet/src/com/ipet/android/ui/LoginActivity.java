package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.task.LoginAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.manager.ActivityManager;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.StringUtils;

public class LoginActivity extends Activity {

	private AutoCompleteTextView accountView = null;
	private EditText passwordView = null;
	private TextView toggleView = null;
	private TextView loginBtnView = null;
	private TextView forgotView = null;
	private boolean psdDisplayFlg = false;
	private String account = null;
	private String password = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActivityManager.getInstance().addActivity(this);

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.login_titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));
		titleBar.setRightViewClick(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				LoginActivity.this.goRegister();
			}
		});
		
		accountView = (AutoCompleteTextView) this.findViewById(R.id.login_account);
		passwordView = (EditText) this.findViewById(R.id.login_password);
		toggleView = (TextView) this.findViewById(R.id.login_toggle_password);
		toggleView.setOnClickListener(togglePasswordClick);
		
		loginBtnView = (TextView) this.findViewById(R.id.login_login_btn);
		loginBtnView.setOnClickListener(loginClick);
		
		forgotView = (TextView) this.findViewById(R.id.login_forgot_password);
		forgotView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(LoginActivity.this, "暂时未实现",Toast.LENGTH_LONG).show();
			}
		});
	}
	
	protected OnClickListener togglePasswordClick = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			int index = passwordView.getSelectionStart();
			if (!psdDisplayFlg) {
				// display password text, for example "123456"
				//passwordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				toggleView.setText(R.string.login_toggle_password_hide);
			} else {
				// hide password, display "."
				//passwordView.setTransformationMethod(PasswordTransformationMethod.getInstance());
				passwordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				toggleView.setText(R.string.login_toggle_password_show);
			}
			psdDisplayFlg = !psdDisplayFlg;
			Editable etable = passwordView.getText();
            Selection.setSelection(etable, index);	
		}
	};
	
	private OnClickListener loginClick = new OnClickListener() {
		@Override
		public void onClick(View loginBtnView) {
			if(!validateLogin()){
				return;
			}
			new LoginAsyncTask(LoginActivity.this,LoginActivity.this.account,LoginActivity.this.password).execute();
		}
	};

	
	private boolean validateLogin() {

		this.account =  accountView.getText().toString();
		if(StringUtils.isEmpty(account)){
			showError(R.string.login_empty_account);
			accountView.requestFocus(); 
			return false;
		}
		
		this.password = passwordView.getText().toString();
		if(StringUtils.isEmpty(password)){
			showError(R.string.login_empty_password);
			passwordView.requestFocus(); 
			return false;
		}

		return true;
	}

	public void showError(int resId) {
		// TODO Auto-generated method stub
		Toast.makeText(LoginActivity.this,resId,Toast.LENGTH_LONG).show();
	}

	private void goRegister() {
		Toast.makeText(this, "暂时未实现",
				Toast.LENGTH_LONG).show();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AnimUtils.backAndFinish(this);
			return true;
		}
		return true;
	}
	
	public void goMain(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		AnimUtils.pushLeftToRight(this);
	}
	
	

}
