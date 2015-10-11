/** 
 * @项目名称：TestApp   
 * @文件名：ClockView.java    
 * @版本信息：
 * @日期：2015年9月22日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**       
 * @项目名称：TestApp    
 * @类名称：ClockView    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月22日 下午6:33:12    
 * @修改人：Administrator    
 * @修改时间：2015年9月22日 下午6:33:12    
 * @修改备注：    
 * @version          
 */
public class ClockView extends View{

    /**    
     * 创建一个新的实例 ClockView.    
     * @param context    
     */
    public ClockView(Context context) {
        this(context, null);
    }
       
    /**    
     * 创建一个新的实例 ClockView.    
     * @param context
     * @param attrs    
     */
    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    /**    
     * 创建一个新的实例 ClockView.    
     * @param context
     * @param attrs
     * @param defStyle    
     */
    public ClockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttrs(context, attrs);
    }

    private int mCircleRadius = 100;
    
    private Paint mPaint;
    
    private int mWidth = 3;
    
    private float mCenterX;
    
    private float mCenterY;
    
    private int mLineLengthLong = 20;
    private int mLineLengthShort = 10;
    private int mHourLength;
    private int mMinLength;
    private int mSecLength;
    private Timer mTimer = null;
    private int mHour = 2;
    private int mMin = 3;
    private int mSec = 00;
    private int mAnglePerSec;
    private int mAnglePerHour;
    private void getAttrs(Context context, AttributeSet attrs) {
        if (null == attrs) {
            return;
        }
        float density = context.getResources().getDisplayMetrics().density;
        Log.i("SY", "density:" + density);
        mCircleRadius = (int) (mCircleRadius * density);
        mWidth = (int) (mWidth * density);
        mLineLengthLong = (int) (density * mLineLengthLong);
        mLineLengthShort = (int) (density * mLineLengthShort);
        mHourLength = mCircleRadius - mLineLengthLong * 3;
        mMinLength = mHourLength + mLineLengthLong;
        mSecLength = (int) (mMinLength + mLineLengthLong - 5*density);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mWidth);
        mPaint.setColor(0xFFc72527);
        mAnglePerSec = 360 / 60;
        mAnglePerHour = mAnglePerSec * 5;
    }
       
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = mCircleRadius * 2 + getPaddingLeft() + getPaddingRight();
        int height = mCircleRadius * 2 + getPaddingTop() + getPaddingBottom();
        mCenterX = width / 2;
        mCenterY = height / 2;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCenterX, mCenterY, mCircleRadius, mPaint);
        
        canvas.translate(mCenterX, mCenterY);
        // 绘制表盘刻度线
        for (int i=0; i<60; i++) {
            if (i % 5 ==0) {
                canvas.drawLine(0, -mCircleRadius, 0, -mCircleRadius + mLineLengthLong, mPaint);
            }
            else {
                canvas.drawLine(0, -mCircleRadius, 0, -mCircleRadius + mLineLengthShort, mPaint);
            }
            canvas.rotate(mAnglePerSec);
        }
        // 画时针
        canvas.save();
        canvas.rotate(mAnglePerHour * mHour + mMin / 60f * mAnglePerHour);
        canvas.drawLine(0, 0, 0, -mHourLength, mPaint);
        canvas.restore();
        // 画分针
        canvas.save();
//        canvas.rotate(mAnglePerSec * mMin + mSec / 60f * mAnglePerHour);
        canvas.rotate(mAnglePerSec * mMin);
        canvas.drawLine(0, 0, 0, -mMinLength, mPaint);
        canvas.restore();
        // 画秒针
        canvas.save();
        canvas.rotate(mAnglePerSec * mSec);
        canvas.drawLine(0, 0, 0, -mSecLength, mPaint);
        canvas.restore();
//        
        
        if (null == mTimer) {
            Log.i("SY", "init timer");
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.i("SY", "sec add");
                    mSec++;
                    if (mSec == 60) {
                        mSec = 0;
                        mMin++;
                        if (mMin == 60) {
                            mMin = 0;
                            mHour++;
                            if (mHour == 12) {
                                mHour = 0;
                            }
                        }
                    }
                    postInvalidate();
                }
            }, 1000, 1000);
        }
    }
}
