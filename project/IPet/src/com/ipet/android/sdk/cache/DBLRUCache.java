/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache;

import android.content.Context;

/**
 *
 * @author Administrator
 */
public class DBLRUCache {

    private CacheDAO data;

    public DBLRUCache(Context cxt) {
        data = new CacheDAO(cxt);
    }

    public void put(EtagCacheEntry entry) {
        EtagCacheEntry e = data.find(entry.getKey());
        if (null == e) {
            data.insert(entry);
        } else {
            data.update(entry);
        }
    }

    public EtagCacheEntry get(String uri) {
        return data.find(uri);
    }

    public boolean hasEntry(String uri) {
        EtagCacheEntry e = data.find(uri);
        return null != e;
    }

    public void remove(String uri) {
        data.remove(uri);
    }
}
