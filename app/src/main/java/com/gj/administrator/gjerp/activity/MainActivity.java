package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.ActivityManage;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.fragment.AnalyzeFragment;
import com.gj.administrator.gjerp.fragment.HomeFragment;
import com.gj.administrator.gjerp.fragment.ManageFragment;
import com.gj.administrator.gjerp.fragment.SelectFragment;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.SessionUtil;


/*
 * main activity
 * Created by guojun on 2015/12/07
 */
public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView mNavName;
    TextView mHotelName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        navigationView = (NavigationView) findViewById(R.id.main_navigation);
        View headerLayout = navigationView.getHeaderView(0); // change introduced by changing support library from 23.0.x to 23.1.1
        mNavName = (TextView) headerLayout.findViewById(R.id.nav_username);
        mHotelName = (TextView) headerLayout.findViewById(R.id.nav_hotelname);
        mNavName.setText(SessionUtil.getUser().getUsername());
        mHotelName.setText(SessionUtil.getHotel().getName());
    }


    @Override
    protected void initEvents() {
        if (navigationView != null) {
            setupDrawerContent();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.getInstance(mContext))
                .commit();
        setTitle("Home");

    }


    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                LogUtil.i(TAG, "Select nav" + menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, HomeFragment.getInstance(mContext))
                                .commit();
                        setTitle("Home");
                        break;
                    case R.id.nav_manage:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, ManageFragment.getInstance(mContext))
                                .commit();
                        setTitle("Manage");
                        break;
                    case R.id.nav_analyze:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, AnalyzeFragment.getInstance(mContext))
                                .commit();
                        setTitle("Analyze");
                        break;
                    case R.id.nav_select:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, SelectFragment.getInstance(mContext, new SelectFragment.OnSelectHotelListener() {
                                    @Override
                                    public void setHotel(String hotel) {
                                        mHotelName.setText(hotel);
                                        getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container, HomeFragment.getInstance(mContext))
                                                .commit();
                                        setTitle("Home");
                                        navigationView.setCheckedItem(R.id.nav_home);
                                    }
                                }))
                                .commit();
                        setTitle("Select");
                        break;
                    case R.id.nav_logout:
                        ActivityManage.finishActivity(MainActivity.this);
                        break;
                    case R.id.nav_about:
                        startActivity(AboutActivity.class);
                        break;
                    case R.id.nav_exit:
                        ActivityManage.finishAllActivities();
                        break;
                    //TODO
                    default:
                        break;
                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i(TAG,"Select menu"+item);
        switch (item.getItemId()) {
            //TODO
            case android.R.id.home:
                if(!drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                else
                    drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void processMessage(Message msg) {

    }

}
