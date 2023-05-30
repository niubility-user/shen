package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class PopConfirmBtnMta implements Parcelable {
    public static final Parcelable.Creator<PopConfirmBtnMta> CREATOR = new Parcelable.Creator<PopConfirmBtnMta>() { // from class: com.jd.lib.cashier.sdk.core.model.PopConfirmBtnMta.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PopConfirmBtnMta createFromParcel(Parcel parcel) {
            return new PopConfirmBtnMta(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PopConfirmBtnMta[] newArray(int i2) {
            return new PopConfirmBtnMta[i2];
        }
    };
    private PopConfirmBtnJsonParam confirmBtnJsonParam;
    private String eventId;

    public PopConfirmBtnMta() {
        this.eventId = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEventId() {
        return this.eventId;
    }

    public PopConfirmBtnJsonParam getJsonParam() {
        return this.confirmBtnJsonParam;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    public void setJsonParam(PopConfirmBtnJsonParam popConfirmBtnJsonParam) {
        this.confirmBtnJsonParam = popConfirmBtnJsonParam;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.eventId);
        parcel.writeParcelable(this.confirmBtnJsonParam, i2);
    }

    protected PopConfirmBtnMta(Parcel parcel) {
        this.eventId = "";
        this.eventId = parcel.readString();
        this.confirmBtnJsonParam = (PopConfirmBtnJsonParam) parcel.readParcelable(PopConfirmBtnJsonParam.class.getClassLoader());
    }
}
