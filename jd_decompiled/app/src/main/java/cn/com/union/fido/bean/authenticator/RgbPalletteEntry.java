package cn.com.union.fido.bean.authenticator;

/* loaded from: classes.dex */
public class RgbPalletteEntry {
    private short b;

    /* renamed from: g  reason: collision with root package name */
    private short f813g;
    private short r;

    public RgbPalletteEntry() {
    }

    public RgbPalletteEntry(short s, short s2, short s3) {
        this.r = s;
        this.f813g = s2;
        this.b = s3;
    }

    public short getB() {
        return this.b;
    }

    public short getG() {
        return this.f813g;
    }

    public short getR() {
        return this.r;
    }

    public void setB(short s) {
        this.b = s;
    }

    public void setG(short s) {
        this.f813g = s;
    }

    public void setR(short s) {
        this.r = s;
    }
}
