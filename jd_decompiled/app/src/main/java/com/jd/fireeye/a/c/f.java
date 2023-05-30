package com.jd.fireeye.a.c;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/* loaded from: classes13.dex */
public class f {

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

    static {
        new a();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String b(String str) {
        return str == null ? "" : str;
    }

    public static String a(String str) {
        try {
            Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass("android.os.SystemProperties");
            Method method = loadClass.getMethod(IMantoServerRequester.GET, String.class);
            method.setAccessible(true);
            return (String) method.invoke(loadClass, str);
        } catch (Exception unused) {
            return "";
        }
    }
}
