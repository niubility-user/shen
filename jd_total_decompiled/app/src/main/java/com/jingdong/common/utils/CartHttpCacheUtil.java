package com.jingdong.common.utils;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.cart.clean.OnDiskCacheListener;
import com.jingdong.common.entity.cart.CartABCards;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.un.lib.popup.JDTopPopupWindowHelper;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class CartHttpCacheUtil implements HttpGroup.OnCommonListener {
    private static final String SHOPCART_MAIN = "Shopcart_Main";
    private static final String TAG = "UseCacheHttpGroupUtil";
    private static final Set<String> cartFuncSet;
    private IMyActivity activity;
    public long cacheTime;
    public JDGetWayQueueTools.OnQueueCancelListener cancelListener;
    private String functionID;
    private HttpGroup group;
    public String host;
    public String md5Cachekey;
    private HttpGroup.OnCommonListener onAllListener;
    private String param;
    private boolean isOnlyNetData = false;
    public int interval = 0;
    private boolean isNetBack = false;
    private boolean isOnlyCache = false;
    public boolean isUseLocalCookie = false;
    private boolean isPost = true;
    public boolean needError = false;
    public boolean isNotifyUser = false;
    private int errorDialogType = 0;
    private ViewGroup loadingContainer = null;
    public boolean isProduct = false;

    static {
        HashSet hashSet = new HashSet();
        cartFuncSet = hashSet;
        hashSet.add("cartChange");
        hashSet.add("cartCheckAll");
        hashSet.add("cartUnCheckAll");
        hashSet.add("cartUnCheckSingle");
        hashSet.add("cartCheckSingle");
        hashSet.add(CartConstant.FUNCTION_ID_CART_SWITCH);
        hashSet.add("cartRemoveGift");
        hashSet.add("cartRemove");
        hashSet.add("cart");
        hashSet.add("changePromotion");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequestId() {
        CartCommonUtil.requestIdMap.clear();
    }

    private void initHttpSetting(HttpSetting httpSetting) {
        httpSetting.setFunctionId(this.functionID);
        if (!TextUtils.isEmpty(this.param)) {
            httpSetting.setJsonParams(JsonParser.parseParamsJsonFromString(this.param));
        }
        if (!TextUtils.isEmpty(this.host)) {
            httpSetting.setHost(this.host);
        }
        if (!this.isProduct) {
            httpSetting.setEffect(0);
        } else {
            httpSetting.setEffect(1);
            ViewGroup viewGroup = this.loadingContainer;
            if (viewGroup != null) {
                httpSetting.setProgressBarRootLayout(viewGroup);
            }
        }
        httpSetting.setNotifyUser(this.isNotifyUser);
        httpSetting.setCacheMode(2);
        httpSetting.setNeedGlobalInitialization(false);
        httpSetting.setUseFastJsonParser(true);
        this.group.add(httpSetting);
    }

    private void onEndInside(HttpResponse httpResponse) {
        if (this.onAllListener != null) {
            if (OKLog.D) {
                OKLog.d(TAG, " onEndInside --->after onAllListener : " + this.onAllListener);
            }
            this.onAllListener.onEnd(httpResponse);
        }
        reportExceptionMta(1, httpResponse, null);
    }

    private boolean reLoadData() {
        if (this.isOnlyNetData || this.isOnlyCache) {
            return false;
        }
        this.isOnlyNetData = true;
        addUseCache(this.activity, this.group, this.functionID, this.param, this.isPost, this.onAllListener);
        return true;
    }

    private void realMtaReport(int i2, HttpResponse httpResponse, HttpError httpError) {
        JDJSONObject fastJsonObject;
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) Integer.valueOf(i2));
        jDJSONObject.put("appName", (Object) "\u4eac\u4e1cApp");
        jDJSONObject.put("moduleName", (Object) JDTopPopupWindowHelper.BASE_CART);
        jDJSONObject.put("functionId", (Object) this.functionID);
        jDJSONObject.put("inputParam", (Object) this.param);
        if (httpResponse != null && (fastJsonObject = httpResponse.getFastJsonObject()) != null) {
            jDJSONObject.put("outputParam", (Object) fastJsonObject.toString());
        }
        if (httpError != null) {
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("code", (Object) Integer.valueOf(httpError.getResponseCode()));
            jDJSONObject2.put("resultCode", (Object) Integer.valueOf(httpError.getErrorCode()));
            jDJSONObject2.put(CartConstant.KEY_CART_RESULTMSG, (Object) httpError.getMessage());
            jDJSONObject.put("outputParam", (Object) jDJSONObject2.toString());
        }
        jDJSONObject.put("time", (Object) Long.valueOf(System.currentTimeMillis()));
        if (OKLog.D) {
            OKLog.d(TAG, "[realMtaReport] json: " + jDJSONObject);
        }
        IMyActivity iMyActivity = this.activity;
        if (iMyActivity != null) {
            JDMtaUtils.sendClickDataWithExt(iMyActivity.getThisActivity(), "Shopcart_Exeception_Expo", "", "", "Shopcart_Main", "", "", "", jDJSONObject.toString(), null);
        }
    }

    private void reportExceptionMta(int i2, HttpResponse httpResponse, HttpError httpError) {
        String abResult = CartABCards.getAbResult(CartBaseUtil.AB_KEY_108);
        if (OKLog.D) {
            OKLog.d(TAG, "[reportExceptionMta] ab == " + abResult);
        }
        if (TextUtils.isEmpty(abResult) || !TextUtils.equals(abResult, "A")) {
            try {
                if (i2 != 1) {
                    if (i2 != 2 || httpError == null) {
                        return;
                    }
                    realMtaReport(i2, httpResponse, httpError);
                } else if (httpResponse == null) {
                } else {
                    int code = httpResponse.getCode();
                    if (OKLog.D) {
                        OKLog.d(TAG, "[reportExceptionMta] code == " + code);
                    }
                    if (code != 0) {
                        realMtaReport(i2, httpResponse, httpError);
                        return;
                    }
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    int optInt = fastJsonObject.optInt("code", -1);
                    if (OKLog.D) {
                        OKLog.d(TAG, "[reportExceptionMta] code1: " + optInt);
                    }
                    if (optInt == 0) {
                        int optInt2 = fastJsonObject.optInt("resultCode");
                        if (OKLog.D) {
                            OKLog.d(TAG, "[reportExceptionMta] resultCode: " + optInt2);
                        }
                        if (optInt2 == 0 || optInt2 == 1) {
                            return;
                        }
                        realMtaReport(i2, httpResponse, httpError);
                    }
                }
            } catch (Throwable th) {
                if (OKLog.D) {
                    OKLog.d(TAG, "[reportExceptionMta] Throwable: " + th);
                }
                ExceptionReporter.reportExceptionToBugly(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveRequestId(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return;
        }
        String header = httpResponse.getHeader("X-API-Request-Id");
        if (TextUtils.isEmpty(header)) {
            header = httpResponse.getHeader("x-api-request-id");
        }
        if (TextUtils.isEmpty(header)) {
            return;
        }
        if (cartFuncSet.contains(this.functionID)) {
            CartCommonUtil.requestIdMap.put("cartMainData", header);
        } else {
            CartCommonUtil.requestIdMap.put(this.functionID, header);
        }
    }

    public HttpSetting addUseCache(IMyActivity iMyActivity, HttpGroup httpGroup, String str, String str2, HttpGroup.OnCommonListener onCommonListener) {
        return addUseCache(iMyActivity, httpGroup, str, str2, true, onCommonListener);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        if (httpResponse.isCache()) {
            reLoadData();
            if (this.isNetBack) {
                return;
            }
        } else {
            this.isNetBack = true;
        }
        onEndInside(httpResponse);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        if (OKLog.D) {
            OKLog.d(TAG, "addUseCache -->> onError() " + httpError);
            OKLog.d(TAG, "addUseCache -->> functionID() " + this.functionID);
            OKLog.d(TAG, "addUseCache -->> param() " + this.param);
        }
        if (reLoadData()) {
            return;
        }
        if (this.needError) {
            this.isNetBack = true;
        }
        HttpGroup.OnCommonListener onCommonListener = this.onAllListener;
        if (onCommonListener != null) {
            onCommonListener.onError(httpError);
        }
        reportExceptionMta(2, null, httpError);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        HttpGroup.OnCommonListener onCommonListener = this.onAllListener;
        if (onCommonListener != null) {
            onCommonListener.onReady(httpSettingParams);
        }
    }

    public void setLoadingContainer(ViewGroup viewGroup) {
        this.loadingContainer = viewGroup;
    }

    public HttpSetting addUseCache(IMyActivity iMyActivity, HttpGroup httpGroup, String str, String str2, boolean z, HttpGroup.OnCommonListener onCommonListener) {
        this.group = httpGroup;
        this.functionID = str;
        this.onAllListener = onCommonListener;
        this.isPost = z;
        this.activity = iMyActivity;
        this.param = str2;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEncryptBody(CartCommonUtil.isEncryptBody());
        httpSetting.setPageId("Shopcart_Main");
        JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener = this.cancelListener;
        if (onQueueCancelListener != null) {
            httpSetting.setOnQueueCancelListener(onQueueCancelListener);
        }
        httpSetting.setModeId(JDGetWayQueueTools.QueueMode.MODE_CART);
        httpSetting.setListener(new OnDiskCacheListener(str, str2) { // from class: com.jingdong.common.utils.CartHttpCacheUtil.1
            @Override // com.jingdong.common.cart.clean.OnDiskCacheListener
            public void endOperation(HttpResponse httpResponse) {
                CartHttpCacheUtil.this.saveRequestId(httpResponse);
                CartHttpCacheUtil.this.onEnd(httpResponse);
            }

            @Override // com.jingdong.common.cart.clean.OnDiskCacheListener
            public void errorOperation(HttpError httpError) {
                CartHttpCacheUtil.this.clearRequestId();
                CartHttpCacheUtil.this.onError(httpError);
            }

            @Override // com.jingdong.common.cart.clean.OnDiskCacheListener
            public void readyOperation(HttpGroup.HttpSettingParams httpSettingParams) {
                CartHttpCacheUtil.this.onReady(httpSettingParams);
            }
        });
        initHttpSetting(httpSetting);
        return httpSetting;
    }
}
