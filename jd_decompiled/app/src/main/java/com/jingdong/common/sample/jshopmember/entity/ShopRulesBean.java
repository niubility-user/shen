package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes6.dex */
public class ShopRulesBean implements Parcelable {
    public static final Parcelable.Creator<ShopRulesBean> CREATOR = new Parcelable.Creator<ShopRulesBean>() { // from class: com.jingdong.common.sample.jshopmember.entity.ShopRulesBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopRulesBean createFromParcel(Parcel parcel) {
            return new ShopRulesBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopRulesBean[] newArray(int i2) {
            return new ShopRulesBean[i2];
        }
    };
    public String conditionStr;
    public String curGrade;
    public String curGradeName;
    public String discount;
    public boolean isCurrentLevel;
    public String maxOrderCount;
    public String maxOrderPrice;
    public String minOrderCount;
    public String minOrderPrice;
    public String multiplePoints;
    public String privilegeStr;
    public String ruleContent;
    public int venderId;

    public ShopRulesBean(JDJSONObject jDJSONObject) {
        this.conditionStr = "";
        this.privilegeStr = "";
        this.isCurrentLevel = false;
        if (jDJSONObject != null) {
            this.ruleContent = jDJSONObject.optString("ruleContent");
            this.venderId = jDJSONObject.optInt("venderId");
            this.curGrade = jDJSONObject.optString("curGrade");
            this.curGradeName = jDJSONObject.optString(JshopConst.JSKEY_GRADE_NAME);
            this.discount = jDJSONObject.optString("discount");
            this.multiplePoints = jDJSONObject.optString("multiplePoints");
            this.minOrderPrice = jDJSONObject.optString("minOrderPrice");
            this.maxOrderPrice = jDJSONObject.optString("maxOrderPrice");
            this.minOrderCount = jDJSONObject.optString("minOrderCount");
            this.maxOrderCount = jDJSONObject.optString("maxOrderCount");
            this.conditionStr = jDJSONObject.optString("conditionStr");
            this.privilegeStr = jDJSONObject.optString("privilegeStr");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getItemGrade() {
        try {
            return Float.parseFloat(this.curGrade);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.venderId);
        parcel.writeString(this.curGrade);
        parcel.writeString(this.curGradeName);
        parcel.writeString(this.discount);
        parcel.writeString(this.ruleContent);
        parcel.writeString(this.multiplePoints);
        parcel.writeString(this.minOrderPrice);
        parcel.writeString(this.maxOrderPrice);
        parcel.writeString(this.minOrderCount);
        parcel.writeString(this.maxOrderCount);
        parcel.writeString(this.conditionStr);
        parcel.writeString(this.privilegeStr);
        parcel.writeByte(this.isCurrentLevel ? (byte) 1 : (byte) 0);
    }

    protected ShopRulesBean(Parcel parcel) {
        this.conditionStr = "";
        this.privilegeStr = "";
        this.isCurrentLevel = false;
        this.venderId = parcel.readInt();
        this.curGrade = parcel.readString();
        this.curGradeName = parcel.readString();
        this.discount = parcel.readString();
        this.ruleContent = parcel.readString();
        this.multiplePoints = parcel.readString();
        this.minOrderPrice = parcel.readString();
        this.maxOrderPrice = parcel.readString();
        this.minOrderCount = parcel.readString();
        this.maxOrderCount = parcel.readString();
        this.conditionStr = parcel.readString();
        this.privilegeStr = parcel.readString();
        this.isCurrentLevel = parcel.readByte() != 0;
    }
}
