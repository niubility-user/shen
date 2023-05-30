package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class JdOrderStat implements Serializable {
    private static final String TAG = "JdOrderStat";
    private static final long serialVersionUID = 1;
    public String count;
    public int direct;
    public String functionId;
    public String icon;
    public String name;
    public String type;
    public String url;

    public JdOrderStat() {
    }

    public static ArrayList<JdOrderStat> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<JdOrderStat> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<JdOrderStat> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    arrayList2.add(new JdOrderStat(jSONArrayPoxy.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.E) {
                        OKLog.e("Ware", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public String toString() {
        return "JdOrderStat [icon=" + this.icon + ", functionId=" + this.functionId + ", count=" + this.count + ", url=" + this.url + ", name=" + this.name + "]";
    }

    public JdOrderStat(JSONObjectProxy jSONObjectProxy) {
        try {
            this.icon = jSONObjectProxy.getString("icon");
            this.functionId = jSONObjectProxy.getString("functionId");
            this.count = jSONObjectProxy.getString("count");
            this.url = jSONObjectProxy.getString("url");
            this.name = jSONObjectProxy.getString("name");
            this.type = jSONObjectProxy.optString("type", "");
            this.direct = jSONObjectProxy.optInt("direct", 0);
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }
}
