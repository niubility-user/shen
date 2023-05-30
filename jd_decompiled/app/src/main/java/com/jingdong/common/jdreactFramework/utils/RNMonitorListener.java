package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.text.DecimalFormat;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class RNMonitorListener {
    private static volatile RNMonitorListener sInstance;
    private final String TAG = "RNMonitorListener";
    private boolean isMonitoring = false;
    private ReactMarker.MarkerListener markerListener;
    private String moduleName;
    private HashMap<String, HashMap<String, String>> monitorMap;
    private long timeStamp;

    /* renamed from: com.jingdong.common.jdreactFramework.utils.RNMonitorListener$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants;

        static {
            int[] iArr = new int[ReactMarkerConstants.values().length];
            $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants = iArr;
            try {
                iArr[ReactMarkerConstants.REACT_CONTEXT_THREAD_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CONTENT_APPEARED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private RNMonitorListener() {
        if (this.monitorMap == null) {
            this.monitorMap = new HashMap<>();
        } else {
            clearMonitorMap();
        }
    }

    private void clearMonitorMap() {
        HashMap<String, HashMap<String, String>> hashMap = this.monitorMap;
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        this.monitorMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMonitorModule(String str) {
        HashMap<String, HashMap<String, String>> hashMap = this.monitorMap;
        if (hashMap == null || hashMap.size() <= 0 || !this.monitorMap.containsKey(str)) {
            return;
        }
        this.monitorMap.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean containModule(String str) {
        HashMap<String, HashMap<String, String>> hashMap = this.monitorMap;
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        return this.monitorMap.containsKey(str);
    }

    public static synchronized RNMonitorListener getInstance() {
        RNMonitorListener rNMonitorListener;
        synchronized (RNMonitorListener.class) {
            if (sInstance == null) {
                sInstance = new RNMonitorListener();
            }
            rNMonitorListener = sInstance;
        }
        return rNMonitorListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getModuleVersion(String str) {
        Intent intent;
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = currentMyActivity.getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        String string = intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
        if (TextUtils.isEmpty(str) || !str.equals(string)) {
            return null;
        }
        JLog.d("RNMonitorListener", "Module Name: " + string + "  -  Version: " + intent.getExtras().getString("version"));
        return intent.getExtras().getString("version");
    }

    private void removeListener() {
        ReactMarker.MarkerListener markerListener = this.markerListener;
        if (markerListener != null) {
            ReactMarker.removeListener(markerListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void reportData(String str) {
        JLog.d("RNMonitorListener", "reportData start moduleName:" + str);
        HashMap<String, HashMap<String, String>> hashMap = this.monitorMap;
        if (hashMap != null && hashMap.containsKey(str)) {
            HashMap<String, String> hashMap2 = this.monitorMap.get(str);
            hashMap2.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
            hashMap2.put("typeId", "10");
            hashMap2.put("chId", "3");
            hashMap2.put("rtype", "auto");
            JLog.d("RNMonitorListener", "reportData start 1");
            JDReactHelper.newInstance().postRNMonitorData(hashMap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMonitorParam(String str, String str2, String str3) {
        HashMap<String, HashMap<String, String>> hashMap;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || (hashMap = this.monitorMap) == null) {
            return;
        }
        if (hashMap.containsKey(str)) {
            HashMap<String, String> hashMap2 = this.monitorMap.get(str);
            if (hashMap2 != null) {
                hashMap2.put(str2, str3);
                return;
            }
            return;
        }
        HashMap<String, String> hashMap3 = new HashMap<>();
        hashMap3.put(str2, str3);
        this.monitorMap.put(str, hashMap3);
    }

    public String getMemUsed() {
        long freeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(freeMemory);
        return TextUtils.isEmpty(sb.toString()) ? "" : String.valueOf(freeMemory);
    }

    public String getModuleName() {
        Intent intent;
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = currentMyActivity.getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        return intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
    }

    public String getMonitorParam(String str, String str2) {
        HashMap<String, String> hashMap;
        HashMap<String, HashMap<String, String>> hashMap2 = this.monitorMap;
        return (hashMap2 == null || hashMap2.size() <= 0 || !this.monitorMap.containsKey(str) || (hashMap = this.monitorMap.get(str)) == null || hashMap.size() <= 0 || !hashMap.containsKey(str2)) ? "" : hashMap.get(str2);
    }

    public synchronized void startListen() {
        stopListen();
        JLog.d("RNMonitorListener", "startListen" + this.isMonitoring);
        if (!this.isMonitoring) {
            ReactMarker.MarkerListener markerListener = new ReactMarker.MarkerListener() { // from class: com.jingdong.common.jdreactFramework.utils.RNMonitorListener.1
                @Override // com.facebook.react.bridge.ReactMarker.MarkerListener
                public void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2) {
                    RNMonitorListener rNMonitorListener = RNMonitorListener.this;
                    rNMonitorListener.moduleName = rNMonitorListener.getModuleName();
                    if (RNMonitorListener.this.moduleName == null) {
                        return;
                    }
                    RNMonitorListener.this.timeStamp = System.currentTimeMillis();
                    int i3 = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[reactMarkerConstants.ordinal()];
                    if (i3 == 1) {
                        JLog.d("RNMonitorListener", "REACT_CONTEXT_THREAD_START");
                        RNMonitorListener rNMonitorListener2 = RNMonitorListener.this;
                        if (rNMonitorListener2.containModule(rNMonitorListener2.moduleName)) {
                            RNMonitorListener rNMonitorListener3 = RNMonitorListener.this;
                            rNMonitorListener3.clearMonitorModule(rNMonitorListener3.moduleName);
                        }
                        if (RNMonitorListener.this.monitorMap == null) {
                            RNMonitorListener.this.monitorMap = new HashMap();
                        }
                        RNMonitorListener rNMonitorListener4 = RNMonitorListener.this;
                        rNMonitorListener4.updateMonitorParam(rNMonitorListener4.moduleName, "moduleName", RNMonitorListener.this.moduleName);
                        RNMonitorListener rNMonitorListener5 = RNMonitorListener.this;
                        rNMonitorListener5.updateMonitorParam(rNMonitorListener5.moduleName, "startTime", String.valueOf(RNMonitorListener.this.timeStamp));
                        RNMonitorListener rNMonitorListener6 = RNMonitorListener.this;
                        rNMonitorListener6.updateMonitorParam(rNMonitorListener6.moduleName, "rnVersion", "0.59.9");
                        RNMonitorListener rNMonitorListener7 = RNMonitorListener.this;
                        rNMonitorListener7.updateMonitorParam(rNMonitorListener7.moduleName, "memBefore", RNMonitorListener.this.getMemUsed());
                    } else if (i3 == 2) {
                        JLog.d("RNMonitorListener", "PRE_RUN_JS_BUNDLE_START");
                        RNMonitorListener rNMonitorListener8 = RNMonitorListener.this;
                        rNMonitorListener8.updateMonitorParam(rNMonitorListener8.moduleName, "preLoadEnd", String.valueOf(RNMonitorListener.this.timeStamp));
                    } else if (i3 == 3) {
                        JLog.d("RNMonitorListener", "NATIVE_MODULE_INITIALIZE_START");
                        RNMonitorListener rNMonitorListener9 = RNMonitorListener.this;
                        rNMonitorListener9.updateMonitorParam(rNMonitorListener9.moduleName, "jsLoadEnd", String.valueOf(RNMonitorListener.this.timeStamp));
                    } else if (i3 != 4) {
                    } else {
                        JLog.d("RNMonitorListener", "CONTENT_APPEARED");
                        RNMonitorListener rNMonitorListener10 = RNMonitorListener.this;
                        rNMonitorListener10.updateMonitorParam(rNMonitorListener10.moduleName, "mountEnd", String.valueOf(RNMonitorListener.this.timeStamp));
                        RNMonitorListener rNMonitorListener11 = RNMonitorListener.this;
                        rNMonitorListener11.updateMonitorParam(rNMonitorListener11.moduleName, "memAfter", RNMonitorListener.this.getMemUsed());
                        RNMonitorListener rNMonitorListener12 = RNMonitorListener.this;
                        rNMonitorListener12.updateMonitorParam(rNMonitorListener12.moduleName, "sessionId", System.currentTimeMillis() + "-" + i2);
                        RNMonitorListener rNMonitorListener13 = RNMonitorListener.this;
                        String str2 = rNMonitorListener13.moduleName;
                        RNMonitorListener rNMonitorListener14 = RNMonitorListener.this;
                        rNMonitorListener13.updateMonitorParam(str2, "moduleVersion", rNMonitorListener14.getModuleVersion(rNMonitorListener14.moduleName));
                        RNMonitorListener rNMonitorListener15 = RNMonitorListener.this;
                        rNMonitorListener15.reportData(rNMonitorListener15.moduleName);
                    }
                }
            };
            this.markerListener = markerListener;
            ReactMarker.addListener(markerListener);
            this.isMonitoring = true;
        }
    }

    public synchronized void stopListen() {
        ReactMarker.MarkerListener markerListener = this.markerListener;
        if (markerListener != null) {
            ReactMarker.removeListener(markerListener);
        }
        HashMap<String, HashMap<String, String>> hashMap = this.monitorMap;
        if (hashMap != null) {
            hashMap.clear();
            this.monitorMap = null;
        }
        this.isMonitoring = false;
    }
}
