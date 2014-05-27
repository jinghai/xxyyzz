package com.ipet.android.sdk.core;

import com.ipet.android.sdk.util.URIBuilder;
import android.content.Context;
import java.net.URI;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * API基类, 提供统一RestTemplate对象和一些常用方法
 *
 * @author xiaojinghai
 */
public class ApiBase {

    private final RestTemplateWrap restTemplate;

    private final Context context;

    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public ApiBase(Context ctx) {
        this.context = ctx;
        restTemplate = new RestTemplateWrap(ctx);
    }

    protected URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    protected URI buildUri(String path, String parameterName, String parameterValue) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(parameterName, parameterValue);
        return buildUri(path, parameters);
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(Constant.API_SERVER_BASE + path).queryParams(parameters).build();
    }

    protected void requireAuthorization() {
        if (!getIsAuthorized()) {
            throw new APIException("用户未登录");
        }
    }

    protected boolean getIsAuthorized() {
        return StateManager.getAuthorized(context);
    }

    protected void setIsAuthorized(boolean bl) {
        StateManager.setAuthorized(context, bl);
    }

    public String getCurrUserId() {
        return StateManager.getUid(context);
    }

    protected void setCurrUserId(String uid) {
        StateManager.setUid(context, uid);
    }

    protected RestTemplateWrap getRestTemplate() {
        return restTemplate;
    }

}
