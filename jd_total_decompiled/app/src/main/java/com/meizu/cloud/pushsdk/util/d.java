package com.meizu.cloud.pushsdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.f.d.b;
import com.meizu.cloud.pushsdk.f.g.e;
import com.meizu.cloud.pushsdk.handler.e.j.d;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class d {

    /* loaded from: classes14.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f16037g;

        /* renamed from: h */
        final /* synthetic */ Intent f16038h;

        /* renamed from: i */
        final /* synthetic */ long f16039i;

        /* renamed from: j */
        final /* synthetic */ boolean f16040j;

        /* renamed from: k */
        final /* synthetic */ Map f16041k;

        a(Context context, Intent intent, long j2, boolean z, Map map) {
            this.f16037g = context;
            this.f16038h = intent;
            this.f16039i = j2;
            this.f16040j = z;
            this.f16041k = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f16037g.startService(this.f16038h);
                DebugLogger.i("UxIPUtils", "delayed " + this.f16039i + " ms start tracker data in mz_tracker process " + this.f16038h.getStringExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA));
            } catch (Exception e2) {
                e2.printStackTrace();
                DebugLogger.e("UxIPUtils", "delayed startRemotePushTracker error " + e2.getMessage());
                d.l(this.f16037g, this.f16040j, (String) this.f16041k.get("en"), this.f16041k);
            }
        }
    }

    @SuppressLint({"QueryPermissionsNeeded"})
    private static Intent a(Context context, Map<String, String> map) {
        String str;
        String str2;
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(PushConstants.MZ_PUSH_TRACKER_SERVICE_ACTION), 0);
        if (queryIntentServices != null) {
            Iterator<ResolveInfo> it = queryIntentServices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = null;
                    str2 = null;
                    break;
                }
                ResolveInfo next = it.next();
                if (PushConstants.PUSH_PACKAGE_NAME.equals(next.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = next.serviceInfo;
                    str2 = serviceInfo.packageName;
                    str = serviceInfo.name;
                    break;
                }
            }
            if (TextUtils.isEmpty(str) && queryIntentServices.size() > 0) {
                str2 = queryIntentServices.get(0).serviceInfo.packageName;
                str = queryIntentServices.get(0).serviceInfo.name;
            }
        } else {
            str = null;
            str2 = null;
        }
        DebugLogger.i("UxIPUtils", "current process packageName " + str2);
        if (!TextUtils.isEmpty(str)) {
            try {
                String jSONObject = e.e(map).toString();
                Intent intent = new Intent();
                intent.setPackage(str2);
                intent.setAction(PushConstants.MZ_PUSH_TRACKER_SERVICE_ACTION);
                intent.putExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA, jSONObject);
                return intent;
            } catch (Exception e2) {
                e2.printStackTrace();
                DebugLogger.e("UxIPUtils", "getRemotePushTrackerIntent error " + e2.getMessage());
            }
        }
        return null;
    }

    public static com.meizu.cloud.pushsdk.handler.e.j.d b(String str) {
        String str2;
        com.meizu.cloud.pushsdk.handler.e.j.d dVar = new com.meizu.cloud.pushsdk.handler.e.j.d();
        if (TextUtils.isEmpty(str)) {
            str2 = "the platformExtra is empty";
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.has(PushConstants.TASK_ID) ? jSONObject.getString(PushConstants.TASK_ID) : null;
                String string2 = jSONObject.has(PushConstants.SEQ_ID) ? jSONObject.getString(PushConstants.SEQ_ID) : null;
                String string3 = jSONObject.has(PushConstants.PUSH_TIMESTAMP) ? jSONObject.getString(PushConstants.PUSH_TIMESTAMP) : null;
                String string4 = jSONObject.has(PushConstants.DEVICE_ID) ? jSONObject.getString(PushConstants.DEVICE_ID) : null;
                d.a a2 = com.meizu.cloud.pushsdk.handler.e.j.d.a();
                a2.h(string);
                a2.a(string4);
                a2.d(string3);
                a2.f(string2);
                return a2.b();
            } catch (Exception unused) {
                str2 = "the platformExtra parse error";
            }
        }
        DebugLogger.e("UxIPUtils", str2);
        return dVar;
    }

    public static String c(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
        if (TextUtils.isEmpty(stringExtra)) {
            try {
                MPushMessage mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
                return mPushMessage != null ? mPushMessage.getTaskId() : stringExtra;
            } catch (Exception e2) {
                DebugLogger.e("UxIPUtils", "parse MessageV2 error " + e2.getMessage());
                return "no push platform task";
            }
        }
        return stringExtra;
    }

    public static void d(Context context, Intent intent, String str, int i2) {
        e(context, intent, PushManager.TAG, str, i2);
    }

    public static void e(Context context, Intent intent, String str, String str2, int i2) {
        if (TextUtils.isEmpty(c(intent))) {
            return;
        }
        h(context, context.getPackageName(), intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), c(intent), str, str2, i2);
    }

    public static void f(Context context, String str, int i2, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        h(context, context.getPackageName(), str3, str2, PushManager.TAG, str, i2);
    }

    public static void g(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "acsm", str5);
    }

    public static void h(Context context, String str, String str2, String str3, String str4, String str5, int i2) {
        HashMap hashMap = new HashMap(8);
        hashMap.put(NotificationMessageSummary.TASK_ID, str3);
        hashMap.put("deviceId", str2);
        hashMap.put(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("package_name", str);
        hashMap.put("pushsdk_version", str4);
        hashMap.put("push_info", str5);
        hashMap.put("push_info_type", String.valueOf(i2));
        l(context, false, "notification_service_message", hashMap);
    }

    public static void i(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, false, str, str2, str3, str4, "fspm", str5, j2);
    }

    public static void j(Context context, boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        k(context, z, str, str2, str3, str4, str5, str6, 0L);
    }

    public static void k(Context context, boolean z, String str, String str2, String str3, String str4, String str5, String str6, long j2) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("en", str5);
        hashMap.put("ti", str3);
        hashMap.put(AppIconSetting.DEFAULT_LARGE_ICON, str2);
        hashMap.put("fdId", com.meizu.cloud.pushsdk.d.c.b(context));
        if (TextUtils.isEmpty(str6)) {
            str6 = String.valueOf(System.currentTimeMillis() / 1000);
        }
        hashMap.put("ts", str6);
        hashMap.put("pn", str);
        hashMap.put("pv", PushManager.TAG);
        hashMap.put("nm", String.valueOf(System.currentTimeMillis() / 1000));
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("si", str4);
        }
        if (n(context, hashMap, z, j2)) {
            return;
        }
        l(context, z, str5, hashMap);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [com.meizu.cloud.pushsdk.f.d.b$c] */
    public static void l(Context context, boolean z, String str, Map<String, String> map) {
        DebugLogger.e("UxIPUtils", "onLogEvent eventName [" + str + "] properties = " + map);
        if ("notification_service_message".equals(str)) {
            return;
        }
        com.meizu.cloud.pushsdk.f.a.a(context, null, null).d(((b.c) com.meizu.cloud.pushsdk.f.d.b.e().l(str).f(1).b(Long.valueOf(map.get("ts")).longValue())).j(map.get("nm") != null ? map.get("nm") : String.valueOf(System.currentTimeMillis() / 1000)).g(map.get(AppIconSetting.DEFAULT_LARGE_ICON)).n(map.get("fdId")).r(map.get("pn")).t(map.get("pv")).x(map.get("ti")).v(TextUtils.isEmpty(map.get("si")) ? "" : map.get("si")).p(String.valueOf(b.v(context, map.get("pn")))).k(), z);
    }

    private static boolean m(Context context, Intent intent, boolean z, Map<String, String> map, long j2) {
        String str;
        if (intent == null) {
            str = "startRemotePushTracker error intent is null";
        } else if (j2 != 0) {
            new Handler(context.getMainLooper()).postDelayed(new a(context, intent, j2, z, map), j2);
            return true;
        } else {
            try {
                context.startService(intent);
                DebugLogger.i("UxIPUtils", "immediately start tracker data in mz_tracker process " + intent.getStringExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA));
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                str = "startRemotePushTracker error " + e2.getMessage();
            }
        }
        DebugLogger.e("UxIPUtils", str);
        return false;
    }

    private static boolean n(Context context, Map<String, String> map, boolean z, long j2) {
        return m(context, a(context, map), z, map, j2);
    }

    public static void o(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "acce", str5);
    }

    public static void p(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, false, str, str2, str3, str4, "npm", str5, j2);
    }

    public static void q(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "cpm", str5);
    }

    public static void r(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, false, str, str2, str3, str4, "nspm", str5, j2);
    }

    public static void s(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "dpm", str5);
    }

    public static void t(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, false, str, str2, str3, str4, "rpe", str5, j2);
    }

    public static void u(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "iopm", str5);
    }

    public static void v(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, false, str, str2, str3, str4, "sipm", str5, j2);
    }

    public static void w(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "ipm", str5);
    }

    public static void x(Context context, String str, String str2, String str3, String str4, String str5, long j2) {
        k(context, true, str, str2, str3, str4, "spm", str5, j2);
    }

    public static void y(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, false, str, str2, str3, str4, "rpe", str5);
    }

    public static void z(Context context, String str, String str2, String str3, String str4, String str5) {
        j(context, true, str, str2, str3, str4, "rpe", str5);
    }
}
