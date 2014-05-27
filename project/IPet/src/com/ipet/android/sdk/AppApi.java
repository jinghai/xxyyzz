package com.ipet.android.sdk;

import android.content.Context;
import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.core.Constant;
import com.ipet.android.sdk.domain.IpetAppUpdate;
import java.net.URI;
import org.springframework.util.LinkedMultiValueMap;

public class AppApi extends ApiBase {

    public AppApi(Context context) {
        super(context);
    }

    public IpetAppUpdate checkAppVersion(String appKey) {
        URI uri = buildUri("app/checkUpdate");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("appKey", Constant.APP_KEY);
        IpetAppUpdate ipetAppUpdate = getRestTemplate().postForObject(uri, body, IpetAppUpdate.class);
        return ipetAppUpdate;
    }

}
