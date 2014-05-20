package com.ipet.android.sdk.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.core.ApiContext;
import com.ipet.android.sdk.domain.IpetPhoto;

/**
 * 
 * @author xiaojinghai
 */
public class DiscoverApiImpl extends ApiBase implements DiscoverApi {

	public DiscoverApiImpl(ApiContext context) {
		super(context);
	}

	@Override
	public List<IpetPhoto> listPage(String date, String pageNumber, String pageSize) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("uid", context.getCurrUserId());
		parameters.set("date", date);
		parameters.set("pageNumber", pageNumber);
		parameters.set("pageSize", pageSize);

		URI uri = buildUri("discover/listPage", parameters);
		IpetPhoto[] users = context.getRestTemplate().getForObject(uri, IpetPhoto[].class);
		List<IpetPhoto> list = Arrays.asList(users);
		return list;
	}

}
