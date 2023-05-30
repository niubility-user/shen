package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;

/* loaded from: classes13.dex */
public class LiveAppModel extends LiveProcessModel {
    public static final Parcelable.Creator<LiveAppModel> CREATOR;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public int f2615e;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<LiveAppModel> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveAppModel createFromParcel(Parcel parcel) {
            return new LiveAppModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveAppModel[] newArray(int i2) {
            return new LiveAppModel[i2];
        }
    }

    static {
        new File("/dev/cpuctl/tasks").exists();
        CREATOR = new a();
    }

    public LiveAppModel() {
    }

    public String toString() {
        return this.f2615e + "###" + this.f2616c + "###" + this.a + "###" + this.b;
    }

    @Override // com.jd.fireeye.common.process.LiveProcessModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f2615e);
    }

    protected LiveAppModel(Parcel parcel) {
        super(parcel);
        this.d = parcel.readByte() != 0;
        this.f2615e = parcel.readInt();
    }
}
