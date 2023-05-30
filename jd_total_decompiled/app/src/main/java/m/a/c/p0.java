package m.a.c;

import com.jingdong.sdk.platform.business.personal.R2;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class p0 {
    private static final Map<m.a.b.j, a> a = new HashMap();
    private static final Map<String, a> b = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class a {
        final String a;
        final String b;

        /* renamed from: c  reason: collision with root package name */
        private volatile Class f20395c;

        a(String str, m.a.b.j jVar, String str2) {
            this.a = str;
            this.b = str2;
        }

        Class a() throws CertificateException {
            try {
                Class cls = this.f20395c;
                if (cls == null) {
                    Class<?> cls2 = Class.forName(this.b);
                    this.f20395c = cls2;
                    return cls2;
                }
                return cls;
            } catch (ClassNotFoundException e2) {
                throw ((CertificateException) new CertificateException("Could not load class: " + e2).initCause(e2));
            }
        }
    }

    static {
        a("x509.info.extensions.SubjectKeyIdentifier", s0.A, "sun.security.x509.SubjectKeyIdentifierExtension");
        a("x509.info.extensions.KeyUsage", s0.B, "sun.security.x509.KeyUsageExtension");
        a("x509.info.extensions.PrivateKeyUsage", s0.C, "sun.security.x509.PrivateKeyUsageExtension");
        a("x509.info.extensions.SubjectAlternativeName", s0.F, "sun.security.x509.SubjectAlternativeNameExtension");
        a("x509.info.extensions.IssuerAlternativeName", s0.G, "sun.security.x509.IssuerAlternativeNameExtension");
        a("x509.info.extensions.BasicConstraints", s0.H, "sun.security.x509.BasicConstraintsExtension");
        a("x509.info.extensions.CRLNumber", s0.L, "sun.security.x509.CRLNumberExtension");
        a("x509.info.extensions.CRLReasonCode", s0.O, "sun.security.x509.CRLReasonCodeExtension");
        a("x509.info.extensions.NameConstraints", s0.I, "sun.security.x509.NameConstraintsExtension");
        a("x509.info.extensions.PolicyMappings", s0.E, "sun.security.x509.PolicyMappingsExtension");
        a("x509.info.extensions.AuthorityKeyIdentifier", s0.z, "sun.security.x509.AuthorityKeyIdentifierExtension");
        a("x509.info.extensions.PolicyConstraints", s0.J, "sun.security.x509.PolicyConstraintsExtension");
        a("x509.info.extensions.NetscapeCertType", m.a.b.j.newInternal(new int[]{2, 16, R2.attr.exTabIndicatorHeight, 1, 113730, 1, 1}), "sun.security.x509.NetscapeCertTypeExtension");
        a("x509.info.extensions.CertificatePolicies", s0.D, "sun.security.x509.CertificatePoliciesExtension");
        a("x509.info.extensions.ExtendedKeyUsage", s0.P, "sun.security.x509.ExtendedKeyUsageExtension");
        a("x509.info.extensions.InhibitAnyPolicy", s0.Q, "sun.security.x509.InhibitAnyPolicyExtension");
        a("x509.info.extensions.CRLDistributionPoints", s0.K, "sun.security.x509.CRLDistributionPointsExtension");
        a("x509.info.extensions.CertificateIssuer", s0.R, "sun.security.x509.CertificateIssuerExtension");
        a("x509.info.extensions.AuthorityInfoAccess", s0.S, "sun.security.x509.AuthorityInfoAccessExtension");
        a("x509.info.extensions.IssuingDistributionPoint", s0.M, "sun.security.x509.IssuingDistributionPointExtension");
        a("x509.info.extensions.DeltaCRLIndicator", s0.N, "sun.security.x509.DeltaCRLIndicatorExtension");
        a("x509.info.extensions.FreshestCRL", s0.T, "sun.security.x509.FreshestCRLExtension");
    }

    private static void a(String str, m.a.b.j jVar, String str2) {
        a aVar = new a(str, jVar, str2);
        a.put(jVar, aVar);
        b.put(str, aVar);
    }

    public static Class b(m.a.b.j jVar) throws CertificateException {
        a aVar = a.get(jVar);
        if (aVar == null) {
            return null;
        }
        return aVar.a();
    }

    public static String c(m.a.b.j jVar) {
        a aVar = a.get(jVar);
        if (aVar == null) {
            return null;
        }
        return aVar.a;
    }
}
