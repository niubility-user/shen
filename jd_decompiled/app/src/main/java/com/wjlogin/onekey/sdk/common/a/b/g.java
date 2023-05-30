package com.wjlogin.onekey.sdk.common.a.b;

import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes11.dex */
public class g {
    private static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static RSAPrivateKey f18349c;
    private static RSAPublicKey d;

    static {
        d();
        a = "";
        b = "";
    }

    public static void a() {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RSAUtils.KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            keyPairGenerator = null;
        }
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        f18349c = (RSAPrivateKey) generateKeyPair.getPrivate();
        d = (RSAPublicKey) generateKeyPair.getPublic();
    }

    public static RSAPrivateKey b() {
        return f18349c;
    }

    public static RSAPublicKey c() {
        return d;
    }

    public static void d() {
        a = d.f18348e;
        try {
            b(d.f18348e);
            System.out.println("\u52a0\u8f7d\u516c\u94a5\u6210\u529f");
        } catch (Exception e2) {
            System.err.println(e2.getMessage());
            System.err.println("\u52a0\u8f7d\u516c\u94a5\u5931\u8d25");
        }
        try {
            a(b);
            System.out.println("\u52a0\u8f7d\u79c1\u94a5\u6210\u529f");
        } catch (Exception e3) {
            System.err.println(e3.getMessage());
            System.err.println("\u52a0\u8f7d\u79c1\u94a5\u5931\u8d25");
        }
    }

    public static void b(InputStream inputStream) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.charAt(0) != '-') {
                        sb.append(readLine);
                        sb.append('\r');
                    }
                } else {
                    b(sb.toString());
                    return;
                }
            }
        } catch (IOException unused) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u6d41\u8bfb\u53d6\u9519\u8bef");
        } catch (NullPointerException unused2) {
            throw new Exception("\u516c\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }

    public static void a(InputStream inputStream) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.charAt(0) != '-') {
                        sb.append(readLine);
                        sb.append('\r');
                    }
                } else {
                    a(sb.toString());
                    return;
                }
            }
        } catch (IOException unused) {
            throw new Exception("\u79c1\u94a5\u6570\u636e\u8bfb\u53d6\u9519\u8bef");
        } catch (NullPointerException unused2) {
            throw new Exception("\u79c1\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }

    public static void b(String str) throws Exception {
        try {
            d = (RSAPublicKey) KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        }
    }

    public static void a(String str) throws Exception {
        try {
            f18349c = (RSAPrivateKey) KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("\u79c1\u94a5\u6570\u636e\u4e3a\u7a7a");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("\u79c1\u94a5\u975e\u6cd5");
        }
    }

    public static String a(RSAPublicKey rSAPublicKey, String str) throws Exception {
        if (rSAPublicKey != null && !h.m(str)) {
            byte[] bytes = str.getBytes();
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, rSAPublicKey);
                return b.a(cipher.doFinal(bytes));
            } catch (InvalidKeyException unused) {
                throw new Exception("\u52a0\u5bc6\u516c\u94a5\u975e\u6cd5,\u8bf7\u68c0\u67e5");
            } catch (NoSuchAlgorithmException unused2) {
                throw new Exception("\u65e0\u6b64\u52a0\u5bc6\u7b97\u6cd5");
            } catch (BadPaddingException unused3) {
                throw new Exception("\u660e\u6587\u6570\u636e\u5df2\u635f\u574f");
            } catch (IllegalBlockSizeException unused4) {
                throw new Exception("\u660e\u6587\u957f\u5ea6\u975e\u6cd5");
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        throw new Exception("\u52a0\u5bc6\u516c\u94a5\u4e3a\u7a7a, \u8bf7\u8bbe\u7f6e");
    }

    public static String a(RSAPrivateKey rSAPrivateKey, String str) throws Exception {
        if (rSAPrivateKey != null && !h.m(str)) {
            byte[] a2 = b.a(str);
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, rSAPrivateKey);
                return new String(cipher.doFinal(a2));
            } catch (InvalidKeyException unused) {
                throw new Exception("\u89e3\u5bc6\u79c1\u94a5\u975e\u6cd5,\u8bf7\u68c0\u67e5");
            } catch (NoSuchAlgorithmException unused2) {
                throw new Exception("\u65e0\u6b64\u89e3\u5bc6\u7b97\u6cd5");
            } catch (BadPaddingException unused3) {
                throw new Exception("\u5bc6\u6587\u6570\u636e\u5df2\u635f\u574f");
            } catch (IllegalBlockSizeException unused4) {
                throw new Exception("\u5bc6\u6587\u957f\u5ea6\u975e\u6cd5");
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        throw new Exception("\u89e3\u5bc6\u79c1\u94a5\u4e3a\u7a7a, \u8bf7\u8bbe\u7f6e");
    }
}
