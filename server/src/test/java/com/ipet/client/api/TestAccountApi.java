package com.ipet.client.api;

import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.impl.IpetApiImpl;
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
public class TestAccountApi {

    private static final Logger logger = LoggerFactory.getLogger(TestAccountApi.class);
    private AccountApi api = IpetApiImpl.getInstance("1", "1").getAccountApi();

    @Test
    public void login() {
        IpetUser ret = api.login("admin", "admin");
        logger.debug(ToStringBuilder.reflectionToString(ret));
        assertEquals("1", ret.getId());
    }

    @Test
    public void logout() {
        api.logout();
    }

    @Test
    public void register() {
        IpetUser ret = api.register("测试", "test");
        logger.debug(ToStringBuilder.reflectionToString(ret));
        assertEquals("测试", ret.getLoginName());
    }

    @Test
    public void checkLoginName() {
        Boolean ret = api.checkLoginName("neverused");
        assertEquals(true, ret);
        ret = api.checkLoginName("admin");
        assertEquals(false, ret);
    }

    @Test
    public void checkPhone() {
        Boolean ret = api.checkPhone("9999");
        logger.debug(ret.toString());
        assertEquals(true, ret);
    }

    @Test
    public void checkEmail() {
        Boolean ret = api.checkEmail("test@test.com");
        logger.debug(ret.toString());
        assertEquals(true, ret);
    }

    @Test
    public void changePassword() {
        api.login("admin", "admin");
        Boolean ret = api.changePassword("admin", "admin");
        logger.debug(ret.toString());
        assertEquals(true, ret);
    }

}
