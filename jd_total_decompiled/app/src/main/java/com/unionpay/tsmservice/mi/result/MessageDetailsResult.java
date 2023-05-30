package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.mi.data.MessageDetail;

/* loaded from: classes11.dex */
public class MessageDetailsResult implements Parcelable {
    public static final Parcelable.Creator<MessageDetailsResult> CREATOR = new Parcelable.Creator<MessageDetailsResult>() { // from class: com.unionpay.tsmservice.mi.result.MessageDetailsResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MessageDetailsResult createFromParcel(Parcel parcel) {
            return new MessageDetailsResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MessageDetailsResult[] newArray(int i2) {
            return new MessageDetailsResult[i2];
        }
    };
    private String mLastUpdatedTag;
    private MessageDetail[] mMessageDetails;

    public MessageDetailsResult() {
        this.mLastUpdatedTag = "";
    }

    public MessageDetailsResult(Parcel parcel) {
        this.mLastUpdatedTag = "";
        this.mMessageDetails = (MessageDetail[]) parcel.createTypedArray(MessageDetail.CREATOR);
        this.mLastUpdatedTag = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getLastUpdatedTag() {
        return this.mLastUpdatedTag;
    }

    public MessageDetail[] getMessageDetails() {
        return this.mMessageDetails;
    }

    public void setLastUpdatedTag(String str) {
        this.mLastUpdatedTag = str;
    }

    public void setMessageDetails(MessageDetail[] messageDetailArr) {
        this.mMessageDetails = messageDetailArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedArray(this.mMessageDetails, i2);
        parcel.writeString(this.mLastUpdatedTag);
    }
}
