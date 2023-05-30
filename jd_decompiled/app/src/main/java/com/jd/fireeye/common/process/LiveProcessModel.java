package com.jd.fireeye.common.process;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes13.dex */
public class LiveProcessModel implements Parcelable {
    public static final Parcelable.Creator<LiveProcessModel> CREATOR = new a();
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f2616c;

    /* loaded from: classes13.dex */
    class a implements Parcelable.Creator<LiveProcessModel> {
        a() {
        }

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
    }

    public LiveProcessModel() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeInt(this.f2616c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LiveProcessModel(Parcel parcel) {
        this.a = parcel.readString();
        this.f2616c = parcel.readInt();
    }
}
