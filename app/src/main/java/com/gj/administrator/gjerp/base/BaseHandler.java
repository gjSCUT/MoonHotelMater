package com.gj.administrator.gjerp.base;

import android.os.Message;
import android.os.Handler;
import java.lang.ref.WeakReference;

/**
 * Handler
 * Created by guojun on 2015/12/14
 */
public class BaseHandler {
    /**
     * 防止handler对activity有隐式引用，匿名内部类不会对外部类有引用
     */
    public static class UnleakHandler extends Handler {
        private final WeakReference<BaseActivity> activity;

        public UnleakHandler(BaseActivity activity) {
            this.activity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activity.get() == null) {
                return;
            }
            activity.get().processMessage(msg);
        }
    }
}
