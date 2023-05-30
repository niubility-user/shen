package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class QueryPromotionResult implements Parcelable {
    public static final Parcelable.Creator<QueryPromotionResult> CREATOR = new Parcelable.Creator<QueryPromotionResult>() { // from class: com.unionpay.tsmservice.mi.result.QueryPromotionResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final QueryPromotionResult createFromParcel(Parcel parcel) {
            return new QueryPromotionResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final QueryPromotionResult[] newArray(int i2) {
            return new QueryPromotionResult[i2];
        }
    };
    private String mPromotions;

    public QueryPromotionResult() {
    }

    public QueryPromotionResult(Parcel parcel) {
        this.mPromotions = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPromotions() {
        return this.mPromotions;
    }

    public void setPromotions(String str) {
        this.mPromotions = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mPromotions);
    }
}
