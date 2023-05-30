package jd.wjlogin_sdk.d.e;

import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: e  reason: collision with root package name */
    private static final int f19826e = 1024;
    private ByteBuffer a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f19827c;
    private int d;

    public a() {
        this.a = ByteBuffer.allocate(1024);
        this.d = 1024;
    }

    public void a(byte b) {
        b(1);
        this.a.put(b);
        this.b++;
        a();
    }

    public void b(String str) {
        if (str == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        short length = (short) bytes.length;
        a(length);
        b(length);
        this.a.put(bytes);
        this.b += length;
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

    public int d(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes().length;
    }

    public short e(String str) {
        if (str == null) {
            return (short) 0;
        }
        return (short) str.getBytes().length;
    }

    public byte[] f() {
        int i2 = i();
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = this.a.get();
        }
        this.f19827c += i2;
        return bArr;
    }

    public int g() {
        this.f19827c += 4;
        return this.a.getInt();
    }

    public long h() {
        this.f19827c += 8;
        return this.a.getLong();
    }

    public short i() {
        this.f19827c = (short) (this.f19827c + 2);
        return this.a.getShort();
    }

    public String j() {
        int g2 = g();
        byte[] bArr = new byte[g2];
        for (int i2 = 0; i2 < g2; i2++) {
            bArr[i2] = this.a.get();
        }
        this.f19827c += g2;
        return new String(bArr);
    }

    public String k() {
        int i2 = i();
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = this.a.get();
        }
        this.f19827c += i2;
        return new String(bArr);
    }

    public int l() {
        return this.a.limit();
    }

    public int m() {
        return this.a.position();
    }

    public boolean n() {
        return this.f19827c >= this.b;
    }

    public byte d() {
        this.f19827c++;
        return this.a.get();
    }

    public byte[] e() {
        int g2 = g();
        byte[] bArr = new byte[g2];
        for (int i2 = 0; i2 < g2; i2++) {
            bArr[i2] = this.a.get();
        }
        this.f19827c += g2;
        return bArr;
    }

    public a(byte[] bArr) {
        int length = bArr.length;
        this.b = length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        this.a = allocate;
        allocate.put(bArr);
    }

    public void a(short s) {
        b(2);
        this.a.putShort(s);
        this.b += 2;
        a();
    }

    public void c(String str) {
        if (str == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        b(length);
        this.a.put(bytes);
        this.b += length;
        a();
    }

    public String d(int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = this.a.get();
        }
        this.f19827c += i2;
        return new String(bArr);
    }

    private void b(int i2) {
        int i3 = this.b;
        int i4 = i3 + i2;
        int i5 = this.d;
        if (i4 > i5) {
            int i6 = i5 + i3 + i2;
            this.d = i6;
            ByteBuffer allocate = ByteBuffer.allocate(i6);
            allocate.put(c());
            this.a = allocate;
        }
    }

    public void a(int i2) {
        b(4);
        this.a.putInt(i2);
        this.b += 4;
        a();
    }

    public byte[] c(int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = this.a.get();
        }
        this.f19827c += i2;
        return bArr;
    }

    public void a(long j2) {
        b(8);
        this.a.putLong(j2);
        this.b += 8;
        a();
    }

    public void b() {
        this.a.flip();
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        a(length);
        b(length);
        this.a.put(bytes);
        this.b += length;
        a();
    }

    public void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        a(length);
        b(length);
        this.a.put(bArr);
        this.b += length;
        a();
    }

    private void a() {
        this.a.putLong(2, this.b);
    }
}
