package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class i extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<i> CREATOR = new a();
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f13782c;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<i> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final i[] newArray(int i2) {
            return new i[i2];
        }
    }

    public i() {
    }

    public i(Parcel parcel) {
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
        if ((obj instanceof i) && super.equals(obj)) {
            i iVar = (i) obj;
            return Float.compare(iVar.b, this.b) == 0 && Float.compare(iVar.f13782c, this.f13782c) == 0;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Float.valueOf(this.b), Float.valueOf(this.f13782c)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.f13782c);
    }
}
