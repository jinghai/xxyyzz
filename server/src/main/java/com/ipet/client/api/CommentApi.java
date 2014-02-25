/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

import com.ipet.client.api.domain.IpetComment;
import java.util.List;

/**
 * 评论API
 *
 * @author xiaojinghai
 */
public interface CommentApi {

    /**
     * 发表评论
     *
     * @param photoId
     * @param text
     * @return
     */
    public IpetComment comment(String photoId, String text);

    /**
     * 分页获取评论列表
     *
     * @param photoId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<IpetComment> listPage(String photoId, String pageNumber, String pageSize);
}
