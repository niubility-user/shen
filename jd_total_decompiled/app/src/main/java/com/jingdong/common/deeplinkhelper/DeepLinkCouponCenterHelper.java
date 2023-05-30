package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class DeepLinkCouponCenterHelper {
    public static final String DISPLAY_TAB_MAIN_AND_MARKET = "2";
    public static final String EXTRA_REFRESH_COUPON = "refresh_coupon";
    private static final String HOST_COUPONCENTER_MARKET_ACTIVITY = "couponcenter_market";
    private static final String TAB_ID_KEY = "tab";
    private static final String TAB_ID_VALUE_MARKET = "1";
    public static final int TAB_MAIN = 0;
    public static final int TAB_MARKET = 1;
    private static final String TAG = "DeepLinkCouponCenterHelper";

    private static boolean checkDeepLinkHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        a d = b.a().d(new DeepLinkUri.Builder().scheme("jingdong").host(str).toString());
        return (d == null || TextUtils.isEmpty(d.b())) ? false : true;
    }

    public static String getCouponActivityUrl() {
        ConcurrentHashMap<String, String> concurrentHashMap = ConfigUtil.sServerConfigHashMap;
        return (concurrentHashMap == null || !concurrentHashMap.containsKey(ConfigUtil.KEY_COUPON_ACTIVITY_URL)) ? "" : concurrentHashMap.get(ConfigUtil.KEY_COUPON_ACTIVITY_URL);
    }

    public static String getDisplayCouponTabConfig() {
        ConcurrentHashMap<String, String> concurrentHashMap = ConfigUtil.sServerConfigHashMap;
        return (concurrentHashMap == null || !concurrentHashMap.containsKey(ConfigUtil.KEY_COUPON_TAB_VALUE)) ? "2" : concurrentHashMap.get(ConfigUtil.KEY_COUPON_TAB_VALUE);
    }

    public static boolean isDisplayCouponMarket() {
        ConcurrentHashMap<String, String> concurrentHashMap = ConfigUtil.sServerConfigHashMap;
        if (concurrentHashMap == null || !concurrentHashMap.containsKey(ConfigUtil.KEY_DISPLAY_COUPON_MARKET)) {
            return true;
        }
        return DYConstants.DY_TRUE.equals(concurrentHashMap.get(ConfigUtil.KEY_DISPLAY_COUPON_MARKET));
    }

    private static boolean isStartMarket(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("tab");
        if (!TextUtils.isEmpty(string)) {
            return "1".equals(string);
        }
        int i2 = bundle.getInt("tab", -1);
        if (i2 != -1) {
            return "1".equals(String.valueOf(i2));
        }
        return false;
    }

    public static void startCouponCenter(Context context) {
        startCouponCenter(context, null, null);
    }

    public static void startCouponCenterByTab(Context context, String str, int i2) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("from", str);
        }
        bundle.putInt("tab", i2);
        startCouponCenter(context, bundle, null);
    }

    public static void startCouponCenterByTabForResult(Context context, String str, int i2, int i3) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("from", str);
        }
        if (i2 == 0 || i2 == 1) {
            bundle.putInt("tab", i2);
        }
        startCouponCenterDynamic(context, bundle, true, i3);
    }

    private static void startCouponCenterDynamic(Context context, Bundle bundle, boolean z, int i2) {
        if (checkDeepLinkHost("couponcenter") && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(11))) {
            if (z && (context instanceof Activity)) {
                DeepLinkCommonHelper.startActivityForResult((Activity) context, "couponcenter", bundle, i2);
                return;
            } else {
                DeepLinkCommonHelper.startActivityDirect(context, "couponcenter", bundle);
                return;
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "https://coupon.m.jd.com/center/getCouponCenter.action");
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle2);
    }

    public static void startMarketHistory(BaseActivity baseActivity) {
        if (checkDeepLinkHost(DeepLinkCommonHelper.HOST_MARKET_HISTORY_ACTIVITY) && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(11))) {
            DeepLinkCommonHelper.startActivity(baseActivity, DeepLinkCommonHelper.HOST_MARKET_HISTORY_ACTIVITY, null, true, 0, true, "");
        }
    }

    public static void startCouponCenter(Context context, Bundle bundle) {
        startCouponCenter(context, bundle, null);
    }

    public static void startCouponCenter(Context context, String str) {
        startCouponCenter(context, null, str);
    }

    public static void startCouponCenter(BaseActivity baseActivity, int i2) {
        startCouponCenterDynamic(baseActivity, null, true, i2);
    }

    private static void startCouponCenter(Context context, Bundle bundle, String str) {
        if (bundle != null) {
            startCouponCenterDynamic(context, bundle, false, -1);
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("from", str);
            startCouponCenterDynamic(context, bundle2, false, -1);
        } else {
            startCouponCenterDynamic(context, null, false, -1);
        }
    }
}
