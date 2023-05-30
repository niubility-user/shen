package com.jingdong.manto.m.u0;

import com.jingdong.manto.m.l0;

/* loaded from: classes15.dex */
public class j extends l0 {
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087 A[Catch: all -> 0x00bb, TRY_LEAVE, TryCatch #0 {all -> 0x00bb, blocks: (B:10:0x0029, B:14:0x004f, B:17:0x005c, B:18:0x0062, B:24:0x0076, B:25:0x0079, B:27:0x0087, B:30:0x008e, B:32:0x0096, B:35:0x00a6, B:37:0x00ad, B:34:0x009e, B:21:0x0069, B:23:0x0071, B:16:0x0057), top: B:41:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    @Override // com.jingdong.manto.m.l0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(com.jingdong.manto.h r8, org.json.JSONObject r9) {
        /*
            r7 = this;
            java.lang.String r8 = "text"
            java.lang.String r8 = r9.optString(r8)
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L13
            java.lang.String r8 = "fail:data is null"
        Le:
            java.lang.String r8 = r7.putErrMsg(r8)
            return r8
        L13:
            r0 = 16
            float r0 = com.jingdong.manto.utils.MantoDensityUtils.dip2pixel(r0)
            double r0 = (double) r0
            java.lang.String r2 = "fontSize"
            double r0 = r9.optDouble(r2, r0)
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L29
            java.lang.String r8 = "fail:param is illegal"
            goto Le
        L29:
            java.lang.String r2 = "fontFamily"
            java.lang.String r2 = r9.optString(r2)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "fontWeight"
            java.lang.String r3 = r9.optString(r3)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r4 = "fontStyle"
            java.lang.String r9 = r9.optString(r4)     // Catch: java.lang.Throwable -> Lbb
            com.jingdong.manto.m.u0.n r4 = new com.jingdong.manto.m.u0.n     // Catch: java.lang.Throwable -> Lbb
            r4.<init>()     // Catch: java.lang.Throwable -> Lbb
            r4.a(r2)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = "oblique"
            boolean r2 = android.text.TextUtils.equals(r9, r2)     // Catch: java.lang.Throwable -> Lbb
            r5 = 0
            java.lang.String r6 = "normal"
            if (r2 == 0) goto L4f
            goto L57
        L4f:
            java.lang.String r2 = "italic"
            boolean r2 = android.text.TextUtils.equals(r9, r2)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L5c
        L57:
            r9 = 2
            r4.a(r9)     // Catch: java.lang.Throwable -> Lbb
            goto L62
        L5c:
            android.text.TextUtils.equals(r9, r6)     // Catch: java.lang.Throwable -> Lbb
            r4.a(r5)     // Catch: java.lang.Throwable -> Lbb
        L62:
            boolean r9 = android.text.TextUtils.equals(r3, r6)     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto L69
            goto L76
        L69:
            java.lang.String r9 = "bold"
            boolean r9 = android.text.TextUtils.equals(r3, r9)     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto L76
            r9 = 1
            r4.setFakeBoldText(r9)     // Catch: java.lang.Throwable -> Lbb
            goto L79
        L76:
            r4.setFakeBoldText(r5)     // Catch: java.lang.Throwable -> Lbb
        L79:
            float r9 = (float) r0     // Catch: java.lang.Throwable -> Lbb
            r4.setTextSize(r9)     // Catch: java.lang.Throwable -> Lbb
            float r8 = r4.measureText(r8)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r9 = com.jingdong.sdk.baseinfo.BaseInfo.getDeviceBrand()     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto L8c
            java.lang.String r9 = r9.toLowerCase()     // Catch: java.lang.Throwable -> Lbb
            goto L8e
        L8c:
            java.lang.String r9 = ""
        L8e:
            java.lang.String r0 = "vivo"
            boolean r0 = r9.contains(r0)     // Catch: java.lang.Throwable -> Lbb
            if (r0 != 0) goto L9e
            java.lang.String r0 = "huawei"
            boolean r9 = r9.contains(r0)     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto La6
        L9e:
            r9 = 1065437102(0x3f8147ae, float:1.01)
            float r8 = r8 * r9
            r9 = 1082130432(0x40800000, float:4.0)
            float r8 = r8 + r9
        La6:
            java.util.HashMap r9 = new java.util.HashMap     // Catch: java.lang.Throwable -> Lbb
            r9.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r0 = "width"
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch: java.lang.Throwable -> Lbb
            r9.put(r0, r8)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r8 = "ok"
            java.lang.String r8 = r7.putErrMsg(r8, r9)     // Catch: java.lang.Throwable -> Lbb
            return r8
        Lbb:
            r8 = move-exception
            r8.printStackTrace()
            java.lang.String r8 = "fail: measureText error"
            goto Le
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.u0.j.a(com.jingdong.manto.h, org.json.JSONObject):java.lang.String");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "measureText";
    }
}
