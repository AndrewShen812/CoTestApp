package com.sy.testapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.sy.testapp.R;

public class RingChartView extends View{

    private int backColor = Color.WHITE;
    
    private int piePaddingLeft = 0;
    private int piePaddingTop = 0;
    private int piePaddingRight = 0;
    private int piePaddingBottom = 0;
    private int specialSpace = 10;
    
    
    private float[] data = null;
    private String[] title = null;
    private int defColor = Color.GREEN;
    private int[] color = null;
    private float sumData = 0;
    private int dataCount = 0;
    private int specialIndex = -1;
    private float startAngle = 0;
    
    private int tex_size = (int) getResources().getDimension(R.dimen.font_size_default);
    private int barWidth = tex_size/2;
    
    private int textColor = 0xaa333333;
    
    public RingChartView(Context context){
        super(context);
    }
    
    public RingChartView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    
    public RingChartView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        
    }
    public void setSpecial(int index){
        if(data != null && dataCount > index){
            specialIndex = index;
        }
    }
    
    public void setDataCount(int count){
        if(count > 0){
            data = new float[count];
            title = new String[count];
            dataCount = count;
            color = new int[count];
            for(int i = 0; i < count; i++){
                color[i] = defColor;
            }
        }
    }
    
    public void setData(float[] d){
    	sumData = 0;
        if(d != null && d.length == dataCount){
            for(int i = 0; i < dataCount; i++){
                sumData += d[i];
            }
            data = d;
        }
    }
    
    public void setData(int index,float d){
    	sumData = 0;
        if(data != null && dataCount > index){
            sumData -= data[index];
            data[index] = d;
            sumData += d;
        }
    }
    
    public void setDataTitle(String[] desc){
        if(desc != null && dataCount == desc.length){
            title = desc;
        }
    }
    
    public void setDataTitle(int index,String desc){
        if(title != null && dataCount > index){
            title[index] = desc;
        }
    }
    
    public void setColor(int[] c){
        if(color != null && c.length == dataCount){
            color = c;
        }
    }
    
    public void setColor(int index,int c){
        if(color != null & dataCount > index){
            color[index] = c;
        }
    }
    
    public void setBackgroundColor(int color){
        backColor = color;
    }
    
    protected void onDraw(Canvas canvas) {
        int paddingLeft = getWidth()/6;
        int paddingRight = getWidth()/6;
        int paddingTop = getHeight()/8 ;
        boolean is_dummy_data = false;
        float sum = sumData;
        
        if ( paddingTop < 0 ) {
        	paddingTop = 10;
		}
        //int paddingBottom = getPaddingBottom();
        
        int height = getHeight()*2/3;
        int width = getWidth() - paddingLeft - paddingRight ;
        
        if(data != null){
            canvas.save();
            canvas.translate(paddingLeft, paddingTop);
            canvas.clipRect(0,0,width,height);
            
            canvas.drawColor(backColor);
            
            int w = width;
            int h = height - piePaddingTop - piePaddingBottom;
            
            int r = w;
            if(w > h)
                r = h;
            
            RectF rf = new RectF(piePaddingLeft,piePaddingTop,piePaddingLeft + r,piePaddingTop + r);
            
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setTextSize(tex_size);
            paint.setStyle(Paint.Style.FILL);
            
            float ang = startAngle;
            
            float[] percent = new float[dataCount];
            
            if ( sumData == 0 ) {
            	sum = 2f;
            	is_dummy_data = true;
            	data[0] = 1f;
            	data[1] = 1f;
			}
            
            for(int i = 0; i < data.length; i++){
                paint.setColor(color[i]);
                float tmp = data[i] / (sum * 1.0f);
                percent[i] = tmp;
                tmp = tmp * 360;
                float toang = Math.round(tmp);
     
                if(specialIndex == i){
                    float ds = (ang + toang / 2) ;
                    float dy = (float)Math.abs((specialSpace * Math.sin(ds*0.01745)));
                    float dx = (float)Math.abs((specialSpace * Math.cos(ds*0.01745)));
                    if(ds > 0 && ds <= 90){
                        
                    }else if(ds > 90 && ds <=180){
                        dx = dx * (-1);
                    }else if(ds > 180 && ds <= 270){
                        dx = dx * (-1);
                        dy = dy * (-1);
                    }else if(ds > 270){
                        dy = dy * (-1);
                    }
                    RectF sf = new RectF(piePaddingLeft +dx,piePaddingTop + dy,piePaddingLeft +dx + r,piePaddingTop +r + dy );
                    canvas.drawArc(sf, ang,toang,true,paint);
                }else{
                    canvas.drawArc(rf, ang,toang,true,paint);
                    paint.setColor(backColor);
                }
                ang += toang;
            }
            canvas.drawCircle(piePaddingLeft+r/2, piePaddingTop+r/2, r*2/5, paint);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.airplug_ele_incon);  
            canvas.drawBitmap(bitmap, piePaddingLeft + r/2 - 10,piePaddingTop+r/5, paint);
            
            FontMetrics fm = paint.getFontMetrics();
            float texty = piePaddingTop +  r/2 - 20 ;
            float textx = piePaddingLeft  + r/4 ;
            for(int i = 0; i < dataCount; i++){
                paint.setColor(color[i]);
               // canvas.drawRect(textx, texty, textx +barWidth,texty+ barWidth, paint);
                
                //paint.setColor(textColor);
                // canvas.drawText(title[i]+"("+String.format("%.1f%%", percent[i] * 100)+")", textx + barWidth + 10, texty - fm.ascent, paint);
               String txt = "";
                
                if(is_dummy_data){
                	txt = " "+title[i]+" · "+ "0.0" + getResources().getString(R.string.plug_kwh);
               }else{
            	   txt = " "+title[i]+" · "+ data[i] + getResources().getString(R.string.plug_kwh);
               }
                
                Rect rect = new Rect();
                paint.getTextBounds(txt, 0, txt.length(), rect);
                int textw = rect.width();
                
                canvas.drawText( txt ,piePaddingLeft + tex_size + (r-textw)/2 , texty - fm.ascent, paint);
                canvas.drawCircle( piePaddingLeft + (r-textw)/2, texty + barWidth, barWidth, paint);
                
                texty = piePaddingTop+ fm.descent  - fm.ascent +  r/2 ;
            }
            
            canvas.restore();
        }
    }
    
    
}
