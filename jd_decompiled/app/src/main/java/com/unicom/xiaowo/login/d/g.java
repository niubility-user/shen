package com.unicom.xiaowo.login.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import javax.crypto.Cipher;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes11.dex */
public class g {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i2 = 0; i2 < digest.length; i2++) {
                int i3 = digest[i2];
                if (i3 < 0) {
                    i3 += 256;
                }
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        int indexOf = str.indexOf("://");
        if (indexOf > 0) {
            str = str.substring(indexOf + 3);
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 >= 0) {
            str = str.substring(0, indexOf2);
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 >= 0) {
            str = str.substring(0, indexOf3);
        }
        int indexOf4 = str.indexOf(63);
        return indexOf4 >= 0 ? str.substring(0, indexOf4) : str;
    }

    public static int c(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    public static String d(String str) {
        try {
            return a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbRkBR4leALApkWRp2ng8zJ2WgI7YEqtMwW9Q1tmRzDLPNhH0ugACfbiStBG4ybdYNHzRlxvOwQ7R0MeN56qEPsv6qieg/HiRXBnQ2hQ2hypo9JHqHx8BX54ESZ+BIf0imjGTcxtHvbzYA04ckmH5Enl2Pkd+R/RZuMK589C7KwQIDAQAB");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static PublicKey e(String str) {
        return KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(com.unicom.xiaowo.login.a.b.b(str)));
    }

    public static String c(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!nextElement.getName().toLowerCase().contains("wlan") && !nextElement.getName().toLowerCase().contains("tun")) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && !nextElement2.isLinkLocalAddress()) {
                            if (nextElement2 instanceof Inet4Address) {
                                sb.append(nextElement2.getHostAddress());
                                sb.append(DYConstants.DY_REGEX_COMMA);
                            }
                            if (nextElement2 instanceof Inet6Address) {
                                sb2.append(nextElement2.getHostAddress());
                                sb2.append(DYConstants.DY_REGEX_COMMA);
                            }
                        }
                    }
                }
            }
            if (sb.length() > 0) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
            if (sb2.length() > 0) {
                sb2 = sb2.delete(sb2.length() - 1, sb2.length());
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb.toString());
            sb3.append("|");
            sb3.append(sb2.toString());
            String sb4 = sb3.toString();
            e.b(sb4);
            if (!TextUtils.isEmpty(sb.toString())) {
                f.e(sb.toString());
            }
            return sb4;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(Context context, String str) {
        try {
            return a(a(context, str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean a(ConnectivityManager connectivityManager) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static String b(Context context) {
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0));
        } catch (Exception unused) {
            return "";
        }
    }

    public static int a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                if (a(connectivityManager)) {
                    e.a("Data and WIFI");
                    return 1;
                }
                e.a("Only WIFI");
                return 2;
            }
            if (activeNetworkInfo.getType() == 0) {
                e.a("Only Data");
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo)) {
                    return 0;
                }
                f.d(extraInfo);
                return 0;
            }
            return -1;
        }
        return -1;
    }

    public static byte[] a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0].toByteArray();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(bArr);
            int length = digest.length;
            char[] cArr = new char[length * 2];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                char[] cArr2 = a;
                cArr[i3] = cArr2[(digest[i2] & 240) >> 4];
                cArr[i3 + 1] = cArr2[digest[i2] & 15];
            }
            return new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) {
        PublicKey e2 = e(str2);
        Cipher cipher = Cipher.getInstance(new String(com.unicom.xiaowo.login.a.b.b("UlNBL0VDQi9QS0NTMVBhZGRpbmc=")));
        cipher.init(1, e2);
        return com.unicom.xiaowo.login.a.b.a(cipher.doFinal(str.getBytes()));
    }
}
