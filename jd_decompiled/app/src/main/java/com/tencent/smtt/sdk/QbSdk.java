package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsPrivacyAccess;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.Timer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SuppressLint({"NewApi"})
/* loaded from: classes9.dex */
public class QbSdk {
    private static int A = 170;
    private static String B = null;
    private static String C = null;
    @Deprecated
    public static final int CORE_VER_ENABLE_202112 = 45912;
    public static final int CORE_VER_ENABLE_202207 = 46110;
    private static boolean D = false;
    private static boolean E = false;
    public static final int EXTENSION_INIT_FAILURE = -99999;
    private static boolean F = false;
    private static TbsListener G = null;
    private static TbsListener H = null;
    private static boolean I = false;
    private static boolean J = false;
    public static final String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";
    public static final String KEY_THIRD_PARTY_TURING = "turing";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final int QBMODE = 2;
    public static final String SHARE_PREFERENCES_NAME = "tbs_file_open_dialog_config";
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;
    static boolean b = false;

    /* renamed from: c */
    static boolean f17722c = true;
    static String d = null;

    /* renamed from: e */
    static boolean f17723e = false;

    /* renamed from: f */
    static long f17724f = 0;

    /* renamed from: g */
    static long f17725g = 0;

    /* renamed from: i */
    static boolean f17727i = true;

    /* renamed from: j */
    static boolean f17728j = true;

    /* renamed from: k */
    static boolean f17729k = false;

    /* renamed from: l */
    static boolean f17730l = false;
    public static boolean mDisableUseHostBackupCore = false;
    private static int p = 0;
    private static String q = "";
    private static Class<?> r = null;
    private static Object s = null;
    public static boolean sIsVersionPrinted = false;
    private static boolean t = false;
    private static String[] u = null;
    private static String v = "NULL";
    private static String w = "UNKNOWN";
    private static boolean x = false;
    private static int y = -1;
    private static int z;

    /* renamed from: h */
    static Object f17726h = new Object();
    static boolean a = false;

    /* renamed from: m */
    static volatile boolean f17731m = a;

