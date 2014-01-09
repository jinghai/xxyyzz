/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.web.rest.error;

import com.ipet.server.web.rest.exceptions.RestException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author yneos
 */
//@Aspect
public class ExceptionInterceptor {
//Todo:全系统异常转换:exception-conversion-aspect
//http://www.cnblogs.com/wushiqi54719880/archive/2011/08/09/2133048.html
//http://forum.spring.io/forum/spring-projects/aop/42208-exception-conversion-aspect

    @AfterThrowing(pointcut = "execution(* com.example.*.*(..))", throwing = "t")
    public void toRuntimeException(Throwable t) {

        //if (t instanceof KnownException) {
        //    throw (KnownException) t;
        // } else {
        RestException se = new RestException(t.getMessage());
        se.setStackTrace(t.getStackTrace());
        throw se;
        // }
    }
}
