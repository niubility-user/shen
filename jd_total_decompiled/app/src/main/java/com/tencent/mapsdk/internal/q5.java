package com.tencent.mapsdk.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class q5 implements Parcelable {
    public static final Parcelable.Creator<q5> CREATOR = new a();
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f17018c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public float f17019e;

    /* renamed from: f  reason: collision with root package name */
    public float f17020f;

    /* renamed from: g  reason: collision with root package name */
    public float f17021g;

    /* renamed from: h  reason: collision with root package name */
    public String f17022h;

    /* renamed from: i  reason: collision with root package name */
    public int f17023i;

    /* renamed from: j  reason: collision with root package name */
    public int f17024j;

    /* renamed from: k  reason: collision with root package name */
    public String f17025k;

    /* renamed from: l  reason: collision with root package name */
    public float f17026l;

    /* renamed from: m  reason: collision with root package name */
    public float f17027m;

    /* renamed from: n  reason: collision with root package name */
    public int f17028n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public LatLng u;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<q5> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public q5 createFromParcel(Parcel parcel) {
            return new q5(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public q5[] newArray(int i2) {
            return new q5[i2];
        }
    }

    public q5() {
        this.f17019e = 0.5f;
        this.f17020f = 0.5f;
        this.f17021g = 1.0f;
        this.f17028n = 0;
        this.o = 3;
    }

    public q5(Parcel parcel) {
        this.f17019e = 0.5f;
        this.f17020f = 0.5f;
        this.f17021g = 1.0f;
        this.f17028n = 0;
        this.o = 3;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.f17018c = parcel.readInt();
        this.d = parcel.readInt();
        this.f17019e = parcel.readFloat();
        this.f17020f = parcel.readFloat();
        this.f17021g = parcel.readFloat();
        this.f17022h = parcel.readString();
        this.f17023i = parcel.readInt();
        this.f17024j = parcel.readInt();
        this.f17025k = parcel.readString();
        this.f17026l = parcel.readFloat();
        this.f17027m = parcel.readFloat();
        this.f17028n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
        this.u = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.f17018c);
        parcel.writeInt(this.d);
        parcel.writeFloat(this.f17019e);
        parcel.writeFloat(this.f17020f);
        parcel.writeFloat(this.f17021g);
        parcel.writeString(this.f17022h);
        parcel.writeInt(this.f17023i);
        parcel.writeInt(this.f17024j);
        parcel.writeString(this.f17025k);
        parcel.writeFloat(this.f17026l);
        parcel.writeFloat(this.f17027m);
        parcel.writeInt(this.f17028n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
        parcel.writeParcelable(this.u, i2);
    }
}
