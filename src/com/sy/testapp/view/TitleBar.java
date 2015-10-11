/** 
 * @项目名称：ChiffoDemo   
 * @文件名：TitleBar.java    
 * @版本信息：
 * @日期：2015年9月18日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sy.testapp.R;

/**       
 * @项目名称：ChiffoDemo    
 * @类名称：TitleBar    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月18日 下午2:21:03    
 * @修改人：Administrator    
 * @修改时间：2015年9月18日 下午2:21:03    
 * @修改备注：    
 * @version          
 */
public class TitleBar extends RelativeLayout{
       
    /** 标题 */
    private TextView mTvTitle;
    
    /** 返回按钮 */
    private ImageView mIvBack;
    
    /** 添加按钮 */
    private ImageView mIvAdd;
    
    /** 菜单按钮 */
    private ImageView mIvMenu;
    
    private OnTitleClickListener mTitleListener;
    
    /**    
     * 创建一个新的实例 TitleBar.    
     * @param context
     * @param attrs
     * @param defStyleAttr    
     */
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

       
    /**    
     * 创建一个新的实例 TitleBar.    
     * @param context
     * @param attrs    
     */
    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

       
    /**    
     * 创建一个新的实例 TitleBar.    
     * @param context    
     */
    public TitleBar(Context context) {
        this(context, null);
    }
    
    private void init(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_title_bar, this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title_name);
        mIvBack = (ImageView) view.findViewById(R.id.iv_title_nav_back);
        mIvAdd = (ImageView) view.findViewById(R.id.iv_title_add);
        mIvMenu = (ImageView) view.findViewById(R.id.iv_title_menu);
        mIvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mTitleListener) {
                    mTitleListener.onNavBack();
                }
            }
        });
        mIvAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mTitleListener) {
                    mTitleListener.onAddClick();
                }
            }
        });
        mIvMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mTitleListener) {
                    mTitleListener.onMenuClick();
                }
            }
        });
    }
    
    public void setOnTitleClickListener(OnTitleClickListener titleClickListener) {
        mTitleListener = titleClickListener;
    }
    
    /** 标题栏事件监听器 */
    public interface OnTitleClickListener {
        /** 导航返回 */
        void onNavBack();
        /** 添加 */
        void onAddClick();
        /** 菜单 */
        void onMenuClick();
    }
    
    /**
     * @description 设置页面标题
     * @date 2015年9月8日
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }
    
    /**
     * 设置页面标题
     */
    public void setTitle(int titleId) {
        mTvTitle.setText(titleId);
    }
    
    /**
     * @description 设置是否显示返回按钮
     * @date 2015年9月8日
     * @param visible
     */
    public void setNavBackVisible(boolean visible) {
        if (visible) {
            mIvBack.setVisibility(View.VISIBLE);
        }
        else {
            mIvBack.setVisibility(View.INVISIBLE);
        }
    }
    
    /**
     * @description 是否显示添加按钮
     * @date 2015年9月8日
     * @param visible
     */
    public void setAddBtnVisible(boolean visible) {
        if (visible) {
            mIvAdd.setVisibility(View.VISIBLE);
        }
        else {
            mIvAdd.setVisibility(View.INVISIBLE);
        }
    }
    
    /**
     * @description 是否显示菜单按钮
     * @date 2015年9月8日
     * @param visible
     */
    public void setMenuBtnVisible(boolean visible) {
        if (visible) {
            mIvMenu.setVisibility(View.VISIBLE);
        }
        else {
            mIvMenu.setVisibility(View.INVISIBLE);
        }
    }
}
