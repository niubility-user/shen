package m.a.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class x0 {
    final a[] a;
    private volatile String b;

    public x0(m.a.b.i iVar) throws IOException {
        if (iVar.a == 49) {
            m.a.b.i[] n2 = new m.a.b.g(iVar.D()).n(5);
            this.a = new a[n2.length];
            for (int i2 = 0; i2 < n2.length; i2++) {
                this.a[i2] = new a(n2[i2]);
            }
            return;
        }
        throw new IOException("X500 RDN");
    }

    private String d(boolean z, Map<String, String> map) {
        a[] aVarArr = this.a;
        int i2 = 0;
        if (aVarArr.length == 1) {
            return z ? aVarArr[0].e() : aVarArr[0].f(map);
        }
        StringBuilder sb = new StringBuilder();
        if (!z) {
            while (i2 < this.a.length) {
                if (i2 > 0) {
                    sb.append('+');
                }
                sb.append(this.a[i2].f(map));
                i2++;
            }
        } else {
            ArrayList arrayList = new ArrayList(this.a.length);
            int i3 = 0;
            while (true) {
                a[] aVarArr2 = this.a;
                if (i3 >= aVarArr2.length) {
                    break;
                }
                arrayList.add(aVarArr2[i3]);
                i3++;
            }
            Collections.sort(arrayList, b.b());
            while (i2 < arrayList.size()) {
                if (i2 > 0) {
                    sb.append('+');
                }
                sb.append(((a) arrayList.get(i2)).e());
                i2++;
            }
        }
        return sb.toString();
    }

    public void a(m.a.b.h hVar) throws IOException {
        hVar.s(ReplyCode.reply0x31, this.a);
    }

    public String b(Map<String, String> map) {
        return d(false, map);
    }

    public String c(boolean z) {
        if (!z) {
            return d(false, Collections.emptyMap());
        }
        String str = this.b;
        if (str == null) {
            String d = d(true, Collections.emptyMap());
            this.b = d;
            return d;
        }
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof x0) {
            x0 x0Var = (x0) obj;
            if (this.a.length != x0Var.a.length) {
                return false;
            }
            return c(true).equals(x0Var.c(true));
        }
        return false;
    }

    public int hashCode() {
        return c(true).hashCode();
    }

    public String toString() {
        a[] aVarArr = this.a;
        if (aVarArr.length == 1) {
            return aVarArr[0].toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.a.length; i2++) {
            if (i2 != 0) {
                sb.append(" + ");
            }
            sb.append(this.a[i2].toString());
        }
        return sb.toString();
    }
}
