package com.jingdong.common.shop;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes6.dex */
public class JshopEnterParam {
    public static String getJumpTab(Bundle bundle) {
        String string = bundle.getString("jumpTab", "");
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString(JshopConst.JSHOP_TARGET_PAGE, "");
        }
        return TextUtils.isEmpty(string) ? "home" : string;
    }

    public static String getShopId(Bundle bundle) {
        JDJSONObject parseObject;
        String string = bundle.getString("shopId");
        if (TextUtils.isEmpty(string)) {
            String string2 = bundle.getString(JshopConst.INTENT_BRAND_JSON);
            if (!TextUtils.isEmpty(string2) && (parseObject = JDJSON.parseObject(string2)) != null) {
                string = parseObject.optString("shopId");
            }
        }
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString("shopid");
        }
        return TextUtils.isEmpty(string) ? bundle.getString("shopID") : string;
    }

    public static String getVenderId(Bundle bundle) {
        JDJSONObject parseObject;
        String string = bundle.getString("venderId");
        if (TextUtils.isEmpty(string)) {
            String string2 = bundle.getString(JshopConst.INTENT_BRAND_JSON);
            if (!TextUtils.isEmpty(string2) && (parseObject = JDJSON.parseObject(string2)) != null) {
                string = parseObject.optString("venderId");
            }
        }
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString("venderid");
        }
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString("vendorId");
        }
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString("vendorid");
        }
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString("venderID");
        }
        return TextUtils.isEmpty(string) ? bundle.getString("vendorID") : string;
    }

    public static boolean isClose(Bundle bundle) {
        return bundle != null && TextUtils.equals(bundle.getString("operation", ""), "close");
    }
}
