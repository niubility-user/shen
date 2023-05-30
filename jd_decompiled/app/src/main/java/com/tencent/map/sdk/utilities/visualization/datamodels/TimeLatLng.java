package com.tencent.map.sdk.utilities.visualization.datamodels;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class TimeLatLng implements Parcelable {
    public static final Parcelable.Creator<TimeLatLng> CREATOR = new a();
    private static final int DEFAULT_TIME = 0;
    private LatLng mPoint;
    private int mTime;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<TimeLatLng> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public TimeLatLng createFromParcel(@NonNull Parcel parcel) {
            return new TimeLatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public TimeLatLng[] newArray(int i2) {
            return new TimeLatLng[i2];
        }
    }

    public TimeLatLng(Parcel parcel) {
        this.mPoint = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.mTime = parcel.readInt();
    }

    public TimeLatLng(LatLng latLng) {
        this(latLng, 0);
    }

    public TimeLatLng(LatLng latLng, int i2) {
        setPoint(latLng);
        setTime(i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TimeLatLng) {
            if (obj == this) {
                return true;
            }
            LatLng latLng = this.mPoint;
            TimeLatLng timeLatLng = (TimeLatLng) obj;
            return latLng != null ? latLng.equals(timeLatLng.mPoint) && this.mTime == timeLatLng.mTime : timeLatLng.mPoint == null && this.mTime == timeLatLng.mTime;
        }
        return false;
    }

    public LatLng getPoint() {
        return this.mPoint;
    }

    public int getTime() {
        return this.mTime;
    }

    public int hashCode() {
        LatLng latLng = this.mPoint;
        if (latLng == null) {
            double d = this.mTime;
            Double.isNaN(d);
            return (int) (d * 1000000.0d);
        }
        int hashCode = latLng.hashCode();
        double d2 = this.mTime;
        Double.isNaN(d2);
        return hashCode + ((int) (d2 * 1000000.0d));
    }

    public void setPoint(LatLng latLng) {
        this.mPoint = latLng;
    }

    public void setTime(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mTime = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.mPoint, i2);
        parcel.writeInt(this.mTime);
    }
}
