package com.jingdong.jdreact.plugin.fileUpload;

import android.app.Activity;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class JDReactNativeFileUploadListener implements NativeFileUploadListener, JDFlutterCall {
    public static final String FILEUPLOADCHANNEL = "com.jd.jdflutter/fileUpload";
    private static final String TAG = "JDReactNativeFileUploadListener";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener
    public void fileToBase64(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        if (TextUtils.isEmpty(str)) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        try {
            AresCommonUtils.invokeCallback(jDCallback, AresCommonUtils.fileToBase64(new File(str)));
        } catch (Exception e2) {
            e2.toString();
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener
    public void getFileName(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("filepath")) {
            File file = new File((String) hashMap.get("filepath"));
            if (file.exists()) {
                jDCallback.invoke(file.getName());
                return;
            } else {
                jDCallback2.invoke(new Object[0]);
                return;
            }
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener
    public void getFileSize(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("filepath")) {
            File file = new File((String) hashMap.get("filepath"));
            if (file.exists()) {
                jDCallback.invoke("" + file.length());
                return;
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("getFileName")) {
            getFileName(hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getFileSize")) {
            getFileSize(hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("upLoadingFile")) {
            upLoadingFile(hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("fileToBase64")) {
            fileToBase64(hashMap.containsKey("path") ? (String) hashMap.get("path") : "", new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener
    public void upLoadingFile(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        String str;
        String str2;
        String str3;
        if (!hashMap.containsKey("filepath")) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        String str4 = (String) hashMap.get("filepath");
        if (hashMap.containsKey("httphead")) {
            HashMap hashMap2 = (HashMap) hashMap.get("httphead");
            String str5 = (String) hashMap2.get("partOne");
            str2 = (String) hashMap2.get("partTwo");
            str = str5;
            str3 = hashMap.containsKey("boundary") ? (String) hashMap.get("boundary") : null;
        } else {
            str = null;
            str2 = null;
            str3 = null;
        }
        hashMap.containsKey(HttpHeaders.CONTENT_TYPE);
        if (hashMap.containsKey("url")) {
            SimpleHttpClient.doGet((String) hashMap.get("url"), str4, str, str2, str3, new SimpleHttpClient.HttpCallback<String>() { // from class: com.jingdong.jdreact.plugin.fileUpload.JDReactNativeFileUploadListener.1
                @Override // com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient.HttpCallback
                public void onError(String str6) {
                    if (TextUtils.isEmpty(str6)) {
                        JDCallback jDCallback3 = jDCallback2;
                        if (jDCallback3 != null) {
                            jDCallback3.invoke(JDReactConstant.FAILED);
                            return;
                        }
                        return;
                    }
                    JDCallback jDCallback4 = jDCallback2;
                    if (jDCallback4 != null) {
                        jDCallback4.invoke(str6);
                    }
                }

                @Override // com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient.HttpCallback
                public void onSuccess(String str6) {
                    if (TextUtils.isEmpty(str6)) {
                        JDCallback jDCallback3 = jDCallback;
                        if (jDCallback3 != null) {
                            jDCallback3.invoke("OK");
                            return;
                        }
                        return;
                    }
                    JDCallback jDCallback4 = jDCallback;
                    if (jDCallback4 != null) {
                        jDCallback4.invoke(str6);
                    }
                }
            });
        } else if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }
}
