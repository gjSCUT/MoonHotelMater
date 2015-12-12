package com.gj.administrator.gjerp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.dao.BookRecordDao;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.domain.User;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.RandomUtil;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.util.Date;
import java.util.Random;

import de.greenrobot.dao.query.QueryBuilder;


/**
 * login activity
 * Created by guojun on 2015/12/07
 */
public class LoginActivity extends BaseActivity{
    private static final String TAG = "LoginActivity";
    private EditText loginEdUsr;
    private EditText loginEdPwd;
    private Spinner loginSpHotel;
    private Button loginBtn;
    private Button upBtn;
    private Button clearBtn;
    HotelDao hotelDao;
    UserDao userDao;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("gjerp", MODE_PRIVATE);
        DaoSession session = BaseApplication.getDaoSession(this);
        hotelDao = session.getHotelDao();
        userDao = session.getUserDao();
        initViews();
        initEvents();
    }



    @Override
    protected void initViews() {
        loginSpHotel = (Spinner) findViewById(R.id.loginSpHotel);
        loginEdUsr = (EditText) findViewById(R.id.loginEdUsr);
        loginEdPwd = (EditText) findViewById(R.id.loginEdPwd);

        loginBtn = (Button) findViewById(R.id.loginBtIn);
        upBtn = (Button) findViewById(R.id.loginBtnUp);
        clearBtn = (Button) findViewById(R.id.loginBtnClr);

        ArrayAdapter<String> hotelAdapter  = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,SessionUtil.getHotelnames());
        hotelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginSpHotel.setAdapter(hotelAdapter);

        loginSpHotel.setSelection(Integer.parseInt(preferences.getString("loginHotel", "0")));
        loginEdUsr.setText(preferences.getString("loginUsr", "admin"));
        loginEdPwd.setText(preferences.getString("loginPwd", "admin"));

        SessionUtil.setHotel(hotelDao.load(loginSpHotel.getSelectedItemId() + 1));
    }


    @Override
    protected void initEvents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save the last input data
                editor = preferences.edit();
                editor.putString("loginHotel", String.valueOf(loginSpHotel.getSelectedItemId()));
                editor.putString("loginUsr", loginEdUsr.getText().toString());
                editor.putString("loginPwd", loginEdPwd.getText().toString());
                editor.apply();

                QueryBuilder<User> qb = userDao.queryBuilder();
                qb.where(UserDao.Properties.Username.eq(loginEdUsr.getText().toString()),
                        UserDao.Properties.Password.eq(loginEdPwd.getText().toString()));
                if(qb.list().isEmpty()){
                    showShortToast("username or password is wrong");
                }
                else {
                    // save the login info
                    SessionUtil.setHotel(hotelDao.load(loginSpHotel.getSelectedItemId() + 1));
                    SessionUtil.setUser(qb.list().get(0));

                    LogUtil.i(TAG, "LOGIN");
                    startActivity(MainActivity.class);
                }
            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignUpActivity.class);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginEdUsr.setText("");
                loginEdPwd.setText("");
                LogUtil.i(TAG, "CLEAR");
            }
        });

    }

    @Override
    protected void processMessage(Message msg) {
    }

}
