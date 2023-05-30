package com.meizu.cloud.pushsdk.platform.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public abstract class c<T extends BasicPushStatus> {
    private ScheduledExecutorService a;
    protected final Context b;

    /* renamed from: c  reason: collision with root package name */
    protected String f16019c;
    protected String d;

    /* renamed from: e  reason: collision with root package name */
    protected String f16020e;

    /* renamed from: f  reason: collision with root package name */
    protected final com.meizu.cloud.pushsdk.platform.c.a f16021f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f16022g = true;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f16023h = true;

    /* renamed from: i  reason: collision with root package name */
    private String f16024i = null;

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.p();
        }
    }

    public c(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this.a = scheduledExecutorService;
        this.b = context;
        this.f16019c = str;
        this.d = str2;
        this.f16021f = aVar;
    }

    private boolean f(int i2) {
        return i2 >= 110000 && i2 <= 200000;
    }

    private boolean g(T t) {
        int intValue = Integer.valueOf(t.getCode()).intValue();
        return (intValue > 200 && intValue < 600) || (intValue > 1000 && intValue < 2000) || intValue == 0;
    }

    private boolean t() {
        return this.f16023h && !this.b.getPackageName().equals(this.f16024i);
    }

    protected abstract T a();

    @SuppressLint({"QueryPermissionsNeeded"})
    protected String b(Context context, String str) {
        String str2 = null;
        if (!TextUtils.isEmpty(str)) {
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(str), 0);
            if (queryIntentServices != null) {
                Iterator<ResolveInfo> it = queryIntentServices.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ResolveInfo next = it.next();
                    if (PushConstants.PUSH_PACKAGE_NAME.equals(next.serviceInfo.packageName)) {
                        ServiceInfo serviceInfo = next.serviceInfo;
                        this.f16024i = serviceInfo.packageName;
                        str2 = serviceInfo.name;
                        break;
                    }
                }
                if (TextUtils.isEmpty(str2) && queryIntentServices.size() > 0) {
                    this.f16024i = queryIntentServices.get(0).serviceInfo.packageName;
                    str2 = queryIntentServices.get(0).serviceInfo.name;
                }
            }
        }
        DebugLogger.i("Strategy", "current process packageName " + this.f16024i);
        return str2;
    }

    protected void c(Intent intent) {
        try {
            intent.setPackage(this.f16024i);
            intent.setAction(PushConstants.MZ_PUSH_MANAGER_SERVICE_ACTION);
            this.b.startService(intent);
        } catch (Exception e2) {
            DebugLogger.e("Strategy", "start RemoteService error " + e2.getMessage());
        }
    }

    public void d(String str) {
        this.f16019c = str;
    }

    public void e(boolean z) {
        this.f16022g = z;
    }

    protected abstract void h(T t);

    public void i(String str) {
        this.d = str;
    }

    protected boolean j() {
        return 2 == s() || 32 == s();
    }

    protected abstract T k();

    public void l(String str) {
        this.f16020e = str;
    }

    protected abstract boolean m();

    protected abstract T n();

    public boolean o() {
        ScheduledExecutorService scheduledExecutorService = this.a;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(new a());
            return true;
        }
        return p();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean p() {
        T t;
        String str;
        if (m()) {
            if (u()) {
                DebugLogger.i("Strategy", "send message to remote service");
                if (j()) {
                    t = null;
                } else {
                    t = k();
                    if (t != null) {
                        DebugLogger.e("Strategy", "local response " + t);
                        h(t);
                    }
                }
                Intent q = q();
                if (q != null) {
                    c(q);
                }
                Intent[] r = r();
                if (r != null) {
                    DebugLogger.e("Strategy", "send sendRpcRequests length " + r.length);
                    for (Intent intent : r) {
                        c(intent);
                    }
                }
                com.meizu.cloud.pushsdk.a.a(this.b);
            } else {
                t = n();
                DebugLogger.i("Strategy", "real response status " + t);
                if (t != null) {
                    if (j() && "20000".equals(t.getCode())) {
                        return true;
                    }
                    if (!t()) {
                        String code = t.getCode();
                        if (TextUtils.isEmpty(code)) {
                            code = "0";
                        }
                        if (BasicPushStatus.SUCCESS_CODE.equals(t.getCode())) {
                            h(t);
                        }
                        int intValue = Integer.valueOf(code).intValue();
                        str = f(intValue) ? "service error so notify pushManager invoker code=" + intValue + " message " + t.getMessage() : "response all request in local app";
                    }
                    DebugLogger.e("Strategy", str);
                }
            }
            if (t == null) {
                DebugLogger.e("Strategy", "current status code " + t.getCode());
                return true ^ g(t);
            }
            return true;
        }
        DebugLogger.e("Strategy", "Missing required parameters");
        t = a();
        h(t);
        if (t == null) {
        }
    }

    protected abstract Intent q();

    protected Intent[] r() {
        return null;
    }

    protected abstract int s();

    protected boolean u() {
        return this.f16023h && this.f16022g && !TextUtils.isEmpty(b(this.b, PushConstants.MZ_PUSH_MANAGER_SERVICE_ACTION));
    }
}
