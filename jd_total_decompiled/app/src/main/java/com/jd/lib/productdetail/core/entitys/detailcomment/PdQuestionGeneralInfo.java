package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdQuestionGeneralInfo implements Parcelable {
    public static final Parcelable.Creator<PdQuestionGeneralInfo> CREATOR = new Parcelable.Creator<PdQuestionGeneralInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionGeneralInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdQuestionGeneralInfo createFromParcel(Parcel parcel) {
            return new PdQuestionGeneralInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdQuestionGeneralInfo[] newArray(int i2) {
            return new PdQuestionGeneralInfo[i2];
        }
    };
    public String deeplinkText;
    public String questionCountText;
    public String questionIcon;
    public String questionText;
    public String sum;

    public PdQuestionGeneralInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.questionText);
        parcel.writeString(this.questionIcon);
        parcel.writeString(this.questionCountText);
        parcel.writeString(this.deeplinkText);
        parcel.writeString(this.sum);
    }

    protected PdQuestionGeneralInfo(Parcel parcel) {
        this.questionText = parcel.readString();
        this.questionIcon = parcel.readString();
        this.questionCountText = parcel.readString();
        this.deeplinkText = parcel.readString();
        this.sum = parcel.readString();
    }
}
