/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache.http;

import java.net.URI;
import org.springframework.util.Assert;

/**
 *
 * @author Administrator
 */
public class EtagCacheEntry {

    private final String etag;
    private final URI uri;
    private final Object resource;

    /**
     * @param etag
     * @param uri
     * @param resource
     */
    public EtagCacheEntry(String etag, URI uri, Object resource) {

        Assert.notNull(etag);
        Assert.notNull(uri);

        this.etag = etag;
        this.uri = uri;
        this.resource = resource;
    }

    public String getEtag() {

        return etag;
    }

    public URI getUri() {

        return uri;
    }

    public Object getResource() {

        return resource;
    }


    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append("URI: ").append(uri);
        buffer.append(" - ETag: ").append(etag);
        buffer.append(" - Resource: ").append(resource);

        return buffer.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EtagCacheEntry)) {
            return false;
        }

        EtagCacheEntry that = (EtagCacheEntry) obj;

        boolean uriAndEtag
                = this.uri.equals(that.uri) && this.etag.equals(that.etag);

        return uriAndEtag
                && (null == this.resource ? null == that.resource
                : this.resource.equals(that.resource));
    }

    @Override
    public int hashCode() {

        int result = 31;

        result += 17 * etag.hashCode();
        result += 17 * uri.hashCode();

        result += null == resource ? 0 : resource.hashCode();

        return result;
    }
}
