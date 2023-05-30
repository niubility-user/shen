package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SkuSize implements Serializable {
    public static final int CONSTRUCTOR = 0;
    private static final long serialVersionUID = -2367217923502645109L;
    public Boolean directShow;
    public Boolean promotion;
    public String sizeName;
    public Long wareId;

    public SkuSize(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public static ArrayList<SkuSize> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2);
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 != 0) {
            return;
        }
        this.directShow = jDJSONObject.getBoolean("directShow");
        this.promotion = jDJSONObject.getBoolean("promotion");
        this.sizeName = jDJSONObject.getString(ApkDownloadTable.FIELD_SIZE);
        this.wareId = jDJSONObject.getLong("wareId");
    }

    public Boolean getDirectShow() {
        Boolean bool = this.directShow;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getPromotion() {
        Boolean bool = this.promotion;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getSizeName() {
        String str = this.sizeName;
        return str == null ? "" : str;
    }

    public static ArrayList<SkuSize> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<SkuSize> arrayList = null;
        if (jDJSONArray == null) {
            return null;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(new SkuSize(jDJSONArray.getJSONObject(i3), i2));
            } catch (Exception e2) {
                if (OKLog.V) {
                    OKLog.v("SkuSize", e2.getMessage());
                }
            }
        }
        return arrayList;
    }

    public SkuSize(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
