package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.h7;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class s0 implements a {

    /* renamed from: e  reason: collision with root package name */
    private static volatile s0 f18424e;
    private Context a;
    private p b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18425c = false;
    private Map<r0, a> d = new HashMap();

    private s0(Context context) {
        this.a = context.getApplicationContext();
    }

    public static s0 d(Context context) {
        if (f18424e == null) {
            synchronized (s0.class) {
                if (f18424e == null) {
                    f18424e = new s0(context);
                }
            }
        }
        return f18424e;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void e() {
        a c2;
        r0 r0Var;
        a c3;
        r0 r0Var2;
        a c4;
        r0 r0Var3;
        a c5;
        p pVar = this.b;
        if (pVar == null) {
            return;
        }
        if (pVar.d()) {
            StringBuilder sb = new StringBuilder();
            sb.append("ASSEMBLE_PUSH : ");
            sb.append(" HW user switch : " + this.b.d() + " HW online switch : " + v0.g(this.a, r0.ASSEMBLE_PUSH_HUAWEI) + " HW isSupport : " + a1.c(this.a));
            g.j.a.a.a.c.o(sb.toString());
        }
        if (this.b.d()) {
            Context context = this.a;
            r0 r0Var4 = r0.ASSEMBLE_PUSH_HUAWEI;
            if (v0.g(context, r0Var4) && a1.c(this.a)) {
                if (!i(r0Var4)) {
                    h(r0Var4, b0.a(this.a, r0Var4));
                }
                g.j.a.a.a.c.B("hw manager add to list");
                if (this.b.b()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("ASSEMBLE_PUSH : ");
                    sb2.append(" FCM user switch : " + this.b.b() + " FCM online switch : " + v0.g(this.a, r0.ASSEMBLE_PUSH_FCM) + " FCM isSupport : " + a1.d(this.a));
                    g.j.a.a.a.c.o(sb2.toString());
                }
                if (this.b.b()) {
                    Context context2 = this.a;
                    r0 r0Var5 = r0.ASSEMBLE_PUSH_FCM;
                    if (v0.g(context2, r0Var5) && a1.d(this.a)) {
                        if (!i(r0Var5)) {
                            h(r0Var5, b0.a(this.a, r0Var5));
                        }
                        g.j.a.a.a.c.B("fcm manager add to list");
                        if (this.b.a()) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("ASSEMBLE_PUSH : ");
                            sb3.append(" COS user switch : " + this.b.a() + " COS online switch : " + v0.g(this.a, r0.ASSEMBLE_PUSH_COS) + " COS isSupport : " + a1.e(this.a));
                            g.j.a.a.a.c.o(sb3.toString());
                        }
                        if (this.b.a()) {
                            Context context3 = this.a;
                            r0 r0Var6 = r0.ASSEMBLE_PUSH_COS;
                            if (v0.g(context3, r0Var6) && a1.e(this.a)) {
                                h(r0Var6, b0.a(this.a, r0Var6));
                                if (this.b.c()) {
                                    Context context4 = this.a;
                                    r0 r0Var7 = r0.ASSEMBLE_PUSH_FTOS;
                                    if (v0.g(context4, r0Var7) && a1.f(this.a)) {
                                        h(r0Var7, b0.a(this.a, r0Var7));
                                        return;
                                    }
                                }
                                r0Var3 = r0.ASSEMBLE_PUSH_FTOS;
                                if (i(r0Var3) || (c5 = c(r0Var3)) == null) {
                                    return;
                                }
                                g(r0Var3);
                                c5.unregister();
                                return;
                            }
                        }
                        r0Var2 = r0.ASSEMBLE_PUSH_COS;
                        if (i(r0Var2) && (c4 = c(r0Var2)) != null) {
                            g(r0Var2);
                            c4.unregister();
                        }
                        if (this.b.c()) {
                        }
                        r0Var3 = r0.ASSEMBLE_PUSH_FTOS;
                        if (i(r0Var3)) {
                            return;
                        }
                        return;
                    }
                }
                r0Var = r0.ASSEMBLE_PUSH_FCM;
                if (i(r0Var) && (c3 = c(r0Var)) != null) {
                    g(r0Var);
                    c3.unregister();
                }
                if (this.b.a()) {
                }
                if (this.b.a()) {
                }
                r0Var2 = r0.ASSEMBLE_PUSH_COS;
                if (i(r0Var2)) {
                    g(r0Var2);
                    c4.unregister();
                }
                if (this.b.c()) {
                }
                r0Var3 = r0.ASSEMBLE_PUSH_FTOS;
                if (i(r0Var3)) {
                }
            }
        }
        r0 r0Var8 = r0.ASSEMBLE_PUSH_HUAWEI;
        if (i(r0Var8) && (c2 = c(r0Var8)) != null) {
            g(r0Var8);
            c2.unregister();
        }
        if (this.b.b()) {
        }
        if (this.b.b()) {
        }
        r0Var = r0.ASSEMBLE_PUSH_FCM;
        if (i(r0Var)) {
            g(r0Var);
            c3.unregister();
        }
        if (this.b.a()) {
        }
        if (this.b.a()) {
        }
        r0Var2 = r0.ASSEMBLE_PUSH_COS;
        if (i(r0Var2)) {
        }
        if (this.b.c()) {
        }
        r0Var3 = r0.ASSEMBLE_PUSH_FTOS;
        if (i(r0Var3)) {
        }
    }

    @Override // com.xiaomi.mipush.sdk.a
    public void a() {
        g.j.a.a.a.c.o("ASSEMBLE_PUSH : assemble push register");
        if (this.d.size() <= 0) {
            e();
        }
        if (this.d.size() > 0) {
            for (a aVar : this.d.values()) {
                if (aVar != null) {
                    aVar.a();
                }
            }
            v0.f(this.a);
        }
    }

    public a c(r0 r0Var) {
        return this.d.get(r0Var);
    }

    public void f(p pVar) {
        this.b = pVar;
        this.f18425c = com.xiaomi.push.service.b0.d(this.a).m(h7.AggregatePushSwitch.a(), true);
        if (this.b.d() || this.b.b() || this.b.a() || this.b.c()) {
            com.xiaomi.push.service.b0.d(this.a).j(new t0(this, 101, "assemblePush"));
        }
    }

    public void g(r0 r0Var) {
        this.d.remove(r0Var);
    }

    public void h(r0 r0Var, a aVar) {
        if (aVar != null) {
            if (this.d.containsKey(r0Var)) {
                this.d.remove(r0Var);
            }
            this.d.put(r0Var, aVar);
        }
    }

    public boolean i(r0 r0Var) {
        return this.d.containsKey(r0Var);
    }

    public boolean l(r0 r0Var) {
        int i2 = u0.a[r0Var.ordinal()];
        boolean z = false;
        if (i2 == 1) {
            p pVar = this.b;
            if (pVar != null) {
                return pVar.d();
            }
            return false;
        } else if (i2 == 2) {
            p pVar2 = this.b;
            if (pVar2 != null) {
                return pVar2.b();
            }
            return false;
        } else {
            if (i2 == 3) {
                p pVar3 = this.b;
                if (pVar3 != null) {
                    z = pVar3.a();
                }
            } else if (i2 != 4) {
                return false;
            }
            p pVar4 = this.b;
            return pVar4 != null ? pVar4.c() : z;
        }
    }

    @Override // com.xiaomi.mipush.sdk.a
    public void unregister() {
        g.j.a.a.a.c.o("ASSEMBLE_PUSH : assemble push unregister");
        for (a aVar : this.d.values()) {
            if (aVar != null) {
                aVar.unregister();
            }
        }
        this.d.clear();
    }
}
