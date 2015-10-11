package com.sy.testapp;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sy.testapp.util.AppConfig;
import com.sy.testapp.util.SPUtils;

public class SwitchLanguageActivity extends BaseActivity {
    
    private Button mBtnSwitch;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        initView();
    }
    
    private void initView() {
        mBtnSwitch = (Button) findViewById(R.id.btn_second_switch);
        mBtnSwitch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang = SPUtils.getLanguage(mContext);
                if (AppConfig.LANGUAGE_CH.equals(lang)) {
                    SPUtils.setlanguage(mContext, AppConfig.LANGUAGE_EN);
                }
                else {
                    SPUtils.setlanguage(mContext, AppConfig.LANGUAGE_CH);
                }
                TestApp.getInstance().restart();
            }
        });
    }
}
