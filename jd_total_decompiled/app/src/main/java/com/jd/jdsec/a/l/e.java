package com.jd.jdsec.a.l;

import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e {
    private static ThreadLocal<DecimalFormat> a = new a();

    /* loaded from: classes13.dex */
    class a extends ThreadLocal<DecimalFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DecimalFormat initialValue() {
            try {
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
                decimalFormat.applyPattern("0.0");
                return decimalFormat;
            } catch (Exception unused) {
                return new DecimalFormat("0.0");
            }
        }
    }

    public static String a() {
        String str = "";
        try {
            char[] charArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            Random random = new Random();
            for (int i2 = 0; i2 <= 8; i2++) {
                str = str + charArray[random.nextInt(charArray.length)];
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static String b(double d) {
        return a.get().format(d / 1000000.0d);
    }

    public static String c(String str) {
        try {
            Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass("android.os.SystemProperties");
            Method method = loadClass.getMethod(IMantoServerRequester.GET, String.class);
            method.setAccessible(true);
            return (String) method.invoke(loadClass, str);
        } catch (Exception unused) {
            return "";
        }
    }

    public static ArrayList<String> d(JSONObject jSONObject, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    arrayList.add(jSONArray.get(i2).toString());
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static List e(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    arrayList.add(jSONArray.get(i2));
                } catch (Exception unused) {
                }
            }
        }
        return arrayList;
    }

    public static void f(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static float[] g(List list, float[] fArr) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            try {
                if (list.get(i2) instanceof Float) {
                    fArr[i2] = ((Float) list.get(i2)).floatValue();
                } else {
                    fArr[i2] = Float.parseFloat(String.valueOf(list.get(i2)));
                }
            } catch (Exception unused) {
            }
        }
        return fArr;
    }

    public static String h(String str) {
        return str == null ? "" : str;
    }

    public static void i(String str) {
        if (str.length() > 4000) {
            int i2 = 0;
            while (i2 < str.length()) {
                int i3 = i2 + 4000;
                if (i3 < str.length()) {
                    b.e("JDSec.Security.Util", str.substring(i2, i3));
                } else {
                    b.e("JDSec.Security.Util", str.substring(i2));
                }
                i2 = i3;
            }
            return;
        }
        b.e("JDSec.Security.Util", str);
    }

    public static String j(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 1) ? "" : str.substring(0, str.length() - 1);
    }
}
