package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class GuideInfo implements Parcelable, ICheckNullObj {
    public static final Parcelable.Creator<GuideInfo> CREATOR = new Parcelable.Creator<GuideInfo>() { // from class: com.jd.lib.cashier.sdk.core.model.GuideInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GuideInfo createFromParcel(Parcel parcel) {
            return new GuideInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GuideInfo[] newArray(int i2) {
            return new GuideInfo[i2];
        }
    };
    public String guideOpType;
    public String guideTitle;
    public String guideUrl;

    public GuideInfo() {
        this.guideTitle = "";
        this.guideUrl = "";
        this.guideOpType = "";
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.guideTitle);
        parcel.writeString(this.guideUrl);
        parcel.writeString(this.guideOpType);
    }

    protected GuideInfo(Parcel parcel) {
        this.guideTitle = "";
        this.guideUrl = "";
        this.guideOpType = "";
        this.guideTitle = parcel.readString();
        this.guideUrl = parcel.readString();
        this.guideOpType = parcel.readString();
    }
}
