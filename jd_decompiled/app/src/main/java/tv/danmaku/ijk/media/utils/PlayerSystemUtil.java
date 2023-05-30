package tv.danmaku.ijk.media.utils;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import com.jd.android.sdk.coreinfo.ScreenSize;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.JDPlayerSdk;

/* loaded from: classes11.dex */
public class PlayerSystemUtil {
    private static int HEIGHT_STATUS_BAR = 0;
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    private static long mLastClickTime;

    public static int dip2px(Context context, float f2) {
        return (context == null || BaseInfo.getDensity() == 0.0f) ? (int) f2 : (int) ((f2 * BaseInfo.getDensity()) + 0.5f);
    }

    public static ScreenSize getScreenSize(Context context) {
        if (context == null) {
            return null;
        }
        return BaseInfo.getRealScreenSize();
    }

    @SuppressLint({"PrivateApi"})
    public static int getStatusBarHeight(Context context) {
        if (HEIGHT_STATUS_BAR == 0) {
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                HEIGHT_STATUS_BAR = context.getResources().getDimensionPixelSize(((Integer) cls.getField("status_bar_height").get(cls.newInstance())).intValue());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return HEIGHT_STATUS_BAR;
    }

    public static void hideUIMenu(Window window, boolean z) {
        int i2;
        if (window == null || window.getDecorView() == null) {
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            i2 = R2.color.c_863077;
            if (z) {
                i2 = R2.color.c_878787;
            }
        } else {
            i2 = i3 >= 16 ? R2.attr.buttonIconDimen : 2;
        }
        window.getDecorView().setSystemUiVisibility(i2);
    }

    public static boolean isFastClick() {
        if (System.currentTimeMillis() - mLastClickTime < 300) {
            return true;
        }
        mLastClickTime = System.currentTimeMillis();
        return false;
    }

    public static boolean requestOverlayPermission(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
                return false;
            }
        } else if (i2 >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            try {
                Class cls = Integer.TYPE;
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", cls, cls, String.class).invoke(appOpsManager, 24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception e2) {
                DebugLog.e(JDPlayerSdk.TAG_JDPLAYER, Log.getStackTraceString(e2));
                return false;
            }
        }
        return true;
    }
}
