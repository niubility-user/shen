package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class w extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<w> CREATOR = new a();
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public float f13787c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13788e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<w> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final w createFromParcel(Parcel parcel) {
            return new w(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final w[] newArray(int i2) {
            return new w[i2];
        }
    }

    public w() {
    }

    public w(Parcel parcel) {
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
        if ((obj instanceof w) && super.equals(obj)) {
            w wVar = (w) obj;
            return Float.compare(wVar.d, this.d) == 0 && Float.compare(wVar.f13788e, this.f13788e) == 0 && Float.compare(wVar.f13787c, this.f13787c) == 0 && this.b == wVar.b;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.d), Float.valueOf(this.f13788e), Float.valueOf(this.f13787c), Integer.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13788e);
        parcel.writeFloat(this.f13787c);
        parcel.writeInt(this.b);
    }
}
