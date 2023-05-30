package com.jdcn.fido.utils;

import android.os.Build;
import android.os.Looper;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public class CommandUtil {

    /* loaded from: classes18.dex */
    static class SingletonHolder {
        private static final CommandUtil INSTANCE = new CommandUtil();

        private SingletonHolder() {
        }
    }

    private CommandUtil() {
    }

    public static final CommandUtil getSingleInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003d, code lost:
        if (r1.waitFor() == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String handlerCommand(String str, boolean z) {
        String str2 = "";
        Process process = null;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            process = Runtime.getRuntime().exec(str);
            process.getOutputStream().close();
            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), z);
            streamGobbler.start();
            if (Build.VERSION.SDK_INT >= 26) {
                if (process.waitFor(1L, TimeUnit.SECONDS)) {
                    str2 = streamGobbler.getContent();
                }
            }
        }
    }

    public String getProperty(String str) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, str);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
