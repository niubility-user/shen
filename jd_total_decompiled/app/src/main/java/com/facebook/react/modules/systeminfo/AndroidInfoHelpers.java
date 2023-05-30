package com.facebook.react.modules.systeminfo;

import android.os.Build;
import com.facebook.common.logging.FLog;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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

    /* JADX WARN: Code restructure failed: missing block: B:159:0x0044, code lost:
        if (r2 == null) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0046, code lost:
        r2.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0071, code lost:
        if (r2 == null) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0074, code lost:
        r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0077, code lost:
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0082 A[Catch: all -> 0x0086, TRY_ENTER, TryCatch #5 {, blocks: (B:144:0x0003, B:156:0x003f, B:160:0x0046, B:179:0x0074, B:174:0x006c, B:184:0x007b, B:188:0x0082, B:189:0x0085), top: B:201:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static synchronized String getMetroHostPropValue() {
        BufferedReader bufferedReader;
        Throwable th;
        Process process;
        Exception e2;
        synchronized (AndroidInfoHelpers.class) {
            String str = metroHostPropValue;
            if (str != null) {
                return str;
            }
            try {
                process = Runtime.getRuntime().exec(new String[]{"/system/bin/getprop", METRO_HOST_PROP_NAME});
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("UTF-8")));
                    String str2 = "";
                    while (true) {
                        try {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                str2 = readLine;
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception unused) {
                                    }
                                }
                                if (process != null) {
                                    process.destroy();
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            e2 = e3;
                            FLog.w(TAG, "Failed to query for metro.host prop:", e2);
                            metroHostPropValue = "";
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception unused2) {
                                }
                            }
                        }
                    }
                    metroHostPropValue = str2;
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                } catch (Exception e4) {
                    bufferedReader = null;
                    e2 = e4;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                    if (bufferedReader != null) {
                    }
                    if (process != null) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                bufferedReader = null;
                e2 = e5;
                process = null;
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                process = null;
            }
        }
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
