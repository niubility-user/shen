package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class DeepLinkMatrixArHelper {
    public static final String EXTRA_ACTIVITY_ID = "activity_id";
    public static final String EXTRA_ACTIVITY_URL = "activity_url";
    private static final String TAG = "DeepLinkMatrixArHelper";

    public static void callBundleMethod(String str, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        if (!isBundleSwitchOpen()) {
            if (Log.D) {
                Log.d(TAG, "bundle-matrixar switch is close ");
                return;
            }
            return;
        }
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.matrixar.PublicInterface");
            if (Log.D) {
                Log.d(TAG, "clz=====" + loadClass);
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
                if (Log.D) {
                    Log.d(TAG, "method=====" + declaredMethod);
                }
                if (declaredMethod == null) {
                    return;
                }
                declaredMethod.invoke(null, objArr);
            } catch (NoSuchMethodException e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            } catch (InvocationTargetException e3) {
                if (Log.D) {
                    e3.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e4) {
            if (Log.D) {
                e4.printStackTrace();
            }
        } catch (IllegalAccessException e5) {
            if (Log.D) {
                e5.printStackTrace();
            }
        }
    }

    public static void cleanCache(Context context) {
        callBundleMethod("cleanCache", new Class[]{Context.class}, new Object[]{context});
    }

    private static boolean isBundleSwitchOpen() {
        return true;
    }

    public static void preload(Context context) {
        callBundleMethod(HttpDnsConfig.PREDOWNLOAD_PARAMS, new Class[]{Context.class}, new Object[]{context});
    }

    public static void startMatrixAr(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(TAG, "start bundle-matrixar");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "matrixaractivity", bundle);
    }
}
