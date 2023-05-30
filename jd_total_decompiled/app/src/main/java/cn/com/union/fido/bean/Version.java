package cn.com.union.fido.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator<Version>() { // from class: cn.com.union.fido.bean.Version.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Version createFromParcel(Parcel parcel) {
            return new Version(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Version[] newArray(int i2) {
            return new Version[i2];
        }
    };
    public Integer major;
    public Integer minor;

    public Version() {
    }

    private Version(Parcel parcel) {
        this.major = Integer.valueOf(parcel.readInt());
        this.minor = Integer.valueOf(parcel.readInt());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.major.intValue());
        parcel.writeInt(this.minor.intValue());
    }
}
