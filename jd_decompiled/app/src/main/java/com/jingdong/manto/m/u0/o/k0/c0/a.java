package com.jingdong.manto.m.u0.o.k0.c0;

import android.graphics.Canvas;
import android.os.Parcel;

/* loaded from: classes15.dex */
public abstract class a implements c {
    public String a;

    public a() {
    }

    public a(Parcel parcel) {
        this.a = parcel.readString();
    }

    public boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas) {
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            String str = this.a;
            String str2 = ((a) obj).a;
            if (str == str2) {
                return true;
            }
            return str != null && str.equals(str2);
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return String.format("method %s", this.a);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
    }
}
