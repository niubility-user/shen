package com.jdjr.securehttp;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jdjr.tools.CommonTools;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
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
    */
    private static X509Certificate getCertByContent(String str) {
        ByteArrayInputStream byteArrayInputStream;
        CertificateFactory certificateFactory;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                certificateFactory = CertificateFactory.getInstance("X.509");
                byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 2));
            } catch (CertificateException e2) {
                e = e2;
                byteArrayInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (byteArrayInputStream2 != null) {
                }
                throw th;
            }
            try {
                X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
                try {
                    byteArrayInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return x509Certificate;
            } catch (CertificateException e4) {
                e = e4;
                e.printStackTrace();
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                        return null;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
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
