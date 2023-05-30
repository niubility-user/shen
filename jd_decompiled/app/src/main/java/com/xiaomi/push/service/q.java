package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.platform.business.personal.R2;
import com.xiaomi.push.a3;
import com.xiaomi.push.a5;
import com.xiaomi.push.u9;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public class q {
    private static long a;
    private static ThreadPoolExecutor b;

    static {
        Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        a = 0L;
        b = new ThreadPoolExecutor(1, 1, 20L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    private static String a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    String sb2 = sb.toString();
                    u9.b(bufferedReader);
                    return sb2;
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                sb.append(readLine);
            }
        } catch (Exception unused2) {
            u9.b(bufferedReader);
            return null;
        } catch (Throwable th3) {
            th = th3;
            u9.b(bufferedReader);
            throw th;
        }
    }

    public static void b() {
        a3 c2;
        long currentTimeMillis = System.currentTimeMillis();
        if ((b.getActiveCount() <= 0 || currentTimeMillis - a >= 1800000) && a5.f().k() && (c2 = z0.f().c()) != null && c2.y() > 0) {
            a = currentTimeMillis;
            c(c2.o(), true);
        }
    }

    public static void c(List<String> list, boolean z) {
        b.execute(new r(list, z));
    }

    public static void e() {
        String a2 = a("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(a2)) {
            g.j.a.a.a.c.o("dump tcp for uid = " + Process.myUid());
            g.j.a.a.a.c.o(a2);
        }
        String a3 = a("/proc/self/net/tcp6");
        if (TextUtils.isEmpty(a3)) {
            return;
        }
        g.j.a.a.a.c.o("dump tcp6 for uid = " + Process.myUid());
        g.j.a.a.a.c.o(a3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            g.j.a.a.a.c.o("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(com.xiaomi.push.d1.d(str, R2.dimen.abc_button_padding_horizontal_material), 5000);
            socket.setTcpNoDelay(true);
            g.j.a.a.a.c.o("ConnectivityTest: connect to " + str + " in " + (System.currentTimeMillis() - currentTimeMillis));
            socket.close();
            return true;
        } catch (Throwable th) {
            g.j.a.a.a.c.D("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }
}
