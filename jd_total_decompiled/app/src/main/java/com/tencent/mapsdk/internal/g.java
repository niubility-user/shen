package com.tencent.mapsdk.internal;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.InputStream;
import java.util.Properties;

/* loaded from: classes9.dex */
public class g {
    private static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static String f16564c;

    static {
        try {
            InputStream resourceAsStream = g.class.getResourceAsStream("/com/qq/jce/wup/wup.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            resourceAsStream.close();
            a = properties.getProperty("client.info");
            b = properties.getProperty("client.built");
            f16564c = properties.getProperty("client.number");
        } catch (Throwable unused) {
        }
        if (a == null) {
            a = "Tencent Taf";
        }
        if (b == null) {
            b = "unknown";
        }
        if (f16564c == null) {
            f16564c = "unknown";
        }
    }

    public static String a() {
        return b;
    }

    public static String b() {
        return a;
    }

    public static String c() {
        return f16564c;
    }

    public static String d() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client version: " + b() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("Client built:   " + a() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("Client number:  " + c() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("OS Name:        " + System.getProperty("os.name") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("OS Version:     " + System.getProperty("os.version") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("Architecture:   " + System.getProperty("os.arch") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("JVM Version:    " + System.getProperty("java.runtime.version") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("JVM Vendor:     " + System.getProperty("java.vm.vendor") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return sb.toString();
    }
}
