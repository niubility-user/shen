package c.t.m.g;

import android.os.Bundle;
import com.jd.dynamic.DYConstants;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentPoi;
import java.util.List;

/* loaded from: classes.dex */
public class o2 implements TencentLocation {
    public static final o2 q = new o2();

    /* renamed from: g  reason: collision with root package name */
    public int f563g;

    /* renamed from: h  reason: collision with root package name */
    public TencentLocation f564h;

    /* renamed from: i  reason: collision with root package name */
    public double f565i;

    /* renamed from: j  reason: collision with root package name */
    public double f566j;

    /* renamed from: k  reason: collision with root package name */
    public float f567k;

    /* renamed from: l  reason: collision with root package name */
    public float f568l;

    /* renamed from: m  reason: collision with root package name */
    public float f569m;

    /* renamed from: n  reason: collision with root package name */
    public float f570n;
    public String o;
    public long p;

    public o2() {
        this.f564h = w2.f726h;
        this.f563g = 404;
    }

    public o2(r1 r1Var) {
        this.f564h = w2.f726h;
        c(r1Var);
    }

    public o2(TencentLocation tencentLocation) {
        this.f564h = w2.f726h;
        try {
            this.f564h = new w2(tencentLocation);
        } catch (Exception unused) {
        }
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

    public void b(int i2) {
        this.f563g = i2;
    }

    public void c(r1 r1Var) {
        try {
            this.f563g = r1Var.f() <= 0.0d ? 5 : 0;
            this.f565i = r1Var.d();
            this.f566j = r1Var.e();
            this.f567k = (float) r1Var.b();
            this.f568l = (float) r1Var.a();
            this.f569m = (float) r1Var.c();
            this.f570n = (float) r1Var.h();
            this.o = r1Var.g();
            this.p = r1Var.i();
        } catch (Exception unused) {
        }
    }

    public void d(TencentLocation tencentLocation) {
        this.f564h = tencentLocation;
    }

    public int e() {
        return this.f563g;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getAccuracy() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f568l : tencentLocation.getAccuracy();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getAddress() {
        return this.f564h.getAddress();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getAltitude() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f567k : tencentLocation.getAltitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Integer getAreaStat() {
        return this.f564h.getAreaStat();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getBearing() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f569m : tencentLocation.getBearing();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCity() {
        return this.f564h.getCity();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityCode() {
        return this.f564h.getCityCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityPhoneCode() {
        return this.f564h.getCityPhoneCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getCoordinateType() {
        return this.f564h.getCoordinateType();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getDirection() {
        return this.f564h.getDirection();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getDistrict() {
        return this.f564h.getDistrict();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getElapsedRealtime() {
        return this.f564h.getElapsedRealtime();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Bundle getExtra() {
        return this.f564h.getExtra();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getFakeProbability() {
        return a();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getFakeReason() {
        return 0;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getGPSRssi() {
        return this.f564h.getGPSRssi();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingFloor() {
        return this.f564h.getIndoorBuildingFloor();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingId() {
        return this.f564h.getIndoorBuildingId();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getIndoorLocationType() {
        return this.f564h.getIndoorLocationType();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLatitude() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f565i : tencentLocation.getLatitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLongitude() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f566j : tencentLocation.getLongitude();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getName() {
        return this.f564h.getName();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getNation() {
        return this.f564h.getNation();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getNationCode() {
        return 0;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public List<TencentPoi> getPoiList() {
        return this.f564h.getPoiList();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvider() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.o : tencentLocation.getProvider();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvince() {
        return this.f564h.getProvince();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getSourceProvider() {
        TencentLocation tencentLocation = this.f564h;
        if (tencentLocation == w2.f726h) {
            return null;
        }
        return tencentLocation.getSourceProvider();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getSpeed() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.f570n : tencentLocation.getSpeed();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreet() {
        return this.f564h.getStreet();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreetNo() {
        return this.f564h.getStreetNo();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getTime() {
        TencentLocation tencentLocation = this.f564h;
        return tencentLocation == w2.f726h ? this.p : tencentLocation.getTime();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getTown() {
        return this.f564h.getTown();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getVillage() {
        return this.f564h.getVillage();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getadCode() {
        return this.f564h.getadCode();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int isMockGps() {
        return this.f564h.isMockGps();
    }

    public String toString() {
        return "TencentLocation{name=" + getName() + DYConstants.DY_REGEX_COMMA + "address=" + getAddress() + DYConstants.DY_REGEX_COMMA + "provider=" + getProvider() + DYConstants.DY_REGEX_COMMA + "latitude=" + getLatitude() + DYConstants.DY_REGEX_COMMA + "longitude=" + getLongitude() + DYConstants.DY_REGEX_COMMA + "altitude=" + getAltitude() + DYConstants.DY_REGEX_COMMA + "accuracy=" + getAccuracy() + DYConstants.DY_REGEX_COMMA + "cityCode=" + getCityCode() + DYConstants.DY_REGEX_COMMA + "areaStat=" + getAreaStat() + DYConstants.DY_REGEX_COMMA + "nation=" + getNation() + DYConstants.DY_REGEX_COMMA + "province=" + getProvince() + DYConstants.DY_REGEX_COMMA + "city=" + getCity() + DYConstants.DY_REGEX_COMMA + "district=" + getDistrict() + DYConstants.DY_REGEX_COMMA + "street=" + getStreet() + DYConstants.DY_REGEX_COMMA + "streetNo=" + getStreetNo() + DYConstants.DY_REGEX_COMMA + "town=" + getTown() + DYConstants.DY_REGEX_COMMA + "village=" + getVillage() + DYConstants.DY_REGEX_COMMA + "bearing=" + getBearing() + DYConstants.DY_REGEX_COMMA + "time=" + getTime() + DYConstants.DY_REGEX_COMMA + "}";
    }
}
