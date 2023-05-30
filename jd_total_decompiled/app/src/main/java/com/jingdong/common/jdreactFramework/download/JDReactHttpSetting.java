package com.jingdong.common.jdreactFramework.download;

import com.jd.framework.json.JDJSONObject;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactHttpSetting {
    AbstractJDReactHttpSetting mAbstractJDReactHttpSetting;

    /* loaded from: classes5.dex */
    public static abstract class AbstractJDReactHttpSetting {
        public abstract void download();

        public abstract void putJsonParam(String str, Object obj);

        public abstract void putJsonParam(String str, String str2);

        public abstract void setA2(String str);

        public abstract void setAttempts(int i2);

        public abstract void setCacheMode(int i2);

        public abstract void setEffect(int i2);

        public abstract void setFunctionId(String str);

        public abstract void setHost(String str);

        public abstract void setListener(HttpCallback httpCallback);

        public abstract void setNotifyUser(boolean z);

        public abstract void setPIN(String str);

        public abstract void setReferer(String str);

        public abstract void setSavePath(JDReactFileGuider jDReactFileGuider);

        public abstract void setTopPriority(boolean z);

        public abstract void setType(int i2);

        public abstract void setUrl(String str);

        public abstract void setUseFastJsonParser(boolean z);

        public abstract void startToload();
    }

    /* loaded from: classes5.dex */
    public interface HttpCallback {
        void onEnd(JDJSONObject jDJSONObject);

        void onEnd(File file);

        void onError();

        void onPause();

        void onProgress(int i2, int i3);

        void onStart();
    }

    public void download() {
        this.mAbstractJDReactHttpSetting.download();
    }

    public void putJsonParam(String str, String str2) {
        this.mAbstractJDReactHttpSetting.putJsonParam(str, str2);
    }

    public void setA2(String str) {
        this.mAbstractJDReactHttpSetting.setA2(str);
    }

    public void setAbstractJDReactHttpSetting(AbstractJDReactHttpSetting abstractJDReactHttpSetting) {
        this.mAbstractJDReactHttpSetting = abstractJDReactHttpSetting;
    }

    public void setAttempts(int i2) {
        this.mAbstractJDReactHttpSetting.setAttempts(i2);
    }

    public void setCacheMode(int i2) {
        this.mAbstractJDReactHttpSetting.setCacheMode(i2);
    }

    public void setEffect(int i2) {
        this.mAbstractJDReactHttpSetting.setEffect(i2);
    }

    public void setFunctionId(String str) {
        this.mAbstractJDReactHttpSetting.setFunctionId(str);
    }

    public void setHost(String str) {
        this.mAbstractJDReactHttpSetting.setHost(str);
    }

    public void setListener(HttpCallback httpCallback) {
        this.mAbstractJDReactHttpSetting.setListener(httpCallback);
    }

    public void setNotifyUser(boolean z) {
        this.mAbstractJDReactHttpSetting.setNotifyUser(z);
    }

    public void setPIN(String str) {
        this.mAbstractJDReactHttpSetting.setPIN(str);
    }

    public void setReferer(String str) {
        this.mAbstractJDReactHttpSetting.setReferer(str);
    }

    public void setSavePath(JDReactFileGuider jDReactFileGuider) {
        this.mAbstractJDReactHttpSetting.setSavePath(jDReactFileGuider);
    }

    public void setTopPriority(boolean z) {
        this.mAbstractJDReactHttpSetting.setTopPriority(z);
    }

    public void setType(int i2) {
        this.mAbstractJDReactHttpSetting.setType(i2);
    }

    public void setUrl(String str) {
        this.mAbstractJDReactHttpSetting.setUrl(str);
    }

    public void setUseFastJsonParser(boolean z) {
        this.mAbstractJDReactHttpSetting.setUseFastJsonParser(z);
    }

    public void startToload() {
        this.mAbstractJDReactHttpSetting.startToload();
    }

    public void putJsonParam(String str, Object obj) {
        this.mAbstractJDReactHttpSetting.putJsonParam(str, obj);
    }
}
