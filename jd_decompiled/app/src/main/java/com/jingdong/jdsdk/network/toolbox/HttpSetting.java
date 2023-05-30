package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.DefaultEffectHttpListener;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.InternalConfiguration;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.utils.HttpUtils;
import com.jingdong.jdsdk.network.utils.URLParamMap;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpSetting implements HttpGroup.HttpSettingParams, JDGetWayQueueTools.QueueMode {
    public static final int CACHE_MODE_ASSETS = 3;
    public static final int CACHE_MODE_AUTO = 0;
    public static final int CACHE_MODE_BOTH = 4;
    public static final int CACHE_MODE_ONLY_CACHE = 1;
    public static final int CACHE_MODE_ONLY_NET = 2;
    public static final int EFFECT_DEFAULT = 1;
    public static final int EFFECT_NO = 0;
    public static final int EFFECT_STATE_NO = 0;
    public static final int EFFECT_STATE_YES = 1;
    public static final int ERROR_DIALOG_TYPE_BACK_RETRY = 2;
    public static final int ERROR_DIALOG_TYPE_DEFAULT = 0;
    public static final int ERROR_DIALOG_TYPE_ONLY_CANCEL = 1;
    public static final int ERROR_DIALOG_TYPE_SETUP_CANCEL = 3;
    public static final String TAG = "HttpSetting";
    private static ConcurrentHashMap<String, String> TOKENS = new ConcurrentHashMap<>(4);
    public static String charset = "UTF-8";
    private String appId;
    private int attempts;
    private int attemptsTime;
    private String buttonText;
    private CacheResponseChecker cacheResponseChecker;
    private int callTimeout;
    private int connectTimeout;
    private String currentPageName;
    private Map<String, String> customEncryptMapParam;
    private Map<String, String> customMapParams;
    private boolean encryptBodyFlag;
    private Boolean encryptOnlineSwitch;
    private String finalUrl;
    private String functionId;
    private String host;
    private int id;
    private boolean ignoreCharset;
    private boolean ignoreRedirect;
    private boolean isBreakpointTransmission;
    public boolean isEncryptionDowngrade;
    private boolean isForeverCache;
    public boolean isGatewaySignFinalRetry;
    public boolean isSafetyModeDowngrade;
    private JDJSONObject jsonParams;
    private String mModeId;
    private JDGetWayQueueTools.OnQueueCancelListener mOnQueueCancelListener;
    private String mPageId;
    private String mRequestUrl;
    private Map<String, String> mapParams;
    private String md5;
    private Map<String, Object> moreParams;
    private boolean needShareImage;
    private HttpGroup.OnCancelListener onCancelListener;
    private HttpGroup.OnEndListener onEndListener;
    private HttpGroup.OnErrorListener onErrorListener;
    private HttpGroup.OnJsonResponseEndLisener onJsonResponseEndLisener;
    private HttpGroup.OnPauseListener onPauseListener;
    private HttpGroup.OnProgressListener onProgressListener;
    private HttpGroup.OnReadyListener onReadyListener;
    private HttpGroup.OnStartListener onStartListener;
    private boolean onTouchEvent;
    private int priority;
    private WeakReference<ViewGroup> progressBarRootLayout;
    private int readTimeout;
    private String referer;
    private boolean retrieveInputStream;
    private FileGuider savePath;
    private String secretKey;
    private String signatureString;
    private int startPosBreakpointTransmission;
    private int type;
    private String url;
    private String urlPath;
    private boolean needLoal = true;
    private boolean post = IMantoServerRequester.POST.equals(InternalConfiguration.getProperty("requestMethod", IMantoServerRequester.POST));
    private boolean notifyUser = false;
    private boolean useLocalCookies = false;
    private boolean isUseCookies = true;
    private boolean isUseHttps = true;
    private boolean notifyUserWithExit = false;
    private boolean localMemoryCache = false;
    private boolean localFileCache = false;
    private long localFileCacheTime = -1;
    private boolean needGlobalInitialization = true;
    private int effect = 1;
    private int effectState = 0;
    private int cacheMode = 0;
    private boolean noAttempts = false;
    private boolean mNeedAgainEncrypt = false;
    private boolean isTopPriority = false;
    private boolean isExclusiveTask = false;
    private boolean isReady = true;
    private boolean isUseFastJsonParser = false;
    private boolean enableSensitiveParamEnc = false;
    private int alertErrorDialogType = 0;
    private int bussinessId = -1;
    private Map<String, String> mHeaderMap = Collections.EMPTY_MAP;
    private HashMap<String, String> mQueryParam = new HashMap<>();
    private boolean incompatibleWithOkHttp = false;
    private boolean needRetryOnBusinessLayer = true;
    private boolean needRetryOnNetworkLayer = true;
    private boolean needExtraStatisticParam = true;
    private boolean needSignature = true;
    private boolean enableGatewayQueue = true;
    private boolean enableBusinessLayerCheck = true;
    private boolean isSafeMode = true;
    private boolean showDialogOnTopWindow = false;

    public HttpSetting() {
        if (TextUtils.isEmpty(JDGetWayQueueTools.getQT())) {
            return;
        }
        putMapParams("qt", JDGetWayQueueTools.getQT());
    }

    private void dealHeaderToken(HttpResponse httpResponse) {
        if (httpResponse == null || TextUtils.isEmpty(this.mModeId)) {
            return;
        }
        String header = httpResponse.getHeader("JD-M-Scalp");
        if (TextUtils.isEmpty(header)) {
            return;
        }
        TOKENS.put(this.mModeId, header);
    }

    public int getAlertErrorDialogType() {
        return this.alertErrorDialogType;
    }

    public String getAppId() {
        return this.appId;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public int getAttemptsTime() {
        return this.attemptsTime;
    }

    public int getBussinessId() {
        return this.bussinessId;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public int getCacheMode() {
        return this.cacheMode;
    }

    public CacheResponseChecker getCacheResponseChecker() {
        return this.cacheResponseChecker;
    }

    public int getCallTimeout() {
        return this.callTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public String getCurrentPageName() {
        return this.currentPageName;
    }

    public Map<String, String> getCustomEncryptMapParam() {
        return this.customEncryptMapParam;
    }

    public Map<String, String> getCustomMapParam() {
        return this.customMapParams;
    }

    public int getEffect() {
        return this.effect;
    }

    public int getEffectState() {
        return this.effectState;
    }

    public String getFinalUrl() {
        String str = this.finalUrl;
        return str == null ? "" : str;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public Map<String, String> getHeaderMap() {
        if (!TextUtils.isEmpty(this.referer)) {
            Map<String, String> map = this.mHeaderMap;
            if (map == null || map.isEmpty()) {
                this.mHeaderMap = new HashMap();
            }
            this.mHeaderMap.put("Referer", this.referer);
        }
        return this.mHeaderMap;
    }

    public String getHost() {
        return this.host;
    }

    public int getId() {
        return this.id;
    }

    public String getJsonParamsString() {
        if (isEnableSensitiveParamEnc() && JDHttpTookit.getEngine() != null) {
            String encryptBodyParamStr = JDHttpTookit.getEngine().getPhcEncryptionPlugin().getEncryptBodyParamStr(this, this.jsonParams);
            return !TextUtils.isEmpty(encryptBodyParamStr) ? encryptBodyParamStr : "{}";
        }
        JDJSONObject jDJSONObject = this.jsonParams;
        if (jDJSONObject != null) {
            String jSONString = JDJSON.toJSONString(jDJSONObject, SerializerFeature.WriteMapNullValue);
            return jSONString.contains(JsonEncryptUtil.ENC_PROFIX) ? jSONString.replaceAll(JsonEncryptUtil.ENC_PROFIX, "") : jSONString;
        }
        return "{}";
    }

    public long getLocalFileCacheTime() {
        return this.localFileCacheTime;
    }

    public Map<String, String> getMapParams() {
        return this.mapParams;
    }

    public String getMd5() {
        if (this.md5 == null) {
            String url = getUrl();
            if (url == null) {
                return null;
            }
            try {
                URL url2 = new URL(url);
                String path = url2.getPath();
                List<String> splitQuery = HttpUtils.splitQuery(url2);
                if (splitQuery.size() > 0) {
                    Iterator<String> it = splitQuery.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (next.contains(IStatInfoConfig.REPORT_PARAM_NETWORK_TYPE) || next.contains(IStatInfoConfig.REPORT_PARAM_LBS_AREA) || next.contains(IStatInfoConfig.REPORT_PARAM_WIFI_BSSID) || next.contains(IStatInfoConfig.REPORT_PARAM_SIGN) || next.contains(IStatInfoConfig.REPORT_PARAM_ST) || next.contains(IStatInfoConfig.REPORT_PARAM_SV)) {
                            it.remove();
                        }
                    }
                    String queryStr = HttpUtils.toQueryStr(splitQuery);
                    if (!TextUtils.isEmpty(queryStr)) {
                        path = path + "?" + queryStr;
                    }
                }
                if (isPost()) {
                    path = path + getParamCacheKey();
                }
                this.md5 = Md5Encrypt.md5(path);
                if (OKLog.D) {
                    OKLog.d("HttpGroup", "id:" + getId() + " - cacheKey -->> " + path + " md5 -->> " + this.md5);
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return this.md5;
    }

    public Map<String, Object> getMoreParams() {
        if (this.moreParams == null) {
            this.moreParams = new HashMap();
        }
        return this.moreParams;
    }

    public String getMtaFunctionId() {
        return TextUtils.isEmpty(this.functionId) ? "" : this.functionId;
    }

    public boolean getNeedAgainEncrypt() {
        return this.mNeedAgainEncrypt;
    }

    public HttpGroup.OnCancelListener getOnCancelListener() {
        return this.onCancelListener;
    }

    public HttpGroup.OnEndListener getOnEndListener() {
        return this.onEndListener;
    }

    public HttpGroup.OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public HttpGroup.OnPauseListener getOnPauseListener() {
        return this.onPauseListener;
    }

    public HttpGroup.OnProgressListener getOnProgressListener() {
        return this.onProgressListener;
    }

    public JDGetWayQueueTools.OnQueueCancelListener getOnQueueCancelListener() {
        return this.mOnQueueCancelListener;
    }

    public HttpGroup.OnReadyListener getOnReadyListener() {
        return this.onReadyListener;
    }

    public HttpGroup.OnStartListener getOnStartListener() {
        return this.onStartListener;
    }

    public String getPageId() {
        return this.mPageId;
    }

    public String getParamCacheKey() {
        JDJSONObject jDJSONObject = this.jsonParams;
        String jSONString = jDJSONObject != null ? JDJSON.toJSONString(jDJSONObject, SerializerFeature.WriteMapNullValue) : null;
        return TextUtils.isEmpty(jSONString) ? "{}" : jSONString;
    }

    public Map<String, String> getPostMapParams() {
        Map<String, String> map;
        Map<String, String> map2 = this.mapParams;
        if (map2 != null) {
            map2.remove("functionId");
            if (isPost()) {
                Map<String, String> map3 = this.customMapParams;
                if (map3 != null && !map3.isEmpty()) {
                    this.mapParams.putAll(this.customMapParams);
                }
                if (!isEnableEncryptTransmission() && (map = this.customEncryptMapParam) != null && !map.isEmpty()) {
                    this.mapParams.putAll(this.customEncryptMapParam);
                }
            }
        }
        return this.mapParams;
    }

    public int getPriority() {
        return this.priority;
    }

    public ViewGroup getProgressBarRootLayout() {
        WeakReference<ViewGroup> weakReference = this.progressBarRootLayout;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public HashMap<String, String> getQueryParam() {
        return this.mQueryParam;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public String getReferer() {
        return this.referer;
    }

    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    public FileGuider getSavePath() {
        return this.savePath;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSignature() {
        String str = this.signatureString;
        return str == null ? "" : str;
    }

    public int getStartPosBreakpointTransmission() {
        return this.startPosBreakpointTransmission;
    }

    public int getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public boolean incompatibleWithOkHttp() {
        return this.incompatibleWithOkHttp;
    }

    public boolean isBreakpointTransmission() {
        return this.isBreakpointTransmission;
    }

    public boolean isEnableBusinessLayerCheck() {
        return this.enableBusinessLayerCheck;
    }

    public boolean isEnableEncryptTransmission() {
        if (TextUtils.isEmpty(this.host)) {
            return false;
        }
        if (this.encryptOnlineSwitch == null) {
            this.encryptOnlineSwitch = Boolean.valueOf(RuntimeConfigHelper.enableEncryptTransmission(this.host));
        }
        return this.isSafeMode && this.encryptOnlineSwitch.booleanValue();
    }

    public boolean isEnableGatewayQueue() {
        return this.enableGatewayQueue;
    }

    public boolean isEnableSensitiveParamEnc() {
        return this.enableSensitiveParamEnc;
    }

    public boolean isEncryptBody() {
        return this.encryptBodyFlag;
    }

    public boolean isEncryptionDowngrade() {
        return this.isEncryptionDowngrade;
    }

    public boolean isExclusiveTask() {
        return this.isExclusiveTask;
    }

    public boolean isForeverCache() {
        return this.isForeverCache;
    }

    public boolean isIgnoreCharset() {
        return this.ignoreCharset;
    }

    public boolean isIgnoreRedirect() {
        return this.ignoreRedirect;
    }

    public boolean isLocalFileCache() {
        return this.localFileCache;
    }

    public boolean isNeedExtraStatisticParam() {
        return this.needExtraStatisticParam;
    }

    public boolean isNeedGlobalInitialization() {
        return this.needGlobalInitialization;
    }

    public boolean isNeedLoal() {
        return this.needLoal;
    }

    public boolean isNeedShareImage() {
        return this.needShareImage;
    }

    public boolean isNoAttempts() {
        return this.noAttempts;
    }

    public boolean isNotifyUser() {
        return this.notifyUser;
    }

    public boolean isNotifyUserWithExit() {
        return this.notifyUserWithExit;
    }

    public boolean isOnTouchEvent() {
        return this.onTouchEvent;
    }

    public boolean isPost() {
        return this.post;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public boolean isRetrieveInputStream() {
        return this.retrieveInputStream;
    }

    public boolean isSafetyModeDowngrade() {
        return this.isSafetyModeDowngrade;
    }

    public boolean isTopPriority() {
        return this.isTopPriority;
    }

    public boolean isUseCookies() {
        return this.isUseCookies;
    }

    public boolean isUseFastJsonParser() {
        return this.isUseFastJsonParser;
    }

    public boolean isUseHttps() {
        return this.isUseHttps;
    }

    public boolean isUseLocalCookies() {
        return this.useLocalCookies;
    }

    public boolean needRetryOnBusinessLayer() {
        return this.needRetryOnBusinessLayer;
    }

    public boolean needRetryOnNetworkLayer() {
        return this.needRetryOnNetworkLayer;
    }

    public boolean needSignature() {
        return this.needSignature;
    }

    public void onCancel() {
        HttpGroup.OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel();
        }
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id: " + getId() + " oncancel \u3002\u3002\u3002");
        }
    }

    public void onEnd(HttpResponse httpResponse) {
        int length;
        String jSONObjectProxy;
        if (httpResponse == null || (getType() == 1000 && httpResponse.getJSONObject() == null && httpResponse.getFastJsonObject() == null)) {
            JDHttpTookit.getEngine().getExceptionReportDelegate().reportHttpBusinessException(this, httpResponse);
        }
        Type type = null;
        if (OKLog.D) {
            try {
                if (getType() == 1000 && httpResponse != null) {
                    if (this.isUseFastJsonParser) {
                        if (httpResponse.getFastJsonObject() != null) {
                            length = httpResponse.getFastJsonObject().toString().length();
                            jSONObjectProxy = httpResponse.getFastJsonObject().toString();
                            OKLog.d("HttpGroup", "id:" + getId() + "- response data length :" + length);
                            OKLog.d("HttpGroup", "id:" + getId() + "- response data:" + jSONObjectProxy);
                        }
                        jSONObjectProxy = null;
                        length = 0;
                        OKLog.d("HttpGroup", "id:" + getId() + "- response data length :" + length);
                        OKLog.d("HttpGroup", "id:" + getId() + "- response data:" + jSONObjectProxy);
                    } else {
                        if (httpResponse.getJSONObject() != null) {
                            length = httpResponse.getJSONObject().toString().length();
                            jSONObjectProxy = httpResponse.getJSONObject().toString();
                            OKLog.d("HttpGroup", "id:" + getId() + "- response data length :" + length);
                            OKLog.d("HttpGroup", "id:" + getId() + "- response data:" + jSONObjectProxy);
                        }
                        jSONObjectProxy = null;
                        length = 0;
                        OKLog.d("HttpGroup", "id:" + getId() + "- response data length :" + length);
                        OKLog.d("HttpGroup", "id:" + getId() + "- response data:" + jSONObjectProxy);
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    e2.printStackTrace();
                }
            }
        }
        dealHeaderToken(httpResponse);
        HttpGroup.OnEndListener onEndListener = this.onEndListener;
        if (onEndListener != null) {
            onEndListener.onEnd(httpResponse);
        }
        HttpGroup.OnJsonResponseEndLisener onJsonResponseEndLisener = this.onJsonResponseEndLisener;
        if (onJsonResponseEndLisener != null) {
            for (Type type2 : onJsonResponseEndLisener.getClass().getGenericInterfaces()) {
                if (type2 instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) type2).getActualTypeArguments();
                    if (actualTypeArguments.length > 0) {
                        type = actualTypeArguments[0];
                    }
                }
            }
            if (type == null) {
                HttpGroup.OnErrorListener onErrorListener = this.onErrorListener;
                if (onErrorListener != null) {
                    onErrorListener.onError(new HttpError(new Exception("Can't get Json response Type.")));
                    return;
                }
                return;
            }
            try {
                this.onJsonResponseEndLisener.onEnd(httpResponse, JDJSON.parseObject(httpResponse.getString(), type, new Feature[0]));
            } catch (Exception e3) {
                e3.printStackTrace();
                if (OKLog.D) {
                    OKLog.e("HttpGroup", "Parse object " + type + "Error! \nHttpResponse Json Data : " + httpResponse.getString());
                }
                HttpGroup.OnErrorListener onErrorListener2 = this.onErrorListener;
                if (onErrorListener2 != null) {
                    onErrorListener2.onError(new HttpError(e3));
                }
            }
        }
    }

    public void onError(HttpError httpError) {
        if (OKLog.D) {
            OKLog.d("HttpGroup", "onError: request:" + getId() + ":" + getFunctionId());
        }
        HttpGroup.OnErrorListener onErrorListener = this.onErrorListener;
        if (onErrorListener != null) {
            onErrorListener.onError(httpError);
        }
    }

    public void onPause() {
        HttpGroup.OnPauseListener onPauseListener = this.onPauseListener;
        if (onPauseListener != null) {
            onPauseListener.onPause();
        }
    }

    public void onProgress(int i2, int i3) {
        HttpGroup.OnProgressListener onProgressListener = this.onProgressListener;
        if (onProgressListener != null) {
            onProgressListener.onProgress(i2, i3);
        }
    }

    public void onStart() {
        HttpGroup.OnStartListener onStartListener = this.onStartListener;
        if (onStartListener != null) {
            onStartListener.onStart();
        }
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id: " + getId() + " onstart \u3002\u3002\u3002");
        }
    }

    public void putCustomEncryptMapParams(Map<String, String> map) {
        if (this.customEncryptMapParam == null) {
            this.customEncryptMapParam = new HashMap();
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        this.customEncryptMapParam.putAll(map);
    }

    public void putCustomMapParams(Map<String, String> map) {
        if (this.customMapParams == null) {
            this.customMapParams = new HashMap();
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        this.customMapParams.putAll(map);
    }

    public void putJsonParam(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (this.jsonParams == null) {
                this.jsonParams = (JDJSONObject) JDJSON.toJSON(obj);
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                th.printStackTrace();
                OKLog.d("HttpGroup", "JSONException -->> ", th);
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpSettingParams
    public void putMapParams(String str, String str2) {
        if (this.mapParams == null) {
            if ((getType() == 1000 || getType() == 0) && isPost()) {
                this.mapParams = new HashMap();
            } else {
                this.mapParams = new URLParamMap(charset);
            }
        }
        this.mapParams.put(str, str2);
    }

    public void putQueryParam(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.mQueryParam.put(str, str2);
    }

    public void resetHttpSetting() {
        setUrl(null);
        if (getMapParams() == null || !getMapParams().containsKey("body")) {
            return;
        }
        getMapParams().remove("body");
    }

    public void setAlertErrorDialogType(int i2) {
        this.alertErrorDialogType = i2;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAttempts(int i2) {
        this.attempts = i2;
    }

    public void setAttemptsTime(int i2) {
        this.attemptsTime = i2;
    }

    public void setBreakpointTransmission(boolean z) {
        this.isBreakpointTransmission = z;
    }

    public void setBusinessLayerCheckSwitch(boolean z) {
        this.enableBusinessLayerCheck = z;
    }

    public void setBussinessId(int i2) {
        this.bussinessId = i2;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public void setCacheMode(int i2) {
        this.cacheMode = i2;
    }

    public void setCacheResponseChecker(CacheResponseChecker cacheResponseChecker) {
        this.cacheResponseChecker = cacheResponseChecker;
    }

    public void setCallTimeout(int i2) {
        this.callTimeout = i2;
    }

    @Deprecated
    public void setConnectTimeout(int i2) {
        this.connectTimeout = i2;
    }

    public void setCurrentPageName(String str) {
        this.currentPageName = str;
    }

    public void setEffect(int i2) {
        this.effect = i2;
    }

    public void setEffectState(int i2) {
        this.effectState = i2;
    }

    public void setEnableGatewayQueue(boolean z) {
        this.enableGatewayQueue = z;
    }

    public void setEnableSensitiveParamEnc(boolean z) {
        this.enableSensitiveParamEnc = z;
    }

    public void setEncryptBody(boolean z) {
        this.encryptBodyFlag = z;
    }

    public void setFinalUrl(String str) {
        this.finalUrl = str;
    }

    public void setForeverCache(boolean z) {
        this.isForeverCache = z;
    }

    public void setFunctionId(String str) {
        if (JDHttpTookit.getEngine() == null) {
            this.functionId = str;
        } else if (TextUtils.isEmpty(JDHttpTookit.getEngine().getFunctionIdPrefix())) {
            this.functionId = str;
        } else {
            this.functionId = String.format("%s_%s", JDHttpTookit.getEngine().getFunctionIdPrefix(), str);
        }
    }

    public void setHeaderMap(Map<String, String> map) {
        this.mHeaderMap = map;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setIgnoreCharset(boolean z) {
        this.ignoreCharset = z;
    }

    public void setIgnoreRedirect(boolean z) {
        this.ignoreRedirect = z;
    }

    public void setIncompatibleWithOkHttp(boolean z) {
        this.incompatibleWithOkHttp = z;
    }

    public void setIsExclusiveTask(boolean z) {
        this.isExclusiveTask = z;
    }

    public void setIsSafeMode(boolean z) {
        this.isSafeMode = z;
    }

    @Deprecated
    public void setJsonParams(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            this.jsonParams = JDJSON.parseObject(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setListener(HttpGroup.HttpTaskListener httpTaskListener) {
        if (httpTaskListener instanceof HttpGroup.CustomOnAllListener) {
            setEffect(0);
        }
        if (httpTaskListener instanceof DefaultEffectHttpListener) {
            setEffectState(1);
        }
        if (httpTaskListener instanceof HttpGroup.OnErrorListener) {
            this.onErrorListener = (HttpGroup.OnErrorListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnStartListener) {
            this.onStartListener = (HttpGroup.OnStartListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnCancelListener) {
            this.onCancelListener = (HttpGroup.OnCancelListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnProgressListener) {
            this.onProgressListener = (HttpGroup.OnProgressListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnEndListener) {
            this.onEndListener = (HttpGroup.OnEndListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnReadyListener) {
            this.onReadyListener = (HttpGroup.OnReadyListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnPauseListener) {
            this.onPauseListener = (HttpGroup.OnPauseListener) httpTaskListener;
        }
        if (httpTaskListener instanceof HttpGroup.OnJsonResponseEndLisener) {
            this.onJsonResponseEndLisener = (HttpGroup.OnJsonResponseEndLisener) httpTaskListener;
        }
    }

    public void setLocalFileCache(boolean z) {
        this.localFileCache = z;
    }

    public void setLocalFileCacheTime(long j2) {
        this.localFileCacheTime = j2;
    }

    public void setLocalMemoryCache(boolean z) {
        this.localMemoryCache = z;
    }

    @Deprecated
    public void setMapParams(Map<String, String> map) {
        if (map == null) {
            return;
        }
        for (String str : map.keySet()) {
            putMapParams(str, map.get(str));
        }
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setModeId(String str) {
        this.mModeId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = TOKENS.get(this.mModeId);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        putMapParams("scalp", str2);
    }

    public void setMoreParams(Map<String, Object> map) {
        this.moreParams = map;
    }

    public void setNeedEncrypt(boolean z) {
        this.mNeedAgainEncrypt = z;
    }

    public void setNeedExtraStatisticParam(boolean z) {
        this.needExtraStatisticParam = z;
    }

    public void setNeedGlobalInitialization(boolean z) {
        this.needGlobalInitialization = z;
    }

    public void setNeedLoal(boolean z) {
        this.needLoal = z;
    }

    public void setNeedRetryOnBusinessLayer(boolean z) {
        this.needRetryOnBusinessLayer = z;
    }

    public void setNeedRetryOnNetworkLayer(boolean z) {
        this.needRetryOnNetworkLayer = z;
    }

    public void setNeedShareImage(boolean z) {
        this.needShareImage = z;
    }

    public void setNeedSignature(boolean z) {
        this.needSignature = z;
    }

    public void setNoAttempts(boolean z) {
        this.noAttempts = z;
    }

    public void setNotifyUser(boolean z) {
        this.notifyUser = z;
    }

    public void setNotifyUserWithExit(boolean z) {
        this.notifyUserWithExit = z;
    }

    public void setOnQueueCancelListener(JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        this.mOnQueueCancelListener = onQueueCancelListener;
    }

    public void setOnTouchEvent(boolean z) {
        this.onTouchEvent = z;
    }

    public void setPageId(String str) {
        this.mPageId = str;
    }

    public void setPost(boolean z) {
        this.post = z;
    }

    public void setPriority(int i2) {
        this.priority = i2;
    }

    public void setProgressBarRootLayout(ViewGroup viewGroup) {
        this.progressBarRootLayout = new WeakReference<>(viewGroup);
    }

    @Deprecated
    public void setReadTimeout(int i2) {
        this.readTimeout = i2;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpSettingParams
    public void setReady(boolean z) {
        this.isReady = z;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public void setRequestUrl(String str) {
        this.mRequestUrl = str;
        setUrl(str);
    }

    public void setRetrieveInputStreamFlag(boolean z) {
        this.retrieveInputStream = z;
    }

    public void setSavePath(FileGuider fileGuider) {
        this.savePath = fileGuider;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public void setShowDialogOnTopWindow(boolean z) {
        this.showDialogOnTopWindow = z;
    }

    public void setSignature(String str) {
        this.signatureString = str;
    }

    public void setStartPosBreakpointTransmission(int i2) {
        this.startPosBreakpointTransmission = i2;
    }

    public void setTopPriority(boolean z) {
        this.isTopPriority = z;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUrlPath(String str) {
        this.urlPath = str;
    }

    public void setUseCookies(boolean z) {
        this.isUseCookies = z;
    }

    public void setUseFastJsonParser(boolean z) {
        this.isUseFastJsonParser = z;
    }

    public void setUseHttps(boolean z) {
        this.isUseHttps = z;
    }

    public void setUseLocalCookies(boolean z) {
        this.useLocalCookies = z;
    }

    public boolean shouldColorSign() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.secretKey)) ? false : true;
    }

    public boolean showDialogOnTopWindow() {
        return this.showDialogOnTopWindow;
    }

    @Deprecated
    public void setJsonParams(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.jsonParams = jDJSONObject;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpSettingParams
    public void putJsonParam(String str, Object obj) {
        if (this.jsonParams == null) {
            this.jsonParams = new JDJSONObject();
        }
        try {
            if (obj instanceof JSONObject) {
                obj = JDJSON.parseObject(obj.toString());
            } else if (obj instanceof JSONArray) {
                obj = JDJSON.parseArray(obj.toString());
            }
            this.jsonParams.put(str, obj);
        } catch (JSONException e2) {
            if (OKLog.D) {
                OKLog.d("HttpGroup", "JSONException -->> ", e2);
            }
        }
    }
}
