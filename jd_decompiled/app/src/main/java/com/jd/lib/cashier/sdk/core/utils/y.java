package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.bcashier.IBCashierConfig;
import com.jd.cashier.app.jdlibcutter.protocol.caas.IHWCaaS;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.eldermode.IElderMode;
import com.jd.cashier.app.jdlibcutter.protocol.live.ILive;
import com.jd.cashier.app.jdlibcutter.protocol.notification.INotification;
import com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy;
import com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk;
import com.jd.cashier.app.jdlibcutter.protocol.thread.IThreadPool;

/* loaded from: classes14.dex */
public class y {
    public static void a() {
        IHWCaaS hWCaaS = DependInitializer.getHWCaaS();
        if (hWCaaS != null) {
            hWCaaS.activeShare();
        }
    }

    public static void b() {
        INotification notification = DependInitializer.getNotification();
        if (notification != null) {
            notification.activePopNotification();
        }
    }

    public static void c(LifecycleOwner lifecycleOwner, Observer<Integer> observer, boolean z) {
        IDarkMode darkMode = DependInitializer.getDarkMode();
        if (darkMode != null) {
            darkMode.addDeepDarkChangeListener(lifecycleOwner, observer, z);
        }
    }

    public static void d() {
        ILive live = DependInitializer.getLive();
        if (live != null) {
            live.collapseLiveWindow();
        }
    }

    public static void e(Runnable runnable) {
        IThreadPool threadPool = DependInitializer.getThreadPool();
        if (runnable == null || threadPool == null) {
            return;
        }
        threadPool.pushJob(runnable);
    }

    public static void f() {
        ILive live = DependInitializer.getLive();
        if (live != null) {
            live.expandLiveWindow();
        }
    }

    public static String g() {
        IRisk risk = DependInitializer.getRisk();
        return risk != null ? risk.getAId() : "";
    }

    public static String h() {
        IBCashierConfig bCashierConfig = DependInitializer.getBCashierConfig();
        return bCashierConfig != null ? bCashierConfig.getCurrentMode() : "";
    }

    public static DisplayMetrics i() {
        IPrivacy privacy = DependInitializer.getPrivacy();
        if (privacy != null) {
            return privacy.getDisplayMetrics();
        }
        return null;
    }

    public static int j() {
        IElderMode elderMode = DependInitializer.getElderMode();
        if (elderMode != null) {
            return elderMode.getElderMode();
        }
        return -1;
    }

    public static String k() {
        IRisk risk = DependInitializer.getRisk();
        return risk != null ? risk.getPackageId() : "";
    }

    public static String l() {
        IConfig sdkConfig = DependInitializer.getSdkConfig();
        return sdkConfig != null ? sdkConfig.getPayAppID() : "";
    }

    public static String m() {
        IConfig sdkConfig = DependInitializer.getSdkConfig();
        return sdkConfig != null ? sdkConfig.getPayAppKey() : "";
    }

    public static float n(float f2) {
        IElderMode elderMode = DependInitializer.getElderMode();
        return elderMode != null ? elderMode.getScaleTextSize(f2) : f2;
    }

    public static String o() {
        IRisk risk = DependInitializer.getRisk();
        return risk != null ? risk.getLocalIpAddress() : "";
    }

    public static boolean p() {
        IDarkMode darkMode = DependInitializer.getDarkMode();
        return darkMode != null && darkMode.isDarkMode();
    }

    public static boolean q(Context context) {
        IPrivacy privacy = DependInitializer.getPrivacy();
        return privacy != null && privacy.isNFCAvailable(context);
    }

    public static boolean r(Context context, String str) {
        IPrivacy privacy = DependInitializer.getPrivacy();
        if (privacy != null) {
            return privacy.isPackageInstalled(context, str);
        }
        return false;
    }

    public static boolean s() {
        IPrivacy privacy = DependInitializer.getPrivacy();
        return privacy != null && privacy.isProcessForeground();
    }

    public static boolean t(float f2) {
        return n(f2) != f2;
    }

    public static void u() {
        IDynamic dynamic = DependInitializer.getDynamic();
        if (dynamic != null) {
            dynamic.prepare("pay", "");
        }
    }

    public static void v(Observer<Integer> observer) {
        IDarkMode darkMode = DependInitializer.getDarkMode();
        if (darkMode != null) {
            darkMode.removeDeepDarkChangeListener(observer);
        }
    }

    public static void w() {
        INotification notification = DependInitializer.getNotification();
        if (notification != null) {
            notification.shieldActivePopNotification();
        }
    }

    public static void x() {
        IHWCaaS hWCaaS = DependInitializer.getHWCaaS();
        if (hWCaaS != null) {
            hWCaaS.shieldShare();
        }
    }
}
