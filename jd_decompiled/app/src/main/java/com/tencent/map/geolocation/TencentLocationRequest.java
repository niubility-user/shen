package com.tencent.map.geolocation;

import android.os.Bundle;
import android.text.TextUtils;
import c.t.m.g.j1;
import com.jingdong.common.jdmiaosha.utils.cache.Final;

/* loaded from: classes9.dex */
public final class TencentLocationRequest {
    public static final int GNSS_SOURCE_BEIDOU_FIRST = 21;
    public static final int GNSS_SOURCE_GPS_FIRST = 20;
    public static final int HIGH_ACCURACY_MODE = 10;
    public static final int ONLY_GPS_MODE = 12;
    public static final int ONLY_GPS_TIME_OUT = 8000;
    public static final int ONLY_NETWORK_MODE = 11;
    public static final int REQUEST_LEVEL_ADMIN_AREA = 3;
    public static final int REQUEST_LEVEL_GEO = 0;
    public static final int REQUEST_LEVEL_NAME = 1;
    public static final int REQUEST_LEVEL_POI = 4;
    public long a;
    public int b;

    /* renamed from: c */
    public boolean f16177c;
    public boolean d;

    /* renamed from: e */
    public boolean f16178e;

    /* renamed from: f */
    public boolean f16179f;

    /* renamed from: g */
    public long f16180g;

    /* renamed from: h */
    public int f16181h;

    /* renamed from: i */
    public int f16182i;

    /* renamed from: j */
    public String f16183j;

    /* renamed from: k */
    public String f16184k;

    /* renamed from: l */
    public Bundle f16185l;

    /* renamed from: m */
    public int f16186m;

    /* renamed from: n */
    public boolean f16187n;
    public int o;

    public TencentLocationRequest() {
    }

    public TencentLocationRequest(TencentLocationRequest tencentLocationRequest) {
        this.a = tencentLocationRequest.a;
        this.b = tencentLocationRequest.b;
        this.d = tencentLocationRequest.d;
        this.f16178e = tencentLocationRequest.f16178e;
        this.f16180g = tencentLocationRequest.f16180g;
        this.f16181h = tencentLocationRequest.f16181h;
        this.f16177c = tencentLocationRequest.f16177c;
        this.f16182i = tencentLocationRequest.f16182i;
        this.f16179f = tencentLocationRequest.f16179f;
        this.f16184k = tencentLocationRequest.f16184k;
        this.f16183j = tencentLocationRequest.f16183j;
        Bundle bundle = new Bundle();
        this.f16185l = bundle;
        bundle.putAll(tencentLocationRequest.f16185l);
        setLocMode(tencentLocationRequest.f16186m);
        this.f16187n = tencentLocationRequest.f16187n;
        this.o = tencentLocationRequest.o;
    }

    public static void copy(TencentLocationRequest tencentLocationRequest, TencentLocationRequest tencentLocationRequest2) {
        tencentLocationRequest.a = tencentLocationRequest2.a;
        tencentLocationRequest.b = tencentLocationRequest2.b;
        tencentLocationRequest.d = tencentLocationRequest2.d;
        tencentLocationRequest.f16178e = tencentLocationRequest2.f16178e;
        tencentLocationRequest.f16180g = tencentLocationRequest2.f16180g;
        tencentLocationRequest.f16182i = tencentLocationRequest2.f16182i;
        tencentLocationRequest.f16181h = tencentLocationRequest2.f16181h;
        tencentLocationRequest.f16179f = tencentLocationRequest2.f16179f;
        tencentLocationRequest.f16177c = tencentLocationRequest2.f16177c;
        tencentLocationRequest.f16184k = tencentLocationRequest2.f16184k;
        tencentLocationRequest.f16183j = tencentLocationRequest2.f16183j;
        tencentLocationRequest.f16185l.clear();
        tencentLocationRequest.f16185l.putAll(tencentLocationRequest2.f16185l);
        tencentLocationRequest.f16186m = tencentLocationRequest2.f16186m;
        tencentLocationRequest.f16187n = tencentLocationRequest2.f16187n;
        tencentLocationRequest.o = tencentLocationRequest2.o;
    }

