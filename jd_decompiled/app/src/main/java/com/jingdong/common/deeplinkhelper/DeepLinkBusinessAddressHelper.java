package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressSelectedListener;
import com.jingdong.common.lbs.businessAddress.JDUserCityAddressSelectedListener;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkBusinessAddressHelper {
    private static String HOST_BUSINESS_ADDRESS_SELECT = "businessAddressSelect";
    private static String HOST_BUSINESS_MAP = "businessMap";
    private static String HOST_JDLBS_CHECK = "JDLbsCheck";
    private static String HOST_USER_CITY_ADDRESS_SELECT = "userCityAddressSelect";

    public static void startBusinessAddressSelectForResult(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(102))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_BUSINESS_ADDRESS_SELECT).toString(), bundle, i2);
        }
    }

    public static void startBusinessMap(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(102))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_BUSINESS_MAP).toString(), bundle, i2);
        }
    }

    public static void startLbsCheck(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(102))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDLBS_CHECK).toString(), bundle, i2);
        }
    }

    public static void startUserCityAddressSelect(Activity activity, Bundle bundle, int i2, JDUserCityAddressSelectedListener jDUserCityAddressSelectedListener) {
        if (jDUserCityAddressSelectedListener != null) {
            JDBusinessAddressManager.getInstance().setUserCityAddressSelectedListener(jDUserCityAddressSelectedListener);
        }
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(102))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_USER_CITY_ADDRESS_SELECT).toString(), bundle, i2);
        }
    }

    public static void startBusinessAddressSelectForResult(Activity activity, Bundle bundle, int i2, JDBusinessAddressSelectedListener jDBusinessAddressSelectedListener) {
        if (jDBusinessAddressSelectedListener != null) {
            JDBusinessAddressManager.getInstance().setAddressSelectedListener(jDBusinessAddressSelectedListener);
        }
        startBusinessAddressSelectForResult(activity, bundle, i2);
    }
}
