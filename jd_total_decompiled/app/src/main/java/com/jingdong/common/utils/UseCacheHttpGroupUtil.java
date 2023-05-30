package com.jingdong.common.utils;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.common.BaseApplication;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class UseCacheHttpGroupUtil implements HttpGroup.OnCommonListener {
    private static final String MORE_PARAMES_RESPONSE_MD5 = "responseMd5";
    private static final String TAG = "UseCacheHttpGroupUtil";
    private long cacheTime;
    private String functionID;
    private HttpGroup group;
    private String host;
    private String md5Cachekey;
    private HttpGroup.OnCommonListener onAllListener;
    private String param;
    private String responseMd5;
    private boolean isOnlyNetData = false;
    private int interval = 0;
    private boolean isNetBack = false;
    private boolean isOnlyCache = false;
    private boolean isUseLocalCookie = false;
    private boolean isPost = true;
    private boolean needError = false;
    private boolean isNotifyUser = false;
    private int errorDialogType = 0;
    private int bussinessId = -1;
    private ViewGroup loadingContainer = null;
    private boolean isProduct = false;
    private boolean useFastJSON = false;
    private boolean hasStop = false;

    /* loaded from: classes6.dex */
    public interface UseCacheListener extends HttpGroup.OnCommonListener {
        void onEndWithoutUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndInside(HttpResponse httpResponse) {
        if (this.onAllListener != null) {
            if (OKLog.D) {
                OKLog.d(TAG, " onEndInside --->before responseMd5 : " + this.responseMd5);
            }
            if (this.onAllListener instanceof UseCacheListener) {
                this.responseMd5 = Md5Encrypt.md5(httpResponse.getString());
                Object obj = httpResponse.getMoreParams().get(MORE_PARAMES_RESPONSE_MD5);
                if (OKLog.D) {
                    OKLog.i(TAG, " onEndInside ---> getJSONObject :  " + httpResponse.getString());
                    OKLog.i(TAG, " onEndInside ---> responseMd5 :  " + this.responseMd5);
                    OKLog.i(TAG, " onEndInside ---> lastResponseMd5 :  " + obj);
                }
                if (obj != null && TextUtils.equals(this.responseMd5, (String) obj)) {
                    if (OKLog.D) {
                        OKLog.i(TAG, " onEndInside ---> equals in ");
                    }
                    ((UseCacheListener) this.onAllListener).onEndWithoutUpdate();
                    return;
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, " onEndInside --->after responseMd5 : " + this.responseMd5);
                OKLog.d(TAG, " onEndInside --->after onAllListener : " + this.onAllListener);
            }
            this.onAllListener.onEnd(httpResponse);
        }
    }

    private boolean reLoadData() {
        if (this.isOnlyNetData || this.isOnlyCache) {
            return false;
        }
        this.isOnlyNetData = true;
        addUseCache(this.group, this.functionID, this.param, this.isPost, this.useFastJSON, this.onAllListener);
        return true;
    }

    public HttpSetting addUseCache(HttpGroup httpGroup, String str, String str2, HttpGroup.OnCommonListener onCommonListener) {
        return addUseCache(httpGroup, str, str2, true, onCommonListener);
    }

    public int getBussinessId() {
        return this.bussinessId;
    }

    public long getCacheTime() {
        return this.cacheTime;
    }

    public int getErrorDialogType() {
        return this.errorDialogType;
    }

    public int getInterval() {
        return this.interval;
    }

    public String getMd5Cachekey() {
        return this.md5Cachekey;
    }

    public boolean isNetBack() {
        return this.isNetBack;
    }

    public boolean isNotifyUser() {
        return this.isNotifyUser;
    }

    public boolean isOnlyNetData() {
        return this.isOnlyNetData;
    }

    protected void onCacheError() {
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(final HttpResponse httpResponse) {
        if (httpResponse.isCache()) {
            if (this.isProduct) {
                this.isNetBack = false;
            }
            reLoadData();
            BaseApplication.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.utils.UseCacheHttpGroupUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    if (UseCacheHttpGroupUtil.this.isNetBack || UseCacheHttpGroupUtil.this.hasStop) {
                        return;
                    }
                    UseCacheHttpGroupUtil.this.onEndInside(httpResponse);
                }
            }, this.interval);
            return;
        }
        this.isNetBack = true;
        if (this.hasStop) {
            return;
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
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        HttpGroup.OnCommonListener onCommonListener = this.onAllListener;
        if (onCommonListener != null) {
            onCommonListener.onReady(httpSettingParams);
        }
    }

    public void onStart() {
        this.hasStop = false;
    }

    public void onStop() {
        this.hasStop = true;
    }

    public void setBussinessId(int i2) {
        this.bussinessId = i2;
    }

    public void setCacheTime(long j2) {
        this.cacheTime = j2;
    }

    public void setErrorDialogType(int i2) {
        this.errorDialogType = i2;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setInterval(int i2) {
        this.interval = i2;
    }

    public void setIsProduct(boolean z) {
        this.isProduct = z;
    }

    public void setLoadingContainer(ViewGroup viewGroup) {
        this.loadingContainer = viewGroup;
    }

    public void setMd5Cachekey(String str) {
        this.md5Cachekey = str;
    }

    public void setNeedError(boolean z) {
        this.needError = z;
    }

    public void setNotifyUser(boolean z) {
        this.isNotifyUser = z;
    }

    public void setOnlyNetData(boolean z) {
        this.isOnlyNetData = z;
    }

    public void setOnlyUseCache(boolean z) {
        this.isOnlyCache = z;
    }

    public void setResponseMd5(String str) {
        this.responseMd5 = str;
    }

    public void setUseLocalCookie(boolean z) {
        this.isUseLocalCookie = z;
    }

    public HttpSetting addUseCache(HttpGroup httpGroup, String str, String str2, boolean z, HttpGroup.OnCommonListener onCommonListener) {
        return addUseCache(httpGroup, str, str2, z, false, onCommonListener);
    }

    public HttpSetting addUseCache(HttpGroup httpGroup, String str, String str2, boolean z, boolean z2, HttpGroup.OnCommonListener onCommonListener) {
        this.group = httpGroup;
        this.functionID = str;
        this.onAllListener = onCommonListener;
        this.isPost = z;
        this.useFastJSON = z2;
        HttpSetting httpSetting = new HttpSetting();
        if (z) {
            httpSetting.setFunctionId(str);
            if (!TextUtils.isEmpty(str2)) {
                httpSetting.setJsonParams(JsonParser.parseParamsJsonFromString(str2));
            }
            if (!TextUtils.isEmpty(this.host)) {
                httpSetting.setHost(this.host);
            }
        } else {
            httpSetting.setPost(false);
            httpSetting.setFinalUrl(str);
            httpSetting.setUrl(str);
        }
        this.param = str2;
        if (z2) {
            httpSetting.setUseFastJsonParser(true);
        }
        if (!TextUtils.isEmpty(getMd5Cachekey())) {
            httpSetting.setMd5(getMd5Cachekey());
        }
        httpSetting.setListener(this);
        if (!this.isProduct) {
            httpSetting.setEffect(0);
        } else {
            ViewGroup viewGroup = this.loadingContainer;
            if (viewGroup != null) {
                httpSetting.setProgressBarRootLayout(viewGroup);
            }
        }
        httpSetting.setNotifyUser(this.isNotifyUser);
        if (getBussinessId() == -1) {
            if (getCacheTime() == 0) {
                httpSetting.setForeverCache(true);
            } else {
                httpSetting.setLocalFileCacheTime(getCacheTime());
            }
        } else {
            if (OKLog.D) {
                OKLog.d(TAG, " -->>set cache time  getCacheTime() : " + getCacheTime());
            }
            httpSetting.setBussinessId(getBussinessId());
            httpSetting.setLocalFileCacheTime(getCacheTime());
        }
        httpSetting.setLocalFileCache(true);
        httpSetting.setUseLocalCookies(this.isUseLocalCookie);
        CommonBase.handleHomeConnectReadTimeByNetType(httpSetting);
        if (this.isOnlyNetData && !this.isOnlyCache) {
            httpSetting.setCacheMode(2);
        } else {
            httpSetting.setCacheMode(0);
        }
        httpSetting.setNeedGlobalInitialization(false);
        if (OKLog.D) {
            OKLog.i(TAG, " addUseCache ---> responseMd5 :  " + this.responseMd5);
        }
        if (!TextUtils.isEmpty(this.responseMd5)) {
            HashMap hashMap = new HashMap();
            hashMap.put(MORE_PARAMES_RESPONSE_MD5, this.responseMd5);
            httpSetting.setMoreParams(hashMap);
        }
        httpGroup.add(httpSetting);
        return httpSetting;
    }
}
