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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e() {
        /*
            Method dump skipped, instructions count: 488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.s0.e():void");
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
