package m.a.c;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class k0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private byte[] f20371g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f20372h;

    /* renamed from: i  reason: collision with root package name */
    private String f20373i;

    public k0(m.a.b.i iVar) throws IOException {
        this(iVar.q());
    }

    private void e(String str) throws IOException {
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            this.f20371g = InetAddress.getByName(str).getAddress();
            return;
        }
        this.f20371g = new byte[8];
        byte[] address = InetAddress.getByName(str.substring(indexOf + 1)).getAddress();
        System.arraycopy(InetAddress.getByName(str.substring(0, indexOf)).getAddress(), 0, this.f20371g, 0, 4);
        System.arraycopy(address, 0, this.f20371g, 4, 4);
    }

    private void f(String str) throws IOException {
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            this.f20371g = InetAddress.getByName(str).getAddress();
            return;
        }
        this.f20371g = new byte[32];
        System.arraycopy(InetAddress.getByName(str.substring(0, indexOf)).getAddress(), 0, this.f20371g, 0, 16);
        int parseInt = Integer.parseInt(str.substring(indexOf + 1));
        if (parseInt <= 128) {
            m.a.b.a aVar = new m.a.b.a(128);
            for (int i2 = 0; i2 < parseInt; i2++) {
                aVar.d(i2, true);
            }
            byte[] g2 = aVar.g();
            for (int i3 = 0; i3 < 16; i3++) {
                this.f20371g[i3 + 16] = g2[i3];
            }
            return;
        }
        throw new IOException("IPv6Address prefix is longer than 128");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00b3 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b5 A[RETURN, SYNTHETIC] */
    @Override // m.a.c.g0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int a(g0 g0Var) throws UnsupportedOperationException {
        int i2 = 0;
        if (g0Var != null && g0Var.getType() == 7) {
            k0 k0Var = (k0) g0Var;
            if (!k0Var.equals(this)) {
                byte[] c2 = k0Var.c();
                if (c2.length != 4 || this.f20371g.length != 4) {
                    if ((c2.length == 8 && this.f20371g.length == 8) || (c2.length == 32 && this.f20371g.length == 32)) {
                        int length = this.f20371g.length / 2;
                        boolean z = false;
                        boolean z2 = false;
                        boolean z3 = true;
                        boolean z4 = true;
                        for (int i3 = 0; i3 < length; i3++) {
                            byte[] bArr = this.f20371g;
                            int i4 = i3 + length;
                            if (((byte) (bArr[i3] & bArr[i4])) != bArr[i3]) {
                                z = true;
                            }
                            if (((byte) (c2[i3] & c2[i4])) != c2[i3]) {
                                z2 = true;
                            }
                            if (((byte) (bArr[i4] & c2[i4])) != bArr[i4] || ((byte) (bArr[i3] & bArr[i4])) != ((byte) (c2[i3] & bArr[i4]))) {
                                z3 = false;
                            }
                            if (((byte) (c2[i4] & bArr[i4])) != c2[i4] || ((byte) (c2[i3] & c2[i4])) != ((byte) (bArr[i3] & c2[i4]))) {
                                z4 = false;
                            }
                        }
                        if (!z && !z2) {
                            if (!z3) {
                                if (z4) {
                                }
                            }
                            return 1;
                        } else if (!z || !z2) {
                            if (z) {
                            }
                        }
                    } else if (c2.length != 8 && c2.length != 32) {
                        byte[] bArr2 = this.f20371g;
                        if (bArr2.length == 8 || bArr2.length == 32) {
                            int length2 = bArr2.length / 2;
                            while (i2 < length2) {
                                byte b = c2[i2];
                                byte[] bArr3 = this.f20371g;
                                if ((b & bArr3[i2 + length2]) != bArr3[i2]) {
                                    break;
                                }
                                i2++;
                            }
                            if (i2 == length2) {
                                return 1;
                            }
                        }
                    } else {
                        int length3 = c2.length / 2;
                        while (i2 < length3 && (this.f20371g[i2] & c2[i2 + length3]) == c2[i2]) {
                            i2++;
                        }
                        if (i2 == length3) {
                            return 2;
                        }
                    }
                }
                return 3;
            }
            return 0;
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        hVar.q(this.f20371g);
    }

    public byte[] c() {
        return (byte[]) this.f20371g.clone();
    }

    public String d() throws IOException {
        String str = this.f20373i;
        if (str != null) {
            return str;
        }
        int i2 = 0;
        if (this.f20372h) {
            byte[] bArr = new byte[4];
            System.arraycopy(this.f20371g, 0, bArr, 0, 4);
            this.f20373i = InetAddress.getByAddress(bArr).getHostAddress();
            byte[] bArr2 = this.f20371g;
            if (bArr2.length == 8) {
                byte[] bArr3 = new byte[4];
                System.arraycopy(bArr2, 4, bArr3, 0, 4);
                this.f20373i += "/" + InetAddress.getByAddress(bArr3).getHostAddress();
            }
        } else {
            byte[] bArr4 = new byte[16];
            System.arraycopy(this.f20371g, 0, bArr4, 0, 16);
            this.f20373i = InetAddress.getByAddress(bArr4).getHostAddress();
            if (this.f20371g.length == 32) {
                byte[] bArr5 = new byte[16];
                for (int i3 = 16; i3 < 32; i3++) {
                    bArr5[i3 - 16] = this.f20371g[i3];
                }
                m.a.b.a aVar = new m.a.b.a(128, bArr5);
                while (i2 < 128 && aVar.a(i2)) {
                    i2++;
                }
                this.f20373i += "/" + i2;
                while (i2 < 128) {
                    if (aVar.a(i2)) {
                        throw new IOException("Invalid IPv6 subdomain - set bit " + i2 + " not contiguous");
                    }
                    i2++;
                }
            }
        }
        return this.f20373i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        byte[] c2 = ((k0) obj).c();
        int length = c2.length;
        byte[] bArr = this.f20371g;
        if (length != bArr.length) {
            return false;
        }
        if (bArr.length != 8 && bArr.length != 32) {
            return Arrays.equals(c2, bArr);
        }
        int length2 = bArr.length / 2;
        byte[] bArr2 = new byte[length2];
        byte[] bArr3 = new byte[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            byte[] bArr4 = this.f20371g;
            int i3 = i2 + length2;
            bArr2[i2] = (byte) (bArr4[i3] & bArr4[i2]);
            bArr3[i2] = (byte) (c2[i2] & c2[i3]);
            if (bArr2[i2] != bArr3[i2]) {
                return false;
            }
        }
        while (true) {
            byte[] bArr5 = this.f20371g;
            if (length2 >= bArr5.length) {
                return true;
            }
            if (bArr5[length2] != c2[length2]) {
                return false;
            }
            length2++;
        }
    }

    @Override // m.a.c.g0
    public int getType() {
        return 7;
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f20371g;
            if (i2 >= bArr.length) {
                return i3;
            }
            i3 += bArr[i2] * i2;
            i2++;
        }
    }

    public String toString() {
        try {
            return "IPAddress: " + d();
        } catch (IOException unused) {
            return "IPAddress: " + new HexDumpEncoder().encodeBuffer(this.f20371g);
        }
    }

    public k0(byte[] bArr) throws IOException {
        if (bArr.length != 4 && bArr.length != 8) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IOException("Invalid IPAddressName");
            }
            this.f20372h = false;
        } else {
            this.f20372h = true;
        }
        this.f20371g = bArr;
    }

    public k0(String str) throws IOException {
        if (str != null && str.length() != 0) {
            if (str.charAt(str.length() - 1) != '/') {
                if (str.indexOf(58) >= 0) {
                    f(str);
                    this.f20372h = false;
                    return;
                } else if (str.indexOf(46) >= 0) {
                    e(str);
                    this.f20372h = true;
                    return;
                } else {
                    throw new IOException("Invalid IPAddress: " + str);
                }
            }
            throw new IOException("Invalid IPAddress: " + str);
        }
        throw new IOException("IPAddress cannot be null or empty");
    }
}
