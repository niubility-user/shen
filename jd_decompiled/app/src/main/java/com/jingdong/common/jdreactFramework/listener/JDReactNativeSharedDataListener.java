package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.react.bridge.WritableNativeMap;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeSharedDataListener implements NativeSharedDataListener, JDFlutterCall {
    public static final String SHAREDDATACHANNEL = "com.jd.jdflutter/sharedData";
    private static final String TAG = "JDReactNativeSharedDataListener";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener
    public void addSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                String str = (String) ((Map.Entry) it.next()).getKey();
                if (str != null) {
                    JDReactNativeSharedDataModule.putData(str, (String) hashMap.get(str));
                }
            }
            jDCallback.invoke(new Object[0]);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener
    public void getSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap != null && hashMap.containsKey(ApkDownloadTable.FIELD_FILE_NAME)) {
            String str = (String) hashMap.get(ApkDownloadTable.FIELD_FILE_NAME);
            if (hashMap.containsKey("key")) {
                jDCallback.invoke(ReactModuleAvailabilityUtils.getSharedPreferences(str).getString((String) hashMap.get("key"), ""));
                return;
            } else {
                jDCallback2.invoke(new Object[0]);
                return;
            }
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("putSharedData")) {
            putSharedData(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getSharedData")) {
            getSharedData(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("querySharedDataByName")) {
            querySharedDataByName(hashMap.containsKey("name") ? (String) hashMap.get("name") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("queryAllSharedData")) {
            queryAllSharedData(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("addSharedData")) {
            addSharedData(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSharedDataListener.10
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener
    public void putSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap != null && hashMap.containsKey(ApkDownloadTable.FIELD_FILE_NAME)) {
            String str = (String) hashMap.get(ApkDownloadTable.FIELD_FILE_NAME);
            if (hashMap.containsKey("key")) {
                String str2 = (String) hashMap.get("key");
                if (hashMap.containsKey("value")) {
                    Object obj = hashMap.get("value");
                    SharedPreferences sharedPreferences = ReactModuleAvailabilityUtils.getSharedPreferences(str);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    if (sharedPreferences != null) {
                        if (obj instanceof Number) {
                            edit.putInt(str2, (int) ((Double) hashMap.get("value")).doubleValue());
                            edit.apply();
                        } else if (obj instanceof Boolean) {
                            edit.putBoolean(str2, ((Boolean) hashMap.get("value")).booleanValue());
                            edit.apply();
                        } else if (obj instanceof String) {
                            edit.putString(str2, (String) hashMap.get("value"));
                            edit.apply();
                        }
                        jDCallback.invoke(new Object[0]);
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

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener
    public void queryAllSharedData(JDCallback jDCallback, JDCallback jDCallback2) {
        String str = TAG;
        JLog.d(str, "invoke querySharedDataByName method. okCB = " + jDCallback + ", errorCB = " + jDCallback2);
        if (jDCallback != null && jDCallback2 != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            Map<String, ?> all = JDReactNativeSharedDataModule.getAll();
            if (all != null && all.size() > 0) {
                for (String str2 : all.keySet()) {
                    writableNativeMap.putString(str2, String.valueOf(all.get(str2)));
                }
            }
            jDCallback.invoke(writableNativeMap);
            return;
        }
        JLog.e(str, "parameters are invalid!!");
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener
    public void querySharedDataByName(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        String str2 = TAG;
        JLog.d(str2, "invoke querySharedDataByName method. name = " + str + "\uff0c okCB = " + jDCallback + ", errorCB = " + jDCallback2);
        if (TextUtils.isEmpty(str) || jDCallback == null || jDCallback2 == null) {
            JLog.e(str2, "parameters are invalid!!");
        } else {
            jDCallback.invoke(JDReactNativeSharedDataModule.getData(str));
        }
    }
}
