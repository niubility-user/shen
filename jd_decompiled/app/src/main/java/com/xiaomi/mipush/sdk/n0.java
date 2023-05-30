package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.xiaomi.push.a8;
import com.xiaomi.push.a9;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.y4;
import com.xiaomi.push.z6;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class n0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18399g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f18400h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n0(Context context, boolean z) {
        this.f18399g = context;
        this.f18400h = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        String g2;
        String g3;
        Map<String, String> map;
        String g4;
        String str;
        String f2;
        String f3;
        g.j.a.a.a.c.o("do sync info");
        c8 c8Var = new c8(com.xiaomi.push.service.f0.a(), false);
        o0 c2 = o0.c(this.f18399g);
        c8Var.c(m7.SyncInfo.f179a);
        c8Var.b(c2.d());
        c8Var.d(this.f18399g.getPackageName());
        HashMap hashMap = new HashMap();
        c8Var.f113a = hashMap;
        Context context = this.f18399g;
        a9.c(hashMap, ApplicationUpgradeHelper.APP_VERSION, y4.h(context, context.getPackageName()));
        Map<String, String> map2 = c8Var.f113a;
        Context context2 = this.f18399g;
        a9.c(map2, "app_version_code", Integer.toString(y4.b(context2, context2.getPackageName())));
        a9.c(c8Var.f113a, "push_sdk_vn", "5_3_0-C");
        a9.c(c8Var.f113a, "push_sdk_vc", Integer.toString(50300));
        a9.c(c8Var.f113a, "token", c2.m());
        if (!a8.t()) {
            String b = com.xiaomi.push.p0.b(z6.s(this.f18399g));
            String w = z6.w(this.f18399g);
            if (!TextUtils.isEmpty(w)) {
                b = b + DYConstants.DY_REGEX_COMMA + w;
            }
            if (!TextUtils.isEmpty(b)) {
                a9.c(c8Var.f113a, "imei_md5", b);
            }
        }
        com.xiaomi.push.z.a(this.f18399g).d(c8Var.f113a);
        a9.c(c8Var.f113a, "reg_id", c2.q());
        a9.c(c8Var.f113a, "reg_secret", c2.t());
        a9.c(c8Var.f113a, "accept_time", m.t(this.f18399g).replace(DYConstants.DY_REGEX_COMMA, "-"));
        if (this.f18400h) {
            Map<String, String> map3 = c8Var.f113a;
            f2 = m0.f(m.u(this.f18399g));
            a9.c(map3, "aliases_md5", f2);
            Map<String, String> map4 = c8Var.f113a;
            f3 = m0.f(m.v(this.f18399g));
            a9.c(map4, "topics_md5", f3);
            map = c8Var.f113a;
            g4 = m0.f(m.w(this.f18399g));
            str = "accounts_md5";
        } else {
            Map<String, String> map5 = c8Var.f113a;
            g2 = m0.g(m.u(this.f18399g));
            a9.c(map5, "aliases", g2);
            Map<String, String> map6 = c8Var.f113a;
            g3 = m0.g(m.v(this.f18399g));
            a9.c(map6, "topics", g3);
            map = c8Var.f113a;
            g4 = m0.g(m.w(this.f18399g));
            str = "user_accounts";
        }
        a9.c(map, str, g4);
        f0.h(this.f18399g).y(c8Var, c7.Notification, false, null);
    }
}
