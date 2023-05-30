package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.interfaces.IExceptionHandler;

/* loaded from: classes13.dex */
public class j implements h {
    /* JADX WARN: Removed duplicated region for block: B:72:0x0036 A[Catch: Exception -> 0x0053, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0053, blocks: (B:58:0x000b, B:69:0x002b, B:72:0x0036, B:77:0x003e, B:79:0x0042, B:66:0x0020), top: B:101:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x003e A[Catch: Exception -> 0x0053, TRY_ENTER, TryCatch #2 {Exception -> 0x0053, blocks: (B:58:0x000b, B:69:0x002b, B:72:0x0036, B:77:0x003e, B:79:0x0042, B:66:0x0020), top: B:101:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0072  */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(com.jd.dynamic.a.g r4, java.lang.String r5, java.lang.Object... r6) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L93
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 == 0) goto Lb
            goto L93
        Lb:
            int r1 = r5.hashCode()     // Catch: java.lang.Exception -> L53
            r2 = -1926005497(0xffffffff8d337d07, float:-5.530913E-31)
            if (r1 == r2) goto L28
            r2 = 3590(0xe06, float:5.03E-42)
            if (r1 == r2) goto L25
            r2 = 94750088(0x5a5c588, float:1.5589087E-35)
            if (r1 == r2) goto L1e
            goto L2b
        L1e:
            java.lang.String r1 = "click"
        L20:
            boolean r1 = r5.equals(r1)     // Catch: java.lang.Exception -> L53
            goto L2b
        L25:
            java.lang.String r1 = "pv"
            goto L20
        L28:
            java.lang.String r1 = "exposure"
            goto L20
        L2b:
            r1 = 0
            java.lang.Object r6 = com.jd.dynamic.a.a.a.i.a(r1, r6)     // Catch: java.lang.Exception -> L53
            boolean r1 = r6 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> L53
            java.lang.String r2 = "fun"
            if (r1 == 0) goto L3e
            org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch: java.lang.Exception -> L53
            r6.put(r2, r5)     // Catch: java.lang.Exception -> L3c
            goto L58
        L3c:
            r5 = move-exception
            goto L55
        L3e:
            boolean r1 = r6 instanceof java.lang.String     // Catch: java.lang.Exception -> L53
            if (r1 == 0) goto L51
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L53
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L53
            r1.<init>(r6)     // Catch: java.lang.Exception -> L53
            r1.put(r2, r5)     // Catch: java.lang.Exception -> L4e
            r6 = r1
            goto L58
        L4e:
            r5 = move-exception
            r6 = r1
            goto L55
        L51:
            r6 = r0
            goto L58
        L53:
            r5 = move-exception
            r6 = r0
        L55:
            r5.printStackTrace()
        L58:
            if (r6 == 0) goto L93
            com.jd.dynamic.base.DynamicTemplateEngine r5 = r4.b()
            if (r5 == 0) goto L93
            com.jd.dynamic.base.DynamicTemplateEngine r5 = r4.b()
            com.jd.dynamic.base.CachePool r5 = r5.getCachePool()
            java.lang.String r0 = r3.a()
            com.jd.dynamic.base.CommFunction r5 = r5.getCommFunctionByType(r0)
            if (r5 != 0) goto L86
            com.jd.dynamic.lib.b.a.a.j1 r5 = new com.jd.dynamic.lib.b.a.a.j1
            r5.<init>()
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r4.b()
            com.jd.dynamic.base.CachePool r0 = r0.getCachePool()
            java.lang.String r1 = r3.a()
            r0.addCommFunction(r1, r5)
        L86:
            android.view.View r0 = r4.f1712e
            r5.mTargetView = r0
            com.jd.dynamic.base.DynamicTemplateEngine r4 = r4.b()
            java.lang.Object r4 = r5.exec(r4, r6)
            return r4
        L93:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.a.a.a.j.a(com.jd.dynamic.a.g, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return IExceptionHandler.DynamicExceptionData.TYPE_MTA;
    }
}
