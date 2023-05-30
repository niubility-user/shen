package com.jingdong.sdk.dialingtest.c.c;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.tencent.smtt.sdk.ProxyConfig;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: classes7.dex */
public class b implements HostnameVerifier {
    public static final b a = new b();
    private static final Pattern b = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private b() {
    }

    private List<String> a(X509Certificate x509Certificate, int i2) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i2 && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    static boolean b(String str) {
        return b.matcher(str).matches();
    }

    private boolean e(String str, X509Certificate x509Certificate) {
        String c2;
        String lowerCase = str.toLowerCase(Locale.US);
        Iterator<String> it = a(x509Certificate, 2).iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (c(lowerCase, it.next())) {
                return true;
            }
            z = true;
        }
        if (z || (c2 = new a(x509Certificate.getSubjectX500Principal()).c(AdvanceSetting.CLEAR_NOTIFICATION)) == null) {
            return false;
        }
        return c(lowerCase, c2);
    }

    private boolean f(String str, X509Certificate x509Certificate) {
        Iterator<String> it = a(x509Certificate, 7).iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean c(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains(ProxyConfig.MATCH_ALL_SCHEMES)) {
            return str.equals(lowerCase);
        }
        if (lowerCase.startsWith("*.") && str.equals(lowerCase.substring(2))) {
            return true;
        }
        int indexOf = lowerCase.indexOf(42);
        if (indexOf <= lowerCase.indexOf(46) && str.regionMatches(0, lowerCase, 0, indexOf)) {
            int i2 = indexOf + 1;
            int length = lowerCase.length() - i2;
            int length2 = str.length() - length;
            return (str.indexOf(46, indexOf) >= length2 || str.endsWith(".clients.google.com")) && str.regionMatches(length2, lowerCase, i2, length);
        }
        return false;
    }

    public boolean d(String str, X509Certificate x509Certificate) {
        if (b(str)) {
            return f(str, x509Certificate);
        }
        return e(str, x509Certificate);
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return d(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }
}
