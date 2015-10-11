/** 
 * @项目名称：TestApp   
 * @文件名：PagerFragment.java    
 * @版本信息：
 * @日期：2015年9月14日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.sy.testapp;

import com.sy.testapp.util.adapter.ViewPageAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**    
 *     
 * @项目名称：TestApp    
 * @类名称：PagerFragment    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月14日 上午9:19:33    
 * @修改人：Administrator    
 * @修改时间：2015年9月14日 上午9:19:33    
 * @修改备注：    
 * @version     
 *     
 */
public class PagerFragment extends Fragment{
    
    private ViewPager mPager;
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    
    /**
     * 图片资源id
     */
    private int[] imgIdArray;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        mPager = (ViewPager) view.findViewById(R.id.vp_test_frame);
        imgIdArray = new int[] { R.drawable.baby, R.drawable.bingbing, R.drawable.ym};
        
        // 将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }
        // 设置Adapter
        mPager.setAdapter(new ViewPageAdapter(getActivity(), mImageViews));
        mPager.setCurrentItem(0);
        return view;
    }
}
