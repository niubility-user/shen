package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.engine.ProductListEngine;
import com.jd.lib.productdetail.core.entitys.PdFeedsSkuIdsEntity;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import jpbury.t;
import org.json.JSONObject;
import rx.Observer;

/* loaded from: classes5.dex */
public class DeeplinkProductDetailHelper implements PDConstant {
    public static final String EVENT_TYPE_GIFT_SHOP_FINISH = "event_type_gift_shop_finish";
    public static final String EXTRA_KEY_SHOWLAYER = "showLayer";
    private static final String HOST_PRODUCTDETAIL = "productdetail";
    private static final String HOST_PRODUCTDETAIL_AUTH_HUAWEI = "productdetail_auth_huawei";
    private static final String HOST_PRODUCTDETAIL_DEGRADE = "productdetail_degrade";
    private static final String HOST_PRODUCTDETAIL_FEEDS = "productdetail_feeds";
    private static final String HOST_PRODUCTDETAIL_FEEDS_BIGIMAGE = "productdetail_feeds_bigimage";
    private static final String HOST_PRODUCTDETAIL_GIFTSHOPPING_GREETING = "giftShoppingGreetingActivity";
    public static final String HOST_PRODUCT_DETAIL_GO_BIG_IMAGE_FROM_ORDER = "host_productdetail_go_big_image_from_fillorder";
    public static final String HOST_PRODUCT_DETAIL_LOOK_SAME = "host_productdetail_look_same";
    public static final String HOST_PRODUCT_DETAIL_NO_STOCK_ORDER = "host_productdetail_no_stock_order";
    public static final String HOST_PRODUCT_DETAIL_REFRESH_FROM_ORDER = "host_productdetail_refresh_from_fillorder";
    public static final String HOST_PRODUCT_DETAIL_SKU_NOTICE = "host_productdetail_sku_notice";
    public static final String LAYER_STYLE = "style";
    private static final String TAG = "DeeplinkProductDetailHe";
    public static boolean isUsePlugin;

    /* loaded from: classes5.dex */
    public static class BundleBuilder {
        Bundle mBundle;

        private BundleBuilder(String str) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putString("id", str);
        }

        public static BundleBuilder from(String str) {
            return new BundleBuilder(str);
        }

