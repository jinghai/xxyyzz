/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;

/**
 *
 * @author yneos
 */
public class IpetUserUpdate {

    private String id;
    @JsonProperty("displayName")
    private String name;
    private String email;
    private String phone;

}
