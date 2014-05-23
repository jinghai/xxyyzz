package com.ipet.android.sdk;

import android.content.Context;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetPhoto;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class DiscoverApi extends ApiBase {

    public DiscoverApi(Context context) {
        super(context);
    }

    public List<IpetPhoto> listPage(String date, String pageNumber, String pageSize) {
        requireAuthorization();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("uid", getCurrUserId());
        parameters.set("date", date);
        parameters.set("pageNumber", pageNumber);
        parameters.set("pageSize", pageSize);

        URI uri = buildUri("discover/listPage", parameters);
        IpetPhoto[] users = getRestTemplate().getForObject(uri, IpetPhoto[].class);
        List<IpetPhoto> list = Arrays.asList(users);
        return list;
    }

}
