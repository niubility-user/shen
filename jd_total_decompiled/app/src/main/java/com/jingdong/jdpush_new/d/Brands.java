package com.jingdong.jdpush_new.d;

import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import java.util.Locale;

/* renamed from: com.jingdong.jdpush_new.d.a */
/* loaded from: classes12.dex */
public final /* synthetic */ class Brands {
    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 71;
        }
        String upperCase = str.toUpperCase(Locale.ROOT);
        upperCase.hashCode();
        char c2 = '\uffff';
        switch (upperCase.hashCode()) {
            case -1881642058:
                if (upperCase.equals("REALME")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1706170181:
                if (upperCase.equals("XIAOMI")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1139460026:
                if (upperCase.equals("SUMSUNG")) {
                    c2 = 2;
                    break;
                }
                break;
            case -602397472:
                if (upperCase.equals(SystemInfo.ROM_1JIA)) {
                    c2 = 3;
                    break;
                }
                break;
            case 2255112:
                if (upperCase.equals("IQOO")) {
                    c2 = 4;
                    break;
                }
                break;
            case 2432928:
                if (upperCase.equals("OPPO")) {
                    c2 = 5;
                    break;
                }
                break;
            case 2634924:
                if (upperCase.equals("VIVO")) {
                    c2 = 6;
                    break;
                }
                break;
            case 68924490:
                if (upperCase.equals("HONOR")) {
                    c2 = 7;
                    break;
                }
                break;
            case 73239724:
                if (upperCase.equals("MEIZU")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 77852109:
                if (upperCase.equals("REDMI")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 2141820391:
                if (upperCase.equals("HUAWEI")) {
                    c2 = '\n';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 41;
            case 1:
                return 30;
            case 2:
                return 70;
            case 3:
                return 42;
            case 4:
                return 51;
            case 5:
                return 40;
            case 6:
                return 50;
            case 7:
                return 10;
            case '\b':
                return 60;
            case '\t':
                return 31;
            case '\n':
                return 20;
            default:
                return 71;
        }
    }
}
