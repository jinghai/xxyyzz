/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Administrator
 */
public class DataBase extends SQLiteOpenHelper {

	private static final String TAG = "DataBase";

	private static final int VERSION = 1;

	private static final String NAME = "cache.db";

	public DataBase(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate:" + db.toString());
		StringBuilder str = new StringBuilder();
		str.append("create table IF NOT EXISTS [http](");
		str.append("[key] TEXT,");
		str.append("[val] TEXT,");
		str.append("[etag] TEXT,");
		str.append("[expire_on] BIGINT,");
		str.append("CONSTRAINT [] PRIMARY KEY ([key]));");
		db.execSQL(str.toString());
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		Log.d(TAG, "onUpgrade:" + db.toString());
		db.execSQL("DROP TABLE IF EXISTS [http]");
		onCreate(db);
	}

}
