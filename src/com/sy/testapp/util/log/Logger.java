/** 
 * @项目名称：TestApp   
 * @文件名：Logger.java
 * @日期：2015年10月9日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.util.log;
/**       
 * @项目名称：TestApp    
 * @类名称：Logger    
 * @类描述：  
 * @version          
 */
public class Logger {
    
    private String TAG = "Logger";
    
    private boolean DEBUG;

    public Logger(String tag, boolean debug) {
        TAG = tag;
        DEBUG = debug;
    }
    
    public void i(String msg) {
        if (DEBUG) {
            android.util.Log.i(TAG, msg);
        }
    }
    
    public void d(String msg) {
        if (DEBUG) {
            android.util.Log.d(TAG, msg);
        } 
    }
    
    public void w(String msg) {
        if (DEBUG) {
            android.util.Log.w(TAG, msg);
        }
    }
    
    public void e(String msg) {
        if (DEBUG) {
            android.util.Log.e(TAG, msg);
        }
    }
}
