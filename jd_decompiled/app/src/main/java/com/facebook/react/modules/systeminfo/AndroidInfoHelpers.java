package com.facebook.react.modules.systeminfo;

import android.os.Build;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Locale;

/* loaded from: classes12.dex */
public class AndroidInfoHelpers {
    private static final int DEBUG_SERVER_HOST_PORT = 8081;
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    private static final int INSPECTOR_PROXY_PORT = 8082;
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = "AndroidInfoHelpers";
    private static String metroHostPropValue;

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return BaseInfo.getDeviceModel();
        }
        return BaseInfo.getDeviceModel() + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    public static String getInspectorProxyHost() {
        return getServerIpAddress(8082);
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0071, code lost:
        if (r2 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0074, code lost:
        r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0077, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0044, code lost:
        if (r2 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0046, code lost:
        r2.destroy();
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0082 A[Catch: all -> 0x0086, TRY_ENTER, TryCatch #5 {, blocks: (B:74:0x0003, B:86:0x003f, B:90:0x0046, B:109:0x0074, B:104:0x006c, B:114:0x007b, B:118:0x0082, B:119:0x0085), top: B:131:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized java.lang.String getMetroHostPropValue() {
        /*
            java.lang.Class<com.facebook.react.modules.systeminfo.AndroidInfoHelpers> r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L9
            monitor-exit(r0)
            return r1
        L9:
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
            r4 = 0
            java.lang.String r5 = "/system/bin/getprop"
            r3[r4] = r5     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
            r4 = 1
            java.lang.String r5 = "metro.host"
            r3[r4] = r5     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            java.lang.String r6 = "UTF-8"
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r6)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L51
            java.lang.String r1 = ""
        L35:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L78
            if (r4 == 0) goto L3d
            r1 = r4
            goto L35
        L3d:
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L78
            r3.close()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L86
            goto L44
        L43:
        L44:
            if (r2 == 0) goto L74
        L46:
            r2.destroy()     // Catch: java.lang.Throwable -> L86
            goto L74
        L4a:
            r1 = move-exception
            goto L5f
        L4c:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L79
        L51:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L5f
        L56:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
            goto L79
        L5b:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L5f:
            java.lang.String r4 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.TAG     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w(r4, r5, r1)     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ""
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L71
            r3.close()     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L86
            goto L71
        L70:
        L71:
            if (r2 == 0) goto L74
            goto L46
        L74:
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L86
            monitor-exit(r0)
            return r1
        L78:
            r1 = move-exception
        L79:
            if (r3 == 0) goto L80
            r3.close()     // Catch: java.lang.Exception -> L7f java.lang.Throwable -> L86
            goto L80
        L7f:
        L80:
            if (r2 == 0) goto L85
            r2.destroy()     // Catch: java.lang.Throwable -> L86
        L85:
            throw r1     // Catch: java.lang.Throwable -> L86
        L86:
            r1 = move-exception
            monitor-exit(r0)
            goto L8a
        L89:
            throw r1
        L8a:
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }

    public static String getServerHost() {
        return getServerIpAddress(8081);
    }

    private static String getServerIpAddress(int i2) {
        String metroHostPropValue2 = getMetroHostPropValue();
        if (metroHostPropValue2.equals("")) {
            if (isRunningOnGenymotion()) {
                metroHostPropValue2 = GENYMOTION_LOCALHOST;
            } else {
                metroHostPropValue2 = isRunningOnStockEmulator() ? EMULATOR_LOCALHOST : DEVICE_LOCALHOST;
            }
        }
        return String.format(Locale.US, "%s:%d", metroHostPropValue2, Integer.valueOf(i2));
    }

    private static boolean isRunningOnGenymotion() {
        return BaseInfo.getOSFingerprint().contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return BaseInfo.getOSFingerprint().contains("generic");
    }
}
