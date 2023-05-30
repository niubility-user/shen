package com.jingdong.common.sample.jshop.Entity;

import java.io.Serializable;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<com.jingdong.common.sample.jshop.Entity.DynamicShopEntity> toVendorList(org.json.JSONArray r13) {
        /*
            r12 = this;
            java.lang.String r0 = "activitySrc"
            java.lang.String r1 = "activity"
            java.lang.String r2 = "timeStamp"
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            if (r13 == 0) goto Lf1
            int r4 = r13.length()
            if (r4 > 0) goto L15
            goto Lf1
        L15:
            r4 = 0
            r5 = 0
        L17:
            int r6 = r13.length()
            if (r5 >= r6) goto Lf1
            org.json.JSONObject r6 = r13.getJSONObject(r5)     // Catch: java.lang.Exception -> Led
            if (r6 == 0) goto Led
            int r7 = r6.length()     // Catch: java.lang.Exception -> Led
            if (r7 <= 0) goto Led
            com.jingdong.common.sample.jshop.Entity.DynamicShopEntity r7 = new com.jingdong.common.sample.jshop.Entity.DynamicShopEntity     // Catch: java.lang.Exception -> Led
            r7.<init>()     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "shopType"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> Led
            r7.shopType = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "shopTypeDes"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> Led
            r7.shopTypeDes = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = r6.optString(r2)     // Catch: java.lang.Exception -> Led
            r7.timeStamp = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "shopName"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> Led
            r7.shopName = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "shopId"
            long r8 = r6.optLong(r8)     // Catch: java.lang.Exception -> Led
            r7.shopId = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "venderId"
            long r8 = r6.optLong(r8)     // Catch: java.lang.Exception -> Led
            r7.venderId = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "logoUrl"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> Led
            r7.logoUrl = r8     // Catch: java.lang.Exception -> Led
            org.json.JSONObject r8 = r6.optJSONObject(r1)     // Catch: java.lang.Exception -> Led
            r7.setActivity(r8)     // Catch: java.lang.Exception -> Led
            int r8 = r6.optInt(r0)     // Catch: java.lang.Exception -> Led
            r7.activitySrc = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "hadPraised"
            boolean r8 = r6.optBoolean(r8)     // Catch: java.lang.Exception -> Led
            r7.hadPraised = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "praiseCount"
            long r8 = r6.optLong(r8)     // Catch: java.lang.Exception -> Led
            r7.praiseCount = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "viewCount"
            long r8 = r6.optLong(r8)     // Catch: java.lang.Exception -> Led
            r7.viewCount = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "isNewComer"
            boolean r8 = r6.optBoolean(r8)     // Catch: java.lang.Exception -> Led
            r7.newComer = r8     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = "newBanners"
            org.json.JSONArray r8 = r6.optJSONArray(r8)     // Catch: java.lang.Exception -> Led
            r7.newBanners = r8     // Catch: java.lang.Exception -> Led
            r3.add(r7)     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = r12.mOldTimeStamp     // Catch: java.lang.Exception -> Led
            java.lang.String r9 = r6.optString(r2)     // Catch: java.lang.Exception -> Led
            boolean r8 = r8.equals(r9)     // Catch: java.lang.Exception -> Led
            r9 = 1
            if (r8 == 0) goto Lac
            r7.isShowTitle = r4     // Catch: java.lang.Exception -> Led
            goto Lb4
        Lac:
            r7.isShowTitle = r9     // Catch: java.lang.Exception -> Led
            java.lang.String r8 = r6.optString(r2)     // Catch: java.lang.Exception -> Led
            r12.mOldTimeStamp = r8     // Catch: java.lang.Exception -> Led
        Lb4:
            org.json.JSONObject r8 = r6.optJSONObject(r1)     // Catch: java.lang.Exception -> Led
            if (r8 == 0) goto Lc5
            org.json.JSONObject r8 = r6.optJSONObject(r1)     // Catch: java.lang.Exception -> Led
            java.lang.String r10 = "isRcmd"
            boolean r8 = r8.optBoolean(r10)     // Catch: java.lang.Exception -> Led
            goto Lc6
        Lc5:
            r8 = 0
        Lc6:
            int r10 = r12.mCurActivitySrc     // Catch: java.lang.Exception -> Led
            int r11 = r6.optInt(r0)     // Catch: java.lang.Exception -> Led
            if (r10 == r11) goto Lda
            if (r8 == 0) goto Ld1
            goto Lda
        Ld1:
            r7.isShowRecDivider = r9     // Catch: java.lang.Exception -> Led
            int r8 = r6.optInt(r0)     // Catch: java.lang.Exception -> Led
            r12.mCurActivitySrc = r8     // Catch: java.lang.Exception -> Led
            goto Ldc
        Lda:
            r7.isShowRecDivider = r4     // Catch: java.lang.Exception -> Led
        Ldc:
            java.lang.String r8 = "followed"
            boolean r6 = r6.optBoolean(r8)     // Catch: java.lang.Exception -> Led
            if (r6 == 0) goto Le9
            r7.hasArrow = r9     // Catch: java.lang.Exception -> Led
            r7.isConcern = r9     // Catch: java.lang.Exception -> Led
            goto Led
        Le9:
            r7.hasArrow = r4     // Catch: java.lang.Exception -> Led
            r7.isConcern = r4     // Catch: java.lang.Exception -> Led
        Led:
            int r5 = r5 + 1
            goto L17
        Lf1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.jshop.Entity.DynamicShopEntity.toVendorList(org.json.JSONArray):java.util.ArrayList");
    }
}
