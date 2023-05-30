package com.huawei.dmsdpsdk.localapp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    public Map<Integer, String> f1151g;

    /* renamed from: h  reason: collision with root package name */
    private String f1152h;

    /* renamed from: i  reason: collision with root package name */
    private String f1153i;

    /* renamed from: j  reason: collision with root package name */
    private int f1154j;

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<DeviceInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DeviceInfo[] newArray(int i2) {
            return new DeviceInfo[i2];
        }
    }

    public DeviceInfo() {
        this.f1151g = new HashMap(1);
    }

    public DeviceInfo(Parcel parcel) {
        this.f1151g = new HashMap(1);
        this.f1152h = parcel.readString();
        this.f1153i = parcel.readString();
        this.f1154j = parcel.readInt();
        this.f1151g = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1152h);
        parcel.writeString(this.f1153i);
        parcel.writeInt(this.f1154j);
        parcel.writeMap(this.f1151g);
    }
}
