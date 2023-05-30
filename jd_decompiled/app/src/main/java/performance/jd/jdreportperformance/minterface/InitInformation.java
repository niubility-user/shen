package performance.jd.jdreportperformance.minterface;

/* loaded from: classes.dex */
public class InitInformation {
    public IPerformanceController controller;
    public String env = "";
    public String appId = "";
    public String appVersion = "";
    public String harmonyVersion = "";
    public String build = "";
    public String pin = "";
    public String guid = "";
    public String deviceManufacture = "";
    public String screenInfo = "";
    public int logLevel = 0;
    public int userModel = 0;

    /* loaded from: classes.dex */
    public interface IPerformanceController {
        String queryIpByHost(String str);
    }
}
