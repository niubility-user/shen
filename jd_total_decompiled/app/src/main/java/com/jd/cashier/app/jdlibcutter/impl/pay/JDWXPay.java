package com.jd.cashier.app.jdlibcutter.impl.pay;

import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.R;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.WXPayApiKey;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes13.dex */
public class JDWXPay implements IWXPay, WXPayApiKey {
    private IWXAPI weiXinApi;

    private void instanceWXApi() {
        if (this.weiXinApi == null) {
            try {
                IConfig sdkConfig = DependInitializer.getSdkConfig();
                if (sdkConfig != null) {
                    IWXAPI createWXAPI = WXAPIFactory.createWXAPI(JdSdk.getInstance().getApplicationContext(), sdkConfig.getWXAppId());
                    this.weiXinApi = createWXAPI;
                    createWXAPI.registerApp(sdkConfig.getWXAppId());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay
    public void doWXPay(Bundle bundle) {
        if (bundle != null) {
            try {
                IConfig sdkConfig = DependInitializer.getSdkConfig();
                if (!isWXInstalled()) {
                    ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdlibcutter_check_install_wx));
                } else if (!isWXSupported()) {
                    JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECK_WX_APP_SUPPORTAPI, "com.jingdong.common.weixin.WeiXinUtil", this.weiXinApi.getWXAppSupportAPI() + CartConstant.KEY_YB_INFO_LINK + 570425345);
                    ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdlibcutter_check_support_wx));
                } else {
                    PayReq payReq = new PayReq();
                    if (sdkConfig != null) {
                        payReq.appId = sdkConfig.getWXAppId();
                    }
                    payReq.sign = bundle.getString(WXPayApiKey.WX_PAY_SIGN);
                    payReq.prepayId = bundle.getString(WXPayApiKey.WX_PAY_PREPAY_ID);
                    payReq.nonceStr = bundle.getString(WXPayApiKey.WX_PAY_NONCE);
                    payReq.timeStamp = bundle.getString(WXPayApiKey.WX_PAY_TIMESTAMP);
                    payReq.partnerId = bundle.getString(WXPayApiKey.WX_PAY_PARTNER_ID);
                    payReq.packageValue = bundle.getString(WXPayApiKey.WX_PAY_PACKAGE_VALUE);
                    if (this.weiXinApi == null) {
                        instanceWXApi();
                    }
                    IWXAPI iwxapi = this.weiXinApi;
                    if (iwxapi != null) {
                        iwxapi.sendReq(payReq);
                    }
                }
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                    JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECKOUT_WEIXIN_PAYRESULT, "com.jingdong.common.weixin.WeiXinUtil", bundle.getString(WXPayApiKey.WX_PAY_PREPAY_ID) + "_-8");
                } catch (Exception unused) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay
    public boolean isWXInstalled() {
        if (this.weiXinApi == null) {
            instanceWXApi();
        }
        IWXAPI iwxapi = this.weiXinApi;
        return iwxapi != null && iwxapi.isWXAppInstalled();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay
    public boolean isWXSupported() {
        if (this.weiXinApi == null) {
            instanceWXApi();
        }
        IWXAPI iwxapi = this.weiXinApi;
        return iwxapi != null && iwxapi.getWXAppSupportAPI() >= 570425345;
    }
}
