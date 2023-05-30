package com.huawei.dmsdpsdk.localapp;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes12.dex */
public class CameraInfo implements Parcelable {
    public static final Parcelable.Creator<CameraInfo> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private String f1145g;

    /* renamed from: h  reason: collision with root package name */
    private int f1146h;

    /* renamed from: i  reason: collision with root package name */
    private String f1147i;

    /* renamed from: j  reason: collision with root package name */
    private String f1148j;

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<CameraInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CameraInfo createFromParcel(Parcel parcel) {
            return new CameraInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CameraInfo[] newArray(int i2) {
            return new CameraInfo[i2];
        }
    }

    public CameraInfo() {
    }

    public CameraInfo(Parcel parcel) {
        this.f1145g = parcel.readString();
        this.f1146h = parcel.readInt();
        this.f1147i = parcel.readString();
        this.f1148j = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1145g);
        parcel.writeInt(this.f1146h);
        parcel.writeString(this.f1147i);
        parcel.writeString(this.f1148j);
    }
}
