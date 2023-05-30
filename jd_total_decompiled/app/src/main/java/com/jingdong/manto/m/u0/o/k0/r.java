package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class r extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<r> CREATOR = new a();
    public String b;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<r> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final r createFromParcel(Parcel parcel) {
            return new r(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final r[] newArray(int i2) {
            return new r[i2];
        }
    }

    public r() {
    }

    public r(Parcel parcel) {
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
        if ((obj instanceof r) && super.equals(obj)) {
            return TextUtils.equals(this.b, ((r) obj).b);
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
        parcel.writeString(this.b);
    }
}
