package com.jingdong.common.entity.settlement.global;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SettlementJSONObject extends JSONObject implements Serializable, Parcelable {
    public static final Parcelable.Creator<SettlementJSONObject> CREATOR = new Parcelable.Creator<SettlementJSONObject>() { // from class: com.jingdong.common.entity.settlement.global.SettlementJSONObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SettlementJSONObject createFromParcel(Parcel parcel) {
            return new SettlementJSONObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SettlementJSONObject[] newArray(int i2) {
            return new SettlementJSONObject[i2];
        }
    };

    public SettlementJSONObject() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }

    protected SettlementJSONObject(Parcel parcel) {
    }
}
