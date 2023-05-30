package com.jingdong.common.auraSetting;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import androidx.multidex.MultiDex;
import com.jingdong.JdImageToolKit;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.InitApplication;
import com.jingdong.common.utils.CommonNightModeUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.baseinfo.IPrivacyCheck;
import com.jingdong.sdk.deeplink.b;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.oklog.OKLogConfig;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class AuraGlobalSetting {
    private static final String TAG = "AuraGlobalSetting";
    private static IConfigurationFetcher sConfigurationFetcher;

    /* loaded from: classes5.dex */
    public interface IConfigurationFetcher {
        Configuration getConfiguration(Activity activity);
    }

    private static Method getMethod(Class cls, String str, Class[] clsArr) throws Exception {
        try {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                return cls.getMethod(str, clsArr);
            }
        } catch (NoSuchMethodException unused2) {
            if (cls.getSuperclass() == null) {
                return null;
            }
            return getMethod(cls.getSuperclass(), str, clsArr);
        }
    }

    public static Configuration getNormalConfiguration(Activity activity) {
        IConfigurationFetcher iConfigurationFetcher = sConfigurationFetcher;
        if (iConfigurationFetcher != null) {
            return iConfigurationFetcher.getConfiguration(activity);
        }
        return JdSdk.getInstance().getApplication().getResources().getConfiguration();
    }

    public static void initJdMallBaseApplication(Application application) {
        if (isInAuraEnvironment(application)) {
            return;
        }
        MultiDex.install(application);
        JdSdk.getInstance().setApplication(application);
        JdSdk.getInstance().setBuildConfigDebug(true);
        BaseInfo.init(application);
        BaseInfo.setPrivacyCheckUtil(new IPrivacyCheck() { // from class: com.jingdong.common.auraSetting.AuraGlobalSetting.1
            @Override // com.jingdong.sdk.baseinfo.IPrivacyCheck
            public boolean isUserAgreed() {
                return true;
            }
        });
        new OKLogConfig().setDebug(true).diskRecord(true, application).start();
        AuraBundleInfos.init("jingdong", JdSdk.getInstance().getApplication());
        b.a().b(application.getApplicationContext());
        BaseApplication.initOnCreateInBase();
        InitApplication.instance(application);
        CommonNightModeUtils.optionsDefaultMode();
        JdImageToolKit.initialize(JdImageToolKit.newBuilder(application).build());
    }

    public static boolean isInAuraEnvironment(Context context) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            return applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData.getBoolean("isInAura");
        } catch (PackageManager.NameNotFoundException e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    public static void setConfigurationFetcher(IConfigurationFetcher iConfigurationFetcher) {
        sConfigurationFetcher = iConfigurationFetcher;
    }
}
