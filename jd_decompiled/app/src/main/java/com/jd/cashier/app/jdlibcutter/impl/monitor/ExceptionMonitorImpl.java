package com.jd.cashier.app.jdlibcutter.impl.monitor;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;
import com.jd.cashier.app.jdlibcutter.protocol.monitor.IExceptionMonitor;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes13.dex */
public class ExceptionMonitorImpl implements IExceptionMonitor {
    private static final String ERROR_CODE = "933";
    private static final String TAG = "ExceptionMonitorImpl";

    private String formatMillis(long j2) {
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf(d / 1000.0d));
    }

    private String getCurrentMicrosecond() {
        return formatMillis(System.currentTimeMillis());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.monitor.IExceptionMonitor
    public void sendExceptionData(String str, String str2, String str3, String str4) {
        try {
            HashMap hashMap = new HashMap();
            IConfig sdkConfig = DependInitializer.getSdkConfig();
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            sb.append(" appSource = ");
            sb.append(sdkConfig != null ? sdkConfig.getAppSource() : "");
            String sb2 = sb.toString();
            hashMap.put("function", str);
            hashMap.put("errCode", ERROR_CODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put(t.f20145j, str2);
            hashMap.put("url", str3);
            hashMap.put("errMsg", sb2);
            hashMap.put("occurTime", getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
            ILog log = DependInitializer.getLog();
            if (log != null) {
                log.d(TAG, "The monitor mta function " + str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
