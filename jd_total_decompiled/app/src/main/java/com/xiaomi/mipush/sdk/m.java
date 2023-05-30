package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.xiaomi.mipush.sdk.o;
import com.xiaomi.push.a4;
import com.xiaomi.push.a8;
import com.xiaomi.push.a9;
import com.xiaomi.push.b4;
import com.xiaomi.push.c4;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d8;
import com.xiaomi.push.g2;
import com.xiaomi.push.h7;
import com.xiaomi.push.h8;
import com.xiaomi.push.j8;
import com.xiaomi.push.l4;
import com.xiaomi.push.l9;
import com.xiaomi.push.m7;
import com.xiaomi.push.p7;
import com.xiaomi.push.p9;
import com.xiaomi.push.q7;
import com.xiaomi.push.r2;
import com.xiaomi.push.r9;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.w7;
import com.xiaomi.push.y4;
import com.xiaomi.push.z6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public abstract class m {
    private static Context a;

    /* loaded from: classes11.dex */
    public interface a<R> {
    }

    @Deprecated
    /* loaded from: classes11.dex */
    public static abstract class b {
        private String a;

        public String a() {
            return this.a;
        }

        public void b(String str, long j2, String str2, List<String> list) {
        }

        public void c(long j2, String str, String str2) {
        }

        public void d(MiPushMessage miPushMessage) {
        }

        public void e(String str, String str2, String str3, boolean z) {
        }

        public void f(long j2, String str, String str2) {
        }

        public void g(long j2, String str, String str2) {
        }
    }

    static {
        System.currentTimeMillis();
    }

    public static boolean A(Context context) {
        k(context, AnnoConst.Constructor_Context);
        return s0.d(context).l(r0.ASSEMBLE_PUSH_COS);
    }

    public static boolean B(Context context) {
        return s0.d(context).l(r0.ASSEMBLE_PUSH_FTOS);
    }

    public static String C(Context context) {
        if (o0.c(context).s()) {
            return o0.c(context).q();
        }
        return null;
    }

    private static void D(Context context) {
        c4.o(new k());
        g.j.b.a.a c2 = c4.c(context);
        g.j.b.b.b.e(context).o("5_3_0-C");
        g.j.b.b.a.a(context, c2, new a4(context), new b4(context));
        r.b(context);
        g1.a(context, c2);
        com.xiaomi.push.service.b0.d(context).j(new l(100, "perf event job update", context));
    }

    public static void E(Context context, String str, String str2, b bVar, String str3, a aVar) {
        try {
            g.j.a.a.a.c.l(context.getApplicationContext());
            g.j.a.a.a.c.E("sdk_version = 5_3_0-C");
            com.xiaomi.push.z.a(context).c();
            g2.a(context);
            if (bVar != null) {
                PushMessageHandler.a(bVar);
            }
            if (aVar != null) {
                PushMessageHandler.a(aVar);
            }
            if (r9.g(a)) {
                i1.b(a);
            }
            boolean z = o0.c(a).a() != c.a();
            if (!z && !b0(a)) {
                f0.h(a).m();
                g.j.a.a.a.c.o("Could not send  register message within 5s repeatly .");
                return;
            }
            if (z || !o0.c(a).l(str, str2) || o0.c(a).x()) {
                String a2 = com.xiaomi.push.p0.a(6);
                o0.c(a).e();
                o0.c(a).f(c.a());
                o0.c(a).i(str, str2, a2);
                o.a.b().h("com.xiaomi.xmpushsdk.tinydataPending.appId");
                l(a);
                o(context);
                d8 d8Var = new d8();
                d8Var.a(com.xiaomi.push.service.f0.c());
                d8Var.b(str);
                d8Var.e(str2);
                d8Var.d(a.getPackageName());
                d8Var.f(a2);
                Context context2 = a;
                d8Var.c(y4.h(context2, context2.getPackageName()));
                Context context3 = a;
                d8Var.b(y4.b(context3, context3.getPackageName()));
                d8Var.h("5_3_0-C");
                d8Var.a(50300);
                d8Var.a(q7.Init);
                if (!TextUtils.isEmpty(str3)) {
                    d8Var.g(str3);
                }
                if (!a8.t()) {
                    String v = z6.v(a);
                    if (!TextUtils.isEmpty(v)) {
                        d8Var.i(com.xiaomi.push.p0.b(v) + DYConstants.DY_REGEX_COMMA + z6.x(a));
                    }
                }
                int c2 = z6.c();
                if (c2 >= 0) {
                    d8Var.c(c2);
                }
                f0.h(a).u(d8Var, z);
                a.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
            } else {
                if (1 == q.c(a)) {
                    k(bVar, "callback");
                    bVar.c(0L, null, o0.c(a).q());
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(o0.c(a).q());
                    q.f(a, q.a(l4.COMMAND_REGISTER.f172a, arrayList, 0L, null, null, null));
                }
                f0.h(a).m();
                if (o0.c(a).k()) {
                    c8 c8Var = new c8();
                    c8Var.b(o0.c(a).d());
                    c8Var.c(m7.ClientInfoUpdate.f179a);
                    c8Var.a(com.xiaomi.push.service.f0.a());
                    HashMap hashMap = new HashMap();
                    c8Var.f113a = hashMap;
                    Context context4 = a;
                    hashMap.put(ApplicationUpgradeHelper.APP_VERSION, y4.h(context4, context4.getPackageName()));
                    Map<String, String> map = c8Var.f113a;
                    Context context5 = a;
                    map.put("app_version_code", Integer.toString(y4.b(context5, context5.getPackageName())));
                    c8Var.f113a.put("push_sdk_vn", "5_3_0-C");
                    c8Var.f113a.put("push_sdk_vc", Integer.toString(50300));
                    String v2 = o0.c(a).v();
                    if (!TextUtils.isEmpty(v2)) {
                        c8Var.f113a.put("deviceid", v2);
                    }
                    f0.h(a).y(c8Var, c7.Notification, false, null);
                    f0.h(a).q(a);
                }
                if (!a9.d(a, "update_devId", false)) {
                    h0();
                    a9.b(a, "update_devId", true);
                }
                if (c0(a) && a0(a)) {
                    c8 c8Var2 = new c8();
                    c8Var2.b(o0.c(a).d());
                    c8Var2.c(m7.PullOfflineMessage.f179a);
                    c8Var2.a(com.xiaomi.push.service.f0.a());
                    c8Var2.a(false);
                    f0.h(a).z(c8Var2, c7.Notification, false, null, false);
                    g(a);
                }
            }
            h(a);
            V();
            U(a);
            D(a);
            m0.b(a);
            if (!a.getPackageName().equals("com.xiaomi.xmsf")) {
                if (g.a() != null) {
                    g.c(a, g.a());
                }
                g.j.a.a.a.c.h(2);
            }
            F(context);
        } catch (Throwable th) {
            g.j.a.a.a.c.s(th);
        }
    }

    private static void F(Context context) {
        if ("syncing".equals(x.b(a).c(l0.DISABLE_PUSH))) {
            r(a);
        }
        if ("syncing".equals(x.b(a).c(l0.ENABLE_PUSH))) {
            s(a);
        }
        x b2 = x.b(a);
        l0 l0Var = l0.UPLOAD_HUAWEI_TOKEN;
        if ("syncing".equals(b2.c(l0Var))) {
            f0.h(a).E(null, l0Var, r0.ASSEMBLE_PUSH_HUAWEI, XView2Constants.XVIEW2_ACTION_INIT);
        }
        if ("syncing".equals(x.b(a).c(l0.UPLOAD_FCM_TOKEN))) {
            e0(a);
        }
        x b3 = x.b(a);
        l0 l0Var2 = l0.UPLOAD_COS_TOKEN;
        if ("syncing".equals(b3.c(l0Var2))) {
            f0.h(a).E(null, l0Var2, r0.ASSEMBLE_PUSH_COS, XView2Constants.XVIEW2_ACTION_INIT);
        }
        x b4 = x.b(a);
        l0 l0Var3 = l0.UPLOAD_FTOS_TOKEN;
        if ("syncing".equals(b4.c(l0Var3))) {
            f0.h(context).E(null, l0Var3, r0.ASSEMBLE_PUSH_FTOS, XView2Constants.XVIEW2_ACTION_INIT);
        }
    }

    public static void G(Context context, q7 q7Var) {
        g.j.a.a.a.c.E("re-register reason: " + q7Var);
        String a2 = com.xiaomi.push.p0.a(6);
        String d = o0.c(context).d();
        String m2 = o0.c(context).m();
        o0.c(context).e();
        m(context);
        o(context);
        o0.c(context).f(c.a());
        o0.c(context).i(d, m2, a2);
        d8 d8Var = new d8();
        d8Var.a(com.xiaomi.push.service.f0.c());
        d8Var.b(d);
        d8Var.e(m2);
        d8Var.f(a2);
        d8Var.d(context.getPackageName());
        d8Var.c(y4.h(context, context.getPackageName()));
        d8Var.b(y4.b(context, context.getPackageName()));
        d8Var.h("5_3_0-C");
        d8Var.a(50300);
        d8Var.a(q7Var);
        int c2 = z6.c();
        if (c2 >= 0) {
            d8Var.c(c2);
        }
        f0.h(context).u(d8Var, false);
    }

    private static void H(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            l9.a(context.getApplicationContext(), new NetworkStatusReceiver(null), intentFilter, 2);
        } catch (Throwable th) {
            g.j.a.a.a.c.o("dynamic register network status receiver failed:" + th);
        }
    }

    public static void I(Context context, String str, String str2) {
        J(context, str, str2, new p());
    }

    public static void J(Context context, String str, String str2, p pVar) {
        K(context, str, str2, pVar, null, null);
    }

    private static void K(Context context, String str, String str2, p pVar, String str3, a aVar) {
        k(context, AnnoConst.Constructor_Context);
        k(str, "appID");
        k(str2, "appToken");
        Context applicationContext = context.getApplicationContext();
        a = applicationContext;
        if (applicationContext == null) {
            a = context;
        }
        Context context2 = a;
        r9.e(context2);
        if (!NetworkStatusReceiver.a()) {
            H(a);
        }
        s0.d(a).f(pVar);
        com.xiaomi.push.i.b(context2).g(new h(str, str2, str3, aVar));
    }

    public static synchronized void L(Context context) {
        synchronized (m.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("accept_time");
            p9.a(edit);
        }
    }

    public static synchronized void M(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + str).commit();
        }
    }

    public static synchronized void N(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
        }
    }

    public static synchronized void O(Context context) {
        synchronized (m.class) {
            Iterator<String> it = w(context).iterator();
            while (it.hasNext()) {
                M(context, it.next());
            }
        }
    }

    public static synchronized void P(Context context) {
        synchronized (m.class) {
            Iterator<String> it = u(context).iterator();
            while (it.hasNext()) {
                N(context, it.next());
            }
        }
    }

    public static synchronized void Q(Context context) {
        synchronized (m.class) {
            Iterator<String> it = v(context).iterator();
            while (it.hasNext()) {
                R(context, it.next());
            }
        }
    }

    public static synchronized void R(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
        }
    }

    public static void S(Context context, String str, p7 p7Var, String str2, String str3) {
        c8 c8Var = new c8();
        if (TextUtils.isEmpty(str3)) {
            g.j.a.a.a.c.D("do not report clicked message");
            return;
        }
        c8Var.b(str3);
        c8Var.c("bar:click");
        c8Var.a(str);
        c8Var.a(false);
        f0.h(context).B(c8Var, c7.Notification, false, true, p7Var, true, str2, str3);
    }

    public static void T(Context context, String str, p7 p7Var, String str2) {
        c8 c8Var = new c8();
        if (TextUtils.isEmpty(str2)) {
            if (!o0.c(context).p()) {
                g.j.a.a.a.c.D("do not report clicked message");
                return;
            }
            str2 = o0.c(context).d();
        }
        c8Var.b(str2);
        c8Var.c("bar:click");
        c8Var.a(str);
        c8Var.a(false);
        f0.h(context).y(c8Var, c7.Notification, false, p7Var);
    }

    private static void U(Context context) {
        if (com.xiaomi.push.service.b0.d(a).m(h7.DataCollectionSwitch.a(), x())) {
            r2.b().c(new e1(context));
            com.xiaomi.push.i.b(a).h(new i(), 10);
        }
    }

    private static void V() {
        com.xiaomi.push.i.b(a).k(new w(a), com.xiaomi.push.service.b0.d(a).a(h7.OcVersionCheckFrequency.a(), RemoteMessageConst.DEFAULT_TTL), 5);
    }

    public static void W(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        X(context, l4.COMMAND_SET_ALIAS.f172a, str, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0031, code lost:
        if (1 == com.xiaomi.mipush.sdk.q.c(r12)) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0033, code lost:
        com.xiaomi.mipush.sdk.PushMessageHandler.a(r12, r15, r13, 0, null, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x003e, code lost:
        com.xiaomi.mipush.sdk.q.f(r12, com.xiaomi.mipush.sdk.q.a(r0.f172a, r6, 0, null, r15, null));
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00a9, code lost:
        if (1 == com.xiaomi.mipush.sdk.q.c(r12)) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected static void X(Context context, String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        l4 l4Var = l4.COMMAND_SET_ALIAS;
        if (!l4Var.f172a.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - j(context, str2)) >= 86400000) {
            if (!l4.COMMAND_UNSET_ALIAS.f172a.equalsIgnoreCase(str) || j(context, str2) >= 0) {
                l4Var = l4.COMMAND_SET_ACCOUNT;
                if (!l4Var.f172a.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - c(context, str2)) >= 3600000) {
                    if (!l4.COMMAND_UNSET_ACCOUNT.f172a.equalsIgnoreCase(str) || c(context, str2) >= 0) {
                        Y(context, str, arrayList, str3);
                        return;
                    } else {
                        sb = new StringBuilder();
                        str4 = "Don't cancel account for ";
                    }
                }
            } else {
                sb = new StringBuilder();
                str4 = "Don't cancel alias for ";
            }
            sb.append(str4);
            sb.append(com.xiaomi.push.p0.c(arrayList.toString(), 3));
            sb.append(" is unseted");
            g.j.a.a.a.c.o(sb.toString());
        }
    }

    protected static void Y(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (TextUtils.isEmpty(o0.c(context).d())) {
            return;
        }
        w7 w7Var = new w7();
        String a2 = com.xiaomi.push.service.f0.a();
        w7Var.a(a2);
        w7Var.b(o0.c(context).d());
        w7Var.c(str);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            w7Var.m175a(it.next());
        }
        w7Var.e(str2);
        w7Var.d(context.getPackageName());
        g.j.a.a.a.c.E("cmd:" + str + ", " + a2);
        f0.h(context).w(w7Var, c7.Command, null);
    }

    public static void Z(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        X(context, l4.COMMAND_SET_ACCOUNT.f172a, str, str2);
    }

    private static boolean a0(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1L)) > 300000;
    }

    private static boolean b0(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1L)) > Final.FIVE_SECOND;
    }

    public static long c(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + str, -1L);
    }

    public static boolean c0(Context context) {
        return f0.h(context).J();
    }

    public static synchronized void d(Context context, String str, String str2) {
        synchronized (m.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString("accept_time", str + DYConstants.DY_REGEX_COMMA + str2);
            p9.a(edit);
        }
    }

    public static void d0(Context context, String str, String str2) {
        if (TextUtils.isEmpty(o0.c(context).d()) || TextUtils.isEmpty(str)) {
            return;
        }
        if (Math.abs(System.currentTimeMillis() - f0(context, str)) <= 86400000) {
            if (1 == q.c(context)) {
                PushMessageHandler.a(context, str2, 0L, null, str);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            q.f(context, q.a(l4.COMMAND_SUBSCRIBE_TOPIC.f172a, arrayList, 0L, null, null, null));
            return;
        }
        h8 h8Var = new h8();
        String a2 = com.xiaomi.push.service.f0.a();
        h8Var.a(a2);
        h8Var.b(o0.c(context).d());
        h8Var.c(str);
        h8Var.d(context.getPackageName());
        h8Var.e(str2);
        g.j.a.a.a.c.E("cmd:" + l4.COMMAND_SUBSCRIBE_TOPIC + ", " + a2);
        f0.h(context).w(h8Var, c7.Subscription, null);
    }

    public static synchronized void e(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void e0(Context context) {
        f0.h(context).E(null, l0.UPLOAD_FCM_TOKEN, r0.ASSEMBLE_PUSH_FCM, "");
    }

    public static synchronized void f(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static long f0(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1L);
    }

    private static void g(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        p9.a(edit);
    }

    public static void g0(Context context) {
        v0.n(context);
        com.xiaomi.push.service.b0.d(context).h();
        if (o0.c(context).p()) {
            j8 j8Var = new j8();
            j8Var.a(com.xiaomi.push.service.f0.a());
            j8Var.b(o0.c(context).d());
            j8Var.c(o0.c(context).q());
            j8Var.e(o0.c(context).m());
            j8Var.d(context.getPackageName());
            f0.h(context).v(j8Var);
            PushMessageHandler.a();
            PushMessageHandler.b();
            o0.c(context).n();
            n(context);
            o(context);
            l(context);
        }
    }

    private static void h(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        p9.a(edit);
    }

    private static void h0() {
        new Thread(new j()).start();
    }

    public static synchronized void i(Context context, String str) {
        synchronized (m.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static long j(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1L);
    }

    private static void k(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException("param " + str + " is not nullable");
    }

    public static void l(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    private static void m(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        Iterator<String> it = u(context).iterator();
        while (it.hasNext()) {
            edit.remove("alias_" + it.next());
        }
        Iterator<String> it2 = w(context).iterator();
        while (it2.hasNext()) {
            edit.remove("account_" + it2.next());
        }
        Iterator<String> it3 = v(context).iterator();
        while (it3.hasNext()) {
            edit.remove("topic_" + it3.next());
        }
        edit.remove("accept_time");
        edit.commit();
    }

    public static void n(Context context) {
        f0.h(context).b0();
    }

    public static void o(Context context) {
        f0.h(context).n(-1);
    }

    public static void p(Context context, int i2) {
        f0.h(context).n(i2);
    }

    public static void q(Context context, String str, String str2) {
        f0.h(context).G(str, str2);
    }

    public static void r(Context context) {
        f0.h(context).H(true);
    }

    public static void s(Context context) {
        f0.h(context).H(false);
    }

    public static String t(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString("accept_time", "00:00-23:59");
    }

    public static List<String> u(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> v(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> w(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring(8));
            }
        }
        return arrayList;
    }

    private static boolean x() {
        return a8.p();
    }

    public static boolean y(Context context) {
        k(context, AnnoConst.Constructor_Context);
        return s0.d(context).l(r0.ASSEMBLE_PUSH_FCM);
    }

    public static boolean z(Context context) {
        k(context, AnnoConst.Constructor_Context);
        return s0.d(context).l(r0.ASSEMBLE_PUSH_HUAWEI);
    }
}
