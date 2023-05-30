package com.jdjr.risk.device.b;

/* loaded from: classes18.dex */
public class j extends a {
    public j() {
        this.a = new com.jdjr.risk.device.entity.i();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_permission_info";
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // com.jdjr.risk.device.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void b(android.content.Context r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            if (r8 == 0) goto L84
            com.jdjr.risk.device.entity.a r0 = r6.a
            com.jdjr.risk.device.entity.i r0 = (com.jdjr.risk.device.entity.i) r0
            android.content.pm.PackageManager r1 = r7.getPackageManager()
            if (r1 == 0) goto L84
            r2 = 0
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            java.lang.String r4 = r7.getPackageName()     // Catch: java.lang.Exception -> L2e
            int r3 = r1.checkPermission(r3, r4)     // Catch: java.lang.Exception -> L2e
            java.lang.String r4 = "android.permission.ACCESS_COARSE_LOCATION"
            java.lang.String r5 = r7.getPackageName()     // Catch: java.lang.Exception -> L2f
            int r4 = r1.checkPermission(r4, r5)     // Catch: java.lang.Exception -> L2f
            java.lang.String r5 = "android.permission.ACCESS_FINE_LOCATION"
            java.lang.String r7 = r7.getPackageName()     // Catch: java.lang.Exception -> L2c
            int r2 = r1.checkPermission(r5, r7)     // Catch: java.lang.Exception -> L2c
            goto L30
        L2c:
            goto L30
        L2e:
            r3 = 0
        L2f:
            r4 = 0
        L30:
            java.lang.String r7 = "p_fine_location"
            int r7 = r8.optInt(r7)
            java.lang.String r1 = ""
            r5 = 1
            if (r7 != r5) goto L4e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            int r2 = r2 + r5
            r7.append(r2)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            r0.a(r7)
        L4e:
            java.lang.String r7 = "p_location"
            int r7 = r8.optInt(r7)
            if (r7 != r5) goto L69
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            int r4 = r4 + r5
            r7.append(r4)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            r0.b(r7)
        L69:
            java.lang.String r7 = "p_phone"
            int r7 = r8.optInt(r7)
            if (r7 != r5) goto L84
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            int r3 = r3 + r5
            r7.append(r3)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            r0.c(r7)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.b.j.b(android.content.Context, org.json.JSONObject):void");
    }
}
