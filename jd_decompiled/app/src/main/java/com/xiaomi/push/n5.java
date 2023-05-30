package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import com.xiaomi.push.o5;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/* loaded from: classes11.dex */
public class n5 implements b6 {

    /* renamed from: e */
    public static boolean f18877e;
    private o5 b;
    private SimpleDateFormat a = new SimpleDateFormat("hh:mm:ss aaa");

    /* renamed from: c */
    private a f18878c = null;
    private a d = null;

    /* loaded from: classes11.dex */
    public class a implements t5, c6 {
        String a;
        private boolean b;

        a(boolean z) {
            n5.this = r1;
            this.b = true;
            this.b = z;
            this.a = z ? " RCV " : " Sent ";
        }

        @Override // com.xiaomi.push.t5
        public void a(g6 g6Var) {
            StringBuilder sb;
            String str;
            if (n5.f18877e) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(n5.this.a.format(new Date()));
                sb.append(this.a);
                sb.append(" PKT ");
                str = g6Var.f();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(n5.this.a.format(new Date()));
                sb.append(this.a);
                sb.append(" PKT [");
                sb.append(g6Var.m());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(g6Var.l());
                str = "]";
            }
            sb.append(str);
            g.j.a.a.a.c.B(sb.toString());
        }

        @Override // com.xiaomi.push.c6
        /* renamed from: a */
        public boolean mo104a(g6 g6Var) {
            return true;
        }

        @Override // com.xiaomi.push.t5
        public void b(e5 e5Var) {
            StringBuilder sb;
            String str;
            if (n5.f18877e) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(n5.this.a.format(new Date()));
                sb.append(this.a);
                str = e5Var.toString();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(n5.this.a.format(new Date()));
                sb.append(this.a);
                sb.append(" Blob [");
                sb.append(e5Var.e());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(e5Var.a());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(com.xiaomi.push.service.f0.b(e5Var.D()));
                str = "]";
            }
            sb.append(str);
            g.j.a.a.a.c.B(sb.toString());
            if (e5Var == null || e5Var.a() != 99999) {
                return;
            }
            String e2 = e5Var.e();
            e5 e5Var2 = null;
            if (!this.b) {
                if ("BIND".equals(e2)) {
                    g.j.a.a.a.c.o("build binded result for loopback.");
                    e3 e3Var = new e3();
                    e3Var.l(true);
                    e3Var.s("login success.");
                    e3Var.p("success");
                    e3Var.k("success");
                    e5 e5Var3 = new e5();
                    e5Var3.n(e3Var.h(), null);
                    e5Var3.m((short) 2);
                    e5Var3.h(99999);
                    e5Var3.l("BIND", null);
                    e5Var3.k(e5Var.D());
                    e5Var3.v(null);
                    e5Var3.B(e5Var.F());
                    e5Var2 = e5Var3;
                } else if (!"UBND".equals(e2) && "SECMSG".equals(e2)) {
                    e5 e5Var4 = new e5();
                    e5Var4.h(99999);
                    e5Var4.l("SECMSG", null);
                    e5Var4.B(e5Var.F());
                    e5Var4.k(e5Var.D());
                    e5Var4.m(e5Var.g());
                    e5Var4.v(e5Var.E());
                    e5Var4.n(e5Var.q(com.xiaomi.push.service.i0.c().b(String.valueOf(99999), e5Var.F()).f19101i), null);
                    e5Var2 = e5Var4;
                }
            }
            if (e5Var2 != null) {
                for (Map.Entry<t5, o5.a> entry : n5.this.b.f().entrySet()) {
                    if (n5.this.f18878c != entry.getKey()) {
                        entry.getValue().a(e5Var2);
                    }
                }
            }
        }
    }

    public n5(o5 o5Var) {
        this.b = null;
        this.b = o5Var;
        d();
    }

    private void d() {
        a aVar = new a(true);
        this.f18878c = aVar;
        this.d = new a(false);
        this.b.k(aVar, aVar);
        o5 o5Var = this.b;
        a aVar2 = this.d;
        o5Var.z(aVar2, aVar2);
    }
}
