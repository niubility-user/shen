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
    */
    public void requestPermission(Activity activity, List list, final JDCallback jDCallback, final JDCallback jDCallback2) {
        if (list != null) {
            try {
                if (list.size() > 0 && activity != null && !activity.isFinishing()) {
                    if (!PermissionHelper.hasPermission(activity, list)) {
                        PermissionHelper.requestPermission(activity, PermissionHelper.generateBundle(ReactConstants.TAG, JDReactNativePermissionListener.class.getSimpleName(), "requestPermission", true), list, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativePermissionListener.2
                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onCanceled() {
                                super.onCanceled();
                                JDCallback jDCallback3 = jDCallback2;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onDenied() {
                                super.onDenied();
                                JDCallback jDCallback3 = jDCallback2;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onGranted() {
                                super.onGranted();
                                JDCallback jDCallback3 = jDCallback;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onIgnored() {
                                super.onIgnored();
                                JDCallback jDCallback3 = jDCallback2;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onOpenSetting() {
                                super.onOpenSetting();
                                JDCallback jDCallback3 = jDCallback2;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }
                        }, new ArrayList(), new ArrayList());
                    } else if (jDCallback != null) {
                        jDCallback.invoke(new Object[0]);
                    }
                }
            } catch (Exception unused) {
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            }
        }
    }
}
