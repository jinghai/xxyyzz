package com.ipet.android.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ipet.android.db.DBHelper;
import com.ipet.android.db.entity.Feed;

public class FeedDao {

    private static final String TAG = "FeedDao";

    private DBHelper helper = null;

    public FeedDao(Context cxt) {
        helper = new DBHelper(cxt);
    }

    public void save(Feed feed) {
        String sql = "insert into feeds (content,created_at,created_by,created_on)values(?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, db.toString());
        Log.d(TAG, feed.toString());
        db.execSQL(sql, new Object[]{feed.content, feed.createdAt,
            feed.createdBy, feed.createdOn});
    }

    public void update(Feed feed) {
        //todo
    }

    public Feed getById(Integer id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from feeds where id=?",
                new String[]{id.toString()});

        if (cursor.moveToFirst()) {
            Integer fid = cursor.getInt(cursor.getColumnIndex("id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String createdBy = cursor.getString(cursor
                    .getColumnIndex("created_by"));
            String createdOn = cursor.getString(cursor
                    .getColumnIndex("created_on"));
            String createdAt = cursor.getString(cursor
                    .getColumnIndex("created_at"));
            String imgUrl = cursor
                    .getString(cursor.getColumnIndex("img_url"));

            return new Feed(fid, content, createdBy, createdOn, createdAt,
                    imgUrl);
        } else {

            return null;
        }

    }

    public List<Feed> getScrollData(Integer offset, Integer maxResult) {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Feed> feeds = new ArrayList<Feed>();
        Cursor cursor = db.rawQuery("select * from feeds limit ?,?",
                new String[]{offset.toString(), maxResult.toString()});

        while (cursor.moveToNext()) {
            Integer fid = cursor.getInt(cursor.getColumnIndex("id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String createdBy = cursor.getString(cursor
                    .getColumnIndex("created_by"));
            String createdOn = cursor.getString(cursor
                    .getColumnIndex("created_on"));
            String createdAt = cursor.getString(cursor
                    .getColumnIndex("created_at"));
            String imgUrl = cursor
                    .getString(cursor.getColumnIndex("img_url"));

            feeds.add(new Feed(fid, content, createdBy, createdOn, createdAt,
                    imgUrl));
        }
        cursor.close();

        return feeds;
    }

    public void removeByIds(Integer id) {
        String sql = "delete from feeds where id=?";
        SQLiteDatabase db = helper.getWritableDatabase();

        Log.d(TAG, db.toString());
        Log.d(TAG, "start to delete id=" + id.toString());

        db.execSQL(sql, new Object[]{id.toString()});

    }

    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from feeds", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

}
