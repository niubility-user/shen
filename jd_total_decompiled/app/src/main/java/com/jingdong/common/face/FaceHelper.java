package com.jingdong.common.face;

import android.content.Context;
import android.os.Build;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
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
    */
    private static void startRequest(final String str, JSONObject jSONObject, final ApiCallback apiCallback) {
        HttpSetting httpSetting;
        HttpSetting httpSetting2 = null;
        try {
            httpSetting = new HttpSetting();
            try {
                httpSetting.setFunctionId(str);
                httpSetting.setPost(false);
                httpSetting.setReferer("arvision.jd.com");
                httpSetting.setHost(Configuration.getCommonNewHost());
                httpSetting.setJsonParams(jSONObject);
                httpSetting.setIncompatibleWithOkHttp(true);
                httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.face.FaceHelper.1
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        if (ApiCallback.this != null) {
                            try {
                                JSONObjectProxy jSONObject2 = httpResponse.getJSONObject();
                                jSONObject2.put("functionId", str);
                                ApiCallback.this.onEnd(jSONObject2);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        ApiCallback apiCallback2 = ApiCallback.this;
                        if (apiCallback2 != null) {
                            apiCallback2.onFailure(new Exception(httpError.toString()));
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                    }
                });
            } catch (Exception e2) {
                e = e2;
                httpSetting2 = httpSetting;
                e.printStackTrace();
                if (apiCallback != null) {
                    apiCallback.onFailure(new Exception(e.toString()));
                }
                httpSetting = httpSetting2;
                if (httpSetting == null) {
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
        if (httpSetting == null) {
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }
}
