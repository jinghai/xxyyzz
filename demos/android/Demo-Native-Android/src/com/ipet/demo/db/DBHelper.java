package com.ipet.demo.db;

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
    private static final String NAME = "IpetDemo.db";

    /**
     * @param context
     */
    public DBHelper(final Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Log.d(TAG, "onCreate:" + db.toString());
        String users = "create table IF NOT EXISTS users("
                + "id integer primary key autoincrement,"
                + "account varchar(500),"
                + "name varchar(20),"
                + "password varchar(20) );";
        db.execSQL(users);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
            final int newVersion) {
        Log.d(TAG, "onUpgrade:" + db.toString());
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
