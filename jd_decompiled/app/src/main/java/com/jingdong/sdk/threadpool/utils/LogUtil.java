package com.jingdong.sdk.threadpool.utils;

import com.jd.dynamic.DYConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/* loaded from: classes.dex */
public final class LogUtil {
    private static boolean a;

    private LogUtil() {
    }

    private static String a(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null && stackTraceElementArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append(stackTraceElement.toString());
            sb.append('\n');
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private static String b(String... strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null && strArr.length > 0) {
            sb.append("[");
            for (String str : strArr) {
                sb.append(str);
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }

    public static void d(String... strArr) {
        if (a) {
            b(strArr);
        }
    }

    public static void e(String... strArr) {
        if (a) {
            b(strArr);
        }
    }

    public static String extractThrowableInfo(Throwable th) {
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

    public static void i(String... strArr) {
        if (a) {
            b(strArr);
        }
    }

    public static void printLog(boolean z) {
        a = z;
    }

    public static void printThreadStackTrace() {
        Set<Thread> keySet = Thread.getAllStackTraces().keySet();
        e("all thread size:: " + keySet.size());
        for (Thread thread : keySet) {
            i("thread::" + thread.getName(), "  id:" + thread.getId(), "priority:" + thread.getPriority(), "  state:" + thread.getState(), "stacktrace::" + a(thread.getStackTrace()));
            e("--------------------------------------");
        }
    }

    public static void printThrow(Throwable th) {
        if (th != null) {
            if (a) {
                String str = "printThrow: throwable:" + extractThrowableInfo(th);
                return;
            }
            return;
        }
        boolean z = a;
    }

    public static void v(String... strArr) {
        if (a) {
            b(strArr);
        }
    }

    public static void w(String... strArr) {
        if (a) {
            b(strArr);
        }
    }
}
