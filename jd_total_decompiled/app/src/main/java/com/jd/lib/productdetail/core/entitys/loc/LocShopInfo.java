package com.jd.lib.productdetail.core.entitys.loc;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes15.dex */
public class LocShopInfo {
    public String distance;
    public String distanceImage;
    public boolean isNeedLocShopId;
    public String locShopAddr;
    public String locShopBusinessTime;
    public String locShopId;
    public String locShopLatitude;
    public String locShopLongitude;
    public String locShopName;
    public String locShopNavigationImage;
    public String locShopNavigationLink;
    public String locShopNavigationText;
    public String locShopPhone;
    public String locShopPhoneImage;
    public String locShopPhoneText;
    public boolean newWinPageFlag;
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
