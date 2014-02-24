/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.impl;

import com.ipet.client.api.FavorApi;
import com.ipet.client.api.base.ApiBase;
import com.ipet.client.api.base.ApiContext;
import com.ipet.client.api.domain.IpetFavor;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.LinkedMultiValueMap;

/**
 *
 * @author yneos
 */
public class FavorApiImpl extends ApiBase implements FavorApi {

    public FavorApiImpl(ApiContext context) {
        super(context);
    }

    @Override
    public IpetFavor favor(String photoId, String text) {
        requireAuthorization();
        URI uri = buildUri("favor/create");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", context.getCurrUserId());
        body.add("photoId", photoId);
        body.add("text", text);
        IpetFavor ret = context.getRestTemplate().postForObject(uri, body, IpetFavor.class);
        return ret;
    }

    @Override
    public List<IpetFavor> list(String photoId) {
        URI uri = buildUri("favor/list", "photoId", photoId);
        IpetFavor[] ret = context.getRestTemplate().getForObject(uri, IpetFavor[].class);
        List<IpetFavor> list = Arrays.asList(ret);
        return list;
    }
}
