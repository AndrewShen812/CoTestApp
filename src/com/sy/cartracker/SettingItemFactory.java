/** 
 * @项目名称：CoTestApp   
 * @文件名：SettingItemFactory.java    
 * @版本信息：
 * @日期：2015-10-24             
 */
package com.sy.cartracker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.sy.testapp.R;

/**    
 * @项目名称：CoTestApp    
 * @类名称：SettingItemFactory    
 * @类描述：    
 * @version		  
 */
public class SettingItemFactory {
    
    public static enum ItemId {
        SPEED,
        DISTANCE,
        TIME,
        VOICE,
        OTHER,
        MAX;
    }
    
    private List<ItemId> mIds = new ArrayList<SettingItemFactory.ItemId>();
    private List<SettingItem> mItems = new ArrayList<SettingItem>();
    private List<Integer> mIconIds;
    private List<String> mTitles;
    private Context mContext;
    public SettingItemFactory(Context context) {
        mContext = context;
        initIconRes();
        initTitles();
    }
    
    private void initIconRes() {
        mIconIds = new ArrayList<Integer>();
        mIconIds.add(R.drawable.more_menu_about);
        mIconIds.add(R.drawable.more_menu_feedback);
        mIconIds.add(R.drawable.more_menu_phone);
        mIconIds.add(R.drawable.more_menu_lang);
        mIconIds.add(R.drawable.more_menu_settings);
    }
    
    private void initTitles() {
        mTitles = new ArrayList<String>();
        mTitles.add(mContext.getString(R.string.setting_item_speed));
        mTitles.add(mContext.getString(R.string.setting_item_distance));
        mTitles.add(mContext.getString(R.string.setting_item_time));
        mTitles.add(mContext.getString(R.string.setting_item_voice));
        mTitles.add(mContext.getString(R.string.setting_item_other));
    }
    
    public void add(ItemId itemId) {
        // 避免重复添加
        if (!mIds.contains(itemId)) {
            mIds.add(itemId);
            SettingItem item = new SettingItem(mIconIds.get(itemId.ordinal()), mTitles.get(itemId.ordinal()));
            mItems.add(item);
        }
    }
    
    public List<SettingItem> getItems() {
        List<SettingItem> retItems = new ArrayList<SettingItem>();
        for (SettingItem item : mItems) {
            retItems.add(item);
        }
        return retItems;
    }
}
