package com.tencent.map.sdk.utilities.heatmap;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class WeightedLatLng extends com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng {
    public static final Parcelable.Creator<WeightedLatLng> CREATOR = new a();

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
        super(parcel);
    }

    public WeightedLatLng(LatLng latLng) {
        super(latLng);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        super(latLng, d);
    }

    @Override // com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof WeightedLatLng) {
            if (obj == this) {
                return true;
            }
            LatLng point2 = getPoint();
            double intensity = getIntensity();
            WeightedLatLng weightedLatLng = (WeightedLatLng) obj;
            return point2 != null ? point2.equals(weightedLatLng.getPoint()) && intensity == weightedLatLng.getIntensity() : weightedLatLng.getPoint() == null && intensity == weightedLatLng.getIntensity();
        }
        return false;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }
}
