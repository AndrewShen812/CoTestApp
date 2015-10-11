package com.sy.cartracker;

import android.os.Bundle;

import com.sy.testapp.BaseActivity;
import com.sy.testapp.R;
import com.sy.testapp.R.id;
import com.sy.testapp.R.layout;
import com.sy.testapp.util.log.Log;
import com.sy.testapp.view.CalendarView;
import com.sy.testapp.view.CalendarView.OnCellClickListener;
import com.sy.testapp.view.CustomDate;

public class CalendarTestActivity extends BaseActivity {
    
    private CalendarView mMonJan;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test);
        
        initView();
    }
    
    private void initView() {
        mMonJan = (CalendarView) findViewById(R.id.cv_mon_jan);
        mMonJan.setOnCellClickListener(new OnCellClickListener() {
            @Override
            public void clickDate(CustomDate date) {
                Log.MyLog.i("clickDate called. year=" + date.year + ", mon=" + date.month + ", day=" + date.day);
            }
        });
    }
}
