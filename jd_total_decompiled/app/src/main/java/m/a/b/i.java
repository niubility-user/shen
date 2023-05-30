package m.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.apache.commons.codec.CharEncoding;
import sun.misc.IOUtils;

/* loaded from: classes11.dex */
public class i {
    public byte a;
    protected f b;

    /* renamed from: c */
    public final g f20295c;
    private int d;

    public i(byte b, byte[] bArr) {
        this.a = b;
        f fVar = new f((byte[]) bArr.clone());
        this.b = fVar;
        this.d = bArr.length;
        g gVar = new g(fVar);
        this.f20295c = gVar;
        gVar.s(Integer.MAX_VALUE);
    }

    public static boolean A(char c2) {
        if ((c2 < 'a' || c2 > 'z') && ((c2 < 'A' || c2 > 'Z') && ((c2 < '0' || c2 > '9') && c2 != ' ' && c2 != ':' && c2 != '=' && c2 != '?'))) {
            switch (c2) {
                case '\'':
                case '(':
                case ')':
                    break;
                default:
                    switch (c2) {
                        case '+':
                        case ',':
                        case '-':
                        case '.':
                        case '/':
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte b(byte b, boolean z, byte b2) {
        byte b3 = (byte) (b | b2);
        return z ? (byte) (b3 | 32) : b3;
    }

    private static boolean c(i iVar, i iVar2) {
        boolean g2;
        synchronized (iVar.f20295c) {
            synchronized (iVar2.f20295c) {
                iVar.f20295c.v();
                iVar2.f20295c.v();
                g2 = iVar.b.g(iVar2.b);
            }
        }
        return g2;
    }

    private g v(boolean z, InputStream inputStream) throws IOException {
        this.a = (byte) inputStream.read();
        byte read = (byte) inputStream.read();
        int i2 = g.i(read & 255, inputStream);
        this.d = i2;
        if (i2 == -1) {
            int available = inputStream.available();
            byte[] bArr = new byte[available + 2];
            bArr[0] = this.a;
            bArr[1] = read;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(bArr, 2, available);
            dataInputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new e().a(bArr));
            if (this.a == byteArrayInputStream.read()) {
                this.d = g.j(byteArrayInputStream);
                inputStream = byteArrayInputStream;
            } else {
                throw new IOException("Indefinite length encoding not supported");
            }
        }
        if (this.d == 0) {
            return null;
        }
        if (z && inputStream.available() != this.d) {
            throw new IOException("extra data given to DerValue constructor");
        }
        f fVar = new f(IOUtils.readFully(inputStream, this.d, true));
        this.b = fVar;
        return new g(fVar);
    }

    public int B() {
        return this.d;
    }

    public void C(byte b) {
        this.a = b;
    }

    public byte[] D() throws IOException {
        h hVar = new h();
        d(hVar);
        this.f20295c.v();
        return hVar.toByteArray();
    }

    public g E() throws IOException {
        byte b = this.a;
        if (b != 48 && b != 49) {
            throw new IOException("toDerInputStream rejects tag type " + ((int) this.a));
        }
        return new g(this.b);
    }

    public void d(h hVar) throws IOException {
        hVar.write(this.a);
        hVar.n(this.d);
        int i2 = this.d;
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            synchronized (this.f20295c) {
                this.b.reset();
                if (this.b.read(bArr) == this.d) {
                    hVar.write(bArr);
                } else {
                    throw new IOException("short DER value read (encode)");
                }
            }
        }
    }

    public boolean e(i iVar) {
        if (this == iVar) {
            return true;
        }
        if (this.a != iVar.a) {
            return false;
        }
        g gVar = this.f20295c;
        if (gVar == iVar.f20295c) {
            return true;
        }
        return System.identityHashCode(gVar) > System.identityHashCode(iVar.f20295c) ? c(this, iVar) : c(iVar, this);
    }

    public boolean equals(Object obj) {
        if (obj instanceof i) {
            return e((i) obj);
        }
        return false;
    }

    public String f() throws IOException {
        byte b = this.a;
        if (b == 12) {
            return t();
        }
        if (b == 19) {
            return r();
        }
        if (b == 20) {
            return s();
        }
        if (b == 22) {
            return n();
        }
        if (b == 30) {
            return g();
        }
        if (b == 27) {
            return m();
        }
        return null;
    }

    public String g() throws IOException {
        if (this.a == 30) {
            return new String(l(), "UnicodeBigUnmarked");
        }
        throw new IOException("DerValue.getBMPString, not BMP " + ((int) this.a));
    }

    public BigInteger h() throws IOException {
        if (this.a == 2) {
            return this.b.h(this.f20295c.a(), false);
        }
        throw new IOException("DerValue.getBigInteger, not an int " + ((int) this.a));
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public byte[] i() throws IOException {
        if (this.a == 3) {
            return this.b.i();
        }
        throw new IOException("DerValue.getBitString, not a bit string " + ((int) this.a));
    }

    public boolean j() throws IOException {
        if (this.a == 1) {
            if (this.d == 1) {
                return this.b.read() != 0;
            }
            throw new IOException("DerValue.getBoolean, invalid length " + this.d);
        }
        throw new IOException("DerValue.getBoolean, not a BOOLEAN " + ((int) this.a));
    }

    public final g k() {
        return this.f20295c;
    }

    public byte[] l() throws IOException {
        byte[] bArr = new byte[this.d];
        synchronized (this.f20295c) {
            this.f20295c.v();
            this.f20295c.d(bArr);
        }
        return bArr;
    }

    public String m() throws IOException {
        if (this.a == 27) {
            return new String(l(), "ASCII");
        }
        throw new IOException("DerValue.getGeneralString, not GeneralString " + ((int) this.a));
    }

    public String n() throws IOException {
        if (this.a == 22) {
            return new String(l(), "ASCII");
        }
        throw new IOException("DerValue.getIA5String, not IA5 " + ((int) this.a));
    }

    public int o() throws IOException {
        if (this.a == 2) {
            return this.b.l(this.f20295c.a());
        }
        throw new IOException("DerValue.getInteger, not an int " + ((int) this.a));
    }

    public j p() throws IOException {
        if (this.a == 6) {
            return new j(this.b);
        }
        throw new IOException("DerValue.getOID, not an OID " + ((int) this.a));
    }

    public byte[] q() throws IOException {
        if (this.a != 4 && !x((byte) 4)) {
            throw new IOException("DerValue.getOctetString, not an Octet String: " + ((int) this.a));
        }
        byte[] bArr = new byte[this.d];
        if (this.b.read(bArr) == this.d) {
            if (w()) {
                g gVar = new g(bArr);
                bArr = null;
                while (gVar.a() != 0) {
                    bArr = a(bArr, gVar.l());
                }
            }
            return bArr;
        }
        throw new IOException("short read on DerValue buffer");
    }

    public String r() throws IOException {
        if (this.a == 19) {
            return new String(l(), "ASCII");
        }
        throw new IOException("DerValue.getPrintableString, not a string " + ((int) this.a));
    }

    public String s() throws IOException {
        if (this.a == 20) {
            return new String(l(), CharEncoding.ISO_8859_1);
        }
        throw new IOException("DerValue.getT61String, not T61 " + ((int) this.a));
    }

    public String t() throws IOException {
        if (this.a == 12) {
            return new String(l(), "UTF8");
        }
        throw new IOException("DerValue.getUTF8String, not UTF-8 " + ((int) this.a));
    }

    public String toString() {
        try {
            String f2 = f();
            if (f2 != null) {
                return "\"" + f2 + "\"";
            }
            byte b = this.a;
            if (b == 5) {
                return "[DerValue, null]";
            }
            if (b == 6) {
                return "OID." + p();
            }
            return "[DerValue, tag = " + ((int) this.a) + ", length = " + this.d + "]";
        } catch (IOException unused) {
            throw new IllegalArgumentException("misformatted DER value");
        }
    }

    public a u(boolean z) throws IOException {
        if (!z && this.a != 3) {
            throw new IOException("DerValue.getBitString, not a bit string " + ((int) this.a));
        }
        return this.b.o();
    }

    public boolean w() {
        return (this.a & 32) == 32;
    }

    public boolean x(byte b) {
        return w() && (this.a & 31) == b;
    }

    public boolean y() {
        return (this.a & 192) == 128;
    }

    public boolean z(byte b) {
        return y() && (this.a & 31) == b;
    }

    public i(f fVar) throws IOException {
        this.a = (byte) fVar.read();
        byte read = (byte) fVar.read();
        int i2 = g.i(read & 255, fVar);
        this.d = i2;
        if (i2 == -1) {
            f f2 = fVar.f();
            int available = f2.available();
            byte[] bArr = new byte[available + 2];
            bArr[0] = this.a;
            bArr[1] = read;
            DataInputStream dataInputStream = new DataInputStream(f2);
            dataInputStream.readFully(bArr, 2, available);
            dataInputStream.close();
            f fVar2 = new f(new e().a(bArr));
            if (this.a == fVar2.read()) {
                this.d = g.j(fVar2);
                f f3 = fVar2.f();
                this.b = f3;
                f3.r(this.d);
                this.f20295c = new g(this.b);
                fVar.skip(this.d + 2);
                return;
            }
            throw new IOException("Indefinite length encoding not supported");
        }
        f f4 = fVar.f();
        this.b = f4;
        f4.r(this.d);
        this.f20295c = new g(this.b);
        fVar.skip(this.d);
    }

    public i(byte[] bArr) throws IOException {
        this.f20295c = v(true, new ByteArrayInputStream(bArr));
    }

    public i(InputStream inputStream) throws IOException {
        this.f20295c = v(false, inputStream);
    }
}
