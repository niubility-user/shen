package k.a.a.d;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import k.a.a.f.f;
import k.a.a.f.g;
import k.a.a.f.h;
import k.a.a.f.i;
import k.a.a.f.j;
import k.a.a.f.k;
import k.a.a.f.l;
import k.a.a.f.n;
import k.a.a.f.o.d;
import k.a.a.i.e;

/* loaded from: classes11.dex */
public class a {
    private n a;
    private e b = new e();

    /* renamed from: c */
    private byte[] f20200c = new byte[4];

    private long a(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[4096];
        long filePointer = randomAccessFile.getFilePointer();
        do {
            int i2 = filePointer > 4096 ? 4096 : (int) filePointer;
            filePointer = (filePointer - i2) + 4;
            if (filePointer == 4) {
                filePointer = 0;
            }
            v(randomAccessFile, filePointer);
            randomAccessFile.read(bArr, 0, i2);
            for (int i3 = 0; i3 < i2 - 3; i3++) {
                if (this.b.e(bArr, i3) == b.END_OF_CENTRAL_DIRECTORY.getValue()) {
                    return filePointer + i3;
                }
            }
        } while (filePointer > 0);
        throw new k.a.a.c.a("Zip headers not found. Probably not a zip file");
    }

    private long b(n nVar) {
        if (nVar.g()) {
            return nVar.d().e();
        }
        return nVar.b().e();
    }

