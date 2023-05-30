package com.jingdong.common.phonecharge.phone;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.phonecharge.model.ChargeOrder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ParseJsonToData {
    public static final String success = "0";

    public static ChargeOrder parseJSONtoChargeOrder(JDJSONObject jDJSONObject) {
        JSONObject jSONObject;
        ChargeOrder chargeOrder = null;
        if (jDJSONObject == null || !"0".equals(jDJSONObject.optString("code"))) {
            return null;
        }
        String optString = jDJSONObject.optString("rechargeOrder");
        String optString2 = jDJSONObject.optString("payback");
        if (optString == null || "".equals(optString)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(optString);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            ChargeOrder chargeOrder2 = (ChargeOrder) JDJSON.parseObject(jSONObject.toString(), ChargeOrder.class);
            try {
                chargeOrder2.payback = optString2;
                return chargeOrder2;
            } catch (Exception e3) {
                e = e3;
                chargeOrder = chargeOrder2;
                e.printStackTrace();
                return chargeOrder;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }
}
