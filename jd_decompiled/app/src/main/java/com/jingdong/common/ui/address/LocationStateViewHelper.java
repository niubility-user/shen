package com.jingdong.common.ui.address;

import android.text.TextUtils;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes6.dex */
public class LocationStateViewHelper {
    private static final int ALERT_SHOW_TIMES_MAX = 10;
    private static final long ALERT_SHOW_TIME_LAG_MAX = 604800000;
    private static final String SP_KEY_ALERT_SHOW_LAG = "un_alert_show_lag";
    private static final String SP_KEY_ALERT_SHOW_TIMES = "un_alert_show_times";
    private String sceneId;

    public LocationStateViewHelper(String str) {
        this.sceneId = str;
    }

    public static boolean canShowAlert() {
        return System.currentTimeMillis() - SharedPreferencesUtil.getLong(SP_KEY_ALERT_SHOW_LAG, 0L) > 604800000 || SharedPreferencesUtil.getInt(SP_KEY_ALERT_SHOW_TIMES, 0) <= 10;
    }

    private AddressGlobal createAddressWithLoc() {
        return UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
    }

    private String getNull(String str) {
        return str == null ? "" : str;
    }

    public static boolean isGangAoTai(int i2) {
        return i2 == 32 || i2 == 52993 || i2 == 53283;
    }

    public static void putAlertShowTime() {
        int i2 = SharedPreferencesUtil.getInt(SP_KEY_ALERT_SHOW_TIMES, 0);
        long j2 = SharedPreferencesUtil.getLong(SP_KEY_ALERT_SHOW_LAG, 0L);
        long currentTimeMillis = System.currentTimeMillis();
        int i3 = currentTimeMillis - j2 <= 604800000 ? i2 : 0;
        SharedPreferencesUtil.putLong(SP_KEY_ALERT_SHOW_LAG, currentTimeMillis);
        SharedPreferencesUtil.putInt(SP_KEY_ALERT_SHOW_TIMES, i3 + 1);
    }

    public String getSceneId() {
        return this.sceneId;
    }

    public boolean isLocal(AddressGlobal addressGlobal) {
        AddressGlobal locAddressGlobal;
        if (addressGlobal != null && (locAddressGlobal = UnAddressSelectUtils.getLocAddressGlobal(this.sceneId)) != null && !TextUtils.isEmpty(locAddressGlobal.getLatitude()) && !TextUtils.isEmpty(locAddressGlobal.getLongitude())) {
            try {
                float floatValue = Float.valueOf(locAddressGlobal.getLatitude()).floatValue();
                float floatValue2 = Float.valueOf(locAddressGlobal.getLongitude()).floatValue();
                if (floatValue == 0.0f || floatValue2 == 0.0f) {
                    return false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (addressGlobal.getIsUserAddress().booleanValue()) {
                return false;
            }
            if (TextUtils.equals(String.valueOf(addressGlobal.getIdProvince()) + String.valueOf(addressGlobal.getIdCity()) + String.valueOf(addressGlobal.getIdArea()) + String.valueOf(addressGlobal.getIdTown()), String.valueOf(locAddressGlobal.getIdProvince()) + String.valueOf(locAddressGlobal.getIdCity()) + String.valueOf(locAddressGlobal.getIdArea()) + String.valueOf(locAddressGlobal.getIdTown()))) {
                return true;
            }
        }
        return false;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    public LocationStateViewEntity showAddress() {
        LocationStateViewEntity locationStateViewEntity = new LocationStateViewEntity();
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal == null) {
            locationStateViewEntity.isLocal = true;
            locationStateViewEntity.isSelect = false;
            AddressGlobal createAddressWithLoc = createAddressWithLoc();
            locationStateViewEntity.address = createAddressWithLoc;
            if (!TextUtils.isEmpty(createAddressWithLoc.getAddressDetail())) {
                AddressUtil.updateAddressGlobal(locationStateViewEntity.address);
            }
            return locationStateViewEntity;
        } else if (addressGlobal.getIsUserAddress().booleanValue()) {
            locationStateViewEntity.isLocal = true;
            locationStateViewEntity.isSelect = false;
            locationStateViewEntity.address = createAddressWithLoc();
            return locationStateViewEntity;
        } else if (TextUtils.isEmpty(addressGlobal.getAddressDetail())) {
            locationStateViewEntity.isLocal = true;
            locationStateViewEntity.isSelect = false;
            locationStateViewEntity.address = createAddressWithLoc();
            return locationStateViewEntity;
        } else {
            locationStateViewEntity.isLocal = isLocal(addressGlobal);
            locationStateViewEntity.address = addressGlobal;
            locationStateViewEntity.isSelect = true;
            return locationStateViewEntity;
        }
    }

    public AddressGlobal showAddressNormal() {
        AddressGlobal createAddressWithLoc = createAddressWithLoc();
        return TextUtils.isEmpty(createAddressWithLoc.getAddressDetail()) ? AddressUtil.getAddressGlobal() : createAddressWithLoc;
    }
}
