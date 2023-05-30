package com.jd.jdsec.a.j;

import android.os.Build;
import com.jd.jdsec.c.g;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    private static final List<String> a = Arrays.asList("android", "");
    private static final String b = com.jd.jdsec.a.c.a(g.a);

    /* renamed from: c  reason: collision with root package name */
    private static String f2723c;
    private static String d;

    public static String a(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[1024];
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                int i2 = 0;
                while (i2 != -1) {
                    i2 = fileInputStream.read(bArr);
                    if (i2 > 0) {
                        messageDigest.update(bArr, 0, i2);
                    }
                }
                String b2 = b(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (Exception e2) {
                    com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
                }
                return b2;
            } catch (Exception unused) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                        com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e3.getMessage());
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e4) {
                        com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e4.getMessage());
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String b(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            str = str + Integer.toString((b2 & 255) + 256, 16).substring(1);
        }
        return str.toUpperCase();
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("modelOn", "0");
            jSONObject.put("modelHandleSuccess", "0");
            jSONObject.put("modelFileReal", "0");
            jSONObject.put("modelUnpack", "0");
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
        }
        return jSONObject;
    }

    public static JSONObject d(JSONArray jSONArray, int i2) throws Exception {
        long currentTimeMillis;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        float[] i3;
        JSONObject m2 = m();
        JSONObject c2 = c();
        try {
            currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
            jSONObject = jSONObject3.getJSONObject("modelInfo");
            jSONObject2 = jSONObject3.getJSONObject("dependsOn");
            i3 = a.i();
        } finally {
            try {
                return m2;
            } finally {
            }
        }
        if (i3 == null) {
            return null;
        }
        m2.put(Constants.PARAM_MODEL_NAME, jSONObject.getString("name") + CartConstant.KEY_YB_INFO_LINK + jSONObject.getString("version"));
        String str = "/data/data/" + com.jd.jdsec.c.h.b.a.f2756e + "/files/fzb_model/" + jSONObject.getString("name");
        if (g(jSONObject2)) {
            if (!l(str)) {
                new File(str).mkdirs();
            }
            String str2 = str + File.separator;
            String str3 = str2 + jSONObject.getString("name") + CartConstant.KEY_YB_INFO_LINK + jSONObject.getString("version") + ".zip";
            Boolean valueOf = Boolean.valueOf(e(jSONObject.getString("url"), str2, str3, m2));
            c2.put("modelHandleSuccess", "1");
            if (valueOf.booleanValue() && j(jSONObject).booleanValue()) {
                c2.put("modelOn", "1");
                if (h(jSONObject, str3)) {
                    c2.put("modelFileReal", "1");
                    if (f.a(str3)) {
                        c2.put("modelUnpack", "1");
                        e.a(i3, str2, jSONObject.optString("decryptionKey"), m2);
                    }
                }
            }
            c2.put("timeDiff", System.currentTimeMillis() - currentTimeMillis);
            m2.put("client_log", c2.toString());
            return m2;
        }
        return null;
    }

    public static boolean e(String str, String str2, String str3, JSONObject jSONObject) {
        try {
            File[] listFiles = new File(str2).listFiles();
            if (listFiles.length == 0) {
                if (d.a(str, str3).booleanValue()) {
                    jSONObject.put("model_isdl", "1");
                }
            } else if (listFiles.length == 1) {
                if (!l(str3)) {
                    if (d.a(str, str3).booleanValue()) {
                        jSONObject.put("model_isdl", "1");
                    }
                } else {
                    jSONObject.put("model_iscache", "1");
                }
            } else if (listFiles.length == 2) {
                if (!l(str3)) {
                    if (d.a(str, str3).booleanValue()) {
                        jSONObject.put("model_isdl", "1");
                    }
                    i(listFiles, str2);
                } else {
                    jSONObject.put("model_iscache", "1");
                }
            }
            return true;
        } catch (NullPointerException e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
            return false;
        } catch (Exception e3) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e3.getMessage());
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r0.contains(r3) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean f(java.lang.String r3, org.json.JSONObject r4) {
        /*
            java.lang.String r0 = "ban-device"
            java.util.ArrayList r0 = com.jd.jdsec.a.l.e.d(r4, r0)     // Catch: java.lang.Exception -> L30
            java.lang.String r1 = "device"
            java.util.ArrayList r1 = com.jd.jdsec.a.l.e.d(r4, r1)     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = "totalTrafficOn"
            boolean r4 = r4.optBoolean(r2)     // Catch: java.lang.Exception -> L30
            if (r4 == 0) goto L15
            goto L2e
        L15:
            int r4 = r1.size()     // Catch: java.lang.Exception -> L30
            if (r4 <= 0) goto L22
            boolean r3 = r1.contains(r3)     // Catch: java.lang.Exception -> L30
            if (r3 == 0) goto L3a
            goto L2e
        L22:
            int r4 = r0.size()     // Catch: java.lang.Exception -> L30
            if (r4 <= 0) goto L3a
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Exception -> L30
            if (r3 != 0) goto L3a
        L2e:
            r3 = 1
            goto L3b
        L30:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()
            java.lang.String r4 = "JDSec.Security.DuccProcess"
            com.jd.jdsec.a.l.b.b(r4, r3)
        L3a:
            r3 = 0
        L3b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.j.b.f(java.lang.String, org.json.JSONObject):boolean");
    }

    public static boolean g(JSONObject jSONObject) {
        try {
            if (a.contains(jSONObject.getString(Constants.PARAM_PLATFORM))) {
                String string = jSONObject.getString("appVersion");
                if ((string.equals("") || string.contains(b)) && !jSONObject.getString("ban-appVersion").contains(b)) {
                    String string2 = jSONObject.getString("minAPILevel");
                    if (string2.equals("") || Build.VERSION.SDK_INT >= Integer.parseInt(string2)) {
                        if (f(BaseInfo.getDeviceModel(), jSONObject)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
        }
        return false;
    }

    public static boolean h(JSONObject jSONObject, String str) {
        return jSONObject.optString(MessageDigestAlgorithms.MD5).equals(a(str));
    }

    public static boolean i(File[] fileArr, String str) {
        String str2 = "21220902141118";
        for (File file : fileArr) {
            String name = file.getName();
            String substring = name.substring(name.lastIndexOf(CartConstant.KEY_YB_INFO_LINK) + 1);
            if (str2.compareTo(substring) > 0) {
                str2 = substring;
            }
        }
        boolean z = false;
        for (File file2 : fileArr) {
            String name2 = file2.getName();
            if (name2.contains(str2)) {
                z = d.b(str + name2);
                com.jd.jdsec.a.l.b.e("JDSec.Security.DuccProcess", ">>>>>>>>\u5220\u9664\u540e\u6587\u4ef6\u4e2a\u6570>>>>>>>>" + fileArr.length);
            }
        }
        return z;
    }

    public static Boolean j(JSONObject jSONObject) {
        boolean z;
        try {
            z = jSONObject.optBoolean("on");
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public static String k() {
        return d;
    }

    public static boolean l(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
            return false;
        }
    }

    private static JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("extkey", g.a());
            jSONObject.put("createdate", System.currentTimeMillis());
            jSONObject.put("prevuuid", o());
            jSONObject.put("currentuuid", k());
            jSONObject.put("input_vec", "");
            jSONObject.put("model_iscache", "0");
            jSONObject.put("model_isdl", "0");
            jSONObject.put(Constants.PARAM_MODEL_NAME, "");
            jSONObject.put("model_isinferstart", "0");
            jSONObject.put("model_isinferend", "0");
            jSONObject.put("model_inferendts", "");
            jSONObject.put("output_vec", "");
            jSONObject.put("input_vec", "");
            jSONObject.put("debug_info", "");
            jSONObject.put("model_log", "");
            jSONObject.put("client_log", "");
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DuccProcess", e2.getMessage());
        }
        return jSONObject;
    }

    public static void n(String str) {
        d = str;
    }

    public static String o() {
        return f2723c;
    }

    public static void p(String str) {
        f2723c = str;
    }
}
