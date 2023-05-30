package com.jingdong.common.web.uilistener.impl;

import com.jingdong.common.R;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.TitleChangeListener;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class TitleChangeListenerImpl extends BaseWebComponent implements TitleChangeListener {
    private final String TAG;
    private JsBridgeEntity jsBridgeEntity;
    private WebJavaScript webJavaScript;

    public TitleChangeListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = TitleChangeListenerImpl.class.getSimpleName();
        this.webJavaScript = (WebJavaScript) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    private void sendException(String str) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", "933");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("url", this.webUiBinder.getJdWebView().getUrl());
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.web.uilistener.TitleChangeListener
    public void onTitleChange(String str) {
        Log.d(this.TAG, "onTitleChange:" + str);
        if (this.webUiBinder.getWebEntity().isCashierDesk && this.webJavaScript != null) {
            this.webUiBinder.getJdWebView().setShareBtnState(false);
            this.webUiBinder.getJdWebView().setMoreBtnVisible(false);
            if (JumpUtils.CASHIERDESK_HOME.equals(this.webJavaScript.getPageIndex())) {
                this.webUiBinder.getJdWebView().setCloseBtnVisible(false);
                if (this.webUiBinder.getWebEntity().fromNewFillOrderActivity.equals(this.webUiBinder.getWebEntity().fromActivity)) {
                    this.webUiBinder.getJdWebView().setRightTextViewState(true);
                    this.webUiBinder.getJdWebView().reSetRightTextView(this.webUiBinder.getBaseActivity().getResources().getString(R.string.forwardToAllOrderList));
                }
            } else if (this.webJavaScript.getPayCompleted()) {
                this.webUiBinder.getJdWebView().setCloseBtnVisible(false);
                this.webUiBinder.getJdWebView().setRightTextViewState(true);
                this.webUiBinder.getJdWebView().reSetRightTextView(this.webUiBinder.getBaseActivity().getResources().getString(R.string.pay_finish));
                this.webUiBinder.getJdWebView().setTitleBackBtnVisible(false);
            }
            sendException("onTitleChange:" + str);
            return;
        }
        if (this.webUiBinder.getBaseActivity() instanceof IMainActivity) {
            this.webUiBinder.getJdWebView().setTitleBackBtnVisible(false);
        } else if (this.webUiBinder.getWebEntity() != null && !this.webUiBinder.getWebEntity().isUseBackBtn) {
            this.webUiBinder.getJdWebView().setTitleBackBtnVisible(false);
        } else {
            this.webUiBinder.getJdWebView().setTitleBackBtnVisible(true);
        }
        this.webUiBinder.getJdWebView().setRightTextViewState(false);
        this.webUiBinder.getJdWebView().setShareBtnState(this.jsBridgeEntity.isNeedShare);
    }
}
