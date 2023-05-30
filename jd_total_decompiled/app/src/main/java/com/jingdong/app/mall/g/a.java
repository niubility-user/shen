package com.jingdong.app.mall.g;

import android.app.ActivityManager;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes19.dex */
public class a {
    private static final AtomicBoolean a = new AtomicBoolean(false);

    /* renamed from: com.jingdong.app.mall.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes19.dex */
    private static class C0264a implements InvocationHandler {

        /* renamed from: g  reason: collision with root package name */
        private final Object f8429g;

        public C0264a(Object obj) {
            this.f8429g = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("reportSizeConfigurations".equals(method.getName())) {
                try {
                    OKLog.d("JDAMSFixUtil_XPJJJ", "reportSizeConfigurations invoke execute " + a.a.get());
                    return method.invoke(this.f8429g, objArr);
                } catch (Exception e2) {
                    OKLog.d("JDAMSFixUtil_XPJJJ", " reportSizeConfigurations exception: " + e2.getMessage() + " is init:" + a.a.get());
                    JdCrashReport.postCaughtException(new IllegalStateException("catch the remote exception"));
                    return null;
                }
            }
            return method.invoke(this.f8429g, objArr);
        }
    }

    public static synchronized void b() {
        synchronized (a.class) {
            StringBuilder sb = new StringBuilder();
            sb.append("hook ams, init: ");
            AtomicBoolean atomicBoolean = a;
            sb.append(atomicBoolean.get());
            OKLog.d("JDAMSFixUtil_XPJJJ", sb.toString());
            if (atomicBoolean.get()) {
                OKLog.d("JDAMSFixUtil_XPJJJ", "hook ams have init return.");
                return;
            }
            try {
            } catch (Exception e2) {
                OKLog.d("JDAMSFixUtil_XPJJJ", " hook exception ," + e2.getMessage());
            }
            if (!b.a()) {
                OKLog.d("JDAMSFixUtil_XPJJJ", "hook return, not match model.");
                return;
            }
            Field declaredField = ActivityManager.class.getDeclaredField("IActivityManagerSingleton");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj == null) {
                OKLog.d("JDAMSFixUtil_XPJJJ", "iActivityManagerSingleton == null");
                return;
            }
            Class<? super Object> superclass = obj.getClass().getSuperclass();
            if (superclass == null) {
                OKLog.d("JDAMSFixUtil_XPJJJ", " singletonCls == null");
                return;
            }
            Field declaredField2 = superclass.getDeclaredField("mInstance");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Class<?> cls = Class.forName("android.app.IActivityManager");
            declaredField2.set(obj, Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0264a(obj2)));
            atomicBoolean.set(true);
            OKLog.d("JDAMSFixUtil_XPJJJ", " hook success!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c() {
        return " hookAMSSuccess: " + a.get();
    }
}
