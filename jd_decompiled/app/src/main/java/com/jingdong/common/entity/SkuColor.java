package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SkuColor implements Serializable {
    public static final int CONSTRUCTOR = 0;
    private static final long serialVersionUID = -1953037815425591903L;
    public String colorImg;
    public String colorName;
    public Boolean promotion;
    public Long wareId;
    public String wareName;
    public String wareTitle;

    public SkuColor(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public static ArrayList<SkuColor> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2);
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 != 0) {
            return;
        }
        this.colorName = jDJSONObject.getString("color");
        this.colorImg = jDJSONObject.getString("colorImg");
        this.promotion = jDJSONObject.getBoolean("promotion");
        this.wareId = jDJSONObject.getLong("wareId");
        this.wareTitle = jDJSONObject.getString("waretitle");
        this.wareName = jDJSONObject.getString("wname");
    }

    public String getColorImg() {
        String str = this.colorImg;
        return str == null ? "" : str;
    }

    public String getColorName() {
        String str = this.colorName;
        return str == null ? "" : str;
    }

    public Boolean getPromotion() {
        Boolean bool = this.promotion;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Long getWareId() {
        Long l2 = this.wareId;
        return Long.valueOf(l2 == null ? 0L : l2.longValue());
    }

    public String getWareName() {
        String str = this.wareName;
        return str == null ? "" : str;
    }

    public String getWareTitle() {
        String str = this.wareTitle;
        return str == null ? "" : str;
    }

    public static ArrayList<SkuColor> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<SkuColor> arrayList = null;
        if (jDJSONArray == null) {
            return null;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(new SkuColor(jDJSONArray.getJSONObject(i3), i2));
            } catch (Exception e2) {
                if (OKLog.V) {
                    OKLog.v("SkuColor", e2.getMessage());
                }
            }
        }
        return arrayList;
    }

    public SkuColor(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
