package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class CheckPhoneNumberResult implements Parcelable {
    public static final Parcelable.Creator<CheckPhoneNumberResult> CREATOR = new Parcelable.Creator<CheckPhoneNumberResult>() { // from class: com.unionpay.tsmservice.mi.result.CheckPhoneNumberResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CheckPhoneNumberResult createFromParcel(Parcel parcel) {
            return new CheckPhoneNumberResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CheckPhoneNumberResult[] newArray(int i2) {
            return new CheckPhoneNumberResult[i2];
        }
    };
    private String phoneNumber;
    private String verifyMsg;
    private String verifyStatus;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private String phoneNumber;
        private String verifyMsg;
        private String verifyStatus;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public final CheckPhoneNumberResult build() {
            CheckPhoneNumberResult checkPhoneNumberResult = new CheckPhoneNumberResult();
            checkPhoneNumberResult.setVerifyStatus(this.verifyStatus);
            checkPhoneNumberResult.setVerifyMsg(this.verifyMsg);
            checkPhoneNumberResult.setPhoneNumber(this.phoneNumber);
            return checkPhoneNumberResult;
        }

        public final Builder phoneNumber(String str) {
            this.phoneNumber = str;
            return this;
        }

        public final Builder verifyMsg(String str) {
            this.verifyMsg = str;
            return this;
        }

        public final Builder verifyStatus(String str) {
            this.verifyStatus = str;
            return this;
        }
    }

    public CheckPhoneNumberResult() {
    }

    protected CheckPhoneNumberResult(Parcel parcel) {
        this.verifyStatus = parcel.readString();
        this.verifyMsg = parcel.readString();
        this.phoneNumber = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getVerifyMsg() {
        return this.verifyMsg;
    }

    public String getVerifyStatus() {
        return this.verifyStatus;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setVerifyMsg(String str) {
        this.verifyMsg = str;
    }

    public void setVerifyStatus(String str) {
        this.verifyStatus = str;
    }

    public String toString() {
        return "CheckPhoneNumberResult{verifyStatus='" + this.verifyStatus + "', verifyMsg='" + this.verifyMsg + "', phoneNumber='" + this.phoneNumber + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.verifyStatus);
        parcel.writeString(this.verifyMsg);
        parcel.writeString(this.phoneNumber);
    }
}