    public static TencentLocationRequest create() {
        TencentLocationRequest tencentLocationRequest = new TencentLocationRequest();
        tencentLocationRequest.a = Final.FIVE_SECOND;
        tencentLocationRequest.b = 3;
        tencentLocationRequest.d = true;
        tencentLocationRequest.f16178e = false;
        tencentLocationRequest.f16182i = 20;
        tencentLocationRequest.f16179f = false;
        tencentLocationRequest.f16180g = Long.MAX_VALUE;
        tencentLocationRequest.f16181h = Integer.MAX_VALUE;
        tencentLocationRequest.f16177c = true;
        tencentLocationRequest.f16184k = "";
        tencentLocationRequest.f16183j = "";
        tencentLocationRequest.f16185l = new Bundle();
        tencentLocationRequest.f16186m = 10;
        tencentLocationRequest.f16187n = false;
        tencentLocationRequest.o = 5000;
        return tencentLocationRequest;
    }

    public final Bundle getExtras() {
        return this.f16185l;
    }

    public final int getGnssSource() {
        return this.f16182i;
    }

    public final int getGpsFirstTimeOut() {
        return this.o;
    }

    public final long getInterval() {
        return this.a;
    }

    public final int getLocMode() {
        return this.f16186m;
    }

    public final String getPhoneNumber() {
        String string = this.f16185l.getString("phoneNumber");
        return string == null ? "" : string;
    }

    public final String getQQ() {
        return this.f16184k;
    }

    public final int getRequestLevel() {
        return this.b;
    }

    public final String getSmallAppKey() {
        return this.f16183j;
    }

    public final boolean isAllowCache() {
        return this.d;
    }

    public final boolean isAllowDirection() {
        return this.f16178e;
    }

    public final boolean isAllowGPS() {
        return this.f16177c;
    }

    public final boolean isGpsFirst() {
        return this.f16187n;
    }

    public final boolean isIndoorLocationMode() {
        return this.f16179f;
    }

    public final TencentLocationRequest setAllowCache(boolean z) {
        this.d = z;
        return this;
    }

    public final TencentLocationRequest setAllowDirection(boolean z) {
        this.f16178e = z;
        return this;
    }

    public final TencentLocationRequest setAllowGPS(boolean z) {
        if (this.b == 10) {
            this.f16177c = z;
        }
        return this;
    }

    public final TencentLocationRequest setGnssSource(int i2) {
        if (i2 == 21 || i2 == 20) {
            this.f16182i = i2;
            return this;
        }
        throw new IllegalArgumentException("gnss_source: " + i2 + " not supported!");
    }

    public final TencentLocationRequest setGpsFirst(boolean z) {
        this.f16187n = z;
        this.f16177c = z || this.f16177c;
        return this;
    }

    public final TencentLocationRequest setGpsFirstTimeOut(int i2) {
        if (i2 >= 60000) {
            this.o = 60000;
        } else if (i2 < 0) {
            throw new IllegalArgumentException("GpsFirstTimeOut illegalArgument, time should 0");
        } else {
            this.o = i2;
        }
        return this;
    }

    public final TencentLocationRequest setIndoorLocationMode(boolean z) {
        this.f16179f = z;
        return this;
    }

    public final TencentLocationRequest setInterval(long j2) {
        if (j2 >= 0) {
            this.a = j2;
            return this;
        }
        throw new IllegalArgumentException("interval should >= 0");
    }

    public final TencentLocationRequest setLocMode(int i2) {
        boolean z;
        if (!j1.f(i2)) {
            throw new IllegalArgumentException("locMode: " + i2 + " not supported!");
        }
        this.f16186m = i2;
        if (i2 != 11) {
            z = i2 == 12;
            return this;
        }
        this.f16177c = z;
        return this;
    }

    public final TencentLocationRequest setPhoneNumber(String str) {
        if (str == null) {
            str = "";
        }
        this.f16185l.putString("phoneNumber", str);
        return this;
    }

    public final TencentLocationRequest setQQ(String str) {
        this.f16184k = str;
        return this;
    }

    public final TencentLocationRequest setRequestLevel(int i2) {
        if (j1.d(i2)) {
            this.b = i2;
            return this;
        }
        throw new IllegalArgumentException("request_level: " + i2 + " not supported!");
    }

    public final TencentLocationRequest setSmallAppKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f16183j = str;
        }
        return this;
    }

    public final String toString() {
        return "TencentLocationRequest {interval = " + this.a + "ms , level = " + this.b + ", LocMode = " + this.f16186m + ", allowGps = " + this.f16177c + ", isGPsFirst = " + this.f16187n + ", GpsFirstTimeOut = " + this.o + ", allowDirection = " + this.f16178e + ", isIndoorMode = " + this.f16179f + ", QQ = " + this.f16184k + "}";
    }
}
