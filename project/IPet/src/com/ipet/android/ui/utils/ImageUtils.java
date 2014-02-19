package com.ipet.android.ui.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {
	public static String getPathByUri(Uri uri, Context paramContext) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = paramContext.getContentResolver().query(uri, proj, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String path = cursor.getString(column_index);
		return path;
	}

	public static String getPathByUriFromFile(Uri uri, Context paramContext) {

		if ((!StringUtils.isEmpty(uri.toString())) && (uri.toString().contains("file://"))) {
			String path = uri.getPath();
			String sdPath = PathUtils.SDCARD_PATH;
			if ((!StringUtils.isEmpty(sdPath)) && (sdPath.contains("/mnt")) && (!StringUtils.isEmpty(path)) && (!path.startsWith("/mnt"))) {
				path = "/mnt" + path;
			}
			return path;
		}
		return getPathByUri(uri, paramContext);
	}
}
