/** 
 * @项目名称：TestApp   
 * @文件名：MyCalendarView.java
 * @日期：2015年10月9日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**       
 * @项目名称：TestApp    
 * @类名称：MyCalendarView    
 * @类描述：  
 * @version          
 */
public class MyCalendarView extends LinearLayout{

    /**    
     * 创建一个新的实例 MyCalendarView.    
     * @param context
     * @param attrs
     * @param defStyleAttr    
     */
    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init(context);
    }

    /**    
     * 创建一个新的实例 MyCalendarView.    
     * @param context
     * @param attrs    
     */
    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }

    /**    
     * 创建一个新的实例 MyCalendarView.    
     * @param context    
     */
    public MyCalendarView(Context context) {
        super(context);
        init(context);
    }
    
    private void getAttrs(Context context, AttributeSet attrs) {
        if (null == attrs) {
            return;
        }
    }
    
    private void init(Context context) {
        
    }
    
}
