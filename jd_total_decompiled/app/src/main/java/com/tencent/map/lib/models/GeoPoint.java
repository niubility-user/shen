package com.tencent.map.lib.models;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class GeoPoint implements Parcelable {
    public static final Parcelable.Creator<GeoPoint> CREATOR = new a();
    private int mLatitudeE6;
    private int mLongitudeE6;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<GeoPoint> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public GeoPoint createFromParcel(Parcel parcel) {
            return new GeoPoint(parcel, (a) null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public GeoPoint[] newArray(int i2) {
            return new GeoPoint[i2];
        }
    }

    public GeoPoint() {
        this.mLatitudeE6 = -85000001;
        this.mLongitudeE6 = -180000001;
    }

    public GeoPoint(int i2, int i3) {
        this.mLatitudeE6 = i2;
        this.mLongitudeE6 = i3;
    }

    private GeoPoint(Parcel parcel) {
        this.mLatitudeE6 = parcel.readInt();
        this.mLongitudeE6 = parcel.readInt();
    }

    public /* synthetic */ GeoPoint(Parcel parcel, a aVar) {
        this(parcel);
    }

    public GeoPoint(GeoPoint geoPoint) {
        this();
        this.mLatitudeE6 = geoPoint.mLatitudeE6;
        this.mLongitudeE6 = geoPoint.mLongitudeE6;
    }

    public GeoPoint(LatLng latLng) {
        this.mLatitudeE6 = (int) (latLng.latitude * 1000000.0d);
        this.mLongitudeE6 = (int) (latLng.longitude * 1000000.0d);
    }

    public static GeoPoint formString(String str) {
        GeoPoint geoPoint = new GeoPoint();
        if (str != null) {
            try {
                String[] split = str.split(DYConstants.DY_REGEX_COMMA);
                if (split != null && split.length == 2) {
                    geoPoint.mLatitudeE6 = Integer.parseInt(split[0]);
                    geoPoint.mLongitudeE6 = Integer.parseInt(split[1]);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return geoPoint;
    }

    public static GeoPoint from(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new GeoPoint(latLng);
    }

    public static List<GeoPoint> from(List<LatLng> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList(0);
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            LatLng latLng = list.get(i2);
            if (latLng != null) {
                arrayList.add(from(latLng));
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GeoPoint) {
            GeoPoint geoPoint = (GeoPoint) obj;
            return this.mLatitudeE6 == geoPoint.mLatitudeE6 && this.mLongitudeE6 == geoPoint.mLongitudeE6;
        }
        return false;
    }

    public int getLatitudeE6() {
        return this.mLatitudeE6;
    }

    public int getLongitudeE6() {
        return this.mLongitudeE6;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.mLatitudeE6 = geoPoint.getLatitudeE6();
        this.mLongitudeE6 = geoPoint.getLongitudeE6();
    }

    public void setLatitudeE6(int i2) {
        this.mLatitudeE6 = i2;
    }

    public void setLongitudeE6(int i2) {
        this.mLongitudeE6 = i2;
    }

    public LatLng toLatLng() {
        double d = this.mLatitudeE6;
        Double.isNaN(d);
        double d2 = this.mLongitudeE6;
        Double.isNaN(d2);
        return new LatLng(d / 1000000.0d, d2 / 1000000.0d);
    }

    public Point toPoint() {
        return new Point(getLongitudeE6(), getLatitudeE6());
    }

    public String toString() {
        return this.mLatitudeE6 + DYConstants.DY_REGEX_COMMA + this.mLongitudeE6;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.mLatitudeE6);
        parcel.writeInt(this.mLongitudeE6);
    }
}
