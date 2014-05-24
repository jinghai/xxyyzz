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
public class Cache4DB {
	private final int maxNum;
    private CacheDAO dao;

    public Cache4DB(Context cxt,int maxNum) {
    	dao = new CacheDAO(cxt);
    	this.maxNum = maxNum;
    }

    public synchronized void put(CacheEntry e){
    	if(dao.find(e)!=null){
    		dao.update(e);
    		return;
    	}
    	dao.insert(e);
    }
    
    public synchronized CacheEntry get(String url) {
    	CacheEntry ret = get(new CacheEntry(url));
    	if(null!=ret){
    		ret.setHits(ret.getHits()+1);
    		dao.update(ret);
    	}
        return ret;
    }

    public synchronized void remove(CacheEntry e) {
    	dao.remove(e);
    }
    
    private CacheEntry get(CacheEntry e) {
        return dao.find(e);
    }
}
