package com.jingdong.common.entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class NewEasyBuyPackSite implements Serializable {
    private static final String TAG = "NewEasyBuyPackSite";
    private static final long serialVersionUID = -8458072461297309249L;
    public String lat;
    public String laty;
    public String lng;
    public String lngx;
    public String showMessage;
    public Integer siteId;
    public String siteName;

    public NewEasyBuyPackSite(JDJSONObject jDJSONObject) {
        try {
            this.siteId = Integer.valueOf(jDJSONObject.optInt("Id"));
            this.siteName = jDJSONObject.optString("Name");
            this.showMessage = jDJSONObject.optString("Address");
            this.lngx = jDJSONObject.optString("lngx");
            this.laty = jDJSONObject.optString("laty");
            this.lng = jDJSONObject.optString(HybridSDK.LNG);
            this.lat = jDJSONObject.optString("lat");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static ArrayList<NewEasyBuyPackSite> parseList(JDJSONArray jDJSONArray) {
        ArrayList<NewEasyBuyPackSite> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new NewEasyBuyPackSite(optJSONObject));
            }
        }
        return arrayList;
    }

    public static ArrayList<NewEasyBuyPackSite> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<NewEasyBuyPackSite> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
            if (jSONObjectOrNull != null) {
                arrayList.add(new NewEasyBuyPackSite(jSONObjectOrNull));
            }
        }
        return arrayList;
    }

    public String getShowMessage() {
        String str = this.showMessage;
        return str == null ? "" : str;
    }

    public Integer getSiteId() {
        Integer num = this.siteId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getSiteName() {
        String str = this.siteName;
        return str == null ? "" : str;
    }

    public NewEasyBuyPackSite(JSONObjectProxy jSONObjectProxy) {
        try {
            this.siteId = jSONObjectProxy.getIntOrNull("Id");
            this.siteName = jSONObjectProxy.getStringOrNull("Name");
            this.showMessage = jSONObjectProxy.getStringOrNull("Address");
            this.lngx = jSONObjectProxy.getStringOrNull("lngx");
            this.laty = jSONObjectProxy.getStringOrNull("laty");
            this.lng = jSONObjectProxy.getStringOrNull(HybridSDK.LNG);
            this.lat = jSONObjectProxy.getStringOrNull("lat");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public NewEasyBuyPackSite() {
    }
}
