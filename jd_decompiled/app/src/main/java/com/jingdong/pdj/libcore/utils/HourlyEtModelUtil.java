package com.jingdong.pdj.libcore.utils;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.pdj.libcore.point.PointData;

/* loaded from: classes7.dex */
public class HourlyEtModelUtil {
    public static void addParams(PointData pointData, JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2, int i2, String str) {
        addParams(pointData, jDJSONObject, jDJSONObject2, null, i2, str);
    }

    public static JDJSONObject buildAreaObject(long j2, int i2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put(EtModelMaker.KEY_A_ID, (Object) String.valueOf(j2));
        jDJSONObject.put(EtModelMaker.KEY_A_P, (Object) String.valueOf(i2));
        return jDJSONObject;
    }

    private static JDJSONObject buildStmObject() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("src", (Object) "-100");
        JDJSONArray jDJSONArray = new JDJSONArray();
        jDJSONObject.put(EtModelMaker.KEY_CONT, (Object) jDJSONArray);
        jDJSONObject.put("abtest", (Object) jDJSONArray);
        return jDJSONObject;
    }

    private static boolean isCorrectId(JDJSONObject jDJSONObject) {
        return !"0".equals(jDJSONObject.optString(EtModelMaker.KEY_A_ID));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:4|5|(2:7|(1:9)(1:10))|(12:12|(1:14)|(1:18)|19|(1:21)|22|23|24|25|(1:27)|28|29)|33|(2:16|18)|19|(0)|22|23|24|25|(0)|28|29) */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003e A[Catch: Exception -> 0x006b, TryCatch #1 {Exception -> 0x006b, blocks: (B:4:0x0003, B:6:0x0018, B:8:0x001e, B:11:0x0025, B:13:0x002b, B:16:0x0031, B:18:0x0037, B:21:0x003e, B:22:0x0043, B:28:0x005f, B:29:0x0063), top: B:36:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f A[Catch: Exception -> 0x006b, TRY_ENTER, TryCatch #1 {Exception -> 0x006b, blocks: (B:4:0x0003, B:6:0x0018, B:8:0x001e, B:11:0x0025, B:13:0x002b, B:16:0x0031, B:18:0x0037, B:21:0x003e, B:22:0x0043, B:28:0x005f, B:29:0x0063), top: B:36:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void addParams(com.jingdong.pdj.libcore.point.PointData r5, com.jd.framework.json.JDJSONObject r6, com.jd.framework.json.JDJSONObject r7, com.jd.framework.json.JDJSONObject r8, int r9, java.lang.String r10) {
        /*
            if (r5 != 0) goto L3
            return
        L3:
            com.jd.framework.json.JDJSONObject r0 = new com.jd.framework.json.JDJSONObject     // Catch: java.lang.Exception -> L6b
            r0.<init>()     // Catch: java.lang.Exception -> L6b
            java.lang.String r1 = "p_id"
            java.lang.String r2 = "Home_Nearby_Main"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L6b
            r1 = 0
            com.jd.framework.json.JDJSONArray r2 = new com.jd.framework.json.JDJSONArray     // Catch: java.lang.Exception -> L6b
            r2.<init>()     // Catch: java.lang.Exception -> L6b
            r3 = 1
            if (r6 == 0) goto L23
            boolean r4 = isCorrectId(r6)     // Catch: java.lang.Exception -> L6b
            if (r4 == 0) goto L22
            r2.add(r6)     // Catch: java.lang.Exception -> L6b
            goto L23
        L22:
            r1 = 1
        L23:
            if (r7 == 0) goto L2e
            boolean r6 = isCorrectId(r7)     // Catch: java.lang.Exception -> L6b
            if (r6 == 0) goto L2f
            r2.add(r7)     // Catch: java.lang.Exception -> L6b
        L2e:
            r3 = r1
        L2f:
            if (r8 == 0) goto L3a
            boolean r6 = isCorrectId(r8)     // Catch: java.lang.Exception -> L6b
            if (r6 == 0) goto L3a
            r2.add(r8)     // Catch: java.lang.Exception -> L6b
        L3a:
            java.lang.String r6 = "area"
            if (r3 == 0) goto L43
            com.jd.framework.json.JDJSONArray r2 = new com.jd.framework.json.JDJSONArray     // Catch: java.lang.Exception -> L6b
            r2.<init>()     // Catch: java.lang.Exception -> L6b
        L43:
            r0.put(r6, r2)     // Catch: java.lang.Exception -> L6b
            java.lang.String r6 = "la_id"
            java.lang.String r7 = "-100"
            r0.put(r6, r7)     // Catch: java.lang.Exception -> L6b
            java.lang.String r6 = "la_p"
            java.lang.String r7 = java.lang.String.valueOf(r9)     // Catch: java.lang.Exception -> L6b
            r0.put(r6, r7)     // Catch: java.lang.Exception -> L6b
            r6 = 0
            java.lang.Object r6 = com.jd.framework.json.JDJSON.parse(r10)     // Catch: java.lang.Exception -> L5c
            goto L5d
        L5c:
        L5d:
            if (r6 != 0) goto L63
            com.jd.framework.json.JDJSONObject r6 = buildStmObject()     // Catch: java.lang.Exception -> L6b
        L63:
            java.lang.String r7 = "stm"
            r0.put(r7, r6)     // Catch: java.lang.Exception -> L6b
            r5.et_model_param = r0     // Catch: java.lang.Exception -> L6b
            return
        L6b:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r6 = "HourlyEtModelUtil="
            java.lang.String r5 = r6.concat(r5)
            java.lang.String r6 = "HourlyEtModelUtil"
            com.jingdong.sdk.oklog.OKLog.e(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.utils.HourlyEtModelUtil.addParams(com.jingdong.pdj.libcore.point.PointData, com.jd.framework.json.JDJSONObject, com.jd.framework.json.JDJSONObject, com.jd.framework.json.JDJSONObject, int, java.lang.String):void");
    }
}
