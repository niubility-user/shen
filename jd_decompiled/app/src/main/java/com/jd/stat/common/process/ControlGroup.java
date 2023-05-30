package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes18.dex */
public class ControlGroup implements Parcelable {
    public static final Parcelable.Creator<ControlGroup> CREATOR = new Parcelable.Creator<ControlGroup>() { // from class: com.jd.stat.common.process.ControlGroup.1
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
    };
    public final int a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f7010c;

    /* JADX INFO: Access modifiers changed from: protected */
    public ControlGroup(String str) throws NumberFormatException, IndexOutOfBoundsException {
        String[] split = str.split(":");
        this.a = Integer.parseInt(split[0]);
        this.b = split[1];
        this.f7010c = split[2];
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("%d:%s:%s", Integer.valueOf(this.a), this.b, this.f7010c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f7010c);
    }

    protected ControlGroup(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.f7010c = parcel.readString();
    }
}
