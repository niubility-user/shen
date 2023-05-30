package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.permission.PermissionHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactNativePermissionListener implements NativePermissionListener, JDFlutterCall {
    private static final String TAG = "JDReactNativePermissionListener";

    @Override // com.jingdong.common.jdreactFramework.listener.NativePermissionListener
    public void getPermissionStatus(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (currentMyActivity == null) {
            jDCallback2.invoke(new Object[0]);
            return;
        }
        WritableMap createMap = Arguments.createMap();
        if (hashMap.containsKey("name")) {
            String str = (String) hashMap.get("name");
            if (ContextCompat.checkSelfPermission(currentMyActivity, str) == 0) {
                createMap.putString("status", "Authorized");
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(currentMyActivity, str)) {
                createMap.putString("status", "NoAsk");
            } else {
                createMap.putString("status", "Denied");
            }
        }
        if (jDCallback != null) {
            jDCallback.invoke(createMap);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativePermissionListener
    public void newRequestPermission(Activity activity, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        Bundle generateBundle;
        try {
            String str = hashMap.containsKey("moduleName") ? (String) hashMap.get("moduleName") : ReactConstants.TAG;
            List arrayList = hashMap.containsKey("tittleMsg") ? (List) hashMap.get("tittleMsg") : new ArrayList();
            List arrayList2 = hashMap.containsKey("tipMsg") ? (List) hashMap.get("tipMsg") : new ArrayList();
            List arrayList3 = hashMap.containsKey("permissions") ? (List) hashMap.get("permissions") : new ArrayList();
            try {
                if (arrayList3 != null && arrayList3.size() > 0 && activity != null && !activity.isFinishing()) {
                    if (PermissionHelper.hasPermission(activity, arrayList3)) {
                        if (jDCallback != null) {
                            jDCallback.invoke(Boolean.TRUE);
                        }
                        return;
                    }
                    if (hashMap.containsKey(PermissionHelper.PARAM_USER_INITIATIVE)) {
                        generateBundle = PermissionHelper.generateBundle(str, JDReactNativePermissionListener.class.getSimpleName(), "newRequestPermission", ((Boolean) hashMap.get(PermissionHelper.PARAM_USER_INITIATIVE)).booleanValue());
                    } else {
                        generateBundle = PermissionHelper.generateBundle(str, JDReactNativePermissionListener.class.getSimpleName(), "newRequestPermission");
                    }
                    PermissionHelper.requestPermission(activity, generateBundle, arrayList3, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener.1
                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onCanceled() {
                            super.onCanceled();
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(Boolean.FALSE);
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onDenied() {
                            super.onDenied();
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(Boolean.FALSE);
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onGranted() {
                            super.onGranted();
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(Boolean.TRUE);
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onIgnored() {
                            super.onIgnored();
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(Boolean.FALSE);
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onOpenSetting() {
                            super.onOpenSetting();
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(Boolean.FALSE);
                            }
                        }
                    }, arrayList, arrayList2);
                }
                if (jDCallback2 != null) {
                    jDCallback2.invoke(Boolean.FALSE);
                }
            } catch (Exception unused) {
                if (jDCallback2 != null) {
                    jDCallback2.invoke(Boolean.FALSE);
                }
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0048, code lost:
        r15.invoke(new java.lang.Object[0]);
     */
    @Override // com.jingdong.common.jdreactFramework.listener.NativePermissionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void requestPermission(android.app.Activity r12, java.util.List r13, final com.jingdong.common.jdreactFramework.JDCallback r14, final com.jingdong.common.jdreactFramework.JDCallback r15) {
        /*
            r11 = this;
            r0 = 0
            if (r13 == 0) goto L46
            int r1 = r13.size()     // Catch: java.lang.Exception -> L44
            if (r1 <= 0) goto L46
            if (r12 == 0) goto L46
            boolean r1 = r12.isFinishing()     // Catch: java.lang.Exception -> L44
            if (r1 == 0) goto L12
            goto L46
        L12:
            boolean r1 = com.jingdong.common.permission.PermissionHelper.hasPermission(r12, r13)     // Catch: java.lang.Exception -> L44
            if (r1 == 0) goto L20
            if (r14 == 0) goto L55
            java.lang.Object[] r12 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L44
            r14.invoke(r12)     // Catch: java.lang.Exception -> L44
            goto L55
        L20:
            java.lang.String r1 = "ReactNative"
            java.lang.Class<com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener> r2 = com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener.class
            java.lang.String r2 = r2.getSimpleName()     // Catch: java.lang.Exception -> L44
            java.lang.String r3 = "requestPermission"
            r4 = 1
            android.os.Bundle r6 = com.jingdong.common.permission.PermissionHelper.generateBundle(r1, r2, r3, r4)     // Catch: java.lang.Exception -> L44
            com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener$2 r8 = new com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener$2     // Catch: java.lang.Exception -> L44
            r8.<init>()     // Catch: java.lang.Exception -> L44
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch: java.lang.Exception -> L44
            r9.<init>()     // Catch: java.lang.Exception -> L44
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: java.lang.Exception -> L44
            r10.<init>()     // Catch: java.lang.Exception -> L44
            r5 = r12
            r7 = r13
            com.jingdong.common.permission.PermissionHelper.requestPermission(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L44
            goto L55
        L44:
            goto L4e
        L46:
            if (r15 == 0) goto L55
            java.lang.Object[] r12 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L44
            r15.invoke(r12)     // Catch: java.lang.Exception -> L44
            goto L55
        L4e:
            if (r15 == 0) goto L55
            java.lang.Object[] r12 = new java.lang.Object[r0]
            r15.invoke(r12)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener.requestPermission(android.app.Activity, java.util.List, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
    }
}
