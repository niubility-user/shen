package com.jingdong.aura.core.util;

import android.text.TextUtils;
import com.jingdong.aura.a.c.l;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class d {
    public static String a(String str) {
        String b = b(str);
        if (b == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        byte[] bytes = b.getBytes();
        int length = bytes.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = i3 >= length ? i3 - length : i3;
            int i5 = i2 + 4;
            if (i5 >= length) {
                i5 -= length;
            }
            sb.append("e70d12f4985ac3b6".charAt((bytes[i2] + bytes[i4] + bytes[i5]) & 15));
            i2 = i3;
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r9) {
        /*
            java.lang.String r0 = "0123456789abcdef"
            r1 = 0
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            boolean r9 = r3.exists()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            if (r9 == 0) goto L83
            boolean r9 = r3.isFile()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            if (r9 != 0) goto L1c
            goto L83
        L1c:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            r9.<init>(r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L88
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
            r3.<init>()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r4]     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
        L2a:
            r6 = 0
            int r7 = r9.read(r5, r6, r4)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r8 = -1
            if (r7 == r8) goto L36
            r3.write(r5, r6, r7)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            goto L2a
        L36:
            byte[] r4 = r3.toByteArray()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r2.update(r4)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            byte[] r2 = r2.digest()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            int r5 = r2.length     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            int r5 = r5 * 2
            r4.<init>(r5)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            int r5 = r2.length     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
        L4a:
            if (r6 >= r5) goto L65
            r7 = r2[r6]     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            int r8 = r7 >> 4
            r8 = r8 & 15
            char r8 = r0.charAt(r8)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r4.append(r8)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r7 = r7 & 15
            char r7 = r0.charAt(r7)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r4.append(r7)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            int r6 = r6 + 1
            goto L4a
        L65:
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> La4
            r9.close()     // Catch: java.lang.Exception -> L6d
            goto L71
        L6d:
            r9 = move-exception
            r9.printStackTrace()
        L71:
            r3.close()     // Catch: java.lang.Exception -> L75
            goto L79
        L75:
            r9 = move-exception
            r9.printStackTrace()
        L79:
            return r0
        L7a:
            r0 = move-exception
            goto L8c
        L7c:
            r0 = move-exception
            r3 = r1
        L7e:
            r1 = r9
            goto La6
        L80:
            r0 = move-exception
            r3 = r1
            goto L8c
        L83:
            return r1
        L84:
            r9 = move-exception
            r0 = r9
            r3 = r1
            goto La6
        L88:
            r9 = move-exception
            r0 = r9
            r9 = r1
            r3 = r9
        L8c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> La4
            if (r9 == 0) goto L99
            r9.close()     // Catch: java.lang.Exception -> L95
            goto L99
        L95:
            r9 = move-exception
            r9.printStackTrace()
        L99:
            if (r3 == 0) goto La3
            r3.close()     // Catch: java.lang.Exception -> L9f
            goto La3
        L9f:
            r9 = move-exception
            r9.printStackTrace()
        La3:
            return r1
        La4:
            r0 = move-exception
            goto L7e
        La6:
            if (r1 == 0) goto Lb0
            r1.close()     // Catch: java.lang.Exception -> Lac
            goto Lb0
        Lac:
            r9 = move-exception
            r9.printStackTrace()
        Lb0:
            if (r3 == 0) goto Lba
            r3.close()     // Catch: java.lang.Exception -> Lb6
            goto Lba
        Lb6:
            r9 = move-exception
            r9.printStackTrace()
        Lba:
            goto Lbc
        Lbb:
            throw r0
        Lbc:
            goto Lbb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.util.d.b(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x005e -> B:70:0x0061). Please submit an issue!!! */
    public static void a(InputStream inputStream, File file) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileChannel fileChannel;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileChannel = fileOutputStream.getChannel();
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (Throwable th3) {
                fileOutputStream = null;
                th = th3;
                fileChannel = null;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileChannel.write(ByteBuffer.wrap(bArr, 0, read));
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                fileOutputStream.close();
            } catch (Throwable th4) {
                th = th4;
                try {
                    th.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th5) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                    }
                    throw th5;
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    public static File a() {
        File filesDir = l.a.getFilesDir();
        if (filesDir == null) {
            return new File("/data/data/" + l.a.getPackageName() + "/files");
        }
        return filesDir;
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

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && new File(str).exists() && new File(str2).exists()) {
            String b = b(str);
            String b2 = b(str2);
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
                return b.equals(b2);
            }
        }
        return false;
    }
}
