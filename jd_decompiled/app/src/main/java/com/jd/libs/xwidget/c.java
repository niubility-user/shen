package com.jd.libs.xwidget;

import com.jd.xbridge.base.IBridgePlugin;

/* loaded from: classes16.dex */
public class c implements IBridgePlugin {
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r5 == 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
        com.jd.libs.xwidget.a.a("hybrid-image").newInstance().callNative(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        return true;
     */
    @Override // com.jd.xbridge.base.IBridgePlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean execute(com.jd.xbridge.base.IBridgeWebView r5, java.lang.String r6, java.lang.String r7, com.jd.xbridge.base.IBridgeCallback r8) {
        /*
            r4 = this;
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r0 = 0
            r5[r0] = r6
            r1 = 1
            r5[r1] = r7
            java.lang.String r2 = "action:%s, params:%s"
            java.lang.String.format(r2, r5)
            r5 = -1
            int r2 = r6.hashCode()     // Catch: java.lang.Exception -> L56
            r3 = 1499608255(0x596234bf, float:3.97945873E15)
            if (r2 == r3) goto L28
            r3 = 1511497695(0x5a179fdf, float:1.06696254E16)
            if (r2 == r3) goto L1e
            goto L31
        L1e:
            java.lang.String r2 = "hybridVideo"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> L56
            if (r6 == 0) goto L31
            r5 = 0
            goto L31
        L28:
            java.lang.String r2 = "hybridImage"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> L56
            if (r6 == 0) goto L31
            r5 = 1
        L31:
            if (r5 == 0) goto L46
            if (r5 == r1) goto L36
            goto L5a
        L36:
            java.lang.String r5 = "hybrid-image"
            java.lang.Class r5 = com.jd.libs.xwidget.a.a(r5)     // Catch: java.lang.Exception -> L56
            java.lang.Object r5 = r5.newInstance()     // Catch: java.lang.Exception -> L56
            com.jd.libs.xwidget.b r5 = (com.jd.libs.xwidget.b) r5     // Catch: java.lang.Exception -> L56
            r5.callNative(r7, r8)     // Catch: java.lang.Exception -> L56
            return r1
        L46:
            java.lang.String r5 = "hybrid-video"
            java.lang.Class r5 = com.jd.libs.xwidget.a.a(r5)     // Catch: java.lang.Exception -> L56
            java.lang.Object r5 = r5.newInstance()     // Catch: java.lang.Exception -> L56
            com.jd.libs.xwidget.b r5 = (com.jd.libs.xwidget.b) r5     // Catch: java.lang.Exception -> L56
            r5.callNative(r7, r8)     // Catch: java.lang.Exception -> L56
            return r1
        L56:
            r5 = move-exception
            r5.toString()
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.xwidget.c.execute(com.jd.xbridge.base.IBridgeWebView, java.lang.String, java.lang.String, com.jd.xbridge.base.IBridgeCallback):boolean");
    }
}
