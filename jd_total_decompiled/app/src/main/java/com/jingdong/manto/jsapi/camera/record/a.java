package com.jingdong.manto.jsapi.camera.record;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;

/* loaded from: classes15.dex */
public class a implements Comparable<a>, Parcelable {
    private final int a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private static final SparseArrayCompat<SparseArrayCompat<a>> f13160c = new SparseArrayCompat<>(16);
    public static final Parcelable.Creator<a> CREATOR = new C0524a();

    /* renamed from: com.jingdong.manto.jsapi.camera.record.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0524a implements Parcelable.Creator<a> {
        C0524a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return a.b(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    private a(int i2, int i3) {
        this.a = i2;
        this.b = i3;
    }

    private static int a(int i2, int i3) {
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i2 == 0) {
                return i5;
            }
            i3 = i5 % i2;
        }
    }

    public static a b(int i2, int i3) {
        int a = a(i2, i3);
        int i4 = i2 / a;
        int i5 = i3 / a;
        SparseArrayCompat<SparseArrayCompat<a>> sparseArrayCompat = f13160c;
        SparseArrayCompat<a> sparseArrayCompat2 = sparseArrayCompat.get(i4);
        if (sparseArrayCompat2 == null) {
            a aVar = new a(i4, i5);
            SparseArrayCompat<a> sparseArrayCompat3 = new SparseArrayCompat<>();
            sparseArrayCompat3.put(i5, aVar);
            sparseArrayCompat.put(i4, sparseArrayCompat3);
            return aVar;
        }
        a aVar2 = sparseArrayCompat2.get(i5);
        if (aVar2 == null) {
            a aVar3 = new a(i4, i5);
            sparseArrayCompat2.put(i5, aVar3);
            return aVar3;
        }
        return aVar2;
    }

    public int a() {
        return this.a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull a aVar) {
        if (equals(aVar)) {
            return 0;
        }
        return d() - aVar.d() > 0.0f ? 1 : -1;
    }

    public boolean a(e eVar) {
        int a = a(eVar.b(), eVar.a());
        return this.a == eVar.b() / a && this.b == eVar.a() / a;
    }

    public int b() {
        return this.b;
    }

    public a c() {
        return b(this.b, this.a);
    }

    public float d() {
        return this.a / this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.b;
        int i3 = this.a;
        return i2 ^ ((i3 >>> 16) | (i3 << 16));
    }

    public String toString() {
        return this.a + ":" + this.b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
    }
}
