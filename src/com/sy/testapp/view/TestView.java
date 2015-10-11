/** 
 * @项目名称：TestApp   
 * @文件名：TestView.java    
 * @版本信息：
 * @日期：2015年9月23日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**       
 * @项目名称：TestApp    
 * @类名称：TestView    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月23日 上午10:27:29    
 * @修改人：Administrator    
 * @修改时间：2015年9月23日 上午10:27:29    
 * @修改备注：    
 * @version          
 */
public class TestView extends View {

       
    /**    
     * 创建一个新的实例 TestView.    
     * @param context
     * @param attrs
     * @param defStyle    
     */
    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

       
    /**    
     * 创建一个新的实例 TestView.    
     * @param context
     * @param attrs    
     */
    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

       
    /**    
     * 创建一个新的实例 TestView.    
     * @param context    
     */
    public TestView(Context context) {
        super(context);
        init(context);
    }

    private int mWidth = 150;
    
    private int mHeight = 150;
    
    private Paint mPaint;
    
    private int mCenterX;
    
    private int mCenterY;
    
    private void init(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        mWidth = (int) (mWidth * density);
        mHeight = (int) (mHeight * density);
        mPaint = new Paint();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = mWidth + getPaddingLeft() + getPaddingRight();
        mHeight = mHeight + getPaddingTop() + getPaddingBottom();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        setMeasuredDimension(mWidth , mHeight);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
//        canvas.translate(mCenterX, mCenterY);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(-mWidth * 0.25f, -mWidth * 0.25f, mWidth * 0.25f, mHeight * 0.25f, mPaint);
//        
//        canvas.rotate(45);
////        canvas.scale(1.5f, 1.5f);
//        mPaint.setColor(Color.RED);
////        canvas.skew(0.5f, 0);
//        canvas.drawRect(-mWidth * 0.25f, -mHeight * 0.25f, mWidth * 0.25f, mHeight * 0.25f, mPaint);
//        // 角度计算
        double deg = Math.toDegrees(Math.atan2(mCenterY, mCenterX));
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        canvas.drawText(deg + "", 50, 50, mPaint);
    }
}
