package com.ipet.client.api;

import java.util.List;

import com.ipet.client.api.domain.IpetFavor;

/**
 * 赞API
 * 
 * @author xiaojinghai
 */
public interface FavorApi {

	public IpetFavor favor(String photoId, String text);

	public List<IpetFavor> list(String photoId);

}
