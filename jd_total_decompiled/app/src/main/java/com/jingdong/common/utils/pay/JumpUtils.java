package com.jingdong.common.utils.pay;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonUtil;
import com.jingdong.jdsdk.utils.c;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JumpUtils {
    public static final String ACCOUNT_MODE = "ACCOUNT_MODE";
    public static final String ACCOUNT_PARAM = "ACCOUNT_PARAM";
    public static final String ACCOUNT_PIN = "ACCOUNT_PIN";
    public static final String ANDROID_PAY_TYPE = "5";
    public static final String APP_ID = "APP_ID";
    public static final String CASHIERDESK = "cashierDesk";
    public static final String CASHIERDESK_BAITIAO_PAY = "cashierDesk_baiTiaoPay";
    public static final String CASHIERDESK_BETA_HOST = "paybeta.m.jd.com";
    public static final String CASHIERDESK_BETA_M_HOST = "beta-mpay.m.jd.com";
    public static final String CASHIERDESK_BTQUICKPAY = "cashierDesk_btQuickPay";
    public static final String CASHIERDESK_COFFERPAY = "cashierDesk_cofferPay";
    public static final String CASHIERDESK_ERROR = "cashierDesk_error";
    public static final String CASHIERDESK_FINISH = "cashierDesk_finish";
    public static final String CASHIERDESK_HOME = "cashierDesk_home";
    public static final String CASHIERDESK_HOST = "pay.m.jd.com";
    public static final String CASHIERDESK_M_HOST = "mpay.m.jd.com";
    public static final String CASHIERDESK_NEW_BETA_HOST = "beta-cashier.m.jd.com";
    public static final String CASHIERDESK_NEW_HOST = "cashier.m.jd.com";
    public static final String CASHIERDESK_QUICK = "cashierDesk_quick";
    public static final String CASHIERDESK_QUICK_NEW_CARD = "cashierDesk_quick_newcard";
    public static final String CASHIERDESK_QUICK_NEW_CARDINFO = "cashierDesk_quick_newcardinfo";
    public static final String FUNCTION_APP_ID = "appId";
    public static final String FUNCTION_ID_GENAPPPAYID = "genAppPayId";
    public static final String FUNCTION_ID_GETSUCCESSURL = "getSuccessUrl";
    public static final String FUNCTION_ID_JDPAYV2 = "jdPayV2";
    public static final String FUNCTION_ID_OCTOPUSPAY = "octopusPay";
    public static final String FUNCTION_ID_QQWALLETPAY = "qqWalletPay";
    public static final String FUNCTION_ID_UNIONPAYV2 = "unionPayV2";
    public static final String FUNCTION_ID_WEIXINPAY = "weixinPay";
    public static final String FUNCTION_PAY_ID = "payId";
    public static final String FUNCTION_PAY_JDPAY_CHANNEL = "jdPayChannel";
    public static final String FUNCTION_SE_TYPE = "seType";
    public static final String IS_COMBINE_PAY = "isCombinePay";
    public static final String JDPAY_ACCOUNT_SECURITY = "JDPAY_ACCOUNT_SECURITY";
    public static final String JDPAY_ACCOUNT_SECURITY_TYPE = "1";
    public static final String JDPAY_CHANNEL_TYPE = "JDPAY_CHANNEL_TYPE";
    public static final String JDPAY_CODED = "JDPAY_CODE";
    public static final String JDPAY_CODE_SOURCE = "JDPAY_CODE_SOURCE";
    public static final String JDPAY_CODE_SOURCE_VALUE = "0";
    public static final String JDPAY_COUNTER = "JDPAY_COUNTER";
    public static final String JDPAY_COUNTER_CODE = "JDPAY_COUNTER_CODE";
    public static final String JDPAY_COUNTER_V4 = "JDPAY_COUNTER_V4";
    public static final String JDPAY_ENTRANCE_VERIFY = "JDPAY_ENTRANCE_VERIFY";
    public static final String JDPAY_EXTEND_INFO = "JDPAY_EXTEND_INFO";
    public static final String JDPAY_SMALL_FREE = "JDPAY_SMALL_FREE";
    public static final String JDPAY_SMALL_FREE_TYPE = "2";
    public static final String JDPAY_TOAST_PRINT = "JDPAY_TOAST_PRINT";
    public static final String JDPAY_UI_THEME = "JDPAY_UI_THEME";
    public static final String JDP_PAY_CANCEL = "JDP_PAY_CANCEL";
    public static final String JDP_PAY_FAIL = "JDP_PAY_FAIL";
    public static final String JDP_PAY_PARTIAL_SUCCESS = "JDP_PAY_PARTIAL_SUCCESS";
    public static final String JDP_PAY_SUCCESS = "JDP_PAY_SUCCESS";
    public static final int JD_PAY_REQUEST_CODE = 10;
    public static final String JD_PAY_RESULT = "jdpay_Result";
    public static final int JD_PAY_RESULT_CODE = 1024;
    private static String JD_PAY_STATUS = null;
    public static final String JD_PAY_TYPE = "12";
    public static final int JD_REQUESTCODE_SCANCODE_PAY = 4000;
    public static final int JD_REQUESTCODE_THIRD_PAY = 5000;
    public static final int JD_SMALL_FREE_REQUEST_CODE = 3000;
    public static final String OCTOPUS_PAY_RESULT_FAIL_CODE = "2";
    public static final String OCTOPUS_PAY_RESULT_OTHER_CODE = "3";
    public static final String OCTOPUS_PAY_RESULT_SUCCESS_CODE = "1";
    public static final String OCTOPUS_PAY_TYPE = "6";
    public static final String PAY_PARAM = "PAY_PARAM";
    public static final String PAY_TYPE = "type";
    public static final String QQ_PAY_RESULT_ACTION = "com.jd.QQPayResult";
    public static final int QQ_PAY_RESULT_CANCEL_CODE = -1;
    public static final int QQ_PAY_RESULT_SUCCESS_CODE = 0;
    public static final String QQ_PAY_TYPE = "13";
    public static final String R_CANCEL = "cancel";
    public static final String R_FAIL = "fail";
    public static final String R_SUCCESS = "success";
    public static final String UNION_PAY_TYPE = "type";
    public static final String UN_PAY_RESULT = "pay_result";
    public static final String UN_PAY_TYPE = "4";
    public static final int WX_AUTH_DENIED_CODE = -4;
    public static final int WX_CANCEL_CODE = -2;
    public static final int WX_COMM_CODE = -1;
    public static final int WX_GET_WX_PAY_DATA_FAILED_CODE = -6;
    public static final String WX_PAY_INVALID_ACTION = "com.jd.wxPay.invalid.action";
    public static final String WX_PAY_RESULT_ACTION = "com.jd.wxPayResult";
    public static final String WX_PAY_TYPE = "10";
    public static final int WX_SENT_FAILED_CODE = -3;
    public static final int WX_SUCCESS_CODE = 0;
    public static final int WX_UNSUPPORT_CODE = -5;
    private static IJump iJump;
    public static CashierDataCallBack mDataCallBack;

    static {
        setIJump((IJump) c.a("com.jingdong.app.mall.utils.pay.JumpImpl"));
        JD_PAY_STATUS = JDPayApiKey.JD_PAY_STATUS;
    }

    public static boolean checkPayFinishHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            String host = parse.getHost();
            return "payfinish.m.jd.com".equalsIgnoreCase(host) || "beta-finish.m.jd.com".equalsIgnoreCase(host);
        }
        return false;
    }

    public static boolean checkPayHost(String str) {
        return "pay.m.jd.com".equalsIgnoreCase(str) || "paybeta.m.jd.com".equalsIgnoreCase(str) || "cashier.m.jd.com".equalsIgnoreCase(str) || "beta-cashier.m.jd.com".equalsIgnoreCase(str) || "mpay.m.jd.com".equalsIgnoreCase(str) || "beta-mpay.m.jd.com".equalsIgnoreCase(str);
    }

    public static boolean checkPayHttpHost(String str) {
        Uri parse;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
            return false;
        }
        String scheme = parse.getScheme();
        if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            String host = parse.getHost();
            return !"1".equals(parse.getQueryParameter("useM")) && ("pay.m.jd.com".equalsIgnoreCase(host) || "paybeta.m.jd.com".equalsIgnoreCase(host) || "cashier.m.jd.com".equalsIgnoreCase(host) || "beta-cashier.m.jd.com".equalsIgnoreCase(host) || "mpay.m.jd.com".equalsIgnoreCase(host) || "beta-mpay.m.jd.com".equalsIgnoreCase(host));
        }
        return false;
    }

    public static void dataCallBack(Bundle bundle) {
        CashierDataCallBack cashierDataCallBack = mDataCallBack;
        if (cashierDataCallBack != null) {
            cashierDataCallBack.callBack(bundle);
        }
    }

    public static void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.doPayFinishForward(str, browserNewUrlListener);
        }
    }

    @Deprecated
    public static void getJumpToken(Activity activity, Bundle bundle, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.getJumpToken(activity, bundle, browserNewUrlListener);
        }
    }

    @Deprecated
    public static void jumpToHomeActivity(Activity activity, Bundle bundle) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.jumpToHomeActivity(activity, bundle);
        }
    }

    public static void jumpToInterfaceActivity(Activity activity, Bundle bundle) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.jumpToInterfaceActivity(activity, bundle);
        }
    }

    public static void jumpToOrderListActivity(Activity activity, Bundle bundle) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.jumpToOrderListActivity(activity, bundle);
        }
    }

    public static void jumpToWebActivity(BaseActivity baseActivity, Bundle bundle) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.jumpToWebActivity(baseActivity, bundle);
        }
    }

    public static String parserJDPayResult(String str) {
        try {
            return new JSONObject(str).optString(JD_PAY_STATUS);
        } catch (Exception unused) {
            return "";
        }
    }

    public static void queryPayResult(JDJSONObject jDJSONObject, CommonBase.BrowserCashierNewUrlListener browserCashierNewUrlListener) {
        CommonUtil.queryPayResult(jDJSONObject, browserCashierNewUrlListener, 1);
    }

    public static void reDoJDPay(Activity activity) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.reDoJDPay(activity);
        }
    }

    public static void reDoUnionPay(Activity activity) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.reDoUnionPay(activity);
        }
    }

    public static void registCashierDataCallBack(CashierDataCallBack cashierDataCallBack) {
        mDataCallBack = cashierDataCallBack;
    }

    private static void setIJump(IJump iJump2) {
        iJump = iJump2;
    }

    public static void unRegistCashierDataCallBack() {
        mDataCallBack = null;
    }

    public static void unionAndWeiXinPay(Activity activity, Bundle bundle, CommonBase.ProgresslListener progresslListener) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.unionAndWeiXinPay(activity, bundle, progresslListener);
        }
    }

    public static void queryPayResult(JDJSONObject jDJSONObject, CommonBase.BrowserCashierNewUrlListener browserCashierNewUrlListener, int i2) {
        CommonUtil.queryPayResult(jDJSONObject, browserCashierNewUrlListener, i2);
    }

    public static void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.doPayFinishForward(str, browserCashierUrlListener);
        }
    }

    public static void unionAndWeiXinPay(Activity activity, Bundle bundle) {
        IJump iJump2 = iJump;
        if (iJump2 != null) {
            iJump2.unionAndWeiXinPay(activity, bundle, null);
        }
    }
}
