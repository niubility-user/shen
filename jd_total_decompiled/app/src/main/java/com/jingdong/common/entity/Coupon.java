package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Coupon implements Serializable {
    public static final int PROMOTION = 0;
    private static final long serialVersionUID = 1173004860606670344L;
    public Integer balance;
    public String message;
    public Integer type;

    public Coupon(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.balance = jSONObjectProxy.getIntOrNull("balance");
        this.type = jSONObjectProxy.getIntOrNull("bankType");
        this.message = jSONObjectProxy.getStringOrNull("message");
    }

    public static ArrayList<Coupon> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<Coupon> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<Coupon> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new Coupon(jSONArrayPoxy.getJSONObject(i3), i2));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("Ware", e.getMessage());
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }
}
