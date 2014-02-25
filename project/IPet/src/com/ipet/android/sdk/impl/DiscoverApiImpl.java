/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.impl;

import com.ipet.android.sdk.DiscoverApi;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.base.ApiContext;
import com.ipet.android.sdk.domain.IpetPhoto;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author yneos
 */
public class DiscoverApiImpl extends ApiBase implements DiscoverApi {

    public DiscoverApiImpl(ApiContext context) {
        super(context);
    }

    @Override
    public List<IpetPhoto> listPage(String date, String pageNumber, String pageSize) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("date", date);
        parameters.set("pageNumber", pageNumber);
        parameters.set("pageSize", pageSize);

        URI uri = buildUri("discover/listPage", parameters);
        IpetPhoto[] users = context.getRestTemplate().getForObject(uri, IpetPhoto[].class);
        List<IpetPhoto> list = Arrays.asList(users);
        return list;
    }

}
