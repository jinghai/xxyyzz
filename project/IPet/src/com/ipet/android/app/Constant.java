package com.ipet.android.app;

public class Constant {
	// SharedPreferences
	public static final String SP_SETTING_FILENAME = "SettingInfo";
	public static final String SP_SETTING_FIRST_RUN_KEY = "SOFT_IS_FIRST_RUN";

	// splash
	public static final long SPLASH_DELAY_MILLIS = 2000;

	// TitleBar
	public static final int TITLE_VISIBILITY_VISIBLE = 0;
	public static final int TITLE_VISIBILITY_INVISIBLE = 1;
	public static final int TITLE_VISIBILITY_GONE = 2;

	public static final int TITLE_TYPE_TEXT = 0;
	public static final int TITLE_TYPE_IMAGE = 1;

	public static final String IPET_PHOTO_ID = "IPET_PHOTO_ID";
	public static final String IPET_PHOTO_SERIALIZABLE = "IPET_PHOTO_SERIALIZABLE";
	public static final String IPET_COMMENT_SERIALIZABLE = "IPET_COMMENT_SERIALIZABLE";
	public static final String BROADCAST_INTENT_IPET_PHOTO_FAVORED = "com.ipet.broadcast.favored";
	public static final String BROADCAST_INTENT_IPET_PHOTO_COMMENT = "com.ipet.broadcast.comment";

	public static final String IPET_COMMENT_TYPE = "IPET_COMMENT_TYPE";
	public static final String IPET_COMMENT_TYPE_ADD = "ADD";
	public static final String IPET_COMMENT_TYPE_DELETE = "DELETE";

}
