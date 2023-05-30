package com.jd.lib.push.utils;

import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdpush_new.j.f;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.l;
import com.jingdong.jdpush_new.j.o;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.xiaomi.mipush.sdk.m;

/* loaded from: classes16.dex */
public class PushMessageUtils {
    public static final int GT_MODLE = 5;
    public static final int HW_MODLE = 2;
    public static final int MI_MODLE = 1;
    public static final int MZ_MODLE = 3;
    public static final int OPPO_MODLE = 6;
    private static final long OUT_OF_DAY_TIME = 864000000;
    private static final String TAG = "PushMessageUtils";
    public static final int VIVO_MODLE = 8;
    public static long initTime;
    private static boolean initialized;

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.a("push\u521d\u59cb\u5316\u5f00\u59cb");
            BackForegroundWatcher.getInstance().registerListener(new com.jd.lib.push.b());
            com.jingdong.jdpush_new.mta.b.b().d(JdSdk.getInstance().getApplicationContext(), true);
            com.jingdong.jdpush_new.mta.b.b().l(100);
            com.jd.lib.push.c.b.b().c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.i("PushBundle", "tryBindAgain");
            try {
                String pin = UserUtil.getWJLoginHelper().getPin();
                if (TextUtils.isEmpty(pin) || !PushMessageUtils.needBind(JdSdk.getInstance().getApplication(), pin).booleanValue()) {
                    return;
                }
                PushMessageUtils.messageCenterNeedCallByLogin();
                g.a("\u7248\u672c\u53d8\u5316/\u5927\u4e8e\u5341\u5929\u91cd\u65b0\u7ed1\u5b9a");
            } catch (Throwable th) {
                g.g(th);
            }
        }
    }

    public static /* synthetic */ void a(Context context) {
        com.jd.lib.push.c.a a2 = com.jd.lib.push.c.b.b().a();
        if (a2 == null || !a2.d()) {
            return;
        }
        a2.b(context);
    }

    public static /* synthetic */ void b(Context context, int i2) {
        com.jd.lib.push.c.a a2 = com.jd.lib.push.c.b.b().a();
        if (a2 == null || !a2.d()) {
            return;
        }
        a2.e(context, i2);
    }

    public static void bindPin(String str) {
        try {
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            if (!TextUtils.isEmpty(str)) {
                com.jingdong.jdpush_new.a.a(applicationContext, 0, str, null);
                g.a("JDmessageCenterNeedCallByLogin------------------->\u767b\u9646\u7ed1\u5b9a");
                int a2 = l.a();
                if (a2 != 0) {
                    String mIRegId = getMIRegId(applicationContext, a2);
                    if (!TextUtils.isEmpty(mIRegId)) {
                        com.jingdong.jdpush_new.a.a(applicationContext, a2, str, mIRegId);
                        g.a("messageCenterNeedCallByLogin------------------->\u767b\u9646\u7ed1\u5b9a,deviceModel=" + a2);
                    }
                }
            }
            saveBingMessage(str, getSdkVersion(), PackageInfoUtil.getVersionName(), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void checkRegId(String str) {
    }

    public static void clearBadge(final Context context) {
        if (d.n()) {
            o.b().a(new Runnable() { // from class: com.jd.lib.push.utils.b
                @Override // java.lang.Runnable
                public final void run() {
                    PushMessageUtils.a(context);
                }
            });
            return;
        }
        com.jd.lib.push.c.a a2 = com.jd.lib.push.c.b.b().a();
        if (a2 == null || !a2.d()) {
            return;
        }
        a2.b(context);
    }

    public static void clearPushNotices() {
        if (ProcessUtil.isMainProcess() && l.a() == 1) {
            m.o(JdSdk.getInstance().getApplication().getApplicationContext());
        }
    }

    public static synchronized void controlPushService(boolean z) {
        synchronized (PushMessageUtils.class) {
            if (initialized) {
                g.a("\u5df2\u521d\u59cb\u5316\u8fc7push");
                return;
            }
            initialized = true;
            initTime = System.currentTimeMillis();
            MessageCommonUtils.jdPushApi = new c();
            o.b().a(new a());
        }
    }

    public static String getMIRegId(Context context, int i2) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("MIRegId" + i2, "");
    }

    public static String getPushConfig() {
        return CommonBase.getStringFromPreference("pushConfig", "");
    }

    public static boolean getPushState(int i2) {
        return SharedPreferencesUtil.getBoolean("MIRegId" + i2, true);
    }

    public static String getSavedOpenPushStatus(Context context) {
        return SharedPreferencesUtil.getString("messageCenterOpenPush", "");
    }

    public static String getSdkVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static void init() {
        controlPushService(true);
    }

    public static boolean isAPPVersionChange() {
        return System.currentTimeMillis() - PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplication().getApplicationContext()).getLong("messageCenterRegistTime", 0L) > OUT_OF_DAY_TIME || !PackageInfoUtil.getVersionName().equals(PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplication().getApplicationContext()).getString("messageCenterRegistApp", null));
    }

    public static void messageCenterNeedCallByLogin() {
        bindPin(UserUtil.getWJLoginHelper().getPin());
    }

    public static Boolean needBind(Context context, String str) {
        return Boolean.valueOf((System.currentTimeMillis() - PreferenceManager.getDefaultSharedPreferences(context).getLong("messageCenterBingTime", 0L) <= OUT_OF_DAY_TIME && getSdkVersion().equals(PreferenceManager.getDefaultSharedPreferences(context).getString("messageCenterBingOs", null)) && PackageInfoUtil.getVersionName().equals(PreferenceManager.getDefaultSharedPreferences(context).getString("messageCenterBingApp", null)) && str.equals(SharedPreferencesUtil.getString("messageCenterBingPin", null))) ? false : true);
    }

    public static boolean openGeTuiPush(Context context) {
        return !SharedPreferencesUtil.getString("messageCenterGeTuiOpenPush", "").equals(f.b(context));
    }

    public static boolean openPush(Context context) {
        return !SharedPreferencesUtil.getString("messageCenterOpenPush", "").equals(f.b(context));
    }

    public static void recordOpenPushInfo(Context context, int i2, String str, String str2, int i3, String str3, String str4) {
        try {
            com.jingdong.jdpush_new.a.e(context, i2, str, str2, i3, str3, str4);
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void registeredBusiness(int i2, Class<?> cls) {
        g.b(TAG, i2 + "" + cls.getName());
        try {
            com.jingdong.jdpush_new.a.g(i2, cls);
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void reportUnbind(Context context, int i2, String str) {
    }

    public static void saveBingMessage(String str, String str2, String str3, Long l2) {
        SharedPreferencesUtil.putString("messageCenterBingPin", str);
        PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplicationContext()).edit().putString("messageCenterBingOs", str2).apply();
        PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplicationContext()).edit().putString("messageCenterBingApp", str3).apply();
        PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplicationContext()).edit().putLong("messageCenterBingTime", l2.longValue()).apply();
    }

    public static void saveMIRegId(Context context, String str, int i2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("MIRegId" + i2, str).apply();
        PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplicationContext()).edit().putString("messageCenterRegistApp", PackageInfoUtil.getVersionName()).apply();
        PreferenceManager.getDefaultSharedPreferences(JdSdk.getInstance().getApplicationContext()).edit().putLong("messageCenterRegistTime", System.currentTimeMillis()).apply();
    }

    public static void saveOpenGeTuiPush(String str) {
        SharedPreferencesUtil.putString("messageCenterGeTuiOpenPush", str);
    }

    public static void saveOpenPush(String str) {
        SharedPreferencesUtil.putString("messageCenterOpenPush", str);
    }

    public static void savePushState(int i2, boolean z) {
        SharedPreferencesUtil.putBoolean("MIRegId" + i2, z);
    }

    public static void setBadgeNum(final Context context, final int i2) {
        if (d.n()) {
            o.b().a(new Runnable() { // from class: com.jd.lib.push.utils.a
                @Override // java.lang.Runnable
                public final void run() {
                    PushMessageUtils.b(context, i2);
                }
            });
            return;
        }
        com.jd.lib.push.c.a a2 = com.jd.lib.push.c.b.b().a();
        if (a2 == null || !a2.d()) {
            return;
        }
        a2.e(context, i2);
    }

    public static void stopPushService() {
        try {
            com.jingdong.jdpush_new.a.j(JdSdk.getInstance().getApplication().getApplicationContext());
            l.a();
        } catch (IllegalAccessError e2) {
            g.g(e2);
        }
    }

    public static void tryBindAgain() {
        ThreadManager.light().post(new b());
    }

    public static void unBindUser(String str) {
        try {
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            com.jingdong.jdpush_new.a.i(applicationContext, 0, str, null);
            g.a("JDunBindUser----------------------------->\u9000\u51fa\u89e3\u7ed1");
            int a2 = l.a();
            if (a2 != 0) {
                String mIRegId = getMIRegId(applicationContext, a2);
                if (TextUtils.isEmpty(mIRegId)) {
                    return;
                }
                com.jingdong.jdpush_new.a.i(applicationContext, a2, str, mIRegId);
                g.a("unBindUser----------------------------->\u9000\u51fa\u89e3\u7ed1\uff0cdeviceModel=" + a2);
            }
        } catch (Throwable th) {
            g.g(th);
        }
    }
}
