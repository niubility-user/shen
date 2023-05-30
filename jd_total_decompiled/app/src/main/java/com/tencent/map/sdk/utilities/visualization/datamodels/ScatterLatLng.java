package com.tencent.map.sdk.utilities.visualization.datamodels;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class ScatterLatLng implements Parcelable {
    public static final Parcelable.Creator<ScatterLatLng> CREATOR = new a();
    private static final double DEFAULT_INTENSITY = 1.0d;
    private static final int DEFAULT_TYPE = 0;
    private double mIntensity;
    private LatLng mPoint;
    private int mType;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<ScatterLatLng> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ScatterLatLng createFromParcel(@NonNull Parcel parcel) {
            return new ScatterLatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ScatterLatLng[] newArray(int i2) {
            return new ScatterLatLng[i2];
        }
    }

    public ScatterLatLng(Parcel parcel) {
        this.mPoint = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.mType = parcel.readInt();
        this.mIntensity = parcel.readDouble();
    }

    public ScatterLatLng(LatLng latLng) {
        this(latLng, 0, 1.0d);
    }

    public ScatterLatLng(LatLng latLng, double d) {
        setPoint(latLng);
        setType(0);
        setIntensity(d);
    }

    public ScatterLatLng(LatLng latLng, int i2) {
        setPoint(latLng);
        setType(i2);
        setIntensity(1.0d);
    }

    public ScatterLatLng(LatLng latLng, int i2, double d) {
        setPoint(latLng);
        setType(i2);
        setIntensity(d);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ScatterLatLng) {
            if (obj == this) {
                return true;
            }
            LatLng latLng = this.mPoint;
            ScatterLatLng scatterLatLng = (ScatterLatLng) obj;
            return latLng != null ? latLng.equals(scatterLatLng.mPoint) && this.mType == scatterLatLng.mType && this.mIntensity == scatterLatLng.mIntensity : scatterLatLng.mPoint == null && this.mType == scatterLatLng.mType && this.mIntensity == scatterLatLng.mIntensity;
        }
        return false;
    }

    public double getIntensity() {
        return this.mIntensity;
    }

    public LatLng getPoint() {
        return this.mPoint;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        int i2;
        LatLng latLng = this.mPoint;
        if (latLng != null) {
            int hashCode = latLng.hashCode();
            double d = this.mType;
            Double.isNaN(d);
            i2 = hashCode + ((int) (d * 1000000.0d));
        } else {
            double d2 = this.mType;
            Double.isNaN(d2);
            i2 = (int) (d2 * 1000000.0d);
        }
        return i2 + ((int) (this.mIntensity * 1000000.0d));
    }

    public void setIntensity(double d) {
        this.mIntensity = d;
    }

    public void setPoint(LatLng latLng) {
        this.mPoint = latLng;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.mPoint, i2);
        parcel.writeInt(this.mType);
        parcel.writeDouble(this.mIntensity);
    }
}
