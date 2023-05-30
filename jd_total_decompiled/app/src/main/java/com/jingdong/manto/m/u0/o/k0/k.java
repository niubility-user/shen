package com.jingdong.manto.m.u0.o.k0;

import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class k extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<k> CREATOR = new a();
    private f b;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<k> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final k[] newArray(int i2) {
            return new k[i2];
        }
    }

    public k() {
    }

    public k(Parcel parcel) {
        super(parcel);
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas) {
        f fVar = this.b;
        if (fVar == null) {
            return false;
        }
        return fVar.a(cVar, canvas);
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
        if ((obj instanceof k) && super.equals(obj)) {
            return this.b.equals(((k) obj).b);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.b});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.b, i2);
    }
}
