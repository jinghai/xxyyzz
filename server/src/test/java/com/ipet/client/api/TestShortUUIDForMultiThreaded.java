package com.ipet.client.api;

import com.ipet.client.api.impl.IpetApiImpl;
import com.ipet.server.util.ProjectUtil;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
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
public class TestShortUUIDForMultiThreaded {

    private static final Logger logger = LoggerFactory.getLogger(TestShortUUIDForMultiThreaded.class);
    private AccountApi api = IpetApiImpl.getInstance("1", "1").getAccountApi();

    //测试短UUID重复的可能性
    @Test
    public void TestShortUUID() throws Throwable {
        //TestRunnable，实例化自定义的7个线程
        TestRunnable tr1, tr2, tr3, tr4;
        tr1 = new ThreadA();
        tr2 = new ThreadB();
        tr3 = new ThreadB();
        tr4 = new ThreadB();
        //必须声明为一个数组，把该数组当参数传递给 MultiThreadedTestRunner
        TestRunnable[] trs = {tr1, tr2, tr3, tr4};
        //不需改动
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
        //执行MTTR和7线程
        mttr.runTestRunnables();
    }

    private class ThreadA extends TestRunnable {

        @Override
        public void runTest() throws Throwable {
            long times = 1000;
            long start = System.currentTimeMillis();
            for (long i = 0; i < times; i++) {
                api.register(ProjectUtil.generateShortUUID(), "test");
            }
            long end = System.currentTimeMillis();
            Long useAV = (end - start) / times;
            logger.debug("ThreadA-useAV:" + useAV);
        }
    }

    private class ThreadB extends TestRunnable {

        @Override
        public void runTest() throws Throwable {
            long times = 1000;
            long start = System.currentTimeMillis();
            for (long i = 0; i < times; i++) {
                api.register(ProjectUtil.generateShortUUID(), "test");
            }
            long end = System.currentTimeMillis();
            Long useAV = (end - start) / times;
            logger.debug("ThreadB-useAV:" + useAV);
        }
    }

    private class ThreadC extends TestRunnable {

        @Override
        public void runTest() throws Throwable {
            long times = 1000;
            long start = System.currentTimeMillis();
            for (long i = 0; i < times; i++) {
                api.register(ProjectUtil.generateShortUUID(), "test");
            }
            long end = System.currentTimeMillis();
            Long useAV = (end - start) / times;
            logger.debug("ThreadC-useAV:" + useAV);
        }
    }

    private class ThreadD extends TestRunnable {

        @Override
        public void runTest() throws Throwable {
            long times = 1000;
            long start = System.currentTimeMillis();
            for (long i = 0; i < times; i++) {
                api.register(ProjectUtil.generateShortUUID(), "test");
            }
            long end = System.currentTimeMillis();
            Long useAV = (end - start) / times;
            logger.debug("ThreadD-useAV:" + useAV);
        }
    }

}
