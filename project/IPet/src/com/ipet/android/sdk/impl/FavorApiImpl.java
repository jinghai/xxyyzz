package com.ipet.android.sdk.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;

import com.ipet.android.sdk.FavorApi;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.base.ApiContext;
import com.ipet.android.sdk.domain.IpetFavor;
import com.ipet.android.sdk.domain.IpetPhoto;

/**
 * 
 * @author xiaojinghai
 */
public class FavorApiImpl extends ApiBase implements FavorApi {

	public FavorApiImpl(ApiContext context) {
		super(context);
	}

	@Override
	public IpetPhoto favor(String photoId, String text) {
		requireAuthorization();
		URI uri = buildUri("favor/favor");
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("uid", context.getCurrUserId());
		body.add("photoId", photoId);
		body.add("text", text);
		return context.getRestTemplate().postForObject(uri, body, IpetPhoto.class);
	}

	@Override
	public IpetPhoto unfavor(String photoId) {
		requireAuthorization();
		URI uri = buildUri("favor/unfavor");
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("uid", context.getCurrUserId());
		body.add("photoId", photoId);
		return context.getRestTemplate().postForObject(uri, body, IpetPhoto.class);
	}

	@Override
	public List<IpetFavor> list(String photoId) {
		URI uri = buildUri("favor/list", "photoId", photoId);
		IpetFavor[] ret = context.getRestTemplate().getForObject(uri, IpetFavor[].class);
		List<IpetFavor> list = Arrays.asList(ret);
		return list;
	}

}
