package com.jingdong.jdsdk.network.dependency;

import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public interface IStatInfoConfig {
    public static final String KEY_CLEARTEXT = "clearText";
    public static final String KEY_ENCRYPT = "encrypt";
    public static final String REPORT_PARAM_LBS_AREA = "area=";
    public static final String REPORT_PARAM_NETWORK_TYPE = "networkType=";
    public static final String REPORT_PARAM_SIGN = "sign=";
    public static final String REPORT_PARAM_ST = "st=";
    public static final String REPORT_PARAM_SV = "sv=";
    public static final String REPORT_PARAM_WIFI_BSSID = "wifiBssid=";

    boolean canUseReferer();

    String encryptBody(String str);

    Map<String, String> getColorStatParamStr(boolean z, boolean z2, boolean z3, Map<String, String> map, String str);

    String getDeviceUUID(String str, boolean z);

    String getDeviceUUID(boolean z);

    String getJdv();

    String getStatisticReportString(String str, boolean z, boolean z2, boolean z3, Map<String, String> map, String str2);

    Map<String, String> getUniformHeaderField(boolean z, boolean z2);

    String getVersionName();

    void reportTlsHandshakeStatData(JDNetworkStatisticTool.TlsStatEntry tlsStatEntry);

    void saveNetworkStatistic(HashMap<String, Integer> hashMap);
}
