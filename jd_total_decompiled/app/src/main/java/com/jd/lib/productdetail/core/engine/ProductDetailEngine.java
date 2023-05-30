package com.jd.lib.productdetail.core.engine;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.base.ProductDetailBaseEngine;
import com.jd.lib.productdetail.core.business.entity.PDWareBusinessEntity;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.eut.PdEuropAddress;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdShortFillOrderEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCollageJoinBuyInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jd.lib.productdetail.core.protocol.PDBuyStatusProtocol;
import com.jd.lib.productdetail.core.protocol.PDDJGetCartNumProtocol;
import com.jd.lib.productdetail.core.protocol.PDECardAddCartProtocol;
import com.jd.lib.productdetail.core.protocol.PDFreefreightProtocol;
import com.jd.lib.productdetail.core.protocol.PDPrescriptionAddCartProtocol;
import com.jd.lib.productdetail.core.protocol.PdExceptionReportProtocol;
import com.jd.lib.productdetail.core.protocol.PdShareContentProtocol;
import com.jd.lib.productdetail.core.utils.PDCarO2oGiftUtils;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.PDAuthCodeDialog;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ProductDetailController;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.ProductFillOrderRefreshPageEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.address.eu.EuShippingUtils;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.UseCacheHttpGroupUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import java.lang.ref.WeakReference;

/* loaded from: classes15.dex */
public class ProductDetailEngine extends ProductDetailBaseEngine {
    private static final String DESKEY = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    private static final String EUROP_REFRESH = "refreshMe0";
    public static final String EXTRA_INNER_SOURCE_INIT = "extra.inner.source.init";
    public static final int[] errorCode = {1, 2, -1, 600, 602};
    private boolean isDestroy;
    private final WeakReference<IMyActivity> mContext;
    private String mJsonString;
    private final PdMainRequestParam mWareParam;
    private PDAuthCodeDialog pDAuthCodeDialog;
    private ProductDetailEntity product;
    UseCacheHttpGroupUtil productCacheUtil;
    private JDJSONObject wareJsonObject;

    public ProductDetailEngine(IMyActivity iMyActivity, HttpGroupUtil httpGroupUtil, ProductDetailEntity productDetailEntity) {
        super(httpGroupUtil);
        this.isDestroy = false;
        this.productCacheUtil = new UseCacheHttpGroupUtil();
        this.mContext = new WeakReference<>(iMyActivity);
        this.product = productDetailEntity;
        this.mWareParam = new PdMainRequestParam(productDetailEntity);
    }

