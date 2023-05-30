package com.tencent.map.geolocation;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import c.t.m.g.j1;
import c.t.m.g.k0;
import c.t.m.g.o4;
import c.t.m.g.r4;

/* loaded from: classes.dex */
public final class TencentLocationManager {
    public static final int COORDINATE_TYPE_GCJ02 = 1;
    public static final int COORDINATE_TYPE_WGS84 = 0;
    public static int DR_TYPE_BIKE = 3;
    public static int DR_TYPE_WALK = 2;
    public static final int SIGN_IN_SCENE = 10;
    public static final int SPORT_SCENE = 11;
    public static final int TRANSPORT_SCENE = 12;

    /* renamed from: f  reason: collision with root package name */
    public static boolean f16172f;

    /* renamed from: g  reason: collision with root package name */
    public static TencentLocationManager f16173g;
    public Context d;
    public volatile boolean a = false;
    public final byte[] b = new byte[0];

    /* renamed from: e  reason: collision with root package name */
    public ServiceConnection f16175e = new ServiceConnection(this) { // from class: com.tencent.map.geolocation.TencentLocationManager.1
        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            o4.o("LOC", "s onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            o4.o("LOC", "s onServiceDisconnected");
        }
    };

    /* renamed from: c  reason: collision with root package name */
    public TencentLocationBridge f16174c = a();

    public TencentLocationManager(Context context) {
        this.d = context;
    }

    public static synchronized TencentLocationManager getInstance(Context context) {
        TencentLocationManager tencentLocationManager;
        synchronized (TencentLocationManager.class) {
            if (f16173g == null) {
                if (context == null) {
                    throw new NullPointerException("context is null");
                }
                if (context.getApplicationContext() == null) {
                    throw new NullPointerException("application context is null");
                }
                f16173g = new TencentLocationManager(context.getApplicationContext());
            }
            tencentLocationManager = f16173g;
        }
        return tencentLocationManager;
    }

    public static boolean getUserAgreePrivacy() {
        return f16172f;
    }

    public static void setUserAgreePrivacy(boolean z) {
        f16172f = z;
    }

    public final TencentLocationBridge a() {
        if (f16172f) {
            TencentLocationBridge tencentLocationBridge = this.f16174c;
            return tencentLocationBridge != null ? tencentLocationBridge : new r4(this.d);
        }
        return null;
    }

    public final void disableForegroundLocation(boolean z) {
        if (f16172f && this.a) {
            s.removeNotification = z;
            this.d.unbindService(this.f16175e);
            this.a = false;
            o4.o("LOC", "disableForegroundLocation");
        }
    }

    public final void enableForegroundLocation(int i2, Notification notification) {
        if (f16172f) {
            if (i2 <= 0 || notification == null) {
                throw new IllegalArgumentException("enableForegroundLocation illegalArgument");
            }
            if (this.a) {
                return;
            }
            Intent intent = new Intent(this.d, s.class);
            intent.putExtra("LocNotification", notification);
            intent.putExtra("LocNotificationId", i2);
            this.d.bindService(intent, this.f16175e, 1);
            this.a = true;
            o4.o("LOC", "enableForegroundLocation");
        }
    }

