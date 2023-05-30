package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class UserInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.jingdong.common.entity.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int i2) {
            return new UserInfo[i2];
        }
    };
    private UserAddress userAddress;

    public UserInfo() {
    }

    public static UserInfo parser(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (UserInfo) JDJSON.parseObject(str, UserInfo.class);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserAddress getUserAddress() {
        UserAddress userAddress = this.userAddress;
        return userAddress == null ? new UserAddress() : userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.userAddress, i2);
    }

    protected UserInfo(Parcel parcel) {
        this.userAddress = (UserAddress) parcel.readParcelable(UserAddress.class.getClassLoader());
    }
}
