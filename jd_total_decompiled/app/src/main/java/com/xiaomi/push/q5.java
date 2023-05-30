package com.xiaomi.push;

import com.jingdong.sdk.platform.business.personal.R2;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* loaded from: classes11.dex */
public class q5 {
    public static int a(Throwable th) {
        boolean z = th instanceof a6;
        if (z) {
            a6 a6Var = (a6) th;
            if (a6Var.a() != null) {
                th = a6Var.a();
            }
        }
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        if (th instanceof SocketTimeoutException) {
            return 105;
        }
        if (!(th instanceof SocketException)) {
            if (th instanceof UnknownHostException) {
                return 107;
            }
            if (z) {
                return R2.attr.arrow_color;
            }
            return 0;
        } else if (message.indexOf("Network is unreachable") != -1) {
            return 102;
        } else {
            if (message.indexOf("Connection refused") != -1) {
                return 103;
            }
            if (message.indexOf("Connection timed out") != -1) {
                return 105;
            }
            if (message.endsWith("EACCES (Permission denied)")) {
                return 101;
            }
            if (message.indexOf("Connection reset by peer") != -1) {
                return 109;
            }
            if (message.indexOf("Broken pipe") != -1) {
                return 110;
            }
            if (message.indexOf("No route to host") != -1) {
                return 104;
            }
            if (message.endsWith("EINVAL (Invalid argument)")) {
                return 106;
            }
            return R2.anim.slide_out_top_dongdong;
        }
    }
}
