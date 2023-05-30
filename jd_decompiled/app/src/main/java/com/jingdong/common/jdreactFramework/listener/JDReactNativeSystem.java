package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.e;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.utils.MobileDesUtil;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.common.utils.pay.PayCallBackAllListener;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeSystem extends JDReactNativeSystemListener {
    public static final String BACK_URL = "";
    public static final String EVENT_WEIXIN_LOGIN = "JDReact.WEIXIN_LOGIN";
    public static final String ORDER_TYPE_CODE = "0";
    public static final String ORDER_TYPE_QQORGAME = "34";
    public static final String SYSTEMCHANNEL = "com.jd.jdflutter/system";
    private static final String TAG = "JDReactNativeSystem";
    private BroadcastReceiver mWXReceiver;
    private ReactContext reactContext;

    public JDReactNativeSystem(ReactContext reactContext) {
        this.reactContext = reactContext;
    }

    public void notifyJS(WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT_WEIXIN_LOGIN, writableMap);
    }

    public WritableMap packWeiXinLoginData(Intent intent) {
        WritableMap createMap = Arguments.createMap();
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra("code");
                String stringExtra2 = intent.getStringExtra(XView2Constants.STATE);
                int intExtra = intent.getIntExtra("type", -1);
                int intExtra2 = intent.getIntExtra("errCode", -1);
                if (intExtra == 1 && LoginConstans.WX_LOGIN_BY_H5.equals(stringExtra2) && intExtra2 == 0 && !TextUtils.isEmpty(stringExtra)) {
                    createMap.putString("wxCode", stringExtra);
                    createMap.putInt("authResult", 1);
                } else {
                    if (intExtra2 == -2) {
                        createMap.putInt("authResult", 2);
                    } else {
                        createMap.putInt("authResult", 0);
                    }
                    createMap.putString("authErrMsg", "code is NULL or errorCode=" + intExtra2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return createMap;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public String desDecode(HashMap hashMap) {
        try {
            return MobileDesUtil.decrypt(hashMap.containsKey("sourceData") ? (String) hashMap.get("sourceData") : "", hashMap.containsKey("decodeKey") ? (String) hashMap.get("decodeKey") : "");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public String desEncode(HashMap hashMap) {
        try {
            String encrypt = MobileDesUtil.encrypt(hashMap.containsKey("sourceData") ? (String) hashMap.get("sourceData") : "", hashMap.containsKey("encodeKey") ? (String) hashMap.get("encodeKey") : "");
            Log.d("JDReactNativeSystemModule", "desEncode result =" + encrypt);
            return encrypt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean doPay(HashMap hashMap) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        try {
            str = hashMap.containsKey("orderId") ? (String) hashMap.get("orderId") : "";
            str2 = hashMap.containsKey("orderTypeCode") ? (String) hashMap.get("orderTypeCode") : "";
            str3 = hashMap.containsKey("orderType") ? (String) hashMap.get("orderType") : "";
            String str6 = hashMap.containsKey("back_url") ? (String) hashMap.get("back_url") : "";
            str4 = hashMap.containsKey("orderPrice") ? (String) hashMap.get("orderPrice") : "";
            String str7 = hashMap.containsKey("cardPwdFlag") ? (String) hashMap.get("cardPwdFlag") : "";
            String str8 = str6;
            Log.d("JDReactNativeSystemModule", "doPay orderId =" + str);
            Log.d("JDReactNativeSystemModule", "doPay data =" + hashMap);
            boolean moduleAvailability = ReactModuleAvailabilityUtils.getModuleAvailability(JDReactConstant.AVAILABILITY_PAYSUCCESS);
            String moduleBackupUrl = ReactModuleAvailabilityUtils.getModuleBackupUrl(JDReactConstant.AVAILABILITY_PAYSUCCESS);
            if (!moduleAvailability || TextUtils.isEmpty(moduleBackupUrl)) {
                str5 = str8;
            } else {
                Log.d("JDReactNativeSystemModule", "doPay reactUrl =" + moduleBackupUrl);
                JDReactNativeSharedDataModule.putData("orderId", str + "");
                JDReactNativeSharedDataModule.putData("orderPrice", str4);
                JDReactNativeSharedDataModule.putData("orderTypeCode", str2);
                JDReactNativeSharedDataModule.putData("orderType", str3);
                JDReactNativeSharedDataModule.putData("cardPwdFlag", str7);
                JDReactNativeSharedDataModule.putData("from", "gameProp");
                str5 = moduleBackupUrl;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (Float.parseFloat(str4) > 0.0d) {
                BaseActivity a = e.b().a();
                String str9 = str + "";
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                String str10 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "34";
                }
                PayUtils.doPay(a, str9, str10, str3, str4 + "", !TextUtils.isEmpty(str5) ? str5 : "", new PayCallBackAllListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.2
                    {
                        JDReactNativeSystem.this = this;
                    }

                    @Override // com.jingdong.common.utils.pay.PayCallBackAllListener
                    public void failed() {
                        Log.d("############", " pay fail !!! ");
                    }

                    @Override // com.jingdong.common.utils.pay.PayCallbackListener
                    public void succeed() {
                        Log.d("############", " pay success !!! ");
                    }
                });
            } else {
                Intent intent = new Intent(e.b().a(), WebActivity.class);
                intent.putExtra(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
                intent.putExtra("url", str5);
                e.b().a().startActivity(intent);
            }
            Log.d("JDReactNativeSystemModule", "doPay orderId =" + str);
            return true;
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean doWeiXinLogin(HashMap hashMap) {
        try {
            if (WeixinUtil.check()) {
                if (this.mWXReceiver == null) {
                    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.3
                        {
                            JDReactNativeSystem.this = this;
                        }

                        @Override // android.content.BroadcastReceiver
                        public void onReceive(Context context, Intent intent) {
                            JDReactNativeSystem.this.notifyJS(JDReactNativeSystem.this.packWeiXinLoginData(intent));
                            JDReactNativeSystem.this.reactContext.unregisterReceiver(this);
                            JDReactNativeSystem.this.mWXReceiver = null;
                        }
                    };
                    this.mWXReceiver = broadcastReceiver;
                    this.reactContext.registerReceiver(broadcastReceiver, new IntentFilter(Configuration.BROADCAST_FROM_WXLOGIN), Configuration.SLEF_BROADCAST_PERMISSION, null);
                }
                WeixinUtil.wxLogin(LoginConstans.WX_LOGIN_BY_H5);
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean jumpPay(HashMap hashMap) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        try {
            str = (String) hashMap.get("orderId");
            str2 = (String) hashMap.get("orderTypeCode");
            str3 = (String) hashMap.get("orderType");
            str4 = (String) hashMap.get("back_url");
            str5 = (String) hashMap.get("orderPrice");
            String str6 = (String) hashMap.get("cardPwdFlag");
            if (hashMap.containsKey("commonPay")) {
                ((Boolean) hashMap.get("commonPay")).booleanValue();
            }
            Log.d("JDReactNativeSystemModule", "doPay orderId =" + str);
            Log.d("JDReactNativeSystemModule", "doPay data =" + hashMap);
            ReactModuleAvailabilityUtils.getModuleAvailability(JDReactConstant.AVAILABILITY_PAYSUCCESS);
            ReactModuleAvailabilityUtils.getModuleBackupUrl(JDReactConstant.AVAILABILITY_PAYSUCCESS);
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (Float.parseFloat(str5) > 0.0d) {
                BaseActivity a = e.b().a();
                String str7 = str + "";
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                String str8 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "34";
                }
                PayUtils.doPay(a, str7, str8, str3, str5 + "", !TextUtils.isEmpty(str4) ? str4 : "", new PayCallBackAllListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.1
                    {
                        JDReactNativeSystem.this = this;
                    }

                    @Override // com.jingdong.common.utils.pay.PayCallBackAllListener
                    public void failed() {
                        Log.d("############", " pay fail !!! ");
                    }

                    @Override // com.jingdong.common.utils.pay.PayCallbackListener
                    public void succeed() {
                        Log.d("############", " pay success !!! ");
                    }
                });
            } else {
                Intent intent = new Intent(e.b().a(), WebActivity.class);
                intent.putExtra(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
                intent.putExtra("url", str4);
                e.b().a().startActivity(intent);
            }
            Log.d("JDReactNativeSystemModule", "doPay orderId =" + str);
            return true;
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener, com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("desDecode")) {
            String desDecode = desDecode(hashMap);
            if (desDecode != null) {
                jDFlutterCallResult.success(desDecode);
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        } else if (str.equals("desEncode")) {
            String desEncode = desEncode(hashMap);
            if (desEncode != null) {
                jDFlutterCallResult.success(desEncode);
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        } else if (str.equals("doPay")) {
            if (doPay(hashMap)) {
                return;
            }
            jDFlutterCallResult.error("", "", "");
        } else if (str.equals("getCartUUID")) {
            getCartUUID(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.4
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.5
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getDeviceID")) {
            getDeviceID(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.6
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.7
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getDeviceInfo")) {
            getDeviceInfo(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.8
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.9
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isAppDebug")) {
            isAppDebug(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.10
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.11
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("changeStausBarColor")) {
            changeStausBarColor(hashMap.containsKey("color") ? (String) hashMap.get("color") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.12
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.13
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("payOutOrder")) {
            payOutOrder(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.14
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.15
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getCurrentAddress")) {
            getCurrentAddress(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.16
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.17
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("setCacheAddress")) {
            setCacheAddress(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.18
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.19
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getCacheAddress")) {
            getCacheAddress(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.20
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.21
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isDebugMode")) {
            isDebugMode(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.22
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.23
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isWifiVideoAutoPlay")) {
            isWifiVideoAutoPlay(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.24
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystem.25
                {
                    JDReactNativeSystem.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }
}
