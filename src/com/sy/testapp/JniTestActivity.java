/** 
 * @项目名称：TestApp   
 * @文件名：LoginActivity.java    
 * @版本信息：
 * @日期：2015年9月25日    
 * @Copyright 2015 GALAXYWIND Network Systems Co.,Ltd.All rights reserved.         
 */
package com.sy.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sy.testapp.jni.JniLib;

/**       
 * @项目名称：TestApp    
 * @类名称：LoginActivity    
 * @类描述：    
 * @创建人：Administrator    
 * @创建时间：2015年9月25日 下午3:21:19    
 * @修改人：Administrator    
 * @修改时间：2015年9月25日 下午3:21:19    
 * @修改备注：    
 * @version          
 */
public class JniTestActivity extends BaseActivity{
    
    private EditText mEtName;
    
    private EditText mEtPwd;
    
    private Button mBtnLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        mEtName = (EditText) findViewById(R.id.et_login_name);
        mEtPwd = (EditText) findViewById(R.id.et_login_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login_login);
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JniLib.userLogin(mEtName.getText().toString(), mEtPwd.getText().toString());
                Toast.makeText(mContext, "正在登录", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    protected void HandleCallBack(int event, int uid, int ext) {
        super.HandleCallBack(event, uid, ext);
        
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }
}
