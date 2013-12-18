package com.ipet.android;

import org.apache.http.client.CookieStore;

import android.app.Application;

public class MyApp extends Application {
	private String SessionId = null;
	private CookieStore cookies;   
	 
    public CookieStore getCookie(){    
        return cookies;
    }
 
    public void setCookie(CookieStore cks){
        cookies = cks;
    }

	public String getSessionId() {
		return SessionId;
	}
    
  
 
}
