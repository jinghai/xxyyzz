/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk;

import com.ipet.android.sdk.domain.IpetPhoto;
import java.util.List;

/**
 * 发现API
 *
 * @author xiaojinghai
 */
public interface DiscoverApi {

    /**
     * 分页获取在某时间点之后发布的图片
     *
     * @param date 时间字符串"yyyy-MM-dd HH:mm:ss"
     * @param pageNumber
     * @param pageSize
     * @return List<IpetPhoto>
     */
    public List<IpetPhoto> listPage(String date, String pageNumber, String pageSize);
}