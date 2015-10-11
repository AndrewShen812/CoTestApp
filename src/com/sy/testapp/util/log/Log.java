/** 
 * @项目名称：TestApp   
 * @文件名：Log.java
 * @日期：2015年10月9日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.util.log;
/**       
 * @项目名称：TestApp    
 * @类名称：Log    
 * @类描述：  
 * @version          
 */
public class Log {
    public static boolean showMyLog = true;
    
    public static final String LOG_MY = "SY";
    
    public static Logger MyLog = new Logger(LOG_MY, showMyLog);
}
