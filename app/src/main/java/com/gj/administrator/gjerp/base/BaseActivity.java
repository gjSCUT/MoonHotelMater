package com.gj.administrator.gjerp.base;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


import com.gj.administrator.gjerp.dao.DaoMaster;
import com.gj.administrator.gjerp.dao.DaoSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BaseActivity
 * Created by guojun on 2015/12/07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected Context mContext;
    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<>();
    protected ActionBar actionBar;
    protected BaseHandler.UnleakHandler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        handler = new BaseHandler.UnleakHandler(this);
        ActivityManage.addActivity(this);
        actionBar = getSupportActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAsyncTask();
    }

    protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        mAsyncTasks.add(asyncTask.execute());
    }

    /** 清理异步处理事件 */
    public void clearAsyncTask() {
        for(AsyncTask task: mAsyncTasks){
            if (task != null && !task.isCancelled()) {
                task.cancel(true);
            }
        }
        mAsyncTasks.clear();
    }

    /** 通过Class跳转界面 **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /** 含有Bundle通过Class跳转界面 **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /** 通过Action跳转界面 **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /** 含有Bundle通过Action跳转界面 **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /** 初始化视图 **/
    protected abstract void initViews();

    /** 初始化事件 **/
    protected abstract void initEvents();
    /**
     * 处理消息
     */
    protected abstract void processMessage(Message msg);


}
