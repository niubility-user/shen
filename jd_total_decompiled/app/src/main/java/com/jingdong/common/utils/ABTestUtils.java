package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;

/* loaded from: classes.dex */
public class ABTestUtils {
    private static final String A_HOSTORY_ID = "wareHistory";
    private static final String A_PRDUCT_DETAIL_ID = "productDetail";
    private static final String A_PRDUCT_DETAIL_ID_NEW = "1";
    private static final String B_PRDUCT_DETAIL_ID = "productDetailBTest";
    private static final String B_PRDUCT_DETAIL_ID_NEW = "0";
    private static final String KEY_AB_SHAKE_ID = "shakeAbTest";
    private static final String KEY_AURA_PRODUCT = "productAura";
    public static final String KEY_BASE_ARCH_CONFIG_NAMESPACE = "abTest";
    public static final String KEY_COMMON_ENABLE = "enable";
    public static final String KEY_CONFIG_ACTIVITY_CONTROL = "activityNumControl";
    public static final String KEY_CONFIG_CRASH_RECOVER_STACK_ENABLE = "crashRecoverStack";
    public static final String KEY_CONFIG_CRASH_SETTINGS = "crashSettings";
    public static final String KEY_CONFIG_ENCRYPT_ENABLE = "encrypt_enable";
    public static final String KEY_CONFIG_FUNCTIONID_LIST = "functionid_list";
    public static final String KEY_CONFIG_GLOBAL_ENCRYPT_FLAG = "global_encrypt_flag";
    public static final String KEY_CONFIG_LOTTIE_SWITCHER = "lottieSwitcher";
    public static final String KEY_CONFIG_LOTTIE_SWITCHER_ENABLE = "isLottieEnable";
    public static final String KEY_CONFIG_PATRONS = "patronsConfig";
    public static final String KEY_CONFIG_PLAINTEXT_LIST = "plaintext_list";
    public static final String KEY_CONFIG_UUID_DECODE = "uuidEncode";
    public static final String KEY_CONFIG_UUID_ENCRYPT = "uuidEncrypt";
    private static final String KEY_COUPON_MERGE = "couponMerge";
    private static final String KEY_HARDWARE_ACCELERATE = "hardwareAccelerate";
    private static final String KEY_OPT_CDN_FEATURE = "optCDNFeatureFlag";
    private static final String KEY_OPT_COMBINE_SETTING = "optCombineSetting";
    public static final String KEY_PARAM_CONTROL_LIST = "controlActivity";
    public static final String KEY_PARAM_ENABLE = "enable";
    public static final String KEY_PARAM_MAX_NUM = "maxStackNum";
    public static final String KEY_PARAM_NOT_CONTROL_LIST = "notStackActivity";
    public static final String KEY_PARAM_PATRONS_ENABLE = "enable";
    public static final String KEY_PARAM_THIRD_START_UP = "thirdStartup";
    private static final String KEY_PRDUCT_DETAIL_ID = "productDetail_3Tab";
    private static final String KEY_PRDUCT_DETAIL_ID_NEW = "skuDetail";
    private static final String KEY_UNI_ACTIVITY_NUM = "androidActivityAnim";
    private static final String KEY_UN_STATUS_BAR_TINT = "statusBarTint";
    public static final String VALUE_COMMON_DISABLE = "0";
    public static final String VALUE_COMMON_ENABLE = "1";
    private static final String VAL_AURA_PRODUCT_NATIVE = "native";
    private static final String VAL_AURA_PRODUCT_PLUGIN = "plugin";
    private static final String VAL_OPT_CDN_FEATURE_DISABLE = "disable";
    private static final String VAL_OPT_CDN_FEATURE_ENABLE = "enable";
    private static final String VAL_OPT_COMBINE_SETTING_DISABLE = "disable";
    private static final String VAL_OPT_COMBINE_SETTING_ENABLE = "enable";
    public static volatile boolean abTest800Style;
    public static volatile boolean abTest900Style;
    private static String couponMerge;
    private static String optCDNFeatureFlag;
    private static String optCombineSettingFlag;
    private static String optPluginFlag;
    private static String productType;
    private static String productType_new;

