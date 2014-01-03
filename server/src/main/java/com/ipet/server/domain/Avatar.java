/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author xiaojinghai
 *
 * Json转换有问题,此类暂时没用
 */
@Embeddable
public class Avatar implements Serializable {

    private String url12;

    private String url23;

    private String url48;

    private String url64;

    /**
     * @return the url12
     */
    public String getUrl12() {
        return url12;
    }

    /**
     * @param url12 the url12 to set
     */
    public void setUrl12(String url12) {
        this.url12 = url12;
    }

    /**
     * @return the url23
     */
    public String getUrl23() {
        return url23;
    }

    /**
     * @param url23 the url23 to set
     */
    public void setUrl23(String url23) {
        this.url23 = url23;
    }

    /**
     * @return the url48
     */
    public String getUrl48() {
        return url48;
    }

    /**
     * @param url48 the url48 to set
     */
    public void setUrl48(String url48) {
        this.url48 = url48;
    }

    /**
     * @return the url64
     */
    public String getUrl64() {
        return url64;
    }

    /**
     * @param url64 the url64 to set
     */
    public void setUrl64(String url64) {
        this.url64 = url64;
    }

}
