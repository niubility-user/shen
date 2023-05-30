package com.jingdong.common.web.urlcheck.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uibinder.impl.CommonWebUiBinder;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class WxPayResultBroadCastReceiver extends BroadcastReceiver {
    private final String TAG = WxPayResultBroadCastReceiver.class.getSimpleName();
    private boolean isFromH5JsFlag = false;
    private IWebUiBinder webUiBinder;

    public WxPayResultBroadCastReceiver(IWebUiBinder iWebUiBinder) {
        this.webUiBinder = iWebUiBinder;
    }

    private void handleH5JsAction(Context context, Intent intent) {
        if (intent != null && this.webUiBinder != null) {
            if (OKLog.D) {
                OKLog.d(this.TAG, "handleH5JsAction   PayResult.getAction-->" + intent.getAction());
            }
            JDAppUnite jDAppUnite = (JDAppUnite) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE);
            if (jDAppUnite == null) {
                return;
            }
            if ("com.jd.wxPayResult".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("errCode", 10);
                String str = "code: " + intExtra + "  ,errStr: " + intent.getStringExtra("errStr");
                if (intExtra == 0) {
                    jDAppUnite.feedbackWxPayCallback("0", str, "\u652f\u4ed8\u6210\u529f");
                } else if (intExtra == -2) {
                    jDAppUnite.feedbackWxPayCallback("2", str, "\u53d6\u6d88\u652f\u4ed8");
                } else {
                    jDAppUnite.feedbackWxPayCallback("1", str, "\u652f\u4ed8\u5931\u8d25");
                }
            }
        }
        this.isFromH5JsFlag = false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.isFromH5JsFlag) {
            handleH5JsAction(context, intent);
        } else if (intent != null) {
            if (Log.D) {
                Log.d(this.TAG, "PayResult.getAction-->" + intent.getAction());
            }
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            CommonWebUiBinder commonWebUiBinder = iWebUiBinder instanceof CommonWebUiBinder ? (CommonWebUiBinder) iWebUiBinder : null;
            if ("com.jd.wxPayResult".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("errCode", 10);
                if (commonWebUiBinder != null) {
                    commonWebUiBinder.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_WEIXIN_PAYRESULT, this.webUiBinder.getWebEntity().payID + CartConstant.KEY_YB_INFO_LINK + intExtra);
                }
                if (intExtra == 0) {
                    WebUtils.forwardSuccessPage(this.webUiBinder);
                } else if (intExtra == -2) {
                } else {
                    WebUtils.doPayFail(this.webUiBinder, "10");
                }
            } else if ("com.jd.QQPayResult".equals(intent.getAction())) {
                int intExtra2 = intent.getIntExtra("retCode", 10);
                if (intExtra2 == 0) {
                    WebUtils.forwardSuccessPage(this.webUiBinder);
                } else if (intExtra2 == -1) {
                } else {
                    WebUtils.doPayFail(this.webUiBinder, "13");
                }
            }
        }
    }

    public void setFromH5JsFlag(boolean z) {
        this.isFromH5JsFlag = z;
    }
}
