/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.test;

import com.ipet.client.api.base.APIException;
import com.ipet.client.api.domain.IpetUser;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Administrator
 */
public class JSONUtil {

    private final String TAG = "JSONUtil";

    public static String toJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        Writer str = new StringWriter();
        try {
            mapper.writeValue(str, o);
        } catch (IOException ex) {
            throw new APIException(ex);
        }
        return str.toString();
    }

    public static <T> T extractData(String str) {
        TypeReference<Class<T>> type = new TypeReference<Class<T>>() {
        };
        return fromJSON(str, type);
    }

    public static <T> T fromJSON(String str, TypeReference<Class<T>> valueTypeRef) {
        ObjectMapper mapper = new ObjectMapper();
        T t;
        try {
            t = mapper.readValue(str, valueTypeRef);
        } catch (IOException ex) {
            throw new APIException(ex);
        }
        return t;
    }

    public static <T> T fromJSON(String str, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        T t;
        try {
            t = mapper.readValue(str, type);
        } catch (IOException ex) {
            throw new APIException(ex);
        }
        return t;
    }

    public static void main(String arge[]) {
        IpetUser user = new IpetUser();
        user.setId("888");
        String str = toJson(user);
        System.out.println("json:" + str);
        TypeReference<IpetUser> userType = new TypeReference<IpetUser>() {
        };
        IpetUser u = extractData(str);
        System.out.println("转换后:" + u.getId());

    }
}
