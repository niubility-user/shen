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

    /* JADX WARN: Removed duplicated region for block: B:55:0x005b A[LOOP:1: B:54:0x0059->B:55:0x005b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m(int r8) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 4
            byte[] r1 = new byte[r0]
            r2 = r8 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r3 = 3
            r1[r3] = r2
            r2 = 65280(0xff00, float:9.1477E-41)
            r2 = r2 & r8
            int r2 = r2 >>> 8
            byte r2 = (byte) r2
            r4 = 2
            r1[r4] = r2
            r2 = 16711680(0xff0000, float:2.3418052E-38)
            r2 = r2 & r8
            int r2 = r2 >>> 16
            byte r2 = (byte) r2
            r4 = 1
            r1[r4] = r2
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r8 = r8 & r2
            int r8 = r8 >>> 24
            byte r8 = (byte) r8
            r2 = 0
            r1[r2] = r8
            r8 = r1[r2]
            r4 = 128(0x80, float:1.794E-43)
            r5 = 255(0xff, float:3.57E-43)
            if (r8 != r5) goto L3e
            r8 = 0
        L2e:
            if (r2 >= r3) goto L53
            r6 = r1[r2]
            if (r6 != r5) goto L53
            int r2 = r2 + 1
            r6 = r1[r2]
            r6 = r6 & r4
            if (r6 != r4) goto L53
            int r8 = r8 + 1
            goto L2e
        L3e:
            r8 = r1[r2]
            if (r8 != 0) goto L54
            r8 = 0
        L43:
            if (r2 >= r3) goto L53
            r5 = r1[r2]
            if (r5 != 0) goto L53
            int r2 = r2 + 1
            r5 = r1[r2]
            r5 = r5 & r4
            if (r5 != 0) goto L53
            int r8 = r8 + 1
            goto L43
        L53:
            r2 = r8
        L54:
            int r8 = 4 - r2
            r7.n(r8)
        L59:
            if (r2 >= r0) goto L63
            r8 = r1[r2]
            r7.write(r8)
            int r2 = r2 + 1
            goto L59
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m.a.b.h.m(int):void");
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
