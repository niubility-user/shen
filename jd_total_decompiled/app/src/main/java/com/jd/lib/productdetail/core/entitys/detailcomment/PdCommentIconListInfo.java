package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PdCommentIconListInfo implements Parcelable {
    private static final String COMMENTOFFICER_ICON_ID = "commentOfficer";
    public static final Parcelable.Creator<PdCommentIconListInfo> CREATOR = new Parcelable.Creator<PdCommentIconListInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentIconListInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentIconListInfo createFromParcel(Parcel parcel) {
            return new PdCommentIconListInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentIconListInfo[] newArray(int i2) {
            return new PdCommentIconListInfo[i2];
        }
    };
    private static final String PLUS_ICON_ID = "plus";
    private static final String SKIN_ICON_ID = "skin";
    public String iconId;
    public String iconJumpUrl;
    public String iconTagId;
    public String iconTagType;
    public String iconType;
    public String userImgSwitch;

    public PdCommentIconListInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isCommentOfficer() {
        return TextUtils.equals(this.iconId, COMMENTOFFICER_ICON_ID);
    }

    public boolean isPlus() {
        return TextUtils.equals(this.iconId, PLUS_ICON_ID);
    }

    public boolean isSkinIcon() {
        return TextUtils.equals(this.iconId, "skin");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.iconTagId);
        parcel.writeString(this.iconId);
        parcel.writeString(this.iconJumpUrl);
        parcel.writeString(this.iconType);
        parcel.writeString(this.userImgSwitch);
        parcel.writeString(this.iconTagType);
    }

    protected PdCommentIconListInfo(Parcel parcel) {
        this.iconTagId = parcel.readString();
        this.iconId = parcel.readString();
        this.iconJumpUrl = parcel.readString();
        this.iconType = parcel.readString();
        this.userImgSwitch = parcel.readString();
        this.iconTagType = parcel.readString();
    }
}
