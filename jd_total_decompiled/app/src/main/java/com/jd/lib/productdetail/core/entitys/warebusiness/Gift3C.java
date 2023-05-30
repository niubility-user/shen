package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;

/* loaded from: classes15.dex */
public class Gift3C implements Parcelable {
    public static final Parcelable.Creator<Gift3C> CREATOR = new Parcelable.Creator<Gift3C>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.Gift3C.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gift3C createFromParcel(Parcel parcel) {
            return new Gift3C(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gift3C[] newArray(int i2) {
            return new Gift3C[i2];
        }
    };
    private int CheckType;
    private String Id;
    private String ImgUrl;
    private String Name;
    private String Num;
    private String pno;
    private String poolName;
    private String skuId;

    public Gift3C() {
    }

    public static Gift3C objectFromData(String str) {
        return (Gift3C) new Gson().fromJson(str, Gift3C.class);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.Id;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public String getName() {
        return this.Name;
    }

    public String getNum() {
        return this.Num;
    }

    public String getPoolName() {
        return this.poolName;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public boolean isChecked() {
        return this.CheckType == 1;
    }

    public void setCheckType(int i2) {
        this.CheckType = i2;
    }

    public void setId(String str) {
        this.Id = str;
    }

    public void setImgUrl(String str) {
        this.ImgUrl = str;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public void setNum(String str) {
        this.Num = str;
    }

    public void setPoolName(String str) {
        this.poolName = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.pno);
        parcel.writeString(this.Id);
        parcel.writeString(this.Num);
        parcel.writeString(this.ImgUrl);
        parcel.writeString(this.skuId);
        parcel.writeString(this.Name);
        parcel.writeString(this.poolName);
        parcel.writeInt(this.CheckType);
    }

    protected Gift3C(Parcel parcel) {
        this.pno = parcel.readString();
        this.Id = parcel.readString();
        this.Num = parcel.readString();
        this.ImgUrl = parcel.readString();
        this.skuId = parcel.readString();
        this.Name = parcel.readString();
        this.poolName = parcel.readString();
        this.CheckType = parcel.readInt();
    }

    public void setNum(int i2) {
        this.Num = String.valueOf(i2);
    }
}
