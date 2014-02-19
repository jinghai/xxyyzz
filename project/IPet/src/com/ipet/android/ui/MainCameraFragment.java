package com.ipet.android.ui;

import java.io.File;

import android.content.Intent;
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
import android.widget.Button;

import com.ipet.R;
import com.ipet.android.task.FeedAddAsyncTask;
import com.ipet.android.ui.utils.DeviceUtils;
import com.ipet.android.ui.utils.PathUtils;

public class MainCameraFragment extends Fragment {
	private static final int REQUEST_CODE_PHOTORESOULT = 20;
	private FragmentActivity activity;

	private Button btn_take_photo;
	private Button btn_pick_photo;

	private String mImageName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_camera, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// 这里相当于 activity的 onCreate方法处理

		this.activity = getActivity();

		// this.activity就相当于 Activity 中的 this

		// 找到元素
		btn_take_photo = (Button) this.activity.findViewById(R.id.btn_take_photo);
		btn_pick_photo = (Button) this.activity.findViewById(R.id.btn_pick_photo);

		btn_take_photo.setOnClickListener(takePhotoClick);
		btn_pick_photo.setOnClickListener(pickPhotoClick);
	}

	private final OnClickListener takePhotoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mImageName = (System.currentTimeMillis() + ".jpg");
			DeviceUtils.takePicture(MainCameraFragment.this, PathUtils.getCarmerDir(), MainCameraFragment.this.mImageName);
		}
	};

	private final OnClickListener pickPhotoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mImageName = (System.currentTimeMillis() + ".jpg");
			DeviceUtils.chooserSysPics(MainCameraFragment.this);

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
			new FeedAddAsyncTask(this, pathUri).execute();
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

	public void backToFeed() {
		((MainActivity) this.activity).setTab(0);
	}

}
