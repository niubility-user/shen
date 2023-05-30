package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.widget.custom.liveplayer.util.TemplateFlagKt;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class Image implements Serializable {
    public static final int PRODUCTDETAIL = 0;
    public static final int PRODUCT_DETAIL_NEW = 1;
    public String big;
    public String small;

    public Image(String str, String str2) {
        this.small = str;
        this.big = str2;
    }

    public static LinkedList<Image> toList(JSONArray jSONArray, int i2) {
        LinkedList<Image> linkedList = null;
        if (jSONArray == null) {
            return null;
        }
        try {
            LinkedList<Image> linkedList2 = new LinkedList<>();
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                try {
                    linkedList2.add(new Image(jSONArray.getJSONObject(i3), i2));
                } catch (JSONException e2) {
                    e = e2;
                    linkedList = linkedList2;
                    if (OKLog.V) {
                        OKLog.v(Image.class.getName(), e.getMessage());
                    }
                    return linkedList;
                }
            }
            return linkedList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        try {
            if (i2 == 0) {
                this.small = jDJSONObject.getString("newpath");
                this.big = jDJSONObject.getString("bigpath");
            } else if (i2 != 1) {
            } else {
                this.small = jDJSONObject.getString(TemplateFlagKt.KEY_SMALL);
                this.big = jDJSONObject.getString("big");
            }
        } catch (Exception e2) {
            if (OKLog.V) {
                OKLog.v(Image.class.getName(), e2.getMessage());
            }
        }
    }

    public Image(JSONObject jSONObject, int i2) {
        if (jSONObject == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObject.toString()), i2);
    }

    public Image(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
