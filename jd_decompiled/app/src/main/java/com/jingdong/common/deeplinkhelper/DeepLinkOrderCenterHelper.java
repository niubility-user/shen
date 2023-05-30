package com.jingdong.common.deeplinkhelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.ILogin;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import java.util.HashMap;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class DeepLinkOrderCenterHelper {
    private static final String HOST_BARRAGE_HISTORY = "barragehistory";
    private static final String HOST_FLUTTER_CANCEL_PROGRESS_MODULE = "modulename";
    private static final String HOST_FLUTTER_CANCEL_PROGRESS_VALUE = "JDFlutterRefundProgress";
    private static final String HOST_FLUTTER_NAV_BAR = "isShowNativeNavBar";
    private static final String HOST_INSTALL_TRACE = "installtrace";
    private static final String HOST_LOGISTICS_DRAWABLE_TRACK = "logisticsdrawabletrack";
    private static final String HOST_LOGISTICS_PATH = "logisticsdrawabletrack";
    private static final String HOST_LOGISTICS_TRACK = "logisticsdrawabletrack";
    private static final String HOST_LOGISTICS_TRACK_LAYER = "logisticstracklayer";
    private static final String HOST_MODIFY_ORDER = "modifyorder";
    private static final String HOST_OFTEN_BUY_LIST = "oftenbuylist";
    private static final String HOST_ORDERDETAIL = "orderdetailactivity";
    private static final String HOST_ORDERDETAIL_NEW = "neworderdetailactivity";
    private static final String HOST_ORDERLIST = "myorderlist";
    private static final String HOST_ORDERRECYCLE = "orderRecycle";
    private static final String HOST_ORDER_CONFIRM_SUCCESS = "orderConfirmSuccessPage";
    private static final String HOST_ORDER_INFO_CARD_LAYER = "orderinfocard";
    private static final String MAIN_APP_ORDER = "main";

    @Deprecated
    private static Bundle getBundle(String str) {
        return getBundle(str, "", "", "");
    }

    private static boolean isSwitchOpen(String str) {
        if ("main".equals(str)) {
            return true;
        }
        return DeepLinkSwitch.getInstance().isSwitchOpen(str);
    }

    private static void loginAndstartActivity(final IMyActivity iMyActivity, final String str, final Bundle bundle, final int i2, String str2) {
        DeepLinkLoginHelper.startLoginActivity((Context) iMyActivity, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper.3
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str3) {
                if (str.equals(str3)) {
                    DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), str, bundle, i2);
                }
            }
        }, str);
    }

    private static Bundle resetCancelProcessBundle(Bundle bundle) {
        if (bundle != null) {
            HashMap hashMap = new HashMap();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                }
            }
            bundle.putString("modulename", HOST_FLUTTER_CANCEL_PROGRESS_VALUE);
            bundle.putString(HOST_FLUTTER_NAV_BAR, "0");
            JDJSONObject jDJSONObject = new JDJSONObject();
            for (String str2 : bundle.keySet()) {
                Object obj2 = bundle.get(str2);
                if (obj2 instanceof String) {
                    jDJSONObject.put(str2, (Object) ((String) obj2));
                }
            }
            jDJSONObject.put("param", (Object) hashMap);
            bundle.putString("params", jDJSONObject.toJSONString());
        }
        return bundle;
    }

    public static void startActivity(Context context, String str, Bundle bundle, boolean z, int i2, boolean z2, String str2) {
        if (context != null) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str);
            a d = b.a().d(host.toString());
            if (d == null) {
                return;
            }
            String b = d.b();
            if (!TextUtils.isEmpty(b) && isSwitchOpen(b)) {
                if (z2) {
                    if (context instanceof IMyActivity) {
                        loginAndstartActivity((IMyActivity) context, host.toString(), bundle, i2, str2);
                        return;
                    }
                    throw new IllegalArgumentException("context must be a subclass of BaseActivity if need login");
                }
                DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("context parameter is null");
    }

    public static void startBarrageHistory(Context context, Bundle bundle) {
        startJump(context, HOST_BARRAGE_HISTORY, bundle);
    }

    public static void startBarrageHistoryForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_BARRAGE_HISTORY, bundle, i2);
    }

    public static void startCancelProgress(Context context, Bundle bundle) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(f.f19954c, "com.jd.lib.jdflutter.JDFlutterMainActivity"));
            resetCancelProcessBundle(bundle);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static void startCancelProgressForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(f.f19954c, "com.jd.lib.jdflutter.JDFlutterMainActivity"));
            resetCancelProcessBundle(bundle);
            intent.putExtras(bundle);
            baseActivity.startActivityForResult(intent, i2);
        } catch (Exception unused) {
        }
    }

    public static void startInstallTrace(Context context, Bundle bundle) {
        startJump(context, HOST_INSTALL_TRACE, bundle);
    }

    public static void startInstallTraceForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_INSTALL_TRACE, bundle, i2);
    }

    private static void startJump(final Context context, final String str, final Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper.1
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if (str.equals(str2)) {
                    DeepLinkCommonHelper.startActivityDirect(context, str, bundle);
                }
            }
        }, str);
    }

    private static void startJumpForResult(final BaseActivity baseActivity, final String str, final Bundle bundle, final int i2) {
        DeepLinkLoginHelper.startLoginActivity(baseActivity, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper.2
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if (str.equals(str2)) {
                    DeepLinkCommonHelper.startActivityForResult(baseActivity, str, bundle, i2);
                }
            }
        }, str);
    }

    @Deprecated
    public static void startLogistics(Context context, String str) {
        startJump(context, "logisticsdrawabletrack", getBundle(str));
    }

    @Deprecated
    public static void startLogisticsForMessage(Context context, String str, String str2, String str3, int i2) {
        DeepLinkCommonHelper.startActivity(context, "logisticsdrawabletrack", getBundle(str, str2, str3), true, i2, true, "");
    }

    @Deprecated
    public static void startLogisticsForResult(BaseActivity baseActivity, String str, int i2) {
        startJumpForResult(baseActivity, "logisticsdrawabletrack", getBundle(str), i2);
    }

    public static void startLogisticsPath(Context context, Bundle bundle) {
        startJump(context, "logisticsdrawabletrack", bundle);
    }

    public static void startLogisticsPathForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, "logisticsdrawabletrack", bundle, i2);
    }

    public static void startLogisticsTrackLayer(Context context, Bundle bundle) {
        startJump(context, HOST_LOGISTICS_TRACK_LAYER, bundle);
    }

    public static void startLogisticsTrackLayerForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_LOGISTICS_TRACK_LAYER, bundle, i2);
    }

    public static void startOftenBuyList(Context context, Bundle bundle) {
        startJump(context, HOST_OFTEN_BUY_LIST, bundle);
    }

    public static void startOftenBuyListForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_OFTEN_BUY_LIST, bundle, i2);
    }

    public static void startOrderConfirmSuccess(Context context, Bundle bundle) {
        startJump(context, "orderConfirmSuccessPage", bundle);
    }

    public static void startOrderDetail(Context context, Bundle bundle) {
        startJump(context, HOST_ORDERDETAIL_NEW, bundle);
    }

    public static void startOrderDetailCardLayer(Context context, Bundle bundle) {
        startJump(context, HOST_ORDER_INFO_CARD_LAYER, bundle);
    }

    public static void startOrderDetailForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_ORDERDETAIL_NEW, bundle, i2);
    }

    @Deprecated
    public static void startOrderDetailWithFlags(Context context, String str, String str2, String str3, int i2) {
        startActivity(context, HOST_ORDERDETAIL_NEW, getBundle(str, str2, str3), true, i2, true, "");
    }

    @Deprecated
    public static void startOrderList(Context context) {
        startOrderList(context, new Bundle());
    }

    public static void startOrderListForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, HOST_ORDERLIST, bundle, i2);
    }

    public static void startOrderLogisticsDrawableTrack(Context context, Bundle bundle) {
        if (bundle.containsKey("orderid")) {
            bundle.putString("orderId", bundle.getString("orderid"));
        }
        startJump(context, "logisticsdrawabletrack", bundle);
    }

    public static void startOrderModify(Context context, Bundle bundle) {
        startJump(context, HOST_MODIFY_ORDER, bundle);
    }

    public static void startOrderRecycle(Context context, Bundle bundle) {
        startJump(context, "orderRecycle", bundle);
    }

    public static void startOrderRecycleForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, "orderRecycle", bundle, i2);
    }

    @Deprecated
    private static Bundle getBundle(String str, String str2, String str3) {
        return getBundle(str, str2, str3, "");
    }

    public static void startLogistics(Context context, String str, String str2) {
        startJump(context, "logisticsdrawabletrack", getBundle(str, "", "", "", str2));
    }

    public static void startLogisticsForMessage(Context context, String str, String str2, String str3, String str4, int i2) {
        DeepLinkCommonHelper.startActivity(context, "logisticsdrawabletrack", getBundle(str, str2, str3, "", str4), true, i2, true, "");
    }

    public static void startLogisticsForResult(BaseActivity baseActivity, String str, String str2, int i2) {
        startJumpForResult(baseActivity, "logisticsdrawabletrack", getBundle(str, "", "", "", str2), i2);
    }

    public static void startOrderConfirmSuccess(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, "orderConfirmSuccessPage", bundle, i2);
    }

    @Deprecated
    public static void startOrderDetail(Context context, String str) {
        startOrderDetail(context, getBundle(str));
    }

    public static void startOrderDetailWithFlags(Context context, String str, String str2, String str3, String str4, int i2) {
        startActivity(context, HOST_ORDERDETAIL_NEW, getBundle(str, str2, str3, "", str4), true, i2, true, "");
    }

    @Deprecated
    private static Bundle getBundle(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString("function", str4);
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("msgId", str2);
        }
        if (!TextUtils.isEmpty(str3) && str3.equals("0")) {
            bundle.putString("fromNotice", str3);
        }
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        return bundle;
    }

    public static void startLogistics(Context context, Bundle bundle) {
        if (bundle.containsKey("orderid")) {
            bundle.putString("orderId", bundle.getString("orderid"));
        }
        startJump(context, "logisticsdrawabletrack", bundle);
    }

    public static void startLogisticsForResult(BaseActivity baseActivity, Bundle bundle, int i2) {
        startJumpForResult(baseActivity, "logisticsdrawabletrack", bundle, i2);
    }

    public static void startOrderDetail(Context context, String str, String str2) {
        startOrderDetail(context, getBundle(str, "", "", "", str2));
    }

    public static void startOrderList(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("from", str);
        startOrderList(context, bundle);
    }

    public static void startOrderList(Context context, Bundle bundle) {
        startJump(context, HOST_ORDERLIST, bundle);
    }

    @Deprecated
    public static void startOrderList(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString("functionId", str2);
        startJump(context, HOST_ORDERLIST, bundle);
    }

    public static void startOrderList(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString("functionId", str2);
        bundle.putString("from", str3);
        startJump(context, HOST_ORDERLIST, bundle);
    }

    private static Bundle getBundle(String str, String str2, String str3, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString("function", str4);
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("msgId", str2);
        }
        if (!TextUtils.isEmpty(str3) && str3.equals("0")) {
            bundle.putString("fromNotice", str3);
        }
        if (!TextUtils.isEmpty(str5)) {
            bundle.putString("from", str5);
        }
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        return bundle;
    }

    public static void startOrderList(Context context, Bundle bundle, int i2) {
        DeepLinkCommonHelper.startActivity(context, HOST_ORDERLIST, bundle, true, i2, true, "");
    }
}
