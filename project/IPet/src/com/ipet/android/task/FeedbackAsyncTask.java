package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.ui.FeedbackActivity;

public class FeedbackAsyncTask extends AsyncTask<String, String, Boolean> {
	private final FeedbackActivity activity;
	private ProgressDialog progress;

	public FeedbackAsyncTask(FeedbackActivity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		String str = this.activity.getResources().getString(R.string.submit_loading);
		this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		String str = params[0];
		MyApp application = (MyApp) this.activity.getApplication();
		Boolean bl = application.getApi().getFeedbackApi().feedback("", str, "");
		this.progress.dismiss();
		return bl;
	}

	@Override
	protected void onPostExecute(Boolean bl) {
		int res = R.string.feedback_success;
		if (bl.booleanValue()) {
			activity.finish();
		} else {
			res = R.string.feedback_failure;
		}

		Toast.makeText(this.activity, res, Toast.LENGTH_SHORT).show();
	}

}
