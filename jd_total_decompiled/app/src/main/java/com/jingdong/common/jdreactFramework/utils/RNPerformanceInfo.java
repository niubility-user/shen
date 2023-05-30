package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class RNPerformanceInfo {
    public String errMsg;
    public String extData;
    public String jsLoadEnd;
    public String memAfter;
    public String memBefore;
    public String moduleName;
    public String moduleVersion;
    public String mountEnd;
    public String preLoadEnd;
    public String requestsInfo;
    public String rnVersion;
    public String rtype;
    public String sessionId;
    public String startTime;
    public String updateEnd;
    public boolean isRn = false;
    public String errCode = "0";

    public static String generateSessionId() {
        return String.format(Locale.ENGLISH, "%010d", Long.valueOf(System.currentTimeMillis() / 1000));
    }

    private static String getCurrentMicrosecond() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format("%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    public static RNPerformanceInfo parseData(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        RNPerformanceInfo rNPerformanceInfo = new RNPerformanceInfo();
        rNPerformanceInfo.moduleName = parseDataFromMap(hashMap, "moduleName");
        rNPerformanceInfo.moduleVersion = parseDataFromMap(hashMap, "moduleVersion");
        rNPerformanceInfo.rnVersion = parseDataFromMap(hashMap, "rnVersion");
        rNPerformanceInfo.memBefore = parseDataFromMap(hashMap, "memBefore");
        rNPerformanceInfo.memAfter = parseDataFromMap(hashMap, "memAfter");
        rNPerformanceInfo.startTime = parseDataFromMap(hashMap, "startTime");
        rNPerformanceInfo.preLoadEnd = parseDataFromMap(hashMap, "preLoadEnd");
        rNPerformanceInfo.jsLoadEnd = parseDataFromMap(hashMap, "jsLoadEnd");
        rNPerformanceInfo.mountEnd = parseDataFromMap(hashMap, "mountEnd");
        rNPerformanceInfo.requestsInfo = parseDataFromMap(hashMap, "requestsInfo");
        rNPerformanceInfo.updateEnd = parseDataFromMap(hashMap, "updateEnd");
        rNPerformanceInfo.extData = parseDataFromMap(hashMap, "extdata");
        rNPerformanceInfo.rtype = parseDataFromMap(hashMap, "rtype");
        rNPerformanceInfo.sessionId = parseDataFromMap(hashMap, "sessionId");
        rNPerformanceInfo.isRn = "1".equals(parseDataFromMap(hashMap, "isRN"));
        rNPerformanceInfo.errMsg = parseDataFromMap(hashMap, "errMsg");
        rNPerformanceInfo.errCode = parseDataFromMap(hashMap, "errCode");
        return rNPerformanceInfo;
    }

    private static String parseDataFromMap(HashMap<String, Object> hashMap, String str) {
        Object obj;
        String moduleName = RNMonitorListener.getInstance().getModuleName();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(moduleName)) {
            return "";
        }
        String monitorParam = RNMonitorListener.getInstance().getMonitorParam(moduleName, str);
        return ((TextUtils.isEmpty(monitorParam) || "rtype".equals(str)) && hashMap != null && hashMap.containsKey(str) && (obj = hashMap.get(str)) != null) ? obj.toString() : monitorParam;
    }

    public void stopRNMonitorListener() {
        RNMonitorListener.getInstance().stopListen();
    }

    public String toString() {
        return "RNPerformanceInfo{moduleName='" + this.moduleName + "', moduleVersion='" + this.moduleVersion + "', rnVersion='" + this.rnVersion + "', memBefore='" + this.memBefore + "', memAfter='" + this.memAfter + "', startTime='" + this.startTime + "', preLoadEnd='" + this.preLoadEnd + "', jsLoadEnd='" + this.jsLoadEnd + "', mountEnd='" + this.mountEnd + "', requestsInfo='" + this.requestsInfo + "', updateEnd='" + this.updateEnd + "', extData='" + this.extData + "', rtype='" + this.rtype + "', sessionId='" + this.sessionId + "', isRn=" + this.isRn + ", errMsg='" + this.errMsg + "', errCode='" + this.errCode + "'}";
    }

    HashMap<String, String> transform2HashMap() {
        HashMap<String, String> hashMap = new HashMap<>(18);
        hashMap.put("moduleName", this.moduleName);
        hashMap.put("moduleVersion", TextUtils.isEmpty(this.moduleVersion) ? "" : this.moduleVersion);
        hashMap.put("RNVersion", this.rnVersion);
        hashMap.put("memBefore", this.memBefore);
        hashMap.put("memAfter", "auto".equals(this.rtype) ? this.memAfter : RNMonitorListener.getInstance().getMemUsed());
        hashMap.put("startTime", this.startTime);
        hashMap.put("preLoadEnd", this.preLoadEnd);
        hashMap.put("jsLoadEnd", this.jsLoadEnd);
        hashMap.put("mountEnd", this.mountEnd);
        hashMap.put("requestsInfo", this.requestsInfo);
        hashMap.put("updateEnd", this.updateEnd);
        hashMap.put("extdata", this.extData);
        hashMap.put("rtype", this.rtype);
        hashMap.put("sessionId", this.sessionId);
        hashMap.put("isRN", this.isRn ? "1" : "0");
        hashMap.put("errMsg", this.errMsg);
        hashMap.put("errCode", this.errCode);
        hashMap.put("occurTime", getCurrentMicrosecond());
        return hashMap;
    }
}
