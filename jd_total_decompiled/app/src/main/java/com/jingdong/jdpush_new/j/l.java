package com.jingdong.jdpush_new.j;

import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0062: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:106:0x0062 */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String e(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            } catch (IOException e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader3 != null) {
                }
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    g.g(e3);
                }
                return readLine;
            } catch (IOException e4) {
                e = e4;
                g.e("RomUtil", "Unable to read prop " + str, e);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                        g.g(e5);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader2;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (IOException e6) {
                    g.g(e6);
                }
            }
            throw th;
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0059, code lost:
        if (r2.toLowerCase().contains("oneplus") != false) goto L88;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m() {
        boolean z;
        try {
            String deviceBrand = BaseInfo.getDeviceBrand();
            String deviceManufacture = BaseInfo.getDeviceManufacture();
            boolean z2 = !TextUtils.isEmpty(deviceBrand) && (deviceBrand.toLowerCase().contains("oppo") || deviceBrand.toLowerCase().contains("realme") || deviceBrand.toLowerCase().contains("oneplus"));
            if (!TextUtils.isEmpty(deviceManufacture)) {
                if (!deviceManufacture.toLowerCase().contains("oppo") && !deviceManufacture.toLowerCase().contains("realme")) {
                }
                z = true;
                return !z2 || z;
            }
            z = false;
            if (z2) {
            }
        } catch (Throwable unused) {
            return false;
        }
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
