package com.gj.administrator.gjerp.util;

import android.content.Context;

import com.gj.administrator.gjerp.dao.DaoMaster;
import com.gj.administrator.gjerp.dao.DaoSession;

/**
 * Created by Administrator on 2015/12/14.
 */
public class DBUtil {
    public static String dbName = "testdb";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    /**
     * @param context
     * @return DaoMaster
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
     * @param context
     * @return DaoSession
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
}
