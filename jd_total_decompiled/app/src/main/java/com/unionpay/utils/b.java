package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.framework.common.ExceptionCode;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

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
    */
    public static String a(Context context) {
        String str;
        int i2;
        String a2 = UPUtils.a(context, "configs");
        String a3 = UPUtils.a(context, "mode");
        String a4 = UPUtils.a(context, "or");
        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4)) {
            try {
                JSONObject jSONObject = new JSONObject(a2);
                String a5 = i.a(jSONObject, "sign");
                try {
                    i2 = Integer.parseInt(a3);
                } catch (Exception unused) {
                    i2 = 0;
                }
                str = new String(Base64.decode(jSONObject.getString("configs"), 2));
                String str2 = jSONObject.has("sePayConf") ? new String(Base64.decode(jSONObject.getString("sePayConf"), 2)) : "";
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                }
                String b = b(UPUtils.a(str + str2 + a4));
                String forConfig = UPUtils.forConfig(i2, a5);
                if (!TextUtils.isEmpty(forConfig)) {
                }
            } catch (Exception unused2) {
            }
        }
        str = "";
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i3 = 0; i3 < length; i3++) {
                Object a6 = i.a(jSONArray, i3);
                if (a6 != null) {
                    JSONObject jSONObject2 = (JSONObject) a6;
                    if ("app".equals(i.a(jSONObject2, "type"))) {
                        return new String(Base64.decode(i.a(jSONObject2, "ca"), 2));
                    }
                }
            }
        } catch (Exception unused3) {
        }
        return "";
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
    */
    private static String a(Context context, String str, String str2) {
        PackageManager packageManager;
        PackageInfo packageInfo;
        Signature[] signatureArr;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        String str3 = null;
        if (context != null) {
            try {
                packageManager = context.getPackageManager();
            } catch (Exception unused) {
            }
        } else {
            packageManager = null;
        }
        if (packageManager != null) {
            try {
                packageInfo = packageManager.getPackageInfo(str, 64);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0 && signatureArr[0] != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(signatureArr[0].toByteArray());
                try {
                    certificateFactory = CertificateFactory.getInstance("X509");
                } catch (CertificateException e3) {
                    e3.printStackTrace();
                    certificateFactory = null;
                }
                if (certificateFactory != null) {
                    try {
                        x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
                    } catch (CertificateException e4) {
                        e4.printStackTrace();
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance(str2);
                    if (x509Certificate != null) {
                        str3 = a(messageDigest.digest(x509Certificate.getEncoded()));
                    }
                    if (str3 != null) {
                        return str3.replaceAll(":", "");
                    }
                }
                x509Certificate = null;
                MessageDigest messageDigest2 = MessageDigest.getInstance(str2);
                if (x509Certificate != null) {
                }
                if (str3 != null) {
                }
            }
            return "";
        }
        packageInfo = null;
        if (packageInfo != null) {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(signatureArr[0].toByteArray());
            certificateFactory = CertificateFactory.getInstance("X509");
            if (certificateFactory != null) {
            }
            x509Certificate = null;
            MessageDigest messageDigest22 = MessageDigest.getInstance(str2);
            if (x509Certificate != null) {
            }
            if (str3 != null) {
            }
        }
        return "";
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
