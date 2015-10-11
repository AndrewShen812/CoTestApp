/** 
 * @项目名称：TestApp   
 * @文件名：DotGroupView.java    
 * @版本信息：
 * @日期：2015年9月14日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.sy.testapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**    
 *     
 * @项目名称：TestApp    
 * @类名称：DotGroupView    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月14日 上午10:25:39    
 * @修改人：Administrator    
 * @修改时间：2015年9月14日 上午10:25:39    
 * @修改备注：    
 * @version     
 *     
 */
public class DotGroupView extends View{

    private Paint mPaint;
    
    private int currentItemColor = 0xFFBABABA;
    
    private int otherItemColor = 0xFFD9D9D9;
    
    private int currentIndex = 0;
    
    private int dotTotal = 3;
    
    private float offset = 10;
    
    private float currentItemRadius = 12;
    
    private float otherItemRadius = 10;
    
    private float dotGap = 15;
    
    public DotGroupView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
       
    public DotGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DotGroupView(Context context) {
        super(context);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) (offset * 2 + dotTotal * otherItemRadius * 2 + (dotTotal - 1) * dotGap), (int) (offset * 2 + otherItemRadius * 2));
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        mPaint = new Paint();
        for (int i=0; i < dotTotal; i++) {
            mPaint.setColor(otherItemColor);
            float radius = otherItemRadius;
            if (i == currentIndex) {
                radius = currentItemRadius;
                mPaint.setColor(currentItemColor);
            }
            float cx = offset + otherItemRadius * (i + 1) + dotGap * i;
            float cy = offset + otherItemRadius;
            canvas.drawCircle(cx, cy, radius, mPaint);
        }
    }
    
    public void setDotTotal(int total) {
        if (total < 0) {
            total = 0;
        }
        dotTotal = total;
    }
    
    public void setCurrentIndex(int index) {
        if (index >= dotTotal) {
            index = dotTotal-1;
        }
        else if (index < 0) {
            index = 0;
        }
        currentIndex = index;
        invalidate();
    }
    
}
