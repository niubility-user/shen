package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

/* loaded from: classes19.dex */
public class DolphinMtaEntity {
    public String eventIdSuffix;
    public String fno = "\"\"";
    public String mid = "\"\"";
    public String sgid = "\"\"";
    public String aid = "\"\"";
    public String sku = "\"\"";

    public String getEventParam() {
        return "{\"aid\":" + this.aid + ",\"sku\":" + this.sku + ",\"sgid\":" + this.sgid + ",\"fno\":" + this.fno + ",\"mid\":" + this.mid + "}";
    }
}
