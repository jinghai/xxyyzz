/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 *
 * @author Administrator
 */
public class CacheDAO {

    private static final String TAG = "CacheDAO";

    private CacheDataBase helper = null;

    public CacheDAO(Context cxt) {
        helper = new CacheDataBase(cxt);
    }
    
    
    public CacheEntry find(CacheEntry e){
    	 SQLiteDatabase db = helper.getReadableDatabase();
         String str = "select * from [http] where [id]=?";
         Cursor cursor = db.rawQuery(str, new String[]{e.getId()});
         Log.d(TAG, str);
         if (cursor.getCount() < 1) {
         	Log.d(TAG, "not find");
             return null;
         }
         cursor.moveToFirst() ;
         String url = cursor.getString(cursor.getColumnIndex("url"));
         String value = cursor.getString(cursor.getColumnIndex("value"));
         String etag = cursor.getString(cursor.getColumnIndex("etag"));
         Long hits = cursor.getLong(cursor.getColumnIndex("hits"));
         Long put_on = cursor.getLong(cursor.getColumnIndex("put_on"));
         Long expire_on = cursor.getLong(cursor.getColumnIndex("expire_on"));
         CacheEntry ret = new CacheEntry(url,value,etag,expire_on);
         ret.setHits(hits);
         ret.setPutOn(put_on);
         Log.d(TAG, "find");
         return ret;
    }
    
    public void remove(CacheEntry e) {
        String sql = "delete from [http] where [id]=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "remove:" + sql);
        db.execSQL(sql, new Object[]{e.getId()});
    }
    
    public void insert(CacheEntry e) {
        String sql = "insert into [http] ([id],[url],[value],[etag],[expire_on],[hits],[put_on]) values(?,?,?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "save:" + sql);
        db.execSQL(sql, new Object[]{e.getId(),e.getUrl(), e.getValue(), e.getEtag(), e.getExpireOn(),e.getHits(),e.getPutOn()});
    }

    

    public void update(CacheEntry e) {
        String sql = "update [http] set [value]=?,[etag]=?,[expire_on]=?,[hits]=?,[put_on]=? where [id]=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.d(TAG, "update:" + sql);
        db.execSQL(sql, new Object[]{e.getValue(), e.getEtag(), e.getExpireOn(),e.getHits(),e.getPutOn(), e.getId()});

    }

    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from [http]", null);
        cursor.moveToFirst();
        int c = cursor.getInt(0);
        Log.d(TAG, "cout:"+ c);
        return c;
    }
}
