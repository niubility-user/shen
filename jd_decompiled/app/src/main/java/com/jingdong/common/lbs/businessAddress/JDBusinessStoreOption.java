package com.jingdong.common.lbs.businessAddress;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.lbs.http.JDLbsHttpOption;

/* loaded from: classes5.dex */
public class JDBusinessStoreOption extends JDLbsHttpOption implements Parcelable {
    public static final Parcelable.Creator<JDBusinessStoreOption> CREATOR = new Parcelable.Creator<JDBusinessStoreOption>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessStoreOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDBusinessStoreOption createFromParcel(Parcel parcel) {
            return new JDBusinessStoreOption(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDBusinessStoreOption[] newArray(int i2) {
            return new JDBusinessStoreOption[i2];
        }
    };
    private String businessType;
    private String key;
    private double locationLat;
    private double locationLng;
    private int page;
    private String scopeModel;

    public JDBusinessStoreOption() {
        this.businessType = "9";
        this.scopeModel = "3";
    }

    @Override // com.jingdong.common.lbs.http.JDLbsHttpOption, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBusinessType() {
        return TextUtils.isEmpty(this.businessType) ? "9" : this.businessType;
    }

    public String getKey() {
        return TextUtils.isEmpty(this.key) ? "" : this.key;
    }

    public double getLocationLat() {
        if (Double.isNaN(this.locationLat)) {
            return 0.0d;
        }
        return this.locationLat;
    }

    public double getLocationLng() {
        if (Double.isNaN(this.locationLng)) {
            return 0.0d;
        }
        return this.locationLng;
    }

    public int getPage() {
        int i2 = this.page;
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public String getScopeModel() {
        return TextUtils.isEmpty(this.scopeModel) ? "3" : this.scopeModel;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setLocationLat(double d) {
        this.locationLat = d;
    }

    public void setLocationLng(double d) {
        this.locationLng = d;
    }

    public void setPage(int i2) {
        this.page = i2;
    }

    public void setScopeModel(String str) {
        this.scopeModel = str;
    }

    @Override // com.jingdong.common.lbs.http.JDLbsHttpOption, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeDouble(this.locationLng);
        parcel.writeDouble(this.locationLat);
        parcel.writeString(this.key);
        parcel.writeInt(this.page);
        parcel.writeString(this.businessType);
        parcel.writeString(this.scopeModel);
    }

    protected JDBusinessStoreOption(Parcel parcel) {
        this.businessType = "9";
        this.scopeModel = "3";
        this.locationLng = parcel.readDouble();
        this.locationLat = parcel.readDouble();
        this.key = parcel.readString();
        this.page = parcel.readInt();
        this.businessType = parcel.readString();
        this.scopeModel = parcel.readString();
    }
}
