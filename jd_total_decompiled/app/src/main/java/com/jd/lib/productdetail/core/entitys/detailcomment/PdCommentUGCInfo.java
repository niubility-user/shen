package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdCommentUGCInfo implements Parcelable {
    public static final Parcelable.Creator<PdCommentUGCInfo> CREATOR = new Parcelable.Creator<PdCommentUGCInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentUGCInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentUGCInfo createFromParcel(Parcel parcel) {
            return new PdCommentUGCInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentUGCInfo[] newArray(int i2) {
            return new PdCommentUGCInfo[i2];
        }
    };
    public String imageUrl;
    public String text;

    public PdCommentUGCInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.text);
    }

    protected PdCommentUGCInfo(Parcel parcel) {
        this.imageUrl = parcel.readString();
        this.text = parcel.readString();
    }
}
