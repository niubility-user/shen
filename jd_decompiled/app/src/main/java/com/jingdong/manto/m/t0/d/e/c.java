package com.jingdong.manto.m.t0.d.e;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new a();
    final int a;
    final long b;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<c> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final c[] newArray(int i2) {
            return new c[i2];
        }
    }

    /* loaded from: classes15.dex */
    public static final class b {
        private int a = 0;
        private long b = 0;

        public final b a(int i2) {
            if (i2 < 0 || i2 > 2) {
                throw new IllegalArgumentException("invalid scan mode ".concat(String.valueOf(i2)));
            }
            this.a = i2;
            return this;
        }

        public final c a() {
            return new c(this.a, this.b);
        }
    }

    c(int i2, long j2) {
        this.a = i2;
        this.b = j2;
    }

    c(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
    }
}
