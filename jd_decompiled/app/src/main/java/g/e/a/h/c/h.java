package g.e.a.h.c;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;

/* loaded from: classes12.dex */
class h implements i {
    private SecretKey a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(String str, String str2, String str3, String str4) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalArgumentException {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            return;
        }
        this.a = k.a(a.b(str), a.b(str2), a.b(str3), a.b(str4), 5000);
    }

    @Override // g.e.a.h.c.i
    public String a(String str, String str2) {
        if (this.a == null) {
            return str;
        }
        try {
            return new String(k.b(this.a, a.b(str)), "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException unused) {
            return str2;
        }
    }
}
