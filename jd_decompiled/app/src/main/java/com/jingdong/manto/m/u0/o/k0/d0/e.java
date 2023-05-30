package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class e extends c {
    public static final Parcelable.Creator<e> CREATOR = new a();

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
}
