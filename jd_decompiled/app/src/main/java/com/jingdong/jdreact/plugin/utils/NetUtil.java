package com.jingdong.jdreact.plugin.utils;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes14.dex */
public class NetUtil {
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_4G = "4g";
    public static final String NETWORK_TYPE_UNKNOWN = "UNKNOWN";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String TAG = "NetUtil";

    /* loaded from: classes14.dex */
    public static class SimInfo {
        public String networkOperator;
        public String networkOperatorName;
        public String networkType;
        public String networkTypeName;
        public Integer simState;
    }

    public static NetworkInfo getActiveNetworkInfo(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return null;
        }
        try {
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception e2) {
            LogUtil.e(TAG, e2);
            return null;
        }
    }

    public static ConnectivityManager getConnectivityManager() {
        try {
            return (ConnectivityManager) JDReactHelper.newInstance().getApplicationContext().getSystemService("connectivity");
        } catch (Exception e2) {
            LogUtil.e(TAG, e2);
            return null;
        }
    }

    public static String getExtraInfo(NetworkInfo networkInfo) {
        return networkInfo == null ? "" : networkInfo.getExtraInfo();
    }

    public static NetType getNetType() {
        NetType netType = new NetType();
        if (isNetworkAvailable()) {
            ConnectivityManager connectivityManager = getConnectivityManager();
            return new NetType(getSummaryType(connectivityManager), getExtraInfo(getActiveNetworkInfo(connectivityManager)));
        }
        return netType;
    }

    public static NetworkInfo getNetworkInfo(ConnectivityManager connectivityManager, int i2) {
        if (connectivityManager == null) {
            return null;
        }
        try {
            return connectivityManager.getNetworkInfo(i2);
        } catch (Exception e2) {
            LogUtil.e(TAG, e2);
            return null;
        }
    }

    @SuppressLint({"MissingPermission", "WrongConstant"})
    public static int getNetworkType(TelephonyManager telephonyManager) {
        int networkTypeInt;
        if (telephonyManager == null) {
            return -1;
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                networkTypeInt = telephonyManager.getDataNetworkType();
            } else {
                networkTypeInt = BaseInfo.getNetworkTypeInt();
            }
            return networkTypeInt;
        } catch (Exception e2) {
            LogUtil.e(TAG, e2);
            return -1;
        }
    }

    public static int getSummaryType(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = getNetworkInfo(connectivityManager, 0);
        NetworkInfo.State state = networkInfo != null ? networkInfo.getState() : null;
        NetworkInfo networkInfo2 = getNetworkInfo(connectivityManager, 1);
        NetworkInfo.State state2 = networkInfo2 != null ? networkInfo2.getState() : null;
        if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
            return 1;
        }
        return (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) ? 2 : 0;
    }

    public static TelephonyManager getTelephonyManager() {
        try {
            return (TelephonyManager) JDReactHelper.newInstance().getApplicationContext().getSystemService(SignUpTable.TB_COLUMN_PHONE);
        } catch (Exception e2) {
            LogUtil.e(TAG, e2);
            return null;
        }
    }

    public static boolean is3GOr2GNetwork() {
        return getNetType().summaryType == 2;
    }

    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(getConnectivityManager());
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        LogUtil.d(TAG, " isNetworkAvailable -->> result " + z);
        if (z) {
            return z;
        }
        try {
            Thread.sleep(100L);
        } catch (Exception unused) {
        }
        NetworkInfo activeNetworkInfo2 = getActiveNetworkInfo(getConnectivityManager());
        boolean z2 = activeNetworkInfo2 != null && activeNetworkInfo2.isConnectedOrConnecting();
        LogUtil.d(TAG, " isNetworkAvailable -->> retry result " + z2);
        return z2;
    }

    public static boolean isWifi() {
        return getNetType().summaryType == 1;
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
        private int summaryType;

        public NetType(int i2, String str) {
            this.summaryType = 0;
            this.summaryType = i2;
            this.extraInfo = str;
            getSimAndOperatorInfo();
        }

        private synchronized void getSimAndOperatorInfo() {
            if (simInfo == null) {
                simInfo = new SimInfo();
                TelephonyManager telephonyManager = NetUtil.getTelephonyManager();
                if (telephonyManager == null) {
                    return;
                }
                SimInfo simInfo2 = new SimInfo();
                simInfo = simInfo2;
                simInfo2.simState = Integer.valueOf(telephonyManager.getSimState());
                simInfo.networkOperatorName = JDReactHelper.newInstance().getNetworkOperatorName(JDReactHelper.newInstance().getApplicationContext());
                simInfo.networkOperator = JDReactHelper.newInstance().getNetworkOperator(JDReactHelper.newInstance().getApplicationContext());
                int networkType = NetUtil.getNetworkType(telephonyManager);
                simInfo.networkType = "" + networkType;
                simInfo.networkTypeName = getNetworkTypeName(networkType);
            }
        }

        public synchronized int getNSP() {
            Integer num;
            SimInfo simInfo2 = simInfo;
            if (simInfo2 != null && (num = simInfo2.simState) != null && num.intValue() != 0) {
                if (TextUtils.isEmpty(simInfo.networkOperatorName) && TextUtils.isEmpty(simInfo.networkOperator)) {
                    return -1;
                }
                if (!"\u4e2d\u56fd\u79fb\u52a8".equalsIgnoreCase(simInfo.networkOperatorName) && !"CMCC".equalsIgnoreCase(simInfo.networkOperatorName) && !"46000".equalsIgnoreCase(simInfo.networkOperator) && !"China Mobile".equalsIgnoreCase(simInfo.networkOperatorName)) {
                    if (!"\u4e2d\u56fd\u7535\u4fe1".equalsIgnoreCase(simInfo.networkOperatorName) && !"China Telecom".equalsIgnoreCase(simInfo.networkOperatorName) && !"46003".equalsIgnoreCase(simInfo.networkOperator)) {
                        if (!"\u4e2d\u56fd\u8054\u901a".equalsIgnoreCase(simInfo.networkOperatorName) && !"China Unicom".equalsIgnoreCase(simInfo.networkOperatorName) && !"46001".equalsIgnoreCase(simInfo.networkOperator)) {
                            if (!"CU-GSM".equalsIgnoreCase(simInfo.networkOperatorName)) {
                                return 0;
                            }
                        }
                        return 2;
                    }
                    return 3;
                }
                return 1;
            }
            return -1;
        }

        public synchronized String getNetworkOperator() {
            SimInfo simInfo2;
            simInfo2 = simInfo;
            return simInfo2 == null ? "" : simInfo2.networkOperator;
        }

        public synchronized String getNetworkOperatorName() {
            SimInfo simInfo2;
            simInfo2 = simInfo;
            return simInfo2 == null ? "UNKNOWN" : simInfo2.networkOperatorName;
        }

        @SuppressLint({"NewApi"})
        public String getNetworkTypeName(int i2) {
            switch (i2) {
                case 1:
                    return "GPRS";
                case 2:
                    return "EDGE";
                case 3:
                    return "UMTS";
                case 4:
                    return "CDMA";
                case 5:
                    return "CDMA - EvDo rev. 0";
                case 6:
                    return "CDMA - EvDo rev. A";
                case 7:
                    return "CDMA - 1xRTT";
                case 8:
                    return "HSDPA";
                case 9:
                    return "HSUPA";
                case 10:
                    return "HSPA";
                default:
                    return "UNKNOWN";
            }
        }

        public synchronized String getUploadType() {
            SimInfo simInfo2 = simInfo;
            if (simInfo2 != null) {
                return simInfo2.networkType;
            }
            return "";
        }

        public NetType() {
            this.summaryType = 0;
        }
    }

    public static String getNetworkType() {
        return JDReactHelper.newInstance().getNetworkType();
    }
}
