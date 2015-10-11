package com.sy.testapp;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

/**
 * @项目名称：TestApp
 * @类名称：WelcomeActivity
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015年9月7日 上午8:38:55
 * @修改人：Administrator
 * @修改时间：2015年9月7日 上午8:38:55
 * @修改备注：
 * @version
 */
public class WelcomeActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitleVisible(false);
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
