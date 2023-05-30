package com.jingdong.common;

import android.app.Activity;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
public class ActivityManagerUtil {
    private static Stack<Activity> activityStack;
    private static ActivityManagerUtil instance;

    private ActivityManagerUtil() {
    }

    public static ActivityManagerUtil getScreenManager() {
        if (instance == null) {
            synchronized (ActivityManagerUtil.class) {
                if (instance == null) {
                    instance = new ActivityManagerUtil();
                }
            }
        }
        return instance;
    }

    public Activity currentActivity() {
        Stack<Activity> stack = activityStack;
        if (stack == null || stack.empty()) {
            return null;
        }
        return activityStack.peek();
    }

    public Stack<Activity> getActivities() {
        return activityStack;
    }

    public int getNumActivitiesInStack() {
        Stack<Activity> stack = activityStack;
        if (stack != null) {
            return stack.size();
        }
        return 0;
    }

    public boolean isExsitActivity(String str) {
        if (OKLog.D) {
            OKLog.d("ActivityManagerUtil", "isExsitActivity className=" + str);
        }
        try {
            Stack<Activity> stack = activityStack;
            if (stack == null || stack.empty()) {
                return false;
            }
            Iterator<Activity> it = activityStack.iterator();
            while (it.hasNext()) {
                Activity next = it.next();
                if (next != null) {
                    if (OKLog.D) {
                        OKLog.d("ActivityManagerUtil", "isExsitActivity activity.getClass().getName()=" + next.getClass().getName());
                    }
                    if (str.equals(next.getClass().getName())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void popActivity(Activity activity) {
        Stack<Activity> stack;
        if (activity == null || (stack = activityStack) == null) {
            return;
        }
        stack.remove(activity);
    }

    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.push(activity);
    }
}
