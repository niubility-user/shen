package com.meizu.cloud.pushsdk.handler.f;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.handler.e.j.e;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.b;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public abstract class a<T> implements com.meizu.cloud.pushsdk.handler.c {
    private com.meizu.cloud.pushsdk.handler.a a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private SparseArray<String> f15982c;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.b = context.getApplicationContext();
        this.a = aVar;
        SparseArray<String> sparseArray = new SparseArray<>();
        this.f15982c = sparseArray;
        sparseArray.put(2, "MESSAGE_TYPE_PUSH_SERVICE_V2");
        this.f15982c.put(4, "MESSAGE_TYPE_PUSH_SERVICE_V3");
        this.f15982c.put(16, "MESSAGE_TYPE_REGISTER");
        this.f15982c.put(32, "MESSAGE_TYPE_UNREGISTER");
        this.f15982c.put(8, "MESSAGE_TYPE_THROUGH");
        this.f15982c.put(64, "MESSAGE_TYPE_NOTIFICATION_CLICK");
        this.f15982c.put(128, "MESSAGE_TYPE_NOTIFICATION_DELETE");
        this.f15982c.put(256, "MESSAGE_TYPE_PUSH_SWITCH_STATUS");
        this.f15982c.put(512, "MESSAGE_TYPE_PUSH_REGISTER_STATUS");
        this.f15982c.put(2048, "MESSAGE_TYPE_PUSH_SUBTAGS_STATUS");
        this.f15982c.put(1024, "MESSAGE_TYPE_PUSH_UNREGISTER_STATUS");
        this.f15982c.put(4096, "MESSAGE_TYPE_PUSH_SUBALIAS_STATUS");
        this.f15982c.put(8192, "MESSAGE_TYPE_SCHEDULE_NOTIFICATION");
        this.f15982c.put(16384, "MESSAGE_TYPE_RECEIVE_NOTIFY_MESSAGE");
        this.f15982c.put(32768, "MESSAGE_TYPE_NOTIFICATION_STATE");
        this.f15982c.put(65536, "MESSAGE_TYPE_UPLOAD_FILE_LOG");
        this.f15982c.put(131072, "MESSAGE_TYPE_NOTIFICATION_ARRIVED");
        this.f15982c.put(262144, "MESSAGE_TYPE_NOTIFICATION_WITHDRAW");
        this.f15982c.put(524288, "MESSAGE_TYPE_BRIGHT_NOTIFICATION");
        this.f15982c.put(1048576, "MESSAGE_TYPE_NOTIFICATION_CLOSE");
    }

    private String c(int i2) {
        return this.f15982c.get(i2);
    }

    public static boolean j(Context context, String str) {
        try {
            return ((Boolean) Class.forName("com.meizu.cloud.utils.ProcessUtils").getDeclaredMethod("isRunningProcess", Context.class, String.class).invoke(null, context, str)).booleanValue();
        } catch (Exception e2) {
            DebugLogger.e("AbstractMessageHandler", "getDeviceId error " + e2.getMessage());
            return true;
        }
    }

    private boolean m(String str, MessageV3 messageV3, String str2) {
        if (TextUtils.isEmpty(str)) {
            DebugLogger.e("AbstractMessageHandler", "security check fail, public key is null");
            return false;
        }
        String a = com.meizu.cloud.pushsdk.util.c.a(str, str2);
        DebugLogger.i("AbstractMessageHandler", "decrypt sign: " + a);
        boolean e2 = com.meizu.cloud.pushsdk.handler.e.j.e.e(a, messageV3);
        DebugLogger.i("AbstractMessageHandler", "check public key result: " + e2);
        return e2;
    }

    private boolean r(String str, MessageV3 messageV3, String str2) {
        String str3;
        if (!TextUtils.isEmpty(str)) {
            str3 = "sa, public key not empty";
        } else if (!PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE.equals(str2)) {
            str3 = "sa, message not click method";
        } else if (b.u(t(), messageV3.getPackageName())) {
            b.i(t(), messageV3.getPackageName(), false);
            return true;
        } else {
            str3 = "sa, not first request";
        }
        DebugLogger.i("AbstractMessageHandler", str3);
        return false;
    }

    private String w() {
        String str = null;
        for (int i2 = 0; i2 < 2; i2++) {
            str = A();
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String A() {
        return new e.a((String) com.meizu.cloud.pushsdk.e.a.b(PushConstants.GET_PUBLIC_KEY).c().k().e()).a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String B(Intent intent) {
        return intent.getStringExtra("method");
    }

    protected int C(T t) {
        return 0;
    }

    protected abstract T D(Intent intent);

    /* JADX INFO: Access modifiers changed from: protected */
    public String E(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SERVICE_DEFAULT_PACKAGE_NAME);
        return TextUtils.isEmpty(stringExtra) ? t().getPackageName() : stringExtra;
    }

    protected void F(T t) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String G(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_TIMES_TAMP);
        DebugLogger.i("AbstractMessageHandler", "receive push timestamp from pushservice " + stringExtra);
        return TextUtils.isEmpty(stringExtra) ? String.valueOf(System.currentTimeMillis() / 1000) : stringExtra;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String H(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String I(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean J(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(PushConstants.MZ_PUSH_WHITE_LIST, false);
        DebugLogger.i("AbstractMessageHandler", "receive push whiteList from pushservice " + booleanExtra);
        return booleanExtra;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a7 A[ADDED_TO_REGION] */
    @Override // com.meizu.cloud.pushsdk.handler.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.content.Intent r7) {
        /*
            r6 = this;
            boolean r0 = r6.b(r7)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "current message Type "
            r0.append(r2)
            int r2 = r6.a()
            java.lang.String r2 = r6.c(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "AbstractMessageHandler"
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r0)
            java.lang.Object r0 = r6.D(r7)
            java.lang.String r7 = r6.B(r7)
            boolean r7 = r6.l(r0, r7)
            if (r7 != 0) goto L3a
            java.lang.String r7 = "invalid push message"
            com.meizu.cloud.pushinternal.DebugLogger.e(r2, r7)
            return r1
        L3a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r3 = "current Handler message "
            r7.append(r3)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
            r6.y(r0)
            int r7 = r6.C(r0)
            r3 = 1
            if (r7 == 0) goto L87
            if (r7 == r3) goto L80
            r4 = 2
            if (r7 == r4) goto L7d
            r4 = 3
            if (r7 == r4) goto L73
            r4 = 4
            if (r7 == r4) goto L6a
            r3 = 5
            if (r7 == r3) goto L67
            goto L85
        L67:
            java.lang.String r7 = "ad cannot show message"
            goto L82
        L6a:
            java.lang.String r7 = "bright notification"
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
            r6.g(r0)
            goto L7b
        L73:
            java.lang.String r7 = "schedule notification"
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
            r6.F(r0)
        L7b:
            r1 = 1
            goto L85
        L7d:
            java.lang.String r7 = "notification on time ,show message"
            goto L89
        L80:
            java.lang.String r7 = "expire notification, don't show message"
        L82:
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
        L85:
            r3 = 0
            goto L8d
        L87:
            java.lang.String r7 = "schedule send message off, send message directly"
        L89:
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
            r1 = 1
        L8d:
            boolean r7 = r6.p(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "can send message "
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r4)
            if (r1 == 0) goto Lba
            if (r3 == 0) goto Lba
            if (r7 == 0) goto Lba
            com.meizu.cloud.pushsdk.notification.c r7 = r6.z(r0)
            r6.h(r0, r7)
            r6.v(r0)
            java.lang.String r7 = "send message end "
            com.meizu.cloud.pushinternal.DebugLogger.i(r2, r7)
        Lba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.handler.f.a.a(android.content.Intent):boolean");
    }

    public String d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("launcher");
            if (jSONObject.has("pkg") && !TextUtils.isEmpty(jSONObject.getString("pkg"))) {
                return jSONObject.getString("pkg");
            }
        } catch (Exception unused) {
            DebugLogger.e("AbstractMessageHandler", "parse desk top json error");
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Context context, MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.handler.f.b.a e2;
        com.meizu.cloud.pushsdk.notification.model.a f2;
        if (messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage()) || (e2 = com.meizu.cloud.pushsdk.b.a(context).e()) == null || (f2 = com.meizu.cloud.pushsdk.notification.model.a.f(messageV3)) == null) {
            return;
        }
        e2.b(f2.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(MessageV3 messageV3) {
        if (!MinSdkChecker.isSupportSetDrawableSmallIcon()) {
            o(messageV3);
            return;
        }
        com.meizu.cloud.pushsdk.notification.model.a f2 = com.meizu.cloud.pushsdk.notification.model.a.f(messageV3);
        if (f2 != null) {
            DebugLogger.e("AbstractMessageHandler", "delete notifyId " + f2.a() + " notifyKey " + f2.h());
            if (TextUtils.isEmpty(f2.h())) {
                com.meizu.cloud.pushsdk.platform.c.b.b(t()).k(messageV3.getUploadDataPackageName(), f2.a());
            } else {
                com.meizu.cloud.pushsdk.platform.c.b.b(t()).e(messageV3.getUploadDataPackageName(), f2.h());
            }
        }
    }

    protected void g(T t) {
    }

    protected abstract void h(T t, com.meizu.cloud.pushsdk.notification.c cVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i(int i2, String str) {
        boolean z = true;
        if (i2 == 0) {
            z = b.y(t(), str);
        } else if (i2 == 1) {
            z = b.H(t(), str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i2 == 0 ? " canNotificationMessage " : " canThroughMessage ");
        sb.append(z);
        DebugLogger.i("AbstractMessageHandler", sb.toString());
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean k(MessageV3 messageV3, String str) {
        String c2 = com.meizu.cloud.pushsdk.handler.e.j.e.c(messageV3);
        if (TextUtils.isEmpty(c2)) {
            DebugLogger.i("AbstractMessageHandler", "message does not contain signature field");
            return false;
        }
        String z = b.z(t(), messageV3.getPackageName());
        DebugLogger.i("AbstractMessageHandler", "local public key is: " + z);
        if (r(z, messageV3, str)) {
            DebugLogger.i("AbstractMessageHandler", "message special approval no check");
            return true;
        } else if (m(z, messageV3, c2)) {
            DebugLogger.i("AbstractMessageHandler", "security check passed");
            return true;
        } else {
            String w = w();
            DebugLogger.i("AbstractMessageHandler", "network request public key: " + w);
            if (!m(w, messageV3, c2)) {
                DebugLogger.e("AbstractMessageHandler", "security check fail");
                return false;
            }
            b.x(t(), messageV3.getPackageName(), w);
            DebugLogger.i("AbstractMessageHandler", "security check passed");
            return true;
        }
    }

    protected boolean l(T t, String str) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.meizu.cloud.pushsdk.handler.a n() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void o(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.notification.model.a f2 = com.meizu.cloud.pushsdk.notification.model.a.f(messageV3);
        if (f2 != null) {
            DebugLogger.i("AbstractMessageHandler", "delete notifyKey " + f2.h() + " notifyId " + f2.a());
            if (TextUtils.isEmpty(f2.h())) {
                com.meizu.cloud.pushsdk.notification.g.b.i(t(), messageV3.getUploadDataPackageName(), f2.a());
            } else {
                com.meizu.cloud.pushsdk.notification.g.b.f(t(), messageV3.getUploadDataPackageName(), f2.h());
            }
        }
    }

    protected boolean p(T t) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean q(String str) {
        try {
            return t().getPackageName().equals(new JSONObject(str).getString("appId"));
        } catch (Exception unused) {
            DebugLogger.e("AbstractMessageHandler", "parse notification error");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long s(Intent intent) {
        long longExtra = intent.getLongExtra(PushConstants.MZ_PUSH_DELAYED_REPORT_MILLIS, 0L);
        DebugLogger.i("AbstractMessageHandler", "receive push delayedReportMillis from pushservice " + longExtra);
        return longExtra;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context t() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(MessageV3 messageV3) {
        if (messageV3 == null || messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) {
            if (!MinSdkChecker.isSupportSetDrawableSmallIcon()) {
                n().f(t(), MzPushMessage.fromMessageV3(messageV3));
            } else if (j(t(), messageV3.getUploadDataPackageName())) {
                DebugLogger.i("AbstractMessageHandler", "send notification arrived message to " + messageV3.getUploadDataPackageName());
                Intent intent = new Intent();
                if (MinSdkChecker.isSupportTransmitMessageValue(this.b, messageV3.getUploadDataPackageName())) {
                    intent.putExtra(PushConstants.MZ_MESSAGE_VALUE, com.meizu.cloud.pushsdk.handler.d.b(messageV3));
                } else {
                    intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
                }
                intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_ARRIVED);
                MzSystemUtils.sendMessageFromBroadcast(t(), intent, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getUploadDataPackageName());
            }
        }
    }

    protected void v(T t) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String x(Intent intent) {
        String stringExtra = intent != null ? intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY) : null;
        if (TextUtils.isEmpty(stringExtra)) {
            String a = com.meizu.cloud.pushsdk.d.c.a(t());
            DebugLogger.e("AbstractMessageHandler", "force get deviceId " + a);
            return a;
        }
        return stringExtra;
    }

    protected void y(T t) {
    }

    protected com.meizu.cloud.pushsdk.notification.c z(T t) {
        return null;
    }
}
