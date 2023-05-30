package com.jingdong.union.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.common.config.UnionConstants;

/* loaded from: classes12.dex */
public class e {
    private static String a(String str) {
        return TextUtils.isEmpty(str) ? LangUtils.SINGLE_SPACE : str;
    }

    public static void b(Context context, Bundle bundle, String str, String str2, String str3, String str4, String str5, int i2, String str6) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(UnionConstants.BUNDLE_SKUID, a(str));
        bundle2.putString("vender_id", a(str2));
        bundle2.putString("actUrl", a(str3));
        bundle2.putString(UnionConstants.BUNDLE_REFER, a(str4));
        bundle2.putString(UnionConstants.BUNDLE_CURRENT, a(str5));
        bundle2.putString("msg", a(str6));
        bundle2.putInt(XView2Constants.STATE, i2);
        c(context, i2 != -10004 ? (i2 == -10003 || i2 == -10000 || i2 == 0) ? "jingdongunionsdk_1626424295026|5" : i2 != 1 ? "jingdongunionsdk_1626424295026|7" : "jingdongunionsdk_1626424295026|8" : "jingdongunionsdk_1626424295026|6", bundle2, bundle);
    }

    public static void c(Context context, String str, Bundle bundle, Bundle bundle2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        bundle.putInt("source", 2);
        JdUnionBase.getMtaUtils().sendCommonData(context, str, c.a(bundle), LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
    }
}
