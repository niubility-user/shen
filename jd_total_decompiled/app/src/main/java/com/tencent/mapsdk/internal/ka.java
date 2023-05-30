package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes9.dex */
public class ka {
    private static final String a = "LibaryLoaderHelper";
    private static boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ boolean f16766c = true;

    public static File a(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    private static File a(Context context, String str) {
        return new File(a(context), System.mapLibraryName(str));
    }

    private static ZipEntry a(ZipFile zipFile, String str) {
        ZipEntry entry = zipFile.getEntry("lib/" + Build.CPU_ABI + "/" + System.mapLibraryName(str));
        if (entry != null) {
            return entry;
        }
        return zipFile.getEntry("lib/" + Build.CPU_ABI2 + "/" + System.mapLibraryName(str));
    }

    private static void a(File file) {
        try {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.getName();
                    if (!file2.delete() && Log.isLoggable(a, 6)) {
                        String str = "Failed to remove " + file2.getAbsolutePath();
                    }
                }
            }
            if (file.delete() || !Log.isLoggable(a, 6)) {
                return;
            }
            String str2 = "Failed to remove " + file.getAbsolutePath();
        } catch (Exception unused) {
            Log.isLoggable(a, 6);
        }
    }

    private static boolean b(Context context) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        if (b) {
            return false;
        }
        b = true;
        File a2 = a(context);
        a(a2);
        try {
            ZipFile zipFile = new ZipFile(new File(context.getApplicationInfo().sourceDir), 1);
            for (String str : ja.b) {
                ZipEntry a3 = a(zipFile, str);
                if (a3 == null) {
                    zipFile.close();
                    a(a2);
                    return false;
                }
                String name = a3.getName();
                if (name != null && (name.contains("../") || name.contains(".."))) {
                    zipFile.close();
                    a(a2);
                    return false;
                }
                File a4 = a(context, str);
                if (Log.isLoggable(a, 4)) {
                    String str2 = "Extracting native libraries into " + a4.getAbsolutePath();
                }
                if (!f16766c && a4.exists()) {
                    throw new AssertionError();
                }
                try {
                    if (!a4.createNewFile()) {
                        throw new IOException();
                    }
                    FileOutputStream fileOutputStream2 = null;
                    try {
                        inputStream = zipFile.getInputStream(a3);
                        try {
                            fileOutputStream = new FileOutputStream(a4);
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            byte[] bArr = new byte[16384];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            inputStream.close();
                            fileOutputStream.close();
                            if (Build.VERSION.SDK_INT >= 9) {
                                a4.setReadable(true, false);
                                a4.setExecutable(true, false);
                                a4.setWritable(true);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream2 = fileOutputStream;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = null;
                    }
                } catch (IOException e2) {
                    if (a4.exists() && !a4.delete() && Log.isLoggable(a, 6)) {
                        String str3 = "Failed to delete " + a4.getAbsolutePath();
                    }
                    zipFile.close();
                    throw e2;
                }
            }
            zipFile.close();
            return true;
        } catch (IOException unused) {
            Log.isLoggable(a, 6);
            a(a2);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        if (f16766c || context != null) {
            File a2 = a(context, str);
            boolean exists = a2.exists();
            if (Log.isLoggable(a, 4)) {
                String str2 = "libary:" + a2.getAbsolutePath() + " is exist:" + exists;
            }
            if (exists || b(context)) {
                try {
                    System.load(a2.getAbsolutePath());
                    return true;
                } catch (UnsatisfiedLinkError unused) {
                    return false;
                }
            }
            return false;
        }
        throw new AssertionError();
    }
}
