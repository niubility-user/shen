package com.jdcn.fido.utils;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes18.dex */
public class CommandUtil {

    /* loaded from: classes18.dex */
    static class SingletonHolder {
        private static final CommandUtil INSTANCE = new CommandUtil();

        private SingletonHolder() {
        }
    }

    private CommandUtil() {
    }

    public static final CommandUtil getSingleInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003d, code lost:
        if (r1.waitFor() == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String handlerCommand(java.lang.String r4, boolean r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L45
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L45
            if (r2 == r3) goto L47
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L45
            java.lang.Process r1 = r2.exec(r4)     // Catch: java.lang.Throwable -> L45
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L45
            r4.close()     // Catch: java.lang.Throwable -> L45
            com.jdcn.fido.utils.StreamGobbler r4 = new com.jdcn.fido.utils.StreamGobbler     // Catch: java.lang.Throwable -> L45
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L45
            r4.<init>(r2, r5)     // Catch: java.lang.Throwable -> L45
            r4.start()     // Catch: java.lang.Throwable -> L45
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L45
            r2 = 26
            if (r5 < r2) goto L39
            r2 = 1
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L45
            boolean r5 = r1.waitFor(r2, r5)     // Catch: java.lang.Throwable -> L45
            if (r5 == 0) goto L47
            goto L3f
        L39:
            int r5 = r1.waitFor()     // Catch: java.lang.Throwable -> L45
            if (r5 != 0) goto L47
        L3f:
            java.lang.String r4 = r4.getContent()     // Catch: java.lang.Throwable -> L45
            r0 = r4
            goto L47
        L45:
            goto L4d
        L47:
            if (r1 == 0) goto L50
        L49:
            r1.destroy()
            goto L50
        L4d:
            if (r1 == 0) goto L50
            goto L49
        L50:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdcn.fido.utils.CommandUtil.handlerCommand(java.lang.String, boolean):java.lang.String");
    }

    public String getProperty(String str) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, str);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
