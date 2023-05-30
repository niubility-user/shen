package com.jingdong.union.common.helper;

import android.text.TextUtils;
import com.jingdong.union.a.f;
import com.jingdong.union.common.config.JdUnionConfig;

/* loaded from: classes12.dex */
public class b {
    public static Boolean a(JdUnionConfig jdUnionConfig) {
        if (jdUnionConfig == null) {
            f.a("no init");
            return Boolean.FALSE;
        }
        Boolean bool = Boolean.TRUE;
        if (TextUtils.isEmpty(jdUnionConfig.getToken())) {
            bool = Boolean.FALSE;
            f.d("token is needed");
        }
        if (jdUnionConfig.getiAdvertUtils() == null && jdUnionConfig.getiJdAdvertUtils() == null) {
            bool = Boolean.FALSE;
            f.d("AdvertUtils is needed");
        }
        if (jdUnionConfig.getiLoginUser() == null) {
            bool = Boolean.FALSE;
            f.d("LoginUser is needed");
        }
        if (jdUnionConfig.getiWebUa() == null) {
            bool = Boolean.FALSE;
            f.d("ua is needed");
        }
        if (jdUnionConfig.getContext() == null) {
            bool = Boolean.FALSE;
            f.d("context is needed");
        }
        if (jdUnionConfig.getiJumpDispatchCallBack() == null) {
            Boolean bool2 = Boolean.FALSE;
            f.d("iJumpDispatchCallBack is needed");
            return bool2;
        }
        return bool;
    }
}
