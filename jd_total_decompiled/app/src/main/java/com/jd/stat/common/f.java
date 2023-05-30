package com.jd.stat.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.tsmservice.data.AppStatus;
import com.unionpay.tsmservice.data.Constant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes18.dex */
public class f {
    private static String a = "";

    public static String a() {
        return TextUtils.isEmpty(a) ? "" : a;
    }

    private static String b() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < 10; i2++) {
            stringBuffer.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(secureRandom.nextInt(62)));
        }
        return String.valueOf(stringBuffer);
    }

    private static String c() {
        return String.format("%02x", Integer.valueOf(new SecureRandom().nextInt(6) + 1));
    }

    private static List<Integer> d(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 5; i2++) {
            if (Character.isDigit(str.charAt(i2))) {
                arrayList.add(Integer.valueOf(str.substring(i2, i2 + 1)));
            } else {
                arrayList.add(Integer.valueOf(str.charAt(i2) % '\n'));
            }
        }
        return arrayList;
    }

    private static List<Integer> e(String str) {
        ArrayList arrayList = new ArrayList();
        for (int length = str.length() - 5; length < str.length(); length++) {
            if (Character.isDigit(str.charAt(length))) {
                arrayList.add(Integer.valueOf(str.substring(length, length + 1)));
            } else {
                arrayList.add(Integer.valueOf(str.charAt(length) % '\n'));
            }
        }
        return arrayList;
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("sha-256").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String g(String str) {
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            String l2 = Long.toString(crc32.getValue(), 36);
            if (l2.length() > 7) {
                return l2.substring(l2.length() - 7);
            }
            if (l2.length() < 7) {
                String str2 = "";
                for (int i2 = 0; i2 < 7 - l2.length(); i2++) {
                    str2 = str2 + "0";
                }
                return str2 + l2;
            }
            return l2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        com.jd.stat.common.b.b.b("JDMob.Security.DeviceFingerprint", "getDeviceFinger called.");
        try {
            String b = b();
            String str = "" + System.currentTimeMillis();
            String c2 = c();
            String str2 = "" + b + str + c2;
            byte[] b2 = b(c2, t.a(context), a(b, str, c2));
            String encodeToString = b2 != null ? Base64.encodeToString(b2, 11) : "";
            String f2 = f(c.d(context) + com.jd.stat.security.c.m());
            int charAt = f2.charAt(0) % '\n';
            String str3 = "JD0121" + f2.substring(charAt, charAt + 4) + str2 + encodeToString;
            String str4 = str3 + g(str3);
            a = str4;
            return str4;
        } catch (Exception e2) {
            com.jd.stat.common.b.b.a("atf", "getDeviceFinger error:" + e2.getMessage());
            return "";
        }
    }

    private static String c(String str, String str2) {
        List<Integer> d = d(str);
        List<Integer> e2 = e(str2);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < d.size(); i2++) {
            sb.append(Math.abs(d.get(i2).intValue() - e2.get(i2).intValue()));
        }
        return sb.toString();
    }

    private static byte[] d(String str, String str2) {
        byte[] bytes = str2.getBytes();
        if (bytes.length % 16 != 0) {
            byte[] bArr = new byte[((bytes.length / 16) + 1) * 16];
            Arrays.fill(bArr, (byte) 0);
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            bytes = bArr;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, JDKeyStore.KEY_TYPE_AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(str.getBytes("UTF-8"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] e(String str, String str2) {
        byte[] bytes = str2.getBytes();
        if (bytes.length % 24 != 0) {
            byte[] bArr = new byte[((bytes.length / 24) + 1) * 24];
            Arrays.fill(bArr, (byte) 0);
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            bytes = bArr;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "DESede");
            IvParameterSpec ivParameterSpec = new IvParameterSpec("00000000".getBytes());
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(str.getBytes("UTF-8"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String b(String str, String str2) {
        List<Integer> c2 = c(str);
        List<Integer> b = b(str2);
        if (c2.size() == 5 && b.size() == 6) {
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            sb.append(Math.abs(b.get(0).intValue()));
            while (i2 < c2.size()) {
                i2++;
                sb.append(Math.abs(c2.get(i2).intValue() - b.get(i2).intValue()));
            }
            return sb.toString();
        }
        return "";
    }

    private static List<Integer> c(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2 += 2) {
            if (Character.isDigit(str.charAt(i2))) {
                arrayList.add(Integer.valueOf(str.substring(i2, i2 + 1)));
            } else {
                arrayList.add(Integer.valueOf(str.charAt(i2) % '\n'));
            }
        }
        return arrayList;
    }

    private static List<Integer> b(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        for (int i2 = 1; i2 < length; i2 += 2) {
            if (Character.isDigit(str.charAt(i2))) {
                arrayList.add(Integer.valueOf(str.substring(i2, i2 + 1)));
            } else {
                arrayList.add(Integer.valueOf(str.charAt(i2) % '\n'));
            }
        }
        return arrayList;
    }

    public static String a(String str, String str2, String str3) {
        str3.hashCode();
        char c2 = '\uffff';
        switch (str3.hashCode()) {
            case R2.attr.placeholderText /* 1537 */:
                if (str3.equals("01")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.placeholderTextAppearance /* 1538 */:
                if (str3.equals("02")) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.attr.placeholderTextColor /* 1539 */:
                if (str3.equals(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                    c2 = 2;
                    break;
                }
                break;
            case R2.attr.placeholder_emptyVisibility /* 1540 */:
                if (str3.equals(Constant.RECHARGE_MODE_DESIGNATED_AND_CACH)) {
                    c2 = 3;
                    break;
                }
                break;
            case R2.attr.player_radius /* 1541 */:
                if (str3.equals(AppStatus.OPEN)) {
                    c2 = 4;
                    break;
                }
                break;
            case R2.attr.pnsIsAutoDark /* 1542 */:
                if (str3.equals(AppStatus.APPLY)) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
                return a(str, str2);
            case 1:
            case 4:
                return b(str, str2);
            case 2:
            case 5:
                return c(str, str2);
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static byte[] b(String str, String str2, String str3) {
        char c2;
        switch (str.hashCode()) {
            case R2.attr.placeholderText /* 1537 */:
                if (str.equals("01")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.attr.placeholderTextAppearance /* 1538 */:
                if (str.equals("02")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.attr.placeholderTextColor /* 1539 */:
                if (str.equals(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.attr.placeholder_emptyVisibility /* 1540 */:
                if (str.equals(Constant.RECHARGE_MODE_DESIGNATED_AND_CACH)) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.attr.player_radius /* 1541 */:
                if (str.equals(AppStatus.OPEN)) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.attr.pnsIsAutoDark /* 1542 */:
                if (str.equals(AppStatus.APPLY)) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        if (c2 != 0 && c2 != 1 && c2 != 2) {
            return d(str2, str3);
        }
        return e(str2, str3);
    }

    private static String a(String str, String str2) {
        Long a2 = a(str);
        Long l2 = 0L;
        List<Integer> b = b(str2);
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            l2 = Long.valueOf(sb.toString());
        }
        return String.valueOf(Math.abs(a2.longValue() - l2.longValue()));
    }

    private static Long a(String str) {
        StringBuilder sb = new StringBuilder();
        String replaceAll = Pattern.compile("[^0-9]").matcher(str).replaceAll("");
        int i2 = 0;
        while (i2 < replaceAll.length()) {
            int i3 = i2 + 1;
            sb.append(replaceAll.substring(i2, i3));
            i2 = i3;
        }
        if (sb.toString().equals("")) {
            sb.append("0");
        }
        return Long.valueOf(sb.toString());
    }
}
