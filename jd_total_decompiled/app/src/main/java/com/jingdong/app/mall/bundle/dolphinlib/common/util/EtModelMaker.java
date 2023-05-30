package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;
import java.util.Map;

/* loaded from: classes19.dex */
public final class EtModelMaker {
    public static final String JUMP_PROTOCOL_M = "m";
    public static final String JUMP_PROTOCOL_PRODUCT = "productDetail";
    public static final String KEY_AREA = "area";
    public static final String KEY_A_ID = "a_id";
    public static final String KEY_A_P = "a_p";
    public static final String KEY_CONT = "cont";
    public static final String KEY_ET_MODEL = "et_model";
    public static final String KEY_ID = "id";
    public static final String KEY_LA_ID = "la_id";
    public static final String KEY_LA_P = "la_p";
    public static final String KEY_LSTM = "lstm";
    public static final String KEY_LSTMS = "lstms";
    public static final String KEY_P_ID = "p_id";
    public static final String KEY_SKUID = "skuId";
    public static final String KEY_SRC = "src";
    public static final String KEY_STM = "stm";
    public static final String KEY_TYP = "typ";
    public static final String KEY_URL = "url";

    public static final JDJSONObject makeJsonLSTM(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put(KEY_P_ID, nullToEmpty(str));
            jDJSONObject.put(KEY_LA_ID, nullToEmpty(str2));
            jDJSONObject.put(KEY_LA_P, nullToEmpty(str3));
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put(KEY_A_ID, (Object) nullToEmpty(str4));
            jDJSONObject2.put(KEY_A_P, (Object) nullToEmpty(str5));
            JDJSONArray jDJSONArray = new JDJSONArray();
            jDJSONArray.add(jDJSONObject2);
            jDJSONObject.put("area", (Object) jDJSONArray);
            JDJSONObject jDJSONObject3 = new JDJSONObject();
            if (!TextUtils.isEmpty(str6)) {
                jDJSONObject3.put("src", (Object) nullToEmpty(str6));
            }
            JDJSONObject jDJSONObject4 = new JDJSONObject();
            if (!TextUtils.isEmpty(str7)) {
                jDJSONObject4.put(KEY_TYP, (Object) nullToEmpty(str7));
            }
            if (!TextUtils.isEmpty(str8)) {
                jDJSONObject4.put("id", (Object) nullToEmpty(str8));
            }
            JDJSONArray jDJSONArray2 = new JDJSONArray();
            if (!jDJSONObject4.isEmpty()) {
                jDJSONArray2.add(jDJSONObject4);
            }
            if (!jDJSONArray2.isEmpty()) {
                jDJSONObject3.put(KEY_CONT, (Object) jDJSONArray2);
            }
            if (!jDJSONObject3.isEmpty()) {
                jDJSONObject.put(KEY_STM, (Object) jDJSONObject3);
            }
        } catch (Throwable unused) {
        }
        return jDJSONObject;
    }

    public static final Map<String, String> makeLSTMArray(Map<String, String> map, JDJSONArray jDJSONArray) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (jDJSONArray != null && !jDJSONArray.isEmpty()) {
                jDJSONObject.put(KEY_LSTMS, (Object) jDJSONArray);
            }
            if (!jDJSONObject.isEmpty()) {
                map.put(KEY_ET_MODEL, jDJSONObject.toJSONString());
            }
        } catch (Throwable unused) {
        }
        return map;
    }

    public static final Map<String, String> makeSingleLSTM(Map<String, String> map, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            JDJSONObject makeJsonLSTM = makeJsonLSTM(str, str2, str3, str4, str5, str6, str7, str8);
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (!makeJsonLSTM.isEmpty()) {
                jDJSONObject.put(KEY_LSTM, (Object) makeJsonLSTM);
            }
            if (!jDJSONObject.isEmpty()) {
                map.put(KEY_ET_MODEL, jDJSONObject.toJSONString());
            }
        } catch (Throwable unused) {
        }
        return map;
    }

    private static final String nullToEmpty(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static final String parseIdByJump(JumpEntity jumpEntity) {
        try {
            String str = jumpEntity.des;
            if (TextUtils.equals(str, "m")) {
                return (String) jumpEntity.getParamValue("url");
            }
            if (TextUtils.equals(str, "productDetail")) {
                return (String) jumpEntity.getParamValue("skuId");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final String parseTypByJump(JumpEntity jumpEntity) {
        try {
            String str = jumpEntity.des;
            if (TextUtils.equals(str, "m")) {
                return "4_1";
            }
            if (TextUtils.equals(str, "productDetail")) {
                return "0";
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
