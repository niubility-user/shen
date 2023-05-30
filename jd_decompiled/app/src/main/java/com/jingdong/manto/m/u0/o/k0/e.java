package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class e extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<e> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13780c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13781e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<e> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final e[] newArray(int i2) {
            return new e[i2];
        }
    }

    public e() {
    }

    public e(Parcel parcel) {
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
        if ((obj instanceof e) && super.equals(obj)) {
            e eVar = (e) obj;
            return Float.compare(eVar.d, this.d) == 0 && Float.compare(eVar.f13781e, this.f13781e) == 0 && Float.compare(eVar.f13780c, this.f13780c) == 0 && Float.compare(eVar.b, this.b) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.d), Float.valueOf(this.f13781e), Float.valueOf(this.f13780c), Float.valueOf(this.b)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13781e);
        parcel.writeFloat(this.f13780c);
        parcel.writeFloat(this.b);
    }
}
