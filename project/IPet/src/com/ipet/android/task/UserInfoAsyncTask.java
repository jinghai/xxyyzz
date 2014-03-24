package com.ipet.android.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.sdk.domain.IpetUserUpdate;

public class UserInfoAsyncTask extends AsyncTask<String, String, IpetUser> {
	private final Activity activity;
	private ProgressDialog progress;
	private final IpetUserUpdate userUpdate;

	public UserInfoAsyncTask(Activity activity, IpetUserUpdate userUpdate) {
		this.activity = activity;
		this.userUpdate = userUpdate;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		String str = this.activity.getResources().getString(R.string.save_loading);
		this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected IpetUser doInBackground(String... params) {
		// TODO Auto-generated method stub

		MyApp application = (MyApp) this.activity.getApplication();
		IpetUser ipetUser = application.getApi().getUserApi().updateUserInfo(userUpdate);
		this.progress.dismiss();
		return ipetUser;
	}

	@Override
	protected void onPostExecute(IpetUser ipetUser) {
		MyApp application = (MyApp) this.activity.getApplication();
		application.setUser(ipetUser);
		this.activity.finish();
	}
}
