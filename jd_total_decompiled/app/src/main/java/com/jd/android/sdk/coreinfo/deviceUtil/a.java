package com.jd.android.sdk.coreinfo.deviceUtil;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: com.jd.android.sdk.coreinfo.deviceUtil.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0065a {
        public int a;
        public int b;

        public final String toString() {
            return this.b + ProxyConfig.MATCH_ALL_SCHEMES + this.a;
        }
    }

    public static C0065a a(Context context) {
        int i2;
        int i3;
        int i4;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        int i5 = -1;
        if (defaultDisplay != null) {
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getRealMetrics(displayMetrics);
                i3 = displayMetrics.widthPixels;
                i4 = displayMetrics.heightPixels;
            } else {
                if (i6 >= 14) {
                    try {
                        Method method = Display.class.getMethod("getRawWidth", new Class[0]);
                        Method method2 = Display.class.getMethod("getRawHeight", new Class[0]);
                        Object invoke = method.invoke(defaultDisplay, new Object[0]);
                        Object invoke2 = method2.invoke(defaultDisplay, new Object[0]);
                        if (invoke != null && invoke2 != null) {
                            int intValue = ((Integer) invoke).intValue();
                            try {
                                int intValue2 = ((Integer) invoke2).intValue();
                                i3 = intValue;
                                i4 = intValue2;
                            } catch (Exception unused) {
                                i3 = intValue;
                                i4 = -1;
                                if (i3 != -1) {
                                }
                                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                                defaultDisplay.getMetrics(displayMetrics2);
                                i2 = displayMetrics2.widthPixels;
                                i5 = displayMetrics2.heightPixels;
                                C0065a c0065a = new C0065a();
                                c0065a.b = i5;
                                c0065a.a = i2;
                                return c0065a;
                            }
                        }
                    } catch (Exception unused2) {
                        i3 = -1;
                    }
                }
                i4 = -1;
                i3 = -1;
            }
            if (i3 != -1 || i4 == -1) {
                DisplayMetrics displayMetrics22 = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics22);
                i2 = displayMetrics22.widthPixels;
                i5 = displayMetrics22.heightPixels;
            } else {
                i5 = i4;
                i2 = i3;
            }
        } else {
            i2 = -1;
        }
        C0065a c0065a2 = new C0065a();
        c0065a2.b = i5;
        c0065a2.a = i2;
        return c0065a2;
    }
}
