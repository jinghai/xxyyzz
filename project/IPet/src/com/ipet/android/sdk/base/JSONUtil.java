/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.base;

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

    public static <T> T fromJSON(String str, TypeReference valueTypeRef) {
        ObjectMapper mapper = new ObjectMapper();
        T t;
        try {
            t = mapper.readValue(str, valueTypeRef);
        } catch (IOException ex) {
            throw new APIException(ex);
        }
        return t;
    }
}
