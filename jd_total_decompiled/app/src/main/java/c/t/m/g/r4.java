package c.t.m.g;

import android.content.Context;
import android.os.Looper;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationBridge;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.util.SoUtils;

/* loaded from: classes.dex */
public class r4 implements TencentLocationBridge {
    public y4 a;
    public s3 b;

    public r4(Context context) {
        init(context);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public String getBuild() {
        return "220803";
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public int getCoordinateType() {
        return this.b.f0();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public TencentLocation getLastKnownLocation() {
        return this.b.k0();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public TencentLocation getPosition() {
        return h2.b(this.a).e();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public String getVersion() {
        return "7.4.9.official_1";
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void init(Context context) {
        y3.b(context);
        y3.c(true);
        this.a = y4.b(context);
        this.b = new s3(this.a);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public boolean isSupport() {
        return h2.b(this.a).f();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void removeUpdates(TencentLocationListener tencentLocationListener) {
        this.b.A(tencentLocationListener);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public int requestLocationUpdates(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        return this.b.f(tencentLocationRequest, tencentLocationListener, looper);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public int requestLocationWithScene(int i2, TencentLocationListener tencentLocationListener) {
        return this.b.d(i2, tencentLocationListener);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public int requestSingleFreshLocation(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        return this.b.H(tencentLocationRequest, tencentLocationListener, looper);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void setCoordinateType(int i2) {
        this.b.K(i2);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void setDebuggable(boolean z) {
        n4.b("CONF_USER_DEBUGGABLE", Boolean.valueOf(z));
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void setDeviceID(Context context, String str) {
        f0.g("LocationSDK", "location_device_id", str);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public int startDrEngine(int i2) {
        try {
            return h2.b(this.a).a(i2);
        } catch (Exception unused) {
            return -999;
        }
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public boolean startIndoorLocation() {
        return this.b.y0();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public boolean stopIndoorLocation() {
        return this.b.E0();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void stopLocationWithScene(int i2, TencentLocationListener tencentLocationListener) {
        this.b.L(i2, tencentLocationListener);
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void terminateDrEngine() {
        h2.b(this.a).h();
    }

    @Override // com.tencent.map.geolocation.TencentLocationBridge
    public void triggerCodeGuarder(boolean z) {
        SoUtils.fun_x(z);
    }
}
