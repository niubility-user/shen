package com.jingdong.jdsdk.utils;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class d {
    private static int a = 7;

    private static void a(int i2, StackTraceElement[] stackTraceElementArr, StringBuffer stringBuffer) {
        stringBuffer.append(stackTraceElementArr[i2].getClassName() + OrderISVUtil.MONEY_DECIMAL + stackTraceElementArr[i2].getMethodName() + "(" + stackTraceElementArr[i2].getLineNumber() + ")\n");
    }

    public static String b(Throwable th) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            c(th, stringBuffer, true);
            return stringBuffer.toString();
        } catch (Exception e2) {
            StringWriter stringWriter = new StringWriter();
            e2.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }
    }

    private static void c(Throwable th, StringBuffer stringBuffer, boolean z) {
        if (th == null || stringBuffer == null) {
            return;
        }
        stringBuffer.append("\n---" + th.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (th.getCause() == null) {
            for (int i2 = 0; i2 < stackTrace.length; i2++) {
                a(i2, stackTrace, stringBuffer);
            }
            return;
        }
        int length = stackTrace.length;
        int i3 = a;
        if (length > i3) {
            length = i3;
        }
        for (int i4 = 0; i4 < length; i4++) {
            a(i4, stackTrace, stringBuffer);
        }
        c(th.getCause(), stringBuffer, false);
    }
}
