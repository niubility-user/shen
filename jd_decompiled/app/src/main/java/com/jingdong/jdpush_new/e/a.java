package com.jingdong.jdpush_new.e;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.push.utils.d;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdpush_new.j.g;

/* loaded from: classes12.dex */
public class a {
    public static synchronized com.jingdong.jdpush_new.g.c.a a(Context context) {
        com.jingdong.jdpush_new.g.c.a h2;
        synchronized (a.class) {
            com.jingdong.jdpush_new.d.c.b().d(context);
            h2 = com.jingdong.jdpush_new.f.a.k(context).h(com.jingdong.jdpush_new.j.c.d(context));
            if (h2 == null) {
                h2 = new com.jingdong.jdpush_new.g.c.a();
                h2.q("1");
                h2.m(com.jingdong.jdpush_new.j.c.q(context));
            } else {
                String c2 = h2.c();
                if (!TextUtils.isEmpty(c2) && c2.matches(d.c())) {
                    g.h("\u81ea\u5efa\u901a\u9053DT[" + c2 + "]\u65e0\u6548\uff0c\u89e3\u7ed1DT\u5e76\u91cd\u65b0\u751f\u6210");
                    if (UserUtil.getWJLoginHelper().hasLogin()) {
                        com.jingdong.jdpush_new.a.i(context, 0, UserUtil.getWJLoginHelper().getPin(), c2);
                    }
                    h2.m(com.jingdong.jdpush_new.j.c.q(context));
                    h2.q("1");
                }
                if ("0".equals(h2.g())) {
                    h2.q(com.jingdong.jdpush_new.j.c.r(context) ? "1" : "0");
                }
            }
            try {
                h2.k(com.jingdong.jdpush_new.j.c.d(context));
                h2.l(com.jingdong.jdpush_new.j.c.b(context));
                h2.r(com.jingdong.jdpush_new.j.c.o(context));
                h2.s(com.jingdong.jdpush_new.j.c.c());
                h2.o(com.jingdong.jdpush_new.j.c.l(context));
                h2.p(String.valueOf(System.currentTimeMillis()));
                com.jingdong.jdpush_new.f.a.k(context).m(h2);
            } catch (Exception e2) {
                g.g(e2);
            }
            com.jingdong.jdpush_new.d.c.b().c(h2);
        }
        return h2;
    }
}
