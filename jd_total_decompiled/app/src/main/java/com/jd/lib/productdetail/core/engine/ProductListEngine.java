package com.jd.lib.productdetail.core.engine;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.base.ProductDetailBaseEngine;
import com.jd.lib.productdetail.core.entitys.PdFeedsSkuIdsEntity;
import com.jd.lib.productdetail.core.entitys.PreferentialGuideEntity;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.pgcarticle.PdPgcArticleEntity;
import com.jd.lib.productdetail.core.entitys.promotion.PdSuitEntry;
import com.jd.lib.productdetail.core.entitys.warebusiness.BannerRight;
import com.jd.lib.productdetail.core.entitys.warebusiness.CommonBannerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.FeedEvaluate;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessAddrEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessDiscountPriceEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJingPriceEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessNameEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessProductRefresh;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePromotionInfo;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/* loaded from: classes15.dex */
public class ProductListEngine extends ProductDetailBaseEngine {
    public static final String FETCH_TYPE = "firstEntryFeed";
    public static final int FETCH_TYPE_CHECK = 1;
    public static final int FETCH_TYPE_PAGE = 0;
    public static final int FETCH_TYPE_SPECIFIED = -1;
    private static final String TAG = "ProductListEngine";
    public int darkModelEnum;
    private JDLocation location;
    private WeakReference<IMyActivity> mContext;
    private HttpResponse mHttpResponse;
    public String mSourceType;

    /* loaded from: classes.dex */
    public @interface FetchType {
    }

