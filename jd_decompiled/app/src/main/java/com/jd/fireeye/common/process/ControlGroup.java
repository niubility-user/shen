package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes13.dex */
public class ControlGroup implements Parcelable {
    public static final Parcelable.Creator<ControlGroup> CREATOR = new a();
    public final int a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f2614c;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<ControlGroup> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ControlGroup createFromParcel(Parcel parcel) {
            return new ControlGroup(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ControlGroup[] newArray(int i2) {
            return new ControlGroup[i2];
        }
    }

    protected ControlGroup(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.f2614c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("%d:%s:%s", Integer.valueOf(this.a), this.b, this.f2614c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f2614c);
    }
}
