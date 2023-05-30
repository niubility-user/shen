package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity;
import com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactMessageUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.weixin.WeiXinEntity;
import com.jingdong.common.weixin.WeiXinPayUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;
import com.unionpay.UPPayAssistEx;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactNativeCommonPayListener implements NativeCommonPayListener, JDReactOnActivityResultCallBack, JDFlutterCall {
    public static final String PAYCHANNEL = "com.jd.jdflutter/pay";
    public static final int REQUESTJDPAY = 11086;
    private static final String TAG = "JDReactNativeCommonPayListener";
    JDCallback cloudErrorCB;
    JDCallback cloudSuccessCB;
    public HashMap<String, WritableMap> mHashMap = new HashMap<>();
    public HashMap<String, WritableArray> mHashArray = new HashMap<>();

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public void doJDPay(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        Iterator it = hashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            JLog.d("kris", "" + ((Map.Entry) it.next()).getKey());
        }
        boolean booleanValue = hashMap.containsKey("JDPAY_EXTERNAL") ? ((Boolean) hashMap.get("JDPAY_EXTERNAL")).booleanValue() : true;
        if (hashMap.containsKey("mode")) {
            String str = (String) hashMap.get("mode");
            if (hashMap.containsKey("merchatId")) {
                String str2 = (String) hashMap.get("merchatId");
                if (hashMap.containsKey("orderId")) {
                    String str3 = (String) hashMap.get("orderId");
                    if (hashMap.containsKey("signData")) {
                        String str4 = (String) hashMap.get("signData");
                        if (hashMap.containsKey("sessionKey")) {
                            String str5 = (String) hashMap.get("sessionKey");
                            String str6 = hashMap.containsKey("JDPAY_EXTRA_INFO") ? (String) hashMap.get("JDPAY_EXTRA_INFO") : "";
                            if (hashMap.containsKey("source")) {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("JDPAY_EXTERNAL", booleanValue);
                                bundle.putString(JumpUtils.ACCOUNT_MODE, str);
                                bundle.putString("MERCHANT", str2);
                                bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, str5);
                                bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_ACCESS");
                                bundle.putString("ORDERID", str3);
                                bundle.putString("SIGNDATA", str4);
                                bundle.putString("JDPAY_EXTRA_INFO", str6);
                                bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, (String) hashMap.get("source"));
                                ReactMessageUtils.startJDPay((BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity(), this, bundle, 5000, jDCallback, jDCallback2);
                                return;
                            }
                            jDCallback2.invoke(new Object[0]);
                            return;
                        }
                        jDCallback2.invoke(new Object[0]);
                        return;
                    }
                    jDCallback2.invoke(new Object[0]);
                    return;
                }
                jDCallback2.invoke(new Object[0]);
                return;
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public void doJDPayApp(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("AppID")) {
            String str = (String) hashMap.get("AppID");
            if (hashMap.containsKey("sdkParam")) {
                Bundle bundle = new Bundle();
                bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER");
                bundle.putString("APP_ID", str);
                bundle.putString("PAY_PARAM", (String) hashMap.get("sdkParam"));
                ReactMessageUtils.startJDPay((BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity(), this, bundle, 11086, jDCallback, jDCallback2);
                return;
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public void doUnionPay(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("tn")) {
            String str = (String) hashMap.get("tn");
            if (hashMap.containsKey("mode")) {
                String str2 = (String) hashMap.get("mode");
                this.cloudSuccessCB = jDCallback;
                this.cloudErrorCB = jDCallback2;
                if (activity == null) {
                    activity = AbstractJDReactInitialHelper.getCurrentMyActivity();
                }
                UPPayAssistEx.startPay(activity, null, null, str, str2);
                return;
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public boolean doWeiXinPay(HashMap hashMap, Context context) {
        if (!WeiXinPayUtil.isWeiXinInstalled()) {
            ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdreact_check_install_weixin));
            return false;
        } else if (!WeiXinPayUtil.isWeixinPaySupported()) {
            ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdreact_check_support_weixin));
            return false;
        } else {
            if (hashMap.containsKey("jsonbody")) {
                String str = (String) hashMap.get("jsonbody");
                Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
                if (currentMyActivity != null && (currentMyActivity instanceof JDReactNativeBaseActivity)) {
                    try {
                        WeiXinPayUtil.doWeiXinPay(new WeiXinEntity(new JSONObjectProxy(new JSONObject(str))));
                        ((JDReactNativeBaseActivity) currentMyActivity).registWXPayBroadcast(context);
                        return true;
                    } catch (JSONException e2) {
                        JLog.e(TAG, e2.toString());
                    }
                }
            }
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap getState(String str) {
        return this.mHashMap.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray getStateArray(String str) {
        return this.mHashArray.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public boolean isWeiXinInstalled() {
        return WeiXinPayUtil.isWeiXinInstalled();
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public boolean isWeiXinSupport() {
        if (!WeiXinPayUtil.isWeiXinInstalled()) {
            ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdreact_check_install_weixin));
            return false;
        } else if (WeiXinPayUtil.isWeixinPaySupported()) {
            return true;
        } else {
            ToastUtils.showToast(JdSdk.getInstance().getApplication().getString(R.string.jdreact_check_support_weixin));
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public boolean isWeixinPaySupported() {
        return WeiXinPayUtil.isWeixinPaySupported();
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (intent == null) {
            return;
        }
        if (i2 == 5000) {
            String stringExtra = intent.getStringExtra("jdpay_Result");
            String parserJDPayResult = JumpUtils.parserJDPayResult(stringExtra);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("jdPayStatus", parserJDPayResult);
            createMap.putString("jdPayResult", stringExtra);
            saveState(String.valueOf(5000), createMap);
        } else if (i2 == 11086) {
            String stringExtra2 = intent.getStringExtra("jdpay_Result");
            String parserJDPayResult2 = JumpUtils.parserJDPayResult(stringExtra2);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putString("jdPayStatus", parserJDPayResult2);
            createMap2.putString("jdPayResult", stringExtra2);
            saveState(String.valueOf(11086), createMap2);
        }
        if (this.cloudSuccessCB == null) {
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        String string = intent.getExtras().getString("pay_result");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (string.equalsIgnoreCase("success")) {
            writableNativeMap.putString("code", "0");
            writableNativeMap.putString("message", "payment succeeded");
            this.cloudSuccessCB.invoke(writableNativeMap);
        } else if (string.equalsIgnoreCase("fail")) {
            writableNativeMap.putString("code", "-1");
            writableNativeMap.putString("message", "payment failed");
            this.cloudSuccessCB.invoke(writableNativeMap);
        } else if (string.equalsIgnoreCase("cancel")) {
            writableNativeMap.putString("code", "-2");
            writableNativeMap.putString("message", "payment canceled");
            this.cloudSuccessCB.invoke(writableNativeMap);
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("isWeiXinSupport")) {
            if (!isWeiXinInstalled()) {
                if (isWeiXinSupport()) {
                    jDFlutterCallResult.success("");
                    return;
                } else {
                    jDFlutterCallResult.error("", "", "");
                    return;
                }
            }
            jDFlutterCallResult.error("", "", "");
        } else if (str.equals("isWeiXinInstalled")) {
            if (isWeiXinInstalled()) {
                jDFlutterCallResult.success("");
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        } else if (str.equals("isWeixinPaySupported")) {
            if (isWeixinPaySupported()) {
                jDFlutterCallResult.success("");
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        } else if (str.equals("doWeiXinPay")) {
            if (doWeiXinPay(hashMap, (ReactApplicationContext) activity.getApplicationContext())) {
                return;
            }
            jDFlutterCallResult.error("", "", "");
        } else if (str.equals("doJDPay")) {
            doJDPay(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.1
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.2
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("doJDPayApp")) {
            doJDPayApp(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.3
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.4
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("openWeixinNoPwdPay")) {
            openWeixinNoPwdPay(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.5
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener.6
                {
                    JDReactNativeCommonPayListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener
    public void openWeixinNoPwdPay(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
        if (WeiXinPayUtil.isWeiXinInstalled()) {
            if (WeiXinPayUtil.isWeixinPaySupported()) {
                OpenWebview.Req req = new OpenWebview.Req();
                if (hashMap == null || !hashMap.containsKey(JshopConst.JSKEY_SHOP_SIGNURL)) {
                    return;
                }
                String str = (String) hashMap.get(JshopConst.JSKEY_SHOP_SIGNURL);
                if (StringUtil.isEmpty(str)) {
                    return;
                }
                req.url = str;
                if (WeiXinPayUtil.getWeiXinApi().sendReq(req)) {
                    jDCallback.invoke(new Object[0]);
                    return;
                } else {
                    jDCallback2.invoke(new Object[0]);
                    return;
                }
            }
            if (currentMyActivity != null) {
                ToastUtil.showToast(currentMyActivity, "\u5fae\u4fe1\u7248\u672c\u4f4e\uff0c\u8bf7\u5347\u7ea7");
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        if (currentMyActivity != null) {
            ToastUtil.showToast(currentMyActivity, "\u62b1\u6b49\uff0c\u60a8\u5c1a\u672a\u5b89\u88c5\u5fae\u4fe1");
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap removeState(String str) {
        return this.mHashMap.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray removeStateArray(String str) {
        return this.mHashArray.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveState(String str, WritableMap writableMap) {
        this.mHashMap.put(str, writableMap);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveStateArray(String str, WritableArray writableArray) {
        this.mHashArray.put(str, writableArray);
    }
}
