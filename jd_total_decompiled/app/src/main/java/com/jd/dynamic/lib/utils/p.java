package com.jd.dynamic.lib.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class p {
    public static String a(String str) {
        return b(str, "");
    }

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append("0123456789abcdef".charAt((b >> 4) & 15));
                sb.append("0123456789abcdef".charAt(b & 15));
            }
            return new String(sb).toUpperCase();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static String c(String str) {
        FileInputStream fileInputStream;
        NoSuchAlgorithmException e2;
        IOException e3;
        FileNotFoundException e4;
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        try {
                            fileInputStream = new FileInputStream(file);
                        } catch (FileNotFoundException e5) {
                            fileInputStream = null;
                            e4 = e5;
                        } catch (IOException e6) {
                            fileInputStream = null;
                            e3 = e6;
                        } catch (NoSuchAlgorithmException e7) {
                            fileInputStream = null;
                            e2 = e7;
                        } catch (Throwable th) {
                            th = th;
                            if (0 != 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            throw th;
                        }
                        try {
                            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                messageDigest.update(bArr, 0, read);
                            }
                            str2 = new BigInteger(1, messageDigest.digest()).toString(16);
                            while (str2.length() < 32) {
                                str2 = "0" + str2;
                            }
                            fileInputStream.close();
                        } catch (FileNotFoundException e9) {
                            e4 = e9;
                            e4.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return str2;
                        } catch (IOException e10) {
                            e3 = e10;
                            e3.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return str2;
                        } catch (NoSuchAlgorithmException e11) {
                            e2 = e11;
                            e2.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return str2;
                        }
                    } catch (IOException e12) {
                        e12.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        return str2;
    }
}
