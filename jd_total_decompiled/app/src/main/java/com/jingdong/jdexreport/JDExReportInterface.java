package com.jingdong.jdexreport;

import android.content.Context;
import com.jingdong.jdexreport.a.a.d;
import com.jingdong.jdexreport.broadcast.UserChangedListener;
import com.jingdong.jdexreport.d.a;
import com.jingdong.jdexreport.e.b;
import com.jingdong.jdexreport.e.e;
import com.jingdong.jdexreport.einterface.InitCommonInfo;
import com.jingdong.jdexreport.record.JDExReportDbImpl;
import java.util.HashMap;

/* loaded from: classes.dex */
public class JDExReportInterface {
    public static JDExReportDbImpl mCore;

    private static void addPrivateData(Context context, HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return;
        }
        hashMap.put("net", d.b(context));
    }

    public static void destroy() {
        try {
            JDExReportDbImpl.destroyInstance();
            mCore = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static synchronized JDExReportDbImpl getCore(Context context, InitCommonInfo initCommonInfo) throws Throwable {
        JDExReportDbImpl jDExReportDbImpl;
        synchronized (JDExReportInterface.class) {
            if (context != null) {
                if (mCore == null) {
                    mCore = JDExReportDbImpl.getInstance(context, initCommonInfo);
                }
                jDExReportDbImpl = mCore;
                if (jDExReportDbImpl == null) {
                    throw new Exception("find some exception when get core..");
                }
            } else {
                throw new Exception("context is null");
            }
        }
        return jDExReportDbImpl;
    }

    public static long getDBDataCount() {
        return JDExReportDbImpl.getDBDataCount();
    }

    public static String getEncryptLoginUserName(String str) {
        return b.a(str);
    }

    public static long getLimitCnt(Context context) {
        e a = e.a(context);
        if (a.g()) {
            return a.d();
        }
        return 0L;
    }

    public static long getLimitInt(Context context) {
        e a = e.a(context);
        if (a.g()) {
            return a.e();
        }
        return 0L;
    }

    public static void init(Context context, InitCommonInfo initCommonInfo) {
        if (mCore == null) {
            try {
                getCore(context, initCommonInfo);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean isNumeric(String str) {
        char charAt;
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
            charAt = str.charAt(length);
            if (charAt < '0') {
                return false;
            }
        } while (charAt <= '9');
        return false;
    }

    public static boolean sendData(Context context, InitCommonInfo initCommonInfo, HashMap<String, String> hashMap) {
        if (context == null || initCommonInfo == null || hashMap == null) {
            return false;
        }
        if (mCore == null) {
            try {
                getCore(context, initCommonInfo);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        if (mCore == null) {
            return false;
        }
        addPrivateData(context, hashMap);
        a.b().a(new com.jingdong.jdexreport.g.a(mCore, hashMap));
        return true;
    }

    public static void setPin(String str) {
        b.f12597m = str;
    }

    public static void setProductHost(boolean z) {
        com.jingdong.jdexreport.a.a.a.a = z;
    }

    public static void setShowLog(boolean z) {
        com.jingdong.jdexreport.a.a.a.d = z;
    }

    public static void setUseChangedListener(UserChangedListener userChangedListener) {
        JDExReportDbImpl jDExReportDbImpl = mCore;
        if (jDExReportDbImpl != null) {
            jDExReportDbImpl.setUserChangedListener(userChangedListener);
        }
    }
}
