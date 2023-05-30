package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class ChannelTabInfo extends AbstractTabInfo {
    public String categoryActivationText;
    public String categoryActivationUrl;
    public int categoryKind;
    public String color;
    public int isCategoryActivatedUser;
    public String name;
    public long tagEndTime;
    public long tagStartTime;
    public String tagText;
    public int tagType;

    public ChannelTabInfo() {
    }

    public static ArrayList<ChannelTabInfo> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<ChannelTabInfo> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<ChannelTabInfo> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList2.add(new ChannelTabInfo(jSONArrayPoxy.getJSONObject(i2)));
                    }
                } catch (JSONException unused) {
                    arrayList = arrayList2;
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException unused2) {
        }
    }

    public ChannelTabInfo(JSONObjectProxy jSONObjectProxy) {
        this.name = jSONObjectProxy.optString("name", "");
        this.id = jSONObjectProxy.optInt("id");
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
        this.tagType = jSONObjectProxy.optInt("tagType");
        this.tagText = jSONObjectProxy.optString("tagText", "");
        this.tagStartTime = jSONObjectProxy.optLong("tagStartTime");
        this.tagEndTime = jSONObjectProxy.optLong("tagEndTime");
        this.isCategoryActivatedUser = jSONObjectProxy.optInt("isCategoryActivatedUser");
        this.categoryActivationText = jSONObjectProxy.optString("categoryActivationText");
        this.categoryActivationUrl = jSONObjectProxy.optString("categoryActivationUrl");
        this.categoryKind = jSONObjectProxy.optInt("categoryKind");
    }
}
