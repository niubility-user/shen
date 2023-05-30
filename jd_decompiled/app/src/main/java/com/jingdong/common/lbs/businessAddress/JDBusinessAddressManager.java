package com.jingdong.common.lbs.businessAddress;

import android.os.CountDownTimer;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.R;
import com.jingdong.common.XView2.business.PermissionBridge;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.http.JDLbsHttpError;
import com.jingdong.common.lbs.http.JDLbsHttpListListener;
import com.jingdong.common.lbs.http.JDLbsHttpListener;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.lbs.utils.AESUtil;
import com.jingdong.common.lbs.utils.GPSUtil;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDBusinessAddressManager {
    private static final String SP_UCADDRESS = "UCAddress";
    private static final String SP_YFADDRESS = "YFAddress";
    private static JDBusinessAddressManager manager;
    private JDBusinessAddress cachedBusinessAddress;
    private boolean isLocating;
    private boolean isYFLocating;
    private String key;
    private CopyOnWriteArrayList<JDBusinessAddressListener> mListenerList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<JDYFAddressListener> mYFListenerList = new CopyOnWriteArrayList<>();
    private JDBusinessAddressSelectedListener selectedListener;
    private JDBusinessAddress toastBusinessAddress;
    private JDUserCityAddressSelectedListener userCityAddressSelectedListener;

    private JDBusinessAddress createDefaultBusinessAddress() {
        JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
        jDBusinessAddress.setAddressID(0L);
        jDBusinessAddress.setProvinceCode(1);
        jDBusinessAddress.setProvince(StringUtil.product_province_beijing);
        jDBusinessAddress.setCityCode(72);
        jDBusinessAddress.setCity("\u671d\u9633\u533a");
        jDBusinessAddress.setFullAddress("\u5317\u4eac\u5e02\u671d\u9633\u533a");
        jDBusinessAddress.setLat(39.921469d);
        jDBusinessAddress.setLng(116.443107d);
        jDBusinessAddress.setType(JDBusinessAddress.TYPE_BACKUP);
        jDBusinessAddress.setShowToast(false);
        jDBusinessAddress.setCode(302);
        jDBusinessAddress.setMessage(JDLbsHttpError.MSG_DEFAULTCACHE_ERROR);
        return jDBusinessAddress;
    }

    private JDYFAddress createJDFYAddressWithJDBusinessAddress(JDBusinessAddress jDBusinessAddress) {
        JDYFAddress jDYFAddress = new JDYFAddress();
        jDYFAddress.setCode("0");
        jDYFAddress.setMessage("OK");
        jDYFAddress.setAddressID(jDBusinessAddress.getAddressID());
        jDYFAddress.setProvinceCode(jDBusinessAddress.getProvinceCode());
        jDYFAddress.setCityCode(jDBusinessAddress.getCityCode());
        jDYFAddress.setDistrictCode(jDBusinessAddress.getDistrictCode());
        jDYFAddress.setTownCode(jDBusinessAddress.getTownCode());
        jDYFAddress.setProvince(jDBusinessAddress.getProvince());
        jDYFAddress.setCity(jDBusinessAddress.getCity());
        jDYFAddress.setDistrict(jDBusinessAddress.getDistrict());
        jDYFAddress.setTown(jDBusinessAddress.getTown());
        jDYFAddress.setFullAddress(jDBusinessAddress.getFullAddress());
        jDYFAddress.setDetailAddress(jDBusinessAddress.getDetailAddress());
        jDYFAddress.setLng(jDBusinessAddress.getLng());
        jDYFAddress.setLat(jDBusinessAddress.getLat());
        jDYFAddress.setAddressName(jDBusinessAddress.getAddressName());
        jDYFAddress.setType("choose");
        jDYFAddress.setPermission(true);
        jDYFAddress.setNearby(true);
        jDYFAddress.setGridId(jDBusinessAddress.getGridId());
        jDYFAddress.setAddressTitle(jDBusinessAddress.getAddressTitle());
        return jDYFAddress;
    }

    private JDYFAddress createJDFYAddressWithJDLocation(JDLocation jDLocation) {
        JDYFAddress jDYFAddress = new JDYFAddress();
        jDYFAddress.setCode("0");
        jDYFAddress.setMessage("OK");
        jDYFAddress.setAddressID(0L);
        jDYFAddress.setProvinceCode(jDLocation.getProvinceId());
        jDYFAddress.setCityCode(jDLocation.getCityId());
        jDYFAddress.setDistrictCode(jDLocation.getDistrictId());
        jDYFAddress.setTownCode(jDLocation.getTownId());
        jDYFAddress.setProvince(jDLocation.getProvinceName());
        jDYFAddress.setCity(jDLocation.getCityName());
        jDYFAddress.setDistrict(jDLocation.getDistrictName());
        jDYFAddress.setTown(jDLocation.getTownName());
        jDYFAddress.setFullAddress(jDLocation.getProvinceName() + jDLocation.getCityName() + jDLocation.getDistrictName() + jDLocation.getTownName() + jDLocation.getDetailAddress());
        jDYFAddress.setDetailAddress(jDLocation.getDetailAddress());
        jDYFAddress.setLng(jDLocation.getLng());
        jDYFAddress.setLat(jDLocation.getLat());
        jDYFAddress.setAddressName("");
        jDYFAddress.setType("gis");
        jDYFAddress.setPermission(true);
        jDYFAddress.setNearby(true);
        jDYFAddress.setGridId(jDLocation.getGridId());
        jDYFAddress.setAddressTitle("");
        return jDYFAddress;
    }

    public static JDBusinessAddressManager getInstance() {
        JDBusinessAddressManager jDBusinessAddressManager;
        JDBusinessAddressManager jDBusinessAddressManager2 = manager;
        if (jDBusinessAddressManager2 != null) {
            return jDBusinessAddressManager2;
        }
        synchronized (JDBusinessAddressManager.class) {
            if (manager == null) {
                manager = new JDBusinessAddressManager();
            }
            jDBusinessAddressManager = manager;
        }
        return jDBusinessAddressManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JDBusinessAddress getUserCloseToast() {
        return this.toastBusinessAddress;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBusinessAddressFail(final JDLbsHttpOption jDLbsHttpOption, final int i2, final String str) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.11
            @Override // java.lang.Runnable
            public void run() {
                JDBusinessAddressManager.this.isLocating = false;
                JDBusinessAddress createJDBusinessAddressWithGlobal = JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption);
                if (createJDBusinessAddressWithGlobal.getLat() == 0.0d && createJDBusinessAddressWithGlobal.getLng() == 0.0d) {
                    createJDBusinessAddressWithGlobal = new JDBusinessAddress();
                    createJDBusinessAddressWithGlobal.setAddressID(0L);
                    createJDBusinessAddressWithGlobal.setProvinceCode(1);
                    createJDBusinessAddressWithGlobal.setProvince(StringUtil.product_province_beijing);
                    createJDBusinessAddressWithGlobal.setCityCode(72);
                    createJDBusinessAddressWithGlobal.setCity("\u671d\u9633\u533a");
                    createJDBusinessAddressWithGlobal.setFullAddress("\u5317\u4eac\u5e02\u671d\u9633\u533a");
                    createJDBusinessAddressWithGlobal.setLat(39.921469d);
                    createJDBusinessAddressWithGlobal.setLng(116.443107d);
                    createJDBusinessAddressWithGlobal.setType(JDBusinessAddress.TYPE_BACKUP);
                    createJDBusinessAddressWithGlobal.setShowToast(false);
                    createJDBusinessAddressWithGlobal.setCode(502);
                    createJDBusinessAddressWithGlobal.setMessage(JDLbsHttpError.MSG_GLOBAL_LATLNG_EMPTY);
                } else {
                    createJDBusinessAddressWithGlobal.setCode(i2);
                    createJDBusinessAddressWithGlobal.setMessage(str);
                }
                JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                if (jDLbsHttpOption2 != null && createJDBusinessAddressWithGlobal != null) {
                    createJDBusinessAddressWithGlobal.setSceneId(jDLbsHttpOption2.getSceneId());
                }
                JDBusinessAddressManager.this.setCachedBusinessAddress(jDLbsHttpOption, createJDBusinessAddressWithGlobal);
                if (JDBusinessAddressManager.this.mListenerList != null) {
                    Iterator it = JDBusinessAddressManager.this.mListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDBusinessAddressListener) it.next()).onEnd(createJDBusinessAddressWithGlobal);
                    }
                    JDBusinessAddressManager.this.mListenerList.clear();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBusinessAddressSuccess(final JDLbsHttpOption jDLbsHttpOption, final JDBusinessAddress jDBusinessAddress) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.10
            @Override // java.lang.Runnable
            public void run() {
                JDBusinessAddress jDBusinessAddress2;
                JDBusinessAddressManager.this.isLocating = false;
                JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                if (jDLbsHttpOption2 != null && (jDBusinessAddress2 = jDBusinessAddress) != null) {
                    jDBusinessAddress2.setSceneId(jDLbsHttpOption2.getSceneId());
                }
                JDBusinessAddressManager.this.setCachedBusinessAddress(jDLbsHttpOption, jDBusinessAddress);
                if (JDBusinessAddressManager.this.mListenerList != null) {
                    Iterator it = JDBusinessAddressManager.this.mListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDBusinessAddressListener) it.next()).onEnd(jDBusinessAddress);
                    }
                    JDBusinessAddressManager.this.mListenerList.clear();
                }
                if (OKLog.D) {
                    if (jDBusinessAddress == null) {
                        OKLog.d("LBS", "onBusinessAddressSuccess address null");
                        return;
                    }
                    OKLog.d("LBS", "onBusinessAddressSuccess address=" + jDBusinessAddress.getJsonStr());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserCityAddressFail(int i2, String str, JDUserCityAddressListener jDUserCityAddressListener) {
        JDUserCityAddress jDUserCityAddress = new JDUserCityAddress();
        jDUserCityAddress.setCode("" + i2);
        jDUserCityAddress.setMessage(str);
        jDUserCityAddressListener.onEnd(jDUserCityAddress);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onYFAddressFail(final JDLbsHttpOption jDLbsHttpOption, final int i2, final String str) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.13
            @Override // java.lang.Runnable
            public void run() {
                JDBusinessAddressManager.this.isYFLocating = false;
                JDYFAddress defaultYFAddress = JDBusinessAddressManager.this.getDefaultYFAddress();
                defaultYFAddress.setCode("" + i2);
                defaultYFAddress.setMessage(str);
                if (!TextUtils.isEmpty(defaultYFAddress.getType())) {
                    defaultYFAddress.setType("default");
                }
                JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                if (jDLbsHttpOption2 != null && defaultYFAddress != null) {
                    defaultYFAddress.setSceneId(jDLbsHttpOption2.getSceneId());
                }
                if (JDBusinessAddressManager.this.mYFListenerList != null) {
                    Iterator it = JDBusinessAddressManager.this.mYFListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDYFAddressListener) it.next()).onEnd(defaultYFAddress);
                    }
                    JDBusinessAddressManager.this.mYFListenerList.clear();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onYFAddressSuccess(final JDLbsHttpOption jDLbsHttpOption, final JDYFAddress jDYFAddress) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.12
            @Override // java.lang.Runnable
            public void run() {
                JDYFAddress jDYFAddress2;
                JDBusinessAddressManager.this.isYFLocating = false;
                JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                if (jDLbsHttpOption2 != null && (jDYFAddress2 = jDYFAddress) != null) {
                    jDYFAddress2.setSceneId(jDLbsHttpOption2.getSceneId());
                }
                if (JDBusinessAddressManager.this.mYFListenerList != null) {
                    Iterator it = JDBusinessAddressManager.this.mYFListenerList.iterator();
                    while (it.hasNext()) {
                        ((JDYFAddressListener) it.next()).onEnd(jDYFAddress);
                    }
                    JDBusinessAddressManager.this.mYFListenerList.clear();
                }
                if (OKLog.D) {
                    if (jDYFAddress == null) {
                        OKLog.d("LBS", "onYFAddressSuccess address null");
                        return;
                    }
                    OKLog.d("LBS", "onYFAddressSuccess address=" + jDYFAddress.getJsonStr());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestYFAddress(final JDLbsHttpOption jDLbsHttpOption) {
        if (OKLog.D) {
            OKLog.d("LBS", "requestYFAddress");
        }
        if (jDLbsHttpOption == null) {
            return;
        }
        JDBusinessAddressNet.getInstance().getYFAddress(jDLbsHttpOption, new JDLbsHttpListener<JDYFAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.9
            @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
            public void onFail(JDLbsHttpError jDLbsHttpError) {
                JDBusinessAddressManager.this.onYFAddressFail(jDLbsHttpOption, jDLbsHttpError.getCode(), jDLbsHttpError.getMessage());
            }

            @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
            public void onSuccess(JDYFAddress jDYFAddress) {
                JDBusinessAddressManager.this.onYFAddressSuccess(jDLbsHttpOption, jDYFAddress);
            }
        });
    }

    public void checkAddressAvailable(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListener<JSONObject> jDLbsHttpListener) {
        try {
            JDBusinessAddressNet.getInstance().checkAddressAvailable(jDLbsHttpOption, new JDLbsHttpListener<JSONObject>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.16
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.16.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onSuccess(final JSONObject jSONObject) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.16.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListener.onSuccess(jSONObject);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.17
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public int compareAddress(JDBusinessAddress jDBusinessAddress, JDBusinessAddress jDBusinessAddress2) {
        if (jDBusinessAddress != null && jDBusinessAddress2 != null) {
            try {
                long addressID = jDBusinessAddress.getAddressID();
                long addressID2 = jDBusinessAddress2.getAddressID();
                if (addressID != 0 || addressID2 != 0) {
                    return addressID == addressID2 ? 0 : 1;
                }
                double lat = jDBusinessAddress.getLat();
                double lng = jDBusinessAddress.getLng();
                double lat2 = jDBusinessAddress2.getLat();
                double lng2 = jDBusinessAddress2.getLng();
                if (lat != 0.0d || lng != 0.0d || lat2 != 0.0d || lng2 != 0.0d) {
                    return GPSUtil.calculateDistance(lat, lng, lat2, lng2) < 100 ? 0 : 1;
                }
                String fullAddress = jDBusinessAddress.getFullAddress();
                String fullAddress2 = jDBusinessAddress2.getFullAddress();
                return (TextUtils.isEmpty(fullAddress) || TextUtils.isEmpty(fullAddress2) || !fullAddress.equalsIgnoreCase(fullAddress2)) ? 2 : 0;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 2;
    }

    public void getBusinessAddress(final JDLbsHttpOption jDLbsHttpOption, JDBusinessAddressListener jDBusinessAddressListener) {
        try {
            if (OKLog.D) {
                OKLog.d("LBS", "getBusinessAddress");
            }
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (jDBusinessAddressListener != null) {
                this.mListenerList.add(jDBusinessAddressListener);
            }
            if (this.isLocating) {
                return;
            }
            this.isLocating = true;
            final CountDownTimer countDownTimer = new CountDownTimer(20000L, 1000L) { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.1
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (JDBusinessAddressManager.this.isLocating) {
                        JDBusinessAddressManager.this.onBusinessAddressFail(jDLbsHttpOption, 503, JDLbsHttpError.MSG_TIME_OUT);
                    }
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                }
            };
            countDownTimer.start();
            JDLocationOption jDLocationOption = new JDLocationOption();
            if (jDLbsHttpOption != null) {
                jDLocationOption.setBusinessId(jDLbsHttpOption.getBusinessId());
                jDLocationOption.setSceneId(jDLbsHttpOption.getSceneId());
            }
            JDLocationManager.getInstance().getLatLng(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.2
                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onFail(JDLocationError jDLocationError) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    JDBusinessAddressManager.this.onBusinessAddressFail(jDLbsHttpOption, jDLocationError.getCode(), jDLocationError.getMsg());
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onSuccess(JDLocation jDLocation) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    if (jDLocation == null || (jDLocation.getLat() == 0.0d && jDLocation.getLng() == 0.0d)) {
                        JDBusinessAddressManager.this.onBusinessAddressFail(jDLbsHttpOption, 501, JDLbsHttpError.MSG_LOCATION_LATLNG_EMPTY);
                        return;
                    }
                    jDLbsHttpOption.setLat(jDLocation.getLat());
                    jDLbsHttpOption.setLng(jDLocation.getLng());
                    JDBusinessAddressNet.getInstance().getBusinessAddress(jDLbsHttpOption, new JDLbsHttpListener<JDBusinessAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.2.1
                        @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                        public void onFail(JDLbsHttpError jDLbsHttpError) {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            JDBusinessAddressManager.this.onBusinessAddressFail(jDLbsHttpOption, jDLbsHttpError.getCode(), jDLbsHttpError.getMessage());
                        }

                        @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                        public void onSuccess(JDBusinessAddress jDBusinessAddress) {
                            JDLbsHttpOption jDLbsHttpOption2 = jDLbsHttpOption;
                            if (jDLbsHttpOption2 != null && jDLbsHttpOption2.isJustToastOnce()) {
                                JDBusinessAddressManager jDBusinessAddressManager = JDBusinessAddressManager.this;
                                if (jDBusinessAddressManager.compareAddress(jDBusinessAddress, jDBusinessAddressManager.getUserCloseToast()) == 0) {
                                    jDBusinessAddress.setShowToast(false);
                                }
                            }
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            JDBusinessAddressManager.this.onBusinessAddressSuccess(jDLbsHttpOption, jDBusinessAddress);
                        }
                    });
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            onBusinessAddressFail(jDLbsHttpOption, 300, JDLbsHttpError.MSG_EXCEPTION);
        }
    }

    public void getBusinessAddressList(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListListener<JDBusinessAddress> jDLbsHttpListListener) {
        try {
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            jDLocationCacheOption.setBusinessId(jDLbsHttpOption.getBusinessId());
            jDLocationCacheOption.setSceneId(jDLbsHttpOption.getSceneId());
            JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
            jDLbsHttpOption.setLng(location.getLng());
            jDLbsHttpOption.setLat(location.getLat());
            JDBusinessAddressNet.getInstance().getBusinessAddressList(jDLbsHttpOption, new JDLbsHttpListListener<JDBusinessAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.14
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.14.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDBusinessAddress> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.14.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.15
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public JDBusinessAddress getCachedBusinessAddress(JDLbsHttpOption jDLbsHttpOption) {
        JDBusinessAddress jDBusinessAddress = this.cachedBusinessAddress;
        return jDBusinessAddress == null ? new JDBusinessAddress() : jDBusinessAddress;
    }

    public JDUserCityAddress getCachedUserCityAddress() {
        JDUserCityAddress jDUserCityAddress = new JDUserCityAddress();
        try {
            String string = SharedPreferencesUtil.getString(SP_UCADDRESS, "");
            if (!TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(this.key)) {
                    this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
                }
                String decrypt = AESUtil.decrypt(string, this.key);
                if (!TextUtils.isEmpty(decrypt)) {
                    JSONObject jSONObject = new JSONObject(decrypt);
                    jDUserCityAddress.setCode(jSONObject.optString("code"));
                    jDUserCityAddress.setMessage(jSONObject.optString("message"));
                    jDUserCityAddress.setLng(jSONObject.optDouble(HybridSDK.LNG));
                    jDUserCityAddress.setLat(jSONObject.optDouble("lat"));
                    jDUserCityAddress.setProvinceCode(jSONObject.optInt("provinceCode"));
                    jDUserCityAddress.setProvince(jSONObject.optString("province"));
                    jDUserCityAddress.setCityCode(jSONObject.optInt("cityCode"));
                    jDUserCityAddress.setCity(jSONObject.optString("city"));
                    jDUserCityAddress.setDistrictCode(jSONObject.optInt(Constant.KEY_DISTRICT_CODE));
                    jDUserCityAddress.setDistrict(jSONObject.optString("district"));
                    jDUserCityAddress.setTownCode(jSONObject.optInt("townCode"));
                    jDUserCityAddress.setTown(jSONObject.optString("town"));
                    jDUserCityAddress.setType(jSONObject.optString("type"));
                    jDUserCityAddress.setAddressTitle(jSONObject.optString("addressTitle"));
                    jDUserCityAddress.setLevel(jSONObject.optInt("level"));
                    jDUserCityAddress.setDifferent(jSONObject.optBoolean("isDifferent"));
                }
            }
        } catch (Exception unused) {
        }
        return jDUserCityAddress;
    }

    public void getChildAreaAddressList(JDLbsHttpOption jDLbsHttpOption, final JDUserCityAddress jDUserCityAddress, final JDLbsHttpListListener<JDUserCityAddress> jDLbsHttpListListener) {
        String str;
        if (jDLbsHttpListListener == null || jDLbsHttpOption == null || jDUserCityAddress == null) {
            return;
        }
        try {
            if (jDUserCityAddress.getLevel() != 1 && !jDUserCityAddress.getProvince().contains(StringUtil.product_province_beijing) && !jDUserCityAddress.getProvince().contains("\u4e0a\u6d77") && !jDUserCityAddress.getProvince().contains("\u5929\u6d25") && !jDUserCityAddress.getProvince().contains("\u91cd\u5e86") && !jDUserCityAddress.getProvince().contains("\u9999\u6e2f") && !jDUserCityAddress.getProvince().contains("\u6fb3\u95e8") && !jDUserCityAddress.getProvince().contains("\u53f0\u6e7e")) {
                str = "" + jDUserCityAddress.getCityCode();
                jDLbsHttpOption.setParentId(str);
                JDBusinessAddressNet.getInstance().getChildAreaAddressList(jDLbsHttpOption, new JDLbsHttpListListener<JDAreaAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8
                    @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                    public void onFail(final JDLbsHttpError jDLbsHttpError) {
                        if (jDLbsHttpListListener != null) {
                            BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    jDLbsHttpListListener.onFail(jDLbsHttpError);
                                }
                            });
                        }
                    }

                    @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                    public void onSuccess(ArrayList<JDAreaAddress> arrayList) {
                        final ArrayList arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                            JDAreaAddress jDAreaAddress = arrayList.get(i2);
                            JDUserCityAddress jDUserCityAddress2 = new JDUserCityAddress();
                            if (jDUserCityAddress.getLevel() != 1 && !jDUserCityAddress.getProvince().contains(StringUtil.product_province_beijing) && !jDUserCityAddress.getProvince().contains("\u4e0a\u6d77") && !jDUserCityAddress.getProvince().contains("\u5929\u6d25") && !jDUserCityAddress.getProvince().contains("\u91cd\u5e86") && !jDUserCityAddress.getProvince().contains("\u9999\u6e2f") && !jDUserCityAddress.getProvince().contains("\u6fb3\u95e8") && !jDUserCityAddress.getProvince().contains("\u53f0\u6e7e")) {
                                jDUserCityAddress2.setCode("0");
                                jDUserCityAddress2.setMessage("OK");
                                jDUserCityAddress2.setProvinceCode(jDUserCityAddress.getProvinceCode());
                                jDUserCityAddress2.setProvince(jDUserCityAddress.getProvince());
                                jDUserCityAddress2.setCityCode(jDUserCityAddress.getCityCode());
                                jDUserCityAddress2.setCity(jDUserCityAddress.getCity());
                                jDUserCityAddress2.setDistrictCode(jDAreaAddress.getId());
                                jDUserCityAddress2.setDistrict(jDAreaAddress.getName());
                                jDUserCityAddress2.setTownCode(0);
                                jDUserCityAddress2.setTown("");
                                jDUserCityAddress2.setAddressTitle(jDAreaAddress.getName());
                                jDUserCityAddress2.setLevel(3);
                            } else {
                                jDUserCityAddress2.setCode("0");
                                jDUserCityAddress2.setMessage("OK");
                                jDUserCityAddress2.setProvinceCode(jDUserCityAddress.getProvinceCode());
                                jDUserCityAddress2.setProvince(jDUserCityAddress.getProvince());
                                jDUserCityAddress2.setCityCode(jDAreaAddress.getId());
                                jDUserCityAddress2.setCity(jDAreaAddress.getName());
                                jDUserCityAddress2.setDistrictCode(0);
                                jDUserCityAddress2.setDistrict("");
                                jDUserCityAddress2.setTownCode(0);
                                jDUserCityAddress2.setTown("");
                                jDUserCityAddress2.setAddressTitle(jDAreaAddress.getName());
                                jDUserCityAddress2.setLevel(2);
                            }
                            arrayList2.add(jDUserCityAddress2);
                        }
                        if (jDLbsHttpListListener != null) {
                            BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    jDLbsHttpListListener.onSuccess(arrayList2);
                                }
                            });
                        }
                    }
                });
            }
            str = "" + jDUserCityAddress.getProvinceCode();
            jDLbsHttpOption.setParentId(str);
            JDBusinessAddressNet.getInstance().getChildAreaAddressList(jDLbsHttpOption, new JDLbsHttpListListener<JDAreaAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(ArrayList<JDAreaAddress> arrayList) {
                    final ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        JDAreaAddress jDAreaAddress = arrayList.get(i2);
                        JDUserCityAddress jDUserCityAddress2 = new JDUserCityAddress();
                        if (jDUserCityAddress.getLevel() != 1 && !jDUserCityAddress.getProvince().contains(StringUtil.product_province_beijing) && !jDUserCityAddress.getProvince().contains("\u4e0a\u6d77") && !jDUserCityAddress.getProvince().contains("\u5929\u6d25") && !jDUserCityAddress.getProvince().contains("\u91cd\u5e86") && !jDUserCityAddress.getProvince().contains("\u9999\u6e2f") && !jDUserCityAddress.getProvince().contains("\u6fb3\u95e8") && !jDUserCityAddress.getProvince().contains("\u53f0\u6e7e")) {
                            jDUserCityAddress2.setCode("0");
                            jDUserCityAddress2.setMessage("OK");
                            jDUserCityAddress2.setProvinceCode(jDUserCityAddress.getProvinceCode());
                            jDUserCityAddress2.setProvince(jDUserCityAddress.getProvince());
                            jDUserCityAddress2.setCityCode(jDUserCityAddress.getCityCode());
                            jDUserCityAddress2.setCity(jDUserCityAddress.getCity());
                            jDUserCityAddress2.setDistrictCode(jDAreaAddress.getId());
                            jDUserCityAddress2.setDistrict(jDAreaAddress.getName());
                            jDUserCityAddress2.setTownCode(0);
                            jDUserCityAddress2.setTown("");
                            jDUserCityAddress2.setAddressTitle(jDAreaAddress.getName());
                            jDUserCityAddress2.setLevel(3);
                        } else {
                            jDUserCityAddress2.setCode("0");
                            jDUserCityAddress2.setMessage("OK");
                            jDUserCityAddress2.setProvinceCode(jDUserCityAddress.getProvinceCode());
                            jDUserCityAddress2.setProvince(jDUserCityAddress.getProvince());
                            jDUserCityAddress2.setCityCode(jDAreaAddress.getId());
                            jDUserCityAddress2.setCity(jDAreaAddress.getName());
                            jDUserCityAddress2.setDistrictCode(0);
                            jDUserCityAddress2.setDistrict("");
                            jDUserCityAddress2.setTownCode(0);
                            jDUserCityAddress2.setTown("");
                            jDUserCityAddress2.setAddressTitle(jDAreaAddress.getName());
                            jDUserCityAddress2.setLevel(2);
                        }
                        arrayList2.add(jDUserCityAddress2);
                    }
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.8.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList2);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JDBusinessAddress getDefaultBusinessAddress(JDLbsHttpOption jDLbsHttpOption) {
        double d;
        double d2;
        double lat;
        if (jDLbsHttpOption != null) {
            try {
                AddressGlobal addressGlobal = JDGlobalAddressManager.getAddressGlobal(jDLbsHttpOption);
                if (addressGlobal != null) {
                    try {
                        d = Double.parseDouble(addressGlobal.getLatitude());
                        try {
                            d2 = Double.parseDouble(addressGlobal.getLongitude());
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            d2 = 0.0d;
                            if (0.0d == d) {
                                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                                jDLocationCacheOption.setBusinessId(jDLbsHttpOption.getBusinessId());
                                jDLocationCacheOption.setSceneId(jDLbsHttpOption.getSceneId());
                                JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
                                lat = location.getLat();
                                double lng = location.getLng();
                                if (0.0d == lat) {
                                }
                                return JDGlobalAddressManager.createJDBusinessAddressWithJDLocation(jDLbsHttpOption, location);
                            }
                            return JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        d = 0.0d;
                    }
                    if (0.0d == d && 0.0d == d2) {
                        JDLocationCacheOption jDLocationCacheOption2 = new JDLocationCacheOption();
                        jDLocationCacheOption2.setBusinessId(jDLbsHttpOption.getBusinessId());
                        jDLocationCacheOption2.setSceneId(jDLbsHttpOption.getSceneId());
                        JDLocation location2 = JDLocationCache.getInstance().getLocation(jDLocationCacheOption2);
                        lat = location2.getLat();
                        double lng2 = location2.getLng();
                        if (0.0d == lat || 0.0d != lng2) {
                            return JDGlobalAddressManager.createJDBusinessAddressWithJDLocation(jDLbsHttpOption, location2);
                        }
                    }
                    return JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption);
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                return createDefaultBusinessAddress();
            }
        }
        return createDefaultBusinessAddress();
    }

    public JDYFAddress getDefaultYFAddress() {
        if (OKLog.D) {
            OKLog.d("LBS", "getDefaultYFAddress");
        }
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        jDLbsHttpOption.setBusinessId("15f39f78ae41d235baf6dfdc573ccd47");
        jDLbsHttpOption.setSceneId("locService");
        JDYFAddress jDYFAddress = new JDYFAddress();
        try {
            jDYFAddress.setPermission(PermissionHelper.hasLocationPermissionWithScene(jDLbsHttpOption.getSceneId(), jDLbsHttpOption.getBusinessId()));
            String string = SharedPreferencesUtil.getString(SP_YFADDRESS, "");
            if (!TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(this.key)) {
                    this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
                }
                String decrypt = AESUtil.decrypt(string, this.key);
                if (!TextUtils.isEmpty(decrypt)) {
                    JSONObject jSONObject = new JSONObject(decrypt);
                    jDYFAddress.setCode(jSONObject.optString("code"));
                    jDYFAddress.setMessage(jSONObject.optString("message"));
                    jDYFAddress.setAddressID(jSONObject.optLong("addressID"));
                    jDYFAddress.setProvinceCode(jSONObject.optLong("provinceCode"));
                    jDYFAddress.setCityCode(jSONObject.optLong("cityCode"));
                    jDYFAddress.setDistrictCode(jSONObject.optLong(Constant.KEY_DISTRICT_CODE));
                    jDYFAddress.setTownCode(jSONObject.optLong("townCode"));
                    jDYFAddress.setProvince(jSONObject.optString("province"));
                    jDYFAddress.setCity(jSONObject.optString("city"));
                    jDYFAddress.setDistrict(jSONObject.optString("district"));
                    jDYFAddress.setTown(jSONObject.optString("town"));
                    jDYFAddress.setFullAddress(jSONObject.optString("fullAddress"));
                    jDYFAddress.setDetailAddress(jSONObject.optString("detailAddress"));
                    jDYFAddress.setLng(jSONObject.optDouble(HybridSDK.LNG));
                    jDYFAddress.setLat(jSONObject.optDouble("lat"));
                    jDYFAddress.setAddressName(jSONObject.optString("addressName"));
                    jDYFAddress.setType(jSONObject.optString("type"));
                    jDYFAddress.setNearby(true);
                    jDYFAddress.setGridId(jSONObject.optLong("gridId"));
                    jDYFAddress.setAddressTitle(jSONObject.optString("addressTitle"));
                }
            }
        } catch (Exception unused) {
        }
        return jDYFAddress;
    }

    @Deprecated
    public void getJDNearbyEntranceAddress(JDNearbyEntranceAddressListener jDNearbyEntranceAddressListener) {
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        jDLbsHttpOption.setBusinessId(PermissionBridge.HOME_COMMON_LBS_ID);
        getJDNearbyEntranceAddress(jDLbsHttpOption, jDNearbyEntranceAddressListener);
    }

    public void getNearByStoreList(JDBusinessStoreOption jDBusinessStoreOption, final JDLbsHttpListListener<JDBusinessStore> jDLbsHttpListListener) {
        try {
            JDBusinessAddressNet.getInstance().getNearByStoreList(jDBusinessStoreOption, new JDLbsHttpListListener<JDBusinessStore>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.18
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.18.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDBusinessStore> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.18.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.19
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getSearchStoreList(JDBusinessStoreOption jDBusinessStoreOption, final JDLbsHttpListListener<JDBusinessStore> jDLbsHttpListListener) {
        try {
            JDBusinessAddressNet.getInstance().getSearchStoreList(jDBusinessStoreOption, new JDLbsHttpListListener<JDBusinessStore>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.20
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.20.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDBusinessStore> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.20.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.21
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getUserCityAddress(JDLbsHttpOption jDLbsHttpOption, final JDUserCityAddressListener jDUserCityAddressListener) {
        if (jDUserCityAddressListener == null) {
            return;
        }
        try {
            JDLocationOption jDLocationOption = new JDLocationOption();
            if (jDLbsHttpOption != null) {
                jDLocationOption.setBusinessId(jDLbsHttpOption.getBusinessId());
                jDLocationOption.setSceneId(jDLbsHttpOption.getSceneId());
            }
            JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.5
                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onFail(JDLocationError jDLocationError) {
                    JDBusinessAddressManager.this.onUserCityAddressFail(jDLocationError.getCode(), jDLocationError.getMsg(), jDUserCityAddressListener);
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onSuccess(JDLocation jDLocation) {
                    if (jDLocation == null || (jDLocation.getLat() == 0.0d && jDLocation.getLng() == 0.0d)) {
                        JDBusinessAddressManager.this.onUserCityAddressFail(501, JDLbsHttpError.MSG_LOCATION_LATLNG_EMPTY, jDUserCityAddressListener);
                        return;
                    }
                    JDUserCityAddress jDUserCityAddress = new JDUserCityAddress();
                    jDUserCityAddress.setCode("0");
                    jDUserCityAddress.setMessage("OK");
                    jDUserCityAddress.setLng(jDLocation.getLng());
                    jDUserCityAddress.setLat(jDLocation.getLat());
                    jDUserCityAddress.setProvinceCode(jDLocation.getProvinceId());
                    jDUserCityAddress.setProvince(jDLocation.getProvinceName());
                    jDUserCityAddress.setCityCode(jDLocation.getCityId());
                    jDUserCityAddress.setCity(jDLocation.getCityName());
                    jDUserCityAddress.setDistrictCode(jDLocation.getDistrictId());
                    jDUserCityAddress.setDistrict(jDLocation.getDistrictName());
                    jDUserCityAddress.setTownCode(jDLocation.getTownId());
                    jDUserCityAddress.setTown(jDLocation.getTownName());
                    jDUserCityAddress.setType("gis");
                    jDUserCityAddress.setAddressTitle(jDLocation.getCityName());
                    jDUserCityAddress.setLevel(2);
                    JDUserCityAddress cachedUserCityAddress = JDBusinessAddressManager.getInstance().getCachedUserCityAddress();
                    if (jDUserCityAddress.getProvinceCode() == cachedUserCityAddress.getProvinceCode() && jDUserCityAddress.getCityCode() == cachedUserCityAddress.getCityCode()) {
                        jDUserCityAddress.setDifferent(false);
                    } else {
                        jDUserCityAddress.setDifferent(true);
                    }
                    jDUserCityAddressListener.onEnd(jDUserCityAddress);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            onUserCityAddressFail(300, JDLbsHttpError.MSG_EXCEPTION, jDUserCityAddressListener);
        }
    }

    public void getUserCityAddressList(JDLbsHttpOption jDLbsHttpOption, final JDLbsHttpListListener<JDUserCityAddress> jDLbsHttpListListener) {
        if (jDLbsHttpListListener == null || jDLbsHttpOption == null) {
            return;
        }
        try {
            JDBusinessAddressNet.getInstance().getUserCityAddressList(jDLbsHttpOption, new JDLbsHttpListListener<JDUserCityAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.6
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.6.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDUserCityAddress> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.7
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    @Deprecated
    public void getYFAddress(JDYFAddressListener jDYFAddressListener) {
        if (OKLog.D) {
            OKLog.d("LBS", "getYFAddress Deprecated");
        }
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        jDLbsHttpOption.setBusinessId("15f39f78ae41d235baf6dfdc573ccd47");
        jDLbsHttpOption.setSourceId(4);
        getYFAddress(jDLbsHttpOption, jDYFAddressListener);
    }

    public void onAddressSelected(String str) {
        JDBusinessAddressSelectedListener jDBusinessAddressSelectedListener = this.selectedListener;
        if (jDBusinessAddressSelectedListener != null) {
            jDBusinessAddressSelectedListener.onAddressSelected(str);
            this.selectedListener = null;
        }
    }

    public void onUserCityAddressSelected(String str) {
        JDUserCityAddressSelectedListener jDUserCityAddressSelectedListener = this.userCityAddressSelectedListener;
        if (jDUserCityAddressSelectedListener != null) {
            jDUserCityAddressSelectedListener.onAddressSelected(str);
            this.userCityAddressSelectedListener = null;
        }
    }

    public void setAddressSelectedListener(JDBusinessAddressSelectedListener jDBusinessAddressSelectedListener) {
        this.selectedListener = jDBusinessAddressSelectedListener;
    }

    public void setCachedBusinessAddress(JDLbsHttpOption jDLbsHttpOption, JDBusinessAddress jDBusinessAddress) {
        if (jDLbsHttpOption == null) {
            jDLbsHttpOption = new JDLbsHttpOption();
        }
        if (jDBusinessAddress != null) {
            jDBusinessAddress.setSceneId(jDLbsHttpOption.getSceneId());
            this.cachedBusinessAddress = jDBusinessAddress;
        }
    }

    public void setUserCityAddressSelectedListener(JDUserCityAddressSelectedListener jDUserCityAddressSelectedListener) {
        this.userCityAddressSelectedListener = jDUserCityAddressSelectedListener;
    }

    public void setUserCloseToast(JDBusinessAddress jDBusinessAddress) {
        if (jDBusinessAddress != null) {
            this.toastBusinessAddress = jDBusinessAddress;
        }
    }

    public void updateCachedUserCityAddress(JDUserCityAddress jDUserCityAddress) {
        if (jDUserCityAddress != null) {
            try {
                if (TextUtils.isEmpty(jDUserCityAddress.getUserCityAddressJson())) {
                    return;
                }
                if (TextUtils.isEmpty(this.key)) {
                    this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
                }
                SharedPreferencesUtil.putString(SP_UCADDRESS, AESUtil.encrypt(jDUserCityAddress.getUserCityAddressJson(), this.key));
            } catch (Exception unused) {
            }
        }
    }

    public void updateDefaultYFAddress(JDYFAddress jDYFAddress) {
        try {
            if (OKLog.D) {
                OKLog.d("LBS", "updateDefaultYFAddress JDYFAddress");
            }
            if (jDYFAddress == null || TextUtils.isEmpty(jDYFAddress.getJsonStr())) {
                return;
            }
            if (TextUtils.isEmpty(this.key)) {
                this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
            }
            SharedPreferencesUtil.putString(SP_YFADDRESS, AESUtil.encrypt(jDYFAddress.getJsonStr(), this.key));
        } catch (Exception unused) {
        }
    }

    public JDBusinessAddress getCachedBusinessAddress(String str) {
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        jDLbsHttpOption.setBusinessId(str);
        return getCachedBusinessAddress(jDLbsHttpOption);
    }

    public void getJDNearbyEntranceAddress(final JDLbsHttpOption jDLbsHttpOption, final JDNearbyEntranceAddressListener jDNearbyEntranceAddressListener) {
        if (jDLbsHttpOption != null && jDNearbyEntranceAddressListener != null) {
            try {
                final JDNearbyEntranceAddress jDNearbyEntranceAddress = new JDNearbyEntranceAddress();
                if (UserUtil.getWJLoginHelper().hasLogin()) {
                    JDBusinessAddressNet.getInstance().getBusinessAddressList(jDLbsHttpOption, new JDLbsHttpListListener<JDBusinessAddress>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.22
                        @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                        public void onFail(JDLbsHttpError jDLbsHttpError) {
                            jDNearbyEntranceAddress.setGlobalAddress(JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption));
                            jDNearbyEntranceAddressListener.onEnd(jDNearbyEntranceAddress);
                        }

                        @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                        public void onSuccess(ArrayList<JDBusinessAddress> arrayList) {
                            if (arrayList != null) {
                                Iterator<JDBusinessAddress> it = arrayList.iterator();
                                while (it.hasNext()) {
                                    JDBusinessAddress next = it.next();
                                    if (next.isAddressDefault()) {
                                        jDNearbyEntranceAddress.setDefaultAddress(next);
                                    }
                                }
                            }
                            jDNearbyEntranceAddress.setGlobalAddress(JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption));
                            jDNearbyEntranceAddressListener.onEnd(jDNearbyEntranceAddress);
                        }
                    });
                } else {
                    jDNearbyEntranceAddress.setGlobalAddress(JDGlobalAddressManager.createJDBusinessAddressWithGlobal(jDLbsHttpOption));
                    jDNearbyEntranceAddressListener.onEnd(jDNearbyEntranceAddress);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void getYFAddress(final JDLbsHttpOption jDLbsHttpOption, JDYFAddressListener jDYFAddressListener) {
        try {
            if (OKLog.D) {
                OKLog.d("LBS", "getYFAddress");
            }
            jDLbsHttpOption.setLat(0.0d);
            jDLbsHttpOption.setLng(0.0d);
            jDLbsHttpOption.setNeedDefault(false);
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (jDYFAddressListener != null) {
                this.mYFListenerList.add(jDYFAddressListener);
            }
            if (this.isYFLocating) {
                return;
            }
            this.isYFLocating = true;
            final CountDownTimer countDownTimer = new CountDownTimer(20000L, 1000L) { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.3
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (JDBusinessAddressManager.this.isYFLocating) {
                        JDBusinessAddressManager.this.onYFAddressFail(jDLbsHttpOption, 503, JDLbsHttpError.MSG_TIME_OUT);
                    }
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                }
            };
            countDownTimer.start();
            JDLocationOption jDLocationOption = new JDLocationOption();
            if (jDLbsHttpOption != null) {
                jDLocationOption.setBusinessId(jDLbsHttpOption.getBusinessId());
                jDLocationOption.setSceneId(jDLbsHttpOption.getSceneId());
            }
            JDLocationManager.getInstance().getLatLng(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager.4
                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onFail(JDLocationError jDLocationError) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    if (jDLocationError == null) {
                        return;
                    }
                    if (jDLocationError.getCode() != 210 && jDLocationError.getCode() != 200) {
                        JDBusinessAddressManager.this.onYFAddressFail(jDLbsHttpOption, jDLocationError.getCode(), jDLocationError.getMsg());
                        return;
                    }
                    JDYFAddress defaultYFAddress = JDBusinessAddressManager.this.getDefaultYFAddress();
                    if (defaultYFAddress.getLat() != 0.0d || defaultYFAddress.getLng() != 0.0d) {
                        JDBusinessAddressManager.this.onYFAddressFail(jDLbsHttpOption, jDLocationError.getCode(), jDLocationError.getMsg());
                        return;
                    }
                    jDLbsHttpOption.setNeedDefault(true);
                    JDBusinessAddressManager.this.requestYFAddress(jDLbsHttpOption);
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onSuccess(JDLocation jDLocation) {
                    CountDownTimer countDownTimer2 = countDownTimer;
                    if (countDownTimer2 != null) {
                        countDownTimer2.cancel();
                    }
                    if (jDLocation == null || (jDLocation.getLat() == 0.0d && jDLocation.getLng() == 0.0d)) {
                        JDBusinessAddressManager.this.onYFAddressFail(jDLbsHttpOption, 501, JDLbsHttpError.MSG_LOCATION_LATLNG_EMPTY);
                        return;
                    }
                    jDLbsHttpOption.setLat(jDLocation.getLat());
                    jDLbsHttpOption.setLng(jDLocation.getLng());
                    JDBusinessAddressManager.this.requestYFAddress(jDLbsHttpOption);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            onYFAddressFail(jDLbsHttpOption, 300, JDLbsHttpError.MSG_EXCEPTION);
        }
    }

    public void updateDefaultYFAddress(JDBusinessAddress jDBusinessAddress) {
        try {
            if (OKLog.D) {
                OKLog.d("LBS", "updateDefaultYFAddress JDBusinessAddress");
            }
            if (jDBusinessAddress != null) {
                JDYFAddress createJDFYAddressWithJDBusinessAddress = createJDFYAddressWithJDBusinessAddress(jDBusinessAddress);
                if (TextUtils.isEmpty(this.key)) {
                    this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
                }
                SharedPreferencesUtil.putString(SP_YFADDRESS, AESUtil.encrypt(createJDFYAddressWithJDBusinessAddress.getJsonStr(), this.key));
            }
        } catch (Exception unused) {
        }
    }

    public void updateDefaultYFAddress(JDLocation jDLocation) {
        try {
            if (OKLog.D) {
                OKLog.d("LBS", "updateDefaultYFAddress JDLocation");
            }
            if (jDLocation != null) {
                JDYFAddress createJDFYAddressWithJDLocation = createJDFYAddressWithJDLocation(jDLocation);
                if (TextUtils.isEmpty(this.key)) {
                    this.key = JdSdk.getInstance().getApplicationContext().getString(R.string.lng_lat_key);
                }
                SharedPreferencesUtil.putString(SP_YFADDRESS, AESUtil.encrypt(createJDFYAddressWithJDLocation.getJsonStr(), this.key));
            }
        } catch (Exception unused) {
        }
    }
}
