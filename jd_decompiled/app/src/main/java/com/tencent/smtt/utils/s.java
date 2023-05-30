package com.tencent.smtt.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsPrivacyAccess;

/* loaded from: classes9.dex */
public class s {
    private static String a = "";

    public static long a() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    public static String a(Context context) {
        SharedPreferences sharedPreferences;
        String string;
        if (TextUtils.isEmpty(a)) {
            try {
                sharedPreferences = context.getSharedPreferences("sai", 0);
                string = sharedPreferences.getString("bmo", "");
                a = string;
            } catch (Throwable th) {
                TbsLog.i("TbsUtils", "stack is " + Log.getStackTraceString(th));
            }
            if (TextUtils.isEmpty(string)) {
                TbsPrivacyAccess.ConfigurablePrivacy configurablePrivacy = TbsPrivacyAccess.ConfigurablePrivacy.MODEL;
                a = TbsPrivacyAccess.getConfigurePrivacy(context, configurablePrivacy, "");
                TbsLog.i("TbsUtils", "getBuildModel from sp is " + a);
                if (TextUtils.isEmpty(a)) {
                    if (TextUtils.isEmpty(a) && !"com.tencent.mobileqq".equals(context.getPackageName())) {
                        a = BaseInfo.getDeviceModel();
                    }
                    TbsLog.i("TbsUtils", "getBuildModel is " + a);
                    if (!TextUtils.isEmpty(a)) {
                        TbsPrivacyAccess.configurePrivacy(context, configurablePrivacy, a);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("bmo", a);
                        edit.commit();
                    }
                    return a;
                }
                return a;
            }
            return a;
        }
        return a;
    }

    public static void a(Context context, Bundle bundle) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("sai", 0).edit();
            if (bundle.containsKey(QbSdk.KEY_THIRD_PARTY_TURING)) {
                boolean z = bundle.getBoolean(QbSdk.KEY_THIRD_PARTY_TURING);
                edit.putBoolean("itge", z);
                TbsLog.e("TbsUtils", "setEnableForThirdParty key is itge value is " + z);
            }
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context) {
        return "removenone";
    }
}
