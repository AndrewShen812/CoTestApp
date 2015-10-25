/** 
 * @项目名称：CoTestApp   
 * @文件名：SettingItem.java    
 * @版本信息：
 * @日期：2015-10-24             
 */
package com.sy.cartracker;

/**    
 * @项目名称：CoTestApp    
 * @类名称：SettingItem    
 * @类描述：    
 * @version		  
 */
public class SettingItem {
    public int iconResId;
    public String title;
    public boolean showMore;
    public boolean showSwitch;
    public String switchTitle;
    
    public SettingItem() {
        showMore = true;
        showSwitch = false;
    }

    public SettingItem(int iconResId, String title) {
        this.iconResId = iconResId;
        this.title = title;
        showMore = true;
        showSwitch = false;
    }

    public SettingItem(int iconResId, String title, String switchTitle) {
        super();
        this.iconResId = iconResId;
        this.title = title;
        showMore = false;
        showSwitch = true;
        this.switchTitle = switchTitle;
    }
    
}
