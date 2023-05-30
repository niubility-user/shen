package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class NetUtils {
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_4G = "4g";
    public static final String NETWORK_TYPE_5G = "5g";
    public static final String NETWORK_TYPE_UNKNOWN = "UNKNOWN";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String TAG = "NetUtils";
    public static int currentNetType = 0;
    private static String current_type = "";
    private static boolean isOffNetwork = false;
    public static boolean isProxy = true;

    /* loaded from: classes14.dex */
    public static class NetworkUtilFactory {
        public static NetworkInfo getActiveNetworkInfo(ConnectivityManager connectivityManager) {
            if (connectivityManager == null) {
                return null;
            }
            try {
                return connectivityManager.getActiveNetworkInfo();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static ConnectivityManager getConnectivityManager() {
            try {
                return (ConnectivityManager) JDHttpTookit.getEngine().getApplicationContext().getSystemService("connectivity");
            } catch (Exception e2) {
                e2.printStackTrace();
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
                e2.printStackTrace();
                return null;
            }
        }

        @Deprecated
        public static int getNetworkType(TelephonyManager telephonyManager) {
            return -1;
        }

        public static TelephonyManager getTelephonyManager() {
            try {
                return (TelephonyManager) JDHttpTookit.getEngine().getApplicationContext().getSystemService(SignUpTable.TB_COLUMN_PHONE);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    @Deprecated
    /* loaded from: classes14.dex */
    public static class SimInfo {
        public String networkOperator;
        public String networkOperatorName;
        public String networkType;
        public String networkTypeName;
        public Integer simState;
    }

    public static String getCurrentMicrosecond() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format("%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    public static String getCurrentType() {
        return current_type;
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

    @Deprecated
    public static String getNetworkOperator() {
        return getNetType().getNetworkOperator();
    }

    @Deprecated
    public static String getNetworkOperatorName() {
        return getNetType().getNetworkOperatorName();
    }

    @Deprecated
    public static String getNetworkType(Context context) {
        return getNetworkType();
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

    @Deprecated
    public static boolean is2GNetwork(Context context) {
        return false;
    }

    @Deprecated
    public static boolean is3GOr2GNetwork() {
        return getNetType().summaryType == 2;
    }

    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager = NetworkUtilFactory.getConnectivityManager();
            if (OKLog.D) {
                OKLog.d(TAG, " isNetworkAvailable -->> connectivityManager " + connectivityManager);
            }
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = NetworkUtilFactory.getActiveNetworkInfo(connectivityManager);
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            if (OKLog.D) {
                OKLog.d(TAG, " isNetworkAvailable -->> result " + z);
            }
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isOffNetwork() {
        return isOffNetwork;
    }

    public static boolean isWifi() {
        return getNetType().summaryType == 1;
    }

    public static boolean isWifiForLoadImage() {
        return currentNetType == 1;
    }

    public static void setCurrentType(String str) {
        current_type = str;
    }

    public static void setOffNetwork(boolean z) {
        isOffNetwork = z;
    }

    public static void updateNetType() {
        currentNetType = getNetType().summaryType;
    }

    @Deprecated
    public static String getNetworkType() {
        NetType netType = getNetType();
        if (netType.summaryType == 1) {
            return "wifi";
        }
        if (netType.summaryType == 2) {
        }
        return "UNKNOWN";
    }

    /* loaded from: classes14.dex */
    public static class NetType {
        public static final int NSP_CHINA_MOBILE = 1;
        public static final int NSP_CHINA_TELECOM = 3;
        public static final int NSP_CHINA_UNICOM = 2;
        public static final int NSP_NO = -1;
        public static final int NSP_OTHER = 0;
        public static final int SUMMARY_TYPE_MOBILE = 2;
        public static final int SUMMARY_TYPE_OTHER = 0;
        public static final int SUMMARY_TYPE_WIFI = 1;
        private static SimInfo simInfo;
        private String extraInfo;
        String proxyHost;
        Integer proxyPort;
        private int summaryType;

        public NetType(int i2, String str) {
            this.summaryType = 0;
            this.summaryType = i2;
            this.extraInfo = str;
        }

        @Deprecated
        public String getDetailType() {
            return "";
        }

        @Deprecated
        public synchronized int getNSP() {
            return -1;
        }

        @Deprecated
        public synchronized String getNetworkOperator() {
            return "";
        }

        @Deprecated
        public synchronized String getNetworkOperatorName() {
            return "UNKNOWN";
        }

        @Deprecated
        public String getNetworkTypeName(int i2) {
            return "UNKNOWN";
        }

        public String getProxyHost() {
            String defaultHost = Proxy.getDefaultHost();
            if (OKLog.D) {
                OKLog.d(NetUtils.TAG, "getProxyHost() proxyHost -->> " + defaultHost);
            }
            if (1 == this.summaryType) {
                if (OKLog.D) {
                    OKLog.d(NetUtils.TAG, "getProxyHost() WIFI -->> ");
                    return null;
                }
                return null;
            }
            if (OKLog.D) {
                OKLog.d(NetUtils.TAG, "getProxyHost() else -->> ");
            }
            this.proxyHost = defaultHost;
            this.proxyPort = Integer.valueOf(Proxy.getDefaultPort());
            if (OKLog.D) {
                OKLog.d(NetUtils.TAG, "getProxyHost() proxyHost -->> " + defaultHost);
            }
            return this.proxyHost;
        }

        public Integer getProxyPort() {
            return this.proxyPort;
        }

        @Deprecated
        public synchronized String getUploadType() {
            return "";
        }

        public NetType() {
            this.summaryType = 0;
        }
    }

    public static NetType getNetType() {
        return getNetType(JDHttpTookit.getEngine().getApplicationContext());
    }
}
