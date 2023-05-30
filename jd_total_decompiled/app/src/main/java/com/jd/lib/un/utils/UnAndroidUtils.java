package com.jd.lib.un.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jd.lib.un.utils.config.UnUtilsConfig;
import com.jd.lib.un.utils.fold.FoldableUtils;
import com.jd.lib.un.utils.fold.WindowLocState;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class UnAndroidUtils {
    private static final String BRAND_HONOR = "HONOR";
    private static final String BRAND_HUAWEI = "HUAWEI";
    private static final String BRAND_OPPO = "OPPO";
    private static final String BRAND_SAMSUNG = "SAMSUNG";
    private static final String BRAND_VIVO = "VIVO";
    private static final String BRAND_XIAOMI = "XIAOMI";
    private static final int D_MODEL_SPLIT_LEN = 25;
    public static final int NOTCH_IN_SCREEN_VOIO = 32;
    private static String[] cutoutPhoneModels = {""};
    public static volatile Boolean isFoldableDevice;
    private static volatile Boolean isMatex;
    public static int statusBarHeight;

    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        String str;
        try {
            Resources resources = activity.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                str = (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, "qemu.hw.mainkeys");
            } catch (Exception unused) {
            }
            if ("1".equals(str)) {
                return false;
            }
            if ("0".equals(str)) {
                return true;
            }
            return z;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static int computeUsableHeight(Activity activity) {
        View findViewById;
        if (activity == null || (findViewById = activity.findViewById(16908290)) == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    public static int getAndroidVersion() {
        return UnDeviceInfo.getSdkVersion();
    }

    public static String getBrand() {
        return UnDeviceInfo.getManufacturer();
    }

    public static String getModel() {
        return UnDeviceInfo.getDeviceModel();
    }

    public static Resources getResources(Context context) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        return context.getResources();
    }

    public static int getStatusBarHeight(Context context) {
        context.getResources();
        int i2 = statusBarHeight;
        if (i2 > 0) {
            return i2;
        }
        Resources resources = getResources(context);
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
        statusBarHeight = dimensionPixelSize;
        return dimensionPixelSize;
    }

    public static String getSystemProperties(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, str);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return "";
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return "";
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            return "";
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return "";
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return "";
        }
    }

    @WindowLocState
    public static int getWindowLocSate(Activity activity) {
        return FoldableUtils.getWindowLocSate(activity);
    }

    public static String getWindowLocSateStr(Activity activity) {
        return FoldableUtils.getWindowLocSateStr(activity);
    }

    public static boolean hasNotchInScreenAtHuawei(Context context) {
        try {
            try {
                Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                return false;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return false;
            } catch (Exception e4) {
                e4.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean hasNotchInScreenAtOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean hasNotchInScreenAtVivo(Context context) {
        try {
            try {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("android.util.FtFeature");
                    return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    return false;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return false;
                }
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean hasNotchInScreenXiaomi(Context context) {
        return TextUtils.equals(getSystemProperties("ro.miui.notch"), "1");
    }

    public static boolean isAndroidPCut(Activity activity) {
        WindowInsets rootWindowInsets;
        View decorView = activity.getWindow().getDecorView();
        return (decorView == null || UnDeviceInfo.getSdkVersion() < 28 || (rootWindowInsets = decorView.getRootWindowInsets()) == null || rootWindowInsets.getDisplayCutout() == null) ? false : true;
    }

    public static boolean isDisplayCutoutLocal(Context context) {
        if ((context instanceof Activity) && isNotchScreen(((Activity) context).getWindow())) {
            return true;
        }
        String manufacturer = UnDeviceInfo.getManufacturer();
        if (TextUtils.isEmpty(manufacturer)) {
            return false;
        }
        if (TextUtils.equals(BRAND_HUAWEI, manufacturer.toUpperCase())) {
            return hasNotchInScreenAtHuawei(context);
        }
        if (TextUtils.equals("VIVO", manufacturer.toUpperCase())) {
            return hasNotchInScreenAtVivo(context);
        }
        if (TextUtils.equals("OPPO", manufacturer.toUpperCase())) {
            return hasNotchInScreenAtOppo(context);
        }
        if (TextUtils.equals(BRAND_XIAOMI, manufacturer.toUpperCase())) {
            return hasNotchInScreenXiaomi(context);
        }
        return false;
    }

    public static boolean isFoldScreen() {
        if (isFoldableDevice != null && isFoldableDevice.booleanValue()) {
            UnLog.i("UnAndroidUtils", "isFoldableDevice::" + isFoldableDevice);
            return isFoldableDevice.booleanValue();
        }
        String manufacturer = UnDeviceInfo.getManufacturer();
        boolean z = false;
        if (!TextUtils.isEmpty(manufacturer)) {
            String upperCase = manufacturer.toUpperCase();
            upperCase.hashCode();
            char c2 = '\uffff';
            switch (upperCase.hashCode()) {
                case -1712043046:
                    if (upperCase.equals("SAMSUNG")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 2432928:
                    if (upperCase.equals("OPPO")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 2634924:
                    if (upperCase.equals("VIVO")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 68924490:
                    if (upperCase.equals(BRAND_HONOR)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 2141820391:
                    if (upperCase.equals(BRAND_HUAWEI)) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    z = FoldableUtils.isSamsungFoldableDevice();
                    break;
                case 1:
                    z = FoldableUtils.isOppoFoldableDevice();
                    break;
                case 2:
                    z = FoldableUtils.isVivoFoldableDevice();
                    break;
                case 3:
                    z = FoldableUtils.isHonorFoldableDevice();
                    break;
                case 4:
                    z = FoldableUtils.isHuaweiFoldableDevice();
                    break;
            }
        }
        if (!z) {
            z = FoldableUtils.isFoldableDeviceFromConfig();
        }
        isFoldableDevice = Boolean.valueOf(z);
        UnLog.i("UnAndroidUtils", "isFoldableDevice::" + isFoldableDevice);
        return z;
    }

    public static boolean isFullScreen(Activity activity) {
        return activity != null && (activity.getWindow().getAttributes().flags & 1024) == 1024;
    }

    @RequiresApi(api = 17)
    public static boolean isFullScreenModel(Context context) {
        try {
            String string = Settings.Global.getString(context.getContentResolver(), "policy_control");
            if (string != null) {
                string = string.trim();
            }
            if (!TextUtils.equals("immersive.full=*", string)) {
                if (!TextUtils.equals("immersive.status=*", string)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            if (UnLog.D) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    public static boolean isMatex(Context context) {
        if (isMatex != null) {
            return isMatex.booleanValue();
        }
        if ("OPPO".equalsIgnoreCase(UnDeviceInfo.getManufacturer())) {
            isMatex = Boolean.valueOf(isOppoLargeScreen(context) || FoldableUtils.isOppoFoldableDevice());
            return isMatex.booleanValue();
        } else if (BRAND_HONOR.equalsIgnoreCase(UnDeviceInfo.getManufacturer()) && context.getPackageManager().hasSystemFeature("com.hihonor.hardware.sensor.posture")) {
            isMatex = Boolean.TRUE;
            return isMatex.booleanValue();
        } else if ("VIVO".equalsIgnoreCase(UnDeviceInfo.getManufacturer()) && FoldableUtils.isVivoFoldableDevice()) {
            isMatex = Boolean.TRUE;
            return true;
        } else if (useMatexNewFun()) {
            if (BRAND_HUAWEI.equalsIgnoreCase(UnDeviceInfo.getManufacturer()) && context.getPackageManager().hasSystemFeature("com.huawei.hardware.sensor.posture")) {
                isMatex = Boolean.TRUE;
            } else {
                isMatex = Boolean.FALSE;
            }
            return isMatex.booleanValue();
        } else {
            return false;
        }
    }

    public static boolean isNotchScreen(Window window) {
        WindowInsets rootWindowInsets;
        DisplayCutout displayCutout;
        return (UnDeviceInfo.getSdkVersion() < 28 || (rootWindowInsets = window.getDecorView().getRootWindowInsets()) == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null || displayCutout.getBoundingRects() == null) ? false : true;
    }

    public static boolean isOppoLargeScreen(Context context) {
        boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("oplus.feature.largescreen");
        String str = "oppo isLargeScreen:" + hasSystemFeature;
        return hasSystemFeature;
    }

    public static boolean isTabletDevice(Context context) {
        if (context == null) {
            return false;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean mateXEasyClient(Context context) {
        if (isMatex(context) && context != null) {
            boolean mateXEasyClientNew = useMatexNewFun() ? mateXEasyClientNew(context) : false;
            if (UnLog.D) {
                UnLog.i("UnAndroidUtils", "mateXEasyClient:" + mateXEasyClientNew);
            }
            return mateXEasyClientNew;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0069, code lost:
        if (com.jd.lib.un.utils.fold.FoldableUtils.isSplitScreenInVivo(r2) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean mateXEasyClientNew(Context context) {
        boolean z = false;
        try {
            String configuration = UnUtilsConfig.getInstance().getConfiguration(context).toString();
            if (UnLog.D) {
                UnLog.d("UnAndroidUtils", "config:" + configuration);
                UnLog.d("UnAndroidUtils", "config-context:" + context.getResources().getConfiguration().toString());
            }
            if (!configuration.contains("magic-windows") && !configuration.contains("hwMultiwindow-magic") && !configuration.contains("hw-magic-windows") && !FoldableUtils.isSplitScreenInOppo(configuration)) {
            }
            z = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (UnLog.D) {
            UnLog.d("UnAndroidUtils", "matex-easy-client-new:" + z);
        }
        return z;
    }

    private static boolean useMatexNewFun() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "baseConfig", "matexEasyClient");
        return TextUtils.isEmpty(config) || TextUtils.equals(config, "1");
    }

    public static boolean isMatex() {
        if (UnUtilsConfig.getInstance().getApplication() != null) {
            return isMatex(UnUtilsConfig.getInstance().getApplication());
        }
        return false;
    }
}
