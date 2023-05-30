package com.jingdong.manto.m.u0.o.k0;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class b extends com.jingdong.manto.m.u0.o.k0.c0.a {
    public static final Parcelable.Creator<b> CREATOR = new a();
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public String f13749c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public float f13750e;

    /* renamed from: f  reason: collision with root package name */
    public float f13751f;

    /* renamed from: g  reason: collision with root package name */
    public float f13752g;

    /* renamed from: h  reason: collision with root package name */
    public int f13753h;

    /* renamed from: i  reason: collision with root package name */
    public int f13754i;

    /* renamed from: j  reason: collision with root package name */
    public int f13755j;

    /* renamed from: k  reason: collision with root package name */
    public int f13756k;

    /* loaded from: classes15.dex */
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
    }

    public b(Parcel parcel) {
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
        if ((obj instanceof b) && super.equals(obj)) {
            b bVar = (b) obj;
            return this.b == bVar.b && Float.compare(bVar.d, this.d) == 0 && Float.compare(bVar.f13750e, this.f13750e) == 0 && Float.compare(bVar.f13751f, this.f13751f) == 0 && Float.compare(bVar.f13752g, this.f13752g) == 0 && this.f13753h == bVar.f13753h && this.f13754i == bVar.f13754i && this.f13755j == bVar.f13755j && this.f13756k == bVar.f13756k && TextUtils.equals(this.f13749c, bVar.f13749c);
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), Integer.valueOf(this.b), this.f13749c, Float.valueOf(this.d), Float.valueOf(this.f13750e), Float.valueOf(this.f13751f), Float.valueOf(this.f13752g), Integer.valueOf(this.f13753h), Integer.valueOf(this.f13754i), Integer.valueOf(this.f13755j), Integer.valueOf(this.f13756k)});
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.b);
        parcel.writeString(this.f13749c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f13750e);
        parcel.writeFloat(this.f13751f);
        parcel.writeFloat(this.f13752g);
        parcel.writeInt(this.f13753h);
        parcel.writeInt(this.f13754i);
        parcel.writeInt(this.f13755j);
        parcel.writeInt(this.f13756k);
    }
}
