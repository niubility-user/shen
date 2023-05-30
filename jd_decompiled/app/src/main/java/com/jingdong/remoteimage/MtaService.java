package com.jingdong.remoteimage;

import com.jd.framework.json.JDJSON;
import com.jingdong.common.web.managers.PerformanceManager;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class MtaService {
    private static IMtaExceptionReport exceptionReport;

    MtaService() {
    }

    public static void calorieGetImgExp(String str, String str2, String str3) {
        IMtaExceptionReport iMtaExceptionReport = exceptionReport;
        if (iMtaExceptionReport != null) {
            iMtaExceptionReport.calorieGetImgExp(str, str2, str3);
        }
    }

    protected static void exceptionReport(HashMap<String, String> hashMap) {
        IMtaExceptionReport iMtaExceptionReport = exceptionReport;
        if (iMtaExceptionReport != null) {
            iMtaExceptionReport.exceptionReport(hashMap);
        }
        if (Constants.DEBUG) {
            String str = "ExceptionReport param :" + JDJSON.toJSONString(hashMap);
        }
    }

    protected static HashMap<String, String> getCommonFieldMap(int i2) {
        HashMap<String, String> hashMap = new HashMap<>(i2 + 3);
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        hashMap.put("occurTime", String.format("%.6f", Double.valueOf(currentTimeMillis / 1000.0d)));
        hashMap.put("errCode", "929");
        hashMap.put(PerformanceManager.ERR_TYPE, "2");
        return hashMap;
    }

    public static void init(IMtaExceptionReport iMtaExceptionReport) {
        exceptionReport = iMtaExceptionReport;
    }

    public static void sendErrMsg(String str, String str2) {
        HashMap<String, String> commonFieldMap = getCommonFieldMap(2);
        commonFieldMap.put("function", str);
        commonFieldMap.put("errMsg", str2);
        exceptionReport(commonFieldMap);
    }

    public static void sendErrNum(String str, String str2) {
        HashMap<String, String> commonFieldMap = getCommonFieldMap(2);
        commonFieldMap.put("function", str);
        commonFieldMap.put("errNum", str2);
        exceptionReport(commonFieldMap);
    }

    public static void sendErrMsg(String str, String str2, String str3) {
        HashMap<String, String> commonFieldMap = getCommonFieldMap(3);
        commonFieldMap.put("function", str);
        commonFieldMap.put("errMsg", str2);
        commonFieldMap.put("param", str3);
        exceptionReport(commonFieldMap);
    }
}
