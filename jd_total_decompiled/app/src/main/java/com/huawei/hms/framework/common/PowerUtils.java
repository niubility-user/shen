package com.huawei.hms.framework.common;

import android.annotation.SuppressLint;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.PowerManager;

/* loaded from: classes12.dex */
public class PowerUtils {
    private static final String TAG = "PowerUtils";

    /* loaded from: classes12.dex */
    public static final class PowerMode {
        static int POWER_MODE_DEFAULT_RETURN_VALUE = 0;
        static int POWER_SAVER_MODE = 4;
        static String SMART_MODE_STATUS = "SmartModeStatus";
    }

    public static boolean isAppIdleMode(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            UsageStatsManager usageStatsManager = null;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 21) {
                Logger.i(TAG, "isAppIdleMode statsManager is null!");
            } else if (i2 >= 22) {
                Object systemService = context.getSystemService("usagestats");
                if (!(systemService instanceof UsageStatsManager)) {
                    return false;
                }
                usageStatsManager = (UsageStatsManager) systemService;
            }
            if (usageStatsManager == null) {
                Logger.i(TAG, "isAppIdleMode statsManager is null!");
                return false;
            } else if (i2 >= 23) {
                return usageStatsManager.isAppInactive(packageName);
            } else {
                return false;
            }
        }
        Logger.i(TAG, "isAppIdleMode Context is null!");
        return false;
    }

    public static boolean isDozeIdleMode(Context context) {
        if (context != null) {
            Object systemService = ContextCompat.getSystemService(context, "power");
            PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (powerManager != null) {
                if (Build.VERSION.SDK_INT >= 23) {
                    try {
                        return powerManager.isDeviceIdleMode();
                    } catch (RuntimeException e2) {
                        Logger.e(TAG, "dealType rethrowFromSystemServer:", e2);
                        return false;
                    }
                }
                Logger.i(TAG, "isDozeIdleMode is version control state!");
                return false;
            }
            Logger.i(TAG, "isDozeIdleMode powerManager is null!");
            return false;
        }
        Logger.i(TAG, "isDozeIdleMode Context is null!");
        return false;
    }

    public static boolean isInteractive(Context context) {
        if (context != null) {
            Object systemService = ContextCompat.getSystemService(context, "power");
            if (systemService instanceof PowerManager) {
                PowerManager powerManager = (PowerManager) systemService;
                if (Build.VERSION.SDK_INT >= 20) {
                    try {
                        return powerManager.isInteractive();
                    } catch (RuntimeException e2) {
                        Logger.i(TAG, "getActiveNetworkInfo failed, exception:" + e2.getClass().getSimpleName() + e2.getMessage());
                    }
                }
            }
        }
        return false;
    }

    public static boolean isWhilteList(Context context) {
        if (context != null) {
            Object systemService = ContextCompat.getSystemService(context, "power");
            PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            String packageName = context.getPackageName();
            if (powerManager != null && Build.VERSION.SDK_INT >= 23) {
                try {
                    return powerManager.isIgnoringBatteryOptimizations(packageName);
                } catch (RuntimeException e2) {
                    Logger.e(TAG, "dealType rethrowFromSystemServer:", e2);
                }
            }
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    public static int readDataSaverMode(Context context) {
        if (context != null) {
            Object systemService = context.getSystemService("connectivity");
            ConnectivityManager connectivityManager = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
            if (connectivityManager != null) {
                int i2 = Build.VERSION.SDK_INT;
                if (i2 < 16 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                    return 0;
                }
                if (!connectivityManager.isActiveNetworkMetered()) {
                    Logger.v(TAG, "ConnectType is not Mobile Network!");
                    return 0;
                } else if (i2 >= 24) {
                    return connectivityManager.getRestrictBackgroundStatus();
                } else {
                    return 0;
                }
            }
            Logger.i(TAG, "readDataSaverMode Context is null!");
            return 0;
        }
        Logger.i(TAG, "readDataSaverMode manager is null!");
        return 0;
    }

    public static int readPowerSaverMode(Context context) {
        int i2;
        if (context != null) {
            int systemInt = SettingUtil.getSystemInt(context.getContentResolver(), PowerMode.SMART_MODE_STATUS, PowerMode.POWER_MODE_DEFAULT_RETURN_VALUE);
            if (systemInt == PowerMode.POWER_MODE_DEFAULT_RETURN_VALUE) {
                Object systemService = ContextCompat.getSystemService(context, "power");
                PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
                if (powerManager != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        try {
                            if (powerManager.isPowerSaveMode()) {
                                i2 = PowerMode.POWER_SAVER_MODE;
                            } else {
                                i2 = PowerMode.POWER_MODE_DEFAULT_RETURN_VALUE;
                            }
                            return i2;
                        } catch (RuntimeException e2) {
                            Logger.e(TAG, "dealType rethrowFromSystemServer:", e2);
                            return systemInt;
                        }
                    }
                    Logger.i(TAG, "readPowerSaverMode is control by version!");
                    return systemInt;
                }
                return systemInt;
            }
            return systemInt;
        }
        Logger.i(TAG, "readPowerSaverMode Context is null!");
        return 0;
    }
}
