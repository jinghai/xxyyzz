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
    
    //�˳�ջ��Activity 
    public void removeActivity(Activity activity) { 
        if (activity != null) { 
           //�ڴ��Զ��弯����ȡ����ǰActivityʱ��Ҳ������Activity�Ĺرղ��� 
            activity.finish(); 
            activityList.remove(activity); 
            activity = null; 
        } 
    } 
    //��õ�ǰջ��Activity 
    public Activity currentActivity() { 
        Activity activity = null; 
       if(!activityList.isEmpty()) 
         activity= activityList.get(activityList.size()-1);
        return activity; 
    } 
    //����ǰActivity����ջ�� 
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