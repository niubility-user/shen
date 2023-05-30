package k.a.a.d;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import k.a.a.f.h;
import k.a.a.f.n;
import k.a.a.i.d;

/* loaded from: classes11.dex */
public class c {
    public static String a(byte[] bArr, boolean z, Charset charset) {
        Charset charset2 = d.b;
        if (charset2.equals(charset) && !z) {
            try {
                return new String(bArr, "Cp437");
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        } else if (charset != null) {
            return new String(bArr, charset);
        } else {
            return new String(bArr, charset2);
        }
    }

    public static long b(n nVar) {
        if (nVar.g()) {
            return nVar.d().c();
        }
        return nVar.b().d();
    }

    public static long c(List<h> list) {
        long m2;
        long j2 = 0;
        for (h hVar : list) {
            if (hVar.n() != null && hVar.n().e() > 0) {
                m2 = hVar.n().e();
            } else {
                m2 = hVar.m();
            }
            j2 += m2;
        }
        return j2;
    }
}
