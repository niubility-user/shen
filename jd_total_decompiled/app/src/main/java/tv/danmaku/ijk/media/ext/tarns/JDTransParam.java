package tv.danmaku.ijk.media.ext.tarns;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class JDTransParam implements Parcelable {
    public static final Parcelable.Creator<JDTransParam> CREATOR = new Parcelable.Creator<JDTransParam>() { // from class: tv.danmaku.ijk.media.ext.tarns.JDTransParam.1
        @Override // android.os.Parcelable.Creator
        public JDTransParam createFromParcel(Parcel parcel) {
            return new JDTransParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public JDTransParam[] newArray(int i2) {
            return new JDTransParam[i2];
        }
    };
    public int bottom;
    public int height;
    public int left;
    public int right;
    public int top;
    public int width;

    public JDTransParam() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.left);
        parcel.writeInt(this.right);
        parcel.writeInt(this.top);
        parcel.writeInt(this.bottom);
    }

    protected JDTransParam(Parcel parcel) {
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.left = parcel.readInt();
        this.right = parcel.readInt();
        this.top = parcel.readInt();
        this.bottom = parcel.readInt();
    }
}
