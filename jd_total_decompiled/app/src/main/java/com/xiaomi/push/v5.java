package com.xiaomi.push;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes11.dex */
public abstract class v5 extends o5 {
    protected Exception q;
    protected Socket r;
    private String s;
    protected XMPushService t;
    protected volatile long u;
    private int v;
    private long w;

    public v5(XMPushService xMPushService, p5 p5Var) {
        super(xMPushService, p5Var);
        this.q = null;
        this.u = 0L;
        this.w = 0L;
        this.t = xMPushService;
    }

    private void K(p5 p5Var) {
        M(p5Var.j(), p5Var.a());
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0339  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void M(String str, int i2) {
        StringBuilder sb;
        long j2;
        int i3;
        String str2;
        boolean z;
        Iterator<String> it;
        String str3;
        b1 b1Var;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        this.q = null;
        ArrayList<String> arrayList = new ArrayList<>();
        int intValue = g.j.a.a.a.c.b("get bucket for host : " + str).intValue();
        b1 G = G(str);
        g.j.a.a.a.c.n(Integer.valueOf(intValue));
        if (G != null) {
            arrayList = G.e(true);
        }
        b1 x = f1.c().x(str);
        if (x != null) {
            Iterator<String> it2 = x.e(true).iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                if (arrayList.indexOf(next) == -1) {
                    arrayList.add(next);
                }
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(str);
        }
        long j3 = 0;
        this.u = 0L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String g2 = j0.g(this.t);
        StringBuilder sb2 = new StringBuilder();
        Iterator<String> it3 = arrayList.iterator();
        String str10 = "";
        int i4 = 0;
        while (it3.hasNext()) {
            String next2 = it3.next();
            long currentTimeMillis = System.currentTimeMillis();
            this.a++;
            int i5 = i4 + 1;
            try {
                g.j.a.a.a.c.o("begin to connect to " + next2);
                this.r = H();
                this.r.connect(d1.d(next2, i2), 8000);
                g.j.a.a.a.c.o("tcp connected");
                this.r.setTcpNoDelay(true);
                this.s = next2;
                I();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                this.b = currentTimeMillis2;
                if (G != null) {
                    it = it3;
                    sb = sb2;
                    str3 = g2;
                    j2 = 0;
                    b1Var = G;
                    try {
                        G.s(next2, currentTimeMillis2, 0L);
                    } catch (Exception e2) {
                        e = e2;
                        str4 = str10;
                        str5 = str3;
                        try {
                            this.q = e;
                            g.j.a.a.a.c.D("SMACK: Could not connect to:" + next2);
                            sb.append("SMACK: Could not connect to ");
                            sb.append(next2);
                            sb.append(" port:");
                            sb.append(i2);
                            sb.append(" err:");
                            sb.append(this.q.getClass().getSimpleName());
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            if (TextUtils.isEmpty(str4)) {
                                str2 = next2;
                            } else {
                                str2 = str4 + "|" + next2;
                            }
                            c5.h(next2, this.q);
                            if (b1Var != null) {
                                str7 = str5;
                                b1Var.t(next2, System.currentTimeMillis() - currentTimeMillis, 0L, this.q);
                            } else {
                                str7 = str5;
                            }
                        } catch (Throwable th) {
                            th = th;
                            g.j.a.a.a.c.D("SMACK: Could not connect to:" + next2);
                            sb.append("SMACK: Could not connect to ");
                            sb.append(next2);
                            sb.append(" port:");
                            sb.append(i2);
                            sb.append(" err:");
                            sb.append(this.q.getClass().getSimpleName());
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            if (TextUtils.isEmpty(str4)) {
                                str8 = str4 + "|" + next2;
                            } else {
                                str8 = next2;
                            }
                            c5.h(next2, this.q);
                            if (b1Var == null) {
                                str9 = str5;
                                b1Var.t(next2, System.currentTimeMillis() - currentTimeMillis, 0L, this.q);
                            } else {
                                str9 = str5;
                            }
                            if (!TextUtils.equals(str9, j0.g(this.t))) {
                                throw th;
                            }
                            str2 = str8;
                            i3 = i5;
                            z = false;
                            f1.c().w();
                            int elapsedRealtime2 = (int) (SystemClock.elapsedRealtime() - elapsedRealtime);
                            if (!z) {
                            }
                        }
                        if (TextUtils.equals(str7, j0.g(this.t))) {
                            str10 = str2;
                            sb2 = sb;
                            g2 = str7;
                            i4 = i5;
                            it3 = it;
                            j3 = j2;
                            G = b1Var;
                        } else {
                            i3 = i5;
                            z = false;
                            f1.c().w();
                            int elapsedRealtime22 = (int) (SystemClock.elapsedRealtime() - elapsedRealtime);
                            if (!z) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            this.q = new Exception("abnormal exception", th);
                            g.j.a.a.a.c.s(th);
                            g.j.a.a.a.c.D("SMACK: Could not connect to:" + next2);
                            sb.append("SMACK: Could not connect to ");
                            sb.append(next2);
                            sb.append(" port:");
                            sb.append(i2);
                            sb.append(" err:");
                            sb.append(this.q.getClass().getSimpleName());
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            if (TextUtils.isEmpty(str10)) {
                                str2 = next2;
                            } else {
                                str2 = str10 + "|" + next2;
                            }
                            c5.h(next2, this.q);
                            if (b1Var != null) {
                                b1Var.t(next2, System.currentTimeMillis() - currentTimeMillis, 0L, this.q);
                            }
                            str6 = str3;
                        } catch (Throwable th3) {
                            th = th3;
                            str4 = str10;
                            str5 = str3;
                            g.j.a.a.a.c.D("SMACK: Could not connect to:" + next2);
                            sb.append("SMACK: Could not connect to ");
                            sb.append(next2);
                            sb.append(" port:");
                            sb.append(i2);
                            sb.append(" err:");
                            sb.append(this.q.getClass().getSimpleName());
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            if (TextUtils.isEmpty(str4)) {
                            }
                            c5.h(next2, this.q);
                            if (b1Var == null) {
                            }
                            if (!TextUtils.equals(str9, j0.g(this.t))) {
                            }
                        }
                        if (TextUtils.equals(str6, j0.g(this.t))) {
                            str7 = str6;
                            str10 = str2;
                            sb2 = sb;
                            g2 = str7;
                            i4 = i5;
                            it3 = it;
                            j3 = j2;
                            G = b1Var;
                        } else {
                            i3 = i5;
                            z = false;
                            f1.c().w();
                            int elapsedRealtime222 = (int) (SystemClock.elapsedRealtime() - elapsedRealtime);
                            if (!z) {
                            }
                        }
                    }
                } else {
                    it = it3;
                    sb = sb2;
                    str3 = g2;
                    b1Var = G;
                    j2 = 0;
                }
                this.u = SystemClock.elapsedRealtime();
                g.j.a.a.a.c.o("connected to " + next2 + " in " + this.b);
                str2 = str10;
                i3 = i5;
                z = true;
                break;
            } catch (Exception e3) {
                e = e3;
                it = it3;
                sb = sb2;
                b1Var = G;
                j2 = 0;
                str4 = str10;
                str5 = g2;
            } catch (Throwable th4) {
                th = th4;
                it = it3;
                sb = sb2;
                str3 = g2;
                b1Var = G;
                j2 = 0;
            }
        }
        sb = sb2;
        j2 = j3;
        i3 = i4;
        str2 = str10;
        z = false;
        f1.c().w();
        int elapsedRealtime2222 = (int) (SystemClock.elapsedRealtime() - elapsedRealtime);
        if (!z) {
            c5.d(0, r4.BATCH_TCP_CONN_SUCCESS.a(), elapsedRealtime2222, str2, i3);
            return;
        }
        if (this.w == j2 || SystemClock.elapsedRealtime() - this.w > 480000) {
            this.w = SystemClock.elapsedRealtime();
            c5.d(0, r4.BATCH_TCP_CONN_FAIL.a(), elapsedRealtime2222, str2, j0.q(this.t.getApplicationContext()) ? 1 : 0);
        }
        throw new a6(sb.toString());
    }

    @Override // com.xiaomi.push.o5
    public void A(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentTimeMillis = System.currentTimeMillis();
        O(z);
        com.xiaomi.push.service.n2.c(this.t).q();
        if (z) {
            return;
        }
        this.t.a(new w5(this, 13, elapsedRealtime, currentTimeMillis), 10000L);
    }

    public Context F() {
        return this.t;
    }

    b1 G(String str) {
        b1 b = f1.c().b(str, false);
        if (!b.u()) {
            s6.c(new z5(this, str));
        }
        return b;
    }

    public Socket H() {
        return new Socket();
    }

    protected synchronized void I() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void J(int i2, Exception exc) {
        if (s() == 2) {
            return;
        }
        h(2, i2, exc);
        this.f18900i = "";
        try {
            this.r.close();
        } catch (Throwable unused) {
        }
    }

    protected void L(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.u < 300000) {
            if (!j0.p(this.t)) {
                return;
            }
            int i2 = this.v + 1;
            this.v = i2;
            if (i2 < 2) {
                return;
            }
            String d = d();
            g.j.a.a.a.c.o("max short conn time reached, sink down current host:" + d);
            N(d, 0L, exc);
        }
        this.v = 0;
    }

    protected void N(String str, long j2, Exception exc) {
        b1 b = f1.c().b(p5.b(), false);
        if (b != null) {
            b.t(str, j2, 0L, exc);
            f1.c().w();
        }
    }

    protected abstract void O(boolean z);

    public String P() {
        return this.f18900i;
    }

    public void Q(int i2, Exception exc) {
        this.t.a(new x5(this, 2, i2, exc));
    }

    public synchronized void R() {
        try {
            if (!D() && !B()) {
                h(0, 0, null);
                K(this.f18903l);
                return;
            }
            g.j.a.a.a.c.o("WARNING: current xmpp has connected");
        } catch (IOException e2) {
            throw new a6(e2);
        }
    }

    public void S() {
        SystemClock.elapsedRealtime();
    }

    public void T() {
        SystemClock.elapsedRealtime();
    }

    @Override // com.xiaomi.push.o5
    public String d() {
        return this.s;
    }

    @Override // com.xiaomi.push.o5
    public void v(int i2, Exception exc) {
        J(i2, exc);
        if ((exc != null || i2 == 18) && this.u != 0) {
            L(exc);
        }
    }
}
