package com.jd.stat.common;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class q {
    private static volatile String a;

    public static String a() {
        try {
            if (TextUtils.isEmpty(a)) {
                a = com.jd.stat.common.b.f.b("sequenceId", "");
            }
            if (TextUtils.isEmpty(a)) {
                a = b();
            }
            return a;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String b() {
        try {
            String str = a(System.currentTimeMillis() + "" + Build.VERSION.SDK_INT + "" + Process.myPid() + "" + UUID.randomUUID()) + c();
            String str2 = str + b(str);
            com.jd.stat.common.b.f.a("sequenceId", str2);
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String c() {
        try {
            Random random = new Random();
            String str = "";
            for (int i2 = 0; i2 < 4; i2++) {
                str = str + Integer.toHexString(random.nextInt(16)).toLowerCase();
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes());
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append(String.format("%02X", new Integer(b & 255)));
            }
            return sb.toString().toLowerCase();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String b(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() == 36) {
                char[] charArray = str.toCharArray();
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < charArray.length; i4++) {
                    if (i4 % 2 == 0) {
                        i2 += Integer.parseInt(String.valueOf(charArray[i4]), 16);
                    } else {
                        int parseInt = Integer.parseInt(String.valueOf(charArray[i4]), 16) * 2;
                        i3 = parseInt < 16 ? i3 + parseInt : i3 + (parseInt / 16) + (parseInt % 16);
                    }
                }
                int i5 = (i2 + i3) % 16;
                return i5 == 0 ? "0" : Integer.toHexString(16 - i5);
            }
        } catch (Exception unused) {
        }
        return JshopConst.JSHOP_PROMOTIO_X;
    }
}
