package com.jingdong.common.network;

import com.jingdong.common.utils.HardGuardVerifyTools;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.common.utils.SoftGuardVerifyTools;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public final class WindowPopManager {
    public static final String TAG = "PopWindowManager";
    private JDGetWayQueueTools gatewayQueueTools;
    private HardGuardVerifyTools hardGuardVerifyTools;
    private SoftGuardVerifyTools softGuardVerifyTools;
    private WindowStateListener windowStateListener;

    /* loaded from: classes5.dex */
    public static class Singleton {
        private static WindowPopManager instance = new WindowPopManager();

        private Singleton() {
        }
    }

    /* loaded from: classes5.dex */
    public interface StateChangeListener {
        void onWindowDismiss(WindowType windowType);

        void onWindowShow(WindowType windowType);
    }

    /* loaded from: classes5.dex */
    public static class WindowState {
        public static final int STATE_DISMISS = 4;
        public static final int STATE_INITIAL = 0;
        public static final int STATE_SHOW = 1;
        protected int mState = 0;
        protected StateChangeListener mStateChangeLister;
        protected final WindowType mType;

        public WindowState(WindowType windowType) {
            this.mType = windowType;
        }

        private void notifyStateChange() {
            StateChangeListener stateChangeListener;
            int i2 = this.mState;
            if (i2 != 1) {
                if (i2 == 4 && (stateChangeListener = this.mStateChangeLister) != null) {
                    stateChangeListener.onWindowDismiss(this.mType);
                    return;
                }
                return;
            }
            StateChangeListener stateChangeListener2 = this.mStateChangeLister;
            if (stateChangeListener2 != null) {
                stateChangeListener2.onWindowShow(this.mType);
            }
        }

        public void dismiss() {
            this.mState = 4;
            notifyStateChange();
        }

        public int getState() {
            return this.mState;
        }

        public void setStateChangeLister(StateChangeListener stateChangeListener) {
            this.mStateChangeLister = stateChangeListener;
        }

        public void show() {
            this.mState = 1;
            notifyStateChange();
        }
    }

    /* loaded from: classes5.dex */
    public class WindowStateListener implements StateChangeListener {
        public WindowStateListener() {
            WindowPopManager.this = r1;
        }

        @Override // com.jingdong.common.network.WindowPopManager.StateChangeListener
        public void onWindowDismiss(WindowType windowType) {
            if (windowType == WindowType.TYPE_601) {
                if (OKLog.D) {
                    OKLog.d(WindowPopManager.TAG, "601\u5f39\u7a97\u6d88\u5931");
                }
                WindowPopManager.this.hardGuardVerifyTools.triggerPendingRequest();
            } else if (windowType == WindowType.TYPE_HARD_GUARD_VERIFY) {
                if (OKLog.D) {
                    OKLog.d(WindowPopManager.TAG, "\u5f3a\u9a8c\u8bc1\u5f39\u7a97\u6d88\u5931");
                }
                WindowPopManager.this.gatewayQueueTools.triggerPendingRequest();
            }
        }

        @Override // com.jingdong.common.network.WindowPopManager.StateChangeListener
        public void onWindowShow(WindowType windowType) {
            if (windowType == WindowType.TYPE_601) {
                if (OKLog.D) {
                    OKLog.d(WindowPopManager.TAG, "\u5f39\u8d77\u4e86601\u5f39\u7a97");
                }
            } else if (windowType == WindowType.TYPE_HARD_GUARD_VERIFY && OKLog.D) {
                OKLog.d(WindowPopManager.TAG, "\u5f39\u8d77\u4e86\u5f3a\u9a8c\u8bc1");
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum WindowType {
        TYPE_601,
        TYPE_HARD_GUARD_VERIFY,
        TYPE_SOFT_GUARD_VERIFY
    }

    public static WindowPopManager getInstance() {
        return Singleton.instance;
    }

    public boolean checkAndHandlePopWindow(HttpResponse httpResponse, HttpSetting httpSetting) throws HttpError {
        if (OKLog.D) {
            OKLog.d(TAG, "checkAndHandlePopWindow \u7ebf\u7a0bID\uff1a" + Thread.currentThread().getId());
        }
        boolean z = false;
        if (this.gatewayQueueTools.shouldInterceptRequest(httpResponse)) {
            if (!this.hardGuardVerifyTools.isWindowShowing() && !this.softGuardVerifyTools.isWindowShowing()) {
                z = true;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "\u62e6\u622a\u5230\u7f51\u5173\u6392\u961f\u8bf7\u6c42\uff0c\u662f\u5426\u5f39\u8d77\u7a97\u53e3: " + z);
            }
            if (JDGetWayQueueTools.getInstance().checkAndDoQueue(httpResponse, httpSetting, z)) {
                if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof DefaultEffectHttpListener)) {
                    ((DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                }
                return true;
            }
            HttpError httpError = new HttpError();
            httpError.setHttpResponse(httpResponse);
            httpError.setResponseCode(httpResponse.getStatusCode());
            httpError.setJsonCode(httpResponse.getCode());
            httpError.setErrorCode(601);
            throw httpError;
        } else if (this.hardGuardVerifyTools.shouldInterceptRequest(httpResponse)) {
            boolean z2 = !this.gatewayQueueTools.isWindowShowing();
            if (OKLog.D) {
                OKLog.d(TAG, "\u62e6\u622a\u5230\u795e\u76fe\u5f3a\u9a8c\u8bc1\u8bf7\u6c42\uff0c\u662f\u5426\u5f39\u8d77\u7a97\u53e3: " + z2);
            }
            if (this.hardGuardVerifyTools.checkAndHandleEvent(httpResponse, httpSetting, z2)) {
                if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof DefaultEffectHttpListener)) {
                    ((DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                }
                return true;
            }
            HttpError httpError2 = new HttpError();
            httpError2.setHttpResponse(httpResponse);
            httpError2.setResponseCode(httpResponse.getStatusCode());
            httpError2.setJsonCode(httpResponse.getCode());
            httpError2.setErrorCode(605);
            throw httpError2;
        } else {
            if (this.softGuardVerifyTools.shouldInterceptRequest(httpResponse)) {
                this.softGuardVerifyTools.checkAndHandleEvent(httpResponse, httpSetting, !JDGetWayQueueTools.getInstance().isWindowShowing());
            }
            return false;
        }
    }

    public boolean shouldInterceptResponse(HttpResponse httpResponse) {
        return this.gatewayQueueTools.shouldInterceptRequest(httpResponse) || this.hardGuardVerifyTools.shouldInterceptRequest(httpResponse) || this.softGuardVerifyTools.shouldInterceptRequest(httpResponse);
    }

    private WindowPopManager() {
        this.windowStateListener = new WindowStateListener();
        this.softGuardVerifyTools = new SoftGuardVerifyTools();
        HardGuardVerifyTools hardGuardVerifyTools = new HardGuardVerifyTools();
        this.hardGuardVerifyTools = hardGuardVerifyTools;
        hardGuardVerifyTools.setWindowStateChangeListener(this.windowStateListener);
        JDGetWayQueueTools jDGetWayQueueTools = JDGetWayQueueTools.getInstance();
        this.gatewayQueueTools = jDGetWayQueueTools;
        jDGetWayQueueTools.setWindowStateChangeListener(this.windowStateListener);
    }
}
