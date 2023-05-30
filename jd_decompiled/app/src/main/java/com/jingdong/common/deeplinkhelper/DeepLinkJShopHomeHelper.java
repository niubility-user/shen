package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.shop.JShopHomeIntentBean;
import com.jingdong.common.shop.JshopFloorDataPreloadUtil;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeepLinkJShopHomeHelper {
    private static final int ACTION_TO_SHOP_HOME_THRESHOLD = 700;
    public static final String JSHOP_FROM_FANS_PRODUCT_DETAIL = "fansPriceFloor";
    public static final String JSHOP_FROM_KA_PRODUCT_DETAIL = "app-productDetail";
    public static final String JSHOP_HOME_BIG_PRO = "bigPro";
    public static final String JSHOP_HOME_TAB_ACTIVITY = "activity";
    public static final String JSHOP_HOME_TAB_ALL_PRODUCT = "allProduct";
    public static final String JSHOP_HOME_TAB_DYNAMIC = "dynamic";
    public static final String JSHOP_HOME_TAB_FLASH_SALE = "flashPurchase";
    public static final String JSHOP_HOME_TAB_HOME = "home";
    public static final String JSHOP_HOME_TAB_NEW = "new";
    public static final String JSHOP_HOME_TAB_PROMOTION = "promotion";
    private static final String JSHOP_MAIN_PAGE_KEY = "com.jd.lib.jshop.jshop.JshopMainShopActivity";
    public static final String TAG = "DeepLinkJShopHomeHelper";
    private static long actionToShopTime = -1;

    /* loaded from: classes5.dex */
    public static class AllProductTabInfo {
        private JShopBundleBuilder jShopBundleBuilder;
        public String keyword;
        public String searchType;

        public AllProductTabInfo(JShopBundleBuilder jShopBundleBuilder) {
            this.jShopBundleBuilder = jShopBundleBuilder;
        }

        public AllProductTabInfo addKeyword(String str) {
            this.keyword = str;
            return this;
        }

        public AllProductTabInfo addSearchType(String str) {
            this.searchType = str;
            return this;
        }

        public JShopBundleBuilder endAllProductInfo() {
            this.jShopBundleBuilder.endAllProductInfo(this);
            return this.jShopBundleBuilder;
        }
    }

    /* loaded from: classes5.dex */
    public static class JShopBundleBuilder {
        private Bundle mBundle;

        public JShopBundleBuilder(String str, String str2, String str3) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putString("shopId", str);
            this.mBundle.putString("venderId", str2);
            this.mBundle.putString("shopName", str3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void endAllProductInfo(AllProductTabInfo allProductTabInfo) {
            if (allProductTabInfo != null) {
                Bundle bundle = new Bundle();
                if (!TextUtils.isEmpty(allProductTabInfo.keyword)) {
                    bundle.putString("keyword", allProductTabInfo.keyword);
                }
                if (!TextUtils.isEmpty(allProductTabInfo.searchType)) {
                    bundle.putString("searchType", allProductTabInfo.searchType);
                }
                this.mBundle.putBundle("allProductTabInfo", bundle);
            }
        }

        public static JShopBundleBuilder from(String str, String str2, String str3) {
            return new JShopBundleBuilder(str, str2, str3);
        }

        public JShopBundleBuilder addADUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("ad.url", str);
            }
            return this;
        }

        public JShopBundleBuilder addBigProExt(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("bigProExt", str);
            }
            return this;
        }

        public JShopBundleBuilder addCateInfo(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("id", str);
                if (!TextUtils.isEmpty(str2)) {
                    this.mBundle.putString("title", str2);
                }
            }
            return this;
        }

        public JShopBundleBuilder addClickType(int i2) {
            this.mBundle.putInt("clickType", i2);
            return this;
        }

        public JShopBundleBuilder addComplexSource(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("complexSource", str);
            }
            return this;
        }

        public JShopBundleBuilder addDesignerId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_DESIGNER_ID, str);
            }
            return this;
        }

        public JShopBundleBuilder addHomeFloorExt(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("homeFloorExt", str);
            }
            return this;
        }

        public JShopBundleBuilder addIsFromCollectCard(boolean z) {
            this.mBundle.putBoolean("isFromCollectCard", z);
            return this;
        }

        public JShopBundleBuilder addJumpTab(String str) {
            this.mBundle.putString("jumpTab", str);
            return this;
        }

        public JShopBundleBuilder addKeyWord(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_SEARCH_KEYWORD, str);
            }
            return this;
        }

        public JShopBundleBuilder addLogoUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("logoUrl", str);
            }
            return this;
        }

        public JShopBundleBuilder addMParam(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_M_PARAM, str);
            }
            return this;
        }

        public JShopBundleBuilder addMSourceFrom(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_M_SOURCE_FROM, str);
            }
            return this;
        }

        public JShopBundleBuilder addMessageId(String str) {
            this.mBundle.putString("messageId", str);
            return this;
        }

        public JShopBundleBuilder addMsg(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("msg", str);
            }
            return this;
        }

        public JShopBundleBuilder addMsgCode(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_TIP_MESSAGE_CODE, str);
            }
            return this;
        }

        public JShopBundleBuilder addNotificationType(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("notificationType", str);
            }
            return this;
        }

        public JShopBundleBuilder addOperation(String str) {
            this.mBundle.putString("operation", str);
            return this;
        }

        public JShopBundleBuilder addPDShopAction(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_PD_SHOP_ACTION, str);
            }
            return this;
        }

        public JShopBundleBuilder addPageId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("page_id", str);
            }
            return this;
        }

        public JShopBundleBuilder addProjectId(String str) {
            this.mBundle.putString("projectId", str);
            return this;
        }

        public JShopBundleBuilder addQueryWord(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("queryWord", str);
            }
            return this;
        }

        public JShopBundleBuilder addRecommendCateInfo(String str, String str2, String str3, String str4) {
            Bundle bundle = new Bundle();
            bundle.putString("categoryId", str);
            bundle.putString("categoryName", str2);
            bundle.putString("sku", str3);
            bundle.putString(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, str4);
            this.mBundle.putBundle("categoryInfo", bundle);
            return this;
        }

        public JShopBundleBuilder addSearchType(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("searchType", str);
            }
            return this;
        }

        public JShopBundleBuilder addSearchWareExt(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("searchWareExt", str);
            }
            return this;
        }

        public JShopBundleBuilder addSendTime(long j2) {
            this.mBundle.putLong(RemoteMessageConst.SEND_TIME, j2);
            return this;
        }

        public JShopBundleBuilder addShareActivityId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("shareActivityId", str);
            }
            return this;
        }

        public JShopBundleBuilder addShareRuleType(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("shareRuleType", str);
            }
            return this;
        }

        public JShopBundleBuilder addShareToken(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("shareToken", str);
            }
            return this;
        }

        public JShopBundleBuilder addShowingUrl(String str) {
            this.mBundle.putString("showingUrl", str);
            return this;
        }

        public JShopBundleBuilder addSignBoardUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.JSHOP_YU_SIGN_BOARD_URL, str);
            }
            return this;
        }

        public JShopBundleBuilder addSkuId(String str) {
            return addSkuList(str, new ArrayList());
        }

        public JShopBundleBuilder addSkuList(String str, List<String> list) {
            String str2 = "";
            for (String str3 : list) {
                if (!TextUtils.isEmpty(str3)) {
                    str2 = str2 + DYConstants.DY_REGEX_COMMA + str3;
                }
            }
            this.mBundle.putString("clickSku", str);
            this.mBundle.putString("skus", str2);
            return this;
        }

        public JShopBundleBuilder addSkus(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("skus", str);
            }
            return this;
        }

        public JShopBundleBuilder addSource(SourceEntity sourceEntity) {
            if (sourceEntity != null) {
                this.mBundle.putSerializable("source", sourceEntity);
            }
            return this;
        }

        public JShopBundleBuilder addTargetPage(String str) {
            this.mBundle.putString(JshopConst.JSHOP_TARGET_PAGE, str);
            return this;
        }

        public JShopBundleBuilder addTemplateId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("templateId", str);
            }
            return this;
        }

        public JShopBundleBuilder addTestId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(JshopConst.KEY_TEST_ID, str);
            }
            return this;
        }

        public Bundle build() {
            return this.mBundle;
        }

        public AllProductTabInfo startAllProductTabInfo() {
            return new AllProductTabInfo(this);
        }
    }

    public static String bundleFromat(Bundle bundle, int i2) {
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            int i3 = 0;
            if (obj instanceof Bundle) {
                for (int i4 = 0; i4 < i2; i4++) {
                    sb.append(' ');
                }
                sb.append(str + " = [\n");
                for (int i5 = 0; i5 < i2; i5++) {
                    sb.append(' ');
                }
                sb.append(bundleFromat(bundle.getBundle(str), i2 + 2));
                while (i3 < i2) {
                    sb.append(' ');
                    i3++;
                }
                sb.append("\n]\n");
            } else {
                while (i3 < i2) {
                    sb.append(' ');
                    i3++;
                }
                sb.append(str + " = " + obj);
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return sb.toString();
    }

    private static Bundle getBundle(Context context, SourceEntity sourceEntity, JShopHomeIntentBean jShopHomeIntentBean) {
        JSONObject json = getJSON(jShopHomeIntentBean);
        Bundle bundle = new Bundle();
        bundle.putSerializable("source", sourceEntity);
        bundle.putString(JshopConst.INTENT_BRAND_JSON, json.toString());
        return bundle;
    }

    private static JSONObject getJSON(JShopHomeIntentBean jShopHomeIntentBean) {
        JSONObject jSONObject = new JSONObject();
        if (jShopHomeIntentBean != null) {
            try {
                return new JSONObject(JDJSON.toJSONString(jShopHomeIntentBean));
            } catch (Exception unused) {
                return jSONObject;
            }
        }
        return jSONObject;
    }

    public static String getJshopMainName() {
        return JSHOP_MAIN_PAGE_KEY;
    }

    private static boolean getSwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(16));
    }

    public static void gotoJShopActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, "jshopActivity", bundle);
    }

    public static void gotoJShopCouponList(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_COUPONS_LIST_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopDynamic(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_DYNAMIC_ACTIVITY, bundle);
        }
    }

    @Deprecated
    public static void gotoJShopHome(Context context, String str, String str2, String str3, String str4, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = str4;
        gotoJShopHome(context, getBundle(context, sourceEntity, jShopHomeIntentBean));
    }

    public static void gotoJShopHomeForResult(Activity activity, Bundle bundle, int i2) {
        if (getSwitchOpen()) {
            if (actionToShopTime == -1 || System.currentTimeMillis() <= actionToShopTime || System.currentTimeMillis() - actionToShopTime >= 700) {
                actionToShopTime = System.currentTimeMillis();
                gotoJShopHomeForResultInternal(activity, bundle, i2);
                return;
            }
            return;
        }
        OKLog.d(TAG, "the aura switch is off !!!");
    }

    public static void gotoJShopHomeForResultInternal(Activity activity, Bundle bundle, int i2) {
        if (getSwitchOpen()) {
            JshopFloorDataPreloadUtil.handlePreload(activity, bundle);
            DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_MAINSHOP, bundle, i2);
            return;
        }
        OKLog.d(TAG, "the aura switch is off !!!");
    }

    @Deprecated
    public static void gotoJShopHomeWithRecommendProduct(Context context, String str, String str2, String str3, String str4, String str5, String str6, SourceEntity sourceEntity) {
        gotoJShopHomeWithRecommendProduct(context, str, str2, str3, str4, str5, str6, "", sourceEntity);
    }

    public static void gotoJShopHomeWithSortSkus(Context context, String str, String str2, String str3, String str4, List<String> list, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = "allProduct";
        JShopHomeIntentBean.AllProductSort allProductSort = new JShopHomeIntentBean.AllProductSort();
        allProductSort.sku = str4;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        if (arrayList.contains(str4)) {
            arrayList.remove(str4);
        }
        allProductSort.skuArray = arrayList;
        jShopHomeIntentBean.setAllProductSort(allProductSort);
        gotoJShopHome(context, getBundle(context, sourceEntity, jShopHomeIntentBean));
    }

    public static void gotoJShopMember(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_MEMBER_ACTIVITY_ORIGIN, bundle);
    }

    public static void gotoJShopNew(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_NEW_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopNoPayPin(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_NOPAYPIN_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopNoPayPinForResult(Activity activity, Bundle bundle, int i2) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_JSHOP_NOPAYPIN_ACTIVITY, bundle, i2);
        }
    }

    public static void gotoJShopProductList(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_PRODUCTLIST_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopPromotion(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_PROMOTION_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopRecommendProductList(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_RECOMMEND_PRODUCT_LIST, bundle);
    }

    public static void gotoJShopSignUp(Context context, String str, String str2, String str3, int i2, String str4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("shopName", str3);
        bundle.putString("shopId", str + "");
        bundle.putString("venderId", str2 + "");
        bundle.putString("cateJSON", str4);
        bundle.putInt(JshopConst.JSKEY_SHOP_SIGNTYPE, i2);
        bundle.putBoolean(JshopConst.FOLLOWED_KEY, z);
        gotoJShopSignUp(context, bundle);
    }

    public static void gotoJShopTakeCoupon(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_TAKE_COUPON_ACTIVITY, bundle);
        }
    }

    public static void gotoJShopTakeCouponForResult(Activity activity, Bundle bundle, int i2) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_JSHOP_TAKE_COUPON_ACTIVITY, bundle, i2);
        }
    }

    public static void gotoJShopTopicWare(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_TOPICWARE_ACTIVITY, bundle);
        }
    }

    public static void gotoJshopHomeInternal(Context context, Bundle bundle, boolean z) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MAINSHOP, bundle);
    }

    public static void gotoJshopSearchPage(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_SEARCH_ACTIVITY, bundle);
        }
    }

    public static void gotoJshopSearchShopList(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SEARCHSHOP_LIST_ORIGIN, bundle);
    }

    public static void gotoJshopSearchWordActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_SEARCH_WORD_ACTIVITY, bundle);
    }

    public static void gotoJShopHomeWithRecommendProduct(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = "home";
        JShopHomeIntentBean.FloatProduct floatProduct = new JShopHomeIntentBean.FloatProduct();
        floatProduct.cateId = str5;
        floatProduct.cateName = str6;
        floatProduct.sku = str4;
        floatProduct.abTest = str7;
        jShopHomeIntentBean.setFloatProduct(floatProduct);
        gotoJShopHome(context, getBundle(context, sourceEntity, jShopHomeIntentBean));
    }

    public static void gotoJShopMember(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("shopId", str);
        bundle.putString("venderId", str2);
        gotoJShopMember(context, bundle);
    }

    public static void gotoJshopSearchPage(Activity activity, Bundle bundle, int i2) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityForResult(activity, DeepLinkCommonHelper.HOST_JSHOP_SEARCH_ACTIVITY, bundle, i2);
        }
    }

    public static void gotoJShopHome(Context context, Bundle bundle) {
        if (actionToShopTime == -1 || System.currentTimeMillis() <= actionToShopTime || System.currentTimeMillis() - actionToShopTime >= 700) {
            actionToShopTime = System.currentTimeMillis();
            long elapsedRealtime = OKLog.D ? SystemClock.elapsedRealtime() : 0L;
            JshopFloorDataPreloadUtil.handlePreload(context, bundle);
            if (OKLog.D) {
                Log.i("shop-preload-duration", "duration=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
            }
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MAINSHOP, bundle);
            if (OKLog.D) {
                Log.i(TAG, "enter shop param : \n" + bundleFromat(bundle, 0));
            }
        }
    }

    public static void gotoJShopSignUp(Context context, Bundle bundle) {
        if (getSwitchOpen()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JSHOP_SIGNUP_ACTIVITY, bundle);
        }
    }
}
