package com.jd.android.sdk.coreinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface SensitiveApi {
    String getDeviceName();

    String getHardwareSerialNo();

    List<PackageInfo> getInstalledPackages(Context context, int i2);

    int getNetworkType(Context context);

    List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses(Context context);

    List<ActivityManager.RunningServiceInfo> getRunningServices(Context context, int i2);

    List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context, int i2);

    String getSimSerialNo(Context context);

    String getWifiMacAddress(Context context);

    String getWifiMacAddressForDeviceFinger(Context context);

    String getWifiMacAddressOver23();
}
