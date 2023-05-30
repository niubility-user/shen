package com.jingdong.common.weixin;

import android.content.Context;
import android.os.Handler;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes12.dex */
public class WeiXinPayUtil {
    private static final String TAG = "WeiXinUtil";
    private static IWXAPI weiXinApi;
    private static WeiXinEntity weiXinInfo;

    static {
        createAndRegisterWX(JdSdk.getInstance().getApplication());
    }

    public static void createAndRegisterWX(Context context) {
        try {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, "wxe75a2e68877315fb");
            weiXinApi = createWXAPI;
            createWXAPI.registerApp("wxe75a2e68877315fb");
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void doWeiXinPay(final WeiXinEntity weiXinEntity) {
        if (OKLog.D) {
            OKLog.d(TAG, "doWeiXinPay() -->> weiXinEntity = " + weiXinEntity);
        }
        try {
            Handler handler = BaseApplication.getHandler();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.jingdong.common.weixin.WeiXinPayUtil.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WeiXinPayUtil.startWXPay(WeiXinEntity.this);
                    }
                });
                return;
            }
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (currentMyActivity != null) {
                currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.weixin.WeiXinPayUtil.2
                    @Override // java.lang.Runnable
                    public void run() {
                        WeiXinPayUtil.startWXPay(WeiXinEntity.this);
                    }
                });
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static IWXAPI getWeiXinApi() {
        if (weiXinApi == null) {
            weiXinApi = WXAPIFactory.createWXAPI(JdSdk.getInstance().getApplication(), "wxe75a2e68877315fb");
        }
        return weiXinApi;
    }

    public static WeiXinEntity getWeiXinInfo() {
        return weiXinInfo;
    }

    public static boolean isWeiXinInstalled() {
        if (weiXinApi == null) {
            createAndRegisterWX(JdSdk.getInstance().getApplication());
        }
        IWXAPI iwxapi = weiXinApi;
        if (iwxapi != null) {
            return iwxapi.isWXAppInstalled();
        }
        return false;
    }

    public static boolean isWeixinPaySupported() {
        if (weiXinApi == null) {
            createAndRegisterWX(JdSdk.getInstance().getApplication());
        }
        IWXAPI iwxapi = weiXinApi;
        return iwxapi != null && iwxapi.getWXAppSupportAPI() >= 570425345;
    }

    public static void setWeiXinInfo(WeiXinEntity weiXinEntity) {
        weiXinInfo = weiXinEntity;
    }

    public static void startWXPay(WeiXinEntity weiXinEntity) {
        try {
            if (!isWeiXinInstalled()) {
                ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.check_install_weixin));
            } else if (!isWeixinPaySupported()) {
                JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECK_WX_APP_SUPPORTAPI, "com.jingdong.common.weixin.WeiXinUtil", weiXinApi.getWXAppSupportAPI() + CartConstant.KEY_YB_INFO_LINK + 570425345);
                ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.check_support_weixin));
            } else if (weiXinEntity == null) {
            } else {
                if (OKLog.D) {
                    OKLog.d(TAG, "doWeiXinPay() -->> appId = " + weiXinEntity.appId);
                    OKLog.d(TAG, "doWeiXinPay() -->> partnerId = " + weiXinEntity.partnerId);
                    OKLog.d(TAG, "doWeiXinPay() -->> prepayId = " + weiXinEntity.prepayId);
                    OKLog.d(TAG, "doWeiXinPay() -->> nonceStr = " + weiXinEntity.nonceStr);
                    OKLog.d(TAG, "doWeiXinPay() -->> timeStamp = " + weiXinEntity.timeStamp);
                    OKLog.d(TAG, "doWeiXinPay() -->> packageValue = " + weiXinEntity.packageValue);
                    OKLog.d(TAG, "doWeiXinPay() -->> sign = " + weiXinEntity.sign);
                }
                PayReq payReq = new PayReq();
                payReq.appId = weiXinEntity.appId;
                payReq.partnerId = weiXinEntity.partnerId;
                payReq.prepayId = weiXinEntity.prepayId;
                payReq.nonceStr = weiXinEntity.nonceStr;
                payReq.timeStamp = weiXinEntity.timeStamp;
                payReq.packageValue = weiXinEntity.packageValue;
                payReq.sign = weiXinEntity.sign;
                getWeiXinApi().sendReq(payReq);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            try {
                JDMtaUtils.onClick(JdSdk.getInstance().getApplication().getBaseContext(), CashierDeskMtaIDs.JDCHECKOUT_WEIXIN_PAYRESULT, "com.jingdong.common.weixin.WeiXinUtil", weiXinEntity.prepayId + "_-8");
            } catch (Exception e3) {
                OKLog.e(TAG, e3);
            }
        }
    }
}
