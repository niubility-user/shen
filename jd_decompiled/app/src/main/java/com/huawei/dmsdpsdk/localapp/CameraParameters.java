package com.huawei.dmsdpsdk.localapp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class CameraParameters implements Parcelable {
    public static final Parcelable.Creator<CameraParameters> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    public String f1149g;

    /* renamed from: h  reason: collision with root package name */
    public Map<String, String> f1150h;

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<CameraParameters> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CameraParameters createFromParcel(Parcel parcel) {
            return new CameraParameters(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CameraParameters[] newArray(int i2) {
            return new CameraParameters[i2];
        }
    }

    public CameraParameters() {
        this.f1150h = new HashMap(1);
    }

    public CameraParameters(Parcel parcel) {
        this.f1150h = new HashMap(1);
        this.f1149g = parcel.readString();
        this.f1150h = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1149g);
        parcel.writeMap(this.f1150h);
    }
}
