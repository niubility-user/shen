package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class f extends c {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<f> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f[] newArray(int i2) {
            return new f[i2];
        }
    }

    public f() {
    }

    public f(Parcel parcel) {
        super(parcel);
    }
}
