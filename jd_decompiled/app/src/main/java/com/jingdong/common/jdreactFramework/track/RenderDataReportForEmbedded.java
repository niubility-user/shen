package com.jingdong.common.jdreactFramework.track;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.listener.DataReportListener;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.common.jdreactFramework.utils.SPForDataReportUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class RenderDataReportForEmbedded {
    private static RenderDataReportForEmbedded sRenderDataReport;
    public static ArrayList<Map> sRenderReportList = new ArrayList<>();
    HashMap<String, HashMap<String, String>> dataMap = new HashMap<>();

    private RenderDataReportForEmbedded() {
    }

    public static RenderDataReportForEmbedded getInstance() {
        if (sRenderDataReport == null) {
            sRenderDataReport = new RenderDataReportForEmbedded();
        }
        return sRenderDataReport;
    }

    public void clearData() {
        JLog.d("RenderDataReport", "clearData ==> " + this.dataMap);
        this.dataMap.clear();
    }

    public void loadStart(String str, String str2) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("renderStartTime", System.currentTimeMillis() + "");
        hashMap.put(JDReactConstant.IntentConstant.MODULE_NAME, str2);
        hashMap.put("session", SPForDataReportUtils.generateSessionId());
    }

    public void renderEnd(String str) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        JLog.d("RenderDataReport", "renderEnd ==> " + str + "-->" + hashMap);
        if (hashMap == null) {
            return;
        }
        long parseLong = hashMap.get("renderStartTime") == null ? 0L : Long.parseLong(hashMap.get("renderStartTime"));
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - parseLong;
        hashMap.put("loadStartTime", String.valueOf(parseLong));
        hashMap.put("loadresult", "0");
        hashMap.put("loadtime", j2 + "");
        hashMap.put("loadtotaltime", j2 + "");
        hashMap.put("engineversion", "0.59.9");
        hashMap.put("appid", NetConfig.sAppCode);
        hashMap.put("fromWhere", "embedded");
        if (JDReactHelper.newInstance().isDebug()) {
            hashMap.put("title", AresCommonUtils.timestampToDateStr(currentTimeMillis));
            sRenderReportList.add(hashMap);
            JLog.d("RenderDataReport", "res = " + sRenderReportList.toString());
        }
        DataReportListener dataReportListener = JDReactHelper.newInstance().getDataReportListener();
        if (dataReportListener != null) {
            hashMap.remove("title");
            hashMap.remove("loadStartTime");
            hashMap.remove("renderStartTime");
            dataReportListener.onLoadDataReport(hashMap);
        }
        this.dataMap.remove(str);
    }
}
