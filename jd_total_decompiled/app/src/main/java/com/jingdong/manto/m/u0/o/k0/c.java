package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class c extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<c> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13758c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13759e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<c> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final c[] newArray(int i2) {
            return new c[i2];
        }
    }

    public c() {
    }

    public c(Parcel parcel) {
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
        if ((obj instanceof c) && super.equals(obj)) {
            c cVar = (c) obj;
            return Float.compare(cVar.d, this.d) == 0 && Float.compare(cVar.f13759e, this.f13759e) == 0 && Float.compare(cVar.f13758c, this.f13758c) == 0 && Float.compare(cVar.b, this.b) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.d), Float.valueOf(this.f13759e), Float.valueOf(this.f13758c), Float.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13759e);
        parcel.writeFloat(this.f13758c);
        parcel.writeFloat(this.b);
    }
}
