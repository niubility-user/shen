package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;

/* loaded from: classes13.dex */
public class ProcFile extends File implements Parcelable {
    public static final Parcelable.Creator<ProcFile> CREATOR = new a();
    public final String a;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<ProcFile> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ProcFile createFromParcel(Parcel parcel) {
            return new ProcFile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ProcFile[] newArray(int i2) {
            return new ProcFile[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProcFile(Parcel parcel) {
        super(parcel.readString());
        this.a = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.io.File
    public long length() {
        return this.a.length();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(getAbsolutePath());
        parcel.writeString(this.a);
    }
}
