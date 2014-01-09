package com.ipet.server.web.rest.exceptions;

/**
 * Service层公用的Exception.
 *
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * @author calvin
 */
public class RestException extends RuntimeException {

    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
    }
}
