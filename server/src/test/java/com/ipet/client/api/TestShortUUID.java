package com.ipet.client.api;

import com.ipet.client.api.base.APIException;
import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.impl.IpetApiImpl;
import com.ipet.server.util.ProjectUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class TestShortUUID {

    private static final Logger logger = LoggerFactory.getLogger(TestShortUUID.class);
    private AccountApi api = IpetApiImpl.getInstance("1", "1").getAccountApi();

    //测试短UUID重复的可能性
    @Test
    public void TestShortUUID() {
        //long times = 100 * 10000; //一百万次
        long times = 1000;
        long start = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            api.register(ProjectUtil.generateShortUUID(), "test");
        }
        long end = System.currentTimeMillis();
        Long useAV = (end - start) / times;
        logger.debug("useAV:" + useAV);
    }

}
