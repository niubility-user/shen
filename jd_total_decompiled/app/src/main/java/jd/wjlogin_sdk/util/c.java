package jd.wjlogin_sdk.util;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class c {

    /* renamed from: e  reason: collision with root package name */
    private static final String f19915e = "WJLogin.BufferManager";

    /* renamed from: f  reason: collision with root package name */
    private static final short f19916f = 1024;
    private ByteBuffer a;
    private short b;

    /* renamed from: c  reason: collision with root package name */
    private short f19917c;
    private short d;

    public c() {
        this.a = ByteBuffer.allocate(1024);
        this.d = (short) 1024;
    }

    private void e(short s) {
        p.b(f19915e, "checkLongBufferLen");
        if (this.b + s > this.d) {
            p.b(f19915e, "checkLongBufferLen (bufferlen + len)=" + ((int) this.b) + ((int) s) + " total_len=" + ((int) this.d));
            this.d = (short) (this.d + this.b + s);
            StringBuilder sb = new StringBuilder();
            sb.append("checkLongBufferLen after change  total_len=");
            sb.append((int) this.d);
            p.b(f19915e, sb.toString());
            ByteBuffer allocate = ByteBuffer.allocate(this.d);
            allocate.put(c());
            this.a = allocate;
        }
    }

    public void a(byte b) {
        c((short) 1);
        this.a.put(b);
        this.b = (short) (this.b + 1);
        a();
    }

    public void b(String str) {
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes();
        short length = (short) bytes.length;
        c(length);
        this.a.put(bytes);
        this.b = (short) (this.b + length);
        a();
    }

    public byte[] c() {
        b();
        byte[] bArr = new byte[this.b];
        for (int i2 = 0; i2 < this.b; i2++) {
            bArr[i2] = this.a.get();
        }
        return bArr;
    }

    public void d(String str) {
        p.b(f19915e, "AddLongBuffer(String string) " + str);
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes();
        short length = (short) bytes.length;
        a(length);
        e(length);
        this.a.put(bytes);
        this.b = (short) (this.b + length);
        a();
    }

    public int f() {
        this.f19917c = (short) (this.f19917c + 4);
        return this.a.getInt();
    }

    public long g() {
        this.f19917c = (short) (this.f19917c + 8);
        return this.a.getLong();
    }

    public short h() {
        this.f19917c = (short) (this.f19917c + 2);
        return this.a.getShort();
    }

    public String i() {
        int h2 = h();
        byte[] bArr = new byte[h2];
        for (int i2 = 0; i2 < h2; i2++) {
            bArr[i2] = this.a.get();
        }
        this.f19917c = (short) (this.f19917c + h2);
        return new String(bArr);
    }

    public int j() {
        return this.a.limit();
    }

    public int k() {
        return this.a.position();
    }

    public boolean l() {
        return this.f19917c >= this.b;
    }

    public String f(short s) {
        byte[] bArr = new byte[s];
        for (int i2 = 0; i2 < s; i2++) {
            bArr[i2] = this.a.get();
        }
        this.f19917c = (short) (this.f19917c + s);
        return new String(bArr);
    }

    public c(byte[] bArr) {
        short length = (short) bArr.length;
        this.b = length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        this.a = allocate;
        allocate.put(bArr);
    }

    private void c(short s) {
        if (this.b + s > this.d) {
            p.b(f19915e, "checkBufferLen (bufferlen + len)=" + ((int) this.b) + ((int) s) + "total_len=" + ((int) this.d));
            this.d = (short) (this.d * 2);
            StringBuilder sb = new StringBuilder();
            sb.append("checkBufferLen after change  total_len=");
            sb.append((int) this.d);
            p.b(f19915e, sb.toString());
            ByteBuffer allocate = ByteBuffer.allocate(this.d);
            allocate.put(c());
            this.a = allocate;
        }
    }

    public void a(short s) {
        c((short) 2);
        this.a.putShort(s);
        this.b = (short) (this.b + 2);
        a();
    }

    public void b() {
        this.a.flip();
    }

    public void b(int i2) {
        d((short) 4);
        this.a.putInt(i2);
        this.b = (short) (this.b + 4);
        a();
    }

    public void a(int i2) {
        c((short) 4);
        this.a.putInt(i2);
        this.b = (short) (this.b + 4);
        a();
    }

    public byte d() {
        this.f19917c = (short) (this.f19917c + 1);
        return this.a.get();
    }

    public short e(String str) {
        if (str == null) {
            return (short) 0;
        }
        return (short) str.getBytes().length;
    }

    private void d(short s) {
        short s2 = this.b;
        int i2 = s2 + s;
        short s3 = this.d;
        if (i2 > s3) {
            short s4 = (short) (s3 + s2 + s);
            this.d = s4;
            ByteBuffer allocate = ByteBuffer.allocate(s4);
            allocate.put(c());
            this.a = allocate;
        }
    }

    public byte[] e() {
        int h2 = h();
        byte[] bArr = new byte[h2];
        for (int i2 = 0; i2 < h2; i2++) {
            bArr[i2] = this.a.get();
        }
        this.f19917c = (short) (this.f19917c + h2);
        return bArr;
    }

    public void b(short s) {
        d((short) 2);
        this.a.putShort(s);
        this.b = (short) (this.b + 2);
        a();
    }

    public byte[] c(int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = this.a.get();
        }
        this.f19917c = (short) (this.f19917c + i2);
        return bArr;
    }

    public void a(long j2) {
        c((short) 8);
        this.a.putLong(j2);
        this.b = (short) (this.b + 8);
        a();
    }

    public void c(String str) {
        if (str == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        short length = (short) bytes.length;
        b(length);
        d(length);
        this.a.put(bytes);
        this.b = (short) (this.b + length);
        a();
    }

    public void a(String str) {
        p.b(f19915e, "AddBuffer(String string) " + str);
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes();
        short length = (short) bytes.length;
        a(length);
        c(length);
        this.a.put(bytes);
        this.b = (short) (this.b + length);
        a();
    }

    public void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        short length = (short) bArr.length;
        a(length);
        c(length);
        this.a.put(bArr);
        this.b = (short) (this.b + length);
        a();
    }

    private void a() {
        this.a.putShort(0, this.b);
    }
}
