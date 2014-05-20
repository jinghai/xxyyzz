package com.ipet.android.sdk.impl;

import java.util.List;

import com.ipet.android.sdk.domain.IpetFavor;
import com.ipet.android.sdk.domain.IpetPhoto;

/**
 * 赞API
 * 
 * @author xiaojinghai
 */
public interface FavorApi {

	public IpetPhoto favor(String photoId, String text);

	public IpetPhoto unfavor(String photoId);

	public List<IpetFavor> list(String photoId);

}