    public ProductListEngine(IMyActivity iMyActivity, HttpGroupUtil httpGroupUtil) {
        super(httpGroupUtil);
        this.darkModelEnum = -1;
        this.mSourceType = "";
        this.mContext = new WeakReference<>(iMyActivity);
        this.location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption(false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PdFeedsSkuIdsEntity a(HttpResponse httpResponse) {
        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
        if (fastJsonObject != null) {
            int optInt = fastJsonObject.optInt("code", -1);
            int optInt2 = fastJsonObject.optInt("feedCode", -1);
            if (optInt == 0 && ((optInt2 == 0 && !fastJsonObject.isNull("data")) || optInt2 == 112)) {
                return (PdFeedsSkuIdsEntity) JDJSON.parseObject(fastJsonObject.toJSONString(), PdFeedsSkuIdsEntity.class);
            }
        }
        return null;
    }

    private void add(HttpSetting httpSetting) {
        if (getContext() != null) {
            this.httpGroupUtil.getHttpGroupWithNPSGroup(getContext(), null).add(httpSetting);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ List c(HttpResponse httpResponse) {
        ProductDetailEntity parseProduct;
        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
        ArrayList arrayList = new ArrayList();
        if (fastJsonObject != null) {
            if (fastJsonObject.optInt("code", -1) == 0 && !fastJsonObject.isNull("data")) {
                JDJSONArray jSONArray = fastJsonObject.getJSONArray("data");
                if (jSONArray != null && jSONArray.size() > 0) {
                    for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                        JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                        if (jSONObject != null && (parseProduct = parseProduct(jSONObject)) != null) {
                            parseProduct.skuId = jSONObject.getString("sku");
                            arrayList.add(parseProduct);
                        }
                    }
                }
            } else {
                throw new RuntimeException();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e() {
        if (getContext() != null) {
            getContext().finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g(StringBuilder sb, int i2, int i3, final Subscriber subscriber) {
        Log.d(TAG, "ObservableOnSubscribe");
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setModeId(JDGetWayQueueTools.QueueMode.MODE_PRODUCT_DETAIL);
        httpSetting.setPageId(PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"));
        if (!TextUtils.isEmpty(this.mSourceType)) {
            httpSetting.putJsonParam(CartConstant.KEY_SOURCE_TYPE, this.mSourceType);
        }
        httpSetting.putJsonParam("skuId", sb.toString());
        httpSetting.putJsonParam("currentpage", Integer.valueOf(i2));
        httpSetting.putJsonParam("abTest800", DYConstants.DY_TRUE);
        httpSetting.putJsonParam(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        httpSetting.putJsonParam(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null) {
            httpSetting.putJsonParam("uAddrId", String.valueOf(addressGlobal.getId()));
        }
        if (this.location == null) {
            this.location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption(false));
        }
        JDLocation jDLocation = this.location;
        if (jDLocation != null) {
            httpSetting.putJsonParam(PdLVBody.LATITUDE, String.valueOf(jDLocation.getLat()));
            httpSetting.putJsonParam(PdLVBody.LONGITUDE, String.valueOf(this.location.getLng()));
        }
        httpSetting.putJsonParam(FETCH_TYPE, Integer.valueOf(i3));
        int i4 = this.darkModelEnum;
        if (i4 == 0) {
            httpSetting.putJsonParam("darkModelEnum", 1);
        } else if (i4 == 1) {
            httpSetting.putJsonParam("darkModelEnum", 2);
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
            httpSetting.putJsonParam("darkModelEnum", 3);
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            httpSetting.putJsonParam("darkModelEnum", 4);
        }
        httpSetting.setOnQueueCancelListener(new JDGetWayQueueTools.OnQueueCancelListener() { // from class: com.jd.lib.productdetail.core.engine.a
            @Override // com.jingdong.common.utils.JDGetWayQueueTools.OnQueueCancelListener
            public final void onCancel() {
                ProductListEngine.this.e();
            }
        });
        httpSetting.setFunctionId("feedAction");
        httpSetting.setHost(Configuration.getWareHost());
        httpSetting.setEncryptBody(false);
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "bodyEncrypt", "pdBodyEncrypt"), DYConstants.DY_TRUE)) {
            httpSetting.setEncryptBody(true);
        }
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jd.lib.productdetail.core.engine.ProductListEngine.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d(ProductListEngine.TAG, "onEnd response = ");
                ProductListEngine.this.mHttpResponse = httpResponse;
                subscriber.onNext(httpResponse);
                subscriber.onCompleted();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(ProductListEngine.TAG, "onError");
                subscriber.onError(new Throwable());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                Log.d(ProductListEngine.TAG, "onReady");
            }
        });
        add(httpSetting);
    }

    private IMyActivity getContext() {
        return this.mContext.get();
    }

    private Observable<HttpResponse> getProductListInternal(String str, @FetchType int i2) {
        return getProductListInternal(str, i2, 0);
    }

    private ProductDetailEntity parseProduct(JDJSONObject jDJSONObject) {
        PreferentialGuideEntity preferentialGuideEntity;
        ProductDetailEntity productDetailEntity;
        JDJSONObject optJSONObject;
        BannerRight bannerRight;
        Log.d(TAG, "parseProduct begin");
        boolean z = false;
        if (jDJSONObject != null) {
            productDetailEntity = new ProductDetailEntity();
            if (jDJSONObject.isNull("data")) {
                preferentialGuideEntity = null;
            } else {
                JDJSONObject jSONObject = jDJSONObject.getJSONObject("data");
                if (jSONObject != null && !jSONObject.isNull("WareBasicInfo")) {
                    productDetailEntity.mWareBusinessData = (WareBusinessData) JDJSON.parseObject(jSONObject.getJSONObject("WareBasicInfo").toJSONString(), WareBusinessData.class);
                    z = true;
                } else {
                    Log.d(TAG, "parseProduct WareBasicInfo is null");
                }
                if (z && !jSONObject.isNull("commonBannerInfo")) {
                    productDetailEntity.mWareBusinessData.commonBannerInfo = (CommonBannerInfo) JDJSON.parseObject(jSONObject.getJSONObject("commonBannerInfo").toJSONString(), CommonBannerInfo.class);
                    WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
                    CommonBannerInfo commonBannerInfo = wareBusinessData.commonBannerInfo;
                    if (commonBannerInfo != null && (bannerRight = commonBannerInfo.bannerRight) != null && bannerRight.bannerActRemainTime > 0) {
                        wareBusinessData.mRefreshData = new WareBusinessProductRefresh();
                        WareBusinessData wareBusinessData2 = productDetailEntity.mWareBusinessData;
                        WareBusinessProductRefresh wareBusinessProductRefresh = wareBusinessData2.mRefreshData;
                        wareBusinessProductRefresh.mNeedRefresh = true;
                        wareBusinessProductRefresh.mValidDuraion = wareBusinessData2.commonBannerInfo.bannerRight.bannerActRemainTime * 1000;
                        wareBusinessProductRefresh.mValidTimeStamp = System.currentTimeMillis() + productDetailEntity.mWareBusinessData.mRefreshData.mValidDuraion;
                    }
                }
                if (z && !jSONObject.isNull("GodTitle")) {
                    productDetailEntity.mWareBusinessData.GodTitle = (WareBusinessNameEntity) JDJSON.parseObject(jSONObject.getJSONObject("GodTitle").toJSONString(), WareBusinessNameEntity.class);
                }
                if (z && !jSONObject.isNull("StockInfo")) {
                    productDetailEntity.mWareBusinessData.StockInfo = (WareBusinessAddrEntity) JDJSON.parseObject(jSONObject.getJSONObject("StockInfo").toJSONString(), WareBusinessAddrEntity.class);
                }
                if (z && !jSONObject.isNull("ProductImage")) {
                    productDetailEntity.mWareBusinessData.ProductImage = (WareBusinessTopImageEntity) JDJSON.parseObject(jSONObject.getJSONObject("ProductImage").toJSONString(), WareBusinessTopImageEntity.class);
                }
                if (z && !jSONObject.isNull("FeedEvaluate")) {
                    productDetailEntity.mWareBusinessData.FeedEvaluate = (FeedEvaluate) JDJSON.parseObject(jSONObject.getJSONObject("FeedEvaluate").toJSONString(), FeedEvaluate.class);
                }
                preferentialGuideEntity = (!z || jSONObject.isNull("PreferentialGuide")) ? null : (PreferentialGuideEntity) JDJSON.parseObject(jSONObject.getJSONObject("PreferentialGuide").toJSONString(), PreferentialGuideEntity.class);
                if (z && !jSONObject.isNull("DiscountPrice")) {
                    productDetailEntity.mWareBusinessData.DiscountPrice = (WareBusinessDiscountPriceEntity) JDJSON.parseObject(jSONObject.getJSONObject("DiscountPrice").toJSONString(), WareBusinessDiscountPriceEntity.class);
                }
                if (z && !jSONObject.isNull("WarePrice")) {
                    productDetailEntity.mWareBusinessData.WarePrice = (WareBusinessJingPriceEntity) JDJSON.parseObject(jSONObject.getJSONObject("WarePrice").toJSONString(), WareBusinessJingPriceEntity.class);
                }
                if (z && !jSONObject.isNull("PGC")) {
                    productDetailEntity.mWareBusinessData.PGC = (PdPgcArticleEntity) JDJSON.parseObject(jSONObject.getJSONObject("PGC").toJSONString(), PdPgcArticleEntity.class);
                }
                if (z && !jSONObject.isNull("Comment") && (optJSONObject = jSONObject.optJSONObject("Comment")) != null && !optJSONObject.isNull("commentCount")) {
                    productDetailEntity.mWareBusinessData.mCommentCount = optJSONObject.optString("commentCount");
                }
            }
        } else {
            preferentialGuideEntity = null;
            productDetailEntity = null;
            z = true;
        }
        if (z) {
            productDetailEntity.dealWareBusinessData(productDetailEntity.mWareBusinessData);
            WareBusinessData wareBusinessData3 = productDetailEntity.mWareBusinessData;
            if (wareBusinessData3 != null && preferentialGuideEntity != null) {
                WarePromotionInfo warePromotionInfo = wareBusinessData3.promotionInfo;
                if (warePromotionInfo != null) {
                    preferentialGuideEntity.giftPool3C = warePromotionInfo.giftPool3C;
                }
                PdSuitEntry pdSuitEntry = wareBusinessData3.suit;
                if (pdSuitEntry != null && !pdSuitEntry.getSuit().isEmpty()) {
                    productDetailEntity.mWareBusinessData.suit.setItemData();
                }
                WareBusinessPromotionEntity wareBusinessPromotionEntity = preferentialGuideEntity.promotion;
                if (wareBusinessPromotionEntity != null) {
                    wareBusinessPromotionEntity.suitEntry = productDetailEntity.mWareBusinessData.suit;
                    wareBusinessPromotionEntity.packABTest = productDetailEntity.getPackABTest();
                    preferentialGuideEntity.promotion.dealData();
                }
                preferentialGuideEntity.sortFloorCoupon();
                productDetailEntity.mWareBusinessData.PreferentialGuide.setValue(preferentialGuideEntity);
            }
            return productDetailEntity;
        }
        return null;
    }

    public Observable<PdFeedsSkuIdsEntity> getFeedsSkuIds(String str) {
        return getProductListInternal(str, 1).timeout(1000L, TimeUnit.MILLISECONDS).map(new Func1() { // from class: com.jd.lib.productdetail.core.engine.c
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ProductListEngine.a((HttpResponse) obj);
            }
        });
    }

    public Observable<List<ProductDetailEntity>> getProductList(String str) {
        return getProductList(str, 0);
    }

    private Observable<HttpResponse> getProductListInternal(String str, @FetchType int i2, int i3) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        return getProductListInternal(arrayList, i2, i3);
    }

    public Observable<List<ProductDetailEntity>> getProductList(String str, @FetchType int i2) {
        return getProductList(str, i2, 0);
    }

    public Observable<List<ProductDetailEntity>> getProductList(String str, @FetchType int i2, int i3) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        return getProductList(arrayList, i2, i3);
    }

    private Observable<HttpResponse> getProductListInternal(ArrayList<String> arrayList, @FetchType int i2, int i3) {
        return getProductListInternal(arrayList, i2, i3, null);
    }

    private Observable<HttpResponse> getProductListInternal(ArrayList<String> arrayList, @FetchType final int i2, final int i3, Bundle bundle) {
        final StringBuilder sb = new StringBuilder();
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                sb.append(arrayList.get(i4));
                if (i4 < arrayList.size() - 1) {
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
            }
        }
        String str = "getProductListInternal skuParams = " + ((Object) sb);
        return Observable.create(new Observable.OnSubscribe() { // from class: com.jd.lib.productdetail.core.engine.b
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ProductListEngine.this.g(sb, i3, i2, (Subscriber) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<ProductDetailEntity>> getProductList(ArrayList<String> arrayList, @FetchType int i2) {
        return getProductList(arrayList, i2, 0);
    }

    public Observable<List<ProductDetailEntity>> getProductList(ArrayList<String> arrayList, @FetchType int i2, int i3) {
        return getProductListInternal(arrayList, i2, i3).map(new Func1() { // from class: com.jd.lib.productdetail.core.engine.d
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ProductListEngine.this.c((HttpResponse) obj);
            }
        });
    }
}
