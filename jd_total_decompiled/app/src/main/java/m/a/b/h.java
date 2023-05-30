package m.a.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes11.dex */
public class h extends ByteArrayOutputStream implements d {

    /* renamed from: g */
    private static b f20294g = new b();

    private void B(String str, byte b, String str2) throws IOException {
        byte[] bytes = str.getBytes(str2);
        write(b);
        n(bytes.length);
        write(bytes);
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x005b A[LOOP:1: B:86:0x0059->B:87:0x005b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m(int i2) throws IOException {
        int i3;
        byte[] bArr = {(byte) ((i2 & (-16777216)) >>> 24), (byte) ((16711680 & i2) >>> 16), (byte) ((65280 & i2) >>> 8), (byte) (i2 & 255)};
        int i4 = 0;
        if (bArr[0] == 255) {
            i3 = 0;
            while (i4 < 3 && bArr[i4] == 255) {
                i4++;
                if ((bArr[i4] & 128) != 128) {
                    break;
                }
                i3++;
            }
        } else {
            if (bArr[0] == 0) {
                i3 = 0;
                while (i4 < 3 && bArr[i4] == 0) {
                    i4++;
                    if ((bArr[i4] & 128) != 0) {
                        break;
                    }
                    i3++;
                }
            }
            n(4 - i4);
            while (i4 < 4) {
                write(bArr[i4]);
                i4++;
            }
        }
        i4 = i3;
        n(4 - i4);
        while (i4 < 4) {
        }
    }

    private void r(byte b, d[] dVarArr, Comparator<byte[]> comparator) throws IOException {
        int length = dVarArr.length;
        h[] hVarArr = new h[length];
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            hVarArr[i2] = new h();
            dVarArr[i2].derEncode(hVarArr[i2]);
        }
        byte[][] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = hVarArr[i3].toByteArray();
        }
        Arrays.sort(bArr, comparator);
        h hVar = new h();
        for (int i4 = 0; i4 < length; i4++) {
            hVar.write(bArr[i4]);
        }
        y(b, hVar);
    }

    private void u(Date date, byte b) throws IOException {
        String str;
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        if (b == 23) {
            str = "yyMMddHHmmss'Z'";
        } else {
            b = 24;
            str = "yyyyMMddHHmmss'Z'";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(timeZone);
        byte[] bytes = simpleDateFormat.format(date).getBytes(CharEncoding.ISO_8859_1);
        write(b);
        n(bytes.length);
        write(bytes);
    }

    public void A(byte b, h hVar) throws IOException {
        write(b);
        write(((ByteArrayOutputStream) hVar).buf, 1, ((ByteArrayOutputStream) hVar).count - 1);
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    public void f(byte[] bArr) throws IOException {
        write(3);
        n(bArr.length + 1);
        write(0);
        write(bArr);
    }

    public void g(boolean z) throws IOException {
        write(1);
        n(1);
        if (z) {
            write(255);
        } else {
            write(0);
        }
    }

    public void h(i iVar) throws IOException {
        iVar.d(this);
    }

    public void i(Date date) throws IOException {
        u(date, (byte) 24);
    }

    public void j(String str) throws IOException {
        B(str, (byte) 22, "ASCII");
    }

    public void k(int i2) throws IOException {
        write(2);
        m(i2);
    }

    public void l(BigInteger bigInteger) throws IOException {
        write(2);
        byte[] byteArray = bigInteger.toByteArray();
        n(byteArray.length);
        write(byteArray, 0, byteArray.length);
    }

    public void n(int i2) throws IOException {
        if (i2 < 128) {
            write((byte) i2);
        } else if (i2 < 256) {
            write(-127);
            write((byte) i2);
        } else if (i2 < 65536) {
            write(-126);
            write((byte) (i2 >> 8));
            write((byte) i2);
        } else if (i2 < 16777216) {
            write(-125);
            write((byte) (i2 >> 16));
            write((byte) (i2 >> 8));
            write((byte) i2);
        } else {
            write(-124);
            write((byte) (i2 >> 24));
            write((byte) (i2 >> 16));
            write((byte) (i2 >> 8));
            write((byte) i2);
        }
    }

    public void o() throws IOException {
        write(5);
        n(0);
    }

    public void p(j jVar) throws IOException {
        jVar.b(this);
    }

    public void q(byte[] bArr) throws IOException {
        z((byte) 4, bArr);
    }

    public void s(byte b, d[] dVarArr) throws IOException {
        r(b, dVarArr, f20294g);
    }

    public void t(String str) throws IOException {
        B(str, (byte) 19, "ASCII");
    }

    public void v(a aVar) throws IOException {
        x(aVar.h());
    }

    public void w(Date date) throws IOException {
        u(date, (byte) 23);
    }

    public void x(a aVar) throws IOException {
        byte[] g2 = aVar.g();
        write(3);
        n(g2.length + 1);
        write((g2.length * 8) - aVar.b());
        write(g2);
    }

    public void y(byte b, h hVar) throws IOException {
        write(b);
        n(((ByteArrayOutputStream) hVar).count);
        write(((ByteArrayOutputStream) hVar).buf, 0, ((ByteArrayOutputStream) hVar).count);
    }

    public void z(byte b, byte[] bArr) throws IOException {
        write(b);
        n(bArr.length);
        write(bArr, 0, bArr.length);
    }
}
