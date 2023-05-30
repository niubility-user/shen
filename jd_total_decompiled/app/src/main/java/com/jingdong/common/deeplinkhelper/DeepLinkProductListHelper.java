package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.navutils.NavCenter;
import com.jingdong.common.navutils.NavUri;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.common.search.SearchConstants;
import com.jingdong.common.search.entity.CategoryTabItem;
import com.jingdong.common.search.entity.SearchInfo;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class DeepLinkProductListHelper {
    public static final String EXTRA_NEED_RETURN = "IsNeedReturn";
    public static final String HASHIDDENAUDIOBUTTON = "hiddenAudioButton";
    public static final String HINTWORDSAMEWITHMAIN = "hintword_samewith_main";
    public static final String HOT_WORD = "hotword";
    public static final String SHOP_LIST_NEED_RETURN = "ShopListIsNeedReturn";
    private static final String TAG = "DeepLinkProductListHelper";

    /* loaded from: classes5.dex */
    public static class BundleBuilder {
        Bundle mBundle;

        private BundleBuilder(String str) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putString(ProductListConstant.INLET, str);
        }

        public static BundleBuilder from(String str) {
            return new BundleBuilder(str);
        }

        public BundleBuilder activityId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("activityId", str);
            }
            return this;
        }

        public Bundle build() {
            return this.mBundle;
        }

        public BundleBuilder sourceEntity(SourceEntity sourceEntity) {
            if (sourceEntity != null) {
                this.mBundle.putSerializable("source", sourceEntity);
            }
            return this;
        }

        public BundleBuilder tip(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("tipFirst", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.mBundle.putString("tipSecond", str2);
            }
            return this;
        }
    }

    public static void bebalToSearch(Context context, boolean z, String str, String str2, SearchInfo searchInfo, boolean z2) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putBoolean(HINTWORDSAMEWITHMAIN, true);
        } else {
            if (str == null) {
                str = "";
            }
            bundle.putString("hintword", str);
            if (str2 == null) {
                str2 = "";
            }
            bundle.putString("realword", str2);
            bundle.putBoolean(HINTWORDSAMEWITHMAIN, false);
        }
        if (searchInfo != null) {
            bundle.putSerializable("searchinfo", searchInfo);
        }
        bundle.putString(SearchConstants.PREPATH, SearchConstants.PATH_IS_FROM_CHANNEL);
        bundle.putBoolean(HASHIDDENAUDIOBUTTON, z2);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, bundle);
    }

    public static void categoryToProductListActvity(Context context, Bundle bundle) {
        startProductListActivity(context, bundle, SearchConstants.PATH_IS_FROM_CATEGORY);
    }

    public static void categoryToSearchActvity(Context context, Bundle bundle) {
        startSearchActivity(context, bundle, SearchConstants.PATH_IS_FROM_CATEGORY);
    }

    @Deprecated
    public static void catelogyToSearch(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            bundle.putString(str, str2);
        }
        bundle.putString(SearchConstants.PREPATH, SearchConstants.PATH_IS_FROM_CATEGORY);
        bundle.putBoolean(SearchConstants.PATH_IS_FROM_HOME, true);
        bundle.putBoolean("isNoAnimation", true);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, bundle);
    }

    private static Bundle checkBundle(Bundle bundle) {
        return bundle == null ? new Bundle() : bundle;
    }

    private static boolean checkCategoryParams(Bundle bundle) {
        if (bundle == null || stringIsEmpty(bundle.getString("from"), bundle.getString("keyword"), bundle.getString("title"))) {
            return false;
        }
        String string = bundle.getString("landCid1");
        String string2 = bundle.getString("landCid2");
        String string3 = bundle.getString("landCid3");
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
            return false;
        }
        ArrayList arrayList = bundle.getBoolean("isBrand", false) ? null : (ArrayList) JDJSON.optParseArray(bundle.getString("categoryBarData"), CategoryTabItem.class);
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                CategoryTabItem categoryTabItem = (CategoryTabItem) it.next();
                if (categoryTabItem == null || stringIsEmpty(categoryTabItem.name, categoryTabItem.title, categoryTabItem.keyword)) {
                    return false;
                }
                if (TextUtils.isEmpty(categoryTabItem.landCid1) && TextUtils.isEmpty(categoryTabItem.landCid2) && TextUtils.isEmpty(categoryTabItem.landCid3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void globelToSearch(Context context, String str, String str2, SearchInfo searchInfo) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle bundle = new Bundle();
        if (searchInfo != null) {
            bundle.putSerializable("searchinfo", searchInfo);
        }
        if (str == null) {
            str = "";
        }
        bundle.putString("hintword", str);
        if (str2 == null) {
            str2 = "";
        }
        bundle.putString("realword", str2);
        bundle.putBoolean(HINTWORDSAMEWITHMAIN, false);
        bundle.putString(SearchConstants.PREPATH, SearchConstants.PATH_IS_FROM_CHANNEL);
        bundle.putBoolean(HASHIDDENAUDIOBUTTON, true);
        bundle.putBoolean("global_from_channel", true);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, bundle);
    }

    public static void homeToSearchActivity(Context context) {
        startSearchActivity(context, null, SearchConstants.PATH_IS_FROM_HOME);
    }

    public static void isChannelToSearch(Context context, boolean z) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("global_from_channel", z);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, bundle);
    }

    private static boolean isSearchSwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(12));
    }

    public static void startCategoryProductList(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        Bundle checkBundle = checkBundle(bundle);
        if (checkCategoryParams(checkBundle)) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CATEGORY_PRODUCT_LIST_ACTIVITY, checkBundle);
        }
    }

    public static void startChannelProductListActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close \u2014\u2014 startChannelProductListActivity");
                return;
            }
            return;
        }
        Bundle checkBundle = checkBundle(bundle);
        if (checkBundle.containsKey("requestCode")) {
            int i2 = 404;
            try {
                i2 = checkBundle.getInt("requestCode", 404);
            } catch (Exception unused) {
                if (OKLog.D) {
                    OKLog.e("requestCode", "requestCode\u4e0d\u662f\u6574\u6570");
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is open \u2014\u2014 startChannelProductListActivityForResult");
            }
            if (context instanceof Activity) {
                DeepLinkCommonHelper.startActivityForResult((Activity) context, DeepLinkCommonHelper.HOST_CHANNELPRODUCTLIST_ACTIVITY, checkBundle, i2);
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open \u2014\u2014 startChannelProductListActivity");
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CHANNELPRODUCTLIST_ACTIVITY, checkBundle);
    }

    public static void startChannelSearchActivity(Context context, Bundle bundle) {
        startChannelSearchActivity(context, bundle, null);
    }

    public static void startConvergeListActivityForResult(Activity activity, Bundle bundle, int i2) {
        if (activity == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close \u2014\u2014 startConvergeListActivityForResult");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open \u2014\u2014 startConvergeListActivityForResult");
        }
        DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle, i2);
    }

    public static void startHourSearchActivity(Context context, Bundle bundle) {
        startHourSearchActivity(context, bundle, null);
    }

    public static void startMultiSellerActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MULTISELLER_ACTIVITY, bundle);
    }

    public static void startOneboxListActivity(Context context, Bundle bundle) {
        startOneboxListActivity(context, bundle, null);
    }

    public static void startProductListActivity(Context context, Bundle bundle) {
        startProductListActivity(context, bundle, null);
    }

    public static void startProductListActivityForResult(Activity activity, Bundle bundle, int i2) {
        if (activity == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close \u2014\u2014 startProductListActivityForResult");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open \u2014\u2014 startProductListActivityForResult");
        }
        DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle, i2);
    }

    public static void startProductListConvergeActivity(Context context, Bundle bundle) {
        if (isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is open ");
            }
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, checkBundle(bundle));
        }
    }

    public static void startPurchaseListActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PURCHASE_LIST, bundle);
    }

    public static void startSearchActivity(Context context, Bundle bundle) {
        startSearchActivity(context, bundle, null);
    }

    public static void startSearchActivityForResult(Activity activity, Bundle bundle, int i2) {
        if (activity == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, bundle, i2);
    }

    private static boolean stringIsEmpty(String... strArr) {
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    private static void startChannelSearchActivity(Context context, Bundle bundle, String str) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle checkBundle = checkBundle(bundle);
        if (!TextUtils.isEmpty(str)) {
            checkBundle.putString(SearchConstants.PREPATH, str);
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CHANNELSEARCH_ACTIVITY, checkBundle);
    }

    private static void startHourSearchActivity(Context context, Bundle bundle, String str) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle checkBundle = checkBundle(bundle);
        if (!TextUtils.isEmpty(str)) {
            checkBundle.putString(SearchConstants.PREPATH, str);
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_HOURSEARCH_ACTIVITY, checkBundle);
    }

    private static void startOneboxListActivity(Context context, Bundle bundle, String str) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle checkBundle = checkBundle(bundle);
        if (!TextUtils.isEmpty(str)) {
            checkBundle.putString(SearchConstants.PREPATH, str);
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_ONEBOXLIST_ACTIVITY, checkBundle);
    }

    private static void startProductListActivity(Context context, Bundle bundle, String str) {
        Uri build;
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
            }
            if (bundle == null) {
                NavCenter.from(context).navTo(new NavUri().scheme("http").host("so.m.jd.com").path("/ware/search.action").build());
                return;
            }
            if (!TextUtils.isEmpty(bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD))) {
                build = new NavUri().scheme("http").host("so.m.jd.com").path("/ware/search.action").appendQueryParameter("keyword", bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD)).build();
            } else if (!TextUtils.isEmpty(bundle.getString("cid"))) {
                build = new NavUri().scheme("http").host("so.m.jd.com").path("/products/" + (bundle.getString("levelFirst", "0") + "-" + bundle.getString("levelSecond", "0") + "-" + bundle.getString("cid", "0")) + ".html").build();
            } else {
                build = new NavUri().scheme("http").host("so.m.jd.com").path("/ware/search.action").build();
            }
            NavCenter.from(context).putBundle(bundle).navTo(build);
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle checkBundle = checkBundle(bundle);
        if (!TextUtils.isEmpty(str)) {
            checkBundle.putString(SearchConstants.PREPATH, str);
        }
        if (TextUtils.isEmpty(checkBundle.getString("activityId")) && TextUtils.isEmpty(checkBundle.getString("CouponbatchID")) && TextUtils.isEmpty(checkBundle.getString("eCardID"))) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, checkBundle);
        } else {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, checkBundle);
        }
    }

    private static void startSearchActivity(Context context, Bundle bundle, String str) {
        if (context == null) {
            return;
        }
        if (!isSearchSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-search switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-search switch is open ");
        }
        Bundle checkBundle = checkBundle(bundle);
        if (!TextUtils.isEmpty(str)) {
            checkBundle.putString(SearchConstants.PREPATH, str);
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, checkBundle);
    }
}
