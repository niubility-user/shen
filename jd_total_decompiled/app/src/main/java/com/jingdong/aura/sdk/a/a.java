package com.jingdong.aura.sdk.a;

import com.jingdong.aura.sdk.update.b.c;
import com.jingdong.aura.sdk.update.b.g;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class a {
    private static int a = -1408583183;

    /* JADX WARN: Multi-variable type inference failed */
    private static int a(File file, File file2) {
        Throwable th;
        FileChannel fileChannel;
        FileInputStream fileInputStream;
        c.a("deTransformV2...");
        if (!file.exists()) {
            c.b("origin file not exist!");
            return -1;
        }
        file2.delete();
        if (file2.getParentFile() != null) {
            file2.getParentFile().mkdirs();
        }
        long currentTimeMillis = System.currentTimeMillis();
        FileChannel fileChannel2 = null;
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            fileInputStream = new FileInputStream(file);
            int i2 = 4;
            try {
                byte[] bArr = new byte[4];
                fileInputStream.read(bArr);
                int a2 = a(bArr);
                int i3 = a;
                if (a2 != i3) {
                    c.a("no need deTransform");
                    a(fileInputStream);
                    a((Closeable) null);
                    a((Closeable) null);
                    c.a("deTransformV2 cost:" + (System.currentTimeMillis() - currentTimeMillis));
                    return 1;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    fileChannel2 = fileOutputStream.getChannel();
                    int i4 = 4096;
                    byte[] bArr2 = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        byte[] bArr3 = new byte[i4];
                        int i5 = 0;
                        while (i5 < read) {
                            int i6 = i5 + 3;
                            if (i6 >= read) {
                                i6 = read - 1;
                                a(bArr3, i5, Arrays.copyOfRange(bArr2, i5, i6 + 1), 0);
                            } else {
                                i3 ^= a(Arrays.copyOfRange(bArr2, i5, i6 + 1));
                                byte[] bArr4 = new byte[i2];
                                bArr4[0] = (byte) ((i3 >> 24) & 255);
                                bArr4[1] = (byte) ((i3 >> 16) & 255);
                                bArr4[2] = (byte) ((i3 >> 8) & 255);
                                bArr4[3] = (byte) (i3 & 255);
                                a(bArr3, i5, bArr4, 1);
                            }
                            i5 = i6 + 1;
                            i2 = 4;
                        }
                        if (read <= 0) {
                            c.a("deTransform success!");
                            a(fileInputStream);
                            a(fileChannel2);
                            a(fileOutputStream);
                            c.a("deTransformV2 cost:" + (System.currentTimeMillis() - currentTimeMillis));
                            return 2;
                        }
                        fileChannel2.write(ByteBuffer.wrap(bArr3, 0, read));
                        i2 = 4;
                        i4 = 4096;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = fileChannel2;
                    fileChannel2 = fileOutputStream;
                    try {
                        th.printStackTrace();
                        com.jingdong.aura.sdk.update.a.a().f12243m.onException(file.getName(), -1, "deTransform failed, md5:" + g.a(file.getAbsolutePath()), "BundleTrasnform.deTransformV2", th);
                        a(fileInputStream);
                        a(fileChannel);
                        a(fileChannel2);
                        c.a("deTransformV2 cost:" + (System.currentTimeMillis() - currentTimeMillis));
                        return -1;
                    } catch (Throwable th4) {
                        a(fileInputStream);
                        a(fileChannel);
                        a(fileChannel2);
                        c.a("deTransformV2 cost:" + (System.currentTimeMillis() - currentTimeMillis));
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                fileChannel = null;
            }
        } catch (Throwable th6) {
            th = th6;
            th = th;
            fileChannel = null;
            fileInputStream = null;
            th.printStackTrace();
            com.jingdong.aura.sdk.update.a.a().f12243m.onException(file.getName(), -1, "deTransform failed, md5:" + g.a(file.getAbsolutePath()), "BundleTrasnform.deTransformV2", th);
            a(fileInputStream);
            a(fileChannel);
            a(fileChannel2);
            c.a("deTransformV2 cost:" + (System.currentTimeMillis() - currentTimeMillis));
            return -1;
        }
    }

    public static int a(String str, String str2) {
        return a(new File(str), new File(str2));
    }

    private static int a(byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    private static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }

    private static void a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        if (i3 != 1) {
            while (i4 < bArr2.length) {
                bArr[i2 + i4] = bArr2[i4];
                i4++;
            }
        } else if (bArr2.length != 4) {
            throw new IllegalArgumentException("tartet not a int!");
        } else {
            while (i4 < 4) {
                bArr[i2 + i4] = bArr2[i4];
                i4++;
            }
        }
    }
}
