package com.ipet.android.ui.manager;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class ActivityManager { 
	private List<Activity> activityList = new LinkedList<Activity>(); 
    private static ActivityManager instance; 
    private ActivityManager() { 
    } 
    
    
    public static ActivityManager getInstance() {  
        if (null == instance) {  
            instance = new ActivityManager();  
        }  
        return instance;  
    }  
    
    //退出栈顶Activity 
    public void removeActivity(Activity activity) { 
        if (activity != null) { 
           //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作 
            activity.finish(); 
            activityList.remove(activity); 
            activity = null; 
        } 
    } 
    //获得当前栈顶Activity 
    public Activity currentActivity() { 
        Activity activity = null; 
       if(!activityList.isEmpty()) 
         activity= activityList.get(activityList.size()-1);
        return activity; 
    } 
    //将当前Activity推入栈中 
    public void addActivity(Activity activity) { 
    	activityList.add(activity); 
    } 
    
    public void exit() {  
        for (Activity activity : activityList) {  
            activity.finish();  
        }  
        System.exit(0);  
    } 
} 