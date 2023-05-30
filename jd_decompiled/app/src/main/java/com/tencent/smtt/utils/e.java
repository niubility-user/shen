package com.tencent.smtt.utils;

import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

/* loaded from: classes9.dex */
public class e implements Closeable {
    static final char[] a = {Ascii.MAX, 'E', 'L', 'F', 0};
    final char[] b;

    /* renamed from: c  reason: collision with root package name */
    boolean f17919c;
    j[] d;

    /* renamed from: e  reason: collision with root package name */
    l[] f17920e;

    /* renamed from: f  reason: collision with root package name */
    byte[] f17921f;

    /* renamed from: g  reason: collision with root package name */
    private final com.tencent.smtt.utils.c f17922g;

    /* renamed from: h  reason: collision with root package name */
    private final a f17923h;

    /* renamed from: i  reason: collision with root package name */
    private final k[] f17924i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f17925j;

    /* loaded from: classes9.dex */
    public static abstract class a {
        short a;
        short b;

        /* renamed from: c  reason: collision with root package name */
        int f17926c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        short f17927e;

        /* renamed from: f  reason: collision with root package name */
        short f17928f;

        /* renamed from: g  reason: collision with root package name */
        short f17929g;

        /* renamed from: h  reason: collision with root package name */
        short f17930h;

        /* renamed from: i  reason: collision with root package name */
        short f17931i;

        /* renamed from: j  reason: collision with root package name */
        short f17932j;

        abstract long a();

        abstract long b();
    }

    /* loaded from: classes9.dex */
    static class b extends a {

        /* renamed from: k  reason: collision with root package name */
        int f17933k;

        /* renamed from: l  reason: collision with root package name */
        int f17934l;

        /* renamed from: m  reason: collision with root package name */
        int f17935m;

        b() {
        }

        @Override // com.tencent.smtt.utils.e.a
        long a() {
            return this.f17935m;
        }

