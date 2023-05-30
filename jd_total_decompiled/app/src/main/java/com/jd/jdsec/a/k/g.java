package com.jd.jdsec.a.k;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class g {
    private static long a;
    private static String[] b = {"com.redfinger.tw", "com.photon.hybrid", "com.qi.earthnutproxy", "com.sigma_rt.totalcontrol", "io.va.exposed", "net.s17s.s17ray", "com.qx.qiangziip", "com.fvcorp.android.aijiasuclient", "org.soutaproxy", "de.robv.android.xposed.installer", "com.soft.controllers", "com.soft.apk008v", "com.soft.apk008Tool", "com.doubee.ig", "com.cyjh.mobileanjian", "com.ruokuai.rktech", "com.topjohnwu.magisk", "com.kingroot.kinguser", "com.saurik.substrate", "com.touchsprite.android", "com.stardust.scriptdroid", "com.mobileuncle.toolhero", "com.huluxia.gametools", "com.gmail.heagoo.apkeditor.pro", "com.sollyu.xposed.hook.model.dev", "com.txy.anywhere", "pro.burgerz.wsm.manager", "com.virtualdroid.loc", "com.virtualdroid.txl", "com.virtualdroid.wzs", "com.virtualdroid.kit", "com.virtualdroid.wxg", "com.virtualdroid.gps", "top.a1024bytes.mockloc.ca.pro", "com.deruhai.guangzi.noroot2", "com.mcmonjmb.yggb", "xiake.xserver", "com.dracrays.fakeloc", "net.anylocation.ultra", "com.wifi99.android.locationcheater", "com.dingweizshou", "top.a1024bytes.mockloc.ca.pro", "com.txy.anywhere.clone", "com.dracrays.fakelocc", "com.tandy.android.mockwxlocation", "net.anylocation", "com.sigma_rt.totalcontrol", "com.chuangdian.ipjl2"};

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(Context context) {
        Boolean bool = Boolean.FALSE;
        int i2 = 0;
        while (true) {
            try {
                String[] strArr = b;
                if (i2 >= strArr.length) {
                    break;
                } else if (BaseInfo.isPkgInstalled(context, strArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            } catch (Exception unused) {
            }
        }
        return bool.booleanValue();
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
