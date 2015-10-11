/** 
 * @项目名称：TestApp   
 * @文件名：AucmaApp.java    
 * @版本信息：
 * @日期：2015年9月7日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.sy.testapp;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**    
 *     
 * @项目名称：TestApp    
 * @类名称：AucmaApp    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月7日 上午11:41:03    
 * @修改人：Administrator    
 * @修改时间：2015年9月7日 上午11:41:03    
 * @修改备注：    
 * @version     
 *     
 */
public class TestApp extends Application{
    
    private Stack<Activity> mActivities = new Stack<Activity>();
    
    private static TestApp mInstance;
    
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        

    }
    
    public static TestApp getInstance() {
        return mInstance;
    }
    
    public void add(Activity activity) {
        if (null != activity) {
            mActivities.push(activity);
        }
    }
    
    public void restart() {
        if (!mActivities.isEmpty()) {
            Activity activity = null;
            do {
                activity = mActivities.pop();
                activity.finish();
            }
            while (!mActivities.isEmpty());
            
            Intent it = new Intent(mInstance, WelcomeActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
            Log.i("SY", "WelcomeActivity restart.");
        }
    }
}
