package com.huawei.hms.device;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.support.log.common.Base64;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import com.jd.dynamic.DYConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class a {
    public static boolean a(X509Certificate x509Certificate, List<X509Certificate> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        if (x509Certificate == null) {
            HMSLog.e("X509CertUtil", "rootCert is null,verify failed ");
            return false;
        }
        PublicKey publicKey = x509Certificate.getPublicKey();
        for (X509Certificate x509Certificate2 : list) {
            if (x509Certificate2 != null) {
                try {
                    x509Certificate2.checkValidity();
                    x509Certificate2.verify(publicKey);
                    publicKey = x509Certificate2.getPublicKey();
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException e2) {
                    HMSLog.e("X509CertUtil", "verify failed " + e2.getMessage());
                }
            }
            return false;
        }
        return a(list);
    }

    public static List<X509Certificate> b(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    private static List<String> c(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 1) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.getString(i2));
            }
            return arrayList;
        } catch (JSONException e2) {
            HMSLog.e("X509CertUtil", "Failed to getCertChain: " + e2.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<X509Certificate> b(String str) {
        return b(c(str));
    }

    public static boolean b(X509Certificate x509Certificate, String str, String str2) {
        if (x509Certificate == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(a(x509Certificate.getSubjectDN().getName(), str));
    }

    public static boolean b(X509Certificate x509Certificate, String str) {
        return b(x509Certificate, "OU", str);
    }

    public static X509Certificate a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return a(Base64.decode(str));
        } catch (IllegalArgumentException e2) {
            HMSLog.e("X509CertUtil", "getCert failed : " + e2.getMessage());
            return null;
        }
    }

    public static X509Certificate a(byte[] bArr) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e2) {
            HMSLog.e("X509CertUtil", "Failed to get cert: " + e2.getMessage());
            return null;
        }
    }

    private static String a(String str, String str2) {
        int indexOf = str.toUpperCase(Locale.getDefault()).indexOf(str2 + ContainerUtils.KEY_VALUE_DELIMITER);
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(DYConstants.DY_REGEX_COMMA, indexOf);
        if (indexOf2 != -1) {
            return str.substring(indexOf + str2.length() + 1, indexOf2);
        }
        return str.substring(indexOf + str2.length() + 1);
    }

    public static boolean a(X509Certificate x509Certificate) {
        if (x509Certificate == null || x509Certificate.getBasicConstraints() == -1) {
            return false;
        }
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (5 < keyUsage.length) {
            return keyUsage[5];
        }
        return false;
    }

    public static boolean a(List<X509Certificate> list) {
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            if (!a(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(X509Certificate x509Certificate, String str) {
        return b(x509Certificate, "CN", str);
    }

    public static boolean a(X509Certificate x509Certificate, String str, String str2) {
        try {
            return a(x509Certificate, str.getBytes("UTF-8"), Base64.decode(str2));
        } catch (UnsupportedEncodingException | IllegalArgumentException e2) {
            HMSLog.e("X509CertUtil", " plainText exception: " + e2.getMessage());
            return false;
        }
    }

    public static boolean a(X509Certificate x509Certificate, byte[] bArr, byte[] bArr2) {
        try {
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            signature.initVerify(x509Certificate.getPublicKey());
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
            HMSLog.e("X509CertUtil", "failed checkSignature : " + e2.getMessage());
            return false;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0091: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:91:0x0091 */
    public static X509Certificate a(Context context, String str) {
        InputStream inputStream;
        InputStream inputStream2;
        KeyStore keyStore;
        InputStream inputStream3 = null;
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        keyStore = KeyStore.getInstance("bks");
                        inputStream2 = context.getAssets().open("hmsrootcas.bks");
                    } catch (IOException e2) {
                        e = e2;
                        inputStream2 = null;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (KeyStoreException e3) {
                        e = e3;
                        inputStream2 = null;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (NoSuchAlgorithmException e4) {
                        e = e4;
                        inputStream2 = null;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (CertificateException e5) {
                        e = e5;
                        inputStream2 = null;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(inputStream3);
                        throw th;
                    }
                    try {
                        keyStore.load(inputStream2, "".toCharArray());
                    } catch (IOException e6) {
                        e = e6;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (KeyStoreException e7) {
                        e = e7;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (NoSuchAlgorithmException e8) {
                        e = e8;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    } catch (CertificateException e9) {
                        e = e9;
                        HMSLog.e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    }
                    if (!keyStore.containsAlias(str)) {
                        HMSLog.e("X509CertUtil", "Not include alias " + str);
                        HMSPackageManager.getInstance(context).setUseOldCertificate(true);
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    }
                    Certificate certificate = keyStore.getCertificate(str);
                    if (!(certificate instanceof X509Certificate)) {
                        IOUtils.closeQuietly(inputStream2);
                        return null;
                    }
                    X509Certificate x509Certificate = (X509Certificate) certificate;
                    x509Certificate.checkValidity();
                    IOUtils.closeQuietly(inputStream2);
                    return x509Certificate;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream3 = inputStream;
            }
        }
        HMSLog.e("X509CertUtil", "args are error");
        return null;
    }

    public static X509Certificate a(Context context) {
        return a(context, "052root");
    }
}
