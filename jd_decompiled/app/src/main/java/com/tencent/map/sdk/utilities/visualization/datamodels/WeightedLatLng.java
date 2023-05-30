package com.tencent.map.sdk.utilities.visualization.datamodels;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class WeightedLatLng implements Parcelable {
    public static final Parcelable.Creator<WeightedLatLng> CREATOR = new a();
    private static final double DEFAULT_INTENSITY = 1.0d;
    private double mIntensity;
    private LatLng mPoint;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<WeightedLatLng> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public WeightedLatLng createFromParcel(@NonNull Parcel parcel) {
            return new WeightedLatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public WeightedLatLng[] newArray(int i2) {
            return new WeightedLatLng[i2];
        }
    }

    public WeightedLatLng(Parcel parcel) {
        this.mPoint = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.mIntensity = parcel.readDouble();
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        setPoint(latLng);
        setIntensity(d);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof WeightedLatLng) {
            if (obj == this) {
                return true;
            }
            LatLng latLng = this.mPoint;
            WeightedLatLng weightedLatLng = (WeightedLatLng) obj;
            return latLng != null ? latLng.equals(weightedLatLng.mPoint) && this.mIntensity == weightedLatLng.mIntensity : weightedLatLng.mPoint == null && this.mIntensity == weightedLatLng.mIntensity;
        }
        return false;
    }

    public double getIntensity() {
        return this.mIntensity;
    }

    public LatLng getPoint() {
        return this.mPoint;
    }

    public int hashCode() {
        LatLng latLng = this.mPoint;
        return latLng != null ? latLng.hashCode() + ((int) (this.mIntensity * 1000000.0d)) : (int) (this.mIntensity * 1000000.0d);
    }

    public void setIntensity(double d) {
        if (d < 0.0d) {
            d = 1.0d;
        }
        this.mIntensity = d;
    }

    public void setPoint(LatLng latLng) {
        this.mPoint = latLng;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.mPoint, i2);
        parcel.writeDouble(this.mIntensity);
    }
}
