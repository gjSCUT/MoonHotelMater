package com.gj.administrator.gjerp.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.gj.administrator.gjerp.dao.DaoMaster;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.domain.User;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.RandomUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * BaseApplication
 * Created by GuoJun on 2015/12/9.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    public static boolean isDebugmode = true;
    public static String dbName = "testdb";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * <p>
     * 获取BaseApplication实例
     * <p>
     * 单例模式，返回唯一实例
     *
     * @return instance
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,dbName, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }

        ActivityManage.init();
        LogUtil.setLogStatus(isDebugmode);

        //TODO test mode,so delete all data
        if(isDebugmode) {
            this.deleteDatabase(dbName);
            createTestDate();
        }
    }

    private void createTestDate(){
        DaoSession session = BaseApplication.getDaoSession(this);
        HotelDao hotelDao = session.getHotelDao();
        UserDao userDao = session.getUserDao();
        //user and hotel test
        User user = new User(1L,"admin","admin");
        userDao.insertOrReplace(user);
        String[] hotelnames = {"GuangZhou hotel", "ShangHai hotel"};
        for(int i = 1;i<hotelnames.length+1;i++){
            Hotel hotel= new Hotel((long)i,hotelnames[i-1]);
            hotelDao.insertOrReplace(hotel);
        }
        //task test
        for(int i=1;i<6;i++){
            Task task = new Task(
                    (long)i,
                    "Meet",
                    "Have a meet with Lucy",
                    "Important",
                    "Finish",
                    new Date()
            );
            session.getTaskDao().insertOrReplace(task);
        }
        //dialog test
        String [] name = {"Guo Jun","Xiao Hong","Meet","Business","Check Hotel"};
        String [] msg_type = {"Chat","Chat","Task","Task","Schdule"};
        String [] person_type = {"Employee","Partner",null,null,null};
        Random random = new Random();
        for(int i=1;i<6;i++){
            Dialog dialog = new Dialog((long)i,"normal");
            Long id = session.getDialogDao().insertOrReplace(dialog);
            dialog.setId(id);
        }
        //msg test
        for(int i=1;i<21;i++){
            com.gj.administrator.gjerp.domain.Message message = new com.gj.administrator.gjerp.domain.Message(
                    (long)i,
                    RandomUtil.getRandomString(20),
                    name[i%5],
                    msg_type[i%5],
                    new Date(),
                    person_type[i%5],
                    null,
                    (long)i%5+1);
            session.getMessageDao().insertOrReplace(message);
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