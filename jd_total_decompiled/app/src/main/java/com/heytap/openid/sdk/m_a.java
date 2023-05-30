package com.heytap.openid.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import jd.wjlogin_sdk.util.e;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public class m_a {
    public static Pair<String, String> m_a(String str, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
            SecretKey m_c = m_c(str);
            if (m_c == null) {
                return null;
            }
            cipher.init(1, m_c);
            return new Pair<>(Base64.encodeToString(cipher.doFinal(bArr), 2), Base64.encodeToString(cipher.getIV(), 2));
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("1018");
            sb.append(e2.getMessage() != null ? e2.getMessage() : e2.getLocalizedMessage());
            sb.toString();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0023 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0024 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m_a(Context context, String str) {
        Signature[] signatureArr;
        PackageInfo packageInfo;
        ArrayList arrayList = new ArrayList();
        if (str != null && context != null && str.length() != 0) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (packageInfo != null) {
                signatureArr = packageInfo.signatures;
                if (signatureArr != null) {
                    return "";
                }
                try {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    for (Signature signature : signatureArr) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(signature.toByteArray());
                        byte[] encoded = ((X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream)).getEncoded();
                        StringBuffer stringBuffer = new StringBuffer(encoded.length);
                        for (byte b : encoded) {
                            String hexString = Integer.toHexString(((char) b) & '\u00ff');
                            if (hexString.length() < 2) {
                                stringBuffer.append(0);
                            }
                            stringBuffer.append(hexString.toUpperCase());
                        }
                        String stringBuffer2 = stringBuffer.toString();
                        try {
                            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                            messageDigest.update(stringBuffer2.getBytes());
                            String format = String.format("%032x", new BigInteger(1, messageDigest.digest()));
                            if (!arrayList.contains(format)) {
                                arrayList.add(format);
                            }
                            byteArrayInputStream.close();
                        } catch (NoSuchAlgorithmException e2) {
                            throw new RuntimeException(e2.getMessage());
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return "";
                    }
                    Collections.sort(arrayList);
                    String[] strArr = (String[]) arrayList.toArray(new String[0]);
                    StringBuilder sb = new StringBuilder();
                    for (String str2 : strArr) {
                        sb.append(str2);
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    return TextUtils.isEmpty(substring) ? "" : substring.length() > 32 ? substring.substring(0, 32) : substring;
                } catch (IOException | CertificateException unused2) {
                    return "";
                }
            }
        }
        signatureArr = null;
        if (signatureArr != null) {
        }
    }

    public static String m_a(Context context, String str, String str2) {
        Signature[] signatureArr;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (PackageManager.NameNotFoundException e2) {
            if (("1011 " + e2.getMessage()) != null) {
                e2.getMessage();
            } else {
                e2.getLocalizedMessage();
            }
            signatureArr = null;
        }
        if (signatureArr != null) {
            for (Signature signature : signatureArr) {
                if ("SHA1".equals(str2)) {
                    byte[] byteArray = signature.toByteArray();
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                        if (messageDigest != null) {
                            byte[] digest = messageDigest.digest(byteArray);
                            StringBuilder sb = new StringBuilder();
                            for (byte b : digest) {
                                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                            }
                            return sb.toString();
                        }
                        return null;
                    } catch (NoSuchAlgorithmException e3) {
                        if (("1012 " + e3.getMessage()) != null) {
                            e3.getMessage();
                            return null;
                        }
                        e3.getLocalizedMessage();
                        return null;
                    }
                }
            }
            return null;
        }
        return null;
    }

    public static String m_a(String str) {
        try {
            return new String(Base64.decode(str, 0));
        } catch (Exception e2) {
            new StringBuilder("4025: ").append(e2.toString());
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r13.equals("APID") != false) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b0 A[Catch: IllegalStateException -> 0x00d9, TryCatch #0 {IllegalStateException -> 0x00d9, blocks: (B:10:0x0017, B:19:0x0046, B:38:0x0071, B:40:0x0078, B:41:0x007a, B:60:0x00d5, B:42:0x007e, B:44:0x0085, B:45:0x0088, B:47:0x0098, B:49:0x00a1, B:51:0x00a8, B:52:0x00ac, B:53:0x00b0, B:55:0x00c0, B:57:0x00c9, B:59:0x00d0, B:22:0x004e, B:25:0x0056, B:28:0x005e), top: B:68:0x0017 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m_a(Context context, m_e m_eVar, String str) {
        String str2;
        String str3;
        String str4;
        long j2;
        if (m_eVar == null || str.equals("OUID") || str.equals("OUID_STATUS") || str == "OUID" || str == "OUID_STATUS") {
            return;
        }
        try {
            char c2 = 0;
            SharedPreferences.Editor edit = context.getSharedPreferences("cache", 0).edit();
            int hashCode = str.hashCode();
            if (hashCode != 2015626) {
                if (hashCode == 2020431) {
                    if (str.equals("AUID")) {
                        c2 = 2;
                        if (c2 != 0) {
                        }
                    }
                    c2 = '\uffff';
                    if (c2 != 0) {
                    }
                } else if (hashCode != 2109804) {
                    if (hashCode == 2199177 && str.equals(e.d)) {
                        c2 = 1;
                        if (c2 != 0) {
                            Pair<String, String> m_a = m_a(m_a("U3RkSWRBcHBLZXk="), m_eVar.m_a.getBytes());
                            if (m_a != null) {
                                edit.putString("APID", (String) m_a.first);
                                edit.putLong("APID_TIME", m_eVar.m_b);
                                str2 = "APID_IV";
                                str3 = (String) m_a.second;
                                edit.putString(str2, str3);
                            }
                            edit.apply();
                            return;
                        }
                        if (c2 != 1) {
                            if (c2 == 2) {
                                edit.putString("AUID", m_eVar.m_a);
                                str4 = "AUID_TIME";
                                j2 = m_eVar.m_b;
                            } else if (c2 == 3) {
                                edit.putString("DUID", m_eVar.m_a);
                                str4 = "DUID_TIME";
                                j2 = m_eVar.m_b;
                            }
                            edit.putLong(str4, j2);
                        } else {
                            Pair<String, String> m_a2 = m_a(m_a("U3RkSWRBcHBLZXk="), m_eVar.m_a.getBytes());
                            if (m_a2 != null) {
                                edit.putString(e.d, (String) m_a2.first);
                                edit.putLong("GUID_TIME", m_eVar.m_b);
                                str2 = "GUID_IV";
                                str3 = (String) m_a2.second;
                                edit.putString(str2, str3);
                            }
                        }
                        edit.apply();
                        return;
                    }
                    c2 = '\uffff';
                    if (c2 != 0) {
                    }
                } else {
                    if (str.equals("DUID")) {
                        c2 = 3;
                        if (c2 != 0) {
                        }
                    }
                    c2 = '\uffff';
                    if (c2 != 0) {
                    }
                }
            }
        } catch (IllegalStateException e2) {
            if (("1019:" + e2.getMessage()) != null) {
                e2.getMessage();
            } else {
                e2.getLocalizedMessage();
            }
        }
    }

    public static byte[] m_a(String str, String str2, String str3) {
        try {
            byte[] decode = Base64.decode(str2, 2);
            byte[] decode2 = Base64.decode(str3, 2);
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
            GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, decode2);
            SecretKey m_c = m_c(str);
            if (m_c == null) {
                return null;
            }
            cipher.init(2, m_c, gCMParameterSpec);
            return cipher.doFinal(decode);
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("1015:");
            sb.append(e2.getMessage() != null ? e2.getMessage() : e2.getLocalizedMessage());
            sb.toString();
            return null;
        }
    }

    public static SecretKey m_b(String str) {
        try {
            "generateSecretKey, alias:".concat(String.valueOf(str));
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES, "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
            return keyGenerator.generateKey();
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("1017:");
            sb.append(e2.getMessage() != null ? e2.getMessage() : e2.getLocalizedMessage());
            sb.toString();
            return null;
        }
    }

    public static SecretKey m_c(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry(str, null);
            SecretKey secretKey = entry != null ? ((KeyStore.SecretKeyEntry) entry).getSecretKey() : null;
            return secretKey == null ? m_b(str) : secretKey;
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("1016:");
            sb.append(e2.getMessage() != null ? e2.getMessage() : e2.getLocalizedMessage());
            sb.toString();
            return null;
        }
    }

    public static long m_d(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 2015626:
                if (str.equals("APID")) {
                    c2 = 0;
                    break;
                }
                break;
            case 2020431:
                if (str.equals("AUID")) {
                    c2 = 1;
                    break;
                }
                break;
            case 2109804:
                if (str.equals("DUID")) {
                    c2 = 2;
                    break;
                }
                break;
            case 2199177:
                if (str.equals(e.d)) {
                    c2 = 3;
                    break;
                }
                break;
            case 2437505:
                if (str.equals("OUID")) {
                    c2 = 4;
                    break;
                }
                break;
            case 572132464:
                if (str.equals("OUID_STATUS")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
                return 259200000L;
            case 1:
                return Final.SEV_DAY;
            case 2:
                return 86400000L;
            case 4:
            case 5:
                return JDReminderNewUtils.REMINDER_DURATION_TIME_MAX;
            default:
                return 0L;
        }
    }
}
