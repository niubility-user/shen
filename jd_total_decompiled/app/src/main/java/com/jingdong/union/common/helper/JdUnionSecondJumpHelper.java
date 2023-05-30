package com.jingdong.union.common.helper;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jingdong.union.UnionLoadingActivity;
import com.jingdong.union.a.e;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.common.config.UnionConstants;
import com.jingdong.union.dependency.IJumpSubCallBack;

/* loaded from: classes12.dex */
public class JdUnionSecondJumpHelper {
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
        if (context == null) {
            context = JdUnionBase.getContext();
        }
        Context context2 = context;
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("msg", "\u4f20\u9012\u8fdb\u6765\u7684bundle\u662fnull");
            e.c(context2, "jingdongunionsdk_1626424295026|5", bundle2, bundle2);
            if (iJumpSubCallBack != null) {
                iJumpSubCallBack.onResult(context2, "", "", "", -10000);
            }
        } else if (context2 == null) {
            Bundle bundle3 = new Bundle(bundle);
            bundle3.putString("msg", "context\u662fnull");
            e.c(context2, "jingdongunionsdk_1626424295026|5", bundle3, bundle3);
            if (iJumpSubCallBack != null) {
                iJumpSubCallBack.onResult(context2, bundle.getString(UnionConstants.BUNDLE_SKUID, ""), bundle.getString("vender_id", ""), bundle.getString(UnionConstants.BUNDLE_ACTURL, ""), -10000);
            }
        } else {
            bundle.putInt("union_request_timeout", i2);
            if (z) {
                JdUnionBase.tempBgView = view;
                JdUnionBase.tempIJumpSubCallBack = iJumpSubCallBack;
                UnionLoadingActivity.c(context2, bundle);
                return;
            }
            new c().e(context2, bundle, iJumpSubCallBack);
        }
    }
}
