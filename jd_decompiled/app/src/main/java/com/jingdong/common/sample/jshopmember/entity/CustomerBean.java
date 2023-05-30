package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class CustomerBean implements Parcelable {
    public static final Parcelable.Creator<CustomerBean> CREATOR = new Parcelable.Creator<CustomerBean>() { // from class: com.jingdong.common.sample.jshopmember.entity.CustomerBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomerBean createFromParcel(Parcel parcel) {
            return new CustomerBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomerBean[] newArray(int i2) {
            return new CustomerBean[i2];
        }
    };
    public String customerHead;
    public String customerLevel;
    public String customerLevelName;
    public String customerName;
    public long customerPoint;
    public long tradeAmount;
    public long tradeCount;
    public String upgradeCondition;

    public CustomerBean(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.customerHead = jDJSONObject.optString("customerHead");
            this.customerName = jDJSONObject.optString("customerName");
            this.customerPoint = jDJSONObject.optInt("customerPoint");
            this.customerLevel = jDJSONObject.optString("customerLevel");
            this.upgradeCondition = jDJSONObject.optString("upgradeCondition");
            this.tradeCount = jDJSONObject.optInt("tradeCount");
            this.tradeAmount = jDJSONObject.optInt("tradeAmount");
            this.customerLevelName = jDJSONObject.optString("customerLevelName");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getCurrentLevel() {
        try {
            return Float.parseFloat(this.customerLevel);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 1.0f;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.customerHead);
        parcel.writeString(this.customerName);
        parcel.writeLong(this.customerPoint);
        parcel.writeString(this.customerLevel);
        parcel.writeString(this.upgradeCondition);
        parcel.writeLong(this.tradeCount);
        parcel.writeLong(this.tradeAmount);
        parcel.writeString(this.customerLevelName);
    }

    protected CustomerBean(Parcel parcel) {
        this.customerHead = parcel.readString();
        this.customerName = parcel.readString();
        this.customerPoint = parcel.readLong();
        this.customerLevel = parcel.readString();
        this.upgradeCondition = parcel.readString();
        this.tradeCount = parcel.readLong();
        this.tradeAmount = parcel.readLong();
        this.customerLevelName = parcel.readString();
    }
}
