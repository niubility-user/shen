package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class WareType implements Serializable {
    private static final long serialVersionUID = 1;
    public String description;
    public String iconUrl;
    public String name;
    public String needDrawRect;
    public String type;

    public WareType() {
    }

    public static ArrayList<WareType> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<WareType> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<WareType> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    arrayList2.add(new WareType(jSONArrayPoxy.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("WareType", e.getMessage());
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public WareType(JSONObjectProxy jSONObjectProxy) {
        this.name = jSONObjectProxy.getStringOrNull("name");
        this.description = jSONObjectProxy.getStringOrNull("description");
        this.iconUrl = jSONObjectProxy.getStringOrNull("icon");
        this.type = jSONObjectProxy.getStringOrNull("type");
        this.needDrawRect = jSONObjectProxy.getStringOrNull("needDrawRect");
    }
}