    private List<g> c(byte[] bArr, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < i2) {
            g gVar = new g();
            gVar.f(this.b.m(bArr, i3));
            int i4 = i3 + 2;
            int m2 = this.b.m(bArr, i4);
            gVar.g(m2);
            int i5 = i4 + 2;
            if (m2 > 0) {
                byte[] bArr2 = new byte[m2];
                System.arraycopy(bArr, i5, bArr2, 0, m2);
                gVar.e(bArr2);
            }
            i3 = i5 + m2;
            arrayList.add(gVar);
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private k.a.a.f.a d(List<g> list, e eVar) throws k.a.a.c.a {
        if (list == null) {
            return null;
        }
        for (g gVar : list) {
            if (gVar != null) {
                long c2 = gVar.c();
                b bVar = b.AES_EXTRA_DATA_RECORD;
                if (c2 == bVar.getValue()) {
                    if (gVar.b() != null) {
                        k.a.a.f.a aVar = new k.a.a.f.a();
                        aVar.a(bVar);
                        aVar.h(gVar.d());
                        byte[] b = gVar.b();
                        aVar.f(k.a.a.f.o.b.getFromVersionNumber(eVar.m(b, 0)));
                        byte[] bArr = new byte[2];
                        System.arraycopy(b, 2, bArr, 0, 2);
                        aVar.i(new String(bArr));
                        aVar.e(k.a.a.f.o.a.getAesKeyStrengthFromRawCode(b[4] & 255));
                        aVar.g(k.a.a.f.o.c.getCompressionMethodFromCode(eVar.m(b, 5)));
                        return aVar;
                    }
                    throw new k.a.a.c.a("corrupt AES extra data records");
                }
            }
        }
        return null;
    }

    private void e(h hVar, e eVar) throws k.a.a.c.a {
        k.a.a.f.a d;
        if (hVar.h() == null || hVar.h().size() <= 0 || (d = d(hVar.h(), eVar)) == null) {
            return;
        }
        hVar.s(d);
        hVar.A(d.AES);
    }

    private void f(i iVar, e eVar) throws k.a.a.c.a {
        k.a.a.f.a d;
        if (iVar.h() == null || iVar.h().size() <= 0 || (d = d(iVar.h(), eVar)) == null) {
            return;
        }
        iVar.s(d);
        iVar.A(d.AES);
    }

    private k.a.a.f.c h(RandomAccessFile randomAccessFile, e eVar, Charset charset) throws IOException {
        k.a.a.f.c cVar = new k.a.a.f.c();
        ArrayList arrayList = new ArrayList();
        long b = c.b(this.a);
        long b2 = b(this.a);
        randomAccessFile.seek(b);
        int i2 = 2;
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[4];
        int i3 = 0;
        int i4 = 0;
        while (i4 < b2) {
            h hVar = new h();
            byte[] bArr3 = bArr2;
            long c2 = eVar.c(randomAccessFile);
            b bVar = b.CENTRAL_DIRECTORY;
            if (c2 == bVar.getValue()) {
                hVar.a(bVar);
                hVar.U(eVar.l(randomAccessFile));
                hVar.J(eVar.l(randomAccessFile));
                byte[] bArr4 = new byte[i2];
                randomAccessFile.readFully(bArr4);
                hVar.z(k.a.a.i.b.a(bArr4[i3], i3));
                hVar.x(k.a.a.i.b.a(bArr4[i3], 3));
                hVar.F(k.a.a.i.b.a(bArr4[1], 3));
                hVar.G((byte[]) bArr4.clone());
                hVar.u(k.a.a.f.o.c.getCompressionMethodFromCode(eVar.l(randomAccessFile)));
                hVar.H(eVar.c(randomAccessFile));
                randomAccessFile.readFully(bArr3);
                byte[] bArr5 = bArr;
                hVar.v(eVar.j(bArr3, i3));
                hVar.w(bArr3);
                hVar.t(eVar.i(randomAccessFile, 4));
                hVar.I(eVar.i(randomAccessFile, 4));
                int l2 = eVar.l(randomAccessFile);
                hVar.E(l2);
                hVar.C(eVar.l(randomAccessFile));
                int l3 = eVar.l(randomAccessFile);
                hVar.R(l3);
                hVar.O(eVar.l(randomAccessFile));
                randomAccessFile.readFully(bArr5);
                hVar.S((byte[]) bArr5.clone());
                randomAccessFile.readFully(bArr3);
                hVar.P((byte[]) bArr3.clone());
                randomAccessFile.readFully(bArr3);
                long j2 = b2;
                hVar.T(eVar.j(bArr3, 0));
                if (l2 > 0) {
                    byte[] bArr6 = new byte[l2];
                    randomAccessFile.readFully(bArr6);
                    String a = c.a(bArr6, hVar.r(), charset);
                    if (a.contains(":\\")) {
                        a = a.substring(a.indexOf(":\\") + 2);
                    }
                    hVar.D(a);
                    hVar.y(a.endsWith("/") || a.endsWith("\\"));
                } else {
                    hVar.D(null);
                }
                n(randomAccessFile, hVar);
                s(hVar, eVar);
                e(hVar, eVar);
                if (l3 > 0) {
                    byte[] bArr7 = new byte[l3];
                    randomAccessFile.readFully(bArr7);
                    hVar.Q(c.a(bArr7, hVar.r(), charset));
                }
                if (hVar.q()) {
                    if (hVar.b() != null) {
                        hVar.A(d.AES);
                    } else {
                        hVar.A(d.ZIP_STANDARD);
                    }
                }
                arrayList.add(hVar);
                i4++;
                bArr = bArr5;
                bArr2 = bArr3;
                b2 = j2;
                i2 = 2;
                i3 = 0;
            } else {
                throw new k.a.a.c.a("Expected central directory entry not found (#" + (i4 + 1) + ")");
            }
        }
        cVar.b(arrayList);
        k.a.a.f.e eVar2 = new k.a.a.f.e();
        b bVar2 = b.DIGITAL_SIGNATURE;
        if (eVar.c(randomAccessFile) == bVar2.getValue()) {
            eVar2.a(bVar2);
            eVar2.d(eVar.l(randomAccessFile));
            if (eVar2.b() > 0) {
                byte[] bArr8 = new byte[eVar2.b()];
                randomAccessFile.readFully(bArr8);
                eVar2.c(new String(bArr8));
            }
        }
        return cVar;
    }

    private f j(RandomAccessFile randomAccessFile, e eVar, Charset charset) throws IOException {
        long length = randomAccessFile.length() - 22;
        v(randomAccessFile, length);
        b bVar = b.END_OF_CENTRAL_DIRECTORY;
        if (eVar.c(randomAccessFile) != bVar.getValue()) {
            length = a(randomAccessFile);
            randomAccessFile.seek(4 + length);
        }
        f fVar = new f();
        fVar.a(bVar);
        fVar.g(eVar.l(randomAccessFile));
        fVar.h(eVar.l(randomAccessFile));
        fVar.m(eVar.l(randomAccessFile));
        fVar.l(eVar.l(randomAccessFile));
        fVar.k(eVar.c(randomAccessFile));
        fVar.i(length);
        randomAccessFile.readFully(this.f20200c);
        fVar.j(eVar.j(this.f20200c, 0));
        fVar.f(u(randomAccessFile, eVar.l(randomAccessFile), charset));
        this.a.j(fVar.b() > 0);
        return fVar;
    }

    private List<g> k(InputStream inputStream, int i2) throws IOException {
        if (i2 < 4) {
            if (i2 > 0) {
                inputStream.skip(i2);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i2];
        k.a.a.i.g.g(inputStream, bArr);
        try {
            return c(bArr, i2);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<g> l(RandomAccessFile randomAccessFile, int i2) throws IOException {
        if (i2 < 4) {
            if (i2 > 0) {
                randomAccessFile.skipBytes(i2);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i2];
        randomAccessFile.read(bArr);
        try {
            return c(bArr, i2);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private void m(InputStream inputStream, i iVar) throws IOException {
        int i2 = iVar.i();
        if (i2 <= 0) {
            return;
        }
        iVar.B(k(inputStream, i2));
    }

    private void n(RandomAccessFile randomAccessFile, h hVar) throws IOException {
        int i2 = hVar.i();
        if (i2 <= 0) {
            return;
        }
        hVar.B(l(randomAccessFile, i2));
    }

    private k p(RandomAccessFile randomAccessFile, e eVar) throws IOException {
        if (this.a.c() != null) {
            long b = this.a.c().b();
            if (b >= 0) {
                randomAccessFile.seek(b);
                k kVar = new k();
                long c2 = eVar.c(randomAccessFile);
                b bVar = b.ZIP64_END_CENTRAL_DIRECTORY_RECORD;
                if (c2 == bVar.getValue()) {
                    kVar.a(bVar);
                    kVar.k(eVar.h(randomAccessFile));
                    kVar.n(eVar.l(randomAccessFile));
                    kVar.o(eVar.l(randomAccessFile));
                    kVar.g(eVar.c(randomAccessFile));
                    kVar.h(eVar.c(randomAccessFile));
                    kVar.m(eVar.h(randomAccessFile));
                    kVar.l(eVar.h(randomAccessFile));
                    kVar.j(eVar.h(randomAccessFile));
                    kVar.i(eVar.h(randomAccessFile));
                    long d = kVar.d() - 44;
                    if (d > 0) {
                        byte[] bArr = new byte[(int) d];
                        randomAccessFile.readFully(bArr);
                        kVar.f(bArr);
                    }
                    return kVar;
                }
                throw new k.a.a.c.a("invalid signature for zip64 end of central directory record");
            }
            throw new k.a.a.c.a("invalid offset for start of end of central directory record");
        }
        throw new k.a.a.c.a("invalid zip64 end of central directory locator");
    }

    private j q(RandomAccessFile randomAccessFile, e eVar, long j2) throws IOException {
        j jVar = new j();
        w(randomAccessFile, j2);
        b bVar = b.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR;
        if (eVar.c(randomAccessFile) == bVar.getValue()) {
            this.a.m(true);
            jVar.a(bVar);
            jVar.c(eVar.c(randomAccessFile));
            jVar.d(eVar.h(randomAccessFile));
            jVar.e(eVar.c(randomAccessFile));
            return jVar;
        }
        this.a.m(false);
        return null;
    }

    private l r(List<g> list, e eVar, long j2, long j3, long j4, int i2) {
        for (g gVar : list) {
            if (gVar != null && b.ZIP64_EXTRA_FIELD_SIGNATURE.getValue() == gVar.c()) {
                l lVar = new l();
                byte[] b = gVar.b();
                if (gVar.d() <= 0) {
                    return null;
                }
                int i3 = 0;
                if (gVar.d() > 0 && j2 == 4294967295L) {
                    lVar.i(eVar.j(b, 0));
                    i3 = 8;
                }
                if (i3 < gVar.d() && j3 == 4294967295L) {
                    lVar.f(eVar.j(b, i3));
                    i3 += 8;
                }
                if (i3 < gVar.d() && j4 == 4294967295L) {
                    lVar.h(eVar.j(b, i3));
                    i3 += 8;
                }
                if (i3 < gVar.d() && i2 == 65535) {
                    lVar.g(eVar.e(b, i3));
                }
                return lVar;
            }
        }
        return null;
    }

    private void s(h hVar, e eVar) throws k.a.a.c.a {
        l r;
        if (hVar.h() == null || hVar.h().size() <= 0 || (r = r(hVar.h(), eVar, hVar.m(), hVar.c(), hVar.N(), hVar.L())) == null) {
            return;
        }
        hVar.K(r);
        if (r.e() != -1) {
            hVar.I(r.e());
        }
        if (r.b() != -1) {
            hVar.t(r.b());
        }
        if (r.d() != -1) {
            hVar.T(r.d());
        }
        if (r.c() != -1) {
            hVar.O(r.c());
        }
    }

    private void t(i iVar, e eVar) throws k.a.a.c.a {
        l r;
        if (iVar != null) {
            if (iVar.h() == null || iVar.h().size() <= 0 || (r = r(iVar.h(), eVar, iVar.m(), iVar.c(), 0L, 0)) == null) {
                return;
            }
            iVar.K(r);
            if (r.e() != -1) {
                iVar.I(r.e());
            }
            if (r.b() != -1) {
                iVar.t(r.b());
                return;
            }
            return;
        }
        throw new k.a.a.c.a("file header is null in reading Zip64 Extended Info");
    }

    private String u(RandomAccessFile randomAccessFile, int i2, Charset charset) {
        if (i2 <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i2];
            randomAccessFile.readFully(bArr);
            return new String(bArr, charset);
        } catch (IOException unused) {
            return null;
        }
    }

    private void v(RandomAccessFile randomAccessFile, long j2) throws IOException {
        if (randomAccessFile instanceof k.a.a.e.a.g) {
            ((k.a.a.e.a.g) randomAccessFile).i(j2);
        } else {
            randomAccessFile.seek(j2);
        }
    }

    private void w(RandomAccessFile randomAccessFile, long j2) throws IOException {
        v(randomAccessFile, (((j2 - 4) - 8) - 4) - 4);
    }

    public n g(RandomAccessFile randomAccessFile, Charset charset) throws IOException {
        if (randomAccessFile.length() >= 22) {
            n nVar = new n();
            this.a = nVar;
            try {
                nVar.i(j(randomAccessFile, this.b, charset));
                if (this.a.b().e() == 0) {
                    return this.a;
                }
                n nVar2 = this.a;
                nVar2.k(q(randomAccessFile, this.b, nVar2.b().c()));
                if (this.a.g()) {
                    this.a.l(p(randomAccessFile, this.b));
                    if (this.a.d() != null && this.a.d().b() > 0) {
                        this.a.j(true);
                    } else {
                        this.a.j(false);
                    }
                }
                this.a.h(h(randomAccessFile, this.b, charset));
                return this.a;
            } catch (k.a.a.c.a e2) {
                throw e2;
            } catch (IOException e3) {
                throw new k.a.a.c.a("Zip headers not found. Probably not a zip file or a corrupted zip file", e3);
            }
        }
        throw new k.a.a.c.a("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
    }

    public k.a.a.f.d i(InputStream inputStream, boolean z) throws IOException {
        k.a.a.f.d dVar = new k.a.a.f.d();
        byte[] bArr = new byte[4];
        k.a.a.i.g.g(inputStream, bArr);
        long j2 = this.b.j(bArr, 0);
        b bVar = b.EXTRA_DATA_RECORD;
        if (j2 == bVar.getValue()) {
            dVar.a(bVar);
            k.a.a.i.g.g(inputStream, bArr);
            dVar.f(this.b.j(bArr, 0));
        } else {
            dVar.f(j2);
        }
        if (z) {
            dVar.e(this.b.f(inputStream));
            dVar.g(this.b.f(inputStream));
        } else {
            dVar.e(this.b.b(inputStream));
            dVar.g(this.b.b(inputStream));
        }
        return dVar;
    }

    public i o(InputStream inputStream, Charset charset) throws IOException {
        i iVar = new i();
        byte[] bArr = new byte[4];
        b bVar = b.LOCAL_FILE_HEADER;
        if (this.b.b(inputStream) != bVar.getValue()) {
            return null;
        }
        iVar.a(bVar);
        iVar.J(this.b.k(inputStream));
        byte[] bArr2 = new byte[2];
        if (k.a.a.i.g.g(inputStream, bArr2) == 2) {
            iVar.z(k.a.a.i.b.a(bArr2[0], 0));
            iVar.x(k.a.a.i.b.a(bArr2[0], 3));
            boolean z = true;
            iVar.F(k.a.a.i.b.a(bArr2[1], 3));
            iVar.G((byte[]) bArr2.clone());
            iVar.u(k.a.a.f.o.c.getCompressionMethodFromCode(this.b.k(inputStream)));
            iVar.H(this.b.b(inputStream));
            k.a.a.i.g.g(inputStream, bArr);
            iVar.v(this.b.j(bArr, 0));
            iVar.w((byte[]) bArr.clone());
            iVar.t(this.b.g(inputStream, 4));
            iVar.I(this.b.g(inputStream, 4));
            int k2 = this.b.k(inputStream);
            iVar.E(k2);
            iVar.C(this.b.k(inputStream));
            if (k2 > 0) {
                byte[] bArr3 = new byte[k2];
                k.a.a.i.g.g(inputStream, bArr3);
                String a = c.a(bArr3, iVar.r(), charset);
                if (a != null) {
                    if (a.contains(":" + System.getProperty("file.separator"))) {
                        a = a.substring(a.indexOf(":" + System.getProperty("file.separator")) + 2);
                    }
                    iVar.D(a);
                    if (!a.endsWith("/") && !a.endsWith("\\")) {
                        z = false;
                    }
                    iVar.y(z);
                } else {
                    throw new k.a.a.c.a("file name is null, cannot assign file name to local file header");
                }
            } else {
                iVar.D(null);
            }
            m(inputStream, iVar);
            t(iVar, this.b);
            f(iVar, this.b);
            if (iVar.q() && iVar.g() != d.AES) {
                if (BigInteger.valueOf(iVar.k()[0]).testBit(6)) {
                    iVar.A(d.ZIP_STANDARD_VARIANT_STRONG);
                } else {
                    iVar.A(d.ZIP_STANDARD);
                }
            }
            return iVar;
        }
        throw new k.a.a.c.a("Could not read enough bytes for generalPurposeFlags");
    }
}
