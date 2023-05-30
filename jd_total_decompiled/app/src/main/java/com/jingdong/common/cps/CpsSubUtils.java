package com.jingdong.common.cps;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.union.common.helper.JdUnionSecondJumpHelper;
import com.jingdong.union.dependency.IJumpSubCallBack;

/* loaded from: classes5.dex */
public class CpsSubUtils {
    private static final String BUNDLE = "bundle";
    private static final String BUNDLE_ACTURL = "act_url";
    private static final String BUNDLE_SKUID = "sku_id";
    private static final String BUNDLE_VENDERID = "vender_id";
    public static final String ENTER_SUB = "jingdongunionsdk_1626424295026|9";
    private static final String MSG = "msg";

    public static void reportSubJdUnion(Context context, Bundle bundle) {
        reportSubJdUnion(context, null, bundle, true, 0, null);
    }

    public static void reportSubJdUnionNoLoading(Context context, Bundle bundle) {
        reportSubJdUnion(context, null, bundle, false, 0, null);
    }

    public static void reportSubJdUnion(Context context, Bundle bundle, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, null, bundle, true, 0, iJumpSubCallBack);
    }

    public static void reportSubJdUnionNoLoading(Context context, Bundle bundle, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, null, bundle, false, 0, iJumpSubCallBack);
    }

    public static void reportSubJdUnion(Context context, Bundle bundle, int i2, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, null, bundle, true, i2, iJumpSubCallBack);
    }

    public static void reportSubJdUnionNoLoading(Context context, Bundle bundle, int i2, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, null, bundle, false, i2, iJumpSubCallBack);
    }

    public static void reportSubJdUnion(Context context, View view, Bundle bundle) {
        reportSubJdUnion(context, view, bundle, true, 0, null);
    }

    public static void reportSubJdUnion(Context context, View view, Bundle bundle, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, view, bundle, true, 0, iJumpSubCallBack);
    }

    public static void reportSubJdUnion(Context context, View view, Bundle bundle, int i2, IJumpSubCallBack iJumpSubCallBack) {
        reportSubJdUnion(context, view, bundle, true, i2, iJumpSubCallBack);
    }

    public static void reportSubJdUnion(Context context, View view, Bundle bundle, boolean z, int i2, IJumpSubCallBack iJumpSubCallBack) {
        String str;
        String str2;
        String str3;
        String config = JDMobileConfig.getInstance().getConfig("JingdongUnion", "isCloseJdSubUnion", "switchType", "0");
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putString(BUNDLE, JDUnionUtils.bundleToJson(bundle));
        } else {
            bundle2.putString(BUNDLE, "bundle\u4e3a\u7a7a");
        }
        bundle2.putString("msg", "\u8fdb\u5165sdk");
        bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        bundle2.putInt("source", 2);
        bundle2.putString("isCloseJdSubUnion", config);
        JDMtaUtils.sendCommonData(context, ENTER_SUB, JDUnionUtils.bundleToJson(bundle2), LangUtils.SINGLE_SPACE, "RequestUrlHelper", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, "");
        if (!TextUtils.equals("1", config)) {
            JdUnionSecondJumpHelper.reportSubJdUnion(context, view, bundle, z, i2, iJumpSubCallBack);
        } else if (iJumpSubCallBack != null) {
            if (bundle != null) {
                String string = bundle.getString("sku_id", "");
                String string2 = bundle.getString("vender_id", "");
                str3 = bundle.getString("act_url", "");
                str = string;
                str2 = string2;
            } else {
                str = "";
                str2 = str;
                str3 = str2;
            }
            iJumpSubCallBack.onResult(context, str, str2, str3, 0);
        }
    }
}
