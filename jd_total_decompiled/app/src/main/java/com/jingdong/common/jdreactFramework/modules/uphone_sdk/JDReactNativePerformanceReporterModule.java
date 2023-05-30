package com.jingdong.common.jdreactFramework.modules.uphone_sdk;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.app.mall.performance.a;
import com.jingdong.app.mall.performance.b;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import java.util.HashMap;
import java.util.Map;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes5.dex */
public class JDReactNativePerformanceReporterModule extends ReactContextBaseJavaModule {
    public JDReactNativePerformanceReporterModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("performanceReportForRNEnabled", Boolean.valueOf(SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PerformanceReportForRN, false)));
        return hashMap;
    }

    @ReactMethod
    public void getCurrentUsedMemory(Callback callback) {
        long freeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (callback != null) {
            callback.invoke(String.valueOf(freeMemory));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativePerformanceReporterModule";
    }

    @ReactMethod
    public void getRNPerformancePolicy(Callback callback) {
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "10", "3");
        if (stategyEntitiy == null || !"1".equals(stategyEntitiy.ret)) {
            if (callback != null) {
                callback.invoke(Boolean.FALSE);
            }
        } else if (PerformanceReporter.getModuleSwitch(stategyEntitiy, b.m().o())) {
            if (callback != null) {
                callback.invoke(Boolean.TRUE);
            }
        } else if (callback != null) {
            callback.invoke(Boolean.FALSE);
        }
    }

    @ReactMethod
    public void getRNPerformanceSwitch(Callback callback) {
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PerformanceReportForRN, false);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(switchBooleanValue));
        }
    }

    @ReactMethod
    public void reportChannelData(ReadableMap readableMap, Callback callback) {
        if (readableMap == null) {
            return;
        }
        PerformanceReporter.reportChannelData(a.b(readableMap.toHashMap()));
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }
}
