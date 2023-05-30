package com.jingdong.common.lbs.jdlocation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Looper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.lbs.R;
import com.jingdong.common.lbs.proxy.a;
import com.jingdong.common.lbs.report.LBSReportManager;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.lbs.utils.d;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
class JDLocationTencentSDK {
    private static JDLocationTencentSDK instance;
    private static TencentLocationManager manager;
    private JDLocationInnerListener innerIntervalListener;
    private boolean isCreateChannel;
    private JDLocationChangedInnerListener locationChangedInnerListener;
    private NotificationManager notificationManager;
    private long requestTime;
    private boolean isIntervalLocating = false;
    private CopyOnWriteArrayList<JDLocationInnerListener> innerListenerList = new CopyOnWriteArrayList<>();
    private TencentLocationListener locationListener = new TencentLocationListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationTencentSDK.1
        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
            if (tencentLocation != null) {
                try {
                    if (i2 != 0) {
                        JDLocationError jDLocationError = new JDLocationError();
                        jDLocationError.setCode(i2);
                        jDLocationError.setMsg(str);
                        JDLocationTencentSDK.this.onInnerListenerFail(jDLocationError);
                        return;
                    }
                    JDLocation jDLocation = new JDLocation();
                    jDLocation.setLat(tencentLocation.getLatitude());
                    jDLocation.setLng(tencentLocation.getLongitude());
                    jDLocation.setCoord(tencentLocation.getCoordinateType() == 1 ? "gcj" : "wgs");
                    jDLocation.setProvider(tencentLocation.getProvider());
                    jDLocation.setAccuracy(tencentLocation.getAccuracy());
                    jDLocation.setAltitude(tencentLocation.getAltitude());
                    jDLocation.setType(0);
                    jDLocation.setRequestTime(JDLocationTencentSDK.this.requestTime);
                    jDLocation.setUpdateTime(System.currentTimeMillis());
                    JDLocationTencentSDK.this.onInnerListenerSuccess(jDLocation);
                    if (JDLocationTencentSDK.this.locationChangedInnerListener != null) {
                        JDLocationTencentSDK.this.locationChangedInnerListener.onLocationChanged(jDLocation);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onStatusUpdate(String str, int i2, String str2) {
        }
    };
    private TencentLocationListener locationIntervalListener = new TencentLocationListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationTencentSDK.2
        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
            try {
                if (JDLocationTencentSDK.this.innerIntervalListener == null || tencentLocation == null) {
                    return;
                }
                if (i2 != 0) {
                    JDLocationError jDLocationError = new JDLocationError();
                    jDLocationError.setCode(i2);
                    jDLocationError.setMsg(str);
                    JDLocationTencentSDK.this.innerIntervalListener.onFail(jDLocationError);
                    return;
                }
                JDLocation jDLocation = new JDLocation();
                jDLocation.setLat(tencentLocation.getLatitude());
                jDLocation.setLng(tencentLocation.getLongitude());
                jDLocation.setCoord(tencentLocation.getCoordinateType() == 1 ? "gcj" : "wgs");
                jDLocation.setProvider(tencentLocation.getProvider());
                jDLocation.setAccuracy(tencentLocation.getAccuracy());
                jDLocation.setAltitude(tencentLocation.getAltitude());
                JDLocationTencentSDK.this.innerIntervalListener.onSuccess(jDLocation);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.tencent.map.geolocation.TencentLocationListener
        public final void onStatusUpdate(String str, int i2, String str2) {
        }
    };

    JDLocationTencentSDK() {
    }

    private Notification buildNotification() {
        Notification.Builder builder;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            if (this.notificationManager == null) {
                this.notificationManager = (NotificationManager) a.a.getSystemService(RemoteMessageConst.NOTIFICATION);
            }
            String packageName = a.a.getPackageName();
            if (!this.isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(packageName, "lbs", 3);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(-16776961);
                notificationChannel.setShowBadge(true);
                this.notificationManager.createNotificationChannel(notificationChannel);
                this.isCreateChannel = true;
            }
            builder = new Notification.Builder(a.a, packageName);
        } else {
            builder = new Notification.Builder(a.a);
        }
        int i3 = R.drawable.ic_locating;
        builder.setSmallIcon(i3).setContentTitle("LBS\u670d\u52a1").setContentText("\u6b63\u5728\u540e\u53f0\u8fd0\u884c").setLargeIcon(BitmapFactory.decodeResource(a.a.getResources(), i3)).setWhen(System.currentTimeMillis());
        return i2 >= 16 ? builder.build() : builder.getNotification();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JDLocationTencentSDK getInstance(Context context) {
        JDLocationTencentSDK jDLocationTencentSDK;
        JDLocationTencentSDK jDLocationTencentSDK2 = instance;
        if (jDLocationTencentSDK2 != null) {
            return jDLocationTencentSDK2;
        }
        synchronized (JDLocationTencentSDK.class) {
            if (instance == null) {
                instance = new JDLocationTencentSDK();
            }
            if (manager == null) {
                manager = TencentLocationManager.getInstance(context);
            }
            jDLocationTencentSDK = instance;
        }
        return jDLocationTencentSDK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void singleLocation(Looper looper) {
        try {
            if (DeviceUtil.isAppForeground()) {
                manager.requestSingleFreshLocation(TencentLocationRequest.create().setInterval(Final.FIVE_SECOND).setRequestLevel(1), this.locationListener, looper);
                this.requestTime = System.currentTimeMillis();
                return;
            }
            JDLocationError jDLocationError = new JDLocationError();
            jDLocationError.setCode(104);
            jDLocationError.setMsg(JDLocationError.MSG_NOT_FOREGROUND);
            onInnerListenerFail(jDLocationError);
        } catch (Exception e2) {
            JDLocationError jDLocationError2 = new JDLocationError();
            jDLocationError2.setMsg(e2.getMessage());
            onInnerListenerFail(jDLocationError2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIntervalLocating() {
        return this.isIntervalLocating;
    }

    void onInnerListenerFail(JDLocationError jDLocationError) {
        CopyOnWriteArrayList<JDLocationInnerListener> copyOnWriteArrayList = this.innerListenerList;
        if (copyOnWriteArrayList != null) {
            Iterator<JDLocationInnerListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onFail(jDLocationError);
            }
            this.innerListenerList.clear();
        }
    }

    void onInnerListenerSuccess(JDLocation jDLocation) {
        CopyOnWriteArrayList<JDLocationInnerListener> copyOnWriteArrayList = this.innerListenerList;
        if (copyOnWriteArrayList != null) {
            Iterator<JDLocationInnerListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onSuccess(jDLocation);
            }
            this.innerListenerList.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLocationChangedInnerListener(JDLocationChangedInnerListener jDLocationChangedInnerListener) {
        this.locationChangedInnerListener = jDLocationChangedInnerListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startIntervalLocation(JDLocationInnerListener jDLocationInnerListener) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.innerIntervalListener = jDLocationInnerListener;
            TencentLocationRequest allowGPS = TencentLocationRequest.create().setInterval(Final.FIVE_SECOND).setRequestLevel(0).setAllowGPS(true);
            manager.enableForegroundLocation(100, buildNotification());
            manager.requestLocationUpdates(allowGPS, this.locationIntervalListener, Looper.getMainLooper());
            this.isIntervalLocating = true;
        } catch (Exception e2) {
            if (jDLocationInnerListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setMsg(e2.getMessage());
                jDLocationInnerListener.onFail(jDLocationError);
            }
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startLocation(JDLocationInnerListener jDLocationInnerListener) {
        try {
            CopyOnWriteArrayList<JDLocationInnerListener> copyOnWriteArrayList = this.innerListenerList;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.add(jDLocationInnerListener);
            }
            if (LBSReportManager.getInstance().getThreadSwitch() == 1 && Build.VERSION.SDK_INT >= 21) {
                d.a();
                d.a(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationTencentSDK.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (Looper.myLooper() == null) {
                            Looper.prepare();
                        }
                        JDLocationTencentSDK.this.singleLocation(Looper.myLooper());
                        if (Looper.myLooper() != Looper.getMainLooper()) {
                            Looper.loop();
                        }
                    }
                });
                return;
            }
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            singleLocation(Looper.getMainLooper());
        } catch (Exception e2) {
            JDLocationError jDLocationError = new JDLocationError();
            jDLocationError.setMsg(e2.getMessage());
            onInnerListenerFail(jDLocationError);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopIntervalLocation() {
        try {
            this.isIntervalLocating = false;
            manager.removeUpdates(this.locationIntervalListener);
            manager.disableForegroundLocation(true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopLocation() {
        try {
            if (this.isIntervalLocating) {
                return;
            }
            manager.removeUpdates(this.locationListener);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
