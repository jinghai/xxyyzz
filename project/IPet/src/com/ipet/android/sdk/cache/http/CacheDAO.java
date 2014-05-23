/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache.http;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ipet.android.db.DBHelper;

/**
 *
 * @author Administrator
 */
public class CacheDAO {

    private static final String TAG = "CacheDAO";

    private DataBase helper = null;

    public CacheDAO(Context cxt) {
        helper = new DataBase(cxt);
    }

    public void insert(EtagCacheEntry e) {
        String sql = "insert into [http] ([key],[val],[etag],[expire_on]) values(?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "save:" + sql);
        db.execSQL(sql, new Object[]{e.getKey(), e.getValue(), e.getEtag(), e.getExpireOn()});
    }

    public void remove(String key) {
        String sql = "delete from [http] where [key]=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "remove:" + sql);
        db.execSQL(sql, new Object[]{key});
    }

    public void update(EtagCacheEntry e) {
        String sql = "update [http] set [key]=?,[etag]=?,[expire_on]=? where [uri]=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "update:" + sql);
        db.execSQL(sql, new Object[]{e.getValue(), e.getEtag(), e.getExpireOn(), e.getKey()});

    }

    public EtagCacheEntry find(String key) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from [http] where [key]=?", new String[]{key});
        if (cursor.getCount() < 1) {
            return null;
        }
        String rkey = cursor.getString(cursor.getColumnIndex("key"));
        String uri = cursor.getString(cursor.getColumnIndex("uri"));
        String etag = cursor.getString(cursor.getColumnIndex("etag"));
        Long expire_on = cursor.getLong(cursor.getColumnIndex("expire_on"));
        EtagCacheEntry e = new EtagCacheEntry(rkey, uri, etag, expire_on);
        return e;
    }

    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from [http]", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
}
