package com.cmic.sso.sdk.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;

/* loaded from: classes.dex */
public class j {
    @SuppressLint({"StaticFieldLeak"})
    private static j b;
    private final Context a;

    private j(Context context) {
        this.a = context;
    }

    public static void a(Context context) {
        b = new j(context);
    }

    public String b() {
        try {
            int a = com.cmic.sso.sdk.b.a.a().b().a();
            return a >= 0 ? Integer.toString(a) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        if (telephonyManager != null) {
            String simOperator = telephonyManager.getSimOperator();
            c.b("SIMUtils", "SysOperator= " + simOperator);
            return simOperator;
        }
        return "";
    }

    public static j a() {
        return b;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = c();
        }
        return b(str);
    }

    private String b(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 49679470:
                if (str.equals("46000")) {
                    c2 = 0;
                    break;
                }
                break;
            case 49679471:
                if (str.equals("46001")) {
                    c2 = 1;
                    break;
                }
                break;
            case 49679472:
                if (str.equals("46002")) {
                    c2 = 2;
                    break;
                }
                break;
            case 49679473:
                if (str.equals("46003")) {
                    c2 = 3;
                    break;
                }
                break;
            case 49679474:
                if (str.equals("46004")) {
                    c2 = 4;
                    break;
                }
                break;
            case 49679475:
                if (str.equals("46005")) {
                    c2 = 5;
                    break;
                }
                break;
            case 49679476:
                if (str.equals("46006")) {
                    c2 = 6;
                    break;
                }
                break;
            case 49679477:
                if (str.equals("46007")) {
                    c2 = 7;
                    break;
                }
                break;
            case 49679479:
                if (str.equals("46009")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 49679502:
                if (str.equals("46011")) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 2:
            case 4:
            case 7:
                c.a("SIMUtils", "\u4e2d\u56fd\u79fb\u52a8");
                return "1";
            case 1:
            case 6:
            case '\b':
                c.a("SIMUtils", "\u4e2d\u56fd\u8054\u901a");
                return "2";
            case 3:
            case 5:
            case '\t':
                c.a("SIMUtils", "\u4e2d\u56fd\u7535\u4fe1");
                return "3";
            default:
                return "0";
        }
    }
}
