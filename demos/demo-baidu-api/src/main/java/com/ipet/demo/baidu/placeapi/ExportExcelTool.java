/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author xiaojinghai
 */
public class ExportExcelTool {

    public static final String ak = "559933d2044dafee4bcc08c1b691673c";
    public static final String base_url = "http://api.map.baidu.com/place/v2/";

    public static void main(String[] args) {

    }

    public static void log(Object o) {
        if (o instanceof String) {
            System.out.println(o);
        } else {
            System.out.println(ToStringBuilder.reflectionToString(o));
        }
    }
}
