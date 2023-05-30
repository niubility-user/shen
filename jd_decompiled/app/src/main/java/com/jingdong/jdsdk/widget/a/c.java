package com.jingdong.jdsdk.widget.a;

import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes14.dex */
public final class c {
    private static h a = null;
    private static b b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Toast f12952c = null;
    public static Application d = null;

    /* renamed from: e  reason: collision with root package name */
    public static boolean f12953e = true;

    public static synchronized void a() {
        synchronized (c.class) {
            b();
            a.b();
        }
    }

    private static void b() {
        if (f12952c == null) {
            throw new IllegalStateException("NewToastUtils has not been initialized");
        }
    }

    private static TextView c(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(b.getBackgroundColor());
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, b.getCornerRadius(), context.getResources().getDisplayMetrics()));
        TextView textView = new TextView(context);
        textView.setId(16908299);
        textView.setTextColor(b.b());
        textView.setTextSize(0, TypedValue.applyDimension(2, b.getTextSize(), context.getResources().getDisplayMetrics()));
        textView.setPadding((int) TypedValue.applyDimension(1, b.g(), context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, b.f(), context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, b.d(), context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, b.a(), context.getResources().getDisplayMetrics()));
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        if (i2 >= 21) {
            textView.setZ(b.e());
        }
        if (b.c() > 0) {
            textView.setMaxLines(b.c());
        }
        return textView;
    }

    public static Toast d() {
        return f12952c;
    }

    public static void e(Application application) {
        d = application;
        try {
            if (b == null) {
                f(new g());
            }
            if (g(application)) {
                f12953e = true;
                if (Build.VERSION.SDK_INT == 25) {
                    i(new d(application));
                } else {
                    i(new a(application));
                }
            } else {
                f12953e = false;
                i(new e(application));
            }
            k(c(application.getApplicationContext()));
            h(b.h(), b.getXOffset(), b.getYOffset());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void f(b bVar) {
        b = bVar;
        Toast toast = f12952c;
        if (toast != null) {
            toast.cancel();
            f12952c.setView(c(d.getApplicationContext()));
            f12952c.setGravity(b.h(), b.getXOffset(), b.getYOffset());
        }
    }

    private static boolean g(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            return ((NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION)).areNotificationsEnabled();
        }
        if (i2 >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i3 = applicationInfo.uid;
            try {
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                Class<?> cls2 = Integer.TYPE;
                return ((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i3), packageName)).intValue() == 0;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
                return true;
            }
        }
        return true;
    }

    public static void h(int i2, int i3, int i4) {
        b();
        if (Build.VERSION.SDK_INT >= 17) {
            i2 = Gravity.getAbsoluteGravity(i2, f12952c.getView().getResources().getConfiguration().getLayoutDirection());
        }
        f12952c.setGravity(i2, i3, i4);
    }

    public static void i(Toast toast) {
        f12952c = toast;
        a = new h(toast);
    }

    public static void j(int i2) {
        b();
        k(View.inflate(f12952c.getView().getContext().getApplicationContext(), i2, null));
    }

    public static void k(View view) {
        Toast toast;
        b();
        if (view == null || view.getContext() != view.getContext().getApplicationContext() || (toast = f12952c) == null) {
            return;
        }
        toast.cancel();
        f12952c.setView(view);
    }

    public static synchronized void l(CharSequence charSequence) {
        synchronized (c.class) {
            b();
            if (charSequence != null && !"".equals(charSequence.toString())) {
                a.a(charSequence);
                a.d();
            }
        }
    }
}
