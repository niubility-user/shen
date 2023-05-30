package sun.security.pkcs;

import java.io.IOException;
import m.a.b.i;

/* loaded from: classes11.dex */
public class g {
    private b[] a = null;

    public g(byte[] bArr) throws IOException {
        a(bArr);
    }

    public void a(byte[] bArr) throws IOException {
        i iVar = new i(bArr);
        if (iVar.a == 48) {
            i[] m2 = iVar.f20295c.m(1);
            this.a = new b[m2.length];
            for (int i2 = 0; i2 < m2.length; i2++) {
                this.a[i2] = new b(m2[i2]);
            }
            if (iVar.f20295c.a() > 0) {
                for (int i3 = 0; i3 < iVar.f20295c.m(1).length; i3++) {
                }
                return;
            }
            return;
        }
        throw new IOException("Bad encoding for signingCertificate");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[\n");
        int i2 = 0;
        while (true) {
            b[] bVarArr = this.a;
            if (i2 < bVarArr.length) {
                stringBuffer.append(bVarArr[i2].toString());
                i2++;
            } else {
                stringBuffer.append("\n]");
                return stringBuffer.toString();
            }
        }
    }
}
