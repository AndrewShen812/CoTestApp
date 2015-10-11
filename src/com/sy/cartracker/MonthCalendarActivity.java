/** 
 * @项目名称：TestApp   
 * @文件名：MonthCalendarActivity.java
 * @日期：2015年10月9日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.cartracker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.sy.testapp.BaseActivity;
import com.sy.testapp.R;
import com.sy.testapp.util.DateUtil;
import com.sy.testapp.util.log.Log;
import com.sy.testapp.view.CustomDate;

/**       
 * @项目名称：TestApp    
 * @类名称：MonthCalendarActivity    
 * @类描述：  
 * @version          
 */
public class MonthCalendarActivity extends BaseActivity{
    
    private GridView mGvCalendar;
    private CustomDate mDate = new CustomDate();
    private int lastMonDays = DateUtil.getMonthDays(mDate.year, mDate.month - 1);
    private int curMonthDays = DateUtil.getMonthDays(mDate.year, mDate.month);
    private int firstDayWeekIndex = DateUtil.getWeekDayFromDate(mDate.year, mDate.month);
    private int curMonday = 0;
    private int showDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_calendar);
        
        initView();
    }
    
    private void initView() {
        mGvCalendar = (GridView) findViewById(R.id.gv_calendar);
        mGvCalendar.setAdapter(new GridViewAdapter(mContext));
        mGvCalendar.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= firstDayWeekIndex && position < firstDayWeekIndex+curMonthDays) {
                    int realDayIndex = position - firstDayWeekIndex + 1;
                    Log.MyLog.i("position:" + realDayIndex);
                }
            }
        });
        mGvCalendar.setSelector(R.drawable.selector_mon_calendar); // 点击效果
    }
    
    private class GridViewAdapter extends BaseAdapter {

        private TextView[] Days = new TextView[42];
        private Context context;
        
        public GridViewAdapter(Context context) {
            super();
            this.context = context;
        }

        @Override
        public int getCount() {
            return Days == null ? 0 : Days.length;
        }

        @Override
        public Object getItem(int position) {
            return Days == null ? null : Days[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder item;
            if (convertView == null) {
                item = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_mon_calendar_item, null);
                item.mTvDay = (TextView) convertView.findViewById(R.id.tv_day_calendar_item);
                item.mTvDay.setGravity(Gravity.CENTER);
                item.mTvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                convertView.setTag(item);
            }
            else {
                item = (ViewHolder) convertView.getTag();
            }
            // 当前月份
            if (position >= firstDayWeekIndex && position < firstDayWeekIndex+curMonthDays) {
                curMonday++;
                item.mTvDay.setTextColor(Color.BLACK);
                showDay = curMonday;
            }
            // 上一月份
            else if (position < firstDayWeekIndex) {
                item.mTvDay.setTextColor(Color.GRAY);
                showDay = lastMonDays - firstDayWeekIndex + position + 1;
            }
            // 下一月份
            else if (position >= firstDayWeekIndex + curMonthDays) {
                item.mTvDay.setTextColor(Color.GRAY); 
                showDay = position - firstDayWeekIndex - curMonthDays + 1;
            }
            // 今天
            if (DateUtil.isCurrentMonth(mDate) && curMonday == DateUtil.getCurrentMonthDay()) {
                item.mTvDay.setTextColor(Color.WHITE);
                item.mTvDay.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_circle_blue));
            }
            item.mTvDay.setText(showDay + "");
            
            return convertView;
        }
    }
    private class ViewHolder {
        TextView mTvDay;
    }
    
}
