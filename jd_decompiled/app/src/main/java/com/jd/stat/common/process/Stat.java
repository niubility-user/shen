package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

/* loaded from: classes18.dex */
public final class Stat extends ProcFile {
    public static final Parcelable.Creator<Stat> CREATOR = new Parcelable.Creator<Stat>() { // from class: com.jd.stat.common.process.Stat.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Stat createFromParcel(Parcel parcel) {
            return new Stat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Stat[] newArray(int i2) {
            return new Stat[i2];
        }
    };
    private final String[] a;

    public static Stat a(int i2) throws IOException {
        return new Stat(String.format("/proc/%d/stat", Integer.valueOf(i2)));
    }

    public int b() {
        return Integer.parseInt(this.a[40]);
    }

    @Override // com.jd.stat.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeStringArray(this.a);
    }

    private Stat(String str) throws IOException {
        super(str);
        this.a = this.b.split("\\s+");
    }

    public String a() {
        return this.a[1].replace("(", "").replace(")", "");
    }

    private Stat(Parcel parcel) {
        super(parcel);
        this.a = parcel.createStringArray();
    }
}
