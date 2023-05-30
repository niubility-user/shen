package com.jingdong.common.messagecenter;

import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class RomUtil {
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_MAGICUI_VERSION = "ro.build.version.magic";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_OPPO_VERSION = "ro.build.version.opporom";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

    public static int getDevice() {
        if (isMagicUI()) {
            return 12;
        }
        if (isEMUI()) {
            return 2;
        }
        if (isMIUI()) {
            return 1;
        }
        if (isFlyme()) {
            return 3;
        }
        return isOPPOOs() ? 6 : 0;
    }

    private static String getEmuiVersion() {
        Class<?>[] clsArr = {String.class};
        Object[] objArr = {KEY_EMUI_VERSION_CODE};
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod(IMantoServerRequester.GET, clsArr).invoke(cls, objArr);
            OKLog.d("RomUtil", "get EMUI version is:" + str);
            return !TextUtils.isEmpty(str) ? str : "";
        } catch (ClassNotFoundException unused) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, ClassNotFoundException");
            return "";
        } catch (LinkageError unused2) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, LinkageError");
            return "";
        } catch (NoSuchMethodException unused3) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, NoSuchMethodException");
            return "";
        } catch (NullPointerException unused4) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, NullPointerException");
            return "";
        } catch (Exception unused5) {
            OKLog.e("RomUtil", " getEmuiVersion wrong");
            return "";
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0059: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:60:0x0059 */
    /* JADX WARN: Removed duplicated region for block: B:73:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getProp(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            r2.<init>()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            r2.append(r4)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L3d java.lang.Throwable -> L58
            r2.close()     // Catch: java.io.IOException -> L3d java.lang.Throwable -> L58
            r2.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r4 = move-exception
            r4.printStackTrace()
        L39:
            return r1
        L3a:
            r4 = move-exception
            goto L5a
        L3c:
            r2 = r0
        L3d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58
            r1.<init>()     // Catch: java.lang.Throwable -> L58
            java.lang.String r3 = "Unable to read prop "
            r1.append(r3)     // Catch: java.lang.Throwable -> L58
            r1.append(r4)     // Catch: java.lang.Throwable -> L58
            r1.toString()     // Catch: java.lang.Throwable -> L58
            if (r2 == 0) goto L57
            r2.close()     // Catch: java.io.IOException -> L53
            goto L57
        L53:
            r4 = move-exception
            r4.printStackTrace()
        L57:
            return r0
        L58:
            r4 = move-exception
            r0 = r2
        L5a:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r0 = move-exception
            r0.printStackTrace()
        L64:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.messagecenter.RomUtil.getProp(java.lang.String):java.lang.String");
    }

    private static String getProperty(String str) {
        try {
            try {
                return getSystemProperty(str, "");
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isEMUI() {
        return !TextUtils.isEmpty(getEmuiVersion());
    }

    public static boolean isFlyme() {
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
                OKLog.d("rom", oSName + "---" + ((String) arrayList.get(0)));
                return Integer.valueOf((String) arrayList.get(0)).intValue() > 3;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean isMIUI() {
        try {
            if (TextUtils.isEmpty(getProperty(KEY_MIUI_VERSION_CODE))) {
                if (TextUtils.isEmpty(getProperty(KEY_MIUI_VERSION_NAME))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMagicUI() {
        try {
            return !TextUtils.isEmpty(getProperty(KEY_MAGICUI_VERSION));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMeizuFlymeOS() {
        String oSName = BaseInfo.getOSName();
        if (TextUtils.isEmpty(oSName)) {
            return false;
        }
        return oSName.contains("flyme") || oSName.toLowerCase().contains("flyme");
    }

    public static boolean isOPPOOs() {
        try {
            if ("OPPO".equalsIgnoreCase(BaseInfo.getDeviceBrand())) {
                String prop = getProp(KEY_OPPO_VERSION);
                if (TextUtils.isEmpty(prop)) {
                    return false;
                }
                ArrayList arrayList = new ArrayList();
                String replaceAll = Pattern.compile("[^0-9]").matcher(prop).replaceAll("");
                int i2 = 0;
                while (i2 < replaceAll.length()) {
                    int i3 = i2 + 1;
                    arrayList.add(replaceAll.substring(i2, i3));
                    i2 = i3;
                }
                StringBuilder sb = new StringBuilder();
                sb.append((String) arrayList.get(0));
                sb.append((String) arrayList.get(1));
                return Integer.valueOf(sb.toString()).intValue() >= 31;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isVIVO() {
        try {
            if (TextUtils.isEmpty(getProperty(KEY_VERSION_VIVO))) {
                if (TextUtils.isEmpty(getProperty(KEY_VERSION_VIVO))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
