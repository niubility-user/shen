package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.auth.AuthInfo;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class WebData implements Parcelable, Serializable {
    public static final Parcelable.Creator<WebData> CREATOR = new a();
    private static final long serialVersionUID = -4038177938155795889L;
    protected AuthInfo av;
    protected String aw;

    /* renamed from: i  reason: collision with root package name */
    protected String f16092i;
    protected int type;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<WebData> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WebData createFromParcel(Parcel parcel) {
            return new WebData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ WebData[] newArray(int i2) {
            return new WebData[i2];
        }
    }

    public WebData(AuthInfo authInfo, int i2, String str, String str2) {
        this.av = authInfo;
        this.type = i2;
        this.f16092i = str;
        this.aw = str2;
    }

    public final AuthInfo a() {
        return this.av;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.f16092i;
    }

    public final String r() {
        return this.aw;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.av, i2);
        parcel.writeInt(this.type);
        parcel.writeString(this.f16092i);
        parcel.writeString(this.aw);
    }

    protected WebData(Parcel parcel) {
        this.av = (AuthInfo) parcel.readParcelable(AuthInfo.class.getClassLoader());
        this.type = parcel.readInt();
        this.f16092i = parcel.readString();
        this.aw = parcel.readString();
    }
}
