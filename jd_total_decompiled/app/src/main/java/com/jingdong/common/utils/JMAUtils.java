package com.jingdong.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.UserHandle;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.stat.bot.BlogUtil;
import com.jd.stat.bot.BotDetector;
import com.jd.stat.common.callback.JmaCallback;
import com.jd.stat.security.InfoCollectHelper;
import com.jd.stat.security.PrivacyHelper;
import com.jd.stat.security.SecurityInit;
import com.jd.stat.security.TrackBaseData;
import com.jd.stat.security.WSKeyHelper;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.entity.SceneStatus;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.union.common.config.UnionConstants;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jd.wjlogin_sdk.util.f;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JMAUtils {
    private static final String TAG = "JMAUtils";
    private static ExecutorService sExecutorService = Executors.newCachedThreadPool();
    private static String refer = "";

    public static void JMAReportForScene(final String str, final String str2) {
        ExecutorService executorService = sExecutorService;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.jingdong.common.utils.JMAUtils.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (OKLog.D) {
                            OKLog.d(JMAUtils.TAG, "reportforscene sid:" + str + " bid:" + str2);
                        }
                        JMA.triggerEvent(JdSdk.getInstance().getApplicationContext(), "ReportLoginScene", "{\"sceneId\":\"" + str + "\",\"businessId\":\"" + str2 + "\"}");
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            OKLog.d(JMAUtils.TAG, th);
                        }
                    }
                }
            });
        }
    }

    public static void JMAReportReferrerForInterfaceActivity(final String str, final Activity activity, final String str2, final String str3) {
        ExecutorService executorService = sExecutorService;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.jingdong.common.utils.JMAUtils.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        JSONObject reportEvent = JMAUtils.getReportEvent(str, activity);
                        if (reportEvent != null) {
                            try {
                                reportEvent.put("openapp_param", str2);
                                reportEvent.put("openapp_action", str3);
                                reportEvent.put("addScreenTime", true);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        JMA.report(JdSdk.getInstance().getApplication(), reportEvent);
                        SharedPreferencesUtil.putString("InterfaceActivityStartTime", String.valueOf(System.currentTimeMillis()));
                        JMAUtils.getBlog(true);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public static void JMAReportReferrerForMainActivity(final String str, final Activity activity) {
        ExecutorService executorService = sExecutorService;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.jingdong.common.utils.JMAUtils.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        JMA.report(JdSdk.getInstance().getApplication(), JMAUtils.getReportEvent(str, activity));
                        SharedPreferencesUtil.putString("MainActivityStartTime", String.valueOf(System.currentTimeMillis()));
                        JMAUtils.getBlog(true);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public static String getBlog() {
        return getBlog(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8 */
    private static int getInteractive(Context context) {
        boolean z;
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (Build.VERSION.SDK_INT >= 20) {
                z = powerManager.isInteractive();
            } else {
                z = powerManager.isScreenOn();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            z = 0;
        }
        return z ^ 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int getKeyGuardLockedStatus(Context context) {
        int i2;
        try {
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (Build.VERSION.SDK_INT >= 16) {
                i2 = keyguardManager.isKeyguardLocked();
            } else {
                i2 = keyguardManager.inKeyguardRestrictedInputMode();
            }
            return i2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private static String getMD5(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getRecentTasksReflect(Context context, int i2, int i3) throws Exception {
        int i4;
        Object invoke;
        int i5;
        List list;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            invoke = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            i5 = Build.VERSION.SDK_INT;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (i5 >= 28) {
            return "";
        }
        if (i5 <= 16) {
            Class<?> cls = invoke.getClass();
            Class<?> cls2 = Integer.TYPE;
            Method declaredMethod = cls.getDeclaredMethod("getRecentTasks", cls2, cls2);
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(invoke, Integer.valueOf(i2), Integer.valueOf(i3));
        } else {
            Method declaredMethod2 = UserHandle.class.getDeclaredMethod("myUserId", new Class[0]);
            declaredMethod2.setAccessible(true);
            Object invoke2 = declaredMethod2.invoke(null, new Object[0]);
            Class<?> cls3 = invoke.getClass();
            Class<?> cls4 = Integer.TYPE;
            Method declaredMethod3 = cls3.getDeclaredMethod("getRecentTasks", cls4, cls4, cls4);
            declaredMethod3.setAccessible(true);
            list = (List) declaredMethod3.invoke(invoke, Integer.valueOf(i2), Integer.valueOf(i3), invoke2);
        }
        if (list != null && list.size() != 0) {
            for (i4 = 0; i4 < list.size(); i4++) {
                stringBuffer.append(((ActivityManager.RecentTaskInfo) list.get(i4)).baseIntent.getComponent().getPackageName());
                stringBuffer.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        return stringBuffer.toString();
    }

    public static String getRefererByRecentTask4(Activity activity) {
        if (Log.D) {
            Log.d(TAG, "getRefererByRecentTask() called with: openingActivity = [" + activity + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("recentTaskStartTime = ");
            sb.append(System.currentTimeMillis());
            Log.d(TAG, sb.toString());
        }
        List<ActivityManager.RecentTaskInfo> recentTasks = ((ActivityManager) activity.getSystemService("activity")).getRecentTasks(4, 0);
        StringBuilder sb2 = new StringBuilder();
        Iterator<ActivityManager.RecentTaskInfo> it = recentTasks.iterator();
        while (it.hasNext()) {
            ComponentName component = it.next().baseIntent.getComponent();
            if (component != null) {
                sb2.append(component.getPackageName());
            }
            sb2.append(DYConstants.DY_REGEX_COMMA);
        }
        if (Log.D) {
            Log.d(TAG, "recentTaskEndTime = " + System.currentTimeMillis());
        }
        return sb2.toString();
    }

    public static String getReferrers(Activity activity) {
        return JMA.getReferrers(activity);
    }

    private static String getReferrersDetail(Context context, String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || context == null || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            try {
                if (!TextUtils.isEmpty(str2) && !includeJDApp(str2)) {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str2, 64);
                    String str3 = packageInfo.versionName;
                    long j2 = packageInfo.lastUpdateTime;
                    Signature[] signatureArr = packageInfo.signatures;
                    String md5 = (signatureArr == null || signatureArr.length <= 0 || signatureArr[0] == null) ? "" : getMD5(signatureArr[0].toByteArray());
                    sb.append(str2);
                    sb.append("###");
                    sb.append(str3);
                    sb.append("###");
                    sb.append(j2);
                    sb.append("###");
                    sb.append(md5);
                    sb.append("###");
                    sb.append("0");
                    sb.append("$$$");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getReportEvent(String str, Activity activity) {
        String userPin = LoginUserBase.getUserPin();
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        JSONObject jSONObject = new JSONObject();
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId("03b05847ea061aff507a5eedda9b5e29");
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        try {
            String referrers = getReferrers(activity);
            refer = referrers;
            jSONObject.put("devicecode", readDeviceUUID);
            jSONObject.put("eventid", str);
            jSONObject.put("uid", userPin);
            jSONObject.put(JDMtaUtils.LON, location.getLng() + "");
            jSONObject.put("lat", location.getLat() + "");
            jSONObject.put(UnionConstants.BUNDLE_REFER, referrers);
            jSONObject.put("referDetail", getReferrersDetail(activity, referrers));
            jSONObject.put("screenStatus", getScreenStatus(activity));
            jSONObject.put("isKeyguardLocked", getKeyGuardLockedStatus(activity));
            jSONObject.put("isInteractive", getInteractive(activity));
            jSONObject.put("api_level", Build.VERSION.SDK_INT);
            jSONObject.put("MainActivityStartTime", SharedPreferencesUtil.getString("MainActivityStartTime", ""));
            jSONObject.put("InterfaceActivityStartTime", SharedPreferencesUtil.getString("InterfaceActivityStartTime", ""));
            jSONObject.put("rt", getRecentTasksReflect(activity, 4, 0));
            if (Log.D) {
                Log.d(TAG, "refer == " + getReferrers(activity));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static int getScreenStatus(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 20) {
                int state = ((DisplayManager) context.getSystemService(ViewProps.DISPLAY)).getDisplays()[0].getState();
                if (state == 0) {
                    return 3;
                }
                if (state == 1) {
                    return 0;
                }
                if (state == 2) {
                    return 1;
                }
                if (state == 3 || state == 4) {
                    return 2;
                }
                return state;
            }
            return -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 3;
        }
    }

    private static boolean includeJDApp(String str) {
        String[] strArr = {f.f19954c, "com.jd.jrapp", "com.jingdong.pdj", "com.jd.app.reader", "com.jingdong.app.reader", "com.thestore.main"};
        for (int i2 = 0; i2 < 6; i2++) {
            if (TextUtils.equals(str, strArr[i2])) {
                return true;
            }
        }
        return false;
    }

    public static void initJMATrack() {
        String property = Configuration.getProperty(Configuration.UNION_ID);
        String property2 = Configuration.getProperty(Configuration.PARTNER);
        String property3 = Configuration.getProperty(Configuration.SUB_UNION_ID);
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        String userPin = LoginUserBase.getUserPin();
        if (OKLog.I) {
            OKLog.i(TAG, "initJMATrack");
        }
        SecurityInit.init(JdSdk.getInstance().getApplication(), "100", new TrackBaseData.TrackBaseDataBuilder().partner(property2).deviceCode(readDeviceUUID).subunionId(property3).unionId(property).installtionid(StatisticsReportUtil.readInstallationId()).pin(userPin).oaid(BaseInfo.getOAID()).enableLog(OKLog.I).debug(false).useRemoteConfig(true).wsKeyHelper(new WSKeyHelper() { // from class: com.jingdong.common.utils.JMAUtils.3
            @Override // com.jd.stat.security.WSKeyHelper
            public String getPin() {
                try {
                    String pin = UserUtil.getWJLoginHelper().getPin();
                    if (OKLog.D) {
                        OKLog.d(JMAUtils.TAG, "getPin:" + pin);
                    }
                    return pin;
                } catch (Throwable th) {
                    if (OKLog.D) {
                        OKLog.d(JMAUtils.TAG, "getPin", th);
                        return "";
                    }
                    return "";
                }
            }

            @Override // com.jd.stat.security.WSKeyHelper
            public String getWsKey() {
                try {
                    String a2 = UserUtil.getWJLoginHelper().getA2();
                    if (OKLog.D) {
                        OKLog.d(JMAUtils.TAG, "getWsKey:" + a2);
                    }
                    return a2;
                } catch (Throwable th) {
                    if (OKLog.D) {
                        OKLog.d(JMAUtils.TAG, "getWsKey", th);
                        return "";
                    }
                    return "";
                }
            }
        }).privacyHelper(new PrivacyHelper() { // from class: com.jingdong.common.utils.JMAUtils.2
            @Override // com.jd.stat.security.PrivacyHelper
            public boolean isOpen() {
                return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication());
            }
        }).infoCollectHelper(new InfoCollectHelper() { // from class: com.jingdong.common.utils.JMAUtils.1
            @Override // com.jd.stat.security.InfoCollectHelper
            public boolean canCollect(int i2, String str) {
                if (OKLog.D) {
                    OKLog.d(JMAUtils.TAG, "InfoCollectHelper call: code:" + i2 + " string:" + str);
                }
                if (i2 == 1) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            return false;
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        SceneStatus hasLocationPermissionWithSceneV2 = PermissionHelper.hasLocationPermissionWithSceneV2(jSONObject.optString("sceneId", ""), jSONObject.optString("businessId", ""));
                        if (OKLog.D) {
                            OKLog.d(JMAUtils.TAG, "sceneStatus:" + hasLocationPermissionWithSceneV2);
                        }
                        return hasLocationPermissionWithSceneV2 == SceneStatus.HAS_ALL_PERMISSION;
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            OKLog.d(JMAUtils.TAG, th);
                        }
                    }
                } else if (i2 == -1 && OKLog.D) {
                    OKLog.d(JMAUtils.TAG, "new default scene " + str);
                }
                return false;
            }
        }).build(), new JmaCallback() { // from class: com.jingdong.common.utils.JMAUtils.4
            @Override // com.jd.stat.common.callback.JmaCallback
            public void onCheckAppIdResult(String str) {
            }
        });
        BotDetector.init(JdSdk.getInstance().getApplication());
    }

    public static void privacyOn() {
        try {
            String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("devicecode", readDeviceUUID);
            jSONObject.put("eventid", "PrivacyOn");
            JMA.forceRefresh(JdSdk.getInstance().getApplication(), jSONObject);
        } catch (Exception unused) {
        }
    }

    public static String getBlog(boolean z) {
        try {
            return BlogUtil.getBlog(LoginUserBase.getUserPin(), refer, z);
        } catch (Throwable unused) {
            return "";
        }
    }
}
