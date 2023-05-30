package com.jingdong.manto.t;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes16.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f14209c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public long f14210e;

    /* renamed from: f  reason: collision with root package name */
    public long f14211f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f14212g;

    /* loaded from: classes16.dex */
    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.f14209c = parcel.readString();
        this.d = parcel.readString();
        this.f14212g = parcel.readByte() != 0;
        this.f14211f = parcel.readLong();
        this.f14210e = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f14209c);
        parcel.writeString(this.d);
        parcel.writeByte(this.f14212g ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f14211f);
        parcel.writeLong(this.f14210e);
    }
}
