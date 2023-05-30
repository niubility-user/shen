package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class k extends com.jingdong.manto.m.u0.o.k0.d0.a {
    public static final Parcelable.Creator<k> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13778c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13779e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<k> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final k[] newArray(int i2) {
            return new k[i2];
        }
    }

    public k() {
    }

    public k(Parcel parcel) {
        super(parcel);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof k)) {
            k kVar = (k) obj;
            if (kVar.d == this.d && kVar.f13779e == this.f13779e && kVar.f13778c == this.f13778c && kVar.b == this.b) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13779e);
        parcel.writeFloat(this.f13778c);
        parcel.writeFloat(this.b);
    }
}
