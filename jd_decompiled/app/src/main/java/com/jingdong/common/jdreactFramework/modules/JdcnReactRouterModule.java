package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jdcn.common_bridge.JdcnCommonBridge;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JdcnReactRouterModule extends ReactContextBaseJavaModule {
    private final String CODE_INPUT_PARAM_FAILD;
    private final String CODE_INVALIDE_TYPE;
    private final String CODE_NATIVE_CALL_FAILD;
    private final String CODE_SUCCESS;
    private final String TYPE_IDENTITY_VERIFY;

    public JdcnReactRouterModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.CODE_SUCCESS = "0";
        this.CODE_INPUT_PARAM_FAILD = "-1";
        this.CODE_INVALIDE_TYPE = "-2";
        this.CODE_NATIVE_CALL_FAILD = "-3";
        this.TYPE_IDENTITY_VERIFY = "1";
    }

    private void commonBridge(Activity activity, JSONObject jSONObject, final Callback callback) {
        try {
            JdcnCommonBridge.serviceCall(activity, jSONObject.toString(), new JdcnCommonBridge.JdcnCommonBridgeCallback() { // from class: com.jingdong.common.jdreactFramework.modules.JdcnReactRouterModule.1
                @Override // com.jdcn.common_bridge.JdcnCommonBridge.JdcnCommonBridgeCallback
                public void callback(String str) {
                    try {
                        callback.invoke("0", str);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReturn(Callback callback, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("routerCode", str);
            jSONObject.put("routerType", str2);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("result", new JSONObject(str3));
            }
            callback.invoke(str, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    private String identityVerify(Activity activity, JSONObject jSONObject, final Callback callback) {
        try {
            try {
                try {
                    try {
                        Class<?> cls = Class.forName("com.jdjr.risk.identity.verify.IdentityVerityEngine");
                        Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                        InvocationHandler invocationHandler = new InvocationHandler() { // from class: com.jingdong.common.jdreactFramework.modules.JdcnReactRouterModule.2
                            @Override // java.lang.reflect.InvocationHandler
                            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                                if (method == null || !TextUtils.equals(method.getName(), "onVerifyResult") || objArr == null || objArr.length < 5) {
                                    return null;
                                }
                                JdcnReactRouterModule.this.handleReturn(callback, "0", "1", (String) objArr[4]);
                                return null;
                            }
                        };
                        Class<?> cls2 = Class.forName("com.jdjr.risk.identity.verify.IdentityVerityCallback");
                        cls.getMethod("checkIdentityVerity", Context.class, Bundle.class, String.class, cls2).invoke(invoke, activity, null, jSONObject.toString(), Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{cls2}, invocationHandler));
                        return "0";
                    } catch (ClassNotFoundException e2) {
                        e2.printStackTrace();
                        return "-2";
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                        return "-2";
                    }
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                    return "-2";
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return "-2";
                }
            } catch (NoSuchMethodException e6) {
                e6.printStackTrace();
                return "-2";
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            return "-3";
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "JdcnReactRouterModule";
    }

    @ReactMethod
    public void jdcnRegisterReactRouterWithParameters(String str, Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || callback == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("routerType");
            JSONObject optJSONObject = jSONObject.optJSONObject("routerParams");
            if (!TextUtils.isEmpty(optString) && TextUtils.equals(optString, "1")) {
                String identityVerify = identityVerify(currentActivity, optJSONObject, callback);
                if (!TextUtils.equals("0", identityVerify)) {
                    handleReturn(callback, identityVerify, optString, null);
                }
            } else {
                commonBridge(currentActivity, jSONObject, callback);
            }
        } catch (Throwable unused) {
            handleReturn(callback, "-1", "-1", null);
        }
    }
}
