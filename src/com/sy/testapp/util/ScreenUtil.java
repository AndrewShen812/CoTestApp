package com.sy.testapp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

import com.sy.testapp.TestApp;


public class ScreenUtil {
	private static int textSizeMinPx = 8;
	private static int textSizeMaxPx = 25;
	public static final float TEXT_LEADING_RATE = 0.25f;
	int screenWidth;       // 屏幕宽（像素，如：480px）  
	int screenHeight;      // 屏幕高（像素，如：800p） 
	
	public static ArrayList<TextSize> textSizeTable = null;
	
	public static int getScreenWidth(Activity activity){
		return activity.getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public static int getScreenHeight(Activity activity){
		return activity.getWindowManager().getDefaultDisplay().getHeight();
	}
	
	public static void initTextSizeTable(Context ctx) {
		if (textSizeTable == null) {
			textSizeTable = new ArrayList<ScreenUtil.TextSize>();
			Paint paint = new Paint();
			for (int i = textSizeMinPx; i < textSizeMaxPx; ++i) {
				TextSize item = new TextSize();
				item.sp = i;
				
				float pxSize = ScreenUtil.sp2px(ctx, i);
				paint.setTextSize(pxSize);
				FontMetrics fm = paint.getFontMetrics(); 
				item.screenPx = (int)Math.ceil(fm.descent - fm.ascent);
				textSizeTable.add(item);
			}
		}
		
	}
	
//	public static float sp2px(Context ctx, float sp) {
//		return sp / ctx.getResources().getDisplayMetrics().scaledDensity  ;
//	}
	
	public static float sp2px(Context ctx, float sp) {
		return (sp * ctx.getResources().getDisplayMetrics().scaledDensity + 0.5f) ;
	}
	
	public static int px2sp(Context ctx, float px) {
		return (int)(px / ctx.getResources().getDisplayMetrics().scaledDensity + 0.5f) ;
	}
	
	public static int dp2px(Context context, float dpValue) {
		return (int) (context.getResources().getDisplayMetrics().density * dpValue + 0.5f);
	}
	
	public static int px2dp(Context ctx, float px) {
		 final float scale = ctx.getResources().getDisplayMetrics().density;  
		 return (int) (px / scale + 0.5f);  
	}
	
	public static int getTextHeightSp(Context ctx, float textSp) {
		Paint paint = new Paint();
		float pxSize = ScreenUtil.sp2px(ctx, textSp);
		paint.setTextSize(pxSize);
		FontMetrics fm = paint.getFontMetrics(); 
    	return (int)Math.ceil(fm.descent - fm.ascent) + 2;
	}
	
	public static int getTextHeightPx(Context ctx, float textPx) {
		Paint paint = new Paint();
		paint.setTextSize(textPx);
		FontMetrics fm = paint.getFontMetrics(); 
    	return (int)Math.ceil(fm.descent - fm.ascent) + 2;
	}
	
	public static float getTextWidth(String content, float sizePx) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize(sizePx);
		return paint.measureText(content);
	}
	
	public static int getStatusBarHeight(Context ctx) {
		Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0,sbar = 0;
        try{
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                sbar = ctx.getResources().getDimensionPixelSize(x);
        }catch(Exception e){
                e.printStackTrace();
        }
        return sbar;
	}
	
	public static float getTextSp(int screenPx) {
		if (textSizeTable == null) {
			return 0;
		}
		for (TextSize size : textSizeTable) {
			if (screenPx <= size.screenPx) {
				return size.sp;
			}
		}
		return  textSizeTable.get(textSizeTable.size() - 1).sp;
	}
	
	public static class TextSize{
		int sp;
		int screenPx;
	}
	
	public static int getScreenWidth() { 
		Context context = TestApp.getInstance();
	    return context.getResources().getDisplayMetrics().widthPixels;
	} 
	//获取屏幕的高度 
	public static int getScreenHeight() { 
		Context context = TestApp.getInstance();
	    return context.getResources().getDisplayMetrics().heightPixels;
	}
}
