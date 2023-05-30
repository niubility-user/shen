package com.jingdong.common.unification.watermark;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkConfig;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.jdsdk.constant.JshopConst;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void request(int r5, java.lang.String r6, java.lang.String r7, final com.jingdong.common.unification.watermark.OnWatermarkListener r8) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.requestSwitch()     // Catch: java.lang.Throwable -> L90
            r1 = 0
            if (r0 == 0) goto L89
            com.jd.lib.un.business.widget.a r0 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Throwable -> L90
            boolean r0 = r0.r()     // Catch: java.lang.Throwable -> L90
            if (r0 != 0) goto L13
            goto L89
        L13:
            com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting     // Catch: java.lang.Throwable -> L90
            r0.<init>()     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = "get_marketmerchant_info"
            r0.setFunctionId(r2)     // Catch: java.lang.Throwable -> L90
            r2 = 2
            r0.setCacheMode(r2)     // Catch: java.lang.Throwable -> L90
            r2 = 1
            r0.setUseFastJsonParser(r2)     // Catch: java.lang.Throwable -> L90
            r0.setAttempts(r1)     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = "loginType"
            r2 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L90
            r0.putJsonParam(r1, r2)     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = "source"
            r0.putJsonParam(r1, r6)     // Catch: java.lang.Throwable -> L90
            java.lang.String r6 = "requestId"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90
            r1.<init>()     // Catch: java.lang.Throwable -> L90
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L90
            r1.append(r2)     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = ""
            r1.append(r2)     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L90
            r0.putJsonParam(r6, r1)     // Catch: java.lang.Throwable -> L90
            java.lang.String r6 = "appId"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L90
            r0.putJsonParam(r6, r5)     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = "version"
            r0.putJsonParam(r5, r7)     // Catch: java.lang.Throwable -> L90
            com.jd.lib.un.business.widget.a r5 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = r5.k()     // Catch: java.lang.Throwable -> L90
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L90
            if (r5 != 0) goto L78
            com.jd.lib.un.business.widget.a r5 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Throwable -> L90
            java.lang.String r5 = r5.k()     // Catch: java.lang.Throwable -> L90
            r0.setHost(r5)     // Catch: java.lang.Throwable -> L90
        L78:
            com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper$3 r5 = new com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper$3     // Catch: java.lang.Throwable -> L90
            r5.<init>()     // Catch: java.lang.Throwable -> L90
            r0.setListener(r5)     // Catch: java.lang.Throwable -> L90
            com.jingdong.jdsdk.network.toolbox.HttpGroup r5 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()     // Catch: java.lang.Throwable -> L90
            r5.add(r0)     // Catch: java.lang.Throwable -> L90
            monitor-exit(r4)
            return
        L89:
            if (r8 == 0) goto L8e
            r8.onResult(r1)     // Catch: java.lang.Throwable -> L90
        L8e:
            monitor-exit(r4)
            return
        L90:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper.request(int, java.lang.String, java.lang.String, com.jingdong.common.unification.watermark.OnWatermarkListener):void");
    }
}
