package com.jingdong.manto.utils;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.manto.sdk.api.IHostBaseInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

/* loaded from: classes16.dex */
public class q {
    public static int a() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            MantoLog.e("DeviceUtils", th);
            return -1;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "UNKNOWN_TEXT";
        }
        List<String> e2 = e();
        return e2.size() > 0 ? e2.get(0) : "UNKNOWN_TEXT";
    }

    public static String b() {
        try {
            IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
            if (iHostBaseInfo != null) {
                return iHostBaseInfo.getDeviceBrand().replaceAll(LangUtils.SINGLE_SPACE, "");
            }
        } catch (Throwable unused) {
        }
        try {
            return BaseInfo.getDeviceBrand().replaceAll(LangUtils.SINGLE_SPACE, "");
        } catch (Throwable th) {
            MantoLog.e("DeviceUtils", th);
            return "fail";
        }
    }

    public static String c() {
        WindowManager windowManager = (WindowManager) com.jingdong.manto.b.f().getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point2 = new Point();
            defaultDisplay.getSize(point2);
            return String.format(Locale.getDefault(), "%d*%d", Integer.valueOf(point2.y), Integer.valueOf(point2.x));
        }
        return "0*0";
    }

    public static String d() {
        try {
            IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
            if (iHostBaseInfo != null) {
                return iHostBaseInfo.getDeviceModel().replaceAll(LangUtils.SINGLE_SPACE, "");
            }
        } catch (Throwable unused) {
        }
        try {
            return BaseInfo.getDeviceModel().replaceAll(LangUtils.SINGLE_SPACE, "");
        } catch (Throwable th) {
            MantoLog.e("DeviceUtils", th);
            return "fail";
        }
    }

    static List<String> e() {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        String hostAddress = nextElement.getHostAddress();
                        if (!TextUtils.isEmpty(hostAddress) && (nextElement instanceof Inet4Address)) {
                            arrayList.add(hostAddress);
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    public static String f() {
        try {
            return Build.VERSION.RELEASE.replaceAll(LangUtils.SINGLE_SPACE, "");
        } catch (Throwable th) {
            MantoLog.e("DeviceUtils", th);
            return "unknown";
        }
    }

    public static String g() {
        NetworkInfo activeNetworkInfo;
        String str;
        Context f2 = com.jingdong.manto.b.f();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) f2.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "Unknown";
            }
            if (activeNetworkInfo.getType() != 1) {
                TelephonyManager telephonyManager = (TelephonyManager) f2.getSystemService(SignUpTable.TB_COLUMN_PHONE);
                if (activeNetworkInfo.getType() == 0 && telephonyManager != null) {
                    int networkTypeInt = BaseInfo.getNetworkTypeInt();
                    switch (networkTypeInt) {
                        case 1:
                            str = "GPRS";
                            break;
                        case 2:
                            str = "EDGE";
                            break;
                        case 3:
                            str = "UMTS";
                            break;
                        case 4:
                            str = "CDMA";
                            break;
                        case 5:
                            str = "EVDO_0";
                            break;
                        case 6:
                            str = "EVDO_A";
                            break;
                        case 7:
                            str = "1xRTT";
                            break;
                        case 8:
                            str = "HSDPA";
                            break;
                        case 9:
                            str = "HSUPA";
                            break;
                        case 10:
                            str = "HSPA";
                            break;
                        case 11:
                            str = "iDen";
                            break;
                        case 12:
                            str = "EVDO_B";
                            break;
                        case 13:
                            str = "LTE";
                            break;
                        case 14:
                            str = "eHRPD";
                            break;
                        case 15:
                            str = "HSPA+";
                            break;
                        default:
                            str = "MOBILE(" + networkTypeInt + ")";
                            break;
                    }
                } else {
                    return "Unknown";
                }
            } else {
                str = "WiFi";
            }
            return str;
        } catch (Exception e2) {
            MantoLog.e("DeviceUtils", e2);
            return "Unknown";
        }
    }
}
