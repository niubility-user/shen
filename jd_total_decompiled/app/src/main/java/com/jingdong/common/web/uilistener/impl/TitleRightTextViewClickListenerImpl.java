package com.jingdong.common.web.uilistener.impl;

import android.view.View;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.TitleRightTextViewClickListener;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes12.dex */
public class TitleRightTextViewClickListenerImpl extends BaseWebComponent implements TitleRightTextViewClickListener {
    private WebJavaScript webJavaScript;

    public TitleRightTextViewClickListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.webJavaScript = (WebJavaScript) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT);
    }

    private void onClickEvent(String str) {
        JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), str, "NewFillOrderActivity");
    }

    @Override // com.jingdong.common.web.uilistener.TitleRightTextViewClickListener
    public void onRightTextViewClickListener(View view) {
        WebJavaScript webJavaScript = this.webJavaScript;
        if (webJavaScript != null && webJavaScript.getPayCompleted()) {
            onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_PAYMENT_SUCCESSFINISH);
            this.webUiBinder.finishUi();
        } else if (this.webUiBinder.getWebEntity().fromNewFillOrderActivity.equals(this.webUiBinder.getWebEntity().fromActivity)) {
            onClickEvent(CashierDeskMtaIDs.JDCASHIER_SEE_ORDERS);
            WebUtils.gotoOrderListActivity(this.webUiBinder.getBaseActivity());
        }
    }
}
