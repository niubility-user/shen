package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.entity.SelfUserAddress;
import com.jingdong.common.entity.UserAddress;
import com.jingdong.common.entity.UserInfo;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.listener.OnShortAddressListener;
import com.jingdong.jdsdk.utils.JdStringUtils;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AddressUtil {
    private static final String ADDRESS_CHANGE = "AddressChange";
    private static final String ADDRESS_GLOBAL_ALL = "AddressGlobal";
    private static final String ADDRESS_GLOBAL_TAG = "Global_";
    private static final String TAG = "AddressUtil";
    private static SharedPreferences sharedPreferences = CommonBase.getJdSharedPreferences();

    public static void clearAddressChange() {
        try {
            sharedPreferences.edit().putBoolean(ADDRESS_CHANGE, false).commit();
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void clearAddressGlobal() {
        sharedPreferences.edit().remove(ADDRESS_GLOBAL_ALL).commit();
        sharedPreferences.edit().remove(getGlobalUserKey()).commit();
        sharedPreferences.edit().remove(getGlobalUserKeyMD5()).commit();
    }

    public static String decodeValue(JSONObject jSONObject, String str) {
        return (jSONObject == null || TextUtils.isEmpty(str)) ? "" : decodeValue(jSONObject.optString(str));
    }

    public static void encodeValue(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (JdStringUtils.isEncrypt(str2)) {
                String decodeValue = decodeValue(str2);
                try {
                    jSONObject.put(str, DesCommonUtils.encryptThreeDESECB(decodeValue, DesCommonUtils.key));
                } catch (Exception unused) {
                    jSONObject.put(str, decodeValue);
                }
            } else {
                jSONObject.put(str, str2);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:261:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AddressGlobal getAddressGlobal(String str) {
        String str2;
        String str3;
        String str4;
        AddressGlobal addressGlobal;
        String globalUserKey = getGlobalUserKey();
        String globalUserKeyMD5 = getGlobalUserKeyMD5();
        AddressGlobal addressGlobal2 = null;
        try {
            str3 = sharedPreferences.getString(globalUserKey, "");
            try {
                str2 = sharedPreferences.getString(globalUserKeyMD5, "");
                try {
                    str4 = sharedPreferences.getString(ADDRESS_GLOBAL_ALL, "");
                } catch (Exception e2) {
                    e = e2;
                    str4 = null;
                    OKLog.e(TAG, e);
                    if (!TextUtils.isEmpty(str2)) {
                    }
                    if (!TextUtils.isEmpty(str4)) {
                    }
                    if (LoginUserBase.hasLogin()) {
                    }
                    return getOldAddress();
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "";
            }
        } catch (Exception e4) {
            e = e4;
            str2 = "";
            str3 = null;
            str4 = null;
        }
        try {
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("1.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 ADDRESS_GLOBAL_USER ");
                sb.append(globalUserKey);
                sb.append(" = ");
                sb.append(TextUtils.isEmpty(str3) ? str2 : str3);
                OKLog.d(TAG, sb.toString());
                OKLog.d(TAG, "2.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 ADDRESS_GLOBAL_ALL = " + str4);
            }
        } catch (Exception e5) {
            e = e5;
            OKLog.e(TAG, e);
            if (!TextUtils.isEmpty(str2)) {
            }
            if (!TextUtils.isEmpty(str4)) {
            }
            if (LoginUserBase.hasLogin()) {
            }
            return getOldAddress();
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                addressGlobal = (AddressGlobal) JDJSON.parseObject(str2, AddressGlobal.class);
                try {
                    if (!TextUtils.equals(globalUserKey, globalUserKeyMD5) && addressGlobal != null && sharedPreferences.contains(globalUserKey)) {
                        sharedPreferences.edit().remove(globalUserKey).commit();
                    }
                } catch (Exception e6) {
                    e = e6;
                    OKLog.e(TAG, e);
                    if (!TextUtils.isEmpty(str4)) {
                    }
                    if (LoginUserBase.hasLogin()) {
                    }
                    return getOldAddress();
                }
            } catch (Exception e7) {
                e = e7;
                addressGlobal = null;
            }
        } else if (TextUtils.isEmpty(str3)) {
            addressGlobal = null;
        } else {
            try {
                addressGlobal = (AddressGlobal) JDJSON.parseObject(str3, AddressGlobal.class);
                if (addressGlobal != null) {
                    try {
                        updateAddressGlobal(addressGlobal, addressGlobal.getTimeStamp(), null, false);
                        if (!TextUtils.equals(globalUserKey, globalUserKeyMD5) && sharedPreferences.contains(globalUserKey)) {
                            sharedPreferences.edit().remove(globalUserKey).commit();
                        }
                    } catch (Exception e8) {
                        e = e8;
                        OKLog.e(TAG, e);
                        if (!TextUtils.isEmpty(str4)) {
                        }
                        if (LoginUserBase.hasLogin()) {
                        }
                        return getOldAddress();
                    }
                }
            } catch (Exception e9) {
                e = e9;
                addressGlobal = null;
            }
        }
        if (!TextUtils.isEmpty(str4)) {
            try {
                addressGlobal2 = (AddressGlobal) JDJSON.parseObject(str4, AddressGlobal.class);
            } catch (Exception e10) {
                OKLog.e(TAG, e10);
            }
        }
        if (LoginUserBase.hasLogin()) {
            if (addressGlobal != null && addressGlobal2 != null) {
                if (addressGlobal.getTimeStamp() >= addressGlobal2.getTimeStamp()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "3.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 ADDRESS_GLOBAL_USER = " + addressGlobal.getTimeStamp());
                    }
                    return addressGlobal;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "3.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 ADDRESS_GLOBAL_ALL = " + addressGlobal2.getTimeStamp());
                }
                return addressGlobal2;
            } else if (addressGlobal != null) {
                return addressGlobal;
            } else {
                if (addressGlobal2 != null) {
                    if (addressGlobal2.isLocation() && !UnAddressSelectUtils.isSceneSwitch(str)) {
                        addressGlobal2.setLatitude(addressGlobal2.getGeohashLat());
                        addressGlobal2.setLongitude(addressGlobal2.getGeohashLng());
                        addressGlobal2.setAddressDetail("");
                        addressGlobal2.setWhere("");
                    }
                    return addressGlobal2;
                }
            }
        } else if (addressGlobal2 != null) {
            if (addressGlobal2.isLocation() && !UnAddressSelectUtils.isSceneSwitch(str)) {
                addressGlobal2.setLatitude(addressGlobal2.getGeohashLat());
                addressGlobal2.setLongitude(addressGlobal2.getGeohashLng());
                addressGlobal2.setAddressDetail("");
                addressGlobal2.setWhere("");
            }
            return addressGlobal2;
        }
        return getOldAddress();
    }

    public static AddressGlobal getAddressGlobalByAll() {
        AddressGlobal addressGlobal = null;
        try {
            String string = sharedPreferences.getString(ADDRESS_GLOBAL_ALL, "");
            if (!TextUtils.isEmpty(string)) {
                addressGlobal = (AddressGlobal) JDJSON.parseObject(string, AddressGlobal.class);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        if (addressGlobal != null) {
            addressGlobal.setIsUserAddress(Boolean.FALSE);
        }
        return addressGlobal;
    }

    public static int getAddressListSize(ArrayList<UserAddress> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return 0;
        }
        return arrayList.size();
    }

    public static UserAddress getDefaultAddress(ArrayList<UserAddress> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            Iterator<UserAddress> it = arrayList.iterator();
            while (it.hasNext()) {
                UserAddress next = it.next();
                if (next != null && next.IsDefaultAddr().booleanValue()) {
                    return next;
                }
            }
        }
        return null;
    }

    private static UserAddress getFirstNormalAddress(ArrayList<UserAddress> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        Iterator<UserAddress> it = arrayList.iterator();
        while (it.hasNext()) {
            UserAddress next = it.next();
            if (next != null && !(next instanceof SelfUserAddress)) {
                return next;
            }
        }
        return null;
    }

    public static String getGlobalUserKey() {
        String loginUserName = LoginUserBase.getLoginUserName();
        if (TextUtils.isEmpty(loginUserName)) {
            return "";
        }
        return ADDRESS_GLOBAL_TAG + loginUserName;
    }

    public static String getGlobalUserKeyMD5() {
        String loginUserName = LoginUserBase.getLoginUserName();
        String str = "";
        if (!TextUtils.isEmpty(loginUserName)) {
            try {
                str = Md5Encrypt.md5(loginUserName);
            } catch (Exception unused) {
            }
        }
        return ADDRESS_GLOBAL_TAG + str;
    }

    private static AddressGlobal getOldAddress() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        String provinceIDFromSharedPreferences = AddressStorageUtil.getProvinceIDFromSharedPreferences();
        String cityIDFromSharedPreferences = AddressStorageUtil.getCityIDFromSharedPreferences();
        String districtIdFromSharedPreferences = AddressStorageUtil.getDistrictIdFromSharedPreferences();
        String townIdFromSharedPreferences = AddressStorageUtil.getTownIdFromSharedPreferences();
        int i9 = 0;
        try {
            i5 = !TextUtils.isEmpty(provinceIDFromSharedPreferences) ? Integer.parseInt(provinceIDFromSharedPreferences) : 0;
            try {
                i6 = !TextUtils.isEmpty(cityIDFromSharedPreferences) ? Integer.parseInt(cityIDFromSharedPreferences) : 0;
                try {
                    i7 = !TextUtils.isEmpty(districtIdFromSharedPreferences) ? Integer.parseInt(districtIdFromSharedPreferences) : 0;
                    try {
                        if (!TextUtils.isEmpty(townIdFromSharedPreferences)) {
                            i9 = Integer.parseInt(townIdFromSharedPreferences);
                        }
                    } catch (NumberFormatException e2) {
                        i8 = i6;
                        i2 = i5;
                        e = e2;
                        i4 = i7;
                        i3 = i8;
                        OKLog.e(TAG, e);
                        i5 = i2;
                        i6 = i3;
                        i7 = i4;
                        return i5 == 0 ? null : null;
                    }
                } catch (NumberFormatException e3) {
                    i4 = 0;
                    i8 = i6;
                    i2 = i5;
                    e = e3;
                }
            } catch (NumberFormatException e4) {
                i3 = 0;
                i4 = 0;
                i2 = i5;
                e = e4;
            }
        } catch (NumberFormatException e5) {
            e = e5;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        if (i5 == 0 && i6 != 0) {
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setIdProvince(i5);
            addressGlobal.setIdCity(i6);
            addressGlobal.setIdArea(i7);
            addressGlobal.setIdTown(i9);
            addressGlobal.setIsUserAddress(Boolean.FALSE);
            updateAddressGlobal(addressGlobal);
            return addressGlobal;
        }
    }

    public static UserAddress getSelectedUserAddress(UserAddress userAddress, ArrayList<UserAddress> arrayList) {
        if (getAddressListSize(arrayList) <= 0) {
            return null;
        }
        if (isContainCurrentSelectedAddress(userAddress, arrayList)) {
            return userAddress;
        }
        UserAddress defaultAddress = getDefaultAddress(arrayList);
        return defaultAddress == null ? getFirstNormalAddress(arrayList) : defaultAddress;
    }

    public static UserInfo getUpdateUserInfo(UserAddress userAddress) {
        if (userAddress == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAddress(userAddress);
        return userInfo;
    }

    public static boolean isAddressChanged() {
        try {
            return sharedPreferences.getBoolean(ADDRESS_CHANGE, false);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    public static boolean isContainCurrentSelectedAddress(UserAddress userAddress, List<UserAddress> list) {
        if (userAddress != null && list != null && list.size() > 0) {
            for (UserAddress userAddress2 : list) {
                long j2 = userAddress.id;
                if (j2 != 0 && userAddress2.id == j2) {
                    return true;
                }
                if (j2 != 0 && matchCurrentUserAddress(userAddress2, userAddress)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isForeignOverSea(int i2) {
        return i2 == 53283;
    }

    public static boolean isGangAoTai(int i2) {
        return i2 == 52993 || i2 == 32;
    }

    public static boolean matchCurrentUserAddress(UserAddress userAddress, UserAddress userAddress2) {
        if (userAddress == null || userAddress2 == null) {
            return false;
        }
        String name = userAddress2.getName();
        String where = userAddress2.getWhere();
        String mobile = userAddress2.getMobile();
        String name2 = userAddress.getName();
        String where2 = userAddress.getWhere();
        String mobile2 = userAddress.getMobile();
        return (TextUtils.isEmpty(name) || TextUtils.isEmpty(where) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(name2) || TextUtils.isEmpty(where2) || TextUtils.isEmpty(mobile2) || !TextUtils.equals(name.trim(), name2.trim()) || !TextUtils.equals(where.trim(), where2.trim()) || !TextUtils.equals(mobile.trim(), mobile2.trim())) ? false : true;
    }

    public static void onAddressChanged() {
        sharedPreferences.edit().putBoolean(ADDRESS_CHANGE, true).commit();
    }

    private static boolean shortAddressEnable() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "shortAddressEnable");
        return TextUtils.isEmpty(config) || TextUtils.equals(config, "1");
    }

    public static void updateAddressByAll(AddressGlobal addressGlobal) {
        AddressGlobal addressGlobalByAll = getAddressGlobalByAll();
        if (addressGlobalByAll == null) {
            addressGlobalByAll = new AddressGlobal();
        }
        updateAddressGlobal(AddressGlobal.cloneAddressGlobal(addressGlobal, addressGlobalByAll), 0L, null, true);
    }

    public static boolean updateAddressGlobal(AddressGlobal addressGlobal) {
        if (addressGlobal != null) {
            addressGlobal.setAddressNotUserChecked(Boolean.FALSE);
        }
        return updateAddressGlobal(addressGlobal, System.currentTimeMillis(), null, true);
    }

    public static boolean updateAddressGlobalNotUserChecked(AddressGlobal addressGlobal, boolean z) {
        if (addressGlobal != null) {
            addressGlobal.setAddressNotUserChecked(Boolean.valueOf(z));
        }
        return updateAddressGlobal(addressGlobal, System.currentTimeMillis(), null, false);
    }

    public static String decodeValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            if (JdStringUtils.isEncrypt(str)) {
                return DesCommonUtils.decryptThreeDESECB(str, DesCommonUtils.key);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return str;
    }

    public static boolean updateAddressGlobal(AddressGlobal addressGlobal, OnShortAddressListener onShortAddressListener) {
        if (addressGlobal != null) {
            addressGlobal.setAddressNotUserChecked(Boolean.FALSE);
        }
        return updateAddressGlobal(addressGlobal, System.currentTimeMillis(), onShortAddressListener, true);
    }

    public static boolean updateAddressGlobal(AddressGlobal addressGlobal, boolean z) {
        if (addressGlobal != null) {
            addressGlobal.setAddressNotUserChecked(Boolean.FALSE);
        }
        return updateAddressGlobal(addressGlobal, System.currentTimeMillis(), null, z);
    }

    private static boolean updateAddressGlobal(AddressGlobal addressGlobal, long j2, OnShortAddressListener onShortAddressListener, boolean z) {
        if (addressGlobal == null) {
            return false;
        }
        addressGlobal.isForeignOverSea = isForeignOverSea(addressGlobal.getIdProvince());
        addressGlobal.isGangAoTai = isGangAoTai(addressGlobal.getIdProvince());
        String loginUserName = LoginUserBase.getLoginUserName();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (OKLog.D) {
            OKLog.d(TAG, "1.\u66f4\u65b0\u5168\u5c40\u5730\u5740\u7f13\u5b58 ADDRESS_GLOBAL_USER = " + loginUserName + "  : " + JDJSON.toJSONString(addressGlobal));
        }
        if (addressGlobal.getIsUserAddress().booleanValue() && !TextUtils.isEmpty(loginUserName)) {
            addressGlobal.setAddressTitle("");
            if (z) {
                addressGlobal.setSubAddressDetail("");
            }
            addressGlobal.setAddressType(2);
            boolean commit = edit.putString(getGlobalUserKeyMD5(), addressGlobal.toString()).commit();
            if (shortAddressEnable() && z && commit) {
                UnAddressSelectUtils.requestShortAddress(addressGlobal, onShortAddressListener);
            } else if (onShortAddressListener != null) {
                onShortAddressListener.shortAddress(addressGlobal);
            }
            return commit;
        }
        onAddressChanged();
        addressGlobal.setIsUserAddress(Boolean.FALSE);
        return edit.putString(ADDRESS_GLOBAL_ALL, addressGlobal.toString(j2)).commit();
    }

    public static AddressGlobal getAddressGlobal() {
        return getAddressGlobal(null);
    }
}
