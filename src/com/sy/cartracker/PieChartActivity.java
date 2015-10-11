package com.sy.cartracker;

import android.os.Bundle;

import com.sy.testapp.BaseActivity;
import com.sy.testapp.R;
import com.sy.testapp.R.color;
import com.sy.testapp.R.id;
import com.sy.testapp.R.layout;
import com.sy.testapp.view.PieChartView;
import com.sy.testapp.view.RingChartView;

public class PieChartActivity extends BaseActivity {
    
    private RingChartView mRingChart;
    private int[] pie_char_color;
    private PieChartView mPieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        
        initView();
    }
    
    private void initView() {
//        pie_char_color = new int[]{getResources().getColor(R.color.main_color), getResources().getColor(R.color.main_light_color)};
//        mRingChart = (RingChartView) findViewById(R.id.ring_chart_test);
//        mRingChart.setDataCount(2); 
//        mRingChart.setDataTitle(new String[]{getString(R.string.electric_hight),getString(R.string.electric_low)});
//        mRingChart.setColor(pie_char_color);
//        mRingChart.setData(new float[]{80f, 20f});
        pie_char_color = new int[]{getResources().getColor(R.color.main_color),
                                   getResources().getColor(R.color.gray)};
        mPieChart = (PieChartView) findViewById(R.id.pie_chart_test);
        mPieChart.setDataCount(2);
        mPieChart.setColors(pie_char_color);
        mPieChart.setData(new float[] {40, 60});
    }
}
