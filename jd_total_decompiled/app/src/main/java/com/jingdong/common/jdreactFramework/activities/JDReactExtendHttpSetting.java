package com.jingdong.common.jdreactFramework.activities;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.download.JDReactFileGuider;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactExtendHttpSetting extends JDReactHttpSetting.AbstractJDReactHttpSetting {
    HttpSetting mHttpSetting = new HttpSetting();

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void download() {
        this.mHttpSetting.setIncompatibleWithOkHttp(true);
        this.mHttpSetting.setConnectTimeout(20000);
        this.mHttpSetting.setReadTimeout(R2.styleable.TextStyle_android_hyphenationFrequency);
        HttpGroupUtils.getHttpGroupaAsynPool().execute(this.mHttpSetting);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void putJsonParam(String str, String str2) {
        this.mHttpSetting.putJsonParam(str, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setA2(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setAttempts(int i2) {
        this.mHttpSetting.setAttempts(i2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setCacheMode(int i2) {
        this.mHttpSetting.setCacheMode(i2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setEffect(int i2) {
        this.mHttpSetting.setEffect(i2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setFunctionId(String str) {
        this.mHttpSetting.setFunctionId(str);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setHost(String str) {
        this.mHttpSetting.setHost(str);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setListener(final JDReactHttpSetting.HttpCallback httpCallback) {
        this.mHttpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHttpSetting.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                File saveFile = httpResponse.getSaveFile();
                if (saveFile != null && !TextUtils.isEmpty(saveFile.getAbsolutePath())) {
                    httpCallback.onEnd(saveFile);
                } else {
                    httpCallback.onEnd(httpResponse.getFastJsonObject());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                httpCallback.onError();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
                httpCallback.onPause();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
                httpCallback.onProgress(i2, i3);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                httpCallback.onStart();
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setNotifyUser(boolean z) {
        this.mHttpSetting.setNotifyUser(z);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setPIN(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setReferer(String str) {
        this.mHttpSetting.setReferer(str);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setSavePath(JDReactFileGuider jDReactFileGuider) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(jDReactFileGuider.getSpace());
        fileGuider.setImmutable(jDReactFileGuider.isImmutable());
        fileGuider.setFileName(jDReactFileGuider.getFileName());
        this.mHttpSetting.setSavePath(fileGuider);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setTopPriority(boolean z) {
        this.mHttpSetting.setTopPriority(z);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setType(int i2) {
        this.mHttpSetting.setType(i2);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setUrl(String str) {
        this.mHttpSetting.setUrl(str);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void setUseFastJsonParser(boolean z) {
        this.mHttpSetting.setUseFastJsonParser(z);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void startToload() {
        HttpGroupUtils.getHttpGroupaAsynPool().add(this.mHttpSetting);
    }

    @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.AbstractJDReactHttpSetting
    public void putJsonParam(String str, Object obj) {
        this.mHttpSetting.putJsonParam(str, obj);
    }
}
