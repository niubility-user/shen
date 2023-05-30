package com.jd.jdsec.c.h.c;

import android.os.Build;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class j {
    public static long a = com.jd.jdsec.a.l.d.a("lastfixinfotime", 0);

    static {
        com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", "lastFixInfoReportTime get from sharedPreferences = " + a);
    }

    public static boolean a() {
        String c2 = com.jd.jdsec.a.l.d.c(CustomThemeConstance.NAVI_MODEL, "");
        if (!c2.equals("")) {
            String deviceModel = BaseInfo.getDeviceModel();
            if (!c2.equals(deviceModel)) {
                com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fmodel\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c2, deviceModel));
                return false;
            }
        }
        String c3 = com.jd.jdsec.a.l.d.c("board", "");
        if (!c3.equals("") && !c3.equals(Build.BOARD)) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fboard\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c3, Build.BOARD));
            return false;
        }
        String c4 = com.jd.jdsec.a.l.d.c(JDNetCacheManager.BRAND_BIZKEY, "");
        if (!c4.equals("") && !c4.equals(BaseInfo.getDeviceBrand())) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fbrand\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c4, BaseInfo.getDeviceBrand()));
            return false;
        }
        String c5 = com.jd.jdsec.a.l.d.c("cputype", "");
        if (!c5.equals("") && !c5.equals(BaseInfo.getCpuName())) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fcputype\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c5, BaseInfo.getCpuName()));
            return false;
        }
        String c6 = com.jd.jdsec.a.l.d.c("screen", "");
        if (!c6.equals("") && !c6.equals(BaseInfo.getRealScreenSize().toString())) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fscreen\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c6, BaseInfo.getRealScreenSize()));
            return false;
        }
        String c7 = com.jd.jdsec.a.l.d.c("physicalcpu", "");
        if (!c7.equals("") && !c7.equals(String.valueOf(com.jd.jdsec.a.g.p()))) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fphysicalcpu\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c7, Integer.valueOf(com.jd.jdsec.a.g.p())));
            return false;
        }
        String c8 = com.jd.jdsec.a.l.d.c("memSize", "");
        if (!c8.equals("") && !c8.equals(com.jd.jdsec.a.g.G(com.jd.jdsec.c.g.a))) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fmemSize\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c8, com.jd.jdsec.a.g.G(com.jd.jdsec.c.g.a)));
            return false;
        }
        String c9 = com.jd.jdsec.a.l.d.c("totaldiskspace", "");
        if (!c9.equals("") && !c9.equals(com.jd.jdsec.a.g.F(com.jd.jdsec.c.g.a))) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606ftotaldiskspace\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c9, com.jd.jdsec.a.g.F(com.jd.jdsec.c.g.a)));
            return false;
        }
        String c10 = com.jd.jdsec.a.l.d.c("maxCpuFrequency", "");
        if (!c10.equals("") && !c10.equals(com.jd.jdsec.a.g.m())) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fmaxCpuFrequency\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c10, com.jd.jdsec.a.g.m()));
            return false;
        }
        String c11 = com.jd.jdsec.a.l.d.c("minCpuFrequency", "");
        if (!c11.equals("") && !c11.equals(com.jd.jdsec.a.g.n())) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fminCpuFrequency\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c11, com.jd.jdsec.a.g.n()));
            return false;
        }
        String c12 = com.jd.jdsec.a.l.d.c("hardware", "");
        if (!c12.equals("")) {
            String str = Build.HARDWARE;
            if (!c12.equals(str)) {
                com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", String.format("\u786c\u4ef6\u4fe1\u606fhardware\u53d1\u751f\u53d8\u5316\uff0cold: %s - new: %s", c12, str));
                return false;
            }
        }
        com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", "\u786c\u4ef6\u4fe1\u606f\u6ca1\u6709\u68c0\u6d4b\u5230\u53d8\u66f4\uff0c\u5f53\u524d\u8ddf\u7f13\u5b58\u4e00\u81f4 \u6216\u8005\u7b2c\u4e00\u6b21\uff0c\u7f13\u5b58\u4e2d\u53d6\u5230\u7684\u90fd\u662f\u7a7a\u5b57\u7b26\u4e32");
        return true;
    }

    public static boolean b() {
        long b = com.jd.jdsec.c.c.c().b();
        long currentTimeMillis = System.currentTimeMillis() - a;
        boolean z = currentTimeMillis >= (60 * b) * 1000;
        com.jd.jdsec.a.l.b.e("JDSec.Security.SendController", "timeShould = " + z + ", \u65f6\u95f4\u95f4\u9694\u9650\u5236\uff1a" + b + " \u5206\u949f , \u771f\u5b9e\u95f4\u9694 = " + currentTimeMillis + " ms");
        if (z) {
            long currentTimeMillis2 = System.currentTimeMillis();
            a = currentTimeMillis2;
            com.jd.jdsec.a.l.d.d("lastfixinfotime", currentTimeMillis2);
        }
        return z || !a();
    }
}
