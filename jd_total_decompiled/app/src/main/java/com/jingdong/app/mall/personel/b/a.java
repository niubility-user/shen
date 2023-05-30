package com.jingdong.app.mall.personel.b;

import com.jingdong.app.mall.R;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes4.dex */
public class a {
    public static String a() {
        return CommonBase.getJdSharedPreferences().getString("personal_dna_title", JdSdk.getInstance().getApplication().getResources().getString(R.string.personal_mystreet_string));
    }

    public static boolean b() {
        return CommonBase.getJdSharedPreferences().getBoolean(LoginUserBase.getLoginUserName() + "_isFirstEnterMy618DNA", true);
    }

    public static void c(String str) {
        CommonBase.getJdSharedPreferences().edit().putString("personal_dna_title", str).apply();
    }

    public static void d(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean(LoginUserBase.getLoginUserName() + "_isFirstEnterMy618DNA", z).apply();
    }
}
