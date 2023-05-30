package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class DeepLinkArVrPublicInterfaceHelper {
    public static final String BUNDLE_KEY_CLASS_NAME = "className";
    public static final String BUNDLE_KEY_METHOD_NAME = "methodName";
    public static final String BUNDLE_KEY_METHOD_PARAMS = "methodParams";
    public static final String BUNDLE_KEY_MODULE = "module";
    private static final String TAG = "DeepLinkArVrPublicInterfaceHelper";

    public static void callBundleMethod(String str, String str2, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass(str);
            if (OKLog.D) {
                OKLog.d(TAG, "clz=====" + loadClass);
            }
            if (loadClass == null) {
                return;
            }
            try {
                if (clsArr != null) {
                    declaredMethod = loadClass.getDeclaredMethod(str2, clsArr);
                } else {
                    declaredMethod = loadClass.getDeclaredMethod(str2, new Class[0]);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "method=====" + declaredMethod);
                }
                if (declaredMethod == null) {
                    return;
                }
                declaredMethod.invoke(null, objArr);
            } catch (NoSuchMethodException e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
            } catch (InvocationTargetException e3) {
                if (OKLog.D) {
                    OKLog.e(TAG, e3);
                }
            }
        } catch (ClassNotFoundException e4) {
            if (OKLog.D) {
                OKLog.e(TAG, e4);
            }
        } catch (IllegalAccessException e5) {
            if (OKLog.D) {
                OKLog.e(TAG, e5);
            }
        } catch (Exception e6) {
            if (OKLog.D) {
                OKLog.e(TAG, e6);
            }
        }
    }

    public static void execMethod(Context context, Bundle bundle, boolean z) {
        String string = bundle.getString("className");
        String string2 = bundle.getString("methodName");
        String string3 = bundle.getString(BUNDLE_KEY_METHOD_PARAMS);
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            if (OKLog.D) {
                OKLog.d(TAG, "arvr className\uff1a" + string + ", method:" + string2);
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (context != null && z) {
                arrayList.add(Context.class);
                arrayList2.add(context);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "arvr methodParams\uff1a" + string3);
                }
                try {
                    JSONArray jSONArray = new JSONArray(string3);
                    if (jSONArray.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            String optString = jSONArray.optString(i2);
                            arrayList.add(String.class);
                            arrayList2.add(optString);
                        }
                    }
                } catch (JSONException e2) {
                    if (OKLog.D) {
                        OKLog.e(TAG, e2);
                    }
                }
            }
            if (arrayList.size() > 0) {
                callBundleMethod(string, string2, (Class[]) arrayList.toArray(new Class[arrayList.size()]), arrayList2.toArray());
            } else {
                callBundleMethod(string, string2, null, new Object[0]);
            }
        } else if (OKLog.D) {
            OKLog.e(TAG, "arvr bundle class name or method name is null or empty");
        }
    }
}
