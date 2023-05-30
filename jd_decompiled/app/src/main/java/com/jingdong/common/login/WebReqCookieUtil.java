package com.jingdong.common.login;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.sdk.oklog.OKLog;
import jd.wjweblogin.common.listener.WJReqWebCookieCallBack;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;

/* loaded from: classes.dex */
public class WebReqCookieUtil {
    private static final String TAG = "WJWebLogin.WebReqCookieUtil";
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static PreGetWebCookieListener preGetWebCookieListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class BackWatcher implements BackForegroundWatcher.BackForegroundListener {
        private BackWatcher() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
            if (OKLog.D) {
                OKLog.d(WebReqCookieUtil.TAG, "registerBackToForeground onBackToForeground begin reqWebCookie");
            }
            WebReqCookieUtil.reqWebCookie(2);
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
        }
    }

    /* loaded from: classes5.dex */
    public interface PreGetWebCookieListener {
        void onFail();

        void onSuccess();

        void onWithinTheValidity();
    }

    public static void clearWebCookie() {
        WebLoginUtil.getWJLoginHelper().clearLocalWebLoginStatus();
    }

    public static void initWebCookie() {
        if (OKLog.D) {
            OKLog.d(TAG, "initWebCookie");
        }
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            registerBackToForeground();
            reWebCookieLogin(1);
        }
    }

    public static void reWebCookieLogin(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "reWebCookieLogin");
        }
        reqWebCookie(i2);
    }

    public static void registerBackToForeground() {
        if (OKLog.D) {
            OKLog.d(TAG, "registerBackToForeground");
        }
        BackForegroundWatcher.getInstance().registerListener(new BackWatcher());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reqWebCookie(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, " begin reqWebCookie sceneid=" + i2);
        }
        int refreshWebCookie = WebLoginUtil.getWJLoginHelper().refreshWebCookie(i2, new WJReqWebCookieCallBack() { // from class: com.jingdong.common.login.WebReqCookieUtil.1
            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onFail(WJFailResult wJFailResult) {
                if (OKLog.D) {
                    OKLog.d(WebReqCookieUtil.TAG, "reqWebLogin onFail" + wJFailResult.getErrorCode() + wJFailResult.getErrorMessage() + wJFailResult.getHttpCode());
                }
                if (WebReqCookieUtil.preGetWebCookieListener != null) {
                    WebReqCookieUtil.preGetWebCookieListener.onFail();
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onHttpTaskError(HttpError httpError) {
                if (OKLog.D) {
                    OKLog.d(WebReqCookieUtil.TAG, "reqWebLogin onHttpTaskError" + httpError.getErrorCode() + httpError.getMessage());
                }
                if (WebReqCookieUtil.preGetWebCookieListener != null) {
                    WebReqCookieUtil.preGetWebCookieListener.onFail();
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onLocalError(WJErrorResult wJErrorResult) {
                if (OKLog.D) {
                    OKLog.d(WebReqCookieUtil.TAG, "reqWebLogin onLocalError" + wJErrorResult.getErrorCode() + wJErrorResult.getErrorMsg());
                }
                if (WebReqCookieUtil.preGetWebCookieListener != null) {
                    WebReqCookieUtil.preGetWebCookieListener.onFail();
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onSuccess(String str) {
                if (OKLog.D) {
                    OKLog.d(WebReqCookieUtil.TAG, "reqWebLogin onSuccess url=" + str);
                }
                if (WebReqCookieUtil.preGetWebCookieListener != null) {
                    WebReqCookieUtil.preGetWebCookieListener.onSuccess();
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onWithinTheValidity(String str) {
                if (OKLog.D) {
                    OKLog.d(WebReqCookieUtil.TAG, "reqWebLogin onWithinTheValidity url =" + str);
                }
                if (WebReqCookieUtil.preGetWebCookieListener != null) {
                    WebReqCookieUtil.preGetWebCookieListener.onWithinTheValidity();
                }
            }
        });
        if (refreshWebCookie == 1) {
            WebLoginUtil.isRefreshWebCookieed = true;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "ptlogin\u65b9\u6cd5 \u8fd4\u56de\u503c requested =" + refreshWebCookie + "  WebLoginUtil.isRefreshWebCookieed =" + WebLoginUtil.isRefreshWebCookieed);
        }
    }

    public static void setPreGetWebCookieListener(PreGetWebCookieListener preGetWebCookieListener2) {
        preGetWebCookieListener = preGetWebCookieListener2;
    }

    public static void clearWebCookie(int i2) {
        WebLoginUtil.getWJLoginHelper().clearLocalWebLoginStatus(i2);
    }
}
