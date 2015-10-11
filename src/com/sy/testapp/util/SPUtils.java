/** 
 * @项目名称：TestApp   
 * @文件名：SPUtils.java    
 * @版本信息：
 * @日期：2015年9月7日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.sy.testapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**    
 *     
 * @项目名称：TestApp    
 * @类名称：SPUtils    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月7日 上午8:54:01    
 * @修改人：Administrator    
 * @修改时间：2015年9月7日 上午8:54:01    
 * @修改备注：    
 * @version     
 *     
 */
public class SPUtils {
    public static final String FILE_NAME = "config_info";
    
    public static final String LANGUAGE = "language";
    
    private static SharedPreferences mInstance;
    
    public static void init(Context context) {
        if (null == mInstance) {
            synchronized (SPUtils.class) {
                if (null == mInstance) {
                    mInstance = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
                }
            }
        }
    }
    
    public static String getLanguage(Context context) {
        init(context);
        return mInstance.getString(LANGUAGE, "ch");
    }
    
    public static void setlanguage(Context context, String language) {
        init(context);
        Editor editor = mInstance.edit();
        editor.putString(LANGUAGE, language);
        editor.commit();
    }
}
