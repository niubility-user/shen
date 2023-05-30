package g.e.a.h.c;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

/* loaded from: classes12.dex */
public class g implements i {
    private final f a;
    private SecretKey b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar) {
        this.a = fVar;
        b();
    }

    private SecretKey b() {
        try {
            String a = this.a.a("/code/code1", null);
            String a2 = this.a.a("/code/code2", null);
            String a3 = this.a.a("/code/code3", null);
            String a4 = this.a.a("/code/code4", null);
            if (a != null && a2 != null && a3 != null && a4 != null) {
                this.b = k.a(a.b(a), a.b(a2), a.b(a3), a.b(a4), 10000);
            }
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException unused) {
            this.b = null;
        }
        return this.b;
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^\\[!([A-Fa-f0-9]*)]", str);
    }

    static String d(String str) {
        try {
            Matcher matcher = Pattern.compile("^\\[!([A-Fa-f0-9]*)]").matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IllegalStateException | IndexOutOfBoundsException unused) {
        }
        return "";
    }

    @Override // g.e.a.h.c.i
    public String a(String str, String str2) {
        if (this.b != null && c(str)) {
            try {
                return new String(k.b(this.b, a.b(d(str))), "UTF-8");
            } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException unused) {
            }
        }
        return str2;
    }
}
