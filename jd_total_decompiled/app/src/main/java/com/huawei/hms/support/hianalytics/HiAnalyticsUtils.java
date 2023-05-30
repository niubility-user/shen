package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.util.HiAnalyticTools;
import com.huawei.hms.hatool.HmsHiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.AnalyticsSwitchHolder;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class HiAnalyticsUtils {

    /* renamed from: c  reason: collision with root package name */
    private static final Object f1491c = new Object();
    private static final Object d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static HiAnalyticsUtils f1492e;
    private int a = 0;
    private final boolean b = com.huawei.hms.stats.c.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f1493c;

        a(Context context, String str, Map map) {
            this.a = context;
            this.b = str;
            this.f1493c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onEvent(this.a, this.b, this.f1493c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class b implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f1494c;

        b(Context context, String str, String str2) {
            this.a = context;
            this.b = str;
            this.f1494c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onEvent2(this.a, this.b, this.f1494c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class c implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f1495c;

        c(Context context, String str, Map map) {
            this.a = context;
            this.b = str;
            this.f1495c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onNewEvent(this.a, this.b, this.f1495c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class d implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f1496c;
        final /* synthetic */ int d;

        d(Context context, String str, Map map, int i2) {
            this.a = context;
            this.b = str;
            this.f1496c = map;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onNewEvent(this.a, this.b, this.f1496c, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class e implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f1498c;

        e(Context context, String str, Map map) {
            this.a = context;
            this.b = str;
            this.f1498c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onReport(this.a, this.b, this.f1498c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class f implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f1499c;
        final /* synthetic */ int d;

        f(Context context, String str, Map map, int i2) {
            this.a = context;
            this.b = str;
            this.f1499c = map;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onReport(this.a, this.b, this.f1499c, this.d);
        }
    }

    private HiAnalyticsUtils() {
    }

    private static LinkedHashMap<String, String> a(Map<String, String> map) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    private void b(Context context) {
        synchronized (d) {
            int i2 = this.a;
            if (i2 < 60) {
                this.a = i2 + 1;
            } else {
                this.a = 0;
                if (!this.b) {
                    HmsHiAnalyticsUtils.onReport();
                } else {
                    com.huawei.hms.stats.b.a(context, 0);
                    com.huawei.hms.stats.b.a(context, 1);
                }
            }
        }
    }

    private void c(Context context, String str, Map map) {
        try {
            com.huawei.hms.stats.a.c().a(new e(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnReportToCache> failed. " + th.getMessage());
        }
    }

    public static HiAnalyticsUtils getInstance() {
        HiAnalyticsUtils hiAnalyticsUtils;
        synchronized (f1491c) {
            if (f1492e == null) {
                f1492e = new HiAnalyticsUtils();
            }
            hiAnalyticsUtils = f1492e;
        }
        return hiAnalyticsUtils;
    }

    public static String versionCodeToName(String str) {
        if (!TextUtils.isEmpty(str) && (str.length() == 8 || str.length() == 9)) {
            try {
                Integer.parseInt(str);
                return Integer.parseInt(str.substring(0, str.length() - 7)) + OrderISVUtil.MONEY_DECIMAL + Integer.parseInt(str.substring(str.length() - 7, str.length() - 5)) + OrderISVUtil.MONEY_DECIMAL + Integer.parseInt(str.substring(str.length() - 5, str.length() - 3)) + OrderISVUtil.MONEY_DECIMAL + Integer.parseInt(str.substring(str.length() - 3));
            } catch (NumberFormatException unused) {
            }
        }
        return "";
    }

    public void enableLog(Context context) {
        HMSLog.i("HiAnalyticsUtils", "Enable Log");
        if (!this.b) {
            HmsHiAnalyticsUtils.enableLog();
        } else {
            HiAnalyticTools.enableLog(context);
        }
    }

    public boolean getInitFlag() {
        if (!this.b) {
            return HmsHiAnalyticsUtils.getInitFlag();
        }
        return HiAnalyticsManager.getInitFlag(HiAnalyticsConstant.HA_SERVICE_TAG);
    }

    public int getOobeAnalyticsState(Context context) {
        if (context == null) {
            return 0;
        }
        int a2 = a(context);
        if (a2 == 1) {
            return a2;
        }
        Bundle bundle = new Bundle();
        bundle.putString("hms_cp_bundle_key", "content://com.huawei.hms.contentprovider/com.huawei.hms.privacy.HmsAnalyticsStateProvider");
        try {
            Bundle call = context.getApplicationContext().getContentResolver().call(Uri.parse("content://com.huawei.hms.contentprovider"), "getAnalyticsState", (String) null, bundle);
            if (call != null) {
                a2 = call.getInt("SWITCH_IS_CHECKED");
                HMSLog.i("HiAnalyticsUtils", "get hms analyticsOobe state " + a2);
                return a2;
            }
            return a2;
        } catch (IllegalArgumentException unused) {
            HMSLog.i("HiAnalyticsUtils", "getOobeAnalyticsState IllegalArgumentException ");
            return a2;
        } catch (SecurityException unused2) {
            HMSLog.i("HiAnalyticsUtils", "getOobeAnalyticsState SecurityException ");
            return a2;
        } catch (Exception unused3) {
            HMSLog.i("HiAnalyticsUtils", "getOobeAnalyticsState Exception ");
            return a2;
        }
    }

    public boolean hasError(Context context) {
        return AnalyticsSwitchHolder.isAnalyticsDisabled(context);
    }

    public void onBuoyEvent(Context context, String str, String str2) {
        onEvent2(context, str, str2);
    }

    public void onEvent(Context context, String str, Map<String, String> map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (a(initFlag, andRefreshAnalyticsState != 2, map)) {
                a(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.b) {
                    HmsHiAnalyticsUtils.onEvent(0, str, a(map));
                    HmsHiAnalyticsUtils.onEvent(1, str, a(map));
                } else {
                    com.huawei.hms.stats.b.a(context, 0, str, a(map));
                    com.huawei.hms.stats.b.a(context, 1, str, a(map));
                }
                b(context);
                return;
            }
            return;
        }
        HMSLog.e("HiAnalyticsUtils", "<onEvent> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void onEvent2(Context context, String str, String str2) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (context == null) {
            HMSLog.e("HiAnalyticsUtils", "<onEvent2> context is null, state: " + andRefreshAnalyticsState);
            return;
        }
        boolean initFlag = getInitFlag();
        if (!initFlag && andRefreshAnalyticsState != 2 && a(str2)) {
            a(context, str, str2);
        }
        if (andRefreshAnalyticsState == 1 && initFlag) {
            if (!this.b) {
                HmsHiAnalyticsUtils.onEvent(context, str, str2);
            } else {
                com.huawei.hms.stats.b.a(context, str, str2);
            }
        }
    }

    public void onNewEvent(Context context, String str, Map map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (a(initFlag, andRefreshAnalyticsState != 2, map)) {
                b(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.b) {
                    HmsHiAnalyticsUtils.onEvent(0, str, a(map));
                    HmsHiAnalyticsUtils.onEvent(1, str, a(map));
                } else {
                    com.huawei.hms.stats.b.a(context, 0, str, a(map));
                    com.huawei.hms.stats.b.a(context, 1, str, a(map));
                }
                b(context);
                return;
            }
            return;
        }
        HMSLog.e("HiAnalyticsUtils", "<onNewEvent> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void onReport(Context context, String str, Map map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (a(initFlag, andRefreshAnalyticsState != 2, map)) {
                c(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.b) {
                    HmsHiAnalyticsUtils.onStreamEvent(0, str, a(map));
                    HmsHiAnalyticsUtils.onStreamEvent(1, str, a(map));
                    return;
                }
                com.huawei.hms.stats.b.b(context, 0, str, a(map));
                com.huawei.hms.stats.b.b(context, 1, str, a(map));
                return;
            }
            return;
        }
        HMSLog.e("HiAnalyticsUtils", "<onReport> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void enableLog() {
        HMSLog.i("HiAnalyticsUtils", "Enable Log");
        if (!this.b) {
            HmsHiAnalyticsUtils.enableLog();
        } else {
            HMSLog.i("HiAnalyticsUtils", "cp needs to pass in the context, this method is not supported");
        }
    }

    private int a(Context context) {
        int i2 = 0;
        try {
            i2 = Settings.Secure.getInt(context.getContentResolver(), "hw_app_analytics_state");
            HMSLog.i("HiAnalyticsUtils", "getOobeStateForSettings value is " + i2);
            return i2;
        } catch (Settings.SettingNotFoundException e2) {
            HMSLog.i("HiAnalyticsUtils", "Settings.SettingNotFoundException " + e2.getMessage());
            return i2;
        }
    }

    private boolean a(boolean z, boolean z2, Map<?, ?> map) {
        return !z && z2 && b(map);
    }

    private boolean b(Map<?, ?> map) {
        try {
            Iterator<?> it = map.values().iterator();
            long j2 = 0;
            while (it.hasNext()) {
                if (it.next() instanceof String) {
                    j2 += ((String) r3).getBytes(Charset.forName("UTF-8")).length;
                }
            }
            return j2 <= 512;
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<isValidSize map> Exception: " + th.getMessage());
            return false;
        }
    }

    private boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            return str.getBytes(Charset.forName("UTF-8")).length <= 512;
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<isValidSize String> Exception: " + th.getMessage());
            return false;
        }
    }

    public void onReport(Context context, String str, Map map, int i2) {
        if (i2 != 0 && i2 != 1) {
            HMSLog.e("HiAnalyticsUtils", "<onReport with type> Data reporting type is not supported");
            return;
        }
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (a(initFlag, andRefreshAnalyticsState != 2, map)) {
                b(context, str, map, i2);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.b) {
                    HmsHiAnalyticsUtils.onStreamEvent(i2, str, a(map));
                    return;
                } else {
                    com.huawei.hms.stats.b.b(context, i2, str, a(map));
                    return;
                }
            }
            return;
        }
        HMSLog.e("HiAnalyticsUtils", "<onReport with type> map or context is null, state: " + andRefreshAnalyticsState);
    }

    private void a(Context context, String str, Map<String, String> map) {
        try {
            com.huawei.hms.stats.a.c().a(new a(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnEventToCache> failed. " + th.getMessage());
        }
    }

    public void onNewEvent(Context context, String str, Map map, int i2) {
        if (i2 != 0 && i2 != 1) {
            HMSLog.e("HiAnalyticsUtils", "<onNewEvent with type> Data reporting type is not supported");
            return;
        }
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (a(initFlag, andRefreshAnalyticsState != 2, map)) {
                a(context, str, map, i2);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.b) {
                    HmsHiAnalyticsUtils.onEvent(i2, str, a(map));
                } else {
                    com.huawei.hms.stats.b.a(context, i2, str, a(map));
                }
                b(context);
                return;
            }
            return;
        }
        HMSLog.e("HiAnalyticsUtils", "<onNewEvent with type> map or context is null, state: " + andRefreshAnalyticsState);
    }

    private void b(Context context, String str, Map map) {
        try {
            com.huawei.hms.stats.a.c().a(new c(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnNewEventToCache> failed. " + th.getMessage());
        }
    }

    private void a(Context context, String str, String str2) {
        try {
            com.huawei.hms.stats.a.c().a(new b(context.getApplicationContext(), str, str2));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnEvent2ToCache> Failed. " + th.getMessage());
        }
    }

    private void b(Context context, String str, Map map, int i2) {
        try {
            com.huawei.hms.stats.a.c().a(new f(context.getApplicationContext(), str, map, i2));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnReportToCache with type> failed. " + th.getMessage());
        }
    }

    private void a(Context context, String str, Map map, int i2) {
        try {
            com.huawei.hms.stats.a.c().a(new d(context.getApplicationContext(), str, map, i2));
        } catch (Throwable th) {
            HMSLog.e("HiAnalyticsUtils", "<addOnNewEventToCache with type> failed. " + th.getMessage());
        }
    }
}
