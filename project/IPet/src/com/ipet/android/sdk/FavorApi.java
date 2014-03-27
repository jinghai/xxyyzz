package com.ipet.android.sdk;

import java.util.List;

import com.ipet.android.sdk.domain.IpetFavor;

/**
 * 赞API
 * 
 * @author xiaojinghai
 */
public interface FavorApi {

	public IpetFavor favor(String photoId, String text);

	public List<IpetFavor> list(String photoId);

}
