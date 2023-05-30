package com.jd.stat.bot;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jd.stat.bot.bean.HeaderBean;
import com.jd.stat.bot.bean.JoyyTokenBean;
import com.jd.stat.bot.bean.PayloadBean;
import com.jd.stat.common.TriTouchUtil;
import com.jd.stat.common.b.b;
import com.jd.stat.common.n;
import com.jd.stat.security.c;
import com.jd.stat.security.d;
import com.jd.stat.security.jma.b.k;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.smtt.sdk.ProxyConfig;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.zip.CRC32;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class BlogUtil {
    public static final String SUPPORT_IDS = "vwxyz";
    private static String a = "0,0";
    public static boolean allowTriTouch = false;
    private static String b = "cccc";

    /* renamed from: c  reason: collision with root package name */
    private static String f6945c = "";
    private static long d = 0;
    public static boolean debugGettoken = false;

    /* renamed from: e  reason: collision with root package name */
    private static JoyyTokenBean f6946e = null;

    /* renamed from: f  reason: collision with root package name */
    private static final String[] f6947f = {"com.baidu", "com.huawei", "com.miui", "com.xiaomi", "com.tencent", "com.vivo", "com.oneplus", "com.gionee", "com.sohu", "com.oppo", "com.samsung", "com.fooview", "com.iflytek", "com.qihoo", "com.amigo", "com.hmct", "com.microsoft", "com.qiku", "com.lenovo", "cn.nubia", "com.motorola", "com.zte", "com.smartisanos", "com.google", "com.asus", "com.meizu", "com.ss.android", "com.kugou", "com.meitu", "com.clean", "com.xuexi", "com.xmiles", "screenlock", "com.coloros", "market", "appcenter", "appstore", "com.android"};
    public static String md5pin = "";

    static /* synthetic */ String a() {
        return i();
    }

    static /* synthetic */ String b() {
        return j();
    }

    private static void c() {
        try {
            b.a("bot", "updateJoyyToken");
            JoyyTokenBean joyyTokenBean = f6946e;
            if (joyyTokenBean == null || joyyTokenBean.isExpire()) {
                f6946e = new JoyyTokenBean();
                b.a("bot", "getToken request");
                new k().c(new JSONObject());
            }
        } catch (Exception e2) {
            b.a("bot", "updateJoyyToken error:" + e2.getMessage());
        }
    }

    private static void d() {
        try {
            new Thread(new Runnable() { // from class: com.jd.stat.bot.BlogUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        b.a("bot", "updateBotCache");
                        String unused = BlogUtil.a = BlogUtil.a();
                        String unused2 = BlogUtil.b = BlogUtil.b();
                    } catch (Throwable th) {
                        b.a("bot", "updateBotCache error:" + th.getMessage());
                    }
                }
            }).start();
        } catch (Throwable th) {
            b.b("JDMob.BlogUtil", th);
        }
    }

    private static boolean e() {
        return System.currentTimeMillis() - d > ((long) (d.a().N() * 1000));
    }

    private static String f() {
        try {
            char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
            Random random = new Random();
            String str = "";
            for (int i2 = 0; i2 < 10; i2++) {
                str = str + charArray[random.nextInt(charArray.length)];
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g() {
        PayloadBean payloadBean;
        JoyyTokenBean joyyTokenBean = f6946e;
        return (joyyTokenBean == null || (payloadBean = joyyTokenBean.payload) == null) ? "x,z,*,1" : payloadBean.encrypt_id;
    }

    public static String getBlog(String str, String str2, boolean z) {
        JoyyTokenBean joyyTokenBean;
        try {
            b.a("bot", "===getBlog start===");
            if (z || (joyyTokenBean = f6946e) == null || joyyTokenBean.isExpire() || TextUtils.isEmpty(f6945c) || e()) {
                d();
                c();
                f6945c = a(str, str2);
                d = System.currentTimeMillis();
                allowTriTouch = true;
            }
            md5pin = e(str);
            b.a("bot", "getBlog=" + f6945c + "\n===getBlog end===");
            return f6945c;
        } catch (Throwable th) {
            b.a("bot", "getBlog error=" + th.getMessage());
            return "{\"status\":101,\"msg\":\"" + th.getMessage() + "\",\"blog\":\"f\"}";
        }
    }

    public static String getCF_V() {
        HeaderBean headerBean;
        JoyyTokenBean joyyTokenBean = f6946e;
        return (joyyTokenBean == null || (headerBean = joyyTokenBean.header) == null) ? "00" : headerBean.version;
    }

    public static long getQ() {
        JoyyTokenBean joyyTokenBean = f6946e;
        if (joyyTokenBean == null) {
            return 0L;
        }
        return joyyTokenBean.q;
    }

    private static String h() {
        try {
            String a2 = com.jd.stat.common.a.a(c.a);
            if (!TextUtils.isEmpty(a2) && !";".equals(a2)) {
                String[] split = a2.split(";");
                ArrayList<String> arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Set<String> E = d.a().E();
                for (String str : split) {
                    Iterator<String> it = E.iterator();
                    while (it.hasNext()) {
                        if (str.contains(it.next()) && !arrayList.contains(str)) {
                            arrayList.add(str);
                        }
                    }
                }
                for (String str2 : split) {
                    arrayList2.add(str2);
                    for (String str3 : f6947f) {
                        if (str2.contains(str3) && arrayList2.contains(str2)) {
                            arrayList2.remove(str2);
                        }
                    }
                    Iterator<String> it2 = E.iterator();
                    while (it2.hasNext()) {
                        if (str2.contains(it2.next()) && arrayList2.contains(str2)) {
                            arrayList2.remove(str2);
                        }
                    }
                }
                arrayList.addAll(arrayList2);
                int i2 = 0;
                String str4 = "";
                for (String str5 : arrayList) {
                    if (i2 < 3) {
                        i2++;
                        str4 = str4 + str5 + ";";
                    }
                }
                return (TextUtils.isEmpty(str4) || !str4.endsWith(";")) ? str4 : str4.substring(0, str4.length() - 1);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    private static String i() {
        try {
            return (n.p() ? "1" : "0") + DYConstants.DY_REGEX_COMMA + com.jd.stat.security.jma.a.a.d.a();
        } catch (Exception e2) {
            b.a("bot", "getIsRoot error:" + e2.getMessage());
            return "0,0";
        }
    }

    private static String j() {
        try {
            return com.jd.stat.common.c.f(c.a).a;
        } catch (Exception e2) {
            b.a("bot", "getMultiOpen error:" + e2.getMessage());
            return "cccc";
        }
    }

    public static void reportFailEvent(String str, String str2) {
        try {
            new k().a(str, str2);
        } catch (Exception e2) {
            b.a("bot", "reportFailEvent error:" + e2.getMessage());
        }
    }

    public static void updateJoyytoken(JSONObject jSONObject) {
        try {
            if (f6946e != null) {
                b.a("bot", "isExpire=" + f6946e.isExpire());
            }
            f6946e = new JoyyTokenBean(jSONObject);
        } catch (Exception e2) {
            b.a("bot", "updateJoyytoken error:" + e2.getMessage());
        }
    }

    private static String e(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes());
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b2 : digest) {
                sb.append(String.format("%02X", new Integer(b2 & 255)));
            }
            return sb.toString().toLowerCase();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(String str, String str2) {
        try {
            b.a("bot", "getRealBlog");
            b.a("bot", "pin=" + str);
            b.a("bot", "refer=" + str2);
            String c2 = c(str2);
            String valueOf = String.valueOf(System.currentTimeMillis() + getQ());
            b.a("bot", "timestamp=" + valueOf);
            String a2 = a(str, c2, valueOf);
            b.a("bot", "json=" + a2);
            String f2 = f();
            String d2 = d(str);
            b.a("bot", "token=" + d2);
            String g2 = g();
            b.a("bot", "encrypeid=" + g2);
            String[] g3 = g(g2);
            String a3 = a(g2, g3);
            b.a("bot", "reportIds=" + a3);
            String a4 = a(g3, valueOf, f2);
            b.a("bot", "key=" + a4);
            String str3 = a4.contains("GKDSJENWQSAA") ? AuraConstants.MESSAGE_COUPON_TYPE_NEW : "B";
            b.a("bot", "isDefaultKey=" + str3);
            String b2 = b(a2, a4);
            b.a("bot", "cipher=" + b2);
            String f3 = f(b2);
            b.a("bot", "crc=" + f3);
            String str4 = valueOf + "~1" + f2 + d2 + "~" + a3 + "~~~" + str3 + "~" + b2 + "~" + f3;
            b.a("bot", "blog=" + str4);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", 0);
            jSONObject.put("msg", "");
            jSONObject.put("blog", str4);
            return jSONObject.toString();
        } catch (Exception e2) {
            b.a("bot", "getRealBlog error:" + e2.getMessage());
            return "{\"status\":102,\"msg\":\"" + e2.getMessage() + "\",\"blog\":\"f\"}";
        }
    }

    private static String b(String str, String str2) {
        try {
            char[] charArray = str.toCharArray();
            char[] charArray2 = str2.toCharArray();
            String str3 = "";
            for (int i2 = 0; i2 < charArray.length; i2++) {
                str3 = str3 + String.valueOf((char) (charArray[i2] ^ charArray2[i2 % charArray2.length]));
            }
            b.a("bot", "before base64=" + str3);
            return Base64.encodeToString(str3.getBytes(), 2);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String[] g(String str) {
        String str2;
        String str3;
        String str4;
        try {
            if (TextUtils.isEmpty(str)) {
                return new String[]{"z"};
            }
            b.a("bot", "getAlgoIds encrypeid=" + str);
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            String str5 = "";
            if (split == null || split.length < 4) {
                str2 = "";
                str3 = str2;
                str4 = str3;
            } else {
                str2 = split[0];
                str3 = split[1];
                str4 = split[2];
                String str6 = split[3];
            }
            if (SUPPORT_IDS.contains(str2) || SUPPORT_IDS.contains(str3)) {
                String substring = SUPPORT_IDS.indexOf(str2) != -1 ? SUPPORT_IDS.substring(SUPPORT_IDS.indexOf(str2)) : SUPPORT_IDS;
                str5 = substring.indexOf(str3) != -1 ? substring.substring(0, substring.indexOf(str3) + 1) : substring;
            }
            b.a("bot", "getAlgoIds rangeIds=" + str5);
            char[] charArray = str4.toCharArray();
            String[] strArr = new String[charArray.length];
            for (int i2 = 0; i2 < charArray.length; i2++) {
                if (SUPPORT_IDS.contains(String.valueOf(charArray[i2]))) {
                    strArr[i2] = String.valueOf(charArray[i2]);
                    b.a("bot", "algoId in =" + strArr[i2]);
                } else if (ProxyConfig.MATCH_ALL_SCHEMES.equals(String.valueOf(charArray[i2])) && str5.length() > 0) {
                    strArr[i2] = String.valueOf(str5.toCharArray()[new Random().nextInt(str5.length())]);
                    b.a("bot", "algoId * =" + strArr[i2]);
                } else {
                    strArr[i2] = "z";
                    b.a("bot", "algoId out =" + strArr[i2]);
                }
            }
            return strArr;
        } catch (Exception e2) {
            b.a("bot", "getEncrypeId error:" + e2.getMessage());
            return new String[]{"z"};
        }
    }

    private static String d(String str) {
        JoyyTokenBean joyyTokenBean = f6946e;
        if (joyyTokenBean != null && !TextUtils.isEmpty(joyyTokenBean.joyytoken) && !f6946e.isExpire()) {
            return f6946e.joyytoken;
        }
        return e(str);
    }

    private static String f(String str) {
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

    private static String c(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(DYConstants.DY_REGEX_COMMA)) {
            return str;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        return split.length > 1 ? split[0] : str;
    }

    private static String d(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                char[] charArray = str.toCharArray();
                char[] charArray2 = str2.toCharArray();
                String str3 = "";
                String str4 = "";
                for (int i2 = 0; i2 < charArray.length; i2++) {
                    str4 = str4 + Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]);
                }
                char[] charArray3 = str4.toCharArray();
                String str5 = "";
                for (int i3 = 0; i3 < charArray3.length; i3++) {
                    if (i3 % 2 == 0) {
                        str3 = str3 + String.valueOf(charArray3[i3]);
                    } else {
                        str5 = str5 + String.valueOf(charArray3[i3]);
                    }
                }
                String str6 = str3 + str5;
                return TextUtils.isEmpty(str6) ? "GKDSJENWQSAA" : str6;
            }
            return "GKDSJENWQSAA";
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String e(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                char[] charArray = str.toCharArray();
                char[] charArray2 = str2.toCharArray();
                String str3 = "";
                for (int i2 = 0; i2 < charArray.length; i2++) {
                    str3 = str3 + Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]);
                }
                return TextUtils.isEmpty(str3) ? "GKDSJENWQSAA" : str3;
            }
            return "GKDSJENWQSAA";
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String b(String str, int i2) {
        if (str == null) {
            return "";
        }
        try {
            if (i2 >= str.length()) {
                return str;
            }
            String substring = str.substring(0, str.length() - i2);
            return str.substring(str.length() - i2) + substring;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String c(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                char[] charArray = str.toCharArray();
                char[] charArray2 = str2.toCharArray();
                String str3 = "";
                String str4 = "";
                for (int i2 = 0; i2 < charArray.length; i2++) {
                    str4 = str4 + Integer.toHexString(charArray[i2] | charArray2[i2 % charArray2.length]);
                }
                char[] charArray3 = str4.toCharArray();
                String str5 = "";
                for (int i3 = 0; i3 < charArray3.length; i3++) {
                    if (i3 % 2 == 0) {
                        str3 = str3 + String.valueOf(charArray3[i3]);
                    } else {
                        str5 = str5 + String.valueOf(charArray3[i3]);
                    }
                }
                String str6 = str3 + str5;
                return TextUtils.isEmpty(str6) ? "GKDSJENWQSAA" : str6;
            }
            return "GKDSJENWQSAA";
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String f(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                char[] charArray = str.toCharArray();
                char[] charArray2 = str2.toCharArray();
                String str3 = "";
                for (int i2 = 0; i2 < charArray.length; i2++) {
                    str3 = str3 + Integer.toHexString(charArray[i2] & charArray2[i2 % charArray2.length]);
                }
                return TextUtils.isEmpty(str3) ? "GKDSJENWQSAA" : str3;
            }
            return "GKDSJENWQSAA";
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String h(String str) {
        try {
            return str.substring(str.length() - 4);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                String a2 = a(str, 1);
                String b2 = b(str2, 1);
                char[] charArray = a2.toCharArray();
                char[] charArray2 = b2.toCharArray();
                String str3 = "";
                for (int i2 = 0; i2 < charArray.length; i2++) {
                    str3 = str3 + Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]);
                }
                return TextUtils.isEmpty(str3) ? "GKDSJENWQSAA" : str3;
            }
            return "GKDSJENWQSAA";
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String a(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("r", "" + str2);
            jSONObject.put(com.jingdong.jdsdk.a.a.a, "" + h());
            jSONObject.put("c", com.jingdong.jdsdk.a.a.a);
            jSONObject.put("cf_v", "" + getCF_V());
            jSONObject.put("t", "" + h(str3));
            jSONObject.put("ir", "" + a);
            jSONObject.put("mp", "" + b);
            jSONObject.put("m", TriTouchUtil.getInstance().getBlogActionUpTouchEvent());
            jSONObject.put("ci", "c3.4");
            String jSONObject2 = jSONObject.toString();
            b.a("BlogUtil", "blogGetJsonStr" + jSONObject2);
            return jSONObject2;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(String str, String[] strArr) {
        try {
            if (!TextUtils.isEmpty(str) && strArr != null && strArr.length != 0) {
                String str2 = "";
                String str3 = "1";
                String[] split = str.split(DYConstants.DY_REGEX_COMMA);
                if (split != null && split.length >= 4) {
                    str3 = split[3];
                }
                for (String str4 : strArr) {
                    str2 = str2 + str4;
                }
                return str2 + DYConstants.DY_REGEX_COMMA + str3;
            }
        } catch (Exception unused) {
        }
        return "z,1";
    }

    private static String a(String[] strArr, String str, String str2) {
        try {
            String str3 = "";
            for (String str4 : strArr) {
                char c2 = '\uffff';
                switch (str4.hashCode()) {
                    case 118:
                        if (str4.equals("v")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 119:
                        if (str4.equals(JshopConst.JSHOP_PROMOTIO_W)) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 120:
                        if (str4.equals(JshopConst.JSHOP_PROMOTIO_X)) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 121:
                        if (str4.equals(JshopConst.JSHOP_PROMOTIO_Y)) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 122:
                        if (str4.equals("z")) {
                            c2 = 2;
                            break;
                        }
                        break;
                }
                if (c2 == 0) {
                    str3 = str3 + c(str, str2);
                } else if (c2 == 1) {
                    str3 = str3 + d(str, str2);
                } else if (c2 == 2) {
                    str3 = str3 + e(str, str2);
                } else if (c2 == 3) {
                    str3 = str3 + f(str, str2);
                } else if (c2 != 4) {
                    str3 = str3 + "GKDSJENWQSAA";
                } else {
                    str3 = str3 + g(str, str2);
                }
            }
            return TextUtils.isEmpty(str3) ? "GKDSJENWQSAA" : str3;
        } catch (Exception unused) {
            return "GKDSJENWQSAA";
        }
    }

    private static String a(String str, int i2) {
        if (str == null) {
            return "";
        }
        try {
            if (i2 >= str.length()) {
                return str;
            }
            String substring = str.substring(0, i2);
            return str.substring(i2) + substring;
        } catch (Exception unused) {
            return "";
        }
    }
}
