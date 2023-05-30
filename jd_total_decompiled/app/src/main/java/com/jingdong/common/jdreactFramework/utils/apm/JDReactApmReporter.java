package com.jingdong.common.jdreactFramework.utils.apm;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.d;
import com.jingdong.lib.monitor.MonitorInfo;
import java.util.Map;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes5.dex */
public class JDReactApmReporter {
    private static final String TAG = "JDReactApmReporter";
    private static final String chId = "3";
    private static final String typeId = "7";

    private static String parseAppName(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("appName");
    }

    private static String parseModuleName(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("moduleName");
    }

    private static String parseModuleVersion(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("moduleVersion");
    }

    private static String parseaExceptionType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(System.getProperty("line.separator"));
        if (TextUtils.isEmpty(split[0]) && split.length > 2) {
            return split[1];
        }
        return split[0];
    }

    public static void post(Throwable th, ArrayMap arrayMap) {
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JDReactHelper.newInstance().getApplicationContext(), "7", "3");
        if (stategyEntitiy == null || !"1".equals(stategyEntitiy.ret)) {
            return;
        }
        String str = stategyEntitiy.param;
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "param = " + str);
        }
        JSExceptionEntity jSExceptionEntity = new JSExceptionEntity();
        jSExceptionEntity.setFeedback(arrayMap.toString());
        jSExceptionEntity.setOccurTime(ExceptionReporter.formatMillis(System.currentTimeMillis()));
        MonitorInfo monitorInfo = new MonitorInfo();
        String b = th == null ? "" : d.b(th);
        jSExceptionEntity.setExceptionStack(b);
        jSExceptionEntity.setExceptionType(parseaExceptionType(b));
        jSExceptionEntity.setPageInfo(monitorInfo.currentPageInfo);
        jSExceptionEntity.setRNSdkVersion("0.55.9");
        jSExceptionEntity.setBName(parseModuleName(arrayMap));
        jSExceptionEntity.setBVersion(parseModuleVersion(arrayMap));
        jSExceptionEntity.setAppCode(NetConfig.sAppCode);
        jSExceptionEntity.setTypeId("7");
        jSExceptionEntity.setChId("3");
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, jSExceptionEntity.getParamsMap().toString());
        }
        PerformanceReporter.reportData(jSExceptionEntity.getParamsMap());
    }
}
