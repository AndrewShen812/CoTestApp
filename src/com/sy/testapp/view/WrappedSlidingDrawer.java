/**
 * @项目名称：CoTestApp
 * @文件名：WrappedSlidingDrawer.java
 * @版本信息：
 * @日期：2015-10-25
 */
package com.sy.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SlidingDrawer;

/**
 * @项目名称：CoTestApp
 * @类名称：WrappedSlidingDrawer
 * @类描述：
 * @version
 */
@SuppressWarnings("deprecation")
public class WrappedSlidingDrawer extends SlidingDrawer implements SlidingDrawer.OnDrawerOpenListener, SlidingDrawer.OnDrawerCloseListener {
    
    private boolean mVertical;
    
    private int mTopOffset;
    
    public WrappedSlidingDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    
    public WrappedSlidingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        setOnDrawerOpenListener(this);
        setOnDrawerCloseListener(this);
    }
    
    private void init(Context context, AttributeSet attrs) {
        int orientation = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "orientation", ORIENTATION_VERTICAL);
        mTopOffset = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "topOffset", 0);
        mVertical = (orientation == SlidingDrawer.ORIENTATION_VERTICAL);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        final View handle = getHandle();
        final View content = getContent();
        measureChild(handle, widthMeasureSpec, heightMeasureSpec);
        if (mVertical) {
            int height = heightSpecSize - handle.getMeasuredHeight() - mTopOffset;
            content.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightSpecMode));
            if (isOpened()) {
                heightSpecSize = handle.getMeasuredHeight() + mTopOffset + content.getMeasuredHeight();
            }
            else {
                heightSpecSize = handle.getMeasuredHeight() + mTopOffset;
            } 
//            heightSpecSize = handle.getMeasuredHeight() + mTopOffset + content.getMeasuredHeight();
            widthSpecSize = content.getMeasuredWidth();
            if (handle.getMeasuredWidth() > widthSpecSize)
                widthSpecSize = handle.getMeasuredWidth();
        }
        else {
            int width = widthSpecSize - handle.getMeasuredWidth() - mTopOffset;
            content.measure(MeasureSpec.makeMeasureSpec(width, widthSpecMode), heightMeasureSpec);
            if (isOpened()) {
                widthSpecSize = handle.getMeasuredWidth() + mTopOffset + content.getMeasuredWidth();
            }
            else {
                widthSpecSize = handle.getMeasuredWidth() + mTopOffset;
            }
//            widthSpecSize = handle.getMeasuredWidth() + mTopOffset + content.getMeasuredWidth();
            heightSpecSize = content.getMeasuredHeight();
            if (handle.getMeasuredHeight() > heightSpecSize)
                heightSpecSize = handle.getMeasuredHeight();
        }
        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }
    
    @Override
    public void onDrawerClosed() {
        requestLayout();
    }
    
    @Override
    public void onDrawerOpened() {
        requestLayout();
    }
}
