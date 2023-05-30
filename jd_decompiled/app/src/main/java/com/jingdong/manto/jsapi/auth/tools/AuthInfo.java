package com.jingdong.manto.jsapi.auth.tools;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;

/* loaded from: classes15.dex */
public class AuthInfo implements Parcelable {
    public static final Parcelable.Creator<AuthInfo> CREATOR = new Parcelable.Creator<AuthInfo>() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthInfo createFromParcel(Parcel parcel) {
            return new AuthInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthInfo[] newArray(int i2) {
            return new AuthInfo[i2];
        }
    };
    public String description;
    public String permission;
    public String scope;
    public AuthorizeManager.State state;
    public String title;

    protected AuthInfo(Parcel parcel) {
        this.scope = parcel.readString();
        this.permission = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.state = (AuthorizeManager.State) parcel.readParcelable(AuthorizeManager.State.class.getClassLoader());
    }

    public AuthInfo(String str, String str2, String str3, int i2, String str4) {
        this.scope = str;
        this.permission = str2;
        this.title = str3;
        this.state = AuthorizeManager.State.get(i2);
        this.description = str4;
    }

    public AuthInfo(String str, String str2, String str3, String str4, AuthorizeManager.State state) {
        this.scope = str;
        this.permission = str2;
        this.title = str3;
        this.description = str4;
        this.state = state;
    }

    public static AuthInfo getAuthInfo(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(ContainerUtils.FIELD_DELIMITER);
        if (split.length < 5) {
            return null;
        }
        return new AuthInfo(split[0], split[1], split[2], Integer.valueOf(split[3]).intValue(), split[4]);
    }

    public static String getAuthInfoString(AuthInfo authInfo) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(authInfo.scope);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append(authInfo.permission);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append(authInfo.title);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append(authInfo.state.value());
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append(authInfo.description);
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.scope);
        parcel.writeString(this.permission);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeParcelable(this.state, i2);
    }
}
