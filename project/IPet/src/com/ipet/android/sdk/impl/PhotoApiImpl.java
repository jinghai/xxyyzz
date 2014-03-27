package com.ipet.android.sdk.impl;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ipet.android.sdk.PhotoApi;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.base.ApiContext;
import com.ipet.android.sdk.domain.IpetPhoto;

/**
 * 
 * @author yneos
 */
public class PhotoApiImpl extends ApiBase implements PhotoApi {

	public PhotoApiImpl(ApiContext context) {
		super(context);
	}

	@Override
	public IpetPhoto publish(String text, FileSystemResource file) {
		requireAuthorization();
		URI uri = buildUri("photo/create");
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("uid", context.getCurrUserId());
		body.add("context", text);
		body.add("file", file);
		IpetPhoto ret = context.getRestTemplate().postForObject(uri, body, IpetPhoto.class);
		return ret;
	}

	@Override
	public IpetPhoto publish(String text, File file) {
		FileSystemResource fsr = new FileSystemResource(file);
		IpetPhoto ret = publish(text, fsr);
		return ret;
	}

	@Override
	public IpetPhoto publish(String text, String file) {
		File f = new File(file);
		IpetPhoto ret = publish(text, f);
		return ret;
	}

	@Override
	public List<IpetPhoto> listFollowd(String date, String pageNumber, String pageSize) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("uid", context.getCurrUserId());
		parameters.set("date", date);
		parameters.set("pageNumber", pageNumber);
		parameters.set("pageSize", pageSize);

		URI uri = buildUri("photo/listFollow", parameters);
		IpetPhoto[] ret = context.getRestTemplate().getForObject(uri, IpetPhoto[].class);
		List<IpetPhoto> list = Arrays.asList(ret);
		return list;
	}

}
