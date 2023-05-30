package com.jingdong.app.mall.bundle.styleinfoview.entitys.loc;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes3.dex */
public class LocShopInfo {
    public String distance;
    public boolean isNeedLocShopId;
    public String locShopAddr;
    public String locShopId;
    public String locShopName;
    public String recommendIconUrl;
    public String winPageUrl;

    public void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.locShopId = jDJSONObject.optString("locShopId");
            this.locShopName = jDJSONObject.optString("locShopName");
            this.locShopAddr = jDJSONObject.optString("locShopAddr");
            this.distance = jDJSONObject.optString("distance");
            this.recommendIconUrl = jDJSONObject.optString("recommendIconUrl");
            this.winPageUrl = jDJSONObject.optString("winPageUrl");
        }
    }
}
