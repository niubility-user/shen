package com.jingdong.common.web.javainterface.impl;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkJDPayGeneralFlowSdkHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.manto.sdk.api.IRequestPayment;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class JDPaySDK extends BaseWebComponent implements IJavaInterface {
    public static final int JD_REQUESTCODE_SCANCODE_NEWPATHWAYS_PAY = 4001;
    private final String TAG;
    private JsBridgeEntity jsBridgeEntity;

    public JDPaySDK(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = JDPaySDK.class.getSimpleName();
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    @JavascriptInterface
    public void account(String str, String str2, String str3, String str4) {
        if (Log.D) {
            Log.d(this.TAG, " JDPaySDK.account-->>type=" + str + "  accountParam=" + str2 + " bizId=" + str3 + " jdPayJSCallBack=" + str4);
        }
        try {
            this.jsBridgeEntity.jdPayAccountCallBack = str4;
            if ("1".equals(str)) {
                Bundle bundle = new Bundle();
                bundle.putString("JDPAY_ENTRANCE_VERIFY", JumpUtils.JDPAY_ACCOUNT_SECURITY);
                bundle.putString(JumpUtils.ACCOUNT_PARAM, str2);
                bundle.putString("APP_ID", str3);
                DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 3000);
            } else if ("2".equals(str)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("JDPAY_ENTRANCE_VERIFY", JumpUtils.JDPAY_SMALL_FREE);
                bundle2.putString(JumpUtils.ACCOUNT_PARAM, str2);
                bundle2.putString("APP_ID", str3);
                DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle2, 3000);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(this.TAG, " JDPaySDK.account.Exception-->>" + e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void closeMetroPage() {
        Uri parse;
        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
        jsBridgeEntity.canReturnThirdApp = false;
        if (TextUtils.isEmpty(jsBridgeEntity.metroPayData)) {
            parse = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey={\"payStatus\":\"JDP_PAY_CANCEL\"}");
        } else {
            parse = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey=" + this.jsBridgeEntity.metroPayData);
        }
        Intent intent = new Intent();
        intent.setData(parse);
        intent.addFlags(67108864);
        try {
            this.webUiBinder.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.webUiBinder.finishUi();
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-closeMetroPage");
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JDPAY_SDK;
    }

    @JavascriptInterface
    public void jdPay(String str) {
        if (Log.D) {
            Log.d(this.TAG, " jdpaysdk_jdPay-->>data=" + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.jsBridgeEntity.jdPayParam = new JsBridgeEntity.JdPayParam();
            this.jsBridgeEntity.jdPayParam.callback = jSONObject.optString("callback", "");
            this.jsBridgeEntity.jdPayParam.param = jSONObject.optString("param", "");
            this.jsBridgeEntity.jdPayParam.returnType = jSONObject.optString("returnType", "");
            this.jsBridgeEntity.jdPayParam.type = jSONObject.optString("type", "");
            JsBridgeEntity.JdPayParam jdPayParam = this.jsBridgeEntity.jdPayParam;
            if (jdPayParam == null) {
                return;
            }
            if (!"pay".equals(jdPayParam.type)) {
                if ("thirdPay".equals(this.jsBridgeEntity.jdPayParam.type)) {
                    try {
                        JDJSONObject parseObject = JDJSON.parseObject(this.jsBridgeEntity.jdPayParam.param);
                        Bundle bundle = new Bundle();
                        bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_ACCESS");
                        bundle.putString("ORDERID", parseObject.optString("orderId", ""));
                        bundle.putString("MERCHANT", parseObject.optString(IRequestPayment.OUT_merchant, ""));
                        bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "");
                        bundle.putString(JumpUtils.ACCOUNT_MODE, "Native");
                        bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
                        bundle.putString("SIGNDATA", parseObject.optString("signData", ""));
                        if (Log.D) {
                            Log.d(this.TAG, "thirdPay bundle:" + bundle);
                        }
                        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 5000);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if (!"quickPass".equals(this.jsBridgeEntity.jdPayParam.type)) {
                    JumpUtil.VALUE_DES_JDPAY_CODE.equals(this.jsBridgeEntity.jdPayParam.type);
                }
            }
            WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-jdPay");
        } catch (Exception e3) {
            e3.printStackTrace();
            this.jsBridgeEntity.jdPayParam = null;
        }
    }

    @JavascriptInterface
    public void jdpaysdk_pay(String str, String str2, String str3) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", JumpUtils.JDPAY_COUNTER_CODE);
            bundle.putString(JumpUtils.JDPAY_CODED, str2);
            bundle.putString(JumpUtils.ACCOUNT_PIN, UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "");
            bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
            bundle.putString(JumpUtils.ACCOUNT_MODE, "Native");
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 4000);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(this.TAG, " jdpaysdk_pay.Exception-->>" + e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void jdpaysdk_scanCodePay(String str, String str2, String str3, String str4) {
        try {
            this.jsBridgeEntity.jdPayScanCodeCallBack = str4;
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", JumpUtils.JDPAY_COUNTER_CODE);
            bundle.putString(JumpUtils.JDPAY_CODED, str2);
            bundle.putString(JumpUtils.ACCOUNT_PIN, UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "");
            bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
            bundle.putString(JumpUtils.ACCOUNT_MODE, "Native");
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 4001);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(this.TAG, " jdpaysdk_pay.Exception-->>" + e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void jsOpenWeb(String str) {
        try {
            if (TextUtils.isEmpty(str) || this.webUiBinder.getBaseActivity() == null || this.webUiBinder.getBaseActivity().isFinishing()) {
                return;
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(this.webUiBinder.getBaseActivity(), "com.wangyin.payment.jdpaysdk.browser.BrowserActivity"));
            intent.putExtra("url", str);
            this.webUiBinder.getBaseActivity().startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void metroPay(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (Log.D) {
                Log.d(this.TAG, "metroPay:" + str);
            }
            this.jsBridgeEntity.canReturnThirdApp = false;
            Uri parse = Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey=" + str);
            Intent intent = new Intent();
            intent.setData(parse);
            try {
                this.webUiBinder.startActivity(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.webUiBinder.finishUi();
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-metroPay");
    }

    @JavascriptInterface
    public void pay(String str, String str2, String str3, String str4, String str5) {
        if (Log.D) {
            Log.d(this.TAG, "\u767d\u6761/\u4f17\u7b79_pay-->>appId=" + str2);
        }
        try {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                return;
            }
            this.jsBridgeEntity.jdPayAccountCallBack = str5;
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER");
            bundle.putString("APP_ID", str2);
            bundle.putString("PAY_PARAM", str3);
            bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "");
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 3000);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(this.TAG, "\u767d\u6761/\u4f17\u7b79_Exception-->>" + e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void setJDpayOrder() {
        try {
            if (this.webUiBinder.getBaseActivity() != null && !this.webUiBinder.getBaseActivity().isFinishing()) {
                Bundle bundle = new Bundle();
                bundle.putString("JDPAY_ENTRANCE_VERIFY", "JD_PAY_GENERAL_FLOW_PAYMENT_ORDER");
                bundle.putString("JDPAY_APP_SOURCE", "jdmall");
                bundle.putString("JDPAY_ACCOUNT_MODE", "Native");
                bundle.putString("JDPAY_SESSIONKEY", UserUtil.getWJLoginHelper().getA2());
                DeeplinkJDPayGeneralFlowSdkHelper.startJDPayActivity(this.webUiBinder.getBaseActivity(), bundle);
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-setJDpayOrder");
    }

    @JavascriptInterface
    public void setMetroPayResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (Log.D) {
                Log.d(this.TAG, "metroPay:" + str);
            }
            this.jsBridgeEntity.metroPayData = str;
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-setMetroPayResult");
    }

    @JavascriptInterface
    public void startJDPayFacePay() {
        try {
            if (this.webUiBinder.getBaseActivity() != null && !this.webUiBinder.getBaseActivity().isFinishing()) {
                Bundle bundle = new Bundle();
                bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_FACE_PAY");
                bundle.putString("APP_ID", "mallapp");
                bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
                if (Log.D) {
                    Log.d(this.TAG, " startJDPayFacePay-->>");
                }
                DeeplinkJDpaySdkHelper.startJDPayActivity(this.webUiBinder.getBaseActivity(), bundle);
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-startJDPayFacePay");
    }

    @JavascriptInterface
    public void thirdPay(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (Log.D) {
                    Log.d(this.TAG, "thirdPay:" + str);
                }
                if (!this.jsBridgeEntity.canJumpToPay) {
                    return;
                }
                JDJSONObject parseObject = JDJSON.parseObject(str);
                Bundle bundle = new Bundle();
                bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_ACCESS");
                bundle.putString("ORDERID", parseObject.optString("orderId", ""));
                bundle.putString("MERCHANT", parseObject.optString(IRequestPayment.OUT_merchant, ""));
                bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "");
                bundle.putString(JumpUtils.ACCOUNT_MODE, "Native");
                bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
                bundle.putString("SIGNDATA", parseObject.optString("signData", ""));
                if (Log.D) {
                    Log.d(this.TAG, "thirdPay bundle:" + bundle);
                }
                DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 5000);
                this.jsBridgeEntity.canJumpToPay = false;
            } catch (Exception e2) {
                e2.printStackTrace();
                this.jsBridgeEntity.canJumpToPay = true;
            }
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDPaySDK-thirdPay");
    }

    public JDPaySDK() {
        this.TAG = JDPaySDK.class.getSimpleName();
    }
}
