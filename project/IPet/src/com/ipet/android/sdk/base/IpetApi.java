package com.ipet.android.sdk.base;

import android.content.Context;
import com.ipet.android.sdk.impl.AccountApi;
import com.ipet.android.sdk.impl.AppApi;
import com.ipet.android.sdk.impl.CommentApi;
import com.ipet.android.sdk.impl.ContactApi;
import com.ipet.android.sdk.impl.DiscoverApi;
import com.ipet.android.sdk.impl.FavorApi;
import com.ipet.android.sdk.impl.FeedbackApi;
import com.ipet.android.sdk.impl.PhotoApi;
import com.ipet.android.sdk.impl.UserApi;

/**
 * API门户，负载组装API组件，对用户提供统一访问接口
 *
 * @author xiaojinghai
 */
public class IpetApi extends ApiBase {

    private static IpetApi instance;

    private final AccountApi accountApi;

    private final UserApi userApi;

    private final ContactApi contactApi;

    private final PhotoApi photoApi;

    private final DiscoverApi discoverApi;

    private final FavorApi favorApi;

    private final CommentApi commentApi;

    private final FeedbackApi feedbackApi;

    private final AppApi appApi;

    public IpetApi(Context context) {
        super(context);
        accountApi = new AccountApi(context);
        userApi = new UserApi(context);
        contactApi = new ContactApi(context);
        photoApi = new PhotoApi(context);
        discoverApi = new DiscoverApi(context);
        favorApi = new FavorApi(context);
        commentApi = new CommentApi(context);
        feedbackApi = new FeedbackApi(context);
        appApi = new AppApi(context);
    }

    public AccountApi getAccountApi() {
        return accountApi;
    }

    public UserApi getUserApi() {
        return userApi;
    }

    public ContactApi getContactApi() {
        return contactApi;
    }

    public PhotoApi getPhotoApi() {
        return photoApi;
    }

    public DiscoverApi getDiscoverApi() {
        return discoverApi;
    }

    public CommentApi getCommentApi() {
        return commentApi;
    }

    public FavorApi getFavorApi() {
        return favorApi;
    }

    public FeedbackApi getFeedbackApi() {
        return feedbackApi;
    }

    public AppApi getAppApi() {
        return appApi;
    }

}
