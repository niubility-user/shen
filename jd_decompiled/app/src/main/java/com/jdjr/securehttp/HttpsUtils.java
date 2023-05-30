package com.jdjr.securehttp;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jdjr.tools.CommonTools;
import com.tencent.smtt.sdk.ProxyConfig;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes18.dex */
public class HttpsUtils {
    private static final String TAG = "CertsUtils";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkHttpsCert(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr != null) {
            boolean z = true;
            if (x509CertificateArr.length >= 1) {
                if (!TextUtils.isEmpty(str)) {
                    X509Certificate x509Certificate = x509CertificateArr[x509CertificateArr.length - 1];
                    String[] split = CommonTools.getRootCerts().split(DYConstants.DY_REGEX_VERTICAL_LINE);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= split.length) {
                            break;
                        } else if (x509Certificate.equals(getCertByContent(split[i2]))) {
                            z = false;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= split.length) {
                                break;
                            }
                            try {
                                x509Certificate.verify(getCertByContent(split[i3]).getPublicKey());
                                z = false;
                                break;
                            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException unused) {
                                i3++;
                            }
                        }
                        if (z) {
                            throw new CertificateException("root certificate is not correct");
                        }
                    }
                    try {
                        x509CertificateArr[0].checkValidity();
                        if (!isHostMatch(x509CertificateArr[0], str)) {
                            throw new CertificateException("hostname is not correct");
                        }
                        return;
                    } catch (CertificateException unused2) {
                        throw new CertificateException("validity is error");
                    }
                }
                throw new CertificateException("hostname is empty");
            }
            throw new CertificateException("X509Certificate is empty");
        }
        throw new CertificateException("X509Certificate array is null");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.security.cert.X509Certificate getCertByContent(java.lang.String r3) {
        /*
            r0 = 0
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            r2 = 2
            byte[] r3 = android.util.Base64.decode(r3, r2)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            java.security.cert.Certificate r3 = r1.generateCertificate(r2)     // Catch: java.security.cert.CertificateException -> L21 java.lang.Throwable -> L35
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3     // Catch: java.security.cert.CertificateException -> L21 java.lang.Throwable -> L35
            r2.close()     // Catch: java.io.IOException -> L1b
            goto L1f
        L1b:
            r0 = move-exception
            r0.printStackTrace()
        L1f:
            r0 = r3
            goto L34
        L21:
            r3 = move-exception
            goto L27
        L23:
            r3 = move-exception
            goto L37
        L25:
            r3 = move-exception
            r2 = r0
        L27:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L34
            r2.close()     // Catch: java.io.IOException -> L30
            goto L34
        L30:
            r3 = move-exception
            r3.printStackTrace()
        L34:
            return r0
        L35:
            r3 = move-exception
            r0 = r2
        L37:
            if (r0 == 0) goto L41
            r0.close()     // Catch: java.io.IOException -> L3d
            goto L41
        L3d:
            r0 = move-exception
            r0.printStackTrace()
        L41:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.securehttp.HttpsUtils.getCertByContent(java.lang.String):java.security.cert.X509Certificate");
    }

    private static List<String> getChainSubjectNames(X509Certificate x509Certificate) {
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && ((Integer) list.get(0)) != null && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHostMatch(X509Certificate x509Certificate, String str) {
        List<String> chainSubjectNames = getChainSubjectNames(x509Certificate);
        for (int i2 = 0; i2 < chainSubjectNames.size(); i2++) {
            String str2 = chainSubjectNames.get(i2);
            if (str2.contains(ProxyConfig.MATCH_ALL_SCHEMES)) {
                str2 = str2.substring(str2.lastIndexOf(ProxyConfig.MATCH_ALL_SCHEMES) + 1).trim();
            }
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }
}
