package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class b extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<b> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13763c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13764e;

    /* renamed from: f  reason: collision with root package name */
    public float f13765f;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b[] newArray(int i2) {
            return new b[i2];
        }
    }

    public b() {
    }

    public b(Parcel parcel) {
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
        if ((obj instanceof b) && super.equals(obj)) {
            b bVar = (b) obj;
            return Float.compare(bVar.f13764e, this.f13764e) == 0 && Float.compare(bVar.f13765f, this.f13765f) == 0 && Float.compare(bVar.d, this.d) == 0 && Float.compare(bVar.b, this.b) == 0 && Float.compare(bVar.f13763c, this.f13763c) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.f13764e), Float.valueOf(this.f13765f), Float.valueOf(this.d), Float.valueOf(this.b), Float.valueOf(this.f13763c)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.f13764e);
        parcel.writeFloat(this.f13765f);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.f13763c);
    }
}
