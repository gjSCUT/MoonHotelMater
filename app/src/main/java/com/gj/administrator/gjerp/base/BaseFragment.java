package com.gj.administrator.gjerp.base;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.util.DBUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BaseFragment
 * Created by guojun on 2015/12/14
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;
    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<>();

    public BaseFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        initEvents();
        return mView;
    }

    @Override
    public void onDestroy() {
        clearAsyncTask();
        super.onDestroy();
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    public View findViewById(int id) {
        return mView.findViewById(id);
    }

    protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        mAsyncTasks.add(asyncTask.execute());
    }

    public void clearAsyncTask() {
        for(AsyncTask task: mAsyncTasks){
            if (task != null && !task.isCancelled()) {
                task.cancel(true);
            }
        }
        mAsyncTasks.clear();
    }

    /** 短暂显示Toast提示(来自res) **/
    protected void showShortToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(CharSequence charStr) {
        Toast.makeText(getActivity(), charStr, Toast.LENGTH_SHORT).show();
    }

    /** 通过Class跳转界面 **/
    protected void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }

    /** 含有Bundle通过Action跳转界面 **/
    protected void startActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}