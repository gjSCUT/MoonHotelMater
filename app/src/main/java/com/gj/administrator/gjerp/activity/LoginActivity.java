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
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.User;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;


/**
 * login activity
 * Created by guojun on 2015/12/14
 */
public class LoginActivity extends BaseActivity{
    private static final String TAG = "LoginActivity";
    private EditText loginEdUsr;
    private EditText loginEdPwd;
    private Spinner loginSpHotel;
    private Button loginBtn;
    private Button upBtn;
    private Button clearBtn;
    private HotelDao hotelDao;
    private UserDao userDao;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("gjerp", MODE_PRIVATE);
        DaoSession session = DBUtil.getDaoSession(this);
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

        List<Hotel> hotels = hotelDao.loadAll();
        List<String> hotelNames = new ArrayList<>();
        for(Hotel hotel:hotels)
            hotelNames.add(hotel.getName());
        ArrayAdapter<String> hotelAdapter  = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,hotelNames);
        hotelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginSpHotel.setAdapter(hotelAdapter);

        loginSpHotel.setSelection(preferences.getInt("loginHotelSelect", 0));
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
                editor.putInt("loginHotelSelect", loginSpHotel.getSelectedItemPosition());
                editor.putString("loginUsr", loginEdUsr.getText().toString());
                editor.putString("loginPwd", loginEdPwd.getText().toString());
                editor.apply();
                Hotel hotel = hotelDao.load(loginSpHotel.getSelectedItemId() + 1);
                QueryBuilder<User> qb = userDao.queryBuilder();
                User user = qb.where(
                            UserDao.Properties.Username.eq(loginEdUsr.getText().toString()),
                            UserDao.Properties.Password.eq(loginEdPwd.getText().toString())
                        )
                        .unique();
                if(user == null){
                    showShortToast("username or password is wrong");
                }
                else {
                    // save the login info
                    SessionUtil.setHotel(hotel);
                    SessionUtil.setStaff(user.getStaff());

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
