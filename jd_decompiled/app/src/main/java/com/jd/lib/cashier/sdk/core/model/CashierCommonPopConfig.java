package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.TextUtils;

/* loaded from: classes14.dex */
public class CashierCommonPopConfig implements Parcelable, ICheckNullObj, Cloneable {
    public static final Parcelable.Creator<CashierCommonPopConfig> CREATOR = new Parcelable.Creator<CashierCommonPopConfig>() { // from class: com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CashierCommonPopConfig createFromParcel(Parcel parcel) {
            return new CashierCommonPopConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CashierCommonPopConfig[] newArray(int i2) {
            return new CashierCommonPopConfig[i2];
        }
    };
    public PopBusinessMap businessMap;
    public String cancelBtn;
    public String cancelBtnUrl;
    public String cancelOpType;
    public boolean cancelable;
    public String confirmBtn;
    public String confirmBtnUrl;
    public String confirmOpType;
    public String highLightString;
    public SpannableString highLightTitle;
    public String message;
    public String messageTwo;
    public String replacedMessage;
    public String subTitle;
    public String title;

    public CashierCommonPopConfig() {
        this.title = "";
        this.subTitle = "";
        this.message = "";
        this.messageTwo = "";
        this.confirmBtn = "";
        this.cancelBtn = "";
        this.confirmBtnUrl = "";
        this.cancelBtnUrl = "";
        this.confirmOpType = "";
        this.cancelOpType = "";
        this.replacedMessage = "";
        this.highLightString = "";
    }

    public boolean canDialogShow() {
        return (TextUtils.isEmpty(this.confirmBtn) && TextUtils.isEmpty(this.cancelBtn)) ? false : true;
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.businessMap == null) {
            this.businessMap = new PopBusinessMap();
        }
        this.businessMap.checkNullObjAndInit();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.subTitle);
        parcel.writeString(this.message);
        parcel.writeString(this.confirmBtn);
        parcel.writeString(this.cancelBtn);
        parcel.writeString(this.confirmBtnUrl);
        parcel.writeString(this.cancelBtnUrl);
        parcel.writeString(this.confirmOpType);
        parcel.writeString(this.cancelOpType);
        parcel.writeString(this.replacedMessage);
        parcel.writeString(this.highLightString);
        parcel.writeByte(this.cancelable ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.businessMap, i2);
    }

    protected CashierCommonPopConfig(Parcel parcel) {
        this.title = "";
        this.subTitle = "";
        this.message = "";
        this.messageTwo = "";
        this.confirmBtn = "";
        this.cancelBtn = "";
        this.confirmBtnUrl = "";
        this.cancelBtnUrl = "";
        this.confirmOpType = "";
        this.cancelOpType = "";
        this.replacedMessage = "";
        this.highLightString = "";
        this.title = parcel.readString();
        this.subTitle = parcel.readString();
        this.message = parcel.readString();
        this.confirmBtn = parcel.readString();
        this.cancelBtn = parcel.readString();
        this.confirmBtnUrl = parcel.readString();
        this.cancelBtnUrl = parcel.readString();
        this.confirmOpType = parcel.readString();
        this.cancelOpType = parcel.readString();
        this.replacedMessage = parcel.readString();
        this.highLightString = parcel.readString();
        this.cancelable = parcel.readByte() != 0;
        this.businessMap = (PopBusinessMap) parcel.readParcelable(PopBusinessMap.class.getClassLoader());
    }
}
