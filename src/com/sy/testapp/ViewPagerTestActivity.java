/**
 * @项目名称：TestApp
 * @文件名：ViewPagerTestActivity.java
 * @版本信息：
 * @日期：2015年9月14日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.sy.testapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

/**
 * @项目名称：TestApp
 * @类名称：ViewPagerTestActivity
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015年9月14日 上午8:39:22
 * @修改人：Administrator
 * @修改时间：2015年9月14日 上午8:39:22
 * @修改备注：
 * @version
 */
public class ViewPagerTestActivity extends BaseActivity {
    
    private PagerFragment mPagerFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);
        
        mPagerFragment = new PagerFragment();
        getFragmentManager().beginTransaction().add(R.id.pager_frame, mPagerFragment).commit();
    }
}
