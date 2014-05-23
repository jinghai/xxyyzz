package com.ipet.android.sdk;

import android.content.Context;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetComment;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class CommentApi extends ApiBase {

    public CommentApi(Context context) {
        super(context);
    }

    public IpetComment comment(String photoId, String text) {
        requireAuthorization();
        URI uri = buildUri("comment/create");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", getCurrUserId());
        body.add("photoId", photoId);
        body.add("text", text);
        IpetComment ret = getRestTemplate().postForObject(uri, body, IpetComment.class);
        return ret;
    }

    public List<IpetComment> listPage(String photoId, String pageNumber, String pageSize) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("photoId", photoId);
        parameters.set("pageNumber", pageNumber);
        parameters.set("pageSize", pageSize);

        URI uri = buildUri("comment/listPage", parameters);
        IpetComment[] ret = getRestTemplate().getForObject(uri, IpetComment[].class);
        List<IpetComment> list = Arrays.asList(ret);
        return list;
    }

}
