package com.gj.administrator.gjerp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.gj.administrator.gjerp.base.BaseApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * Created by Guojun on 2015/12/9.
 */

public class SPUtil {
    private static final String GlobalSharedName = "gjerp";
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSP;

    public SPUtil() {
        BaseApplication instance = BaseApplication.getInstance();
        mSP = instance.getSharedPreferences(GlobalSharedName,Context.MODE_PRIVATE);
        mEditor = mSP.edit();
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }

}