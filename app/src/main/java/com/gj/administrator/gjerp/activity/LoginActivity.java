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
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.SessionUtil;


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

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("gjerp", MODE_PRIVATE);
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

        hotelDao = BaseApplication.getDaoSession(mContext).getHotelDao();
        for(int i = 1;i<SessionUtil.getHotelnames().length;i++){
            Hotel hotel= new Hotel((long)i,SessionUtil.getHotelnames()[i-1]);
            hotelDao.insertOrReplace(hotel);
        }
        loginSpHotel.setSelection(Integer.parseInt(preferences.getString("loginHotel", "0")));
        loginEdUsr.setText(preferences.getString("loginUsr", "admin"));
        loginEdPwd.setText(preferences.getString("loginPwd", "admin"));
        SessionUtil.setHotel(hotelDao.load(loginSpHotel.getSelectedItemId()+1));
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

                // save the login info
                SessionUtil.setHotel(hotelDao.load(loginSpHotel.getSelectedItemId()+1));
                SessionUtil.getUser().setUsername(loginEdUsr.getText().toString());
                SessionUtil.getUser().setPassword(loginEdPwd.getText().toString());

                LogUtil.i(TAG, "LOGIN");
                startActivity(MainActivity.class);
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
