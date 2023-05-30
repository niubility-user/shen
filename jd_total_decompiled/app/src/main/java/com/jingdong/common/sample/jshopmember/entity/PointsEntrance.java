package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class PointsEntrance implements Parcelable {
    public static final Parcelable.Creator<PointsEntrance> CREATOR = new Parcelable.Creator<PointsEntrance>() { // from class: com.jingdong.common.sample.jshopmember.entity.PointsEntrance.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PointsEntrance createFromParcel(Parcel parcel) {
            return new PointsEntrance(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PointsEntrance[] newArray(int i2) {
            return new PointsEntrance[i2];
        }
    };
    public String earnPointsContent;
    public String earnPointsCount;
    public String earnPointsUrl;
    public String spendPointsContent;
    public String spendPointsCount;
    public String spendPointsUrl;
    public int tag;

    public PointsEntrance(JDJSONObject jDJSONObject) {
        this.tag = 0;
        if (jDJSONObject == null) {
            return;
        }
        this.earnPointsCount = jDJSONObject.optString("earnPointsCount");
        this.earnPointsUrl = jDJSONObject.optString("earnPointsUrl");
        this.earnPointsContent = jDJSONObject.optString("earnPointsContent");
        this.spendPointsCount = jDJSONObject.optString("spendPointsCount");
        this.spendPointsUrl = jDJSONObject.optString("spendPointsUrl");
        this.spendPointsContent = jDJSONObject.optString("spendPointsContent");
        this.tag = jDJSONObject.optInt("tag");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.earnPointsCount);
        parcel.writeString(this.earnPointsUrl);
        parcel.writeString(this.earnPointsContent);
        parcel.writeString(this.spendPointsCount);
        parcel.writeString(this.spendPointsUrl);
        parcel.writeString(this.spendPointsContent);
        parcel.writeInt(this.tag);
    }

    protected PointsEntrance(Parcel parcel) {
        this.tag = 0;
        this.earnPointsCount = parcel.readString();
        this.earnPointsUrl = parcel.readString();
        this.earnPointsContent = parcel.readString();
        this.spendPointsCount = parcel.readString();
        this.spendPointsUrl = parcel.readString();
        this.spendPointsContent = parcel.readString();
        this.tag = parcel.readInt();
    }
}
