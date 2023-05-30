package com.jd.cashier.app.jdlibcutter.impl.http;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.http.MultiRequestTokenHelper;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes13.dex */
public class JDHttpImpl implements IHttpSetting, HttpGroup.OnCommonListener {
    private static final String TAG = "HttpImpl";
    private HttpListener mHttpListener;
    private HttpRequest mHttpRequest;
    private HttpSetting mHttpSetting;
    private MultiRequestTokenHelper mRequestTokenHelper = new MultiRequestTokenHelper();

    public JDHttpImpl() {
        HttpSetting httpSetting = new HttpSetting();
        this.mHttpSetting = httpSetting;
        httpSetting.setUseFastJsonParser(true);
        this.mHttpSetting.setListener(this);
        MultiRequestTokenHelper multiRequestTokenHelper = this.mRequestTokenHelper;
        if (multiRequestTokenHelper != null) {
            multiRequestTokenHelper.bindToken(this.mHttpSetting);
        }
        IHostConfig hostConfig = DependInitializer.getHostConfig();
        if (hostConfig != null) {
            this.mHttpSetting.setHost(hostConfig.getHost());
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting
    public void cancelRequest() {
        HttpRequest httpRequest = this.mHttpRequest;
        if (httpRequest != null) {
            httpRequest.stop();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting
    public void doRequest() {
        if (this.mHttpSetting != null) {
            this.mHttpRequest = HttpGroupUtils.getHttpGroupaAsynPool().add(this.mHttpSetting);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        MultiRequestTokenHelper multiRequestTokenHelper;
        if (this.mHttpListener == null || (multiRequestTokenHelper = this.mRequestTokenHelper) == null || !multiRequestTokenHelper.hasPermission(httpResponse)) {
            return;
        }
        if (httpResponse != null) {
            this.mHttpListener.onEnd(httpResponse.getFastJsonObject().toString());
        } else {
            this.mHttpListener.onError(null);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        if (this.mHttpListener != null) {
            if (httpError != null && httpError.getHttpResponse() != null) {
                this.mHttpListener.onError(httpError.getHttpResponse().getString());
            } else {
                this.mHttpListener.onError(null);
            }
        }
        ILog log = DependInitializer.getLog();
        if (log != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("on http error errorCode = ");
            sb.append(httpError == null ? DYConstants.DY_NULL_STR : Integer.valueOf(httpError.getErrorCode()));
            log.d(TAG, sb.toString());
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        HttpListener httpListener = this.mHttpListener;
        if (httpListener != null) {
            httpListener.onReady(this);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void putJsonParam(Object obj) {
        IJsonParser jsonParser = DependInitializer.getJsonParser();
        if (obj == null || jsonParser == null) {
            return;
        }
        putJsonParam(jsonParser.toJsonString(obj));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void putQueryParam(String str, String str2) {
        HttpSetting httpSetting;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || (httpSetting = this.mHttpSetting) == null) {
            return;
        }
        httpSetting.putQueryParam(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setAppId(String str) {
        if (this.mHttpSetting == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mHttpSetting.setAppId(str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setAttempts(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setAttempts(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setCallTimeOut(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setCallTimeout(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    @Deprecated
    public void setConnectTimeOut(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setConnectTimeout(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpUiConfig
    public void setEffect(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setEffect(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting
    public void setEnableSensitiveParamEnc(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setEnableSensitiveParamEnc(z);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setFunctionId(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setFunctionId(str);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setHost(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setHost(str);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setListener(HttpListener httpListener) {
        this.mHttpListener = httpListener;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpUiConfig
    public void setNotifyUser(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setNotifyUser(z);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    @Deprecated
    public void setReadTimeOut(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setReadTimeout(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void setSecretKey(String str) {
        if (this.mHttpSetting == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mHttpSetting.setSecretKey(str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting
    public void doRequest(Activity activity) {
        if (activity != null && this.mHttpSetting != null) {
            HttpGroupUtils.getHttpGroupaAsynPool(1000, activity).add(this.mHttpSetting);
        } else {
            doRequest();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void putJsonParam(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.putJsonParam(str);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig
    public void putJsonParam(String str, Object obj) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.putJsonParam(str, obj);
        }
    }
}