        public BundleBuilder addCartTime(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("addCartTime", str);
            }
            return this;
        }

        public Bundle build() {
            return this.mBundle;
        }

        public BundleBuilder categroyId(String str) {
            this.mBundle.putString(PDConstant.EXTRA_CATEGROY_ID, str);
            return this;
        }

        public BundleBuilder clickUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("targetUrl", str);
            }
            return this;
        }

        public BundleBuilder entityId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("entityId", str);
            }
            return this;
        }

        public BundleBuilder extraObject(Bundle bundle) {
            this.mBundle.putBundle(PDConstant.EXTRA_OBJECT, bundle);
            return this;
        }

        public BundleBuilder flt(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(PDConstant.EXTRA_FLT, str);
            }
            return this;
        }

        public BundleBuilder fromCar(boolean z) {
            this.mBundle.putBoolean(PDConstant.EXTRA_FROM_CAR, z);
            return this;
        }

        public BundleBuilder fromIsOpenApp(boolean z) {
            this.mBundle.putBoolean("pd_isOpenApp", z);
            return this;
        }

        public BundleBuilder fromSource(String str) {
            this.mBundle.putString("fromSource", str);
            return this;
        }

        public BundleBuilder imageTitlePrice(String str, String str2, String str3) {
            if (!TextUtils.isEmpty(str2)) {
                this.mBundle.putString("title", str2);
            }
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("image", str);
            }
            if (!TextUtils.isEmpty(str3)) {
                this.mBundle.putString("price", str3);
            }
            return this;
        }

        public BundleBuilder modelId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(PDConstant.EXTRA_MODEL_ID, str);
            }
            return this;
        }

        public BundleBuilder oneboxKeyword(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("oneboxKeyword", str);
            }
            return this;
        }

        public BundleBuilder oneboxSource(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("oneboxSource", str);
            }
            return this;
        }

        public BundleBuilder passThroughMap(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("passThroughMap", str);
            }
            return this;
        }

        public BundleBuilder personas(String str) {
            this.mBundle.putString(PDConstant.EXTRA_PERSONAS, str);
            return this;
        }

        public BundleBuilder recommendExt(String str) {
            Bundle bundle = this.mBundle;
            if (bundle != null) {
                bundle.putString("recommend_ext", str);
            }
            return this;
        }

        public BundleBuilder recommendParam(String str, String str2, String str3, String str4) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("index", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.mBundle.putString(PDConstant.EXTRA_RID, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                this.mBundle.putString(PDConstant.EXTRA_EXPID, str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                this.mBundle.putString(PDConstant.EXTRA_CSKU, str4);
            }
            return this;
        }

        public BundleBuilder searchParam(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString(PDConstant.EXTRA_SEARCH_PARAM, str);
            }
            return this;
        }

        public BundleBuilder searchWareflag(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("searchWareflag", str);
            }
            return this;
        }

        public BundleBuilder secKillParams(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("secKillParams", str);
            }
            return this;
        }

        public BundleBuilder shadowSku(String str) {
            this.mBundle.putString(PDConstant.EXTRA_SHADOWSKU, str);
            return this;
        }

        public BundleBuilder sop() {
            this.mBundle.putBoolean(PDConstant.EXTRA_SOP, true);
            return this;
        }

        public BundleBuilder sourceEntity(SourceEntity sourceEntity) {
            if (sourceEntity != null) {
                this.mBundle.putSerializable("source", sourceEntity);
            }
            return this;
        }

        public BundleBuilder storeId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("storeId", str);
            }
            return this;
        }

        public BundleBuilder targetUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mBundle.putString("targetUrl", str);
            }
            return this;
        }

        public BundleBuilder toTab(String str) {
            this.mBundle.putString(PDConstant.EXTRA_TO_TAB, str);
            return this;
        }

        public BundleBuilder toTabComment() {
            return toTab(PDConstant.TAB_COMMENT);
        }

        public BundleBuilder toTabDetail() {
            return toTab(PDConstant.TAB_DETAIL);
        }

        public BundleBuilder toTabMian() {
            return toTab(PDConstant.TAB_MAIN);
        }

        public static BundleBuilder from(long j2) {
            return new BundleBuilder(j2);
        }

        public BundleBuilder personas() {
            return personas(PDConstant.EXTRA_PERSONAS);
        }

        private BundleBuilder(long j2) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putLong("id", j2);
        }

        public BundleBuilder recommendParam(String str, String str2, String str3) {
            return recommendParam(str, str2, str3, null);
        }
    }

    /* loaded from: classes5.dex */
    public static final class UrlBuilder {
        static final String scheme = "router://JDProductDetailModule/show?";
        StringBuilder sb;

        private UrlBuilder(String str) {
            StringBuilder sb = new StringBuilder(scheme);
            this.sb = sb;
            sb.append("skuId=" + str);
        }

        public static UrlBuilder from(String str) {
            return new UrlBuilder(str);
        }

        public String build() {
            return this.sb.toString();
        }

        public UrlBuilder clickUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("clickUrl=" + str);
            }
            return this;
        }

        public UrlBuilder flt(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("flt=" + str);
            }
            return this;
        }

        public UrlBuilder fromCar() {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("fromCar=true");
            return this;
        }

        public UrlBuilder fromSource(String str) {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("fromSource=" + str);
            return this;
        }

        public UrlBuilder imageTitlePrice(String str, String str2, String str3) {
            if (!TextUtils.isEmpty(str)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("image=" + str);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("title=" + str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("title=" + str3);
            }
            return this;
        }

        public UrlBuilder personas() {
            return personas(PDConstant.EXTRA_PERSONAS);
        }

        public UrlBuilder searchParam(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("searchParam=" + str);
            }
            return this;
        }

        public UrlBuilder sop() {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("sop=true");
            return this;
        }

        public UrlBuilder sourceEntity(SourceEntity sourceEntity) {
            if (sourceEntity != null) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("sourceType=" + sourceEntity.getSourceType());
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("sourceValue=" + sourceEntity.getSourceValue());
            }
            return this;
        }

        public UrlBuilder storeId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.sb.append(ContainerUtils.FIELD_DELIMITER);
                this.sb.append("storeId=" + str);
            }
            return this;
        }

        public UrlBuilder targetUrl(String str) {
            return clickUrl(str);
        }

        public UrlBuilder toTabComment() {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("toTab=tab_comment");
            return this;
        }

        public UrlBuilder toTabDetail() {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("toTab=tab_detail");
            return this;
        }

        public UrlBuilder toTabMian() {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("toTab=tab_main");
            return this;
        }

        public UrlBuilder personas(String str) {
            this.sb.append(ContainerUtils.FIELD_DELIMITER);
            this.sb.append("personas=" + str);
            return this;
        }
    }

    static {
        if (OKLog.D) {
            isUsePlugin = true;
        }
    }

    private static void dealBundleParam4OpenApp(Bundle bundle, Bundle bundle2) {
        Object obj;
        List asList = Arrays.asList("skuId", "id", "source", "clickUrl", JshopConst.JSHOP_M_PARAM, "params", PDConstant.EXTRA_FLT, "title", "price", "image", PDConstant.EXTRA_SOP, PDConstant.EXTRA_FROM_CAR, PDConstant.EXTRA_SEARCH_PARAM);
        Set<String> keySet = bundle.keySet();
        if (keySet == null || keySet.isEmpty()) {
            return;
        }
        for (String str : keySet) {
            if (!asList.contains(str) && (obj = bundle.get(str)) != null) {
                if (obj instanceof String) {
                    bundle2.putString(str, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle2.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    bundle2.putBoolean(str, ((Boolean) obj).booleanValue());
                }
            }
        }
    }

    private static void dealFromSource(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("DeeplinkProductDetailHelper", "dealFromSource from = " + str);
        }
        if (TextUtils.equals("JdScan", str)) {
            PDHelper.onClick("Productdetail_ExternalFlow", null, PDHelper.getPDClassName(), str2);
        }
    }

    private static String getIdFromBundle(Bundle bundle) {
        String valueOf;
        String str = "";
        if (bundle == null) {
            return "";
        }
        Object obj = bundle.get("id");
        if (obj == null) {
            obj = bundle.get("param_skuId");
        }
        if (obj == null) {
            return "";
        }
        try {
            if (obj instanceof String) {
                valueOf = (String) obj;
            } else {
                valueOf = String.valueOf(obj);
            }
            str = valueOf;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
        if (Log.D) {
            Log.d(TAG, " onCreate-->> proId : " + obj);
        }
        return str;
    }

    public static boolean isUsePlugin() {
        return true;
    }

    public static void startHmProductDetail(Context context, Bundle bundle) {
    }

    public static void startHmProductDetail(String str, String str2, Context context, Bundle bundle) {
    }

    public static void startPDGiftShoppingGreetingActivityForResult(Activity activity, String str, String str2, String str3, String str4, int i2, int i3) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PRODUCTDETAIL_GIFTSHOPPING_GREETING);
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        bundle.putString("content", str2);
        bundle.putString(PDConstant.EXTRA_GREETINGS_SENDNAME, str3);
        bundle.putString(PDConstant.EXTRA_GREETINGS_RECENAME, str4);
        bundle.putInt(PDConstant.EXTRA_GREETINGS_SOURCETYPE, i2);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i3);
    }

    public static void startProductAuthHuaWei(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PRODUCTDETAIL_AUTH_HUAWEI).toString(), bundle);
    }

    public static void startProductBigImage(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PRODUCTDETAIL_FEEDS_BIGIMAGE).toString(), bundle);
    }

    public static void startProductDetail(Activity activity, long j2, SourceEntity sourceEntity) {
        startProductDetail(activity, BundleBuilder.from(j2).sourceEntity(sourceEntity).build());
    }

    public static void startProductDetailActivityWithFlag(Context context, Bundle bundle, int i2) {
        if (context == null || bundle == null) {
            return;
        }
        startProductDetailWithFlag(context, bundle, i2);
    }

    public static void startProductDetailFeeds(Context context, Bundle bundle) {
        startProductDetailFeeds(context, bundle, null);
    }

    public static void startProductDetailForResult(Activity activity, long j2, String str, String str2, String str3, String str4, SourceEntity sourceEntity, int i2) {
        startProductDetailForResult(activity, BundleBuilder.from(j2).imageTitlePrice(str2, str, str3).targetUrl(str4).sourceEntity(sourceEntity).build(), i2);
    }

    public static void startProductDetailFromJDRouter(Context context, JDJSONObject jDJSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (jDJSONObject != null) {
            if (OKLog.D) {
                OKLog.d("DeeplinkProductDetailHelper", "json = " + jDJSONObject);
            }
            String optString = jDJSONObject.optString("skuId");
            if (TextUtils.isEmpty(optString)) {
                long optLong = jDJSONObject.optLong("id", 0L);
                if (optLong != 0) {
                    optString = String.valueOf(optLong);
                }
            }
            if (TextUtils.isEmpty(optString)) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            BundleBuilder from = BundleBuilder.from(optString);
            String optString2 = jDJSONObject.optString("sourceType");
            String optString3 = jDJSONObject.optString("sourceValue");
            if (!TextUtils.isEmpty(optString2) || !TextUtils.isEmpty(optString3)) {
                from.sourceEntity(new SourceEntity(optString2, optString3));
            }
            String optString4 = jDJSONObject.optString("clickUrl");
            if (!TextUtils.isEmpty(optString4)) {
                from.targetUrl(optString4);
            }
            String optString5 = jDJSONObject.optString("storeId");
            if (!TextUtils.isEmpty(optString5)) {
                from.storeId(optString5);
            }
            String optString6 = jDJSONObject.optString(PDConstant.EXTRA_FLT);
            if (!TextUtils.isEmpty(optString6)) {
                from.flt(optString6);
            }
            String optString7 = jDJSONObject.optString("title");
            String optString8 = jDJSONObject.optString("price");
            String optString9 = jDJSONObject.optString("image");
            if (!TextUtils.isEmpty(optString7) || !TextUtils.isEmpty(optString8) || !TextUtils.isEmpty(optString9)) {
                from.imageTitlePrice(optString9, optString7, optString8);
            }
            if (bundle != null) {
                from.extraObject(bundle);
            }
            String optString10 = jDJSONObject.optString("secKillParams");
            if (!TextUtils.isEmpty(optString10)) {
                from.secKillParams(optString10);
            }
            String optString11 = jDJSONObject.optString("addCartTime");
            if (!TextUtils.isEmpty(optString11)) {
                from.addCartTime(optString11);
            }
            if (jDJSONObject.optBoolean(PDConstant.EXTRA_SOP)) {
                from.sop();
            }
            if (jDJSONObject.optBoolean(PDConstant.EXTRA_FROM_CAR)) {
                from.fromCar(true);
            }
            String optString12 = jDJSONObject.optString(PDConstant.EXTRA_SEARCH_PARAM);
            if (!TextUtils.isEmpty(optString12)) {
                from.searchParam(optString12);
            }
            String optString13 = jDJSONObject.optString(PDConstant.EXTRA_PERSONAS);
            if (!TextUtils.isEmpty(optString13)) {
                from.personas(optString13);
            }
            String optString14 = jDJSONObject.optString(PDConstant.EXTRA_TO_TAB);
            if (!TextUtils.isEmpty(optString14)) {
                from.toTab(optString14);
            }
            String optString15 = jDJSONObject.optString("fromSource");
            if (!TextUtils.isEmpty(optString15)) {
                from.fromSource(optString15);
                dealFromSource(optString15, optString);
            }
            startProductDetail(context, from.build());
        }
    }

    public static void startProductDetailFromOpenApp(Context context, Bundle bundle) {
        Uri parse;
        if (bundle != null) {
            if (OKLog.D) {
                OKLog.d("DeeplinkProductDetailHelper", "bundle = " + bundle);
            }
            String string = bundle.getString("skuId");
            if (TextUtils.isEmpty(string)) {
                long j2 = bundle.getLong("id", 0L);
                if (j2 != 0) {
                    string = String.valueOf(j2);
                }
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            BundleBuilder from = BundleBuilder.from(string);
            if (bundle.get("source") == null) {
                from.sourceEntity(SourceEntity.getOpenAppSourceEntity(bundle));
            }
            String string2 = bundle.getString("clickUrl");
            if (!TextUtils.isEmpty(string2)) {
                from.targetUrl(string2);
            }
            String string3 = bundle.getString(PDConstant.EXTRA_FLT);
            if (TextUtils.isEmpty(string3)) {
                String string4 = bundle.getString(JshopConst.JSHOP_M_PARAM);
                if (!TextUtils.isEmpty(string4)) {
                    try {
                        String optString = new JSONObjectProxy(new JSONObject(string4)).optString("ref");
                        if (!TextUtils.isEmpty(optString) && (parse = Uri.parse(optString)) != null) {
                            string3 = parse.getQueryParameter(PDConstant.EXTRA_FLT);
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(string3)) {
                from.flt(string3);
            }
            String string5 = bundle.getString("title");
            String string6 = bundle.getString("price");
            String string7 = bundle.getString("image");
            if (!TextUtils.isEmpty(string5) || !TextUtils.isEmpty(string6) || !TextUtils.isEmpty(string7)) {
                from.imageTitlePrice(string7, string5, string6);
            }
            try {
                if (Boolean.parseBoolean(bundle.getString(PDConstant.EXTRA_SOP))) {
                    from.sop();
                }
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                }
            }
            try {
                if (Boolean.parseBoolean(bundle.getString(PDConstant.EXTRA_FROM_CAR))) {
                    from.fromCar(true);
                }
            } catch (Exception e4) {
                if (OKLog.E) {
                    OKLog.e(TAG, e4);
                }
            }
            String string8 = bundle.getString(PDConstant.EXTRA_SEARCH_PARAM);
            if (!TextUtils.isEmpty(string8)) {
                from.searchParam(string8);
            }
            String string9 = bundle.getString(PDConstant.EXTRA_PERSONAS);
            if (!TextUtils.isEmpty(string9)) {
                from.personas(string9);
            }
            String string10 = bundle.getString(PDConstant.EXTRA_TO_TAB);
            if (!TextUtils.isEmpty(string10)) {
                from.toTab(string10);
            }
            String string11 = bundle.getString("fromSource");
            if (!TextUtils.isEmpty(string11)) {
                from.fromSource(string11);
                dealFromSource(string11, string);
            }
            from.fromIsOpenApp(true);
            Bundle build = from.build();
            dealBundleParam4OpenApp(bundle, build);
            startProductDetail(context, build);
        }
    }

    public static void startProductDetailWithFlag(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(isUsePlugin() ? "productdetail" : HOST_PRODUCTDETAIL_DEGRADE).toString(), bundle, i2);
    }

    public static void startProductDetailWithTargetUrl(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, SourceEntity sourceEntity) {
        if (activity == null || TextUtils.isEmpty(str)) {
            return;
        }
        startProductDetail(activity, BundleBuilder.from(str).imageTitlePrice(str3, str2, str4).targetUrl(str5).searchParam(str6).sourceEntity(sourceEntity).build());
    }

    public static void startProductDetailFeeds(final Context context, final Bundle bundle, final Bundle bundle2) {
        final String[] strArr = {"productdetail"};
        if (isUsePlugin()) {
            strArr[0] = "productdetail";
        } else {
            strArr[0] = HOST_PRODUCTDETAIL_DEGRADE;
        }
        String idFromBundle = getIdFromBundle(bundle);
        if (!TextUtils.isEmpty(idFromBundle) && (context instanceof BaseActivity)) {
            ProductListEngine productListEngine = new ProductListEngine((BaseActivity) context, new HttpGroupUtil());
            productListEngine.mSourceType = bundle.getString(CartConstant.KEY_SOURCE_TYPE);
            productListEngine.getFeedsSkuIds(idFromBundle).subscribe(new Observer<PdFeedsSkuIdsEntity>() { // from class: com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper.1
                @Override // rx.Observer
                public void onCompleted() {
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    try {
                        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(strArr[0]).toString(), bundle);
                    } catch (Exception e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }

                @Override // rx.Observer
                public void onNext(PdFeedsSkuIdsEntity pdFeedsSkuIdsEntity) {
                    Bundle bundle3 = null;
                    if (pdFeedsSkuIdsEntity != null) {
                        ArrayList<PdFeedsSkuIdsEntity.PdSkuEntity> arrayList = pdFeedsSkuIdsEntity.data;
                        if (arrayList != null && arrayList.size() > 2) {
                            strArr[0] = DeeplinkProductDetailHelper.HOST_PRODUCTDETAIL_FEEDS;
                            bundle.putParcelableArrayList("feeds.skuIds", arrayList);
                            Bundle bundle4 = bundle;
                            PdFeedsSkuIdsEntity.Others others = pdFeedsSkuIdsEntity.others;
                            bundle4.putString("feeds.touchstone_expids", others != null ? others.touchstone_expids : null);
                        } else {
                            Bundle bundle5 = bundle;
                            PdFeedsSkuIdsEntity.Others others2 = pdFeedsSkuIdsEntity.others;
                            bundle5.putString("productdetail.feed.touchstone_expids", others2 != null ? others2.touchstone_expids : null);
                        }
                    }
                    try {
                        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(strArr[0]);
                        Context context2 = context;
                        String builder = host.toString();
                        Bundle bundle6 = bundle;
                        if (!TextUtils.equals(strArr[0], DeeplinkProductDetailHelper.HOST_PRODUCTDETAIL_FEEDS)) {
                            bundle3 = bundle2;
                        }
                        DeepLinkDispatch.startActivityDirect(context2, builder, bundle6, bundle3);
                    } catch (Exception e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }
            });
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(strArr[0]).toString(), bundle, bundle2);
    }

    public static void startProductDetail(Activity activity, String str, SourceEntity sourceEntity) {
        startProductDetail(activity, BundleBuilder.from(str).sourceEntity(sourceEntity).build());
    }

    public static void startProductDetailForResult(Activity activity, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(isUsePlugin() ? "productdetail" : HOST_PRODUCTDETAIL_DEGRADE).toString(), bundle, i2);
    }

    public static void startProductDetail(Activity activity, Bundle bundle, int i2) {
        if (activity == null || bundle == null) {
            return;
        }
        startProductDetailForResult(activity, bundle, i2);
    }

    public static void startProductDetail(Activity activity, Long l2, String str, String str2, String str3, SourceEntity sourceEntity) {
        startProductDetail(activity, String.valueOf(l2), str, str2, str3, sourceEntity);
    }

    public static void startProductDetailWithTargetUrl(Activity activity, Long l2, String str, String str2, String str3, String str4, SourceEntity sourceEntity) {
        startProductDetailWithTargetUrl(activity, String.valueOf(l2), str, str2, str3, str4, sourceEntity);
    }

    public static void startProductDetail(Activity activity, String str, String str2, String str3, String str4, SourceEntity sourceEntity) {
        startProductDetailWithTargetUrl(activity, str, str2, str3, str4, null, null, sourceEntity);
    }

    public static void startProductDetailWithTargetUrl(Activity activity, String str, String str2, String str3, String str4, String str5, SourceEntity sourceEntity) {
        startProductDetailWithTargetUrl(activity, str, str2, str3, str4, str5, null, sourceEntity);
    }

    public static void startProductDetail(Context context, Long l2, String str, SourceEntity sourceEntity) {
        if (context == null || l2 == null) {
            return;
        }
        startProductDetail(context, BundleBuilder.from(l2.longValue()).imageTitlePrice(null, str, null).sourceEntity(sourceEntity).build());
    }

    public static void startProductDetail(Context context, long j2, String str, String str2, String str3, SourceEntity sourceEntity) {
        startProductDetail(context, j2, str, str2, str3, null, sourceEntity);
    }

    public static void startProductDetail(Context context, long j2, String str, String str2, String str3, String str4, SourceEntity sourceEntity) {
        startProductDetail(context, j2, str, str2, str3, str4, false, sourceEntity);
    }

    public static void startProductDetail(Context context, long j2, String str, String str2, String str3, String str4, boolean z, SourceEntity sourceEntity) {
        startProductDetail(context, BundleBuilder.from(j2).imageTitlePrice(str2, str, str3).targetUrl(str4).sourceEntity(sourceEntity).fromCar(z).build());
    }

    public static void startProductDetail(Context context, long j2, String str, String str2, String str3, String str4, String str5, SourceEntity sourceEntity) {
        startProductDetail(context, BundleBuilder.from(j2).imageTitlePrice(str2, str, str3).targetUrl(str4).searchParam(str5).sourceEntity(sourceEntity).build());
    }

    public static void startProductDetail(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(isUsePlugin() ? "productdetail" : HOST_PRODUCTDETAIL_DEGRADE).toString(), bundle);
    }

    public static void startProductDetail(Context context, Bundle bundle, Bundle bundle2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(isUsePlugin() ? "productdetail" : HOST_PRODUCTDETAIL_DEGRADE).toString(), bundle, bundle2);
    }
}
