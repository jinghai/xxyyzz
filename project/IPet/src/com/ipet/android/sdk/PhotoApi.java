package com.ipet.android.sdk;

import android.content.Context;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetPhoto;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class PhotoApi extends ApiBase {

    public PhotoApi(Context context) {
        super(context);
    }

    public IpetPhoto publish(String text, FileSystemResource file) {
        requireAuthorization();
        URI uri = buildUri("photo/create");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", getCurrUserId());
        body.add("context", text);
        body.add("file", file);
        IpetPhoto ret = getRestTemplate().postForObject(uri, body, IpetPhoto.class);
        return ret;
    }

    public IpetPhoto publish(String text, File file) {
        FileSystemResource fsr = new FileSystemResource(file);
        IpetPhoto ret = publish(text, fsr);
        return ret;
    }

    public IpetPhoto publish(String text, String file) {
        File f = new File(file);
        IpetPhoto ret = publish(text, f);
        return ret;
    }

    /**
     * 为了解决发布文字乱码问题而临时提供的一个接口
     */
    @Deprecated
    public IpetPhoto publishText(String id, String text) {
        requireAuthorization();
        URI uri = buildUri("photo/publishText");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("uid", getCurrUserId());
        body.add("id", id);
        body.add("context", text);
        IpetPhoto result = getRestTemplate().postForObject(uri, body, IpetPhoto.class);
        return result;
    }

    public List<IpetPhoto> listFollowd(String date, String pageNumber, String pageSize) {
        requireAuthorization();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("uid", getCurrUserId());
        parameters.set("date", date);
        parameters.set("pageNumber", pageNumber);
        parameters.set("pageSize", pageSize);

        URI uri = buildUri("photo/listFollow", parameters);
        IpetPhoto[] ret = getRestTemplate().getForObject(uri, IpetPhoto[].class);
        List<IpetPhoto> list = Arrays.asList(ret);
        return list;
    }

}
