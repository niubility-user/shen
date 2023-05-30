package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes15.dex */
public class PdQuestionInfo implements Parcelable {
    public static final Parcelable.Creator<PdQuestionInfo> CREATOR = new Parcelable.Creator<PdQuestionInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdQuestionInfo createFromParcel(Parcel parcel) {
            return new PdQuestionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdQuestionInfo[] newArray(int i2) {
            return new PdQuestionInfo[i2];
        }
    };
    public String answerCountText;
    public List<PdAnswerInfo> answerList;
    public String content;
    public String questionIcon;

    public PdQuestionInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.content);
        parcel.writeString(this.answerCountText);
        parcel.writeTypedList(this.answerList);
        parcel.writeString(this.questionIcon);
    }

    protected PdQuestionInfo(Parcel parcel) {
        this.content = parcel.readString();
        this.answerCountText = parcel.readString();
        this.answerList = parcel.createTypedArrayList(PdAnswerInfo.CREATOR);
        this.questionIcon = parcel.readString();
    }
}
