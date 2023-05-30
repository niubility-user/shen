package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class SuperGroupObject extends MediaObject {
    public static final Parcelable.Creator<SuperGroupObject> CREATOR = new a();
    public String secName;
    public String sgExtParam;
    public String sgName;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<SuperGroupObject> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final SuperGroupObject createFromParcel(Parcel parcel) {
            return new SuperGroupObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final SuperGroupObject[] newArray(int i2) {
            return new SuperGroupObject[i2];
        }
    }

    public SuperGroupObject() {
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.sgName);
        parcel.writeString(this.secName);
        parcel.writeString(this.sgExtParam);
    }

    protected SuperGroupObject(Parcel parcel) {
        this.sgName = parcel.readString();
        this.secName = parcel.readString();
        this.sgExtParam = parcel.readString();
    }
}
