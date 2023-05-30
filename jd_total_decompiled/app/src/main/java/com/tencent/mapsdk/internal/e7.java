package com.tencent.mapsdk.internal;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.tencent.map.lib.models.AccessibleTouchItem;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class e7 {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    public static int a(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i2 = 0;
        while (true) {
            if (i2 >= (split.length > split2.length ? split2.length : split.length)) {
                if (split.length != split2.length) {
                    return split.length - split2.length;
                }
                return 0;
            }
            int intValue = Integer.valueOf(split[i2]).intValue() - Integer.valueOf(split2[i2]).intValue();
            if (intValue != 0) {
                return intValue;
            }
            i2++;
        }
    }

    public static CharSequence a(String[] strArr, int[] iArr) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(strArr[i2]);
            spannableStringBuilder2.setSpan(new ForegroundColorSpan(iArr[i2]), 0, spannableStringBuilder2.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableStringBuilder2);
        }
        return spannableStringBuilder;
    }

    public static String a(long j2) {
        return a.format(Long.valueOf(j2));
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Collection<String> collection, String str) {
        if (collection == null || collection.isEmpty() || str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            int size = collection.size() - 1;
            sb.append(it.next());
            if (i2 != size) {
                sb.append(str);
            }
            i2++;
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(256);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean b(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean b(String str, String str2) {
        if (str2 == null || str == null) {
            return false;
        }
        return str2.contains(str);
    }

    public static boolean c(String str) {
        try {
            return Pattern.compile("[0-9]*").matcher(str).matches();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return str2.equals(str);
        }
        return false;
    }

    public static boolean d(String str) {
        if (b(str)) {
            return false;
        }
        return str.equals(AccessibleTouchItem.MY_LOCATION_PREFIX) || str.equals("\u5f53\u524d\u4f4d\u7f6e") || str.equals("\u6211\u5728\u54ea") || str.equals("\u6211\u5728\u54ea\u513f") || str.equals("\u6211\u5728\u7684\u4f4d\u7f6e") || str.equals("\u6211\u7684\u4f4d\u7f6e\u5728\u54ea") || str.equals("\u6211\u7684\u4f4d\u7f6e\u5728\u54ea\u513f");
    }

    public static boolean d(String str, String str2) {
        return (str2 == null || str == null || str2.indexOf(str) != 0) ? false : true;
    }

    public static int e(String str) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            if (str.substring(i2, i4).matches("[\u4e00-\u9fa5]")) {
                i3++;
            } else {
                double d = i3;
                Double.isNaN(d);
                i3 = (int) (d + 0.5d);
            }
            i2 = i4;
        }
        return (int) Math.ceil(i3);
    }

    public static String f(String str) {
        return str == null ? "" : str;
    }

    public static String g(String str) {
        int lastIndexOf;
        return (!b(str) && (lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) > 0) ? str.substring(0, lastIndexOf) : str;
    }

    public static String h(String str) {
        if (b(str)) {
            return str;
        }
        try {
            URI uri = new URI(str);
            String[] split = uri.getQuery().split(ContainerUtils.FIELD_DELIMITER);
            Arrays.sort(split);
            StringBuilder sb = new StringBuilder();
            for (String str2 : split) {
                sb.append(str2);
            }
            return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), sb.toString(), uri.getFragment()).toString();
        } catch (URISyntaxException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String i(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "gbk");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static String j(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception unused) {
            return "";
        }
    }
}
