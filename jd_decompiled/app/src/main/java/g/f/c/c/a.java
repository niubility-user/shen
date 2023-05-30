package g.f.c.c;

/* loaded from: classes13.dex */
public class a {
    public byte a = 0;
    public byte b = 1;

    /* renamed from: c  reason: collision with root package name */
    public byte f19553c = 0;
    public byte d = 0;

    /* renamed from: e  reason: collision with root package name */
    public byte f19554e = 1;

    /* renamed from: f  reason: collision with root package name */
    public byte f19555f = 0;

    /* renamed from: g  reason: collision with root package name */
    public byte f19556g = 0;

    /* renamed from: h  reason: collision with root package name */
    public byte f19557h = 0;

    /* renamed from: i  reason: collision with root package name */
    public byte f19558i = 1;

    public byte a() {
        byte b;
        byte b2;
        byte b3 = this.f19553c;
        byte b4 = (byte) (((byte) (((byte) (((byte) ((this.a << 0) | 0)) | (this.b << 1))) | (b3 << 2))) | (this.d << 3));
        if (b3 == 0) {
            b = (byte) (((byte) (b4 | (this.f19554e << 4))) | (this.f19555f << 5));
            b2 = this.f19556g;
        } else {
            b = (byte) (((byte) (b4 | (this.f19557h << 4))) | (this.f19558i << 5));
            b2 = this.f19556g;
        }
        return (byte) (b | (b2 << 6));
    }
}
