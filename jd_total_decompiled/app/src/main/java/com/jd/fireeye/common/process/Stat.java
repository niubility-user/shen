package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes13.dex */
public final class Stat extends ProcFile {
    public static final Parcelable.Creator<Stat> CREATOR = new a();
    private final String[] b;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<Stat> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Stat createFromParcel(Parcel parcel) {
            return new Stat(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Stat[] newArray(int i2) {
            return new Stat[i2];
        }
    }

    /* synthetic */ Stat(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // com.jd.fireeye.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeStringArray(this.b);
    }

    private Stat(Parcel parcel) {
        super(parcel);
        this.b = parcel.createStringArray();
    }
}
