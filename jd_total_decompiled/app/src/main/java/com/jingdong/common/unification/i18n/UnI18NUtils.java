package com.jingdong.common.unification.i18n;

import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.a;

/* loaded from: classes.dex */
public class UnI18NUtils {
    private static String getClientRes() {
        return a.a(JdSdk.getInstance().getApplicationContext());
    }

    public static boolean isGlobalApp() {
        return TextUtils.equals(JshopConst.JSKEY_IS_GLOBAL, getClientRes());
    }

    public static boolean isMainApp() {
        return TextUtils.equals("jingdong", getClientRes());
    }

    public static boolean isThApp() {
        return TextUtils.equals(JshopConst.JSKEY_IS_GLOBAL, getClientRes());
    }
}
