package com.google.gson.internal;

import com.jingdong.common.utils.LangUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes12.dex */
public class PreJava9DateFormatProvider {
    private static String getDateFormatPattern(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return "M/d/yy";
                    }
                    throw new IllegalArgumentException("Unknown DateFormat style: " + i2);
                }
                return "MMM d, y";
            }
            return "MMMM d, y";
        }
        return "EEEE, MMMM d, y";
    }

    private static String getDatePartOfDateTimePattern(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return "M/d/yy";
                    }
                    throw new IllegalArgumentException("Unknown DateFormat style: " + i2);
                }
                return "MMM d, yyyy";
            }
            return "MMMM d, yyyy";
        }
        return "EEEE, MMMM d, yyyy";
    }

    private static String getTimePartOfDateTimePattern(int i2) {
        if (i2 == 0 || i2 == 1) {
            return "h:mm:ss a z";
        }
        if (i2 != 2) {
            if (i2 == 3) {
                return "h:mm a";
            }
            throw new IllegalArgumentException("Unknown DateFormat style: " + i2);
        }
        return "h:mm:ss a";
    }

    public static DateFormat getUSDateFormat(int i2) {
        return new SimpleDateFormat(getDateFormatPattern(i2), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int i2, int i3) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i2) + LangUtils.SINGLE_SPACE + getTimePartOfDateTimePattern(i3), Locale.US);
    }
}
