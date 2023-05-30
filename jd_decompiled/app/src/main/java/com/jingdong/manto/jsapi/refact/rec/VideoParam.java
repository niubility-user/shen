package com.jingdong.manto.jsapi.refact.rec;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class VideoParam implements Parcelable {
    public static final Parcelable.Creator<VideoParam> CREATOR = new Parcelable.Creator<VideoParam>() { // from class: com.jingdong.manto.jsapi.refact.rec.VideoParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoParam createFromParcel(Parcel parcel) {
            return new VideoParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoParam[] newArray(int i2) {
            return new VideoParam[i2];
        }
    };
    public int camera;
    public int maxTime;
    public int minTime;
    public String recordFilePath;

    public VideoParam() {
    }

    protected VideoParam(Parcel parcel) {
        this.maxTime = parcel.readInt();
        this.minTime = parcel.readInt();
        this.camera = parcel.readInt();
        this.recordFilePath = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.maxTime);
        parcel.writeInt(this.minTime);
        parcel.writeInt(this.camera);
        parcel.writeString(this.recordFilePath);
    }
}
