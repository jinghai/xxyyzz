package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.LoginActivity;
import com.ipet.android.ui.manager.LoginManager;

public class LoginAsyncTask extends AsyncTask<Integer, Integer, Integer> {
	public final static String TAG = "LoginAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE_NETWORK = 1;
	public final static int RESULT_FAILURE_AUTH = 2;
	public final static int RESULT_FAILURE_OTHER = 3;

	private final LoginActivity activity;
	private final String account;
	private final String password;
	private ProgressDialog progress;

	public LoginAsyncTask(LoginActivity activity, String account, String password) {
		this.activity = activity;
		this.account = account;
		this.password = password;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Log.i("LoginAsyncTask", "开始执行异步线程");
		String str = this.activity.getResources().getString(R.string.login_loading);
		this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE_OTHER;
		try {
			MyApp application = (MyApp) this.activity.getApplication();
			IpetUser u = application.getApi().getAccountApi().login(account, password);
			application.setUser(u);
			result = RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = RESULT_FAILURE_AUTH;
			e.printStackTrace();
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		this.progress.dismiss();
		return result;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		switch (result.intValue()) {
		case RESULT_SUCCESS: {
			LoginManager.saveAccountAndPassword(activity, account, password);
			this.activity.goMain();
			break;
		}
		case RESULT_FAILURE_AUTH: {
			this.activity.showError(R.string.login_failure_auth);
			break;
		}
		case RESULT_FAILURE_OTHER: {
			this.activity.showError(R.string.login_failure_other);
			break;
		}

		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);

	}

}
