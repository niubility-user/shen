package com.alibaba.android.patronus;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.alibaba.android.patronus.Patrons;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class _Patrons {
    private static final int ANDROID_VERSION_NOT_SUPPORT = 2001;
    private static final int ERROR_READ_VSS_FAILED = 1001;
    private static final long GB = 1073741824;
    private static final int HEAP_SIZE_IS_NOT_BIG_ENOUGH = 2002;
    private static final long KB = 1024;
    private static final int LOWER_LIMIT_IS_TOO_SMALL = 2003;
    private static final int MAX_CHECK_OF_STRICT_MODE = 5;
    private static final long MB = 1048576;
    private static boolean NATIVE_LIB_LOADED = false;
    private static final long S = 1000;
    public static final String TAG = "Patrons";
    private static final int VERSION_CODES_R = 30;
    private static final float VSS_MAX_IN_V7A = 4.2949673E9f;
    private static long currentRegionSpaces = 0;
    private static final String numRegEx = "[^0-9]";
    private static final Pattern numPattern = Pattern.compile(numRegEx);
    private static Patrons.PatronsConfig config = new Patrons.PatronsConfig();
    private static Timer autoCheckVssTimer = null;
    private static final AtomicInteger strictCount = new AtomicInteger(0);

    /* loaded from: classes.dex */
    public static class AutoCheckerTask extends TimerTask {
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (_Patrons.strictCount.get() != 0 && _Patrons.strictCount.addAndGet(1) > 5) {
                _Patrons.strictCount.set(0);
                _Patrons._start(_Patrons.config.periodOfCheck);
            }
            long readVssSize = _Patrons.readVssSize();
            float f2 = ((float) readVssSize) / _Patrons.VSS_MAX_IN_V7A;
            if (_Patrons.currentRegionSpaces - _Patrons.config.shrinkStep >= _Patrons.config.lowerLimit) {
                if (f2 <= _Patrons.config.periodOfShrink) {
                    if (_Patrons.getCurrentRegionSpaceSize() / _Patrons.MB >= _Patrons.config.lowerLimit) {
                        if (_Patrons.config.debuggable) {
                            String str = "[" + _Patrons.strictCount.get() + "] every thing is OK, vss = " + (readVssSize / _Patrons.MB) + " mb, current period = " + f2 + ", heap = " + (_Patrons.getCurrentRegionSpaceSize() / _Patrons.MB) + " mb";
                            return;
                        }
                        return;
                    }
                    String str2 = "current heap size (" + (_Patrons.getCurrentRegionSpaceSize() / _Patrons.MB) + ") less than lower limit (" + _Patrons.config.lowerLimit + ") stop watching.";
                    _Patrons.stop();
                    return;
                }
                String str3 = "vss has over the period, current vss = " + (readVssSize / _Patrons.MB) + "mb, period = " + f2;
                if (!_Patrons.shrinkRegionSpace((int) _Patrons.currentRegionSpaces -= _Patrons.config.shrinkStep)) {
                    _Patrons.stop();
                    return;
                }
                String str4 = "resize success, step = " + _Patrons.config.shrinkStep + "mb, current vss = " + (_Patrons.readVssSize() / _Patrons.MB) + "mb";
                _Patrons.strictCount.set(1);
                _Patrons._start(_Patrons.config.periodOfCheck / 2);
                return;
            }
            String str5 = "vss has no space to resize, stop watching. current space = " + _Patrons.currentRegionSpaces;
            _Patrons.stop();
        }
    }

    static {
        NATIVE_LIB_LOADED = false;
        if (isSupport()) {
            System.loadLibrary("patrons");
            NATIVE_LIB_LOADED = true;
        }
    }

    private _Patrons() {
    }

    protected static synchronized int __init() {
        synchronized (_Patrons.class) {
            if (isSupport()) {
                Patrons.PatronsConfig patronsConfig = config;
                int __init = __init(true, patronsConfig.debuggable, patronsConfig.fixHuaweiBinderAbort);
                if (__init != 0) {
                    return __init;
                }
                long currentRegionSpaceSize = getCurrentRegionSpaceSize() / MB;
                currentRegionSpaces = currentRegionSpaceSize;
                if (currentRegionSpaceSize > 0 && currentRegionSpaceSize <= 1024) {
                    Patrons.PatronsConfig patronsConfig2 = config;
                    if (currentRegionSpaceSize < patronsConfig2.lowerLimit) {
                        return 2003;
                    }
                    if (patronsConfig2.auto) {
                        if (readVssSize() < 0) {
                            return 1001;
                        }
                        toForeground();
                    }
                    String str = "patrons init finish, vss = " + (readVssSize() / MB) + " mb, heap = " + currentRegionSpaces + " mb";
                    return 0;
                }
                return 2002;
            }
            return 2001;
        }
    }

    private static native int __init(boolean z, boolean z2, boolean z3);

    /* JADX INFO: Access modifiers changed from: private */
    public static void _start(int i2) {
        if (config.auto) {
            Timer timer = autoCheckVssTimer;
            if (timer != null) {
                timer.cancel();
                autoCheckVssTimer = null;
            }
            Timer timer2 = new Timer();
            autoCheckVssTimer = timer2;
            long j2 = 1000 * i2;
            timer2.schedule(new AutoCheckerTask(), j2, j2);
        }
    }

    private static void asyncWriteInitResultToFile(final Context context, final int i2) {
        new Thread(new Runnable() { // from class: com.alibaba.android.patronus._Patrons.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String str = context.getDir("patrons", 0).getAbsolutePath() + File.separator;
                    _Patrons.stringToFile(String.valueOf(i2), str + "code.txt");
                    if (i2 != 0) {
                        _Patrons.stringToFile(_Patrons.dumpNativeLogs(false), str + "msg.txt");
                    }
                } catch (Exception unused) {
                    String str2 = "record init result failed, code = " + i2;
                }
            }
        }).start();
    }

    static native String dumpLogs(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String dumpNativeLogs(boolean z) {
        return NATIVE_LIB_LOADED ? dumpLogs(z) : "can not dump logs without native libs";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getCurrentRegionSpaceSize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void inBackground() {
        Timer timer;
        if (!config.auto || (timer = autoCheckVssTimer) == null) {
            return;
        }
        timer.cancel();
        autoCheckVssTimer = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized int init(Context context, Patrons.PatronsConfig patronsConfig) {
        int __init;
        synchronized (_Patrons.class) {
            if (patronsConfig != null) {
                try {
                    config = patronsConfig;
                } catch (Throwable th) {
                    throw th;
                }
            }
            String str = "patrons start init, config = " + config.toString();
            __init = __init();
            if (config.recordInitResult && context != null) {
                asyncWriteInitResultToFile(context, __init);
            }
        }
        return __init;
    }

    private static boolean isSupport() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 26 && i2 <= 30 && !Process.is64Bit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long readVssSize() {
        long j2 = -1;
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/" + Process.myPid() + "/status");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (readLine.toLowerCase().contains("vmsize")) {
                    j2 = Integer.parseInt(numPattern.matcher(r4).replaceAll("").trim()) * 1024;
                    break;
                }
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (Exception unused) {
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean shrinkRegionSpace(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static void stop() {
        inBackground();
        config.auto = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stringToFile(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            fileOutputStream.write((str + "\n\n").getBytes());
            fileOutputStream.close();
        } catch (Exception unused) {
            String str3 = "write content to file: " + str2 + " failed.";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toForeground() {
        strictCount.set(0);
        _start(config.periodOfCheck);
    }
}
