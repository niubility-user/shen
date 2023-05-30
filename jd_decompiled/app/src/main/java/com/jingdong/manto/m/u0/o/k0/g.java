package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<g> CREATOR = new a();

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<g> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g[] newArray(int i2) {
            return new g[i2];
        }
    }

    public g(Parcel parcel) {
        super(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
