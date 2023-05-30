package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdAnswerInfo implements Parcelable {
    public static final Parcelable.Creator<PdAnswerInfo> CREATOR = new Parcelable.Creator<PdAnswerInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdAnswerInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdAnswerInfo createFromParcel(Parcel parcel) {
            return new PdAnswerInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdAnswerInfo[] newArray(int i2) {
            return new PdAnswerInfo[i2];
        }
    };
    public String content;
    public String nickName;
    public String systemId;
    public String type;
    public String typeIcon;
    public String userImg;

    public PdAnswerInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.content);
        parcel.writeString(this.type);
        parcel.writeString(this.nickName);
        parcel.writeString(this.userImg);
        parcel.writeString(this.typeIcon);
        parcel.writeString(this.systemId);
    }

    protected PdAnswerInfo(Parcel parcel) {
        this.content = parcel.readString();
        this.type = parcel.readString();
        this.nickName = parcel.readString();
        this.userImg = parcel.readString();
        this.typeIcon = parcel.readString();
        this.systemId = parcel.readString();
    }
}
