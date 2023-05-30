package com.huawei.hms.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidException;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes12.dex */
public class AvailableUtil {
    private static final Object a = new Object();
    private static boolean b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f1182c;

    public static boolean isInstallerLibExist(Context context) {
        Bundle bundle;
        Object obj;
        if (b) {
            HMSLog.i("AvailableUtil", "installerInit exist: " + f1182c);
            return f1182c;
        }
        synchronized (a) {
            if (!b) {
                boolean z = false;
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    HMSLog.e("AvailableUtil", "In isAvailableLibExist, Failed to get 'PackageManager' instance.");
                    try {
                        Class.forName("com.huawei.hms.update.manager.UpdateManager");
                        z = true;
                    } catch (ClassNotFoundException unused) {
                        HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to find class UpdateManager.");
                    }
                    f1182c = z;
                    b = true;
                } else {
                    try {
                        try {
                            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("availableHMSCoreInstaller")) != null && String.valueOf(obj).equalsIgnoreCase("yes")) {
                                HMSLog.i("AvailableUtil", "available exist: true");
                                z = true;
                            }
                        } catch (AndroidException unused2) {
                            HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.");
                        }
                    } catch (RuntimeException e2) {
                        HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.", e2);
                    }
                    f1182c = z;
                    b = true;
                }
            }
        }
        HMSLog.i("AvailableUtil", "available exist: " + f1182c);
        return f1182c;
    }
}
