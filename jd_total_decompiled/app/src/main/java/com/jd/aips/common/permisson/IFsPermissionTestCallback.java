package com.jd.aips.common.permisson;

/* loaded from: classes12.dex */
public interface IFsPermissionTestCallback {
    public static final int CODE_EXCEPTION = 1;
    public static final int CODE_HARDWARE_FAILD = 2;
    public static final int CODE_SUCCESS = 0;

    void permissionTestCallback(int i2, int i3);
}
