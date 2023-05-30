package com.jingdong.sdk.jdshare.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/* loaded from: classes7.dex */
public class i {
    private static Class a;
    private static com.jingdong.c.a.b.c b;

    /* renamed from: c */
    private static boolean f15025c;

    public static boolean a() {
        if (f15025c) {
            return b.check();
        }
        return WeixinUtil.check();
    }

    public static boolean b() {
        if (f15025c) {
            return b.checkSupportFileProvider();
        }
        return WeixinUtil.checkSupportFileProvider();
    }

    public static boolean c() {
        return b != null;
    }

    public static void d(ShareInfo shareInfo, String str, boolean z, boolean z2, byte[] bArr) {
        if (f15025c) {
            b.doWXShare(shareInfo, z, z2, bArr);
        } else {
            WeixinUtil.doWXShare(shareInfo, z, z2, bArr);
        }
        i(z ? "Share_Wxfriends_MP" : "Share_Wxfriends", shareInfo.getUrl(), str, z2);
    }

    public static void e(ShareInfo shareInfo, String str, boolean z, byte[] bArr, Bitmap bitmap) {
        if (f15025c) {
            b.doWXShare(shareInfo, z, bArr, bitmap);
        } else {
            WeixinUtil.doWXShare(shareInfo, z, bArr, bitmap);
        }
        i("Share_Wxfriends", shareInfo.getUrl(), str, z);
    }

    public static void f(ShareInfo shareInfo, String str, boolean z, byte[] bArr, String str2) {
        if (f15025c) {
            b.doWXShare(shareInfo, z, bArr, str2);
        } else {
            WeixinUtil.doWXShare(shareInfo, z, bArr, str2);
        }
        i("Share_Wxfriends", shareInfo.getUrl(), str, z);
    }

    public static IWXAPI g() {
        if (f15025c) {
            return b.getWXApi();
        }
        return WeixinUtil.getWXApi();
    }

    public static void h() {
        String config;
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.bundle.jd_wx_share.utils.WeChatUtils");
            a = cls;
            if (cls != null) {
                com.jingdong.c.a.b.c cVar = (com.jingdong.c.a.b.c) cls.newInstance();
                b = cVar;
                if (cVar != null) {
                    if (ShareUtil.isUseSwitchQuery()) {
                        config = SwitchQueryFetcher.getSwitchStringValue("switchShareType", "");
                    } else {
                        config = JDMobileConfig.getInstance().getConfig("JDShare", "switchShareType", "switchType");
                    }
                    if (TextUtils.equals("1", config)) {
                        f15025c = true;
                        OKLog.d("share-wx-JDMobileConfig", config);
                    }
                }
            }
            g();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void i(String str, String str2, String str3, boolean z) {
        if (z) {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str2, "", "", "ShareActivity", str3, "", ShareValues.wxJsonParam, null);
            OKLog.d("weiXinUtils", "sendMta");
        }
    }
}
