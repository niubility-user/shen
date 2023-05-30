package com.jingdong.common.unification.watermark;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkConfig;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class UnWatermarkBusinessHelper {
    private static final String FUNCTION_ID = "get_marketmerchant_info";
    public static final String SP_WATER_MARK = "un_wark_mark_request_result";
    private static UnWatermarkBusinessHelper helper;
    public OnWatermarkResumeListener mainFrameListener;
    private Map<String, OnWatermarkResumeListener> resumeListeners = new HashMap();
    private Boolean lastSwitch = null;
    private Handler handler = new Handler(Looper.getMainLooper());

    private UnWatermarkBusinessHelper() {
        JDMobileConfig.getInstance().registerListener(new JDMoblieConfigListener() { // from class: com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper.1
            @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
            public void onConfigUpdate() {
                if (UnWatermarkBusinessHelper.this.lastSwitch == null || UnWatermarkBusinessHelper.this.lastSwitch.booleanValue() != UnWatermarkBusinessHelper.this.requestSwitch()) {
                    UnWatermarkBusinessHelper unWatermarkBusinessHelper = UnWatermarkBusinessHelper.this;
                    unWatermarkBusinessHelper.lastSwitch = Boolean.valueOf(unWatermarkBusinessHelper.requestSwitch());
                    if (UnWatermarkBusinessHelper.this.lastSwitch.booleanValue()) {
                        UnWatermarkBusinessHelper.this.request(null);
                        return;
                    }
                    WatermarkConfig.getConfig().setCanAdd(false);
                    UnWatermarkBusinessHelper.this.optResumeList();
                }
            }
        });
    }

    public static UnWatermarkBusinessHelper getInstance() {
        UnWatermarkBusinessHelper unWatermarkBusinessHelper;
        UnWatermarkBusinessHelper unWatermarkBusinessHelper2 = helper;
        if (unWatermarkBusinessHelper2 != null) {
            return unWatermarkBusinessHelper2;
        }
        synchronized (UnWatermarkBusinessHelper.class) {
            if (helper == null) {
                helper = new UnWatermarkBusinessHelper();
            }
            unWatermarkBusinessHelper = helper;
        }
        return unWatermarkBusinessHelper;
    }

    public synchronized void addResumeListener(String str, OnWatermarkResumeListener onWatermarkResumeListener) {
        if (onWatermarkResumeListener != null) {
            this.resumeListeners.put(str, onWatermarkResumeListener);
        }
    }

    public boolean isJiaDianUser() {
        return UnSharedPreferencesUtils.getBoolean(a.g().d(), SP_WATER_MARK, false);
    }

    public synchronized void optResumeList() {
        if (this.resumeListeners.size() <= 0) {
            return;
        }
        this.handler.post(new Runnable() { // from class: com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = UnWatermarkBusinessHelper.this.resumeListeners.entrySet().iterator();
                while (it.hasNext()) {
                    ((OnWatermarkResumeListener) ((Map.Entry) it.next()).getValue()).resume();
                }
            }
        });
    }

    public synchronized void removeResumeListener(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.resumeListeners.remove(str);
        }
    }

    public synchronized void request(OnWatermarkListener onWatermarkListener) {
        request(a.g().h().intValue(), a.g().b(), a.g().c(), onWatermarkListener);
    }

    public boolean requestSwitch() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "watermark", JshopConst.JSKEY_CATE_OPEN);
        if (TextUtils.isEmpty(config)) {
            return true;
        }
        return TextUtils.equals("1", config);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x008b, code lost:
        r8.onResult(false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void request(int i2, String str, String str2, final OnWatermarkListener onWatermarkListener) {
        if (requestSwitch() && a.g().r()) {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId(FUNCTION_ID);
            httpSetting.setCacheMode(2);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setAttempts(0);
            httpSetting.putJsonParam("loginType", 4);
            httpSetting.putJsonParam("source", str);
            httpSetting.putJsonParam("requestId", System.currentTimeMillis() + "");
            httpSetting.putJsonParam("appId", Integer.valueOf(i2));
            httpSetting.putJsonParam("version", str2);
            if (!TextUtils.isEmpty(a.g().k())) {
                httpSetting.setHost(a.g().k());
            }
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper.3
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    JDJSONObject jSONObject;
                    JDJSONObject jSONObject2;
                    if (httpResponse == null || httpResponse == null) {
                        return;
                    }
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject != null && (jSONObject = fastJsonObject.getJSONObject("data")) != null && (jSONObject2 = jSONObject.getJSONObject("marketMerchantInfo")) != null) {
                        boolean optBoolean = jSONObject2.optBoolean("isJiaDianUser");
                        WatermarkConfig.getConfig().setCanAdd(optBoolean);
                        UnSharedPreferencesUtils.putBoolean(a.g().d(), UnWatermarkBusinessHelper.SP_WATER_MARK, optBoolean);
                        OnWatermarkListener onWatermarkListener2 = onWatermarkListener;
                        if (onWatermarkListener2 != null) {
                            onWatermarkListener2.onResult(optBoolean);
                        } else {
                            OnWatermarkResumeListener onWatermarkResumeListener = UnWatermarkBusinessHelper.this.mainFrameListener;
                            if (onWatermarkResumeListener != null) {
                                onWatermarkResumeListener.resume();
                            }
                        }
                        UnWatermarkBusinessHelper.this.optResumeList();
                        return;
                    }
                    OnWatermarkListener onWatermarkListener3 = onWatermarkListener;
                    if (onWatermarkListener3 != null) {
                        onWatermarkListener3.onResult(false);
                    } else {
                        OnWatermarkResumeListener onWatermarkResumeListener2 = UnWatermarkBusinessHelper.this.mainFrameListener;
                        if (onWatermarkResumeListener2 != null) {
                            onWatermarkResumeListener2.resume();
                        }
                    }
                    WatermarkConfig.getConfig().setCanAdd(false);
                    UnSharedPreferencesUtils.putBoolean(a.g().d(), UnWatermarkBusinessHelper.SP_WATER_MARK, false);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    UnSharedPreferencesUtils.putBoolean(a.g().d(), UnWatermarkBusinessHelper.SP_WATER_MARK, false);
                    WatermarkConfig.getConfig().setCanAdd(false);
                    UnWatermarkBusinessHelper.this.optResumeList();
                    OnWatermarkListener onWatermarkListener2 = onWatermarkListener;
                    if (onWatermarkListener2 != null) {
                        onWatermarkListener2.onResult(false);
                        return;
                    }
                    OnWatermarkResumeListener onWatermarkResumeListener = UnWatermarkBusinessHelper.this.mainFrameListener;
                    if (onWatermarkResumeListener != null) {
                        onWatermarkResumeListener.resume();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i3, int i4) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }
}
