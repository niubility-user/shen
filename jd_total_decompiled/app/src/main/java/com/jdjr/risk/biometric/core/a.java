package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.jdjr.risk.biometric.c.h;
import com.jdjr.risk.device.c.q;
import com.jdjr.risk.util.a.f;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    private static volatile a a;

    /* renamed from: c  reason: collision with root package name */
    private static ReentrantLock f7293c = new ReentrantLock();
    private ExecutorService b = null;

    private a() {
        a();
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                    BiometricManager.getInstance().a().g(context);
                    q.a(context);
                }
            }
        }
        return a;
    }

    private void a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new f(), new ThreadPoolExecutor.DiscardPolicy());
        this.b = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, String str2, Bundle bundle) {
        a(context, str, str2, bundle, new LorasHttpCallback() { // from class: com.jdjr.risk.biometric.core.a.2
            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInCurentThread(int i2, String str3) {
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInNetThread(int i2, String str3) {
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onSuccess(String str3) {
            }
        });
    }

    private void a(final Context context, final String str, final String str2, final Bundle bundle, final LorasHttpCallback lorasHttpCallback) {
        String c2 = BiometricManager.getInstance().a().c(context);
        if (TextUtils.isEmpty(c2)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.b.execute(new Runnable() { // from class: com.jdjr.risk.biometric.core.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        e.a.set(bundle);
                        a.this.b(context, str, str2, lorasHttpCallback);
                        e.a.remove();
                    }
                });
                return;
            } else {
                b(context, str, str2, lorasHttpCallback);
                return;
            }
        }
        final Map<String, Object> b = b.a(context).b(context, str);
        if (b == null) {
            lorasHttpCallback.onSuccess(c2);
            com.jdjr.risk.device.a.b.a().a(context, str, str2);
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.execute(new Runnable() { // from class: com.jdjr.risk.biometric.core.a.4
                @Override // java.lang.Runnable
                public void run() {
                    e.a.set(bundle);
                    a.this.a(context, str, str2, (JSONObject) b.get("policy"), ((Integer) b.get("version")).intValue(), lorasHttpCallback);
                    e.a.remove();
                }
            });
        } else {
            a(context, str, str2, (JSONObject) b.get("policy"), ((Integer) b.get("version")).intValue(), lorasHttpCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, String str2, JSONObject jSONObject, int i2, LorasHttpCallback lorasHttpCallback) {
        try {
            b.a(context).a(context, i2);
            lorasHttpCallback.onSuccess(com.jdjr.risk.biometric.c.f.a(context, str, str2, jSONObject) == 900 ? BiometricManager.getInstance().a().c(context) : BiometricManager.getInstance().a().b(context));
            b.a(context).b(context, i2);
            com.jdjr.risk.device.a.b.a().a(context, str, str2);
        } catch (Throwable unused) {
            lorasHttpCallback.onSuccess(BiometricManager.getInstance().a().b(context));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str, String str2, LorasHttpCallback lorasHttpCallback) {
        String c2;
        e a2;
        ReentrantLock reentrantLock;
        try {
            if (!f7293c.tryLock(600L, TimeUnit.MILLISECONDS)) {
                c2 = BiometricManager.getInstance().a().c(context);
                if (TextUtils.isEmpty(c2)) {
                    a2 = BiometricManager.getInstance().a();
                    c2 = a2.a(context);
                }
                lorasHttpCallback.onSuccess(c2);
                com.jdjr.risk.device.a.b.a().a(context, str, str2);
                return;
            }
            c2 = BiometricManager.getInstance().a().c(context);
            if (TextUtils.isEmpty(c2)) {
                if (h.a(context, str, str2) == 900) {
                    c2 = BiometricManager.getInstance().a().c(context);
                    if (TextUtils.isEmpty(c2)) {
                        f7293c.unlock();
                        a2 = BiometricManager.getInstance().a();
                    } else {
                        reentrantLock = f7293c;
                    }
                } else {
                    f7293c.unlock();
                    a2 = BiometricManager.getInstance().a();
                }
                c2 = a2.a(context);
                lorasHttpCallback.onSuccess(c2);
                com.jdjr.risk.device.a.b.a().a(context, str, str2);
                return;
            }
            reentrantLock = f7293c;
            reentrantLock.unlock();
            lorasHttpCallback.onSuccess(c2);
            com.jdjr.risk.device.a.b.a().a(context, str, str2);
            return;
        } catch (Throwable unused) {
            lorasHttpCallback.onSuccess(BiometricManager.getInstance().a().a(context));
        }
        lorasHttpCallback.onSuccess(BiometricManager.getInstance().a().a(context));
    }

    private Bundle c(Context context) {
        Bundle bundle = new Bundle();
        try {
            e a2 = BiometricManager.getInstance().a();
            bundle.putBoolean("agreedPrivacy", BaseInfo.isAgreedPrivacy());
            bundle.putBoolean("tokenExist", a2.d(context));
            bundle.putBoolean("cuid", true);
            if (a2.b() || !BaseInfo.isAgreedPrivacy()) {
                return bundle;
            }
            bundle.putBoolean("cuid", false);
            a2.a(true);
            return bundle;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str, String str2) {
        try {
            Bundle c2 = c(context);
            if (c2 != null) {
                e.a.set(c2);
                a(context, str, str2, c2);
                e.a.remove();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str, String str2, LorasHttpCallback lorasHttpCallback) {
        try {
            Bundle c2 = c(context);
            if (c2 != null) {
                e.a.set(c2);
                a(context, str, str2, c2, lorasHttpCallback);
                e.a.remove();
            } else {
                lorasHttpCallback.onSuccess(BiometricManager.getInstance().a().a(context));
                com.jdjr.risk.device.a.b.a().a(context, str, str2);
            }
        } catch (Throwable unused) {
            lorasHttpCallback.onSuccess(BiometricManager.getInstance().a().a(context));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b(Context context) {
        e.a.set(c(context));
        return BiometricManager.getInstance().a().f(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b(final Context context, final String str, final String str2) {
        try {
            final Bundle c2 = c(context);
            if (c2 == null) {
                String a2 = BiometricManager.getInstance().a().a(context);
                com.jdjr.risk.device.a.b.a().a(context, str, str2);
                return a2;
            }
            e.a.set(c2);
            String f2 = TextUtils.equals(str, com.jd.sec.a.c()) ? BiometricManager.getInstance().a().f(context) : BiometricManager.getInstance().a().c(context);
            if (TextUtils.isEmpty(f2)) {
                String a3 = BiometricManager.getInstance().a().a(context);
                this.b.execute(new Runnable() { // from class: com.jdjr.risk.biometric.core.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            e.a.set(c2);
                            a.this.a(context, str, str2, c2);
                            e.a.remove();
                        } catch (Throwable unused) {
                        }
                    }
                });
                f2 = a3;
            } else {
                com.jdjr.risk.device.a.b.a().a(context, str, str2);
            }
            e.a.remove();
            return f2;
        } catch (Throwable unused) {
            return BiometricManager.getInstance().a().a(context);
        }
    }
}
