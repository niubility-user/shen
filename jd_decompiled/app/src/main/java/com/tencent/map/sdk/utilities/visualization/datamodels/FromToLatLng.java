package com.tencent.map.sdk.utilities.visualization.datamodels;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class FromToLatLng implements Parcelable {
    public static final Parcelable.Creator<FromToLatLng> CREATOR = new a();
    private static final double DEFAULT_ARC = 45.0d;
    private double mArc;
    private LatLng mEndPoint;
    private LatLng mStartPoint;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<FromToLatLng> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public FromToLatLng createFromParcel(@NonNull Parcel parcel) {
            return new FromToLatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public FromToLatLng[] newArray(int i2) {
            return new FromToLatLng[i2];
        }
    }

    public FromToLatLng(Parcel parcel) {
        this.mStartPoint = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.mEndPoint = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.mArc = parcel.readDouble();
    }

    public FromToLatLng(LatLng latLng, LatLng latLng2) {
        this(latLng, latLng2, DEFAULT_ARC);
    }

    private FromToLatLng(LatLng latLng, LatLng latLng2, double d) {
        setPoint(latLng, latLng2);
        setArc(d);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof FromToLatLng) {
            if (obj == this) {
                return true;
            }
            LatLng latLng = this.mStartPoint;
            if (latLng == null || this.mEndPoint == null) {
                FromToLatLng fromToLatLng = (FromToLatLng) obj;
                return fromToLatLng.mStartPoint == null && this.mArc == fromToLatLng.mArc;
            }
            FromToLatLng fromToLatLng2 = (FromToLatLng) obj;
            return latLng.equals(fromToLatLng2.mStartPoint) && this.mEndPoint.equals(fromToLatLng2.mEndPoint) && this.mArc == fromToLatLng2.mArc;
        }
        return false;
    }

    public double getArc() {
        return this.mArc;
    }

    public LatLng getEndPoint() {
        return this.mEndPoint;
    }

    public LatLng getStartPoint() {
        return this.mStartPoint;
    }

    public int hashCode() {
        LatLng latLng = this.mStartPoint;
        return (latLng == null || this.mEndPoint == null) ? (int) (this.mArc * 1000000.0d) : latLng.hashCode() + this.mEndPoint.hashCode() + ((int) (this.mArc * 1000000.0d));
    }

    public void setArc(double d) {
        if (d <= 0.0d || d > 90.0d) {
            d = DEFAULT_ARC;
        }
        this.mArc = d;
    }

    public void setPoint(LatLng latLng, LatLng latLng2) {
        this.mStartPoint = latLng;
        this.mEndPoint = latLng2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.mStartPoint, i2);
        parcel.writeParcelable(this.mEndPoint, i2);
        parcel.writeDouble(this.mArc);
    }
}
