package com.laser.utils.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes13.dex */
public final class LogUtil {
    private static final String DEFAULT_TAG = "LASER";
    public static final int LEVEL_LOG_D = 2;
    public static final int LEVEL_LOG_E = 3;
    public static final int LEVEL_LOG_I = 1;
    public static final int LEVEL_LOG_V = 4;
    public static final int LEVEL_LOG_W = 5;
    private static boolean isLog = true;
    private static boolean isSaveToFile = true;
    private static ExecutorService sExecutor;
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ");
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final Config CONFIG = new Config();

    /* loaded from: classes13.dex */
    public static class Config {
        private String mDefaultDir;
        private String mDir;
        private String mFilePrefix;

        private Config() {
            this.mFilePrefix = "log";
            if (this.mDefaultDir != null) {
                return;
            }
            if ("mounted".equals(Environment.getExternalStorageState()) && Utils.getApp().getExternalCacheDir() != null) {
                this.mDefaultDir = Utils.getApp().getExternalCacheDir() + LogUtil.FILE_SEP + "log" + LogUtil.FILE_SEP;
                return;
            }
            this.mDefaultDir = Utils.getApp().getCacheDir() + LogUtil.FILE_SEP + "log" + LogUtil.FILE_SEP;
        }
    }

    private LogUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (createOrExistsDir(file.getParentFile())) {
            try {
                boolean createNewFile = file.createNewFile();
                if (createNewFile) {
                    printDeviceInfo(str);
                }
                return createNewFile;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static void d(String str, String str2) {
        if (isLog && !TextUtils.isEmpty(str2) && isSaveToFile) {
            file(str2);
        }
    }

    public static void dLongInfo(String str) {
        dTAGLongInfo(DEFAULT_TAG, str);
    }

    public static void dTAGLongInfo(String str, String str2) {
        String substring;
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        String trim = str2.trim();
        int i2 = 0;
        while (i2 < trim.length()) {
            int i3 = i2 + 3500;
            if (trim.length() <= i3) {
                substring = trim.substring(i2);
            } else {
                substring = trim.substring(i2, i3);
            }
            d(str, substring.trim());
            i2 = i3;
        }
    }

    public static void e(String str, String str2) {
        if (isLog && !TextUtils.isEmpty(str2) && isSaveToFile) {
            file(str2);
        }
    }

    public static void file(String str) {
        print2File(DEFAULT_TAG, str);
    }

    public static void i(String str, String str2) {
        if (isLog && !TextUtils.isEmpty(str2) && isSaveToFile) {
            file(str2);
        }
    }

    private static void input2File(final String str, final String str2) {
        if (sExecutor == null) {
            sExecutor = Executors.newSingleThreadExecutor();
        }
        try {
            if (((Boolean) sExecutor.submit(new Callable<Boolean>() { // from class: com.laser.utils.common.LogUtil.1
                /*  JADX ERROR: Type inference failed with exception
                    java.lang.NullPointerException
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:418)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:215)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:196)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdateForSsaVar(TypeUpdate.java:172)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:166)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:149)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:82)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:55)
                    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryPossibleTypes(TypeInferenceVisitor.java:414)
                    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.deduceType(TypeInferenceVisitor.java:472)
                    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryDeduceTypes(TypeInferenceVisitor.java:452)
                    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:109)
                    */
                /* JADX WARN: Can't rename method to resolve collision */
                /* JADX WARN: Not initialized variable reg: 1, insn: 0x0037: MOVE (r5 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:24:0x0037 */
                @Override // java.util.concurrent.Callable
                public java.lang.Boolean call() throws java.lang.Exception {
                    /*
                        r6 = this;
                        r0 = 0
                        java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
                        java.io.FileWriter r2 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
                        java.lang.String r3 = r1     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
                        r4 = 1
                        r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
                        r1.<init>(r2)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
                        java.lang.String r0 = r2     // Catch: java.io.IOException -> L1e java.lang.Throwable -> L36
                        r1.write(r0)     // Catch: java.io.IOException -> L1e java.lang.Throwable -> L36
                        java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.io.IOException -> L1e java.lang.Throwable -> L36
                        r1.close()     // Catch: java.io.IOException -> L19
                        goto L1d
                    L19:
                        r1 = move-exception
                        r1.printStackTrace()
                    L1d:
                        return r0
                    L1e:
                        r0 = move-exception
                        goto L26
                    L20:
                        r1 = move-exception
                        goto L3a
                    L22:
                        r1 = move-exception
                        r5 = r1
                        r1 = r0
                        r0 = r5
                    L26:
                        r0.printStackTrace()     // Catch: java.lang.Throwable -> L36
                        java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> L36
                        if (r1 == 0) goto L35
                        r1.close()     // Catch: java.io.IOException -> L31
                        goto L35
                    L31:
                        r1 = move-exception
                        r1.printStackTrace()
                    L35:
                        return r0
                    L36:
                        r0 = move-exception
                        r5 = r1
                        r1 = r0
                        r0 = r5
                    L3a:
                        if (r0 == 0) goto L44
                        r0.close()     // Catch: java.io.IOException -> L40
                        goto L44
                    L40:
                        r0 = move-exception
                        r0.printStackTrace()
                    L44:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.laser.utils.common.LogUtil.AnonymousClass1.call():java.lang.Boolean");
                }
            }).get()).booleanValue()) {
                return;
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (ExecutionException e3) {
            e3.printStackTrace();
        }
        e("log to " + str2 + " failed!");
    }

    public static boolean isIsSaveToFile() {
        return isSaveToFile;
    }

    public static boolean isLog() {
        return isLog;
    }

    private static void print2File(String str, String str2) {
        String format = FORMAT.format(new Date(System.currentTimeMillis()));
        String substring = format.substring(0, 5);
        String substring2 = format.substring(6);
        StringBuilder sb = new StringBuilder();
        Config config = CONFIG;
        sb.append(config.mDir == null ? config.mDefaultDir : config.mDir);
        sb.append(config.mFilePrefix);
        sb.append("-");
        sb.append(substring);
        sb.append(".txt");
        String sb2 = sb.toString();
        if (!createOrExistsFile(sb2)) {
            e("create " + sb2 + " failed!");
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(substring2);
        sb3.append("\t");
        sb3.append(str + "\t");
        sb3.append(str2);
        sb3.append(LINE_SEP);
        input2File(sb3.toString(), sb2);
    }

    private static void printDeviceInfo(String str) {
        String str2 = "";
        int i2 = 0;
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(Utils.getApp().getPackageName(), 0);
            if (packageInfo != null) {
                str2 = packageInfo.versionName;
                i2 = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        input2File("************* Log Head ****************\nDate of Log        : " + str.substring(str.length() - 9, str.length() - 4) + "\nDevice Manufacturer: " + BaseInfo.getDeviceManufacture() + "\nDevice Model       : " + BaseInfo.getDeviceModel() + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + str2 + "\nApp VersionCode    : " + i2 + "\n************* Log Head ****************\n\n", str);
    }

    public static void setIsSaveToFile(boolean z) {
        isSaveToFile = z;
    }

    public static void setLog(boolean z) {
        isLog = z;
    }

    public static void v(String str, String str2) {
        if (isLog && !TextUtils.isEmpty(str2) && isSaveToFile) {
            file(str2);
        }
    }

    public static void w(String str, String str2) {
        if (isLog && !TextUtils.isEmpty(str2) && isSaveToFile) {
            file(str2);
        }
    }

    public static void d(String str) {
        d(DEFAULT_TAG, str);
    }

    public static void e(String str) {
        e(DEFAULT_TAG, str);
    }

    public static void i(String str) {
        i(DEFAULT_TAG, str);
    }

    public static void v(String str) {
        v(DEFAULT_TAG, str);
    }

    public static void w(String str) {
        w(DEFAULT_TAG, str);
    }
}
