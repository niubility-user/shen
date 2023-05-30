package com.jingdong.manto.m.u0.o.k0.d0;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class a extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<a> CREATOR = new C0626a();

    /* renamed from: com.jingdong.manto.m.u0.o.k0.d0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0626a implements Parcelable.Creator<a> {
        C0626a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcel parcel) {
        super(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }
}