        @Override // com.tencent.smtt.utils.e.a
        long b() {
            return this.f17934l;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class c extends j {
        int a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f17936c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f17937e;

        /* renamed from: f  reason: collision with root package name */
        int f17938f;

        c() {
        }
    }

    /* loaded from: classes9.dex */
    static class d extends k {
        int a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f17939c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f17940e;

        /* renamed from: f  reason: collision with root package name */
        int f17941f;

        d() {
        }

        @Override // com.tencent.smtt.utils.e.k
        public int a() {
            return this.d;
        }

        @Override // com.tencent.smtt.utils.e.k
        public long b() {
            return this.f17939c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.smtt.utils.e$e  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0814e extends l {
        int a;
        int b;

        C0814e() {
        }
    }

    /* loaded from: classes9.dex */
    static class f extends a {

        /* renamed from: k  reason: collision with root package name */
        long f17942k;

        /* renamed from: l  reason: collision with root package name */
        long f17943l;

        /* renamed from: m  reason: collision with root package name */
        long f17944m;

        f() {
        }

        @Override // com.tencent.smtt.utils.e.a
        long a() {
            return this.f17944m;
        }

        @Override // com.tencent.smtt.utils.e.a
        long b() {
            return this.f17943l;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class g extends j {
        long a;
        long b;

        /* renamed from: c  reason: collision with root package name */
        long f17945c;
        long d;

        /* renamed from: e  reason: collision with root package name */
        long f17946e;

        /* renamed from: f  reason: collision with root package name */
        long f17947f;

        g() {
        }
    }

    /* loaded from: classes9.dex */
    static class h extends k {
        long a;
        long b;

        /* renamed from: c  reason: collision with root package name */
        long f17948c;
        long d;

        /* renamed from: e  reason: collision with root package name */
        long f17949e;

        /* renamed from: f  reason: collision with root package name */
        long f17950f;

        h() {
        }

        @Override // com.tencent.smtt.utils.e.k
        public int a() {
            return (int) this.d;
        }

        @Override // com.tencent.smtt.utils.e.k
        public long b() {
            return this.f17948c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class i extends l {
        long a;
        long b;

        i() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static abstract class j {

        /* renamed from: g  reason: collision with root package name */
        int f17951g;

        /* renamed from: h  reason: collision with root package name */
        int f17952h;

        j() {
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class k {

        /* renamed from: g  reason: collision with root package name */
        int f17953g;

        /* renamed from: h  reason: collision with root package name */
        int f17954h;

        /* renamed from: i  reason: collision with root package name */
        int f17955i;

        /* renamed from: j  reason: collision with root package name */
        int f17956j;

        public abstract int a();

        public abstract long b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static abstract class l {

        /* renamed from: c  reason: collision with root package name */
        int f17957c;
        char d;

        /* renamed from: e  reason: collision with root package name */
        char f17958e;

        /* renamed from: f  reason: collision with root package name */
        short f17959f;

        l() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(File file) throws IOException, UnknownFormatConversionException {
        b bVar;
        char[] cArr = new char[16];
        this.b = cArr;
        com.tencent.smtt.utils.c cVar = new com.tencent.smtt.utils.c(file);
        this.f17922g = cVar;
        cVar.a(cArr);
        if (!a()) {
            throw new UnknownFormatConversionException("Invalid elf magic: " + file);
        }
        cVar.a(e());
        boolean d2 = d();
        if (d2) {
            f fVar = new f();
            fVar.a = cVar.a();
            fVar.b = cVar.a();
            fVar.f17926c = cVar.b();
            fVar.f17942k = cVar.c();
            fVar.f17943l = cVar.c();
            fVar.f17944m = cVar.c();
            bVar = fVar;
        } else {
            b bVar2 = new b();
            bVar2.a = cVar.a();
            bVar2.b = cVar.a();
            bVar2.f17926c = cVar.b();
            bVar2.f17933k = cVar.b();
            bVar2.f17934l = cVar.b();
            bVar2.f17935m = cVar.b();
            bVar = bVar2;
        }
        this.f17923h = bVar;
        a aVar = this.f17923h;
        aVar.d = cVar.b();
        aVar.f17927e = cVar.a();
        aVar.f17928f = cVar.a();
        aVar.f17929g = cVar.a();
        aVar.f17930h = cVar.a();
        aVar.f17931i = cVar.a();
        aVar.f17932j = cVar.a();
        this.f17924i = new k[aVar.f17931i];
        for (int i2 = 0; i2 < aVar.f17931i; i2++) {
            cVar.a(aVar.a() + (aVar.f17930h * i2));
            if (d2) {
                h hVar = new h();
                hVar.f17953g = cVar.b();
                hVar.f17954h = cVar.b();
                hVar.a = cVar.c();
                hVar.b = cVar.c();
                hVar.f17948c = cVar.c();
                hVar.d = cVar.c();
                hVar.f17955i = cVar.b();
                hVar.f17956j = cVar.b();
                hVar.f17949e = cVar.c();
                hVar.f17950f = cVar.c();
                this.f17924i[i2] = hVar;
            } else {
                d dVar = new d();
                dVar.f17953g = cVar.b();
                dVar.f17954h = cVar.b();
                dVar.a = cVar.b();
                dVar.b = cVar.b();
                dVar.f17939c = cVar.b();
                dVar.d = cVar.b();
                dVar.f17955i = cVar.b();
                dVar.f17956j = cVar.b();
                dVar.f17940e = cVar.b();
                dVar.f17941f = cVar.b();
                this.f17924i[i2] = dVar;
            }
        }
        short s = aVar.f17932j;
        if (s > -1) {
            k[] kVarArr = this.f17924i;
            if (s < kVarArr.length) {
                k kVar = kVarArr[s];
                if (kVar.f17954h != 3) {
                    throw new UnknownFormatConversionException("Wrong string section e_shstrndx=" + ((int) aVar.f17932j));
                }
                this.f17925j = new byte[kVar.a()];
                cVar.a(kVar.b());
                cVar.a(this.f17925j);
                if (this.f17919c) {
                    f();
                    return;
                }
                return;
            }
        }
        throw new UnknownFormatConversionException("Invalid e_shstrndx=" + ((int) aVar.f17932j));
    }

    public static boolean a(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long readInt = randomAccessFile.readInt();
            randomAccessFile.close();
            return readInt == 2135247942;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(File file) {
        StringBuilder sb;
        String str;
        if (g() && a(file)) {
            try {
                new e(file);
                return true;
            } catch (IOException e2) {
                String str2 = "checkElfFile IOException: " + e2;
                return false;
            } catch (UnknownFormatConversionException e3) {
                e = e3;
                sb = new StringBuilder();
                str = "checkElfFile UnknownFormatConversionException: ";
                sb.append(str);
                sb.append(e);
                sb.toString();
                return true;
            } catch (Throwable th) {
                e = th;
                sb = new StringBuilder();
                str = "checkElfFile Throwable: ";
                sb.append(str);
                sb.append(e);
                sb.toString();
                return true;
            }
        }
        return true;
    }

    private void f() throws IOException {
        a aVar = this.f17923h;
        com.tencent.smtt.utils.c cVar = this.f17922g;
        boolean d2 = d();
        k a2 = a(".dynsym");
        if (a2 != null) {
            cVar.a(a2.b());
            int a3 = a2.a() / (d2 ? 24 : 16);
            this.f17920e = new l[a3];
            char[] cArr = new char[1];
            for (int i2 = 0; i2 < a3; i2++) {
                if (d2) {
                    i iVar = new i();
                    iVar.f17957c = cVar.b();
                    cVar.a(cArr);
                    iVar.d = cArr[0];
                    cVar.a(cArr);
                    iVar.f17958e = cArr[0];
                    iVar.a = cVar.c();
                    iVar.b = cVar.c();
                    iVar.f17959f = cVar.a();
                    this.f17920e[i2] = iVar;
                } else {
                    C0814e c0814e = new C0814e();
                    c0814e.f17957c = cVar.b();
                    c0814e.a = cVar.b();
                    c0814e.b = cVar.b();
                    cVar.a(cArr);
                    c0814e.d = cArr[0];
                    cVar.a(cArr);
                    c0814e.f17958e = cArr[0];
                    c0814e.f17959f = cVar.a();
                    this.f17920e[i2] = c0814e;
                }
            }
            k kVar = this.f17924i[a2.f17955i];
            cVar.a(kVar.b());
            byte[] bArr = new byte[kVar.a()];
            this.f17921f = bArr;
            cVar.a(bArr);
        }
        this.d = new j[aVar.f17929g];
        for (int i3 = 0; i3 < aVar.f17929g; i3++) {
            cVar.a(aVar.b() + (aVar.f17928f * i3));
            if (d2) {
                g gVar = new g();
                gVar.f17951g = cVar.b();
                gVar.f17952h = cVar.b();
                gVar.a = cVar.c();
                gVar.b = cVar.c();
                gVar.f17945c = cVar.c();
                gVar.d = cVar.c();
                gVar.f17946e = cVar.c();
                gVar.f17947f = cVar.c();
                this.d[i3] = gVar;
            } else {
                c cVar2 = new c();
                cVar2.f17951g = cVar.b();
                cVar2.f17952h = cVar.b();
                cVar2.a = cVar.b();
                cVar2.b = cVar.b();
                cVar2.f17936c = cVar.b();
                cVar2.d = cVar.b();
                cVar2.f17937e = cVar.b();
                cVar2.f17938f = cVar.b();
                this.d[i3] = cVar2;
            }
        }
    }

    private static boolean g() {
        String property = System.getProperty("java.vm.version");
        return property != null && property.startsWith("2");
    }

    public final k a(String str) {
        for (k kVar : this.f17924i) {
            if (str.equals(a(kVar.f17953g))) {
                return kVar;
            }
        }
        return null;
    }

    public final String a(int i2) {
        if (i2 == 0) {
            return "SHN_UNDEF";
        }
        int i3 = i2;
        while (this.f17925j[i3] != 0) {
            i3++;
        }
        return new String(this.f17925j, i2, i3 - i2);
    }

    final boolean a() {
        return this.b[0] == a[0];
    }

    final char b() {
        return this.b[4];
    }

    final char c() {
        return this.b[5];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f17922g.close();
    }

    public final boolean d() {
        return b() == 2;
    }

    public final boolean e() {
        return c() == 1;
    }
}
