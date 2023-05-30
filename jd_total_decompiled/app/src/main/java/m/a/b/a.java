package m.a.b;

import java.io.ByteArrayOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final byte[][] f20288c = {new byte[]{48, 48, 48, 48}, new byte[]{48, 48, 48, ReplyCode.reply0x31}, new byte[]{48, 48, ReplyCode.reply0x31, 48}, new byte[]{48, 48, ReplyCode.reply0x31, ReplyCode.reply0x31}, new byte[]{48, ReplyCode.reply0x31, 48, 48}, new byte[]{48, ReplyCode.reply0x31, 48, ReplyCode.reply0x31}, new byte[]{48, ReplyCode.reply0x31, ReplyCode.reply0x31, 48}, new byte[]{48, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31}, new byte[]{ReplyCode.reply0x31, 48, 48, 48}, new byte[]{ReplyCode.reply0x31, 48, 48, ReplyCode.reply0x31}, new byte[]{ReplyCode.reply0x31, 48, ReplyCode.reply0x31, 48}, new byte[]{ReplyCode.reply0x31, 48, ReplyCode.reply0x31, ReplyCode.reply0x31}, new byte[]{ReplyCode.reply0x31, ReplyCode.reply0x31, 48, 48}, new byte[]{ReplyCode.reply0x31, ReplyCode.reply0x31, 48, ReplyCode.reply0x31}, new byte[]{ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, 48}, new byte[]{ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31}};
    private byte[] a;
    private int b;

    public a(int i2) throws IllegalArgumentException {
        if (i2 >= 0) {
            this.b = i2;
            this.a = new byte[((i2 + 8) - 1) / 8];
            return;
        }
        throw new IllegalArgumentException("Negative length for BitArray");
    }

    private static int c(int i2) {
        return 1 << (7 - (i2 % 8));
    }

    private static int e(int i2) {
        return i2 / 8;
    }

    public boolean a(int i2) throws ArrayIndexOutOfBoundsException {
        if (i2 < 0 || i2 >= this.b) {
            throw new ArrayIndexOutOfBoundsException(Integer.toString(i2));
        }
        return (c(i2) & this.a[e(i2)]) != 0;
    }

    public int b() {
        return this.b;
    }

    public Object clone() {
        return new a(this);
    }

    public void d(int i2, boolean z) throws ArrayIndexOutOfBoundsException {
        if (i2 >= 0 && i2 < this.b) {
            int e2 = e(i2);
            int c2 = c(i2);
            if (z) {
                byte[] bArr = this.a;
                bArr[e2] = (byte) (c2 | bArr[e2]);
                return;
            }
            byte[] bArr2 = this.a;
            bArr2[e2] = (byte) ((c2 ^ (-1)) & bArr2[e2]);
            return;
        }
        throw new ArrayIndexOutOfBoundsException(Integer.toString(i2));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (aVar.b != this.b) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr = this.a;
            if (i2 >= bArr.length) {
                return true;
            }
            if (bArr[i2] != aVar.a[i2]) {
                return false;
            }
            i2++;
        }
    }

    public boolean[] f() {
        boolean[] zArr = new boolean[this.b];
        for (int i2 = 0; i2 < this.b; i2++) {
            zArr[i2] = a(i2);
        }
        return zArr;
    }

    public byte[] g() {
        return (byte[]) this.a.clone();
    }

    public a h() {
        for (int i2 = this.b - 1; i2 >= 0; i2--) {
            if (a(i2)) {
                return new a(i2 + 1, n.a.a.a(this.a, (i2 + 8) / 8));
            }
        }
        return new a(1);
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.a;
            if (i2 < bArr.length) {
                i3 = (i3 * 31) + bArr[i2];
                i2++;
            } else {
                return this.b ^ i3;
            }
        }
    }

    public String toString() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (true) {
            bArr = this.a;
            if (i2 >= bArr.length - 1) {
                break;
            }
            byte[][] bArr2 = f20288c;
            byteArrayOutputStream.write(bArr2[(bArr[i2] >> 4) & 15], 0, 4);
            byteArrayOutputStream.write(bArr2[this.a[i2] & 15], 0, 4);
            if (i2 % 8 == 7) {
                byteArrayOutputStream.write(10);
            } else {
                byteArrayOutputStream.write(32);
            }
            i2++;
        }
        for (int length = (bArr.length - 1) * 8; length < this.b; length++) {
            byteArrayOutputStream.write(a(length) ? 49 : 48);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    public a(int i2, byte[] bArr) throws IllegalArgumentException {
        if (i2 >= 0) {
            if (bArr.length * 8 >= i2) {
                this.b = i2;
                int i3 = ((i2 + 8) - 1) / 8;
                byte b = (byte) (255 << ((i3 * 8) - i2));
                byte[] bArr2 = new byte[i3];
                this.a = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, i3);
                if (i3 > 0) {
                    byte[] bArr3 = this.a;
                    int i4 = i3 - 1;
                    bArr3[i4] = (byte) (b & bArr3[i4]);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Byte array too short to represent bit array of given length");
        }
        throw new IllegalArgumentException("Negative length for BitArray");
    }

    public a(boolean[] zArr) {
        int length = zArr.length;
        this.b = length;
        this.a = new byte[(length + 7) / 8];
        for (int i2 = 0; i2 < this.b; i2++) {
            d(i2, zArr[i2]);
        }
    }

    private a(a aVar) {
        this.b = aVar.b;
        this.a = (byte[]) aVar.a.clone();
    }
}
