package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ExceptionCode;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public final class b {
    private static HashMap a = new c();

    public static int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new DecimalFormat("0000000").format(new SecureRandom().nextInt(ExceptionCode.CRASH_EXCEPTION)));
        stringBuffer.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())));
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x008c, code lost:
        if (r0.equals(r9) != false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r9) {
        /*
            java.lang.String r0 = "sePayConf"
            java.lang.String r1 = "configs"
            java.lang.String r2 = com.unionpay.utils.UPUtils.a(r9, r1)
            java.lang.String r3 = "mode"
            java.lang.String r3 = com.unionpay.utils.UPUtils.a(r9, r3)
            java.lang.String r4 = "or"
            java.lang.String r9 = com.unionpay.utils.UPUtils.a(r9, r4)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            r5 = 0
            r6 = 2
            java.lang.String r7 = ""
            if (r4 != 0) goto L8f
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L8f
            boolean r4 = android.text.TextUtils.isEmpty(r9)
            if (r4 != 0) goto L8f
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L8f
            r4.<init>(r2)     // Catch: java.lang.Exception -> L8f
            java.lang.String r2 = "sign"
            java.lang.String r2 = com.unionpay.utils.i.a(r4, r2)     // Catch: java.lang.Exception -> L8f
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Exception -> L3a
            goto L3b
        L3a:
            r3 = 0
        L3b:
            java.lang.String r8 = new java.lang.String     // Catch: java.lang.Exception -> L8f
            java.lang.String r1 = r4.getString(r1)     // Catch: java.lang.Exception -> L8f
            byte[] r1 = android.util.Base64.decode(r1, r6)     // Catch: java.lang.Exception -> L8f
            r8.<init>(r1)     // Catch: java.lang.Exception -> L8f
            boolean r1 = r4.has(r0)     // Catch: java.lang.Exception -> L8f
            if (r1 == 0) goto L5c
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Exception -> L8f
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Exception -> L8f
            byte[] r0 = android.util.Base64.decode(r0, r6)     // Catch: java.lang.Exception -> L8f
            r1.<init>(r0)     // Catch: java.lang.Exception -> L8f
            goto L5d
        L5c:
            r1 = r7
        L5d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L8f
            if (r0 == 0) goto L64
            r1 = r7
        L64:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8f
            r0.<init>()     // Catch: java.lang.Exception -> L8f
            r0.append(r8)     // Catch: java.lang.Exception -> L8f
            r0.append(r1)     // Catch: java.lang.Exception -> L8f
            r0.append(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = r0.toString()     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = com.unionpay.utils.UPUtils.a(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = b(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r0 = com.unionpay.utils.UPUtils.forConfig(r3, r2)     // Catch: java.lang.Exception -> L8f
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L8f
            if (r1 != 0) goto L8f
            boolean r9 = r0.equals(r9)     // Catch: java.lang.Exception -> L8f
            if (r9 == 0) goto L8f
            goto L90
        L8f:
            r8 = r7
        L90:
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch: java.lang.Exception -> Lc4
            r9.<init>(r8)     // Catch: java.lang.Exception -> Lc4
            int r0 = r9.length()
        L99:
            if (r5 >= r0) goto Lc4
            java.lang.Object r1 = com.unionpay.utils.i.a(r9, r5)
            if (r1 == 0) goto Lc1
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r2 = "type"
            java.lang.String r2 = com.unionpay.utils.i.a(r1, r2)
            java.lang.String r3 = "app"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto Lc1
            java.lang.String r9 = "ca"
            java.lang.String r9 = com.unionpay.utils.i.a(r1, r9)
            java.lang.String r0 = new java.lang.String
            byte[] r9 = android.util.Base64.decode(r9, r6)
            r0.<init>(r9)
            return r0
        Lc1:
            int r5 = r5 + 1
            goto L99
        Lc4:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.a(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:15|(2:16|17)|(6:35|36|20|21|(2:23|24)|(2:27|28))|19|20|21|(0)|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0060, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0061, code lost:
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0065, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0066, code lost:
        r3.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0052 A[Catch: CertificateEncodingException -> 0x0060, NoSuchAlgorithmException -> 0x0065, Exception -> 0x0072, TRY_LEAVE, TryCatch #6 {NoSuchAlgorithmException -> 0x0065, CertificateEncodingException -> 0x0060, blocks: (B:33:0x004c, B:35:0x0052), top: B:54:0x004c, outer: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006b A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #5 {Exception -> 0x0072, blocks: (B:4:0x0005, B:9:0x000f, B:15:0x001b, B:17:0x001f, B:19:0x0022, B:21:0x0027, B:22:0x0032, B:28:0x0040, B:33:0x004c, B:35:0x0052, B:42:0x006b, B:38:0x0061, B:40:0x0066, B:31:0x0048, B:25:0x003a, B:12:0x0015), top: B:52:0x0005, inners: #0, #1, #3, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            if (r3 == 0) goto La
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.Exception -> L72
            goto Lb
        La:
            r3 = r1
        Lb:
            if (r3 == 0) goto L18
            r2 = 64
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L14 java.lang.Exception -> L72
            goto L19
        L14:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L18:
            r3 = r1
        L19:
            if (r3 == 0) goto L72
            android.content.pm.Signature[] r3 = r3.signatures     // Catch: java.lang.Exception -> L72
            if (r3 == 0) goto L72
            int r4 = r3.length     // Catch: java.lang.Exception -> L72
            if (r4 <= 0) goto L72
            r4 = 0
            r2 = r3[r4]     // Catch: java.lang.Exception -> L72
            if (r2 == 0) goto L72
            r3 = r3[r4]     // Catch: java.lang.Exception -> L72
            byte[] r3 = r3.toByteArray()     // Catch: java.lang.Exception -> L72
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch: java.lang.Exception -> L72
            r4.<init>(r3)     // Catch: java.lang.Exception -> L72
            java.lang.String r3 = "X509"
            java.security.cert.CertificateFactory r3 = java.security.cert.CertificateFactory.getInstance(r3)     // Catch: java.security.cert.CertificateException -> L39 java.lang.Exception -> L72
            goto L3e
        L39:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
            r3 = r1
        L3e:
            if (r3 == 0) goto L4b
            java.security.cert.Certificate r3 = r3.generateCertificate(r4)     // Catch: java.security.cert.CertificateException -> L47 java.lang.Exception -> L72
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3     // Catch: java.security.cert.CertificateException -> L47 java.lang.Exception -> L72
            goto L4c
        L47:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L4b:
            r3 = r1
        L4c:
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r5)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            if (r3 == 0) goto L69
            byte[] r3 = r3.getEncoded()     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            byte[] r3 = r4.digest(r3)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            java.lang.String r3 = a(r3)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            r1 = r3
            goto L69
        L60:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
            goto L69
        L65:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L69:
            if (r1 == 0) goto L72
            java.lang.String r3 = ":"
            java.lang.String r3 = r1.replaceAll(r3, r0)     // Catch: java.lang.Exception -> L72
            return r3
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String a(InputStream inputStream, String str) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString(str);
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            String hexString = Integer.toHexString(bArr[i2]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i2 < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static boolean a(Context context, String str) {
        PackageInfo packageInfo = null;
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && !TextUtils.isEmpty(str)) {
                    packageInfo = packageManager.getPackageInfo(str, 0);
                }
            } catch (Exception unused) {
            }
        }
        return packageInfo != null;
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    String c2 = c(context, str);
                    if (!TextUtils.isEmpty(c2) && a(context, str) && c2.matches(str3)) {
                        if (str2.equalsIgnoreCase(a(context, str, "SHA1"))) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String b(Context context, String str) {
        return a(context, str, "SHA1");
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b : str.getBytes()) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString().trim();
    }

    public static boolean b() {
        try {
            return "HUAWEI".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    int e2 = e(context, str);
                    int f2 = f(str3);
                    if (a(context, str) && e2 >= f2) {
                        if (str2.equalsIgnoreCase(a(context, str, "SHA256"))) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(String str) {
        return !TextUtils.isEmpty((CharSequence) a.get(str)) ? (String) a.get(str) : str;
    }

    public static boolean d(Context context, String str) {
        return a(context, str);
    }

    public static final boolean d(String str) {
        return !Pattern.compile("[^0-9]+").matcher(str).find();
    }

    private static int e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String e(String str) {
        if (str != null) {
            try {
                return Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim();
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    private static int f(String str) {
        try {
            return Integer.valueOf(str, 10).intValue();
        } catch (Exception unused) {
            return Integer.MAX_VALUE;
        }
    }
}
