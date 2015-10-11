/**
 * @项目名称：TestApp
 * @文件名：BaseActivity.java
 * @版本信息：
 * @日期：2015年9月7日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.sy.testapp;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sy.testapp.jni.JniLib;
import com.sy.testapp.util.AppConfig;
import com.sy.testapp.util.SPUtils;
import com.sy.testapp.view.TitleBar;
import com.sy.testapp.view.TitleBar.OnTitleClickListener;

/**
 * @项目名称：TestApp
 * @类名称：BaseActivity
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015年9月7日 上午8:53:00
 * @修改人：Administrator
 * @修改时间：2015年9月7日 上午8:53:00
 * @修改备注：
 * @version
 */
public class BaseActivity extends FragmentActivity {
    
    protected Activity mContext;
    
    private LinearLayout mLayoutContainer;
    
    private DrawerLayout mDrawerContainer;
    
    private TitleBar mTitle;
    
    public static final int VERSION_KITKAT = 19;
    
    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestApp.getInstance().add(this);
        
//         if (VERSION.SDK_INT >= VERSION_KITKAT) {
//             // 透明状态栏
//             getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//             // 透明导航栏
//             getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//         }
        
        mContext = this;
        SPUtils.init(this);
        setLanguage(this, SPUtils.getLanguage(this));
    }
    
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mDrawerContainer = (DrawerLayout) findViewById(R.id.drawer_basic_container);
        mTitle = new TitleBar(mContext);
        mTitle.setOnTitleClickListener(mTitleListener);
        View content = LayoutInflater.from(mContext).inflate(layoutResID, null);
        mLayoutContainer = (LinearLayout) findViewById(R.id.base_page);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mLayoutContainer.addView(mTitle);
        mLayoutContainer.addView(content, lParams);
    }
    protected void lockDrawerScroll() {
        mDrawerContainer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    
    private OnTitleClickListener mTitleListener = new OnTitleClickListener() {
        @Override
        public void onNavBack() {
            finish();
        }
        
        @Override
        public void onAddClick() {
            onTitleAdd();
        }
        
        @Override
        public void onMenuClick() {
            mDrawerContainer.openDrawer(Gravity.RIGHT);
        }
    };
    
    protected void onTitleAdd() {
    }
    
    private void setLanguage(Context context, String language) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        if (AppConfig.LANGUAGE_EN.equals(language)) {
            config.locale = Locale.ENGLISH;
        }
        else {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        // 保存设置的语言类型
        SPUtils.setlanguage(context, language);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        setHandler();
    }
    
    private Handler mHandler = new Handler(new Callback() {
        public boolean handleMessage(Message msg) {
            HandleCallBack(msg.what, msg.arg1, msg.arg2);
            return true;
        }
    });
    
    private void setHandler() {
        JniLib.mJniHandler = mHandler;
    }
    
    protected void HandleCallBack(int event, int uid, int ext) {
        Log.i("SY", "HandleCallBack ----> event=" + event + ", uid=" + uid + ", ext=" + ext);
    }
    
    /**
     * @description 标题栏是否可见
     * @date 2015年9月28日
     * @param visible
     */
    protected void setTitleVisible(boolean visible) {
        if (visible) {
            mTitle.setVisibility(View.VISIBLE);
        }
        else {
            mTitle.setVisibility(View.GONE);
        }
    }
    
    /**
     * @description 设置页面标题
     * @date 2015年9月8日
     * @param title
     */
    protected void setTitle(String title) {
        mTitle.setTitle(title);
    }
    
    /**
     * 设置页面标题
     */
    public void setTitle(int titleId) {
        mTitle.setTitle(titleId);
    }
    
    /**
     * @description 设置是否显示返回按钮
     * @date 2015年9月8日
     * @param visible
     */
    protected void setNavBackVisible(boolean visible) {
        mTitle.setNavBackVisible(visible);
    }
    
    /**
     * @description 是否显示添加按钮
     * @date 2015年9月8日
     * @param visible
     */
    protected void setAddBtnVisible(boolean visible) {
        mTitle.setAddBtnVisible(visible);
    }
    
    /**
     * @description 是否显示菜单按钮
     * @date 2015年9月8日
     * @param visible
     */
    protected void setMenuBtnVisible(boolean visible) {
        mTitle.setMenuBtnVisible(visible);
    }
}
