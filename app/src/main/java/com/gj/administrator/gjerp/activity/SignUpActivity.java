package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.domain.Guest;
import com.gj.administrator.gjerp.domain.User;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.util.Date;


public class SignUpActivity extends BaseActivity {

    EditText loginEdUsr;
    EditText loginEdPwd;
    EditText loginEdName;
    EditText loginEdGender;
    EditText loginEdAge;
    EditText loginEdPhone;
    Button sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        loginEdUsr = (EditText)findViewById(R.id.loginEdUsr);
        loginEdPwd = (EditText)findViewById(R.id.loginEdPwd);
        loginEdName = (EditText)findViewById(R.id.loginEdName);
        loginEdGender = (EditText) findViewById(R.id.loginEdGender);
        loginEdAge = (EditText) findViewById(R.id.loginEdAge);
        loginEdPhone = (EditText) findViewById(R.id.loginEdPhone);
        sureBtn = (Button)findViewById(R.id.loginBtIn);
    }

    @Override
    protected void initEvents() {
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(null,loginEdUsr.getText().toString(),loginEdPwd.getText().toString());
                DaoSession daoSession = BaseApplication.getDaoSession(mContext);
                long userId =daoSession .getUserDao().insert(user);
                Guest guest = new Guest(
                        null,
                        loginEdName.getText().toString(),
                        Guest.NORMAL,
                        loginEdPhone.getText().toString(),
                        "gjscut@qq.com",
                        new Date(),
                        loginEdGender.getText().toString(),
                        Integer.parseInt(loginEdAge.getText().toString()),
                        userId);
                daoSession.getGuestDao().insert(guest);
                SessionUtil.getUser().setUsername(loginEdUsr.getText().toString());
                SessionUtil.getUser().setPassword(loginEdPwd.getText().toString());
                startActivity(MainActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void processMessage(Message msg) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
