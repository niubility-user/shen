package com.jingdong.sdk.jdtoast;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.jdsdk.widget.a.c;
import com.jingdong.jdsdk.widget.a.f;
import com.jingdong.jdsdk.widget.a.g;

/* loaded from: classes.dex */
public class ToastUtils {
    private static JDToast centerToast;
    private static JDToast centerToastNoIcon;
    private static OnThemeConfig config;
    private static Handler mHandler;
    private static JDToast sToast;

    /* loaded from: classes.dex */
    public interface OnThemeConfig {
        boolean isElder();
    }

    public static void cancel() {
        if (c.d() != null) {
            c.a();
        }
        JDToast jDToast = sToast;
        if (jDToast != null) {
            jDToast.cancel();
        }
        JDToast jDToast2 = centerToast;
        if (jDToast2 != null) {
            jDToast2.cancel();
        }
        JDToast jDToast3 = centerToastNoIcon;
        if (jDToast3 != null) {
            jDToast3.cancel();
        }
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * 160.0f) + 0.5f);
    }

    private static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public static void init(Application application, OnThemeConfig onThemeConfig) {
        config = onThemeConfig;
        c.e(application);
    }

    public static boolean isElder() {
        OnThemeConfig onThemeConfig = config;
        if (onThemeConfig == null) {
            return false;
        }
        return onThemeConfig.isElder();
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void longToast(Context context, int i2) {
        showToastPrivate(context, i2, 1);
    }

    private static void newCenter(Context context, String str, int i2) throws Throwable {
        c.f(new f());
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.jd_common_toast_style_center, (ViewGroup) null);
        c.k(inflate);
        ((ImageView) inflate.findViewById(R.id.jd_toast_image)).setBackgroundResource(i2);
        c.l(str);
    }

    public static void shortToast(Context context, String str) {
        showToastPrivate(context, str, 0);
    }

    public static void showCustomCenter(final Context context, final View view) {
        if (view == null) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.6
            @Override // java.lang.Runnable
            public void run() {
                if (context == null) {
                    return;
                }
                if (ToastUtils.centerToastNoIcon != null) {
                    ToastUtils.centerToastNoIcon.cancel();
                }
                JDToast unused = ToastUtils.centerToastNoIcon = new JDToast(context.getApplicationContext(), (byte) 5);
                ToastUtils.centerToastNoIcon.setCustomViewByCenter(view);
                ToastUtils.centerToastNoIcon.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void showToast(Context context, String str) {
        longToast(context, str);
    }

    public static void showToastInCenter(final Context context, final byte b, final String str, final int i2) {
        if (!c.f12953e) {
            try {
                newCenter(context, str, b);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.3
            @Override // java.lang.Runnable
            public void run() {
                if (context == null || TextUtils.isEmpty(str)) {
                    return;
                }
                if (ToastUtils.centerToast != null) {
                    ToastUtils.centerToast.cancel();
                }
                JDToast unused = ToastUtils.centerToast = new JDToast(context.getApplicationContext(), (byte) 1);
                ToastUtils.centerToast.setImage(b);
                ToastUtils.centerToast.setText(str);
                ToastUtils.centerToast.setDuration(i2);
                ToastUtils.centerToast.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    private static void showToastPrivate(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        showToastPrivate(context, context.getString(i2), i3);
    }

    private static void showToastPrivateY(final Context context, final String str, final int i2) {
        if (!c.f12953e) {
            c.f(new g());
            c.l(str);
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.2
            @Override // java.lang.Runnable
            public void run() {
                if (context == null || TextUtils.isEmpty(str)) {
                    return;
                }
                if (ToastUtils.sToast != null) {
                    ToastUtils.sToast.cancel();
                }
                JDToast unused = ToastUtils.sToast = new JDToast(context.getApplicationContext(), ToastUtils.dip2px(100.0f));
                ToastUtils.sToast.setText(str);
                ToastUtils.sToast.setDuration(i2);
                ToastUtils.sToast.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void showToastY(Context context, String str) {
        showToastPrivateY(context, str, 0);
    }

    public static void longToast(Context context, String str) {
        showToastPrivate(context, str, 1);
    }

    public static void shortToast(Context context, int i2) {
        showToastPrivate(context, i2, 0);
    }

    private static void showToastPrivate(final Context context, final String str, final int i2) {
        if (!c.f12953e) {
            c.f(new g());
            c.l(str);
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (context == null || TextUtils.isEmpty(str)) {
                    return;
                }
                if (ToastUtils.sToast != null) {
                    ToastUtils.sToast.cancel();
                }
                JDToast unused = ToastUtils.sToast = new JDToast(context.getApplicationContext(), (byte) 2);
                ToastUtils.sToast.setText(str);
                ToastUtils.sToast.setDuration(i2);
                ToastUtils.sToast.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void showToastY(Context context, int i2) {
        showToastPrivateY(context, context.getApplicationContext().getString(i2), 0);
    }

    public static void init(Application application) {
        c.e(application);
    }

    private static void newCenter(Context context, String str, byte b) throws Throwable {
        c.f(new f());
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.jd_common_toast_style_center, (ViewGroup) null);
        c.k(inflate);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.jd_toast_image);
        if (b == 1) {
            imageView.setBackgroundResource(R.drawable.jd_toast_exclamation);
        } else if (b == 2) {
            imageView.setBackgroundResource(R.drawable.jd_toast_tick);
        }
        c.l(str);
    }

    public static void showToastInCenter(final Context context, final int i2, final String str, final int i3) {
        if (!c.f12953e) {
            try {
                newCenter(context, str, i2);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.4
            @Override // java.lang.Runnable
            public void run() {
                if (context == null || TextUtils.isEmpty(str)) {
                    return;
                }
                if (ToastUtils.centerToast != null) {
                    ToastUtils.centerToast.cancel();
                }
                JDToast unused = ToastUtils.centerToast = new JDToast(context.getApplicationContext(), (byte) 1);
                ToastUtils.centerToast.setImageResource(i2);
                ToastUtils.centerToast.setText(str);
                ToastUtils.centerToast.setDuration(i3);
                ToastUtils.centerToast.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void showToastInCenter(final Context context, final String str, final int i2) {
        if (!c.f12953e) {
            try {
                c.f(new f());
                c.j(R.layout.jd_common_toast_style_bottom);
                c.l(str);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.sdk.jdtoast.ToastUtils.5
            @Override // java.lang.Runnable
            public void run() {
                if (context == null || TextUtils.isEmpty(str)) {
                    return;
                }
                if (ToastUtils.centerToastNoIcon != null) {
                    ToastUtils.centerToastNoIcon.cancel();
                }
                JDToast unused = ToastUtils.centerToastNoIcon = new JDToast(context.getApplicationContext(), (byte) 4);
                ToastUtils.centerToastNoIcon.setText(str);
                ToastUtils.centerToastNoIcon.setDuration(i2);
                ToastUtils.centerToastNoIcon.show();
            }
        };
        if (isMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void showToastInCenter(Context context, String str) {
        showToastInCenter(context, str, 0);
    }
}
