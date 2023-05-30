package com.jd.dynamic.lib.utils;

import com.jd.dynamic.DYConstants;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes13.dex */
public final class t {
    private static boolean a;

    public static void a(Throwable th) {
        if (a) {
            StringBuilder sb = new StringBuilder();
            sb.append("printThrow: throwable:");
            sb.append(th == null ? DYConstants.DY_NULL_STR : d(th));
            sb.toString();
        }
    }

    public static void b(boolean z) {
        a = z;
    }

    public static void c(Object... objArr) {
        if (a) {
            h(objArr);
        }
    }

    public static String d(Throwable th) {
        StringBuilder sb = new StringBuilder();
        if (th != null) {
            sb.append("[throwable: ");
            sb.append(th);
            sb.append('\n');
            sb.append("cause: ");
            Throwable cause = th.getCause();
            if (cause != null) {
                sb.append(cause);
            }
            sb.append('\n');
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();
            String stringBuffer = stringWriter.getBuffer().toString();
            sb.append("stackTraceInfo: ");
            sb.append(stringBuffer);
            sb.append("]");
        }
        return sb.toString();
    }

    public static void e(Object... objArr) {
        if (a) {
            h(objArr);
        }
    }

    public static void f(Object... objArr) {
        if (a) {
            h(objArr);
        }
    }

    public static void g(Object... objArr) {
        if (a) {
            h(objArr);
        }
    }

    private static String h(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (objArr != null && objArr.length > 0) {
            sb.append("[");
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = objArr[i2];
                sb.append(obj == null ? DYConstants.DY_NULL_STR : obj.toString());
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }
}
