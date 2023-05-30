package com.wjlogin.onekey.sdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes11.dex */
public class MobileDeviceUtil {
    private static final String a = "WJLogin.OneKey.MobileDeviceUtil";

    public static boolean canGoToTelecom(Context context) {
        return getSimiState(context) && getDefaultDataSubId(context).intValue() != -1 && "CT".equals(getOperateType(context)) && dataEnable(context);
    }

    public static boolean canGoToUnicom(Context context) {
        return getSimiState(context) && getDefaultDataSubId(context).intValue() != -1 && "CU".equals(getOperateType(context)) && dataEnable(context);
    }

    public static boolean dataEnable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    if (LogUtil.enableLog) {
                        LogUtil.LogI(a, "WIFI");
                    }
                    boolean z = context.getPackageManager().checkPermission("android.permission.CHANGE_NETWORK_STATE", context.getPackageName()) == 0;
                    if (LogUtil.enableLog) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("CHANGE_NETWORK_STATE checkPermisson=");
                        sb.append(z);
                        LogUtil.LogI(a, sb.toString());
                    }
                    if (z && isMobileEnableReflex(connectivityManager)) {
                        if (LogUtil.enableLog) {
                            LogUtil.LogI(a, "\u6d41\u91cf\u6570\u636e WIFI \u540c\u5f00");
                        }
                        return true;
                    }
                    return false;
                }
                if (type == 0) {
                    if (LogUtil.enableLog) {
                        LogUtil.LogI(a, "\u6d41\u91cf");
                    }
                    NetworkInfo.State state = connectivityManager.getNetworkInfo(0).getState();
                    if (LogUtil.enableLog) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("TYPE_MOBILE State= ");
                        sb2.append(state);
                        LogUtil.LogI(a, sb2.toString());
                    }
                    if (NetworkInfo.State.CONNECTED == state) {
                        if (LogUtil.enableLog) {
                            LogUtil.LogI(a, "\u6d41\u91cf enable");
                        }
                        return true;
                    }
                }
                return false;
            }
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, "!networkInfo.isAvailable()");
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static Integer getDefaultDataSubId(Context context) {
        int i2 = -1;
        try {
            if (Build.VERSION.SDK_INT >= 22) {
                SubscriptionManager from = SubscriptionManager.from(context.getApplicationContext());
                Method method = from.getClass().getMethod("getDefaultDataSubId", new Class[0]);
                if (method != null) {
                    i2 = Integer.valueOf(((Integer) method.invoke(from, new Object[0])).intValue());
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            try {
                try {
                    if (Build.VERSION.SDK_INT >= 22) {
                        SubscriptionManager from2 = SubscriptionManager.from(context.getApplicationContext());
                        Method method2 = from2.getClass().getMethod("getDefaultDataSubscrptionId", new Class[0]);
                        if (method2 != null) {
                            i2 = Integer.valueOf(((Integer) method2.invoke(from2, new Object[0])).intValue());
                        }
                    }
                } catch (IllegalAccessException unused) {
                    e3.printStackTrace();
                } catch (NoSuchMethodException unused2) {
                    e3.printStackTrace();
                } catch (InvocationTargetException unused3) {
                    e3.printStackTrace();
                } catch (Exception unused4) {
                    e3.printStackTrace();
                }
            } catch (IllegalAccessException unused5) {
                e3.printStackTrace();
            } catch (NoSuchMethodException unused6) {
                if (Build.VERSION.SDK_INT >= 22) {
                    SubscriptionManager from3 = SubscriptionManager.from(context.getApplicationContext());
                    Method method3 = from3.getClass().getMethod("getDefaultDataPhoneId", new Class[0]);
                    if (method3 != null) {
                        i2 = Integer.valueOf(((Integer) method3.invoke(from3, new Object[0])).intValue());
                        StringBuilder sb = new StringBuilder();
                        sb.append(((Integer) method3.invoke(from3, new Object[0])).intValue());
                        sb.append("");
                        sb.toString();
                    }
                }
            } catch (InvocationTargetException unused7) {
                e3.printStackTrace();
            } catch (Exception unused8) {
                e3.printStackTrace();
            }
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, "getDefaultDataSubId id= " + i2);
        }
        return i2;
    }

    public static String getOperateType(Context context) {
        String str = "";
        try {
            String simOperator = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperator();
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("NetworkOperator = ");
                sb.append(simOperator);
                LogUtil.LogI(a, sb.toString());
            }
            if (TextUtils.isEmpty(simOperator)) {
                return "";
            }
            if (simOperator.startsWith("46000") || simOperator.startsWith("46002") || simOperator.startsWith("46007")) {
                str = "CM";
                if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "Operator = \u79fb\u52a8 ");
                }
            }
            if (simOperator.startsWith("46001") || simOperator.startsWith("46006") || simOperator.startsWith("46009")) {
                str = "CU";
                if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "Operator = \u8054\u901a ");
                }
            }
            if (simOperator.startsWith("46003") || simOperator.startsWith("46005") || simOperator.startsWith("46011")) {
                str = "CT";
                if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "Operator = \u7535\u4fe1 ");
                }
            }
            return str;
        } catch (Exception unused) {
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, "ismi =\u83b7\u53d6\u8fd0\u8425\u5546\u4fe1\u606f\u5f02\u5e38 ");
            }
            return str;
        }
    }

    public static boolean getSimiState(Context context) {
        try {
            int simState = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimState();
            if (simState != 0) {
                if (simState != 1) {
                    if (simState != 2) {
                        if (simState != 3) {
                            if (simState != 4) {
                                if (simState == 5) {
                                    if (LogUtil.enableLog) {
                                        LogUtil.LogI(a, "\u826f\u597d");
                                        return true;
                                    }
                                    return true;
                                }
                            } else if (LogUtil.enableLog) {
                                LogUtil.LogI(a, "\u9700\u8981NetworkPIN\u89e3\u9501");
                            }
                        } else if (LogUtil.enableLog) {
                            LogUtil.LogI(a, "\u9700\u8981PUK\u89e3\u9501");
                        }
                    } else if (LogUtil.enableLog) {
                        LogUtil.LogI(a, "\u9700\u8981PIN\u89e3\u9501");
                    }
                } else if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "\u65e0\u5361");
                }
            } else if (LogUtil.enableLog) {
                LogUtil.LogI(a, "\u672a\u77e5\u72b6\u6001");
            }
        } catch (Exception unused) {
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, "\u53d6sim\u7684\u72b6\u6001\u5f02\u5e38");
            }
        }
        return false;
    }

    public static boolean isMobileEnableReflex(ConnectivityManager connectivityManager) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Exception unused) {
            return false;
        }
    }
}
