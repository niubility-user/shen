package com.jingdong.manto.m.n0;

import com.jingdong.manto.m.d0;

/* loaded from: classes15.dex */
public class d extends d0 {
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r6.equals("black") != false) goto L9;
     */
    @Override // com.jingdong.manto.m.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void exec(com.jingdong.manto.h r5, org.json.JSONObject r6, int r7, java.lang.String r8) {
        /*
            r4 = this;
            java.lang.String r0 = "color"
            java.lang.String r1 = ""
            java.lang.String r6 = r6.optString(r0, r1)
            com.jingdong.manto.q.n r0 = com.jingdong.manto.m.c0.getPageView(r5)
            r1 = 0
            if (r0 != 0) goto L19
            java.lang.String r6 = "fail"
        L11:
            java.lang.String r6 = r4.putErrMsg(r6, r1, r8)
            r5.a(r7, r6)
            return
        L19:
            java.lang.String r2 = "white"
            boolean r3 = r6.equals(r2)
            if (r3 == 0) goto L25
        L21:
            r0.a(r2)
            goto L2e
        L25:
            java.lang.String r2 = "black"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L2e
            goto L21
        L2e:
            java.lang.String r6 = "ok"
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.n0.d.exec(com.jingdong.manto.h, org.json.JSONObject, int, java.lang.String):void");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setStatusBarStyle";
    }
}
