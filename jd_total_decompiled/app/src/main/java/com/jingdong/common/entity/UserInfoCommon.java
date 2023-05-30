package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.entity.settlement.AddressOverSea;
import java.io.Serializable;

/* loaded from: classes.dex */
public class UserInfoCommon extends AddressOverSea implements Parcelable, Serializable {
    public static final Parcelable.Creator<UserInfoCommon> CREATOR = new Parcelable.Creator<UserInfoCommon>() { // from class: com.jingdong.common.entity.UserInfoCommon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfoCommon createFromParcel(Parcel parcel) {
            return new UserInfoCommon(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfoCommon[] newArray(int i2) {
            return new UserInfoCommon[i2];
        }
    };
    protected String areaExplainMsg;
    protected String areaExplainUrl;

    public UserInfoCommon() {
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAreaExplainMsg() {
        return this.areaExplainMsg;
    }

    public String getAreaExplainUrl() {
        return this.areaExplainUrl;
    }

    public void setAreaExplainMsg(String str) {
        this.areaExplainMsg = str;
    }

    public void setAreaExplainUrl(String str) {
        this.areaExplainUrl = str;
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.areaExplainMsg);
        parcel.writeString(this.areaExplainUrl);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UserInfoCommon(Parcel parcel) {
        super(parcel);
        this.areaExplainMsg = parcel.readString();
        this.areaExplainUrl = parcel.readString();
    }
}
