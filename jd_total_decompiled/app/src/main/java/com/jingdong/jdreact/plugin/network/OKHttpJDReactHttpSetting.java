package com.jingdong.jdreact.plugin.network;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.download.JDReactFileGuider;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.jdreact.plugin.network.ApiUrl;
import com.jingdong.jdreact.plugin.network.OKHttpUtil;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class OKHttpJDReactHttpSetting extends JDReactHttpSetting.AbstractJDReactHttpSetting {
    private ApiUrl.RequestCallback callback;
    private OKHttpUtil.DownloadListener downloadListener;
    private String downloadUrl;
    private String functionId;
    private String host;
    private JDJSONObject jdJsonObj = new JDJSONObject();
    private String saveFileName;

    public OKHttpJDReactHttpSetting() {
        putJsonParam("appCode", NetConfig.sAppCode);
    }

    public static JDJSONObject strToJDJSONObject(String str) {
        try {
            return JDJSON.parseObject(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void download() {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void putJsonParam(String str, String str2) {
        putJsonParam(str, (Object) str2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setA2(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setAttempts(int i2) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setCacheMode(int i2) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setEffect(int i2) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setFunctionId(String str) {
        this.functionId = str;
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setHost(String str) {
        this.host = str;
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setListener(final JDReactHttpSetting.HttpCallback httpCallback) {
        if (httpCallback == null) {
            return;
        }
        if (this.downloadUrl != null) {
            this.downloadListener = new OKHttpUtil.DownloadListener() { // from class: com.jingdong.jdreact.plugin.network.OKHttpJDReactHttpSetting.1
                @Override // com.jingdong.jdreact.plugin.network.OKHttpUtil.DownloadListener
                public void onDownloadFailed() {
                    httpCallback.onError();
                }

                @Override // com.jingdong.jdreact.plugin.network.OKHttpUtil.DownloadListener
                public void onDownloadStart() {
                    httpCallback.onStart();
                }

                @Override // com.jingdong.jdreact.plugin.network.OKHttpUtil.DownloadListener
                public void onDownloadSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        onDownloadFailed();
                    } else {
                        httpCallback.onEnd(new File(str));
                    }
                }

                @Override // com.jingdong.jdreact.plugin.network.OKHttpUtil.DownloadListener
                public void onDownloading(long j2, long j3) {
                    httpCallback.onProgress((int) j2, (int) j3);
                }
            };
        } else {
            this.callback = new ApiUrl.RequestCallback() { // from class: com.jingdong.jdreact.plugin.network.OKHttpJDReactHttpSetting.2
                @Override // com.jingdong.jdreact.plugin.network.ApiUrl.RequestCallback
                public void onError(Call call, IOException iOException) {
                    httpCallback.onError();
                }

                @Override // com.jingdong.jdreact.plugin.network.ApiUrl.RequestCallback
                public void onSuccess(Call call, String str) {
                    httpCallback.onEnd(OKHttpJDReactHttpSetting.strToJDJSONObject(str));
                }
            };
        }
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setNotifyUser(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setPIN(String str) {
        if (TextUtils.isEmpty(Config.getPIN())) {
            return;
        }
        putJsonParam("loginUser", Config.getPIN());
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setReferer(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setSavePath(JDReactFileGuider jDReactFileGuider) {
        if (jDReactFileGuider != null) {
            this.saveFileName = jDReactFileGuider.getFileName();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setTopPriority(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setType(int i2) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setUrl(String str) {
        this.downloadUrl = str;
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setUseFastJsonParser(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void startToload() {
        String str = this.downloadUrl;
        if (str != null) {
            DownloadHelper.startDownload(str, this.saveFileName, this.downloadListener);
        } else if (NetConfig.isGetMetaDataDisabled() && NetConfig.GET_META_DATA_FUNCTION_ID.equals(this.functionId)) {
            ApiUrl.RequestCallback requestCallback = this.callback;
            if (requestCallback != null) {
                requestCallback.onError(null, null);
            }
        } else {
            new ApiUrl().host(this.host).functionId(this.functionId).loginType(NetConfig.sLoginType).body(this.jdJsonObj.toJSONString()).request(this.callback);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void putJsonParam(String str, Object obj) {
        if (str == null || obj == null) {
            return;
        }
        if (this.jdJsonObj == null) {
            this.jdJsonObj = new JDJSONObject();
        }
        try {
            if (obj instanceof JSONObject) {
                obj = JDJSON.parseObject(obj.toString());
            } else if (obj instanceof JSONArray) {
                obj = JDJSON.parseArray(obj.toString());
            }
        } catch (Exception e2) {
            LogUtil.e("OKHttpJDReactHttpSetting", e2);
        }
        this.jdJsonObj.put(str, obj);
    }
}
