package com.ipet.client.api.base;

/**
 * Service层公用的Exception.
 *
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * @author calvin
 */
public class APIException extends RuntimeException {

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }
}
