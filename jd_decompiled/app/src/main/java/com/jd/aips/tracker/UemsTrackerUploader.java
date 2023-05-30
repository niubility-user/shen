package com.jd.aips.tracker;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class UemsTrackerUploader {
    private static final UemsWorkerThread a;

    static {
        UemsWorkerThread uemsWorkerThread = new UemsWorkerThread("uems_tracker_thread");
        a = uemsWorkerThread;
        uemsWorkerThread.start();
    }

    private UemsTrackerUploader() {
    }

    static String a(Context context, String str, JSONObject jSONObject, String str2, String str3, String str4) {
        JSONObject a2;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("logId", str2);
            jSONObject2.put("appPackage", context.getPackageName());
            jSONObject2.put(l4.f16791e, str3);
            jSONObject2.put("buildVersion", context.getApplicationInfo().targetSdkVersion + "");
            jSONObject2.put("appVersion", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            jSONObject2.put("eventId", str);
            jSONObject2.put("trackId", System.currentTimeMillis() + "");
            jSONObject2.put(Constants.PARAM_PLATFORM, "android");
            if (TextUtils.isEmpty(jSONObject.getString("token")) && (a2 = UemsTrackManger.a(context)) != null) {
                jSONObject2.put("deviceInfo", a2.toString());
            }
            jSONObject2.put("kvs", jSONObject.toString());
            jSONObject2.put("time", System.currentTimeMillis());
            jSONObject2.put("action_elapsedRealtime", SystemClock.elapsedRealtime());
            jSONObject2.put("sourceBiz", str4);
            jSONObject2.put("terminalSource", UemsTrackManger.a());
        } catch (Throwable unused) {
        }
        return jSONObject2.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(final Context context, final String str, final JSONObject jSONObject, final String str2, final String str3, final String str4) {
        a.a(new Runnable() { // from class: com.jd.aips.tracker.UemsTrackerUploader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context context2 = context;
                    UemsTrackerUploader.a(context2, UemsTrackerUploader.a(context2, str, jSONObject, str2, str3, str4));
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(5:58|59|(2:61|(9:63|(1:42)|43|45|46|47|48|49|50)(1:64))|66|(0)(0))|45|46|47|48|49|50) */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0137, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0138, code lost:
        r8.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f9 A[Catch: Exception -> 0x013c, TRY_ENTER, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0105 A[Catch: Exception -> 0x013c, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010b A[Catch: Exception -> 0x013c, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void a(android.content.Context r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.tracker.UemsTrackerUploader.a(android.content.Context, java.lang.String):void");
    }
}
