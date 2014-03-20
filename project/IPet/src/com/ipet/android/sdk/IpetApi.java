/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk;

/**
 * IpetApi 门面
 *
 * @author xiaojinghai
 */
public interface IpetApi {

    /**
     * 评论API
     *
     * @return
     */
    public CommentApi getCommentApi();

    /**
     * 攒API
     *
     * @return
     */
    public FavorApi getFavorApi();

    /**
     * 发现API
     *
     * @return
     */
    public DiscoverApi getDiscoverApi();

    /**
     * 返回帐号API.
     *
     * @return AccountApi
     */
    public AccountApi getAccountApi();

    /**
     * 返回用户API.
     *
     * @return UserApi
     */
    public UserApi getUserApi();

    /**
     * 返回用户关系API.
     *
     * @return
     */
    public ContactApi getContactApi();

    /**
     * 返回图片API
     *
     * @return
     */
    public PhotoApi getPhotoApi();

    /**
     * 返回意见反馈API
     */
    public FeedbackApi getFeedbackApi();

    /**
     * 返回应用API
     *
     * @return
     */
    public AppApi getAppApi();

    /**
     * 获取当前用户Id
     *
     * @return
     */
    public String getCurrUserId();

}
