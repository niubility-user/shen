package com.jingdong.union.common.helper;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.union.UnionLoadingActivity;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.dependency.IJumpDispatchCallBack;

/* loaded from: classes12.dex */
public class JdUnionJumpHelper {
    public static void jumpUnion(Context context, Bundle bundle) {
        jumpUnion(context, null, bundle, true, 0, null);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle) {
        jumpUnion(context, null, bundle, false, 0, null);
    }

    public static void jumpUnion(Context context, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, null, bundle, true, 0, iJumpDispatchCallBack);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, null, bundle, false, 0, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, Bundle bundle, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, null, bundle, true, i2, iJumpDispatchCallBack);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, null, bundle, false, i2, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, View view, Bundle bundle) {
        jumpUnion(context, view, bundle, true, 0, null);
    }

    public static void jumpUnion(Context context, View view, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, view, bundle, true, 0, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, View view, Bundle bundle, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, view, bundle, true, i2, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, View view, Bundle bundle, boolean z, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        IJumpDispatchCallBack jumpDispatchCallBack = iJumpDispatchCallBack == null ? JdUnionBase.getJumpDispatchCallBack() : iJumpDispatchCallBack;
        Context context2 = context == null ? JdUnionBase.getContext() : context;
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            bundle2.putString("msg", "jumpUnion\u4e2dbundle\u4e3a\u7a7a");
            JdUnionBase.getMtaUtils().sendCommonData(context2, "jingdongunionsdk_1626424295026|3", com.jingdong.union.a.c.a(bundle2), LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
            if (jumpDispatchCallBack != null) {
                jumpDispatchCallBack.onFaile(context2, "");
            }
        } else if (context2 == null) {
            Bundle bundle3 = new Bundle(bundle);
            bundle3.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            bundle3.putString("msg", "jumpUnion\u4e2dcontext\u4e3a\u7a7a");
            JdUnionBase.getMtaUtils().sendCommonData(context2, "jingdongunionsdk_1626424295026|3", com.jingdong.union.a.c.a(bundle3), LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle3);
            if (jumpDispatchCallBack != null) {
                jumpDispatchCallBack.onFaile(context2, "");
            }
        } else {
            bundle.putInt("union_request_timeout", i2);
            if (z) {
                JdUnionBase.tempBgView = view;
                boolean z2 = true;
                if (jumpDispatchCallBack != JdUnionBase.getJumpDispatchCallBack()) {
                    JdUnionBase.tempIJumpDispatchCallBack = jumpDispatchCallBack;
                    z2 = false;
                }
                UnionLoadingActivity.d(context2, z2, bundle);
                return;
            }
            new a().f(context2, bundle, jumpDispatchCallBack);
        }
    }
}
