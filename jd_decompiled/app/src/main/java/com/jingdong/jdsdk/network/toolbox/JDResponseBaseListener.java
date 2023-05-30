package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.jd.framework.network.JDNetworkHelper;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.error.JDCacheMissError;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.error.JDFileDownloadError;
import com.jd.framework.network.error.JDHttpsError;
import com.jd.framework.network.error.JDIpError;
import com.jd.framework.network.error.JDJsonExceptionError;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.common.network.DefaultEffectHttpListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.network.WindowPopManager;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.InternalConfiguration;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class JDResponseBaseListener<T> implements JDResponseListener<T> {
    public static final String TAG = "JDResponseBaseListener";
    protected HttpGroup httpGroup;
    protected HttpRequest httpRequest;
    protected HttpSetting httpSetting;
    protected boolean isReTry = false;
    protected JDRequest<T> jdRequest;

    public JDResponseBaseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<T> jDRequest) {
        this.httpGroup = httpGroup;
        this.httpSetting = httpSetting;
        this.httpRequest = httpRequest;
        this.jdRequest = jDRequest;
    }

    private boolean handleSpecialCodeEvent(HttpResponse httpResponse, HttpSetting httpSetting) throws Exception {
        if (httpResponse == null) {
            return false;
        }
        if (WindowPopManager.getInstance().shouldInterceptResponse(httpResponse)) {
            httpSetting.setNeedRetryOnBusinessLayer(false);
            return WindowPopManager.getInstance().checkAndHandlePopWindow(httpResponse, httpSetting);
        }
        if (httpResponse.getCode() == 731) {
            if (!httpSetting.isEncryptionDowngrade) {
                httpSetting.isEncryptionDowngrade = true;
                HttpGroup httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool();
                try {
                    if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof DefaultEffectHttpListener)) {
                        ((DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                    }
                    JDHttpTookit.getEngine().getPhcEncryptionPlugin().resendServer731Error(httpSetting.getUrl(), httpSetting.getMapParams() != null ? httpSetting.getMapParams().get("body") : null);
                    httpSetting.resetHttpSetting();
                    httpGroupaAsynPool.add(httpSetting);
                } catch (Throwable th) {
                    if (OKLog.D) {
                        th.printStackTrace();
                    }
                }
                return true;
            }
        } else if (httpResponse.getCode() == 604) {
            InternalConfiguration.encryptFailedNum.incrementAndGet();
            if (!httpSetting.isSafetyModeDowngrade) {
                httpSetting.isSafetyModeDowngrade = true;
                HttpGroup httpGroupaAsynPool2 = HttpGroupUtils.getHttpGroupaAsynPool();
                try {
                    if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof DefaultEffectHttpListener)) {
                        ((DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                    }
                    JDHttpTookit.getEngine().getPhcEncryptionPlugin().reportGateWayDecryptError(httpSetting.getFunctionId(), httpSetting.getUrl());
                    httpSetting.setIsSafeMode(false);
                    httpSetting.resetHttpSetting();
                    httpGroupaAsynPool2.add(httpSetting);
                } catch (Throwable th2) {
                    if (OKLog.D) {
                        th2.printStackTrace();
                    }
                }
                return true;
            }
        } else if (httpResponse.getCode() == 603 && !httpSetting.isGatewaySignFinalRetry) {
            httpSetting.isGatewaySignFinalRetry = true;
            HttpGroup httpGroupaAsynPool3 = HttpGroupUtils.getHttpGroupaAsynPool();
            try {
                if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof DefaultEffectHttpListener)) {
                    ((DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                }
                httpSetting.resetHttpSetting();
                try {
                    String header = httpResponse.getHeader("X-API-Sign-Millis");
                    if (!TextUtils.isEmpty(header)) {
                        ParamBuilderForThirdApp.setServerTimeOffset(Long.parseLong(header) - System.currentTimeMillis());
                    }
                } catch (Throwable unused) {
                }
                httpGroupaAsynPool3.add(httpSetting);
            } catch (Throwable th3) {
                if (OKLog.D) {
                    th3.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    public void retry() {
        this.isReTry = true;
        JDNetworkHelper.getGlobalJDRequestQueue().add(this.jdRequest);
    }

    private boolean shouldPopRespHeader(HttpSetting httpSetting) {
        if (JDHttpTookit.getEngine().getGatewayRespHeaderListener() == null || TextUtils.isEmpty(httpSetting.getHost())) {
            return false;
        }
        if (VolleyLog.DEBUG) {
            return httpSetting.getHost().contains("api.m.jd.care") || httpSetting.getHost().contains("api.m.jd.com");
        }
        return httpSetting.getHost().contains("api.m.jd.com");
    }

    private boolean shouldRetry(HttpError httpError) {
        if (httpError == null || !(httpError.getErrorCode() == 55 || httpError.getErrorCode() == 5)) {
            try {
                List<String> codeNoRetryList = RuntimeConfigHelper.getCodeNoRetryList(RuntimeConfigHelper.BUSINESS_CODE_NO_RETRY_LIST);
                if (!codeNoRetryList.isEmpty()) {
                    String valueOf = String.valueOf(httpError.getJsonCode());
                    if (codeNoRetryList.contains(valueOf)) {
                        if (VolleyLog.DEBUG) {
                            String str = "\u62e6\u622a\u5230Json\u8fd4\u56de\u7801 " + valueOf + " \u5c06\u4e0d\u53d1\u8d77\u91cd\u8bd5";
                        }
                        return false;
                    }
                }
            } catch (Throwable unused) {
            }
            boolean needRetryOnBusinessLayer = this.isReTry ? false : this.httpSetting.needRetryOnBusinessLayer();
            if (OKLog.D) {
                OKLog.d(TAG, "id:" + this.httpSetting.getId() + ", should retry for business layer : " + needRetryOnBusinessLayer);
            }
            return needRetryOnBusinessLayer;
        }
        return false;
    }

    protected abstract void handleResponse(HttpResponse httpResponse, JDResponse<T> jDResponse) throws Exception;

    protected void handlerError(final HttpError httpError) {
        if (OKLog.D) {
            String str = TAG;
            OKLog.e(str, "id:" + this.httpSetting.getId() + "- handlerError -->> " + httpError.toString());
            if (httpError.getException() instanceof JDError) {
                OKLog.e(str, "id:" + this.httpSetting.getId() + "- URL:" + ((JDError) httpError.getException()).getUrl());
            }
            httpError.printStackTrace();
        }
        HttpGroup.HttpErrorAlertControllerFactory httpErrorAlertControllerFactory = this.httpGroup.httpGroupSetting.getHttpErrorAlertControllerFactory();
        if (httpErrorAlertControllerFactory == null) {
            return;
        }
        httpErrorAlertControllerFactory.createController(this.httpGroup.httpGroupSetting, this.httpSetting, this.httpRequest).throwError(httpError, new HttpGroup.HttpErrorAlertListener() { // from class: com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener.1
            {
                JDResponseBaseListener.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpErrorAlertListener
            public void reTry() {
                JDResponseBaseListener.this.retry();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpErrorAlertListener
            public void sendError() {
                JDResponseBaseListener.this.httpSetting.onError(httpError);
            }
        });
        this.httpGroup.addCompletesCount();
    }

    @Override // com.jd.framework.network.JDResponseListener
    public void onCancel() {
        this.httpSetting.onCancel();
    }

    @Override // com.jd.framework.network.JDResponseListener
    public void onEnd(JDResponse<T> jDResponse) {
        HttpError httpError;
        if (OKLog.D) {
            OKLog.d(TAG, "isCache:" + jDResponse.isCache());
        }
        HttpResponse httpResponse = new HttpResponse(this.httpSetting.getMoreParams());
        httpResponse.setCache(jDResponse.isCache());
        httpResponse.setStatusCode(jDResponse.getStatusCode());
        if (shouldPopRespHeader(this.httpSetting)) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.putAll(jDResponse.getHeaders());
                JDHttpTookit.getEngine().getGatewayRespHeaderListener().onRespHeaderReceived(this.httpSetting.getFunctionId(), hashMap);
            } catch (Throwable unused) {
            }
        }
        try {
            handleResponse(httpResponse, jDResponse);
            if (handleSpecialCodeEvent(httpResponse, this.httpSetting)) {
                return;
            }
            this.httpSetting.onEnd(httpResponse);
            this.httpGroup.addCompletesCount();
        } catch (Exception e2) {
            if (e2 instanceof HttpError) {
                httpError = (HttpError) e2;
            } else {
                httpError = new HttpError(e2);
            }
            httpError.setHttpResponse(httpResponse);
            httpError.setResponseCode(httpResponse.getStatusCode());
            reportBusinessLayerException(httpError);
            try {
                JDNetworkHelper.getGlobalJDRequestQueue().getCache().remove(this.httpSetting.getMd5());
            } catch (Throwable unused2) {
                e2.printStackTrace();
            }
            if (shouldRetry(httpError)) {
                retry();
            } else {
                handlerError(httpError);
            }
        }
    }

    @Override // com.jd.framework.network.JDResponseListener
    public void onError(JDError jDError) {
        if (jDError instanceof JDCacheMissError) {
            HttpError httpError = new HttpError(jDError);
            httpError.setHttpResponse(new HttpResponse(this.httpSetting.getMoreParams()));
            httpError.setErrorCode(10);
            handlerError(httpError);
        } else if ((jDError instanceof JDJsonExceptionError) && ((JDJsonExceptionError) jDError).isParseError()) {
            try {
                JDNetworkHelper.getGlobalJDRequestQueue().getCache().remove(this.httpSetting.getMd5());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            HttpError httpError2 = new HttpError(jDError);
            httpError2.setHttpResponse(new HttpResponse(this.httpSetting.getMoreParams()));
            httpError2.setErrorCode(11);
            handlerError(httpError2);
        } else {
            reportNetworkLayerException(jDError);
            if (jDError.isDowngradeError()) {
                HttpError httpError3 = new HttpError(jDError);
                httpError3.setHttpResponse(new HttpResponse(this.httpSetting.getMoreParams()));
                httpError3.setErrorCode(4);
                handlerError(httpError3);
            }
        }
    }

    @Override // com.jd.framework.network.JDResponseListener
    public void onStart() {
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + this.httpSetting.getId() + "onStart()");
        }
        if (this.isReTry) {
            return;
        }
        this.httpSetting.onStart();
    }

    protected void reportBusinessLayerException(HttpError httpError) {
        JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(this.httpSetting.getUrl(), this.httpSetting, httpError, JDNetworkConstant.HTTP_BUSNISS_ERRCODE);
    }

    protected void reportNetworkLayerException(JDError jDError) {
        if (jDError instanceof JDHttpsError.JDHttpsDomainError) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.HTTPS_REQUEST_EXP_ERRCODE);
        } else if (jDError instanceof JDHttpsError.JDHttpsIPError) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.HTTP2_REQUEST_EXP_ERRCODE);
        } else if (jDError instanceof JDIpError) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.IP_REQUEST_EXP_ERRCODE);
        } else if (jDError instanceof JDJsonExceptionError) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.IP_REQUEST_JSONEXP_ERRCODE);
        } else if (jDError instanceof JDFileDownloadError) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.FILE_DOWNLOAD_ERROR);
        } else {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpException(jDError.getUrl(), this.httpSetting, new HttpError(jDError), JDNetworkConstant.DOMAIN_REQUEST_EXP_ERRCODE);
        }
    }
}
