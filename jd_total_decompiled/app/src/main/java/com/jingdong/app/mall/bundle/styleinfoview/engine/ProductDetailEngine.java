package com.jingdong.app.mall.bundle.styleinfoview.engine;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.styleinfoview.base.ProductDetailBaseEngine;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCollageJoinBuyInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBuyStatusProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDECardAddCartProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDFreefreightProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDPrescriptionAddCartProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDCarO2oGiftUtils;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDAuthCodeDialog;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.common.utils.UseCacheHttpGroupUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ProductDetailEngine extends ProductDetailBaseEngine {
    private static final String DESKEY = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    public static final int ERROR = 2;
    public static final int SUCESS = 1;
    private boolean isDestroy;
    private WeakReference<IMyActivity> mContext;
    private ProductEngineListner mEngineListner;
    HttpResponse mResponse;
    private PdMainRequestParam mWareParam;
    private PDAuthCodeDialog pDAuthCodeDialog;
    private ProductDetailEntity product;
    UseCacheHttpGroupUtil productCacheUtil;
    private JDJSONObject wareJsonObject;

    /* loaded from: classes3.dex */
    public interface ProductEngineListner {
        void engineNetStatus(int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject);
    }

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

    private JSONObject buildDefaultSkuParameters(String str, Bundle bundle, SourceEntity sourceEntity) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("skuId", "" + str);
            if (bundle != null) {
                if (bundle.getString(PDConstant.EXTRA_CSKU) != null) {
                    jSONObject.put(PDConstant.EXTRA_CSKU, bundle.getString(PDConstant.EXTRA_CSKU));
                }
                if (bundle.getString("index") != null) {
                    jSONObject.put("index", bundle.getString("index"));
                }
                if (bundle.getString(PDConstant.EXTRA_RID) != null) {
                    jSONObject.put(PDConstant.EXTRA_RID, bundle.getString(PDConstant.EXTRA_RID));
                }
                if (bundle.getString(PDConstant.EXTRA_EXPID) != null) {
                    jSONObject.put(PDConstant.EXTRA_EXPID, bundle.getString(PDConstant.EXTRA_EXPID));
                }
                if (bundle.getString("targetUrl") != null) {
                    jSONObject.put("adClickUrl", bundle.getString("targetUrl"));
                }
            }
            if (!TextUtils.isEmpty(this.product.personas)) {
                jSONObject.put(PDConstant.EXTRA_PERSONAS, this.product.personas);
            }
            if (sourceEntity != null) {
                jSONObject.put(CartConstant.KEY_SOURCE_TYPE, sourceEntity.getSourceType());
                jSONObject.put(CartConstant.KEY_SOURCE_VALUE, sourceEntity.getSourceValue());
            }
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
            if (addressGlobal != null) {
                jSONObject.put("uAddrId", String.valueOf(addressGlobal.getId()));
            }
            if (addressGlobal != null && !TextUtils.isEmpty(addressGlobal.getLatitude()) && addressGlobal.getIsUserAddress().booleanValue()) {
                jSONObject.put(PdLVBody.GCLAT, addressGlobal.getLatitude());
            }
            if (addressGlobal != null && !TextUtils.isEmpty(addressGlobal.getLongitude()) && addressGlobal.getIsUserAddress().booleanValue()) {
                jSONObject.put(PdLVBody.GCLNG, addressGlobal.getLongitude());
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public IMyActivity getContext() {
        return this.mContext.get();
    }

    public boolean isHighRiskUser(String str, HttpSetting httpSetting) {
        if (!TextUtils.equals("1", str) || getContext() == null) {
            return false;
        }
        PDAuthCodeDialog pDAuthCodeDialog = new PDAuthCodeDialog(getContext(), httpSetting, this.httpGroupUtil.getHttpGroupWithNPSGroup(getContext(), null));
        this.pDAuthCodeDialog = pDAuthCodeDialog;
        pDAuthCodeDialog.getAuthCodeSession();
        return true;
    }

    public synchronized void noticeDyInfoDoNext() {
        if (this.wareJsonObject != null) {
            PDCarO2oGiftUtils.setCarGift(this.product, null);
            new PDWareBusinessEntity(this.wareJsonObject);
            if (getContext() != null) {
                final JDJSONObject jDJSONObject = this.wareJsonObject;
                getContext().post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: INVOKE 
                      (wrap: com.jingdong.common.frame.IMyActivity : 0x001a: INVOKE (r5v0 'this' com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine A[IMMUTABLE_TYPE, THIS]) type: DIRECT call: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.getContext():com.jingdong.common.frame.IMyActivity A[Catch: all -> 0x002a, MD:():com.jingdong.common.frame.IMyActivity (m), WRAPPED] (LINE:6))
                      (wrap: java.lang.Runnable : 0x0020: CONSTRUCTOR 
                      (r5v0 'this' com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine A[IMMUTABLE_TYPE, THIS])
                      (r0 I:com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r2v2 'jDJSONObject' com.jd.framework.json.JDJSONObject A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[Catch: all -> 0x002a, MD:(com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine, com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity, com.jd.framework.json.JDJSONObject):void (m), WRAPPED] call: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.3.<init>(com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine, com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity, com.jd.framework.json.JDJSONObject):void type: CONSTRUCTOR)
                     type: INTERFACE call: com.jingdong.common.frame.IMyActivity.post(java.lang.Runnable):void A[Catch: all -> 0x002a, MD:(java.lang.Runnable):void (m)] (LINE:6) in method: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.noticeDyInfoDoNext():void, file: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    monitor-enter(r5)
                    com.jd.framework.json.JDJSONObject r0 = r5.wareJsonObject     // Catch: java.lang.Throwable -> L2a
                    if (r0 == 0) goto L28
                    com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r0 = r5.product     // Catch: java.lang.Throwable -> L2a
                    r1 = 0
                    com.jingdong.app.mall.bundle.styleinfoview.utils.PDCarO2oGiftUtils.setCarGift(r0, r1)     // Catch: java.lang.Throwable -> L2a
                    com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity r0 = new com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity     // Catch: java.lang.Throwable -> L2a
                    com.jd.framework.json.JDJSONObject r2 = r5.wareJsonObject     // Catch: java.lang.Throwable -> L2a
                    r0.<init>(r2)     // Catch: java.lang.Throwable -> L2a
                    com.jingdong.common.frame.IMyActivity r2 = r5.getContext()     // Catch: java.lang.Throwable -> L2a
                    if (r2 == 0) goto L26
                    com.jd.framework.json.JDJSONObject r2 = r5.wareJsonObject     // Catch: java.lang.Throwable -> L2a
                    com.jingdong.common.frame.IMyActivity r3 = r5.getContext()     // Catch: java.lang.Throwable -> L2a
                    com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine$3 r4 = new com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine$3     // Catch: java.lang.Throwable -> L2a
                    r4.<init>()     // Catch: java.lang.Throwable -> L2a
                    r3.post(r4)     // Catch: java.lang.Throwable -> L2a
                L26:
                    r5.wareJsonObject = r1     // Catch: java.lang.Throwable -> L2a
                L28:
                    monitor-exit(r5)
                    return
                L2a:
                    r0 = move-exception
                    monitor-exit(r5)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.noticeDyInfoDoNext():void");
            }

            private void queryWareBusiness(String str, String str2) {
                queryWareBusiness(str, str2, null);
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

            public void queryProduct(String str, String str2) {
                queryProduct(str, str2, null);
            }

            public void setProductEngineListner(ProductEngineListner productEngineListner) {
                this.mEngineListner = productEngineListner;
            }

            private void queryWareBusiness(String str, String str2, Bundle bundle) {
                if (this.product.isOpenPerformance()) {
                    PerfMonitor.getInstance().onRequest("com.jd.lib.productdetail.ProductDetailActivity", "wareBusiness");
                }
                ProductDetailEntity productDetailEntity = this.product;
                productDetailEntity.isLoaded = false;
                productDetailEntity.source = str2;
                final HttpSetting httpSetting = new HttpSetting();
                PdMainRequestParam pdMainRequestParam = this.mWareParam;
                if (pdMainRequestParam != null) {
                    httpSetting.setJsonParams(pdMainRequestParam.buildWareBusinessParam(str, str2, bundle));
                }
                String config = JDMobileConfig.getInstance().getConfig("JDProductdetail", "libpdstyleinfoviewfunctionid", "libstylefunctionid");
                String str3 = TextUtils.isEmpty(config) ? "wareBusiness" : config;
                String str4 = "ProductDetailEngine function = " + str3;
                httpSetting.setFunctionId(str3);
                httpSetting.setModeId(JDGetWayQueueTools.QueueMode.MODE_PRODUCT_DETAIL);
                httpSetting.setEncryptBody(false);
                if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "bodyEncrypt", "pdBodyEncrypt"), DYConstants.DY_TRUE)) {
                    httpSetting.setEncryptBody(true);
                }
                httpSetting.setPageId(PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"));
                httpSetting.setOnQueueCancelListener(new JDGetWayQueueTools.OnQueueCancelListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.1
                    {
                        ProductDetailEngine.this = this;
                    }

                    @Override // com.jingdong.common.utils.JDGetWayQueueTools.OnQueueCancelListener
                    public void onCancel() {
                        if (ProductDetailEngine.this.getContext() != null) {
                            ProductDetailEngine.this.getContext().finish();
                        }
                    }
                });
                httpSetting.setHost(Configuration.getWareHost());
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(1);
                httpSetting.setNotifyUser(false);
                httpSetting.setUseFastJsonParser(true);
                final String str5 = this.product.skuId;
                httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.2
                    {
                        ProductDetailEngine.this = this;
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        if (ProductDetailEngine.this.isDestroy) {
                            return;
                        }
                        try {
                            if (ProductDetailEngine.this.product.isOpenPerformance()) {
                                PerfMonitor.getInstance().onResponse("com.jd.lib.productdetail.ProductDetailActivity", "wareBusiness");
                            }
                            ProductDetailEngine.this.mResponse = httpResponse;
                            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                            if (!ProductDetailEngine.this.isHighRiskUser(fastJsonObject.optString("isAuth"), httpSetting) && TextUtils.equals(ProductDetailEngine.this.product.skuId, str5)) {
                                ProductDetailEngine.this.wareJsonObject = fastJsonObject;
                                ProductDetailEngine.this.noticeDyInfoDoNext();
                            }
                        } catch (Exception unused) {
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        if (ProductDetailEngine.this.getContext() != null) {
                            ProductDetailEngine.this.getContext().post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.2.1
                                {
                                    AnonymousClass2.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    if (ProductDetailEngine.this.mEngineListner != null) {
                                        ProductDetailEngine.this.mEngineListner.engineNetStatus(2, null, null);
                                    }
                                }
                            });
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                    }
                });
                add(httpSetting);
            }

            public void queryProduct(String str, String str2, Bundle bundle) {
                queryWareBusiness(str, str2, bundle);
            }
        }
