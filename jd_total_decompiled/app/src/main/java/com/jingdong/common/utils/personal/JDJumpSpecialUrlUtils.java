package com.jingdong.common.utils.personal;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.unification.i18n.UnI18NUtils;
import com.jingdong.common.unification.router.JDRouter;

/* loaded from: classes6.dex */
public class JDJumpSpecialUrlUtils {
    private static final String SCHEMA = "router";

    public static boolean isJDRouterProtocol(String str) {
        Uri parse;
        return (!UnI18NUtils.isMainApp() || TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || TextUtils.isEmpty(parse.getScheme()) || !"router".equals(parse.getScheme().toLowerCase())) ? false : true;
    }

    public static boolean isSpecialUrl(String str) {
        return isJDRouterProtocol(str) || JumpUtil.isOpenAppScheme(str);
    }

    public static void jumpSpecialUrl(BaseActivity baseActivity, String str) {
        jumpSpecialUrl(baseActivity, str, false);
    }

    public static void jumpToOpenApp(BaseActivity baseActivity, String str) {
        if (TextUtils.isEmpty(str) || baseActivity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        baseActivity.startActivity(intent);
    }

    public static void jumpSpecialUrl(final BaseActivity baseActivity, final String str, boolean z) {
        if (baseActivity == null) {
            return;
        }
        if (isJDRouterProtocol(str)) {
            Runnable runnable = new Runnable() { // from class: com.jingdong.common.utils.personal.JDJumpSpecialUrlUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    JDRouter.build(BaseActivity.this, str).open();
                }
            };
            if (z) {
                LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, runnable);
            } else {
                baseActivity.post(runnable);
            }
        } else if (JumpUtil.isOpenAppScheme(str)) {
            Runnable runnable2 = new Runnable() { // from class: com.jingdong.common.utils.personal.JDJumpSpecialUrlUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(baseActivity);
                }
            };
            if (z) {
                LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, runnable2);
            } else {
                baseActivity.post(runnable2);
            }
        }
    }
}
