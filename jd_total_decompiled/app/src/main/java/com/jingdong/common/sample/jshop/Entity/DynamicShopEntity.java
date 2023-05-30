package com.jingdong.common.sample.jshop.Entity;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class DynamicShopEntity implements Serializable {
    private static final long serialVersionUID = 7874205397829876784L;
    JSONObject activity;
    public int activitySrc;
    public boolean hadPraised;
    public boolean hasArrow;
    public boolean isConcern;
    public String logoUrl;
    public JSONArray newBanners;
    public boolean newComer;
    public long praiseCount;
    public long shopId;
    public String shopName;
    public String shopType;
    public String shopTypeDes;
    public String timeStamp;
    public long venderId;
    public long viewCount;
    private String mOldTimeStamp = "";
    private int mCurActivitySrc = 1;
    public boolean isShowTitle = false;
    public boolean isShowRecDivider = false;
    public int curTab = 2;

    public JSONObject getActivity() {
        return this.activity;
    }

    public void setActivity(JSONObject jSONObject) {
        this.activity = jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00e4 A[Catch: Exception -> 0x00ed, TryCatch #0 {Exception -> 0x00ed, blocks: (B:10:0x001d, B:12:0x0023, B:14:0x0029, B:16:0x00a9, B:18:0x00b4, B:20:0x00ba, B:22:0x00c6, B:26:0x00d1, B:28:0x00dc, B:30:0x00e4, B:31:0x00e9, B:27:0x00da, B:17:0x00ac), top: B:35:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e9 A[Catch: Exception -> 0x00ed, TRY_LEAVE, TryCatch #0 {Exception -> 0x00ed, blocks: (B:10:0x001d, B:12:0x0023, B:14:0x0029, B:16:0x00a9, B:18:0x00b4, B:20:0x00ba, B:22:0x00c6, B:26:0x00d1, B:28:0x00dc, B:30:0x00e4, B:31:0x00e9, B:27:0x00da, B:17:0x00ac), top: B:35:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ArrayList<DynamicShopEntity> toVendorList(JSONArray jSONArray) {
        ArrayList<DynamicShopEntity> arrayList = new ArrayList<>();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject != null && jSONObject.length() > 0) {
                        DynamicShopEntity dynamicShopEntity = new DynamicShopEntity();
                        dynamicShopEntity.shopType = jSONObject.optString("shopType");
                        dynamicShopEntity.shopTypeDes = jSONObject.optString("shopTypeDes");
                        dynamicShopEntity.timeStamp = jSONObject.optString("timeStamp");
                        dynamicShopEntity.shopName = jSONObject.optString("shopName");
                        dynamicShopEntity.shopId = jSONObject.optLong("shopId");
                        dynamicShopEntity.venderId = jSONObject.optLong("venderId");
                        dynamicShopEntity.logoUrl = jSONObject.optString("logoUrl");
                        dynamicShopEntity.setActivity(jSONObject.optJSONObject("activity"));
                        dynamicShopEntity.activitySrc = jSONObject.optInt("activitySrc");
                        dynamicShopEntity.hadPraised = jSONObject.optBoolean("hadPraised");
                        dynamicShopEntity.praiseCount = jSONObject.optLong("praiseCount");
                        dynamicShopEntity.viewCount = jSONObject.optLong("viewCount");
                        dynamicShopEntity.newComer = jSONObject.optBoolean("isNewComer");
                        dynamicShopEntity.newBanners = jSONObject.optJSONArray("newBanners");
                        arrayList.add(dynamicShopEntity);
                        if (this.mOldTimeStamp.equals(jSONObject.optString("timeStamp"))) {
                            dynamicShopEntity.isShowTitle = false;
                        } else {
                            dynamicShopEntity.isShowTitle = true;
                            this.mOldTimeStamp = jSONObject.optString("timeStamp");
                        }
                        boolean optBoolean = jSONObject.optJSONObject("activity") != null ? jSONObject.optJSONObject("activity").optBoolean("isRcmd") : false;
                        if (this.mCurActivitySrc != jSONObject.optInt("activitySrc") && !optBoolean) {
                            dynamicShopEntity.isShowRecDivider = true;
                            this.mCurActivitySrc = jSONObject.optInt("activitySrc");
                            if (!jSONObject.optBoolean(JshopConst.JSKEY_FOLLOWED)) {
                                dynamicShopEntity.hasArrow = true;
                                dynamicShopEntity.isConcern = true;
                            } else {
                                dynamicShopEntity.hasArrow = false;
                                dynamicShopEntity.isConcern = false;
                            }
                        }
                        dynamicShopEntity.isShowRecDivider = false;
                        if (!jSONObject.optBoolean(JshopConst.JSKEY_FOLLOWED)) {
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        return arrayList;
    }
}
