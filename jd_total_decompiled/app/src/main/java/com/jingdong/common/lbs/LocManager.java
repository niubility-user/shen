package com.jingdong.common.lbs;

import android.content.Context;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.ISchoolLbs;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public class LocManager {
    @Deprecated
    public static final String LAT_KEY = "lati";
    @Deprecated
    public static final String LNG_KEY = "longi";
    @Deprecated
    public static final int LOCATE_STATE_FAIL = -1;
    @Deprecated
    public static final int LOCATE_STATE_GPS_SUCCESS = 2;
    @Deprecated
    public static final int LOCATE_STATE_NO_PERMISSION = -2;
    @Deprecated
    public static final int LOCATE_STATE_RUNNING = 1;
    @Deprecated
    public static final int LOCATE_STATE_SUCCESS = 0;
    @Deprecated
    public static final int STATE_FAIL = -1;
    @Deprecated
    public static final int STATE_SUCCESS = 0;
    private static final String SYMBOL_BOUND = "_";
    private static final String TAG = "LocManager";
    @Deprecated
    public static final int TIMEOUT_TIME = 60000;
    @Deprecated
    public static int cityId = 0;
    @Deprecated
    public static String cityName = "";
    @Deprecated
    public static String detailAddress = "";
    @Deprecated
    public static int districtId = 0;
    @Deprecated
    public static String districtName = "";
    @Deprecated
    public static boolean isLocateSuccess = false;
    @Deprecated
    public static double lati = 0.0d;
    @Deprecated
    public static int locateState = -1;
    @Deprecated
    public static double longi = 0.0d;
    private static LocManager mLocManager = null;
    @Deprecated
    public static int provinceId = 1;
    @Deprecated
    public static String provinceName = "\u5317\u4eac";
    @Deprecated
    public static int townId = 0;
    @Deprecated
    public static String townName = "";
    private List<OnLbsStateListener> stateListeners = new ArrayList();
    private SchoolLbs mSchoolLbs = new SchoolLbs();

    private LocManager(Context context) {
    }

    @Deprecated
    public static String getCommonLbsParameter() {
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null && addressGlobal.getIdProvince() != 0) {
            return addressGlobal.getIdProvince() + "_" + addressGlobal.getIdCity() + "_" + addressGlobal.getIdArea() + "_" + addressGlobal.getIdTown();
        } else if (isLocateSuccess) {
            return provinceId + "_" + cityId + "_" + districtId + "_" + townId;
        } else {
            return null;
        }
    }

    public static LocManager getInstance() {
        LocManager locManager;
        if (OKLog.D) {
            OKLog.d(TAG, " getInstance -->>  ");
        }
        LocManager locManager2 = mLocManager;
        if (locManager2 != null) {
            return locManager2;
        }
        synchronized (LocManager.class) {
            if (Log.D) {
                Log.d("LocManagersynchronized", "LocManager getInstance");
            }
            if (mLocManager == null) {
                mLocManager = new LocManager(JdSdk.getInstance().getApplication().getApplicationContext());
            }
            locManager = mLocManager;
        }
        return locManager;
    }

    @Deprecated
    public static int getLocateState() {
        if (!PermissionHelper.hasGrantedLocation(PermissionHelper.generateBundle(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, TAG, "queryInfoByLocation"))) {
            locateState = -2;
        }
        return locateState;
    }

    @Deprecated
    public ISchoolLbs.ISchoolLbsSupporter getSchoolLbsSupporter() {
        if (Log.D) {
            Log.d("LocManagersynchronized", "getSchoolLbsSupporter");
        }
        if (this.mSchoolLbs == null) {
            this.mSchoolLbs = new SchoolLbs();
        }
        return this.mSchoolLbs;
    }

    @Deprecated
    public void registStateListener(OnLbsStateListener onLbsStateListener) {
        List<OnLbsStateListener> list;
        if (onLbsStateListener == null || (list = this.stateListeners) == null) {
            return;
        }
        list.add(onLbsStateListener);
    }

    @Deprecated
    public void startLocation() {
        JDLocationManager.getInstance().getAddress(new JDLocationOption(), new JDLocationListener() { // from class: com.jingdong.common.lbs.LocManager.1
            {
                LocManager.this = this;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
            }
        });
    }

    @Deprecated
    public void unRegistSateListener(OnLbsStateListener onLbsStateListener) {
        List<OnLbsStateListener> list;
        if (onLbsStateListener == null || (list = this.stateListeners) == null || !list.contains(onLbsStateListener)) {
            return;
        }
        this.stateListeners.remove(onLbsStateListener);
    }

    @Deprecated
    public void updateSchoolLbsLock(double d, double d2) {
        SchoolLbs schoolLbs = this.mSchoolLbs;
        if (schoolLbs != null) {
            schoolLbs.setGeo(d, d2);
        }
    }
}
