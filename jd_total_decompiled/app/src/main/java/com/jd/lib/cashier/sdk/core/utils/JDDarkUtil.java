package com.jd.lib.cashier.sdk.core.utils;

import android.graphics.Color;
import android.text.TextUtils;

/* loaded from: classes14.dex */
public class JDDarkUtil {
    public static final String COLOR_0000000 = "#000000";
    public static final String COLOR_05000000 = "#05000000";
    public static final String COLOR_0A0909 = "#0A0909";
    public static final String COLOR_0F000000 = "#0F000000";
    public static final String COLOR_0F254D = "#0F254D";
    public static final String COLOR_0FFFFFFF = "#0FFFFFFF";
    public static final String COLOR_14000000 = "#14000000";
    public static final String COLOR_141212 = "#141212";
    public static final String COLOR_161515 = "#161515";
    public static final String COLOR_1A1A1A = "#1A1A1A";
    public static final String COLOR_1D1B1B = "#1D1B1B";
    public static final String COLOR_1D1E1E = "#1D1E1E";
    public static final String COLOR_238EFF = "#238EFF";
    public static final String COLOR_262626 = "#262626";
    public static final String COLOR_2E2D2D = "#2E2D2D";
    public static final String COLOR_302E2E = "#302E2E";
    public static final String COLOR_33000000 = "#33000000";
    public static final String COLOR_404040 = "#404040";
    public static final String COLOR_555353 = "#555353";
    public static final String COLOR_757575 = "#757575";
    public static final String COLOR_7F000000 = "#7F000000";
    public static final String COLOR_808080 = "#808080";
    public static final String COLOR_848383 = "#848383";
    public static final String COLOR_878888 = "#878888";
    public static final String COLOR_8C8C8C = "#8c8c8c";
    public static final String COLOR_999999 = "#999999";
    public static final String COLOR_BFBFBF = "#bfbfbf";
    public static final String COLOR_C2C2C2 = "#C2C2C2";
    public static final String COLOR_CCCCCC = "#CCCCCC";
    public static final String COLOR_ECECEC = "#ECECEC";
    public static final String COLOR_F2270C = "#f2270c";
    public static final String COLOR_F2F2F2 = "#f2f2f2";
    public static final String COLOR_F5F5F5 = "#F5F5F5";
    public static final String COLOR_F6F6F6 = "#F6F6F6";
    public static final String COLOR_F8F8F8 = "#F8F8F8";
    public static final String COLOR_F9F9F9 = "#F9F9F9";
    public static final String COLOR_FA200C = "#FA220C";
    public static final String COLOR_FA2C19 = "#FA2C19";
    public static final String COLOR_FF3826 = "#FF3826";
    public static final String COLOR_FFFFFFF = "#ffffff";
    public static final String COLOR_TRANSPARENT = "#00000000";
    private static final String TAG = "JDDarkUtil";

    public static int getDarkColor(String str, boolean z) {
        if (z) {
            return getDarkColor(str);
        }
        return Color.parseColor(str);
    }

    public static boolean isDarkMode() {
        if (TextUtils.equals("2", m.f().j())) {
            return false;
        }
        return y.p();
    }

    public static int getDarkColor(String str, String str2, boolean z) {
        try {
            if (z) {
                return getDarkColor(str, str2);
            }
            return Color.parseColor(str);
        } catch (Exception e2) {
            r.b(TAG, e2.getMessage());
            return -1;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getDarkColor(String str) {
        char c2;
        try {
            if (!isDarkMode()) {
                return Color.parseColor(str);
            }
            switch (str.hashCode()) {
                case -1960092487:
                    if (str.equals(COLOR_0F000000)) {
                        c2 = 11;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1832728461:
                    if (str.equals(COLOR_1A1A1A)) {
                        c2 = '\b';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1829954050:
                    if (str.equals(COLOR_1D1E1E)) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1814238801:
                    if (str.equals(COLOR_262626)) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1600683228:
                    if (str.equals(COLOR_8C8C8C)) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1315017981:
                    if (str.equals(COLOR_CCCCCC)) {
                        c2 = '\t';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1241983824:
                    if (str.equals(COLOR_F5F5F5)) {
                        c2 = 7;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1231484585:
                    if (str.equals(COLOR_FA2C19)) {
                        c2 = '\n';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -394232913:
                    if (str.equals(COLOR_BFBFBF)) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -329216089:
                    if (str.equals(COLOR_F2270C)) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -327670137:
                    if (str.equals(COLOR_F2F2F2)) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -279597021:
                    if (str.equals(COLOR_FFFFFFF)) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    return Color.parseColor(COLOR_141212);
                case 1:
                    return Color.parseColor(COLOR_FF3826);
                case 2:
                    return Color.parseColor(COLOR_1D1B1B);
                case 3:
                    return Color.parseColor(COLOR_555353);
                case 4:
                    return Color.parseColor(COLOR_848383);
                case 5:
                    return Color.parseColor(COLOR_ECECEC);
                case 6:
                    return Color.parseColor(COLOR_FFFFFFF);
                case 7:
                    return Color.parseColor(COLOR_302E2E);
                case '\b':
                    return Color.parseColor(COLOR_ECECEC);
                case '\t':
                    return Color.parseColor(COLOR_555353);
                case '\n':
                    return Color.parseColor(COLOR_FF3826);
                case 11:
                    return Color.parseColor(COLOR_0FFFFFFF);
                default:
                    return Color.parseColor(str);
            }
        } catch (Exception e2) {
            r.d(TAG, e2.getMessage());
            return -1;
        }
    }

    public static int getDarkColor(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return -1;
            }
            if (!isDarkMode()) {
                return Color.parseColor(str);
            }
            return Color.parseColor(str2);
        } catch (Exception e2) {
            r.b(TAG, e2.getMessage());
            return -1;
        }
    }

    public static int getDarkColor(int i2, int i3) {
        return !isDarkMode() ? i2 : i3;
    }
}
