package com.ipet.demo.db;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDao {

    private static final String TAG = UserDao.class.getName();

    private DBHelper helper = null;

    public UserDao(Context cxt) {
        helper = new DBHelper(cxt);
    }

    public void save(User user) {
        if (null == user.id) {
            saveNew(user);
        } else {
            update(user);
        }
    }

    private void saveNew(User user) {
        String sql = "insert into users (account,name,password) values(?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{user.account, user.name, user.password});
    }

    private void update(User user) {
        String sql = "update users set account=?,name=?,password=? where id=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{user.account, user.name, user.password, user.id});

    }

    public User getById(Integer id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where id=?",
                new String[]{id.toString()});

        if (cursor.moveToFirst()) {
            Integer fid = cursor.getInt(cursor.getColumnIndex("id"));
            String account = cursor.getString(cursor.getColumnIndex("account"));
            String password = cursor.getString(cursor
                    .getColumnIndex("password"));
            String name = cursor.getString(cursor
                    .getColumnIndex("name"));

            return new User(fid, account, password, name);
        } else {

            return null;
        }

    }

    public List<User> getScrollData(Integer offset, Integer maxResult) {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<User> users = new ArrayList<User>();
        Cursor cursor = db.rawQuery("select * from users limit ?,?",
                new String[]{offset.toString(), maxResult.toString()});

        while (cursor.moveToNext()) {
            Integer fid = cursor.getInt(cursor.getColumnIndex("id"));
            String account = cursor.getString(cursor.getColumnIndex("account"));
            String password = cursor.getString(cursor
                    .getColumnIndex("password"));
            String name = cursor.getString(cursor
                    .getColumnIndex("name"));

            users.add(new User(fid, account, password, name));
        }
        cursor.close();

        return users;
    }

    public void removeByIds(Integer id) {
        String sql = "delete from users where id=?";
        SQLiteDatabase db = helper.getWritableDatabase();

        Log.d(TAG, "removeByIds:" + sql);
        db.execSQL(sql, new Object[]{id.toString()});

    }

    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from users", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

}
