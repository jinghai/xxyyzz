package com.ipet.android.service;

import com.ipet.android.sdk.api.Ipet;

public class IpetService extends SocialService {

    private final Ipet ipet;

    public IpetService() {
        this.ipet = getApi(Ipet.class);
    }

}
