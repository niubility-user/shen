package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class s extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<s> CREATOR = new a();
    public float[] b;

    /* renamed from: c  reason: collision with root package name */
    public float f13783c;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<s> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final s createFromParcel(Parcel parcel) {
            return new s(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final s[] newArray(int i2) {
            return new s[i2];
        }
    }

    public s() {
        this.b = null;
        this.f13783c = Float.MIN_VALUE;
    }

    public s(Parcel parcel) {
        super(parcel);
        this.b = null;
        this.f13783c = Float.MIN_VALUE;
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
        if ((obj instanceof s) && super.equals(obj)) {
            s sVar = (s) obj;
            return Float.compare(sVar.f13783c, this.f13783c) == 0 && Arrays.equals(this.b, sVar.b);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return (Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.f13783c)}) * 31) + Arrays.hashCode(this.b);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        float[] fArr = this.b;
        if (fArr != null) {
            parcel.writeInt(fArr.length);
            parcel.writeFloatArray(this.b);
        }
        parcel.writeFloat(this.f13783c);
    }
}
