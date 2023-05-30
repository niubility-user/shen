package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class d extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public String f13761c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13762e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d() {
    }

    public d(Parcel parcel) {
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
        if ((obj instanceof d) && super.equals(obj)) {
            d dVar = (d) obj;
            return Float.compare(dVar.d, this.d) == 0 && Float.compare(dVar.f13762e, this.f13762e) == 0 && Float.compare(dVar.b, this.b) == 0 && TextUtils.equals(this.f13761c, dVar.f13761c);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.f13761c, Float.valueOf(this.d), Float.valueOf(this.f13762e), Float.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.f13761c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13762e);
        parcel.writeFloat(this.b);
    }
}
