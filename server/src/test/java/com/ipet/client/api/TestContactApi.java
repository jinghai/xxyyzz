package com.ipet.client.api;

import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.impl.IpetApiImpl;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class TestContactApi {

    private static final Logger logger = LoggerFactory.getLogger(TestContactApi.class);
    private final IpetApi IpetApi = IpetApiImpl.getInstance("1", "1");
    private final ContactApi api = IpetApiImpl.getInstance("1", "1").getContactApi();

    @Test
    public void test() {
        IpetApi.getAccountApi().login("admin", "admin");
        api.follow("2");

        IpetApi.getAccountApi().login("user", "user");
        api.follow("1");

        List<IpetUser> ret = api.getFollowList();
        assertEquals(1, ret.size());

        ret = api.getFollowerList();
        assertEquals(1, ret.size());

        ret = api.getFriendList();
        assertEquals(1, ret.size());

        api.unfollow("1");

        ret = api.getFollowList();
        assertEquals(0, ret.size());

        ret = api.getFollowerList();
        assertEquals(1, ret.size());

        ret = api.getFriendList();
        assertEquals(0, ret.size());

        IpetApi.getAccountApi().login("admin", "admin");
        api.unfollow("2");

    }

}
