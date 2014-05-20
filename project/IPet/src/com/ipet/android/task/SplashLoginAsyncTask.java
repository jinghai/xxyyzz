package com.ipet.android.task;

import android.os.AsyncTask;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.SplashActivity;

public class SplashLoginAsyncTask extends AsyncTask<Integer, Integer, Integer> {
	public final static String TAG = "SplashLoginAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE_NETWORK = 1;
	public final static int RESULT_FAILURE_AUTH = 2;
	public final static int RESULT_FAILURE_OTHER = 3;

	private final SplashActivity activity;
	private final String account;
	private final String password;

	// private ProgressDialog progress;

	public SplashLoginAsyncTask(SplashActivity activity, String account, String password) {
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
		// this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE_OTHER;
		try {
			MyApplication application = (MyApplication) this.activity.getApplication();
			IpetUser u = application.getApi().getAccountApi().login(account, password);
			application.setUser(u);
			result = RESULT_SUCCESS;
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		// this.progress.dismiss();
		return result;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result.intValue() == RESULT_SUCCESS) {
			this.activity.goMain();
		} else {
			this.activity.goWelcome();
		}

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);

	}

}
