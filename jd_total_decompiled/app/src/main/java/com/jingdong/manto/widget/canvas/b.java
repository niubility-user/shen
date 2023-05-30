package com.jingdong.manto.widget.canvas;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.m.u0.o.k0.c0.d;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();
    public List<d> a;

    /* loaded from: classes16.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b[] newArray(int i2) {
            return new b[i2];
        }
    }

    public b() {
        this.a = new ArrayList();
    }

    public b(Parcel parcel) {
        this.a = new ArrayList();
        this.a = parcel.readArrayList(b.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.a);
    }
}
