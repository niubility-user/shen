package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class j extends com.jingdong.manto.m.u0.o.k0.d0.a {
    public static final Parcelable.Creator<j> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13776c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13777e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<j> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final j[] newArray(int i2) {
            return new j[i2];
        }
    }

    public j() {
    }

    public j(Parcel parcel) {
        super(parcel);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof j)) {
            j jVar = (j) obj;
            if (jVar.b == this.b && jVar.d == this.d && jVar.f13776c == this.f13776c && jVar.f13777e == this.f13777e) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.d0.a, com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13776c);
        parcel.writeFloat(this.f13777e);
    }
}
