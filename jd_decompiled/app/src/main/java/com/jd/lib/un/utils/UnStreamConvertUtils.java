package com.jd.lib.un.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes16.dex */
public class UnStreamConvertUtils {
    public static InputStream bytes2InputStream(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.OutputStream bytes2OutputStream(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L39
            int r1 = r2.length
            if (r1 > 0) goto L7
            goto L39
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1c
            r1.<init>()     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1c
            r1.write(r2)     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L2c
            r1.close()     // Catch: java.io.IOException -> L13
            goto L17
        L13:
            r2 = move-exception
            r2.printStackTrace()
        L17:
            return r1
        L18:
            r2 = move-exception
            goto L1e
        L1a:
            r2 = move-exception
            goto L2e
        L1c:
            r2 = move-exception
            r1 = r0
        L1e:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L2c
            if (r1 == 0) goto L2b
            r1.close()     // Catch: java.io.IOException -> L27
            goto L2b
        L27:
            r2 = move-exception
            r2.printStackTrace()
        L2b:
            return r0
        L2c:
            r2 = move-exception
            r0 = r1
        L2e:
            if (r0 == 0) goto L38
            r0.close()     // Catch: java.io.IOException -> L34
            goto L38
        L34:
            r0 = move-exception
            r0.printStackTrace()
        L38:
            throw r2
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.UnStreamConvertUtils.bytes2OutputStream(byte[]):java.io.OutputStream");
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                inputStream.close();
                return byteArrayOutputStream;
            } catch (IOException e3) {
                e3.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            throw th;
        }
    }

    public static byte[] inputStream2Bytes(InputStream inputStream) {
        ByteArrayOutputStream input2OutputStream;
        if (inputStream == null || (input2OutputStream = input2OutputStream(inputStream)) == null) {
            return null;
        }
        return input2OutputStream.toByteArray();
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !UnStringUtils.isSpace(str)) {
            try {
                return new String(inputStream2Bytes(inputStream), str);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static byte[] outputStream2Bytes(OutputStream outputStream) {
        if (outputStream != null && (outputStream instanceof ByteArrayOutputStream)) {
            return ((ByteArrayOutputStream) outputStream).toByteArray();
        }
        return null;
    }

    public static String outputStream2String(OutputStream outputStream, String str) {
        if (outputStream != null && !UnStringUtils.isSpace(str)) {
            try {
                return new String(outputStream2Bytes(outputStream), str);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static InputStream string2InputStream(String str, String str2) {
        if (str != null && !UnStringUtils.isSpace(str2)) {
            try {
                return new ByteArrayInputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static OutputStream string2OutputStream(String str, String str2) {
        if (str != null && !UnStringUtils.isSpace(str2)) {
            try {
                return bytes2OutputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public ByteArrayInputStream output2InputStream(OutputStream outputStream) {
        if (outputStream != null && (outputStream instanceof ByteArrayOutputStream)) {
            return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
        }
        return null;
    }
}
