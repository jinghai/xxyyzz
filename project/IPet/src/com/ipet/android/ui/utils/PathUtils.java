package com.ipet.android.ui.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class PathUtils {
	public static final String CRASH_DIR = "crash";
	public static final String DB_DIR = "databases";
	public static final String HEAD_DIR = ".head";
	public static final String FEED_DIR = ".feed";
	public static final String IPET_DIR = "ipet";
	public static final String IPET_APP_DIR = "ipet/app";
	public static final String PICCOUNT_DIR = ".pics";
	public static final String PIC_ORIGINAL_DIR = ".pictures";
	public static final String PIC_PREVIEW_DIR = ".preview";
	public static final String PIC_SAVE_DIR = "save";
	public static final String PIC_TINY_DIR = ".tiny";
	public static final String SAVE_PICTURE_IN_SDCARD = "Camera";
	public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	// public static final String USERHEAD_DIR = ".userhead";
	// public static final String USERHEAD_DIR = ".userhead";

	static {
		if (existExternalStorageDirectory()) {
			File appFile = new File(getExternalStorageDirectory(), IPET_APP_DIR);
			if (!appFile.exists())
				appFile.mkdirs();
		}
	}

	public static File createFile(String url, String filename) {
		File file = null;
		if ((StringUtils.isEmpty(filename)) || (!existExternalStorageDirectory())) {
			return file;
		}

		if (StringUtils.isEmpty(url)) {
			File dir = createAppDir();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file = new File(dir, filename);
			if (file.exists() && file.isFile()) {
				return file;
			}
			try {
				file.createNewFile();
				return file;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return file;
	}

	public static File createAppDir() {
		File file = new File(getExternalStorageDirectory() + File.separator + IPET_APP_DIR);
		if (!existsPath_ExternalStorageDirectory(IPET_APP_DIR)) {
			file.mkdirs();
		}
		return file.getAbsoluteFile();
	}

	public static String getCarmerDir() {
		File file = new File(createAppDir() + File.separator + SAVE_PICTURE_IN_SDCARD + File.separator);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath() + File.separator;
	}

	public static String getExternalStorageDirectory() {
		if (existExternalStorageDirectory()) {
			return SDCARD_PATH;
		} else {
			return null;
		}
	}

	public static boolean existExternalStorageDirectory() {
		return ("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite());
	}

	public static boolean existsPath_ExternalStorageDirectory(String url) {
		if (StringUtils.isEmpty(url)) {
			return false;
		}

		if (existExternalStorageDirectory()) {
			return new File(getExternalStorageDirectory() + File.separator + url).exists();
		}

		return false;
	}

}
