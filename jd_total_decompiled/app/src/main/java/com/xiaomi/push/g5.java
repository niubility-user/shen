package com.xiaomi.push;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g5 {
    private ByteBuffer a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);

    /* renamed from: c  reason: collision with root package name */
    private Adler32 f18643c = new Adler32();
    private j5 d = new j5();

    /* renamed from: e  reason: collision with root package name */
    private InputStream f18644e;

    /* renamed from: f  reason: collision with root package name */
    private l5 f18645f;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f18646g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f18647h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g5(InputStream inputStream, l5 l5Var) {
        this.f18644e = new BufferedInputStream(inputStream);
        this.f18645f = l5Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ByteBuffer b() {
        ByteBuffer allocate;
        int i2;
        this.a.clear();
        d(this.a, 8);
        short s = this.a.getShort(0);
        short s2 = this.a.getShort(2);
        if (s != -15618 || s2 != 5) {
            throw new IOException("Malformed Input");
        }
        int i3 = this.a.getInt(4);
        int position = this.a.position();
        if (i3 > 32768) {
            throw new IOException("Blob size too large");
        }
        if (i3 + 4 <= this.a.remaining()) {
            if (this.a.capacity() > 4096 && i3 < 2048) {
                allocate = ByteBuffer.allocate(2048);
                allocate.put(this.a.array(), 0, this.a.arrayOffset() + this.a.position());
            }
            d(this.a, i3);
            this.b.clear();
            d(this.b, 4);
            this.b.position(0);
            i2 = this.b.getInt();
            this.f18643c.reset();
            this.f18643c.update(this.a.array(), 0, this.a.position());
            if (i2 != ((int) this.f18643c.getValue())) {
                byte[] bArr = this.f18647h;
                if (bArr != null) {
                    com.xiaomi.push.service.r0.j(bArr, this.a.array(), true, position, i3);
                }
                return this.a;
            }
            g.j.a.a.a.c.o("CRC = " + ((int) this.f18643c.getValue()) + " and " + i2);
            throw new IOException("Corrupted Blob bad CRC");
        }
        allocate = ByteBuffer.allocate(i3 + 2048);
        allocate.put(this.a.array(), 0, this.a.arrayOffset() + this.a.position());
        this.a = allocate;
        d(this.a, i3);
        this.b.clear();
        d(this.b, 4);
        this.b.position(0);
        i2 = this.b.getInt();
        this.f18643c.reset();
        this.f18643c.update(this.a.array(), 0, this.a.position());
        if (i2 != ((int) this.f18643c.getValue())) {
        }
    }

    private void d(ByteBuffer byteBuffer, int i2) {
        int position = byteBuffer.position();
        do {
            int read = this.f18644e.read(byteBuffer.array(), position, i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 -= read;
            position += read;
        } while (i2 > 0);
        byteBuffer.position(position);
    }

    private void f() {
        String str;
        StringBuilder sb;
        boolean z = false;
        this.f18646g = false;
        e5 a = a();
        if ("CONN".equals(a.e())) {
            g3 n2 = g3.n(a.p());
            if (n2.p()) {
                this.f18645f.n(n2.o());
                z = true;
            }
            if (n2.t()) {
                c3 j2 = n2.j();
                e5 e5Var = new e5();
                e5Var.l("SYNC", "CONF");
                e5Var.n(j2.h(), null);
                this.f18645f.W(e5Var);
            }
            g.j.a.a.a.c.o("[Slim] CONN: host = " + n2.r());
        }
        if (!z) {
            g.j.a.a.a.c.o("[Slim] Invalid CONN");
            throw new IOException("Invalid Connection");
        }
        this.f18647h = this.f18645f.X();
        while (!this.f18646g) {
            e5 a2 = a();
            long currentTimeMillis = System.currentTimeMillis();
            this.f18645f.C();
            short g2 = a2.g();
            if (g2 != 1) {
                if (g2 != 2) {
                    if (g2 != 3) {
                        str = "[Slim] unknow blob type " + ((int) a2.g());
                        g.j.a.a.a.c.o(str);
                    } else {
                        try {
                            this.f18645f.Y(this.d.a(a2.p(), this.f18645f));
                        } catch (Exception e2) {
                            e = e2;
                            sb = new StringBuilder();
                            sb.append("[Slim] Parse packet from Blob chid=");
                            sb.append(a2.a());
                            sb.append("; Id=");
                            sb.append(a2.D());
                            sb.append(" failure:");
                            sb.append(e.getMessage());
                            str = sb.toString();
                            g.j.a.a.a.c.o(str);
                        }
                    }
                } else if ("SECMSG".equals(a2.e()) && ((a2.a() == 2 || a2.a() == 3) && TextUtils.isEmpty(a2.t()))) {
                    try {
                        g6 a3 = this.d.a(a2.q(com.xiaomi.push.service.i0.c().b(Integer.valueOf(a2.a()).toString(), a2.F()).f19101i), this.f18645f);
                        a3.f18658j = currentTimeMillis;
                        this.f18645f.Y(a3);
                    } catch (Exception e3) {
                        e = e3;
                        sb = new StringBuilder();
                        sb.append("[Slim] Parse packet from Blob chid=");
                        sb.append(a2.a());
                        sb.append("; Id=");
                        sb.append(a2.D());
                        sb.append(" failure:");
                        sb.append(e.getMessage());
                        str = sb.toString();
                        g.j.a.a.a.c.o(str);
                    }
                }
            }
            this.f18645f.W(a2);
        }
    }

    e5 a() {
        int i2;
        try {
            ByteBuffer b = b();
            i2 = b.position();
            try {
                b.flip();
                b.position(8);
                e5 k5Var = i2 == 8 ? new k5() : e5.d(b.slice());
                g.j.a.a.a.c.B("[Slim] Read {cmd=" + k5Var.e() + ";chid=" + k5Var.a() + ";len=" + i2 + "}");
                return k5Var;
            } catch (IOException e2) {
                e = e2;
                if (i2 == 0) {
                    i2 = this.a.position();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("[Slim] read Blob [");
                byte[] array = this.a.array();
                if (i2 > 128) {
                    i2 = 128;
                }
                sb.append(f.a(array, 0, i2));
                sb.append("] Err:");
                sb.append(e.getMessage());
                g.j.a.a.a.c.o(sb.toString());
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            i2 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        try {
            f();
        } catch (IOException e2) {
            if (!this.f18646g) {
                throw e2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.f18646g = true;
    }
}
