package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.manto.sdk.api.IRequestPayment;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class RecentUnfinishedOrder {
    public String buttonHidden;
    private ArrayList<String> imageurl;
    public String orderId;
    public String orderStatus;
    public String orderStatusName;
    public String paymentType;
    private YushouOrder yushouOrder;

    public static ArrayList<RecentUnfinishedOrder> toList(JSONArrayPoxy jSONArrayPoxy) throws JSONException {
        JSONArrayPoxy jSONArrayOrNull;
        ArrayList<RecentUnfinishedOrder> arrayList = new ArrayList<>();
        int length = jSONArrayPoxy.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
            RecentUnfinishedOrder recentUnfinishedOrder = new RecentUnfinishedOrder();
            recentUnfinishedOrder.orderId = jSONObject.optString("orderId");
            recentUnfinishedOrder.orderStatus = jSONObject.optString("orderStatus");
            recentUnfinishedOrder.orderStatusName = jSONObject.optString("orderStatusName");
            recentUnfinishedOrder.paymentType = jSONObject.optString(IRequestPayment.V_PAY_TYPE);
            recentUnfinishedOrder.buttonHidden = jSONObject.optString("buttonHidden", "");
            recentUnfinishedOrder.yushouOrder = YushouOrder.fromJson(jSONObject);
            JSONObjectProxy jSONObjectOrNull = jSONObject.getJSONObjectOrNull("orderMsg");
            if (jSONObjectOrNull != null && (jSONArrayOrNull = jSONObjectOrNull.getJSONArrayOrNull("wareInfoList")) != null && jSONArrayOrNull.length() > 0) {
                int length2 = jSONArrayOrNull.length();
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i3 = 0; i3 < length2; i3++) {
                    arrayList2.add(jSONArrayOrNull.getJSONObjectOrNull(i3).optString("imageurl"));
                }
                recentUnfinishedOrder.setImageurl(arrayList2);
            }
            arrayList.add(recentUnfinishedOrder);
        }
        return arrayList;
    }

    public ArrayList<String> getImageurl() {
        return this.imageurl;
    }

    public YushouOrder getYushouOrder() {
        return this.yushouOrder;
    }

    public void setImageurl(ArrayList<String> arrayList) {
        this.imageurl = arrayList;
    }
}
