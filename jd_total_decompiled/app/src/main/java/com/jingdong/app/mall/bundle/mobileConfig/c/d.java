package com.jingdong.app.mall.bundle.mobileConfig.c;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* loaded from: classes12.dex */
public class d {
    private static File a = a();

    private static File a() {
        File file = new File(new File(com.jingdong.app.mall.bundle.mobileConfig.a.l().f().getFilesDir(), "Mconfig"), "config");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String a(Context context, String str) {
        Throwable th;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader2 = null;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(str), "UTF-8");
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader2 = inputStreamReader;
                        try {
                            th.printStackTrace();
                            inputStreamReader = inputStreamReader2;
                            a((Closeable) bufferedReader);
                            a((Closeable) inputStreamReader);
                            return sb.toString();
                        } catch (Throwable th3) {
                            a((Closeable) bufferedReader);
                            a((Closeable) inputStreamReader2);
                            throw th3;
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
        }
        a((Closeable) bufferedReader);
        a((Closeable) inputStreamReader);
        return sb.toString();
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean a(Object obj) {
        ObjectOutputStream objectOutputStream;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(a, "MConfigObj");
            file.delete();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream2));
            } catch (Throwable th) {
                th = th;
                objectOutputStream = null;
            }
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                a((Closeable) objectOutputStream);
                a((Closeable) fileOutputStream2);
                return true;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                try {
                    th.printStackTrace();
                    a((Closeable) objectOutputStream);
                    a((Closeable) fileOutputStream);
                    return false;
                } catch (Throwable th3) {
                    a((Closeable) objectOutputStream);
                    a((Closeable) fileOutputStream);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            objectOutputStream = null;
        }
    }

    public static Object b() {
        Throwable th;
        ObjectInputStream objectInputStream;
        FileInputStream fileInputStream;
        File file;
        try {
            file = new File(a, "MConfigObj");
        } catch (Throwable th2) {
            th = th2;
            objectInputStream = null;
            fileInputStream = null;
        }
        if (!file.exists()) {
            a((Closeable) null);
            a((Closeable) null);
            return null;
        }
        fileInputStream = new FileInputStream(file);
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream));
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
        }
        try {
            return objectInputStream.readObject();
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                return null;
            } finally {
                a((Closeable) objectInputStream);
                a((Closeable) fileInputStream);
            }
        }
    }
}
