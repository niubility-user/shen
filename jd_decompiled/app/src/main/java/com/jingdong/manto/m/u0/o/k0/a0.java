package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class a0 extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<a0> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13745c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13746e;

    /* renamed from: f  reason: collision with root package name */
    public float f13747f;

    /* renamed from: g  reason: collision with root package name */
    public float f13748g;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<a0> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a0 createFromParcel(Parcel parcel) {
            return new a0(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a0[] newArray(int i2) {
            return new a0[i2];
        }
    }

    public a0() {
    }

    public a0(Parcel parcel) {
        super(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof a0) && super.equals(obj)) {
            a0 a0Var = (a0) obj;
            return Float.compare(a0Var.f13747f, this.f13747f) == 0 && Float.compare(a0Var.f13745c, this.f13745c) == 0 && Float.compare(a0Var.d, this.d) == 0 && Float.compare(a0Var.f13748g, this.f13748g) == 0 && Float.compare(a0Var.f13746e, this.f13746e) == 0 && Float.compare(a0Var.b, this.b) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.f13747f), Float.valueOf(this.f13745c), Float.valueOf(this.d), Float.valueOf(this.f13748g), Float.valueOf(this.f13746e), Float.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.f13748g);
        parcel.writeFloat(this.f13745c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13748g);
        parcel.writeFloat(this.f13746e);
        parcel.writeFloat(this.b);
    }
}
