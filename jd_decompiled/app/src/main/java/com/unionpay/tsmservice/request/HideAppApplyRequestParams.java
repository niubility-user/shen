package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class HideAppApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator<HideAppApplyRequestParams> CREATOR = new Parcelable.Creator<HideAppApplyRequestParams>() { // from class: com.unionpay.tsmservice.request.HideAppApplyRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HideAppApplyRequestParams createFromParcel(Parcel parcel) {
            return new HideAppApplyRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HideAppApplyRequestParams[] newArray(int i2) {
            return new HideAppApplyRequestParams[i2];
        }
    };
    private String mApplyId;

    public HideAppApplyRequestParams() {
    }

    public HideAppApplyRequestParams(Parcel parcel) {
        super(parcel);
        this.mApplyId = parcel.readString();
    }

    public HideAppApplyRequestParams(String str) {
        this.mApplyId = str;
    }

    public String getApplyId() {
        return this.mApplyId;
    }

    public void setApplyId(String str) {
        this.mApplyId = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.mApplyId);
    }
}
