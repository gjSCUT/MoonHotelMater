package com.gj.administrator.gjerp.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;

import com.gj.administrator.gjerp.dao.CustomerDao;
import com.gj.administrator.gjerp.dao.DaoMaster;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.RegionDao;
import com.gj.administrator.gjerp.dao.RoomTypeDao;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.domain.Customer;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Partner;
import com.gj.administrator.gjerp.domain.Region;
import com.gj.administrator.gjerp.domain.RoomType;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.Supplier;
import com.gj.administrator.gjerp.domain.User;
import com.gj.administrator.gjerp.service.TimeService;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.TestUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.greenrobot.dao.query.QueryBuilder;
import jp.co.worksap.intern.util.Utilities;

/**
 * BaseApplication
 * Created by GuoJun on 2015/12/14.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    public static boolean isDebugmode = true;

    /**
     * <p>
     *
     * @return instance
     */
    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }

        ActivityManage.init();
        LogUtil.setLogStatus(isDebugmode);

        //启动后台服务
        Intent service=new Intent(this, TimeService.class);
        startService(service);

        //TODO test mode,so delete all data
        if(isDebugmode) {
            this.deleteDatabase(DBUtil.dbName);
            TestUtil.createTestDate(this);
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.e("BaseApplication", "onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.e("BaseApplication", "onTerminate");
    }
}