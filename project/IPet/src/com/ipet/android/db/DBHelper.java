package com.ipet.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Helper class to create & upgrade database cache tables
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    /**
     * Version constant to increment when the database should be rebuilt
     */
    private static final int VERSION = 1;

    /**
     * Name of database file
     */
    private static final String NAME = "Ipet.db";

    /**
     * @param context
     */
    public DBHelper(final Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Log.d(TAG, "onCreate:" + db.toString());
        String feeds = "create table IF NOT EXISTS feeds("
                + "id integer primary key autoincrement,"
                + "content varchar(500),"
                + "created_by varchar(20),"
                + "created_at varchar(20),"
                + "img_url varchar(20) );";
        db.execSQL(feeds);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
            final int newVersion) {
        Log.d(TAG, "onUpgrade:" + db.toString());
        db.execSQL("DROP TABLE IF EXISTS feeds");
        onCreate(db);
    }
}
