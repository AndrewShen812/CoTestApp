package com.sy.testapp;

import com.sy.cartracker.CalendarTestActivity;
import com.sy.cartracker.GoogleMapActivity;
import com.sy.cartracker.LineChartActivity;
import com.sy.cartracker.MonthCalendarActivity;
import com.sy.cartracker.PieChartActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @项目名称：TestApp
 * @类名称：MainActivity
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015年9月6日 下午6:12:10
 * @修改人：Administrator
 * @修改时间：2015年9月6日 下午6:12:10
 * @修改备注：
 * @version
 */
public class MainActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onArcSeek(View v) {
        Intent intent = new Intent(mContext, ArcSeekBarActivity.class);
        startActivity(intent);
    }
    
    public void onJni(View v) {
        Intent intent = new Intent(mContext, JniTestActivity.class);
        startActivity(intent);
    }
    
    public void onShape(View v) {
        Intent intent = new Intent(mContext, ShapeTestActivity.class);
        startActivity(intent);
    }
    
    public void onSwitch(View v) {
        Intent intent = new Intent(mContext, SwitchLanguageActivity.class);
        startActivity(intent);
    }
    
    public void onPager(View v) {
        Intent intent = new Intent(mContext, ViewPagerTestActivity.class);
        startActivity(intent);
    }
    
    public void onWave(View v) {
        Intent intent = new Intent(mContext, WaveTestActivity.class);
        startActivity(intent);
    }
    
    public void onCalendar(View v) {
        Intent intent = new Intent(mContext, CalendarTestActivity.class);
        startActivity(intent);
    }
    
    public void onMonCalendar(View v) {
        Intent intent = new Intent(mContext, MonthCalendarActivity.class);
        startActivity(intent);
    }
    public void onPieChart(View v) {
        Intent intent = new Intent(mContext, PieChartActivity.class);
        startActivity(intent);
    }
    public void onLineChart(View v) {
        Intent intent = new Intent(mContext, LineChartActivity.class);
        startActivity(intent);
    }
    public void onMap(View v) {
        Intent intent = new Intent(mContext, GoogleMapActivity.class);
        startActivity(intent);
    }
}
