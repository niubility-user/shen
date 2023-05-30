package com.jd.cashier.app.jdlibcutter.impl.pay;

import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.R;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.IQQPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.QQPayApiKey;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.OpenApiFactory;
import com.tencent.mobileqq.openpay.data.pay.PayApi;

/* loaded from: classes13.dex */
public class JDQQPay implements IQQPay, QQPayApiKey {
    private IOpenApi mOpenApi;

    private IOpenApi instanceQQPayApi() {
        IConfig sdkConfig;
        try {
            if (this.mOpenApi == null && (sdkConfig = DependInitializer.getSdkConfig()) != null) {
                this.mOpenApi = OpenApiFactory.getInstance(JdSdk.getInstance().getApplication(), sdkConfig.getQQAppId());
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
        return this.mOpenApi;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.IQQPay
    public boolean checkParams(Bundle bundle) {
        if (bundle != null) {
            PayApi payApi = new PayApi();
            IConfig sdkConfig = DependInitializer.getSdkConfig();
            if (sdkConfig != null) {
                payApi.appId = sdkConfig.getQQAppId();
                payApi.callbackScheme = sdkConfig.getQQCallBackName();
            }
            payApi.sig = bundle.getString(QQPayApiKey.SIG);
            payApi.bargainorId = bundle.getString(QQPayApiKey.BARGAINORID);
            payApi.serialNumber = bundle.getString(QQPayApiKey.SERIAL_NUMBER);
            payApi.tokenId = bundle.getString(QQPayApiKey.TOKEN_ID);
            payApi.pubAcc = bundle.getString(QQPayApiKey.PUB_ACC);
            payApi.pubAccHint = bundle.getString(QQPayApiKey.PUB_ACC_HINT);
            payApi.nonce = bundle.getString(QQPayApiKey.NONCE);
            payApi.timeStamp = bundle.getLong(QQPayApiKey.TIME_STAMP);
            payApi.sigType = bundle.getString(QQPayApiKey.SIG_TYPE);
            return payApi.checkParams();
        }
        return false;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.IQQPay
    public void doPay(Bundle bundle) {
        if (bundle != null) {
            PayApi payApi = new PayApi();
            IConfig sdkConfig = DependInitializer.getSdkConfig();
            if (sdkConfig != null) {
                payApi.appId = sdkConfig.getQQAppId();
                payApi.callbackScheme = sdkConfig.getQQCallBackName();
            }
            payApi.sig = bundle.getString(QQPayApiKey.SIG);
            payApi.bargainorId = bundle.getString(QQPayApiKey.BARGAINORID);
            payApi.serialNumber = bundle.getString(QQPayApiKey.SERIAL_NUMBER);
            payApi.tokenId = bundle.getString(QQPayApiKey.TOKEN_ID);
            payApi.pubAcc = bundle.getString(QQPayApiKey.PUB_ACC);
            payApi.pubAccHint = bundle.getString(QQPayApiKey.PUB_ACC_HINT);
            payApi.nonce = bundle.getString(QQPayApiKey.NONCE);
            payApi.timeStamp = bundle.getLong(QQPayApiKey.TIME_STAMP);
            payApi.sigType = bundle.getString(QQPayApiKey.SIG_TYPE);
            if (this.mOpenApi == null) {
                this.mOpenApi = instanceQQPayApi();
            }
            IOpenApi iOpenApi = this.mOpenApi;
            if (iOpenApi != null) {
                if (!iOpenApi.isMobileQQInstalled()) {
                    ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdlibcutter_check_install_qq));
                } else if (!this.mOpenApi.isMobileQQSupportApi("pay")) {
                    JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECK_QQ_APP_SUPPORTAPI, "com.jingdong.common.QQWallet.QQWalletPayUtil");
                    ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdlibcutter_check_support_qq));
                } else {
                    this.mOpenApi.execApi(payApi);
                }
            }
        }
    }
}
