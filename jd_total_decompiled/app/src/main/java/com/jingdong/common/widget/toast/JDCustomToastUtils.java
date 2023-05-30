package com.jingdong.common.widget.toast;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.common.DpiUtil;

/* loaded from: classes12.dex */
public class JDCustomToastUtils {
    private static JDCustomToast bottomToast;
    private static JDCustomToast bottomYToast;
    private static JDCustomToast centerToast;
    private static JDCustomToast centerToastNoIcon;
    private static Handler mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkContext(Activity activity) {
        return Build.VERSION.SDK_INT >= 17 ? (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true : (activity == null || activity.isFinishing()) ? false : true;
    }

    private static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public static void longToast(Activity activity, int i2) {
        showToastPrivate(activity, i2, 3500);
    }

    public static void shortToast(Activity activity, String str) {
        showToastPrivate(activity, str, 2000);
    }

    public static void showToast(Activity activity, String str) {
        showToast(activity, str, 3500);
    }

    public static void showToastInCenter(final Activity activity, final byte b, final String str, final int i2) {
        getHandler().post(new Runnable() { // from class: com.jingdong.common.widget.toast.JDCustomToastUtils.3
            @Override // java.lang.Runnable
            public void run() {
                if (JDCustomToastUtils.checkContext(activity)) {
                    if (JDCustomToastUtils.centerToast == null || activity != JDCustomToastUtils.centerToast.getContext()) {
                        JDCustomToast unused = JDCustomToastUtils.centerToast = new JDCustomToast(activity, (byte) 1);
                    }
                    if (!JDCustomToastUtils.centerToast.isInitSuccess()) {
                        JDCustomToast unused2 = JDCustomToastUtils.centerToast = null;
                        return;
                    }
                    JDCustomToastUtils.centerToast.setImage(b);
                    JDCustomToastUtils.centerToast.setText(str);
                    JDCustomToastUtils.centerToast.setDuration(i2);
                    JDCustomToastUtils.centerToast.show();
                }
            }
        });
    }

    private static void showToastPrivate(Activity activity, int i2, int i3) {
        if (activity == null) {
            return;
        }
        showToastPrivate(activity, activity.getString(i2), i3);
    }

    private static void showToastPrivateY(final Activity activity, final String str, final int i2) {
        getHandler().post(new Runnable() { // from class: com.jingdong.common.widget.toast.JDCustomToastUtils.2
            @Override // java.lang.Runnable
            public void run() {
                if (!JDCustomToastUtils.checkContext(activity) || TextUtils.isEmpty(str)) {
                    return;
                }
                if (JDCustomToastUtils.bottomYToast == null || activity != JDCustomToastUtils.bottomYToast.getContext()) {
                    Activity activity2 = activity;
                    JDCustomToast unused = JDCustomToastUtils.bottomYToast = new JDCustomToast(activity2, DpiUtil.dip2px(activity2, 100.0f));
                }
                if (!JDCustomToastUtils.bottomYToast.isInitSuccess()) {
                    JDCustomToast unused2 = JDCustomToastUtils.bottomYToast = null;
                    return;
                }
                JDCustomToastUtils.bottomYToast.setText(str);
                JDCustomToastUtils.bottomYToast.setDuration(i2);
                JDCustomToastUtils.bottomYToast.show();
            }
        });
    }

    public static void longToast(Activity activity, String str) {
        showToastPrivate(activity, str, 3500);
    }

    public static void shortToast(Activity activity, int i2) {
        showToastPrivate(activity, i2, 2000);
    }

    public static void showToast(Activity activity, String str, int i2) {
        showToastPrivate(activity, str, i2);
    }

    public static void showToastInCenter(final Activity activity, final int i2, final String str, final int i3) {
        getHandler().post(new Runnable() { // from class: com.jingdong.common.widget.toast.JDCustomToastUtils.4
            @Override // java.lang.Runnable
            public void run() {
                if (JDCustomToastUtils.checkContext(activity)) {
                    if (JDCustomToastUtils.centerToast == null || activity != JDCustomToastUtils.centerToast.getContext()) {
                        JDCustomToast unused = JDCustomToastUtils.centerToast = new JDCustomToast(activity, (byte) 1);
                    }
                    if (!JDCustomToastUtils.centerToast.isInitSuccess()) {
                        JDCustomToast unused2 = JDCustomToastUtils.centerToast = null;
                        return;
                    }
                    JDCustomToastUtils.centerToast.setImageResource(i2);
                    JDCustomToastUtils.centerToast.setText(str);
                    JDCustomToastUtils.centerToast.setDuration(i3);
                    JDCustomToastUtils.centerToast.show();
                }
            }
        });
    }

    private static void showToastPrivate(final Activity activity, final String str, final int i2) {
        getHandler().post(new Runnable() { // from class: com.jingdong.common.widget.toast.JDCustomToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (!JDCustomToastUtils.checkContext(activity) || TextUtils.isEmpty(str)) {
                    return;
                }
                if (JDCustomToastUtils.bottomToast == null || activity != JDCustomToastUtils.bottomToast.getContext()) {
                    JDCustomToast unused = JDCustomToastUtils.bottomToast = new JDCustomToast(activity, (byte) 2);
                }
                if (!JDCustomToastUtils.bottomToast.isInitSuccess()) {
                    JDCustomToast unused2 = JDCustomToastUtils.bottomToast = null;
                    return;
                }
                JDCustomToastUtils.bottomToast.setText(str);
                JDCustomToastUtils.bottomToast.setDuration(i2);
                JDCustomToastUtils.bottomToast.show();
            }
        });
    }

    public static void showToastInCenter(final Activity activity, final String str, final int i2) {
        getHandler().post(new Runnable() { // from class: com.jingdong.common.widget.toast.JDCustomToastUtils.5
            @Override // java.lang.Runnable
            public void run() {
                if (JDCustomToastUtils.checkContext(activity)) {
                    if (JDCustomToastUtils.centerToastNoIcon == null || activity != JDCustomToastUtils.centerToastNoIcon.getContext()) {
                        JDCustomToast unused = JDCustomToastUtils.centerToastNoIcon = new JDCustomToast(activity, (byte) 4);
                    }
                    if (!JDCustomToastUtils.centerToastNoIcon.isInitSuccess()) {
                        JDCustomToast unused2 = JDCustomToastUtils.centerToastNoIcon = null;
                        return;
                    }
                    JDCustomToastUtils.centerToastNoIcon.setText(str);
                    JDCustomToastUtils.centerToastNoIcon.setDuration(i2);
                    JDCustomToastUtils.centerToastNoIcon.show();
                }
            }
        });
    }

    public static void showToastInCenter(Activity activity, String str) {
        showToastInCenter(activity, str, 2000);
    }
}
