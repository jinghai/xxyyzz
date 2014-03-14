package com.ipet.android.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainHomeFragment;

public class FeedAddAsyncTask extends AsyncTask<String, String, IpetPhoto> {
	private final Uri uri;
	private final MainHomeFragment fragment;
	private final File picture;

	public FeedAddAsyncTask(MainHomeFragment fragment, File picture, Uri uri) {
		this.uri = uri;
		this.fragment = fragment;
		this.picture = picture;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		Toast.makeText(fragment.getActivity(), "发布中...", Toast.LENGTH_LONG).show();
		super.onPreExecute();
	}

	@Override
	protected IpetPhoto doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("uploadFile", "pictrue-->" + picture);

		IpetPhoto ipetPhoto = null;
		try {
			MyApp application = (MyApp) this.fragment.getActivity().getApplication();
			String path = picture.getPath();
			ipetPhoto = application.getApi().getPhotoApi().publish("", path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipetPhoto;
	}

	@Override
	protected void onPostExecute(IpetPhoto ipetPhoto) {
		if (ipetPhoto == null) {
			Toast.makeText(fragment.getActivity(), "发布失败", Toast.LENGTH_LONG).show();
			return;
		}
		Toast.makeText(fragment.getActivity(), "发布成功", Toast.LENGTH_LONG).show();

		List<IpetPhoto> list = new ArrayList<IpetPhoto>(0);
		list.add(ipetPhoto);
		this.fragment.getAdapter().prependList(list);
	}
}
