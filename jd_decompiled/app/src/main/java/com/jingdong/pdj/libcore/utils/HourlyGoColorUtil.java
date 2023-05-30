package com.jingdong.pdj.libcore.utils;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.oklog.OKLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class HourlyGoColorUtil {
    public static int getColorByString(String str, int i2) {
        int[] gradientColorsValueByNetString = getGradientColorsValueByNetString(str, i2);
        return (gradientColorsValueByNetString == null || gradientColorsValueByNetString.length <= 0) ? i2 : gradientColorsValueByNetString[0];
    }

    public static int getColorValueByNetString(String str, int i2) {
        int[] iArr = {0};
        return getColorValueBynetStringWithoutDefault(str, iArr) ? iArr[0] : i2;
    }

    public static boolean getColorValueBynetStringWithoutDefault(String str, int[] iArr) {
        return getColorValueBynetStringWithoutDefault(str, iArr, true);
    }

    public static int[] getGradientColorsValueByNetString(String str, int i2) {
        return getGradientColorsValueByNetString(str, i2, true);
    }

    public static int parseColor(String str) {
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String rgbToArgb(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() != 2) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return str.startsWith("#") ? str : "#".concat(String.valueOf(str));
        }
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        if (str.length() == 8) {
            return "#".concat(String.valueOf(str));
        }
        if (str.length() == 6) {
            return "#" + str2 + str;
        }
        return "#".concat(String.valueOf(str));
    }

    public static boolean getColorValueBynetStringWithoutDefault(String str, int[] iArr, boolean z) {
        if (iArr != null && iArr.length > 0 && str != null && !str.isEmpty()) {
            try {
                Matcher matcher = Pattern.compile("^#([A-Fa-f0-9]{6})$|^#([A-Fa-f0-9]{2})([A-Fa-f0-9]{6})$").matcher(str.trim());
                if (matcher.matches()) {
                    try {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        String group3 = matcher.group(3);
                        if (group2 != null && group3 != null) {
                            iArr[0] = HourlyGoNumberUtil.parseInt(group3, 16) | ((z ? HourlyGoNumberUtil.parseInt(group2, 16) : 255) << 24);
                            return true;
                        } else if (group == null) {
                            return false;
                        } else {
                            try {
                                iArr[0] = (-16777216) | HourlyGoNumberUtil.parseInt(group, 16);
                                return true;
                            } catch (Exception e2) {
                                OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
                                return false;
                            }
                        }
                    } catch (Exception e3) {
                        OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e3));
                        return false;
                    }
                }
                return false;
            } catch (Exception e4) {
                OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e4));
            }
        }
        return false;
    }

    public static int[] getGradientColorsValueByNetString(String str, int i2, boolean z) {
        return getGradientColorsValueByNetString(str, i2, z, true);
    }

    public static int[] getGradientColorsValueByNetString(String str, int i2, boolean z, boolean z2) {
        if (str == null || str.isEmpty()) {
            if (z) {
                return new int[]{i2};
            }
            return null;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 0) {
            if (z) {
                return new int[]{getColorValueByNetString(str, i2)};
            }
            int[] iArr = {0};
            if (getColorValueBynetStringWithoutDefault(str, iArr)) {
                return iArr;
            }
            return null;
        }
        int[] iArr2 = new int[split.length];
        for (int i3 = 0; i3 < split.length; i3++) {
            int[] iArr3 = {0};
            if (!getColorValueBynetStringWithoutDefault(split[i3], iArr3, z2)) {
                if (z) {
                    return new int[]{i2};
                }
                return null;
            }
            iArr2[i3] = iArr3[0];
        }
        return iArr2;
    }

    public static int rgbToArgb(int i2, float f2) {
        return Color.argb((int) ((((-16777216) & i2) >>> 24) * f2), (16711680 & i2) >> 16, (65280 & i2) >> 8, i2 & 255);
    }
}
