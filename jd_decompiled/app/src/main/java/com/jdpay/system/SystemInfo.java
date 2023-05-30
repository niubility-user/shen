package com.jdpay.system;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.lib.util.JPApp;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class SystemInfo {
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    public static final String ROM_1JIA = "ONEPLUS";
    public static final String ROM_360 = "360";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_LETV = "LETV";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    private static Application app;
    private static String sName;
    private static String sVersion;

    public static boolean check(String str) {
        String manufacturer = getManufacturer();
        if (TextUtils.isEmpty(manufacturer)) {
            return false;
        }
        if (sName == null) {
            String systemProperty = JPApp.getSystemProperty(KEY_VERSION_MIUI);
            sVersion = systemProperty;
            if (!TextUtils.isEmpty(systemProperty)) {
                sName = "MIUI";
            } else {
                String systemProperty2 = JPApp.getSystemProperty(KEY_VERSION_EMUI);
                sVersion = systemProperty2;
                if (!TextUtils.isEmpty(systemProperty2)) {
                    sName = "EMUI";
                } else {
                    String systemProperty3 = JPApp.getSystemProperty(KEY_VERSION_OPPO);
                    sVersion = systemProperty3;
                    if (!TextUtils.isEmpty(systemProperty3)) {
                        sName = "OPPO";
                    } else {
                        String systemProperty4 = JPApp.getSystemProperty(KEY_VERSION_VIVO);
                        sVersion = systemProperty4;
                        if (!TextUtils.isEmpty(systemProperty4)) {
                            sName = "VIVO";
                        } else {
                            String systemProperty5 = JPApp.getSystemProperty(KEY_VERSION_SMARTISAN);
                            sVersion = systemProperty5;
                            if (!TextUtils.isEmpty(systemProperty5)) {
                                sName = "SMARTISAN";
                            } else {
                                String display = getDisplay();
                                sVersion = display;
                                if (display.toUpperCase().contains("FLYME")) {
                                    sName = "FLYME";
                                } else {
                                    sVersion = "unknown";
                                    sName = manufacturer.toUpperCase();
                                }
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    public static String getAndroidID() {
        return BaseInfo.getAndroidId();
    }

    public static int getAndroidSdkVersion() {
        return BaseInfo.getAndroidSDKVersion();
    }

    public static String getAndroidVersion() {
        return BaseInfo.getAndroidVersion();
    }

    public static Application getApplication() {
        return app;
    }

    public static String getBrand() {
        return BaseInfo.getDeviceBrand();
    }

    public static String getClientVersion() {
        return BaseInfo.getAppVersionName();
    }

    public static float getDensity() {
        return BaseInfo.getDensity();
    }

    public static String getDisplay() {
        return BaseInfo.getOSName();
    }

    public static String getDisplayMetrics() {
        return BaseInfo.getDisplayMetrics();
    }

    public static DisplayMetrics getDisplayMetricsObject() {
        Application application = app;
        if (application != null) {
            return BaseInfo.getDisplayMetricsObject(application);
        }
        return null;
    }

    public static String getManufacturer() {
        return BaseInfo.getDeviceManufacture();
    }

    public static String getModel() {
        return BaseInfo.getDeviceModel();
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getNetworkOperatorName() {
        Application application = app;
        if (application != null) {
            return BaseInfo.getNetworkOperatorName(application);
        }
        return null;
    }

    public static String getNetworkType() {
        return BaseInfo.getNetworkType();
    }

    public static String getPackgeName() {
        return BaseInfo.getAppPackageName();
    }

    public static String getProductName() {
        return BaseInfo.getDeviceProductName();
    }

    public static int getScreenHeight() {
        return BaseInfo.getScreenHeight();
    }

    public static int getScreenWidth() {
        return BaseInfo.getScreenWidth();
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static void init(@NonNull Application application) {
        app = application;
        BaseInfo.init(application);
    }

    public static boolean is1Jia() {
        String manufacturer = getManufacturer();
        if (TextUtils.isEmpty(manufacturer)) {
            return false;
        }
        return manufacturer.toUpperCase().contains(ROM_1JIA);
    }

    public static boolean is360() {
        return check("QIKU") || check(ROM_360);
    }

    public static boolean isEmui() {
        return check("EMUI");
    }

    public static boolean isFlyme() {
        return check("FLYME");
    }

    public static boolean isGPSAvailable() {
        return BaseInfo.isGPSAvailable();
    }

    public static boolean isLetv() {
        return check(ROM_LETV);
    }

    public static boolean isMiui() {
        return check("MIUI");
    }

    public static boolean isNFCAvailable() {
        Application application = app;
        return application != null && BaseInfo.isNFCAvailable(application);
    }

    public static boolean isNFCEnabled() {
        return BaseInfo.isNFCEnabled(app);
    }

    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo;
        Application application = app;
        if (application == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Throwable th) {
            JDPayLog.e(th);
        }
        return false;
    }

    public static boolean isOppo() {
        return check("OPPO");
    }

    public static boolean isSmartisan() {
        return check("SMARTISAN");
    }

    public static boolean isVivo() {
        return check("VIVO");
    }
}
