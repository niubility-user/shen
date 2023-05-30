package com.jingdong.manto.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.a;
import com.jingdong.manto.R;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes16.dex */
public class MantoCryptoUtils {
    private static String a = null;
    private static String b = "AES";

    /* renamed from: c */
    private static String f14294c = "AES/ECB/PKCS5Padding";
    private static byte[] d = {ReplyCode.reply0x26, ReplyCode.reply0x38, 11, ReplyCode.reply0x64, -92, -85, 114, -41, -63, 30, ReplyCode.reply0x7b, ReplyCode.reply0x88, ReplyCode.reply0xaa, 102, 10, -32};

    private static String a() {
        Context a2;
        String str = a;
        if (str != null) {
            return str;
        }
        a.C0232a g2 = com.jingdong.manto.b.g();
        if (g2 != null && (a2 = g2.a()) != null) {
            String string = a2.getString(R.string.manto_start_key);
            String string2 = a2.getString(R.string.manto_end_key);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                if (string.length() >= 8) {
                    String str2 = "" + string.charAt(4) + string.charAt(5) + string.charAt(6) + string.charAt(7) + string2 + string.charAt(0) + string.charAt(1) + string.charAt(2) + string.charAt(3);
                    a = str2;
                    return str2;
                }
                a = string2;
            }
        }
        return a;
    }

    public static String a(String str) {
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        try {
            return new String(com.jingdong.manto.j.b.a(l.a(str), a2.getBytes(), (byte[]) null));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) {
        try {
            byte[] bytes = ("6A642D6D" + str2).getBytes("UTF-8");
            for (int i2 = 0; i2 < 16; i2++) {
                bytes[i2] = (byte) (bytes[i2] ^ d[i2]);
            }
            String str3 = str + "D4F1E5BBBE321897A0F4BDEC91197EE0";
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, b);
            Cipher cipher = Cipher.getInstance(f14294c);
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(b(str3.getBytes("UTF-8"))), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return "";
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e6) {
            e6.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e7) {
            e7.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; bArr != null && i2 < bArr.length; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return a(mac.doFinal(bArr));
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String a2 = a();
        return TextUtils.isEmpty(a2) ? "" : com.jingdong.manto.j.b.a(new String(l.a(str2)), a2, (byte[]) null);
    }

    private static byte[] b(byte[] bArr) {
        if (bArr.length % 2 != 0) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length / 2];
        for (int i2 = 0; i2 < bArr.length; i2 += 2) {
            bArr2[i2 / 2] = (byte) Integer.parseInt(new String(bArr, i2, 2), 16);
        }
        return bArr2;
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        String b2 = com.jingdong.manto.j.b.b(str2, a2, (byte[]) null);
        return l.a((b2 != null ? b2 : "").getBytes());
    }

    public static String encrypt(String str) {
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        try {
            return l.a(com.jingdong.manto.j.b.b(str.getBytes(), a2.getBytes(), (byte[]) null));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
