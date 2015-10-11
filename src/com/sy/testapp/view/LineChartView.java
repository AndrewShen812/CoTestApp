/**
 * @项目名称：TestApp
 * @文件名：LineChartView.java
 * @日期：2015年10月10日
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.
 */
package com.sy.testapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PathEffect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.sy.testapp.util.ScreenUtil;

/**
 * @项目名称：TestApp
 * @类名称：LineChartView
 * @类描述：
 * @version
 */
public class LineChartView extends View {
    
    /**
     * 创建一个新的实例 LineChartView.
     */
    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init(context);
    }
    
    /**
     * 创建一个新的实例 LineChartView.
     */
    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }
    
    /**
     * 创建一个新的实例 LineChartView.
     */
    public LineChartView(Context context) {
        super(context);
        init(context);
    }
    
    private static final int DEF_LEVELS = 4;
    private static final int DEF_LINE_WIDTH = 1;
    private static int DASH_ON = 2;
    private static int DASH_OFF = 2;
    private static final int TEXT_SIZE = 12;
    
    
    private Paint mPaint;
    
    private String[] xLabels = {"0h", "12h", "0h"};
    
    private String[] yLabels = {"210", "120", "30"};
    
    private boolean mShowYLabel = false;
    
    /** Y方向上多少根线 */
    private int yLevels = DEF_LEVELS;
    
    /** 刻度线样式 */
    private LevelLineStyle mLineStyle;
    
    private float mDensity;
    
    private int mLevelGap;
    private int mPadding;
    private int mPaddingVertical;
    private int mLineWidth = DEF_LINE_WIDTH;
    private int mLineLength;
    private PathEffect pe;
    /** 刻度线样式 */
    public enum LevelLineStyle {
        /** 实线 */
        FULL_LINE,
        /** 虚线 */
        DASH_LINE
    }
    
    private void getAttrs(Context context, AttributeSet attrs) {
    }
    
    private void init(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        mLineWidth = (int) (mLineWidth * mDensity);
        DASH_OFF *= mDensity;
        DASH_ON *= mDensity;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(ScreenUtil.dp2px(context, TEXT_SIZE));
        TextPaint mtxtPaint = new TextPaint();
        pe = new DashPathEffect(new float[] {DASH_ON, DASH_OFF}, 0);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        mPadding = width / 6;
        mPaddingVertical = height / 10;
        mLevelGap = (height - mPaddingVertical * 2) / yLevels;
        mLineLength = width - mPadding * 2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    protected void onDraw(Canvas canvas) {
        for (int i=0; i<yLevels; i++) {
            // 刻度数值
            if (mShowYLabel && i < yLabels.length) {
                float txtLen = mPaint.measureText(yLabels[i]);
                FontMetrics fm = mPaint.getFontMetrics();
                float txtH = fm.bottom - fm.top;
//                float txtY = mLevelGap * (i + 1)
                canvas.drawText(yLabels[i], mPadding-txtLen*1.5f, mLevelGap * (i + 1) + txtH/3, mPaint);
            }
            // 画线
            if (i != yLevels - 1) {
                mPaint.setPathEffect(pe);
            }
            canvas.drawLine(mPadding, mLevelGap * (i + 1),
                            mPadding + mLineLength, mLevelGap * (i + 1), mPaint);
            mPaint.setPathEffect(null); // 清除虚线设置
        }
        int xLevelGap = mLineLength / (xLabels.length-1);
        // 绘制X轴刻度
        if (null != xLabels && xLabels.length > 0) {
            FontMetrics fm = mPaint.getFontMetrics();
            float fontH = fm.bottom - fm.top;
            for(int i=0; i<xLabels.length; i++) {
                if (i == xLabels.length-1) {
                    float txtW = mPaint.measureText(xLabels[i]);
                    canvas.drawText(xLabels[i], mPadding + xLevelGap * i - txtW, mLevelGap*yLevels + fontH, mPaint);
                }
                else {
                    canvas.drawText(xLabels[i], mPadding + xLevelGap * i, mLevelGap*yLevels + fontH, mPaint);
                }
            }
        }
        // 画数据折线
        canvas.translate(mPadding, mLevelGap*yLevels);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, -mLevelGap - 20, xLevelGap, -mLevelGap - 20, mPaint);
        canvas.drawLine(xLevelGap + 80, -mLevelGap - 20, xLevelGap*2 - 20, -mLevelGap - 20, mPaint);
    }
}
