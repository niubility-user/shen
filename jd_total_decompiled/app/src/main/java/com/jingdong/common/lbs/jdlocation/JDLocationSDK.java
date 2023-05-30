package com.jingdong.common.lbs.jdlocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Looper;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.lbs.proxy.LBSCallBack;
import com.jingdong.common.lbs.proxy.LBSListener;
import com.jingdong.common.lbs.proxy.a;
import com.jingdong.common.lbs.report.LBSReportManager;
import com.jingdong.common.lbs.report.b;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.lbs.utils.GPSUtil;
import com.jingdong.common.lbs.utils.d;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.tencent.map.geolocation.TencentLocationManager;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class JDLocationSDK {
    public static final String LBS_BUSINESS_ID = "e0a684c49d77e7749cdf7c2ab92e2d1a";
    private static JDLocationSDK instance;
    private static LBSCallBack lbsCallBack;
    private boolean isLocatingAddress;
    private boolean isLocatingLatLng;
    private JDLocation location;
    private long lastLatLngTime = 0;
    private CopyOnWriteArrayList<JDLocationListener> latLngListenerList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<JDLocationListener> addressListenerList = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<String, JDLocationChangedListener> locationChangedListenerMap = new ConcurrentHashMap<>();

    private JDLocationSDK() {
    }

    private b genLBSLogBeanFail(int i2, JDLocationOption jDLocationOption, JDLocationError jDLocationError) {
        b bVar = new b();
        bVar.b = i2;
        if (jDLocationOption != null) {
            bVar.a(jDLocationOption);
            bVar.f12379l = new Long(System.currentTimeMillis() - jDLocationOption.getRequestTime()).intValue();
        }
        if (jDLocationError != null) {
            bVar.f12377j = jDLocationError.getCode();
            bVar.f12378k = jDLocationError.getMsg();
        }
        return bVar;
    }

    private b genLBSLogBeanSuccess(int i2, JDLocationOption jDLocationOption, JDLocation jDLocation) {
        b bVar = new b();
        bVar.b = i2;
        if (jDLocationOption != null) {
            bVar.a(jDLocationOption);
            bVar.f12379l = new Long(System.currentTimeMillis() - jDLocationOption.getRequestTime()).intValue();
        }
        if (jDLocation != null) {
            bVar.f12371c = jDLocation.getLng();
            bVar.d = jDLocation.getLat();
            bVar.f12372e = jDLocation.getProvinceId();
            bVar.f12373f = jDLocation.getCityId();
            bVar.f12374g = jDLocation.getDistrictId();
            bVar.f12375h = jDLocation.getTownId();
            bVar.f12376i = jDLocation.getDetailAddress();
            bVar.f12377j = 0;
            bVar.f12378k = "OK";
        }
        return bVar;
    }

    public void getAddressInner(JDLocationOption jDLocationOption, JDLocationInnerListener jDLocationInnerListener) {
        if (jDLocationOption == null || jDLocationInnerListener == null) {
            return;
        }
        try {
            boolean z = true;
            boolean z2 = jDLocationOption.getLat() == 0.0d && jDLocationOption.getLng() == 0.0d;
            boolean z3 = TextUtils.isEmpty(getLastLocation(jDLocationOption).getDetailAddress()) && jDLocationOption.isNeedDetail();
            if (GPSUtil.calculateDistance(jDLocationOption.getLat(), jDLocationOption.getLng(), getLastLocation(jDLocationOption).getLat(), getLastLocation(jDLocationOption).getLng()) <= LBSReportManager.getInstance().getBusinessDistance()) {
                z = false;
            }
            if (!z2 && !z3 && !z) {
                jDLocationInnerListener.onSuccess(getLastLocation(jDLocationOption));
                return;
            }
            JDLocationNet.getInstance().getRealAddress(jDLocationOption, jDLocationInnerListener);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationInnerListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(300);
                jDLocationError.setMsg(e2.getMessage());
                jDLocationInnerListener.onFail(jDLocationError);
            }
        }
    }

    private void getAddressWithOutPermission(final JDLocationOption jDLocationOption) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (this.isLocatingAddress) {
                return;
            }
            this.isLocatingAddress = true;
            final CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.15
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(20000L, 1000L);
                    JDLocationSDK.this = this;
                }

                @Override // android.os.CountDownTimer
                public final void onFinish() {
                    if (JDLocationSDK.this.isLocatingAddress) {
                        JDLocationSDK.this.stopLocation();
                        JDLocationError jDLocationError = new JDLocationError();
                        jDLocationError.setCode(202);
                        jDLocationError.setMsg(JDLocationError.MSG_TIME_OUT);
                        JDLocationSDK.this.onAddressFail(jDLocationOption, jDLocationError);
                    }
                }

                @Override // android.os.CountDownTimer
                public final void onTick(long j2) {
                }
            };
            countDownTimer.start();
            getAddressInner(jDLocationOption, new JDLocationInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.16
                {
                    JDLocationSDK.this = this;
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onFail(JDLocationError jDLocationError) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    JDLocationSDK.this.onAddressFail(jDLocationOption, jDLocationError);
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onSuccess(JDLocation jDLocation) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    if (jDLocation == null) {
                        return;
                    }
                    JDLocationSDK.this.setLastLocation(jDLocation);
                    if (JDLocationSDK.lbsCallBack != null) {
                        JDLocationSDK.lbsCallBack.onGetAddressSuccess(jDLocation);
                    }
                    JDLocationSDK.this.onAddressSuccess(jDLocationOption, jDLocation);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            JDLocationError jDLocationError = new JDLocationError();
            jDLocationError.setCode(300);
            jDLocationError.setMsg(e2.getMessage());
            onAddressFail(jDLocationOption, jDLocationError);
        }
    }

    private void getAddressWithPermission(final JDLocationOption jDLocationOption) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (this.isLocatingAddress) {
                return;
            }
            this.isLocatingAddress = true;
            final CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.13
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(20000L, 1000L);
                    JDLocationSDK.this = this;
                }

                @Override // android.os.CountDownTimer
                public final void onFinish() {
                    if (JDLocationSDK.this.isLocatingAddress) {
                        JDLocationSDK.this.stopLocation();
                        JDLocationError jDLocationError = new JDLocationError();
                        jDLocationError.setCode(202);
                        jDLocationError.setMsg(JDLocationError.MSG_TIME_OUT);
                        JDLocationSDK.this.onAddressFail(jDLocationOption, jDLocationError);
                    }
                }

                @Override // android.os.CountDownTimer
                public final void onTick(long j2) {
                }
            };
            countDownTimer.start();
            getLatLngInner(jDLocationOption, new JDLocationInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.14
                {
                    JDLocationSDK.this = this;
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onFail(JDLocationError jDLocationError) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    JDLocationSDK.this.stopLocation();
                    JDLocationSDK.this.onAddressFail(jDLocationOption, jDLocationError);
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onSuccess(JDLocation jDLocation) {
                    JDLocationSDK.this.stopLocation();
                    if (jDLocation != null) {
                        jDLocationOption.setLat(jDLocation.getRealLat());
                        jDLocationOption.setLng(jDLocation.getRealLng());
                        JDLocationSDK.this.lastLatLngTime = System.currentTimeMillis();
                    } else {
                        jDLocationOption.setLat(0.0d);
                        jDLocationOption.setLng(0.0d);
                    }
                    JDLocationSDK.this.getAddressInner(jDLocationOption, new JDLocationInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.14.1
                        {
                            AnonymousClass14.this = this;
                        }

                        @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                        public final void onFail(JDLocationError jDLocationError) {
                            CountDownTimer countDownTimer2 = countDownTimer;
                            if (countDownTimer2 != null) {
                                countDownTimer2.cancel();
                            }
                            AnonymousClass14 anonymousClass14 = AnonymousClass14.this;
                            JDLocationSDK.this.onAddressFail(jDLocationOption, jDLocationError);
                        }

                        @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                        public final void onSuccess(JDLocation jDLocation2) {
                            CountDownTimer countDownTimer2 = countDownTimer;
                            if (countDownTimer2 != null) {
                                countDownTimer2.cancel();
                            }
                            if (jDLocation2 == null) {
                                return;
                            }
                            JDLocationSDK.this.setLastLocation(jDLocation2);
                            if (JDLocationSDK.lbsCallBack != null) {
                                JDLocationSDK.lbsCallBack.onGetAddressSuccess(jDLocation2);
                            }
                            AnonymousClass14 anonymousClass14 = AnonymousClass14.this;
                            JDLocationSDK.this.onAddressSuccess(jDLocationOption, jDLocation2);
                        }
                    });
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            JDLocationError jDLocationError = new JDLocationError();
            jDLocationError.setCode(300);
            jDLocationError.setMsg(e2.getMessage());
            onAddressFail(jDLocationOption, jDLocationError);
        }
    }

    public static JDLocationSDK getInstance() {
        JDLocationSDK jDLocationSDK;
        JDLocationSDK jDLocationSDK2 = instance;
        if (jDLocationSDK2 != null) {
            return jDLocationSDK2;
        }
        synchronized (JDLocationSDK.class) {
            if (instance == null) {
                instance = new JDLocationSDK();
            }
            jDLocationSDK = instance;
        }
        return jDLocationSDK;
    }

    private void getLatLngInner(JDLocationOption jDLocationOption, final JDLocationInnerListener jDLocationInnerListener) {
        if (jDLocationOption == null || jDLocationInnerListener == null) {
            return;
        }
        try {
            boolean z = true;
            boolean z2 = System.currentTimeMillis() - this.lastLatLngTime > ((long) (LBSReportManager.getInstance().getBusinessInterval() * 1000));
            if (System.currentTimeMillis() - getLastLocation(jDLocationOption).getUpdateTime() <= LBSReportManager.getInstance().getBusinessInterval() * 1000) {
                z = false;
            }
            if (!z2 && !z) {
                jDLocationInnerListener.onSuccess(getLastLocation(jDLocationOption));
                return;
            }
            a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.12
                {
                    JDLocationSDK.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDLocationTencentSDK.getInstance(a.a).startLocation(jDLocationInnerListener);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationInnerListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(300);
                jDLocationError.setMsg(e2.getMessage());
                jDLocationInnerListener.onFail(jDLocationError);
            }
        }
    }

    private void getLatLngWithPermission(final JDLocationOption jDLocationOption) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (this.isLocatingLatLng) {
                return;
            }
            this.isLocatingLatLng = true;
            final CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.10
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(20000L, 1000L);
                    JDLocationSDK.this = this;
                }

                @Override // android.os.CountDownTimer
                public final void onFinish() {
                    if (JDLocationSDK.this.isLocatingLatLng) {
                        JDLocationSDK.this.stopLocation();
                        JDLocationError jDLocationError = new JDLocationError();
                        jDLocationError.setCode(202);
                        jDLocationError.setMsg(JDLocationError.MSG_TIME_OUT);
                        JDLocationSDK.this.onLatLngFail(jDLocationOption, jDLocationError);
                    }
                }

                @Override // android.os.CountDownTimer
                public final void onTick(long j2) {
                }
            };
            countDownTimer.start();
            getLatLngInner(jDLocationOption, new JDLocationInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.11
                {
                    JDLocationSDK.this = this;
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onFail(JDLocationError jDLocationError) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    JDLocationSDK.this.stopLocation();
                    JDLocationSDK.this.onLatLngFail(jDLocationOption, jDLocationError);
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                public final void onSuccess(JDLocation jDLocation) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    JDLocationSDK.this.stopLocation();
                    if (jDLocation == null) {
                        return;
                    }
                    JDLocationSDK.this.lastLatLngTime = System.currentTimeMillis();
                    JDLocationSDK.this.onLatLngSuccess(jDLocationOption, jDLocation);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            JDLocationError jDLocationError = new JDLocationError();
            jDLocationError.setCode(300);
            jDLocationError.setMsg(e2.getMessage());
            onLatLngFail(jDLocationOption, jDLocationError);
        }
    }

    public void onAddressFail(JDLocationOption jDLocationOption, final JDLocationError jDLocationError) {
        a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.8
            {
                JDLocationSDK.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                JDLocationSDK.this.isLocatingAddress = false;
                if (JDLocationSDK.this.addressListenerList != null) {
                    Iterator it = JDLocationSDK.this.addressListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDLocationListener) it.next()).onFail(jDLocationError);
                    }
                    JDLocationSDK.this.addressListenerList.clear();
                }
            }
        });
        LBSReportManager.getInstance().reportLBSLog(genLBSLogBeanFail(285216781, jDLocationOption, jDLocationError));
    }

    public void onAddressSuccess(JDLocationOption jDLocationOption, final JDLocation jDLocation) {
        if (jDLocation != null && jDLocationOption != null) {
            jDLocation.setSceneId(jDLocationOption.getSceneId());
        }
        a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.7
            {
                JDLocationSDK.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                JDLocationSDK.this.isLocatingAddress = false;
                if (JDLocationSDK.this.addressListenerList != null) {
                    Iterator it = JDLocationSDK.this.addressListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDLocationListener) it.next()).onSuccess(jDLocation);
                    }
                    JDLocationSDK.this.addressListenerList.clear();
                }
            }
        });
        LBSReportManager.getInstance().reportLBSLog(genLBSLogBeanSuccess(285216781, jDLocationOption, jDLocation));
    }

    public void onLatLngFail(JDLocationOption jDLocationOption, final JDLocationError jDLocationError) {
        a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.6
            {
                JDLocationSDK.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                JDLocationSDK.this.isLocatingLatLng = false;
                if (JDLocationSDK.this.latLngListenerList != null) {
                    Iterator it = JDLocationSDK.this.latLngListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDLocationListener) it.next()).onFail(jDLocationError);
                    }
                    JDLocationSDK.this.latLngListenerList.clear();
                }
            }
        });
        LBSReportManager.getInstance().reportLBSLog(genLBSLogBeanFail(285216780, jDLocationOption, jDLocationError));
    }

    public void onLatLngSuccess(JDLocationOption jDLocationOption, final JDLocation jDLocation) {
        if (jDLocation != null && jDLocationOption != null) {
            jDLocation.setSceneId(jDLocationOption.getSceneId());
        }
        a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.5
            {
                JDLocationSDK.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                JDLocationSDK.this.isLocatingLatLng = false;
                if (JDLocationSDK.this.latLngListenerList != null) {
                    Iterator it = JDLocationSDK.this.latLngListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDLocationListener) it.next()).onSuccess(jDLocation);
                    }
                    JDLocationSDK.this.latLngListenerList.clear();
                }
            }
        });
        LBSReportManager.getInstance().reportLBSLog(genLBSLogBeanSuccess(285216780, jDLocationOption, jDLocation));
    }

    private void startIntervalLocationWithPermission(JDLocationOption jDLocationOption, final JDLocationInnerListener jDLocationInnerListener) {
        if (jDLocationOption == null || jDLocationInnerListener == null) {
            return;
        }
        try {
            a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.3
                {
                    JDLocationSDK.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDLocationTencentSDK.getInstance(a.a).startIntervalLocation(jDLocationInnerListener);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationInnerListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(300);
                jDLocationError.setMsg(e2.getMessage());
                jDLocationInnerListener.onFail(jDLocationError);
            }
        }
    }

    public void stopLocation() {
        try {
            a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.17
                {
                    JDLocationSDK.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDLocationTencentSDK.getInstance(a.a).stopLocation();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void getAddress(JDLocationOption jDLocationOption, JDLocationListener jDLocationListener) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (jDLocationListener != null) {
                this.addressListenerList.add(jDLocationListener);
            }
            if (jDLocationOption != null) {
                jDLocationOption.setRequestTime(System.currentTimeMillis());
            }
            if (!DeviceUtil.hasPrivacy()) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(103);
                jDLocationError.setMsg(JDLocationError.MSG_NOT_HAS_PRIVACY);
                onAddressFail(jDLocationOption, jDLocationError);
            } else if (!DeviceUtil.isAppForeground()) {
                JDLocationError jDLocationError2 = new JDLocationError();
                jDLocationError2.setCode(104);
                jDLocationError2.setMsg(JDLocationError.MSG_NOT_FOREGROUND);
                onAddressFail(jDLocationOption, jDLocationError2);
            } else if (jDLocationOption != null && LBSReportManager.getInstance().getNoLocationBusinessIDList().contains(jDLocationOption.getBusinessId())) {
                JDLocationError jDLocationError3 = new JDLocationError();
                jDLocationError3.setCode(105);
                jDLocationError3.setMsg(JDLocationError.MSG_NOT_LOCATION);
                onAddressFail(jDLocationOption, jDLocationError3);
            } else if (!DeviceUtil.isSceneAllowed(jDLocationOption.getSceneId())) {
                JDLocationError jDLocationError4 = new JDLocationError();
                jDLocationError4.setCode(210);
                jDLocationError4.setMsg(JDLocationError.MSG_NO_SCENE_ALLOWED);
                onAddressFail(jDLocationOption, jDLocationError4);
            } else if (DeviceUtil.hasLocationPermission()) {
                getAddressWithPermission(jDLocationOption);
            } else if (jDLocationOption != null && jDLocationOption.isNeedIP()) {
                getAddressWithOutPermission(jDLocationOption);
            } else {
                JDLocationError jDLocationError5 = new JDLocationError();
                jDLocationError5.setCode(200);
                jDLocationError5.setMsg(JDLocationError.MSG_NO_PERMISSION);
                onAddressFail(jDLocationOption, jDLocationError5);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JDLocationError jDLocationError6 = new JDLocationError();
            jDLocationError6.setCode(300);
            jDLocationError6.setMsg(e2.getMessage());
            onAddressFail(jDLocationOption, jDLocationError6);
        }
    }

    @SuppressLint({"MissingPermission"})
    public CellLocation getBSInfo(JDLocationOption jDLocationOption) {
        try {
            boolean hasLocationPermission = DeviceUtil.hasLocationPermission();
            if (DeviceUtil.hasPrivacy() && DeviceUtil.isAppForeground() && hasLocationPermission) {
                return ((TelephonyManager) a.a.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getCellLocation();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public JDLocation getLastLocation(JDLocationOption jDLocationOption) {
        if (this.location == null) {
            this.location = new JDLocation();
        }
        if (DeviceUtil.hasPrivacy()) {
            boolean isManto = jDLocationOption != null ? jDLocationOption.isManto() : false;
            if (DeviceUtil.isAppForeground() || isManto) {
                if (jDLocationOption != null) {
                    this.location.setBusinessId(jDLocationOption.getBusinessId());
                    this.location.setSceneId(jDLocationOption.getSceneId());
                }
                return this.location;
            }
            return new JDLocation();
        }
        return new JDLocation();
    }

    public void getLatLng(JDLocationOption jDLocationOption, JDLocationListener jDLocationListener) {
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (jDLocationListener != null) {
                this.latLngListenerList.add(jDLocationListener);
            }
            if (jDLocationOption != null) {
                jDLocationOption.setRequestTime(System.currentTimeMillis());
            }
            if (!DeviceUtil.hasPrivacy()) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(103);
                jDLocationError.setMsg(JDLocationError.MSG_NOT_HAS_PRIVACY);
                onLatLngFail(jDLocationOption, jDLocationError);
            } else if (!DeviceUtil.isAppForeground()) {
                JDLocationError jDLocationError2 = new JDLocationError();
                jDLocationError2.setCode(104);
                jDLocationError2.setMsg(JDLocationError.MSG_NOT_FOREGROUND);
                onLatLngFail(jDLocationOption, jDLocationError2);
            } else if (jDLocationOption != null && LBSReportManager.getInstance().getNoLocationBusinessIDList().contains(jDLocationOption.getBusinessId())) {
                JDLocationError jDLocationError3 = new JDLocationError();
                jDLocationError3.setCode(105);
                jDLocationError3.setMsg(JDLocationError.MSG_NOT_LOCATION);
                onLatLngFail(jDLocationOption, jDLocationError3);
            } else if (!DeviceUtil.isSceneAllowed(jDLocationOption.getSceneId())) {
                JDLocationError jDLocationError4 = new JDLocationError();
                jDLocationError4.setCode(210);
                jDLocationError4.setMsg(JDLocationError.MSG_NO_SCENE_ALLOWED);
                onLatLngFail(jDLocationOption, jDLocationError4);
            } else if (DeviceUtil.hasLocationPermission()) {
                getLatLngWithPermission(jDLocationOption);
            } else {
                JDLocationError jDLocationError5 = new JDLocationError();
                jDLocationError5.setCode(200);
                jDLocationError5.setMsg(JDLocationError.MSG_NO_PERMISSION);
                onLatLngFail(jDLocationOption, jDLocationError5);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JDLocationError jDLocationError6 = new JDLocationError();
            jDLocationError6.setCode(300);
            jDLocationError6.setMsg(e2.getMessage());
            onLatLngFail(jDLocationOption, jDLocationError6);
        }
    }

    public boolean hasLocationPermission() {
        return DeviceUtil.hasLocationPermission();
    }

    public JDLocationSDK init(final Context context, LBSListener lBSListener) {
        if (context != null) {
            if (context.getApplicationContext() != null) {
                a.a = context.getApplicationContext();
            } else {
                a.a = context;
            }
        }
        TencentLocationManager.setUserAgreePrivacy(true);
        DeviceUtil.setLBSListener(lBSListener);
        LBSReportManager.getInstance().init();
        if (lBSListener != null && lBSListener.hasPrivacy()) {
            if (Build.VERSION.SDK_INT < 21) {
                try {
                    TencentLocationManager.getInstance(context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                d.a();
                d.a(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.1
                    {
                        JDLocationSDK.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Looper.prepare();
                            TencentLocationManager.getInstance(context);
                            Looper.loop();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                });
            }
        }
        return this;
    }

    public boolean isIntervalLocating() {
        return JDLocationTencentSDK.getInstance(a.a).isIntervalLocating();
    }

    public boolean isMockLocation(JDLocationOption jDLocationOption) {
        Location lastKnownLocation;
        try {
            boolean hasLocationPermission = DeviceUtil.hasLocationPermission();
            if (DeviceUtil.hasPrivacy() && DeviceUtil.isAppForeground() && hasLocationPermission && (lastKnownLocation = ((LocationManager) a.a.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID)).getLastKnownLocation("passive")) != null && Build.VERSION.SDK_INT >= 18) {
                return lastKnownLocation.isFromMockProvider();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Deprecated
    public void registerLocationChangedListener(JDLocationOption jDLocationOption, JDLocationChangedListener jDLocationChangedListener) {
        if (jDLocationOption == null || jDLocationChangedListener == null) {
            return;
        }
        this.locationChangedListenerMap.put(jDLocationOption.getBusinessId(), jDLocationChangedListener);
    }

    public JDLocationSDK setAppKey(String str) {
        DeviceUtil.setAppKey(str);
        return this;
    }

    public JDLocationSDK setDeviceName(String str) {
        DeviceUtil.setDeviceName(str);
        return this;
    }

    public JDLocationSDK setHostDebug(boolean z) {
        DeviceUtil.setHostDebug(z);
        return this;
    }

    public JDLocationSDK setLBSCallBack(LBSCallBack lBSCallBack) {
        lbsCallBack = lBSCallBack;
        return this;
    }

    public void setLastLocation(JDLocation jDLocation) {
        if (jDLocation == null || jDLocation.getType() != 1) {
            return;
        }
        JDLocation jDLocation2 = this.location;
        if (jDLocation2 == null) {
            this.location = jDLocation;
        } else if (jDLocation2.getLat() == 0.0d && this.location.getLng() == 0.0d) {
            this.location = jDLocation;
        } else if (jDLocation.getLat() == 0.0d && jDLocation.getLng() == 0.0d) {
        } else {
            this.location = jDLocation;
        }
    }

    @Deprecated
    public void startIntervalLocation(JDLocationOption jDLocationOption, final JDLocationListener jDLocationListener) {
        try {
            if (!DeviceUtil.hasPrivacy()) {
                if (jDLocationListener != null) {
                    JDLocationError jDLocationError = new JDLocationError();
                    jDLocationError.setCode(103);
                    jDLocationError.setMsg(JDLocationError.MSG_NOT_HAS_PRIVACY);
                    jDLocationListener.onFail(jDLocationError);
                }
            } else if (!DeviceUtil.isAppForeground()) {
                JDLocationError jDLocationError2 = new JDLocationError();
                jDLocationError2.setCode(104);
                jDLocationError2.setMsg(JDLocationError.MSG_NOT_FOREGROUND);
                onLatLngFail(jDLocationOption, jDLocationError2);
            } else {
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                }
                if (DeviceUtil.hasLocationPermission()) {
                    startIntervalLocationWithPermission(jDLocationOption, new JDLocationInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.2
                        {
                            JDLocationSDK.this = this;
                        }

                        @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                        public final void onFail(JDLocationError jDLocationError3) {
                            JDLocationListener jDLocationListener2 = jDLocationListener;
                            if (jDLocationListener2 != null) {
                                jDLocationListener2.onFail(jDLocationError3);
                            }
                        }

                        @Override // com.jingdong.common.lbs.jdlocation.JDLocationInnerListener
                        public final void onSuccess(JDLocation jDLocation) {
                            JDLocationListener jDLocationListener2 = jDLocationListener;
                            if (jDLocationListener2 != null) {
                                jDLocationListener2.onSuccess(jDLocation);
                            }
                        }
                    });
                } else if (jDLocationListener != null) {
                    JDLocationError jDLocationError3 = new JDLocationError();
                    jDLocationError3.setCode(200);
                    jDLocationError3.setMsg(JDLocationError.MSG_NO_PERMISSION);
                    jDLocationListener.onFail(jDLocationError3);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationListener != null) {
                JDLocationError jDLocationError4 = new JDLocationError();
                jDLocationError4.setCode(300);
                jDLocationError4.setMsg(e2.getMessage());
                jDLocationListener.onFail(jDLocationError4);
            }
        }
    }

    @Deprecated
    public void startLocationChangedListener() {
        if (DeviceUtil.hasPrivacy()) {
            JDLocationTencentSDK.getInstance(a.a).setLocationChangedInnerListener(new JDLocationChangedInnerListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.9
                {
                    JDLocationSDK.this = this;
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationChangedInnerListener
                public final void onLocationChanged(final JDLocation jDLocation) {
                    a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.9.1
                        {
                            AnonymousClass9.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            if (jDLocation == null || JDLocationSDK.this.locationChangedListenerMap.size() <= 0) {
                                return;
                            }
                            Iterator it = JDLocationSDK.this.locationChangedListenerMap.values().iterator();
                            while (it.hasNext()) {
                                ((JDLocationChangedListener) it.next()).onLocationChanged(jDLocation);
                            }
                        }
                    });
                }
            });
        }
    }

    @Deprecated
    public void stopIntervalLocation(JDLocationOption jDLocationOption) {
        try {
            a.a().post(new Runnable() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationSDK.4
                {
                    JDLocationSDK.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDLocationTencentSDK.getInstance(a.a).stopIntervalLocation();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public void stopLocationChangedListener() {
        if (DeviceUtil.hasPrivacy()) {
            JDLocationTencentSDK.getInstance(a.a).setLocationChangedInnerListener(null);
        }
    }

    @Deprecated
    public void unregisterLocationChangedListener(JDLocationOption jDLocationOption) {
        if (jDLocationOption == null || this.locationChangedListenerMap.size() <= 0) {
            return;
        }
        this.locationChangedListenerMap.remove(jDLocationOption.getBusinessId());
    }
}
