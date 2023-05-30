package com.jingdong.jdma.minterface;

/* loaded from: classes12.dex */
public class MaInitCommonInfo {
    private OnPermissionCheckListener checkListener;
    public DomainInterface domainInterface;
    private HttpDns httpDns;
    private ISwitchQuery iSwitchQuery;
    private JDMABaseInfo mJDMABaseInfo;
    public int zipFlag = 1;
    public String guid = "";
    public String site_id = "";
    public String app_device = "";
    public String channel = "";
    public String proj_id = "";
    public String appv = "";
    public String appc = "";
    public String build = "";
    public String installationId = "";

    public String getGuid() {
        OnPermissionCheckListener onPermissionCheckListener = this.checkListener;
        if (onPermissionCheckListener != null) {
            return onPermissionCheckListener.updateGuid();
        }
        return this.guid;
    }

    public HttpDns getHttpDns() {
        return this.httpDns;
    }

    public ISwitchQuery getISwitchQuery() {
        return this.iSwitchQuery;
    }

    public String getInstallationId() {
        return this.installationId;
    }

    public JDMABaseInfo getJDMABaseInfo() {
        return this.mJDMABaseInfo;
    }

    public void setCheckListener(OnPermissionCheckListener onPermissionCheckListener) {
        this.checkListener = onPermissionCheckListener;
    }

    public void setHttpDns(HttpDns httpDns) {
        this.httpDns = httpDns;
    }

    public void setISwitchQuery(ISwitchQuery iSwitchQuery) {
        this.iSwitchQuery = iSwitchQuery;
    }

    public void setJDMABaseInfo(JDMABaseInfo jDMABaseInfo) {
        this.mJDMABaseInfo = jDMABaseInfo;
    }
}
