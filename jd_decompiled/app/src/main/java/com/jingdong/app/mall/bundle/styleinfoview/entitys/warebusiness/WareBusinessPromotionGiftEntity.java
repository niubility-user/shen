package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class WareBusinessPromotionGiftEntity implements Parcelable {
    public static final String BUSINESS_TYPE_9 = "9";
    public static final Parcelable.Creator<WareBusinessPromotionGiftEntity> CREATOR = new Parcelable.Creator<WareBusinessPromotionGiftEntity>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPromotionGiftEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessPromotionGiftEntity createFromParcel(Parcel parcel) {
            return new WareBusinessPromotionGiftEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessPromotionGiftEntity[] newArray(int i2) {
            return new WareBusinessPromotionGiftEntity[i2];
        }
    };
    private String activityId;
    public String businessType;
    public boolean coincideFlag;
    public String djPromotionJumpUrl;
    public String iconCode;
    public boolean isPlus = false;
    public String labelColor;
    private String link;
    private String num;
    private int proChannel;
    public long proId;
    private int proSortNum;
    private String promoId;
    private String skuId;
    private int tag;
    private String text;
    private String value;

    public WareBusinessPromotionGiftEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getLink() {
        return this.link;
    }

    public String getNum() {
        return this.num;
    }

    public int getProChannel() {
        return this.proChannel;
    }

    public int getProSortNum() {
        return this.proSortNum;
    }

    public String getPromoId() {
        return this.promoId;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public int getTag() {
        return this.tag;
    }

    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }

    public void readToParcel(Parcel parcel) {
        this.value = parcel.readString();
        this.text = parcel.readString();
        this.skuId = parcel.readString();
        this.num = parcel.readString();
        this.link = parcel.readString();
        this.promoId = parcel.readString();
        this.proChannel = parcel.readInt();
        this.tag = parcel.readInt();
        this.proSortNum = parcel.readInt();
        this.businessType = parcel.readString();
        this.activityId = parcel.readString();
        this.coincideFlag = parcel.readInt() != 0;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public void setProChannel(int i2) {
        this.proChannel = i2;
    }

    public void setProSortNum(int i2) {
        this.proSortNum = i2;
    }

    public void setPromoId(String str) {
        this.promoId = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setTag(int i2) {
        this.tag = i2;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.value);
        parcel.writeString(this.text);
        parcel.writeString(this.skuId);
        parcel.writeString(this.num);
        parcel.writeString(this.link);
        parcel.writeString(this.promoId);
        parcel.writeInt(this.proChannel);
        parcel.writeInt(this.tag);
        parcel.writeInt(this.proSortNum);
        parcel.writeString(this.businessType);
        parcel.writeString(this.activityId);
        parcel.writeInt(this.coincideFlag ? 1 : 0);
    }

    public WareBusinessPromotionGiftEntity(Parcel parcel) {
        readToParcel(parcel);
    }
}
