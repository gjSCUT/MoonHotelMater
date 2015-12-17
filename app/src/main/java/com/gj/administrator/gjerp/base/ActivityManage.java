package com.gj.administrator.gjerp.base;

import java.util.Stack;

/**
 * ActivityManage
 * Created by guojun on 2015/12/14
 */
public class ActivityManage {
    private static Stack<BaseActivity> queue;

    public static void addActivity(BaseActivity activity) {
        queue.add(activity);
    }

    public static void finishActivity(BaseActivity activity) {
        if (activity != null) {
            queue.remove(activity);
            activity.finish();
        }
    }

    public static void finishAllActivities() {
        for (BaseActivity activity : queue) {
            activity.finish();
        }
        queue.clear();
    }

    public static int getActivitiesNum() {
        if (!queue.isEmpty()) {
            return queue.size();
        }
        return 0;
    }

    public static BaseActivity getCurrentActivity() {
        if (!queue.isEmpty()) {
            return queue.lastElement();
        }
        return null;
    }

    public static void init() {
        queue = new Stack<BaseActivity>();
    }
}
