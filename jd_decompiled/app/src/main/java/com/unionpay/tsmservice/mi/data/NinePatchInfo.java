package com.unionpay.tsmservice.mi.data;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class NinePatchInfo implements Parcelable {
    public static final Parcelable.Creator<NinePatchInfo> CREATOR = new Parcelable.Creator<NinePatchInfo>() { // from class: com.unionpay.tsmservice.mi.data.NinePatchInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NinePatchInfo createFromParcel(Parcel parcel) {
            return new NinePatchInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NinePatchInfo[] newArray(int i2) {
            return new NinePatchInfo[i2];
        }
    };
    private Bitmap mBitmap;
    private byte[] mChunk;
    private Rect mPadding;

    public NinePatchInfo() {
    }

    public NinePatchInfo(Parcel parcel) {
        this.mBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mPadding = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt <= 0) {
            this.mChunk = null;
            return;
        }
        byte[] bArr = new byte[readInt];
        this.mChunk = bArr;
        parcel.readByteArray(bArr);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public byte[] getChunk() {
        return this.mChunk;
    }

    public Rect getPadding() {
        return this.mPadding;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setChunk(byte[] bArr) {
        this.mChunk = bArr;
    }

    public void setPadding(Rect rect) {
        this.mPadding = rect;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.mBitmap, i2);
        parcel.writeParcelable(this.mPadding, i2);
        byte[] bArr = this.mChunk;
        if (bArr != null) {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(this.mChunk);
        }
    }
}
