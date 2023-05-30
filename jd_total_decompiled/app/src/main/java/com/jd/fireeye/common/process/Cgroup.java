package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes13.dex */
public final class Cgroup extends ProcFile {
    public static final Parcelable.Creator<Cgroup> CREATOR = new a();
    public final ArrayList<ControlGroup> b;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<Cgroup> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Cgroup createFromParcel(Parcel parcel) {
            return new Cgroup(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Cgroup[] newArray(int i2) {
            return new Cgroup[i2];
        }
    }

    /* synthetic */ Cgroup(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // com.jd.fireeye.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeTypedList(this.b);
    }

    private Cgroup(Parcel parcel) {
        super(parcel);
        this.b = parcel.createTypedArrayList(ControlGroup.CREATOR);
    }
}
