package com.jingdong.app.mall.bundle.styleinfoview.entitys.loc;

import android.text.TextUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.corelib.utils.Log;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class LocInfo implements Serializable {
    public static final int SHOP_INFO = 2;
    public static final int SKU_DETAIL = 1;
    private static final long serialVersionUID = 12343545653125L;
    public String addCartText;
    public String area;
    public PDLocBuyStepEntity buyStep;
    public String distance;
    public String expireDate;
    public int isLocal = 0;
    public boolean isNewLoc;
    public boolean isloc;
    public String listText;
    public String locShopAddr;
    public String locShopFullAddr;
    public String locShopId;
    public String locShopName;
    public String mLink;
    public String mta;
    public LocNearBuyInfo nearByLoc;
    public String phone;
    public String pointText;
    public String serviceStatus;
    public String shopJson;
    public int status;
    public String storeGroupId;
    public String storePrice;
    public String storeTotal;
    public String toBuyUrl;
    public int venderId;
    public String wareId;

    public LocInfo(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }

    public String buildLocBuyUrl(String str, int i2) {
        if (TextUtils.isEmpty(this.toBuyUrl)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(this.toBuyUrl);
        sb.append("wareId=" + str);
        sb.append("&wareNum=" + i2);
        sb.append("&enterOrder=true");
        sb.append("&locType=" + this.serviceStatus);
        return sb.toString();
    }

    public String buildLocShopUrl(boolean z, String str, String str2, String str3) {
        if (TextUtils.isEmpty(this.mLink)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(this.mLink);
        sb.append("storeGroupId=" + this.storeGroupId);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("&isLocal=");
        sb2.append(z ? "1" : "0");
        sb.append(sb2.toString());
        sb.append("&wareId=" + str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append("&locShopId=" + str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("&new_areas=" + str3);
        }
        sb.append("&source=APPSku");
        if (Log.D) {
            Log.d("xyl-----", "Loc\u5546\u54c1\u8df3\u8f6c\u95e8\u5e97\u5217\u8868url = " + sb.toString());
        }
        return sb.toString();
    }

    public String getJsonString() {
        return this.shopJson;
    }

    public void update(JDJSONObject jDJSONObject, int i2) {
        if (jDJSONObject != null) {
            if (Log.D) {
                Log.d("LocInfo", "jsonObject = " + jDJSONObject.toString());
            }
            if (i2 == 1) {
                boolean optBoolean = jDJSONObject.optBoolean("isloc");
                this.isloc = optBoolean;
                if (optBoolean) {
                    this.storeGroupId = jDJSONObject.getString("storeGroupId");
                    this.storeTotal = jDJSONObject.getString("storeTotal");
                    this.expireDate = jDJSONObject.getString("expireDate");
                    this.serviceStatus = jDJSONObject.getString("serviceStatus");
                    this.mLink = jDJSONObject.getString("mLink");
                    this.toBuyUrl = jDJSONObject.getString("toBuyUrl");
                    this.addCartText = jDJSONObject.optString("addCartText");
                    this.mta = jDJSONObject.optString(IExceptionHandler.DynamicExceptionData.TYPE_MTA);
                }
            } else if (i2 != 2) {
            } else {
                this.shopJson = jDJSONObject.toString();
                this.locShopId = jDJSONObject.optString("locShopId");
                this.locShopName = jDJSONObject.optString("locShopName");
                this.phone = jDJSONObject.optString(SignUpTable.TB_COLUMN_PHONE);
                this.locShopAddr = jDJSONObject.optString("locShopAddr");
                this.locShopFullAddr = jDJSONObject.optString("locShopFullAddr");
                this.area = jDJSONObject.optString("area");
                this.venderId = jDJSONObject.optInt("venderId");
                this.wareId = jDJSONObject.optString("wareId");
                this.isLocal = jDJSONObject.optInt("isLocal");
                this.distance = jDJSONObject.optString("distance");
                this.storePrice = jDJSONObject.optString("storePrice");
            }
        }
    }

    public LocInfo() {
    }
}
