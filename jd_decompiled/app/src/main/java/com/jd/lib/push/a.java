package com.jd.lib.push;

import android.app.Application;
import android.text.TextUtils;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdpush_new.j.c;
import com.jingdong.jdpush_new.j.f;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.i;
import com.jingdong.jdpush_new.j.l;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes16.dex */
public class a {
    public static void a() {
        try {
            Application application = JdSdk.getInstance().getApplication();
            String b = f.b(application);
            if (TextUtils.equals(b, PushMessageUtils.getSavedOpenPushStatus(application))) {
                return;
            }
            g.h("push \u524d\u540e\u53f0\u5207\u6362\u4e0a\u62a5\u901a\u77e5\u5f00\u542f\u72b6\u6001\u53d1\u751f\u53d8\u5316\uff0c\u5f53\u524d\u5f00\u542f\uff1a" + b);
            int a = l.a();
            if (a != 0) {
                String mIRegId = PushMessageUtils.getMIRegId(application, a);
                if (TextUtils.isEmpty(mIRegId)) {
                    g.c("push \u524d\u540e\u53f0\u5207\u6362\u4e0a\u62a5\u901a\u77e5\u5f00\u542f\u72b6\u6001\u53d8\u5316\uff0c\u4f46\u662f\u6ca1\u6709\u5382\u5546\u901a\u9053DT");
                } else {
                    com.jingdong.jdpush_new.a.f(application, a, mIRegId, Integer.valueOf(b).intValue());
                }
            }
            com.jingdong.jdpush_new.a.f(application, 0, c.i(application), Integer.valueOf(b).intValue());
            i.d().f(application, "jsp_openpush", b);
            PushMessageUtils.saveOpenPush(b);
        } catch (Throwable th) {
            g.f("push \u524d\u540e\u53f0\u5207\u6362\u4e0a\u62a5\u901a\u77e5\u5f00\u542f\u72b6\u6001\u53d8\u5316\u65f6\u51fa\u9519", th);
        }
    }

    public static void b(int i2, String str) {
        try {
            Application application = JdSdk.getInstance().getApplication();
            com.jingdong.jdpush_new.mta.b.b().c().romDT = str;
            if (TextUtils.isEmpty(str)) {
                g.c("push \u6ce8\u518cDT\u5931\u8d25:\u83b7\u53d6\u5230\u7684token\u662f\u7a7a\u7684\uff0cdeviceModel=" + i2);
                com.jingdong.jdpush_new.mta.b.b().l((i2 * 1000) + 210);
                com.jingdong.jdpush_new.mta.b.b().c().romException = "\u6ce8\u518cDT\u5931\u8d25:\u83b7\u53d6\u5230\u7684token\u662f\u7a7a\u7684";
                com.jingdong.jdpush_new.mta.b.b().g(application);
                return;
            }
            g.c("push check regId \uff1a" + str);
            if (TextUtils.equals(i.c(application, i2), str) && !PushMessageUtils.isAPPVersionChange() && TextUtils.equals(PushMessageUtils.getSavedOpenPushStatus(application), f.b(application))) {
                g.a("DT\u65e0\u9700\u91cd\u590d\u6ce8\u518c");
                com.jingdong.jdpush_new.mta.b.b().l((i2 * 1000) + 220);
                com.jingdong.jdpush_new.mta.b.b().g(application);
                return;
            }
            String b = f.b(application);
            g.a("push \u5f02\u6b65\u6ce8\u518cDT\uff1a" + str + "  , \u901a\u77e5\u5f00\u5173\u72b6\u6001=" + b);
            com.jingdong.jdpush_new.a.f(application, i2, str, Integer.valueOf(b).intValue());
            i.h(application, i2, str);
            PushMessageUtils.saveMIRegId(application, str, i2);
            PushMessageUtils.saveOpenPush(b);
            if (UserUtil.getWJLoginHelper().hasLogin()) {
                com.jingdong.jdpush_new.a.a(application, i2, UserUtil.getWJLoginHelper().getPin(), str);
            } else {
                com.jingdong.jdpush_new.a.a(application, i2, "", str);
            }
        } catch (Throwable th) {
            g.g(th);
        }
    }
}
