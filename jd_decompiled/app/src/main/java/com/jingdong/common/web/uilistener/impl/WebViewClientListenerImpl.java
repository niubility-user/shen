package com.jingdong.common.web.uilistener.impl;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.R;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.javainterface.impl.ScreenConfig;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.managers.JSVoiceManager;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.common.web.urlcheck.impl.UrlCheckImpl;
import com.jingdong.common.web.util.JdWebviewBlackListUtil;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.common.web.util.MultiMedia;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class WebViewClientListenerImpl extends BaseWebComponent implements WebViewClientListener {
    private final String TAG;
    private JsBridgeEntity jsBridgeEntity;
    private WebJavaScript webJavaScript;

    public WebViewClientListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = WebViewClientListenerImpl.class.getSimpleName();
        if (iWebUiBinder != null) {
            this.webJavaScript = (WebJavaScript) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT);
            if (iWebUiBinder.getWebEntity() != null) {
                this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
            }
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (webView.canGoBack()) {
            this.webUiBinder.getJdWebView().setCloseBtnVisible(true);
        }
        Uri uri = null;
        try {
            uri = Uri.parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (uri != null && !UrlCheckImpl.LOGIN_PATH.equals(uri.getPath()) && !UrlCheckImpl.REGIST_PATH.equals(uri.getPath())) {
            this.webUiBinder.getWebEntity().loginStateSync = false;
        }
        if (webView.getProgress() >= 100) {
            this.webUiBinder.getWebEntity().pageFinished = true;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if ((this.webUiBinder.getUi() instanceof CommonMFragment) && ((CommonMFragment) this.webUiBinder.getUi()).getBug5497WorkAround() != null) {
            ((CommonMFragment) this.webUiBinder.getUi()).getBug5497WorkAround().setSoftInputPopupOnce(false);
        }
        WebJavaScript webJavaScript = this.webJavaScript;
        if (webJavaScript != null) {
            webJavaScript.setImproveUserInformationPageFinished(false);
        }
        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
        if (jsBridgeEntity != null) {
            jsBridgeEntity.canControlBackByWeb = 0;
            jsBridgeEntity.shareInfo = this.webUiBinder.getWebEntity().shareInfoInit.m20clone();
            ShareInfo shareInfo = this.jsBridgeEntity.shareInfo;
            if (shareInfo != null && !TextUtils.isEmpty(shareInfo.getUrl())) {
                this.jsBridgeEntity.isNeedShare = true;
                this.webUiBinder.getJdWebView().setShareBtnState(true, this.jsBridgeEntity.shareInfo.isShareGift());
            } else {
                this.jsBridgeEntity.isNeedShare = false;
                this.webUiBinder.getJdWebView().setShareBtnState(false);
            }
        }
        WebEntity webEntity = this.webUiBinder.getWebEntity();
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        webEntity.webviewLoad_start = currentTimeMillis / 1000.0d;
        this.webUiBinder.getWebEntity().pageFinished = false;
        Boolean valueOf = Boolean.valueOf(!JdWebviewBlackListUtil.needHideRightPopButton(str));
        Log.d(this.TAG, "show right morebutton:  webEntity.isShowMoreBtn=  " + this.webUiBinder.getWebEntity().isShowMoreBtn + "\nJdWebviewBlackListUtil.needHideRightPopButton=  " + valueOf);
        if (this.webUiBinder.getWebEntity().isShowMoreBtn) {
            this.webUiBinder.getJdWebView().setMoreBtnVisible(valueOf.booleanValue());
        }
        this.webUiBinder.getJdWebView().setCanPullRefresh(false);
        if (this.webUiBinder.getBaseActivity() != null) {
            BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
            int i2 = R.id.root_layout;
            if (baseActivity.findViewById(i2) != null) {
                MediaUtils.removeCameraPreView((ViewGroup) this.webUiBinder.getBaseActivity().findViewById(i2));
            }
        }
        JSVoiceManager.getInstance().cancel();
        JDAppUnite jDAppUnite = (JDAppUnite) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE);
        if (jDAppUnite != null) {
            jDAppUnite.downLoadCancel();
        }
        MultiMedia.onCancel();
        IJavaInterface javaInterfaceObj = this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JD_SCREEN_CONFIG);
        if (javaInterfaceObj instanceof ScreenConfig) {
            ((ScreenConfig) javaInterfaceObj).unregisterListener();
        }
    }

    @Override // com.jingdong.common.web.uilistener.WebViewClientListener
    public void onReceivedError(WebView webView, int i2, String str, String str2) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null) {
            return;
        }
        ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), this.webUiBinder.getBaseActivity().getString(R.string.m_error_tip) + "(" + i2 + ")");
        this.webUiBinder.getWebEntity().pageFinished = true;
        StringBuilder sb = new StringBuilder();
        sb.append("webview page load error:");
        sb.append(str);
        ExceptionReporter.reportWebPageError("WebView_Error", sb.toString(), str2, i2 + "");
    }

    @Override // com.jingdong.common.web.uilistener.WebViewClientListener
    public void shouldOverrideUrlLoading(WebView webView, String str) {
    }
}
