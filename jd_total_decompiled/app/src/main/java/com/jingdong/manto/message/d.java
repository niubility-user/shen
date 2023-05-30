package com.jingdong.manto.message;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public static int f13863c = 1;
    public static int d = 2;
    private int a;
    private String b;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d() {
        this.b = "";
    }

    protected d(Parcel parcel) {
        this.b = "";
        this.a = parcel.readInt();
        this.b = parcel.readString();
    }

    public int a() {
        return this.a;
    }

    public d a(int i2) {
        this.a = i2;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
    }
}
