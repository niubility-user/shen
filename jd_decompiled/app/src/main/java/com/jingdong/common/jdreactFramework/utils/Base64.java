package com.jingdong.common.jdreactFramework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class Base64 {
    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static String decode(String str) throws RuntimeException {
        try {
            return new String(decode(str.getBytes("ASCII")));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("ASCII is not supported!", e2);
        }
    }

    public static String encode(String str) throws RuntimeException {
        try {
            return new String(encode(str.getBytes()), "ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("ASCII is not supported!", e2);
        }
    }

    public static String decode(String str, String str2) throws RuntimeException {
        try {
            try {
                return new String(decode(str.getBytes("ASCII")), str2);
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException("Unsupported charset: " + str2, e2);
            }
        } catch (UnsupportedEncodingException e3) {
            throw new RuntimeException("ASCII is not supported!", e3);
        }
    }

    public static String encode(String str, String str2) throws RuntimeException {
        try {
            try {
                return new String(encode(str.getBytes(str2)), "ASCII");
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException("ASCII is not supported!", e2);
            }
        } catch (UnsupportedEncodingException e3) {
            throw new RuntimeException("Unsupported charset: " + str2, e3);
        }
    }

    public static byte[] decode(byte[] bArr) throws RuntimeException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                decode(byteArrayInputStream, byteArrayOutputStream);
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused2) {
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused3) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused4) {
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new RuntimeException("Unexpected I/O error", e2);
        }
    }

    public static byte[] encode(byte[] bArr) throws RuntimeException {
        return encode(bArr, 0);
    }

    public static byte[] encode(byte[] bArr, int i2) throws RuntimeException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                encode(byteArrayInputStream, byteArrayOutputStream, i2);
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused2) {
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused3) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused4) {
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new RuntimeException("Unexpected I/O error", e2);
        }
    }

    public static void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        Base64InputStream base64InputStream = new Base64InputStream(inputStream);
        copy(base64InputStream, outputStream);
        try {
            base64InputStream.close();
            AutoCloseable autoCloseable = null;
            if (0 != 0) {
                autoCloseable.close();
            }
        } catch (Throwable unused) {
            base64InputStream.close();
        }
    }

    public static void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        encode(inputStream, outputStream, 0);
    }

    public static void encode(InputStream inputStream, OutputStream outputStream, int i2) throws IOException {
        Base64OutputStream base64OutputStream = null;
        try {
            try {
                Base64OutputStream base64OutputStream2 = new Base64OutputStream(outputStream, i2);
                try {
                    copy(inputStream, base64OutputStream2);
                    base64OutputStream2.commit();
                    try {
                        base64OutputStream2.close();
                    } catch (IOException e2) {
                        throw e2;
                    }
                } catch (IOException e3) {
                } catch (Throwable th) {
                    th = th;
                    base64OutputStream = base64OutputStream2;
                    if (base64OutputStream != null) {
                        try {
                            base64OutputStream.close();
                        } catch (IOException e4) {
                            throw e4;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            throw e5;
        }
    }

    public static void decode(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            decode(fileInputStream, fileOutputStream);
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            try {
                fileInputStream.close();
            } catch (Throwable unused2) {
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable unused3) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused4) {
                }
            }
            throw th;
        }
    }

    public static void encode(File file, File file2, int i2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            encode(fileInputStream, fileOutputStream, i2);
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            try {
                fileInputStream.close();
            } catch (Throwable unused2) {
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable unused3) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused4) {
                }
            }
            throw th;
        }
    }

    public static void encode(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            encode(fileInputStream, fileOutputStream);
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            try {
                fileInputStream.close();
            } catch (Throwable unused2) {
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable unused3) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused4) {
                }
            }
            throw th;
        }
    }
}
