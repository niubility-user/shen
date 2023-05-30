package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes15.dex */
public class PdCommentContentTagInfo implements Parcelable {
    public static final Parcelable.Creator<PdCommentContentTagInfo> CREATOR = new Parcelable.Creator<PdCommentContentTagInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentContentTagInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentContentTagInfo createFromParcel(Parcel parcel) {
            return new PdCommentContentTagInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentContentTagInfo[] newArray(int i2) {
            return new PdCommentContentTagInfo[i2];
        }
    };
    public List<String> bgColors;
    public String text;
    public String textColor;

    public PdCommentContentTagInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.text);
        parcel.writeString(this.textColor);
        parcel.writeStringList(this.bgColors);
    }

    protected PdCommentContentTagInfo(Parcel parcel) {
        this.text = parcel.readString();
        this.textColor = parcel.readString();
        this.bgColors = parcel.createStringArrayList();
    }
}
