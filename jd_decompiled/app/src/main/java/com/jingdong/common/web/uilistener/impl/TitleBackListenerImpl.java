package com.jingdong.common.web.uilistener.impl;

import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.pay.CashDeskConfig;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.common.utils.pay.DialogListener;
import com.jingdong.common.utils.pay.DialogUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.TitleBackListener;
import com.jingdong.common.web.uilistener.WebBackOrCloseListener;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes12.dex */
public class TitleBackListenerImpl extends BaseWebComponent implements TitleBackListener {
    private final String TAG;
    public DialogListener dialogListener;
    private DialogUtils dialogUtils;
    private JsBridgeEntity jsBridgeEntity;
    private WebJavaScript webJavaScript;

    public TitleBackListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = TitleBackListenerImpl.class.getSimpleName();
        this.dialogListener = new DialogListener() { // from class: com.jingdong.common.web.uilistener.impl.TitleBackListenerImpl.1
            @Override // com.jingdong.common.utils.pay.DialogListener
            public void doCancel() {
                TitleBackListenerImpl.this.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_PAY_BACKCANCEL);
                if (TitleBackListenerImpl.this.dialogUtils != null) {
                    TitleBackListenerImpl.this.dialogUtils.dismiss();
                }
            }

            @Override // com.jingdong.common.utils.pay.DialogListener
            public void doConfirm(CashDeskConfig cashDeskConfig) {
                try {
                    TitleBackListenerImpl.this.dialogUtils.dismiss();
                    TitleBackListenerImpl.this.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_PAY_BACKCONFIRM);
                    if (((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.getJdWebView() != null && ((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.getJdWebView().getWebView() != null) {
                        if (((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.getJdWebView().getWebView().canGoBack()) {
                            TitleBackListenerImpl.this.onWebBack();
                            ((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.getJdWebView().getWebView().goBack();
                        } else {
                            TitleBackListenerImpl.this.onWebClose();
                            ((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.finishUi();
                        }
                    }
                } catch (Exception unused) {
                    TitleBackListenerImpl.this.onWebClose();
                    ((BaseWebComponent) TitleBackListenerImpl.this).webUiBinder.finishUi();
                }
            }
        };
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
        this.webJavaScript = (WebJavaScript) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0176, code lost:
        if (r0.equals(com.jingdong.common.web.entity.WebEntity.VALUE_ONEKEYLOGIN_ANDROIDRETURN) != false) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean baseBack() {
        /*
            Method dump skipped, instructions count: 690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.uilistener.impl.TitleBackListenerImpl.baseBack():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWebBack() {
        try {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            JDWebView jdWebView = iWebUiBinder != null ? iWebUiBinder.getJdWebView() : null;
            WebBackOrCloseListener webBackListener = jdWebView != null ? jdWebView.getWebBackListener() : null;
            if (webBackListener == null) {
                return;
            }
            webBackListener.onWebBack();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWebClose() {
        try {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            JDWebView jdWebView = iWebUiBinder != null ? iWebUiBinder.getJdWebView() : null;
            WebBackOrCloseListener webBackListener = jdWebView != null ? jdWebView.getWebBackListener() : null;
            if (webBackListener == null) {
                return;
            }
            webBackListener.onWebClose();
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.web.uilistener.TitleBackListener
    public boolean keyBack() {
        if (WebViewHelper.onKeyBackControl(this.webUiBinder)) {
            return true;
        }
        return baseBack();
    }

    public void onClickEvent(String str) {
        if (Log.D) {
            Log.d(this.TAG, "onClickEvent clickId-->> " + str);
        }
        try {
            JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), str, getClass().getSimpleName());
        } catch (NullPointerException unused) {
            Log.e("JDFragment", "NullPointerException : onClickEvent getActivity is null");
        } catch (Throwable unused2) {
            Log.e("JDFragment", "onClickEvent call JDMtaUtils.onClick() error");
        }
    }

    public void showBackDialog() {
        onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_PAYGETBACK);
        WebJavaScript webJavaScript = this.webJavaScript;
        String dialogTips = webJavaScript != null ? webJavaScript.getDialogTips() : "";
        if (this.dialogUtils == null) {
            this.dialogUtils = new DialogUtils(this.webUiBinder.getBaseActivity(), dialogTips, this.dialogListener);
        }
        this.dialogUtils.showDialogWithMessage(dialogTips);
    }

    @Override // com.jingdong.common.web.uilistener.TitleBackListener
    public boolean titleBack() {
        if (WebViewHelper.onTitleNaviBackControl(this.webUiBinder)) {
            return true;
        }
        return baseBack();
    }
}