    private void add(HttpSetting httpSetting) {
        if (getContext() != null) {
            this.httpGroupUtil.getHttpGroupWithNPSGroup(getContext(), null).add(httpSetting);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fillOrderChangeColorSizeFail(String str, int i2) {
        ProductFillOrderRefreshPageEntity productFillOrderRefreshPageEntity = new ProductFillOrderRefreshPageEntity();
        PdShortFillOrderEntity pdShortFillOrderEntity = new PdShortFillOrderEntity();
        pdShortFillOrderEntity.checkoutEnable = "0";
        pdShortFillOrderEntity.fetchDataCode = i2 + "";
        productFillOrderRefreshPageEntity.skuId = str;
        productFillOrderRefreshPageEntity.jsonStr = JDJSON.toJSONString(pdShortFillOrderEntity);
        ProductDetailController.refreshProductDetailCallBack(productFillOrderRefreshPageEntity, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IMyActivity getContext() {
        return this.mContext.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PdEuropAddress isEuaUser(JDJSONObject jDJSONObject, HttpSetting httpSetting) {
        boolean z;
        JDJSONArray optJSONArray;
        int size;
        JDJSONObject parseObject;
        if (httpSetting == null) {
            return null;
        }
        try {
            parseObject = JDJSON.parseObject(httpSetting.getParamCacheKey());
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        if (parseObject != null) {
            z = parseObject.optBoolean(EUROP_REFRESH, false);
            if (z && jDJSONObject != null && (optJSONArray = jDJSONObject.optJSONArray("floors")) != null) {
                for (size = optJSONArray.size() - 1; size >= 0; size--) {
                    JDJSONObject jSONObject = optJSONArray.getJSONObject(size);
                    if (jSONObject != null && TextUtils.equals(jSONObject.optString("mId"), "bpMasterdata")) {
                        JDJSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null && optJSONObject.optBoolean(EUROP_REFRESH, false)) {
                            PdEuropAddress pdEuropAddress = (PdEuropAddress) JDJSON.parseObject(jSONObject.optString("data"), PdEuropAddress.class);
                            AddressGlobal addressGlobal = new AddressGlobal();
                            if (pdEuropAddress != null) {
                                pdEuropAddress.fill(addressGlobal);
                                EuShippingUtils.updateEUShippingAddress(addressGlobal);
                            }
                            return pdEuropAddress;
                        }
                    }
                }
            }
            return null;
        }
        z = false;
        if (z) {
            return null;
        }
        while (size >= 0) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHighRiskUser(String str, HttpSetting httpSetting) {
        if (!TextUtils.equals("1", str) || getContext() == null) {
            return false;
        }
        PDAuthCodeDialog pDAuthCodeDialog = new PDAuthCodeDialog(getContext(), httpSetting, this.httpGroupUtil.getHttpGroupWithNPSGroup(getContext(), null));
        this.pDAuthCodeDialog = pDAuthCodeDialog;
        pDAuthCodeDialog.getAuthCodeSession();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void noticeDyInfoDoNext() {
        if (this.wareJsonObject != null) {
            PDCarO2oGiftUtils.setCarGift(this.product, null);
            PDWareBusinessEntity pDWareBusinessEntity = new PDWareBusinessEntity(this.wareJsonObject);
            pDWareBusinessEntity.mJsonString = this.mJsonString;
            this.product.isLocalRefresh = false;
            PDManager.getEventBus().post(new PDApiEvent("detail_ware_business_new_key", pDWareBusinessEntity, this.product.mManageKey));
            this.wareJsonObject = null;
        }
    }

    private void queryWareBusiness(String str, Bundle bundle, int i2, boolean z, String str2) {
        queryWareBusiness(str, bundle, i2, z, str2, null);
    }

    public void getBuyStatusData() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity;
        ProductDetailEntity productDetailEntity = this.product;
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        String str = (wareBusinessData == null || (wareBusinessCollageJoinBuyInfoEntity = wareBusinessData.joinBuyInfo) == null || !wareBusinessCollageJoinBuyInfoEntity.isJoinBuy) ? null : "1";
        PDBuyStatusProtocol pDBuyStatusProtocol = new PDBuyStatusProtocol(productDetailEntity.mManageKey);
        pDBuyStatusProtocol.setInputParams(this.product.skuId, str);
        add(pDBuyStatusProtocol.request());
    }

    public void onDestroy() {
        this.isDestroy = true;
        PDAuthCodeDialog pDAuthCodeDialog = this.pDAuthCodeDialog;
        if (pDAuthCodeDialog != null) {
            pDAuthCodeDialog.onDestroy();
            this.pDAuthCodeDialog = null;
        }
        this.product = null;
    }

    public void queryCoudanFreightInfo() {
        PDFreefreightProtocol pDFreefreightProtocol = new PDFreefreightProtocol(this.product.mManageKey);
        ProductDetailEntity productDetailEntity = this.product;
        pDFreefreightProtocol.setInputParams(productDetailEntity.skuId, productDetailEntity.getCoudanOrderMin());
        add(pDFreefreightProtocol.request());
    }

    public void queryDJCartNum() {
        PDDJGetCartNumProtocol pDDJGetCartNumProtocol = new PDDJGetCartNumProtocol(this.product.mManageKey);
        pDDJGetCartNumProtocol.setInputParams(this.product.storeId, 0);
        add(pDDJGetCartNumProtocol.request());
    }

    public void queryDurgListCount() {
        if (LoginUserBase.hasLogin()) {
            ProductDetailEntity productDetailEntity = this.product;
            add(new PDPrescriptionAddCartProtocol(productDetailEntity.mManageKey, "rxCartNum", productDetailEntity.getDrugBusinessId()).request());
        }
    }

    public void queryECardListCount() {
        if (LoginUserBase.hasLogin()) {
            add(new PDECardAddCartProtocol(this.product.mManageKey, "getCartNum").request());
        }
    }

    public void queryProduct(String str, Bundle bundle, String str2) {
        queryWareBusiness(str, bundle, 0, false, str2);
    }

    public void queryProductAfterChangeAddress(String str, Bundle bundle, boolean z, String str2) {
        queryWareBusiness(str, bundle, 1, z, str2);
    }

    public void queryShareContentText() {
        PdShareContentProtocol pdShareContentProtocol = new PdShareContentProtocol(this.product.mManageKey);
        pdShareContentProtocol.setInputParams(this.product.skuId);
        add(pdShareContentProtocol.request());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryWareBusiness(final String str, final Bundle bundle, final int i2, final boolean z, final String str2, PdEuropAddress pdEuropAddress) {
        if (this.product.isOpenPerformance()) {
            PerfMonitor.getInstance().onRequest("com.jd.lib.productdetail.ProductDetailActivity", "wareBusiness");
        }
        this.product.isLoaded = false;
        final HttpSetting httpSetting = new HttpSetting();
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity != null && productDetailEntity.mWareBusinessData != null && productDetailEntity.isPaiPaiSecond() && this.product.isPaiPaiSecondOpen()) {
            if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, this.product.oldSkuId)) {
                this.product.mPaiPaiInspectIds = "";
            }
        } else {
            this.product.mPaiPaiInspectIds = "";
        }
        if (this.mWareParam != null) {
            httpSetting.setJsonParams(this.mWareParam.buildWareBusinessParam(str, bundle, i2, z, str2, getContext() != null ? JDPrivacyHelper.isAcceptPrivacy(getContext().getThisActivity()) : true));
        }
        if (pdEuropAddress != null) {
            httpSetting.putJsonParam("euta", pdEuropAddress.euta);
            httpSetting.putJsonParam(EUROP_REFRESH, Boolean.valueOf(pdEuropAddress.refreshMe0));
        }
        httpSetting.setFunctionId("wareBusiness");
        httpSetting.setEncryptBody(false);
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "bodyEncrypt", "pdBodyEncrypt"), DYConstants.DY_TRUE)) {
            httpSetting.setEncryptBody(true);
        }
        httpSetting.setModeId(JDGetWayQueueTools.QueueMode.MODE_PRODUCT_DETAIL);
        httpSetting.setPageId(PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"));
        httpSetting.putQueryParam(JDMobileConfig.getInstance().getConfig("JDProductdetail", "productDetailputQueryParam", "queryParamKey"), this.product.skuId);
        String config = JDMobileConfig.getInstance().getConfig("JDProductdetail", "rmtj", "hotActivity");
        if (!TextUtils.isEmpty(config) && config.contains(this.product.skuId)) {
            httpSetting.putQueryParam("xAPIScval2", "1");
        }
        httpSetting.setHost(Configuration.getWareHost());
        if (TextUtils.equals(str2, PDManager.WARE_SOURCE_FROM_ORDER_STYLE)) {
            httpSetting.setShowDialogOnTopWindow(true);
        }
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        final String str3 = this.product.skuId;
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jd.lib.productdetail.core.engine.ProductDetailEngine.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (ProductDetailEngine.this.isDestroy) {
                    return;
                }
                try {
                    if (ProductDetailEngine.this.product.isOpenPerformance()) {
                        PerfMonitor.getInstance().onResponse("com.jd.lib.productdetail.ProductDetailActivity", "wareBusiness");
                    }
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (ProductDetailEngine.this.isHighRiskUser(fastJsonObject.optString("isAuth"), httpSetting)) {
                        return;
                    }
                    PdEuropAddress isEuaUser = ProductDetailEngine.this.isEuaUser(fastJsonObject, httpSetting);
                    if (isEuaUser != null) {
                        ProductDetailEngine.this.queryWareBusiness(str, bundle, i2, z, str2, isEuaUser);
                        return;
                    }
                    ProductDetailEngine.this.product.allwareJsonObject = fastJsonObject;
                    ProductDetailEngine.this.product.dataTime = System.currentTimeMillis();
                    if (TextUtils.equals(ProductDetailEngine.this.product.skuId, str3)) {
                        ProductDetailEngine.this.wareJsonObject = fastJsonObject;
                        ProductDetailEngine.this.mJsonString = fastJsonObject.toJSONString();
                        ProductDetailEngine.this.product.oldSkuId = str3;
                        ProductDetailEngine.this.product.wareSourceNative = str2;
                        ProductDetailEngine.this.noticeDyInfoDoNext();
                    }
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d("ProductDetailBaseEngine", "JSONException -->> ", e2);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (ProductDetailEngine.this.isDestroy || httpError == null) {
                    return;
                }
                try {
                    ProductDetailEngine.this.fillOrderChangeColorSizeFail(str3, httpError.getJsonCode());
                    if (PDUtils.isReportExEnable() && ProductDetailEngine.this.getContext() != null && (ProductDetailEngine.this.getContext() instanceof BaseActivity)) {
                        BaseActivity baseActivity = (BaseActivity) ProductDetailEngine.this.getContext();
                        if (ProductDetailEngine.this.product != null && baseActivity.getLifecycle() != null && baseActivity.getLifecycle().getCurrentState() != null && baseActivity.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                            PdExceptionReportProtocol.RequestParams requestParams = new PdExceptionReportProtocol.RequestParams();
                            requestParams.errorCode = PdExceptionReportProtocol.RequestParams.ERROR_CODE_REQUEST_TIMEOUT;
                            requestParams.skuId = ProductDetailEngine.this.product.skuId;
                            if (httpError.getHttpResponse() != null) {
                                requestParams.desc = httpError.getHttpResponse().getString();
                            } else {
                                requestParams.desc = String.valueOf(httpError.getJsonCode());
                            }
                            new PdExceptionReportProtocol(requestParams).request(baseActivity);
                        }
                    }
                    if (httpError.getJsonCode() == 601) {
                        if (ProductDetailEngine.this.getContext() != null) {
                            ProductDetailEngine.this.getContext().finish();
                        }
                    } else if (httpError.getJsonCode() == 605) {
                        PDManager.getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_WARE_BUSINESS_ERROR_KEY, Integer.valueOf(httpError.getJsonCode()), ProductDetailEngine.this.product.mManageKey));
                    } else {
                        if (httpError.getResponseCode() != 502 && !PDUtils.containElement(httpError.getJsonCode(), ProductDetailEngine.errorCode)) {
                            PDManager.getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_WARE_BUSINESS_EXCEPTION_KEY, Integer.valueOf(httpError.getJsonCode()), ProductDetailEngine.this.product.mManageKey));
                            return;
                        }
                        PDManager.getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_WARE_BUSINESS_EXCEPTION_KEY, Integer.valueOf(httpError.getResponseCode() == 502 ? httpError.getResponseCode() : httpError.getJsonCode()), ProductDetailEngine.this.product.mManageKey));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        add(httpSetting);
    }
}
