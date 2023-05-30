package com.jingdong.app.mall.g;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes19.dex */
public final class c {
    private static final AtomicBoolean a = new AtomicBoolean(false);

    /* loaded from: classes19.dex */
    private static final class a implements InvocationHandler {

        /* renamed from: g  reason: collision with root package name */
        private Object f8430g;

        a(Object obj) {
            this.f8430g = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                String name = method.getName();
                char c2 = '\uffff';
                switch (name.hashCode()) {
                    case -1710913560:
                        if (name.equals("getApplicationInfo")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1039974701:
                        if (name.equals("getActivityInfo")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 268353758:
                        if (name.equals("getPackageInfo")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 630467867:
                        if (name.equals("resolveActivity")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 1725989837:
                        if (name.equals("getServiceInfo")) {
                            c2 = 3;
                            break;
                        }
                        break;
                }
                if (c2 != 0 && c2 != 1 && c2 != 2 && c2 != 3 && c2 != 4) {
                    return method.invoke(this.f8430g, objArr);
                }
                OKLog.d("JDPMSFixUtil", method.getName() + " invoke execute: " + c.a.get());
                return method.invoke(this.f8430g, objArr);
            } catch (Exception e2) {
                OKLog.e("JDPMSFixUtil", e2);
                JdCrashReport.postCaughtException(new IllegalStateException("catch the remote exception"));
                return null;
            } catch (Throwable th) {
                OKLog.e("JDPMSFixUtil", th);
                JdCrashReport.postCaughtException(new IllegalStateException("catch the remote exception"));
                return null;
            }
        }
    }

    public static final synchronized boolean b(Context context) {
        synchronized (c.class) {
            AtomicBoolean atomicBoolean = a;
            if (atomicBoolean.get()) {
                OKLog.d("JDPMSFixUtil", "Hook PMS was initialized, then ignore.");
                return true;
            }
            try {
                if (!d.a()) {
                    OKLog.d("JDPMSFixUtil", "Hook PMS not match model, then ignore.");
                    return false;
                }
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object invoke = cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("sPackageManager");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(invoke);
                Class<?> cls2 = Class.forName("android.content.pm.IPackageManager");
                Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new a(obj));
                declaredField.set(invoke, newProxyInstance);
                PackageManager packageManager = context.getPackageManager();
                Field declaredField2 = packageManager.getClass().getDeclaredField("mPM");
                declaredField2.setAccessible(true);
                declaredField2.set(packageManager, newProxyInstance);
                return atomicBoolean.compareAndSet(false, true);
            } catch (Exception e2) {
                OKLog.e("JDPMSFixUtil", e2);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c() {
        return " hookPMSSuccess: " + a.get();
    }
}
