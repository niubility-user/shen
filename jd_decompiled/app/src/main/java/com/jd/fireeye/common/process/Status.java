package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes13.dex */
public final class Status extends ProcFile {
    public static final Parcelable.Creator<Status> CREATOR = new a();

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<Status> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Status createFromParcel(Parcel parcel) {
            return new Status(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Status[] newArray(int i2) {
            return new Status[i2];
        }
    }

    /* synthetic */ Status(Parcel parcel, a aVar) {
        this(parcel);
    }

    private Status(Parcel parcel) {
        super(parcel);
    }
}
