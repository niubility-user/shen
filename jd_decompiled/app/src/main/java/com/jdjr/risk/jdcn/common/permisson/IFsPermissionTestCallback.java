package com.jdjr.risk.jdcn.common.permisson;

/* loaded from: classes18.dex */
public interface IFsPermissionTestCallback {
    public static final int CODE_EXCEPTION = 1;
    public static final int CODE_HARDWARE_FAILD = 2;
    public static final int CODE_SUCCESS = 0;

    void permissionTestCallback(int i2, int i3);
}
