package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class a extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<a> CREATOR = new C0625a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13743c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13744e;

    /* renamed from: com.jingdong.manto.m.u0.o.k0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0625a implements Parcelable.Creator<a> {
        C0625a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public a() {
    }

    public a(Parcel parcel) {
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
        if ((obj instanceof a) && super.equals(obj)) {
            a aVar = (a) obj;
            return Float.compare(aVar.d, this.d) == 0 && Float.compare(aVar.f13744e, this.f13744e) == 0 && Float.compare(aVar.f13743c, this.f13743c) == 0 && Float.compare(aVar.b, this.b) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.d), Float.valueOf(this.f13744e), Float.valueOf(this.f13743c), Float.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13744e);
        parcel.writeFloat(this.f13743c);
        parcel.writeFloat(this.b);
    }
}
