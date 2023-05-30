package com.jd.jdsec.a.k;

import android.content.Context;

/* loaded from: classes13.dex */
public class g {
    private static long a;
    private static String[] b = {"com.redfinger.tw", "com.photon.hybrid", "com.qi.earthnutproxy", "com.sigma_rt.totalcontrol", "io.va.exposed", "net.s17s.s17ray", "com.qx.qiangziip", "com.fvcorp.android.aijiasuclient", "org.soutaproxy", "de.robv.android.xposed.installer", "com.soft.controllers", "com.soft.apk008v", "com.soft.apk008Tool", "com.doubee.ig", "com.cyjh.mobileanjian", "com.ruokuai.rktech", "com.topjohnwu.magisk", "com.kingroot.kinguser", "com.saurik.substrate", "com.touchsprite.android", "com.stardust.scriptdroid", "com.mobileuncle.toolhero", "com.huluxia.gametools", "com.gmail.heagoo.apkeditor.pro", "com.sollyu.xposed.hook.model.dev", "com.txy.anywhere", "pro.burgerz.wsm.manager", "com.virtualdroid.loc", "com.virtualdroid.txl", "com.virtualdroid.wzs", "com.virtualdroid.kit", "com.virtualdroid.wxg", "com.virtualdroid.gps", "top.a1024bytes.mockloc.ca.pro", "com.deruhai.guangzi.noroot2", "com.mcmonjmb.yggb", "xiake.xserver", "com.dracrays.fakeloc", "net.anylocation.ultra", "com.wifi99.android.locationcheater", "com.dingweizshou", "top.a1024bytes.mockloc.ca.pro", "com.txy.anywhere.clone", "com.dracrays.fakelocc", "com.tandy.android.mockwxlocation", "net.anylocation", "com.sigma_rt.totalcontrol", "com.chuangdian.ipjl2"};

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.content.Context r4) {
        /*
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r1 = 0
        L3:
            java.lang.String[] r2 = com.jd.jdsec.a.k.g.b     // Catch: java.lang.Exception -> L16
            int r3 = r2.length     // Catch: java.lang.Exception -> L16
            if (r1 >= r3) goto L16
            r2 = r2[r1]     // Catch: java.lang.Exception -> L16
            boolean r2 = com.jingdong.sdk.baseinfo.BaseInfo.isPkgInstalled(r4, r2)     // Catch: java.lang.Exception -> L16
            if (r2 == 0) goto L13
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> L16
            goto L16
        L13:
            int r1 = r1 + 1
            goto L3
        L16:
            boolean r4 = r0.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.k.g.a(android.content.Context):boolean");
    }

    public static Long b(Context context) {
        try {
            if (a(context)) {
                a |= 1;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }
}
