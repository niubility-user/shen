package com.jingdong.app.mall.mocker;

import android.app.Application;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

/* loaded from: classes4.dex */
public class MockerUtils {
    private static MockerUtils mockerUtils;

    /* loaded from: classes4.dex */
    class MyHandler implements InvocationHandler {
        MyHandler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.getName().equals("getUniqueName")) {
                return MockerUtils.this.modifyFuncId((URL) objArr[0]);
            }
            return null;
        }
    }

    private MockerUtils() {
    }

    public static MockerUtils getInstance() {
        if (mockerUtils == null) {
            synchronized (MockerUtils.class) {
                if (mockerUtils == null) {
                    mockerUtils = new MockerUtils();
                }
            }
        }
        return mockerUtils;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String modifyFuncId(URL url) {
        return null;
    }

    public URL getMockerUrl(URL url) {
        Method method;
        Object invoke;
        try {
            Class<?> cls = Class.forName("com.mockerlib.JdMockerManager");
            Class<?> cls2 = Class.forName("com.mockerlib.Impl.ModifyUrlContent");
            if (cls != null && cls2 != null) {
                Object newProxyInstance = Proxy.newProxyInstance(MockerUtils.class.getClassLoader(), new Class[]{cls2}, new MyHandler());
                Object newInstance = cls.newInstance();
                if (newInstance == null || (method = cls.getMethod("getFinalUrl", URL.class, cls2)) == null || (invoke = method.invoke(newInstance, url, newProxyInstance)) == null) {
                    return null;
                }
                return (URL) invoke;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public void initMocker(Application application, int i2) {
        Object newInstance;
        Method method;
        try {
            Class<?> cls = Class.forName("com.mockerlib.JdMockerManager");
            if (cls == null || (newInstance = cls.newInstance()) == null || (method = cls.getMethod("initMocker", Application.class, Integer.TYPE)) == null) {
                return;
            }
            method.invoke(newInstance, application, Integer.valueOf(i2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
