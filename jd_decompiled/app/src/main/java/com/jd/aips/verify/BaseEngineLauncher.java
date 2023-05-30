package com.jd.aips.verify;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.aips.verify.tracker.TrackerCallback;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes12.dex */
public class BaseEngineLauncher {
    static final String ENGINE_GET_INSTANCE_METHOD = "getInstance";
    static final String ENGINE_LAUNCH_METHOD = "launch";
    static final String TRACKER_CALLBACK = "com.jd.aips.verify.tracker.TrackerCallback";
    static final String TRACKER_CALLBACK_METHOD = "onTrack";
    static final String VERIFY_CALLBACK = "com.jd.aips.verify.VerifyCallback";
    static final String VERIFY_CALLBACK_METHOD = "onResult";
    protected final LauncherCallback launcherCallback;
    protected final Handler mainHandler;

    /* loaded from: classes12.dex */
    public interface LauncherCallback {
        void onFailure();
    }

    public BaseEngineLauncher(@NonNull Handler handler, @NonNull LauncherCallback launcherCallback) {
        this.mainHandler = handler;
        this.launcherCallback = launcherCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void launchEngine(@NonNull Context context, @NonNull String str, @NonNull Bundle bundle, @NonNull final VerifyCallback verifyCallback, final TrackerCallback trackerCallback) {
        boolean z = true;
        try {
            Context applicationContext = context.getApplicationContext();
            Class<?> loadClass = applicationContext.getClassLoader().loadClass(str);
            Object invoke = loadClass.getMethod(ENGINE_GET_INSTANCE_METHOD, new Class[0]).invoke(loadClass, new Object[0]);
            ClassLoader classLoader = loadClass.getClassLoader();
            Class<?> loadClass2 = classLoader.loadClass(VERIFY_CALLBACK);
            Class<?> loadClass3 = classLoader.loadClass(TRACKER_CALLBACK);
            Method method = loadClass.getMethod("launch", Context.class, Bundle.class, loadClass2, loadClass3);
            Object newProxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{loadClass2}, new InvocationHandler() { // from class: com.jd.aips.verify.BaseEngineLauncher.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                    if (method2 == null || !TextUtils.equals(method2.getName(), BaseEngineLauncher.VERIFY_CALLBACK_METHOD) || objArr == null || objArr.length != 4) {
                        return null;
                    }
                    verifyCallback.onResult(((Integer) objArr[0]).intValue(), (String) objArr[1], (String) objArr[2], (Bundle) objArr[3]);
                    return null;
                }
            });
            if (trackerCallback != null) {
                method.invoke(invoke, applicationContext, bundle, newProxyInstance, Proxy.newProxyInstance(classLoader, new Class[]{loadClass3}, new InvocationHandler() { // from class: com.jd.aips.verify.BaseEngineLauncher.2
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                        if (method2 == null || !TextUtils.equals(method2.getName(), BaseEngineLauncher.TRACKER_CALLBACK_METHOD) || objArr == null || objArr.length != 2) {
                            return null;
                        }
                        trackerCallback.onTrack((String) objArr[0], (Bundle) objArr[1]);
                        return null;
                    }
                }));
            } else {
                method.invoke(invoke, applicationContext, bundle, newProxyInstance, null);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | Exception unused) {
            z = false;
        }
        if (z) {
            return;
        }
        this.launcherCallback.onFailure();
    }
}
