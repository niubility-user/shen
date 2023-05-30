package com.jingdong.sdk.jdhttpdns.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdhttpdns.core.HttpDnsImpl;

/* loaded from: classes7.dex */
public class NetUtils {
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_4G = "4g";
    public static final String NETWORK_TYPE_UNKNOWN = "UNKNOWN";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String TAG = "NetUtils";
    public static int currentNetType;

    /* loaded from: classes7.dex */
    public static class NetworkUtilFactory {
        public static NetworkInfo getActiveNetworkInfo(ConnectivityManager connectivityManager) {
            if (connectivityManager == null) {
                return null;
            }
            try {
                return connectivityManager.getActiveNetworkInfo();
            } catch (Exception e2) {
                DNSLog.e(NetUtils.TAG, e2);
                return null;
            }
        }

        public static ConnectivityManager getConnectivityManager() {
            try {
                return (ConnectivityManager) HttpDnsImpl.applicationContext.getApplicationContext().getSystemService("connectivity");
            } catch (Exception e2) {
                DNSLog.e(NetUtils.TAG, e2);
                return null;
            }
        }

        public static NetworkInfo getNetworkInfo(ConnectivityManager connectivityManager, int i2) {
            if (connectivityManager == null) {
                return null;
            }
            try {
                return connectivityManager.getNetworkInfo(i2);
            } catch (Exception e2) {
                DNSLog.e(NetUtils.TAG, e2);
                return null;
            }
        }

        @SuppressLint({"WrongConstant"})
        public static int getNetworkType(TelephonyManager telephonyManager) {
            if (telephonyManager == null) {
                return -1;
            }
            try {
                return BaseInfo.getNetworkTypeInt();
            } catch (Exception e2) {
                DNSLog.e(NetUtils.TAG, e2);
                return -1;
            }
        }

        public static TelephonyManager getTelephonyManager() {
            try {
                return (TelephonyManager) HttpDnsImpl.applicationContext.getApplicationContext().getSystemService(SignUpTable.TB_COLUMN_PHONE);
            } catch (Exception e2) {
                DNSLog.e(NetUtils.TAG, e2);
                return null;
            }
        }
    }

    public static String getExtraInfo(NetworkInfo networkInfo) {
        return networkInfo == null ? "" : networkInfo.getExtraInfo();
    }

    public static NetType getNetType(Context context) {
        NetType netType = new NetType();
        if (isNetworkAvailable()) {
            ConnectivityManager connectivityManager = NetworkUtilFactory.getConnectivityManager();
            int summaryType = getSummaryType(connectivityManager);
            currentNetType = summaryType;
            return new NetType(summaryType, getExtraInfo(NetworkUtilFactory.getActiveNetworkInfo(connectivityManager)));
        }
        return netType;
    }

    public static String getNetworkType() {
        TelephonyManager telephonyManager;
        NetType netType = getNetType();
        if (netType.summaryType == 1) {
            return "wifi";
        }
        if (netType.summaryType != 2 || (telephonyManager = NetworkUtilFactory.getTelephonyManager()) == null) {
            return "UNKNOWN";
        }
        switch (NetworkUtilFactory.getNetworkType(telephonyManager)) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "3g";
            case 13:
            case 18:
                return "4g";
        }
        return "UNKNOWN";
    }

    public static int getSummaryType(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = NetworkUtilFactory.getNetworkInfo(connectivityManager, 0);
        NetworkInfo.State state = networkInfo != null ? networkInfo.getState() : null;
        NetworkInfo networkInfo2 = NetworkUtilFactory.getNetworkInfo(connectivityManager, 1);
        NetworkInfo.State state2 = networkInfo2 != null ? networkInfo2.getState() : null;
        if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
            return 1;
        }
        return (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) ? 2 : 0;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = NetworkUtilFactory.getConnectivityManager();
        if (DNSLog.D) {
            DNSLog.d(TAG, " isNetworkAvailable -->> connectivityManager " + connectivityManager);
        }
        boolean z = false;
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = NetworkUtilFactory.getActiveNetworkInfo(connectivityManager);
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if (DNSLog.D) {
            DNSLog.d(TAG, " isNetworkAvailable -->> result " + z2);
        }
        if (z2) {
            return z2;
        }
        try {
            Thread.sleep(100L);
        } catch (Exception unused) {
        }
        ConnectivityManager connectivityManager2 = NetworkUtilFactory.getConnectivityManager();
        if (connectivityManager2 == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo2 = NetworkUtilFactory.getActiveNetworkInfo(connectivityManager2);
        if (activeNetworkInfo2 != null && activeNetworkInfo2.isConnectedOrConnecting()) {
            z = true;
        }
        if (DNSLog.D) {
            DNSLog.d(TAG, " isNetworkAvailable -->> retry result " + z);
        }
        return z;
    }

    /* loaded from: classes7.dex */
    public static class NetType {
        public static final int SUMMARY_TYPE_MOBILE = 2;
        public static final int SUMMARY_TYPE_OTHER = 0;
        public static final int SUMMARY_TYPE_WIFI = 1;
        private String extraInfo;
        private int summaryType;

        public NetType(int i2, String str) {
            this.summaryType = 0;
            this.summaryType = i2;
            this.extraInfo = str;
        }

        public NetType() {
            this.summaryType = 0;
        }
    }

    public static NetType getNetType() {
        return getNetType(HttpDnsImpl.applicationContext.getApplicationContext());
    }
}
