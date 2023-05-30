package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.tencent.connect.common.Constants;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.b8;
import com.xiaomi.push.c4;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d4;
import com.xiaomi.push.e8;
import com.xiaomi.push.g8;
import com.xiaomi.push.h7;
import com.xiaomi.push.i8;
import com.xiaomi.push.k8;
import com.xiaomi.push.l4;
import com.xiaomi.push.l8;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.o7;
import com.xiaomi.push.p7;
import com.xiaomi.push.p9;
import com.xiaomi.push.q7;
import com.xiaomi.push.s7;
import com.xiaomi.push.s8;
import com.xiaomi.push.service.g2;
import com.xiaomi.push.t7;
import com.xiaomi.push.w1;
import com.xiaomi.push.x7;
import com.xiaomi.push.y4;
import com.xiaomi.push.y7;
import com.xiaomi.push.z7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;

/* loaded from: classes11.dex */
public class d0 {
    private static d0 b;

    /* renamed from: c  reason: collision with root package name */
    private static Queue<String> f18370c;
    private static Object d = new Object();
    private Context a;

    private d0(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        if (applicationContext == null) {
            this.a = context;
        }
    }

    public static Intent a(Context context, String str, Map<String, String> map, int i2) {
        return com.xiaomi.push.service.n.P(context, str, map, i2);
    }

