package com.jd.security.jdguard.d.d.e;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.content.PermissionChecker;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes17.dex */
public class d {
    @SuppressLint({"MissingPermission"})
    public static String a(Context context) {
        return PermissionChecker.checkSelfPermission(context, "android.permission.BLUETOOTH") != 0 ? DYConstants.DY_NULL_STR : BaseInfo.isBluetoothEnabled() ? "1" : "0";
    }
}
