package i.a;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
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

/* loaded from: classes11.dex */
public final class d implements HostnameVerifier {
    public static final d a = new d();
    private static final Pattern b = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private d() {
    }

    private static List<String> a(X509Certificate x509Certificate, int i2) {
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

    private boolean d(String str, X509Certificate x509Certificate) {
        String lowerCase = str.toLowerCase(Locale.US);
        Iterator<String> it = a(x509Certificate, 2).iterator();
        while (it.hasNext()) {
            if (b(lowerCase, it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean e(String str, X509Certificate x509Certificate) {
        List<String> a2 = a(x509Certificate, 7);
        int size = a2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equalsIgnoreCase(a2.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean b(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(OrderISVUtil.MONEY_DECIMAL) || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(OrderISVUtil.MONEY_DECIMAL) || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(OrderISVUtil.MONEY_DECIMAL)) {
            str = str + OrderISVUtil.MONEY_DECIMAL_CHAR;
        }
        if (!str2.endsWith(OrderISVUtil.MONEY_DECIMAL)) {
            str2 = str2 + OrderISVUtil.MONEY_DECIMAL_CHAR;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (lowerCase.contains(ProxyConfig.MATCH_ALL_SCHEMES)) {
            if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
                return false;
            }
            String substring = lowerCase.substring(1);
            if (str.endsWith(substring)) {
                int length = str.length() - substring.length();
                return length <= 0 || str.lastIndexOf(46, length - 1) == -1;
            }
            return false;
        }
        return str.equals(lowerCase);
    }

    public boolean c(String str, X509Certificate x509Certificate) {
        return b.matcher(str).matches() ? e(str, x509Certificate) : d(str, x509Certificate);
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return c(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }
}
