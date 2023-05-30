package com.jingdong.manto.k;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();
    public int a;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i2) {
            return new b[i2];
        }
    }

    public b() {
    }

    protected b(Parcel parcel) {
        this.a = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
    }
}
