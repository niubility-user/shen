package jd.wjlogin_sdk.util;

/* loaded from: classes11.dex */
public class x {
    private int b = 0;
    private StringBuffer a = new StringBuffer();

    public void a(byte b) {
        this.a.append((int) b);
    }

    public int b() {
        char[] cArr = new char[4];
        this.a.getChars(0, 4, cArr, 0);
        return ByteUtil.byteToInt(new String(cArr).getBytes());
    }

    public long c() {
        char[] cArr = new char[8];
        this.a.getChars(0, 8, cArr, 0);
        return ByteUtil.byteToLong(new String(cArr).getBytes());
    }

    public short d() {
        char[] cArr = new char[2];
        this.a.getChars(0, 2, cArr, this.b);
        this.b += 2;
        return ByteUtil.byteToShort(new String(cArr).getBytes());
    }

    public String e() {
        int d = d();
        char[] cArr = new char[d];
        this.a.getChars(0, d, cArr, 0);
        this.b += d;
        return new String(cArr);
    }

    public void a(short s) {
        this.a.append((int) s);
    }

    public void a(int i2) {
        this.a.append(i2);
    }

    public void a(long j2) {
        this.a.append(j2);
    }

    public void a(String str) {
        a((short) str.getBytes().length);
        this.a.append(str);
    }

    public byte a() {
        char[] cArr = new char[1];
        this.a.getChars(0, 1, cArr, 0);
        return new String(cArr).getBytes()[0];
    }
}
