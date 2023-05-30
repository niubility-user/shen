package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class JDColorProductModel implements Serializable {
    private static final String TAG = "JDColorProductModel";
    private static final long serialVersionUID = 1;
    private String cateId;
    private String colorValue;
    private String id;
    private String imageUrl;

    public JDColorProductModel(JSONObjectProxy jSONObjectProxy) {
        try {
            setId(jSONObjectProxy.getStringOrNull("wareId"));
            setImageUrl(jSONObjectProxy.getStringOrNull("imageUrl"));
            setCateId(jSONObjectProxy.getStringOrNull(DeepLinkRankHelper.CATE_ID));
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static ArrayList<JDColorProductModel> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<JDColorProductModel> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
                if (jSONObject != null) {
                    arrayList.add(new JDColorProductModel(jSONObject));
                }
            } catch (JSONException e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return arrayList;
    }

    public String getCateId() {
        return TextUtils.isEmpty(this.cateId) ? "" : this.cateId;
    }

    public String getColorValue() {
        return TextUtils.isEmpty(this.colorValue) ? "" : this.colorValue;
    }

    public String getId() {
        return TextUtils.isEmpty(this.id) ? "" : this.id;
    }

    public String getImageUrl() {
        return TextUtils.isEmpty(this.imageUrl) ? "" : this.imageUrl;
    }

    public void setCateId(String str) {
        this.cateId = str;
    }

    public void setColorValue(String str) {
        this.colorValue = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public JDColorProductModel(JSONObjectProxy jSONObjectProxy, String str) {
        try {
            setId(jSONObjectProxy.getStringOrNull("wareId"));
            setImageUrl(jSONObjectProxy.getStringOrNull("imageUrl"));
            setCateId(jSONObjectProxy.getStringOrNull(DeepLinkRankHelper.CATE_ID));
            setColorValue(str);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static ArrayList<JDColorProductModel> toList(JSONArrayPoxy jSONArrayPoxy, String str) {
        ArrayList<JDColorProductModel> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
                if (jSONObject != null) {
                    arrayList.add(new JDColorProductModel(jSONObject, str));
                }
            } catch (JSONException e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return arrayList;
    }
}
