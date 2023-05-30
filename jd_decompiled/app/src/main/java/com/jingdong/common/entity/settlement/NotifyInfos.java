package com.jingdong.common.entity.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class NotifyInfos implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotifyInfos> CREATOR = new Parcelable.Creator<NotifyInfos>() { // from class: com.jingdong.common.entity.settlement.NotifyInfos.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotifyInfos createFromParcel(Parcel parcel) {
            return new NotifyInfos(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotifyInfos[] newArray(int i2) {
            return new NotifyInfos[i2];
        }
    };
    public String clickNotifyMsg;
    public List<String> clickNotifyMsgs;
    public int clickNotifyType;
    public String clickNotifyUrl;
    public String clickTitle;
    public String notifyMessage;
    public String notifyMessageBgColor;
    public String notifyMessageBgImg;
    public String notifyMessageBgOpacity;
    public String notifyMessageColor;
    public String notifyMessageIcon;
    public Integer pointType;
    public NotifyExplainTips tips;

    public NotifyInfos() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.notifyMessage);
        parcel.writeString(this.notifyMessageBgColor);
        parcel.writeString(this.notifyMessageBgOpacity);
        parcel.writeString(this.notifyMessageIcon);
        parcel.writeString(this.notifyMessageColor);
        parcel.writeString(this.notifyMessageBgImg);
        parcel.writeInt(this.clickNotifyType);
        parcel.writeString(this.clickNotifyMsg);
        parcel.writeString(this.clickNotifyUrl);
        parcel.writeValue(this.pointType);
        parcel.writeParcelable(this.tips, i2);
    }

    protected NotifyInfos(Parcel parcel) {
        this.notifyMessage = parcel.readString();
        this.notifyMessageBgColor = parcel.readString();
        this.notifyMessageBgOpacity = parcel.readString();
        this.notifyMessageIcon = parcel.readString();
        this.notifyMessageColor = parcel.readString();
        this.notifyMessageBgImg = parcel.readString();
        this.clickNotifyType = parcel.readInt();
        this.clickNotifyMsg = parcel.readString();
        this.clickNotifyUrl = parcel.readString();
        Object readValue = parcel.readValue(Integer.class.getClassLoader());
        if (readValue instanceof Integer) {
            this.pointType = (Integer) readValue;
        }
        this.tips = (NotifyExplainTips) parcel.readParcelable(NotifyExplainTips.class.getClassLoader());
    }
}
