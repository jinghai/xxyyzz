package com.ipet.android.ui.utils;

import android.app.Activity;
import android.content.Context;

import com.ipet.R;

public class AnimUtils {
	public static void pushLeftToRight(Activity activity) {
		activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_in);
	}

	public static void pushRightToLeft(Activity activity) {
		activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	public static void backAndFinish(Activity activity) {
		activity.finish();
		pushRightToLeft(activity);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
