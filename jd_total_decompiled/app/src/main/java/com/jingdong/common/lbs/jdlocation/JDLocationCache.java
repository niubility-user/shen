package com.jingdong.common.lbs.jdlocation;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.CellLocation;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDLocationCache {
    private static JDLocationCache instance;
    private JDLocation emptyLocation = new JDLocation();
    private JDLocation basicShoppingProcessLocation = new JDLocation();
    private JDLocation locServiceLocation = new JDLocation();
    private JDLocation marketingActivitiesLocation = new JDLocation();
    private JDLocation receiveLocation = new JDLocation();

    public static JDLocationCache getInstance() {
        JDLocationCache jDLocationCache;
        JDLocationCache jDLocationCache2 = instance;
        if (jDLocationCache2 != null) {
            return jDLocationCache2;
        }
        synchronized (JDLocationCache.class) {
            if (instance == null) {
                instance = new JDLocationCache();
            }
            jDLocationCache = instance;
        }
        return jDLocationCache;
    }

    @Deprecated
    public String getAddressID(JDLocationCacheOption jDLocationCacheOption) {
        return LocManager.getCommonLbsParameter();
    }

    public CellLocation getBSInfo(JDLocationCacheOption jDLocationCacheOption) {
        try {
            boolean hasLocationPermissionWithScene = PermissionHelper.hasLocationPermissionWithScene("basicShoppingProcess", jDLocationCacheOption.getBusinessId());
            if (BaseInfo.isAgreedPrivacy() && hasLocationPermissionWithScene) {
                return JDLocationSDK.getInstance().getBSInfo(jDLocationCacheOption);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public JDLocation getLocation(JDLocationCacheOption jDLocationCacheOption) {
        return JDLocationSDK.getInstance().getLastLocation(jDLocationCacheOption);
    }

    public JDLocation getMtaLocation(JDLocationCacheOption jDLocationCacheOption) {
        String str;
        String str2;
        boolean z;
        try {
            if (!DeviceUtil.hasPrivacy()) {
                return this.emptyLocation;
            }
            char c2 = 0;
            if (jDLocationCacheOption != null) {
                str2 = jDLocationCacheOption.getSceneId();
                str = jDLocationCacheOption.getBusinessId();
                z = jDLocationCacheOption.isManto();
            } else {
                str = "";
                str2 = "basicShoppingProcess";
                z = false;
            }
            if (DeviceUtil.isAppForeground() || z) {
                switch (str2.hashCode()) {
                    case 126020275:
                        if (str2.equals("marketingActivities")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 179587985:
                        if (str2.equals("receiveAddress")) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 215622105:
                        if (str2.equals("basicShoppingProcess")) {
                            c2 = 3;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1580766037:
                        if (str2.equals("locService")) {
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                if (c2 == 0) {
                    this.locServiceLocation.setBusinessId(str);
                    return this.locServiceLocation;
                } else if (c2 == 1) {
                    this.marketingActivitiesLocation.setBusinessId(str);
                    return this.marketingActivitiesLocation;
                } else if (c2 != 2) {
                    this.basicShoppingProcessLocation.setBusinessId(str);
                    return this.basicShoppingProcessLocation;
                } else {
                    this.receiveLocation.setBusinessId(str);
                    return this.receiveLocation;
                }
            }
            return this.emptyLocation;
        } catch (Exception e2) {
            e2.printStackTrace();
            return this.emptyLocation;
        }
    }

    @SuppressLint({"MissingPermission"})
    public Location getSysLocation(JDLocationCacheOption jDLocationCacheOption) {
        if (jDLocationCacheOption == null) {
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) JdSdk.getInstance().getApplicationContext().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
            List<String> providers = locationManager.getProviders(true);
            if (providers != null && providers.size() != 0 && providers.contains(jDLocationCacheOption.getProvider())) {
                return locationManager.getLastKnownLocation(jDLocationCacheOption.getProvider());
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String getSysLocationJson(JDLocationCacheOption jDLocationCacheOption) {
        try {
            Location sysLocation = getSysLocation(jDLocationCacheOption);
            if (sysLocation != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("Provider", sysLocation.getProvider());
                jSONObject.put("Time", sysLocation.getTime());
                jSONObject.put("Longitude", sysLocation.getLongitude());
                jSONObject.put("Latitude", sysLocation.getLatitude());
                jSONObject.put("Altitude", sysLocation.getAltitude());
                jSONObject.put("Speed", sysLocation.getSpeed());
                jSONObject.put("Bearing", sysLocation.getBearing());
                jSONObject.put("Accuracy", sysLocation.getAccuracy());
                if (Build.VERSION.SDK_INT >= 26) {
                    jSONObject.put("VerticalAccuracyMeters", sysLocation.getVerticalAccuracyMeters());
                } else {
                    jSONObject.put("VerticalAccuracyMeters", 0);
                }
                return jSONObject.toString();
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public boolean isMockLocation(JDLocationCacheOption jDLocationCacheOption) {
        try {
            boolean hasLocationPermissionWithScene = PermissionHelper.hasLocationPermissionWithScene("basicShoppingProcess", jDLocationCacheOption.getBusinessId());
            if (BaseInfo.isAgreedPrivacy() && hasLocationPermissionWithScene) {
                return JDLocationSDK.getInstance().isMockLocation(jDLocationCacheOption);
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public void setMtaLocation(JDLocation jDLocation) {
        if (jDLocation != null) {
            try {
                JDLocation jDLocation2 = (JDLocation) jDLocation.clone();
                this.basicShoppingProcessLocation = jDLocation2;
                jDLocation2.setSceneId("basicShoppingProcess");
                JDLocation jDLocation3 = (JDLocation) jDLocation.clone();
                this.locServiceLocation = jDLocation3;
                jDLocation3.setSceneId("locService");
                JDLocation jDLocation4 = (JDLocation) jDLocation.clone();
                this.marketingActivitiesLocation = jDLocation4;
                jDLocation4.setSceneId("marketingActivities");
                JDLocation jDLocation5 = (JDLocation) jDLocation.clone();
                this.receiveLocation = jDLocation5;
                jDLocation5.setSceneId("receiveAddress");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Deprecated
    public JDLocation getLocation() {
        return JDLocationSDK.getInstance().getLastLocation(new JDLocationOption());
    }
}
