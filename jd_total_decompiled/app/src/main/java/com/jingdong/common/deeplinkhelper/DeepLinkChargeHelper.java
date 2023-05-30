package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.R;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.jdsdk.widget.ToastUtils;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class DeepLinkChargeHelper {
    public static final int CHARGE_TYPE_FLOW = 1;
    public static final int CHARGE_TYPE_GAME = 3;
    public static final int CHARGE_TYPE_LIFE = 5;
    public static final int CHARGE_TYPE_PHONE = 0;
    public static final int CHARGE_TYPE_QQ = 2;
    public static final String TAG = "DeepLinkChargeHelper";

    public static String getErrorString(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("1017", getString(R.string.charge_input_right_number));
        hashMap.put("1025", "");
        hashMap.put("1026", "");
        hashMap.put("1034", "");
        hashMap.put("1035", "");
        hashMap.put("1037", "");
        hashMap.put("1038", "");
        hashMap.put("1039", "");
        hashMap.put("1040", "");
        hashMap.put("1111", getString(R.string.charge_loss_this_item));
        hashMap.put("1112", getString(R.string.charge_please_open_pay_pwd));
        hashMap.put("1113", getString(R.string.charge_error_too_times_check_after));
        hashMap.put("1114", getString(R.string.charge_pwd_not_correct));
        int i2 = R.string.charge_system_error;
        hashMap.put("5000", getString(i2));
        hashMap.put("8000", getString(R.string.charge_one_coupon_limit));
        hashMap.put("JDLL_00001", getString(R.string.charge_param_do_not_be_null));
        int i3 = R.string.charge_param_not_correct;
        hashMap.put("JDLL_00002", getString(i3));
        hashMap.put("JDLL_00003", getString(R.string.charge_number_not_exit));
        hashMap.put("JDLL_00004", getString(R.string.charge_product_not_exit));
        hashMap.put("JDLL_00005", getString(R.string.charge_order_not_exit));
        hashMap.put("JDLL_00006", getString(R.string.charge_no_enough_virture_money));
        hashMap.put("JDLL_00007", getString(R.string.charge_sale_money_diff));
        hashMap.put("JDLL_00008", getString(R.string.charge_product_number_diff));
        hashMap.put("JDLL_00009", getString(R.string.charge_order_fail));
        int i4 = R.string.charge_sys_err;
        hashMap.put("JDLL_000010", getString(i4));
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if ("-1".equals(str)) {
            return getString(i4);
        }
        if ("0".equals(str)) {
            if ("".equals(str2)) {
                return "";
            }
            if ("".equals(str3)) {
                if (hashMap.get(str2) == null) {
                    return getString(i2);
                }
                return (String) hashMap.get(str2);
            }
            return str3;
        } else if ("1".equals(str)) {
            return getString(i3);
        } else {
            if ("2".equals(str)) {
                return getString(R.string.charge_no_such_func);
            }
            if ("3".equals(str)) {
                return getString(R.string.charge_no_login);
            }
            return getString(i2);
        }
    }

    private static void getJiaYouKaUrl(final Context context) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId("queryPczConfig");
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                            if (jSONObject == null) {
                                return;
                            }
                            if ("".equals(DeepLinkChargeHelper.getErrorString(jSONObject.optString("code"), jSONObject.optString("errorCode"), jSONObject.optString("errorMessage")))) {
                                String optString = "0".equals(jSONObject.optString("code")) ? jSONObject.optString("oilCardUrl") : "";
                                if (optString != null) {
                                    if (TextUtils.isEmpty(optString)) {
                                        Context context3 = context;
                                        ToastUtils.shortToast(context3, context3.getString(R.string.charge_waitting));
                                        return;
                                    }
                                    URLParamMap uRLParamMap = new URLParamMap();
                                    uRLParamMap.put(RemoteMessageConst.TO, optString);
                                    Bundle bundle = new Bundle();
                                    SerializableContainer serializableContainer = new SerializableContainer();
                                    serializableContainer.setMap(uRLParamMap);
                                    bundle.putSerializable("urlParamMap", serializableContainer);
                                    bundle.putString("urlAction", RemoteMessageConst.TO);
                                    bundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
                                    DeepLinkCommonHelper.startWebActivity(context, bundle, true);
                                }
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static String getString(int i2) {
        return JdSdk.getInstance().getApplicationContext().getString(i2);
    }

    public static void jumpToChargeJDReactNative(Context context, String str) {
        if (str == null) {
            str = "{}";
        }
        JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
        try {
            jSONObjectProxy.put("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            jSONObjectProxy.put("params", "{\"modulename\":\"JDReactRechargeModule\",\"appname\":\"JDReactRechargeModule\",\"param\":" + str + "}");
            JumpEntity create = JumpEntity.create(jSONObjectProxy);
            JumpUtil.execJump(context, create, 0);
            Log.d(TAG, create.toJsonString());
        } catch (JSONException e2) {
            Log.e(TAG, e2.getMessage());
        }
    }

    public static void startChargeActivity(Context context, int i2) {
        JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
        try {
            jSONObjectProxy.put("source", TAG);
            jSONObjectProxy.put("viewTabIndex", i2);
        } catch (JSONException e2) {
            Log.e(TAG, e2.getMessage());
        }
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":" + jSONObjectProxy + "}");
    }

    public static void startFlowChargeActivity(Context context) {
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":{\"source\":\"DeepLinkChargeHelper\",\"viewTabIndex\":\"1\"}}");
    }

    public static void startFlowChargeAgain(Context context, int i2, int i3, int i4, String str) {
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":{\"source\":\"DeepLinkChargeHelper\",\"viewTabIndex\":\"1\"}}");
    }

    public static void startGameChargeActivity(Context context) {
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":{\"source\":\"DeepLinkChargeHelper\",\"viewTabIndex\":\"3\"}}");
    }

    public static void startLifeChargeActivity(Context context) {
        jumpToChargeJDReactNative(context, "{}");
    }

    public static void startPhoneChargeActivity(Context context) {
        jumpToChargeJDReactNative(context, "{}");
    }

    public static void startPhoneChargeAgain(Context context, int i2, String str, String str2) {
        jumpToChargeJDReactNative(context, "{}");
    }

    public static void startProductActivity(Context context, int i2, String str, String str2, String str3, double d) {
        if (i2 == 5) {
            getJiaYouKaUrl(context);
        } else if (i2 == 2) {
            startPhoneChargeActivity(context);
        } else if (i2 == 3) {
            startFlowChargeActivity(context);
        } else if (i2 == 6) {
            Bundle bundle = new Bundle();
            bundle.putString("skuId", str);
            JDReactAuraHelper.getInstance().launchCardPwdBuyMain(context, bundle);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("qb_game_type", i2);
            bundle2.putString("skuId", str);
            bundle2.putString("pTitle", str3);
            bundle2.putDouble("mCount", d);
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_QB_CHARGE_ACTIVITY, bundle2);
        }
    }

    public static void startQQChargeActivity(Context context) {
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":{\"source\":\"DeepLinkChargeHelper\",\"viewTabIndex\":\"2\"}}");
    }

    public static void startYCTNfcActivity(Context context, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.nfc.extra.TAG", intent.getParcelableExtra("android.nfc.extra.TAG"));
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_YCT_NFC_ACTIVITY, bundle);
    }

    public static void startPhoneChargeActivity(Context context, Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
        try {
            for (String str : keySet) {
                jSONObjectProxy.put(str, bundle.get(str));
            }
            jSONObjectProxy.put("source", TAG);
            jSONObjectProxy.put("viewTabIndex", "1");
        } catch (JSONException e2) {
            Log.e(TAG, e2.getMessage());
        }
        jumpToChargeJDReactNative(context, "{\"page\":\"home\",\"routeParams\":" + jSONObjectProxy + "}");
    }
}
