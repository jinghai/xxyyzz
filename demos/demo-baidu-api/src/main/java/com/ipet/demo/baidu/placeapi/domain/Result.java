/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * 返回结果
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {

    private Integer status;     //本次API访问状态，如果成功返回0，如果失败返回其他数字。
    private String message;     //对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
    private Integer total;      //检索总数，用户请求中设置了page_num字段才会出现total字段。
    private List<T> results;    //结果集

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the results
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }
}
