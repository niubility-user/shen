package m.a.b;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Vector;

/* loaded from: classes11.dex */
public class g {
    f a;
    public byte b;

    public g(byte[] bArr) throws IOException {
        r(bArr, 0, bArr.length);
    }

    public static int i(int i2, InputStream inputStream) throws IOException {
        if ((i2 & 128) == 0) {
            return i2;
        }
        int i3 = i2 & 127;
        if (i3 == 0) {
            return -1;
        }
        if (i3 >= 0 && i3 <= 4) {
            int i4 = 0;
            while (i3 > 0) {
                i4 = (i4 << 8) + (inputStream.read() & 255);
                i3--;
            }
            return i4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DerInputStream.getLength(): lengthTag=");
        sb.append(i3);
        sb.append(", ");
        sb.append(i3 < 0 ? "incorrect DER encoding." : "too big.");
        throw new IOException(sb.toString());
    }

    public static int j(InputStream inputStream) throws IOException {
        return i(inputStream.read(), inputStream);
    }

    private void r(byte[] bArr, int i2, int i3) throws IOException {
        if (i2 + 2 <= bArr.length && i2 + i3 <= bArr.length) {
            if (e.d(bArr[i2 + 1])) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i2, bArr2, 0, i3);
                this.a = new f(new e().a(bArr2));
            } else {
                this.a = new f(bArr, i2, i3);
            }
            this.a.mark(Integer.MAX_VALUE);
            return;
        }
        throw new IOException("Encoding bytes too short");
    }

    public int a() {
        return this.a.available();
    }

    public BigInteger b() throws IOException {
        if (this.a.read() == 2) {
            f fVar = this.a;
            return fVar.h(j(fVar), false);
        }
        throw new IOException("DER input, Integer tag error");
    }

    public int c() throws IOException {
        return this.a.read() & 255;
    }

    public void d(byte[] bArr) throws IOException {
        if (bArr.length != 0 && this.a.read(bArr) != bArr.length) {
            throw new IOException("short read of DER octet string");
        }
    }

    public i e() throws IOException {
        return new i(this.a);
    }

    public Date f() throws IOException {
        if (this.a.read() == 24) {
            f fVar = this.a;
            return fVar.k(j(fVar));
        }
        throw new IOException("DER input, GeneralizedTime tag invalid ");
    }

    public int g() throws IOException {
        if (this.a.read() == 2) {
            f fVar = this.a;
            return fVar.l(j(fVar));
        }
        throw new IOException("DER input, Integer tag error");
    }

    public int h() throws IOException {
        return j(this.a);
    }

    public j k() throws IOException {
        return new j(this);
    }

    public byte[] l() throws IOException {
        if (this.a.read() == 4) {
            int j2 = j(this.a);
            byte[] bArr = new byte[j2];
            if (j2 == 0 || this.a.read(bArr) == j2) {
                return bArr;
            }
            throw new IOException("short read of DER octet string");
        }
        throw new IOException("DER input not an octet string");
    }

    public i[] m(int i2) throws IOException {
        byte read = (byte) this.a.read();
        this.b = read;
        if (read == 48) {
            return u(i2);
        }
        throw new IOException("Sequence tag error");
    }

    public i[] n(int i2) throws IOException {
        byte read = (byte) this.a.read();
        this.b = read;
        if (read == 49) {
            return u(i2);
        }
        throw new IOException("Set tag error");
    }

    public i[] o(int i2, boolean z) throws IOException {
        byte read = (byte) this.a.read();
        this.b = read;
        if (!z && read != 49) {
            throw new IOException("Set tag error");
        }
        return u(i2);
    }

    public Date p() throws IOException {
        if (this.a.read() == 23) {
            f fVar = this.a;
            return fVar.n(j(fVar));
        }
        throw new IOException("DER input, UTCtime tag invalid ");
    }

    public a q() throws IOException {
        if (this.a.read() == 3) {
            int j2 = j(this.a) - 1;
            int read = (j2 * 8) - this.a.read();
            byte[] bArr = new byte[j2];
            if (j2 != 0 && this.a.read(bArr) != j2) {
                throw new IOException("short read of DER bit string");
            }
            return new a(read, bArr);
        }
        throw new IOException("DER input not a bit string");
    }

    public void s(int i2) {
        this.a.mark(i2);
    }

    public int t() throws IOException {
        return this.a.p();
    }

    protected i[] u(int i2) throws IOException {
        byte read = (byte) this.a.read();
        int i3 = i(read & 255, this.a);
        if (i3 == -1) {
            int available = this.a.available();
            byte[] bArr = new byte[available + 2];
            bArr[0] = this.b;
            bArr[1] = read;
            DataInputStream dataInputStream = new DataInputStream(this.a);
            dataInputStream.readFully(bArr, 2, available);
            dataInputStream.close();
            f fVar = new f(new e().a(bArr));
            this.a = fVar;
            if (this.b == fVar.read()) {
                i3 = j(this.a);
            } else {
                throw new IOException("Indefinite length encoding not supported");
            }
        }
        if (i3 == 0) {
            return new i[0];
        }
        g w = this.a.available() == i3 ? this : w(i3, true);
        Vector vector = new Vector(i2);
        do {
            vector.addElement(new i(w.a));
        } while (w.a() > 0);
        if (w.a() == 0) {
            int size = vector.size();
            i[] iVarArr = new i[size];
            for (int i4 = 0; i4 < size; i4++) {
                iVarArr[i4] = (i) vector.elementAt(i4);
            }
            return iVarArr;
        }
        throw new IOException("extra data at end of vector");
    }

    public void v() {
        this.a.reset();
    }

    public g w(int i2, boolean z) throws IOException {
        f f2 = this.a.f();
        f2.r(i2);
        if (z) {
            this.a.skip(i2);
        }
        return new g(f2);
    }

    public byte[] x() {
        return this.a.q();
    }

    public g(f fVar) {
        this.a = fVar;
        fVar.mark(Integer.MAX_VALUE);
    }
}
