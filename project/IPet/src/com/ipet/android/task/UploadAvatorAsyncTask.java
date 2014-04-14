package com.ipet.android.task;

import java.io.File;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.SetUserInfoActivity;

public class UploadAvatorAsyncTask extends AsyncTask<String, String, Integer> {
	public final static String TAG = "UploadAvatorAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE = 1;

	private final Uri uri;
	private final SetUserInfoActivity activity;
	private final File picture;
	private ProgressDialog progress;
	private IpetUser ipetUser;

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
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE;
		try {
			MyApp application = (MyApp) this.activity.getApplication();
			String path = picture.getPath();
			ipetUser = application.getApi().getUserApi().updateAvatar(path);
			result = RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "" + e.getLocalizedMessage());
		}

		this.progress.dismiss();
		return result;
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if (RESULT_FAILURE == result.intValue()) {
			Toast.makeText(this.activity, "头像发布失败", Toast.LENGTH_SHORT).show();
			return;
		}

		MyApp application = (MyApp) this.activity.getApplication();
		application.setUser(ipetUser);
		this.activity.uploadFinish();
	}
}
