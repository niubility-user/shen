package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdCommentPictureInfo implements Parcelable {
    public static final Parcelable.Creator<PdCommentPictureInfo> CREATOR = new Parcelable.Creator<PdCommentPictureInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentPictureInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentPictureInfo createFromParcel(Parcel parcel) {
            return new PdCommentPictureInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentPictureInfo[] newArray(int i2) {
            return new PdCommentPictureInfo[i2];
        }
    };
    public String largePicURL;
    public String mediaId;
    public String mediaLength;
    public String mediaType;
    public String picURL;
    public String videoPlayAddress;

    public PdCommentPictureInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.picURL);
        parcel.writeString(this.largePicURL);
        parcel.writeString(this.mediaType);
        parcel.writeString(this.mediaId);
        parcel.writeString(this.mediaLength);
        parcel.writeString(this.videoPlayAddress);
    }

    protected PdCommentPictureInfo(Parcel parcel) {
        this.picURL = parcel.readString();
        this.largePicURL = parcel.readString();
        this.mediaType = parcel.readString();
        this.mediaId = parcel.readString();
        this.mediaLength = parcel.readString();
        this.videoPlayAddress = parcel.readString();
    }
}
