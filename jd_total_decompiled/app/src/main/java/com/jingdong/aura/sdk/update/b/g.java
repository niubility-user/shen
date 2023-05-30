package com.jingdong.aura.sdk.update.b;

import android.text.TextUtils;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipFile;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes4.dex */
public final class g {

    /* loaded from: classes4.dex */
    public static class a {
        public boolean a;
        public String b;

        public a(boolean z, String str) {
            this.a = z;
            this.b = str;
        }

        public final String toString() {
            return "BundleDownloadState{isCompleted=" + this.a + ", msg='" + this.b + "'}";
        }
    }

    public static synchronized Object a() {
        ObjectInputStream objectInputStream;
        File file;
        synchronized (g.class) {
            try {
                file = new File(com.jingdong.aura.sdk.update.a.a().f12241k, "auraUpdateObj");
            } catch (Throwable th) {
                th = th;
                objectInputStream = null;
            }
            if (!file.exists()) {
                a((Closeable) null);
                a((Closeable) null);
                return null;
            }
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                Object readObject = objectInputStream.readObject();
                a((Closeable) objectInputStream);
                a((Closeable) objectInputStream);
                return readObject;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                a((Closeable) objectInputStream);
                a((Closeable) objectInputStream);
                return null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String str) {
        FileInputStream fileInputStream;
        ?? r1 = 0;
        try {
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                    File file = new File(str);
                    if (file.exists() && file.isFile()) {
                        fileInputStream = new FileInputStream(file);
                        try {
                            messageDigest.update(fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
                            byte[] digest = messageDigest.digest();
                            StringBuilder sb = new StringBuilder(digest.length * 2);
                            for (byte b : digest) {
                                sb.append("0123456789abcdef".charAt((b >> 4) & 15));
                                sb.append("0123456789abcdef".charAt(b & 15));
                            }
                            String sb2 = sb.toString();
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            return sb2;
                        } catch (FileNotFoundException e3) {
                            e = e3;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (IOException unused) {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (NoSuchAlgorithmException e4) {
                            e = e4;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        }
                    }
                    return null;
                } catch (FileNotFoundException e5) {
                    e = e5;
                    fileInputStream = null;
                } catch (IOException unused2) {
                    fileInputStream = null;
                } catch (NoSuchAlgorithmException e6) {
                    e = e6;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    if (r1 != 0) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = str;
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(File file) {
        if (file != null) {
            try {
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            a(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(AuraBundleResult auraBundleResult) {
        String str;
        if (auraBundleResult == null) {
            str = "isCompleted: bundleResult is null";
        } else {
            File file = new File(auraBundleResult.downloadedFilePath);
            if (file.exists()) {
                String a2 = a(file.getAbsolutePath());
                c.a("FileUtil", "isCompleted: " + auraBundleResult.updateId + ",expected md5:" + auraBundleResult.md5 + ",downloaded File md5:" + a2);
                return (TextUtils.isEmpty(a2) || TextUtils.isEmpty(auraBundleResult.md5) || !a2.toLowerCase().equals(auraBundleResult.md5.toLowerCase())) ? false : true;
            }
            str = "isCompleted: downloadedFile not exist:" + auraBundleResult.updateId;
        }
        c.b("FileUtil", str);
        return false;
    }

    public static synchronized boolean a(Object obj) {
        FileOutputStream fileOutputStream;
        boolean z;
        synchronized (g.class) {
            ObjectOutputStream objectOutputStream = null;
            try {
                try {
                    File file = new File(com.jingdong.aura.sdk.update.a.a().f12241k, "auraUpdateObj");
                    file.delete();
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
                        try {
                            objectOutputStream2.writeObject(obj);
                            objectOutputStream2.flush();
                            a((Closeable) objectOutputStream2);
                            a((Closeable) fileOutputStream);
                            z = true;
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            th.printStackTrace();
                            a((Closeable) objectOutputStream);
                            a((Closeable) fileOutputStream);
                            z = false;
                            return z;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        return z;
    }
}
