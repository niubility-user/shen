package com.jingdong.common.utils;

import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class LangUtils {
    public static final String EMPTY_STRING = "";
    public static final String SINGLE_SPACE = " ";

    public static String getString(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : str;
    }

    public static String join(String str, ArrayList<String> arrayList) {
        if (arrayList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        int i2 = size - 1;
        for (int i3 = 0; i3 < size; i3++) {
            sb.append(arrayList.get(i3));
            if (i3 != i2) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static int tryParseInteger(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }
}
