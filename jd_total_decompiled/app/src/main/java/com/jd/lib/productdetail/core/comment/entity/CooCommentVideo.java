package com.jd.lib.productdetail.core.comment.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSON;

/* loaded from: classes15.dex */
public class CooCommentVideo implements Parcelable {
    public static final Parcelable.Creator<CooCommentVideo> CREATOR = new Parcelable.Creator<CooCommentVideo>() { // from class: com.jd.lib.productdetail.core.comment.entity.CooCommentVideo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CooCommentVideo createFromParcel(Parcel parcel) {
            return new CooCommentVideo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CooCommentVideo[] newArray(int i2) {
            return new CooCommentVideo[i2];
        }
    };
    private boolean first;
    public String largeVideoImgUrl;
    public String sku;
    public String videoImgUrl;
    public String videoRealPath;
    public String videoTime;

    public CooCommentVideo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isFirst() {
        return this.first;
    }

    public String toString() {
        return JDJSON.toJSONString(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.videoImgUrl);
        parcel.writeString(this.largeVideoImgUrl);
        parcel.writeString(this.videoTime);
        parcel.writeString(this.sku);
        parcel.writeString(this.videoRealPath);
        parcel.writeByte(this.first ? (byte) 1 : (byte) 0);
    }

    protected CooCommentVideo(Parcel parcel) {
        this.videoImgUrl = parcel.readString();
        this.largeVideoImgUrl = parcel.readString();
        this.videoTime = parcel.readString();
        this.sku = parcel.readString();
        this.videoRealPath = parcel.readString();
        this.first = parcel.readByte() != 0;
    }
}
