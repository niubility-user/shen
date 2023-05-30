package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class Promotion {
    public static final int PROMOTION = 0;
    private static final String TAG = "Promotion";
    public static boolean isValid = true;
    public String catelogyId;
    public String imageUrl;
    public String promotionId;
    public String promotionInfo;
    public String promotionName;

    public Promotion() {
    }

    public static ArrayList<Promotion> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<Promotion> arrayList = null;
        try {
            ArrayList<Promotion> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    Promotion promotion = new Promotion(jSONArrayPoxy.getJSONObject(i3), i2);
                    if (isValid) {
                        arrayList2.add(promotion);
                    }
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }

    public Promotion(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.promotionInfo = jSONObjectProxy.getStringOrNull("promition_info");
        this.catelogyId = jSONObjectProxy.getStringOrNull("catelogyId");
        this.imageUrl = jSONObjectProxy.getStringOrNull("imageUrl");
        this.promotionName = jSONObjectProxy.getStringOrNull("promotion_name");
        String stringOrNull = jSONObjectProxy.getStringOrNull("promotion_id");
        this.promotionId = stringOrNull;
        if (TextUtils.isEmpty(stringOrNull)) {
            isValid = false;
        }
    }
}
