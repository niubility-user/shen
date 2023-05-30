package com.jingdong.common.lbs.jdlocation;

import android.text.TextUtils;
import com.jingdong.app.mall.global.GlobalStatusGetter;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.proxy.LBSCallBack;
import com.jingdong.common.lbs.proxy.LBSListener;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDLocationManager {
    private static JDLocationManager manager;

    /* JADX INFO: Access modifiers changed from: private */
    public AddressGlobal createAddressWithLoc(JDLocation jDLocation) {
        if (jDLocation == null) {
            return new AddressGlobal();
        }
        AddressGlobal addressGlobal = new AddressGlobal();
        addressGlobal.setGeohashLat("" + jDLocation.getGeohashLat());
        addressGlobal.setGeohashLng("" + jDLocation.getGeohashLng());
        addressGlobal.setWhere(jDLocation.getProvinceName() + jDLocation.getCityName() + jDLocation.getDistrictName() + jDLocation.getTownName());
        addressGlobal.setAddressDetail(jDLocation.getDetailAddress());
        addressGlobal.setProvinceName(jDLocation.getProvinceName());
        addressGlobal.setIdProvince(jDLocation.getProvinceId());
        addressGlobal.setCityName(jDLocation.getCityName());
        addressGlobal.setIdCity(jDLocation.getCityId());
        addressGlobal.setAreaName(jDLocation.getDistrictName());
        addressGlobal.setIdArea(jDLocation.getDistrictId());
        addressGlobal.setTownName(jDLocation.getTownName());
        addressGlobal.setIdTown(jDLocation.getTownId());
        addressGlobal.setLatitude(String.valueOf(jDLocation.getLat()));
        addressGlobal.setLongitude(String.valueOf(jDLocation.getLng()));
        addressGlobal.setAddressType(getAddressType(jDLocation));
        addressGlobal.setIsUserAddress(Boolean.FALSE);
        return addressGlobal;
    }

    private static int getAddressType(JDLocation jDLocation) {
        if (jDLocation == null) {
            return 0;
        }
        String callType = jDLocation.getCallType();
        callType.hashCode();
        if (callType.equals(JDLocation.IP_SERVICE)) {
            return 1;
        }
        return !callType.equals(JDLocation.GIS_SERVICE) ? 0 : 3;
    }

    public static JDLocationManager getInstance() {
        JDLocationManager jDLocationManager;
        JDLocationManager jDLocationManager2 = manager;
        if (jDLocationManager2 != null) {
            return jDLocationManager2;
        }
        synchronized (JDLocationManager.class) {
            if (manager == null) {
                manager = new JDLocationManager();
            }
            jDLocationManager = manager;
        }
        return jDLocationManager;
    }

    private void mtaOnLocationChanged(JDLocation jDLocation) {
        if (jDLocation != null) {
            String str = "0_OK_" + jDLocation.getType() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getRequestTime() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getLat() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getLng() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getCoord() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getProvider() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getAccuracy() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getUpdateTime();
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplicationContext(), "", "", "", "LBSLocation_SelfCoordinateExpo", "" + str, "", "", "");
        }
    }

    public void getAddress(JDLocationOption jDLocationOption, JDLocationListener jDLocationListener) {
        try {
            if (DeviceUtil.hasPrivacy()) {
                JDLocationSDK.getInstance().getAddress(jDLocationOption, jDLocationListener);
            } else if (jDLocationListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(103);
                jDLocationError.setMsg(JDLocationError.MSG_NOT_HAS_PRIVACY);
                jDLocationListener.onFail(jDLocationError);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationListener != null) {
                JDLocationError jDLocationError2 = new JDLocationError();
                jDLocationError2.setCode(300);
                jDLocationError2.setMsg(e2.getMessage());
                jDLocationListener.onFail(jDLocationError2);
            }
        }
    }

    public void getLatLng(JDLocationOption jDLocationOption, JDLocationListener jDLocationListener) {
        try {
            if (DeviceUtil.hasPrivacy()) {
                JDLocationSDK.getInstance().getLatLng(jDLocationOption, jDLocationListener);
            } else if (jDLocationListener != null) {
                JDLocationError jDLocationError = new JDLocationError();
                jDLocationError.setCode(103);
                jDLocationError.setMsg(JDLocationError.MSG_NOT_HAS_PRIVACY);
                jDLocationListener.onFail(jDLocationError);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLocationListener != null) {
                JDLocationError jDLocationError2 = new JDLocationError();
                jDLocationError2.setCode(300);
                jDLocationError2.setMsg(e2.getMessage());
                jDLocationListener.onFail(jDLocationError2);
            }
        }
    }

    public void init() {
        try {
            JDLocationSDK.getInstance().init(JdSdk.getInstance().getApplicationContext(), new LBSListener() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationManager.2
                @Override // com.jingdong.common.lbs.proxy.LBSListener
                public String getPin() {
                    return UserUtil.getWJLoginHelper().getPin();
                }

                @Override // com.jingdong.common.lbs.proxy.LBSListener
                public String getUUID() {
                    return StatisticsReportUtil.readDeviceUUID();
                }

                @Override // com.jingdong.common.lbs.proxy.LBSListener
                public boolean hasPrivacy() {
                    return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
                }

                @Override // com.jingdong.common.lbs.proxy.LBSListener
                public boolean isAppForeground() {
                    return GlobalStatusGetter.isAppForeground();
                }

                @Override // com.jingdong.common.lbs.proxy.LBSListener
                public boolean isSceneAllowed(String str) {
                    if (TextUtils.isEmpty(str)) {
                        str = "basicShoppingProcess";
                    }
                    return LBSSceneSwitchHelper.getLbsSceneSwitch(str);
                }
            }).setAppKey("1").setDeviceName("none").setLBSCallBack(new LBSCallBack() { // from class: com.jingdong.common.lbs.jdlocation.JDLocationManager.1
                @Override // com.jingdong.common.lbs.proxy.LBSCallBack
                public void onGetAddressSuccess(JDLocation jDLocation) {
                    try {
                        if (OKLog.D) {
                            OKLog.d("LBS", "onGetAddressSuccess " + jDLocation.getJsonForWeb());
                        }
                        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal("basicShoppingProcess");
                        if (addressGlobal == null || addressGlobal.getAddressNotUserChecked().booleanValue() || addressGlobal.isIp()) {
                            AddressUtil.updateAddressGlobal(JDLocationManager.this.createAddressWithLoc(jDLocation));
                        }
                        JDLocationCache.getInstance().setMtaLocation(jDLocation);
                        LocManager.lati = jDLocation.getLat();
                        LocManager.longi = jDLocation.getLng();
                        LocManager.isLocateSuccess = true;
                        LocManager.provinceId = jDLocation.getProvinceId();
                        LocManager.provinceName = jDLocation.getProvinceName();
                        LocManager.cityId = jDLocation.getCityId();
                        LocManager.cityName = jDLocation.getCityName();
                        LocManager.districtId = jDLocation.getDistrictId();
                        LocManager.districtName = jDLocation.getDistrictName();
                        LocManager.townId = jDLocation.getTownId();
                        LocManager.townName = jDLocation.getTownName();
                        LocManager.detailAddress = jDLocation.getDetailAddress();
                        LocManager.locateState = 0;
                        LocManager.getInstance().updateSchoolLbsLock(jDLocation.getLat(), jDLocation.getLng());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void registerLocationChangedListener(JDLocationOption jDLocationOption, JDLocationChangedListener jDLocationChangedListener) {
        JDLocationSDK.getInstance().registerLocationChangedListener(jDLocationOption, jDLocationChangedListener);
    }

    public void setAppKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDLocationSDK.getInstance().setAppKey(str);
    }

    @Deprecated
    public void startLocationChangedListener() {
    }

    public void unregisterLocationChangedListener(JDLocationOption jDLocationOption) {
        JDLocationSDK.getInstance().unregisterLocationChangedListener(jDLocationOption);
    }
}
