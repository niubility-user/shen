package com.jingdong.common.network.cronet;

import android.content.Context;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes5.dex */
public class CronetClient {
    public static final String TAG = "Bundle-Cronet";
    protected static volatile Object mCronetEngine;

    public static synchronized Object getCronetEngine() {
        Object obj;
        synchronized (CronetClient.class) {
            if (mCronetEngine == null) {
                Object invoke = CronetComponentHelper.getMethod(CronetComponentHelper.CLASS_NAME_CRONET_STACK_FACTORY, CronetComponentHelper.METHOD_NAME_NEW_CRONET_ENGINE_BUILDER, Context.class).invoke(null, JdSdk.getInstance().getApplicationContext());
                Class cls = Boolean.TYPE;
                Method method = CronetComponentHelper.getMethod("org.chromium.net.CronetEngine$Builder", "enableHttp2", cls);
                Boolean bool = Boolean.TRUE;
                method.invoke(invoke, bool);
                CronetComponentHelper.getMethod("org.chromium.net.CronetEngine$Builder", "enableQuic", cls).invoke(invoke, bool);
                mCronetEngine = CronetComponentHelper.getMethod("org.chromium.net.CronetEngine$Builder", HybridSDK.APP_VERSION_CODE, new Class[0]).invoke(invoke, new Object[0]);
            }
            obj = mCronetEngine;
        }
        return obj;
    }

    public static HttpURLConnection openConnection(URL url) {
        OKLog.d(TAG, "DefaultCronetClient->openConnection \u65b9\u6cd5\u5f00\u59cb\u6267\u884c");
        Object cronetEngine = getCronetEngine();
        StringBuilder sb = new StringBuilder();
        sb.append("getCronetEngine()\u8fd4\u56de\u7ed3\u679c ");
        sb.append(cronetEngine != null);
        OKLog.d(TAG, sb.toString());
        if (cronetEngine != null) {
            try {
                return (HttpURLConnection) CronetComponentHelper.getMethod("org.chromium.net.CronetEngine", "openConnection", URL.class).invoke(cronetEngine, url);
            } catch (ClassNotFoundException e2) {
                OKLog.d(TAG, e2.getClass().getSimpleName() + "" + e2.getMessage());
                e2.printStackTrace();
                return null;
            } catch (IllegalAccessException e3) {
                OKLog.d(TAG, e3.getClass().getSimpleName() + "" + e3.getMessage());
                e3.printStackTrace();
                return null;
            } catch (NoSuchMethodException e4) {
                OKLog.d(TAG, e4.getClass().getSimpleName() + "" + e4.getMessage());
                e4.printStackTrace();
                return null;
            } catch (InvocationTargetException e5) {
                OKLog.d(TAG, e5.getClass().getSimpleName() + "" + e5.getMessage());
                e5.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
