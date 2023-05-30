package com.jingdong.common.entity.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.entity.UserInfoCommon;

/* loaded from: classes5.dex */
public class NewAddressIntentParameter implements Parcelable {
    public static final Parcelable.Creator<NewAddressIntentParameter> CREATOR = new Parcelable.Creator<NewAddressIntentParameter>() { // from class: com.jingdong.common.entity.settlement.NewAddressIntentParameter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewAddressIntentParameter createFromParcel(Parcel parcel) {
            return new NewAddressIntentParameter(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewAddressIntentParameter[] newArray(int i2) {
            return new NewAddressIntentParameter[i2];
        }
    };
    public boolean isSelect;
    public boolean isShowDeleteBtn;
    private UserInfoCommon userInfoCommon;

    public NewAddressIntentParameter() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoCommon getUserInfoCommon() {
        UserInfoCommon userInfoCommon = this.userInfoCommon;
        return userInfoCommon == null ? new UserInfoCommon() : userInfoCommon;
    }

    public void setUserInfoCommon(UserInfoCommon userInfoCommon) {
        this.userInfoCommon = userInfoCommon;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowDeleteBtn ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.userInfoCommon, i2);
    }

    protected NewAddressIntentParameter(Parcel parcel) {
        this.isSelect = parcel.readByte() != 0;
        this.isShowDeleteBtn = parcel.readByte() != 0;
        this.userInfoCommon = (UserInfoCommon) parcel.readParcelable(UserInfoCommon.class.getClassLoader());
    }
}
