package com.tencent.map.tools.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressLint({"MissingPermission"})
/* loaded from: classes9.dex */
public class NetUtil {
    private static final int BUF_SIZE = 4096;
    private static final String CMWAP = "cmwap";
    private static final String CTWAP = "ctwap";
    public static final int DEFAULT_TIME_OUT = 10000;
    public static final String MAP_USER_AGENT = "QQ Map Mobile";
    private static final String NET_3G = "3gnet";
    public static final String STR_UserAgent = "androidsdk";
    public static final int TYPE_3GWAP = 3;
    public static final int TYPE_CMWAP = 1;
    public static final int TYPE_CTWAP = 4;
    public static final int TYPE_NET_WORK_DISABLED = 0;
    public static final int TYPE_OTHER_NET = 6;
    public static final int TYPE_UNIWAP = 2;
    public static final int TYPE_WIFI = 5;
    private static final String UNIWAP = "uniwap";
    private static final String WAP_3G = "3gwap";
    public static final String WIFI = "wifi";

    public static int getNetType(Context context) {
        return getNetType(getNetworkInfo(context));
    }

    private static int getNetType(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return 0;
        }
        int type = networkInfo.getType();
        if (type == 1) {
            return 5;
        }
        if (type != 0) {
            return 6;
        }
        String extraInfo = networkInfo.getExtraInfo();
        if (TextUtils.isEmpty(extraInfo)) {
            return 6;
        }
        if (extraInfo.equalsIgnoreCase(CMWAP)) {
            return 1;
        }
        if (extraInfo.equalsIgnoreCase(WAP_3G)) {
            return 3;
        }
        if (extraInfo.equalsIgnoreCase(UNIWAP)) {
            return 2;
        }
        return extraInfo.equalsIgnoreCase(CTWAP) ? 4 : 3;
    }

    public static String getNetTypeStr(Context context) {
        int netType = getNetType(context);
        return netType != 1 ? netType != 2 ? netType != 3 ? netType != 4 ? netType != 5 ? "" : "wifi" : CTWAP : WAP_3G : UNIWAP : CMWAP;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    return "wifi";
                }
                if (type == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                            return "2g";
                        case 3:
                            return "3g";
                        case 4:
                            return "2g";
                        case 5:
                        case 6:
                        case 8:
                            return "3g";
                        case 7:
                        default:
                            return "other";
                    }
                }
                return "other";
            }
        } catch (Exception unused) {
        }
        return "unknown";
    }

    public static boolean isMobile(Context context) {
        int netType = getNetType(context);
        return (netType == 0 || netType == 5) ? false : true;
    }

    public static boolean isNetAvailable(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            networkInfo = getNetworkInfo(context);
        } catch (Exception unused) {
        }
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        for (NetworkInfo networkInfo2 : connectivityManager.getAllNetworkInfo()) {
            if (networkInfo2.isAvailable() && networkInfo2.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifi(Context context) {
        return getNetType(context) == 5;
    }

    public static final void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] toBytes(InputStream inputStream) {
        try {
            return toBytesThrow(inputStream);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] toBytesThrow(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (inputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr, 0, 4096);
                if (read == -1) {
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    safeClose(byteArrayOutputStream);
                    return byteArray;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            safeClose(byteArrayOutputStream2);
            throw th;
        }
    }

    public static void writeBytesWithoutClose(byte[] bArr, OutputStream outputStream) {
        if (bArr == null || bArr.length == 0 || outputStream == null) {
            return;
        }
        try {
            outputStream.write(bArr);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
