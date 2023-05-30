package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class d extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public List<com.jingdong.manto.m.u0.o.k0.d0.a> b;

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
        this.b = new ArrayList();
    }

    public d(Parcel parcel) {
        this(parcel, (byte) 0);
    }

    private d(Parcel parcel, byte b) {
        super(parcel);
        this.b = new ArrayList();
        this.b = parcel.readArrayList(d.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof d)) {
            return this.b.equals(((d) obj).b);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeList(this.b);
    }
}
