package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.m.u0.o.k0.d0.a {
    public static final Parcelable.Creator<g> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13770c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13771e;

    /* renamed from: f  reason: collision with root package name */
    public float f13772f;

    /* renamed from: g  reason: collision with root package name */
    public float f13773g;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<g> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g[] newArray(int i2) {
            return new g[i2];
        }
    }

    public g() {
    }

    public g(Parcel parcel) {
        super(parcel);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof g)) {
            g gVar = (g) obj;
            if (gVar.b == this.b && gVar.f13771e == this.f13771e && gVar.f13770c == this.f13770c && gVar.f13772f == this.f13772f && gVar.d == this.d && gVar.f13773g == this.f13773g) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.f13771e);
        parcel.writeFloat(this.f13770c);
        parcel.writeFloat(this.f13772f);
        parcel.writeFloat(this.f13770c);
        parcel.writeFloat(this.f13772f);
    }
}
