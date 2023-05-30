package com.jingdong.manto.m.u0.o.k0;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class v extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<v> CREATOR = new a();
    public Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    public int f13784c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f13785e;

    /* renamed from: f  reason: collision with root package name */
    public int f13786f;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<v> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final v createFromParcel(Parcel parcel) {
            return new v(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final v[] newArray(int i2) {
            return new v[i2];
        }
    }

    public v() {
        this.b = null;
    }

    public v(Parcel parcel) {
        super(parcel);
        this.b = null;
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
        if ((obj instanceof v) && super.equals(obj)) {
            v vVar = (v) obj;
            return this.f13785e == vVar.f13785e && this.f13786f == vVar.f13786f && this.d == vVar.d && this.f13784c == vVar.f13784c && this.b.equals(vVar.b);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Integer.valueOf(this.f13785e), Integer.valueOf(this.f13786f), Integer.valueOf(this.d), Integer.valueOf(this.f13784c), this.b});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f13785e);
        parcel.writeInt(this.f13786f);
        parcel.writeInt(this.d);
        parcel.writeInt(this.f13784c);
        parcel.writeParcelable(this.b, i2);
    }
}
