package com.sy.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class WaveTestActivity extends BaseActivity {
    private TextView mTvSearch;
    
    private static final int ANIMATIONEACHOFFSET = 700; // 每个动画的播放时间间隔
    
    private AnimationSet aniSet, aniSet2, aniSet3;
    
    private ImageView btn, wave1, wave2, wave3;
    
    private Handler handler = new Handler() {
        
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x222) {
                wave2.startAnimation(aniSet2);
            }
            else if (msg.what == 0x333) {
                wave3.startAnimation(aniSet3);
            }
            super.handleMessage(msg);
        }
        
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_test);
        initView();
    }
    
    private void initView() {
        mTvSearch = (TextView) findViewById(R.id.tv_main_search_dev);
        mTvSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SwitchLanguageActivity.class);
                startActivity(intent);
            }
        });
        aniSet = getNewAnimationSet();
        aniSet2 = getNewAnimationSet();
        aniSet3 = getNewAnimationSet();
        btn = (ImageView) findViewById(R.id.btn);
        wave1 = (ImageView) findViewById(R.id.wave1);
        wave2 = (ImageView) findViewById(R.id.wave2);
        wave3 = (ImageView) findViewById(R.id.wave3);
        btn.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cancelWaveAnimation();
                        break;
                    case MotionEvent.ACTION_UP:
                        showWaveAnimation();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        cancelWaveAnimation();
                        break;
                }
                return true;
            }
        });
    }
    
    private AnimationSet getNewAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(1f,
                                               2.5f,
                                               1f,
                                               2.5f,
                                               ScaleAnimation.RELATIVE_TO_SELF,
                                               0.5f,
                                               ScaleAnimation.RELATIVE_TO_SELF,
                                               0.5f);
        sa.setDuration(ANIMATIONEACHOFFSET * 2);
        sa.setRepeatCount(-1);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(-1);// 设置循环
        as.setDuration(ANIMATIONEACHOFFSET * 2);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        return as;
    }
    
    private void showWaveAnimation() {
        wave1.startAnimation(aniSet);
//        handler.sendEmptyMessageDelayed(0x222, ANIMATIONEACHOFFSET);
//        handler.sendEmptyMessageDelayed(0x333, ANIMATIONEACHOFFSET * 2);
    }
    
    private void cancelWaveAnimation() {
        wave1.clearAnimation();
        wave2.clearAnimation();
        wave3.clearAnimation();
    }
}
