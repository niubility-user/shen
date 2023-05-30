package com.jingdong.service.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes10.dex */
public class MediaInfo implements Parcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new Parcelable.Creator<MediaInfo>() { // from class: com.jingdong.service.entity.MediaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo createFromParcel(Parcel parcel) {
            return new MediaInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo[] newArray(int i2) {
            return new MediaInfo[i2];
        }
    };
    public int height;
    public String path;
    public String pictureType;
    public int width;

    public MediaInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.pictureType);
        parcel.writeString(this.path);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
    }

    protected MediaInfo(Parcel parcel) {
        this.pictureType = parcel.readString();
        this.path = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }
}
