package com.jdpay.membercode;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdpay.image.loader.request.JDImageLoader;
import com.jdpay.json.JsonAdapter;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.net.http.okhttp.OkhttpProvider;
import com.jdpay.system.SystemInfo;
import com.jdpay.util.JPMCMonitor;

/* loaded from: classes18.dex */
public class JDPayMemberCode {
    private static JDImageLoader imageLoader;
    private static b service;
    public static volatile String userToken;

    public static String getBiometricToken() {
        if (SystemInfo.getApplication() == null || TextUtils.isEmpty(userToken)) {
            return null;
        }
        return BiometricManager.getInstance().getCacheTokenByBizId(SystemInfo.getApplication(), "SDK-HYM", userToken);
    }

    public static synchronized JDImageLoader getImageLoader() {
        JDImageLoader jDImageLoader;
        synchronized (JDPayMemberCode.class) {
            if (imageLoader == null) {
                imageLoader = new JDImageLoader(new OkhttpProvider());
            }
            jDImageLoader = imageLoader;
        }
        return jDImageLoader;
    }

    public static synchronized b getService() {
        b bVar;
        synchronized (JDPayMemberCode.class) {
            if (service == null) {
                service = new b(new OkhttpProvider());
            }
            bVar = service;
        }
        return bVar;
    }

    public static void init(Context context) {
        Application application;
        if (context instanceof Application) {
            application = (Application) context;
        } else {
            Activity activity = ContextHelper.getActivity(context);
            application = activity != null ? activity.getApplication() : null;
        }
        JsonAdapter.init();
        Aks.initialize(context.getApplicationContext());
        JPMCMonitor.init(context.getApplicationContext());
        if (application != null) {
            SystemInfo.init(application);
        }
        JPMCMonitor.onEvent("MCcreate");
    }
}
