package com.jd.stat.common;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes18.dex */
public class e {
    private static String a = "TracerPid";

    public static String a() {
        try {
            return ActivityManager.isUserAMonkey() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0051, code lost:
        if (java.lang.Integer.decode(r0.substring(com.jd.stat.common.e.a.length() + 1).trim()).intValue() <= 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0055, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005a, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0062, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0066, code lost:
        return "0";
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:?, code lost:
        return "0";
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b() {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e2;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/self/status")), 1000);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.length() > a.length() && readLine.substring(0, a.length()).equalsIgnoreCase(a)) {
                                break;
                            }
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return "c";
                    }
                }
                return "1";
            } catch (Exception e5) {
                bufferedReader = null;
                e2 = e5;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    bufferedReader2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String c() {
        try {
            return Debug.isDebuggerConnected() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String d() {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e2;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                int g2 = g();
                if (g2 > 0) {
                    File file = new File("/proc/" + g2 + "/cmdline");
                    if (file.exists()) {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)), 1000);
                        do {
                            try {
                                readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    bufferedReader2 = bufferedReader;
                                }
                            } catch (Exception e3) {
                                e2 = e3;
                                e2.printStackTrace();
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                return "c";
                            }
                        } while (!readLine.contains("gdb"));
                        bufferedReader.close();
                        return "1";
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                        return "0";
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return "0";
                    }
                }
                return "0";
            } catch (Exception e6) {
                bufferedReader = null;
                e2 = e6;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    bufferedReader2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String e() {
        try {
            return EncryptUtil.androidServerDetected() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    public static String f() {
        try {
            File[] listFiles = new File("/proc/self/fd").listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return "0";
            }
            for (File file : listFiles) {
                if ("5".equals(file.getName())) {
                    return "1";
                }
            }
            return "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    private static int g() {
        Process process;
        try {
            try {
                process = (Process) Process.class.newInstance();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            }
            return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return 0;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return 0;
        }
    }
}
