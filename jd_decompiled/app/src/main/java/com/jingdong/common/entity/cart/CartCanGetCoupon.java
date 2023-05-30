package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartCanGetCoupon {
    public String appid;
    public String batchId;
    public String biinfo;
    public String btActivityId;
    public String channelDetail;
    public Integer chufangYao;
    public String couponId;
    public String couponSource;
    public String couponSourceDetail;
    public int couponStyle;
    public int couponType;
    public int couponUse;
    public String key;
    public String platformid;
    public String ruleId;
    public String skus;

    public CartCanGetCoupon() {
    }

    private String parseArray(JDJSONArray jDJSONArray) {
        StringBuilder sb = new StringBuilder();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                String optString = jDJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    if (sb.length() > 0) {
                        sb.append("#");
                    }
                    sb.append(optString);
                }
            }
        }
        return sb.length() <= 0 ? "-100" : sb.toString();
    }

    public static ArrayList<CartCanGetCoupon> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartCanGetCoupon> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new CartCanGetCoupon(optJSONObject));
            }
        }
        return arrayList;
    }

    public static JSONArray toSummaryParams(ArrayList<CartCanGetCoupon> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        int size = arrayList.size();
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                CartCanGetCoupon cartCanGetCoupon = arrayList.get(i2);
                if (cartCanGetCoupon != null) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(cartCanGetCoupon.ruleId)) {
                        jSONObject.put("ruleId", cartCanGetCoupon.ruleId);
                    }
                    if (!TextUtils.isEmpty(cartCanGetCoupon.key)) {
                        jSONObject.put("key", cartCanGetCoupon.key);
                    }
                    if (!TextUtils.isEmpty(cartCanGetCoupon.couponId)) {
                        jSONObject.put("couponId", cartCanGetCoupon.couponId);
                    }
                    if (!TextUtils.isEmpty(cartCanGetCoupon.batchId)) {
                        jSONObject.put(JshopConst.JSKEY_BATCH_ID, cartCanGetCoupon.batchId);
                    }
                    if (!TextUtils.isEmpty(cartCanGetCoupon.btActivityId)) {
                        jSONObject.put("btActivityId", cartCanGetCoupon.btActivityId);
                    }
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray;
    }

    public CartCanGetCoupon(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.couponId = jDJSONObject.optString("couponId");
        this.batchId = jDJSONObject.optString(JshopConst.JSKEY_BATCH_ID);
        this.ruleId = jDJSONObject.optString("ruleId");
        this.key = jDJSONObject.optString("key");
        this.couponUse = jDJSONObject.optInt("couponUse");
        this.chufangYao = Integer.valueOf(jDJSONObject.optInt("chufangYao"));
        this.btActivityId = jDJSONObject.optString("btActivityId");
        this.couponType = jDJSONObject.optInt(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE);
        this.couponStyle = jDJSONObject.optInt("couponStyle");
        this.couponSource = jDJSONObject.optString("couponSource", "");
        this.couponSourceDetail = jDJSONObject.optString("couponSourceDetail", "");
        this.channelDetail = jDJSONObject.optString("channelDetail", "");
        this.biinfo = jDJSONObject.optString("biinfo", "");
        this.appid = jDJSONObject.optString("appid", "");
        this.platformid = jDJSONObject.optString("platformid", "");
        this.skus = parseArray(jDJSONObject.optJSONArray("skus"));
    }
}
