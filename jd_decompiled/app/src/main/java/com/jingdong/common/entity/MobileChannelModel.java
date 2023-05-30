package com.jingdong.common.entity;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MobileChannelModel {
    public static final String TYPE_CHANNEL_CATEGORY = "Category";
    public static final String TYPE_CHANNEL_CATEGORYGOODS = "CategoryGoods";
    public static final String TYPE_CHANNEL_CATEGORYGOODS2 = "CategoryGoods2";
    public static final String TYPE_CHANNEL_FOCUS = "Focus";
    public static final String TYPE_CHANNEL_IMAGEGROUP = "Hybrid";
    public static final String TYPE_CHANNEL_SNATCHGOODS = "SnatchGoods";
    public static final String TYPE_CHANNEL_VALIDITY = "MobileChannelValidity";
    private JSONArrayPoxy dataList;
    public String jumpTo;
    public int jumpType;
    public String jumpUrl;
    public String param;
    public String rightCorner;
    public String sourceValue;
    public int space;
    public String title;
    public String type;

    public MobileChannelModel() {
    }

    public static ArrayList<MobileChannelModel> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<MobileChannelModel> arrayList = new ArrayList<>();
        if (jSONArrayPoxy != null && jSONArrayPoxy.length() > 0) {
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
                if (jSONObjectOrNull != null) {
                    arrayList.add(new MobileChannelModel(jSONObjectOrNull));
                }
            }
        }
        return arrayList;
    }

    public JSONArrayPoxy getDataList() {
        return this.dataList;
    }

    public MobileChannelModel(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        this.type = jSONObjectProxy.optString("type", "");
        this.rightCorner = jSONObjectProxy.optString("rightCorner", "");
        this.title = jSONObjectProxy.optString("title", "");
        this.dataList = jSONObjectProxy.getJSONArrayOrNull("itemList");
        this.space = jSONObjectProxy.optInt("space");
        this.jumpType = jSONObjectProxy.optInt("jumpType");
        this.jumpUrl = jSONObjectProxy.optString(CartConstant.KEY_JUMPURL);
        this.jumpTo = jSONObjectProxy.optString("jumpTo");
        this.param = jSONObjectProxy.optString("param");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
    }
}
