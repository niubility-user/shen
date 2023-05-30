package com.jingdong.common.jdreactFramework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes5.dex */
public class Base64Utils {
    private static final int CACHE_SIZE = 1024;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005d A[Catch: Exception -> 0x0059, TRY_LEAVE, TryCatch #5 {Exception -> 0x0059, blocks: (B:36:0x0055, B:40:0x005d), top: B:50:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void byteArrayToFile(byte[] bArr, String str) throws Exception {
        ?? r1;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            r1 = new ByteArrayInputStream(bArr);
            try {
                File file = new File(str);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                if (fileOutputStream2 != null) {
                }
                if (r1 != 0) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            r1 = 0;
        }
        try {
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = r1.read(bArr2);
                if (read != -1) {
                    fileOutputStream.write(bArr2, 0, read);
                    fileOutputStream.flush();
                } else {
                    try {
                        fileOutputStream.close();
                        r1.close();
                        return;
                    } catch (Exception e4) {
                        throw e4;
                    }
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream2 = r1;
            try {
                throw e;
            } catch (Throwable th3) {
                th = th3;
                r1 = fileOutputStream2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e6) {
                        throw e6;
                    }
                }
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
            }
            if (r1 != 0) {
            }
            throw th;
        }
    }

    public static byte[] decode(String str) throws Exception {
        return Base64.decode(str.getBytes());
    }

    public static void decodeToFile(String str, String str2) throws Exception {
        byteArrayToFile(decode(str2), str);
    }

    public static String encode(byte[] bArr) throws Exception {
        return new String(Base64.encode(bArr));
    }

    public static String encodeFile(String str) throws Exception {
        return encode(fileToByte(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0063 A[Catch: Exception -> 0x005f, TRY_LEAVE, TryCatch #0 {Exception -> 0x005f, blocks: (B:39:0x005b, B:43:0x0063), top: B:49:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] fileToByte(String str) throws Exception {
        byte[] bArr;
        FileInputStream fileInputStream;
        ?? r1 = new byte[0];
        ?? file = new File(str);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                if (file.exists()) {
                    FileInputStream fileInputStream2 = new FileInputStream((File) file);
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(2048);
                        try {
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = fileInputStream2.read(bArr2);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr2, 0, read);
                                byteArrayOutputStream2.flush();
                            }
                            bArr = byteArrayOutputStream2.toByteArray();
                            byteArrayOutputStream = byteArrayOutputStream2;
                            fileInputStream = fileInputStream2;
                        } catch (Exception e2) {
                            throw e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        file = 0;
                        r1 = fileInputStream2;
                        th = th;
                        if (file != 0) {
                        }
                        if (r1 != 0) {
                        }
                        throw th;
                    }
                } else {
                    bArr = r1;
                    fileInputStream = null;
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                        throw e4;
                    }
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (Exception e5) {
                e = e5;
            } catch (Throwable th2) {
                th = th2;
                r1 = 0;
                file = 0;
            }
        } catch (Throwable th3) {
            th = th3;
            if (file != 0) {
                try {
                    file.close();
                } catch (Exception e6) {
                    throw e6;
                }
            }
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
    }
}
