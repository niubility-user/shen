package com.jingdong.sdk.jdshare.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WeiboUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.sina.weibo.sdk.openapi.IWBAPI;

/* loaded from: classes7.dex */
public class WeiboUtils {
    private static boolean sMobileSwitch;
    private static com.jingdong.c.a.b.b sShare;
    private static Class sWbClass;

    public static boolean check(Context context) {
        if (sMobileSwitch) {
            return sShare.check(context);
        }
        return WeiboUtil.check(context);
    }

    public static boolean checkWbSdk() {
        return sShare != null;
    }

    public static void doActivityResultIntent(Intent intent, ShareActivity shareActivity) {
        if (sMobileSwitch) {
            sShare.doActivityResultIntent(intent, shareActivity);
        } else {
            WeiboUtil.doActivityResultIntent(intent, shareActivity);
        }
    }

    public static void doWBShare(Activity activity, ShareInfo shareInfo, byte[] bArr) {
        if (sMobileSwitch) {
            sShare.doWBShare(activity, shareInfo, bArr);
        } else {
            WeiboUtil.doWBShare(activity, shareInfo, bArr);
        }
    }

    public static IWBAPI getWBShareApi(Context context) {
        if (sMobileSwitch) {
            return sShare.getWBShareApi(context);
        }
        return WeiboUtil.getWBShareApi(context);
    }

    public static void register(Context context) {
        String config;
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.bundle.jd_wb_share.utils.WeiboUtils");
            sWbClass = cls;
            if (cls != null) {
                com.jingdong.c.a.b.b bVar = (com.jingdong.c.a.b.b) cls.newInstance();
                sShare = bVar;
                if (bVar != null) {
                    if (ShareUtil.isUseSwitchQuery()) {
                        config = SwitchQueryFetcher.getSwitchStringValue("switchShareType", "");
                    } else {
                        config = JDMobileConfig.getInstance().getConfig("JDShare", "switchShareType", "switchType");
                    }
                    if (TextUtils.equals("1", config)) {
                        sMobileSwitch = true;
                        OKLog.d("share-wb-JDMobileConfig", config);
                    }
                }
            }
            getWBShareApi(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
