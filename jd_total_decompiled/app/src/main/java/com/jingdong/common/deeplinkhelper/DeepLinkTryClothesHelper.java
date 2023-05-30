package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class DeepLinkTryClothesHelper {
    public static final String PARAM_AREA = "area";
    public static final String PARAM_SKU_ID = "skuId";
    private static final String TAG = "DeepLinkTryClothesHelper";

    public static void callBundleMethod(String str, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.tryclothes.PublicInterface");
            if (OKLog.D) {
                OKLog.d(TAG, "clz=====" + loadClass);
            }
            if (loadClass == null) {
                return;
            }
            try {
                if (clsArr != null) {
                    declaredMethod = loadClass.getDeclaredMethod(str, clsArr);
                } else {
                    declaredMethod = loadClass.getDeclaredMethod(str, new Class[0]);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "method=====" + declaredMethod);
                }
                if (declaredMethod == null) {
                    return;
                }
                declaredMethod.invoke(null, objArr);
            } catch (NoSuchMethodException e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            } catch (InvocationTargetException e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                }
            }
        } catch (ClassNotFoundException e4) {
            if (OKLog.E) {
                OKLog.e(TAG, e4);
            }
        } catch (IllegalAccessException e5) {
            if (OKLog.E) {
                OKLog.e(TAG, e5);
            }
        }
    }

    public static void cleanCache(Context context) {
        callBundleMethod("cleanCache", new Class[]{Context.class}, new Object[]{context});
    }

    public static void startTryClothes(Context context, long j2, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("skuId", j2);
        bundle.putString("area", str);
        startTryClothes(context, bundle);
    }

    public static void startTryClothes(Context context, Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "start bundle-tryclothes");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "tryclothesactivity", bundle);
    }
}
