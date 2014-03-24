package com.ipet.android.task;

import java.io.File;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.SetUserInfoActivity;

public class UploadAvatorAsyncTask extends AsyncTask<String, String, IpetUser> {
	private final Uri uri;
	private final SetUserInfoActivity activity;
	private final File picture;
	private ProgressDialog progress;

	public UploadAvatorAsyncTask(SetUserInfoActivity activity, File picture, Uri uri) {
		this.uri = uri;
		this.activity = activity;
		this.picture = picture;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		String str = this.activity.getResources().getString(R.string.upload_loading);
		this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected IpetUser doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("uploadFile", "pictrue-->" + picture);
		MyApp application = (MyApp) this.activity.getApplication();
		String path = picture.getPath();
		IpetUser ipetUser = application.getApi().getUserApi().updateAvatar(path);
		this.progress.dismiss();
		return ipetUser;
	}

	@Override
	protected void onPostExecute(IpetUser ipetUser) {
		MyApp application = (MyApp) this.activity.getApplication();
		application.setUser(ipetUser);
		this.activity.uploadFinish();
	}
}
