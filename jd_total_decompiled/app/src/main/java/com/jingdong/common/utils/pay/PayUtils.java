package com.jingdong.common.utils.pay;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.utils.c;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PayUtils extends AndroidPayConstants {
    private static final String TAG = "PayUtils";
    private static IPay iPay;
    private static Object nativeJDPay;

    static {
        try {
            Object a = c.a("com.jingdong.app.mall.utils.pay.JDCashierNativePay");
            nativeJDPay = a;
            setPay((IPay) a);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    @Deprecated
    public static void doPay(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, PayCallbackListener payCallbackListener) {
        if (activity == null || iPay == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return;
        }
        iPay.doPay(activity, generatePayJDJsonObject(str, str2, str3, str4, str6, str5, ""), payCallbackListener);
    }

    public static void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        if (iPay == null || TextUtils.isEmpty(str)) {
            return;
        }
        iPay.doPayFinishForward(str, browserNewUrlListener);
    }

    public static void doPayWithWebURL(Activity activity, String str, String str2) {
        if (iPay == null || TextUtils.isEmpty(str)) {
            return;
        }
        iPay.doPayWithWebURL(activity, str, str2);
    }

    public static JDJSONObject generatePayJDJsonObject(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            jDJSONObject.put("orderId", (Object) str);
            jDJSONObject.put("orderTypeCode", (Object) str2);
            jDJSONObject.put("orderType", (Object) str3);
            jDJSONObject.put("payablePrice", (Object) str4);
            if (!TextUtils.isEmpty(str6)) {
                jDJSONObject.put("back_url", (Object) str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jDJSONObject.put("paySourceId", (Object) str7);
            } else {
                jDJSONObject.put("paySourceId", (Object) "-1");
            }
            if (!TextUtils.isEmpty(str5)) {
                jDJSONObject.put("fromActivity", (Object) str5);
            }
        }
        return jDJSONObject;
    }

    @Deprecated
    public static JSONObject generatePayJsonObject(String str, String str2, String str3, String str4, String str5, String str6) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            jSONObject.put("orderId", str);
            jSONObject.put("orderTypeCode", str2);
            jSONObject.put("orderType", str3);
            jSONObject.put("payablePrice", str4);
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("back_url", str6);
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("fromActivity", str5);
            }
        }
        return jSONObject;
    }

    private static void setPay(IPay iPay2) {
        if (iPay2 != null) {
            iPay = iPay2;
        }
    }

    public static void showSuccessPage(Activity activity, Bundle bundle, String str, String str2) {
        if (iPay == null || TextUtils.isEmpty(str)) {
            return;
        }
        iPay.showSuccessPage(activity, bundle, str, str2);
    }

    public static void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener) {
        if (iPay == null || TextUtils.isEmpty(str)) {
            return;
        }
        iPay.doPayFinishForward(str, browserCashierUrlListener);
    }

    @Deprecated
    public static void doPay(Activity activity, String str, String str2, String str3, String str4, String str5, PayCallbackListener payCallbackListener) {
        if (activity == null || iPay == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return;
        }
        iPay.doPay(activity, generatePayJDJsonObject(str, str2, str3, str4, "", str5, ""), payCallbackListener);
    }

    @Deprecated
    public static void doPay(Activity activity, String str, String str2, String str3, String str4, String str5) {
        doPay(activity, str, str2, str3, str4, str5, null);
    }

    @Deprecated
    public static void doPay(Activity activity, Bundle bundle, PayCallbackListener payCallbackListener) {
        if (activity == null || bundle == null) {
            return;
        }
        String string = bundle.getString("orderId");
        String string2 = bundle.getString("orderType");
        String string3 = bundle.getString("payablePrice");
        String string4 = bundle.getString("orderTypeCode");
        if (iPay == null || TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return;
        }
        JDJSONObject generatePayJDJsonObject = generatePayJDJsonObject(string, string4, string2, string3, bundle.getString("fromActivity"), bundle.getString("back_url"), bundle.getString("paySourceId"));
        generatePayJDJsonObject.put("riskUrl", (Object) bundle.getString("riskUrl"));
        generatePayJDJsonObject.put("orderDate", (Object) bundle.getString("orderDate"));
        generatePayJDJsonObject.put("isRiskUser", (Object) bundle.getString("isRiskUser"));
        generatePayJDJsonObject.put("appId", (Object) bundle.getString("appId"));
        generatePayJDJsonObject.put("requestCode", (Object) Integer.valueOf(bundle.getInt("requestCode")));
        generatePayJDJsonObject.put("unJieSuan", (Object) bundle.getString("unJieSuan"));
        generatePayJDJsonObject.put("baiTiaoNum", (Object) bundle.getString("baiTiaoNum"));
        generatePayJDJsonObject.put("businessTag", (Object) bundle.getString("businessTag"));
        generatePayJDJsonObject.put("submitOrderExtFlag", (Object) bundle.getString("submitOrderExtFlag"));
        generatePayJDJsonObject.put("isGoodsDetailBaiTiaoFlag", (Object) bundle.getString("isGoodsDetailBaiTiaoFlag"));
        iPay.doPay(activity, generatePayJDJsonObject, payCallbackListener);
    }

    @Deprecated
    public static JSONObject generatePayJsonObject(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            jSONObject.put("orderId", str);
            jSONObject.put("orderTypeCode", str2);
            jSONObject.put("orderType", str3);
            jSONObject.put("payablePrice", str4);
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("back_url", str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("paySourceId", str7);
            } else {
                jSONObject.put("paySourceId", "-1");
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("fromActivity", str5);
            }
        }
        return jSONObject;
    }

    @Deprecated
    public static void doPay(Activity activity, JSONObject jSONObject, PayCallbackListener payCallbackListener) {
        if (activity == null || jSONObject == null) {
            return;
        }
        JDJSONObject parseObject = JDJSON.parseObject(jSONObject.toString());
        String optString = parseObject.optString("orderId");
        String optString2 = parseObject.optString("orderType");
        String optString3 = parseObject.optString("payablePrice");
        if (iPay == null || TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
            return;
        }
        iPay.doPay(activity, parseObject, payCallbackListener);
    }

    public static void doPay(Activity activity, JDJSONObject jDJSONObject, PayCallbackListener payCallbackListener) {
        if (activity == null || jDJSONObject == null) {
            return;
        }
        String optString = jDJSONObject.optString("orderId");
        String optString2 = jDJSONObject.optString("orderType");
        String optString3 = jDJSONObject.optString("payablePrice");
        if (iPay == null || TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
            return;
        }
        iPay.doPay(activity, jDJSONObject, payCallbackListener);
    }

    @Deprecated
    public static void doPay(Activity activity, JSONObject jSONObject, boolean z, PayCallbackListener payCallbackListener) {
        if (activity == null || jSONObject == null) {
            return;
        }
        JDJSONObject parseObject = JDJSON.parseObject(jSONObject.toString());
        String optString = parseObject.optString("orderId");
        String optString2 = parseObject.optString("orderType");
        String optString3 = parseObject.optString("payablePrice");
        if (iPay == null || TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
            return;
        }
        iPay.doPay(activity, parseObject, payCallbackListener);
    }

    public static void doPay(Activity activity, JDJSONObject jDJSONObject, boolean z, PayCallbackListener payCallbackListener) {
        if (activity == null || jDJSONObject == null) {
            return;
        }
        String optString = jDJSONObject.optString("orderId");
        String optString2 = jDJSONObject.optString("orderType");
        String optString3 = jDJSONObject.optString("payablePrice");
        if (iPay == null || TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
            return;
        }
        iPay.doPay(activity, jDJSONObject, payCallbackListener);
    }
}
