package com.ipet.android.ui;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ipet.R;
import com.ipet.android.app.Constant;
import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetComment;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.FeedLoadAsyncTask;
import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.common.FeedListView.OnLoadMoreListener;
import com.ipet.android.ui.utils.DateTimeUtils;
import com.ipet.android.ui.utils.DeviceUtils;
import com.ipet.android.ui.utils.DialogUtils;
import com.ipet.android.ui.utils.PathUtils;
import com.ipet.android.widget.PullToRefreshListView.OnRefreshListener;

public class MainHomeFragment extends Fragment {
	private static final int REQUEST_CODE_PHOTORESOULT = 20;
	public static final int TYPE_CODE_LOAD = 10;
	public static final int TYPE_CODE_MORE = 20;
	public static final int LIST_SIZE = 10;

	private Activity activity;

	private final ArrayList<IpetPhoto> list = new ArrayList<IpetPhoto>(0);
	private FeedListView listView;
	private ListFeedAdapter adapter;
	private ImageView camera;
	private Dialog dialog;
	private String mImageName;

	private String timeline;
	private int page;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_home, container, false);
	}

	public ListFeedAdapter getAdapter() {
		return adapter;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("MainHomeFragment", "onActivityCreated");
		MyApplication application = (MyApplication) this.getActivity().getApplication();
		// Log.i("User", application.getUser().getId());
		Log.i("API CurrUserId", application.getApi().getCurrUserId());

		this.activity = getActivity();

		camera = (ImageView) activity.findViewById(R.id.composer_buttons_show_hide_button);
		camera.setOnClickListener(cameraClick);

		listView = (FeedListView) activity.findViewById(R.id.main_home_listView);
		adapter = new ListFeedAdapter(activity, listView, list);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);

		View emptyView = activity.findViewById(R.id.empty);
		listView.setEmptyView(emptyView);

		listView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Do work to refresh the list here.
				timeline = DateTimeUtils.getNowDateTime();
				new FeedLoadAsyncTask(MainHomeFragment.this, listView, adapter, MainHomeFragment.TYPE_CODE_LOAD).execute(timeline, "0");
			}
		});

		listView.setOnLoadMoreListener(new OnLoadMoreListener() {

			@Override
			public void LoadMore() {
				// TODO Auto-generated method stub
				MainHomeFragment.this.page++;
				new FeedLoadAsyncTask(MainHomeFragment.this, listView, adapter, MainHomeFragment.TYPE_CODE_MORE).execute(timeline, String.valueOf(page));
			}

		});

		timeline = DateTimeUtils.getNowDateTime();
		page = 0;

	}

	private BroadcastReceiver broadcastreciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();

			if (Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED.equals(action)) {
				IpetPhoto ipetPhoto = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);
				MainHomeFragment.this.updateLike(ipetPhoto);
			}

			if (Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT.equals(action)) {
				IpetComment comment = (IpetComment) intent.getSerializableExtra(Constant.IPET_COMMENT_SERIALIZABLE);
				String type = (String) intent.getStringExtra(Constant.IPET_COMMENT_TYPE);
				MainHomeFragment.this.updateComment(type, comment);
			}
		}

	};

	public void updateLike(IpetPhoto ipetPhoto) {
		this.adapter.updateLike(ipetPhoto);
	}

	protected void updateComment(String type, IpetComment comment) {
		// TODO Auto-generated method stub
		this.adapter.updateComment(type, comment);
	}

	@Override
	public void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT);
		this.activity.registerReceiver(broadcastreciver, filter);

		Log.i("MainHomeFragment", "onStart");
		new FeedLoadAsyncTask(this, listView, adapter, MainHomeFragment.TYPE_CODE_LOAD).execute(timeline, String.valueOf(page));

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i("MainHomeFragment", "onResume");
		super.onResume();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.activity.unregisterReceiver(broadcastreciver);
	}

	private final OnClickListener cameraClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showCameraDialog(v);
		}

	};

	public void showCameraDialog(View v) {
		OnClickListener[] Listener = new OnClickListener[] { takePhotoClick, pickPhotoClick };
		this.dialog = DialogUtils.bottomPopupDialog(this.activity, Listener, R.array.alert_camera, getString(R.string.camera_title), this.dialog);
	}

	private final OnClickListener takePhotoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mImageName = (System.currentTimeMillis() + ".jpg");
			DeviceUtils.takePicture(MainHomeFragment.this, PathUtils.getCarmerDir(), MainHomeFragment.this.mImageName);
			MainHomeFragment.this.dialog.cancel();
		}
	};

	private final OnClickListener pickPhotoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mImageName = (System.currentTimeMillis() + ".jpg");
			DeviceUtils.chooserSysPics(MainHomeFragment.this);
			MainHomeFragment.this.dialog.cancel();
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("Photo", "finish");
		Log.i("Photo", "requestCode" + requestCode);
		Log.i("Photo", "resultCode" + resultCode);

		String path = PathUtils.getCarmerDir() + this.mImageName;
		File picture = new File(path);
		Uri pathUri = Uri.fromFile(picture);

		if (requestCode == DeviceUtils.REQUEST_CODE_PICK_IMAGE) {
			if (resultCode == FragmentActivity.RESULT_OK) {
				Uri uri = data.getData();
				Log.i("Photo", "finish" + uri);
				startPhotoZoom(uri, pathUri);
			}
		}

		if (requestCode == DeviceUtils.REQUEST_CODE_TAKE_IMAGE) {
			if (resultCode == FragmentActivity.RESULT_OK) {
				Log.i("Photo", "finish" + picture);
				startPhotoZoom(Uri.fromFile(picture), pathUri);
			}
		}

		if (requestCode == REQUEST_CODE_PHOTORESOULT) {
			Log.i("Photo", "crop" + pathUri);
			// new FeedAddAsyncTask(this, picture, pathUri).execute();
			Intent intent = new Intent(MainHomeFragment.this.getActivity(), PublishFeedActivity.class);
			Bundle bundle = new Bundle();// 该类用作携带数据
			bundle.putString("PATH", picture.getPath());
			intent.putExtras(bundle);
			startActivity(intent);
			this.activity.finish();
		}

	}

	public void startPhotoZoom(Uri uri, Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 480);
		intent.putExtra("outputY", 480);
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", false);
		startActivityForResult(intent, REQUEST_CODE_PHOTORESOULT);
	}

}
