package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public abstract class a implements Parcelable {
    public String a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public int f15053c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f15054e;

    /* renamed from: f  reason: collision with root package name */
    public String f15055f;

    /* renamed from: g  reason: collision with root package name */
    public String f15056g;

    /* renamed from: h  reason: collision with root package name */
    public String f15057h;

    /* renamed from: i  reason: collision with root package name */
    public String f15058i;

    /* JADX INFO: Access modifiers changed from: protected */
    public a() {
        this.b = 0L;
        this.f15053c = Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcel parcel) {
        this.b = 0L;
        this.f15053c = Integer.MAX_VALUE;
        this.a = parcel.readString();
        this.b = parcel.readLong();
        this.f15053c = parcel.readInt();
        this.d = parcel.readString();
        this.f15054e = parcel.readString();
        this.f15055f = parcel.readString();
        this.f15056g = parcel.readString();
        this.f15057h = parcel.readString();
        this.f15058i = parcel.readString();
    }

    public boolean a() {
        return !"1".equals(this.a);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeLong(this.b);
        parcel.writeInt(this.f15053c);
        parcel.writeString(this.d);
        parcel.writeString(this.f15054e);
        parcel.writeString(this.f15055f);
        parcel.writeString(this.f15056g);
        parcel.writeString(this.f15057h);
        parcel.writeString(this.f15058i);
    }
}