    private PushMessageHandler.a c(y7 y7Var, boolean z, byte[] bArr, String str, int i2, Intent intent) {
        d4 a;
        String packageName;
        String j2;
        int i3;
        String str2;
        MiPushMessage miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        miPushMessage = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        miPushMessage = null;
        ArrayList arrayList3 = null;
        miPushMessage = null;
        try {
            n8 d2 = z.d(this.a, y7Var);
            if (d2 == null) {
                g.j.a.a.a.c.D("receiving an un-recognized message. " + y7Var.a);
                d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, "18");
                f1.f(this.a, y7Var, z);
                return null;
            }
            c7 a2 = y7Var.a();
            g.j.a.a.a.c.r("processing a message, action=", a2, ", hasNotified=", Boolean.valueOf(z));
            switch (e0.a[a2.ordinal()]) {
                case 1:
                    if (!y7Var.m193b()) {
                        g.j.a.a.a.c.D("receiving an un-encrypt message(SendMessage).");
                        return null;
                    } else if (o0.c(this.a).w() && !z) {
                        g.j.a.a.a.c.o("receive a message in pause state. drop it");
                        d4.a(this.a).h(this.a.getPackageName(), c4.j(i2), str, "12");
                        return null;
                    } else {
                        g8 g8Var = (g8) d2;
                        o7 a3 = g8Var.a();
                        if (a3 == null) {
                            g.j.a.a.a.c.D("receive an empty message without push content, drop it");
                            d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, "22");
                            f1.g(this.a, y7Var, z);
                            return null;
                        }
                        int intExtra = intent.getIntExtra("notification_click_button", 0);
                        if (z) {
                            if (com.xiaomi.push.service.n.J(y7Var)) {
                                m.S(this.a, a3.m112a(), y7Var.m185a(), y7Var.b, a3.b());
                            } else {
                                p7 p7Var = y7Var.m185a() != null ? new p7(y7Var.m185a()) : new p7();
                                if (p7Var.m121a() == null) {
                                    p7Var.a(new HashMap());
                                }
                                p7Var.m121a().put("notification_click_button", String.valueOf(intExtra));
                                m.T(this.a, a3.m112a(), p7Var, a3.b());
                            }
                        }
                        if (!z) {
                            if (!TextUtils.isEmpty(g8Var.d()) && m.j(this.a, g8Var.d()) < 0) {
                                m.f(this.a, g8Var.d());
                            } else if (!TextUtils.isEmpty(g8Var.c()) && m.f0(this.a, g8Var.c()) < 0) {
                                m.i(this.a, g8Var.c());
                            }
                        }
                        p7 p7Var2 = y7Var.f263a;
                        String str3 = (p7Var2 == null || p7Var2.m121a() == null) ? null : y7Var.f263a.f195a.get("jobkey");
                        String str4 = str3;
                        if (TextUtils.isEmpty(str3)) {
                            str3 = a3.m112a();
                        }
                        if (z || !m(this.a, str3)) {
                            MiPushMessage b2 = q.b(g8Var, y7Var.m185a(), z);
                            if (b2.getPassThrough() == 0 && !z && com.xiaomi.push.service.n.L(b2.getExtra())) {
                                com.xiaomi.push.service.n.r(this.a, y7Var, bArr);
                                return null;
                            }
                            String u = com.xiaomi.push.service.n.u(b2.getExtra(), intExtra);
                            g.j.a.a.a.c.r("receive a message, msgid=", a3.m112a(), ", jobkey=", str3, ", btn=", Integer.valueOf(intExtra), ", typeId=", u, ", hasNotified=", Boolean.valueOf(z));
                            if (z && b2.getExtra() != null && !TextUtils.isEmpty(u)) {
                                Map<String, String> extra = b2.getExtra();
                                if (intExtra != 0 && y7Var.m185a() != null) {
                                    f0.h(this.a).o(y7Var.m185a().c(), intExtra);
                                }
                                if (com.xiaomi.push.service.n.J(y7Var)) {
                                    Intent a4 = a(this.a, y7Var.b, extra, intExtra);
                                    a4.putExtra("eventMessageType", i2);
                                    a4.putExtra("messageId", str);
                                    a4.putExtra("jobkey", str4);
                                    if (a4 == null) {
                                        g.j.a.a.a.c.o("Getting Intent fail from ignore reg message. ");
                                        d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR);
                                        return null;
                                    }
                                    String c2 = a3.c();
                                    if (!TextUtils.isEmpty(c2)) {
                                        a4.putExtra("payload", c2);
                                    }
                                    this.a.startActivity(a4);
                                    f1.b(this.a, y7Var);
                                    d4.a(this.a).g(this.a.getPackageName(), c4.j(i2), str, 3006, u);
                                    g.j.a.a.a.c.p("PushMessageProcessor", "start business activity succ");
                                } else {
                                    Context context = this.a;
                                    Intent a5 = a(context, context.getPackageName(), extra, intExtra);
                                    if (a5 != null) {
                                        if (!u.equals(com.xiaomi.push.service.m0.f19126c)) {
                                            a5.putExtra("key_message", b2);
                                            a5.putExtra("eventMessageType", i2);
                                            a5.putExtra("messageId", str);
                                            a5.putExtra("jobkey", str4);
                                        }
                                        this.a.startActivity(a5);
                                        f1.b(this.a, y7Var);
                                        g.j.a.a.a.c.p("PushMessageProcessor", "start activity succ");
                                        d4.a(this.a).g(this.a.getPackageName(), c4.j(i2), str, 1006, u);
                                        if (u.equals(com.xiaomi.push.service.m0.f19126c)) {
                                            d4.a(this.a).h(this.a.getPackageName(), c4.j(i2), str, "13");
                                        }
                                    } else {
                                        g.j.a.a.a.c.C("PushMessageProcessor", "missing target intent for message: " + a3.m112a() + ", typeId=" + u);
                                    }
                                }
                                g.j.a.a.a.c.p("PushMessageProcessor", "pre-def msg process done.");
                                return null;
                            }
                            miPushMessage = b2;
                        } else {
                            g.j.a.a.a.c.o("drop a duplicate message, key=" + str3);
                            d4.a(this.a).j(this.a.getPackageName(), c4.j(i2), str, "2:" + str3);
                        }
                        if (y7Var.m185a() == null && !z) {
                            k(g8Var, y7Var);
                            break;
                        }
                    }
                    break;
                case 2:
                    e8 e8Var = (e8) d2;
                    String str5 = o0.c(this.a).d;
                    if (TextUtils.isEmpty(str5) || !TextUtils.equals(str5, e8Var.m48a())) {
                        g.j.a.a.a.c.o("bad Registration result:");
                        d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, "21");
                        return null;
                    }
                    long b3 = f0.h(this.a).b();
                    if (b3 > 0 && SystemClock.elapsedRealtime() - b3 > 900000) {
                        g.j.a.a.a.c.o("The received registration result has expired.");
                        d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, Constants.VIA_REPORT_TYPE_CHAT_VIDEO);
                        return null;
                    }
                    o0.c(this.a).d = null;
                    int i4 = (e8Var.f130a > 0L ? 1 : (e8Var.f130a == 0L ? 0 : -1));
                    Context context2 = this.a;
                    if (i4 == 0) {
                        o0.c(context2).o(e8Var.f18588e, e8Var.f18589f, e8Var.f18595l);
                        d.a(this.a);
                        a = d4.a(this.a);
                        packageName = this.a.getPackageName();
                        j2 = c4.j(i2);
                        i3 = 6006;
                        str2 = "1";
                    } else {
                        a = d4.a(context2);
                        packageName = this.a.getPackageName();
                        j2 = c4.j(i2);
                        i3 = 6006;
                        str2 = "2";
                    }
                    a.g(packageName, j2, str, i3, str2);
                    if (!TextUtils.isEmpty(e8Var.f18588e)) {
                        arrayList3 = new ArrayList();
                        arrayList3.add(e8Var.f18588e);
                    }
                    MiPushCommandMessage a6 = q.a(l4.COMMAND_REGISTER.f172a, arrayList3, e8Var.f130a, e8Var.d, null, e8Var.m49a());
                    f0.h(this.a).W();
                    return a6;
                case 3:
                    if (!y7Var.m193b()) {
                        g.j.a.a.a.c.D("receiving an un-encrypt message(UnRegistration).");
                        return null;
                    }
                    if (((k8) d2).a == 0) {
                        o0.c(this.a).e();
                        m.l(this.a);
                    }
                    PushMessageHandler.a();
                    break;
                case 4:
                    i8 i8Var = (i8) d2;
                    if (i8Var.a == 0) {
                        m.i(this.a, i8Var.b());
                    }
                    if (!TextUtils.isEmpty(i8Var.b())) {
                        arrayList2 = new ArrayList();
                        arrayList2.add(i8Var.b());
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("resp-cmd:");
                    l4 l4Var = l4.COMMAND_SUBSCRIBE_TOPIC;
                    sb.append(l4Var);
                    sb.append(", ");
                    sb.append(i8Var.a());
                    g.j.a.a.a.c.E(sb.toString());
                    return q.a(l4Var.f172a, arrayList2, i8Var.a, i8Var.d, i8Var.c(), null);
                case 5:
                    l8 l8Var = (l8) d2;
                    if (l8Var.a == 0) {
                        m.R(this.a, l8Var.b());
                    }
                    if (!TextUtils.isEmpty(l8Var.b())) {
                        arrayList = new ArrayList();
                        arrayList.add(l8Var.b());
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("resp-cmd:");
                    l4 l4Var2 = l4.COMMAND_UNSUBSCRIBE_TOPIC;
                    sb2.append(l4Var2);
                    sb2.append(", ");
                    sb2.append(l8Var.a());
                    g.j.a.a.a.c.E(sb2.toString());
                    return q.a(l4Var2.f172a, arrayList, l8Var.a, l8Var.d, l8Var.c(), null);
                case 6:
                    w1.f(this.a.getPackageName(), this.a, d2, c7.Command, bArr.length);
                    x7 x7Var = (x7) d2;
                    String b4 = x7Var.b();
                    List<String> m178a = x7Var.m178a();
                    if (x7Var.a == 0) {
                        if (TextUtils.equals(b4, l4.COMMAND_SET_ACCEPT_TIME.f172a) && m178a != null && m178a.size() > 1) {
                            m.d(this.a, m178a.get(0), m178a.get(1));
                            if ("00:00".equals(m178a.get(0)) && "00:00".equals(m178a.get(1))) {
                                o0.c(this.a).j(true);
                            } else {
                                o0.c(this.a).j(false);
                            }
                            m178a = f(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), m178a);
                        } else if (TextUtils.equals(b4, l4.COMMAND_SET_ALIAS.f172a) && m178a != null && m178a.size() > 0) {
                            m.f(this.a, m178a.get(0));
                        } else if (TextUtils.equals(b4, l4.COMMAND_UNSET_ALIAS.f172a) && m178a != null && m178a.size() > 0) {
                            m.N(this.a, m178a.get(0));
                        } else if (TextUtils.equals(b4, l4.COMMAND_SET_ACCOUNT.f172a) && m178a != null && m178a.size() > 0) {
                            m.e(this.a, m178a.get(0));
                        } else if (TextUtils.equals(b4, l4.COMMAND_UNSET_ACCOUNT.f172a) && m178a != null && m178a.size() > 0) {
                            m.M(this.a, m178a.get(0));
                        } else if (TextUtils.equals(b4, l4.COMMAND_CHK_VDEVID.f172a)) {
                            return null;
                        }
                    }
                    List<String> list = m178a;
                    g.j.a.a.a.c.E("resp-cmd:" + b4 + ", " + x7Var.a());
                    return q.a(b4, list, x7Var.a, x7Var.d, x7Var.c(), null);
                case 7:
                    w1.f(this.a.getPackageName(), this.a, d2, c7.Notification, bArr.length);
                    if (d2 instanceof t7) {
                        t7 t7Var = (t7) d2;
                        String a7 = t7Var.a();
                        g.j.a.a.a.c.E("resp-type:" + t7Var.b() + ", code:" + t7Var.a + ", " + a7);
                        if (m7.DisablePushMessage.f179a.equalsIgnoreCase(t7Var.d)) {
                            if (t7Var.a == 0) {
                                synchronized (x.class) {
                                    if (x.b(this.a).f(a7)) {
                                        x.b(this.a).h(a7);
                                        x b5 = x.b(this.a);
                                        l0 l0Var = l0.DISABLE_PUSH;
                                        if ("syncing".equals(b5.c(l0Var))) {
                                            x.b(this.a).d(l0Var, "synced");
                                            m.o(this.a);
                                            m.n(this.a);
                                            PushMessageHandler.a();
                                            f0.h(this.a).N();
                                        }
                                    }
                                }
                                break;
                            } else if ("syncing".equals(x.b(this.a).c(l0.DISABLE_PUSH))) {
                                synchronized (x.class) {
                                    if (x.b(this.a).f(a7)) {
                                        if (x.b(this.a).a(a7) < 10) {
                                            x.b(this.a).g(a7);
                                            f0.h(this.a).I(true, a7);
                                        } else {
                                            x.b(this.a).h(a7);
                                        }
                                    }
                                }
                                break;
                            }
                        } else if (m7.EnablePushMessage.f179a.equalsIgnoreCase(t7Var.d)) {
                            if (t7Var.a == 0) {
                                synchronized (x.class) {
                                    if (x.b(this.a).f(a7)) {
                                        x.b(this.a).h(a7);
                                        x b6 = x.b(this.a);
                                        l0 l0Var2 = l0.ENABLE_PUSH;
                                        if ("syncing".equals(b6.c(l0Var2))) {
                                            x.b(this.a).d(l0Var2, "synced");
                                        }
                                    }
                                }
                                break;
                            } else if ("syncing".equals(x.b(this.a).c(l0.ENABLE_PUSH))) {
                                synchronized (x.class) {
                                    if (x.b(this.a).f(a7)) {
                                        if (x.b(this.a).a(a7) < 10) {
                                            x.b(this.a).g(a7);
                                            f0.h(this.a).I(false, a7);
                                        } else {
                                            x.b(this.a).h(a7);
                                        }
                                    }
                                }
                                break;
                            }
                        } else if (m7.ThirdPartyRegUpdate.f179a.equalsIgnoreCase(t7Var.d)) {
                            o(t7Var);
                            break;
                        } else if (m7.UploadTinyData.f179a.equalsIgnoreCase(t7Var.d)) {
                            h(t7Var);
                            break;
                        }
                        x.b(this.a).h(a7);
                        break;
                    } else if (d2 instanceof c8) {
                        c8 c8Var = (c8) d2;
                        if ("registration id expired".equalsIgnoreCase(c8Var.d)) {
                            List<String> u2 = m.u(this.a);
                            List<String> v = m.v(this.a);
                            List<String> w = m.w(this.a);
                            String t = m.t(this.a);
                            g.j.a.a.a.c.E("resp-type:" + c8Var.d + ", " + c8Var.m34a());
                            m.G(this.a, q7.RegIdExpired);
                            for (String str6 : u2) {
                                m.N(this.a, str6);
                                m.W(this.a, str6, null);
                            }
                            for (String str7 : v) {
                                m.R(this.a, str7);
                                m.d0(this.a, str7, null);
                            }
                            for (String str8 : w) {
                                m.M(this.a, str8);
                                m.Z(this.a, str8, null);
                            }
                            String[] split = t.split(DYConstants.DY_REGEX_COMMA);
                            if (split.length == 2) {
                                m.L(this.a);
                                m.d(this.a, split[0], split[1]);
                                break;
                            }
                        } else if (m7.ClientInfoUpdateOk.f179a.equalsIgnoreCase(c8Var.d)) {
                            if (c8Var.m35a() != null && c8Var.m35a().containsKey(ApplicationUpgradeHelper.APP_VERSION)) {
                                o0.c(this.a).g(c8Var.m35a().get(ApplicationUpgradeHelper.APP_VERSION));
                                break;
                            }
                        } else if (m7.AwakeApp.f179a.equalsIgnoreCase(c8Var.d)) {
                            if (y7Var.m193b() && c8Var.m35a() != null && c8Var.m35a().containsKey("awake_info")) {
                                Context context3 = this.a;
                                b1.e(context3, o0.c(context3).d(), com.xiaomi.push.service.b0.d(this.a).a(h7.AwakeInfoUploadWaySwitch.a(), 0), c8Var.m35a().get("awake_info"));
                                break;
                            }
                        } else {
                            try {
                                if (m7.NormalClientConfigUpdate.f179a.equalsIgnoreCase(c8Var.d)) {
                                    b8 b8Var = new b8();
                                    m8.e(b8Var, c8Var.m40a());
                                    com.xiaomi.push.service.d0.d(com.xiaomi.push.service.b0.d(this.a), b8Var);
                                } else if (m7.CustomClientConfigUpdate.f179a.equalsIgnoreCase(c8Var.d)) {
                                    z7 z7Var = new z7();
                                    m8.e(z7Var, c8Var.m40a());
                                    com.xiaomi.push.service.d0.c(com.xiaomi.push.service.b0.d(this.a), z7Var);
                                } else if (m7.SyncInfoResult.f179a.equalsIgnoreCase(c8Var.d)) {
                                    m0.c(this.a, c8Var);
                                    break;
                                } else if (m7.ForceSync.f179a.equalsIgnoreCase(c8Var.d)) {
                                    g.j.a.a.a.c.o("receive force sync notification");
                                    m0.d(this.a, false);
                                    break;
                                } else if (m7.CancelPushMessage.f179a.equals(c8Var.d)) {
                                    g.j.a.a.a.c.E("resp-type:" + c8Var.d + ", " + c8Var.m34a());
                                    if (c8Var.m35a() != null) {
                                        int i5 = -2;
                                        if (c8Var.m35a().containsKey(com.xiaomi.push.service.m0.M)) {
                                            String str9 = c8Var.m35a().get(com.xiaomi.push.service.m0.M);
                                            if (!TextUtils.isEmpty(str9)) {
                                                try {
                                                    i5 = Integer.parseInt(str9);
                                                } catch (NumberFormatException e2) {
                                                    e2.printStackTrace();
                                                }
                                            }
                                        }
                                        if (i5 >= -1) {
                                            m.p(this.a, i5);
                                        } else {
                                            m.q(this.a, c8Var.m35a().containsKey(com.xiaomi.push.service.m0.K) ? c8Var.m35a().get(com.xiaomi.push.service.m0.K) : "", c8Var.m35a().containsKey(com.xiaomi.push.service.m0.L) ? c8Var.m35a().get(com.xiaomi.push.service.m0.L) : "");
                                        }
                                    }
                                    j(c8Var);
                                    break;
                                } else {
                                    try {
                                        if (m7.HybridRegisterResult.f179a.equals(c8Var.d)) {
                                            e8 e8Var2 = new e8();
                                            m8.e(e8Var2, c8Var.m40a());
                                            n.a(this.a, e8Var2);
                                        } else if (m7.HybridUnregisterResult.f179a.equals(c8Var.d)) {
                                            k8 k8Var = new k8();
                                            m8.e(k8Var, c8Var.m40a());
                                            n.b(this.a, k8Var);
                                        } else if (!m7.PushLogUpload.f179a.equals(c8Var.d)) {
                                            if (m7.DetectAppAlive.f179a.equals(c8Var.d)) {
                                                g.j.a.a.a.c.y("receive detect msg");
                                                q(c8Var);
                                                break;
                                            } else if (g2.b(c8Var)) {
                                                g.j.a.a.a.c.y("receive notification handle by cpra");
                                                break;
                                            }
                                        }
                                        break;
                                    } catch (s8 e3) {
                                        g.j.a.a.a.c.s(e3);
                                        break;
                                    }
                                }
                                break;
                            } catch (s8 unused) {
                                break;
                            }
                        }
                    }
                    break;
            }
            return miPushMessage;
        } catch (h1 e4) {
            g.j.a.a.a.c.s(e4);
            i(y7Var);
            d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, Constants.VIA_ACT_TYPE_NINETEEN);
            f1.f(this.a, y7Var, z);
            return null;
        } catch (s8 e5) {
            g.j.a.a.a.c.s(e5);
            g.j.a.a.a.c.D("receive a message which action string is not valid. is the reg expired?");
            d4.a(this.a).i(this.a.getPackageName(), c4.j(i2), str, "20");
            f1.f(this.a, y7Var, z);
            return null;
        }
    }

    private PushMessageHandler.a d(y7 y7Var, byte[] bArr) {
        String str;
        n8 d2;
        String str2 = null;
        try {
            d2 = z.d(this.a, y7Var);
        } catch (h1 e2) {
            g.j.a.a.a.c.s(e2);
            str = "message arrived: receive a message but decrypt failed. report when click.";
        } catch (s8 e3) {
            g.j.a.a.a.c.s(e3);
            str = "message arrived: receive a message which action string is not valid. is the reg expired?";
        }
        if (d2 == null) {
            g.j.a.a.a.c.D("message arrived: receiving an un-recognized message. " + y7Var.a);
            return null;
        }
        c7 a = y7Var.a();
        g.j.a.a.a.c.o("message arrived: processing an arrived message, action=" + a);
        if (e0.a[a.ordinal()] != 1) {
            return null;
        }
        if (y7Var.m193b()) {
            g8 g8Var = (g8) d2;
            o7 a2 = g8Var.a();
            if (a2 != null) {
                p7 p7Var = y7Var.f263a;
                if (p7Var != null && p7Var.m121a() != null) {
                    str2 = y7Var.f263a.f195a.get("jobkey");
                }
                MiPushMessage b2 = q.b(g8Var, y7Var.m185a(), false);
                b2.setArrivedMessage(true);
                g.j.a.a.a.c.o("message arrived: receive a message, msgid=" + a2.m112a() + ", jobkey=" + str2);
                return b2;
            }
            str = "message arrived: receive an empty message without push content, drop it";
        } else {
            str = "message arrived: receiving an un-encrypt message(SendMessage).";
        }
        g.j.a.a.a.c.D(str);
        return null;
    }

    public static d0 e(Context context) {
        if (b == null) {
            b = new d0(context);
        }
        return b;
    }

    private void g() {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong("last_reinitialize", 0L)) > 1800000) {
            m.G(this.a, q7.PackageUnregistered);
            sharedPreferences.edit().putLong("last_reinitialize", currentTimeMillis).commit();
        }
    }

    private void h(t7 t7Var) {
        String a = t7Var.a();
        g.j.a.a.a.c.y("receive ack " + a);
        Map<String, String> m165a = t7Var.m165a();
        if (m165a != null) {
            String str = m165a.get("real_source");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            g.j.a.a.a.c.y("receive ack : messageId = " + a + "  realSource = " + str);
            com.xiaomi.push.x0.a(this.a).b(a, str, Boolean.valueOf(t7Var.a == 0));
        }
    }

    private void i(y7 y7Var) {
        g.j.a.a.a.c.o("receive a message but decrypt failed. report now.");
        c8 c8Var = new c8(y7Var.m185a().f193a, false);
        c8Var.c(m7.DecryptMessageFail.f179a);
        c8Var.b(y7Var.m186a());
        c8Var.d(y7Var.b);
        HashMap hashMap = new HashMap();
        c8Var.f113a = hashMap;
        hashMap.put("regid", m.C(this.a));
        f0.h(this.a).y(c8Var, c7.Notification, false, null);
    }

    private void j(c8 c8Var) {
        t7 t7Var = new t7();
        t7Var.c(m7.CancelPushMessageACK.f179a);
        t7Var.a(c8Var.m34a());
        t7Var.a(c8Var.a());
        t7Var.b(c8Var.b());
        t7Var.e(c8Var.c());
        t7Var.a(0L);
        t7Var.d("success clear push message.");
        f0.h(this.a).C(t7Var, c7.Notification, false, true, null, false, this.a.getPackageName(), o0.c(this.a).d(), false);
    }

    private void k(g8 g8Var, y7 y7Var) {
        p7 m185a = y7Var.m185a();
        if (m185a != null) {
            m185a = m185a.m119a();
            com.xiaomi.push.service.v0.a(m185a);
        }
        s7 s7Var = new s7();
        s7Var.b(g8Var.b());
        s7Var.a(g8Var.m70a());
        s7Var.a(g8Var.a().a());
        if (!TextUtils.isEmpty(g8Var.c())) {
            s7Var.c(g8Var.c());
        }
        if (!TextUtils.isEmpty(g8Var.d())) {
            s7Var.d(g8Var.d());
        }
        s7Var.a(m8.c(this.a, y7Var));
        f0.h(this.a).w(s7Var, c7.AckMessage, m185a);
    }

    private void l(String str, long j2, r0 r0Var) {
        l0 a = y0.a(r0Var);
        if (a == null) {
            return;
        }
        if (j2 == 0) {
            synchronized (x.class) {
                if (x.b(this.a).f(str)) {
                    x.b(this.a).h(str);
                    if ("syncing".equals(x.b(this.a).c(a))) {
                        x.b(this.a).d(a, "synced");
                    }
                }
            }
        } else if (!"syncing".equals(x.b(this.a).c(a))) {
            x.b(this.a).h(str);
        } else {
            synchronized (x.class) {
                if (x.b(this.a).f(str)) {
                    if (x.b(this.a).a(str) < 10) {
                        x.b(this.a).g(str);
                        f0.h(this.a).E(str, a, r0Var, "retry");
                    } else {
                        x.b(this.a).h(str);
                    }
                }
            }
        }
    }

    private static boolean m(Context context, String str) {
        synchronized (d) {
            o0.c(context);
            SharedPreferences b2 = o0.b(context);
            if (f18370c == null) {
                String[] split = b2.getString("pref_msg_ids", "").split(DYConstants.DY_REGEX_COMMA);
                f18370c = new LinkedList();
                for (String str2 : split) {
                    f18370c.add(str2);
                }
            }
            if (f18370c.contains(str)) {
                return true;
            }
            f18370c.add(str);
            if (f18370c.size() > 25) {
                f18370c.poll();
            }
            String d2 = com.xiaomi.push.p0.d(f18370c, DYConstants.DY_REGEX_COMMA);
            SharedPreferences.Editor edit = b2.edit();
            edit.putString("pref_msg_ids", d2);
            p9.a(edit);
            return false;
        }
    }

    private boolean n(y7 y7Var) {
        Map<String, String> m121a = y7Var.m185a() == null ? null : y7Var.m185a().m121a();
        if (m121a == null) {
            return false;
        }
        String str = m121a.get("push_server_action");
        return TextUtils.equals(str, "hybrid_message") || TextUtils.equals(str, "platform_message");
    }

    private void o(t7 t7Var) {
        Context context;
        r0 r0Var;
        g.j.a.a.a.c.B("ASSEMBLE_PUSH : " + t7Var.toString());
        String a = t7Var.a();
        Map<String, String> m165a = t7Var.m165a();
        if (m165a != null) {
            String str = m165a.get("RegInfo");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.contains("brand:" + y.FCM.name())) {
                g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive fcm token sync ack");
                context = this.a;
                r0Var = r0.ASSEMBLE_PUSH_FCM;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("brand:");
                y yVar = y.HUAWEI;
                sb.append(yVar.name());
                if (!str.contains(sb.toString())) {
                    if (!str.contains("channel:" + yVar.name())) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("brand:");
                        y yVar2 = y.OPPO;
                        sb2.append(yVar2.name());
                        if (!str.contains(sb2.toString())) {
                            if (!str.contains("channel:" + yVar2.name())) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("brand:");
                                y yVar3 = y.VIVO;
                                sb3.append(yVar3.name());
                                if (!str.contains(sb3.toString())) {
                                    if (!str.contains("channel:" + yVar3.name())) {
                                        return;
                                    }
                                }
                                g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive FTOS token sync ack");
                                context = this.a;
                                r0Var = r0.ASSEMBLE_PUSH_FTOS;
                            }
                        }
                        g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive COS token sync ack");
                        context = this.a;
                        r0Var = r0.ASSEMBLE_PUSH_COS;
                    }
                }
                g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive hw token sync ack");
                context = this.a;
                r0Var = r0.ASSEMBLE_PUSH_HUAWEI;
            }
            v0.m(context, r0Var, str);
            l(a, t7Var.a, r0Var);
        }
    }

    private void p(y7 y7Var) {
        p7 m185a = y7Var.m185a();
        if (m185a != null) {
            m185a = m185a.m119a();
            com.xiaomi.push.service.v0.a(m185a);
        }
        s7 s7Var = new s7();
        s7Var.b(y7Var.m186a());
        s7Var.a(m185a.m120a());
        s7Var.a(m185a.m118a());
        if (!TextUtils.isEmpty(m185a.m125b())) {
            s7Var.c(m185a.m125b());
        }
        s7Var.a(m8.c(this.a, y7Var));
        f0.h(this.a).y(s7Var, c7.AckMessage, false, m185a);
    }

    private void q(c8 c8Var) {
        String str;
        Map<String, String> m35a = c8Var.m35a();
        if (m35a == null) {
            str = "detect failed because null";
        } else {
            String str2 = (String) com.xiaomi.push.service.z.f(m35a, "pkgList", null);
            if (!TextUtils.isEmpty(str2)) {
                Map<String, String> i2 = y4.i(this.a, str2);
                if (i2 == null) {
                    g.j.a.a.a.c.o("detect failed because get status illegal");
                    return;
                }
                String str3 = i2.get("alive");
                String str4 = i2.get("notAlive");
                if (TextUtils.isEmpty(str3)) {
                    g.j.a.a.a.c.y("detect failed because no alive process");
                    return;
                }
                c8 c8Var2 = new c8();
                c8Var2.a(c8Var.m34a());
                c8Var2.b(c8Var.b());
                c8Var2.d(c8Var.c());
                c8Var2.c(m7.DetectAppAliveResult.f179a);
                HashMap hashMap = new HashMap();
                c8Var2.f113a = hashMap;
                hashMap.put("alive", str3);
                if (Boolean.parseBoolean((String) com.xiaomi.push.service.z.f(m35a, "reportNotAliveApp", DYConstants.DY_FALSE)) && !TextUtils.isEmpty(str4)) {
                    c8Var2.f113a.put("notAlive", str4);
                }
                f0.h(this.a).y(c8Var2, c7.Notification, false, null);
                return;
            }
            str = "detect failed because empty";
        }
        g.j.a.a.a.c.o(str);
    }

    public PushMessageHandler.a b(Intent intent) {
        String str;
        d4 a;
        String packageName;
        String str2;
        d4 a2;
        String packageName2;
        String format;
        String action = intent.getAction();
        g.j.a.a.a.c.o("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        String stringExtra2 = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                g.j.a.a.a.c.D("receiving an empty message, drop");
                d4.a(this.a).e(this.a.getPackageName(), intent, "12");
                return null;
            }
            y7 y7Var = new y7();
            try {
                m8.e(y7Var, byteArrayExtra);
                o0 c2 = o0.c(this.a);
                p7 m185a = y7Var.m185a();
                c7 a3 = y7Var.a();
                c7 c7Var = c7.SendMessage;
                if (a3 == c7Var && m185a != null && !c2.w() && !booleanExtra) {
                    m185a.a("mrt", stringExtra);
                    m185a.a("mat", Long.toString(System.currentTimeMillis()));
                    if (n(y7Var)) {
                        g.j.a.a.a.c.y("this is a mina's message, ack later");
                        m185a.a("__hybrid_message_ts", String.valueOf(m185a.m118a()));
                        m185a.a("__hybrid_device_status", String.valueOf((int) m8.c(this.a, y7Var)));
                    } else {
                        p(y7Var);
                    }
                }
                if (y7Var.a() == c7Var && !y7Var.m193b()) {
                    if (com.xiaomi.push.service.n.J(y7Var)) {
                        Object[] objArr = new Object[2];
                        objArr[0] = y7Var.b();
                        objArr[1] = m185a != null ? m185a.m120a() : "";
                        g.j.a.a.a.c.o(String.format("drop an un-encrypted wake-up messages. %1$s, %2$s", objArr));
                        a2 = d4.a(this.a);
                        packageName2 = this.a.getPackageName();
                        format = String.format("13: %1$s", y7Var.b());
                    } else {
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = y7Var.b();
                        objArr2[1] = m185a != null ? m185a.m120a() : "";
                        g.j.a.a.a.c.o(String.format("drop an un-encrypted messages. %1$s, %2$s", objArr2));
                        a2 = d4.a(this.a);
                        packageName2 = this.a.getPackageName();
                        format = String.format("14: %1$s", y7Var.b());
                    }
                    a2.e(packageName2, intent, format);
                    f1.c(this.a, y7Var, booleanExtra);
                    return null;
                }
                if (y7Var.a() == c7Var && y7Var.m193b() && com.xiaomi.push.service.n.J(y7Var) && (!booleanExtra || m185a == null || m185a.m121a() == null || !m185a.m121a().containsKey("notify_effect"))) {
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = y7Var.b();
                    objArr3[1] = m185a != null ? m185a.m120a() : "";
                    g.j.a.a.a.c.o(String.format("drop a wake-up messages which not has 'notify_effect' attr. %1$s, %2$s", objArr3));
                    d4.a(this.a).e(this.a.getPackageName(), intent, String.format("25: %1$s", y7Var.b()));
                    f1.e(this.a, y7Var, booleanExtra);
                    return null;
                }
                if (c2.s() || y7Var.a == c7.Registration) {
                    if (!c2.s() || !c2.x()) {
                        return c(y7Var, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                    }
                    if (y7Var.a != c7.UnRegistration) {
                        f1.h(this.a, y7Var, booleanExtra);
                        m.g0(this.a);
                    } else if (y7Var.m193b()) {
                        c2.e();
                        m.l(this.a);
                        PushMessageHandler.a();
                    } else {
                        g.j.a.a.a.c.D("receiving an un-encrypt unregistration message");
                    }
                } else if (com.xiaomi.push.service.n.J(y7Var)) {
                    return c(y7Var, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                } else {
                    f1.h(this.a, y7Var, booleanExtra);
                    boolean u = c2.u();
                    g.j.a.a.a.c.D("receive message without registration. need re-register!registered?" + u);
                    d4.a(this.a).e(this.a.getPackageName(), intent, "15");
                    if (u) {
                        g();
                    }
                }
            } catch (s8 e2) {
                e = e2;
                a = d4.a(this.a);
                packageName = this.a.getPackageName();
                str2 = "16";
                a.e(packageName, intent, str2);
                g.j.a.a.a.c.s(e);
                return null;
            } catch (Exception e3) {
                e = e3;
                a = d4.a(this.a);
                packageName = this.a.getPackageName();
                str2 = Constants.VIA_REPORT_TYPE_START_GROUP;
                a.e(packageName, intent, str2);
                g.j.a.a.a.c.s(e);
                return null;
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
            y7 y7Var2 = new y7();
            try {
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    m8.e(y7Var2, byteArrayExtra2);
                }
            } catch (s8 unused) {
            }
            miPushCommandMessage.setCommand(String.valueOf(y7Var2.a()));
            miPushCommandMessage.setResultCode(intent.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
            g.j.a.a.a.c.D("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra3 == null) {
                g.j.a.a.a.c.D("message arrived: receiving an empty message, drop");
                return null;
            }
            y7 y7Var3 = new y7();
            try {
                m8.e(y7Var3, byteArrayExtra3);
                o0 c3 = o0.c(this.a);
                if (com.xiaomi.push.service.n.J(y7Var3)) {
                    str = "message arrived: receive ignore reg message, ignore!";
                } else if (!c3.s()) {
                    str = "message arrived: receive message without registration. need unregister or re-register!";
                } else if (!c3.s() || !c3.x()) {
                    return d(y7Var3, byteArrayExtra3);
                } else {
                    str = "message arrived: app info is invalidated";
                }
                g.j.a.a.a.c.D(str);
            } catch (Exception e4) {
                g.j.a.a.a.c.D("fail to deal with arrived message. " + e4);
            }
        }
        return null;
    }

    public List<String> f(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = ((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60;
        long parseLong = ((((Long.parseLong(list.get(0).split(":")[0]) * 60) + Long.parseLong(list.get(0).split(":")[1])) - rawOffset) + 1440) % 1440;
        long parseLong2 = ((((Long.parseLong(list.get(1).split(":")[0]) * 60) + Long.parseLong(list.get(1).split(":")[1])) - rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong2 / 60), Long.valueOf(parseLong2 % 60)));
        return arrayList;
    }
}