    private static final String getDefaultOptCDNFeatureFlag() {
        return CommonBase.getJdSharedPreferences().getString(KEY_OPT_CDN_FEATURE, "disable");
    }

    private static final String getDefaultOptCombineSettingFlag() {
        return CommonBase.getJdSharedPreferences().getString(KEY_OPT_COMBINE_SETTING, "disable");
    }

    private static final String getDefaultProductDetailID() {
        return CommonBase.getJdSharedPreferences().getString(KEY_PRDUCT_DETAIL_ID, B_PRDUCT_DETAIL_ID);
    }

    public static void initABData(HttpGroup httpGroup) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        productType = B_PRDUCT_DETAIL_ID;
        edit.putString(KEY_PRDUCT_DETAIL_ID, B_PRDUCT_DETAIL_ID);
        edit.putString("productCommentABTestType", "B");
        edit.putString("productDetail_cutPrice", "B");
        edit.putString("productDetail_productArrival", "B");
        optCDNFeatureFlag = "disable";
        edit.putString(KEY_OPT_CDN_FEATURE, "disable");
        optPluginFlag = VAL_AURA_PRODUCT_PLUGIN;
        edit.putString(KEY_AURA_PRODUCT, VAL_AURA_PRODUCT_PLUGIN);
        productType_new = "1";
        edit.putString("skuDetail", "1");
        couponMerge = "B";
        edit.putString(KEY_COUPON_MERGE, "B");
        edit.commit();
        FunctionAccessUtil.updateLastAccess(Configuration.KEY_LAST_ACCESS_ABTEST);
    }

    public static boolean is800UIStyle() {
        return abTest800Style;
    }

    public static boolean is900UIStyle() {
        return abTest900Style;
    }

    public static boolean isCouponMergeB() {
        if (TextUtils.isEmpty(couponMerge)) {
            couponMerge = CommonBase.getJdSharedPreferences().getString(KEY_COUPON_MERGE, "B");
        }
        return "B".equals(couponMerge);
    }

    public static boolean isLottieEnable() {
        return "1".equals(JDMobileConfig.getInstance().getConfig(KEY_BASE_ARCH_CONFIG_NAMESPACE, "lottieSwitcher", KEY_CONFIG_LOTTIE_SWITCHER_ENABLE, "1"));
    }

    public static boolean isOptCDNFeatureEnabled() {
        if (TextUtils.isEmpty(optCDNFeatureFlag)) {
            optCDNFeatureFlag = getDefaultOptCDNFeatureFlag();
        }
        return "enable".equals(optCDNFeatureFlag);
    }

    public static boolean isOptCombineSettingEnabled() {
        if (TextUtils.isEmpty(optCombineSettingFlag)) {
            optCombineSettingFlag = getDefaultOptCombineSettingFlag();
        }
        return "enable".equals(optCombineSettingFlag);
    }

    public static boolean isProductAuraPlugin() {
        if (TextUtils.isEmpty(optPluginFlag)) {
            optPluginFlag = CommonBase.getJdSharedPreferences().getString(KEY_AURA_PRODUCT, "native");
        }
        return VAL_AURA_PRODUCT_PLUGIN.equals(optPluginFlag);
    }

    public static boolean isProductDetailAType() {
        if (TextUtils.isEmpty(productType)) {
            productType = getDefaultProductDetailID();
        }
        return "productDetail".equals(productType);
    }

    public static boolean useHardwareAccelerate() {
        return "1".equals(JDMobileConfig.getInstance().getConfig(KEY_BASE_ARCH_CONFIG_NAMESPACE, "hardwareAccelerate", "enable", "0"));
    }

    public static boolean useStatusBarTint() {
        return "1".equals(JDMobileConfig.getInstance().getConfig(KEY_BASE_ARCH_CONFIG_NAMESPACE, "statusBarTint", "enable", "1"));
    }

    public static boolean useUniActivityAnim() {
        return "1".equals(JDMobileConfig.getInstance().getConfig(KEY_BASE_ARCH_CONFIG_NAMESPACE, "androidActivityAnim", "enable", "1"));
    }
}
