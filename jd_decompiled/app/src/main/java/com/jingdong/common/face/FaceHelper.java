package com.jingdong.common.face;

import android.content.Context;
import android.os.Build;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;
import java.util.Observer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FaceHelper {
    private static final String TAG = "FaceHelper";

    private static Object callBundleMethod(String str, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.arvrlib.face.FaceHelper");
            if (OKLog.D) {
                OKLog.d(TAG, "clz=====" + loadClass);
            }
            if (loadClass == null) {
                return null;
            }
            if (clsArr != null) {
                declaredMethod = loadClass.getDeclaredMethod(str, clsArr);
            } else {
                declaredMethod = loadClass.getDeclaredMethod(str, new Class[0]);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "method=====" + declaredMethod);
            }
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
            return null;
        }
    }

    public static void download(Context context, String str, String str2, Observer observer) {
        callBundleMethod(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, new Class[]{Context.class, String.class, String.class, Observer.class}, new Object[]{context, str, str2, observer});
    }

    public static void getArConfig(ApiCallback<ARConfigInfo> apiCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mfSdkVersion", "13.0.2");
            int i2 = 1;
            jSONObject.put(HttpDnsConfig.PREDOWNLOAD_PARAMS, 1);
            jSONObject.put("jdSdkVersion", "1.1.4");
            jSONObject.put("v", "1.0");
            if (!is64BitImpl()) {
                i2 = 0;
            }
            jSONObject.put("isGoogle", i2);
            startRequest("arGetConfig", jSONObject, apiCallback);
        } catch (JSONException e2) {
            e2.printStackTrace();
            if (apiCallback != null) {
                apiCallback.onFailure(e2);
            }
        }
    }

    public static String getJFaceRGBTracker(byte[] bArr, int i2, int i3) {
        Class cls = Integer.TYPE;
        Object callBundleMethod = callBundleMethod("getJFaceRGBTracker", new Class[]{byte[].class, cls, cls}, new Object[]{bArr, Integer.valueOf(i2), Integer.valueOf(i3)});
        return callBundleMethod instanceof String ? (String) callBundleMethod : "";
    }

    public static String getJFaceTracker(byte[] bArr, int i2, int i3, int i4, int i5) {
        Class cls = Integer.TYPE;
        Object callBundleMethod = callBundleMethod("getJfaceTracker", new Class[]{byte[].class, cls, cls, cls, cls}, new Object[]{bArr, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
        return callBundleMethod instanceof String ? (String) callBundleMethod : "";
    }

    public static void init(Context context, Observer observer) {
        callBundleMethod(XView2Constants.XVIEW2_ACTION_INIT, new Class[]{Context.class, Observer.class}, new Object[]{context, observer});
    }

    private static boolean is64BitImpl() {
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        Object invoke2;
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null || (invoke2 = declaredMethod2.invoke(invoke, new Object[0])) == null) {
                return false;
            }
            return Boolean.parseBoolean(invoke2.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void release() {
        callBundleMethod("release", null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void startRequest(final java.lang.String r2, org.json.JSONObject r3, final com.jingdong.common.face.ApiCallback r4) {
        /*
            r0 = 0
            com.jingdong.jdsdk.network.toolbox.HttpSetting r1 = new com.jingdong.jdsdk.network.toolbox.HttpSetting     // Catch: java.lang.Exception -> L2c
            r1.<init>()     // Catch: java.lang.Exception -> L2c
            r1.setFunctionId(r2)     // Catch: java.lang.Exception -> L29
            r0 = 0
            r1.setPost(r0)     // Catch: java.lang.Exception -> L29
            java.lang.String r0 = "arvision.jd.com"
            r1.setReferer(r0)     // Catch: java.lang.Exception -> L29
            java.lang.String r0 = com.jingdong.jdsdk.config.Configuration.getCommonNewHost()     // Catch: java.lang.Exception -> L29
            r1.setHost(r0)     // Catch: java.lang.Exception -> L29
            r1.setJsonParams(r3)     // Catch: java.lang.Exception -> L29
            r3 = 1
            r1.setIncompatibleWithOkHttp(r3)     // Catch: java.lang.Exception -> L29
            com.jingdong.common.face.FaceHelper$1 r3 = new com.jingdong.common.face.FaceHelper$1     // Catch: java.lang.Exception -> L29
            r3.<init>()     // Catch: java.lang.Exception -> L29
            r1.setListener(r3)     // Catch: java.lang.Exception -> L29
            goto L3f
        L29:
            r2 = move-exception
            r0 = r1
            goto L2d
        L2c:
            r2 = move-exception
        L2d:
            r2.printStackTrace()
            if (r4 == 0) goto L3e
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r2 = r2.toString()
            r3.<init>(r2)
            r4.onFailure(r3)
        L3e:
            r1 = r0
        L3f:
            if (r1 == 0) goto L48
            com.jingdong.jdsdk.network.toolbox.HttpGroup r2 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()
            r2.add(r1)
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.face.FaceHelper.startRequest(java.lang.String, org.json.JSONObject, com.jingdong.common.face.ApiCallback):void");
    }
}
