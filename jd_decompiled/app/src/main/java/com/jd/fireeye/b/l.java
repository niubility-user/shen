package com.jd.fireeye.b;

import android.content.Context;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.File;
import java.util.List;

/* loaded from: classes13.dex */
public class l {
    private static String a = "";

    public static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String b(Context context) {
        try {
            return Formatter.formatFileSize(context, CoreInfo.Device.getAvailableInternalMemorySize(context));
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String c() {
        try {
            String cPUMaxFreq = BaseInfo.getCPUMaxFreq();
            if (TextUtils.isEmpty(cPUMaxFreq)) {
                return "";
            }
            return q.a(Double.parseDouble(cPUMaxFreq)) + "GHz";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(Context context) {
        return "";
    }

    public static String d() {
        try {
            String cpuMinFreq = BaseInfo.getCpuMinFreq();
            if (TextUtils.isEmpty(cpuMinFreq)) {
                return "";
            }
            return q.a(Double.parseDouble(cpuMinFreq)) + "GHz";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(Context context) {
        return "";
    }

    public static String e() {
        try {
            return TextUtils.isEmpty(BaseInfo.getCpuName()) ? "unknow" : BaseInfo.getCpuName();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknow";
        }
    }

    public static int f() {
        try {
            if (TextUtils.isEmpty(BaseInfo.getCPUNum())) {
                return 1;
            }
            return Integer.parseInt(BaseInfo.getCPUNum());
        } catch (Exception unused) {
            return 1;
        }
    }

    public static String f(Context context) {
        return "";
    }

    public static String g(Context context) {
        String[] split;
        try {
            String displayMetrics = BaseInfo.getDisplayMetrics();
            if (!TextUtils.isEmpty(displayMetrics) && (split = displayMetrics.split("\\*")) != null && split.length == 2) {
                String str = split[0];
                String str2 = split[1];
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    return str2 + ProxyConfig.MATCH_ALL_SCHEMES + str;
                }
                return "";
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String h() {
        if (a()) {
            try {
                String sDCardId = BaseInfo.getSDCardId();
                return TextUtils.isEmpty(sDCardId) ? "unknow" : sDCardId;
            } catch (Exception unused) {
            }
        }
        return "unknow";
    }

    public static String i() {
        return "unknown";
    }

    public static String i(Context context) {
        try {
            return Formatter.formatFileSize(context, BaseInfo.getRomSize());
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String j(Context context) {
        try {
            return Formatter.formatFileSize(context, CoreInfo.Device.getMemState(context));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String k(Context context) {
        if (com.jd.fireeye.security.c.f2639c) {
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    a = WebSettings.getDefaultUserAgent(context);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return a;
        }
        return "";
    }

    public static void a(Context context) {
        if (com.jd.fireeye.security.c.f2639c) {
            try {
                if (Build.VERSION.SDK_INT < 17) {
                    a = new WebView(context).getSettings().getUserAgentString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String b() {
        try {
            String cpuCurFreq = BaseInfo.getCpuCurFreq();
            if (!TextUtils.isEmpty(cpuCurFreq)) {
                return q.a(Double.parseDouble(cpuCurFreq)) + "GHz";
            }
        } catch (Exception unused) {
        }
        return q.a(0.0d) + "GHz";
    }

    public static boolean j() {
        try {
            if (!new File("/system/bin/su").exists() && !new File("/system/xbin/su").exists() && !new File("/su/bin/su").exists()) {
                if (!new File("/su/xbin/su").exists()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String e(Context context) {
        try {
            return BaseInfo.getNetworkISO(context);
        } catch (Exception unused) {
            return "";
        }
    }

    public static List<Sensor> h(Context context) {
        return BaseInfo.getSensorList(context);
    }

    public static String g() {
        String netAddressInfo;
        try {
            netAddressInfo = BaseInfo.getNetAddressInfo();
        } catch (Exception unused) {
        }
        return !TextUtils.isEmpty(netAddressInfo) ? netAddressInfo : "";
    }
}
