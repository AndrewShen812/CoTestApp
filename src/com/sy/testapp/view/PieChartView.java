/**
 * @项目名称：TestApp
 * @文件名：PieChartView.java
 * @日期：2015年10月10日
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.
 */
package com.sy.testapp.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @项目名称：TestApp
 * @类名称：PieChartView
 * @类描述：
 * @version
 */
public class PieChartView extends View {
    
    /**
     * 创建一个新的实例 PieChartView.
     */
    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init(context);
    }
    
    /**
     * 创建一个新的实例 PieChartView.
     */
    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }
    
    /**
     * 创建一个新的实例 PieChartView.
     */
    public PieChartView(Context context) {
        super(context);
        init(context);
    }
    /** 默认颜色 */
    private static final int DEF_COLOR = 0xFF84BFE2;
    
    private static final int DIVIDE_WIDTH = 2;
    
    private int[] mColors;
    
    private int mDataCount;
    
    private float[] mData;
    
    private Paint mPaint;
    
    private Paint mLinePaint;
    
    private float mDensity;
    
    private List<Point> mEnds = new ArrayList<Point>();
    
    private Point mTmpPoint;
    
    private String[] mTitles;
    
    private void getAttrs(Context context, AttributeSet attrs) {
    }
    
    private void init(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEF_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mDensity * DIVIDE_WIDTH);
    }
    
    private int mDiameter;
    
    private int mRadius;
    
    private int mPadding;
    
    private RectF mRect;
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        mPadding = min / 6;
        mDiameter = min - mPadding * 2;
        mRadius = mDiameter / 2;
        mRect = new RectF(mPadding, mPadding, mPadding + mDiameter, mPadding + mDiameter);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mPadding + mRadius, mPadding + mRadius, mRadius, mPaint);
        if (null == mData || mData.length == 0) {
            return;
        }
        float startAngle = 0;
        for (int i = 0; i < mData.length; i++) {
            mPaint.setColor(mColors[i]);
            float sweepAngle = mData[i] / 100 * 360;
            canvas.drawArc(mRect, startAngle, sweepAngle, true, mPaint);
            if (mData.length != 1) { // 只有一个元素时不画分割线
                mTmpPoint = new Point();
                mTmpPoint.x = (int) (mRadius * Math.cos(Math.toRadians(startAngle)));
                mTmpPoint.y = (int) (mRadius * Math.sin(Math.toRadians(startAngle)));
                mEnds.add(mTmpPoint);
            }
            startAngle += sweepAngle;
        }
        // 画分割线
        canvas.save();
        canvas.translate(mPadding + mRadius, mPadding + mRadius);
        for (Point p : mEnds) {
            canvas.drawLine(0, 0, p.x, p.y, mLinePaint);
        }
        mEnds.clear();
        // TODO 绘制说明文字
    }
    
    /**
     * 获取颜色集合
     * 
     * @return the mColors
     */
    public int[] getColors() {
        return mColors;
    }
    
    /**
     * @param mColors
     *            the mColors to set 颜色集合，大小应与data集合一致
     */
    public void setColors(int[] mColors) {
        this.mColors = mColors;
    }
    
    /**
     * 数据种类数目
     * 
     * @return the mDataCount
     */
    public int getDataCount() {
        return mDataCount;
    }
    
    /**
     * @param mDataCount
     *            the mDataCount to set 数据种类数目
     */
    public void setDataCount(int mDataCount) {
        this.mDataCount = mDataCount;
    }
    
    /**
     * 获取数据集合
     * 
     * @return the mData
     */
    public float[] getData() {
        return mData;
    }
    
    /**
     * 设置数据集合，应该和color集合大小一致
     * 
     * @param mData
     *            the mData to set
     */
    public void setData(float[] mData) {
        this.mData = mData;
    }
    
    /**
     * 获取标题
     */
    public String[] getTitles() {
        return mTitles;
    }
    
    /**
     * 设置每部分标题，大小应与data集合一致
     */
    public void setTitles(String[] titles) {
        this.mTitles = titles;
    }
}
