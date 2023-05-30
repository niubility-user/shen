package com.jingdong.common.sample.jshop.Entity;

import com.jingdong.common.database.table.CommentEditTable;
import com.jingdong.jdsdk.constant.JshopConst;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JShopInfo {
    public String curPage;
    public String followCount;
    public int followCountNum;
    public boolean isFollowed;
    public String labelName;
    public String logoUrl;
    public String param;
    public double score;
    public long shopId;
    public String shopIds;
    public String shopName;
    public long venderId;
    public String venderIds;

    public JShopInfo() {
    }

    public JShopInfo(JSONObject jSONObject) {
        this.logoUrl = jSONObject.optString("logoUrl");
        this.followCount = jSONObject.optString(JshopConst.JSKEY_FLW_COUNT);
        this.score = jSONObject.optDouble(CommentEditTable.TB_COLUMN_SCORE);
        this.shopName = jSONObject.optString("shopName");
        this.venderId = jSONObject.optLong("venderId");
        this.shopId = jSONObject.optLong("shopId");
        this.isFollowed = jSONObject.optBoolean(JshopConst.FOLLOWED_KEY, false);
    }
}
