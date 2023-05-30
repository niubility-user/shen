package com.jingdong.sdk.dialingtest.f;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpbury.t;

/* loaded from: classes7.dex */
public class a {
    private static final Pattern a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final Pattern b = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f14807c = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((?:([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$");
    private static final Pattern d = Pattern.compile("(?<=icmp_seq=)([0-9]+)(?=\\s)");

    /* renamed from: e  reason: collision with root package name */
    private static final Pattern f14808e = Pattern.compile("(?<==)([.0-9\\s]+)(?=ms)");

    /* renamed from: f  reason: collision with root package name */
    private static final Pattern f14809f = Pattern.compile("(?<=From )((?:[0-9]{1,3}\\.){3}[0-9]{1,3}|(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|((?:[0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((?:([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?))");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        String replaceAll = "http://A/diagnose?q=A.".replaceAll("A", UUID.randomUUID().toString() + "-" + String.valueOf(System.currentTimeMillis()) + ".jddebug.com");
        com.jingdong.sdk.dialingtest.c.e.a.a("DtCommonUtil", replaceAll);
        return replaceAll;
    }

    public static String b(String str, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        if (k(str)) {
            sb.append("ping6 ");
        } else {
            sb.append("ping ");
        }
        sb.append("-c ");
        sb.append(i2);
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append("-A ");
        if (i3 != 0) {
            sb.append("-t ");
            sb.append(i3);
            sb.append(LangUtils.SINGLE_SPACE);
        }
        if (i4 != 0) {
            sb.append("-W ");
            sb.append(i4);
            sb.append(LangUtils.SINGLE_SPACE);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String c(InetAddress[] inetAddressArr) {
        String str = "";
        if (inetAddressArr == null || inetAddressArr.length == 0) {
            return "";
        }
        if (h()) {
            int length = inetAddressArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                InetAddress inetAddress = inetAddressArr[i2];
                if (inetAddress != null && !inetAddress.isLoopbackAddress()) {
                    String b2 = com.jingdong.sdk.dialingtest.e.b.b.a().b(inetAddress);
                    if (!TextUtils.isEmpty(b2) && !b2.toLowerCase().startsWith("fe80")) {
                        str = b2;
                        break;
                    }
                }
                i2++;
            }
        }
        if (TextUtils.isEmpty(str)) {
            for (InetAddress inetAddress2 : inetAddressArr) {
                if (inetAddress2 != null && !inetAddress2.isLoopbackAddress()) {
                    String a2 = com.jingdong.sdk.dialingtest.e.b.b.a().a(inetAddress2);
                    if (!TextUtils.isEmpty(a2)) {
                        return a2;
                    }
                }
            }
            return str;
        }
        return str;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (k(str)) {
            if (str.contains("::")) {
                return f14807c.matcher(str).matches();
            }
            return b.matcher(str).matches();
        }
        return a.matcher(str).matches();
    }

    public static String e() {
        return String.format(Locale.ENGLISH, "%010d", Long.valueOf(System.currentTimeMillis() / 1000));
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return com.jingdong.sdk.dialingtest.e.a.d.a(com.jingdong.sdk.dialingtest.e.a.d.d(str.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(String str) {
        Matcher matcher = d.matcher(str);
        return matcher.find() ? matcher.group().trim() : "";
    }

    public static boolean h() {
        return com.jingdong.sdk.dialingtest.e.b.b.a().c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String i(String str) {
        Matcher matcher = f14808e.matcher(str);
        return matcher.find() ? matcher.group().trim() : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String j(String str) {
        Matcher matcher = f14809f.matcher(str);
        return matcher.find() ? matcher.group().trim() : "";
    }

    public static boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains(":");
    }

    public static Map<String, Object> l(String str) {
        long j2;
        String name;
        InetAddress[] inetAddressArr;
        String str2;
        HashMap hashMap = new HashMap(4);
        try {
            j2 = SystemClock.uptimeMillis();
            try {
                inetAddressArr = InetAddress.getAllByName(str);
                name = "";
                str2 = name;
            } catch (Exception e2) {
                e = e2;
                name = e.getClass().getName();
                com.jingdong.sdk.dialingtest.c.e.a.c("DtCommonUtil", e.toString());
                inetAddressArr = null;
                str2 = "Dns resolve error";
                hashMap.put("remoteInet", inetAddressArr);
                hashMap.put("time", "" + (SystemClock.uptimeMillis() - j2));
                hashMap.put("exMsg", str2);
                hashMap.put(t.f20145j, name);
                return hashMap;
            }
        } catch (Exception e3) {
            e = e3;
            j2 = 0;
        }
        hashMap.put("remoteInet", inetAddressArr);
        hashMap.put("time", "" + (SystemClock.uptimeMillis() - j2));
        hashMap.put("exMsg", str2);
        hashMap.put(t.f20145j, name);
        return hashMap;
    }
}
