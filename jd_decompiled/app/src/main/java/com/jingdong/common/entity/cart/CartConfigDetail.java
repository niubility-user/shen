package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;
import java.util.Set;

/* loaded from: classes5.dex */
public class CartConfigDetail {
    public static final String KEY_TEXT_INFO = "textInfo";
    public volatile SubCartJumpConfig cartJumpConfig;
    public volatile String cartShareUrl;
    public volatile EggConfigInfo eggConfig;
    public volatile EggConfigInfo eggCouponNumConfig;
    public volatile String giftJumpOn;
    public volatile String giftPackingAlertContent;
    public volatile String giftPackingAlertTitle;
    public volatile HashMap<String, String> jingEggStrongDateConfig;
    public volatile String newUserNoFreight;
    public volatile String nomainlandFreight;
    public volatile String normalWeightFreightMsg;
    public volatile String normalWeightFreightMsgNew;
    public volatile String overWeightFreightMsg;
    public volatile String sevenFreshArea;
    public volatile String studentFreeFreightMsg;
    public volatile HashMap<String, String> textInfoMap;

    public static JDJSONObject mergeDetail(JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
        if (jDJSONObject != null && jDJSONObject2 != null) {
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("config");
            JDJSONObject jSONObject2 = jDJSONObject2.getJSONObject("config");
            if (jSONObject != null && jSONObject2 != null) {
                Set<String> keySet = jSONObject2.keySet();
                if (keySet != null && keySet.size() > 0) {
                    for (String str : keySet) {
                        if (str.equals(KEY_TEXT_INFO)) {
                            JDJSONObject optJSONObject = jSONObject.optJSONObject(KEY_TEXT_INFO);
                            if (optJSONObject == null) {
                                optJSONObject = new JDJSONObject();
                            }
                            JDJSONObject optJSONObject2 = jSONObject2.optJSONObject(KEY_TEXT_INFO);
                            if (optJSONObject2 != null) {
                                for (String str2 : optJSONObject2.keySet()) {
                                    if (!TextUtils.isEmpty(str2)) {
                                        String optString = optJSONObject2.optString(str2);
                                        if (!TextUtils.isEmpty(optString)) {
                                            optJSONObject.put(str2, (Object) optString);
                                        }
                                    }
                                }
                            }
                            if (optJSONObject.size() > 0) {
                                jSONObject.put(KEY_TEXT_INFO, (Object) optJSONObject);
                            }
                        } else {
                            jSONObject.put(str, jSONObject2.get(str));
                        }
                    }
                }
                jDJSONObject2.put("config", (Object) jSONObject);
            }
        }
        return jDJSONObject2;
    }

    public static CartConfigDetail parseDetail(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartConfigDetail cartConfigDetail = new CartConfigDetail();
        cartConfigDetail.eggConfig = EggConfigInfo.parseData(jDJSONObject.optJSONObject("jingEggDateConfig"));
        cartConfigDetail.eggCouponNumConfig = EggConfigInfo.parseData(jDJSONObject.optJSONObject("jingEggCouponNumConfig"));
        cartConfigDetail.cartJumpConfig = new SubCartJumpConfig(jDJSONObject.optJSONObject("subCartJumpInfo"));
        cartConfigDetail.nomainlandFreight = jDJSONObject.optString("nomainlandFreight");
        cartConfigDetail.overWeightFreightMsg = jDJSONObject.optString("overWeightFreightMsg");
        cartConfigDetail.normalWeightFreightMsg = jDJSONObject.optString("normalWeightFreightMsg");
        cartConfigDetail.normalWeightFreightMsgNew = jDJSONObject.optString("normalWeightFreightMsgNew");
        cartConfigDetail.newUserNoFreight = jDJSONObject.optString("newUserNoFreight");
        cartConfigDetail.studentFreeFreightMsg = jDJSONObject.optString("studentFreeFreightMsg");
        cartConfigDetail.giftPackingAlertTitle = jDJSONObject.optString("giftPackingAlertTitle");
        cartConfigDetail.giftPackingAlertContent = jDJSONObject.optString("giftPackingAlertContent");
        cartConfigDetail.giftJumpOn = jDJSONObject.optString("giftJumpOn");
        cartConfigDetail.cartShareUrl = jDJSONObject.optString("cartShareUrl");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject(KEY_TEXT_INFO);
        cartConfigDetail.textInfoMap = new HashMap<>();
        if (optJSONObject != null) {
            for (String str : optJSONObject.keySet()) {
                if (!TextUtils.isEmpty(str)) {
                    String optString = optJSONObject.optString(str);
                    if (!TextUtils.isEmpty(optString)) {
                        cartConfigDetail.textInfoMap.put(str, optString);
                    }
                }
            }
        }
        cartConfigDetail.sevenFreshArea = jDJSONObject.optString("7freshArea");
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("jingEggStrongDateConfig");
        cartConfigDetail.jingEggStrongDateConfig = new HashMap<>();
        if (optJSONObject2 != null) {
            for (String str2 : optJSONObject2.keySet()) {
                if (!TextUtils.isEmpty(str2)) {
                    String optString2 = optJSONObject2.optString(str2);
                    if (!TextUtils.isEmpty(optString2)) {
                        cartConfigDetail.jingEggStrongDateConfig.put(str2, optString2);
                    }
                }
            }
        }
        return cartConfigDetail;
    }
}
