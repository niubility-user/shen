package com.tencent.connect;

import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/* loaded from: classes9.dex */
public class a {
    public static boolean a(String str, IUiListener iUiListener) {
        return a(str, iUiListener, -6, Constants.MSG_PERMISSION_NOT_GRANTED, Constants.MSG_PERMISSION_NOT_GRANTED);
    }

    public static boolean a(String str, IUiListener iUiListener, int i2, String str2, String str3) {
        if (Tencent.isPermissionNotGranted()) {
            SLog.i(str, "permission not granted");
            if (iUiListener != null) {
                iUiListener.onError(new UiError(i2, str2, str3));
                return true;
            }
            return true;
        }
        return false;
    }
}
