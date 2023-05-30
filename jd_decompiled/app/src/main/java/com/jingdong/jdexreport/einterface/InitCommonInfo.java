package com.jingdong.jdexreport.einterface;

/* loaded from: classes.dex */
public class InitCommonInfo {
    private OnPermissionCheckListener checkListener;
    public String appv = "";
    public String hmv = "";
    public String build = "";
    public String guid = "";
    public int zipFlag = 1;
    public String appId = "";

    public String getGuid() {
        OnPermissionCheckListener onPermissionCheckListener = this.checkListener;
        if (onPermissionCheckListener != null) {
            return onPermissionCheckListener.updateGuid();
        }
        return this.guid;
    }

    public void setCheckListener(OnPermissionCheckListener onPermissionCheckListener) {
        this.checkListener = onPermissionCheckListener;
    }
}
