package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.IOException;

/* loaded from: classes18.dex */
public class LiveProcessModel implements Parcelable {
    public static final Parcelable.Creator<LiveProcessModel> CREATOR = new Parcelable.Creator<LiveProcessModel>() { // from class: com.jd.stat.common.process.LiveProcessModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveProcessModel createFromParcel(Parcel parcel) {
            return new LiveProcessModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveProcessModel[] newArray(int i2) {
            return new LiveProcessModel[i2];
        }
    };

    /* renamed from: c  reason: collision with root package name */
    public String f7012c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public int f7013e;

    public LiveProcessModel() {
    }

    static String a(int i2) throws IOException {
        String str;
        try {
            str = ProcFile.b(String.format("/proc/%d/cmdline", Integer.valueOf(i2))).trim();
        } catch (IOException unused) {
            str = null;
        }
        return TextUtils.isEmpty(str) ? Stat.a(i2).a() : str;
    }

    public Cgroup b() throws IOException {
        return Cgroup.a(this.f7013e);
    }

    public Stat c() throws IOException {
        return Stat.a(this.f7013e);
    }

    public Status d() throws IOException {
        return Status.a(this.f7013e);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f7012c);
        parcel.writeInt(this.f7013e);
    }

    public LiveProcessModel(int i2) throws IOException {
        this.f7013e = i2;
        this.f7012c = a(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LiveProcessModel(Parcel parcel) {
        this.f7012c = parcel.readString();
        this.f7013e = parcel.readInt();
    }
}
