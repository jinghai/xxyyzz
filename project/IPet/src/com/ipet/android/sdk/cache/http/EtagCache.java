/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache.http;

import java.net.URI;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author Administrator
 */
public class EtagCache {

    private final Map<URI, EtagCacheEntry> entries
            = new WeakHashMap<URI, EtagCacheEntry>();

    public void put(EtagCacheEntry entry) {

        entries.put(entry.getUri(), entry);
    }

    public Object get(URI uri) {

        return entries.get(uri).getResource();
    }

    public String getEtag(URI uri) {

        return entries.get(uri).getEtag();
    }

    public boolean hasEntry(URI uri) {

        return entries.containsKey(uri);
    }

    public void remove(URI uri) {

        entries.remove(uri);
    }
}
