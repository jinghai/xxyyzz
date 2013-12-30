/*
 * Copyright 2013-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ipet.android.sdk.api.impl;

import com.ipet.android.sdk.api.AccountOperations;
import com.ipet.android.sdk.api.ContactOperations;
import com.ipet.android.sdk.api.DiscoverOperations;
import com.ipet.android.sdk.api.Ipet;
import com.ipet.android.sdk.api.PhotoOperations;
import com.ipet.android.sdk.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestOperations;

/**
 * <p>
 * The central class for interacting with Ipet.
 * </p>
 *
 * @author Craig Walls
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class IpetTemplate extends AbstractOAuth2ApiBinding implements Ipet {

    private AccountOperations accountOperations;
    private ContactOperations contactOperations;
    private DiscoverOperations discoverOperations;
    private PhotoOperations photoOperations;
    private UserOperations userOperations;

    /**
     * 无参构造函数，用于无需验证即可调用的公开rest接口 No-arg constructor to support cases in which
     * you want to call the Ipet API without requiring authorization. This is
     * useful for public operations, such as getting the list of watchers for a
     * public repository.
     */
    public IpetTemplate() {
        super();
        initSubApis();
    }

    /**
     * Constructs a GitHubTemplate with the minimal amount of information
     * required to sign requests with an OAuth <code>Authorization</code>
     * header.
     *
     * @param accessToken An access token granted to the application after OAuth
     * authentication.
     */
    public IpetTemplate(String accessToken) {
        super(accessToken);
        initSubApis();
    }

    public AccountOperations accountOperations() {
        return accountOperations;
    }

    public ContactOperations contactOperations() {
        return contactOperations;
    }

    public DiscoverOperations discoverOperations() {
        return discoverOperations;
    }

    public PhotoOperations photoOperations() {
        return photoOperations;
    }

    public UserOperations userOperations() {
        return userOperations;
    }

    public RestOperations restOperations() {
        return getRestTemplate();
    }

    // internal helpers
    private void initSubApis() {
        this.accountOperations = new AccountTemplate(getRestTemplate(), isAuthorized());
        this.contactOperations = new ContactTemplate(getRestTemplate(), isAuthorized());
        this.discoverOperations = new DiscoverTemplate(getRestTemplate(), isAuthorized());
        this.photoOperations = new PhotoTemplate(getRestTemplate(), isAuthorized());
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
    }

}
