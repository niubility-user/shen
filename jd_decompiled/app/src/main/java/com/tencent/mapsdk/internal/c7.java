package com.tencent.mapsdk.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class c7 {
    public static <T extends Parcelable> T a(T t) {
        Parcel obtain = Parcel.obtain();
        obtain.writeParcelable(t, 0);
        obtain.setDataPosition(0);
        T t2 = (T) obtain.readParcelable(t.getClass().getClassLoader());
        obtain.recycle();
        return t2;
    }
}
