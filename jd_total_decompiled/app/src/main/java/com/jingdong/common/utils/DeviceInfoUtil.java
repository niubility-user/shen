package com.jingdong.common.utils;

import android.app.ActivityManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class DeviceInfoUtil {
    private static final String TAG = "DeviceInfoUtil";

    public static String getCpuInfo() {
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        Pattern compile = Pattern.compile(" [0-9]+");
        BufferedReader bufferedReader = null;
        int i2 = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            jArr[i3] = 0;
            jArr2[i3] = 0;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/stat"), 8192);
                int i4 = 0;
                while (true) {
                    try {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null && (i3 == 0 || i4 < i2)) {
                                if (readLine.toLowerCase().startsWith(PerformanceManager.CUP)) {
                                    i4++;
                                    Matcher matcher = compile.matcher(readLine);
                                    int i5 = 0;
                                    while (matcher.find()) {
                                        try {
                                            long parseLong = Long.parseLong(matcher.group(0).trim());
                                            jArr[i3] = jArr[i3] + parseLong;
                                            if (i5 == 3) {
                                                jArr2[i3] = jArr2[i3] + parseLong;
                                            }
                                            i5++;
                                        } catch (NumberFormatException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                                if (i3 == 0) {
                                    try {
                                        try {
                                            Thread.sleep(50L);
                                        } catch (IOException unused) {
                                            i2 = i4;
                                            bufferedReader = bufferedReader2;
                                            if (bufferedReader != null) {
                                                try {
                                                    bufferedReader.close();
                                                } catch (IOException e3) {
                                                    e3.printStackTrace();
                                                }
                                            }
                                        }
                                    } catch (InterruptedException e4) {
                                        e4.printStackTrace();
                                    }
                                    i2 = i4;
                                }
                            } else {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                                bufferedReader = bufferedReader2;
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                    }
                }
            } catch (IOException unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        double d = -1.0d;
        if (jArr[0] > 0 && jArr[1] > 0 && jArr[0] != jArr[1]) {
            double d2 = (jArr[1] - jArr2[1]) - (jArr[0] - jArr2[0]);
            Double.isNaN(d2);
            double d3 = jArr[1] - jArr[0];
            Double.isNaN(d3);
            d = (d2 * 1.0d) / d3;
        }
        return String.valueOf(Math.round(d * 100.0d));
    }

    public static String getIpAddress() {
        try {
            List<String> netAddressesForIPv4 = BaseInfo.getNetAddressesForIPv4();
            if (netAddressesForIPv4 == null || netAddressesForIPv4.size() <= 0) {
                return null;
            }
            return netAddressesForIPv4.get(0);
        } catch (Exception e2) {
            Log.d(TAG, e2.getMessage());
            return null;
        }
    }

    public static String getMemAppUsed() {
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            return (memoryInfo.getTotalPss() / 1024) + "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getMemTotal() {
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager activityManager = (ActivityManager) JdSdk.getInstance().getApplication().getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return (memoryInfo.totalMem / 1024) + "";
            }
        }
        return "";
    }

    public static String getMemUsed() {
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager activityManager = (ActivityManager) JdSdk.getInstance().getApplication().getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return ((memoryInfo.totalMem - memoryInfo.availMem) / 1024) + "";
            }
        }
        return "";
    }

    public static String getSimId() {
        if (SDKUtils.isSDKVersionMoreThan21()) {
            StringBuilder sb = new StringBuilder();
            try {
                Cursor query = JdSdk.getInstance().getApplication().getContentResolver().query(Uri.parse("content://telephony/siminfo"), new String[]{"sim_id", "icc_id"}, "0=0", new String[0], null);
                if (query != null) {
                    while (query.moveToNext()) {
                        if (query.getInt(query.getColumnIndexOrThrow("sim_id")) != -1) {
                            String string = query.getString(query.getColumnIndex("icc_id"));
                            if (sb.length() > 0) {
                                sb.append(DYConstants.DY_REGEX_COMMA);
                            }
                            sb.append(string);
                            Log.d(TAG, "\u83b7\u53d6icc_id" + string);
                        }
                    }
                    query.close();
                }
            } catch (Exception unused) {
            }
            return sb.toString();
        }
        return BaseInfo.getSimSerialNo();
    }

    private static String intToIp(int i2) {
        return (i2 & 255) + OrderISVUtil.MONEY_DECIMAL + ((i2 >> 8) & 255) + OrderISVUtil.MONEY_DECIMAL + ((i2 >> 16) & 255) + OrderISVUtil.MONEY_DECIMAL + ((i2 >> 24) & 255);
    }

    public static String isLowMem() {
        ActivityManager activityManager = (ActivityManager) JdSdk.getInstance().getApplication().getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.lowMemory ? "1" : "0";
        }
        return "0";
    }
}
