package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

/* loaded from: classes19.dex */
public class DolphinAdvMtaEntity extends DolphinMtaEntity {
    public static final String EVENT_ID_MZHTLC = "mzhtlc";
    public static final String EVENT_ID_MZZBLC = "mzzblc";
    public static final String PREFIX_DEV_ADV = " Babel_dev_adv_";
    public static final String PREFIX_DEV_SKU = "Babel_dev_sku_";
    public static final String PREFIX_EXPO_ADV = "Babel_dev_expo_adv_";
    public static final String PREFIX_EXPO_SKU = "Babel_dev_expo_sku_";
    public String adid = "\"\"";
    public String agid = "\"\"";

    public String makeAdvExpoJson() {
        return "{ \"aid\":" + this.aid + ", \"adid\":" + this.adid + ", \"agid\":" + this.agid + ", \"fno\":" + this.fno + ", \"mid\":" + this.mid + " }";
    }

    public String makeAvdJson() {
        return makeAdvExpoJson();
    }
}
