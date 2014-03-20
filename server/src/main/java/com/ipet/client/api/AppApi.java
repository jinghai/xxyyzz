package com.ipet.client.api;

import com.ipet.client.api.domain.IpetAppUpdate;

/**
 * 应用API
 *
 * @author luocanfeng
 */
public interface AppApi {

    /**
     * 检查应用版本信息
     *
     * @param appKey
     * @return
     */
    public IpetAppUpdate checkAppVersion(String appKey);

}
