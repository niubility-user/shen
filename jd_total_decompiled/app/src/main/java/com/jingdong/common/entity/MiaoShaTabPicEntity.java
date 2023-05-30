package com.jingdong.common.entity;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MiaoShaTabPicEntity {
    public int id;
    public String jumpUrl;
    public String operateImg;
    public int sortno;
    public String sourceValue;

    public MiaoShaTabPicEntity() {
    }

    public static List<MiaoShaTabPicEntity> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList arrayList = new ArrayList();
        int length = jSONArrayPoxy.length();
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(new MiaoShaTabPicEntity(jSONArrayPoxy.getJSONObjectOrNull(i2)));
        }
        return arrayList;
    }

    public MiaoShaTabPicEntity(JSONObjectProxy jSONObjectProxy) {
        this.id = jSONObjectProxy.optInt("id");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.sortno = jSONObjectProxy.optInt("sortno");
        this.jumpUrl = jSONObjectProxy.optString(CartConstant.KEY_JUMPURL);
        this.operateImg = jSONObjectProxy.optString("operateImg");
    }
}
