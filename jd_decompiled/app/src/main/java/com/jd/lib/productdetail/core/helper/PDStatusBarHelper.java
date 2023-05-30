package com.jd.lib.productdetail.core.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes15.dex */
public class PDStatusBarHelper {
    private static final int STATUSBAR_TYPE_ANDROID6 = 3;
    private static final int STATUSBAR_TYPE_DEFAULT = 0;
    private static final int STATUSBAR_TYPE_FLYME = 2;
    private static final int STATUSBAR_TYPE_MIUI = 1;
    private static int mStatuBarType;
    private static Integer sTransparentValue;

    public static boolean MIUISetStatusBarLightMode(Window window, boolean z) {
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Class<?> cls3 = Integer.TYPE;
                Method method = cls.getMethod("setExtraFlags", cls3, cls3);
                if (z) {
                    method.invoke(window, Integer.valueOf(i2), Integer.valueOf(i2));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i2));
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @TargetApi(23)
    private static boolean android6SetStatusBarLightMode(Window window, boolean z) {
        window.getDecorView().setSystemUiVisibility(changeStatusBarModeRetainFlag(window, z ? 8192 : 256));
        if (PDDeviceHelper.isMIUIV9()) {
            MIUISetStatusBarLightMode(window, z);
            return true;
        }
        return true;
    }

    @TargetApi(23)
    private static int changeStatusBarModeRetainFlag(Window window, int i2) {
        return retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, i2, 1024), 4), 2), 4096), 1024), 512);
    }

    public static boolean flymeSetStatusBarLightMode(Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            android6SetStatusBarLightMode(window, z);
        }
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i2 = declaredField.getInt(null);
                int i3 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i3 | i2 : (i2 ^ (-1)) & i3);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static Integer getStatusBarAPITransparentValue(Context context) {
        Integer num = sTransparentValue;
        if (num != null) {
            return num;
        }
        String str = null;
        for (String str2 : context.getPackageManager().getSystemSharedLibraryNames()) {
            if ("touchwiz".equals(str2)) {
                str = "SYSTEM_UI_FLAG_TRANSPARENT_BACKGROUND";
            } else if (str2.startsWith("com.sonyericsson.navigationbar")) {
                str = "SYSTEM_UI_FLAG_TRANSPARENT";
            }
        }
        if (str != null) {
            try {
                Field field = View.class.getField(str);
                if (field != null && field.getType() == Integer.TYPE) {
                    sTransparentValue = Integer.valueOf(field.getInt(null));
                }
            } catch (Exception unused) {
            }
        }
        return sTransparentValue;
    }

    private static boolean isMIUICustomStatusBarLightModeImpl() {
        return PDDeviceHelper.isMIUIV5() || PDDeviceHelper.isMIUIV6() || PDDeviceHelper.isMIUIV7() || PDDeviceHelper.isMIUIV8();
    }

    public static int retainSystemUiFlag(Window window, int i2, int i3) {
        return (window.getDecorView().getSystemUiVisibility() & i3) == i3 ? i2 | i3 : i2;
    }

    public static boolean setStatusBarDarkMode(Activity activity) {
        if (activity == null) {
            return false;
        }
        int i2 = mStatuBarType;
        if (i2 == 0) {
            return true;
        }
        if (i2 == 1) {
            return MIUISetStatusBarLightMode(activity.getWindow(), false);
        }
        if (i2 == 2) {
            return flymeSetStatusBarLightMode(activity.getWindow(), false);
        }
        if (i2 == 3) {
            return android6SetStatusBarLightMode(activity.getWindow(), false);
        }
        return true;
    }

    public static boolean setStatusBarLightMode(Activity activity) {
        if (activity == null || PDDeviceHelper.isZTKC2016()) {
            return false;
        }
        int i2 = mStatuBarType;
        if (i2 != 0) {
            return setStatusBarLightMode(activity, i2);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            if (isMIUICustomStatusBarLightModeImpl() && MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = 1;
                return true;
            } else if (flymeSetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = 2;
                return true;
            } else if (i3 >= 23) {
                android6SetStatusBarLightMode(activity.getWindow(), true);
                mStatuBarType = 3;
                return true;
            }
        }
        return false;
    }

    public static boolean supportTransclentStatusBar6() {
        return (PDDeviceHelper.isZUKZ1() || PDDeviceHelper.isZTKC2016()) ? false : true;
    }

    private static boolean supportTranslucent() {
        return Build.VERSION.SDK_INT >= 19 && !BaseInfo.getDeviceBrand().toLowerCase().contains("essential");
    }

    public static void translucent(Activity activity) {
        translucent(activity, 1073741824);
    }

    @TargetApi(19)
    public static void translucent(Activity activity, @ColorInt int i2) {
        if (supportTranslucent()) {
            if (!PDDeviceHelper.isMeizu() && !PDDeviceHelper.isMIUI()) {
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 21) {
                    Window window = activity.getWindow();
                    window.getDecorView().setSystemUiVisibility(R2.attr.lineSpacing);
                    if (i3 >= 23 && supportTransclentStatusBar6()) {
                        window.clearFlags(67108864);
                        window.addFlags(Integer.MIN_VALUE);
                        window.setStatusBarColor(0);
                        return;
                    }
                    window.clearFlags(67108864);
                    window.addFlags(Integer.MIN_VALUE);
                    window.setStatusBarColor(i2);
                    return;
                }
                return;
            }
            activity.getWindow().setFlags(67108864, 67108864);
        }
    }

    private static boolean setStatusBarLightMode(Activity activity, int i2) {
        if (i2 == 1) {
            return MIUISetStatusBarLightMode(activity.getWindow(), true);
        }
        if (i2 == 2) {
            return flymeSetStatusBarLightMode(activity.getWindow(), true);
        }
        if (i2 == 3) {
            return android6SetStatusBarLightMode(activity.getWindow(), true);
        }
        return false;
    }
}