    public final String getBuild() {
        String build;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                build = a.getBuild();
            }
            return build;
        }
        return "";
    }

    public final int getCoordinateType() {
        synchronized (this.b) {
            if (f16172f) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                return a.getCoordinateType();
            }
            return -1;
        }
    }

    public final TencentLocation getDrPosition() {
        TencentLocation position;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                position = a.getPosition();
            }
            return position;
        }
        return null;
    }

    public final TencentLocation getLastKnownLocation() {
        TencentLocation lastKnownLocation;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                lastKnownLocation = a.getLastKnownLocation();
            }
            return lastKnownLocation;
        }
        return null;
    }

    public final String getVersion() {
        String version;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                version = a.getVersion();
            }
            return version;
        }
        return "";
    }

    public final boolean isDrSupport() {
        boolean isSupport;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                isSupport = a.isSupport();
            }
            return isSupport;
        }
        return false;
    }

    public final void removeUpdates(TencentLocationListener tencentLocationListener) {
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                a.removeUpdates(tencentLocationListener);
            }
        }
    }

    public final int requestLocationUpdates(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener) {
        if (f16172f) {
            return requestLocationUpdates(tencentLocationRequest, tencentLocationListener, Looper.myLooper());
        }
        return 4;
    }

    public final int requestLocationUpdates(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        int requestLocationUpdates;
        if (f16172f) {
            if (tencentLocationRequest != null) {
                if (tencentLocationListener != null) {
                    if (looper != null) {
                        synchronized (this.b) {
                            TencentLocationBridge a = a();
                            this.f16174c = a;
                            requestLocationUpdates = a.requestLocationUpdates(tencentLocationRequest, tencentLocationListener, looper);
                        }
                        return requestLocationUpdates;
                    }
                    throw new NullPointerException("looper is null");
                }
                throw new NullPointerException("listener is null");
            }
            throw new NullPointerException("request is null");
        }
        return 4;
    }

    public final int requestLocationWithScene(int i2, TencentLocationListener tencentLocationListener) {
        int requestLocationWithScene;
        if (f16172f) {
            synchronized (this.b) {
                if (i2 != 10 && i2 != 11 && i2 != 12) {
                    throw new IllegalArgumentException("unknown scenario type: ".concat(String.valueOf(i2)));
                }
                synchronized (this.b) {
                    TencentLocationBridge a = a();
                    this.f16174c = a;
                    requestLocationWithScene = a.requestLocationWithScene(i2, tencentLocationListener);
                }
            }
            return requestLocationWithScene;
        }
        return 4;
    }

    public final int requestSingleFreshLocation(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper) {
        int requestSingleFreshLocation;
        if (f16172f) {
            if (tencentLocationListener != null) {
                if (looper != null) {
                    synchronized (this.b) {
                        TencentLocationBridge a = a();
                        this.f16174c = a;
                        requestSingleFreshLocation = a.requestSingleFreshLocation(tencentLocationRequest, tencentLocationListener, looper);
                    }
                    return requestSingleFreshLocation;
                }
                throw new NullPointerException("looper is null");
            }
            throw new NullPointerException("listener is null");
        }
        return 4;
    }

    public final void setCoordinateType(int i2) {
        if (f16172f) {
            synchronized (this.b) {
                if (i2 != 1 && i2 != 0) {
                    throw new IllegalArgumentException("unknown coordinate type: ".concat(String.valueOf(i2)));
                }
                synchronized (this.b) {
                    TencentLocationBridge a = a();
                    this.f16174c = a;
                    a.setCoordinateType(i2);
                }
            }
        }
    }

    public final void setDebuggable(boolean z) {
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                a.setDebuggable(z);
            }
        }
    }

    public final void setDeviceID(Context context, String str) {
        if (f16172f) {
            if (str.length() <= 0) {
                throw new IllegalArgumentException("setDeviceID, deviceID length must more than 0");
            }
            if (str.length() > 63) {
                str = str.substring(0, 63);
            }
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                a.setDeviceID(context, str);
            }
        }
    }

    public final void setMockEnable(boolean z) {
        if (f16172f) {
            k0.e(z);
        }
    }

    public final void setSystemCacheEnable(boolean z) {
        if (f16172f) {
            j1.c(z);
        }
    }

    public final int startDrEngine(int i2) {
        int startDrEngine;
        if (f16172f) {
            try {
                synchronized (this.b) {
                    TencentLocationBridge a = a();
                    this.f16174c = a;
                    startDrEngine = a.startDrEngine(i2);
                }
                return startDrEngine;
            } catch (Exception unused) {
                return -999;
            }
        }
        return -6;
    }

    @Deprecated
    public final boolean startIndoorLocation() {
        boolean startIndoorLocation;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                startIndoorLocation = a.startIndoorLocation();
            }
            return startIndoorLocation;
        }
        return false;
    }

    @Deprecated
    public final boolean stopIndoorLocation() {
        boolean stopIndoorLocation;
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                stopIndoorLocation = a.stopIndoorLocation();
            }
            return stopIndoorLocation;
        }
        return false;
    }

    public final void stopLocationWithScene(int i2, TencentLocationListener tencentLocationListener) {
        if (f16172f) {
            synchronized (this.b) {
                if (i2 != 10 && i2 != 11 && i2 != 12) {
                    throw new IllegalArgumentException("unknown scenario type: ".concat(String.valueOf(i2)));
                }
                synchronized (this.b) {
                    TencentLocationBridge a = a();
                    this.f16174c = a;
                    a.stopLocationWithScene(i2, tencentLocationListener);
                }
            }
        }
    }

    public final void terminateDrEngine() {
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                a.terminateDrEngine();
            }
        }
    }

    public final void triggerCodeGuarder(boolean z) {
        if (f16172f) {
            synchronized (this.b) {
                TencentLocationBridge a = a();
                this.f16174c = a;
                a.triggerCodeGuarder(z);
            }
        }
    }
}
