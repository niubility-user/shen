package com.jingdong.manto.t;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes16.dex */
public class e extends d {
    public static final Parcelable.Creator<e> CREATOR = new a();

    /* renamed from: h  reason: collision with root package name */
    public int f14213h;

    /* renamed from: i  reason: collision with root package name */
    public int f14214i;

    /* renamed from: j  reason: collision with root package name */
    public int f14215j;

    /* renamed from: k  reason: collision with root package name */
    public int f14216k;

    /* loaded from: classes16.dex */
    class a implements Parcelable.Creator<e> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    }

    public e() {
    }

    public e(Parcel parcel) {
        super(parcel);
        this.f14213h = parcel.readInt();
        this.f14216k = parcel.readInt();
        this.f14215j = parcel.readInt();
        this.f14214i = parcel.readInt();
    }

    @Override // com.jingdong.manto.t.d, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("MantoTempVideoObject: {duration=%d, size=%d, width=%d, height=%d}", Integer.valueOf(this.f14213h), Integer.valueOf(this.f14216k), Integer.valueOf(this.f14215j), Integer.valueOf(this.f14214i));
    }

    @Override // com.jingdong.manto.t.d, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f14213h);
        parcel.writeInt(this.f14216k);
        parcel.writeInt(this.f14215j);
        parcel.writeInt(this.f14214i);
    }
}
