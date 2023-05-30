package com.jingdong.sdk.jdhttpdns.core;

/* loaded from: classes7.dex */
public class DialingMethod {
    public static final String TAG = "DialingMethod";

    /* JADX WARN: Removed duplicated region for block: B:33:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long connect(java.lang.String r6, int r7) {
        /*
            if (r7 != 0) goto L4
            r7 = 443(0x1bb, float:6.21E-43)
        L4:
            r0 = 0
            r1 = 0
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L42
            java.net.Socket r5 = new java.net.Socket     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L40
            r5.<init>()     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L40
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3b
            r0.<init>(r6, r7)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3b
            r7 = 2000(0x7d0, float:2.803E-42)
            r5.connect(r0, r7)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3b
            r7 = 255(0xff, float:3.57E-43)
            r5.sendUrgentData(r7)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3b
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3b
            boolean r7 = r5.isClosed()     // Catch: java.io.IOException -> L33
            if (r7 != 0) goto L2c
            r5.shutdownInput()     // Catch: java.io.IOException -> L33
        L2c:
            r5.shutdownOutput()     // Catch: java.io.IOException -> L33
            r5.close()     // Catch: java.io.IOException -> L33
            goto L58
        L33:
            r7 = move-exception
            r7.printStackTrace()
            goto L58
        L38:
            r6 = move-exception
            r0 = r5
            goto L76
        L3b:
            r7 = move-exception
            r0 = r5
            goto L44
        L3e:
            r7 = move-exception
            goto L44
        L40:
            r6 = move-exception
            goto L76
        L42:
            r7 = move-exception
            r3 = r1
        L44:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L40
            if (r0 == 0) goto L52
            boolean r7 = r0.isClosed()     // Catch: java.io.IOException -> L33
            if (r7 != 0) goto L52
            r0.shutdownInput()     // Catch: java.io.IOException -> L33
        L52:
            r0.shutdownOutput()     // Catch: java.io.IOException -> L33
            r0.close()     // Catch: java.io.IOException -> L33
        L58:
            long r1 = r1 - r3
            boolean r7 = com.jingdong.sdk.jdhttpdns.utils.DNSLog.D
            if (r7 == 0) goto L75
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "ip : "
            r7.append(r0)
            r7.append(r6)
            java.lang.String r6 = ", rtt time : "
            r7.append(r6)
            r7.append(r1)
            r7.toString()
        L75:
            return r1
        L76:
            if (r0 == 0) goto L81
            boolean r7 = r0.isClosed()     // Catch: java.io.IOException -> L88
            if (r7 != 0) goto L81
            r0.shutdownInput()     // Catch: java.io.IOException -> L88
        L81:
            r0.shutdownOutput()     // Catch: java.io.IOException -> L88
            r0.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r7 = move-exception
            r7.printStackTrace()
        L8c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdhttpdns.core.DialingMethod.connect(java.lang.String, int):long");
    }
}
