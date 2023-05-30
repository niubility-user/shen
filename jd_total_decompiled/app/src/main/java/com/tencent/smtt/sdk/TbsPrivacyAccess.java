package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes9.dex */
public enum TbsPrivacyAccess {
    DeviceId(false),
    Imsi(false),
    AndroidId(false),
    MacAddress(false),
    AndroidVersion(true),
    DeviceModel(true),
    AppList(true);
    
    @Deprecated
    private static boolean a = true;

    /* renamed from: c */
    private static boolean f17766c = false;
    private static String[] d = null;
    private boolean b;

    /* loaded from: classes9.dex */
    public enum ConfigurablePrivacy {
        IMSI("imsi"),
        ANDROID_ID("android_id"),
        MAC(Constant.KEY_MAC),
        ANDROID_VERSION("android_version"),
        DEVICE_MODEL("device_model"),
        APP_LIST("app_list"),
        QIMEI36("q36"),
        MODEL(CustomThemeConstance.NAVI_MODEL),
        OAID("oaid"),
        SERIAL("serial"),
        ACTION("action"),
        QB_INSTALLED("qb_installed");
        
        String a;

        ConfigurablePrivacy(String str) {
            this.a = str;
        }
    }

    TbsPrivacyAccess(boolean z) {
        this.b = z;
    }

    private static void a(Context context, SharedPreferences.Editor editor, ConfigurablePrivacy configurablePrivacy, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        TbsLog.i("TbsPrivacy", "doConfigPrivacy  " + configurablePrivacy.a + " is " + str);
        if (!TextUtils.isEmpty(configurablePrivacy.a) && configurablePrivacy.a.equals("action")) {
            a(context, str);
            return;
        }
        editor.putString(configurablePrivacy.a, str);
        TbsLog.i("TbsPrivacy", "configurePrivacy " + configurablePrivacy.a + " is " + str);
    }

    private static void a(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str) && FileUtil.a(context) && str.equals("deleteQBApk")) {
                String a2 = FileUtil.a(context, 9);
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                File file = new File(a2);
                TbsLog.i("TbsPrivacy", "doActionByApp QbApkDir is " + file.getAbsolutePath());
                FileUtil.b(file);
            }
        } catch (Throwable th) {
            TbsLog.i("TbsPrivacy", "doActionByApp stack is " + Log.getStackTraceString(th));
        }
    }

    public static void configureAllPrivacy(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            if (bundle.containsKey("qimei36")) {
                bundle.putString(ConfigurablePrivacy.QIMEI36.a, bundle.getString("qimei36"));
            }
            Iterator<String> it = bundle.keySet().iterator();
            while (it.hasNext()) {
                TbsLog.i("TbsPrivacy", "configureAllPrivacy bundle key is " + it.next());
            }
            SharedPreferences.Editor edit = context.getSharedPreferences("uifa", 0).edit();
            for (ConfigurablePrivacy configurablePrivacy : ConfigurablePrivacy.values()) {
                if (bundle.containsKey(configurablePrivacy.a)) {
                    a(context, edit, configurablePrivacy, bundle.getString(configurablePrivacy.a));
                }
            }
            edit.putString("app_call", "done");
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static void configureAllPrivacy(Context context, String str) {
        if (str == null) {
            return;
        }
        try {
            String b = com.tencent.smtt.utils.s.b(context);
            TbsLog.i("TbsPrivacy", "configureAllPrivacy state is " + b);
            if (b.contains("app_list")) {
                return;
            }
            SharedPreferences.Editor edit = context.getSharedPreferences("uifa", 0).edit();
            edit.putString("app_list", str);
            edit.putString("app_call", "done");
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static void configurePrivacy(Context context, ConfigurablePrivacy configurablePrivacy, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("uifa", 0).edit();
        a(context, edit, configurablePrivacy, str);
        edit.commit();
    }

    @Deprecated
    public static void disableSensitiveApi() {
        a = false;
        HashMap hashMap = new HashMap();
        hashMap.put(TbsCoreSettings.NO_SENSITIVE_API, Boolean.TRUE);
        QbSdk.initTbsSettings(hashMap);
    }

    public static String getConfigurePrivacy(Context context, ConfigurablePrivacy configurablePrivacy, String str) {
        return context.getSharedPreferences("uifa", 0).getString(configurablePrivacy.a, str);
    }

    public static String[] getItemToRmPrivacy() {
        return d;
    }

    @Deprecated
    public static boolean isEnableSensitiveApi() {
        return a;
    }

    public static void rmPrivacyItemIfNeeded(Context context) {
        try {
            TbsLog.i("TbsPrivacy", "mRmPrivacyItemChecked is " + f17766c);
            if (f17766c) {
                return;
            }
            f17766c = true;
            String b = com.tencent.smtt.utils.s.b(context);
            TbsLog.i("TbsPrivacy", "rmPrivacyItemIfNeeded state is " + b);
            if (b.equals("removenone")) {
                d = null;
                return;
            }
            d = b.split(DYConstants.DY_REGEX_VERTICAL_LINE);
            TbsLog.i("TbsPrivacy", "rmPrivacyItemIfNeeded mItemToRmPrivacy is " + d);
            SharedPreferences sharedPreferences = context.getSharedPreferences("uifa", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            for (String str : d) {
                if (sharedPreferences.contains(str)) {
                    edit.remove(str);
                }
            }
            edit.commit();
        } catch (Throwable th) {
            TbsLog.i("TbsPrivacy", "stack is " + Log.getStackTraceString(th));
        }
    }

    public boolean isDisabled() {
        return !this.b;
    }

    public boolean isEnabled() {
        return this.b;
    }

    public void setEnabled(boolean z) {
        this.b = z;
        TbsLog.i("TbsPrivacy", name() + " is " + z);
    }
}
