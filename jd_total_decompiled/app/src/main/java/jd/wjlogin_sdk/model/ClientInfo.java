package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class ClientInfo {
    private short dwAppID;
    private String appName = "";
    private int dwGetSig = 1;
    private String partner = "";
    private String unionId = "";
    private String subunionId = "";
    private boolean isWJAgreePrivacy = true;
    private String osVer = "";
    private String screen = "";
    private String DeviceBrand = "";
    private String DeviceModel = "";
    private String DeviceName = "";
    private long fristInstallTime = 0;
    private long lastUpdateTime = 0;

    public String getAppName() {
        return this.appName;
    }

    public String getDeviceBrand() {
        return this.DeviceBrand;
    }

    public String getDeviceModel() {
        return this.DeviceModel;
    }

    public String getDeviceName() {
        return this.DeviceName;
    }

    public short getDwAppID() {
        return this.dwAppID;
    }

    public int getDwGetSig() {
        return this.dwGetSig;
    }

    public long getFristInstallTime() {
        return this.fristInstallTime;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public String getOsVer() {
        return this.osVer;
    }

    public String getPartner() {
        return this.partner;
    }

    public String getScreen() {
        return this.screen;
    }

    public String getSubunionId() {
        return this.subunionId;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public boolean isWJAgreePrivacy() {
        return this.isWJAgreePrivacy;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setDeviceBrand(String str) {
        this.DeviceBrand = str;
    }

    public void setDeviceModel(String str) {
        this.DeviceModel = str;
    }

    public void setDeviceName(String str) {
        this.DeviceName = str;
    }

    public void setDwAppID(short s) {
        this.dwAppID = s;
    }

    public void setDwGetSig(int i2) {
        this.dwGetSig = i2;
    }

    public void setFristInstallTime(long j2) {
        this.fristInstallTime = j2;
    }

    public void setLastUpdateTime(long j2) {
        this.lastUpdateTime = j2;
    }

    public void setOsVer(String str) {
        this.osVer = str;
    }

    public void setPartner(String str) {
        this.partner = str;
    }

    public void setScreen(String str) {
        this.screen = str;
    }

    public void setSubunionId(String str) {
        this.subunionId = str;
    }

    public void setUnionId(String str) {
        this.unionId = str;
    }

    public void setWJAgreePrivacy(boolean z) {
        this.isWJAgreePrivacy = z;
    }
}
