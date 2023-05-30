package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public final class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();
    public String a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public int f13252c;
    public String d;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<f> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i2) {
            return new f[i2];
        }
    }

    private f() {
    }

    private f(Parcel parcel) {
        this.d = parcel.readString();
        this.a = parcel.readString();
        this.f13252c = parcel.readInt();
        this.b = parcel.readByte() != 0;
    }

    /* synthetic */ f(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.a);
        parcel.writeInt(this.f13252c);
        parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
    }
}
