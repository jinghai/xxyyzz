/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class MemoryLRUCache {

    private final Map<String, EtagCacheEntry> data
            = Collections.synchronizedMap(new LRULinkedHashMap<String, EtagCacheEntry>(50));

    public void put(EtagCacheEntry entry) {

        data.put(entry.getKey(), entry);
    }

    public EtagCacheEntry get(String uri) {

        return data.get(uri);
    }

    public boolean hasEntry(String uri) {

        return data.containsKey(uri);
    }

    public void remove(String uri) {

        data.remove(uri);
    }
}
