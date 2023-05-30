package com.jingdong.common.web.uilistener.impl;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.pay.CashDeskConfig;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.common.utils.pay.DialogListener;
import com.jingdong.common.utils.pay.DialogUtils;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.TitleBackListener;
import com.jingdong.common.web.uilistener.WebBackOrCloseListener;
import com.jingdong.common.web.urlcheck.impl.PayCheckImpl;
import com.jingdong.common.web.util.WebUtils;
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
    */
    private boolean baseBack() {
        Uri parse;
        WebJavaScript webJavaScript;
        if (this.webUiBinder.getWebEntity().isFromGame) {
            if (Log.D) {
                Log.d(this.TAG, "onKeyDown keyCode game finish-->> ");
            }
            onWebClose();
            this.webUiBinder.finishUi();
        } else if (this.webUiBinder.getWebEntity().isCashierDesk && JumpUtils.CASHIERDESK_HOME.equals(this.webJavaScript.getPageIndex()) && (webJavaScript = this.webJavaScript) != null && webJavaScript.getCashDeskConfig() != null && 1 == this.webJavaScript.getCashDeskConfig().getDialogSwitch()) {
            showBackDialog();
        } else {
            WebJavaScript webJavaScript2 = this.webJavaScript;
            if (webJavaScript2 != null && webJavaScript2.getPayCompleted()) {
                PayCheckImpl payCheckImpl = (PayCheckImpl) this.webUiBinder.getWebViewUrlInterceptor().getUrlCheck(WebUiConstans.UrlCheckKeys.CHECK_PAY);
                if (payCheckImpl != null) {
                    payCheckImpl.sendBroadcastToPhoneCharge();
                }
                onWebClose();
                this.webUiBinder.finishUi();
            } else {
                WebJavaScript webJavaScript3 = this.webJavaScript;
                if (webJavaScript3 != null && webJavaScript3.isImproveUserInformationPageFinished()) {
                    this.webJavaScript.setImproveUserInformationPageFinished(false);
                    onWebClose();
                    this.webUiBinder.finishUi();
                } else if (JumpUtil.VAULE_DES_JDTHIRDLOGIN.equals(this.webUiBinder.getWebEntity().des)) {
                    if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().browserlogin_fromurl)) {
                        Uri parse2 = Uri.parse(this.webUiBinder.getWebEntity().browserlogin_fromurl);
                        if (!TextUtils.isEmpty(parse2.getScheme())) {
                            this.webUiBinder.getWebEntity();
                            if (WebEntity.DEFAULTBROWSER_SCHEME.equals(parse2.getScheme())) {
                                String str = this.webUiBinder.getWebEntity().browserlogin_fromurl;
                                String[] split = this.webUiBinder.getWebEntity().browserlogin_fromurl.split("browserlogin_returnurl=");
                                if (split != null && split.length > 1) {
                                    str = split[1];
                                }
                                Uri parse3 = Uri.parse(str);
                                if (parse3 != null) {
                                    String str2 = this.webUiBinder.getWebEntity().onekeylogin;
                                    this.webUiBinder.getWebEntity();
                                    if (TextUtils.equals(str2, WebEntity.VALUE_ONEKEYLOGIN_ANDROIDRETURN)) {
                                        WebUtils.oneKeyLoginKelper(this.webUiBinder.getBaseActivity());
                                        return true;
                                    }
                                    CommonBase.toBrowser(parse3);
                                    onWebClose();
                                    this.webUiBinder.finishUi();
                                    return true;
                                }
                            }
                        }
                    }
                    JDMtaUtils.onClickWithPageId(BaseApplication.getInstance(), "NewLogin_AutoLoginBack", this.TAG, "NewLogin_AuthorizeLogin");
                    if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().returnUrl)) {
                        if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().onekeylogin)) {
                            String str3 = this.webUiBinder.getWebEntity().onekeylogin;
                            this.webUiBinder.getWebEntity();
                            if (!str3.equals(WebEntity.VALUE_ONEKEYLOGIN_KEPLER)) {
                                String str4 = this.webUiBinder.getWebEntity().onekeylogin;
                                this.webUiBinder.getWebEntity();
                            }
                            onWebClose();
                            WebUtils.oneKeyLoginKelper(this.webUiBinder.getBaseActivity());
                            return true;
                        }
                        onWebClose();
                        WebUtils.returnThirdApp(this.webUiBinder.getBaseActivity(), Uri.parse(this.webUiBinder.getWebEntity().returnUrl));
                    }
                } else if (this.webUiBinder.getWebEntity().isThirdPay && !this.webUiBinder.getWebEntity().thirdPayStatus) {
                    Uri parse4 = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey={\"payStatus\":\"JDP_PAY_CANCEL\"}");
                    Intent intent = new Intent();
                    intent.addFlags(67108864);
                    intent.setData(parse4);
                    try {
                        this.webUiBinder.startActivity(intent);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.webUiBinder.getWebEntity().thirdPayStatus = true;
                    onWebClose();
                    this.webUiBinder.finishUi();
                } else {
                    if (this.webUiBinder.getWebEntity().isMetroPay) {
                        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
                        if (jsBridgeEntity.canReturnThirdApp) {
                            if (!TextUtils.isEmpty(jsBridgeEntity.metroPayData)) {
                                parse = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey=" + this.jsBridgeEntity.metroPayData);
                            } else {
                                parse = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey={\"payStatus\":\"JDP_PAY_CANCEL\"}");
                            }
                            Intent intent2 = new Intent();
                            intent2.addFlags(67108864);
                            intent2.setData(parse);
                            try {
                                this.webUiBinder.startActivity(intent2);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            this.jsBridgeEntity.canReturnThirdApp = false;
                            onWebClose();
                            this.webUiBinder.finishUi();
                        }
                    }
                    if (this.webUiBinder.getWebEntity().isFromAuthSdk) {
                        onWebClose();
                        WebUtils.jdAuthNotifyThirdApp(this.webUiBinder);
                    } else if (this.webUiBinder.getJdWebView() != null && !this.webUiBinder.getJdWebView().onBack()) {
                        onWebClose();
                        this.webUiBinder.getBaseActivity().onTitleBack();
                    }
                }
            }
        }
        return true;
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
