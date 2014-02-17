package com.ipet.android.ui.utils;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceUtils {

	public static final int REQUEST_CODE_PICK_IMAGE = 50;
	public static final int REQUEST_CODE_TAKE_IMAGE = 51;

	public static int[] terminalWH(Context context) {
		int[] wh = new int[2];
		int w = 0;
		int h = 0;
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		w = dm.widthPixels;
		h = dm.heightPixels;
		wh[0] = w;
		wh[1] = h;
		return wh;
	}

	public static void chooserSysPics(Context paramContext) {
		if (paramContext == null) {
			return;
		}
		Intent localIntent = new Intent();
		localIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		localIntent.setType("image/*");
		localIntent.setAction("android.intent.action.GET_CONTENT");
		((Activity) paramContext).startActivityForResult(localIntent, REQUEST_CODE_PICK_IMAGE);
	}

	public static void chooserSysPics(Fragment fragment) {
		if (fragment.getActivity() == null) {
			return;
		}
		Intent localIntent = new Intent();
		localIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		localIntent.setType("image/*");
		localIntent.setAction("android.intent.action.GET_CONTENT");
		fragment.startActivityForResult(localIntent, REQUEST_CODE_PICK_IMAGE);
	}

	public static void takePicture(Context context) {
		takePicture(context, null, null);
	}

	public static void takePicture(Context context, String path, String filename) {
		Intent intent = setPhotoIntent(context, path, filename);
		if (intent == null) {
			return;
		}
		((Activity) context).startActivityForResult(intent, REQUEST_CODE_TAKE_IMAGE);

	}

	public static void takePicture(Fragment fragment, String path, String filename) {
		// TODO Auto-generated method stub
		Intent intent = setPhotoIntent(fragment.getActivity(), path, filename);
		if (intent == null) {
			return;
		}
		fragment.startActivityForResult(intent, REQUEST_CODE_TAKE_IMAGE);
	}

	private static Intent setPhotoIntent(Context context, String path, String filename) {
		if (context == null) {
			return null;
		}

		if (StringUtils.isEmpty(path)) {
			path = PathUtils.getCarmerDir();
		}

		if (StringUtils.isEmpty(filename)) {
			filename = System.currentTimeMillis() + ".jpg";
		}

		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File file = new File(path, filename);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		return intent;
	}

}
