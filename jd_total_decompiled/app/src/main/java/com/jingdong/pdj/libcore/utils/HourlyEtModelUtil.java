package com.jingdong.pdj.libcore.utils;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.pdj.libcore.point.PointData;
import com.jingdong.sdk.oklog.OKLog;

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
    */
    public static void addParams(PointData pointData, JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2, JDJSONObject jDJSONObject3, int i2, String str) {
        Object obj;
        if (pointData == null) {
            return;
        }
        try {
            JDJSONObject jDJSONObject4 = new JDJSONObject();
            jDJSONObject4.put(EtModelMaker.KEY_P_ID, (Object) "Home_Nearby_Main");
            boolean z = false;
            JDJSONArray jDJSONArray = new JDJSONArray();
            boolean z2 = true;
            if (jDJSONObject != null) {
                if (isCorrectId(jDJSONObject)) {
                    jDJSONArray.add(jDJSONObject);
                } else {
                    z = true;
                }
            }
            if (jDJSONObject2 != null) {
                if (isCorrectId(jDJSONObject2)) {
                    jDJSONArray.add(jDJSONObject2);
                }
                if (jDJSONObject3 != null && isCorrectId(jDJSONObject3)) {
                    jDJSONArray.add(jDJSONObject3);
                }
                if (z2) {
                    jDJSONArray = new JDJSONArray();
                }
                jDJSONObject4.put("area", (Object) jDJSONArray);
                jDJSONObject4.put(EtModelMaker.KEY_LA_ID, (Object) "-100");
                jDJSONObject4.put(EtModelMaker.KEY_LA_P, (Object) String.valueOf(i2));
                obj = null;
                obj = JDJSON.parse(str);
                if (obj == null) {
                    obj = buildStmObject();
                }
                jDJSONObject4.put(EtModelMaker.KEY_STM, obj);
                pointData.et_model_param = jDJSONObject4;
            }
            z2 = z;
            if (jDJSONObject3 != null) {
                jDJSONArray.add(jDJSONObject3);
            }
            if (z2) {
            }
            jDJSONObject4.put("area", (Object) jDJSONArray);
            jDJSONObject4.put(EtModelMaker.KEY_LA_ID, (Object) "-100");
            jDJSONObject4.put(EtModelMaker.KEY_LA_P, (Object) String.valueOf(i2));
            obj = null;
            obj = JDJSON.parse(str);
            if (obj == null) {
            }
            jDJSONObject4.put(EtModelMaker.KEY_STM, obj);
            pointData.et_model_param = jDJSONObject4;
        } catch (Exception e2) {
            OKLog.e("HourlyEtModelUtil", "HourlyEtModelUtil=".concat(String.valueOf(e2)));
        }
    }
}
