package com.jingdong.sdk.jdshare.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.QQUtil;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/* loaded from: classes7.dex */
public class e {
    private static Class a;
    private static com.jingdong.c.a.b.a b;

    /* renamed from: c */
    private static boolean f15024c;

    public static boolean a(Context context) {
        if (f15024c) {
            return b.check(context);
        }
        return QQUtil.check(context);
    }

    public static boolean b() {
        return b != null;
    }

    public static Tencent c() {
        if (f15024c) {
            return b.getTencentInstance();
        }
        return QQUtil.getTencentInstance();
    }

    public static boolean d(int i2, int i3, Intent intent, IUiListener iUiListener) {
        if (f15024c) {
            return b.onActivityResultData(i2, i3, intent);
        }
        return Tencent.onActivityResultData(i2, i3, intent, iUiListener);
    }

    public static void e(ShareActivity shareActivity) {
        String config;
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.bundle.jd_qq_share.QQUtils");
            a = cls;
            if (cls != null) {
                com.jingdong.c.a.b.a aVar = (com.jingdong.c.a.b.a) cls.newInstance();
                b = aVar;
                if (aVar != null) {
                    if (ShareUtil.isUseSwitchQuery()) {
                        config = SwitchQueryFetcher.getSwitchStringValue("switchShareType", "");
                    } else {
                        config = JDMobileConfig.getInstance().getConfig("JDShare", "switchShareType", "switchType");
                    }
                    if (TextUtils.equals("1", config)) {
                        f15024c = true;
                        OKLog.d("share-qq-JDMobileConfig", config);
                    }
                    b.injectIUiListener(shareActivity);
                }
            }
            c();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void f(Activity activity, ShareInfo shareInfo, IUiListener iUiListener) {
        if (f15024c) {
            b.shareToQQ(activity, shareInfo);
        } else {
            QQUtil.shareToQQ(activity, shareInfo, iUiListener);
        }
    }

    public static void g(Activity activity, ShareInfo shareInfo, String str, IUiListener iUiListener) {
        if (f15024c) {
            b.shareToQQ(activity, shareInfo, str);
        } else {
            QQUtil.shareToQQ(activity, shareInfo, str, iUiListener);
        }
    }

    public static void h(Activity activity, ShareInfo shareInfo, IUiListener iUiListener) {
        if (f15024c) {
            b.shareToQZone(activity, shareInfo);
        } else {
            QQUtil.shareToQZone(activity, shareInfo, iUiListener);
        }
    }
}
