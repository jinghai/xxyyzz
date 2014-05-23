package com.ipet.android.sdk;

import android.content.Context;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetFavor;
import com.ipet.android.sdk.domain.IpetPhoto;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class FavorApi extends ApiBase {

    public FavorApi(Context context) {
        super(context);
    }

    public IpetPhoto favor(String photoId, String text) {
        requireAuthorization();
        URI uri = buildUri("favor/favor");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", getCurrUserId());
        body.add("photoId", photoId);
        body.add("text", text);
        return getRestTemplate().postForObject(uri, body, IpetPhoto.class);
    }

    public IpetPhoto unfavor(String photoId) {
        requireAuthorization();
        URI uri = buildUri("favor/unfavor");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", getCurrUserId());
        body.add("photoId", photoId);
        return getRestTemplate().postForObject(uri, body, IpetPhoto.class);
    }

    public List<IpetFavor> list(String photoId) {
        URI uri = buildUri("favor/list", "photoId", photoId);
        IpetFavor[] ret = getRestTemplate().getForObject(uri, IpetFavor[].class);
        List<IpetFavor> list = Arrays.asList(ret);
        return list;
    }

}
