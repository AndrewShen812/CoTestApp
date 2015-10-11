/**
 * @项目名称：TestApp
 * @文件名：ViewPageAdapter.java
 * @版本信息：
 * @日期：2015年9月14日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.sy.testapp.util.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

/**
 * @项目名称：TestApp
 * @类名称：ViewPageAdapter
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015年9月14日 上午9:02:14
 * @修改人：Administrator
 * @修改时间：2015年9月14日 上午9:02:14
 * @修改备注：
 * @version
 */
public class ViewPageAdapter extends PagerAdapter {
    
    ImageView[] mImageViews;
    
    public ViewPageAdapter(Context context, ImageView[] imageViews) {
        mImageViews = imageViews;
    }
    
    @Override
    public int getCount() {
        return mImageViews == null ? 0 : mImageViews.length;
    }
    
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
        return mImageViews[position % mImageViews.length];
        
    }
    
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);
    }
    
}
