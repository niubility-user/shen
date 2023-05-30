package com.cmic.sso.sdk.e;

import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class i {
    private static final String a = "i";
    private static i d;
    private PublicKey b = null;

    /* renamed from: c  reason: collision with root package name */
    private PublicKey f1038c = null;

    private i() {
        try {
            b();
            if (this.f1038c == null) {
                c();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static i a() {
        if (d == null) {
            d = new i();
        }
        return d;
    }

    private void c() throws Exception {
        try {
            this.f1038c = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuyqBGJVxu+5Z2ZwItIhl\noxI53CVpYUR3OWAQyAQNcMhDDf3nGsxLLHP8kGWqrpLn1uAIgI+EIAl0sM+i1leD\nFD+sYU2rkUVZgpwO7ly+THBFw/YcZNwS094NBdhzxmCCFbCKHVNzDLirlV9T2q4k\nJhjaEmyCOtSU6+mdjcHhbcbF6lKYx8tfQlpPmyM5suFY138qtEoB4b+q/j8q22MI\naUotg1Av257RuMh97hAwoi5D7HS5LH0piLIN/au/X08rxbXnWNdgQtFtUeCNy3vw\nkO0ykg5qH942X8poQ+a9GgBUeDBpY4GSIv6/qq+zJxiJxpoL0SGKAP3FlcuLr07f\nxwIDAQAB", 0)));
        } catch (NullPointerException unused) {
            throw new Exception("\u516c\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }

    public String b(byte[] bArr) {
        if (this.b == null) {
            c.a(a, "mServerPublicKey == null");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
            cipher.init(1, this.f1038c);
            return Base64.encodeToString(cipher.doFinal(bArr), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String a(byte[] bArr) {
        if (this.b == null) {
            c.a(a, "mServerPublicKey == null");
            return "";
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
            cipher.init(1, this.b);
            return q.a(cipher.doFinal(bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private void b() throws Exception {
        try {
            this.b = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNFGdEpQ1d8cPqekvvEDQyBGnI\nKwvjX9o3OmnnqWMGbIiFYIpc21QeG7aqizuWdXlgS5M9rstDfHQfG/AaPElJ7Yix\nBCau4hdVwFpRmb9NIuqavDeHKP9BKPZ01Ra5/666NGKBqmkRRer3lBCe6EKNUc2U\n/DZg6U/Q3CTPiORt/wIDAQAB", 0)));
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }
}
