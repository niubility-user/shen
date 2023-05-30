package com.jdjr.risk.biometric.c;

/* loaded from: classes18.dex */
public class g extends b {
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0075, code lost:
        if (r8.size() > 0) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0097, code lost:
        if (r8.size() > 0) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0099, code lost:
        com.jdjr.risk.device.entity.n.a.clear();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            r0 = 0
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r7.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r2 = r8.getPackageName()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r1.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r1.append(r3)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r3 = ""
            r1.append(r3)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r1 = r7
            r3 = r9
            r4 = r12
            r6 = r10
            com.jdjr.risk.biometric.c.b.a(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            com.jdjr.risk.biometric.core.BiometricManager r10 = com.jdjr.risk.biometric.core.BiometricManager.getInstance()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            com.jdjr.risk.biometric.core.e r10 = r10.a()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r10 = r10.b(r8)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r12 = "token"
            r7.put(r12, r10)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r10 = com.jdjr.risk.device.entity.n.a     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            if (r10 == 0) goto L6a
            int r10 = r10.size()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            if (r10 <= 0) goto L6a
            org.json.JSONArray r10 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r10.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r12 = com.jdjr.risk.device.entity.n.a     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.util.Iterator r12 = r12.iterator()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
        L4c:
            boolean r1 = r12.hasNext()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            if (r1 == 0) goto L60
            java.lang.Object r1 = r12.next()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            com.jdjr.risk.device.entity.n r1 = (com.jdjr.risk.device.entity.n) r1     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            org.json.JSONObject r1 = r1.a()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r10.put(r1)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            goto L4c
        L60:
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r12 = com.jdjr.risk.device.entity.n.a     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            r12.clear()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.lang.String r12 = "sensorInfo"
            r7.put(r12, r10)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
        L6a:
            com.jdjr.risk.biometric.c.b.b(r8, r11, r7, r9)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L8e
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r8 = com.jdjr.risk.device.entity.n.a
            if (r8 == 0) goto L9e
            int r8 = r8.size()
            if (r8 <= 0) goto L9e
            goto L99
        L78:
            r8 = move-exception
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r9 = com.jdjr.risk.device.entity.n.a
            if (r9 == 0) goto L88
            int r9 = r9.size()
            if (r9 <= 0) goto L88
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r9 = com.jdjr.risk.device.entity.n.a
            r9.clear()
        L88:
            java.util.concurrent.atomic.AtomicBoolean r9 = com.jdjr.risk.device.b.o.a
            r9.set(r0)
            throw r8
        L8e:
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r8 = com.jdjr.risk.device.entity.n.a
            if (r8 == 0) goto L9e
            int r8 = r8.size()
            if (r8 <= 0) goto L9e
        L99:
            java.util.ArrayList<com.jdjr.risk.device.entity.n> r8 = com.jdjr.risk.device.entity.n.a
            r8.clear()
        L9e:
            java.util.concurrent.atomic.AtomicBoolean r8 = com.jdjr.risk.device.b.o.a
            r8.set(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.c.g.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
