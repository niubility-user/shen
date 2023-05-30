package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class PromotionMessage {
    public static final int PROMOTIONMSG = 0;
    private static final String TAG = "PromotionMessage";
    private static boolean isValid = true;
    public String activity_id;
    public String catelogyId;
    public String content;
    public String endDate;
    public String imageUrl;
    public String startDate;
    public String title;

    public PromotionMessage() {
    }

    public static ArrayList<PromotionMessage> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<PromotionMessage> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<PromotionMessage> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    PromotionMessage promotionMessage = new PromotionMessage(jSONArrayPoxy.getJSONObject(i3), i2);
                    if (isValid) {
                        arrayList2.add(promotionMessage);
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

    public synchronized boolean getValid() {
        return isValid;
    }

    public synchronized void setValid(boolean z) {
        isValid = z;
    }

    public PromotionMessage(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.activity_id = jSONObjectProxy.getStringOrNull("activity_id");
        this.title = jSONObjectProxy.getStringOrNull("title");
        this.startDate = jSONObjectProxy.getStringOrNull("start_date");
        this.endDate = jSONObjectProxy.getStringOrNull("end_date");
        this.imageUrl = jSONObjectProxy.getStringOrNull("imageUrl");
        this.catelogyId = jSONObjectProxy.getStringOrNull("catelogyId");
        this.content = jSONObjectProxy.getStringOrNull("content");
        if (TextUtils.isEmpty(this.activity_id)) {
            setValid(false);
        }
    }
}
