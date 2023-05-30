package com.jingdong.common.lbs.http;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class JDLbsHttpOption implements Parcelable {
    public static final Parcelable.Creator<JDLbsHttpOption> CREATOR = new Parcelable.Creator<JDLbsHttpOption>() { // from class: com.jingdong.common.lbs.http.JDLbsHttpOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDLbsHttpOption createFromParcel(Parcel parcel) {
            return new JDLbsHttpOption(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDLbsHttpOption[] newArray(int i2) {
            return new JDLbsHttpOption[i2];
        }
    };
    private String businessId;
    private boolean isNeedDefault;
    private boolean justToastOnce;
    private double lat;
    private double lng;
    private String parentId;
    private String sceneId;
    private int sourceId;

    public JDLbsHttpOption() {
        this.businessId = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBusinessId() {
        String str = this.businessId;
        return str == null ? "" : str;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String getParentId() {
        String str = this.parentId;
        return str == null ? "" : str;
    }

    public String getSceneId() {
        return TextUtils.isEmpty(this.sceneId) ? "" : this.sceneId;
    }

    public int getSourceId() {
        return this.sourceId;
    }

    public boolean isJustToastOnce() {
        return this.justToastOnce;
    }

    public boolean isNeedDefault() {
        return this.isNeedDefault;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setJustToastOnce(boolean z) {
        this.justToastOnce = z;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setNeedDefault(boolean z) {
        this.isNeedDefault = z;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    public void setSourceId(int i2) {
        this.sourceId = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.businessId);
        parcel.writeString(this.sceneId);
        parcel.writeDouble(this.lat);
        parcel.writeDouble(this.lng);
        parcel.writeInt(this.sourceId);
        parcel.writeByte(this.justToastOnce ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isNeedDefault ? (byte) 1 : (byte) 0);
        parcel.writeString(this.parentId);
    }

    public JDLbsHttpOption(String str) {
        this.businessId = "";
        this.businessId = str;
    }

    protected JDLbsHttpOption(Parcel parcel) {
        this.businessId = "";
        this.businessId = parcel.readString();
        this.sceneId = parcel.readString();
        this.lat = parcel.readDouble();
        this.lng = parcel.readDouble();
        this.sourceId = parcel.readInt();
        this.justToastOnce = parcel.readByte() != 0;
        this.isNeedDefault = parcel.readByte() != 0;
        this.parentId = parcel.readString();
    }
}
