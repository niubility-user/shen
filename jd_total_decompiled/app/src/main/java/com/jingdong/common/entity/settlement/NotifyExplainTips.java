package com.jingdong.common.entity.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class NotifyExplainTips implements Serializable, Parcelable {
    public static final Parcelable.Creator<NotifyExplainTips> CREATOR = new Parcelable.Creator<NotifyExplainTips>() { // from class: com.jingdong.common.entity.settlement.NotifyExplainTips.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotifyExplainTips createFromParcel(Parcel parcel) {
            return new NotifyExplainTips(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotifyExplainTips[] newArray(int i2) {
            return new NotifyExplainTips[i2];
        }
    };
    public String content;
    public String title;

    public NotifyExplainTips() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isAvailable() {
        return (TextUtils.isEmpty(this.title) || TextUtils.isEmpty(this.content)) ? false : true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.content);
    }

    protected NotifyExplainTips(Parcel parcel) {
        this.title = parcel.readString();
        this.content = parcel.readString();
    }
}
