package com.jdpay.lib.util;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class JPApp {
    public static String getSystemProperty(String str) {
        Process process;
        DataOutputStream dataOutputStream;
        try {
            process = Runtime.getRuntime().exec("getprop " + str);
            try {
                dataOutputStream = new DataOutputStream(process.getOutputStream());
            } catch (Throwable th) {
                th = th;
                dataOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            process = null;
            dataOutputStream = null;
        }
        try {
            String readLine = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8")).readLine();
            try {
                dataOutputStream.close();
            } catch (IOException unused) {
            }
            if (process != null) {
                process.destroy();
            }
            return readLine;
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
                return null;
            } finally {
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (process != null) {
                    process.destroy();
                }
            }
        }
    }

    public static boolean isDebuggable(@NonNull Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static boolean isEmulator() {
        DataOutputStream dataOutputStream;
        Throwable th;
        Process process;
        try {
            process = Runtime.getRuntime().exec("getprop ro.kernel.qemu");
            try {
                dataOutputStream = new DataOutputStream(process.getOutputStream());
            } catch (Throwable th2) {
                dataOutputStream = null;
                th = th2;
            }
            try {
                new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                JDPayLog.i("Result:" + process.waitFor());
                try {
                    dataOutputStream.close();
                } catch (IOException unused) {
                }
                if (process == null) {
                    return false;
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    th.printStackTrace();
                    if (process == null) {
                        return false;
                    }
                    return false;
                } finally {
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                }
            }
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
            process = null;
        }
        return false;
    }

    public static boolean isRoot() {
        DataOutputStream dataOutputStream;
        Throwable th;
        Process process;
        try {
            process = Runtime.getRuntime().exec("su");
            try {
                dataOutputStream = new DataOutputStream(process.getOutputStream());
            } catch (Throwable th2) {
                dataOutputStream = null;
                th = th2;
            }
        } catch (Throwable th3) {
            dataOutputStream = null;
            th = th3;
            process = null;
        }
        try {
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            int waitFor = process.waitFor();
            JDPayLog.i("Result:" + waitFor);
            boolean z = waitFor == 0;
            try {
                dataOutputStream.close();
            } catch (IOException unused) {
            }
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                return false;
            } finally {
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (process != null) {
                    process.destroy();
                }
            }
        }
    }
}
