package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes18.dex */
public final class Statm extends ProcFile {
    public static final Parcelable.Creator<Statm> CREATOR = new Parcelable.Creator<Statm>() { // from class: com.jd.stat.common.process.Statm.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Statm createFromParcel(Parcel parcel) {
            return new Statm(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Statm[] newArray(int i2) {
            return new Statm[i2];
        }
    };
    public final String[] a;

    @Override // com.jd.stat.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeStringArray(this.a);
    }

    private Statm(Parcel parcel) {
        super(parcel);
        this.a = parcel.createStringArray();
    }
}
