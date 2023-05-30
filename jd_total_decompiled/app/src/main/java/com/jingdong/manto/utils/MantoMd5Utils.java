package com.jingdong.manto.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes16.dex */
public class MantoMd5Utils {
    protected static char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static ThreadLocal<MessageDigest> b = new a();

    /* loaded from: classes16.dex */
    class a extends ThreadLocal<MessageDigest> {
        a() {
        }

        private MessageDigest b() {
            try {
                return MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            } catch (Throwable th) {
                throw new RuntimeException("Initialize MD5 failed.", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final MessageDigest initialValue() {
            return b();
        }
    }

    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return md5OfFile(file);
        }
        return null;
    }

    public static String md5Bytes(byte[] bArr) {
        byte[] digest = b.get().digest(bArr);
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b2 : digest) {
            char[] cArr = a;
            char c2 = cArr[(b2 & 240) >> 4];
            char c3 = cArr[b2 & 15];
            sb.append(c2);
            sb.append(c3);
        }
        return sb.toString();
    }

    public static final String md5OfBytes(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b2 : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b2 >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public static String md5OfFile(File file) {
        FileInputStream fileInputStream;
        InputStream inputStream = null;
        String str = null;
        if (file != null) {
            ?? exists = file.exists();
            try {
                if (exists != 0) {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        t.a(inputStream);
                        throw th;
                    }
                    try {
                        str = md5OfStream(fileInputStream, (int) (102400 > file.length() ? file.length() : 102400L));
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        e.printStackTrace();
                        t.a(fileInputStream);
                        return str;
                    }
                    t.a(fileInputStream);
                    return str;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = exists;
            }
        }
        return null;
    }

    public static String md5OfStream(InputStream inputStream, int i2) {
        int i3;
        if (inputStream != null && i2 > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                StringBuilder sb = new StringBuilder(32);
                byte[] bArr = new byte[i2];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                for (byte b2 : messageDigest.digest()) {
                    sb.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
                }
                return sb.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String md5OfString(String str) {
        return md5Bytes(str.getBytes());
    }
}
