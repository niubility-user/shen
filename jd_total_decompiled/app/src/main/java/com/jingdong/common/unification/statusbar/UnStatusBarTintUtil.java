package com.jingdong.common.unification.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.utils.R;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.lib.un.utils.UnLog;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes6.dex */
public class UnStatusBarTintUtil {
    private static final int STATUSBAR_TYPE_ANDROID6 = 3;
    private static final int STATUSBAR_TYPE_DEFAULT = 0;
    private static final int STATUSBAR_TYPE_FLYME = 2;
    private static final int STATUSBAR_TYPE_MIUI = 1;
    public static final int STATUS_BAR_HINT_TAG_DEFAULT = 0;
    public static final int STATUS_BAR_HINT_TAG_DISABLE = -1;
    public static final int STATUS_BAR_HINT_TAG_HINT = 1;
    private static final String TAG_DEF = "tag_def";
    private static final String TAG_TRAN = "tag_tran";
    public static int computeUsableHeight = -1;
    private static IStatusController controller;
    public static boolean isBrowseMode;
    private static int mStatuBarType;
    private static Integer sTransparentValue;

    @TargetApi(23)
    private static boolean Android6SetStatusBarLightMode(Window window, boolean z) {
        window.getDecorView().setSystemUiVisibility(changeStatusBarModeRetainFlag(window, z ? 8192 : 256));
        if (UnDeviceHelper.isMIUIV9()) {
            MIUISetStatusBarLightMode(window, z);
            return true;
        }
        return true;
    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean z) {
        if (UnDeviceInfo.getSdkVersion() >= 23) {
            Android6SetStatusBarLightMode(window, z);
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

    private static void addStatusBarView(Activity activity, View view, boolean z) {
        ViewGroup decorView = getDecorView(activity);
        if (decorView == null || view == null) {
            return;
        }
        View findViewById = decorView.findViewById(R.id.un_status_bar_view);
        if (findViewById != null) {
            decorView.removeView(findViewById);
        }
        if (UnLog.D) {
            UnLog.d("statusbar", "addView ");
        }
        if (z) {
            view.setTag(TAG_TRAN);
        } else {
            view.setTag(TAG_DEF);
        }
        decorView.addView(view);
    }

    @TargetApi(23)
    private static int changeStatusBarModeRetainFlag(Window window, int i2) {
        return retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, retainSystemUiFlag(window, i2, 1024), 4), 2), 4096), 1024), 512);
    }

    public static void clear(Activity activity) {
        if (greaterOrEqualKitkat()) {
            Window window = activity.getWindow();
            if (greaterOrEqualLollipop()) {
                window.clearFlags(67108864);
                window.clearFlags(Integer.MIN_VALUE);
                return;
            }
            window.addFlags(67108864);
        }
    }

    private static View createStatusBarView(Activity activity) {
        int statusBarHeight = getStatusBarHeight(activity);
        View view = new View(activity);
        view.setId(R.id.un_status_bar_view);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, statusBarHeight));
        return view;
    }

    public static void cutout(Activity activity) {
        boolean isDisplayCutoutLocal;
        try {
            IStatusController iStatusController = controller;
            if (iStatusController != null) {
                isDisplayCutoutLocal = iStatusController.isDisplayCutout();
            } else {
                isDisplayCutoutLocal = UnAndroidUtils.isDisplayCutoutLocal(activity);
            }
            if (isDisplayCutoutLocal && (activity.getWindow().getAttributes().flags & 1024) == 1024) {
                activity.getWindow().clearFlags(1024);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void defaultSetStatusBarbg(Activity activity) {
        if (isEnable(activity)) {
            View statusBarView = getStatusBarView(activity);
            if (UnLog.D) {
                UnLog.d("statusbar-sencond", "no tran--view-->" + statusBarView);
            }
            if (statusBarView != null) {
                String str = statusBarView.getTag() == null ? "" : (String) statusBarView.getTag();
                if (UnLog.D) {
                    UnLog.d("statusbar-sencond", "no tran--tag-->" + str);
                }
                if (TextUtils.equals(TAG_DEF, str)) {
                    return;
                }
            }
            setTransparentStatusBar(activity);
            addStatusBarView(activity, createStatusBarView(activity), false);
            if (setStatusBarLightMode(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
            setFitsSystemWindows(activity, -1);
        }
    }

    public static void defaultSetTranslucent(Activity activity) {
        if (isEnable(activity)) {
            View statusBarView = getStatusBarView(activity);
            if (UnLog.D) {
                UnLog.d("statusbar-sencond", "tran--view-->" + statusBarView);
            }
            if (statusBarView != null) {
                String str = statusBarView.getTag() == null ? "" : (String) statusBarView.getTag();
                if (UnLog.D) {
                    UnLog.d("statusbar-sencond", "tran--tag-->" + str);
                }
                if (TextUtils.equals(TAG_TRAN, str)) {
                    return;
                }
            }
            setTransparentStatusBar(activity);
            addStatusBarView(activity, createStatusBarView(activity), true);
            setBackgroundColor(activity, 0);
            setFitsSystemWindows(activity, 1);
        }
    }

    public static boolean equalsModel(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(UnDeviceInfo.getDeviceModel(), str);
    }

    public static ViewGroup getDecorView(Activity activity) {
        return (ViewGroup) activity.getWindow().getDecorView();
    }

    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static View getStatusBar(Activity activity) {
        return getDecorView(activity).findViewById(R.id.un_status_bar_view);
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

    public static int getStatusBarHeight(Activity activity) {
        return UnAndroidUtils.getStatusBarHeight(activity);
    }

    public static View getStatusBarView(Activity activity) {
        ViewGroup decorView = getDecorView(activity);
        if (decorView != null) {
            return decorView.findViewById(R.id.un_status_bar_view);
        }
        return null;
    }

    public static boolean greaterM() {
        return UnDeviceInfo.getSdkVersion() > 23;
    }

    public static boolean greaterOrEqualKitkat() {
        return UnDeviceInfo.getSdkVersion() >= 19;
    }

    public static boolean greaterOrEqualLollipop() {
        return UnDeviceInfo.getSdkVersion() >= 21;
    }

    public static boolean greaterOrEqualM() {
        return UnDeviceInfo.getSdkVersion() >= 23;
    }

    public static boolean greaterOrEqualN() {
        return UnDeviceInfo.getSdkVersion() >= 24;
    }

    public static void init(IStatusController iStatusController) {
        if (UnLog.D && iStatusController != null) {
            iStatusController.toString();
        }
        controller = iStatusController;
    }

    public static boolean isEnable(Activity activity) {
        IStatusController iStatusController;
        if (activity == null) {
            return false;
        }
        if (isBrowseMode) {
            return true;
        }
        if (!greaterOrEqualKitkat() || !isEnableWithKV() || UnAndroidUtils.isFullScreen(activity) || specialPhoneUnable(activity) || UnAndroidUtils.isFullScreenModel(activity)) {
            return false;
        }
        return !greaterOrEqualN() || !activity.isInMultiWindowMode() || (iStatusController = controller) == null || iStatusController.statusBarTransparentEnable() || controller.statusBarHint() == 1;
    }

    public static boolean isEnableChangeModeWithKV() {
        IStatusController iStatusController = controller;
        String serverConfigValue = iStatusController != null ? iStatusController.getServerConfigValue() : "1";
        if (TextUtils.isEmpty(serverConfigValue)) {
            serverConfigValue = "1";
        }
        return TextUtils.equals(serverConfigValue, "1");
    }

    public static boolean isEnableWithKV() {
        IStatusController iStatusController = controller;
        return !TextUtils.equals(TextUtils.isEmpty(iStatusController != null ? iStatusController.getServerConfigValue() : "1") ? "1" : r0, "0");
    }

    private static boolean isMIUICustomStatusBarLightModeImpl() {
        return UnDeviceHelper.isMIUIV5() || UnDeviceHelper.isMIUIV6() || UnDeviceHelper.isMIUIV7() || UnDeviceHelper.isMIUIV8();
    }

    public static void newDefaultSetStatusBarbg(Activity activity) {
        if (isEnable(activity)) {
            View statusBarView = getStatusBarView(activity);
            if (UnLog.D) {
                UnLog.d("statusbar-sencond", "no tran--view-->" + statusBarView);
            }
            if (statusBarView != null) {
                String str = statusBarView.getTag() == null ? "" : (String) statusBarView.getTag();
                if (UnLog.D) {
                    UnLog.d("statusbar-sencond", "no tran--tag-->" + str);
                }
                if (TextUtils.equals(TAG_DEF, str)) {
                    return;
                }
            }
            setTransparentStatusBar(activity);
            addStatusBarView(activity, createStatusBarView(activity), false);
            if (setStatusBarLightMode(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
            setFitsSystemWindows(activity, 0);
        }
    }

    public static void refresh(Activity activity, boolean z) {
        if (isEnable(activity)) {
            if (z) {
                setStatusBarDarkMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg_dark);
            } else if (setStatusBarLightMode(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
        }
    }

    public static void removeStatusView(Activity activity) {
        activity.getWindow();
        ViewGroup decorView = getDecorView(activity);
        View findViewById = decorView.findViewById(R.id.un_status_bar_view);
        if (findViewById != null) {
            decorView.removeView(findViewById);
        }
    }

    public static int retainSystemUiFlag(Window window, int i2, int i3) {
        return (window.getDecorView().getSystemUiVisibility() & i3) == i3 ? i2 | i3 : i2;
    }

    public static void setBackground(Activity activity, Drawable drawable) {
        View statusBarView;
        if (!greaterOrEqualKitkat() || activity == null || drawable == null || (statusBarView = getStatusBarView(activity)) == null) {
            return;
        }
        statusBarView.setBackgroundDrawable(drawable);
    }

    public static void setBackgroundColor(Activity activity, int i2) {
        View statusBarView;
        if (!greaterOrEqualKitkat() || activity == null || (statusBarView = getStatusBarView(activity)) == null) {
            return;
        }
        statusBarView.setBackgroundColor(i2);
    }

    public static void setBackgroundResource(Activity activity, int i2) {
        View statusBarView;
        if (!greaterOrEqualKitkat() || activity == null || i2 < 0 || (statusBarView = getStatusBarView(activity)) == null) {
            return;
        }
        statusBarView.setBackgroundResource(i2);
    }

    public static void setDefaultBg(Activity activity, boolean z) {
        if (isEnable(activity)) {
            if (z) {
                setBackgroundColor(activity, 0);
            } else if (setLightOrDarkEnable(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
        }
    }

    public static void setFitsSystemWindows(Activity activity, int i2) {
        if (((ViewGroup) activity.findViewById(16908290)).getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
            if (i2 == 0) {
                viewGroup.setFitsSystemWindows(false);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                viewGroup.setLayoutParams(marginLayoutParams);
            } else if (i2 != 1) {
                viewGroup.setFitsSystemWindows(true);
            } else {
                viewGroup.setFitsSystemWindows(false);
            }
            viewGroup.setClipToPadding(true);
        }
    }

    public static boolean setLightOrDarkEnable(Activity activity) {
        if (activity != null && isEnable(activity) && isEnableChangeModeWithKV() && !UnDeviceHelper.isZTKC2016()) {
            return greaterOrEqualM() || isMIUICustomStatusBarLightModeImpl();
        }
        return false;
    }

    public static void setStatusBar4Base(Activity activity, int i2) {
        if (activity == null || !greaterOrEqualKitkat()) {
            return;
        }
        if (i2 == 0) {
            newDefaultSetStatusBarbg(activity);
        } else if (i2 != 1) {
            defaultSetStatusBarbg(activity);
        } else {
            defaultSetTranslucent(activity);
        }
    }

    public static boolean setStatusBarDarkMode(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (mStatuBarType == 0) {
            return true;
        }
        if (setLightOrDarkEnable(activity)) {
            int i2 = mStatuBarType;
            if (i2 == 1) {
                return MIUISetStatusBarLightMode(activity.getWindow(), false);
            }
            if (i2 == 2) {
                return FlymeSetStatusBarLightMode(activity.getWindow(), false);
            }
            if (i2 == 3) {
                return Android6SetStatusBarLightMode(activity.getWindow(), false);
            }
            return true;
        }
        return false;
    }

    public static boolean setStatusBarLightMode(Activity activity) {
        if (setLightOrDarkEnable(activity)) {
            int i2 = mStatuBarType;
            if (i2 != 0) {
                return setStatusBarLightMode(activity, i2);
            }
            if (isMIUICustomStatusBarLightModeImpl() && MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = 1;
                return true;
            } else if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = 2;
                return true;
            } else if (greaterOrEqualM()) {
                Android6SetStatusBarLightMode(activity.getWindow(), true);
                mStatuBarType = 3;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void setTransparentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup decorView = getDecorView(activity);
        if (greaterOrEqualLollipop()) {
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(R2.attr.lineSpacing);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else {
            window.addFlags(67108864);
        }
        View findViewById = decorView.findViewById(R.id.un_status_bar_view);
        if (findViewById != null) {
            decorView.removeView(findViewById);
        }
    }

    @RequiresApi(api = 17)
    private static boolean specialPhoneUnable(Context context) {
        if (UnAndroidUtils.getAndroidVersion() >= 26) {
            return false;
        }
        String manufacturer = UnDeviceInfo.getManufacturer();
        String deviceModel = UnDeviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(manufacturer)) {
            return false;
        }
        String upperCase = manufacturer.toUpperCase();
        if (UnAndroidUtils.isTabletDevice(context) && TextUtils.equals("HUAWEI", UnDeviceInfo.getManufacturer())) {
            return (deviceModel.contains("RLI") || deviceModel.contains("TAH") || deviceModel.contains("ANL")) ? false : true;
        }
        if (TextUtils.equals("HUAWEI", upperCase)) {
            if (TextUtils.isEmpty(deviceModel)) {
                return false;
            }
            if (deviceModel.contains("A03") || deviceModel.contains("A01") || deviceModel.contains("M2")) {
                return true;
            }
        }
        return TextUtils.equals("ESSENTIAL", upperCase);
    }

    public static boolean supportTransclentStatusBar6() {
        return (UnDeviceHelper.isZUKZ1() || UnDeviceHelper.isZTKC2016()) ? false : true;
    }

    public static int getStatusBarHeight(Context context) {
        return UnAndroidUtils.getStatusBarHeight(context);
    }

    public static void setStatusBar4Base(Activity activity, int i2, boolean z) {
        if (activity == null || !greaterOrEqualKitkat()) {
            return;
        }
        if (i2 == 0) {
            newDefaultSetStatusBarbg(activity, z);
        } else if (i2 != 1) {
            defaultSetStatusBarbg(activity, z);
        } else {
            defaultSetTranslucent(activity);
        }
    }

    public static void setDefaultBg(Activity activity, boolean z, boolean z2) {
        if (isEnable(activity)) {
            if (z) {
                setBackgroundColor(activity, 0);
            } else if (setLightOrDarkEnable(activity) && !z2) {
                setStatusBarLightMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setStatusBarDarkMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
        }
    }

    public static boolean setLightOrDarkEnable(Context context) {
        if (context != null && isEnable(context) && isEnableChangeModeWithKV() && !UnDeviceHelper.isZTKC2016()) {
            return greaterOrEqualM() || isMIUICustomStatusBarLightModeImpl();
        }
        return false;
    }

    public static boolean isEnable(Context context) {
        IStatusController iStatusController;
        IStatusController iStatusController2;
        if (isBrowseMode) {
            return true;
        }
        if (context == null || !greaterOrEqualKitkat() || !isEnableWithKV() || specialPhoneUnable(context) || UnAndroidUtils.isFullScreenModel(context)) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (UnAndroidUtils.isFullScreen(activity)) {
                return false;
            }
            if (greaterOrEqualN() && activity.isInMultiWindowMode() && (iStatusController2 = controller) != null) {
                return iStatusController2.statusBarTransparentEnable();
            }
        }
        return !UnAndroidUtils.mateXEasyClient(context) || (iStatusController = controller) == null || iStatusController.statusBarTransparentEnable() || controller.statusBarHint() == 1;
    }

    private static boolean setStatusBarLightMode(Activity activity, int i2) {
        if (i2 == 1) {
            return MIUISetStatusBarLightMode(activity.getWindow(), true);
        }
        if (i2 == 2) {
            return FlymeSetStatusBarLightMode(activity.getWindow(), true);
        }
        if (i2 == 3) {
            return Android6SetStatusBarLightMode(activity.getWindow(), true);
        }
        return false;
    }

    public static void setDefaultBg(Activity activity, boolean z, boolean z2, boolean z3) {
        if (isEnable(activity)) {
            if (z) {
                setBackgroundColor(activity, 0);
            } else if (setLightOrDarkEnable(activity) && !z2) {
                setStatusBarLightMode(activity);
                if (!z3) {
                    setBackgroundResource(activity, R.color.un_status_bar_bg_light);
                } else {
                    setBackgroundResource(activity, R.color.un_status_bar_bg_gray);
                }
            } else {
                setStatusBarDarkMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
        }
    }

    public static void defaultSetStatusBarbg(Activity activity, boolean z) {
        if (isEnable(activity)) {
            View statusBarView = getStatusBarView(activity);
            if (UnLog.D) {
                UnLog.d("statusbar-sencond", "no tran--view-->" + statusBarView);
            }
            if (statusBarView != null) {
                String str = statusBarView.getTag() == null ? "" : (String) statusBarView.getTag();
                if (UnLog.D) {
                    UnLog.d("statusbar-sencond", "no tran--tag-->" + str);
                }
                if (TextUtils.equals(TAG_DEF, str)) {
                    return;
                }
            }
            setTransparentStatusBar(activity);
            addStatusBarView(activity, createStatusBarView(activity), false);
            if (z) {
                setStatusBarDarkMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg_dark);
            } else if (setStatusBarLightMode(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
            setFitsSystemWindows(activity, -1);
        }
    }

    public static void newDefaultSetStatusBarbg(Activity activity, boolean z) {
        if (isEnable(activity)) {
            View statusBarView = getStatusBarView(activity);
            if (UnLog.D) {
                UnLog.d("statusbar-sencond", "no tran--view-->" + statusBarView);
            }
            if (statusBarView != null) {
                String str = statusBarView.getTag() == null ? "" : (String) statusBarView.getTag();
                if (UnLog.D) {
                    UnLog.d("statusbar-sencond", "no tran--tag-->" + str);
                }
                if (TextUtils.equals(TAG_DEF, str)) {
                    return;
                }
            }
            setTransparentStatusBar(activity);
            addStatusBarView(activity, createStatusBarView(activity), false);
            if (z) {
                setStatusBarDarkMode(activity);
                setBackgroundResource(activity, R.color.un_status_bar_bg_dark);
            } else if (setStatusBarLightMode(activity)) {
                setBackgroundResource(activity, R.color.un_status_bar_bg_light);
            } else {
                setBackgroundResource(activity, R.color.un_status_bar_bg);
            }
            setFitsSystemWindows(activity, 0);
        }
    }
}
