package com.jingdong.common.permission;

import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes5.dex */
public class RomUtil {
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    private static final String KEY_FLYME_FLYME = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_PUBLISHED = "ro.flyme.published";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_FLYME = "ro.build.display.id";
    private static final String KEY_VERSION_GIONEE = "ro.gn.sv.version";
    private static final String KEY_VERSION_LENOVO = "ro.lenovo.lvp.version";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    private static final String KEY_VIVO_OS_NAME = "ro.vivo.os.name";
    private static final String KEY_VIVO_OS_VERSION = "ro.vivo.os.version";
    private static final String KEY_VIVO_ROM_VERSION = "ro.vivo.rom.version";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SAMSUNG = "SAMSUNG";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    private static final String TAG = "RomUtil";
    private static String sName;
    private static String sVersion;

    public static boolean check(String str) {
        String str2 = sName;
        if (str2 != null) {
            return str2.equals(str);
        }
        String prop = getProp(KEY_VERSION_MIUI);
        sVersion = prop;
        if (!TextUtils.isEmpty(prop)) {
            sName = "MIUI";
        } else {
            String prop2 = getProp(KEY_VERSION_EMUI);
            sVersion = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                sName = "EMUI";
            } else {
                String prop3 = getProp(KEY_VERSION_OPPO);
                sVersion = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    sName = "OPPO";
                } else {
                    String prop4 = getProp("ro.vivo.os.version");
                    sVersion = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        sName = "VIVO";
                    } else {
                        String prop5 = getProp(KEY_VERSION_SMARTISAN);
                        sVersion = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            sName = "SMARTISAN";
                        } else {
                            String oSName = BaseInfo.getOSName();
                            sVersion = oSName;
                            if (oSName.toUpperCase().contains("FLYME")) {
                                sName = "FLYME";
                            } else {
                                sVersion = "unknown";
                                sName = BaseInfo.getDeviceManufacture().toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0062: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:24:0x0062 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getProp(java.lang.String r6) {
        /*
            java.lang.String r0 = "RomUtil"
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r3.<init>()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.String r4 = "getprop "
            r3.append(r4)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r3.append(r6)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r2 = 1024(0x400, float:1.435E-42)
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.String r2 = r3.readLine()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L61
            r3.close()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L61
            r3.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r6 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r6)
        L3b:
            return r2
        L3c:
            r2 = move-exception
            goto L42
        L3e:
            r6 = move-exception
            goto L63
        L40:
            r2 = move-exception
            r3 = r1
        L42:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
            r4.<init>()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "Unable to read prop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L61
            r4.append(r6)     // Catch: java.lang.Throwable -> L61
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L61
            com.jingdong.sdk.oklog.OKLog.e(r0, r6, r2)     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L60
            r3.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r6 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r6)
        L60:
            return r1
        L61:
            r6 = move-exception
            r1 = r3
        L63:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.permission.RomUtil.getProp(java.lang.String):java.lang.String");
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static boolean isEmui() {
        return check("EMUI");
    }

    public static boolean isFlyme() {
        return check("FLYME");
    }

    public static boolean isMiui() {
        return check("MIUI");
    }

    public static boolean isOppo() {
        return check("OPPO");
    }

    public static boolean isQiku() {
        return check("QIKU") || check(SystemInfo.ROM_360);
    }

    public static boolean isSmartisan() {
        return check("SMARTISAN");
    }

    public static boolean isVivo() {
        return check("VIVO");
    }
}
