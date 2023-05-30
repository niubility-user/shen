package c.t.m.g;

import android.os.Bundle;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentPoi;
import java.util.List;

/* loaded from: classes.dex */
public class w2 implements TencentLocation {

    /* renamed from: h  reason: collision with root package name */
    public static final w2 f726h = new w2();

    /* renamed from: g  reason: collision with root package name */
    public TencentLocation f727g;

    public w2() {
    }

    public w2(TencentLocation tencentLocation) {
        this.f727g = tencentLocation;
    }

    public final float a() {
        float max = (getFakeReason() & 1) != 0 ? Math.max(0.0f, 0.99f) : 0.0f;
        if ((getFakeReason() & 2) != 0) {
            max = Math.max(max, 0.8f);
        }
        if ((getFakeReason() & 4) != 0) {
            max = Math.max(max, 0.8f);
        }
        if ((getFakeReason() & 8) != 0) {
            max = Math.max(max, 0.9f);
        }
        if ((getFakeReason() & 16) != 0) {
            max = Math.max(max, 0.9f);
        }
        if ((getFakeReason() & 32) != 0) {
            max = Math.max(max, 0.8f);
        }
        return (getFakeReason() & 64) != 0 ? Math.max(max, 0.9f) : max;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getAccuracy() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0f;
        }
        return tencentLocation.getAccuracy();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getAddress() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getAddress();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getAltitude() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0d;
        }
        return tencentLocation.getAltitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Integer getAreaStat() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getAreaStat();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getBearing() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0f;
        }
        return tencentLocation.getBearing();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCity() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getCity();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityCode() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getCityCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityPhoneCode() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getCityPhoneCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getCoordinateType() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0;
        }
        return tencentLocation.getCoordinateType();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getDirection() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0d;
        }
        return tencentLocation.getDirection();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getDistrict() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getDistrict();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getElapsedRealtime() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0L;
        }
        return tencentLocation.getElapsedRealtime();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Bundle getExtra() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getExtra();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getFakeProbability() {
        return a();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getFakeReason() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0;
        }
        return tencentLocation.getFakeReason();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getGPSRssi() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0;
        }
        return tencentLocation.getGPSRssi();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingFloor() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getIndoorBuildingFloor();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingId() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getIndoorBuildingId();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getIndoorLocationType() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0;
        }
        return tencentLocation.getIndoorLocationType();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLatitude() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0d;
        }
        return tencentLocation.getLatitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLongitude() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0d;
        }
        return tencentLocation.getLongitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getName() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getName();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getNation() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getNation();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getNationCode() {
        return 0;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public List<TencentPoi> getPoiList() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getPoiList();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvider() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getProvider();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvince() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getProvince();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getSourceProvider() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getSourceProvider();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getSpeed() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0.0f;
        }
        return tencentLocation.getSpeed();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreet() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getStreet();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreetNo() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getStreetNo();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getTime() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0L;
        }
        return tencentLocation.getTime();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getTown() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getTown();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getVillage() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getVillage();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getadCode() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return null;
        }
        return tencentLocation.getadCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int isMockGps() {
        TencentLocation tencentLocation = this.f727g;
        if (tencentLocation == null) {
            return 0;
        }
        return tencentLocation.isMockGps();
    }
}
