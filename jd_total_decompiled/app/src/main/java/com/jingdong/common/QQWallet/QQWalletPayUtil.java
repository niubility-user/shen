package com.jingdong.common.QQWallet;

import android.content.Intent;
import android.os.Handler;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.connect.common.Constants;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.IOpenApiListener;
import com.tencent.mobileqq.openpay.api.OpenApiFactory;
import com.tencent.mobileqq.openpay.data.pay.PayApi;

/* loaded from: classes5.dex */
public class QQWalletPayUtil {
    public static final String QQ_APP_ID = "100273020";
    private static final String TAG = "QQWalletPayUtil";
    public static IOpenApi openApi;
    public static PayApi payApi;

    static {
        creatQQWalletPayInstance();
    }

    public static void creatQQWalletPayInstance() {
        try {
            openApi = OpenApiFactory.getInstance(JdSdk.getInstance().getApplication(), "100273020");
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "creatQQWalletPayInstance.Exception-->" + e2.getMessage());
            }
        }
    }

    public static void doQQPay(final PayApi payApi2) {
        try {
            payApi = payApi2;
            Handler handler = BaseApplication.getHandler();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.jingdong.common.QQWallet.QQWalletPayUtil.1
                    @Override // java.lang.Runnable
                    public void run() {
                        QQWalletPayUtil.startQQPay(PayApi.this);
                    }
                });
            } else {
                IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
                if (currentMyActivity != null) {
                    currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.QQWallet.QQWalletPayUtil.2
                        @Override // java.lang.Runnable
                        public void run() {
                            QQWalletPayUtil.startQQPay(PayApi.this);
                        }
                    });
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "doQQPay.Exception-->" + e2.getMessage());
            }
        }
    }

    public static PayApi getPayApi() {
        return payApi;
    }

    public static boolean isQQInstalled() {
        if (openApi == null) {
            creatQQWalletPayInstance();
        }
        IOpenApi iOpenApi = openApi;
        if (iOpenApi != null) {
            return iOpenApi.isMobileQQInstalled();
        }
        return false;
    }

    public static boolean isQQSupport() {
        if (openApi == null) {
            creatQQWalletPayInstance();
        }
        IOpenApi iOpenApi = openApi;
        if (iOpenApi != null) {
            return iOpenApi.isMobileQQSupportApi("pay");
        }
        return false;
    }

    public static PayApi parserPayApi(JSONObjectProxy jSONObjectProxy) {
        PayApi payApi2 = new PayApi();
        payApi2.appId = "100273020";
        payApi2.serialNumber = jSONObjectProxy.optString("serialNumber");
        payApi2.callbackScheme = CashierSdkGlobalConfig.QQ_CALL_BACK_NAME;
        payApi2.tokenId = jSONObjectProxy.optString("tokenId");
        payApi2.pubAcc = jSONObjectProxy.optString("pubAcc");
        payApi2.pubAccHint = jSONObjectProxy.optString("pubAccHint");
        payApi2.nonce = jSONObjectProxy.optString(Constants.NONCE);
        payApi2.timeStamp = jSONObjectProxy.optLong("timeStamp");
        payApi2.bargainorId = jSONObjectProxy.optString("bargainorId");
        payApi2.sig = jSONObjectProxy.optString("sig");
        payApi2.sigType = jSONObjectProxy.optString("sigType");
        return payApi2;
    }

    public static void setCallBackListener(Intent intent, IOpenApiListener iOpenApiListener) {
        try {
            if (openApi == null) {
                creatQQWalletPayInstance();
            }
            openApi.handleIntent(intent, iOpenApiListener);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "setCallBackListener.Exception-->" + e2.getMessage());
            }
        }
    }

    public static void startQQPay(PayApi payApi2) {
        try {
            if (openApi == null) {
                creatQQWalletPayInstance();
            }
            if (!isQQInstalled()) {
                ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.check_install_qq));
            } else if (!isQQSupport()) {
                JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECK_QQ_APP_SUPPORTAPI, "com.jingdong.common.QQWallet.QQWalletPayUtil");
                ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.check_support_qq));
            } else if (payApi2 == null) {
            } else {
                openApi.execApi(payApi2);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "startQQPay.Exception-->" + e2.getMessage());
            }
        }
    }
}
