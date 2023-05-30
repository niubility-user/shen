package com.xiaomi.push;

import android.os.Build;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

/* loaded from: classes11.dex */
public class h5 {
    ByteBuffer a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);

    /* renamed from: c  reason: collision with root package name */
    private Adler32 f18701c = new Adler32();
    private l5 d;

    /* renamed from: e  reason: collision with root package name */
    private OutputStream f18702e;

    /* renamed from: f  reason: collision with root package name */
    private int f18703f;

    /* renamed from: g  reason: collision with root package name */
    private int f18704g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f18705h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h5(OutputStream outputStream, l5 l5Var) {
        this.f18702e = new BufferedOutputStream(outputStream);
        this.d = l5Var;
        TimeZone timeZone = TimeZone.getDefault();
        this.f18703f = timeZone.getRawOffset() / UnTimeUtils.HOUR;
        this.f18704g = timeZone.useDaylightTime() ? 1 : 0;
    }

    public int a(e5 e5Var) {
        int x = e5Var.x();
        if (x > 32768) {
            g.j.a.a.a.c.o("Blob size=" + x + " should be less than 32768 Drop blob chid=" + e5Var.a() + " id=" + e5Var.D());
            return 0;
        }
        this.a.clear();
        int i2 = x + 8 + 4;
        if (i2 > this.a.capacity() || this.a.capacity() > 4096) {
            this.a = ByteBuffer.allocate(i2);
        }
        this.a.putShort((short) -15618);
        this.a.putShort((short) 5);
        this.a.putInt(x);
        int position = this.a.position();
        this.a = e5Var.f(this.a);
        if (!"CONN".equals(e5Var.e())) {
            if (this.f18705h == null) {
                this.f18705h = this.d.X();
            }
            com.xiaomi.push.service.r0.j(this.f18705h, this.a.array(), true, position, x);
        }
        this.f18701c.reset();
        this.f18701c.update(this.a.array(), 0, this.a.position());
        this.b.putInt(0, (int) this.f18701c.getValue());
        this.f18702e.write(this.a.array(), 0, this.a.position());
        this.f18702e.write(this.b.array(), 0, 4);
        this.f18702e.flush();
        int position2 = this.a.position() + 4;
        g.j.a.a.a.c.B("[Slim] Wrote {cmd=" + e5Var.e() + ";chid=" + e5Var.a() + ";len=" + position2 + "}");
        return position2;
    }

    public void b() {
        f3 f3Var = new f3();
        f3Var.l(106);
        f3Var.p(BaseInfo.getDeviceModel());
        f3Var.v(r9.d());
        f3Var.A(com.xiaomi.push.service.z0.g());
        f3Var.t(48);
        f3Var.F(this.d.t());
        f3Var.J(this.d.d());
        f3Var.N(Locale.getDefault().toString());
        int i2 = Build.VERSION.SDK_INT;
        f3Var.z(i2);
        f3Var.E(y4.b(this.d.F(), "com.xiaomi.xmsf"));
        byte[] g2 = this.d.c().g();
        if (g2 != null) {
            f3Var.o(c3.m(g2));
        }
        e5 e5Var = new e5();
        e5Var.h(0);
        e5Var.l("CONN", null);
        e5Var.j(0L, "xiaomi.com", null);
        e5Var.n(f3Var.h(), null);
        a(e5Var);
        g.j.a.a.a.c.o("[slim] open conn: andver=" + i2 + " sdk=48 tz=" + this.f18703f + ":" + this.f18704g + " Model=" + BaseInfo.getDeviceModel() + " os=" + Build.VERSION.INCREMENTAL);
    }

    public void c() {
        e5 e5Var = new e5();
        e5Var.l("CLOSE", null);
        a(e5Var);
        this.f18702e.close();
    }
}
