package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.jdjr.risk.device.c.ag;
import com.jdjr.risk.device.c.k;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jdjr.risk.util.httputil.LorasHttpCallback;

/* loaded from: classes18.dex */
public class BaseInfoService {
    private static volatile BaseInfoService mInstance;

    private BaseInfoService() {
    }

    public static String getAndroidID(Context context) {
        return context != null ? com.jdjr.risk.device.c.b.a(context) : "";
    }

    public static String getCommonID(Context context) {
        return k.a().a(context);
    }

    public static String getIMEI(Context context) {
        PackageManager packageManager;
        return (Build.VERSION.SDK_INT >= 29 || (packageManager = context.getPackageManager()) == null || packageManager.checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0) ? "" : ag.c(context);
    }

    public static BaseInfoService getInstance(Context context) {
        if (mInstance == null) {
            synchronized (BaseInfoService.class) {
                if (mInstance == null) {
                    com.jdjr.risk.a.a.a.a().a(context);
                    mInstance = new BaseInfoService();
                }
            }
        }
        return mInstance;
    }

    public static void getOAID(Context context, LorasHttpCallback lorasHttpCallback) {
        try {
            lorasHttpCallback.onSuccess(JdcnOaidManager.getInstance().getOaid(context));
        } catch (Exception unused) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        }
    }

    public static boolean setDeviceID(Context context, int i2, String str) {
        if (i2 != 1) {
            if (i2 != 2) {
                return false;
            }
            return com.jdjr.risk.a.a.a.a().b(context, str);
        }
        return com.jdjr.risk.a.a.a.a().a(context, str);
    }

    public String getDeviceID(Context context) {
        try {
            return com.jdjr.risk.a.a.a.a().a(context);
        } catch (Exception unused) {
            return "";
        }
    }
}
