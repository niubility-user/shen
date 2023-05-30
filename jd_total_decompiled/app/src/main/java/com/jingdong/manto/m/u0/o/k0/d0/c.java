package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;

/* loaded from: classes15.dex */
public abstract class c extends a {
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f13766c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13767e;

    /* renamed from: f  reason: collision with root package name */
    public float f13768f;

    /* renamed from: g  reason: collision with root package name */
    public float f13769g;

    public c() {
    }

    public c(Parcel parcel) {
        super(parcel);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof c)) {
            c cVar = (c) obj;
            if (cVar.f13768f == this.f13768f && cVar.f13769g == this.f13769g && cVar.f13767e == this.f13767e && cVar.d == this.d && cVar.f13766c == this.f13766c) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.f13768f);
        parcel.writeFloat(this.f13769g);
        parcel.writeFloat(this.f13767e);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.b);
        parcel.writeInt(this.f13766c ? 1 : 0);
    }
}
