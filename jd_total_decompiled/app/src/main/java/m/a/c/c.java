package m.a.c;

import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.tencent.mapsdk.internal.la;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class c {
    private static final Map<m.a.b.j, c> d = new HashMap();

    /* renamed from: e */
    private static final Map<String, c> f20307e = new HashMap();
    private String a;
    private boolean b;

    /* renamed from: c */
    private boolean f20308c;

    static {
        new c("CN", f1.D, true, true);
        new c("C", f1.E, true, true);
        new c("L", f1.F, true, true);
        m.a.b.j jVar = f1.I;
        new c("S", jVar, false, false);
        new c("ST", jVar, true, true);
        new c(IShareAdapter.SHARE_ACTION_OPEN, f1.G, true, true);
        new c("OU", f1.H, true, true);
        new c("T", f1.K, false, false);
        new c("IP", f1.Q, false, false);
        new c("STREET", f1.J, true, true);
        new c(la.p, f1.R, false, true);
        m.a.b.j jVar2 = f1.L;
        new c("DNQUALIFIER", jVar2, false, false);
        new c("DNQ", jVar2, false, false);
        new c("SURNAME", f1.M, false, false);
        new c("GIVENNAME", f1.N, false, false);
        new c("INITIALS", f1.O, false, false);
        new c("GENERATION", f1.P, false, false);
        m.a.b.j jVar3 = sun.security.pkcs.c.f20485k;
        new c("EMAIL", jVar3, false, false);
        new c("EMAILADDRESS", jVar3, false, false);
        new c("UID", f1.S, false, true);
        new c("SERIALNUMBER", f1.T, false, false);
    }

    private c(String str, m.a.b.j jVar, boolean z, boolean z2) {
        this.a = str;
        this.b = z;
        this.f20308c = z2;
        d.put(jVar, this);
        f20307e.put(str, this);
    }

    public static String a(m.a.b.j jVar, int i2, Map<String, String> map) {
        String jVar2 = jVar.toString();
        String str = map.get(jVar2);
        if (str == null) {
            c cVar = d.get(jVar);
            if (cVar == null || !cVar.c(i2)) {
                if (i2 == 3) {
                    return jVar2;
                }
                return "OID." + jVar2;
            }
            return cVar.a;
        } else if (str.length() != 0) {
            String trim = str.trim();
            char charAt = trim.charAt(0);
            if (charAt >= 'A' && charAt <= 'z' && (charAt <= 'Z' || charAt >= 'a')) {
                for (int i3 = 1; i3 < trim.length(); i3++) {
                    char charAt2 = trim.charAt(i3);
                    if ((charAt2 < 'A' || charAt2 > 'z' || (charAt2 > 'Z' && charAt2 < 'a')) && ((charAt2 < '0' || charAt2 > '9') && charAt2 != '_')) {
                        throw new IllegalArgumentException("keyword character is not a letter, digit, or underscore");
                    }
                }
                return trim;
            }
            throw new IllegalArgumentException("keyword does not start with letter");
        } else {
            throw new IllegalArgumentException("keyword cannot be empty");
        }
    }

    public static boolean b(m.a.b.j jVar, int i2) {
        c cVar = d.get(jVar);
        if (cVar == null) {
            return false;
        }
        return cVar.c(i2);
    }

    private boolean c(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return this.f20308c;
                }
                throw new IllegalArgumentException("Invalid standard " + i2);
            }
            return this.b;
        }
        return true;
    }
}
