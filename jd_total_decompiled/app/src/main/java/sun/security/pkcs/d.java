package sun.security.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;

/* loaded from: classes11.dex */
public class d {
    private final Hashtable<j, c> a;
    private final Hashtable<j, j> b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f20491c;
    private boolean d;

    public d(m.a.b.g gVar) throws IOException {
        this(gVar, false);
    }

    static m.a.b.d[] a(Object[] objArr) {
        int length = objArr.length;
        m.a.b.d[] dVarArr = new m.a.b.d[length];
        for (int i2 = 0; i2 < length; i2++) {
            dVarArr[i2] = (m.a.b.d) objArr[i2];
        }
        return dVarArr;
    }

    private byte[] b(m.a.b.g gVar) throws IOException {
        c cVar;
        j b;
        byte[] D = gVar.e().D();
        D[0] = ReplyCode.reply0x31;
        boolean z = true;
        for (i iVar : new m.a.b.g(D).o(3, true)) {
            try {
                cVar = new c(iVar);
                b = cVar.b();
            } catch (e e2) {
                if (!this.d) {
                    throw e2;
                }
                z = false;
            }
            if (this.a.get(b) == null) {
                Hashtable<j, j> hashtable = this.b;
                if (hashtable != null && !hashtable.containsKey(b)) {
                    throw new IOException("Attribute " + b + " not permitted in this attribute set");
                }
                this.a.put(b, cVar);
            } else {
                throw new IOException("Duplicate PKCS9 attribute: " + b);
            }
        }
        return z ? D : d();
    }

    private byte[] d() throws IOException {
        h hVar = new h();
        hVar.s(ReplyCode.reply0x31, a(this.a.values().toArray()));
        return hVar.toByteArray();
    }

    public void c(byte b, OutputStream outputStream) throws IOException {
        outputStream.write(b);
        byte[] bArr = this.f20491c;
        outputStream.write(bArr, 1, bArr.length - 1);
    }

    public c e(j jVar) {
        return this.a.get(jVar);
    }

    public Object f(j jVar) throws IOException {
        try {
            return e(jVar).c();
        } catch (NullPointerException unused) {
            throw new IOException("No value found for attribute " + jVar);
        }
    }

    public byte[] g() throws IOException {
        return (byte[]) this.f20491c.clone();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("PKCS9 Attributes: [\n\t");
        int i2 = 1;
        boolean z = true;
        while (true) {
            j[] jVarArr = c.f20484j;
            if (i2 < jVarArr.length) {
                c e2 = e(jVarArr[i2]);
                if (e2 != null) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer.append(";\n\t");
                    }
                    stringBuffer.append(e2.toString());
                }
                i2++;
            } else {
                stringBuffer.append("\n\t] (end PKCS9 Attributes)");
                return stringBuffer.toString();
            }
        }
    }

    public d(m.a.b.g gVar, boolean z) throws IOException {
        this.a = new Hashtable<>(3);
        this.d = false;
        this.d = z;
        this.f20491c = b(gVar);
        this.b = null;
    }
}
