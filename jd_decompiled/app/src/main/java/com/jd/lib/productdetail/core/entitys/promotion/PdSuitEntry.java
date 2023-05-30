package com.jd.lib.productdetail.core.entitys.promotion;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.database.table.DB_PacksTable;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdSuitEntry implements Parcelable {
    public static final Parcelable.Creator<PdSuitEntry> CREATOR = new Parcelable.Creator<PdSuitEntry>() { // from class: com.jd.lib.productdetail.core.entitys.promotion.PdSuitEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdSuitEntry createFromParcel(Parcel parcel) {
            return new PdSuitEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdSuitEntry[] newArray(int i2) {
            return new PdSuitEntry[i2];
        }
    };
    private String business;
    private String discountMark;
    private String domain;
    private String floorName;
    public List<PdPromotionPackEntry> itemList;
    private long mainSkuId;
    private String mainSkuName;
    private String mainSkuPicUrl;
    private boolean newStyle;
    private String packABTest;
    public PdStyleSelectSuitItem selectSuitItem;
    private String suitMark;
    private String suitNumText;

    public PdSuitEntry() {
        this.itemList = new ArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBusiness() {
        return this.business;
    }

    public String getDiscountMark() {
        return this.discountMark;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getFloorName() {
        return this.floorName;
    }

    public long getMainSkuId() {
        return this.mainSkuId;
    }

    public String getMainSkuName() {
        return this.mainSkuName;
    }

    public String getMainSkuPicUrl() {
        return this.mainSkuPicUrl;
    }

    public String getPackABTest() {
        return this.packABTest;
    }

    public List<PdPromotionPackEntry> getSuit() {
        return this.itemList;
    }

    public String getSuitMark() {
        return this.suitMark;
    }

    public String getSuitNumText() {
        return this.suitNumText;
    }

    public boolean isNewStyle() {
        return this.newStyle;
    }

    public void readToParcel(Parcel parcel) {
        this.mainSkuId = parcel.readLong();
        this.mainSkuName = parcel.readString();
        this.mainSkuPicUrl = parcel.readString();
        this.domain = parcel.readString();
        this.packABTest = parcel.readString();
        parcel.readTypedList(this.itemList, PdPromotionPackEntry.CREATOR);
        this.suitMark = parcel.readString();
        this.discountMark = parcel.readString();
        this.suitNumText = parcel.readString();
        this.floorName = parcel.readString();
        this.newStyle = parcel.readByte() != 0;
        this.business = parcel.readString();
    }

    public void setBusiness(String str) {
        this.business = str;
    }

    public void setDiscountMark(String str) {
        this.discountMark = str;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setFloorName(String str) {
        this.floorName = str;
    }

    public void setItemData() {
        if (this.itemList == null) {
            return;
        }
        for (int i2 = 0; i2 < this.itemList.size(); i2++) {
            PdPromotionPackEntry pdPromotionPackEntry = this.itemList.get(i2);
            if (pdPromotionPackEntry != null) {
                pdPromotionPackEntry.setMainSkuId(this.mainSkuId);
                pdPromotionPackEntry.setMainSkuName(this.mainSkuName);
                pdPromotionPackEntry.setMainSkuPicUrl(this.mainSkuPicUrl);
            }
        }
    }

    public void setMainSkuId(long j2) {
        this.mainSkuId = j2;
    }

    public void setMainSkuName(String str) {
        this.mainSkuName = str;
    }

    public void setMainSkuPicUrl(String str) {
        this.mainSkuPicUrl = str;
    }

    public void setNewStyle(boolean z) {
        this.newStyle = z;
    }

    public void setPackABTest(String str) {
        this.packABTest = str;
    }

    public void setSuit(List<PdPromotionPackEntry> list) {
        this.itemList = list;
    }

    public void setSuitMark(String str) {
        this.suitMark = str;
    }

    public void setSuitNumText(String str) {
        this.suitNumText = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.mainSkuId);
        parcel.writeString(this.mainSkuName);
        parcel.writeString(this.mainSkuPicUrl);
        parcel.writeString(this.domain);
        parcel.writeString(this.packABTest);
        parcel.writeTypedList(this.itemList);
        parcel.writeString(this.suitMark);
        parcel.writeString(this.discountMark);
        parcel.writeString(this.suitNumText);
        parcel.writeString(this.floorName);
        parcel.writeByte(this.newStyle ? (byte) 1 : (byte) 0);
        parcel.writeString(this.business);
    }

    public PdSuitEntry(Parcel parcel) {
        this.itemList = new ArrayList();
        readToParcel(parcel);
    }

    public PdSuitEntry(int i2, String str, JDJSONArray jDJSONArray) {
        PdPromotionPackEntry pdPromotionPackEntry;
        this.itemList = new ArrayList();
        this.packABTest = String.valueOf(i2);
        this.domain = str;
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return;
        }
        this.itemList = new ArrayList(jDJSONArray.size());
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i3);
            if (optJSONObject != null && (pdPromotionPackEntry = (PdPromotionPackEntry) JDJSON.parseObject(optJSONObject.toString(), PdPromotionPackEntry.class)) != null) {
                this.itemList.add(pdPromotionPackEntry);
            }
        }
    }

    public PdSuitEntry(int i2, JDJSONObject jDJSONObject) {
        PdPromotionPackEntry pdPromotionPackEntry;
        this.itemList = new ArrayList();
        this.packABTest = String.valueOf(i2);
        if (jDJSONObject != null) {
            this.mainSkuId = jDJSONObject.optLong(DB_PacksTable.TB_COLOUMN_MAIN_SKU_ID);
            this.mainSkuName = jDJSONObject.optString("mainSkuName");
            this.mainSkuPicUrl = jDJSONObject.optString("mainSkuPicUrl");
            this.domain = jDJSONObject.optString("domain");
            this.suitMark = jDJSONObject.optString("suitMark");
            this.discountMark = jDJSONObject.optString("discountMark");
            this.suitNumText = jDJSONObject.optString("suitNumText");
            this.floorName = jDJSONObject.optString(BottomNavigationConstant.KEY_FLOOR_NAME);
            this.newStyle = jDJSONObject.optBoolean("newStyle");
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("itemList");
            if (optJSONArray == null || optJSONArray.size() <= 0) {
                return;
            }
            this.itemList = new ArrayList(optJSONArray.size());
            for (int i3 = 0; i3 < optJSONArray.size(); i3++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null && (pdPromotionPackEntry = (PdPromotionPackEntry) JDJSON.parseObject(optJSONObject.toString(), PdPromotionPackEntry.class)) != null) {
                    pdPromotionPackEntry.setMainSkuId(this.mainSkuId);
                    pdPromotionPackEntry.setMainSkuName(this.mainSkuName);
                    pdPromotionPackEntry.setMainSkuPicUrl(this.mainSkuPicUrl);
                    this.itemList.add(pdPromotionPackEntry);
                }
            }
        }
    }
}
