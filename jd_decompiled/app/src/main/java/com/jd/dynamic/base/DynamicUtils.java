package com.jd.dynamic.base;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes13.dex */
public class DynamicUtils {
    public static final byte FLAG_EL = 1;
    public static final byte FLAG_INVALID = 0;
    public static final byte FLAG_KNOWN = 2;

    public static View findChildViewUnder(View view, float f2, float f3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (isViewUnder(childAt, f2, f3)) {
                    return childAt;
                }
            }
        }
        return null;
    }

    public static boolean isEL(String str) {
        int length;
        if (str == null || str.length() == 0 || (length = str.length()) <= 3) {
            return false;
        }
        return (str.charAt(0) == '$' && str.charAt(1) == '{' && str.charAt(length + (-1)) == '}') || (str.charAt(0) == '@' && str.charAt(1) == '{' && str.charAt(length - 1) == '}');
    }

    public static boolean isElOrKnownSymbol(String str) {
        return isEL(str) || isKnownSymbol(str) || isFun(str);
    }

    public static byte isElOrKnownSymbolNoFun(String str) {
        int i2;
        if (isEL(str)) {
            i2 = 1;
        } else if (!isKnownSymbol(str)) {
            return (byte) 0;
        } else {
            i2 = 2;
        }
        return (byte) i2;
    }

    public static boolean isFun(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("fun{");
    }

    public static boolean isKnownSymbol(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int indexOf = str.indexOf("(");
        return str.startsWith("$") && str.endsWith(")") && indexOf != -1 && com.jd.dynamic.b.h.b.a.contains(str.substring(1, indexOf));
    }

    public static boolean isThreeUnknown(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.charAt(0) == '@' && str.charAt(1) == '{' && str.charAt(str.length() - 1) == '}';
    }

    public static boolean isViewUnder(View view, float f2, float f3) {
        int[] iArr = new int[2];
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(iArr);
        return f2 >= ((float) iArr[0]) && f2 <= ((float) iArr[0]) + ((float) width) && f3 >= ((float) iArr[1]) && f3 <= ((float) iArr[1]) + ((float) height);
    }

    public static String toString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }
}
