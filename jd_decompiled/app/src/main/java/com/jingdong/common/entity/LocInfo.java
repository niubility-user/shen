package com.jingdong.common.entity;

import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class LocInfo implements Serializable {
    public static final String LOC_TYPE_LOC = "1";
    public static final String LOC_TYPE_NORMAL = "0";
    public static final String LOC_TYPE_ONLINE_EDU = "2";
    public static final int SHOP_INFO = 2;
    public static final int SKU_DETAIL = 1;
    private static final long serialVersionUID = 12343545653125L;
    public String addr;
    public String area;
    public String date;
    public String distances;
    public String fullAddr;
    public boolean isLoc = false;
    public int isLocal = 0;
    public String locGroupId;
    public String locNumStr;
    public String locShopId;
    public String locShopName;
    public String mToBuyUrl;
    public String mToMLink;
    public String phone;
    public String serviceStatus;
    public String shopJson;
    public int status;
    public int venderId;
    public String wareId;

    public LocInfo(JSONObjectProxy jSONObjectProxy, int i2) {
        update(jSONObjectProxy, i2);
    }

    public void update(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy != null) {
            if (OKLog.D) {
                OKLog.d("LocInfo", "jsonObject = " + jSONObjectProxy.toString());
            }
            if (i2 == 1) {
                boolean optBoolean = jSONObjectProxy.optBoolean("isloc");
                this.isLoc = optBoolean;
                if (optBoolean) {
                    this.locGroupId = jSONObjectProxy.getStringOrNull("storeGroupId");
                    this.locNumStr = jSONObjectProxy.getStringOrNull("storeTotal");
                    this.date = jSONObjectProxy.getStringOrNull("expireDate");
                    this.serviceStatus = jSONObjectProxy.getStringOrNull("serviceStatus");
                    this.mToMLink = jSONObjectProxy.getStringOrNull("mLink");
                    this.mToBuyUrl = jSONObjectProxy.getStringOrNull("toBuyUrl");
                }
            } else if (i2 != 2) {
            } else {
                this.shopJson = jSONObjectProxy.toString();
                this.locShopId = jSONObjectProxy.optString("locShopId");
                this.locShopName = jSONObjectProxy.optString("locShopName");
                this.phone = jSONObjectProxy.optString(SignUpTable.TB_COLUMN_PHONE);
                this.addr = jSONObjectProxy.optString("locShopAddr");
                this.fullAddr = jSONObjectProxy.optString("locShopFullAddr");
                this.area = jSONObjectProxy.optString("area");
                this.venderId = jSONObjectProxy.optInt("venderId");
                this.wareId = jSONObjectProxy.optString("wareId");
                this.isLocal = jSONObjectProxy.optInt("isLocal");
                this.distances = jSONObjectProxy.optString("distance");
            }
        }
    }

    public LocInfo() {
    }
}
