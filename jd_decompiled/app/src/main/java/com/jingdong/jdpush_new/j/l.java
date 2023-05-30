package com.jingdong.jdpush_new.j;

import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class l {
    private static Integer a;
    private static String b;

    public static int a() {
        if (a == null) {
            a = Integer.valueOf(b());
        }
        return a.intValue();
    }

    private static int b() {
        if (l() && !com.jd.lib.push.utils.d.j() && n()) {
            return 12;
        }
        if (i()) {
            return 2;
        }
        if (k()) {
            return 1;
        }
        if (j()) {
            return 3;
        }
        if (m()) {
            return 6;
        }
        return o() ? 8 : 0;
    }

    public static String c() {
        Class<?>[] clsArr = {String.class};
        Object[] objArr = {"ro.build.version.emui"};
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod(IMantoServerRequester.GET, clsArr).invoke(cls, objArr);
            g.b("RomUtil", "get EMUI version is:" + str);
            return !TextUtils.isEmpty(str) ? str : "";
        } catch (Exception e2) {
            g.d("RomUtil", " getEmuiVersion wrong:" + e2);
            return "";
        }
    }

    public static String d() {
        return f("ro.miui.ui.version.name");
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0062: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:65:0x0062 */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.<init>()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.append(r6)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r6 = move-exception
            com.jingdong.jdpush_new.j.g.g(r6)
        L39:
            return r1
        L3a:
            r1 = move-exception
            goto L40
        L3c:
            r6 = move-exception
            goto L63
        L3e:
            r1 = move-exception
            r2 = r0
        L40:
            java.lang.String r3 = "RomUtil"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
            r4.<init>()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "Unable to read prop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L61
            r4.append(r6)     // Catch: java.lang.Throwable -> L61
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L61
            com.jingdong.jdpush_new.j.g.e(r3, r6, r1)     // Catch: java.lang.Throwable -> L61
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r6 = move-exception
            com.jingdong.jdpush_new.j.g.g(r6)
        L60:
            return r0
        L61:
            r6 = move-exception
            r0 = r2
        L63:
            if (r0 == 0) goto L6d
            r0.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r0 = move-exception
            com.jingdong.jdpush_new.j.g.g(r0)
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.j.l.e(java.lang.String):java.lang.String");
    }

    private static String f(String str) {
        try {
            return h(str, "");
        } catch (Exception e2) {
            g.g(e2);
            return null;
        }
    }

    public static String g() {
        a();
        if (b == null) {
            b = c.g();
        }
        return b;
    }

    private static String h(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean i() {
        return !TextUtils.isEmpty(c());
    }

    public static boolean j() {
        try {
            if ("meizu".equalsIgnoreCase(BaseInfo.getDeviceBrand())) {
                String oSName = BaseInfo.getOSName();
                if (TextUtils.isEmpty(oSName)) {
                    return true;
                }
                ArrayList arrayList = new ArrayList();
                String replaceAll = Pattern.compile("[^0-9]").matcher(oSName).replaceAll("");
                int i2 = 0;
                while (i2 < replaceAll.length()) {
                    int i3 = i2 + 1;
                    arrayList.add(replaceAll.substring(i2, i3));
                    i2 = i3;
                }
                g.b("rom", oSName + "---" + ((String) arrayList.get(0)));
                return Integer.valueOf((String) arrayList.get(0)).intValue() > 3;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static boolean k() {
        return (TextUtils.isEmpty(f("ro.miui.ui.version.code")) && TextUtils.isEmpty(f("ro.miui.ui.version.name"))) ? false : true;
    }

    private static boolean l() {
        return !TextUtils.isEmpty(f("ro.build.version.magic"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0059, code lost:
        if (r2.toLowerCase().contains("oneplus") != false) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m() {
        /*
            r0 = 0
            java.lang.String r1 = com.jingdong.sdk.baseinfo.BaseInfo.getDeviceBrand()     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = com.jingdong.sdk.baseinfo.BaseInfo.getDeviceManufacture()     // Catch: java.lang.Throwable -> L63
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = "oneplus"
            java.lang.String r5 = "realme"
            java.lang.String r6 = "oppo"
            r7 = 1
            if (r3 != 0) goto L36
            java.lang.String r3 = r1.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r3 = r3.contains(r6)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L34
            java.lang.String r3 = r1.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r3 = r3.contains(r5)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L34
            java.lang.String r1 = r1.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r1 = r1.contains(r4)     // Catch: java.lang.Throwable -> L63
            if (r1 == 0) goto L36
        L34:
            r1 = 1
            goto L37
        L36:
            r1 = 0
        L37:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L5d
            java.lang.String r3 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r3 = r3.contains(r6)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L5b
            java.lang.String r3 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r3 = r3.contains(r5)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L5b
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L63
            boolean r2 = r2.contains(r4)     // Catch: java.lang.Throwable -> L63
            if (r2 == 0) goto L5d
        L5b:
            r2 = 1
            goto L5e
        L5d:
            r2 = 0
        L5e:
            if (r1 != 0) goto L62
            if (r2 == 0) goto L63
        L62:
            r0 = 1
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.j.l.m():boolean");
    }

    public static boolean n() {
        try {
            return com.hihonor.push.sdk.b.c().a(JdSdk.getInstance().getApplication());
        } catch (Exception e2) {
            g.g(e2);
            return false;
        }
    }

    public static boolean o() {
        return "vivo".equalsIgnoreCase(BaseInfo.getDeviceBrand()) && !TextUtils.isEmpty(e("ro.vivo.os.version"));
    }
}
