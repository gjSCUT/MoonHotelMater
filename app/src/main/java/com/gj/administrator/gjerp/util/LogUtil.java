package com.gj.administrator.gjerp.util;

import android.util.Log;

/**
 * Created by guojun on 2015/12/07
 */
public class LogUtil {
    private static final String TAG = "LogUtil";
    private static boolean isDebug = true;


    public static void setLogStatus(boolean flag){
        isDebug = flag;
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

}
