package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Province implements Serializable {
    public static final int CONSTRUCTOR = 0;
    private static final long serialVersionUID = 1654861964412250256L;
    public String provinceID;
    public String provinceName;

    public Province(JSONObjectProxy jSONObjectProxy, int i2) {
        if (i2 != 0) {
            return;
        }
        this.provinceID = jSONObjectProxy.getStringOrNull(Constant.KEY_PROMOTION_LABEL);
        this.provinceName = jSONObjectProxy.getStringOrNull("value");
    }

    public static ArrayList<Province> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<Province> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
            try {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(new Province(jSONArrayPoxy.getJSONObject(i3), i2));
            } catch (JSONException e2) {
                if (OKLog.V) {
                    OKLog.v("SkuColor", e2.getMessage());
                }
            }
        }
        return arrayList;
    }
}
