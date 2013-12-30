/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import com.ipet.android.sdk.api.domain.IpetComment;
import com.ipet.android.sdk.api.domain.IpetPhoto;
import java.util.List;

/**
 *
 * @author xiaojinghai
 */
public interface PhotoOperations {

    /**
     * 发布图片
     *
     * @param photo
     */
    public void publicPhoto(IpetPhoto photo);

    /**
     * 发表评论
     *
     * @param comment
     */
    public void publicComment(IpetComment comment);

    /**
     * 获取最新n条图片
     *
     * @param n
     * @return
     */
    public List<IpetPhoto> getNewer(int n);

    /**
     * 获取某图片之后n条图片
     *
     * @param pid 图片ID
     * @param n
     * @return
     */
    public List<IpetPhoto> getNext(long pid, int n);
}
