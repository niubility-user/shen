package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.network.WindowPopManager;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class HardGuardVerifyTools extends BaseGuardVerifyTool {
    private static final String TAG = "PopWindowManager-JDHardGuardVerifyTools";
    private QueueItem mCurrentItem;
    private String mPToken;
    private String mRequestId;
    private ArrayList<QueueItem> mQueueItems = new ArrayList<>(1);
    private WindowPopManager.WindowState mWindowState = new WindowPopManager.WindowState(WindowPopManager.WindowType.TYPE_HARD_GUARD_VERIFY);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class QueueItem {
        HttpSetting httpSetting;
        String requestId;
        String responseInfo;

        public QueueItem(HttpSetting httpSetting, String str, String str2) {
            this.httpSetting = httpSetting;
            this.requestId = str;
            this.responseInfo = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            if (this.httpSetting != null) {
                HttpError httpError = new HttpError();
                HttpResponse httpResponse = new HttpResponse(null);
                httpResponse.setString(this.responseInfo);
                httpError.setHttpResponse(httpResponse);
                httpError.setJsonCode(605);
                httpError.setErrorCode(605);
                httpError.setMessage(this.responseInfo);
                this.httpSetting.onError(httpError);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retry() {
            try {
                HttpSetting httpSetting = this.httpSetting;
                if (httpSetting != null) {
                    httpSetting.resetHttpSetting();
                    if (!TextUtils.isEmpty(HardGuardVerifyTools.this.mPToken)) {
                        Map<String, String> headerMap = this.httpSetting.getHeaderMap();
                        if (headerMap == null || headerMap.isEmpty()) {
                            headerMap = new HashMap<>();
                        }
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("rpId", (Object) this.requestId);
                        jDJSONObject.put("evToken", (Object) HardGuardVerifyTools.this.mPToken);
                        headerMap.put("X-Rp-Ext", jDJSONObject.toString());
                        this.httpSetting.setHeaderMap(headerMap);
                    }
                    HttpGroupUtils.getHttpGroupaAsynPool().add(this.httpSetting);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private synchronized void addToQueue(QueueItem queueItem) {
        this.mQueueItems.add(queueItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkResult(String str) {
        JDJSONObject parseObject;
        if (!TextUtils.isEmpty(str) && (parseObject = JDJSON.parseObject(str)) != null) {
            int optInt = parseObject.optInt("code", -1);
            String optString = parseObject.optString("data");
            if (optInt == 0 && !TextUtils.isEmpty(optString)) {
                this.mPToken = optString;
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeAndCancelAllQueue() {
        if (OKLog.D) {
            OKLog.d(TAG, "removeAndCancelAllQueue queueItems.size = " + this.mQueueItems.size());
        }
        if (!this.mQueueItems.isEmpty()) {
            Iterator<QueueItem> it = this.mQueueItems.iterator();
            while (it.hasNext()) {
                QueueItem next = it.next();
                if (next != null) {
                    next.cancel();
                }
            }
            this.mQueueItems.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void retryAllQueue() {
        if (OKLog.D) {
            OKLog.d(TAG, "retryAllQueue queueItems.size = " + this.mQueueItems.size());
        }
        if (!this.mQueueItems.isEmpty()) {
            Iterator<QueueItem> it = this.mQueueItems.iterator();
            while (it.hasNext()) {
                QueueItem next = it.next();
                if (next != null) {
                    next.retry();
                }
            }
            this.mQueueItems.clear();
        }
    }

    @Override // com.jingdong.common.utils.BaseGuardVerifyTool
    public boolean checkAndHandleEvent(HttpResponse httpResponse, HttpSetting httpSetting, boolean z) {
        JDJSONObject jSONObject;
        String str = "";
        if (httpSetting.isUseFastJsonParser()) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject != null && (jSONObject = fastJsonObject.getJSONObject("disposal")) != null) {
                str = jSONObject.optString("rpId");
            }
        } else {
            JSONObjectProxy jSONObject2 = httpResponse.getJSONObject();
            if (jSONObject2 != null) {
                try {
                    JSONObjectProxy jSONObject3 = jSONObject2.getJSONObject("disposal");
                    if (jSONObject3 != null) {
                        str = jSONObject3.optString("rpId");
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        String string = httpResponse.getString();
        if (OKLog.D) {
            OKLog.d(TAG, "\u89e3\u6790\u5230\u8bf7\u6c42 requestId: " + str + ", responseInfo: " + string);
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(string)) {
            return false;
        }
        QueueItem queueItem = new QueueItem(httpSetting, str, string);
        addToQueue(queueItem);
        triggerHardVerifyCheck(queueItem, z);
        return true;
    }

    public synchronized void handlePendingRequest() {
        if (!this.mQueueItems.isEmpty()) {
            this.mCurrentItem = this.mQueueItems.get(0);
            this.mWindowState.show();
            JDHttpTookit.getEngine().getHardGuardVerifyPlugin().triggerGuardVerifyCheck(this.mCurrentItem.responseInfo, new IHardGuardVerifyPlugin.ICheckListener() { // from class: com.jingdong.common.utils.HardGuardVerifyTools.2
                @Override // com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin.ICheckListener
                public void onCheckFinished(String str) {
                    if (OKLog.D) {
                        OKLog.d(HardGuardVerifyTools.TAG, "onCheckFinished result " + str);
                    }
                    if (HardGuardVerifyTools.this.checkResult(str)) {
                        HardGuardVerifyTools.this.retryAllQueue();
                    } else {
                        HardGuardVerifyTools.this.removeAndCancelAllQueue();
                    }
                    HardGuardVerifyTools.this.mCurrentItem = null;
                    HardGuardVerifyTools.this.mPToken = null;
                    HardGuardVerifyTools.this.mWindowState.dismiss();
                }
            });
        }
    }

    public boolean isWindowShowing() {
        return this.mWindowState.getState() == 1;
    }

    public void setWindowStateChangeListener(WindowPopManager.WindowStateListener windowStateListener) {
        this.mWindowState.setStateChangeLister(windowStateListener);
    }

    @Override // com.jingdong.common.utils.BaseGuardVerifyTool
    public boolean shouldInterceptRequest(HttpResponse httpResponse) {
        return httpResponse.getCode() == 605;
    }

    public void triggerHardVerifyCheck(QueueItem queueItem, boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "triggerHardVerifyCheck mCurrentItem " + this.mCurrentItem + ", showDialog: " + z);
        }
        if (this.mCurrentItem == null && z) {
            this.mCurrentItem = queueItem;
            this.mWindowState.show();
            if (OKLog.D) {
                OKLog.d(TAG, "triggerHardVerifyCheck \u8c03\u8d77\u5904\u7f6esdk >>>>>>>>>>>>>>");
            }
            JDHttpTookit.getEngine().getHardGuardVerifyPlugin().triggerGuardVerifyCheck(queueItem.responseInfo, new IHardGuardVerifyPlugin.ICheckListener() { // from class: com.jingdong.common.utils.HardGuardVerifyTools.1
                @Override // com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin.ICheckListener
                public void onCheckFinished(String str) {
                    if (OKLog.D) {
                        OKLog.d(HardGuardVerifyTools.TAG, "triggerHardVerifyCheck onCheckFinished() result: " + str);
                    }
                    try {
                        if (HardGuardVerifyTools.this.checkResult(str)) {
                            HardGuardVerifyTools.this.retryAllQueue();
                        } else {
                            HardGuardVerifyTools.this.removeAndCancelAllQueue();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    HardGuardVerifyTools.this.mCurrentItem = null;
                    HardGuardVerifyTools.this.mPToken = null;
                    HardGuardVerifyTools.this.mWindowState.dismiss();
                }
            });
        }
    }

    public void triggerPendingRequest() {
        if (OKLog.D) {
            OKLog.d(TAG, "triggerPendingRequest");
        }
        handlePendingRequest();
    }
}
