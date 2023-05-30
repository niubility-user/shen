package g.d.a.c;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

/* loaded from: classes12.dex */
public abstract class b implements BinaryEncoder, BinaryDecoder {
    protected final int a;
    protected byte[] b;

    /* renamed from: c  reason: collision with root package name */
    protected int f19426c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f19427e;

    /* renamed from: f  reason: collision with root package name */
    protected int f19428f;

    /* renamed from: g  reason: collision with root package name */
    protected int f19429g;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(int i2, int i3, int i4, int i5) {
        this.a = (i4 <= 0 || i5 <= 0) ? 0 : (i4 / i3) * i3;
    }

    private void j() {
        this.b = null;
        this.f19426c = 0;
        this.d = 0;
        this.f19428f = 0;
        this.f19429g = 0;
        this.f19427e = false;
    }

    private void k() {
        byte[] bArr = this.b;
        if (bArr == null) {
            this.b = new byte[g()];
            this.f19426c = 0;
            this.d = 0;
            return;
        }
        byte[] bArr2 = new byte[bArr.length * 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.b = bArr2;
    }

    int a() {
        if (this.b != null) {
            return this.f19426c - this.d;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (61 == b || h(b)) {
                return true;
            }
        }
        return false;
    }

    abstract void c(byte[] bArr, int i2, int i3);

    public byte[] d(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return d((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    abstract void e(byte[] bArr, int i2, int i3);

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(int i2) {
        byte[] bArr = this.b;
        if (bArr == null || bArr.length < this.f19426c + i2) {
            k();
        }
    }

    protected int g() {
        return 8192;
    }

    protected abstract boolean h(byte b);

    int i(byte[] bArr, int i2, int i3) {
        if (this.b == null) {
            return this.f19427e ? -1 : 0;
        }
        int min = Math.min(a(), i3);
        System.arraycopy(this.b, this.d, bArr, i2, min);
        int i4 = this.d + min;
        this.d = i4;
        if (i4 >= this.f19426c) {
            this.b = null;
        }
        return min;
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        j();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        e(bArr, 0, bArr.length);
        e(bArr, 0, -1);
        int i2 = this.f19426c - this.d;
        byte[] bArr2 = new byte[i2];
        i(bArr2, 0, i2);
        return bArr2;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        j();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        c(bArr, 0, bArr.length);
        c(bArr, 0, -1);
        int i2 = this.f19426c;
        byte[] bArr2 = new byte[i2];
        i(bArr2, 0, i2);
        return bArr2;
    }
}
