package c.t.m.g;

import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* loaded from: classes.dex */
public class z0 {
    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        return stringWriter2.contains(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) ? stringWriter2.replaceAll(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "") : stringWriter2;
    }

    public static void b(int i2, String str, String str2) {
        c(str, 3, str2, null);
        if (w5.c() != null) {
            w5.c().a(i2, str, str2);
        }
    }

    public static void c(String str, int i2, String str2, Throwable th) {
        if (w5.e()) {
            if (th != null) {
                str2 = str2 + ". exception: " + Log.getStackTraceString(th);
            }
            Log.println(i2, str, str2);
        }
    }

    public static void d(String str, String str2) {
        b(1006, str, str2);
    }

    public static final boolean e() {
        return w5.e() || w5.d() != null;
    }

    public static void f(String str, String str2) {
        b(1004, str, str2);
    }
}
