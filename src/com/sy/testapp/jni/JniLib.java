/** 
 * @项目名称：TestApp   
 * @文件名：JniLib.java    
 * @版本信息：
 * @日期：2015年9月25日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.jni;

import android.os.Handler;
import android.os.Message;

/**       
 * @项目名称：TestApp    
 * @类名称：JniLib    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月25日 下午2:48:54    
 * @修改人：Administrator    
 * @修改时间：2015年9月25日 下午2:48:54    
 * @修改备注：    
 * @version          
 */
public class JniLib {
    
    static {
        System.loadLibrary("CLibModule");
    }
    
    public static Handler mJniHandler;
    
    public static native void userLogin(String name, String password);
    
    /** Jni中回调的方法 */
    public static void CCallBack(int event, int uid, int ext) {
        Message msg = mJniHandler.obtainMessage();
        msg.what = event;
        msg.arg1 = uid;
        msg.arg2 = ext;
        mJniHandler.sendMessage(msg);
    }
}
