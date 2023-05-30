package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class b0 extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<b0> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13757c;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<b0> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b0 createFromParcel(Parcel parcel) {
            return new b0(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b0[] newArray(int i2) {
            return new b0[i2];
        }
    }

    public b0() {
    }

    public b0(Parcel parcel) {
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
        if ((obj instanceof b0) && super.equals(obj)) {
            b0 b0Var = (b0) obj;
            return Float.compare(b0Var.b, this.b) == 0 && Float.compare(b0Var.f13757c, this.f13757c) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.b), Float.valueOf(this.f13757c)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.f13757c);
    }
}
