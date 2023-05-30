package com.jingdong.common.impl.http;

import androidx.fragment.app.FragmentActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.protocol.ParserModule;
import com.jingdong.common.protocol.http.HttpListener;
import com.jingdong.common.protocol.http.IHttpSetting;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class JDHttpImpl implements IHttpSetting, HttpGroup.OnCommonListener, HttpGroup.OnCancelListener {
    private static final String TAG = "JDHttpImpl";
    private HttpListener mHttpListener;
    private HttpRequest mHttpRequest;
    private HttpSetting mHttpSetting;

    public JDHttpImpl() {
        HttpSetting httpSetting = new HttpSetting();
        this.mHttpSetting = httpSetting;
        httpSetting.setUseFastJsonParser(true);
        this.mHttpSetting.setListener(this);
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void addHttpSetting() {
        if (this.mHttpSetting != null) {
            this.mHttpRequest = HttpGroupUtils.getHttpGroupaAsynPool().add(this.mHttpSetting);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void cancelRequest() {
        HttpRequest httpRequest = this.mHttpRequest;
        if (httpRequest != null) {
            httpRequest.stop();
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void getData() {
        this.mHttpSetting.setUseFastJsonParser(true);
        this.mHttpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnCancelListener
    public void onCancel() {
        HttpListener httpListener = this.mHttpListener;
        if (httpListener != null) {
            httpListener.onCancel();
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        HttpListener httpListener = this.mHttpListener;
        if (httpListener != null) {
            if (httpResponse != null) {
                httpListener.onEnd(httpResponse.getFastJsonObject().toString());
            } else {
                httpListener.onError(null);
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        HttpListener httpListener = this.mHttpListener;
        if (httpListener != null) {
            if (httpError != null) {
                httpListener.onError(httpError.getMessage());
            } else {
                httpListener.onError(null);
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        HttpListener httpListener = this.mHttpListener;
        if (httpListener != null) {
            httpListener.onReady(this);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void putJsonParam(Object obj) {
        IJsonParse jsonParser;
        if (obj == null || (jsonParser = UtilFactory.getInstance().getJsonParser(ParserModule.PARSER_FASTJSON)) == null) {
            return;
        }
        putJsonParam(jsonParser.toJsonString(obj));
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void setAttempts(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setAttempts(i2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpUiConfig
    public void setBussinessId(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setBussinessId(i2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpUiConfig
    public void setCacheMode(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setCacheMode(i2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void setCallTimeout(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setCallTimeout(i2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig
    public void setEffect(int i2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setEffect(i2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void setEnableGatewayQueue(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setEnableGatewayQueue(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void setEnableSensitiveParamEnc(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setEnableSensitiveParamEnc(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void setFunctionId(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setFunctionId(str);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void setHost(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setHost(str);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void setListener(HttpListener httpListener) {
        this.mHttpListener = httpListener;
    }

    @Override // com.jingdong.common.protocol.http.IHttpUiConfig
    public void setLocalFileCache(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setLocalFileCache(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpUiConfig
    public void setLocalFileCacheTime(long j2) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setLocalFileCacheTime(j2);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig
    public void setMd5(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setMd5(str);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig
    public void setNotifyUser(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setNotifyUser(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig
    public void setPost(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setPost(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void setTopPriority(boolean z) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.setTopPriority(z);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpSetting
    public void addHttpSetting(FragmentActivity fragmentActivity) {
        if ((fragmentActivity instanceof BaseActivity) && this.mHttpSetting != null) {
            this.mHttpRequest = ((BaseActivity) fragmentActivity).getHttpGroupaAsynPool().add(this.mHttpSetting);
        } else {
            addHttpSetting();
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void putJsonParam(String str) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.putJsonParam(str);
        }
    }

    @Override // com.jingdong.common.protocol.http.IHttpConfig, com.jingdong.common.protocol.http.IHttpUiConfig
    public void putJsonParam(String str, Object obj) {
        HttpSetting httpSetting = this.mHttpSetting;
        if (httpSetting != null) {
            httpSetting.putJsonParam(str, obj);
        }
    }
}
