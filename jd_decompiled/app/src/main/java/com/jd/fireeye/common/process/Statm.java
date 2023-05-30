package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes13.dex */
public final class Statm extends ProcFile {
    public static final Parcelable.Creator<Statm> CREATOR = new a();
    public final String[] b;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<Statm> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Statm createFromParcel(Parcel parcel) {
            return new Statm(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Statm[] newArray(int i2) {
            return new Statm[i2];
        }
    }

    /* synthetic */ Statm(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // com.jd.fireeye.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeStringArray(this.b);
    }

    private Statm(Parcel parcel) {
        super(parcel);
        this.b = parcel.createStringArray();
    }
}