    /* renamed from: n */
    static TbsListener f17732n = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.5
        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadFinish(int i2) {
            TbsDownloader.a = false;
            if (QbSdk.G != null) {
                QbSdk.G.onDownloadFinish(i2);
            }
            if (QbSdk.H != null) {
                QbSdk.H.onDownloadFinish(i2);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadProgress(int i2) {
            if (QbSdk.H != null) {
                QbSdk.H.onDownloadProgress(i2);
            }
            if (QbSdk.G != null) {
                QbSdk.G.onDownloadProgress(i2);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onInstallFinish(int i2) {
            if (i2 == 243 && QbSdk.H != null) {
                QbSdk.H.onInstallFinish(i2);
                return;
            }
            if (i2 != 200) {
            }
            TbsLog.i("QbSdk", "onInstallFinish errCode is " + i2, true);
            QbSdk.setTBSInstallingStatus(false);
            TbsDownloader.a = false;
            if (QbSdk.G != null) {
                QbSdk.G.onInstallFinish(i2);
            }
            if (QbSdk.H != null) {
                QbSdk.H.onInstallFinish(i2);
            }
        }
    };
    private static boolean K = false;
    private static boolean L = false;
    static Map<String, Object> o = null;
    @Deprecated
    public static final String USER_ID_FROM_APP_IMSI = TbsPrivacyAccess.ConfigurablePrivacy.IMSI.a;
    @Deprecated
    public static final String USER_ID_FROM_APP_ANDROID_ID = TbsPrivacyAccess.ConfigurablePrivacy.ANDROID_ID.a;
    @Deprecated
    public static final String USER_ID_FROM_APP_MAC = TbsPrivacyAccess.ConfigurablePrivacy.MAC.a;
    @Deprecated
    public static final String USER_ID_FROM_APP_ANDROID_VERSION = TbsPrivacyAccess.ConfigurablePrivacy.ANDROID_VERSION.a;
    @Deprecated
    public static final String USER_ID_FROM_APP_DEVICE_MODEL = TbsPrivacyAccess.ConfigurablePrivacy.DEVICE_MODEL.a;
    public static final String USER_ID_FROM_APP_QIMEI36 = TbsPrivacyAccess.ConfigurablePrivacy.QIMEI36.a;
    private static int M = -1;
    private static Timer N = null;
    private static PrivateCDNMode O = PrivateCDNMode.NOT_USE;
    private static SystemCoreProtector P = null;

    /* loaded from: classes9.dex */
    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    /* loaded from: classes9.dex */
    public enum PrivateCDNMode {
        OFFICIAL_IMPL,
        SELF_IMPL,
        NOT_USE
    }

    /* loaded from: classes9.dex */
    public interface a {
        void a(File[] fileArr);
    }

    public static Bundle a(Context context, Bundle bundle) {
        TbsLogReport tbsLogReport;
        String str;
        if (a(context)) {
            Object a2 = com.tencent.smtt.utils.j.a(s, "incrUpdate", new Class[]{Context.class, Bundle.class}, context, bundle);
            if (a2 != null) {
                return (Bundle) a2;
            }
            tbsLogReport = TbsLogReport.getInstance(context);
            str = "incrUpdate return null!";
        } else {
            tbsLogReport = TbsLogReport.getInstance(context);
            str = "initForPatch return false!";
        }
        tbsLogReport.setInstallErrorCode(216, str);
        return null;
    }

    public static Object a(Context context, String str, Bundle bundle) {
        if (a(context)) {
            Object a2 = com.tencent.smtt.utils.j.a(s, "miscCall", new Class[]{String.class, Bundle.class}, str, bundle);
            if (a2 != null) {
                return a2;
            }
            return null;
        }
        return Integer.valueOf((int) EXTENSION_INIT_FAILURE);
    }

    public static String a() {
        return q;
    }

    public static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (b) {
                TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(v));
            } else if (a) {
            } else {
                a = true;
                w = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "forceSysWebViewInner", "Reason:" + w);
                TbsLog.e("QbSdk", "forceSysWebViewInner", Log.getStackTraceString(new Throwable("#")));
                TbsCoreLoadStat.getInstance().a(context, 401);
            }
        }
    }

    static boolean a(Context context) {
        try {
            if (r != null) {
                return true;
            }
            File n2 = m.a().n(context);
            if (n2 == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(n2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            u.a().b(context);
            com.tencent.smtt.utils.n.a(context);
            r = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, n2.getAbsolutePath(), getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            if (isEnableSensitiveApi() || com.tencent.smtt.utils.j.a(r, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) != null) {
                loadTBSSDKExtension(context, file.getParent());
                return true;
            }
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    static boolean a(Context context, int i2, int i3) {
        Map<String, Object> map = o;
        if (map != null && map.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) && o.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals(DYConstants.DY_FALSE)) {
            TbsLog.i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
            return true;
        }
        if (!isEnableSensitiveApi() && com.tencent.smtt.utils.j.a(r, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) == null) {
            TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
            return false;
        }
        m.a().b(context, f.a == 0);
        if (d(context)) {
            return TbsDownloader.isTbsCoreDisabledBySwitch(context.getApplicationContext(), i2);
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z2) {
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 44286; SDK_VERSION_NAME: 4.3.0.386");
            sIsVersionPrinted = true;
        }
        if (a && !z2) {
            TbsCoreLoadStat.getInstance().a(context, 414);
            TbsLog.e("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "Force WebView Inner, " + w);
            return false;
        } else if (b) {
            TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(v));
            TbsLog.e("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "QbSdk init mIsSysWebViewForcedByOuter = true, " + v);
            return false;
        } else if (!com.tencent.smtt.utils.b.b()) {
            TbsCoreLoadStat.getInstance().a(context, 326, new Throwable(com.tencent.smtt.utils.b.f17908c));
            TbsLog.i("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "cpu is invalid: " + com.tencent.smtt.utils.b.f17908c);
            return false;
        } else {
            if (TbsPVConfig.getInstance(context).isEnableProtection()) {
                e(context);
            }
            try {
                File n2 = m.a().n(context);
                if (n2 == null) {
                    TbsCoreLoadStat.getInstance().a(context, 312, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                int a2 = m.a().a(true, context);
                int i2 = p;
                if (i2 != 0 && i2 != a2) {
                    r = null;
                    s = null;
                    TbsCoreLoadStat.getInstance().a(context, 303, new Throwable("sTbsVersion: " + p + "; tbsCoreInstalledVer: " + a2));
                    return false;
                }
                p = a2;
                if (TbsDownloader.a(context, a2)) {
                    TbsLog.w("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "version " + p + " is in blacklist,can not load! return");
                    return false;
                } else if (b(context, p)) {
                    TbsLog.w("QbSdk", "version " + p + " is in below min-version that app set by QbSdk.setCoreMinVersion, reset and not load.");
                    return false;
                } else {
                    int i3 = y;
                    if (i3 == -1) {
                        boolean isTbsCoreDisabledBySwitch = TbsDownloader.isTbsCoreDisabledBySwitch(context.getApplicationContext(), p);
                        y = isTbsCoreDisabledBySwitch ? 0 : 1;
                        if (isTbsCoreDisabledBySwitch) {
                            TbsLog.w("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "version " + p + "is disable by remote switch");
                            TbsCoreLoadStat.getInstance().a(context, 309, new Throwable("version " + p + "is disable by remote switch"));
                            return false;
                        }
                    } else if (i3 == 0) {
                        TbsLog.w("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "version " + p + "is disable by remote switch");
                        return false;
                    }
                    if (r == null || s == null) {
                        File n3 = m.a().n(context);
                        File file = new File(n3, "tbs_sdk_extension_dex.jar");
                        if (!file.exists()) {
                            int g2 = m.a().g(context);
                            if (g2 > 0) {
                                File file2 = new File(file.getParentFile(), "tbs_jars_fusion_dex.jar");
                                TbsCoreLoadStat.getInstance().a(context, 403, new Throwable("[loadMsg] extension is miss, fusion exist is " + file2.exists() + ", core is " + g2 + ", dexPath is " + file.getPath()));
                            } else {
                                TbsCoreLoadStat.getInstance().a(context, 404, new Throwable("core path: " + n3));
                            }
                            return false;
                        }
                        String absolutePath = n2.getAbsolutePath();
                        TbsLog.i("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "optDirExtension #1 is " + absolutePath);
                        TbsLog.i("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                        u.a().b(context);
                        com.tencent.smtt.utils.n.a(context);
                        r = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                        if (!isEnableSensitiveApi() && com.tencent.smtt.utils.j.a(r, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) == null) {
                            TbsLog.e("QbSdk", XView2Constants.XVIEW2_ACTION_INIT, "isSuiteableGetSensitative check failed, can not use x5");
                            return false;
                        }
                        loadTBSSDKExtension(context, file.getParent());
                        com.tencent.smtt.utils.j.a(s, "setClientVersion", new Class[]{Integer.TYPE}, 1);
                        return true;
                    }
                    return true;
                }
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, 306, th);
                return false;
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:174:0x01a3 -> B:220:0x01a5). Please submit an issue!!! */
    public static boolean a(Context context, boolean z2, boolean z3) {
        boolean z4;
        TbsLog.i("QbSdk", "canLoadX5", "#1# installTbsCoreIfNeeded");
        m.a().b(context, f.a == 0);
        int disabledCoreVersion = TbsPVConfig.getInstance(context).getDisabledCoreVersion();
        int g2 = m.a().g(context);
        TbsLog.i("QbSdk", "canLoadX5", "#1-1# disabledCoreVersion is " + disabledCoreVersion + " localCoreVersion is " + g2);
        if (disabledCoreVersion != 0 && disabledCoreVersion == g2) {
            TbsLog.e("QbSdk", "canLoadX5", "#2-1# force use sys by remote switch");
            return false;
        } else if (!a(context, z2)) {
            TbsLog.e("QbSdk", "canLoadX5", "#2-4# QbSdk.init failure!");
            return false;
        } else {
            Object obj = s;
            Class cls = Integer.TYPE;
            Object a2 = com.tencent.smtt.utils.j.a(obj, "canLoadX5Core", new Class[]{cls}, 44286);
            StringBuilder sb = new StringBuilder();
            sb.append("#3# invoke sExtensionObj canLoadX5Core and ret is ");
            sb.append(a2 != null);
            TbsLog.i("QbSdk", "canLoadX5", sb.toString());
            if (a2 == null) {
                Object a3 = com.tencent.smtt.utils.j.a(s, "canLoadX5", new Class[]{cls}, Integer.valueOf(com.tencent.smtt.sdk.a.a()));
                if (a3 == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308, new Throwable());
                    return false;
                } else if (!((a3 instanceof String) && ((String) a3).equalsIgnoreCase("AuthenticationFail")) && (a3 instanceof Boolean)) {
                    p = f.d();
                    boolean a4 = a(context, f.d());
                    z4 = ((Boolean) a3).booleanValue() && !a4;
                    if (!z4) {
                        TbsCoreLoadStat.getInstance().a(context, 318, new Throwable("isX5Disable:" + a4 + "(Boolean) ret:" + a3));
                    }
                    return z4;
                } else {
                    return false;
                }
            } else if ((a2 instanceof String) && ((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                TbsLog.e("QbSdk", "canLoadX5", "[LoadError] authenticationFail");
                return false;
            } else if (!(a2 instanceof Bundle)) {
                TbsCoreLoadStat.getInstance().a(context, 323, new Throwable("" + a2));
                TbsLog.e("QbSdk", "canLoadX5", "[LoadError] ret not instance of bundle, ret:" + a2);
                return false;
            } else {
                Bundle bundle = (Bundle) a2;
                if (bundle.isEmpty()) {
                    TbsCoreLoadStat.getInstance().a(context, 324, new Throwable("" + a2));
                    TbsLog.e("QbSdk", "canLoadX5", "[LoadError] empty bundle" + a2);
                    return false;
                }
                try {
                    int i2 = bundle.getInt(FontsContractCompat.Columns.RESULT_CODE, -1);
                    z4 = i2 == 0;
                    if (!z4) {
                        Object obj2 = null;
                        try {
                            obj2 = com.tencent.smtt.utils.j.a(s, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (obj2 instanceof Integer) {
                            TbsCoreLoadStat.getInstance().a(context, ((Integer) obj2).intValue(), new Throwable("detail: " + obj2));
                        } else {
                            TbsCoreLoadStat.getInstance().a(context, 319, new Throwable("retcode:" + i2 + "; detail: " + obj2));
                        }
                        return false;
                    }
                    try {
                        if (Build.VERSION.SDK_INT >= 12) {
                            q = bundle.getString("tbs_core_version", "0");
                        } else {
                            String string = bundle.getString("tbs_core_version");
                            q = string;
                            if (string == null) {
                                q = "0";
                            }
                        }
                    } catch (Exception unused) {
                        q = "0";
                    }
                    try {
                        p = Integer.parseInt(q);
                    } catch (NumberFormatException e3) {
                        e3.printStackTrace();
                        TbsLog.e("QbSdk", "core version string to num error: " + q);
                        p = 0;
                    }
                    f.a(p);
                    if (p == 0) {
                        TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sTbsVersion is 0, string is " + q));
                        TbsLog.e("QbSdk", "canLoadX5", "[LoadError] sTbsVersion is 0, version string is " + q);
                        return false;
                    }
                    try {
                        String[] stringArray = bundle.getStringArray("tbs_jarfiles");
                        u = stringArray;
                        if (stringArray != null) {
                            try {
                                d = bundle.getString("tbs_librarypath");
                                return z4;
                            } catch (Exception e4) {
                                TbsCoreLoadStat.getInstance().a(context, 307, e4);
                                return false;
                            }
                        }
                        TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + u));
                        TbsLog.e("QbSdk", "canLoadX5", "sJarFiles not instanceof String[]: " + u);
                        return false;
                    } catch (Throwable th) {
                        TbsCoreLoadStat.getInstance().a(context, 322, th);
                        return false;
                    }
                } catch (Exception e5) {
                    TbsCoreLoadStat.getInstance().a(context, 319, e5);
                    return false;
                }
            }
        }
    }

    public static String b() {
        Object invokeStaticMethod;
        u a2 = u.a();
        if (a2 == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    static boolean b(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null) {
            return false;
        }
        try {
            if (context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                TbsLog.i("QbSdk", "clearPluginConfigFile #1");
                String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
                String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
                TbsLog.i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
                if (string == null || string.contains(str) || (sharedPreferences = context.getSharedPreferences("plugin_setting", 0)) == null) {
                    return true;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();
                TbsLog.i("QbSdk", "clearPluginConfigFile done");
                return true;
            }
            return true;
        } catch (Throwable th) {
            TbsLog.i("QbSdk", "clearPluginConfigFile error is " + th.getMessage());
            return false;
        }
    }

    public static boolean b(Context context, int i2) {
        int i3 = M;
        if (i2 >= i3 || i2 == 0 || i3 == -1 || !TbsShareManager.isThirdPartyApp(context)) {
            return false;
        }
        g(context);
        TbsCoreLoadStat.getInstance().a(context, 178, new Throwable("QbSdk.setCoreMinVersion: " + M));
        return true;
    }

    public static Timer c() {
        return N;
    }

    public static boolean canDownloadWithoutWifi() {
        return I;
    }

    @Deprecated
    public static void canGetAndroidId(boolean z2) {
        TbsPrivacyAccess.AndroidId.setEnabled(z2);
    }

    @Deprecated
    public static void canGetDeviceId(boolean z2) {
        TbsPrivacyAccess.DeviceId.setEnabled(z2);
    }

    @Deprecated
    public static void canGetSubscriberId(boolean z2) {
        TbsPrivacyAccess.Imsi.setEnabled(z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean canLoadVideo(android.content.Context r5) {
        /*
            java.lang.Object r0 = com.tencent.smtt.sdk.QbSdk.s
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Integer.TYPE
            r4 = 0
            r2[r4] = r3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            r1[r4] = r3
            java.lang.String r3 = "canLoadVideo"
            java.lang.Object r0 = com.tencent.smtt.utils.j.a(r0, r3, r2, r1)
            if (r0 == 0) goto L2a
            r1 = r0
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L33
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 313(0x139, float:4.39E-43)
            goto L30
        L2a:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 314(0x13a, float:4.4E-43)
        L30:
            r1.a(r5, r2)
        L33:
            if (r0 != 0) goto L36
            goto L3c
        L36:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r4 = r0.booleanValue()
        L3c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canLoadVideo(android.content.Context):boolean");
    }

    @Deprecated
    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    @Deprecated
    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        return false;
    }

    public static void canOpenFile(Context context, String str, ValueCallback<Boolean> valueCallback) {
        throw new IllegalAccessError("Current SDK not support TbsReaderView.");
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        throw new IllegalAccessError("Current SDK not support TbsReaderView.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileInputStream, java.io.InputStream] */
    public static boolean canOpenWebPlus(Context context) {
        BufferedInputStream bufferedInputStream;
        ?? fileInputStream;
        if (z == 0) {
            z = com.tencent.smtt.sdk.a.a();
        }
        TbsLog.i("QbSdk", "canOpenWebPlus - totalRAM: " + z);
        boolean z2 = false;
        if (Build.VERSION.SDK_INT < 7 || z < A || context == null) {
            return false;
        }
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(m.a().n(context), "tbs.conf")));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            String property = properties.getProperty("android_sdk_max_supported");
            String property2 = properties.getProperty("android_sdk_min_supported");
            int parseInt = Integer.parseInt(property);
            int parseInt2 = Integer.parseInt(property2);
            int parseInt3 = Integer.parseInt(Build.VERSION.SDK);
            if (parseInt3 <= parseInt && parseInt3 >= parseInt2) {
                int parseInt4 = Integer.parseInt(properties.getProperty("tbs_core_version"));
                try {
                    bufferedInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    fileInputStream = new FileInputStream(new File(m.o(context), "tbs_extension.conf"));
                } catch (Throwable unused2) {
                }
                try {
                    ?? properties2 = new Properties();
                    properties2.load(fileInputStream);
                    int parseInt5 = Integer.parseInt(properties2.getProperty("tbs_local_version"));
                    int parseInt6 = Integer.parseInt(properties2.getProperty(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE_FOR_SWITCH));
                    if (parseInt4 != 88888888 && parseInt5 != 88888888 && parseInt4 <= parseInt5 && parseInt4 == parseInt5 && ((parseInt6 <= 0 || parseInt6 == com.tencent.smtt.utils.b.b(context)) && Boolean.parseBoolean(properties2.getProperty("x5_disabled")))) {
                        if (!TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_SWITCH_BACKUPCORE_ENABLE, false)) {
                            z2 = true;
                        }
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused3) {
                        return !z2;
                    }
                } catch (Throwable unused4) {
                    bufferedInputStream2 = fileInputStream;
                    try {
                        TbsLog.i("QbSdk", "canOpenWebPlus - isX5Disabled Exception");
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception unused5) {
                            }
                        }
                        z2 = true;
                        return !z2;
                    } catch (Throwable th2) {
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception unused6) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            TbsLog.i("QbSdk", "canOpenWebPlus - sdkVersion: " + parseInt3);
            try {
                bufferedInputStream.close();
            } catch (Exception unused7) {
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream2 = bufferedInputStream;
            try {
                th.printStackTrace();
                TbsLog.i("QbSdk", "canOpenWebPlus - canLoadX5 Exception");
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception unused8) {
                    }
                }
                return false;
            } catch (Throwable th4) {
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception unused9) {
                    }
                }
                throw th4;
            }
        }
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = com.tencent.smtt.utils.j.a(s, "canUseVideoFeatrue", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static void checkTbsValidity(Context context) {
        if (context == null || com.tencent.smtt.utils.n.b(context)) {
            return;
        }
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedBy checkTbsValidity");
        TbsCoreLoadStat.getInstance().a(context, 419);
        forceSysWebView();
    }

    @Deprecated
    public static void clear(Context context) {
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void clearAllWebViewCache(android.content.Context r6, boolean r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "clearAllWebViewCache("
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ", "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QbSdk"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r0 = 1
            r2 = 0
            com.tencent.smtt.sdk.WebView r3 = new com.tencent.smtt.sdk.WebView     // Catch: java.lang.Throwable -> L48
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L48
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r3 = r3.getWebViewClientExtension()     // Catch: java.lang.Throwable -> L48
            if (r3 == 0) goto L64
            com.tencent.smtt.sdk.u r2 = com.tencent.smtt.sdk.u.a()     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L43
            boolean r3 = r2.b()     // Catch: java.lang.Throwable -> L45
            if (r3 == 0) goto L43
            com.tencent.smtt.sdk.v r2 = r2.c()     // Catch: java.lang.Throwable -> L45
            r2.a(r6, r7)     // Catch: java.lang.Throwable -> L45
        L43:
            r2 = 1
            goto L64
        L45:
            r2 = move-exception
            r3 = 1
            goto L4b
        L48:
            r3 = move-exception
            r2 = r3
            r3 = 0
        L4b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "clearAllWebViewCache exception 2 -- "
            r4.append(r5)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r2)
            r2 = r3
        L64:
            if (r2 == 0) goto L6c
            java.lang.String r6 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
            return
        L6c:
            android.webkit.WebView r2 = new android.webkit.WebView     // Catch: java.lang.Throwable -> Lb9
            r2.<init>(r6)     // Catch: java.lang.Throwable -> Lb9
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> Lb9
            r4 = 11
            if (r3 < r4) goto L86
            java.lang.String r3 = "searchBoxJavaBridge_"
            r2.removeJavascriptInterface(r3)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r3 = "accessibility"
            r2.removeJavascriptInterface(r3)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r3 = "accessibilityTraversal"
            r2.removeJavascriptInterface(r3)     // Catch: java.lang.Throwable -> Lb9
        L86:
            r2.clearCache(r0)     // Catch: java.lang.Throwable -> Lb9
            if (r7 == 0) goto L95
            android.webkit.CookieSyncManager.createInstance(r6)     // Catch: java.lang.Throwable -> Lb9
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()     // Catch: java.lang.Throwable -> Lb9
            r7.removeAllCookie()     // Catch: java.lang.Throwable -> Lb9
        L95:
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: java.lang.Throwable -> Lb9
            r7.clearUsernamePassword()     // Catch: java.lang.Throwable -> Lb9
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: java.lang.Throwable -> Lb9
            r7.clearHttpAuthUsernamePassword()     // Catch: java.lang.Throwable -> Lb9
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: java.lang.Throwable -> Lb9
            r6.clearFormData()     // Catch: java.lang.Throwable -> Lb9
            android.webkit.WebStorage r6 = android.webkit.WebStorage.getInstance()     // Catch: java.lang.Throwable -> Lb9
            r6.deleteAllData()     // Catch: java.lang.Throwable -> Lb9
            android.webkit.WebIconDatabase r6 = android.webkit.WebIconDatabase.getInstance()     // Catch: java.lang.Throwable -> Lb9
            r6.removeAllIcons()     // Catch: java.lang.Throwable -> Lb9
            goto Ld2
        Lb9:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "clearAllWebViewCache exception 1 -- "
            r7.append(r0)
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r6)
        Ld2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    @Deprecated
    public static void closeFileReader(Context context) {
        u a2 = u.a();
        a2.a(context);
        if (a2.b()) {
            a2.c().p();
        }
    }

    public static String closeNetLogAndSavaToLocal() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            try {
                Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "closeNetLogAndSavaToLocal", new Class[0], new Object[0]);
                if (invokeStaticMethod != null && (invokeStaticMethod instanceof String)) {
                    return (String) invokeStaticMethod;
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static void configurePrivacy(Context context, TbsPrivacyAccess.ConfigurablePrivacy configurablePrivacy, String str) {
        TbsPrivacyAccess.configurePrivacy(context, configurablePrivacy, str);
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        u a2;
        if (context != null && !TbsDownloader.getOverSea(context) && !isMiniQBShortCutExist(context, str, str2) && (a2 = u.a()) != null && a2.b()) {
            Bitmap bitmap = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : null;
            DexLoader b2 = a2.c().b();
            TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
            Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
            TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
            if (invokeStaticMethod != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        return O != PrivateCDNMode.NOT_USE;
    }

    private static boolean d(Context context) {
        try {
            if (r != null) {
                return true;
            }
            File n2 = m.a().n(context);
            if (n2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            File file = new File(m.a().n(context), "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, 406, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            String absolutePath = n2.getAbsolutePath();
            TbsLog.i("QbSdk", "QbSdk init optDirExtension #3 is " + absolutePath);
            TbsLog.i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            u.a().b(context);
            com.tencent.smtt.utils.n.a(context);
            r = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            if (!isEnableSensitiveApi() && com.tencent.smtt.utils.j.a(r, "isSuiteableGetSensitative", (Class<?>[]) new Class[0], new Object[0]) == null) {
                TbsLog.e("QbSdk", "isSuiteableGetSensitative check failed,can not use x5");
                return false;
            }
            loadTBSSDKExtension(context, file.getParent());
            com.tencent.smtt.utils.j.a(s, "setClientVersion", new Class[]{Integer.TYPE}, 1);
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        u a2;
        return (context == null || TbsDownloader.getOverSea(context) || (a2 = u.a()) == null || !a2.b() || a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) == null) ? false : true;
    }

    @Deprecated
    public static void deleteStableCore(Context context, int i2) {
    }

    public static void disAllowThirdAppDownload() {
        f17722c = false;
    }

    public static void disableAutoCreateX5Webview() {
        f17728j = false;
    }

    @Deprecated
    public static void disableSensitiveApi() {
        TbsPrivacyAccess.disableSensitiveApi();
    }

    public static SystemCoreProtector e() {
        return P;
    }

    private static void e(Context context) {
        if (F || !Thread.currentThread().getName().equals("tbs_preinit")) {
            return;
        }
        F = true;
        TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- process:" + context.getApplicationInfo().processName + "; thread:" + Thread.currentThread().getName());
        int g2 = m.a().g(context);
        if (g2 <= 0) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4);
            int i2 = sharedPreferences.getInt("tbs_preload_x5_recorder", 0) + 1;
            if (i2 > 3) {
                TbsCoreLoadStat.getInstance().a(context, 327, new Throwable("thread " + Thread.currentThread().getName()));
                f(context);
                g(context);
                TbsLogReport tbsLogReport = TbsLogReport.getInstance(context);
                TbsLogReport.TbsLogInfo tbsLogInfo = tbsLogReport.tbsLogInfo();
                tbsLogInfo.setErrorCode(g2);
                tbsLogReport.eventReport(TbsLogReport.EventType.TYPE_CORE_PROTECT_RESET, tbsLogInfo);
            } else {
                sharedPreferences.edit().putInt("tbs_preload_x5_recorder", i2).commit();
            }
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "tbs_preload_x5_counter Inc exception:" + Log.getStackTraceString(th));
        }
    }

    public static void enableX5WithoutRestart() {
        throw new UnsupportedOperationException("Current TBS SDK doesn't support");
    }

    public static void f(Context context) {
        if (Thread.currentThread().getName().equals("tbs_preinit")) {
            context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4).edit().putInt("tbs_preload_x5_recorder", 0).commit();
        }
    }

    public static void fileInfoDetect(Context context, String str, android.webkit.ValueCallback<String> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            return;
        }
        try {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, android.webkit.ValueCallback.class}, context, str, valueCallback);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void forceSysWebView() {
        b = true;
        v = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    private static void g(Context context) {
        TbsLog.e("QbSdk", "QbSdk reset!", true);
        try {
            TbsDownloader.stopDownload();
            TbsDownloader.c(context);
            FileUtil.a(getTbsFolderDir(context), false, "core_share_decouple");
            TbsLog.i("QbSdk", "delete downloaded apk success", true);
            m.b();
            File file = new File(context.getFilesDir(), TbsExtensionFunctionManager.BUGLY_SWITCH_FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk reset exception:" + Log.getStackTraceString(th));
        }
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
        }
        return 0L;
    }

    public static String getCurrentProcessName(Context context) {
        FileInputStream fileInputStream;
        byte[] bArr;
        int i2;
        try {
            try {
                fileInputStream = new FileInputStream("/proc/self/cmdline");
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            bArr = new byte[256];
            i2 = 0;
            while (true) {
                int read = fileInputStream.read();
                if (read <= 0 || i2 >= 256) {
                    break;
                }
                bArr[i2] = (byte) read;
                i2++;
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th3;
            }
        }
        if (i2 <= 0) {
            fileInputStream.close();
            return null;
        }
        String str = new String(bArr, 0, i2, "UTF-8");
        try {
            fileInputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return str;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        String[] strArr = u;
        if (!(strArr instanceof String[])) {
            Object a2 = com.tencent.smtt.utils.j.a(s, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, context, context2, str);
            boolean z2 = a2 instanceof String[];
            String[] strArr2 = a2;
            if (!z2) {
                strArr2 = new String[]{""};
            }
            return (String[]) strArr2;
        }
        int length = strArr.length;
        String[] strArr3 = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr3[i2] = str + u[i2];
        }
        return strArr3;
    }

    @Deprecated
    public static boolean getDownloadWithoutWifi() {
        return I;
    }

    public static boolean getIsInitX5Environment() {
        return f17729k;
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return b;
    }

    public static boolean getJarFilesAndLibraryPath(Context context) {
        String str;
        Object obj = s;
        if (obj == null) {
            str = "getJarFilesAndLibraryPath sExtensionObj is null";
        } else {
            Bundle bundle = (Bundle) com.tencent.smtt.utils.j.a(obj, "canLoadX5CoreAndNotLoadSo", new Class[]{Integer.TYPE}, 44286);
            if (bundle != null) {
                u = bundle.getStringArray("tbs_jarfiles");
                d = bundle.getString("tbs_librarypath");
                return true;
            }
            str = "getJarFilesAndLibraryPath bundle is null and coreverison is " + m.a().a(true, context);
        }
        TbsLog.i("QbSdk", str);
        return false;
    }

    public static String getMiniQBVersion(Context context) {
        u a2 = u.a();
        a2.a(context);
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().f();
    }

    public static boolean getOnlyDownload() {
        return f17730l;
    }

    public static String getQQBuildNumber() {
        return C;
    }

    public static Map<String, Object> getSettings() {
        return o;
    }

    public static boolean getTBSInstalling() {
        return J;
    }

    public static String getTID() {
        return B;
    }

    public static File getTbsFolderDir(Context context) {
        if (context == null) {
            return null;
        }
        return context.getDir(com.tencent.smtt.utils.b.c() ? "tbs_64" : "tbs", 0);
    }

    @Deprecated
    public static String getTbsResourcesPath(Context context) {
        return "";
    }

    public static int getTbsSdkVersion() {
        return 44286;
    }

    public static int getTbsVersion(Context context) {
        return m.a().g(context);
    }

    public static int getTbsVersionForCrash(Context context) {
        if (a) {
            return 0;
        }
        int h2 = m.a().h(context);
        if (h2 == 0 && l.a(context).c() == 3) {
            g(context);
        }
        return h2;
    }

    public static int getTmpDirTbsVersion(Context context) {
        if (l.a(context).c() == 2) {
            return m.a().d(context, 0);
        }
        if (l.a(context).b("copy_status") == 1) {
            return m.a().d(context, 1);
        }
        return 0;
    }

    public static String getX5CoreLoadHelp(Context context) {
        String str;
        if (context == null) {
            return "context is null";
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        int g2 = m.a().g(context);
        if (g2 > 0) {
            int loadErrorCode = TbsCoreLoadStat.getLoadErrorCode();
            if (loadErrorCode == -1 && u.a().b()) {
                str = "core load success";
            } else {
                str = "Core Exist version is " + g2 + ", error code: " + loadErrorCode + ", detail: " + TbsCoreLoadStat.getLoadErrorMessage() + ", suggestion: " + com.tencent.smtt.utils.f.a().a(loadErrorCode);
            }
        } else {
            int nextPostInterval = TbsDownloader.getNextPostInterval(context);
            if (TbsDownloader.a) {
                str = "core is downloading, please check TbsListener.onDownloadProgress.";
            } else if (Apn.getApnType(context) != 3 && !canDownloadWithoutWifi()) {
                str = "Mobile Network will not download as default. If you want, you can set QbSdk.setDownloadWithoutWifi(true) in the beginning of your app.";
            } else if (nextPostInterval > 0) {
                str = "Perhaps server is busy, try to restart your app in " + nextPostInterval + "s later or (uninstall + reinstall).";
            } else {
                str = "Core not exist, unknown network problem.";
            }
        }
        return str + "If it doesn't help, contact with x5 with your tbslog.txt, site: x5.tencent.com";
    }

    public static void initBuglyAsync(boolean z2) {
        f17727i = z2;
    }

    public static void initForinitAndNotLoadSo(Context context) {
        File n2;
        if (r != null || (n2 = m.a().n(context)) == null) {
            return;
        }
        File file = new File(n2, "tbs_sdk_extension_dex.jar");
        if (file.exists()) {
            String absolutePath = n2.getAbsolutePath();
            u.a().b(context);
            com.tencent.smtt.utils.n.a(context);
            r = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Map<String, Object> map2 = o;
        if (map2 == null) {
            o = map;
            return;
        }
        try {
            map2.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initX5Environment(final Context context, final PreInitCallback preInitCallback) {
        com.tencent.smtt.utils.r.a().a("init_tbs_Start");
        TbsLog.initIfNeed(context);
        if (context == null) {
            TbsLog.e("QbSdk", "initX5Environment,context=null");
        } else if (d()) {
            TbsLog.i("QbSdk", "using private CDN mode, preInit directly.");
            preInit(context, preInitCallback);
        } else {
            b(context);
            H = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.3
                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadFinish(int i2) {
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadProgress(int i2) {
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onInstallFinish(int i2) {
                    QbSdk.preInit(context, preInitCallback);
                }
            };
            if (TbsShareManager.isThirdPartyApp(context)) {
                m.a().b(context, f.a == 0);
            }
            TbsDownloader.needDownload(context, false, false, true, new TbsDownloader.TbsDownloaderCallback() { // from class: com.tencent.smtt.sdk.QbSdk.4
                @Override // com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
                public void onNeedDownloadFinish(boolean z2, int i2) {
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        QbSdk.f17732n.onDownloadFinish(TbsDownloadConfig.getInstance(context).getCurrentDownloadInterruptCode());
                        if (QbSdk.f17727i) {
                            TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
                        }
                    }
                    if (QbSdk.c() != null) {
                        TbsDownloader.a(context, new Runnable() { // from class: com.tencent.smtt.sdk.QbSdk.4.1
                            {
                                AnonymousClass4.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                QbSdk.preInit(context, preInitCallback);
                            }
                        });
                    } else {
                        QbSdk.preInit(context, preInitCallback);
                    }
                }
            });
            f17729k = true;
        }
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        f a2 = f.a(true);
        a2.a(context, false, false);
        if (a2 == null || !a2.b()) {
            return false;
        }
        return a2.a().a(context, str, str2, bundle);
    }

    @Deprecated
    public static void installLocalTbsCore(Context context, int i2, String str) {
        m.a().a(context, str, i2);
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        String str3;
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            str = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
                str3 = "unknown";
            }
            hashMap.put("ChannelID", str3);
            hashMap.put("PosID", "14004");
            if (MttLoader.loadUrl(context, "miniqb://home".equals(str) ? "qb://navicard/addCard?cardId=168&cardName=168" : str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                u a2 = u.a();
                if (a2 != null && a2.b() && a2.c().a(context, str, null, str2, null) == 0) {
                    return true;
                }
            }
            return false;
        }
        webView.loadUrl(str);
        return false;
    }

    @Deprecated
    public static boolean isEnableCanGetSubscriberId() {
        return TbsPrivacyAccess.Imsi.isEnabled();
    }

    @Deprecated
    public static boolean isEnableGetAndroidID() {
        return TbsPrivacyAccess.AndroidId.isEnabled();
    }

    @Deprecated
    public static boolean isEnableGetDeviceID() {
        return TbsPrivacyAccess.DeviceId.isEnabled();
    }

    @Deprecated
    public static boolean isEnableSensitiveApi() {
        return TbsPrivacyAccess.isEnableSensitiveApi();
    }

    public static boolean isEnableX5WithoutRestart() {
        return E;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        u a2;
        Object invokeStaticMethod;
        if (context == null || TbsDownloader.getOverSea(context) || (a2 = u.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str)) == null) {
            return false;
        }
        Boolean bool = Boolean.FALSE;
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        }
        return bool.booleanValue();
    }

    public static boolean isNeedInitX5FirstTime() {
        return x;
    }

    public static boolean isStaticNeedDownload() {
        return L;
    }

    public static boolean isTbsCoreInited() {
        f a2 = f.a(false);
        return a2 != null && a2.h();
    }

    public static boolean isX5Core() {
        if (getIsSysWebViewForcedByOuter()) {
            return false;
        }
        return u.a().b();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (l.a(context).c() == 2) {
            return false;
        }
        if (d(context)) {
            int g2 = m.a().g(context);
            Object obj = s;
            Class cls = Integer.TYPE;
            Object a2 = com.tencent.smtt.utils.j.a(obj, "isX5DisabledSync", new Class[]{cls, cls}, Integer.valueOf(g2), 44286);
            if (a2 != null) {
                return ((Boolean) a2).booleanValue();
            }
            return true;
        }
        return true;
    }

    public static void loadTBSSDKExtension(Context context, String str) {
        Constructor<?> constructor;
        boolean z2;
        Object newInstance;
        if (s != null) {
            return;
        }
        synchronized (QbSdk.class) {
            if (s != null) {
                return;
            }
            if (r == null) {
                TbsLog.i("QbSdk", "QbSdk loadTBSSDKExtension sExtensionClass is null");
            }
            try {
                constructor = r.getConstructor(Context.class, Context.class, String.class, String.class, String.class);
                z2 = true;
            } catch (Throwable unused) {
                constructor = null;
                z2 = false;
            }
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            if (z2) {
                newInstance = constructor.newInstance(context, context, null, str, (!"com.tencent.mm".equals(getCurrentProcessName(context)) || WebView.mWebViewCreated) ? null : "notLoadSo");
            } else {
                newInstance = r.getConstructor(Context.class, Context.class).newInstance(context, context);
            }
            s = newInstance;
        }
    }

    public static void openNetLog(String str) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            return;
        }
        try {
            a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "openNetLog", new Class[]{String.class}, str);
        } catch (Exception unused) {
        }
    }

    public static void pauseDownload() {
        TbsDownloader.pauseDownload();
    }

    public static void preInit(Context context) {
        preInit(context, null);
    }

    public static void preInit(Context context, PreInitCallback preInitCallback) {
        preInit(context, false, preInitCallback);
    }

    public static synchronized void preInit(final Context context, boolean z2, final PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            com.tencent.smtt.utils.r.a().a("preinit_start");
            TbsLog.initIfNeed(context);
            TbsLog.i("QbSdk", "preInit", "processName: " + getCurrentProcessName(context));
            TbsLog.i("QbSdk", "preInit", "stack trace: " + Log.getStackTraceString(new Throwable("PreInit Detect:")));
            if (z2 && t && !E) {
                TbsLog.w("QbSdk", "haven't enableX5WithoutRestart");
                return;
            }
            if (z2) {
                a = false;
                t = false;
                TbsLog.w("QbSdk", "is forcePreInit, local web core status has reset");
            }
            if (!t) {
                TbsLog.i("QbSdk", "preInit", "#1# Start new thread to preInit, thread name: tbs_preinit");
                new Handler(Looper.getMainLooper()) { // from class: com.tencent.smtt.sdk.QbSdk.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        PreInitCallback preInitCallback2;
                        int i2 = message.what;
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 == 3 && (preInitCallback2 = preInitCallback) != null) {
                                    preInitCallback2.onCoreInitFinished();
                                    return;
                                }
                                return;
                            }
                            com.tencent.smtt.utils.r.a().a("preinit_finish");
                            PreInitCallback preInitCallback3 = preInitCallback;
                            if (preInitCallback3 != null) {
                                preInitCallback3.onViewInitFinished(false);
                                com.tencent.smtt.utils.r.a().a("init_tbs_end");
                            }
                            TbsLog.writeLogToDisk();
                            com.tencent.smtt.utils.r.a().a(context);
                            return;
                        }
                        com.tencent.smtt.utils.r.a().a("preinit_finish");
                        if (QbSdk.f17728j) {
                            com.tencent.smtt.utils.r.a().a("create_webview_start");
                            v c2 = u.a().c();
                            if (c2 != null) {
                                c2.a(context);
                            }
                            com.tencent.smtt.utils.r.a().a("create_webview_end");
                        }
                        PreInitCallback preInitCallback4 = preInitCallback;
                        if (preInitCallback4 != null) {
                            preInitCallback4.onViewInitFinished(true);
                            com.tencent.smtt.utils.r.a().a("init_tbs_end");
                        }
                        com.tencent.smtt.utils.r.a().a(context);
                        TbsLog.writeLogToDisk();
                    }
                };
                Thread thread = new Thread
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0083: CONSTRUCTOR (r8v1 'thread' java.lang.Thread) = 
                      (r6v0 'context' android.content.Context A[DONT_INLINE])
                      (r7 I:android.os.Handler A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[Catch: all -> 0x0098, DECLARE_VAR, MD:(android.content.Context, android.os.Handler):void (m)] call: com.tencent.smtt.sdk.QbSdk.2.<init>(android.content.Context, android.os.Handler):void type: CONSTRUCTOR in method: com.tencent.smtt.sdk.QbSdk.preInit(android.content.Context, boolean, com.tencent.smtt.sdk.QbSdk$PreInitCallback):void, file: classes9.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:240)
                    	at jadx.core.dex.regions.SynchronizedRegion.generate(SynchronizedRegion.java:44)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    java.lang.Class<com.tencent.smtt.sdk.QbSdk> r0 = com.tencent.smtt.sdk.QbSdk.class
                    monitor-enter(r0)
                    java.lang.String r1 = "preInit"
                    com.tencent.smtt.utils.r r2 = com.tencent.smtt.utils.r.a()     // Catch: java.lang.Throwable -> L98
                    java.lang.String r3 = "preinit_start"
                    r2.a(r3)     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.utils.TbsLog.initIfNeed(r6)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r2 = "QbSdk"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
                    r3.<init>()     // Catch: java.lang.Throwable -> L98
                    java.lang.String r4 = "processName: "
                    r3.append(r4)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r4 = getCurrentProcessName(r6)     // Catch: java.lang.Throwable -> L98
                    r3.append(r4)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.utils.TbsLog.i(r2, r1, r3)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r2 = "QbSdk"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
                    r3.<init>()     // Catch: java.lang.Throwable -> L98
                    java.lang.String r4 = "stack trace: "
                    r3.append(r4)     // Catch: java.lang.Throwable -> L98
                    java.lang.Throwable r4 = new java.lang.Throwable     // Catch: java.lang.Throwable -> L98
                    java.lang.String r5 = "PreInit Detect:"
                    r4.<init>(r5)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r4 = android.util.Log.getStackTraceString(r4)     // Catch: java.lang.Throwable -> L98
                    r3.append(r4)     // Catch: java.lang.Throwable -> L98
                    java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.utils.TbsLog.i(r2, r1, r3)     // Catch: java.lang.Throwable -> L98
                    if (r7 == 0) goto L5f
                    boolean r2 = com.tencent.smtt.sdk.QbSdk.t     // Catch: java.lang.Throwable -> L98
                    if (r2 == 0) goto L5f
                    boolean r2 = com.tencent.smtt.sdk.QbSdk.E     // Catch: java.lang.Throwable -> L98
                    if (r2 != 0) goto L5f
                    java.lang.String r6 = "QbSdk"
                    java.lang.String r7 = "haven't enableX5WithoutRestart"
                    com.tencent.smtt.utils.TbsLog.w(r6, r7)     // Catch: java.lang.Throwable -> L98
                    monitor-exit(r0)
                    return
                L5f:
                    if (r7 == 0) goto L6d
                    r7 = 0
                    com.tencent.smtt.sdk.QbSdk.a = r7     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.sdk.QbSdk.t = r7     // Catch: java.lang.Throwable -> L98
                    java.lang.String r7 = "QbSdk"
                    java.lang.String r2 = "is forcePreInit, local web core status has reset"
                    com.tencent.smtt.utils.TbsLog.w(r7, r2)     // Catch: java.lang.Throwable -> L98
                L6d:
                    boolean r7 = com.tencent.smtt.sdk.QbSdk.t     // Catch: java.lang.Throwable -> L98
                    if (r7 != 0) goto L96
                    java.lang.String r7 = "QbSdk"
                    java.lang.String r2 = "#1# Start new thread to preInit, thread name: tbs_preinit"
                    com.tencent.smtt.utils.TbsLog.i(r7, r1, r2)     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.sdk.QbSdk$1 r7 = new com.tencent.smtt.sdk.QbSdk$1     // Catch: java.lang.Throwable -> L98
                    android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L98
                    r7.<init>(r1)     // Catch: java.lang.Throwable -> L98
                    com.tencent.smtt.sdk.QbSdk$2 r8 = new com.tencent.smtt.sdk.QbSdk$2     // Catch: java.lang.Throwable -> L98
                    r8.<init>()     // Catch: java.lang.Throwable -> L98
                    java.lang.String r6 = "tbs_preinit"
                    r8.setName(r6)     // Catch: java.lang.Throwable -> L98
                    r6 = 10
                    r8.setPriority(r6)     // Catch: java.lang.Throwable -> L98
                    r8.start()     // Catch: java.lang.Throwable -> L98
                    r6 = 1
                    com.tencent.smtt.sdk.QbSdk.t = r6     // Catch: java.lang.Throwable -> L98
                L96:
                    monitor-exit(r0)
                    return
                L98:
                    r6 = move-exception
                    monitor-exit(r0)
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.preInit(android.content.Context, boolean, com.tencent.smtt.sdk.QbSdk$PreInitCallback):void");
            }

            public static boolean preinstallStaticTbs(Context context) {
                throw new IllegalStateException("Current SDK is not support static mode.");
            }

            public static void reset(Context context) {
                if (K) {
                    TbsLog.w("QbSdk", "[warning] TBS only allow reset 1 times each process.");
                    return;
                }
                K = true;
                g(context);
            }

            @Deprecated
            public static void reset(Context context, boolean z2) {
                if (K) {
                    TbsLog.w("QbSdk", "[warning] TBS only allow reset 1 times each process.");
                    return;
                }
                K = true;
                g(context);
            }

            @Deprecated
            public static void resetDecoupleCore(Context context) {
            }

            public static void resumeDownload() {
                TbsDownloader.resumeDownload();
            }

            public static void setAppList(Context context, List<String> list) {
                if (list == null) {
                    return;
                }
                TbsPrivacyAccess.configureAllPrivacy(context, TextUtils.join(DYConstants.DY_REGEX_COMMA, list));
            }

            public static void setCoreMinVersion(int i2) {
                TbsLog.i("QbSdk", "setCoreMinVersion: " + i2);
                M = i2;
            }

            public static void setCurrentID(String str) {
                if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
                    String substring = str.substring(3);
                    B = "0000000000000000".substring(substring.length()) + substring;
                }
            }

            public static void setDisableUnpreinitBySwitch(boolean z2) {
                D = z2;
                TbsLog.i("QbSdk", "setDisableUnpreinitBySwitch -- mDisableUnpreinitBySwitch is " + D);
            }

            public static void setDisableUseHostBackupCoreBySwitch(boolean z2) {
                mDisableUseHostBackupCore = z2;
                TbsLog.i("QbSdk", "setDisableUseHostBackupCoreBySwitch -- mDisableUseHostBackupCore is " + mDisableUseHostBackupCore);
            }

            public static void setDownloadWithoutWifi(boolean z2) {
                I = z2;
            }

            @Deprecated
            public static void setEnableForThirdParty(Context context, Bundle bundle) {
                com.tencent.smtt.utils.s.a(context, bundle);
            }

            public static void setMultiProcessSyncInitTimer(Timer timer) {
                if (N == null) {
                    N = timer;
                }
            }

            public static void setNeedInitX5FirstTime(boolean z2) {
                x = z2;
            }

            public static void setNeedStaticWithDownload(boolean z2) {
                throw new IllegalStateException("Current SDK is not support static mode.");
            }

            public static void setNetLogEncryptionKey(String str) {
                u a2 = u.a();
                if (a2 == null || !a2.b()) {
                    return;
                }
                try {
                    a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "setNetLogEncryptionKey", new Class[]{String.class}, str);
                } catch (Exception unused) {
                }
            }

            public static void setNewDnsHostList(String str) {
                u a2 = u.a();
                if (a2 == null || !a2.b()) {
                    return;
                }
                try {
                    a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "setNewDnsHostList", new Class[]{String.class}, str);
                } catch (Exception unused) {
                }
            }

            public static void setOnlyDownload(boolean z2) {
                f17730l = z2;
            }

            public static void setQQBuildNumber(String str) {
                C = str;
            }

            public static void setSandboxExternalEnable(boolean z2) {
                FileUtil.a(z2);
            }

            public static void setSystemCoreProtector(SystemCoreProtector systemCoreProtector) {
                throw new com.tencent.smtt.utils.a.a();
            }

            public static void setTBSInstallingStatus(boolean z2) {
                J = z2;
            }

            public static void setTbsInstallerCallback(a aVar) {
                m.a().a(aVar);
            }

            public static void setTbsListener(TbsListener tbsListener) {
                G = tbsListener;
            }

            public static void setTbsLogClient(TbsLogClient tbsLogClient) {
                TbsLog.setTbsLogClient(tbsLogClient);
            }

            public static void setUUID(String str) {
                com.tencent.smtt.utils.b.a(str, true);
            }

            @Deprecated
            public static void setUploadCode(Context context, int i2) {
            }

            public static void setUserID(Context context, Bundle bundle) {
                TbsPrivacyAccess.configureAllPrivacy(context, bundle);
            }

            public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, android.webkit.ValueCallback<String> valueCallback) {
                TbsCoreLoadStat.getInstance().a(context, 501);
                if (context == null) {
                    return -100;
                }
                u a2 = u.a();
                a2.a(context);
                if (!a2.b()) {
                    TbsCoreLoadStat.getInstance().a(context, 502);
                    return -102;
                } else if (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) {
                    int a3 = a2.c().a(context, str, hashMap, null, valueCallback);
                    TbsLog.i("QbSdk", "startMiniQBToLoadUrl  ret = " + a3);
                    return a3;
                } else {
                    return -101;
                }
            }

            public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
                HashMap hashMap = new HashMap();
                hashMap.put("ChannelID", context.getApplicationContext().getApplicationInfo().processName);
                hashMap.put("PosID", Integer.toString(i2));
                return MttLoader.openDocWithQb(context, str, i3, str2, hashMap, bundle);
            }

            public static boolean startQBForVideo(Context context, String str, int i2) {
                HashMap hashMap = new HashMap();
                hashMap.put("ChannelID", context.getApplicationInfo().processName);
                hashMap.put("PosID", Integer.toString(i2));
                return MttLoader.openVideoWithQb(context, str, hashMap);
            }

            public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
                u a2;
                Object invokeStaticMethod;
                IX5WebViewBase iX5WebViewBase;
                HashMap hashMap = new HashMap();
                hashMap.put("ChannelID", context.getApplicationInfo().processName);
                hashMap.put("PosID", Integer.toString(i2));
                if (webView == null) {
                    try {
                        String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                        if ((str2 == "com.tencent.mm" || str2 == "com.tencent.mobileqq") && (a2 = u.a()) != null && a2.b() && (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) != null && (iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod) != null) {
                            webView = (WebView) iX5WebViewBase.getView().getParent();
                        }
                    } catch (Exception unused) {
                    }
                }
                return MttLoader.loadUrl(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
            }

            public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
                if (context == null) {
                    return false;
                }
                u a2 = u.a();
                a2.a(context);
                if (hashMap != null && "5".equals(hashMap.get("PosID")) && a2.b()) {
                    Bundle bundle = (Bundle) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
                }
                if (MttLoader.loadUrl(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                    return a2.b() && (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) && a2.c().a(context, str, hashMap, null, valueCallback) == 0;
                }
                return true;
            }

            public static void unForceSysWebView() {
                b = false;
                TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
            }

            public static void uploadNetLog(String str) {
                u a2 = u.a();
                if (a2 == null || !a2.b()) {
                    return;
                }
                try {
                    a2.c().b().invokeStaticMethod("com.tencent.smtt.livelog.NetLogManager", "uploadNetLog", new Class[]{String.class}, str);
                } catch (Exception unused) {
                }
            }

            public static void usePrivateCDN() {
                throw new com.tencent.smtt.utils.a.a();
            }

            public static void usePrivateCDN(PrivateCDNMode privateCDNMode) {
                throw new com.tencent.smtt.utils.a.a();
            }

            public static boolean useSoftWare() {
                Object obj = s;
                if (obj == null) {
                    return false;
                }
                Object a2 = com.tencent.smtt.utils.j.a(obj, "useSoftWare", new Class[0], new Object[0]);
                if (a2 == null) {
                    a2 = com.tencent.smtt.utils.j.a(s, "useSoftWare", new Class[]{Integer.TYPE}, Integer.valueOf(com.tencent.smtt.sdk.a.a()));
                }
                if (a2 == null) {
                    return false;
                }
                return ((Boolean) a2).booleanValue();
            }
        }
