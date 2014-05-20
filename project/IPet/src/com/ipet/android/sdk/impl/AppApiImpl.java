package com.ipet.android.sdk.impl;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.core.ApiContext;
import com.ipet.android.sdk.domain.IpetAppUpdate;
import java.net.URI;
import org.springframework.util.LinkedMultiValueMap;

public class AppApiImpl extends ApiBase implements AppApi {

    public AppApiImpl(ApiContext context) {
        super(context);
    }

    @Override
    public IpetAppUpdate checkAppVersion(String appKey) {
        URI uri = buildUri("app/checkUpdate");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("appKey", "ipet");
        IpetAppUpdate ipetAppUpdate = context.getRestTemplate().postForObject(uri, body, IpetAppUpdate.class);
        return ipetAppUpdate;
    }

}
