/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import com.ipet.android.sdk.api.domain.Photo;
import java.util.List;

/**
 *
 * @author xiaojinghai
 */
public interface DiscoverOperations {

    /**
     * 获取最新n条热点图片
     *
     * @param n
     * @return
     */
    public List<Photo> getNewer(int n);

    /**
     * 获取某图片之后n条热点图片
     *
     * @param pid 图片ID
     * @param n
     * @return
     */
    public List<Photo> getNext(long pid, int n);

}
